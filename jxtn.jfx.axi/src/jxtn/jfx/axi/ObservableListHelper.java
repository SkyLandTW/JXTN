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
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.WeakListChangeListener;

/**
 * {@link ObservableList}輔助
 *
 * @author AqD
 */
public final class ObservableListHelper
{
    /**
     * 透過指定的對照函數自動更新目的集合
     * <ul>
     * <li>{@code targetList}的目前內容會做清空</li>
     * <li>{@code targetList}的項目順序比照{@code sourceList}</li>
     * <li>針對每個{@code sourceList}的來源項目，只會建立一個{@link ObservableValue}(只呼叫一次{@code mapper})</li>
     * </ul>
     *
     * @param <S> 來源集合項目型態
     * @param <T> 目的集合項目型態
     * @param sourceList 來源集合
     * @param targetList 目的集合
     * @param mapper 對照函數，負責建立來源項目的資料連結
     */
    public static <S, T> void mapByBinding(
            ObservableList<S> sourceList,
            ObservableList<T> targetList,
            Function<S, ObservableValue<T>> mapper)
    {
        Objects.requireNonNull(sourceList);
        Objects.requireNonNull(targetList);
        Objects.requireNonNull(mapper);
        targetList.clear();
        Map<S, ObservableValue<? extends T>> sourceToBindingMap = new HashMap<>();
        // 來源項目異動監聽
        ChangeListener<T> bindingListener = (b, oldT, newT) ->
            {
                targetList.remove2(oldT);
                targetList.add(newT);
            };
        WeakChangeListener<T> weakBindingListener = new WeakChangeListener<>(bindingListener);
        // 初始化
        for (S s : sourceList)
        {
            ObservableValue<T> b = mapper.apply(s);
            b.addListener(weakBindingListener);
            sourceToBindingMap.put(s, b);
        }
        targetList.addAll(sourceToBindingMap.values().map(b -> b.getValue()).toArrayList());
        // 監聽來源
        ListChangeListener<S> sourceListener = new ListChangeListener<S>()
            {
                @Override
                public void onChanged(ListChangeListener.Change<? extends S> c)
                {
                    while (c.next())
                    {
                        if (c.wasPermutated())
                        {
                            for (int i = c.getFrom(); i < c.getTo(); i++)
                            {
                                int j = c.getPermutation(i);
                                S s = sourceList.get(j);
                                ObservableValue<? extends T> b = sourceToBindingMap.get2(s);
                                targetList.set(i, b.getValue());
                            }
                        }
                        else
                        {
                            for (S s : c.getRemoved())
                            {
                                ObservableValue<? extends T> b = sourceToBindingMap.get2(s);
                                b.removeListener(weakBindingListener);
                                targetList.remove2(b.getValue());
                                sourceToBindingMap.remove2(s);
                            }
                            int i = c.getFrom();
                            for (S s : c.getAddedSubList())
                            {
                                ObservableValue<T> b = mapper.apply(s);
                                b.addListener(weakBindingListener);
                                targetList.add(i, b.getValue());
                                sourceToBindingMap.put(s, b);
                                i += 1;
                            }
                        }
                    }
                }
            };
        sourceList.addListener(new WeakListChangeListener<>(sourceListener));
        // 存放監聽器的參考(生命週期應同targetList)
        targetList.addListener((InvalidationListener) iv ->
            {
                Object[] refs = { sourceListener, bindingListener, };
                Objects.requireNonNull(refs);
            });
    }

    /**
     * 透過指定的對照函數自動更新目的集合
     * <ul>
     * <li>{@code targetList}的目前內容會做清空</li>
     * <li>{@code targetList}的項目順序比照{@code sourceList}</li>
     * <li>針對每個{@code sourceList}的來源項目，只會建立一個{@code T}(只呼叫一次{@code mapper})</li>
     * </ul>
     *
     * @param <S> 來源集合項目型態
     * @param <T> 目的集合項目型態
     * @param sourceList 來源集合
     * @param targetList 目的集合
     * @param mapper 對照函數
     */
    public static <S, T> void mapByValue(
            ObservableList<S> sourceList,
            ObservableList<T> targetList,
            Function<S, T> mapper)
    {
        Objects.requireNonNull(sourceList);
        Objects.requireNonNull(targetList);
        Objects.requireNonNull(mapper);
        targetList.clear();
        Map<S, T> sourceToTargetMap = new HashMap<>();
        // 初始化
        for (S s : sourceList)
        {
            T t = mapper.apply(s);
            targetList.add(t);
            sourceToTargetMap.put(s, t);
        }
        // 監聽來源
        ListChangeListener<S> sourceListener = new ListChangeListener<S>()
            {
                @Override
                public void onChanged(ListChangeListener.Change<? extends S> c)
                {
                    while (c.next())
                    {
                        if (c.wasPermutated())
                        {
                            for (int i = c.getFrom(); i < c.getTo(); i++)
                            {
                                int j = c.getPermutation(i);
                                S s = sourceList.get(j);
                                T t = sourceToTargetMap.get2(s);
                                targetList.set(i, t);
                            }
                        }
                        else
                        {
                            for (S s : c.getRemoved())
                            {
                                T t = sourceToTargetMap.get2(s);
                                targetList.remove2(t);
                                sourceToTargetMap.remove2(s);
                            }
                            int i = c.getFrom();
                            for (S s : c.getAddedSubList())
                            {
                                T t = mapper.apply(s);
                                targetList.add(i, t);
                                sourceToTargetMap.put(s, t);
                                i += 1;
                            }
                        }
                    }
                }
            };
        sourceList.addListener(new WeakListChangeListener<>(sourceListener));
        // 存放監聽器的參考(生命週期應同targetList)
        targetList.addListener((InvalidationListener) iv ->
            {
                Object[] refs = { sourceListener, };
                Objects.requireNonNull(refs);
            });
    }

    /**
     * 透過指定的群組函數自動更新目的集合
     * <ul>
     * <li>{@code targetGroupMap}的目前內容會做清空</li>
     * <li>{@code targetGroupMap}的群組索引鍵不保證任何順序</li>
     * <li>針對每個{@code sourceList}的來源項目，只會建立一個{@link ObservableValue}(只呼叫一次{@code grouper})</li>
     * </ul>
     *
     * @param <S> 來源集合項目型態
     * @param <K> 項目群組鍵值型態
     * @param sourceList 來源集合
     * @param targetGroupMap 目的群組集合
     * @param grouper 群組函數(取得做群組的鍵值)，負責建立計算來源項目群組鍵的資料連結
     */
    public static <S, K> void groupByBinding(
            ObservableList<S> sourceList,
            ObservableMap<K, ObservableList<S>> targetGroupMap,
            Function<S, ObservableValue<K>> grouper)
    {
        groupByBinding(sourceList, targetGroupMap, grouper,
                k -> FXCollections.observableArrayList(),
                (g, s) -> g.add(s),
                (g, s) ->
                    {
                        g.remove2(s);
                        return g.isEmpty();
                    });
    }

    /**
     * 透過指定的群組函數自動更新目的集合
     * <ul>
     * <li>{@code targetGroupMap}的目前內容會做清空</li>
     * <li>{@code targetGroupMap}的群組索引鍵不保證任何順序</li>
     * <li>針對每個{@code sourceList}的來源項目，只會建立一個{@link ObservableValue}(只呼叫一次{@code grouper})</li>
     * </ul>
     *
     * @param <S> 來源集合項目型態
     * @param <K> 項目群組鍵值型態
     * @param <G> 項目群組項目型態
     * @param sourceList 來源集合
     * @param targetGroupMap 目的群組集合
     * @param grouper 群組函數(取得做群組的鍵值)，負責建立計算來源項目群組鍵的資料連結
     * @param createGroup 建立空白群組的函數
     * @param addToGroup 增加來源項目到群組的函數
     * @param removeFromGroup 從群組移除來源項目的函數，傳回true表示群組該移除
     */
    public static <S, K, G> void groupByBinding(
            ObservableList<S> sourceList,
            ObservableMap<K, G> targetGroupMap,
            Function<S, ObservableValue<K>> grouper,
            Function<K, G> createGroup,
            BiConsumer<G, S> addToGroup,
            BiPredicate<G, S> removeFromGroup)
    {
        Objects.requireNonNull(sourceList);
        Objects.requireNonNull(targetGroupMap);
        Objects.requireNonNull(grouper);
        Objects.requireNonNull(addToGroup);
        Objects.requireNonNull(removeFromGroup);
        targetGroupMap.clear();
        Map<ObservableValue<? extends K>, S> bindingToSourceMap = new HashMap<>();
        Map<S, ObservableValue<? extends K>> sourceToBindingMap = new HashMap<>();
        // 來源項目異動監聽
        ChangeListener<K> bindingListener = (b, oldK, newK) ->
            {
                S s = bindingToSourceMap.get2(b);
                G oldG = targetGroupMap.get2(oldK);
                if (removeFromGroup.test(oldG, s))
                    targetGroupMap.remove2(oldK);
                G newG = targetGroupMap.computeIfAbsent(newK, createGroup);
                addToGroup.accept(newG, s);
            };
        WeakChangeListener<K> weakBindingListener = new WeakChangeListener<>(bindingListener);
        // 初始化
        for (S s : sourceList)
        {
            ObservableValue<K> b = grouper.apply(s);
            b.addListener(weakBindingListener);
            bindingToSourceMap.put(b, s);
            sourceToBindingMap.put(s, b);
            K newK = b.getValue();
            G newG = targetGroupMap.computeIfAbsent(newK, createGroup);
            addToGroup.accept(newG, s);
        }
        // 監聽來源
        ListChangeListener<S> sourceListener = new ListChangeListener<S>()
            {
                @Override
                public void onChanged(ListChangeListener.Change<? extends S> c)
                {
                    while (c.next())
                    {
                        if (c.wasPermutated())
                            continue;
                        for (S s : c.getRemoved())
                        {
                            ObservableValue<? extends K> b = sourceToBindingMap.get2(s);
                            b.removeListener(weakBindingListener);
                            bindingToSourceMap.remove2(b);
                            sourceToBindingMap.remove2(s);
                            K oldK = b.getValue();
                            G oldG = targetGroupMap.get2(oldK);
                            if (removeFromGroup.test(oldG, s))
                                targetGroupMap.remove2(oldK);
                        }
                        for (S s : c.getAddedSubList())
                        {
                            ObservableValue<? extends K> b = grouper.apply(s);
                            b.addListener(weakBindingListener);
                            bindingToSourceMap.put(b, s);
                            sourceToBindingMap.put(s, b);
                            K newK = b.getValue();
                            G newG = targetGroupMap.computeIfAbsent(newK, createGroup);
                            addToGroup.accept(newG, s);
                        }
                    }
                }
            };
        sourceList.addListener(new WeakListChangeListener<>(sourceListener));
        // 存放監聽器的參考(生命週期應同targetGroupMap)
        targetGroupMap.addListener((InvalidationListener) iv ->
            {
                Object[] refs = { sourceListener, bindingListener, };
                Objects.requireNonNull(refs);
            });
    }

    /**
     * 透過指定的群組函數自動更新目的集合
     * <ul>
     * <li>{@code targetGroupMap}的目前內容會做清空</li>
     * <li>{@code targetGroupMap}的群組索引鍵不保證任何順序</li>
     * <li>針對每個{@code sourceList}的來源項目，只會建立一個{@code K}(只呼叫一次{@code grouper})</li>
     * </ul>
     *
     * @param <S> 來源集合項目型態
     * @param <K> 項目群組鍵值型態
     * @param sourceList 來源集合
     * @param targetGroupMap 目的群組集合
     * @param grouper 群組函數(取得做群組的鍵值)，負責計算來源項目的群組鍵
     */
    public static <S, K> void groupByValue(
            ObservableList<S> sourceList,
            ObservableMap<K, ObservableList<S>> targetGroupMap,
            Function<S, K> grouper)
    {
        groupByValue(sourceList, targetGroupMap, grouper,
                k -> FXCollections.observableArrayList(),
                (g, s) -> g.add(s),
                (g, s) ->
                    {
                        g.remove2(s);
                        return g.isEmpty();
                    });
    }

    /**
     * 透過指定的群組函數自動更新目的集合
     * <ul>
     * <li>{@code targetGroupMap}的目前內容會做清空</li>
     * <li>{@code targetGroupMap}的群組索引鍵不保證任何順序</li>
     * <li>針對每個{@code sourceList}的來源項目，只會建立一個{@code K}(只呼叫一次{@code grouper})</li>
     * </ul>
     *
     * @param <S> 來源集合項目型態
     * @param <K> 項目群組鍵值型態
     * @param <G> 項目群組項目型態
     * @param sourceList 來源集合
     * @param targetGroupMap 目的群組集合
     * @param grouper 群組函數(取得做群組的鍵值)，負責計算來源項目的資料連結
     * @param createGroup 建立空白群組的函數
     * @param addToGroup 增加來源項目到群組的函數
     * @param removeFromGroup 從群組移除來源項目的函數，傳回true表示群組該移除
     */
    public static <S, K, G> void groupByValue(
            ObservableList<S> sourceList,
            ObservableMap<K, G> targetGroupMap,
            Function<S, K> grouper,
            Function<K, G> createGroup,
            BiConsumer<G, S> addToGroup,
            BiPredicate<G, S> removeFromGroup)
    {
        Objects.requireNonNull(sourceList);
        Objects.requireNonNull(targetGroupMap);
        Objects.requireNonNull(grouper);
        Objects.requireNonNull(addToGroup);
        Objects.requireNonNull(removeFromGroup);
        targetGroupMap.clear();
        Map<S, K> sourceToKeyMap = new HashMap<>();
        // 初始化
        for (S s : sourceList)
        {
            K k = grouper.apply(s);
            sourceToKeyMap.put(s, k);
            G newG = targetGroupMap.computeIfAbsent(k, createGroup);
            addToGroup.accept(newG, s);
        }
        // 監聽來源
        ListChangeListener<S> sourceListener = new ListChangeListener<S>()
            {
                @Override
                public void onChanged(ListChangeListener.Change<? extends S> c)
                {
                    while (c.next())
                    {
                        if (c.wasPermutated())
                            continue;
                        for (S s : c.getRemoved())
                        {
                            K oldK = sourceToKeyMap.get2(s);
                            sourceToKeyMap.remove2(s);
                            G oldG = targetGroupMap.get2(oldK);
                            if (removeFromGroup.test(oldG, s))
                                targetGroupMap.remove2(oldK);
                        }
                        for (S s : c.getAddedSubList())
                        {
                            K newK = grouper.apply(s);
                            sourceToKeyMap.put(s, newK);
                            G newG = targetGroupMap.computeIfAbsent(newK, createGroup);
                            addToGroup.accept(newG, s);
                        }
                    }
                }
            };
        sourceList.addListener(new WeakListChangeListener<>(sourceListener));
        // 存放監聽器的參考(生命週期應同targetGroupMap)
        targetGroupMap.addListener((InvalidationListener) iv ->
            {
                Object[] refs = { sourceListener, };
                Objects.requireNonNull(refs);
            });
    }

    private ObservableListHelper()
    {
    }
}
