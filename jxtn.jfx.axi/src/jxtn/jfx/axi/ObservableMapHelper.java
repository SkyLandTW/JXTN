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

import java.util.Objects;

import javafx.beans.InvalidationListener;
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
     * </ul>
     *
     * @param <K> 索引鍵型態
     * @param <V> 項目值型態
     * @param sourceMap 來源字典
     * @param targetKeyList 目的集合，將存放目前集合的索引鍵
     */
    public static <K, V> void mapKeysTo(ObservableMap<K, V> sourceMap, ObservableList<K> targetKeyList)
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
     * </ul>
     *
     * @param <K> 索引鍵型態
     * @param <V> 項目值型態
     * @param sourceMap 來源字典
     * @param targetValueList 目的集合，將存放目前集合的項目值
     */
    public static <K, V> void mapValuesTo(ObservableMap<K, V> sourceMap, ObservableList<V> targetValueList)
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
                        targetValueList.remove2(c.getValueRemoved());
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

    private ObservableMapHelper()
    {
    }
}
