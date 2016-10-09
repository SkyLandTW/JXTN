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

package jxtn.core.axi.comparators;

import java.util.Comparator;

/**
 * 可比較{@link Comparable}的比較器。
 * <p>
 * 支援null：null項目作為較小的一方。
 * </p>
 *
 * @author AqD
 * @param <T> 要比較的項目型態
 */
public final class ComparableComparator<T extends Comparable<? super T>> implements Comparator<T> {

    @SuppressWarnings("rawtypes")
    public static final ComparableComparator instance = new ComparableComparator<>();

    public static <T extends Comparable<? super T>> ComparableComparator<T> getInstance() {
        return ComparableComparator.instance;
    }

    private ComparableComparator() {
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public int compare(T o1, T o2) {
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null) {
            return -1;
        }
        if (o2 == null) {
            return 1;
        }
        Comparable x1 = o1;
        Comparable x2 = o2;
        return x1.compareTo(x2);
    }
}
