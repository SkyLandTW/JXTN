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

package java.sql;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Calendar;

import com.sun.istack.internal.Nullable;

/**
 * {@link ResultSet}的延伸功能。
 *
 * @author AqD
 */
@SuppressWarnings("resource")
public interface ResultSetExt
{
    /**
     * 尋找符合名稱的欄位其索引
     *
     * @param columnLabel 欄位標籤
     * @return 欄位索引(從1起算)，或null表示找不到。
     * @throws SQLException SQLException {@link ResultSet#getMetaData}例外
     */
    default Integer findColumnOrNull(String columnLabel) throws SQLException
    {
        ResultSet thiz = (ResultSet) this;
        ResultSetMetaData metaData = thiz.getMetaData();
        for (int columnIndex = 1; columnIndex <= metaData.getColumnCount(); columnIndex++)
        {
            // SQL Server官方用getColumnName而非getColumnLabel!!
            if (metaData.getColumnName(columnIndex).equals(columnLabel))
                return columnIndex;
        }
        for (int columnIndex = 1; columnIndex <= metaData.getColumnCount(); columnIndex++)
        {
            // SQL Server官方用getColumnName而非getColumnLabel!!
            if (metaData.getColumnName(columnIndex).equalsIgnoreCase(columnLabel))
                return columnIndex;
        }
        return null;
    }

    //////////////////////////////////////////////////////////////////////////
    // 取得可為null的欄位值
    //

    /**
     * 取得{@link Boolean}欄位值，空值傳回null。
     *
     * @param columnIndex 欄位索引(從1起算)
     * @return {@link Boolean}欄位值或null
     * @throws SQLException {@link ResultSet#getBoolean(int)}例外
     */
    @com.sun.istack.internal.Nullable
    default Boolean getBooleanOrNull(int columnIndex) throws SQLException
    {
        ResultSet thiz = (ResultSet) this;
        if (thiz.getObject(columnIndex) == null)
            return null;
        return thiz.getBoolean(columnIndex);
    }

    /**
     * 取得{@link Boolean}欄位值，空值傳回null。
     *
     * @param columnLabel 欄位標籤
     * @return {@link Boolean}欄位值或null
     * @throws SQLException {@link ResultSet#getBoolean(int)}例外
     */
    @com.sun.istack.internal.Nullable
    default Boolean getBooleanOrNull(String columnLabel) throws SQLException
    {
        ResultSet thiz = (ResultSet) this;
        int columnIndex = thiz.findColumn(columnLabel);
        return getBooleanOrNull(columnIndex);
    }

    /**
     * 取得{@link Byte}欄位值，空值傳回null。
     *
     * @param columnIndex 欄位索引(從1起算)
     * @return {@link Byte}欄位值或null
     * @throws SQLException {@link ResultSet#getByte(int)}例外
     */
    @com.sun.istack.internal.Nullable
    default Byte getByteOrNull(int columnIndex) throws SQLException
    {
        ResultSet thiz = (ResultSet) this;
        if (thiz.getObject(columnIndex) == null)
            return null;
        return thiz.getByte(columnIndex);
    }

    /**
     * 取得{@link Byte}欄位值，空值傳回null。
     *
     * @param columnLabel 欄位標籤
     * @return {@link Byte}欄位值或null
     * @throws SQLException {@link ResultSet#getByte(int)}例外
     */
    @com.sun.istack.internal.Nullable
    default Byte getByteOrNull(String columnLabel) throws SQLException
    {
        ResultSet thiz = (ResultSet) this;
        int columnIndex = thiz.findColumn(columnLabel);
        return getByteOrNull(columnIndex);
    }

    /**
     * 取得{@link Short}欄位值，空值傳回null。
     *
     * @param columnIndex 欄位索引(從1起算)
     * @return {@link Short}欄位值或null
     * @throws SQLException {@link ResultSet#getShort(int)}例外
     */
    @com.sun.istack.internal.Nullable
    default Short getShortOrNull(int columnIndex) throws SQLException
    {
        ResultSet thiz = (ResultSet) this;
        if (thiz.getObject(columnIndex) == null)
            return null;
        return thiz.getShort(columnIndex);
    }

    /**
     * 取得{@link Short}欄位值，空值傳回null。
     *
     * @param columnLabel 欄位標籤
     * @return {@link Short}欄位值或null
     * @throws SQLException {@link ResultSet#getShort(int)}例外
     */
    @com.sun.istack.internal.Nullable
    default Short getShortOrNull(String columnLabel) throws SQLException
    {
        ResultSet thiz = (ResultSet) this;
        int columnIndex = thiz.findColumn(columnLabel);
        return getShortOrNull(columnIndex);
    }

    /**
     * 取得{@link Integer}欄位值，空值傳回null。
     *
     * @param columnIndex 欄位索引(從1起算)
     * @return {@link Integer}欄位值或null
     * @throws SQLException {@link ResultSet#getInt(int)}例外
     */
    @com.sun.istack.internal.Nullable
    default Integer getIntOrNull(int columnIndex) throws SQLException
    {
        ResultSet thiz = (ResultSet) this;
        if (thiz.getObject(columnIndex) == null)
            return null;
        return thiz.getInt(columnIndex);
    }

    /**
     * 取得{@link Integer}欄位值，空值傳回null。
     *
     * @param columnLabel 欄位標籤
     * @return {@link Integer}欄位值或null
     * @throws SQLException {@link ResultSet#getInt(int)}例外
     */
    @com.sun.istack.internal.Nullable
    default Integer getIntOrNull(String columnLabel) throws SQLException
    {
        ResultSet thiz = (ResultSet) this;
        int columnIndex = thiz.findColumn(columnLabel);
        return getIntOrNull(columnIndex);
    }

    /**
     * 取得{@link Long}欄位值，空值傳回null。
     *
     * @param columnIndex 欄位索引(從1起算)
     * @return {@link Long}欄位值或null
     * @throws SQLException {@link ResultSet#getLong(int)}例外
     */
    @Nullable
    default Long getLongOrNull(int columnIndex) throws SQLException
    {
        ResultSet thiz = (ResultSet) this;
        if (thiz.getObject(columnIndex) == null)
            return null;
        return thiz.getLong(columnIndex);
    }

    /**
     * 取得{@link Long}欄位值，空值傳回null。
     *
     * @param columnLabel 欄位標籤
     * @return {@link Long}欄位值或null
     * @throws SQLException {@link ResultSet#getLong(int)}例外
     */
    @Nullable
    default Long getLongOrNull(String columnLabel) throws SQLException
    {
        ResultSet thiz = (ResultSet) this;
        int columnIndex = thiz.findColumn(columnLabel);
        return getLongOrNull(columnIndex);
    }

    /**
     * 取得{@link Float}欄位值，空值傳回null。
     *
     * @param columnIndex 欄位索引(從1起算)
     * @return {@link Float}欄位值或null
     * @throws SQLException {@link ResultSet#getFloat(int)}例外
     */
    @com.sun.istack.internal.Nullable
    default Float getFloatOrNull(int columnIndex) throws SQLException
    {
        ResultSet thiz = (ResultSet) this;
        if (thiz.getObject(columnIndex) == null)
            return null;
        return thiz.getFloat(columnIndex);
    }

    /**
     * 取得{@link Float}欄位值，空值傳回null。
     *
     * @param columnLabel 欄位標籤
     * @return {@link Float}欄位值或null
     * @throws SQLException {@link ResultSet#getFloat(int)}例外
     */
    @com.sun.istack.internal.Nullable
    default Float getFloatOrNull(String columnLabel) throws SQLException
    {
        ResultSet thiz = (ResultSet) this;
        int columnIndex = thiz.findColumn(columnLabel);
        return getFloatOrNull(columnIndex);
    }

    /**
     * 取得{@link Double}欄位值，空值傳回null。
     *
     * @param columnIndex 欄位索引(從1起算)
     * @return {@link Double}欄位值或null
     * @throws SQLException {@link ResultSet#getDouble(int)}例外
     */
    @com.sun.istack.internal.Nullable
    default Double getDoubleOrNull(int columnIndex) throws SQLException
    {
        ResultSet thiz = (ResultSet) this;
        if (thiz.getObject(columnIndex) == null)
            return null;
        return thiz.getDouble(columnIndex);
    }

    /**
     * 取得{@link Double}欄位值，空值傳回null。
     *
     * @param columnLabel 欄位標籤
     * @return {@link Double}欄位值或null
     * @throws SQLException {@link ResultSet#getDouble(int)}例外
     */
    @com.sun.istack.internal.Nullable
    default Double getDoubleOrNull(String columnLabel) throws SQLException
    {
        ResultSet thiz = (ResultSet) this;
        int columnIndex = thiz.findColumn(columnLabel);
        return getDoubleOrNull(columnIndex);
    }

    //////////////////////////////////////////////////////////////////////////
    // 取得名稱可能不存在的欄位值
    //

    /**
     * 嘗試取得{@link Array}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getArray(int)}例外
     */
    default Array tryGetArray(String columnLabel, Array fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getArray(columnIndex);
    }

    /**
     * 嘗試取得{@link InputStream}(ASCII)欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getAsciiStream(int)}例外
     */
    default InputStream tryGetAsciiStream(String columnLabel, InputStream fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getAsciiStream(columnIndex);
    }

    /**
     * 嘗試取得{@link BigDecimal}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getBigDecimal(int)}例外
     */
    default BigDecimal tryGetBigDecimal(String columnLabel, BigDecimal fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getBigDecimal(columnIndex);
    }

    /**
     * 嘗試取得{@link InputStream}(Binary)欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getBinaryStream(int)}例外
     */
    default InputStream tryGetBinaryStream(String columnLabel, InputStream fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getBinaryStream(columnIndex);
    }

    /**
     * 嘗試取得{@link Blob}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getBlob(int)}例外
     */
    default Blob tryGetBlob(String columnLabel, Blob fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getBlob(columnIndex);
    }

    /**
     * 嘗試取得{@link Boolean}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getBoolean(int)}例外
     */
    default boolean tryGetBoolean(String columnLabel, boolean fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getBoolean(columnIndex);
    }

    /**
     * 嘗試取得{@link Byte}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getByte(int)}例外
     */
    default byte tryGetByte(String columnLabel, byte fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getByte(columnIndex);
    }

    /**
     * 嘗試取得{@code byte[]}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getBytes(int)}例外
     */
    default byte[] tryGetBytes(String columnLabel, byte[] fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getBytes(columnIndex);
    }

    /**
     * 嘗試取得{@link Reader}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getCharacterStream(int)}例外
     */
    default Reader tryGetCharacterStream(String columnLabel, Reader fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getCharacterStream(columnIndex);
    }

    /**
     * 嘗試取得{@link Clob}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getClob(int)}例外
     */
    default Clob tryGetClob(String columnLabel, Clob fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getClob(columnIndex);
    }

    /**
     * 嘗試取得{@link Date}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getDate(int)}例外
     */
    default Date tryGetDate(String columnLabel, Date fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getDate(columnIndex);
    }

    /**
     * 嘗試取得{@link Date}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param cal 月曆
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getDate(int)}例外
     */
    default Date tryGetDate(String columnLabel, Calendar cal, Date fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getDate(columnIndex, cal);
    }

    /**
     * 嘗試取得{@link Double}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getDouble(int)}例外
     */
    default double tryGetDouble(String columnLabel, double fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getDouble(columnIndex);
    }

    /**
     * 嘗試取得{@link Float}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getFloat(int)}例外
     */
    default float tryGetFloat(String columnLabel, float fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getFloat(columnIndex);
    }

    /**
     * 嘗試取得{@link Integer}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getInt(int)}例外
     */
    default int tryGetInt(String columnLabel, int fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getInt(columnIndex);
    }

    /**
     * 嘗試取得{@link Long}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getLong(int)}例外
     */
    default long tryGetLong(String columnLabel, long fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getLong(columnIndex);
    }

    /**
     * 嘗試取得{@link Reader}(Unicode)欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getNCharacterStream(int)}例外
     */
    default Reader tryGetNCharacterStream(String columnLabel, Reader fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getNCharacterStream(columnIndex);
    }

    /**
     * 嘗試取得{@link NClob}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getNClob(int)}例外
     */
    default NClob tryGetNClob(String columnLabel, NClob fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getNClob(columnIndex);
    }

    /**
     * 嘗試取得{@link String}(Unicode)欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getNString(int)}例外
     */
    default String tryGetNString(String columnLabel, String fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getNString(columnIndex);
    }

    /**
     * 嘗試取得{@link Object}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getObject(int)}例外
     */
    default Object tryGetObject(String columnLabel, Object fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getObject(columnIndex);
    }

    /**
     * 嘗試取得{@link Ref}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getRef(int)}例外
     */
    default Ref tryGetRef(String columnLabel, Ref fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getRef(columnIndex);
    }

    /**
     * 嘗試取得{@link RowId}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getRowId(int)}例外
     */
    default RowId tryGetRowId(String columnLabel, RowId fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getRowId(columnIndex);
    }

    /**
     * 嘗試取得{@link Short}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getShort(int)}例外
     */
    default short tryGetShort(String columnLabel, short fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getShort(columnIndex);
    }

    /**
     * 嘗試取得{@link SQLXML}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getSQLXML(int)}例外
     */
    default SQLXML tryGetSQLXML(String columnLabel, SQLXML fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getSQLXML(columnIndex);
    }

    /**
     * 嘗試取得{@link String}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getString(int)}例外
     */
    default String tryGetString(String columnLabel, String fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getString(columnIndex);
    }

    /**
     * 嘗試取得{@link Time}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getTime(int)}例外
     */
    default Time tryGetTime(String columnLabel, Time fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getTime(columnIndex);
    }

    /**
     * 嘗試取得{@link Timestamp}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getTimestamp(int)}例外
     */
    default Timestamp tryGetTimestamp(String columnLabel, Timestamp fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getTimestamp(columnIndex);
    }

    /**
     * 嘗試取得{@link URL}欄位值
     *
     * @param columnLabel 欄位標籤
     * @param fallbackValue {@code columnLabel}不存在時傳回的預設值
     * @return {@code columnLabel}欄位值或{@code defaultValue}
     * @throws SQLException {@link ResultSet#getURL(int)}例外
     */
    default URL tryGetURL(String columnLabel, URL fallbackValue) throws SQLException
    {
        Integer columnIndex = findColumnOrNull(columnLabel);
        if (columnIndex == null)
            return fallbackValue;
        else
            return ((ResultSet) this).getURL(columnIndex);
    }
}
