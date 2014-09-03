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

package jxtn.core.axi;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * 成員比較器
 * <p>
 * 支援null：null項目或成員作為較小的一方
 * </p>
 *
 * @author AqD
 */
public final class MemberComparators
{
    public static <E, M extends Comparable<?>> Comparator<E> byComparable(Function<E, M> getMember)
    {
        return new MemberComparableComparator<E, M>(getMember);
    }

    public static <E> Comparator<E> byBoolean(Predicate<E> getMember)
    {
        return new MemberBooleanComparator<E>(getMember);
    }

    public static <E> Comparator<E> byDouble(ToDoubleFunction<E> getMember)
    {
        return new MemberDoubleComparator<E>(getMember);
    }

    public static <E> Comparator<E> byInt(ToIntFunction<E> getMember)
    {
        return new MemberIntComparator<E>(getMember);
    }

    public static <E> Comparator<E> byLong(ToLongFunction<E> getMember)
    {
        return new MemberLongComparator<E>(getMember);
    }

    private MemberComparators()
    {
    }

    public static class MemberComparableComparator<E, M extends Comparable<?>> implements Comparator<E>
    {
        public final Function<E, M> getMember;

        public MemberComparableComparator(Function<E, M> getMember)
        {
            this.getMember = getMember;
        }

        @SuppressWarnings({ "rawtypes", "unchecked" })
        @Override
        public int compare(E o1, E o2)
        {
            if (o1 == null && o2 == null)
                return 0;
            if (o1 == null)
                return -1;
            if (o2 == null)
                return 1;
            M m1 = this.getMember.apply(o1);
            M m2 = this.getMember.apply(o2);
            if (m1 == null && m2 == null)
                return 0;
            if (m1 == null)
                return -1;
            if (m2 == null)
                return 1;
            Comparable c1 = m1;
            return c1.compareTo(m2);
        }
    }

    public static class MemberBooleanComparator<E> implements Comparator<E>
    {
        public final Predicate<E> getMember;

        public MemberBooleanComparator(Predicate<E> getMember)
        {
            this.getMember = getMember;
        }

        @Override
        public int compare(E o1, E o2)
        {
            if (o1 == null && o2 == null)
                return 0;
            if (o1 == null)
                return -1;
            if (o2 == null)
                return 1;
            boolean m1 = this.getMember.test(o1);
            boolean m2 = this.getMember.test(o2);
            if (m1 && m2)
                return 0;
            if (m1)
                return 1;
            else
                return -1;
        }
    }

    public static class MemberDoubleComparator<E> implements Comparator<E>
    {
        public final ToDoubleFunction<E> getMember;

        public MemberDoubleComparator(ToDoubleFunction<E> getMember)
        {
            this.getMember = getMember;
        }

        @Override
        public int compare(E o1, E o2)
        {
            if (o1 == null && o2 == null)
                return 0;
            if (o1 == null)
                return -1;
            if (o2 == null)
                return 1;
            double m1 = this.getMember.applyAsDouble(o1);
            double m2 = this.getMember.applyAsDouble(o2);
            if (m1 == m2)
                return 0;
            if (m1 > m2)
                return 1;
            else
                return -1;
        }
    }

    public static class MemberIntComparator<E> implements Comparator<E>
    {
        public final ToIntFunction<E> getMember;

        public MemberIntComparator(ToIntFunction<E> getMember)
        {
            this.getMember = getMember;
        }

        @Override
        public int compare(E o1, E o2)
        {
            if (o1 == null && o2 == null)
                return 0;
            if (o1 == null)
                return -1;
            if (o2 == null)
                return 1;
            int m1 = this.getMember.applyAsInt(o1);
            int m2 = this.getMember.applyAsInt(o2);
            if (m1 == m2)
                return 0;
            if (m1 > m2)
                return 1;
            else
                return -1;
        }
    }

    public static class MemberLongComparator<E> implements Comparator<E>
    {
        public final ToLongFunction<E> getMember;

        public MemberLongComparator(ToLongFunction<E> getMember)
        {
            this.getMember = getMember;
        }

        @Override
        public int compare(E o1, E o2)
        {
            if (o1 == null && o2 == null)
                return 0;
            if (o1 == null)
                return -1;
            if (o2 == null)
                return 1;
            long m1 = this.getMember.applyAsLong(o1);
            long m2 = this.getMember.applyAsLong(o2);
            if (m1 == m2)
                return 0;
            if (m1 > m2)
                return 1;
            else
                return -1;
        }
    }
}
