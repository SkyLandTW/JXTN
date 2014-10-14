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
 * 成員比較器
 * <p>
 * 支援null：null項目或成員作為較小的一方
 * </p>
 *
 * @author AqD
 */
public class ArrayComparators
{
    /**
     * 取得boolean陣列的比較器
     *
     * @return boolean陣列的比較器
     */
    public static final Comparator<boolean[]> forBooleans()
    {
        return new Comparator<boolean[]>()
            {
                @Override
                public int compare(boolean[] m1, boolean[] m2)
                {
                    return ArrayComparators.compare(m1, m2);
                }
            };
    }

    /**
     * 取得byte陣列的比較器
     *
     * @return byte陣列的比較器
     */
    public static final Comparator<byte[]> forBytes()
    {
        return new Comparator<byte[]>()
            {
                @Override
                public int compare(byte[] m1, byte[] m2)
                {
                    return ArrayComparators.compare(m1, m2);
                }
            };
    }

    /**
     * 取得char陣列的比較器
     *
     * @return char陣列的比較器
     */
    public static final Comparator<char[]> forChars()
    {
        return new Comparator<char[]>()
            {
                @Override
                public int compare(char[] m1, char[] m2)
                {
                    return ArrayComparators.compare(m1, m2);
                }
            };
    }

    /**
     * 取得short陣列的比較器
     *
     * @return short陣列的比較器
     */
    public static final Comparator<short[]> forShorts()
    {
        return new Comparator<short[]>()
            {
                @Override
                public int compare(short[] m1, short[] m2)
                {
                    return ArrayComparators.compare(m1, m2);
                }
            };
    }

    /**
     * 取得int陣列的比較器
     *
     * @return int陣列的比較器
     */
    public static final Comparator<int[]> forInts()
    {
        return new Comparator<int[]>()
            {
                @Override
                public int compare(int[] m1, int[] m2)
                {
                    return ArrayComparators.compare(m1, m2);
                }
            };
    }

    /**
     * 取得long陣列的比較器
     *
     * @return long陣列的比較器
     */
    public static final Comparator<long[]> forLongs()
    {
        return new Comparator<long[]>()
            {
                @Override
                public int compare(long[] m1, long[] m2)
                {
                    return ArrayComparators.compare(m1, m2);
                }
            };
    }

    /**
     * 取得float陣列的比較器
     *
     * @return float陣列的比較器
     */
    public static final Comparator<float[]> forFloats()
    {
        return new Comparator<float[]>()
            {
                @Override
                public int compare(float[] m1, float[] m2)
                {
                    return ArrayComparators.compare(m1, m2);
                }
            };
    }

    /**
     * 取得double陣列的比較器
     *
     * @return double陣列的比較器
     */
    public static final Comparator<double[]> forDoubles()
    {
        return new Comparator<double[]>()
            {
                @Override
                public int compare(double[] m1, double[] m2)
                {
                    return ArrayComparators.compare(m1, m2);
                }
            };
    }

    /**
     * 取得{@link Comparable}陣列的比較器
     *
     * @param <T> 陣列項目型態
     * @return {@link Comparable}陣列的比較器
     */
    @SuppressWarnings("rawtypes")
    public static final <T extends Comparable> Comparator<T[]> forComparables()
    {
        return new Comparator<T[]>()
            {
                @Override
                public int compare(T[] m1, T[] m2)
                {
                    return ArrayComparators.compare(m1, m2);
                }
            };
    }

    /**
     * 比較兩陣列
     * <p>
     * 支援null：null項目或成員作為較小的一方
     * </p>
     *
     * @param m1 陣列1，可為null
     * @param m2 陣列2，可為null
     * @return 0表示兩陣列相等，大於0表示{@code m1}大於{@code m2}，小於0表示{@code m1}小於{@code m2}
     */
    public static int compare(boolean[] m1, boolean[] m2)
    {
        if (m1 == null && m2 == null)
            return 0;
        if (m1 == null)
            return -1;
        if (m2 == null)
            return 1;
        int len = Math.min(m1.length, m2.length);
        for (int i = 0; i < len; i++)
        {
            boolean v1 = m1[i];
            boolean v2 = m2[i];
            if (v1 != v2)
                return v1 ? 1 : -1;
        }
        return m1.length - m2.length;
    }

    /**
     * 比較兩陣列
     * <p>
     * 支援null：null項目或成員作為較小的一方
     * </p>
     *
     * @param m1 陣列1，可為null
     * @param m2 陣列2，可為null
     * @return 0表示兩陣列相等，大於0表示{@code m1}大於{@code m2}，小於0表示{@code m1}小於{@code m2}
     */
    public static int compare(byte[] m1, byte[] m2)
    {
        if (m1 == null && m2 == null)
            return 0;
        if (m1 == null)
            return -1;
        if (m2 == null)
            return 1;
        int len = Math.min(m1.length, m2.length);
        for (int i = 0; i < len; i++)
        {
            byte v1 = m1[i];
            byte v2 = m2[i];
            int diff = v1 - v2;
            if (diff != 0)
                return diff > 0 ? 1 : -1;
        }
        return m1.length - m2.length;
    }

    /**
     * 比較兩陣列
     * <p>
     * 支援null：null項目或成員作為較小的一方
     * </p>
     *
     * @param m1 陣列1，可為null
     * @param m2 陣列2，可為null
     * @return 0表示兩陣列相等，大於0表示{@code m1}大於{@code m2}，小於0表示{@code m1}小於{@code m2}
     */
    public static int compare(char[] m1, char[] m2)
    {
        if (m1 == null && m2 == null)
            return 0;
        if (m1 == null)
            return -1;
        if (m2 == null)
            return 1;
        int len = Math.min(m1.length, m2.length);
        for (int i = 0; i < len; i++)
        {
            char v1 = m1[i];
            char v2 = m2[i];
            int diff = v1 - v2;
            if (diff != 0)
                return diff > 0 ? 1 : -1;
        }
        return m1.length - m2.length;
    }

    /**
     * 比較兩陣列
     * <p>
     * 支援null：null項目或成員作為較小的一方
     * </p>
     *
     * @param m1 陣列1，可為null
     * @param m2 陣列2，可為null
     * @return 0表示兩陣列相等，大於0表示{@code m1}大於{@code m2}，小於0表示{@code m1}小於{@code m2}
     */
    public static int compare(short[] m1, short[] m2)
    {
        if (m1 == null && m2 == null)
            return 0;
        if (m1 == null)
            return -1;
        if (m2 == null)
            return 1;
        int len = Math.min(m1.length, m2.length);
        for (int i = 0; i < len; i++)
        {
            short v1 = m1[i];
            short v2 = m2[i];
            int diff = v1 - v2;
            if (diff != 0)
                return diff > 0 ? 1 : -1;
        }
        return m1.length - m2.length;
    }

    /**
     * 比較兩陣列
     * <p>
     * 支援null：null項目或成員作為較小的一方
     * </p>
     *
     * @param m1 陣列1，可為null
     * @param m2 陣列2，可為null
     * @return 0表示兩陣列相等，大於0表示{@code m1}大於{@code m2}，小於0表示{@code m1}小於{@code m2}
     */
    public static int compare(int[] m1, int[] m2)
    {
        if (m1 == null && m2 == null)
            return 0;
        if (m1 == null)
            return -1;
        if (m2 == null)
            return 1;
        int len = Math.min(m1.length, m2.length);
        for (int i = 0; i < len; i++)
        {
            int v1 = m1[i];
            int v2 = m2[i];
            int diff = v1 - v2;
            if (diff != 0)
                return diff > 0 ? 1 : -1;
        }
        return m1.length - m2.length;
    }

    /**
     * 比較兩陣列
     * <p>
     * 支援null：null項目或成員作為較小的一方
     * </p>
     *
     * @param m1 陣列1，可為null
     * @param m2 陣列2，可為null
     * @return 0表示兩陣列相等，大於0表示{@code m1}大於{@code m2}，小於0表示{@code m1}小於{@code m2}
     */
    public static int compare(long[] m1, long[] m2)
    {
        if (m1 == null && m2 == null)
            return 0;
        if (m1 == null)
            return -1;
        if (m2 == null)
            return 1;
        int len = Math.min(m1.length, m2.length);
        for (int i = 0; i < len; i++)
        {
            long v1 = m1[i];
            long v2 = m2[i];
            long diff = v1 - v2;
            if (diff != 0)
                return diff > 0 ? 1 : -1;
        }
        return m1.length - m2.length;
    }

    /**
     * 比較兩陣列
     * <p>
     * 支援null：null項目或成員作為較小的一方
     * </p>
     *
     * @param m1 陣列1，可為null
     * @param m2 陣列2，可為null
     * @return 0表示兩陣列相等，大於0表示{@code m1}大於{@code m2}，小於0表示{@code m1}小於{@code m2}
     */
    public static int compare(float[] m1, float[] m2)
    {
        if (m1 == null && m2 == null)
            return 0;
        if (m1 == null)
            return -1;
        if (m2 == null)
            return 1;
        int len = Math.min(m1.length, m2.length);
        for (int i = 0; i < len; i++)
        {
            float v1 = m1[i];
            float v2 = m2[i];
            float diff = v1 - v2;
            if (diff != 0)
                return diff > 0 ? 1 : -1;
        }
        return m1.length - m2.length;
    }

    /**
     * 比較兩陣列
     * <p>
     * 支援null：null項目或成員作為較小的一方
     * </p>
     *
     * @param m1 陣列1，可為null
     * @param m2 陣列2，可為null
     * @return 0表示兩陣列相等，大於0表示{@code m1}大於{@code m2}，小於0表示{@code m1}小於{@code m2}
     */
    public static int compare(double[] m1, double[] m2)
    {
        if (m1 == null && m2 == null)
            return 0;
        if (m1 == null)
            return -1;
        if (m2 == null)
            return 1;
        int len = Math.min(m1.length, m2.length);
        for (int i = 0; i < len; i++)
        {
            double v1 = m1[i];
            double v2 = m2[i];
            double diff = v1 - v2;
            if (diff != 0)
                return diff > 0 ? 1 : -1;
        }
        return m1.length - m2.length;
    }

    /**
     * 比較兩陣列
     * <p>
     * 支援null：null項目或成員作為較小的一方
     * </p>
     *
     * @param <T> 陣列項目型態
     * @param m1 陣列1，可為null
     * @param m2 陣列2，可為null
     * @return 0表示兩陣列相等，大於0表示{@code m1}大於{@code m2}，小於0表示{@code m1}小於{@code m2}
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T extends Comparable> int compare(T[] m1, T[] m2)
    {
        if (m1 == null && m2 == null)
            return 0;
        if (m1 == null)
            return -1;
        if (m2 == null)
            return 1;
        int len = Math.min(m1.length, m2.length);
        for (int i = 0; i < len; i++)
        {
            T v1 = m1[i];
            T v2 = m2[i];
            if (v1 == null && v2 == null)
                continue;
            if (v1 == null)
                return -1;
            if (v2 == null)
                return 1;
            int diff = v1.compareTo(v2);
            if (diff != 0)
                return diff;
        }
        return m1.length - m2.length;
    }

    private ArrayComparators()
    {
    }
}
