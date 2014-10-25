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

package java.util.tuple;

import java.io.Serializable;
import java.util.Arrays;

import jxtn.core.axi.comparators.ArrayComparators;

/**
 * 基本的Tuple型態。
 * <ul>
 * <li>子項目長度、順序及型態固定</li>
 * <li>子項目值可為null</li>
 * <li>不可異動</li>
 * <li>支援透過子項目進行比較</li>
 * </ul>
 *
 * @author AqD
 * @param <T> 最終類別型態
 */
public abstract class BaseTuple<T extends BaseTuple<T>> implements Comparable<T>, Serializable
{
    private static final long serialVersionUID = -7698222474647340866L;

    private final Object[] valueArray;

    protected BaseTuple(Object... values)
    {
        this.valueArray = values;
    }

    @Override
    public int compareTo(T other)
    {
        return ArrayComparators.compare(this.getValueArray(), other.getValueArray());
    }

    @Override
    public boolean equals(Object other)
    {
        if (other instanceof BaseTuple)
            return Arrays.equals(this.getValueArray(), ((BaseTuple<?>) other).getValueArray());
        else
            return false;
    }

    @Override
    public int hashCode()
    {
        return Arrays.hashCode(this.getValueArray());
    }

    /**
     * 取得包含所有子項目的內部陣列。
     *
     * @return 包含所有子項目的內部陣列，不應修改
     */
    public Object[] getValueArray()
    {
        return this.valueArray;
    }
}
