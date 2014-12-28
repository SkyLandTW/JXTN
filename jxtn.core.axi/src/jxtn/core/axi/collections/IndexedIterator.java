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

import java.util.Iterator;
import java.util.Objects;

/**
 * 加上索引的列舉器。
 *
 * @author AqD
 * @param <T> 列舉項目型態
 * @param <TException> 列舉例外型態
 */
public class IndexedIterator<T, TException extends Exception> extends AbstractIterator<IndexedItem<T>, TException>
{
    /**
     * 來源列舉器。
     */
    protected final Iterator<T, ? extends TException> source;

    protected int index = 0;

    /**
     * 建立加上索引的列舉器。
     *
     * @param source 來源列舉器
     */
    public IndexedIterator(Iterator<T, ? extends TException> source)
    {
        Objects.requireNonNull(source);
        this.source = source;
    }

    @Override
    protected IndexedItem<T> fetchNext() throws TException
    {
        if (this.source.hasNext())
        {
            T nextT = this.source.next();
            IndexedItem<T> nextIndexedT = new IndexedItem<>(this.index, nextT);
            this.index += 1;
            return nextIndexedT;
        }
        return this.end();
    }
}
