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

package java.lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import jxtn.core.axi.collections.ConcatedIterator;
import jxtn.core.axi.collections.FilteredIterator;
import jxtn.core.axi.collections.MappedIterator;

/**
 * {@link Iterable}的延伸功能
 * <p>
 * 添加延伸時應同步更新{@link java.util.IteratorExt}
 * </p>
 *
 * @author AqD
 * @param <T> 列舉項目型態
 */
public interface IterableExt<T>
{
    /************************************************************************
     * 條件測試
     ************************************************************************/

    /**
     * 檢查是否所有項目皆符合指定條件
     *
     * @param filter 條件測試的函數
     * @return true表示符合，或沒有項目可測試。false表示任一項目不符合。
     */
    default boolean all(Predicate<? super T> filter)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().all(filter);
    }

    /**
     * 檢查是否有任一項目符合指定條件
     *
     * @param filter 條件測試的函數
     * @return true表示符合。false表示所有項目皆不符合，或沒有項目可測試。
     */
    default boolean any(Predicate<? super T> filter)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().any(filter);
    }

    /************************************************************************
     * 列舉轉換
     ************************************************************************/

    /**
     * 將目前列舉作為指定項目型態的列舉傳回
     *
     * @return 指定項目型態的列舉(物件仍為目前列舉)
     */
    @SuppressWarnings("unchecked")
    default <V> Iterable<V> as()
    {
        return (Iterable<V>) this;
    }

    /**
     * 結合多個列舉
     *
     * @param iterables 要結合在後面的其他列舉
     * @return 目前列舉及{@code iterables}的結合
     */
    @SuppressWarnings("unchecked")
    default Iterable<T> concat(Iterable<T>... iterables)
    {
        List<Iterable<T>> list = new ArrayList<>(iterables.length + 1);
        list.add((Iterable<T>) this);
        for (Iterable<T> other : iterables)
            list.add(other);
        return () -> new ConcatedIterator<>(list.iterator().map(Iterable::iterator));
    }

    /**
     * 依照條件建立過濾列舉
     *
     * @param filter 過濾條件
     * @return 過濾列舉，依賴原有的列舉
     */
    default Iterable<T> filter(Predicate<? super T> filter)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return () -> new FilteredIterator<>(thiz.iterator(), filter);
    }

    /**
     * 依照對照函數建立對照列舉
     *
     * @param mapper 對照項目的函數
     * @return 對照列舉，依賴原有的列舉
     */
    default <R> Iterable<R> map(Function<? super T, R> mapper)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return () -> new MappedIterator<>(thiz.iterator(), mapper);
    }

    /**
     * 取得指定型態的項目列舉
     *
     * @param type 要取得項目的型態
     * @return 包含{@code type}型態項目的列舉
     */
    @SuppressWarnings("unchecked")
    default <R extends T> Iterable<R> ofType(Class<R> type)
    {
        return this.filter(type::isInstance).map(t -> (R) t);
    }

    /************************************************************************
     * 項目挑選
     ************************************************************************/

    /**
     * 取得第一筆項目
     *
     * @return 第一筆項目。
     * @throws NoSuchElementException 沒有第一筆或符合條件的項目
     */
    default T first()
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().next();
    }

    /**
     * 取得符合條件的第一筆項目
     *
     * @param filter 取得項目的條件測試函數
     * @return 第一筆項目。
     * @throws NoSuchElementException 沒有第一筆或符合條件的項目
     */
    default T first(Predicate<T> filter)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().next(filter);
    }

    /**
     * 取得第一筆項目
     *
     * @return 第一筆項目，或null表示沒有項目。
     */
    default T firstOrNull()
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().nextOrNull();
    }

    /**
     * 取得符合條件的第一筆項目
     *
     * @param filter 取得項目的條件測試函數
     * @return 第一筆項目，或null表示沒有項目。
     */
    default T firstOrNull(Predicate<T> filter)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().nextOrNull(filter);
    }

    /**
     * 取得第一個可計算出最大數值的項目
     *
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最大數值的項目
     * @throws NoSuchElementException 沒有項目
     */
    default <V extends Comparable<V>> T firstOfMax(Function<? super T, V> getValue)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().nextOfMax(getValue);
    }

    /**
     * 取得第一個可計算出最大數值的項目
     *
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最大數值的項目
     * @throws NoSuchElementException 沒有項目
     */
    default T firstOfMaxDouble(ToDoubleFunction<T> getValue)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().nextOfMaxDouble(getValue);
    }

    /**
     * 取得第一個可計算出最大數值的項目
     *
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最大數值的項目
     * @throws NoSuchElementException 沒有項目
     */
    default T firstOfMaxInt(ToIntFunction<T> getValue)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().nextOfMaxInt(getValue);
    }

    /**
     * 取得第一個可計算出最大數值的項目
     *
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最大數值的項目
     * @throws NoSuchElementException 沒有項目
     */
    default T firstOfMaxLong(ToLongFunction<T> getValue)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().nextOfMaxLong(getValue);
    }

    /**
     * 取得第一個可計算出最小數值的項目
     *
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最小數值的項目
     * @throws NoSuchElementException 沒有項目
     */
    default <V extends Comparable<V>> T firstOfMin(Function<? super T, V> getValue)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().nextOfMin(getValue);
    }

    /**
     * 取得第一個可計算出最小數值的項目
     *
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最小數值的項目
     * @throws NoSuchElementException 沒有項目
     */
    default T firstOfMinDouble(ToDoubleFunction<T> getValue)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().nextOfMinDouble(getValue);
    }

    /**
     * 取得第一個可計算出最小數值的項目
     *
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最小數值的項目
     * @throws NoSuchElementException 沒有項目
     */
    default T firstOfMinInt(ToIntFunction<T> getValue)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().nextOfMinInt(getValue);
    }

    /**
     * 取得第一個可計算出最小數值的項目
     *
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最小數值的項目
     * @throws NoSuchElementException 沒有項目
     */
    default T firstOfMinLong(ToLongFunction<T> getValue)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().nextOfMinLong(getValue);
    }

    /************************************************************************
     * 項目統整
     ************************************************************************/

    /**
     * 依照鍵值做分群
     *
     * @param getKey 計算每個項目的鍵值
     * @return 分群後的結果，不依賴原有的列舉
     */
    default <K> HashMap<K, ArrayList<T>> groupBy(Function<? super T, K> getKey)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().groupBy(getKey);
    }

    /**
     * 依照鍵值做排序
     *
     * @param getKey 計算每個項目的鍵值
     * @return 排序後的結果，不依賴原有的列舉
     */
    @SuppressWarnings({ "rawtypes" })
    default <V extends Comparable> List<T> orderBy(Function<T, V> getKey)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().orderBy(getKey);
    }

    /**
     * 建立陣列
     *
     * @return 陣列
     */
    default T[] toArray(Class<T> type)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().toArray(type);
    }

    /**
     * 建立{@link ArrayList}
     *
     * @return {@link ArrayList}
     */
    default ArrayList<T> toArrayList()
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().toArrayList();
    }

    /**
     * 建立{@link HashMap}
     *
     * @param getKey 計算項目於新{@link HashMap}內的鍵值
     * @return {@link HashMap}
     */
    default <K> HashMap<K, T> toHashMap(Function<? super T, K> getKey)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().toHashMap(getKey);
    }

    /**
     * 建立{@link HashMap}
     *
     * @param getKey 計算項目於新{@link HashMap}內的鍵值
     * @param getValue 計算項目於新{@link HashMap}內的項目值
     * @return {@link HashMap}
     */
    default <K, V> HashMap<K, V> toHashMap(Function<? super T, K> getKey, Function<? super T, V> getValue)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().toHashMap(getKey, getValue);
    }

    /**
     * 建立{@link HashSet}
     * <p>
     * 重複值會被重疊覆蓋。
     * </p>
     *
     * @return {@link HashSet}
     */
    default HashSet<T> toHashSet()
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().toHashSet();
    }

    /************************************************************************
     * 數學統計
     ************************************************************************/

    /**
     * 計算項目代表數值的平均
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的平均
     */
    default double avgDouble(ToDoubleFunction<? super T> getValue)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().avgDouble(getValue);
    }

    /**
     * 計算項目代表數值的平均
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的平均
     */
    default int avgInt(ToIntFunction<? super T> getValue)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().avgInt(getValue);
    }

    /**
     * 計算項目代表數值的平均
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的平均
     */
    default long avgLong(ToLongFunction<? super T> getValue)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().avgLong(getValue);
    }

    /**
     * 計算項目代表數值的最大值
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最大值
     */
    default double maxDouble(ToDoubleFunction<T> getValue)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().maxDouble(getValue);
    }

    /**
     * 計算項目代表數值的最大值
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最大值
     */
    default int maxInt(ToIntFunction<T> getValue)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().maxInt(getValue);
    }

    /**
     * 計算項目代表數值的最大值
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最大值
     */
    default long maxLong(ToLongFunction<T> getValue)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().maxLong(getValue);
    }

    /**
     * 計算項目代表數值的最小值
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最小值
     */
    default double minDouble(ToDoubleFunction<T> getValue)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().minDouble(getValue);
    }

    /**
     * 計算項目代表數值的最小值
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最小值
     */
    default int minInt(ToIntFunction<T> getValue)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().minInt(getValue);
    }

    /**
     * 計算項目代表數值的最小值
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最小值
     */
    default long minLong(ToLongFunction<T> getValue)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().minLong(getValue);
    }

    /**
     * 計算項目代表數值的總和
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的總和
     */
    default double sumDouble(ToDoubleFunction<T> getValue)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().sumDouble(getValue);
    }

    /**
     * 計算項目代表數值的總和
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的總和
     */
    default int sumInt(ToIntFunction<T> getValue)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().sumInt(getValue);
    }

    /**
     * 計算項目代表數值的總和
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的總和
     */
    default long sumLong(ToIntFunction<T> getValue)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().sumLong(getValue);
    }

    /**
     * 計算項目代表數值的總和
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的總和
     */
    default long sumLong(ToLongFunction<T> getValue)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().sumLong(getValue);
    }
}
