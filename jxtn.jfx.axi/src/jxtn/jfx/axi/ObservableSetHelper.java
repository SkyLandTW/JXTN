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
import java.util.function.Function;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.collections.WeakSetChangeListener;

/**
 * {@link ObservableSet}輔助
 *
 * @author AqD
 */
public final class ObservableSetHelper
{
    /**
     * 安排自動更新目的集合
     * <ul>
     * <li>{@code targetList}的目前內容會做清空</li>
     * </ul>
     *
     * @param <S> 集合項目型態
     * @param sourceSet 來源集
     * @param targetList 目的集合
     */
    public static <S> void map(ObservableSet<S> sourceSet, ObservableList<S> targetList)
    {
        Objects.requireNonNull(sourceSet);
        Objects.requireNonNull(targetList);
        targetList.clear();
        // 初始化
        targetList.addAll(sourceSet);
        // 監聽來源
        SetChangeListener<S> sourceListener = new SetChangeListener<S>()
            {
                @Override
                public void onChanged(SetChangeListener.Change<? extends S> c)
                {
                    if (c.wasRemoved())
                    {
                        targetList.remove2(c.getElementRemoved());
                    }
                    if (c.wasAdded())
                    {
                        targetList.add(c.getElementAdded());
                    }
                }
            };
        sourceSet.addListener(new WeakSetChangeListener<>(sourceListener));
        // 存放監聽器的參考(生命週期應同targetList)
        targetList.addListener((InvalidationListener) iv ->
            {
                Object[] refs = { sourceListener, };
                Objects.requireNonNull(refs);
            });
    }

    /**
     * 透過指定的對照函數自動更新目的集合
     * <ul>
     * <li>{@code targetList}的目前內容會做清空</li>
     * <li>針對每個{@code sourceList}的來源項目，只會建立一個{@link ObservableValue}(只呼叫一次{@code mapper})</li>
     * </ul>
     *
     * @param <S> 來源集合項目型態
     * @param <T> 目的集合項目型態
     * @param sourceSet 來源集
     * @param targetList 目的集合
     * @param mapper 對照函數，負責建立來源項目的資料連結
     */
    public static <S, T> void mapByBinding(
            ObservableSet<S> sourceSet,
            ObservableList<T> targetList,
            Function<S, ObservableValue<T>> mapper)
    {
        Objects.requireNonNull(sourceSet);
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
        for (S s : sourceSet)
        {
            ObservableValue<T> b = mapper.apply(s);
            b.addListener(weakBindingListener);
            sourceToBindingMap.put(s, b);
        }
        targetList.addAll(sourceToBindingMap.values().map(b -> b.getValue()).toArrayList());
        // 監聽來源
        SetChangeListener<S> sourceListener = new SetChangeListener<S>()
            {
                @Override
                public void onChanged(SetChangeListener.Change<? extends S> c)
                {
                    if (c.wasRemoved())
                    {
                        S s = c.getElementRemoved();
                        ObservableValue<? extends T> b = sourceToBindingMap.get2(s);
                        b.removeListener(weakBindingListener);
                        targetList.remove2(b.getValue());
                        sourceToBindingMap.remove2(s);
                    }
                    if (c.wasAdded())
                    {
                        S s = c.getElementAdded();
                        ObservableValue<T> b = mapper.apply(s);
                        b.addListener(weakBindingListener);
                        targetList.add(b.getValue());
                        sourceToBindingMap.put(s, b);
                    }
                }
            };
        sourceSet.addListener(new WeakSetChangeListener<>(sourceListener));
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
     * <li>針對每個{@code sourceList}的來源項目，只會建立一個{@code T}(只呼叫一次{@code mapper})</li>
     * </ul>
     *
     * @param <S> 來源集合項目型態
     * @param <T> 目的集合項目型態
     * @param sourceSet 來源集
     * @param targetList 目的集合
     * @param mapper 對照函數
     */
    public static <S, T> void mapByValue(
            ObservableSet<S> sourceSet,
            ObservableList<T> targetList,
            Function<S, T> mapper)
    {
        Objects.requireNonNull(sourceSet);
        Objects.requireNonNull(targetList);
        Objects.requireNonNull(mapper);
        targetList.clear();
        Map<S, T> sourceToTargetMap = new HashMap<>();
        // 初始化
        for (S s : sourceSet)
        {
            T t = mapper.apply(s);
            targetList.add(t);
            sourceToTargetMap.put(s, t);
        }
        // 監聽來源
        SetChangeListener<S> sourceListener = new SetChangeListener<S>()
            {
                @Override
                public void onChanged(SetChangeListener.Change<? extends S> c)
                {
                    if (c.wasRemoved())
                    {
                        S s = c.getElementRemoved();
                        T t = sourceToTargetMap.get2(s);
                        targetList.remove2(t);
                        sourceToTargetMap.remove2(s);
                    }
                    if (c.wasAdded())
                    {
                        S s = c.getElementAdded();
                        T t = mapper.apply(s);
                        targetList.add(t);
                        sourceToTargetMap.put(s, t);
                    }
                }
            };
        sourceSet.addListener(new WeakSetChangeListener<>(sourceListener));
        // 存放監聽器的參考(生命週期應同targetList)
        targetList.addListener((InvalidationListener) iv ->
            {
                Object[] refs = { sourceListener, };
                Objects.requireNonNull(refs);
            });
    }

    private ObservableSetHelper()
    {
    }
}
