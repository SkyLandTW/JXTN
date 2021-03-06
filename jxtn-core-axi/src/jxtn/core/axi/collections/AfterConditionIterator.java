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
 * 保留指定條件之項目後內容的列舉器（剔除開頭）。
 *
 * @author AqD
 * @param <T> 列舉項目型態
 */
public final class AfterConditionIterator<T> extends AbstractIterator<T> {
    private final Iterator<? extends T> source;
    private final Predicate<? super T> condition;

    private long sourceSteps;
    private boolean passed;

    /**
     * 建立依照條件剔除開頭的列舉器 *
     * <p>
     * 會依照{@code filter}的條件過濾{@code source}，只保留第一個符合條件後的所有項目（包含該項目）。
     * </p>
     *
     * @param source 來源列舉器
     * @param condition 過濾條件
     */
    public AfterConditionIterator(Iterator<? extends T> source, Predicate<? super T> condition) {
        this.source = Objects.requireNonNull(source);
        this.condition = Objects.requireNonNull(condition);
    }

    /**
     * 取得來源列舉器的進行次數。
     *
     * @return {@link #source}的進行次數(過濾前的)
     */
    public long getSourceSteps() {
        return this.sourceSteps;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(",srcSteps=%d", this.sourceSteps);
    }

    @Override
    protected T fetchNext() {
        if (this.passed) {
            if (this.source.hasNext()) {
                T item = this.source.next();
                this.sourceSteps += 1;
                return item;
            } else {
                return this.end();
            }
        } else {
            while (this.source.hasNext()) {
                T item = this.source.next();
                this.sourceSteps += 1;
                if (this.condition.test(item)) {
                    this.passed = true;
                    return item;
                }
            }
            return this.end();
        }
    }
}
