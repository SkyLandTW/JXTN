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
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import jxtn.core.axi.MemberComparators;
import jxtn.core.axi.collections.ConcatedIterator;
import jxtn.core.axi.collections.ExpandedIterator;
import jxtn.core.axi.collections.FilteredIterator;
import jxtn.core.axi.collections.LinkLineIterator;
import jxtn.core.axi.collections.LinkTreeIterator;
import jxtn.core.axi.collections.MappedIterator;

/**
 * {@link Iterator}的延伸功能
 * <p>
 * 添加延伸時應同步更新{@link java.lang.IterableExt}
 * </p>
 *
 * @author AqD
 * @param <E> 列舉項目型態
 */
public interface IteratorExt<E>
{
    /**
     * 結合多個列舉器
     *
     * @param <T> 列舉項目型態
     * @param iterators 要結合的列舉器集合
     * @return 結合的列舉器
     */
    @SafeVarargs
    public static <T> Iterator<T> concatAll(Iterator<T>... iterators)
    {
        return new ConcatedIterator<>(Arrays.asList(iterators).iterator());
    }

    /**
     * 結合多個列舉器
     *
     * @param <T> 列舉項目型態
     * @param iteratorIterable 要結合的列舉器的列舉
     * @return 結合的列舉器
     */
    public static <T> Iterator<T> concatAll(Iterable<Iterator<T>> iteratorIterable)
    {
        return new ConcatedIterator<>(iteratorIterable.iterator());
    }

    /**
     * 結合多個列舉器
     *
     * @param <T> 列舉項目型態
     * @param iteratorIterator 要結合的列舉器的列舉器
     * @return 結合的列舉器
     */
    public static <T> Iterator<T> concatAll(Iterator<Iterator<T>> iteratorIterator)
    {
        return new ConcatedIterator<>(iteratorIterator);
    }

    /**
     * 建立線性結構的串接列舉器
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
     * 建立樹狀結構的串接列舉器
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

    /************************************************************************
     * 條件測試
     ************************************************************************/

    /**
     * 檢查是否所有剩餘項目皆符合指定條件
     * <p>
     * 結束後列舉器會停在結尾
     * </p>
     *
     * @param filter 條件測試的函數
     * @return true表示符合，或沒有項目可測試false表示任一項目不符合
     */
    default boolean all(Predicate<? super E> filter)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        while (thiz.hasNext())
        {
            E item = thiz.next();
            if (!filter.test(item))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 檢查是否有任一項目符合指定條件
     * <p>
     * 結束後列舉器會停在第一筆符合項目之後或是結尾
     * </p>
     *
     * @param filter 條件測試的函數
     * @return true表示符合false表示所有項目皆不符合，或沒有項目可測試
     */
    default boolean any(Predicate<? super E> filter)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        while (thiz.hasNext())
        {
            E item = thiz.next();
            if (filter.test(item))
            {
                return true;
            }
        }
        return false;
    }

    /************************************************************************
     * 列舉轉換
     ************************************************************************/

    /**
     * 將目前列舉作為指定項目型態的列舉傳回
     *
     * @param <V> 傳回項目型態
     * @return 指定項目型態的列舉(物件仍為目前列舉)
     */
    @SuppressWarnings("unchecked")
    default <V> Iterator<V> as()
    {
        return (Iterator<V>) this;
    }

    /**
     * 依照展開函數建立展開列舉
     *
     * @param <R> 展開項目型態
     * @param expander 展開項目的函數
     * @return 展開列舉，依賴原有的列舉
     */
    default <R> Iterator<R> expand(Function<? super E, Iterator<R>> expander)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        return new ExpandedIterator<>(thiz, expander);
    }

    /**
     * 依照條件建立過濾列舉
     *
     * @param filter 過濾條件
     * @return 過濾列舉，依賴原有的列舉
     */
    default Iterator<E> filter(Predicate<? super E> filter)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        return new FilteredIterator<>(thiz, filter);
    }

    /**
     * 依照對照函數建立對照列舉
     *
     * @param <R> 對照項目型態
     * @param mapper 對照項目的函數
     * @return 對照列舉，依賴原有的列舉
     */
    default <R> Iterator<R> map(Function<? super E, R> mapper)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        return new MappedIterator<>(thiz, mapper);
    }

    /**
     * 取得指定型態的項目列舉
     *
     * @param <R> 要取得的項目型態
     * @param type 要取得項目的型態
     * @return 包含{@code type}型態項目的列舉
     */
    @SuppressWarnings("unchecked")
    default <R extends E> Iterator<R> ofType(Class<R> type)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        return thiz.filter(type::isInstance).map(e -> (R) e);
    }

    /************************************************************************
     * 項目挑選
     ************************************************************************/

    /**
     * 取得符合條件的下一筆項目
     * <p>
     * 結束後列舉器會停在第一筆符合項目之後或是結尾
     * </p>
     *
     * @param filter 取得項目的條件測試函數
     * @return 下一筆項目
     * @throws NoSuchElementException 沒有下一筆或符合條件的項目
     */
    default E next(Predicate<E> filter)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        while (thiz.hasNext())
        {
            E item = thiz.next();
            if (filter.test(item))
            {
                return item;
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * 取得符合條件的下一筆項目
     * <p>
     * 結束後列舉器會停在第一筆符合項目之後或是結尾
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
     * 取得符合條件的下一筆項目
     * <p>
     * 結束後列舉器會停在第一筆符合項目之後或是結尾
     * </p>
     *
     * @param filter 取得項目的條件測試函數
     * @return 下一筆項目，或null表示沒有下一筆或符合條件的項目
     */
    default E nextOrNull(Predicate<E> filter)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        while (thiz.hasNext())
        {
            E item = thiz.next();
            if (filter.test(item))
            {
                return item;
            }
        }
        return null;
    }

    /**
     * 取得下一個可計算出最大數值的項目
     *
     * @param <V> 數值型態
     * @param getValue 計算項目數值的函數
     * @return 下一個計算出最大數值的項目(跳過NULL數值)
     * @throws NoSuchElementException 沒有下一筆項目
     */
    default <V extends Comparable<V>> E nextOfMax(Function<? super E, V> getValue)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        if (!thiz.hasNext())
            throw new NoSuchElementException();
        E maxE = thiz.next();
        V maxV = getValue.apply(maxE);
        while (thiz.hasNext())
        {
            E curE = thiz.next();
            V curV = getValue.apply(curE);
            if (maxV == null || maxV.compareTo(curV) < 0)
            {
                maxV = curV;
                maxE = curE;
            }
        }
        return maxE;
    }

    /**
     * 取得下一個可計算出最大數值的項目
     *
     * @param getValue 計算項目數值的函數
     * @return 下一個計算出最大數值的項目
     * @throws NoSuchElementException 沒有下一筆項目
     */
    default E nextOfMaxDouble(ToDoubleFunction<E> getValue)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        if (!thiz.hasNext())
            throw new NoSuchElementException();
        E maxE = thiz.next();
        double maxV = getValue.applyAsDouble(maxE);
        while (thiz.hasNext())
        {
            E curE = thiz.next();
            double curV = getValue.applyAsDouble(curE);
            if (maxV < curV)
            {
                maxV = curV;
                maxE = curE;
            }
        }
        return maxE;
    }

    /**
     * 取得下一個可計算出最大數值的項目
     *
     * @param getValue 計算項目數值的函數
     * @return 下一個計算出最大數值的項目
     * @throws NoSuchElementException 沒有下一筆項目
     */
    default E nextOfMaxInt(ToIntFunction<E> getValue)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        if (!thiz.hasNext())
            throw new NoSuchElementException();
        E maxE = thiz.next();
        int maxV = getValue.applyAsInt(maxE);
        while (thiz.hasNext())
        {
            E curE = thiz.next();
            int curV = getValue.applyAsInt(curE);
            if (maxV < curV)
            {
                maxV = curV;
                maxE = curE;
            }
        }
        return maxE;
    }

    /**
     * 取得下一個可計算出最大數值的項目
     *
     * @param getValue 計算項目數值的函數
     * @return 下一個計算出最大數值的項目
     * @throws NoSuchElementException 沒有下一筆項目
     */
    default E nextOfMaxLong(ToLongFunction<E> getValue)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        if (!thiz.hasNext())
            throw new NoSuchElementException();
        E maxE = thiz.next();
        long maxV = getValue.applyAsLong(maxE);
        while (thiz.hasNext())
        {
            E curE = thiz.next();
            long curV = getValue.applyAsLong(curE);
            if (maxV < curV)
            {
                maxV = curV;
                maxE = curE;
            }
        }
        return maxE;
    }

    /**
     * 取得下一個可計算出最小數值的項目
     *
     * @param <V> 數值型態
     * @param getValue 計算項目數值的函數
     * @return 下一個計算出最小數值的項目(跳過NULL數值)
     * @throws NoSuchElementException 沒有下一筆項目
     */
    default <V extends Comparable<V>> E nextOfMin(Function<? super E, V> getValue)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        if (!thiz.hasNext())
            throw new NoSuchElementException();
        E minE = thiz.next();
        V minV = getValue.apply(minE);
        while (thiz.hasNext())
        {
            E curE = thiz.next();
            V curV = getValue.apply(curE);
            if (minV == null || minV.compareTo(curV) > 0)
            {
                minV = curV;
                minE = curE;
            }
        }
        return minE;
    }

    /**
     * 取得下一個可計算出最小數值的項目
     *
     * @param getValue 計算項目數值的函數
     * @return 下一個計算出最小數值的項目
     * @throws NoSuchElementException 沒有下一筆項目
     */
    default E nextOfMinDouble(ToDoubleFunction<E> getValue)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        if (!thiz.hasNext())
            throw new NoSuchElementException();
        E minE = thiz.next();
        double minV = getValue.applyAsDouble(minE);
        while (thiz.hasNext())
        {
            E curE = thiz.next();
            double curV = getValue.applyAsDouble(curE);
            if (minV > curV)
            {
                minV = curV;
                minE = curE;
            }
        }
        return minE;
    }

    /**
     * 取得下一個可計算出最小數值的項目
     *
     * @param getValue 計算項目數值的函數
     * @return 下一個計算出最小數值的項目
     * @throws NoSuchElementException 沒有下一筆項目
     */
    default E nextOfMinInt(ToIntFunction<E> getValue)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        if (!thiz.hasNext())
            throw new NoSuchElementException();
        E minE = thiz.next();
        int minV = getValue.applyAsInt(minE);
        while (thiz.hasNext())
        {
            E curE = thiz.next();
            int curV = getValue.applyAsInt(curE);
            if (minV > curV)
            {
                minV = curV;
                minE = curE;
            }
        }
        return minE;
    }

    /**
     * 取得下一個可計算出最小數值的項目
     *
     * @param getValue 計算項目數值的函數
     * @return 下一個計算出最小數值的項目
     * @throws NoSuchElementException 沒有下一筆項目
     */
    default E nextOfMinLong(ToLongFunction<E> getValue)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        if (!thiz.hasNext())
            throw new NoSuchElementException();
        E minE = thiz.next();
        long minV = getValue.applyAsLong(minE);
        while (thiz.hasNext())
        {
            E curE = thiz.next();
            long curV = getValue.applyAsLong(curE);
            if (minV > curV)
            {
                minV = curV;
                minE = curE;
            }
        }
        return minE;
    }

    /************************************************************************
     * 項目統整
     ************************************************************************/

    /**
     * 建立陣列
     *
     * @param type 陣列項目型態
     * @return 陣列
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
     * 建立{@link ArrayList}
     *
     * @return {@link ArrayList}
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
     * 建立{@link ArrayList}，依照鍵值做排序
     *
     * @param <V> 鍵值型態
     * @param getKey 計算每個項目的鍵值
     * @return {@link ArrayList}，已排序
     */
    @SuppressWarnings({ "rawtypes" })
    default <V extends Comparable> ArrayList<E> toArrayListSorted(Function<E, V> getKey)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        ArrayList<E> sorted = thiz.toArrayList();
        sorted.sort(MemberComparators.byComparable(getKey));
        return sorted;
    }

    /**
     * 建立{@link ArrayList}，依照比較器做排序
     *
     * @param comparator 項目的比較器
     * @return {@link ArrayList}，已排序
     */
    default ArrayList<E> toArrayListSorted(Comparator<E> comparator)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        ArrayList<E> sorted = thiz.toArrayList();
        sorted.sort(comparator);
        return sorted;
    }

    /**
     * 建立{@link HashMap}
     *
     * @param <K> {@link HashMap}鍵值型態
     * @param getKey 計算項目於新{@link HashMap}內的鍵值
     * @return {@link HashMap}
     */
    default <K> HashMap<K, E> toHashMap(Function<? super E, K> getKey)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        HashMap<K, E> coll = new HashMap<>();
        while (thiz.hasNext())
        {
            E item = thiz.next();
            K k = getKey.apply(item);
            coll.put(k, item);
        }
        return coll;
    }

    /**
     * 建立{@link HashMap}
     *
     * @param <K> {@link HashMap}鍵值型態
     * @param <V> {@link HashMap}項目值型態
     * @param getKey 計算項目於新{@link HashMap}內的鍵值
     * @param getValue 計算項目於新{@link HashMap}內的項目值
     * @return {@link HashMap}
     */
    default <K, V> HashMap<K, V> toHashMap(Function<? super E, K> getKey, Function<? super E, V> getValue)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        HashMap<K, V> coll = new HashMap<>();
        while (thiz.hasNext())
        {
            E item = thiz.next();
            K k = getKey.apply(item);
            V v = getValue.apply(item);
            coll.put(k, v);
        }
        return coll;
    }

    /**
     * 建立{@link HashMap}，依照鍵值做分群
     *
     * @param <K> 分群鍵值型態
     * @param getKey 計算每個項目的鍵值
     * @return {@link HashMap}，已分群組
     */
    default <K> HashMap<K, ArrayList<E>> toHashMapGrouped(Function<? super E, K> getKey)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        HashMap<K, ArrayList<E>> result = new HashMap<>();
        while (thiz.hasNext())
        {
            E item = thiz.next();
            K key = getKey.apply(item);
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
     * 建立{@link HashSet}
     * <p>
     * 重複值會被重疊覆蓋
     * </p>
     *
     * @return {@link HashSet}
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

    /************************************************************************
     * 數學統計
     ************************************************************************/

    /**
     * 計算項目代表數值的平均
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的平均
     */
    default double avgDouble(ToDoubleFunction<? super E> getValue)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        double total = 0;
        double count = 0;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            double v = getValue.applyAsDouble(e);
            total += v;
            count += 1;
        }
        if (count > 0)
        {
            return total / count;
        }
        else
        {
            return 0;
        }
    }

    /**
     * 計算項目代表數值的平均
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的平均
     */
    default int avgInt(ToIntFunction<? super E> getValue)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        double total = 0;
        double count = 0;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            int v = getValue.applyAsInt(e);
            total += v;
            count += 1;
        }
        if (count > 0)
        {
            return Math.round((float) (total / count));
        }
        else
        {
            return 0;
        }
    }

    /**
     * 計算項目代表數值的平均
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的平均
     */
    default long avgLong(ToLongFunction<? super E> getValue)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        double total = 0;
        double count = 0;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            long v = getValue.applyAsLong(e);
            total += v;
            count += 1;
        }
        if (count > 0)
        {
            return Math.round(total / count);
        }
        else
        {
            return 0;
        }
    }

    /**
     * 計算項目代表數值的最大值
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最大值
     */
    default double maxDouble(ToDoubleFunction<E> getValue)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        Double maxValue = null;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            double v = getValue.applyAsDouble(e);
            if (maxValue == null || maxValue < v)
            {
                maxValue = v;
            }
        }
        return maxValue != null ? maxValue : 0;
    }

    /**
     * 計算項目代表數值的最大值
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最大值
     */
    default int maxInt(ToIntFunction<E> getValue)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        Integer maxValue = null;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            int v = getValue.applyAsInt(e);
            if (maxValue == null || maxValue < v)
            {
                maxValue = v;
            }
        }
        return maxValue != null ? maxValue : 0;
    }

    /**
     * 計算項目代表數值的最大值
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最大值
     */
    default long maxLong(ToLongFunction<E> getValue)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        Long maxValue = null;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            long v = getValue.applyAsLong(e);
            if (maxValue == null || maxValue < v)
            {
                maxValue = v;
            }
        }
        return maxValue != null ? maxValue : 0;
    }

    /**
     * 計算項目代表數值的最小值
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最小值
     */
    default double minDouble(ToDoubleFunction<E> getValue)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        Double minValue = null;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            double v = getValue.applyAsDouble(e);
            if (minValue == null || minValue > v)
            {
                minValue = v;
            }
        }
        return minValue != null ? minValue : 0;
    }

    /**
     * 計算項目代表數值的最小值
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最小值
     */
    default int minInt(ToIntFunction<E> getValue)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        Integer minValue = null;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            int v = getValue.applyAsInt(e);
            if (minValue == null || minValue > v)
            {
                minValue = v;
            }
        }
        return minValue != null ? minValue : 0;
    }

    /**
     * 計算項目代表數值的最小值
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最小值
     */
    default long minLong(ToLongFunction<E> getValue)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        Long minValue = null;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            long v = getValue.applyAsLong(e);
            if (minValue == null || minValue > v)
            {
                minValue = v;
            }
        }
        return minValue != null ? minValue : 0;
    }

    /**
     * 計算項目代表數值的總和
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的總和
     */
    default double sumDouble(ToDoubleFunction<E> getValue)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        double sumValue = 0;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            sumValue += getValue.applyAsDouble(e);
        }
        return sumValue;
    }

    /**
     * 計算項目代表數值的總和
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的總和
     */
    default int sumInt(ToIntFunction<E> getValue)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        int sumValue = 0;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            sumValue += getValue.applyAsInt(e);
        }
        return sumValue;
    }

    /**
     * 計算項目代表數值的總和
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的總和
     */
    default long sumLong(ToIntFunction<E> getValue)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        long sumValue = 0;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            sumValue += getValue.applyAsInt(e);
        }
        return sumValue;
    }

    /**
     * 計算項目代表數值的總和
     *
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的總和
     */
    default long sumLong(ToLongFunction<E> getValue)
    {
        Iterator<E> thiz = (Iterator<E>) this;
        long sumValue = 0;
        while (thiz.hasNext())
        {
            E e = thiz.next();
            sumValue += getValue.applyAsLong(e);
        }
        return sumValue;
    }
}
