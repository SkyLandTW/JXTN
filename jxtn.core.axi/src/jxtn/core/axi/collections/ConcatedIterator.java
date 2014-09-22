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

/**
 * 結合多來源列舉的列舉器
 *
 * @author AqD
 * @param <T> 列舉項目型態
 */
public class ConcatedIterator<T> extends AbstractIterator<T>
{
    /**
     * 提供來源列舉器的列舉器
     */
    protected final Iterator<Iterator<? extends T>> sourceIterator;

    /**
     * 目前的來源列舉器
     * <p>
     * 即{@link #sourceIterator}的目前項目
     * </p>
     */
    protected Iterator<? extends T> currentSource;

    private long sourceIteratorSteps;
    private long currentSourceSteps;

    /**
     * 建立新的列舉
     * <p>
     * {@link ConcatedIterator}會依照{@code parentIterator}內的順序展開列舉各項目
     * </p>
     *
     * @param sourceIterator 來源列舉器的列舉器
     */
    public ConcatedIterator(Iterator<Iterator<? extends T>> sourceIterator)
    {
        this.sourceIterator = sourceIterator;
    }

    @Override
    public String toString()
    {
        return super.toString() + String.format(",srcSteps=%d,curSteps=%d",
                this.sourceIteratorSteps, this.currentSourceSteps);
    }

    /**
     * 取得來源列舉器的列舉器的進行次數
     *
     * @return {@link #sourceIterator}的進行次數
     */
    public final long getSourceIteratorSteps()
    {
        return this.sourceIteratorSteps;
    }

    /**
     * 取得目前的來源列舉器的進行次數
     *
     * @return {@link #currentSource}的進行次數
     */
    public final long getCurrentSourceSteps()
    {
        return this.currentSourceSteps;
    }

    @Override
    protected T fetchNext()
    {
        while (true)
        {
            if (this.currentSource == null)
            {
                if (!this.sourceIterator.hasNext())
                    return this.end();
                this.sourceIteratorSteps += 1;
                this.currentSource = this.sourceIterator.next();
            }
            if (this.currentSource.hasNext())
            {
                this.currentSourceSteps += 1;
                return this.currentSource.next();
            }
            else
                this.currentSource = null;
        }
    }
}
