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
package java.nio;

import sun.misc.Unsafe;

/**
 * Publicly-accessible wrapper for {@link java.nio.Bits}
 *
 * @author aqd
 */
public final class Bits2 {

    // -- Swapping --

    public static short swap(short x) {
        return Bits.swap(x);
    }

    public static char swap(char x) {
        return Bits.swap(x);
    }

    public static int swap(int x) {
        return Bits.swap(x);
    }

    public static long swap(long x) {
        return Bits.swap(x);
    }

    public static char getCharL(ByteBuffer bb, int bi) {
        return Bits.getCharL(bb, bi);
    }

    public static char getCharL(long a) {
        return Bits.getCharL(a);
    }

    public static char getCharB(ByteBuffer bb, int bi) {
        return Bits.getCharB(bb, bi);
    }

    public static char getCharB(long a) {
        return Bits.getCharB(a);
    }

    public static char getChar(ByteBuffer bb, int bi, boolean bigEndian) {
        return Bits.getChar(bb, bi, bigEndian);
    }

    public static char getChar(long a, boolean bigEndian) {
        return getChar(a, bigEndian);
    }

    public static void putCharL(ByteBuffer bb, int bi, char x) {
        Bits.putCharL(bb, bi, x);
    }

    public static void putCharL(long a, char x) {
        Bits.putCharL(a, x);
    }

    public static void putCharB(ByteBuffer bb, int bi, char x) {
        Bits.putCharB(bb, bi, x);
    }

    public static void putCharB(long a, char x) {
        Bits.putCharB(a, x);
    }

    public static void putChar(ByteBuffer bb, int bi, char x, boolean bigEndian) {
        Bits.putChar(bb, bi, x, bigEndian);
    }

    public static void putChar(long a, char x, boolean bigEndian) {
        Bits.putChar(a, x, bigEndian);
    }

    public static short getShortL(ByteBuffer bb, int bi) {
        return Bits.getShortL(bb, bi);
    }

    public static short getShortL(long a) {
        return Bits.getShortL(a);
    }

    public static short getShortB(ByteBuffer bb, int bi) {
        return Bits.getShortB(bb, bi);
    }

    public static short getShortB(long a) {
        return Bits.getShortB(a);
    }

    public static short getShort(ByteBuffer bb, int bi, boolean bigEndian) {
        return Bits.getShort(bb, bi, bigEndian);
    }

    public static short getShort(long a, boolean bigEndian) {
        return Bits.getShort(a, bigEndian);
    }

    public static void putShortL(ByteBuffer bb, int bi, short x) {
        Bits.putShortL(bb, bi, x);
    }

    public static void putShortL(long a, short x) {
        Bits.putShortL(a, x);
    }

    public static void putShortB(ByteBuffer bb, int bi, short x) {
        Bits.putShortB(bb, bi, x);
    }

    public static void putShortB(long a, short x) {
        Bits.putShortB(a, x);
    }

    public static void putShort(ByteBuffer bb, int bi, short x, boolean bigEndian) {
        Bits.putShort(bb, bi, x, bigEndian);
    }

    public static void putShort(long a, short x, boolean bigEndian) {
        Bits.putShort(a, x, bigEndian);
    }

    // -- get/put int --

    public static int getIntL(ByteBuffer bb, int bi) {
        return Bits.getIntL(bb, bi);
    }

    public static int getIntL(long a) {
        return Bits.getIntL(a);
    }

    public static int getIntB(ByteBuffer bb, int bi) {
        return Bits.getIntB(bb, bi);
    }

    public static int getIntB(long a) {
        return Bits.getIntB(a);
    }

    public static int getInt(ByteBuffer bb, int bi, boolean bigEndian) {
        return Bits.getInt(bb, bi, bigEndian);
    }

    public static int getInt(long a, boolean bigEndian) {
        return Bits.getInt(a, bigEndian);
    }

    public static void putIntL(ByteBuffer bb, int bi, int x) {
        Bits.putIntL(bb, bi, x);
    }

    public static void putIntL(long a, int x) {
        Bits.putIntL(a, x);
    }

    public static void putIntB(ByteBuffer bb, int bi, int x) {
        Bits.putIntB(bb, bi, x);
    }

    public static void putIntB(long a, int x) {
        Bits.putIntB(a, x);
    }

    public static void putInt(ByteBuffer bb, int bi, int x, boolean bigEndian) {
        Bits.putInt(bb, bi, x, bigEndian);
    }

    public static void putInt(long a, int x, boolean bigEndian) {
        Bits.putInt(a, x, bigEndian);
    }

    // -- get/put long --

    public static long getLongL(ByteBuffer bb, int bi) {
        return Bits.getLongL(bb, bi);
    }

    public static long getLongL(long a) {
        return Bits.getLongL(a);
    }

    public static long getLongB(ByteBuffer bb, int bi) {
        return Bits.getLongB(bb, bi);
    }

    public static long getLongB(long a) {
        return Bits.getLongB(a);
    }

    public static long getLong(ByteBuffer bb, int bi, boolean bigEndian) {
        return Bits.getLong(bb, bi, bigEndian);
    }

    public static long getLong(long a, boolean bigEndian) {
        return Bits.getLong(a, bigEndian);
    }

    public static void putLongL(ByteBuffer bb, int bi, long x) {
        Bits.putLongL(bb, bi, x);
    }

    public static void putLongL(long a, long x) {
        Bits.putLongL(a, x);
    }

    public static void putLongB(ByteBuffer bb, int bi, long x) {
        Bits.putLongB(bb, bi, x);
    }

    public static void putLongB(long a, long x) {
        Bits.putLongB(a, x);
    }

    public static void putLong(ByteBuffer bb, int bi, long x, boolean bigEndian) {
        Bits.putLong(bb, bi, x, bigEndian);
    }

    public static void putLong(long a, long x, boolean bigEndian) {
        Bits.putLong(a, x, bigEndian);
    }

    // -- get/put float --

    public static float getFloatL(ByteBuffer bb, int bi) {
        return Bits.getFloatL(bb, bi);
    }

    public static float getFloatL(long a) {
        return Bits.getFloatL(a);
    }

    public static float getFloatB(ByteBuffer bb, int bi) {
        return Bits.getFloatB(bb, bi);
    }

    public static float getFloatB(long a) {
        return Bits.getFloatB(a);
    }

    public static float getFloat(ByteBuffer bb, int bi, boolean bigEndian) {
        return Bits.getFloat(bb, bi, bigEndian);
    }

    public static float getFloat(long a, boolean bigEndian) {
        return Bits.getFloat(a, bigEndian);
    }

    public static void putFloatL(ByteBuffer bb, int bi, float x) {
        Bits.putFloatL(bb, bi, x);
    }

    public static void putFloatL(long a, float x) {
        Bits.putFloatL(a, x);
    }

    public static void putFloatB(ByteBuffer bb, int bi, float x) {
        Bits.putFloatB(bb, bi, x);
    }

    public static void putFloatB(long a, float x) {
        Bits.putFloatB(a, x);
    }

    public static void putFloat(ByteBuffer bb, int bi, float x, boolean bigEndian) {
        Bits.putFloat(bb, bi, x, bigEndian);
    }

    public static void putFloat(long a, float x, boolean bigEndian) {
        Bits.putFloat(a, x, bigEndian);
    }

    // -- get/put double --

    public static double getDoubleL(ByteBuffer bb, int bi) {
        return Bits.getDoubleL(bb, bi);
    }

    public static double getDoubleL(long a) {
        return Bits.getDoubleL(a);
    }

    public static double getDoubleB(ByteBuffer bb, int bi) {
        return Bits.getDoubleB(bb, bi);
    }

    public static double getDoubleB(long a) {
        return Bits.getDoubleB(a);
    }

    public static double getDouble(ByteBuffer bb, int bi, boolean bigEndian) {
        return Bits.getDouble(bb, bi, bigEndian);
    }

    public static double getDouble(long a, boolean bigEndian) {
        return Bits.getDouble(a, bigEndian);
    }

    public static void putDoubleL(ByteBuffer bb, int bi, double x) {
        Bits.putDoubleL(bb, bi, x);
    }

    public static void putDoubleL(long a, double x) {
        Bits.putDoubleL(a, x);
    }

    public static void putDoubleB(ByteBuffer bb, int bi, double x) {
        Bits.putDoubleB(bb, bi, x);
    }

    public static void putDoubleB(long a, double x) {
        Bits.putDoubleB(a, x);
    }

    public static void putDouble(ByteBuffer bb, int bi, double x, boolean bigEndian) {
        Bits.putDouble(bb, bi, x, bigEndian);
    }

    public static void putDouble(long a, double x, boolean bigEndian) {
        Bits.putDouble(a, x, bigEndian);
    }

    // -- Unsafe access --

    private static final Unsafe unsafe = Unsafe.getUnsafe();

    public static Unsafe unsafe() {
        return unsafe;
    }

    public static int pageSize() {
        return Bits.pageSize();
    }

    public static int pageCount(long size) {
        return Bits.pageCount(size);
    }

    public static boolean unaligned() {
        return Bits.unaligned();
    }

    public static void reserveMemory(long size, int cap) {
        Bits.reserveMemory(size, cap);
    }

    public static void unreserveMemory(long size, int cap) {
        Bits.unreserveMemory(size, cap);
    }

    public static final int JNI_COPY_TO_ARRAY_THRESHOLD = Bits.JNI_COPY_TO_ARRAY_THRESHOLD;
    public static final int JNI_COPY_FROM_ARRAY_THRESHOLD = Bits.JNI_COPY_FROM_ARRAY_THRESHOLD;

    public static final long UNSAFE_COPY_THRESHOLD = Bits.UNSAFE_COPY_THRESHOLD;

    public static void copyFromArray(Object src, long srcBaseOffset, long srcPos, long dstAddr, long length) {
        Bits.copyFromArray(src, srcBaseOffset, srcPos, dstAddr, length);
    }

    public static void copyToArray(long srcAddr, Object dst, long dstBaseOffset, long dstPos, long length) {
        Bits.copyToArray(srcAddr, dst, dstBaseOffset, dstPos, length);
    }

    public static void copyFromCharArray(Object src, long srcPos, long dstAddr, long length) {
        Bits.copyFromCharArray(src, srcPos, dstAddr, length);
    }

    public static void copyToCharArray(long srcAddr, Object dst, long dstPos, long length) {
        Bits.copyToCharArray(srcAddr, dst, dstPos, length);
    }

    public static void copyFromShortArray(Object src, long srcPos, long dstAddr, long length) {
        Bits.copyFromShortArray(src, srcPos, dstAddr, length);
    }

    public static void copyToShortArray(long srcAddr, Object dst, long dstPos, long length) {
        Bits.copyToShortArray(srcAddr, dst, dstPos, length);
    }

    public static void copyFromIntArray(Object src, long srcPos, long dstAddr, long length) {
        Bits.copyFromIntArray(src, srcPos, dstAddr, length);
    }

    public static void copyToIntArray(long srcAddr, Object dst, long dstPos, long length) {
        Bits.copyToIntArray(srcAddr, dst, dstPos, length);
    }

    public static void copyFromLongArray(Object src, long srcPos, long dstAddr, long length) {
        Bits.copyFromLongArray(src, srcPos, dstAddr, length);
    }

    public static void copyToLongArray(long srcAddr, Object dst, long dstPos, long length) {
        Bits.copyToLongArray(srcAddr, dst, dstPos, length);
    }

}
