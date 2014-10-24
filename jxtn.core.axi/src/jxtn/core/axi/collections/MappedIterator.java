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
import java.util.function.Function;

/**
 * 依照指定函數做對照的列舉器
 *
 * @author AqD
 * @param <T> 來源列舉項目型態
 * @param <R> 目的列舉項目型態
 */
public class MappedIterator<T, R> extends AbstractIterator<R>
{
    /**
     * 來源列舉器
     */
    protected final Iterator<T, RuntimeException> source;

    /**
     * 對照函數
     */
    protected final Function<? super T, R> mapper;

    /**
     * 建立指定函數做對照的列舉器
     * <p>
     * {@link MappedIterator}會依照{@code mapper}將每個{@code source}的項目做轉換
     * </p>
     *
     * @param source 來源列舉器
     * @param mapper 對照函數
     */
    public MappedIterator(Iterator<T, RuntimeException> source, Function<? super T, R> mapper)
    {
        Objects.requireNonNull(source);
        Objects.requireNonNull(mapper);
        this.source = source;
        this.mapper = mapper;
    }

    @Override
    protected R fetchNext()
    {
        if (this.source.hasNext())
        {
            T nextT = this.source.next();
            R nextR = this.mapper.apply(nextT);
            return nextR;
        }
        return this.end();
    }
}
