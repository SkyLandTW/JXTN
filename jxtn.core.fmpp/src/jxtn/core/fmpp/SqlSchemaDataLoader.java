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
import java.util.ArrayList;
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
        // 載入schema
        try (Connection connection = DriverManager.getConnection(jdbcUrl))
        {
            try (PreparedStatement stmt = connection.prepareStatement(
                    "select TABLE_NAME from INFORMATION_SCHEMA.tables order by TABLE_NAME"))
            {
                try (ResultSet rs = stmt.executeQuery())
                {
                    while (rs.next())
                    {
                        String tableName = rs.getString("TABLE_NAME");
                        Element tableElem = schemaDoc.createElement("table");
                        tableElem.setAttribute("name", tableName);
                        schemaRoot.appendChild(tableElem);
                    }
                }
            }
            try (PreparedStatement stmt = connection.prepareStatement(
                    "select * from INFORMATION_SCHEMA.columns order by TABLE_NAME, ORDINAL_POSITION"))
            {
                try (ResultSet rs = stmt.executeQuery())
                {
                    while (rs.next())
                    {
                        String tableName = rs.getString("TABLE_NAME");
                        Element tableElem = schemaRoot.getChildNodes().asList()
                                .ofType(Element.class)
                                .filter(elem -> elem.getAttribute("name").equals(tableName))
                                .firstOrNull();
                        if (tableElem == null)
                        {
                            tableElem = schemaDoc.createElement("table");
                            tableElem.setAttribute("name", tableName);
                            schemaRoot.appendChild(tableElem);
                        }
                        Element columnElem = schemaDoc.createElement("column");
                        columnElem.setAttribute("name", rs.getString("COLUMN_NAME"));
                        columnElem.setAttribute("type", rs.getString("DATA_TYPE"));
                        columnElem.setAttribute("nullable", rs.getString("IS_NULLABLE"));
                        tableElem.appendChild(columnElem);
                    }
                }
            }
        }
        // 排除輔助欄位
        for (Node tableNode : schemaRoot.getElementsByTagName("table").asList())
        {
            Element tableElem = (Element) tableNode;
            List<Node> columnListCopy = new ArrayList<>(tableElem.getElementsByTagName("column").asList());
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
        return this.load(engine, args, schemaDoc);
    }
}
