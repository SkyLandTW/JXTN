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

import java.util.function.BiFunction;

import javafx.beans.value.ObservableValue;
import jxtn.jfx.axi.ObservableMapHelper;

/**
 * {@link ObservableMap}的延伸功能
 *
 * @author AqD
 * @param <K> 索引鍵型態
 * @param <V> 項目值型態
 */
public interface ObservableMapExt<K, V>
{
    /**
     * 建立自動對照索引鍵的{@link ObservableList}
     * <ul>
     * <li>傳回{@link ObservableList}的索引鍵不保證任何順序</li>
     * </ul>
     *
     * @return {@link ObservableList}，對應目前字典的索引鍵
     */
    default ObservableList<K> toKeysObservable()
    {
        ObservableList<K> list = FXCollections.observableArrayList();
        ObservableMapHelper.mapKeys((ObservableMap<K, V>) this, list);
        return list;
    }

    /**
     * 建立自動對照索引鍵的{@link ObservableList}
     * <ul>
     * <li>傳回{@link ObservableList}的項目值不保證任何順序</li>
     * </ul>
     *
     * @return {@link ObservableList}，對應目前字典的項目值
     */
    default ObservableList<V> toValuesObservable()
    {
        ObservableList<V> list = FXCollections.observableArrayList();
        ObservableMapHelper.mapValues((ObservableMap<K, V>) this, list);
        return list;
    }

    /**
     * 建立自動對照清單
     * <ul>
     * <li>傳回{@link ObservableList}的項目不保證任何順序</li>
     * <li>針對每個目前{@link ObservableMap}的來源項目，只會建立一個{@link ObservableValue}(只呼叫一次{@code mapper})</li>
     * </ul>
     *
     * @param <R> 目的集合項目型態
     * @param mapper 對照函數，負責建立來源項目的資料連結
     * @return 對照後的結果，會自動配合目前集合做更新
     */
    default <R> ObservableList<R> toMappedObservableByBinding(BiFunction<K, V, ObservableValue<R>> mapper)
    {
        ObservableList<R> mappedList = FXCollections.observableArrayList();
        ObservableMapHelper.mapEntriesByBinding((ObservableMap<K, V>) this, mappedList, mapper);
        return mappedList;
    }

    /**
     * 建立自動對照清單
     * <ul>
     * <li>傳回{@link ObservableList}的項目不保證任何順序</li>
     * <li>針對每個目前{@link ObservableMap}的來源項目，只會建立一個{@code R}(只呼叫一次{@code mapper})</li>
     * </ul>
     *
     * @param <R> 目的集合項目型態
     * @param mapper 對照函數
     * @return 對照後的結果，會自動配合目前集合做更新
     */
    default <R> ObservableList<R> toMappedObservableByValue(BiFunction<K, V, R> mapper)
    {
        ObservableList<R> mappedList = FXCollections.observableArrayList();
        ObservableMapHelper.mapEntriesByValue((ObservableMap<K, V>) this, mappedList, mapper);
        return mappedList;
    }
}
