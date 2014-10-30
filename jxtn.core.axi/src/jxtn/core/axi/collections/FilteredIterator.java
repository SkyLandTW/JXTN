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
import java.util.function.Predicate;

/**
 * 依照條件做過濾的列舉器。
 *
 * @author AqD
 * @param <T> 列舉項目型態
 */
public class FilteredIterator<T> extends AbstractIterator<T>
{
    /**
     * 來源列舉器。
     */
    protected final Iterator<? extends T> source;

    /**
     * 過濾條件。
     */
    protected final Predicate<? super T> filter;

    private long sourceSteps;

    /**
     * 建立新的過濾列舉。
     * <p>
     * {@link FilteredIterator}會依照{@code filter}的條件過濾{@code source}。
     * </p>
     *
     * @param source 來源列舉器
     * @param filter 過濾條件
     */
    public FilteredIterator(Iterator<? extends T> source, Predicate<? super T> filter)
    {
        Objects.requireNonNull(source);
        Objects.requireNonNull(filter);
        this.source = source;
        this.filter = filter;
    }

    @Override
    public String toString()
    {
        return super.toString() + String.format(",srcSteps=%d", this.sourceSteps);
    }

    /**
     * 取得來源列舉器的進行次數。
     *
     * @return {@link #source}的進行次數(過濾前的)
     */
    public final long getSourceSteps()
    {
        return this.sourceSteps;
    }

    @Override
    protected T fetchNext()
    {
        while (this.source.hasNext())
        {
            T item = this.source.next();
            this.sourceSteps += 1;
            if (this.filter.test(item))
            {
                return item;
            }
        }
        return this.end();
    }
}
