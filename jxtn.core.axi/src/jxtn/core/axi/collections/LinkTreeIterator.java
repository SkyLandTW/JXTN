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
import java.util.Stack;
import java.util.function.Function;

/**
 * 依照條件做一對多串接的列舉器
 * <p>
 * depth-first
 * </p>
 *
 * @author AqD
 * @param <T> 列舉項目型態
 */
public class LinkTreeIterator<T> extends AbstractIterator<T>
{
    protected final T initial;
    protected final Function<? super T, ? extends Iterable<? extends T>> getChildren;

    protected Stack<Entry> stack = new Stack<>();

    /**
     * 建立新的串接列舉
     * <p>
     * {@link LinkTreeIterator}會依照{@code getNext}取得目前項目的下一個項目
     * </p>
     *
     * @param initial 初始項目
     * @param getChildren 取得每個項目的下一個項目，傳回null表示結束
     */
    public LinkTreeIterator(T initial, Function<? super T, ? extends Iterable<? extends T>> getChildren)
    {
        this.initial = initial;
        this.getChildren = getChildren;
    }

    @Override
    protected T fetchNext()
    {
        if (this.atHead())
        {
            if (this.initial == null)
                return this.end();
            else
                return this.stack.push(new Entry(this.initial)).item;
        }
        while (!this.stack.isEmpty())
        {
            Entry entry = this.stack.peek();
            if (entry.children != null)
            {
                while (entry.children.hasNext())
                {
                    T next = entry.children.next();
                    if (next != null)
                    {
                        return this.stack.push(new Entry(next)).item;
                    }
                }
            }
            this.stack.pop();
        }
        return this.end();
    }

    public class Entry
    {
        public final T item;
        public final Iterator<? extends T> children;

        public Entry(T item)
        {
            this.item = item;
            Iterable<? extends T> childrenIterable = LinkTreeIterator.this.getChildren.apply(this.item);
            if (childrenIterable != null)
                this.children = childrenIterable.iterator();
            else
                this.children = null;
        }
    }
}
