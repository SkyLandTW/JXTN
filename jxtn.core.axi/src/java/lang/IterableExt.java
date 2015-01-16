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
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.BiFunctionEx;
import java.util.function.ConsumerEx;
import java.util.function.Function;
import java.util.function.FunctionEx;
import java.util.function.Predicate;
import java.util.function.PredicateEx;
import java.util.function.ToDoubleFunctionEx;
import java.util.function.ToIntFunctionEx;
import java.util.function.ToLongFunctionEx;

import jxtn.core.axi.collections.AfterConditionIterator;
import jxtn.core.axi.collections.BeforeConditionIterator;
import jxtn.core.axi.collections.ConcatedIterator;
import jxtn.core.axi.collections.ExpandedIterator;
import jxtn.core.axi.collections.FilteredIterator;
import jxtn.core.axi.collections.IndexedItem;
import jxtn.core.axi.collections.IndexedIterator;
import jxtn.core.axi.collections.LinkLineIterator;
import jxtn.core.axi.collections.LinkTreeIterator;
import jxtn.core.axi.collections.MappedIterator;
import jxtn.core.axi.collections.SkippedIterator;

/**
 * {@link Iterable}的延伸功能。
 * <p>
 * 添加延伸時應同步更新{@link java.util.IteratorExt}。
 * </p>
 *
 * @author AqD
 * @param <T> 列舉項目型態
 */
public interface IterableExt<T>
{
    /**
     * 結合多個列舉。
     *
     * @param <T> 列舉項目型態
     * @param iterables 要結合的列舉集合
     * @return 結合的列舉
     */
    @SafeVarargs
    public static <T> Iterable<T> concatAll(Iterable<? extends T>... iterables)
    {
        return () -> new ConcatedIterator<>(Arrays.asList(iterables).iterator().map(Iterable::iterator));
    }

    /**
     * 結合多個列舉。
     *
     * @param <T> 列舉項目型態
     * @param iterableIterable 要結合的列舉的列舉
     * @return 結合的列舉
     */
    public static <T> Iterable<T> concatAll(Iterable<Iterable<? extends T>> iterableIterable)
    {
        return () -> new ConcatedIterator<>(iterableIterable.iterator().map(Iterable::iterator));
    }

    /**
     * 結合多個列舉。
     *
     * @param <T> 列舉項目型態
     * @param iterableIterator 要結合的列舉的列舉器
     * @return 結合的列舉
     */
    public static <T> Iterable<T> concatAll(Iterator<Iterable<? extends T>> iterableIterator)
    {
        return () -> new ConcatedIterator<>(iterableIterator.map(Iterable::iterator));
    }

    /**
     * 建立線性結構的串接列舉
     *
     * @param <T> 列舉項目型態
     * @param item 初始項目
     * @param getNext 取得每個項目的下一個項目的函數，傳回null表示結尾
     * @return 串接列舉
     */
    public static <T> Iterable<T> linkLine(T item, Function<? super T, ? extends T> getNext)
    {
        return () -> new LinkLineIterator<>(item, getNext);
    }

    /**
     * 建立樹狀結構的串接列舉。
     *
     * @param <T> 列舉項目型態
     * @param item 初始項目；根結點
     * @param getChildren 取得每個項目的子項目集合，傳回null表示結尾
     * @return 串接列舉
     */
    public static <T> Iterable<T> linkTree(T item, Function<? super T, ? extends Iterable<? extends T>> getChildren)
    {
        return () -> new LinkTreeIterator<>(item, t ->
            {
                Iterable<? extends T> children = getChildren.apply(t);
                return children == null ? null : children.iterator();
            });
    }

    /**
     * 針對每個項目執行指定動作
     *
     * @param <TException> 動作可拋出的例外型態
     * @param action 要執行的動作
     * @throws TException 表示{@code action}丟出例外
     */
    default <TException extends Exception> void forEachEx(ConsumerEx<? super T, ? extends TException> action)
            throws TException
    {
        Objects.requireNonNull(action);
        Iterable<T> thiz = (Iterable<T>) this;
        for (T item : thiz)
        {
            action.acceptEx(item);
        }
    }

    //////////////////////////////////////////////////////////////////////////
    // 條件測試
    //

    /**
     * 檢查是否所有項目皆符合指定條件。
     *
     * @param <TException> 測試條件可拋出的例外型態
     * @param condition 條件測試的函數
     * @return true表示符合，或沒有項目可測試。false表示任一項目不符合。
     * @throws TException 表示{@code condition}丟出例外
     */
    default <TException extends Exception> boolean all(PredicateEx<? super T, ? extends TException> condition)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().all(condition);
    }

    /**
     * 檢查是否有任一項目符合指定條件。
     *
     * @param <TException> 測試條件可拋出的例外型態
     * @param condition 條件測試的函數
     * @return true表示符合。false表示所有項目皆不符合，或沒有項目可測試。
     * @throws TException 表示{@code condition}丟出例外
     */
    default <TException extends Exception> boolean any(PredicateEx<? super T, ? extends TException> condition)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().any(condition);
    }

    //////////////////////////////////////////////////////////////////////////
    // 列舉轉換
    //

    /**
     * 將目前列舉作為指定項目型態的列舉傳回。
     *
     * @param <V> 傳回項目型態。
     * @return 指定項目型態的列舉(物件仍為目前列舉)
     */
    @SuppressWarnings("unchecked")
    default <V> Iterable<V> as()
    {
        return (Iterable<V>) this;
    }

    /**
     * 結合多個項目到結尾。
     *
     * @param tailItems 要結合在結尾的其他項目
     * @return 目前列舉及{@code tailItems}的結合
     */
    @SuppressWarnings("unchecked")
    default Iterable<T> append(T... tailItems)
    {
        List<Iterable<? extends T>> list = Arrays.asList(
                (Iterable<T>) this,
                Arrays.asList(tailItems));
        return () -> new ConcatedIterator<>(list.iterator().map(Iterable::iterator));
    }

    /**
     * 結合多個項目到開頭。
     *
     * @param headItems 要結合在開頭的其他項目
     * @return 目前列舉及{@code tailItems}的結合
     */
    @SuppressWarnings("unchecked")
    default Iterable<T> prepend(T... headItems)
    {
        List<Iterable<? extends T>> list = Arrays.asList(
                Arrays.asList(headItems),
                (Iterable<T>) this);
        return () -> new ConcatedIterator<>(list.iterator().map(Iterable::iterator));
    }

    /**
     * 結合多個列舉。
     *
     * @param iterables 要結合在後面的其他列舉
     * @return 目前列舉及{@code iterables}的結合
     */
    @SuppressWarnings("unchecked")
    default Iterable<T> concat(Iterable<? extends T>... iterables)
    {
        List<Iterable<? extends T>> list = new ArrayList<>(iterables.length + 1);
        list.add((Iterable<T>) this);
        for (Iterable<? extends T> other : iterables)
            list.add(other);
        return () -> new ConcatedIterator<>(list.iterator().map(Iterable::iterator));
    }

    /**
     * 依照展開函數建立展開列舉（項目一對多展開）。
     *
     * @param <R> 展開項目型態
     * @param expander 展開項目的函數
     * @return 展開列舉，依賴原有的列舉
     */
    default <R> Iterable<R> expand(Function<? super T, ? extends Iterable<? extends R>> expander)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return () -> new ExpandedIterator<>(thiz.iterator(), t -> expander.apply(t).iterator());
    }

    /**
     * 依照條件建立過濾列舉（保留符合條件之項目）。
     *
     * @param condition 過濾條件
     * @return 過濾列舉，依賴原有的列舉
     */
    default Iterable<T> filter(Predicate<? super T> condition)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return () -> new FilteredIterator<>(thiz.iterator(), condition);
    }

    /**
     * 依照條件建立剔除開頭的過濾列舉（保留符合條件之後項目，包含該項）。
     *
     * @param condition 過濾條件
     * @return 過濾列舉，依賴原有的列舉
     */
    default Iterable<T> after(Predicate<? super T> condition)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return () -> new AfterConditionIterator<>(thiz.iterator(), condition);
    }

    /**
     * 依照條件建立剔除結尾的過濾列舉（保留符合條件之前項目，不含該項）。
     *
     * @param condition 過濾條件
     * @return 過濾列舉，依賴原有的列舉
     */
    default Iterable<T> before(Predicate<? super T> condition)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return () -> new BeforeConditionIterator<>(thiz.iterator(), condition);
    }

    /**
     * 建立加上索引的列舉。
     *
     * @return 加上索引的列舉，依賴原有的列舉
     */
    default Iterable<IndexedItem<T>> indexed()
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return () -> new IndexedIterator<>(thiz.iterator());
    }

    /**
     * 依照對照函數建立對照列舉。
     *
     * @param <R> 對照項目型態
     * @param mapper 對照項目的函數
     * @return 對照列舉，依賴原有的列舉
     */
    default <R> Iterable<R> map(Function<? super T, ? extends R> mapper)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return () -> new MappedIterator<>(thiz.iterator(), mapper);
    }

    /**
     * 建立只包含指定型態項目的列舉。
     *
     * @param <R> 要取得項目的型態
     * @param type 要取得項目的型態
     * @return 只包含{@code type}型態項目的列舉
     */
    @SuppressWarnings("unchecked")
    default <R> Iterable<R> ofType(Class<? extends R> type)
    {
        return this.filter(type::isInstance).map(t -> (R) t);
    }

    /**
     * 建立跳過指定項目數量的列舉。
     *
     * @param count 要跳過的項目數量
     * @return 跳過指定數量的列舉，依賴原有的列舉
     */
    default Iterable<T> skip(int count)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return () -> new SkippedIterator<>(thiz.iterator(), count);
    }

    //////////////////////////////////////////////////////////////////////////
    // 項目挑選
    //

    /**
     * 取得第一筆項目。
     *
     * @return 第一筆項目
     * @throws NoSuchElementException 沒有第一筆或符合條件的項目
     */
    default T first()
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().next();
    }

    /**
     * 取得符合條件的第一筆項目。
     *
     * @param <TException> 測試條件可拋出的例外型態
     * @param condition 取得項目的條件測試函數
     * @return 第一筆項目
     * @throws NoSuchElementException 沒有第一筆或符合條件的項目
     * @throws TException 表示{@code condition}丟出例外
     */
    default <TException extends Exception> T first(PredicateEx<? super T, ? extends TException> condition)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().next(condition);
    }

    /**
     * 取得第一筆項目。
     *
     * @return 第一筆項目，或null表示沒有項目
     */
    default T firstOrNull()
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().nextOrNull();
    }

    /**
     * 取得符合條件的第一筆項目。
     *
     * @param <TException> 測試條件可拋出的例外型態
     * @param condition 取得項目的條件測試函數
     * @return 第一筆項目，或null表示沒有項目
     * @throws TException 表示{@code condition}丟出例外
     */
    default <TException extends Exception> T firstOrNull(PredicateEx<? super T, ? extends TException> condition)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().nextOrNull(condition);
    }

    /**
     * 取得第一個可計算出最大數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param <V> 數值型態
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最大數值的項目
     * @throws NoSuchElementException 沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <V extends Comparable<? super V>, TException extends Exception>
            T firstOfMax(FunctionEx<? super T, ? extends V, ? extends TException> getValue)
                    throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        Iterator<T> it = thiz.iterator();
        return it.nextOfMax(getValue);
    }

    /**
     * 取得第一個可計算出最大數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param <V> 數值型態
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最大數值的項目，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <V extends Comparable<? super V>, TException extends Exception>
            T firstOfMaxOrNull(FunctionEx<? super T, ? extends V, ? extends TException> getValue)
                    throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        Iterator<T> it = thiz.iterator();
        return it.hasNext() ? it.nextOfMax(getValue) : null;
    }

    /**
     * 取得第一個可計算出最大數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最大數值的項目
     * @throws NoSuchElementException 沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> T firstOfMaxDouble(ToDoubleFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        Iterator<T> it = thiz.iterator();
        return it.nextOfMaxDouble(getValue);
    }

    /**
     * 取得第一個可計算出最大數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最大數值的項目，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> T firstOfMaxDoubleOrNull(ToDoubleFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        Iterator<T> it = thiz.iterator();
        return it.hasNext() ? it.nextOfMaxDouble(getValue) : null;
    }

    /**
     * 取得第一個可計算出最大數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最大數值的項目
     * @throws NoSuchElementException 沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> T firstOfMaxInt(ToIntFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        Iterator<T> it = thiz.iterator();
        return it.nextOfMaxInt(getValue);
    }

    /**
     * 取得第一個可計算出最大數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最大數值的項目，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> T firstOfMaxIntOrNull(ToIntFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        Iterator<T> it = thiz.iterator();
        return it.hasNext() ? it.nextOfMaxInt(getValue) : null;
    }

    /**
     * 取得第一個可計算出最大數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最大數值的項目
     * @throws NoSuchElementException 沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> T firstOfMaxLong(ToLongFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        Iterator<T> it = thiz.iterator();
        return it.nextOfMaxLong(getValue);
    }

    /**
     * 取得第一個可計算出最大數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最大數值的項目，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> T firstOfMaxLongOrNull(ToLongFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        Iterator<T> it = thiz.iterator();
        return it.hasNext() ? it.nextOfMaxLong(getValue) : null;
    }

    /**
     * 取得第一個可計算出最小數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param <V> 數值型態
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最小數值的項目
     * @throws NoSuchElementException 沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <V extends Comparable<? super V>, TException extends Exception>
            T firstOfMin(FunctionEx<? super T, ? extends V, ? extends TException> getValue)
                    throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        Iterator<T> it = thiz.iterator();
        return it.nextOfMin(getValue);
    }

    /**
     * 取得第一個可計算出最小數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param <V> 數值型態
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最小數值的項目，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <V extends Comparable<? super V>, TException extends Exception>
            T firstOfMinOrNull(FunctionEx<? super T, ? extends V, ? extends TException> getValue)
                    throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        Iterator<T> it = thiz.iterator();
        return it.hasNext() ? it.nextOfMin(getValue) : null;
    }

    /**
     * 取得第一個可計算出最小數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最小數值的項目
     * @throws NoSuchElementException 沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> T firstOfMinDouble(ToDoubleFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        Iterator<T> it = thiz.iterator();
        return it.nextOfMinDouble(getValue);
    }

    /**
     * 取得第一個可計算出最小數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最小數值的項目，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> T firstOfMinDoubleOrNull(ToDoubleFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        Iterator<T> it = thiz.iterator();
        return it.hasNext() ? it.nextOfMinDouble(getValue) : null;
    }

    /**
     * 取得第一個可計算出最小數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最小數值的項目
     * @throws NoSuchElementException 沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> T firstOfMinInt(ToIntFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        Iterator<T> it = thiz.iterator();
        return it.nextOfMinInt(getValue);
    }

    /**
     * 取得第一個可計算出最小數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最小數值的項目，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> T firstOfMinIntOrNull(ToIntFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        Iterator<T> it = thiz.iterator();
        return it.hasNext() ? it.nextOfMinInt(getValue) : null;
    }

    /**
     * 取得第一個可計算出最小數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最小數值的項目
     * @throws NoSuchElementException 沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> T firstOfMinLong(ToLongFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        Iterator<T> it = thiz.iterator();
        return it.nextOfMinLong(getValue);
    }

    /**
     * 取得第一個可計算出最小數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 第一個計算出最小數值的項目，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> T firstOfMinLongOrNull(ToLongFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        Iterator<T> it = thiz.iterator();
        return it.hasNext() ? it.nextOfMinLong(getValue) : null;
    }

    /**
     * 取得第N筆項目。
     *
     * @param position 位置
     * @return 第N筆項目
     * @throws NoSuchElementException 沒有第N筆項目
     */
    default T getNth(int position)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().nextNth(position);
    }

    /**
     * 取得第N筆項目。
     *
     * @param position 位置
     * @return 第N筆項目，或null表示沒有第N筆項目
     */
    default T getNthOrNull(int position)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().nextNthOrNull(position);
    }

    //////////////////////////////////////////////////////////////////////////
    // 項目統整
    //

    /**
     * 用目前項目值建立陣列。
     *
     * @param type 陣列項目型態
     * @return 包含目前項目的陣列
     */
    default T[] toArray(Class<T> type)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().toArray(type);
    }

    /**
     * 用目前項目值建立{@link ArrayList}。
     *
     * @return 包含目前項目的{@link ArrayList}
     */
    default ArrayList<T> toArrayList()
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().toArrayList();
    }

    /**
     * 用目前項目值建立{@link ArrayList}，依照條件做過濾。
     *
     * @param <TException> 測試條件可拋出的例外型態
     * @param condition 取得項目的條件測試函數
     * @return 包含符合條件項目的{@link ArrayList}
     * @throws TException 表示{@code mapper}丟出例外
     */
    default <TException extends Exception> ArrayList<T>
            toArrayListFiltered(PredicateEx<? super T, ? extends TException> condition)
                    throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().toArrayListFiltered(condition);
    }

    /**
     * 用目前項目值建立{@link ArrayList}，依照函數做對照。
     *
     * @param <R> 對照項目型態
     * @param <TException> 對照函數可拋出的例外型態
     * @param mapper 對照項目的函數
     * @return 包含目前項目對照結果的{@link ArrayList}
     * @throws TException 表示{@code mapper}丟出例外
     */
    default <R, TException extends Exception> ArrayList<R>
            toArrayListMapped(FunctionEx<? super T, ? extends R, ? extends TException> mapper)
                    throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().toArrayListMapped(mapper);
    }

    /**
     * 用目前項目值建立{@link ArrayList}，依照鍵值做排序。
     *
     * @param <V> 鍵值型態
     * @param getKey 計算每個項目的鍵值
     * @return 包含目前項目的{@link ArrayList}，已排序
     */
    default <V extends Comparable<?>> ArrayList<T> toArrayListSorted(Function<? super T, ? extends V> getKey)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().toArrayListSorted(getKey);
    }

    /**
     * 用目前項目值建立{@link ArrayList}，依照比較器做排序。
     *
     * @param comparator 項目的比較器
     * @return 包含目前項目的{@link ArrayList}，已排序
     */
    default ArrayList<T> toArrayListSorted(Comparator<? super T> comparator)
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().toArrayListSorted(comparator);
    }

    /**
     * 用目前項目值建立{@link HashMap}。
     *
     * @param <K> {@link HashMap}鍵值型態
     * @param <KException> 計算鍵值函數可拋出的例外型態
     * @param getKey 計算項目於新{@link HashMap}內的鍵值
     * @return 包含目前項目對照結果的{@link HashMap}
     * @throws KException 表示{@code getKey}丟出例外
     */
    default <K, KException extends Exception>
            HashMap<K, T> toHashMap(FunctionEx<? super T, ? extends K, ? extends KException> getKey)
                    throws KException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().toHashMap(getKey);
    }

    /**
     * 用目前項目值建立{@link HashMap}。
     *
     * @param <K> {@link HashMap}鍵值型態
     * @param <V> {@link HashMap}項目值型態
     * @param <KException> 計算鍵值函數可拋出的例外型態
     * @param <VException> 計算項目值函數可拋出的例外型態
     * @param getKey 計算項目於新{@link HashMap}內的鍵值
     * @param getValue 計算項目於新{@link HashMap}內的項目值
     * @return 包含目前項目對照結果的{@link HashMap}
     * @throws KException 表示{@code getKey}丟出例外
     * @throws VException 表示{@code getValue}丟出例外
     */
    default <K, V, KException extends Exception, VException extends Exception>
            HashMap<K, V> toHashMap(
                    FunctionEx<? super T, ? extends K, ? extends KException> getKey,
                    FunctionEx<? super T, ? extends V, ? extends VException> getValue)
                    throws KException, VException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().toHashMap(getKey, getValue);
    }

    /**
     * 用目前項目值建立{@link HashMap}，依照鍵值做分群。
     *
     * @param <K> 分群鍵值型態
     * @param <KException> 計算鍵值函數可拋出的例外型態
     * @param getKey 計算每個項目的鍵值
     * @return 包含目前項目分群組的{@link HashMap}
     * @throws KException 表示{@code getKey}丟出例外
     */
    default <K, KException extends Exception>
            HashMap<K, ArrayList<T>> toHashMapGrouped(FunctionEx<? super T, ? extends K, ? extends KException> getKey)
                    throws KException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().toHashMapGrouped(getKey);
    }

    /**
     * 用目前項目值建立{@link HashMap}，依照鍵值做分群。
     *
     * @param <K> 群組鍵值型態
     * @param <V> 項目值型態
     * @param <KException> 計算鍵值函數可拋出的例外型態
     * @param <VException> 計算項目值函數可拋出的例外型態
     * @param getKey 計算每個項目做分組的鍵值
     * @param getValue 計算項目於新{@link HashMap}內的項目值
     * @return 包含目前項目分群組的{@link HashMap}
     * @throws KException 表示{@code getKey}丟出例外
     * @throws VException 表示{@code getValue}丟出例外
     */
    default <K, V, KException extends Exception, VException extends Exception>
            HashMap<K, ArrayList<V>> toHashMapGrouped(
                    FunctionEx<? super T, ? extends K, ? extends KException> getKey,
                    FunctionEx<? super T, ? extends V, ? extends VException> getValue)
                    throws KException, VException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().toHashMapGrouped(getKey, getValue);
    }

    /**
     * 用目前項目值建立{@link HashSet}。
     * <p>
     * 重複值會被重疊覆蓋，後面的優先。
     * </p>
     *
     * @return 包含目前項目的{@link HashSet}
     */
    default HashSet<T> toHashSet()
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().toHashSet();
    }

    /**
     * 用目前項目值建立{@link TreeMap}。
     *
     * @param <K> {@link TreeMap}鍵值型態
     * @param <KException> 計算鍵值函數可拋出的例外型態
     * @param getKey 計算項目於新{@link TreeMap}內的鍵值
     * @return 包含目前項目對照結果的{@link TreeMap}
     * @throws KException 表示{@code getKey}丟出例外
     */
    default <K, KException extends Exception>
            TreeMap<K, T> toTreeMap(FunctionEx<? super T, ? extends K, ? extends KException> getKey)
                    throws KException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().toTreeMap(getKey);
    }

    /**
     * 用目前項目值建立{@link TreeMap}。
     *
     * @param <K> {@link TreeMap}鍵值型態
     * @param <V> {@link TreeMap}項目值型態
     * @param <KException> 計算鍵值函數可拋出的例外型態
     * @param <VException> 計算項目值函數可拋出的例外型態
     * @param getKey 計算項目於新{@link TreeMap}內的鍵值
     * @param getValue 計算項目於新{@link TreeMap}內的項目值
     * @return 包含目前項目對照結果的{@link TreeMap}
     * @throws KException 表示{@code getKey}丟出例外
     * @throws VException 表示{@code getValue}丟出例外
     */
    default <K, V, KException extends Exception, VException extends Exception>
            TreeMap<K, V> toTreeMap(
                    FunctionEx<? super T, ? extends K, ? extends KException> getKey,
                    FunctionEx<? super T, ? extends V, ? extends VException> getValue)
                    throws KException, VException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().toTreeMap(getKey, getValue);
    }

    /**
     * 用目前項目值建立{@link TreeMap}，依照鍵值做分群。
     *
     * @param <K> 分群鍵值型態
     * @param <KException> 計算鍵值函數可拋出的例外型態
     * @param getKey 計算每個項目的鍵值
     * @return 包含目前項目分群組的{@link TreeMap}
     * @throws KException 表示{@code getKey}丟出例外
     */
    default <K, KException extends Exception>
            TreeMap<K, ArrayList<T>> toTreeMapGrouped(FunctionEx<? super T, ? extends K, ? extends KException> getKey)
                    throws KException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().toTreeMapGrouped(getKey);
    }

    /**
     * 用目前項目值建立{@link TreeMap}，依照鍵值做分群。
     *
     * @param <K> 群組鍵值型態
     * @param <V> 項目值型態
     * @param <KException> 計算鍵值函數可拋出的例外型態
     * @param <VException> 計算項目值函數可拋出的例外型態
     * @param getKey 計算每個項目做分組的鍵值
     * @param getValue 計算項目於新{@link TreeMap}內的項目值
     * @return 包含目前項目分群組的{@link TreeMap}
     * @throws KException 表示{@code getKey}丟出例外
     * @throws VException 表示{@code getValue}丟出例外
     */
    default <K, V, KException extends Exception, VException extends Exception>
            TreeMap<K, ArrayList<V>> toTreeMapGrouped(
                    FunctionEx<? super T, ? extends K, ? extends KException> getKey,
                    FunctionEx<? super T, ? extends V, ? extends VException> getValue)
                    throws KException, VException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().toTreeMapGrouped(getKey, getValue);
    }

    //////////////////////////////////////////////////////////////////////////
    // 數學統計
    //
    /**
     * 進行歸約動作
     *
     * @param <U> 歸約結果型態
     * @param <TException> 累加函數可拋出的例外型態
     * @param identity 初始值
     * @param accumulator 累加函數
     * @return 歸約結果
     * @throws TException 表示{@code accumulator}丟出例外
     */
    default <U, TException extends Exception> U reduce(
            U identity, BiFunctionEx<U, ? super T, U, TException> accumulator)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().reduce(identity, accumulator);
    }

    /**
     * 統計符合條件的項目數量。
     *
     * @param <TException> 過濾條件函數可拋出的例外型態
     * @param condition 過濾條件的函數
     * @return 符合條件的項目數量
     * @throws TException 表示{@code condition}丟出例外
     */
    default <TException extends Exception> int count(PredicateEx<? super T, ? extends TException> condition)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().count(condition);
    }

    /**
     * 計算項目代表數值的平均。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的平均，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> Double avgDouble(ToDoubleFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().avgDouble(getValue);
    }

    /**
     * 計算項目代表數值的平均。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的平均，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> Integer avgInt(ToIntFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().avgInt(getValue);
    }

    /**
     * 計算項目代表數值的平均。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的平均，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> Long avgLong(ToLongFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().avgLong(getValue);
    }

    /**
     * 計算項目代表數值的最大值。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最大值，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> Double maxDouble(ToDoubleFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().maxDouble(getValue);
    }

    /**
     * 計算項目代表數值的最大值。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最大值，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> Integer maxInt(ToIntFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().maxInt(getValue);
    }

    /**
     * 計算項目代表數值的最大值。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最大值，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> Long maxLong(ToLongFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().maxLong(getValue);
    }

    /**
     * 計算項目代表數值的最小值。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最小值，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> Double minDouble(ToDoubleFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().minDouble(getValue);
    }

    /**
     * 計算項目代表數值的最小值。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最小值，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> Integer minInt(ToIntFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().minInt(getValue);
    }

    /**
     * 計算項目代表數值的最小值。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最小值，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> Long minLong(ToLongFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().minLong(getValue);
    }

    /**
     * 計算項目代表數值的總和。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的總和
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> double sumDouble(ToDoubleFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().sumDouble(getValue);
    }

    /**
     * 計算項目代表數值的總和。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的總和
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> int sumInt(ToIntFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().sumInt(getValue);
    }

    /**
     * 計算項目代表數值的總和。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的總和
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> long sumLong(ToIntFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().sumLong(getValue);
    }

    /**
     * 計算項目代表數值的總和。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的總和
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> long sumLong(ToLongFunctionEx<? super T, ? extends TException> getValue)
            throws TException
    {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().sumLong(getValue);
    }
}
