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
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * 成員比較器。
 * <p>
 * 支援null：null項目或成員作為較小的一方。
 * </p>
 *
 * @author AqD
 */
public final class MemberComparators {
    /**
     * 依照{@link Comparable}成員做比較。
     * <p>
     * 支援null：null項目或成員作為較小的一方。
     * </p>
     *
     * @param <E> 要比較的物件型態
     * @param <M> 代表{@code E}做比較的物件成員型態
     * @param getMember 取得要代表{@code E}做比較的物件成員
     * @return 依照{@code M}成員比較{@code E}的比較器
     */
    public static <E, M extends Comparable<?>> Comparator<E> byComparable(Function<E, M> getMember) {
        return new MemberComparableComparator<>(getMember);
    }

    /**
     * 依照boolean陣列成員做比較。
     * <p>
     * 支援null：null項目或成員作為較小的一方。
     * </p>
     *
     * @param <E> 要比較的物件型態
     * @param getMember 取得要代表{@code E}做比較的boolean陣列成員
     * @return 依照boolean陣列成員比較{@code E}的比較器
     */
    public static <E> Comparator<E> byArrayOfBoolean(Function<E, boolean[]> getMember) {
        return new AbstractMemberComparator<E, boolean[]>(getMember) {
            @Override
            protected int compareMember(boolean[] m1, boolean[] m2) {
                return ArrayComparators.compare(m1, m2);
            }
        };
    }

    /**
     * 依照byte陣列成員做比較。
     * <p>
     * 支援null：null項目或成員作為較小的一方。
     * </p>
     *
     * @param <E> 要比較的物件型態
     * @param getMember 取得要代表{@code E}做比較的byte陣列成員
     * @return 依照byte陣列成員比較{@code E}的比較器
     */
    public static <E> Comparator<E> byArrayOfByte(Function<E, byte[]> getMember) {
        return new AbstractMemberComparator<E, byte[]>(getMember) {
            @Override
            protected int compareMember(byte[] m1, byte[] m2) {
                return ArrayComparators.compare(m1, m2);
            }
        };
    }

    /**
     * 依照char陣列成員做比較。
     * <p>
     * 支援null：null項目或成員作為較小的一方。
     * </p>
     *
     * @param <E> 要比較的物件型態
     * @param getMember 取得要代表{@code E}做比較的char陣列成員
     * @return 依照char陣列成員比較{@code E}的比較器
     */
    public static <E> Comparator<E> byArrayOfChar(Function<E, char[]> getMember) {
        return new AbstractMemberComparator<E, char[]>(getMember) {
            @Override
            protected int compareMember(char[] m1, char[] m2) {
                return ArrayComparators.compare(m1, m2);
            }
        };
    }

    /**
     * 依照short陣列成員做比較。
     * <p>
     * 支援null：null項目或成員作為較小的一方。
     * </p>
     *
     * @param <E> 要比較的物件型態
     * @param getMember 取得要代表{@code E}做比較的short陣列成員
     * @return 依照short陣列成員比較{@code E}的比較器
     */
    public static <E> Comparator<E> byArrayOfShort(Function<E, short[]> getMember) {
        return new AbstractMemberComparator<E, short[]>(getMember) {
            @Override
            protected int compareMember(short[] m1, short[] m2) {
                return ArrayComparators.compare(m1, m2);
            }
        };
    }

    /**
     * 依照int陣列成員做比較。
     * <p>
     * 支援null：null項目或成員作為較小的一方。
     * </p>
     *
     * @param <E> 要比較的物件型態
     * @param getMember 取得要代表{@code E}做比較的int陣列成員
     * @return 依照int陣列成員比較{@code E}的比較器
     */
    public static <E> Comparator<E> byArrayOfInt(Function<E, int[]> getMember) {
        return new AbstractMemberComparator<E, int[]>(getMember) {
            @Override
            protected int compareMember(int[] m1, int[] m2) {
                return ArrayComparators.compare(m1, m2);
            }
        };
    }

    /**
     * 依照long陣列成員做比較。
     * <p>
     * 支援null：null項目或成員作為較小的一方。
     * </p>
     *
     * @param <E> 要比較的物件型態
     * @param getMember 取得要代表{@code E}做比較的long陣列成員
     * @return 依照long陣列成員比較{@code E}的比較器
     */
    public static <E> Comparator<E> byArrayOfLong(Function<E, long[]> getMember) {
        return new AbstractMemberComparator<E, long[]>(getMember) {
            @Override
            protected int compareMember(long[] m1, long[] m2) {
                return ArrayComparators.compare(m1, m2);
            }
        };
    }

    /**
     * 依照float陣列成員做比較。
     * <p>
     * 支援null：null項目或成員作為較小的一方。
     * </p>
     *
     * @param <E> 要比較的物件型態
     * @param getMember 取得要代表{@code E}做比較的float陣列成員
     * @return 依照float陣列成員比較{@code E}的比較器
     */
    public static <E> Comparator<E> byArrayOfFloat(Function<E, float[]> getMember) {
        return new AbstractMemberComparator<E, float[]>(getMember) {
            @Override
            protected int compareMember(float[] m1, float[] m2) {
                return ArrayComparators.compare(m1, m2);
            }
        };
    }

    /**
     * 依照double陣列成員做比較。
     * <p>
     * 支援null：null項目或成員作為較小的一方。
     * </p>
     *
     * @param <E> 要比較的物件型態
     * @param getMember 取得要代表{@code E}做比較的double陣列成員
     * @return 依照double陣列成員比較{@code E}的比較器
     */
    public static <E> Comparator<E> byArrayOfDouble(Function<E, double[]> getMember) {
        return new AbstractMemberComparator<E, double[]>(getMember) {
            @Override
            protected int compareMember(double[] m1, double[] m2) {
                return ArrayComparators.compare(m1, m2);
            }
        };
    }

    /**
     * 依照double陣列成員做比較。
     * <p>
     * 支援null：null項目或成員作為較小的一方。
     * </p>
     *
     * @param <E> 要比較的物件型態
     * @param <M> 代表{@code E}做比較的物件成員的陣列項目型態
     * @param getMember 取得要代表{@code E}做比較的double陣列成員
     * @return 依照double陣列成員比較{@code E}的比較器
     */
    @SuppressWarnings("rawtypes")
    public static <E, M extends Comparable> Comparator<E> byArrayOfComparable(Function<E, M[]> getMember) {
        return new AbstractMemberComparator<E, M[]>(getMember) {
            @Override
            protected int compareMember(M[] m1, M[] m2) {
                return ArrayComparators.compare(m1, m2);
            }
        };
    }

    /**
     * 依照boolean成員做比較。
     * <p>
     * 支援null：null項目或成員作為較小的一方。
     * </p>
     *
     * @param <E> 要比較的物件型態
     * @param getMember 取得要代表{@code E}做比較的物件成員
     * @return 依照boolean成員比較{@code E}的比較器
     */
    public static <E> Comparator<E> byBoolean(Predicate<E> getMember) {
        return new MemberBooleanComparator<>(getMember);
    }

    /**
     * 依照double成員做比較。
     * <p>
     * 支援null：null項目或成員作為較小的一方。
     * </p>
     *
     * @param <E> 要比較的物件型態
     * @param getMember 取得要代表{@code E}做比較的物件成員
     * @return 依照double成員比較{@code E}的比較器
     */
    public static <E> Comparator<E> byDouble(ToDoubleFunction<E> getMember) {
        return new MemberDoubleComparator<>(getMember);
    }

    /**
     * 依照int成員做比較。
     * <p>
     * 支援null：null項目或成員作為較小的一方。
     * </p>
     *
     * @param <E> 要比較的物件型態
     * @param getMember 取得要代表{@code E}做比較的物件成員
     * @return 依照int成員比較{@code E}的比較器
     */
    public static <E> Comparator<E> byInt(ToIntFunction<E> getMember) {
        return new MemberIntComparator<>(getMember);
    }

    /**
     * 依照long成員做比較。
     * <p>
     * 支援null：null項目或成員作為較小的一方。
     * </p>
     *
     * @param <E> 要比較的物件型態
     * @param getMember 取得要代表{@code E}做比較的物件成員
     * @return 依照long成員比較{@code E}的比較器
     */
    public static <E> Comparator<E> byLong(ToLongFunction<E> getMember) {
        return new MemberLongComparator<>(getMember);
    }

    private MemberComparators() {
    }

    private static abstract class AbstractMemberComparator<E, M> implements Comparator<E> {
        private final Function<E, M> getMember;

        public AbstractMemberComparator(Function<E, M> getMember) {
            this.getMember = getMember;
        }

        @Override
        public int compare(E o1, E o2) {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }
            M m1 = this.getMember.apply(o1);
            M m2 = this.getMember.apply(o2);
            if (m1 == null && m2 == null) {
                return 0;
            }
            if (m1 == null) {
                return -1;
            }
            if (m2 == null) {
                return 1;
            }
            return this.compareMember(m1, m2);
        }

        protected abstract int compareMember(M m1, M m2);
    }

    private static class MemberComparableComparator<E, M extends Comparable<?>> implements Comparator<E> {
        public final Function<E, M> getMember;

        public MemberComparableComparator(Function<E, M> getMember) {
            Objects.requireNonNull(getMember);
            this.getMember = getMember;
        }

        @SuppressWarnings({ "rawtypes", "unchecked" })
        @Override
        public int compare(E o1, E o2) {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }
            M m1 = this.getMember.apply(o1);
            M m2 = this.getMember.apply(o2);
            if (m1 == null && m2 == null) {
                return 0;
            }
            if (m1 == null) {
                return -1;
            }
            if (m2 == null) {
                return 1;
            }
            Comparable c1 = m1;
            return c1.compareTo(m2);
        }
    }

    private static class MemberBooleanComparator<E> implements Comparator<E> {
        public final Predicate<E> getMember;

        public MemberBooleanComparator(Predicate<E> getMember) {
            Objects.requireNonNull(getMember);
            this.getMember = getMember;
        }

        @Override
        public int compare(E o1, E o2) {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }
            boolean m1 = this.getMember.test(o1);
            boolean m2 = this.getMember.test(o2);
            if (m1 && m2) {
                return 0;
            }
            if (m1) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    private static class MemberDoubleComparator<E> implements Comparator<E> {
        public final ToDoubleFunction<E> getMember;

        public MemberDoubleComparator(ToDoubleFunction<E> getMember) {
            Objects.requireNonNull(getMember);
            this.getMember = getMember;
        }

        @Override
        public int compare(E o1, E o2) {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }
            double m1 = this.getMember.applyAsDouble(o1);
            double m2 = this.getMember.applyAsDouble(o2);
            if (m1 == m2) {
                return 0;
            }
            if (m1 > m2) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    private static class MemberIntComparator<E> implements Comparator<E> {
        public final ToIntFunction<E> getMember;

        public MemberIntComparator(ToIntFunction<E> getMember) {
            Objects.requireNonNull(getMember);
            this.getMember = getMember;
        }

        @Override
        public int compare(E o1, E o2) {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }
            int m1 = this.getMember.applyAsInt(o1);
            int m2 = this.getMember.applyAsInt(o2);
            if (m1 == m2) {
                return 0;
            }
            if (m1 > m2) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    private static class MemberLongComparator<E> implements Comparator<E> {
        public final ToLongFunction<E> getMember;

        public MemberLongComparator(ToLongFunction<E> getMember) {
            Objects.requireNonNull(getMember);
            this.getMember = getMember;
        }

        @Override
        public int compare(E o1, E o2) {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }
            long m1 = this.getMember.applyAsLong(o1);
            long m2 = this.getMember.applyAsLong(o2);
            if (m1 == m2) {
                return 0;
            }
            if (m1 > m2) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
