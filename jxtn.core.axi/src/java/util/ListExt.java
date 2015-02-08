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

import java.util.function.Function;
import java.util.function.PredicateEx;

import jxtn.core.axi.comparators.MemberComparators;
import jxtn.core.axi.util.BinarySearchResult;

/**
 * {@link List}的延伸功能。
 *
 * @author AqD
 * @param <E> 清單項目型態
 */
public interface ListExt<E> extends CollectionExt<E>
{
    /**
     * 以{@link Collection}型態表示。
     *
     * @return 以{@link Collection}型態表示的目前物件
     */
    default Collection<E> asCollection()
    {
        List<E> thiz = (List<E>) this;
        return thiz;
    }

    /**
     * 二元搜尋，用指定的函數計算排序用鍵值。
     *
     * @param <K> 代表項目的鍵值型態
     * @param getKey 取得項目鍵值的函數，清單本身須已用該鍵值進行排序
     * @param key 要搜尋的目標鍵值
     * @return 搜尋結果
     */
    default <K extends Comparable<? super K>> BinarySearchResult binarySearch(Function<? super E, ? extends K> getKey, K key)
    {
        List<E> thiz = (List<E>) this;
        int low = 0;
        int high = thiz.size() - 1;
        while (low <= high)
        {
            int mid = (low + high) >>> 1;
            E midVal = thiz.get(mid);
            K midKey = getKey.apply(midVal);
            Comparable<? super K> midKeyCmp = midKey;
            int cmp = midKeyCmp.compareTo(key);
            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return new BinarySearchResult(true, mid);
        }
        return new BinarySearchResult(false, low);
    }

    /**
     * 依照鍵值排序目前的清單。
     *
     * @param <V> 排序用的鍵值型態
     * @param getKey 用作排序的鍵值計算函數
     */
    default <V extends Comparable<? super V>> void sort(Function<? super E, V> getKey)
    {
        List<E> thiz = (List<E>) this;
        thiz.sort(MemberComparators.byComparable(getKey));
    }

    //////////////////////////////////////////////////////////////////////////
    // 泛型方法
    //

    /**
     * 泛型版本的{@link List#indexOf}。
     *
     * @param e 要檢查索引的項目
     * @return 第一個值等於{@code e}的項目索引，或是-1表示找不到
     */
    @SuppressWarnings("deprecation")
    default int indexOf2(E e)
    {
        List<E> thiz = (List<E>) this;
        return thiz.indexOf(e);
    }

    /**
     * 泛型版本的{@link List#lastIndexOf}。
     *
     * @param e 要檢查索引的項目
     * @return 最後一個值等於{@code e}的項目索引，或是-1表示找不到
     */
    @SuppressWarnings("deprecation")
    default int lastIndexOf2(E e)
    {
        List<E> thiz = (List<E>) this;
        return thiz.lastIndexOf(e);
    }

    //////////////////////////////////////////////////////////////////////////
    // 項目挑選
    //

    /**
     * 取得第一筆項目。
     *
     * @return 第一筆項目
     * @throws NoSuchElementException 沒有項目
     */
    @Override
    default E first()
    {
        List<E> thiz = (List<E>) this;
        if (thiz.size() == 0)
            throw new NoSuchElementException();
        else
            return thiz.get(0);
    }

    /**
     * 取得第一筆項目。
     *
     * @return 第一筆項目，或null表示沒有項目
     */
    @Override
    default E firstOrNull()
    {
        List<E> thiz = (List<E>) this;
        if (thiz.size() == 0)
            return null;
        else
            return thiz.get(0);
    }

    /**
     * 取得符合指定條件的第一個項目索引。
     *
     * @param <TException> 測試條件可拋出的例外型態
     * @param condition 測試條件的函數
     * @return 符合{@code filter}的第一個項目索引，或-1表示找不到
     * @throws TException 表示{@code condition}丟出例外
     */
    default <TException extends Exception> int firstIndexOf(PredicateEx<? super E, TException> condition)
            throws TException
    {
        List<E> thiz = (List<E>) this;
        for (int i = 0; i < thiz.size(); i++)
        {
            E e = thiz.get(i);
            if (condition.testEx(e))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * 取得第N筆項目。
     *
     * @param position 位置
     * @return 第N筆項目
     * @throws NoSuchElementException 沒有第N筆項目
     */
    @Override
    default E getNth(int position)
    {
        List<E> thiz = (List<E>) this;
        if (position >= thiz.size())
            throw new NoSuchElementException(Integer.toString(position));
        return thiz.get(position);
    }

    /**
     * 取得第N筆項目或null。
     *
     * @param position 位置
     * @return 第N筆項目，或null表示沒有第N筆項目
     */
    @Override
    default E getNthOrNull(int position)
    {
        List<E> thiz = (List<E>) this;
        if (position >= thiz.size())
            return null;
        return thiz.get(position);
    }

    /**
     * 取得最後一筆項目。
     *
     * @return 最後一筆項目
     * @throws NoSuchElementException 沒有項目
     */
    @Override
    default E last()
    {
        List<E> thiz = (List<E>) this;
        if (thiz.size() == 0)
            throw new NoSuchElementException();
        else
            return thiz.get(thiz.size() - 1);
    }

    /**
     * 取得符合條件的最後一筆項目。
     *
     * @param <TException> 測試條件可拋出的例外型態
     * @param condition 取得項目的條件測試函數
     * @return 最後一筆項目
     * @throws NoSuchElementException 沒有最後一筆或符合條件的項目
     * @throws TException 表示{@code condition}丟出例外
     */
    @Override
    default <TException extends Exception> E last(PredicateEx<? super E, ? extends TException> condition)
            throws TException
    {
        List<E> thiz = (List<E>) this;
        for (int i = thiz.size() - 1; i >= 0; i--)
        {
            E item = thiz.get(i);
            if (condition.testEx(item))
                return item;
        }
        throw new NoSuchElementException();
    }

    /**
     * 取得最後一筆項目或null。
     *
     * @return 最後一筆項目，或null表示沒有項目
     */
    @Override
    default E lastOrNull()
    {
        List<E> thiz = (List<E>) this;
        if (thiz.size() == 0)
            return null;
        else
            return thiz.get(thiz.size() - 1);
    }

    /**
     * 取得符合條件的最後一筆項目或null。
     *
     * @param <TException> 測試條件可拋出的例外型態
     * @param condition 取得項目的條件測試函數
     * @return 最後一筆項目，或null表示沒有符合條件的項目
     * @throws TException 表示{@code condition}丟出例外
     */
    @Override
    default <TException extends Exception> E lastOrNull(PredicateEx<? super E, ? extends TException> condition)
            throws TException
    {
        List<E> thiz = (List<E>) this;
        for (int i = thiz.size() - 1; i >= 0; i--)
        {
            E item = thiz.get(i);
            if (condition.testEx(item))
                return item;
        }
        return null;
    }
}
