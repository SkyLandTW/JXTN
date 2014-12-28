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

import java.util.Objects;
import java.util.function.Function;

/**
 * 依照條件做一對一串接的列舉器。
 *
 * @author AqD
 * @param <T> 列舉項目型態
 */
public class LinkLineIterator<T> extends AbstractIterator<T>
{
    /**
     * 初始項目。
     */
    protected final T initial;

    /**
     * 取得每個項目的下一個項目的函數。
     */
    protected final Function<? super T, ? extends T> getNext;

    /**
     * 目前項目。
     */
    protected T current;

    /**
     * 建立新的串接列舉。
     * <p>
     * {@link LinkLineIterator}會依照{@code getNext}取得目前項目的下一個項目。
     * </p>
     *
     * @param initial 初始項目，可為null(空列舉)
     * @param getNext 取得每個項目的下一個項目，傳回null表示結束
     */
    public LinkLineIterator(T initial, Function<? super T, ? extends T> getNext)
    {
        Objects.requireNonNull(getNext);
        this.initial = initial;
        this.getNext = getNext;
    }

    @Override
    protected T fetchNext()
    {
        // 取得項目
        if (this.isAtHead())
            this.current = this.initial;
        else
            this.current = this.getNext.apply(this.current);
        // 檢查是否為null
        if (this.current != null)
            return this.current;
        else
            return this.end();
    }
}
