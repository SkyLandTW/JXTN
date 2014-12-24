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

package javafx.collections;

import java.util.function.Function;

import javafx.beans.value.ObservableValue;
import jxtn.jfx.axi.ObservableSetHelper;

/**
 * {@link ObservableSet}的延伸功能
 *
 * @author AqD
 * @param <E> 集合項目型態
 */
public interface ObservableSetExt<E>
{
    /**
     * 建立自動對照的{@link ObservableList}
     *
     * @return {@link ObservableList}，對應目前集的項目
     */
    default ObservableList<E> toObservable()
    {
        ObservableList<E> list = FXCollections.observableArrayList();
        ObservableSetHelper.mapTo((ObservableSet<E>) this, list);
        return list;
    }

    /**
     * 建立自動對照的{@link ObservableList}
     *
     * @param <R> 目的集合項目型態
     * @param mapper 對照函數，負責建立來源項目的資料連結
     * @return {@link ObservableList}，對應目前集的項目
     */
    default <R> ObservableList<R> toObservable(Function<E, ObservableValue<R>> mapper)
    {
        ObservableList<R> list = FXCollections.observableArrayList();
        ObservableSetHelper.mapTo((ObservableSet<E>) this, list, mapper);
        return list;
    }
}
