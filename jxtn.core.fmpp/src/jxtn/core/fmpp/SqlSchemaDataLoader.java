/*
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <http://unlicense.org/>
 */

package jxtn.core.fmpp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import fmpp.Engine;
import fmpp.dataloaders.XmlDataLoader;

/**
 * 以資料庫schema做為資料來源的載入器
 * <p>
 * 目前僅支援SQL Server
 * </p>
 *
 * @author AqD
 */
public class SqlSchemaDataLoader extends XmlDataLoader
{
    @Override
    public Object load(Engine engine, @SuppressWarnings("rawtypes") java.util.List args) throws Exception
    {
        if (args.size() < 1)
        {
            throw new IllegalArgumentException("sql(url[, options]) needs at least 1 parameter.");
        }
        String jdbcUrl = (String) args.get(0);
        Document schemaDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element schemaRoot = schemaDoc.createElement("schema");
        schemaDoc.appendChild(schemaRoot);
        try (Connection connection = DriverManager.getConnection(jdbcUrl))
        {
            Map<String, Element> globalKeys = new HashMap<>();
            this.doLoadTables(connection, schemaDoc);
            this.doLoadColumns(connection, schemaDoc);
            this.doLoadConstraints(connection, schemaDoc, globalKeys);
            this.doLoadConstraintColumns(connection, schemaDoc, globalKeys);
            this.doLoadConstraintTargets(connection, schemaDoc, globalKeys);
        }
        // 排除輔助欄位
        for (Node tableNode : schemaRoot.getElementsByTagName("table").asList())
        {
            Element tableElem = (Element) tableNode;
            List<Node> columnListCopy = tableElem.getElementsByTagName("column").asList().toArrayList();
            Set<String> columnNameSet = new HashSet<>();
            columnListCopy.forEach(cn -> columnNameSet.add(((Element) cn).getAttribute("name")));
            for (Node columnNode : columnListCopy)
            {
                Element columnElem = (Element) columnNode;
                String columnName = columnElem.getAttribute("name");
                if (columnName.endsWith("WKB") || columnName.endsWith("WKT"))
                {
                    if (columnNameSet.contains2(columnName.substring(0, columnName.length() - 3)))
                    {
                        tableElem.removeChild(columnElem);
                    }
                }
            }
        }
        Element schemaXml = schemaDoc.createElement("xml");
        CDATASection schemaXmlCDATA = schemaDoc.createCDATASection(schemaRoot.toText());
        schemaXml.appendChild(schemaXmlCDATA);
        schemaRoot.appendChild(schemaXml);
        // System.out.println(schemaRoot.toText());
        return this.load(engine, args, schemaDoc);
    }

    protected void doLoadTables(Connection connection, Document schemaDoc) throws SQLException
    {
        Objects.requireNonNull(connection);
        Objects.requireNonNull(schemaDoc);
        try (PreparedStatement stmt = connection.prepareStatement("
select *,
   (
    select pa.rows
      from sys.tables ta
     inner join sys.partitions pa
             on pa.object_id = ta.object_id
     inner join sys.schemas sc
             on ta.schema_id = sc.schema_id
     where pa.index_id IN (1,0)
       and sc.name = TABLE_SCHEMA
       and ta.name = TABLE_NAME
   ) as TABLE_ROWS
  from INFORMATION_SCHEMA.TABLES
 where TABLE_NAME not in ('sysdiagrams')
 order by TABLE_SCHEMA, TABLE_NAME
"
                ))
        {
            try (ResultSet rs = stmt.executeQuery())
            {
                while (rs.next())
                {
                    String tableName = rs.getString("TABLE_NAME");
                    Element tableElem = schemaDoc.createElement("table");
                    tableElem.setAttribute("catalog", rs.getString("TABLE_CATALOG"));
                    tableElem.setAttribute("schema", rs.getString("TABLE_SCHEMA"));
                    tableElem.setAttribute("name", tableName);
                    tableElem.setAttribute("rows", Integer.toString(rs.getInt("TABLE_ROWS")));
                    schemaDoc.getFirstChild().appendChild(tableElem);
                }
            }
        }
    }

    protected void doLoadColumns(Connection connection, Document schemaDoc) throws SQLException
    {
        Objects.requireNonNull(connection);
        Objects.requireNonNull(schemaDoc);
        try (PreparedStatement stmt = connection.prepareStatement("
select *
  from INFORMATION_SCHEMA.COLUMNS
 where TABLE_NAME not in ('sysdiagrams')
 order by TABLE_SCHEMA, TABLE_NAME, ORDINAL_POSITION
"
                ))
        {
            try (ResultSet rs = stmt.executeQuery())
            {
                while (rs.next())
                {
                    String tableName = rs.getString("TABLE_NAME");
                    Element tableElem = schemaDoc.getFirstChild().getChildNodes().asList()
                            .ofType(Element.class)
                            .filter(elem -> elem.getAttribute("name").equals(tableName))
                            .first();
                    Element columnElem = schemaDoc.createElement("column");
                    boolean nullable = SqlSchemaDataLoader.trBoolean(rs.getString("IS_NULLABLE"));
                    Integer precision = getColumnPrecision(rs);
                    Integer scale = getColumnScale(rs);
                    Integer length = getColumnLength(rs);
                    String definition = getColumnDefinition(rs);
                    if (!nullable)
                        definition += " not null";
                    columnElem.setAttribute("name", rs.getString("COLUMN_NAME"));
                    columnElem.setAttribute("type", rs.getString("DATA_TYPE"));
                    columnElem.setAttribute("nullable", Boolean.toString(nullable));
                    columnElem.setAttribute("precision", precision == null ? "" : precision.toString());
                    columnElem.setAttribute("scale", scale == null ? "" : scale.toString());
                    columnElem.setAttribute("length", length == null ? "" : length.toString());
                    columnElem.setAttribute("definition", definition);
                    columnElem.setAttribute("inPK", "false");
                    columnElem.setAttribute("inFK", "false");
                    columnElem.setAttribute("inUK", "false");
                    columnElem.setAttribute("isLOB", isLargeColumn(definition) ? "true" : "false");
                    tableElem.appendChild(columnElem);
                }
            }
        }
    }

    protected void doLoadConstraints(Connection connection, Document schemaDoc, Map<String, Element> globalKeys)
            throws SQLException
    {
        Objects.requireNonNull(connection);
        Objects.requireNonNull(schemaDoc);
        Objects.requireNonNull(globalKeys);
        try (PreparedStatement stmt = connection.prepareStatement("
select *
  from INFORMATION_SCHEMA.TABLE_CONSTRAINTS
 where CONSTRAINT_TYPE in ('PRIMARY KEY', 'UNIQUE', 'FOREIGN KEY')
   and TABLE_NAME not in ('sysdiagrams')
 order by TABLE_SCHEMA, TABLE_NAME, CONSTRAINT_TYPE, CONSTRAINT_NAME
"
                ))
        {
            try (ResultSet rs = stmt.executeQuery())
            {
                while (rs.next())
                {
                    String tableName = rs.getString("TABLE_NAME");
                    Element tableElem = schemaDoc.getFirstChild().getChildNodes().asList()
                            .ofType(Element.class)
                            .filter(elem -> elem.getAttribute("name").equals(tableName))
                            .first();
                    String constraintName = rs.getString("CONSTRAINT_NAME");
                    String constraintType = rs.getString("CONSTRAINT_TYPE");
                    Element constraintElem = constraintType.equals("FOREIGN KEY")
                            ? schemaDoc.createElement("reference")
                            : schemaDoc.createElement("key");
                    String shortName = constraintName;
                    if (shortName.substring(3).startsWith(tableName + "_"))
                        shortName = shortName.substring(3 + tableName.length() + 1);
                    else if (shortName.substring(3).equals(tableName))
                        shortName = "parent";
                    constraintElem.setAttribute("catalog", rs.getString("CONSTRAINT_CATALOG"));
                    constraintElem.setAttribute("schema", rs.getString("CONSTRAINT_SCHEMA"));
                    constraintElem.setAttribute("name", constraintName);
                    constraintElem.setAttribute("shortName", shortName);
                    constraintElem.setAttribute("type", constraintType.split(" ")[0].toLowerCase());
                    tableElem.appendChild(constraintElem);
                    globalKeys.put(constraintName, constraintElem);
                }
            }
        }
    }

    protected void doLoadConstraintColumns(Connection connection, Document schemaDoc, Map<String, Element> globalKeys)
            throws SQLException
    {
        Objects.requireNonNull(connection);
        Objects.requireNonNull(schemaDoc);
        Objects.requireNonNull(globalKeys);
        try (PreparedStatement stmt = connection.prepareStatement("
select *
  from INFORMATION_SCHEMA.KEY_COLUMN_USAGE
 where TABLE_NAME not in ('sysdiagrams')
 order by TABLE_SCHEMA, TABLE_NAME, CONSTRAINT_NAME, ORDINAL_POSITION
"
                ))
        {
            try (ResultSet rs = stmt.executeQuery())
            {
                while (rs.next())
                {
                    String tableName = rs.getString("TABLE_NAME");
                    Element tableElem = schemaDoc.getFirstChild().getChildNodes().asList()
                            .ofType(Element.class)
                            .filter(elem -> elem.getAttribute("name").equals(tableName))
                            .first();
                    String constraintName = rs.getString("CONSTRAINT_NAME");
                    Element constraintElem = globalKeys.get2(constraintName);
                    if (constraintElem == null)
                        continue;
                    String columnName = rs.getString("COLUMN_NAME");
                    Element columnElem = tableElem
                            .getChildNodes()
                            .asList()
                            .ofType(Element.class)
                            .filter(elem -> elem.getNodeName().equals("column")
                                    && elem.getAttribute("name").equals(columnName))
                            .first();
                    switch (constraintElem.getAttribute("type"))
                    {
                    case "primary":
                        columnElem.setAttribute("inPK", "true");
                        break;
                    case "foreign":
                        columnElem.setAttribute("inFK", "true");
                        break;
                    case "unique":
                        columnElem.setAttribute("inUK", "true");
                        break;
                    }
                    Element keyRefCopy = (Element) constraintElem.cloneNode(false);
                    columnElem.appendChild(schemaDoc.renameNode(keyRefCopy, null, "keyRef"));
                    Element colRefElem = schemaDoc.createElement("colRef");
                    colRefElem.setAttribute("name", columnName);
                    colRefElem.setAttribute("type", columnElem.getAttribute("type"));
                    constraintElem.appendChild(colRefElem);
                }
            }
        }
    }

    protected void doLoadConstraintTargets(Connection connection, Document schemaDoc, Map<String, Element> globalKeys)
            throws SQLException
    {
        Objects.requireNonNull(connection);
        Objects.requireNonNull(schemaDoc);
        Objects.requireNonNull(globalKeys);
        try (PreparedStatement stmt = connection.prepareStatement("
select *
  from INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS
 order by CONSTRAINT_SCHEMA, CONSTRAINT_NAME
"
                ))
        {
            try (ResultSet rs = stmt.executeQuery())
            {
                while (rs.next())
                {
                    String dependentRefName = rs.getString("CONSTRAINT_NAME");
                    Element dependentRefElem = globalKeys.get2(dependentRefName);
                    if (dependentRefElem == null)
                        continue;
                    Element dependentTableElem = (Element) dependentRefElem.getParentNode();
                    String principalKeyName = rs.getString("UNIQUE_CONSTRAINT_NAME");
                    Element principalKeyElem = globalKeys.get2(principalKeyName);
                    assert (principalKeyElem != null);
                    Element principalTableElem = (Element) principalKeyElem.getParentNode();
                    //
                    dependentRefElem.setAttribute("parentTable", principalTableElem.getAttribute("name"));
                    dependentRefElem.setAttribute("parentKey", principalKeyName);
                    //
                    Element childDescrElem = schemaDoc.createElement("child");
                    childDescrElem.setAttribute("table", dependentTableElem.getAttribute("name"));
                    childDescrElem.setAttribute("keyName", dependentRefElem.getAttribute("name"));
                    childDescrElem.setAttribute("keyShortName", dependentRefElem.getAttribute("shortName"));
                    for (Element colRef : dependentRefElem.getChildNodes()
                            .asList()
                            .ofType(Element.class)
                            .filter(e -> e.getNodeName().equals("colRef")))
                    {
                        Element colRefCopy = (Element) colRef.cloneNode(false);
                        childDescrElem.appendChild(colRefCopy);
                    }
                    principalKeyElem.appendChild(childDescrElem);
                }
            }
        }
    }

    protected static Integer getColumnPrecision(ResultSet resultSet) throws SQLException
    {
        String type = resultSet.getString("DATA_TYPE");
        switch (type)
        {
        case "decimal":
        case "numeric":
            return resultSet.getInt("NUMERIC_PRECISION");
        default:
            return null;
        }
    }

    protected static Integer getColumnScale(ResultSet resultSet) throws SQLException
    {
        String type = resultSet.getString("DATA_TYPE");
        switch (type)
        {
        case "decimal":
        case "numeric":
            return resultSet.getInt("NUMERIC_SCALE");
        default:
            return null;
        }
    }

    protected static Integer getColumnLength(ResultSet resultSet) throws SQLException
    {
        String type = resultSet.getString("DATA_TYPE");
        switch (type)
        {
        case "char":
        case "varchar":
        case "nchar":
        case "nvarchar":
        case "binary":
        case "varbinary":
            return resultSet.getInt("CHARACTER_MAXIMUM_LENGTH");
        default:
            return null;
        }
    }

    protected static String getColumnDefinition(ResultSet resultSet) throws SQLException
    {
        String type = resultSet.getString("DATA_TYPE");
        switch (type)
        {
        case "decimal":
        case "numeric":
            int precision = resultSet.getInt("NUMERIC_PRECISION");
            int scale = resultSet.getInt("NUMERIC_SCALE");
            return String.format("%s(%d,%d)", type, precision, scale);
        case "char":
        case "varchar":
        case "nchar":
        case "nvarchar":
        case "binary":
        case "varbinary":
            int maxLength = resultSet.getInt("CHARACTER_MAXIMUM_LENGTH");
            if (maxLength == -1)
                return String.format("%s(max)", type);
            else
                return String.format("%s(%d)", type, maxLength);
        default:
            return type;
        }
    }

    protected static boolean isLargeColumn(String columnTypeDefinition)
    {
        switch (columnTypeDefinition)
        {
        case "geography":
        case "geometry":
        case "image":
        case "xml":
            return true;
        default:
            return columnTypeDefinition.endsWith("(max)");
        }
    }

    protected static boolean trBoolean(String yesOrNo)
    {
        switch (yesOrNo)
        {
        case "YES":
            return true;
        case "NO":
            return false;
        default:
            throw new IllegalArgumentException();
        }
    }
}
