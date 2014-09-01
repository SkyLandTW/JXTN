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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import fmpp.Engine;
import fmpp.dataloaders.XmlDataLoader;

/**
 * 資料庫來源為SQL-Server schema的載入器
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
        // 載入表格
        try (Connection connection = DriverManager.getConnection(jdbcUrl))
        {
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
 order by TABLE_NAME
"
                    ))
            {
                try (ResultSet rs = stmt.executeQuery())
                {
                    while (rs.next())
                    {
                        String tableName = rs.getString("TABLE_NAME");
                        Element tableElem = schemaDoc.createElement("table");
                        tableElem.setAttribute("name", tableName);
                        tableElem.setAttribute("rows", Integer.toString(rs.getInt("TABLE_ROWS")));
                        schemaRoot.appendChild(tableElem);
                    }
                }
            }
            // 載入欄位
            try (PreparedStatement stmt = connection.prepareStatement("
select *
  from INFORMATION_SCHEMA.COLUMNS
 order by TABLE_NAME, ORDINAL_POSITION
"
                    ))
            {
                try (ResultSet rs = stmt.executeQuery())
                {
                    while (rs.next())
                    {
                        String tableName = rs.getString("TABLE_NAME");
                        Element tableElem = schemaRoot.getChildNodes().iterable()
                                .ofType(Element.class)
                                .filter(elem -> elem.getAttribute("name").equals(tableName))
                                .first();
                        Element columnElem = schemaDoc.createElement("column");
                        columnElem.setAttribute("name", rs.getString("COLUMN_NAME"));
                        columnElem.setAttribute("type", rs.getString("DATA_TYPE"));
                        columnElem.setAttribute("nullable", rs.getString("IS_NULLABLE"));
                        tableElem.appendChild(columnElem);
                    }
                }
            }
            // 載入索引
            try (PreparedStatement stmt = connection.prepareStatement("
select *
  from INFORMATION_SCHEMA.TABLE_CONSTRAINTS
 where CONSTRAINT_TYPE in ('PRIMARY KEY', 'UNIQUE')
 order by TABLE_SCHEMA, TABLE_NAME, CONSTRAINT_TYPE, CONSTRAINT_NAME
"
                    ))
            {
                try (ResultSet rs = stmt.executeQuery())
                {
                    while (rs.next())
                    {
                        String tableName = rs.getString("TABLE_NAME");
                        Element tableElem = schemaRoot.getChildNodes().iterable()
                                .ofType(Element.class)
                                .filter(elem -> elem.getNodeName().equals("table") && elem.getAttribute("name").equals(tableName))
                                .first();
                        Element keyElem = schemaDoc.createElement("key");
                        keyElem.setAttribute("name", rs.getString("CONSTRAINT_NAME"));
                        keyElem.setAttribute("type", rs.getString("CONSTRAINT_TYPE"));
                        tableElem.appendChild(keyElem);
                    }
                }
            }
            // 載入索引欄位
            try (PreparedStatement stmt = connection.prepareStatement("
select *
  from INFORMATION_SCHEMA.KEY_COLUMN_USAGE
 order by TABLE_SCHEMA, TABLE_NAME, CONSTRAINT_NAME, ORDINAL_POSITION
"
                    ))
            {
                try (ResultSet rs = stmt.executeQuery())
                {
                    while (rs.next())
                    {
                        String tableName = rs.getString("TABLE_NAME");
                        Element tableElem = schemaRoot.getChildNodes().iterable()
                                .ofType(Element.class)
                                .filter(elem -> elem.getNodeName().equals("table") && elem.getAttribute("name").equals(tableName))
                                .first();
                        String keyName = rs.getString("CONSTRAINT_NAME");
                        Element keyElem = tableElem.getChildNodes().iterable()
                                .ofType(Element.class)
                                .filter(elem -> elem.getNodeName().equals("key") && elem.getAttribute("name").equals(keyName))
                                .firstOrNull();
                        if (keyElem != null)
                        {
                            String colName = rs.getString("COLUMN_NAME");
                            Element colElem = tableElem.getChildNodes().iterable()
                                    .ofType(Element.class)
                                    .filter(elem -> elem.getNodeName().equals("column") && elem.getAttribute("name").equals(colName))
                                    .first();
                            Element colRefElem = schemaDoc.createElement("colRef");
                            colRefElem.setAttribute("name", colName);
                            colRefElem.setAttribute("type", colElem.getAttribute("type"));
                            keyElem.appendChild(colRefElem);
                        }
                    }
                }
            }
        }
        // 排除輔助欄位
        for (Node tableNode : schemaRoot.getElementsByTagName("table").iterable())
        {
            Element tableElem = (Element) tableNode;
            List<Node> columnListCopy = tableElem.getElementsByTagName("column").iterable().toArrayList();
            Set<String> columnNameSet = new HashSet<>();
            columnListCopy.forEach(cn -> columnNameSet.add(((Element) cn).getAttribute("name")));
            for (Node columnNode : columnListCopy)
            {
                Element columnElem = (Element) columnNode;
                String columnName = columnElem.getAttribute("name");
                if (columnName.endsWith("WKB") || columnName.endsWith("WKT"))
                {
                    if (columnNameSet.contains(columnName.substring(0, columnName.length() - 3)))
                    {
                        tableElem.removeChild(columnElem);
                    }
                }
            }
        }
        System.out.println(schemaRoot.toText());
        return this.load(engine, args, schemaDoc);
    }
}
