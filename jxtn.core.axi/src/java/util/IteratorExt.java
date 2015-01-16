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

package java.util;

import java.lang.reflect.Array;
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
import jxtn.core.axi.comparators.MemberComparators;

/**
 * {@link Iterator}的延伸功能。
 * <p>
 * 添加延伸時應同步更新{@link java.lang.IterableExt}。
 * </p>
 *
 * @author AqD
 * @param <E> 列舉項目型態
 */
public interface IteratorExt<E>
{
    /**
     * 結合多個列舉器。
     *
     * @param <T> 列舉項目型態
     * @param iterators 要結合的列舉器集合
     * @return 結合的列舉器
     */
    @SafeVarargs
    public static <T> Iterator<T> concatAll(Iterator<? extends T>... iterators)
    {
        return new ConcatedIterator<>(Arrays.asList(iterators).iterator());
    }

    /**
     * 結合多個列舉器。
     *
     * @param <T> 列舉項目型態
     * @param iteratorIterable 要結合的列舉器的列舉
     * @return 結合的列舉器
     */
    public static <T> Iterator<T> concatAll(Iterable<Iterator<? extends T>> iteratorIterable)
    {
        return new ConcatedIterator<>(iteratorIterable.iterator());
    }

    /**
     * 結合多個列舉器。
     *
     * @param <T> 列舉項目型態
     * @param iteratorIterator 要結合的列舉器的列舉器
     * @return 結合的列舉器
     */
    public static <T> Iterator<T> concatAll(Iterator<Iterator<? extends T>> iteratorIterator)
    {
        return new ConcatedIterator<>(iteratorIterator);
    }

    /**
     * 建立線性結構的串接列舉器。
     *
     * @param <T> 列舉項目型態
     * @param item 初始項目
     * @param getNext 取得每個項目的下一個項目的函數，傳回null表示結尾
     * @return 串接列舉器
     */
    public static <T> Iterator<T> linkLine(T item, Function<? super T, ? extends T> getNext)
    {
        return new LinkLineIterator<>(item, getNext);
    }

    /**
     * 建立樹狀結構的串接列舉器。
     *
     * @param <T> 列舉項目型態
     * @param item 初始項目；根結點
     * @param getChildren 取得每個項目的子項目集合，傳回null表示結尾
     * @return 串接列舉器
     */
    public static <T> Iterator<T> linkTree(T item, Function<? super T, ? extends Iterator<? extends T>> getChildren)
    {
        return new LinkTreeIterator<>(item, getChildren);
    }

    /**
     * 針對每個項目執行指定動作
     *
     * @param <TException> 動作可拋出的例外型態
     * @param action 要執行的動作
     * @throws TException 表示{@code action}丟出例外
     */
    default <TException extends Exception> void forEachEx(ConsumerEx<? super E, ? extends TException> action)
            throws TException
    {
        Objects.requireNonNull(action);
        Iterator<E> thiz = (Iterator<E>) this;
        while (thiz.hasNext())
        {
            action.acceptEx(thiz.next());
        }
    }

    //////////////////////////////////////////////////////////////////////////
    // 條件測試
    //

    /**
     * 檢查是否所有剩餘項目皆符合指定條件。
     * <p>
     * 結束後列舉器會停在結尾。
     * </p>
     *
     * @param <TException> 測試條件可拋出的例外型態
     * @param condition 條件測試的函數
     * @return true表示符合，或沒有項目可測試false表示任一項目不符合
     * @throws TException 表示{@code condition}丟出例外
     */
    default <TException extends Exception> boolean all(PredicateEx<? super E, ? extends TException> condition)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        while (thiz.hasNext())
        {
            E item = thiz.next();
            if (!condition.testEx(item))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 檢查是否有任一項目符合指定條件。
     * <p>
     * 結束後列舉器會停在第一筆符合項目之後或是結尾。
     * </p>
     *
     * @param <TException> 測試條件可拋出的例外型態
     * @param condition 條件測試的函數
     * @return true表示符合false表示所有項目皆不符合，或沒有項目可測試
     * @throws TException 表示{@code condition}丟出例外
     */
    default <TException extends Exception> boolean any(PredicateEx<? super E, ? extends TException> condition)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        while (thiz.hasNext())
        {
            E item = thiz.next();
            if (condition.testEx(item))
            {
                return true;
            }
        }
        return false;
    }

    //////////////////////////////////////////////////////////////////////////
    // 列舉轉換
    //

    /**
     * 將目前列舉器作為指定項目型態的列舉器傳回。
     *
     * @param <V> 傳回項目型態
     * @return 指定項目型態的列舉器(物件仍為目前列舉器)
     */
    @SuppressWarnings("unchecked")
    default <V> Iterator<V> as()
    {
        return (Iterator<V>) this;
    }

    /**
     * 結合多個項目到結尾。
     *
     * @param tailItems 要結合在結尾的其他項目
     * @return 目前列舉及{@code tailItems}的結合
     */
    @SuppressWarnings("unchecked")
    default Iterator<E> append(E... tailItems)
    {
        List<Iterator<? extends E>> list = Arrays.asList(
                (Iterator<E>) this,
                Arrays.asList(tailItems).iterator());
        return new ConcatedIterator<>(list.iterator());
    }

    /**
     * 結合多個項目到開頭。
     *
     * @param headItems 要結合在開頭的其他項目
     * @return 目前列舉及{@code tailItems}的結合
     */
    @SuppressWarnings("unchecked")
    default Iterator<E> prepend(E... headItems)
    {
        List<Iterator<? extends E>> list = Arrays.asList(
                Arrays.asList(headItems).iterator(),
                (Iterator<E>) this);
        return new ConcatedIterator<>(list.iterator());
    }

    /**
     * 結合多個列舉器。
     *
     * @param iterators 要結合在後面的其他列舉器
     * @return 目前列舉器及{@code iterators}的結合
     */
    @SuppressWarnings("unchecked")
    default Iterator<E> concat(Iterator<? extends E>... iterators)
    {
        List<Iterator<? extends E>> list = new ArrayList<>(iterators.length + 1);
        list.add((Iterator<E>) this);
        for (Iterator<? extends E> other : iterators)
            list.add(other);
        return new ConcatedIterator<>(list.iterator());
    }

    /**
     * 依照展開函數建立展開列舉器。
     *
     * @param <R> 展開項目型態
     * @param expander 展開項目的函數
     * @return 展開列舉器，依賴原有的列舉器
     */
    default <R> Iterator<R> expand(Function<? super E, Iterator<R>> expander)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        return new ExpandedIterator<>(thiz, expander);
    }

    /**
     * 依照條件建立過濾列舉器。
     *
     * @param condition 過濾條件
     * @return 過濾列舉器，依賴原有的列舉器
     */
    default Iterator<E> filter(Predicate<? super E> condition)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        return new FilteredIterator<>(thiz, condition);
    }

    /**
     * 依照條件建立剔除開頭的列舉器。
     * <p>
     * 列舉內容只保留第一個符合條件後的所有項目（包含該項目）。
     * </p>
     *
     * @param condition 過濾條件
     * @return 過濾列舉器，依賴原有的列舉器
     */
    default Iterator<E> after(Predicate<? super E> condition)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        return new AfterConditionIterator<>(thiz, condition);
    }

    /**
     * 依照條件建立剔除結尾的列舉器。
     * <p>
     * 列舉內容只保留第一個符合條件前的所有項目（不含該項目）。
     * </p>
     *
     * @param condition 過濾條件
     * @return 過濾列舉器，依賴原有的列舉器
     */
    default Iterator<E> before(Predicate<? super E> condition)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        return new BeforeConditionIterator<>(thiz, condition);
    }

    /**
     * 建立加上索引的列舉器。
     *
     * @return 加上索引的列舉器，依賴原有的列舉器
     */
    default Iterator<IndexedItem<E>> indexed()
    {
        Iterator<E> thiz = (Iterator<E>) this;
        return new IndexedIterator<>(thiz);
    }

    /**
     * 依照對照函數建立對照列舉器。
     *
     * @param <R> 對照項目型態
     * @param mapper 對照項目的函數
     * @return 對照列舉器，依賴原有的列舉器
     */
    default <R> Iterator<R> map(Function<? super E, ? extends R> mapper)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        return new MappedIterator<>(thiz, mapper);
    }

    /**
     * 建立只包含指定型態項目的列舉器。
     *
     * @param <R> 要取得項目的型態
     * @param type 要取得項目的型態
     * @return 只包含{@code type}型態項目的列舉
     */
    @SuppressWarnings("unchecked")
    default <R> Iterator<R> ofType(Class<? extends R> type)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        return thiz.filter(type::isInstance).map(e -> (R) e);
    }

    /**
     * 建立跳過指定項目數量的列舉器。
     *
     * @param count 要跳過的項目數量
     * @return 跳過指定數量的列舉器，依賴原有的列舉器
     */
    default Iterator<E> skip(int count)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        return new SkippedIterator<>(thiz, count);
    }

    //////////////////////////////////////////////////////////////////////////
    // 項目挑選
    //

    /**
     * 取得符合條件的下一筆項目。
     * <p>
     * 結束後列舉器會停在第一筆符合項目之後或是結尾。
     * </p>
     *
     * @param <TException> 測試條件可拋出的例外型態
     * @param condition 取得項目的條件測試函數
     * @return 下一筆項目
     * @throws NoSuchElementException 沒有下一筆或符合條件的項目
     * @throws TException 表示{@code condition}丟出例外
     */
    default <TException extends Exception> E next(PredicateEx<? super E, ? extends TException> condition)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        while (thiz.hasNext())
        {
            E item = thiz.next();
            if (condition.testEx(item))
            {
                return item;
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * 取得符合條件的下一筆項目。
     * <p>
     * 結束後列舉器會停在第一筆符合項目之後或是結尾。
     * </p>
     *
     * @return 下一筆項目，或null表示沒有下一筆或符合條件的項目
     */
    default E nextOrNull()
    {
        Iterator<E> thiz = (Iterator<E>) this;
        if (thiz.hasNext())
        {
            return thiz.next();
        }
        return null;
    }

    /**
     * 取得符合條件的下一筆項目。
     * <p>
     * 結束後列舉器會停在第一筆符合項目之後或是結尾。
     * </p>
     *
     * @param <TException> 測試條件可拋出的例外型態
     * @param condition 取得項目的條件測試函數
     * @return 下一筆項目，或null表示沒有下一筆或符合條件的項目
     * @throws TException 表示{@code condition}丟出例外
     */
    default <TException extends Exception> E nextOrNull(PredicateEx<? super E, ? extends TException> condition)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        while (thiz.hasNext())
        {
            E item = thiz.next();
            if (condition.testEx(item))
            {
                return item;
            }
        }
        return null;
    }

    /**
     * 取得第N筆項目。
     *
     * @param position 位置(相對於目前位置)
     * @return 第N筆項目
     * @throws NoSuchElementException 沒有第N筆項目
     */
    default E nextNth(int position)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        for (int i = 0; i < position; i++)
        {
            thiz.next();
        }
        return thiz.next();
    }

    /**
     * 取得第N筆項目。
     *
     * @param position 位置(相對於目前位置)
     * @return 第N筆項目，或null表示沒有第N筆項目
     */
    default E nextNthOrNull(int position)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        for (int i = 0; i < position; i++)
        {
            if (!thiz.hasNext())
                return null;
            thiz.next();
        }
        if (!thiz.hasNext())
            return null;
        return thiz.next();
    }

    /**
     * 取得下一個可計算出最大數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param <V> 數值型態
     * @param getValue 計算項目數值的函數
     * @return 下一個計算出最大數值的項目(跳過NULL數值)
     * @throws NoSuchElementException 沒有下一筆項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <V extends Comparable<? super V>, TException extends Exception>
            E nextOfMax(FunctionEx<? super E, ? extends V, ? extends TException> getValue)
                    throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        if (!thiz.hasNext())
            throw new NoSuchElementException();
        E maxE = thiz.next();
        V maxV = getValue.applyEx(maxE);
        while (thiz.hasNext())
        {
            E curE = thiz.next();
            V curV = getValue.applyEx(curE);
            if (maxV == null || maxV.compareTo(curV) < 0)
            {
                maxV = curV;
                maxE = curE;
            }
        }
        return maxE;
    }

    /**
     * 取得下一個可計算出最大數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 下一個計算出最大數值的項目
     * @throws NoSuchElementException 沒有下一筆項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> E nextOfMaxDouble(ToDoubleFunctionEx<? super E, ? extends TException> getValue)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        if (!thiz.hasNext())
            throw new NoSuchElementException();
        E maxE = thiz.next();
        double maxV = getValue.applyAsDoubleEx(maxE);
        while (thiz.hasNext())
        {
            E curE = thiz.next();
            double curV = getValue.applyAsDoubleEx(curE);
            if (maxV < curV)
            {
                maxV = curV;
                maxE = curE;
            }
        }
        return maxE;
    }

    /**
     * 取得下一個可計算出最大數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 下一個計算出最大數值的項目
     * @throws NoSuchElementException 沒有下一筆項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> E nextOfMaxInt(ToIntFunctionEx<? super E, ? extends TException> getValue)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        if (!thiz.hasNext())
            throw new NoSuchElementException();
        E maxE = thiz.next();
        int maxV = getValue.applyAsIntEx(maxE);
        while (thiz.hasNext())
        {
            E curE = thiz.next();
            int curV = getValue.applyAsIntEx(curE);
            if (maxV < curV)
            {
                maxV = curV;
                maxE = curE;
            }
        }
        return maxE;
    }

    /**
     * 取得下一個可計算出最大數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 下一個計算出最大數值的項目
     * @throws NoSuchElementException 沒有下一筆項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> E nextOfMaxLong(ToLongFunctionEx<? super E, ? extends TException> getValue)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        if (!thiz.hasNext())
            throw new NoSuchElementException();
        E maxE = thiz.next();
        long maxV = getValue.applyAsLongEx(maxE);
        while (thiz.hasNext())
        {
            E curE = thiz.next();
            long curV = getValue.applyAsLongEx(curE);
            if (maxV < curV)
            {
                maxV = curV;
                maxE = curE;
            }
        }
        return maxE;
    }

    /**
     * 取得下一個可計算出最小數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param <V> 數值型態
     * @param getValue 計算項目數值的函數
     * @return 下一個計算出最小數值的項目(跳過NULL數值)
     * @throws NoSuchElementException 沒有下一筆項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <V extends Comparable<? super V>, TException extends Exception> E
            nextOfMin(FunctionEx<? super E, ? extends V, ? extends TException> getValue)
                    throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        if (!thiz.hasNext())
            throw new NoSuchElementException();
        E minE = thiz.next();
        V minV = getValue.applyEx(minE);
        while (thiz.hasNext())
        {
            E curE = thiz.next();
            V curV = getValue.applyEx(curE);
            if (minV == null || minV.compareTo(curV) > 0)
            {
                minV = curV;
                minE = curE;
            }
        }
        return minE;
    }

    /**
     * 取得下一個可計算出最小數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 下一個計算出最小數值的項目
     * @throws NoSuchElementException 沒有下一筆項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> E nextOfMinDouble(ToDoubleFunctionEx<? super E, ? extends TException> getValue)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        if (!thiz.hasNext())
            throw new NoSuchElementException();
        E minE = thiz.next();
        double minV = getValue.applyAsDoubleEx(minE);
        while (thiz.hasNext())
        {
            E curE = thiz.next();
            double curV = getValue.applyAsDoubleEx(curE);
            if (minV > curV)
            {
                minV = curV;
                minE = curE;
            }
        }
        return minE;
    }

    /**
     * 取得下一個可計算出最小數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 下一個計算出最小數值的項目
     * @throws NoSuchElementException 沒有下一筆項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> E nextOfMinInt(ToIntFunctionEx<? super E, ? extends TException> getValue)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        if (!thiz.hasNext())
            throw new NoSuchElementException();
        E minE = thiz.next();
        int minV = getValue.applyAsIntEx(minE);
        while (thiz.hasNext())
        {
            E curE = thiz.next();
            int curV = getValue.applyAsIntEx(curE);
            if (minV > curV)
            {
                minV = curV;
                minE = curE;
            }
        }
        return minE;
    }

    /**
     * 取得下一個可計算出最小數值的項目。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 下一個計算出最小數值的項目
     * @throws NoSuchElementException 沒有下一筆項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> E nextOfMinLong(ToLongFunctionEx<? super E, ? extends TException> getValue)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        if (!thiz.hasNext())
            throw new NoSuchElementException();
        E minE = thiz.next();
        long minV = getValue.applyAsLongEx(minE);
        while (thiz.hasNext())
        {
            E curE = thiz.next();
            long curV = getValue.applyAsLongEx(curE);
            if (minV > curV)
            {
                minV = curV;
                minE = curE;
            }
        }
        return minE;
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
    @SuppressWarnings("unchecked")
    default E[] toArray(Class<E> type)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        ArrayList<E> list = thiz.toArrayList();
        E[] array = (E[]) Array.newInstance(type, list.size());
        return list.toArray(array);
    }

    /**
     * 用目前項目值建立{@link ArrayList}。
     *
     * @return 包含目前項目的{@link ArrayList}
     */
    default ArrayList<E> toArrayList()
    {
        Iterator<E> thiz = (Iterator<E>) this;
        ArrayList<E> coll = new ArrayList<>();
        while (thiz.hasNext())
        {
            coll.add(thiz.next());
        }
        return coll;
    }

    /**
     * 用目前項目值建立{@link ArrayList}，依照條件做過濾。
     *
     * @param <TException> 測試條件可拋出的例外型態
     * @param condition 取得項目的條件測試函數
     * @return 包含符合條件項目的{@link ArrayList}
     * @throws TException 表示{@code mapper}丟出例外
     */
    default <TException extends Exception> ArrayList<E>
            toArrayListFiltered(PredicateEx<? super E, ? extends TException> condition)
                    throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        ArrayList<E> coll = new ArrayList<>();
        while (thiz.hasNext())
        {
            E e = thiz.next();
            if (condition.testEx(e))
                coll.add(e);
        }
        return coll;
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
            toArrayListMapped(FunctionEx<? super E, ? extends R, ? extends TException> mapper)
                    throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        ArrayList<R> coll = new ArrayList<>();
        while (thiz.hasNext())
        {
            coll.add(mapper.applyEx(thiz.next()));
        }
        return coll;
    }

    /**
     * 用目前項目值建立{@link ArrayList}，依照鍵值做排序。
     *
     * @param <V> 鍵值型態
     * @param getKey 計算每個項目的鍵值
     * @return 包含目前項目的{@link ArrayList}，已排序
     */
    default <V extends Comparable<?>> ArrayList<E> toArrayListSorted(Function<? super E, ? extends V> getKey)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        ArrayList<E> sorted = thiz.toArrayList();
        sorted.sort(MemberComparators.byComparable(getKey));
        return sorted;
    }

    /**
     * 用目前項目值建立{@link ArrayList}，依照比較器做排序。
     *
     * @param comparator 項目的比較器
     * @return 包含目前項目的{@link ArrayList}，已排序
     */
    default ArrayList<E> toArrayListSorted(Comparator<? super E> comparator)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        ArrayList<E> sorted = thiz.toArrayList();
        sorted.sort(comparator);
        return sorted;
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
            HashMap<K, E> toHashMap(FunctionEx<? super E, ? extends K, ? extends KException> getKey)
                    throws KException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        HashMap<K, E> coll = new HashMap<>();
        while (thiz.hasNext())
        {
            E item = thiz.next();
            K k = getKey.applyEx(item);
            coll.put(k, item);
        }
        return coll;
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
                    FunctionEx<? super E, ? extends K, ? extends KException> getKey,
                    FunctionEx<? super E, ? extends V, ? extends VException> getValue)
                    throws KException, VException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        HashMap<K, V> coll = new HashMap<>();
        while (thiz.hasNext())
        {
            E item = thiz.next();
            K k = getKey.applyEx(item);
            V v = getValue.applyEx(item);
            coll.put(k, v);
        }
        return coll;
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
            HashMap<K, ArrayList<E>> toHashMapGrouped(FunctionEx<? super E, ? extends K, ? extends KException> getKey)
                    throws KException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        HashMap<K, ArrayList<E>> result = new HashMap<>();
        while (thiz.hasNext())
        {
            E item = thiz.next();
            K key = getKey.applyEx(item);
            ArrayList<E> list = result.get(key);
            if (list == null)
            {
                list = new ArrayList<>();
                result.put(key, list);
            }
            list.add(item);
        }
        return result;
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
                    FunctionEx<? super E, ? extends K, ? extends KException> getKey,
                    FunctionEx<? super E, ? extends V, ? extends VException> getValue)
                    throws KException, VException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        HashMap<K, ArrayList<V>> result = new HashMap<>();
        while (thiz.hasNext())
        {
            E item = thiz.next();
            K key = getKey.applyEx(item);
            ArrayList<V> list = result.get(key);
            if (list == null)
            {
                list = new ArrayList<>();
                result.put(key, list);
            }
            V value = getValue.applyEx(item);
            list.add(value);
        }
        return result;
    }

    /**
     * 用目前項目值建立{@link HashSet}。
     * <p>
     * 重複值會被重疊覆蓋，後面的優先。
     * </p>
     *
     * @return 包含目前項目的{@link HashSet}
     */
    default HashSet<E> toHashSet()
    {
        Iterator<E> thiz = (Iterator<E>) this;
        HashSet<E> coll = new HashSet<>();
        while (thiz.hasNext())
        {
            coll.add(thiz.next());
        }
        return coll;
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
            TreeMap<K, E> toTreeMap(FunctionEx<? super E, ? extends K, ? extends KException> getKey)
                    throws KException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        TreeMap<K, E> coll = new TreeMap<>();
        while (thiz.hasNext())
        {
            E item = thiz.next();
            K k = getKey.applyEx(item);
            coll.put(k, item);
        }
        return coll;
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
                    FunctionEx<? super E, ? extends K, ? extends KException> getKey,
                    FunctionEx<? super E, ? extends V, ? extends VException> getValue)
                    throws KException, VException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        TreeMap<K, V> coll = new TreeMap<>();
        while (thiz.hasNext())
        {
            E item = thiz.next();
            K k = getKey.applyEx(item);
            V v = getValue.applyEx(item);
            coll.put(k, v);
        }
        return coll;
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
            TreeMap<K, ArrayList<E>> toTreeMapGrouped(FunctionEx<? super E, ? extends K, ? extends KException> getKey)
                    throws KException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        TreeMap<K, ArrayList<E>> result = new TreeMap<>();
        while (thiz.hasNext())
        {
            E item = thiz.next();
            K key = getKey.applyEx(item);
            ArrayList<E> list = result.get(key);
            if (list == null)
            {
                list = new ArrayList<>();
                result.put(key, list);
            }
            list.add(item);
        }
        return result;
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
                    FunctionEx<? super E, ? extends K, ? extends KException> getKey,
                    FunctionEx<? super E, ? extends V, ? extends VException> getValue)
                    throws KException, VException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        TreeMap<K, ArrayList<V>> result = new TreeMap<>();
        while (thiz.hasNext())
        {
            E item = thiz.next();
            K key = getKey.applyEx(item);
            ArrayList<V> list = result.get(key);
            if (list == null)
            {
                list = new ArrayList<>();
                result.put(key, list);
            }
            V value = getValue.applyEx(item);
            list.add(value);
        }
        return result;
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
            U identity, BiFunctionEx<U, ? super E, U, TException> accumulator)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        U result = identity;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            result = accumulator.applyEx(result, e);
        }
        return result;

    }

    /**
     * 統計符合條件的項目數量。
     *
     * @param <TException> 過濾條件函數可拋出的例外型態
     * @param condition 過濾條件的函數
     * @return 符合條件的項目數量
     * @throws TException 表示{@code condition}丟出例外
     */
    default <TException extends Exception> int count(PredicateEx<? super E, ? extends TException> condition)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        int count = 0;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            if (condition.testEx(e))
                count += 1;
        }
        return count;
    }

    /**
     * 計算項目代表數值的平均。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的平均，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> Double avgDouble(ToDoubleFunctionEx<? super E, ? extends TException> getValue)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        double total = 0;
        double count = 0;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            double v = getValue.applyAsDoubleEx(e);
            total += v;
            count += 1;
        }
        if (count > 0)
        {
            return total / count;
        }
        else
        {
            return null;
        }
    }

    /**
     * 計算項目代表數值的平均。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的平均，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> Integer avgInt(ToIntFunctionEx<? super E, ? extends TException> getValue)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        double total = 0;
        double count = 0;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            int v = getValue.applyAsIntEx(e);
            total += v;
            count += 1;
        }
        if (count > 0)
        {
            return Math.round((float) (total / count));
        }
        else
        {
            return null;
        }
    }

    /**
     * 計算項目代表數值的平均。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的平均，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> Long avgLong(ToLongFunctionEx<? super E, ? extends TException> getValue)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        double total = 0;
        double count = 0;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            long v = getValue.applyAsLongEx(e);
            total += v;
            count += 1;
        }
        if (count > 0)
        {
            return Math.round(total / count);
        }
        else
        {
            return null;
        }
    }

    /**
     * 計算項目代表數值的最大值。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最大值，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> Double maxDouble(ToDoubleFunctionEx<? super E, ? extends TException> getValue)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        Double maxValue = null;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            double v = getValue.applyAsDoubleEx(e);
            if (maxValue == null || maxValue < v)
            {
                maxValue = v;
            }
        }
        return maxValue;
    }

    /**
     * 計算項目代表數值的最大值。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最大值，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> Integer maxInt(ToIntFunctionEx<? super E, ? extends TException> getValue)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        Integer maxValue = null;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            int v = getValue.applyAsIntEx(e);
            if (maxValue == null || maxValue < v)
            {
                maxValue = v;
            }
        }
        return maxValue;
    }

    /**
     * 計算項目代表數值的最大值。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最大值，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> Long maxLong(ToLongFunctionEx<? super E, ? extends TException> getValue)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        Long maxValue = null;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            long v = getValue.applyAsLongEx(e);
            if (maxValue == null || maxValue < v)
            {
                maxValue = v;
            }
        }
        return maxValue;
    }

    /**
     * 計算項目代表數值的最小值。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最小值，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> Double minDouble(ToDoubleFunctionEx<? super E, ? extends TException> getValue)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        Double minValue = null;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            double v = getValue.applyAsDoubleEx(e);
            if (minValue == null || minValue > v)
            {
                minValue = v;
            }
        }
        return minValue;
    }

    /**
     * 計算項目代表數值的最小值。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最小值，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> Integer minInt(ToIntFunctionEx<? super E, ? extends TException> getValue)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        Integer minValue = null;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            int v = getValue.applyAsIntEx(e);
            if (minValue == null || minValue > v)
            {
                minValue = v;
            }
        }
        return minValue;
    }

    /**
     * 計算項目代表數值的最小值。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最小值，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> Long minLong(ToLongFunctionEx<? super E, ? extends TException> getValue)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        Long minValue = null;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            long v = getValue.applyAsLongEx(e);
            if (minValue == null || minValue > v)
            {
                minValue = v;
            }
        }
        return minValue;
    }

    /**
     * 計算項目代表數值的總和。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的總和，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> double sumDouble(ToDoubleFunctionEx<? super E, ? extends TException> getValue)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        double sumValue = 0;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            sumValue += getValue.applyAsDoubleEx(e);
        }
        return sumValue;
    }

    /**
     * 計算項目代表數值的總和。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的總和
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> int sumInt(ToIntFunctionEx<? super E, ? extends TException> getValue)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        int sumValue = 0;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            sumValue += getValue.applyAsIntEx(e);
        }
        return sumValue;
    }

    /**
     * 計算項目代表數值的總和。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的總和
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> long sumLong(ToIntFunctionEx<? super E, ? extends TException> getValue)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        long sumValue = 0;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            sumValue += getValue.applyAsIntEx(e);
        }
        return sumValue;
    }

    /**
     * 計算項目代表數值的總和。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的總和
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Exception> long sumLong(ToLongFunctionEx<? super E, ? extends TException> getValue)
            throws TException
    {
        Iterator<E> thiz = (Iterator<E>) this;
        long sumValue = 0;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            sumValue += getValue.applyAsLongEx(e);
        }
        return sumValue;
    }
}
