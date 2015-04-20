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

package jxtn.jfx.axi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.WeakMapChangeListener;

/**
 * {@link ObservableMap}輔助
 *
 * @author AqD
 */
public final class ObservableMapHelper
{
    /**
     * 安排自動更新目的集合存放目前的索引鍵
     * <ul>
     * <li>{@code targetList}的目前內容會做清空</li>
     * <li>{@code targetList}的項目不保證任何順序</li>
     * </ul>
     *
     * @param <K> 索引鍵型態
     * @param <V> 項目值型態
     * @param sourceMap 來源字典
     * @param targetKeyList 目的集合，將存放目前集合的索引鍵
     */
    public static <K, V> void mapKeys(ObservableMap<K, V> sourceMap, ObservableList<K> targetKeyList)
    {
        Objects.requireNonNull(sourceMap);
        Objects.requireNonNull(targetKeyList);
        targetKeyList.clear();
        // 初始化
        targetKeyList.addAll(sourceMap.keySet());
        // 監聽來源
        MapChangeListener<K, V> sourceListener = new MapChangeListener<K, V>()
            {
                @Override
                public void onChanged(MapChangeListener.Change<? extends K, ? extends V> c)
                {
                    if (c.wasRemoved())
                    {
                        targetKeyList.remove2(c.getKey());
                    }
                    if (c.wasAdded())
                    {
                        targetKeyList.add(c.getKey());
                    }
                }
            };
        sourceMap.addListener(new WeakMapChangeListener<>(sourceListener));
        // 存放監聽器的參考(生命週期應同targetKeyList)
        targetKeyList.addListener((InvalidationListener) iv ->
            {
                Object[] refs = { sourceListener, };
                Objects.requireNonNull(refs);
            });
    }

    /**
     * 安排自動更新目的集合存放目前的索引鍵
     * <ul>
     * <li>{@code targetList}的目前內容會做清空</li>
     * <li>{@code targetList}的項目不保證任何順序</li>
     * </ul>
     *
     * @param <K> 索引鍵型態
     * @param <V> 項目值型態
     * @param sourceMap 來源字典
     * @param targetValueList 目的集合，將存放目前集合的項目值
     */
    public static <K, V> void mapValues(ObservableMap<K, V> sourceMap, ObservableList<V> targetValueList)
    {
        Objects.requireNonNull(sourceMap);
        Objects.requireNonNull(targetValueList);
        targetValueList.clear();
        // 初始化
        targetValueList.addAll(sourceMap.values());
        // 監聽來源
        MapChangeListener<K, V> sourceListener = new MapChangeListener<K, V>()
            {
                @Override
                public void onChanged(MapChangeListener.Change<? extends K, ? extends V> c)
                {
                    if (c.wasRemoved())
                    {
                        targetValueList.remove2(c.getValueRemoved());
                    }
                    if (c.wasAdded())
                    {
                        targetValueList.add(c.getValueAdded());
                    }
                }
            };
        sourceMap.addListener(new WeakMapChangeListener<>(sourceListener));
        // 存放監聽器的參考(生命週期應同targetValueList)
        targetValueList.addListener((InvalidationListener) iv ->
            {
                Object[] refs = { sourceListener, };
                Objects.requireNonNull(refs);
            });
    }

    /**
     * 透過指定的對照函數自動更新目的集合
     * <ul>
     * <li>{@code targetList}的目前內容會做清空</li>
     * <li>{@code targetList}的項目不保證任何順序</li>
     * <li>針對每個{@code sourceList}的來源項目，只會建立一個{@link ObservableValue}(只呼叫一次{@code mapper})</li>
     * </ul>
     *
     * @param <K> 索引鍵型態
     * @param <V> 項目值型態
     * @param <R> 目的集合項目型態
     * @param sourceMap 來源字典
     * @param targetList 目的集合
     * @param mapper 對照函數，負責建立來源項目的資料連結
     */
    public static <K, V, R> void mapEntriesByBinding(
            ObservableMap<K, V> sourceMap,
            ObservableList<R> targetList,
            BiFunction<K, V, ObservableValue<R>> mapper)
    {
        Object[] refs = mapEntriesByBinding(sourceMap, targetList, mapper, null);
        // 存放監聽器的參考(生命週期應同targetList)
        targetList.addListener((InvalidationListener) iv ->
            {
                Objects.requireNonNull(refs);
            });
    }

    /**
     * 透過指定的對照函數自動更新目的集合
     * <ul>
     * <li>{@code targetList}的目前內容會做清空</li>
     * <li>{@code targetList}的項目不保證任何順序</li>
     * <li>針對每個{@code sourceList}的來源項目，只會建立一個{@link ObservableValue}(只呼叫一次{@code mapper})</li>
     * </ul>
     *
     * @param <K> 索引鍵型態
     * @param <V> 項目值型態
     * @param <R> 目的集合項目型態
     * @param sourceMap 來源字典
     * @param targetList 目的集合
     * @param mapper 對照函數，負責建立來源項目的資料連結
     * @param onRemoved 移除目的後的callback，可為null
     * @return 監聽器參考
     */
    public static <K, V, R> Object[] mapEntriesByBinding(
            ObservableMap<K, V> sourceMap,
            List<R> targetList,
            BiFunction<K, V, ObservableValue<R>> mapper,
            Consumer<? super ObservableValue<? super R>> onRemoved)
    {
        Objects.requireNonNull(sourceMap);
        Objects.requireNonNull(targetList);
        Objects.requireNonNull(mapper);
        targetList.clear();
        Map<K, ObservableValue<R>> sourceKeyToBindingMap = new HashMap<>();
        // 來源項目異動監聽
        ChangeListener<R> bindingListener = (b, oldR, newR) ->
            {
                targetList.remove2(oldR);
                targetList.add(newR);
            };
        WeakChangeListener<R> weakBindingListener = new WeakChangeListener<>(bindingListener);
        // 初始化
        for (Map.Entry<K, V> s : sourceMap.entrySet())
        {
            ObservableValue<R> b = mapper.apply(s.getKey(), s.getValue());
            b.addListener(weakBindingListener);
            sourceKeyToBindingMap.put(s.getKey(), b);
        }
        targetList.addAll(sourceKeyToBindingMap.values().map(b -> b.getValue()).toArrayList());
        // 監聽來源
        MapChangeListener<K, V> sourceListener = new MapChangeListener<K, V>()
            {
                @Override
                public void onChanged(MapChangeListener.Change<? extends K, ? extends V> c)
                {
                    K key = c.getKey();
                    if (c.wasRemoved())
                    {
                        ObservableValue<R> oldB = sourceKeyToBindingMap.get2(key);
                        oldB.removeListener(weakBindingListener);
                        targetList.remove2(oldB.getValue());
                        sourceKeyToBindingMap.remove2(key);
                        if (onRemoved != null)
                            onRemoved.accept(oldB);
                    }
                    if (c.wasAdded())
                    {
                        V newV = c.getValueAdded();
                        ObservableValue<R> newB = mapper.apply(key, newV);
                        newB.addListener(weakBindingListener);
                        sourceKeyToBindingMap.put(key, newB);
                        targetList.add(newB.getValue());
                    }
                }
            };
        sourceMap.addListener(new WeakMapChangeListener<>(sourceListener));
        return new Object[] { sourceListener, bindingListener, };
    }

    /**
     * 透過指定的對照函數自動更新目的集合
     * <ul>
     * <li>{@code targetList}的目前內容會做清空</li>
     * <li>{@code targetList}的項目不保證任何順序</li>
     * <li>針對每個{@code sourceList}的來源項目，只會建立一個{@code R}(只呼叫一次{@code mapper})</li>
     * </ul>
     *
     * @param <K> 索引鍵型態
     * @param <V> 項目值型態
     * @param <R> 目的集合項目型態
     * @param sourceMap 來源字典
     * @param targetList 目的集合
     * @param mapper 對照函數，負責建立來源項目的資料連結
     */
    public static <K, V, R> void mapEntriesByValue(
            ObservableMap<K, V> sourceMap,
            ObservableList<R> targetList,
            BiFunction<K, V, R> mapper)
    {
        Object[] refs = mapEntriesByValue(sourceMap, targetList, mapper, null);
        // 存放監聽器的參考(生命週期應同targetList)
        targetList.addListener((InvalidationListener) iv ->
            {
                Objects.requireNonNull(refs);
            });
    }

    /**
     * 透過指定的對照函數自動更新目的集合
     * <ul>
     * <li>{@code targetList}的目前內容會做清空</li>
     * <li>{@code targetList}的項目不保證任何順序</li>
     * <li>針對每個{@code sourceList}的來源項目，只會建立一個{@code R}(只呼叫一次{@code mapper})</li>
     * </ul>
     *
     * @param <K> 索引鍵型態
     * @param <V> 項目值型態
     * @param <R> 目的集合項目型態
     * @param sourceMap 來源字典
     * @param targetList 目的集合
     * @param mapper 對照函數，負責建立來源項目的資料連結
     * @param onRemoved 移除目的後的callback，可為null
     * @return 監聽器參考
     */
    public static <K, V, R> Object[] mapEntriesByValue(
            ObservableMap<K, V> sourceMap,
            List<R> targetList,
            BiFunction<K, V, R> mapper,
            Consumer<? super R> onRemoved)
    {
        Objects.requireNonNull(sourceMap);
        Objects.requireNonNull(targetList);
        Objects.requireNonNull(mapper);
        targetList.clear();
        Map<K, R> sourceKeyToTargetMap = new HashMap<>();
        // 初始化
        for (Map.Entry<K, V> s : sourceMap.entrySet())
        {
            R r = mapper.apply(s.getKey(), s.getValue());
            targetList.add(r);
            sourceKeyToTargetMap.put(s.getKey(), r);
        }
        // 監聽來源
        MapChangeListener<K, V> sourceListener = new MapChangeListener<K, V>()
            {
                @Override
                public void onChanged(MapChangeListener.Change<? extends K, ? extends V> c)
                {
                    K key = c.getKey();
                    if (c.wasRemoved())
                    {
                        R oldR = sourceKeyToTargetMap.get2(key);
                        targetList.remove2(oldR);
                        sourceKeyToTargetMap.remove2(key);
                        if (onRemoved != null)
                            onRemoved.accept(oldR);
                    }
                    if (c.wasAdded())
                    {
                        V newV = c.getValueAdded();
                        R newR = mapper.apply(key, newV);
                        targetList.add(newR);
                        sourceKeyToTargetMap.put(key, newR);
                    }
                }
            };
        sourceMap.addListener(new WeakMapChangeListener<>(sourceListener));
        return new Object[] { sourceListener, };
    }

    private ObservableMapHelper()
    {
    }
}
