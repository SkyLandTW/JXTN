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
 * Extension to {@link Iterable}
 * <p>
 * Extension methods here are synchronized with those in {@link java.util.IteratorExt}
 * </p>
 *
 * @author AqD
 * @param <T> The {@link Iterable} element type
 */
public interface IterableExt<T> {

    /**
     * Combine multiple {@link Iterable} into a single {@link Iterable}
     *
     * @param <T> The {@link Iterable} element type
     * @param iterables Iterables of the same element type to combine
     * @return The combined {@link Iterable} which can be used to iterate through all items from {@code iterables}
     */
    @SafeVarargs
    static <T> Iterable<T> concatAll(Iterable<? extends T>... iterables) {
        return () -> new ConcatedIterator<>(Arrays.asList(iterables).iterator().mapRemaining(Iterable::iterator));
    }

    /**
     * Combine multiple {@link Iterable} into a single {@link Iterable} which can then iterate through all items
     *
     * @param <T> The {@link Iterable} element type
     * @param iterableIterable Iterable of iterables of the same element type to combine
     * @return The combined {@link Iterable} which can be used to iterate through all items from {@code iterables}
     */
    static <T> Iterable<T> concatAll(Iterable<Iterable<? extends T>> iterableIterable) {
        return () -> new ConcatedIterator<>(iterableIterable.iterator().mapRemaining(Iterable::iterator));
    }

    /**
     * Combine multiple {@link Iterable} into a single {@link Iterable} which can then iterate through all items
     *
     * @param <T> The {@link Iterable} element type
     * @param iterableIterator Iterator of iterables of the same element type to combine
     * @return The combined {@link Iterable} which can be used to iterate through all items from {@code iterables}
     */
    static <T> Iterable<T> concatAll(Iterator<Iterable<? extends T>> iterableIterator) {
        return () -> new ConcatedIterator<>(iterableIterator.mapRemaining(Iterable::iterator));
    }

    /**
     * Link a line structure into an {@link Iterable} of elements
     *
     * @param <T> {@link Iterable} element type
     * @param item The initial item (head)
     * @param getNext A function to get the next item from the given item or return null at the end
     * @return The {@link Iterable} of elements representing this line structure, starting from {@code item}
     */
    static <T> Iterable<T> linkLine(T item, Function<? super T, ? extends T> getNext) {
        return () -> new LinkLineIterator<>(item, getNext);
    }

    /**
     * Link a tree structure into an {@link Iterable} of elements (depth-first search)
     *
     * @param <T> The {@link Iterable} element type
     * @param item The initial item (root node)
     * @param getChildren The function to get child nodes of the given node or return null at the end
     * @return The {@link Iterable} of elements representing this tree structure, starting from {@code item} as root
     *         node (depth-first search)
     */
    static <T> Iterable<T> linkTree(T item, Function<? super T, ? extends Iterable<? extends T>> getChildren) {
        return () -> new LinkTreeIterator<>(item, t -> {
            Iterable<? extends T> children = getChildren.apply(t);
            return children == null ? null : children.iterator();
        });
    }

    /**
     * Performs the given action for each element of this {@link Iterable} while allowing {@code TException} to happen
     *
     * @param <TException> The type of exception allowed to be thrown from {@code action}
     * @param action The action to be performed for each element
     * @throws TException The first exception from {@code action}
     */
    default <TException extends Throwable> void forEachEx(ConsumerEx<? super T, ? extends TException> action)
            throws TException {
        Objects.requireNonNull(action);
        Iterable<T> thiz = (Iterable<T>) this;
        for (T item : thiz) {
            action.acceptEx(item);
        }
    }

    //////////////////////////////////////////////////////////////////////////
    // 條件測試
    //
    /**
     * Check whether all elements of this {@link Iterable} match the given condition
     *
     * @param <TException> The type of exception allowed to be thrown from {@code condition}
     * @param condition The condition to test elements in this {@link Iterable}
     * @return true if all elements match {@code condition} or the {@link Iterable} contains no element
     * @throws TException The first exception from {@code condition}
     */
    default <TException extends Throwable> boolean all(PredicateEx<? super T, ? extends TException> condition)
            throws TException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().allRemaining(condition);
    }

    /**
     * Check if any of the elements in this {@link Iterable} matches the given condition
     *
     * @param <TException> The type of exception allowed to be thrown from {@code condition}
     * @param condition The condition to test elements in this {@link Iterable}
     * @return true if at least one element matches {@code condition}
     * @throws TException The first exception from {@code condition}
     */
    default <TException extends Throwable> boolean any(PredicateEx<? super T, ? extends TException> condition)
            throws TException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().anyRemaining(condition);
    }

    //////////////////////////////////////////////////////////////////////////
    // 列舉轉換
    //
    /**
     * Return the current {@link Iterable} as an {@link Iterable} of a different element type {@code V} (unchecked
     * conversion)
     *
     * @param <V> The target element type
     * @return The current {@link Iterable} as an {@link Iterable} of {@code V} (unchecked conversion)
     */
    @SuppressWarnings("unchecked")
    default <V> Iterable<V> as() {
        return (Iterable<V>) this;
    }

    /**
     * Construct a new {@link Iterable} from the current one with new items appended to the end
     *
     * @param tailItems Items to be appended to the end
     * @return a new {@link Iterable} from the current one with {@code tailItems} appended to the end
     */
    @SuppressWarnings("unchecked")
    default Iterable<T> append(T... tailItems) {
        List<Iterable<? extends T>> list = Arrays.asList(
                (Iterable<T>) this,
                Arrays.asList(tailItems));
        return () -> new ConcatedIterator<>(list.iterator().mapRemaining(Iterable::iterator));
    }

    /**
     * Construct a new {@link Iterable} from the current one with new items appended to the beginning
     *
     * @param headItems Items to be inserted to the beginning
     * @return a new {@link Iterable} from the current one with {@code headItems} inserted to the beginning
     */
    @SuppressWarnings("unchecked")
    default Iterable<T> prepend(T... headItems) {
        List<Iterable<? extends T>> list = Arrays.asList(
                Arrays.asList(headItems),
                (Iterable<T>) this);
        return () -> new ConcatedIterator<>(list.iterator().mapRemaining(Iterable::iterator));
    }

    /**
     * Construct a new {@link Iterable} from the current one with given {@link Iterable} of items appended to the end
     *
     * @param iterables {@link Iterable} of items to be appended to the end
     * @return A new {@link Iterable} from the current one with given {@link Iterable} of items appended to the end
     */
    @SuppressWarnings("unchecked")
    default Iterable<T> concat(Iterable<? extends T>... iterables) {
        List<Iterable<? extends T>> list = new ArrayList<>(iterables.length + 1);
        list.add((Iterable<T>) this);
        for (Iterable<? extends T> other : iterables) {
            list.add(other);
        }
        return () -> new ConcatedIterator<>(list.iterator().mapRemaining(Iterable::iterator));
    }

    /**
     * Construct a new {@link Iterable} with each of element in this {@link Iterable} expanded into one or multiple
     * elements of another type by a specified expansion function
     *
     * @param <R> The element type after expansion
     * @param expander A function to expand each element into multiple elements of {@code R}
     * @return A new {@link Iterable} with each of element in this {@link Iterable} expanded by {@code expander}
     */
    default <R> Iterable<R> expand(Function<? super T, ? extends Iterable<? extends R>> expander) {
        Iterable<T> thiz = (Iterable<T>) this;
        return () -> new ExpandedIterator<>(thiz.iterator(), t -> expander.apply(t).iterator());
    }

    /**
     * 依照條件建立過濾列舉（保留符合條件之項目）。
     *
     * @param condition 過濾條件
     * @return 過濾列舉，依賴原有的列舉
     */
    default Iterable<T> filter(Predicate<? super T> condition) {
        Iterable<T> thiz = (Iterable<T>) this;
        return () -> new FilteredIterator<>(thiz.iterator(), condition);
    }

    /**
     * 依照條件建立剔除開頭的過濾列舉（保留符合條件之後項目，包含該項）。
     *
     * @param condition 過濾條件
     * @return 過濾列舉，依賴原有的列舉
     */
    default Iterable<T> after(Predicate<? super T> condition) {
        Iterable<T> thiz = (Iterable<T>) this;
        return () -> new AfterConditionIterator<>(thiz.iterator(), condition);
    }

    /**
     * 依照條件建立剔除結尾的過濾列舉（保留符合條件之前項目，不含該項）。
     *
     * @param condition 過濾條件
     * @return 過濾列舉，依賴原有的列舉
     */
    default Iterable<T> before(Predicate<? super T> condition) {
        Iterable<T> thiz = (Iterable<T>) this;
        return () -> new BeforeConditionIterator<>(thiz.iterator(), condition);
    }

    /**
     * 建立加上索引的列舉。
     *
     * @return 加上索引的列舉，依賴原有的列舉
     */
    default Iterable<IndexedItem<T>> indexed() {
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
    default <R> Iterable<R> map(Function<? super T, ? extends R> mapper) {
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
    default <R> Iterable<R> ofType(Class<? extends R> type) {
        return this.filter(type::isInstance).map(t -> (R) t);
    }

    /**
     * 建立跳過指定項目數量的列舉。
     *
     * @param count 要跳過的項目數量
     * @return 跳過指定數量的列舉，依賴原有的列舉
     */
    default Iterable<T> skip(int count) {
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
     * @throws NoSuchElementException 沒有任何項目
     */
    default T first() {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().next();
    }

    /**
     * 取得符合條件的第一筆項目。
     *
     * @param <TException> 測試條件可拋出的例外型態
     * @param condition 取得項目的條件測試函數
     * @return 符合條件的第一筆項目
     * @throws NoSuchElementException 沒有任何符合條件的項目
     * @throws TException 表示{@code condition}丟出例外
     */
    default <TException extends Throwable> T first(PredicateEx<? super T, ? extends TException> condition)
            throws TException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().next(condition);
    }

    /**
     * 取得第一筆項目或null。
     *
     * @return 第一筆項目，或null表示沒有項目
     */
    default T firstOrNull() {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().nextOrNull();
    }

    /**
     * 取得符合條件的第一筆項目或null。
     *
     * @param <TException> 測試條件可拋出的例外型態
     * @param condition 取得項目的條件測試函數
     * @return 符合條件的第一筆項目，或null表示沒有項目
     * @throws TException 表示{@code condition}丟出例外
     */
    default <TException extends Throwable> T firstOrNull(PredicateEx<? super T, ? extends TException> condition)
            throws TException {
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
    default <V extends Comparable<? super V>, TException extends Throwable> T firstOfMax(
            FunctionEx<? super T, ? extends V, ? extends TException> getValue)
                    throws TException {
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
    default <V extends Comparable<? super V>, TException extends Throwable> T firstOfMaxOrNull(
            FunctionEx<? super T, ? extends V, ? extends TException> getValue)
                    throws TException {
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
    default <TException extends Throwable> T firstOfMaxDouble(
            ToDoubleFunctionEx<? super T, ? extends TException> getValue)
                    throws TException {
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
    default <TException extends Throwable> T firstOfMaxDoubleOrNull(
            ToDoubleFunctionEx<? super T, ? extends TException> getValue)
                    throws TException {
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
    default <TException extends Throwable> T firstOfMaxInt(ToIntFunctionEx<? super T, ? extends TException> getValue)
            throws TException {
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
    default <TException extends Throwable> T firstOfMaxIntOrNull(
            ToIntFunctionEx<? super T, ? extends TException> getValue)
                    throws TException {
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
    default <TException extends Throwable> T firstOfMaxLong(ToLongFunctionEx<? super T, ? extends TException> getValue)
            throws TException {
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
    default <TException extends Throwable> T firstOfMaxLongOrNull(
            ToLongFunctionEx<? super T, ? extends TException> getValue)
                    throws TException {
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
    default <V extends Comparable<? super V>, TException extends Throwable> T firstOfMin(
            FunctionEx<? super T, ? extends V, ? extends TException> getValue)
                    throws TException {
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
    default <V extends Comparable<? super V>, TException extends Throwable> T firstOfMinOrNull(
            FunctionEx<? super T, ? extends V, ? extends TException> getValue)
                    throws TException {
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
    default <TException extends Throwable> T firstOfMinDouble(
            ToDoubleFunctionEx<? super T, ? extends TException> getValue)
                    throws TException {
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
    default <TException extends Throwable> T firstOfMinDoubleOrNull(
            ToDoubleFunctionEx<? super T, ? extends TException> getValue)
                    throws TException {
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
    default <TException extends Throwable> T firstOfMinInt(ToIntFunctionEx<? super T, ? extends TException> getValue)
            throws TException {
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
    default <TException extends Throwable> T firstOfMinIntOrNull(
            ToIntFunctionEx<? super T, ? extends TException> getValue)
                    throws TException {
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
    default <TException extends Throwable> T firstOfMinLong(ToLongFunctionEx<? super T, ? extends TException> getValue)
            throws TException {
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
    default <TException extends Throwable> T firstOfMinLongOrNull(
            ToLongFunctionEx<? super T, ? extends TException> getValue)
                    throws TException {
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
    default T getNth(int position) {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().nextNth(position);
    }

    /**
     * 取得第N筆項目。
     *
     * @param position 位置
     * @return 第N筆項目，或null表示沒有第N筆項目
     */
    default T getNthOrNull(int position) {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().nextNthOrNull(position);
    }

    /**
     * 取得最後一筆項目。
     *
     * @return 最後一筆項目
     * @throws NoSuchElementException 沒有任何項目
     */
    default T last() {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().lastOfRemaining();
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
    default <TException extends Throwable> T last(PredicateEx<? super T, ? extends TException> condition)
            throws TException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().lastOfRemaining(condition);
    }

    /**
     * 取得最後一筆項目或null。
     *
     * @return 最後一筆項目，或null表示沒有項目
     */
    default T lastOrNull() {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().lastOfRemainingOrNull();
    }

    /**
     * 取得符合條件的最後一筆項目或null。
     *
     * @param <TException> 測試條件可拋出的例外型態
     * @param condition 取得項目的條件測試函數
     * @return 最後一筆項目，或null表示沒有符合條件的項目
     * @throws TException 表示{@code condition}丟出例外
     */
    default <TException extends Throwable> T lastOrNull(PredicateEx<? super T, ? extends TException> condition)
            throws TException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().lastOfRemainingOrNull(condition);
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
    default T[] toArray(Class<T> type) {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().remainingToArray(type);
    }

    /**
     * 用目前項目值建立{@link ArrayList}。
     *
     * @return 包含目前項目的{@link ArrayList}
     */
    default ArrayList<T> toArrayList() {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().remainingToArrayList();
    }

    /**
     * 用目前項目值建立{@link ArrayList}，依照條件做過濾。
     *
     * @param <TException> 測試條件可拋出的例外型態
     * @param condition 取得項目的條件測試函數
     * @return 包含符合條件項目的{@link ArrayList}
     * @throws TException 表示{@code mapper}丟出例外
     */
    default <TException extends Throwable> ArrayList<T> toArrayListFiltered(
            PredicateEx<? super T, ? extends TException> condition)
                    throws TException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().remainingToArrayListFiltered(condition);
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
    default <R, TException extends Throwable> ArrayList<R> toArrayListMapped(
            FunctionEx<? super T, ? extends R, ? extends TException> mapper)
                    throws TException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().remainingToArrayListMapped(mapper);
    }

    /**
     * 用目前項目值建立{@link ArrayList}，依照鍵值做排序。
     *
     * @param <V> 鍵值型態
     * @param getKey 計算每個項目的鍵值
     * @return 包含目前項目的{@link ArrayList}，已排序
     */
    default <V extends Comparable<?>> ArrayList<T> toArrayListSorted(Function<? super T, ? extends V> getKey) {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().remainingToArrayListSorted(getKey);
    }

    /**
     * 用目前項目值建立{@link ArrayList}，依照比較器做排序。
     *
     * @param comparator 項目的比較器
     * @return 包含目前項目的{@link ArrayList}，已排序
     */
    default ArrayList<T> toArrayListSorted(Comparator<? super T> comparator) {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().remainingToArrayListSorted(comparator);
    }

    /**
     * 用目前項目值建立順序相反的{@link ArrayList}。
     *
     * @return 包含目前項目的{@link ArrayList}，順序相反
     */
    default ArrayList<T> toArrayListReversed() {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().remainingToArrayListReversed();
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
    default <K, KException extends Throwable> HashMap<K, T> toHashMap(
            FunctionEx<? super T, ? extends K, ? extends KException> getKey)
                    throws KException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().remainingToHashMap(getKey);
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
    default <K, V, KException extends Throwable, VException extends Throwable> HashMap<K, V> toHashMap(
            FunctionEx<? super T, ? extends K, ? extends KException> getKey,
            FunctionEx<? super T, ? extends V, ? extends VException> getValue)
                    throws KException, VException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().remainingToHashMap(getKey, getValue);
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
    default <K, KException extends Throwable> HashMap<K, ArrayList<T>> toHashMapGrouped(
            FunctionEx<? super T, ? extends K, ? extends KException> getKey)
                    throws KException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().remainingToHashMapGrouped(getKey);
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
    default <K, V, KException extends Throwable, VException extends Throwable> HashMap<K, ArrayList<V>>
            toHashMapGrouped(
                    FunctionEx<? super T, ? extends K, ? extends KException> getKey,
                    FunctionEx<? super T, ? extends V, ? extends VException> getValue)
                            throws KException, VException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().remainingToHashMapGrouped(getKey, getValue);
    }

    /**
     * 用目前項目值建立{@link HashSet}。
     * <p>
     * 重複值會被重疊覆蓋，後面的優先。
     * </p>
     *
     * @return 包含目前項目的{@link HashSet}
     */
    default HashSet<T> toHashSet() {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().remainingToHashSet();
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
    default <K, KException extends Throwable> TreeMap<K, T> toTreeMap(
            FunctionEx<? super T, ? extends K, ? extends KException> getKey)
                    throws KException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().remainingToTreeMap(getKey);
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
    default <K, V, KException extends Throwable, VException extends Throwable> TreeMap<K, V> toTreeMap(
            FunctionEx<? super T, ? extends K, ? extends KException> getKey,
            FunctionEx<? super T, ? extends V, ? extends VException> getValue)
                    throws KException, VException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().remainingToTreeMap(getKey, getValue);
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
    default <K, KException extends Throwable> TreeMap<K, ArrayList<T>> toTreeMapGrouped(
            FunctionEx<? super T, ? extends K, ? extends KException> getKey)
                    throws KException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().remainingToTreeMapGrouped(getKey);
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
    default <K, V, KException extends Throwable, VException extends Throwable> TreeMap<K, ArrayList<V>>
            toTreeMapGrouped(
                    FunctionEx<? super T, ? extends K, ? extends KException> getKey,
                    FunctionEx<? super T, ? extends V, ? extends VException> getValue)
                            throws KException, VException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().remainingToTreeMapGrouped(getKey, getValue);
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
    default <U, TException extends Throwable> U reduce(
            U identity, BiFunctionEx<U, ? super T, U, TException> accumulator)
                    throws TException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().reduceRemaining(identity, accumulator);
    }

    /**
     * 統計符合條件的項目數量。
     *
     * @param <TException> 過濾條件函數可拋出的例外型態
     * @param condition 過濾條件的函數
     * @return 符合條件的項目數量
     * @throws TException 表示{@code condition}丟出例外
     */
    default <TException extends Throwable> int count(PredicateEx<? super T, ? extends TException> condition)
            throws TException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().countRemaining(condition);
    }

    /**
     * 計算項目代表數值的平均。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的平均，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Throwable> Double avgDouble(
            ToDoubleFunctionEx<? super T, ? extends TException> getValue)
                    throws TException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().avgRemainingDouble(getValue);
    }

    /**
     * 計算項目代表數值的平均。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的平均，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Throwable> Integer avgInt(ToIntFunctionEx<? super T, ? extends TException> getValue)
            throws TException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().avgRemainingInt(getValue);
    }

    /**
     * 計算項目代表數值的平均。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的平均，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Throwable> Long avgLong(ToLongFunctionEx<? super T, ? extends TException> getValue)
            throws TException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().avgRemainingLong(getValue);
    }

    /**
     * 計算項目代表數值的最大值。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最大值，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Throwable> Double maxDouble(
            ToDoubleFunctionEx<? super T, ? extends TException> getValue)
                    throws TException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().maxRemainingDouble(getValue);
    }

    /**
     * 計算項目代表數值的最大值。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最大值，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Throwable> Integer maxInt(ToIntFunctionEx<? super T, ? extends TException> getValue)
            throws TException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().maxRemainingInt(getValue);
    }

    /**
     * 計算項目代表數值的最大值。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最大值，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Throwable> Long maxLong(ToLongFunctionEx<? super T, ? extends TException> getValue)
            throws TException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().maxRemainingLong(getValue);
    }

    /**
     * 計算項目代表數值的最小值。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最小值，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Throwable> Double minDouble(
            ToDoubleFunctionEx<? super T, ? extends TException> getValue)
                    throws TException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().minRemainingDouble(getValue);
    }

    /**
     * 計算項目代表數值的最小值。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最小值，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Throwable> Integer minInt(ToIntFunctionEx<? super T, ? extends TException> getValue)
            throws TException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().minRemainingInt(getValue);
    }

    /**
     * 計算項目代表數值的最小值。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的最小值，或null表示沒有項目
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Throwable> Long minLong(ToLongFunctionEx<? super T, ? extends TException> getValue)
            throws TException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().minRemainingLong(getValue);
    }

    /**
     * 計算項目代表數值的總和。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的總和
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Throwable> double sumDouble(
            ToDoubleFunctionEx<? super T, ? extends TException> getValue)
                    throws TException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().sumRemainingDouble(getValue);
    }

    /**
     * 計算項目代表數值的總和。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的總和
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Throwable> int sumInt(ToIntFunctionEx<? super T, ? extends TException> getValue)
            throws TException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().sumRemainingInt(getValue);
    }

    /**
     * 計算項目代表數值的總和。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的總和
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Throwable> long sumLong(ToIntFunctionEx<? super T, ? extends TException> getValue)
            throws TException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().sumRemainingLong(getValue);
    }

    /**
     * 計算項目代表數值的總和。
     *
     * @param <TException> 計算數值函數可拋出的例外型態
     * @param getValue 計算項目數值的函數
     * @return 項目代表數值的總和
     * @throws TException 表示{@code getValue}丟出例外
     */
    default <TException extends Throwable> long sumLong(ToLongFunctionEx<? super T, ? extends TException> getValue)
            throws TException {
        Iterable<T> thiz = (Iterable<T>) this;
        return thiz.iterator().sumRemainingLong(getValue);
    }
}
