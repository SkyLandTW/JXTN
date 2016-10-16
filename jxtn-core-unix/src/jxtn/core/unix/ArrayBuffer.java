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
package jxtn.core.unix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Objects;

/**
 * Wrapper of a single Java array
 *
 * @author aqd
 */
public class ArrayBuffer implements LargeBuffer {

    private final byte[] source;
    private final int base;
    private final int length;

    private int pointer;
    private int limit;
    private boolean swapped;

    public ArrayBuffer(byte[] source, int offset, int length) {
        if (offset < 0 || offset > source.length) {
            throw new IllegalArgumentException("offset=" + offset);
        }
        if (length < 0 || offset + length > source.length) {
            throw new IllegalArgumentException("length=" + length);
        }
        this.source = Objects.requireNonNull(source);
        this.base = offset;
        this.length = length;
        //
        this.pointer = offset;
        this.limit = offset + length;
    }

    @Override
    public final ByteOrder order() {
        if (this.swapped) {
            return ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN
                    ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
        } else {
            return ByteOrder.nativeOrder();
        }
    }

    @Override
    public final void order(ByteOrder newOrder) {
        this.swapped = newOrder != ByteOrder.nativeOrder();
    }

    @Override
    public final boolean hasRemaining() {
        return this.limit > this.pointer;
    }

    @Override
    public final long remaining() {
        return this.limit - this.pointer;
    }

    @Override
    public final long length() {
        return this.length;
    }

    @Override
    public final void move(long relativePosition) {
        this.pointer += (int) relativePosition;
    }

    @Override
    public final long position() {
        return this.pointer - this.base;
    }

    @Override
    public final void position(long absolutePosition) {
        this.pointer = this.base + (int) absolutePosition;
    }

    @Override
    public final boolean getBoolean() {
        boolean value = getBoolean(this.source, this.pointer);
        this.pointer += 1;
        return value;
    }

    @Override
    public final byte getByte() {
        byte value = this.source[this.pointer];
        this.pointer += 1;
        return value;
    }

    @Override
    public final short getShort() {
        short value = getShort(this.source, this.pointer);
        this.pointer += 2;
        return this.swapped ? Short.reverseBytes(value) : value;
    }

    @Override
    public final int getInt() {
        int value = getInt(this.source, this.pointer);
        this.pointer += 4;
        return this.swapped ? Integer.reverseBytes(value) : value;
    }

    @Override
    public final long getLong() {
        long value = getLong(this.source, this.pointer);
        this.pointer += 8;
        return this.swapped ? Long.reverseBytes(value) : value;
    }

    @Override
    public final float getFloat() {
        float value = this.swapped
                ? Float.intBitsToFloat(Integer.reverseBytes(getInt(this.source, this.pointer)))
                : getFloat(this.source, this.pointer);
        this.pointer += 4;
        return value;
    }

    @Override
    public final double getDouble() {
        double value = this.swapped
                ? Double.longBitsToDouble(Long.reverseBytes(getLong(this.source, this.pointer)))
                : getDouble(this.source, this.pointer);
        this.pointer += 8;
        return value;
    }

    @Override
    public final short getUByte() {
        byte value = this.source[this.pointer];
        this.pointer += 1;
        return (short) (value & 0xFF);
    }

    @Override
    public final int getUShort() {
        short value = getShort(this.source, this.pointer);
        this.pointer += 2;
        return (this.swapped ? Short.reverseBytes(value) : value) & 0xFFFF;
    }

    @Override
    public final long getUInt() {
        int value = getInt(this.source, this.pointer);
        this.pointer += 4;
        return (this.swapped ? Integer.reverseBytes(value) : value) & 0xFFFFFFFFL;
    }

    @Override
    public final void getData(byte[] data) {
        System.arraycopy(this.source, this.pointer, data, 0, data.length);
        this.pointer += data.length;
    }

    @Override
    public final boolean getBooleanAt(long offset) {
        return getBoolean(this.source, this.pointer + (int) offset);
    }

    @Override
    public final byte getByteAt(long offset) {
        return this.source[this.pointer + (int) offset];
    }

    @Override
    public final short getShortAt(long offset) {
        short value = getShort(this.source, this.pointer + (int) offset);
        return this.swapped ? Short.reverseBytes(value) : value;
    }

    @Override
    public final int getIntAt(long offset) {
        int value = getInt(this.source, this.pointer + (int) offset);
        return this.swapped ? Integer.reverseBytes(value) : value;
    }

    @Override
    public final long getLongAt(long offset) {
        long value = getLong(this.source, this.pointer + (int) offset);
        return this.swapped ? Long.reverseBytes(value) : value;
    }

    @Override
    public final float getFloatAt(long offset) {
        return this.swapped
                ? Float.intBitsToFloat(Integer.reverseBytes(getInt(this.source, this.pointer + (int) offset)))
                : getFloat(this.source, this.pointer + (int) offset);
    }

    @Override
    public final double getDoubleAt(long offset) {
        return this.swapped
                ? Double.longBitsToDouble(Long.reverseBytes(getLong(this.source, this.pointer + (int) offset)))
                : getDouble(this.source, this.pointer + (int) offset);
    }

    @Override
    public final short getUByteAt(long offset) {
        return (short) (this.source[this.pointer + (int) offset] & 0xFF);
    }

    @Override
    public final int getUShortAt(long offset) {
        short value = getShort(this.source, this.pointer + (int) offset);
        return (this.swapped ? Short.reverseBytes(value) : value) & 0xFFFF;
    }

    @Override
    public final long getUIntAt(long offset) {
        int value = getInt(this.source, this.pointer + (int) offset);
        return (this.swapped ? Integer.reverseBytes(value) : value) & 0xFFFFFFFFL;
    }

    @Override
    public final void getDataAt(long offset, byte[] data) {
        System.arraycopy(this.source, this.pointer + (int) offset, data, 0, data.length);
    }

    @Override
    public final void putBoolean(boolean val) {
        putBoolean(this.source, this.pointer, val);
        this.pointer += 1;
    }

    @Override
    public final void putByte(byte val) {
        this.source[this.pointer] = val;
        this.pointer += 1;
    }

    @Override
    public final void putShort(short val) {
        putShort(this.source, this.pointer, this.swapped ? Short.reverseBytes(val) : val);
        this.pointer += 2;
    }

    @Override
    public final void putInt(int val) {
        putInt(this.source, this.pointer, this.swapped ? Integer.reverseBytes(val) : val);
        this.pointer += 4;
    }

    @Override
    public final void putLong(long val) {
        putLong(this.source, this.pointer, this.swapped ? Long.reverseBytes(val) : val);
        this.pointer += 8;
    }

    @Override
    public final void putFloat(float val) {
        if (this.swapped) {
            putInt(this.source, this.pointer, Integer.reverseBytes(Float.floatToIntBits(val)));
        } else {
            putFloat(this.source, this.pointer, val);
        }
        this.pointer += 4;
    }

    @Override
    public final void putDouble(double val) {
        if (this.swapped) {
            putLong(this.source, this.pointer, Long.reverseBytes(Double.doubleToLongBits(val)));
        } else {
            putDouble(this.source, this.pointer, val);
        }
        this.pointer += 8;
    }

    @Override
    public final void putData(byte[] data) {
        System.arraycopy(data, 0, this.source, this.pointer, data.length);
        this.pointer += data.length;
    }

    @Override
    public final void putBooleanAt(long offset, boolean val) {
        putBoolean(this.source, this.pointer + (int) offset, val);
    }

    @Override
    public final void putByteAt(long offset, byte val) {
        this.source[this.pointer + (int) offset] = val;
    }

    @Override
    public final void putShortAt(long offset, short val) {
        putShort(this.source, this.pointer + (int) offset, this.swapped ? Short.reverseBytes(val) : val);
    }

    @Override
    public final void putIntAt(long offset, int val) {
        putInt(this.source, this.pointer + (int) offset, this.swapped ? Integer.reverseBytes(val) : val);
    }

    @Override
    public final void putLongAt(long offset, long val) {
        putLong(this.source, this.pointer + (int) offset, this.swapped ? Long.reverseBytes(val) : val);
    }

    @Override
    public final void putFloatAt(long offset, float val) {
        if (this.swapped) {
            putInt(this.source, this.pointer + (int) offset, Integer.reverseBytes(Float.floatToIntBits(val)));
        } else {
            putFloat(this.source, this.pointer + (int) offset, val);
        }
    }

    @Override
    public final void putDoubleAt(long offset, double val) {
        if (this.swapped) {
            putLong(this.source, this.pointer + (int) offset, Long.reverseBytes(Double.doubleToLongBits(val)));
        } else {
            putDouble(this.source, this.pointer + (int) offset, val);
        }
    }

    @Override
    public final void putDataAt(long offset, byte[] data) {
        System.arraycopy(data, 0, this.source, this.pointer + (int) offset, data.length);
    }

    @Override
    public final Long find(long limit, byte[] needle) {
        int index = indexOf(this.source, this.pointer, (int) limit, needle, 0, needle.length);
        if (index < 0) {
            return null;
        } else {
            return (long) (index - this.pointer);
        }
    }

    @Override
    public final Long find(long offset, long limit, byte[] needle) {
        int index = indexOf(this.source, this.pointer + (int) offset, (int) limit, needle, 0, needle.length);
        if (index < 0) {
            return null;
        } else {
            return (long) (index - this.pointer);
        }
    }

    public final ByteBuffer toByteBuffer() {
        ByteBuffer buffer = ByteBuffer.wrap(this.source, this.base, this.length);
        buffer.position((int) this.position());
        buffer.order(this.order());
        return buffer;
    }

    private static boolean getBoolean(byte[] b, int off) {
        return b[off] != 0;
    }

    private static short getShort(byte[] b, int off) {
        return (short) ((b[off + 0] & 0xFF) |
                (b[off + 1] << 8));
    }

    private static int getInt(byte[] b, int off) {
        return ((b[off + 0] & 0xFF)) |
                ((b[off + 1] & 0xFF) << 8) |
                ((b[off + 2] & 0xFF) << 16) |
                ((b[off + 3]) << 24);
    }

    private static float getFloat(byte[] b, int off) {
        return Float.intBitsToFloat(getInt(b, off));
    }

    private static long getLong(byte[] b, int off) {
        return ((b[off + 0] & 0xFFL)) |
                ((b[off + 1] & 0xFFL) << 8) |
                ((b[off + 2] & 0xFFL) << 16) |
                ((b[off + 3] & 0xFFL) << 24) |
                ((b[off + 4] & 0xFFL) << 32) |
                ((b[off + 5] & 0xFFL) << 40) |
                ((b[off + 6] & 0xFFL) << 48) |
                (((long) b[off + 7]) << 56);
    }

    private static double getDouble(byte[] b, int off) {
        return Double.longBitsToDouble(getLong(b, off));
    }

    private static void putBoolean(byte[] b, int off, boolean val) {
        b[off] = (byte) (val ? 1 : 0);
    }

    private static void putShort(byte[] b, int off, short val) {
        b[off + 1] = (byte) (val);
        b[off] = (byte) (val >>> 8);
    }

    private static void putInt(byte[] b, int off, int val) {
        b[off + 0] = (byte) (val);
        b[off + 1] = (byte) (val >>> 8);
        b[off + 2] = (byte) (val >>> 16);
        b[off + 3] = (byte) (val >>> 24);
    }

    private static void putFloat(byte[] b, int off, float val) {
        putInt(b, off, Float.floatToIntBits(val));
    }

    private static void putLong(byte[] b, int off, long val) {
        b[off + 0] = (byte) (val);
        b[off + 1] = (byte) (val >>> 8);
        b[off + 2] = (byte) (val >>> 16);
        b[off + 3] = (byte) (val >>> 24);
        b[off + 4] = (byte) (val >>> 32);
        b[off + 5] = (byte) (val >>> 40);
        b[off + 6] = (byte) (val >>> 48);
        b[off + 7] = (byte) (val >>> 56);
    }

    private static void putDouble(byte[] b, int off, double val) {
        putLong(b, off, Double.doubleToLongBits(val));
    }

    private static int indexOf(
            byte[] source, int sourceOffset, int sourceCount,
            byte[] target, int targetOffset, int targetCount) {
        if (targetCount == 0) {
            return sourceOffset;
        }
        byte first = target[targetOffset];
        int max = sourceOffset + (sourceCount - targetCount);
        for (int i = sourceOffset; i <= max; i++) {
            if (source[i] != first) {
                while (++i <= max && source[i] != first) {
                }
            }
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && source[j] == target[k]; j++, k++) {
                }
                if (j == end) {
                    return i;
                }
            }
        }
        return -1;
    }
}
