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

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;

import javafx.beans.value.ObservableValue;
import jxtn.jfx.axi.ObservableListHelper;

/**
 * {@link ObservableList}的延伸功能
 *
 * @author AqD
 * @param <E> 集合項目型態
 */
public interface ObservableListExt<E>
{
    /**
     * 建立自動對照清單
     *
     * @param <R> 目的集合項目型態
     * @param mapper 對照函數，負責建立來源項目的資料連結
     * @return 對照後的結果，會自動配合目前集合做更新
     */
    default <R> ObservableList<R> toMappedObservableByBinding(Function<E, ObservableValue<R>> mapper)
    {
        ObservableList<R> mappedList = FXCollections.observableArrayList();
        ObservableListHelper.mapByBinding((ObservableList<E>) this, mappedList, mapper);
        return mappedList;
    }

    /**
     * 建立自動對照清單
     *
     * @param <R> 目的集合項目型態
     * @param mapper 對照函數
     * @return 對照後的結果，會自動配合目前集合做更新
     */
    default <R> ObservableList<R> toMappedObservableByValue(Function<E, R> mapper)
    {
        ObservableList<R> mappedList = FXCollections.observableArrayList();
        ObservableListHelper.mapByValue((ObservableList<E>) this, mappedList, mapper);
        return mappedList;
    }

    /**
     * 建立自動群組
     *
     * @param <K> 項目群組鍵值型態
     * @param grouper 群組函數(取得做群組的鍵值)，負責建立計算來源項目群組鍵的資料連結
     * @param createGroup 建立空白群組的函數
     * @param addToGroup 增加來源項目到群組的函數
     * @param removeFromGroup 從群組移除來源項目的函數，傳回true表示群組該移除
     * @return 群組後的結果(群組鍵值=>組內項目)，會自動配合目前集合做更新
     */
    default <K> ObservableMap<K, ObservableList<E>> toGroupedObservableByBinding(Function<E, ObservableValue<K>> grouper)
    {
        ObservableMap<K, ObservableList<E>> groupMap = FXCollections.observableHashMap();
        ObservableListHelper.groupByBinding((ObservableList<E>) this, groupMap, grouper);
        return groupMap;
    }

    /**
     * 建立自動群組
     *
     * @param <K> 項目群組鍵值型態
     * @param <G> 項目群組項目型態
     * @param grouper 群組函數(取得做群組的鍵值)，負責建立計算來源項目群組鍵的資料連結
     * @param createGroup 建立空白群組的函數
     * @param addToGroup 增加來源項目到群組的函數
     * @param removeFromGroup 從群組移除來源項目的函數，傳回true表示群組該移除
     * @return 群組後的結果(群組鍵值=>自訂型態群組)，會自動配合目前集合做更新
     */
    default <K, G> ObservableMap<K, G> toGroupedObservableByBinding(
            Function<E, ObservableValue<K>> grouper,
            Function<K, G> createGroup,
            BiConsumer<G, E> addToGroup,
            BiPredicate<G, E> removeFromGroup)
    {
        ObservableMap<K, G> groupMap = FXCollections.observableHashMap();
        ObservableListHelper.groupByBinding((ObservableList<E>) this, groupMap,
                grouper, createGroup, addToGroup, removeFromGroup);
        return groupMap;
    }

    /**
     * 建立自動群組
     *
     * @param <K> 項目群組鍵值型態
     * @param grouper 群組函數(取得做群組的鍵值)，負責計算來源項目的群組鍵
     * @param createGroup 建立空白群組的函數
     * @param addToGroup 增加來源項目到群組的函數
     * @param removeFromGroup 從群組移除來源項目的函數，傳回true表示群組該移除
     * @return 群組後的結果(群組鍵值=>組內項目)，會自動配合目前集合做更新
     */
    default <K> ObservableMap<K, ObservableList<E>> toGroupedObservableByValue(Function<E, K> grouper)
    {
        ObservableMap<K, ObservableList<E>> groupMap = FXCollections.observableHashMap();
        ObservableListHelper.groupByValue((ObservableList<E>) this, groupMap, grouper);
        return groupMap;
    }

    /**
     * 建立自動群組
     *
     * @param <K> 項目群組鍵值型態
     * @param <G> 項目群組項目型態
     * @param grouper 群組函數(取得做群組的鍵值)，負責計算來源項目的群組鍵
     * @param createGroup 建立空白群組的函數
     * @param addToGroup 增加來源項目到群組的函數
     * @param removeFromGroup 從群組移除來源項目的函數，傳回true表示群組該移除
     * @return 群組後的結果(群組鍵值=>自訂型態群組)，會自動配合目前集合做更新
     */
    default <K, G> ObservableMap<K, G> toGroupedObservableByValue(
            Function<E, K> grouper,
            Function<K, G> createGroup,
            BiConsumer<G, E> addToGroup,
            BiPredicate<G, E> removeFromGroup)
    {
        ObservableMap<K, G> groupMap = FXCollections.observableHashMap();
        ObservableListHelper.groupByValue((ObservableList<E>) this, groupMap,
                grouper, createGroup, addToGroup, removeFromGroup);
        return groupMap;
    }
}
