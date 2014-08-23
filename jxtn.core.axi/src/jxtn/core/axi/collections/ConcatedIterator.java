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
    protected final Iterator<Iterator<T>> parentIterator;

    protected Iterator<T> currentParent;

    /**
     * 建立新的列舉
     * <p>
     * {@link ConcatedIterator}會依照{@code parentIterator}內的順序展開列舉各項目
     * </p>
     *
     * @param parentIterable 來源列舉的列舉器
     */
    public ConcatedIterator(Iterator<Iterator<T>> parentIterator)
    {
        this.parentIterator = parentIterator;
    }

    @Override
    protected T fetchNext()
    {
        while (true)
        {
            if (this.currentParent == null)
            {
                if (!this.parentIterator.hasNext())
                    return this.end();
                this.currentParent = this.parentIterator.next();
            }
            if (this.currentParent.hasNext())
                return this.currentParent.next();
            else
                this.currentParent = null;
        }
    }
}
