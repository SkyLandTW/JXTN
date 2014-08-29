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

package jxtn.jfx.builders;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 建構器的最上層類別
 *
 * @author AqD
 * @param <Z> 要建構的物件型態
 * @param <B> 建構器本身的型態
 */
public class AbstractBuilder<Z, B extends AbstractBuilder<Z, B>>
{
    /**
     * 建構後執行動作集合
     */
    protected ArrayList<Consumer<? super Z>> afterBuildActions;

    /**
     * 套用建構器的屬性設定
     *
     * @param instance 要套用屬性的物件實體
     */
    public void applyTo(Z instance)
    {
        Objects.requireNonNull(instance);
        //
    }

    /**
     * 註冊物件建構後的執行動作
     *
     * @param action 執行動作
     * @return 目前建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B afterBuild(Consumer<? super Z> action)
    {
        Objects.requireNonNull(action);
        if (this.afterBuildActions == null)
            this.afterBuildActions = new ArrayList<>();
        this.afterBuildActions.add(action);
        return (B) this;
    }

    /**
     * 建構物件
     *
     * @return 建構的物件
     */
    public Object build()
    {
        return new Object();
    }

    /**
     * 呼叫建構後的執行動作({@link #afterBuildActions})
     *
     * @param instance 要執行動作的目標物件
     */
    protected final void doAfterBuild(Z instance)
    {
        Objects.requireNonNull(instance);
        if (this.afterBuildActions != null)
        {
            for (Consumer<? super Z> action : this.afterBuildActions)
            {
                action.accept(instance);
            }
        }
    }
}
