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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fmpp.Engine;
import fmpp.tdd.DataLoader;
import freemarker.template.SimpleCollection;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * 以資料庫SELECT兩欄位結果做為hash資料來源的載入器
 * <p>
 * SELECT結果第一欄為索引鍵，第二欄為項目值(null以空字串表示)
 * </p>
 *
 * @author AqD
 */
public class SqlSelectKeysDataLoader implements DataLoader
{
    @Override
    public Object load(Engine engine, @SuppressWarnings("rawtypes") List args) throws Exception
    {
        if (args.size() != 2)
        {
            throw new IllegalArgumentException("sql(url, select) needs 2 parameters.");
        }
        String jdbcUrl = (String) args.get(0);
        String selectCommand = (String) args.get(1);
        Map<String, String> resultMap = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(jdbcUrl))
        {
            try (PreparedStatement stmt = connection.prepareStatement(selectCommand))
            {
                try (ResultSet rs = stmt.executeQuery())
                {
                    while (rs.next())
                    {
                        String key = rs.getString(1);
                        String val = rs.getString(2);
                        if (key == null)
                            key = "";
                        if (val == null)
                            val = "";
                        resultMap.put(key, val);
                    }
                }
            }
        }
        return new TemplateHashModelEx()
            {
                @Override
                public TemplateModel get(String key) throws TemplateModelException
                {
                    String val = resultMap.get2(key);
                    if (val == null)
                        return null;
                    return new SimpleScalar(val);
                }

                @Override
                public boolean isEmpty() throws TemplateModelException
                {
                    return resultMap.isEmpty();
                }

                @Override
                public int size() throws TemplateModelException
                {
                    return resultMap.size();
                }

                @Override
                public TemplateCollectionModel keys() throws TemplateModelException
                {
                    return new SimpleCollection(resultMap.keySet());
                }

                @Override
                public TemplateCollectionModel values() throws TemplateModelException
                {
                    return new SimpleCollection(resultMap.values());
                }

            };
    }

}
