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
import java.util.function.FunctionEx;
import jxtn.core.axi.comparators.MemberComparators;

/**
 * {@link Collection}的延伸功能。
 *
 * @author AqD
 * @param <E> 集合項目型態
 */
public interface CollectionExt<E> extends IterableExt<E> {
    //////////////////////////////////////////////////////////////////////////
    // 泛型方法
    //

    /**
     * 泛型版本的{@link Collection#contains}。
     *
     * @param e 要檢查是否包含的項目
     * @return true表示項目{@code e}在目前的集合內
     */
    @SuppressWarnings("deprecation")
    default boolean contains2(E e) {
        Collection<E> thiz = (Collection<E>) this;
        return thiz.contains(e);
    }

    /**
     * 泛型版本的{@link Collection#containsAll}。
     *
     * @param c 要檢查是否包含的項目之集合
     * @return true表示所有{@code c}內的項目皆包含在目前的集合內
     */
    @SuppressWarnings("deprecation")
    default boolean containsAll2(Collection<? extends E> c) {
        Collection<E> thiz = (Collection<E>) this;
        return thiz.containsAll(c);
    }

    /**
     * 泛型版本的{@link Collection#remove}。
     *
     * @param e 要移除的項目
     * @return true表示項目{@code e}移除成功，false表示集合並未包含{@code e}
     */
    @SuppressWarnings("deprecation")
    default boolean remove2(E e) {
        Collection<E> thiz = (Collection<E>) this;
        return thiz.remove(e);
    }

    /**
     * 泛型版本的{@link Collection#removeAll}。
     *
     * @param c 要移除的項目之集合
     * @return true表示集合已變更
     */
    @SuppressWarnings("deprecation")
    default boolean removeAll2(Collection<? extends E> c) {
        Collection<E> thiz = (Collection<E>) this;
        return thiz.removeAll(c);
    }

    /**
     * 泛型版本的{@link Collection#retainAll}。
     *
     * @param c 要保留的項目之集合
     * @return true表示集合已變更
     */
    @SuppressWarnings("deprecation")
    default boolean retainAll2(Collection<? extends E> c) {
        Collection<E> thiz = (Collection<E>) this;
        return thiz.retainAll(c);
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
    @Override
    @SuppressWarnings("unchecked")
    default E[] toArray(Class<E> type) {
        Collection<E> thiz = (Collection<E>) this;
        ArrayList<E> list = thiz.toArrayList();
        E[] array = (E[]) Array.newInstance(type, list.size());
        return list.toArray(array);
    }

    /**
     * 用目前項目值建立{@link ArrayList}。
     *
     * @return 包含目前項目的{@link ArrayList}
     */
    @Override
    default ArrayList<E> toArrayList() {
        Collection<E> thiz = (Collection<E>) this;
        ArrayList<E> coll = new ArrayList<>(thiz);
        return coll;
    }

    /**
     * 用目前項目值建立{@link ArrayList}，依照鍵值做排序。
     *
     * @param <V> 鍵值型態
     * @param getKey 計算每個項目的鍵值
     * @return 包含目前項目的{@link ArrayList}，已排序
     */
    @Override
    default <V extends Comparable<?>> ArrayList<E> toArrayListSorted(Function<? super E, ? extends V> getKey) {
        Collection<E> thiz = (Collection<E>) this;
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
    @Override
    default ArrayList<E> toArrayListSorted(Comparator<? super E> comparator) {
        Collection<E> thiz = (Collection<E>) this;
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
    @Override
    default <K, KException extends Exception> HashMap<K, E> toHashMap(
            FunctionEx<? super E, ? extends K, ? extends KException> getKey)
                    throws KException {
        Collection<E> thiz = (Collection<E>) this;
        HashMap<K, E> coll = new HashMap<>(thiz.size());
        for (E item : thiz) {
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
    @Override
    default <K, V, KException extends Exception, VException extends Exception> HashMap<K, V> toHashMap(
            FunctionEx<? super E, ? extends K, ? extends KException> getKey,
            FunctionEx<? super E, ? extends V, ? extends VException> getValue)
                    throws KException, VException {
        Collection<E> thiz = (Collection<E>) this;
        HashMap<K, V> coll = new HashMap<>(thiz.size());
        for (E item : thiz) {
            K k = getKey.applyEx(item);
            V v = getValue.applyEx(item);
            coll.put(k, v);
        }
        return coll;
    }

    /**
     * 用目前項目值建立{@link HashSet}。
     * <p>
     * 重複值會被重疊覆蓋，後面的優先。
     * </p>
     *
     * @return 包含目前項目的{@link HashSet}
     */
    @Override
    default HashSet<E> toHashSet() {
        Collection<E> thiz = (Collection<E>) this;
        HashSet<E> coll = new HashSet<>(thiz);
        return coll;
    }

}
