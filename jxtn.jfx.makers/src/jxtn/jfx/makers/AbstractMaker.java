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

package jxtn.jfx.makers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Consumer;

/**
 * 基底建構器。
 *
 * @author AqD
 * @param <Z> 要建構的物件型態
 * @param <B> 建構器本身的型態
 */
public class AbstractMaker<Z, B extends AbstractMaker<Z, B>>
{
    /**
     * 建構後執行動作集合(具名)。
     */
    protected Map<String, Consumer<? super Z>> namedAfterBuildActions;

    /**
     * 建構後執行動作集合(不具名)。
     */
    protected ArrayList<Consumer<? super Z>> unnamedAfterBuildActions;

    /**
     * 套用建構器的屬性設定。
     *
     * @param instance 要套用屬性的物件實體
     */
    public void applyTo(Z instance)
    {
        Objects.requireNonNull(instance);
        //
    }

    /**
     * 註冊物件建構後的執行動作。
     *
     * @param action 執行動作
     * @return 目前建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B afterBuild(Consumer<? super Z> action)
    {
        Objects.requireNonNull(action);
        if (this.unnamedAfterBuildActions == null)
            this.unnamedAfterBuildActions = new ArrayList<>();
        this.unnamedAfterBuildActions.add(action);
        return (B) this;
    }

    /**
     * 註冊物件建構後的執行動作。
     *
     * @param name 動作名稱，名稱重複會覆蓋掉舊的
     * @param action 執行動作
     * @return 目前建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B afterBuild(String name, Consumer<? super Z> action)
    {
        Objects.requireNonNull(action);
        if (this.namedAfterBuildActions == null)
            this.namedAfterBuildActions = new TreeMap<>();
        this.namedAfterBuildActions.put(name, action);
        return (B) this;
    }

    /**
     * 建構物件。
     *
     * @return 建構的物件
     */
    public Object build()
    {
        return new Object();
    }

    /**
     * 呼叫建構後的執行動作({@link #unnamedAfterBuildActions})。
     *
     * @param instance 要執行動作的目標物件
     */
    protected final void doAfterBuild(Z instance)
    {
        Objects.requireNonNull(instance);
        if (this.namedAfterBuildActions != null)
        {
            for (Consumer<? super Z> action : this.namedAfterBuildActions.values())
            {
                action.accept(instance);
            }
        }
        if (this.unnamedAfterBuildActions != null)
        {
            for (Consumer<? super Z> action : this.unnamedAfterBuildActions)
            {
                action.accept(instance);
            }
        }
    }
}
