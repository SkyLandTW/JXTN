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

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.WeakListChangeListener;
import javafx.scene.control.TreeItem;

/**
 * {@link ObservableList}輔助
 *
 * @author AqD
 */
public final class ObservableListHelper
{
    //////////////////////////////////////////////////////////////////////////
    // 對照
    //

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
            Function<? super S, ? extends ObservableValue<T>> mapper)
    {
        Object[] refs = mapByBinding(sourceList, targetList, mapper, null);
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
     * <li>{@code targetList}的項目順序比照{@code sourceList}</li>
     * <li>針對每個{@code sourceList}的來源項目，只會建立一個{@link ObservableValue}(只呼叫一次{@code mapper})</li>
     * </ul>
     *
     * @param <S> 來源集合項目型態
     * @param <T> 目的集合項目型態
     * @param sourceList 來源集合
     * @param targetList 目的集合
     * @param mapper 對照函數，負責建立來源項目的資料連結
     * @param onRemoved 移除目的後的callback，可為null
     * @return 監聽器參考
     */
    public static <S, T> Object[] mapByBinding(
            ObservableList<S> sourceList,
            List<T> targetList,
            Function<? super S, ? extends ObservableValue<T>> mapper,
            Consumer<? super ObservableValue<? super T>> onRemoved)
    {
        Objects.requireNonNull(sourceList);
        Objects.requireNonNull(targetList);
        Objects.requireNonNull(mapper);
        targetList.clear();
        Map<S, ObservableValue<T>> sourceToBindingMap = new HashMap<>();
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
                                ObservableValue<T> b = sourceToBindingMap.get2(s);
                                targetList.set(i, b.getValue());
                            }
                        }
                        else
                        {
                            for (S s : c.getRemoved())
                            {
                                ObservableValue<T> b = sourceToBindingMap.get2(s);
                                b.removeListener(weakBindingListener);
                                targetList.remove2(b.getValue());
                                sourceToBindingMap.remove2(s);
                                if (onRemoved != null)
                                    onRemoved.accept(b);
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
        return new Object[] { sourceListener, bindingListener, };
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
            Function<? super S, ? extends T> mapper)
    {
        Object[] refs = mapByValue(sourceList, targetList, mapper, null);
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
     * <li>{@code targetList}的項目順序比照{@code sourceList}</li>
     * <li>針對每個{@code sourceList}的來源項目，只會建立一個{@code T}(只呼叫一次{@code mapper})</li>
     * </ul>
     *
     * @param <S> 來源集合項目型態
     * @param <T> 目的集合項目型態
     * @param sourceList 來源集合
     * @param targetList 目的集合
     * @param mapper 對照函數
     * @param onRemoved 移除目的後的callback，可為null
     * @return 監聽器參考
     */
    public static <S, T> Object[] mapByValue(
            ObservableList<S> sourceList,
            List<T> targetList,
            Function<? super S, ? extends T> mapper,
            Consumer<? super T> onRemoved)
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
                                if (onRemoved != null)
                                    onRemoved.accept(t);
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
        return new Object[] { sourceListener, };
    }

    //////////////////////////////////////////////////////////////////////////
    // 分組
    //

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
            Function<? super S, ObservableValue<K>> grouper)
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
            Function<? super S, ObservableValue<K>> grouper,
            Function<K, G> createGroup,
            BiConsumer<G, S> addToGroup,
            BiPredicate<G, S> removeFromGroup)
    {
        Object[] refs = groupByBinding(sourceList, (Map<K, G>) targetGroupMap,
                grouper, createGroup, addToGroup, removeFromGroup);
        // 存放監聽器的參考(生命週期應同targetGroupMap)
        targetGroupMap.addListener((InvalidationListener) iv ->
            {
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
     * @param <G> 項目群組項目型態
     * @param sourceList 來源集合
     * @param targetGroupMap 目的群組集合
     * @param grouper 群組函數(取得做群組的鍵值)，負責建立計算來源項目群組鍵的資料連結
     * @param createGroup 建立空白群組的函數
     * @param addToGroup 增加來源項目到群組的函數
     * @param removeFromGroup 從群組移除來源項目的函數，傳回true表示群組該移除
     * @return 監聽器參考
     */
    public static <S, K, G> Object[] groupByBinding(
            ObservableList<S> sourceList,
            Map<K, G> targetGroupMap,
            Function<? super S, ObservableValue<K>> grouper,
            Function<? super K, ? extends G> createGroup,
            BiConsumer<? super G, ? super S> addToGroup,
            BiPredicate<? super G, ? super S> removeFromGroup)
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
        return new Object[] { sourceListener, bindingListener, };
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
            Function<? super S, ? extends K> grouper)
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
            Function<? super S, ? extends K> grouper,
            Function<? super K, ? extends G> createGroup,
            BiConsumer<? super G, ? super S> addToGroup,
            BiPredicate<? super G, ? super S> removeFromGroup)
    {
        Object[] refs = groupByValue(sourceList, (Map<K, G>) targetGroupMap,
                grouper, createGroup, addToGroup, removeFromGroup);
        // 存放監聽器的參考(生命週期應同targetGroupMap)
        targetGroupMap.addListener((InvalidationListener) iv ->
            {
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
     * @param <G> 項目群組項目型態
     * @param sourceList 來源集合
     * @param targetGroupMap 目的群組集合
     * @param grouper 群組函數(取得做群組的鍵值)，負責計算來源項目的資料連結
     * @param createGroup 建立空白群組的函數
     * @param addToGroup 增加來源項目到群組的函數
     * @param removeFromGroup 從群組移除來源項目的函數，傳回true表示群組該移除
     * @return 監聽器參考
     */
    public static <S, K, G> Object[] groupByValue(
            ObservableList<S> sourceList,
            Map<K, G> targetGroupMap,
            Function<? super S, ? extends K> grouper,
            Function<? super K, ? extends G> createGroup,
            BiConsumer<? super G, ? super S> addToGroup,
            BiPredicate<? super G, ? super S> removeFromGroup)
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
        return new Object[] { sourceListener, };
    }

    //////////////////////////////////////////////////////////////////////////
    // 組織為樹狀結構
    //

    /**
     * 透過指定的上層取得函數自動組織樹狀結構到{@link TreeItem}
     * <ul>
     * <li>{@code targetRoot}的目前子項目會做清空</li>
     * <li>{@code targetList}的子項目順序比照{@code sourceList}</li>
     * <li>項目新加入到{@code sourceList}時，該項目的上層項目會先建立{@link TreeItem}節點並加入</li>
     * <li>項目從{@code sourceList}被移除時，該項目的子項目的節點會被移到{@code targetRoot}</li>
     * </ul>
     *
     * @param <E> 項目型態
     * @param sourceList 項目集合
     * @param targetRoot 目的根結點
     * @param getParent 取得項目之上層項目的函數，傳回null表示最上層(節點接到{@code targetRoot})
     * @param createNode 從項目建立節點({@link TreeItem})的函數
     */
    public static <E> void organizeByValue(
            ObservableList<E> sourceList,
            TreeItem<E> targetRoot,
            UnaryOperator<E> getParent,
            Function<? super E, ? extends TreeItem<E>> createNode)
    {
        Objects.requireNonNull(sourceList);
        Objects.requireNonNull(targetRoot);
        Objects.requireNonNull(getParent);
        Objects.requireNonNull(createNode);
        targetRoot.getChildren().clear();
        Map<E, TreeItem<E>> sourceToNodeMap = new HashMap<>();
        // 初始化
        TreeFactory.rebuildTree(sourceList, targetRoot, getParent, createNode, sourceToNodeMap);
        // 監聽來源
        ListChangeListener<E> sourceListener = new ListChangeListener<E>()
            {
                @Override
                public void onChanged(ListChangeListener.Change<? extends E> c)
                {
                    while (c.next())
                    {
                        if (c.wasPermutated())
                        {
                            TreeFactory.rebuildTree(sourceList, targetRoot, getParent, createNode, sourceToNodeMap);
                        }
                        else
                        {
                            for (E e : c.getRemoved())
                            {
                                TreeItem<E> node = sourceToNodeMap.get2(e);
                                node.getParent().getChildren().remove2(node);
                                sourceToNodeMap.remove2(e);
                                TreeFactory.moveChildNodesToRoot(targetRoot, node.getChildren());
                            }
                            int index = c.getFrom();
                            for (E e : c.getAddedSubList())
                            {
                                if (!sourceToNodeMap.containsKey2(e))
                                    TreeFactory.addTreeElement(targetRoot, getParent, createNode, sourceToNodeMap, e, sourceList, index);
                                index += 1;
                            }
                        }
                    }
                }
            };
        sourceList.addListener(new WeakListChangeListener<>(sourceListener));
        // 存放監聽器的參考(生命週期應同targetList)
        targetRoot.addEventHandler(TreeItem.valueChangedEvent(), e ->
            {
                Object[] refs = { sourceListener, };
                Objects.requireNonNull(refs);
            });
    }

    /**
     * 透過指定的上層取得函數自動組織樹狀結構到{@link TreeItem}
     * <ul>
     * <li>{@code targetRoot}的目前子項目會做清空</li>
     * <li>{@code targetList}的子項目順序比照{@code sourceList}</li>
     * <li>項目新加入到{@code sourceList}時，該項目的上層項目會先建立{@link TreeItem}節點並加入</li>
     * <li>項目從{@code sourceList}被移除時，該項目的子項目的節點會被移到{@code targetRoot}</li>
     * </ul>
     *
     * @param <E> 項目型態
     * @param sourceList 項目集合
     * @param targetRoot 目的根結點
     * @param getParent 取得項目之上層項目的函數，傳回null表示最上層(節點接到{@code targetRoot})
     * @param createNode 從項目建立節點({@link TreeItem})的函數
     * @param comparator 項目比較器
     */
    public static <E> void organizeByValue(
            ObservableList<E> sourceList,
            TreeItem<E> targetRoot,
            UnaryOperator<E> getParent,
            Function<? super E, ? extends TreeItem<E>> createNode,
            Comparator<? super E> comparator)
    {
        Objects.requireNonNull(sourceList);
        Objects.requireNonNull(targetRoot);
        Objects.requireNonNull(getParent);
        Objects.requireNonNull(createNode);
        Objects.requireNonNull(comparator);
        Comparator<TreeItem<E>> nodeComparator = new Comparator<TreeItem<E>>()
            {
                @Override
                public int compare(TreeItem<E> o1, TreeItem<E> o2)
                {
                    return comparator.compare(o1.getValue(), o2.getValue());
                }
            };
        targetRoot.getChildren().clear();
        Map<E, TreeItem<E>> sourceToNodeMap = new HashMap<>();
        // 初始化
        TreeFactory.rebuildTree(sourceList, targetRoot, getParent, createNode, sourceToNodeMap, nodeComparator);
        // 監聽來源
        ListChangeListener<E> sourceListener = new ListChangeListener<E>()
            {
                @Override
                public void onChanged(ListChangeListener.Change<? extends E> c)
                {
                    while (c.next())
                    {
                        if (c.wasPermutated())
                        {
                            TreeFactory.rebuildTree(sourceList, targetRoot, getParent, createNode, sourceToNodeMap, nodeComparator);
                        }
                        else
                        {
                            for (E e : c.getRemoved())
                            {
                                TreeItem<E> node = sourceToNodeMap.get2(e);
                                node.getParent().getChildren().remove2(node);
                                sourceToNodeMap.remove2(e);
                                TreeFactory.moveChildNodesToRoot(targetRoot, node.getChildren(), nodeComparator);
                            }
                            for (E e : c.getAddedSubList())
                            {
                                if (!sourceToNodeMap.containsKey2(e))
                                    TreeFactory.addTreeElement(targetRoot, getParent, createNode, sourceToNodeMap, e, nodeComparator);
                            }
                        }
                    }
                }
            };
        sourceList.addListener(new WeakListChangeListener<>(sourceListener));
        // 存放監聽器的參考(生命週期應同targetList)
        targetRoot.addEventHandler(TreeItem.valueChangedEvent(), e ->
            {
                Object[] refs = { sourceListener, };
                Objects.requireNonNull(refs);
            });
    }

    //////////////////////////////////////////////////////////////////////////

    private ObservableListHelper()
    {
    }
}
