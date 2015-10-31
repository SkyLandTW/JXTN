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
import java.util.NoSuchElementException;

/**
 * 提供列舉器的架構，參考guava。
 * <p>
 * 會在{@link #hasNext()}時即取得下一項，比正常的列舉器早一步。
 * </p>
 *
 * @author AqD
 * @param <T> 列舉項目型態
 */
public abstract class AbstractIterator<T> implements Iterator<T> {
    private State state = State.HEAD;
    private T next;
    private long steps = 0;

    @Override
    public final boolean hasNext() {
        switch (this.state) {
        case END:
            return false;
        case AVAILABLE:
            return true;
        case HEAD:
        case NEED_FETCH:
            this.next = this.fetchNext();
            if (this.state == State.END) {
                assert (this.next == null);
            } else {
                this.state = State.AVAILABLE;
                this.steps += 1;
                return true;
            }
            return false;
        default:
            throw new IllegalStateException();
        }
    }

    @Override
    public final T next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        T current = this.next;
        this.next = null;
        this.state = State.NEED_FETCH;
        return current;
    }

    @Override
    public String toString() {
        return this.getClass().toGenericString()
                + String.format(":%s,steps=%d", this.state.name(), this.steps);
    }

    /**
     * 取得進行次數。
     *
     * @return 進行次數
     */
    public final long getSteps() {
        return this.steps;
    }

    /**
     * 是否在開頭狀態。
     *
     * @return true表示目前在開頭，尚未進行任何取得動作
     */
    public final boolean isAtHead() {
        return this.state == State.HEAD;
    }

    /**
     * 取得下一個項目。
     * <p>
     * 無項目時傳回{@link #end()}表示結束。
     * </p>
     *
     * @return 下一個項目
     */
    protected abstract T fetchNext();

    /**
     * 提供繼承類別於{@link #fetchNext()}內叫用通知結束。
     *
     * @return null
     */
    protected final T end() {
        this.state = State.END;
        return null;
    }

    /**
     * 列舉器目前狀態。
     *
     * @author AqD
     */
    private enum State {
        /**
         * 開始(需要取得下一個)。
         */
        HEAD,

        /**
         * 需要取得下一個(呼叫{@link AbstractIterator#fetchNext()})。
         */
        NEED_FETCH,

        /**
         * 已有下一個項目可供使用。
         */
        AVAILABLE,

        /**
         * 結束。
         */
        END,
    }
}
