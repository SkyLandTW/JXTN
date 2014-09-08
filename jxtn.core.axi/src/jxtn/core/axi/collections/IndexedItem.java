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

package jxtn.core.axi.collections;

/**
 * 索引項目，用在集合操作
 *
 * @author AqD
 * @param <T> 項目型態
 */
public final class IndexedItem<T> implements Comparable<IndexedItem<T>>
{
    protected final int index;
    protected final T value;

    public IndexedItem(int index, T value)
    {
        if (index < 0)
            throw new IllegalArgumentException("index小於0");
        this.index = index;
        this.value = value;
    }

    /**
     * 取得項目索引
     *
     * @return 項目索引
     */
    public int getIndex()
    {
        return this.index;
    }

    /**
     * 取得項目值
     *
     * @return 項目值
     */
    public T getValue()
    {
        return this.value;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public int compareTo(IndexedItem<T> other)
    {
        if (other == null)
            return 1;
        if (this.index > other.index)
            return 1;
        if (this.index < other.index)
            return -1;
        Object valL = this.value;
        Object valR = other.value;
        if (valL == null && valR == null)
            return 0;
        if (valL == null)
            return -1;
        if (valR == null)
            return 1;
        if (valL instanceof Comparable)
            return ((Comparable) valL).compareTo(valR);
        else if (valR instanceof Comparable)
            return -((Comparable) valR).compareTo(valL);
        else
            return valL.toString().compareTo(valR.toString());
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        if (!obj.getClass().equals(this.getClass()))
            return false;
        IndexedItem<T> other = (IndexedItem<T>) obj;
        return this.index == other.index
                && this.value == other.value;
    }

    @Override
    public int hashCode()
    {
        return (this.index * 397) ^ (this.value != null ? this.value.hashCode() : 0);
    }
}
