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
    /**
     * 初始項目
     */
    protected final T initial;

    /**
     * 取得每個項目的子項目集合的函數
     */
    protected final Function<? super T, ? extends Iterator<? extends T>> getChildren;

    /**
     * 堆疊
     * <p>
     * 堆疊的最外層表示目前項目，內一層即是目前項目的上層，依此類推
     * </p>
     */
    protected final Stack<Entry> stack = new Stack<>();

    /**
     * 建立新的串接列舉
     * <p>
     * {@link LinkTreeIterator}會依照{@code getNext}取得目前項目的下一個項目
     * </p>
     *
     * @param initial 初始項目
     * @param getChildren 取得每個項目的子項目集合，傳回null表示結束
     */
    public LinkTreeIterator(T initial, Function<? super T, ? extends Iterator<? extends T>> getChildren)
    {
        this.initial = initial;
        this.getChildren = getChildren;
    }

    /**
     * 取得目前進行的深度
     *
     * @return 目前進行的深度(根項目為1)
     */
    public int getDepth()
    {
        return this.stack.size();
    }

    @Override
    protected T fetchNext()
    {
        if (this.isAtHead())
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

    /**
     * 堆疊紀錄
     *
     * @author AqD
     */
    public class Entry
    {
        /**
         * 目前項目
         */
        public final T item;

        /**
         * 目前項目的子項目列舉器
         */
        public final Iterator<? extends T> children;

        /**
         * 建立新紀錄
         *
         * @param item 項目
         */
        public Entry(T item)
        {
            this.item = item;
            Iterator<? extends T> childrenIterator = LinkTreeIterator.this.getChildren.apply(this.item);
            if (childrenIterator != null)
                this.children = childrenIterator;
            else
                this.children = null;
        }
    }
}
