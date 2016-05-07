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

import fmpp.Engine;
import fmpp.tdd.DataLoader;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateSequenceModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 以資料庫SELECT結果做為資料來源的載入器
 * <p>
 * 載入資料為清單，每列由hash代表：索引鍵為欄位名稱，項目值為欄位內容字串(null以空字串表示)
 * </p>
 *
 * @author AqD
 */
public class SqlSelectRowsDataLoader implements DataLoader {
    @Override
    public Object load(Engine engine, @SuppressWarnings("rawtypes") List args) throws Exception {
        if (args.size() != 2) {
            throw new IllegalArgumentException("sql(url, select) needs 2 parameters.");
        }
        String jdbcUrl = (String) args.get(0);
        String selectCommand = (String) args.get(1);
        Map<String, Integer> columnNameToIndex = new HashMap<>();
        List<String[]> resultRowList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            try (PreparedStatement stmt = connection.prepareStatement(selectCommand)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    ResultSetMetaData metadata = rs.getMetaData();
                    for (int i = 1; i <= metadata.getColumnCount(); i++) {
                        columnNameToIndex.put(rs.getMetaData().getColumnName(i), i - 1);
                    }
                    while (rs.next()) {
                        String[] row = new String[metadata.getColumnCount()];
                        for (int i = 1; i <= metadata.getColumnCount(); i++) {
                            Object value = rs.getObject(i);
                            if (value == null) {
                                row[i - 1] = "";
                            } else {
                                row[i - 1] = value.toString();
                            }
                        }
                        resultRowList.add(row);
                    }
                }
            }
        }
        return new TemplateSequenceModel() {
            @Override
            public TemplateModel get(int index) throws TemplateModelException {
                if (index < 0 || index >= resultRowList.size()) {
                    return null;
                }
                String[] row = resultRowList.get(index);
                return new TemplateHashModel() {
                    @Override
                    public TemplateModel get(String columnName) throws TemplateModelException {
                        int cidx = columnNameToIndex.get2(columnName);
                        if (cidx == -1) {
                            return null;
                        }
                        String cval = row[cidx];
                        return new SimpleScalar(cval);
                    }

                    @Override
                    public boolean isEmpty() throws TemplateModelException {
                        return columnNameToIndex.isEmpty();
                    }

                };
            }

            @Override
            public int size() throws TemplateModelException {
                return resultRowList.size();
            }
        };
    }
}
