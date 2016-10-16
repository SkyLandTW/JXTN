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

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteOrder;
import sun.misc.Unsafe;

/**
 * Wrapper of native memory block
 * <p>
 * Caution: No index range check
 * </p>
 *
 * @author aqd
 */
public class NativeBuffer implements Closeable, LargeBuffer {

    private static final Unsafe unsafe = Memory.unsafe;

    private final Closeable source;
    private final long base;
    private final long length;
    private final long limit;

    private long pointer;
    private boolean swapped;
    private boolean closed;

    /**
     * Create a wrapper of native memory block at certain address
     *
     * @param source Source to be used for information and closed on {@link NativeBuffer#close}, may be null
     * @param address Starting address of the memory block (be careful of alignment!)
     * @param length Length of the memory block, starting from {@code address}
     */
    public NativeBuffer(Closeable source, long address, long length) {
        this.source = source;
        this.base = address;
        this.length = length;
        this.limit = address + length;
        //
        this.pointer = address;
        this.swapped = false;
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
        this.pointer += relativePosition;
    }

    @Override
    public final long position() {
        return this.pointer - this.base;
    }

    @Override
    public final void position(long absolutePosition) {
        this.pointer = this.base + absolutePosition;
    }

    @Override
    public final boolean getBoolean() {
        boolean value = unsafe.getByte(this.pointer) != (byte) 0;
        this.pointer += 1;
        return value;
    }

    @Override
    public final byte getByte() {
        byte value = unsafe.getByte(this.pointer);
        this.pointer += 1;
        return value;
    }

    @Override
    public final short getShort() {
        short value = unsafe.getShort(this.pointer);
        this.pointer += 2;
        return this.swapped ? Short.reverseBytes(value) : value;
    }

    @Override
    public final int getInt() {
        int value = unsafe.getInt(this.pointer);
        this.pointer += 4;
        return this.swapped ? Integer.reverseBytes(value) : value;
    }

    @Override
    public final long getLong() {
        long value = unsafe.getLong(this.pointer);
        this.pointer += 8;
        return this.swapped ? Long.reverseBytes(value) : value;
    }

    @Override
    public final float getFloat() {
        float value = this.swapped
                ? Float.intBitsToFloat(Integer.reverseBytes(unsafe.getInt(this.pointer)))
                : unsafe.getFloat(this.pointer);
        this.pointer += 4;
        return value;
    }

    @Override
    public final double getDouble() {
        double value = this.swapped
                ? Double.longBitsToDouble(Long.reverseBytes(unsafe.getLong(this.pointer)))
                : unsafe.getDouble(this.pointer);
        this.pointer += 8;
        return value;
    }

    @Override
    public final short getUByte() {
        byte value = unsafe.getByte(this.pointer);
        this.pointer += 1;
        return (short) (value & 0xFF);
    }

    @Override
    public final int getUShort() {
        short value = unsafe.getShort(this.pointer);
        this.pointer += 2;
        return (this.swapped ? Short.reverseBytes(value) : value) & 0xFFFF;
    }

    @Override
    public final long getUInt() {
        int value = unsafe.getInt(this.pointer);
        this.pointer += 4;
        return (this.swapped ? Integer.reverseBytes(value) : value) & 0xFFFFFFFFL;
    }

    @Override
    public final void getData(byte[] data) {
        unsafe.copyMemory(null, this.pointer, data, Unsafe.ARRAY_BYTE_BASE_OFFSET, data.length);
        this.pointer += data.length;
    }

    @Override
    public final boolean getBooleanAt(long offset) {
        return unsafe.getByte(this.pointer + offset) != (byte) 0;
    }

    @Override
    public final byte getByteAt(long offset) {
        return unsafe.getByte(this.pointer + offset);
    }

    @Override
    public final short getShortAt(long offset) {
        short value = unsafe.getShort(this.pointer + offset);
        return this.swapped ? Short.reverseBytes(value) : value;
    }

    @Override
    public final int getIntAt(long offset) {
        int value = unsafe.getInt(this.pointer + offset);
        return this.swapped ? Integer.reverseBytes(value) : value;
    }

    @Override
    public final long getLongAt(long offset) {
        long value = unsafe.getLong(this.pointer + offset);
        return this.swapped ? Long.reverseBytes(value) : value;
    }

    @Override
    public final float getFloatAt(long offset) {
        return this.swapped
                ? Float.intBitsToFloat(Integer.reverseBytes(unsafe.getInt(this.pointer + offset)))
                : unsafe.getFloat(this.pointer + offset);
    }

    @Override
    public final double getDoubleAt(long offset) {
        return this.swapped
                ? Double.longBitsToDouble(Long.reverseBytes(unsafe.getLong(this.pointer + offset)))
                : unsafe.getDouble(this.pointer + offset);
    }

    @Override
    public final short getUByteAt(long offset) {
        return (short) (unsafe.getByte(this.pointer + offset) & 0xFF);
    }

    @Override
    public final int getUShortAt(long offset) {
        short value = unsafe.getShort(this.pointer + offset);
        return (this.swapped ? Short.reverseBytes(value) : value) & 0xFFFF;
    }

    @Override
    public final long getUIntAt(long offset) {
        int value = unsafe.getInt(this.pointer + offset);
        return (this.swapped ? Integer.reverseBytes(value) : value) & 0xFFFFFFFFL;
    }

    @Override
    public final void getDataAt(long offset, byte[] data) {
        unsafe.copyMemory(null, this.pointer + offset, data, Unsafe.ARRAY_BYTE_BASE_OFFSET, data.length);
    }

    @Override
    public final void putBoolean(boolean val) {
        unsafe.putByte(this.pointer, val ? (byte) 1 : (byte) 0);
        this.pointer += 1;
    }

    @Override
    public final void putByte(byte val) {
        unsafe.putByte(this.pointer, val);
        this.pointer += 1;
    }

    @Override
    public final void putShort(short val) {
        unsafe.putShort(this.pointer, this.swapped ? Short.reverseBytes(val) : val);
        this.pointer += 2;
    }

    @Override
    public final void putInt(int val) {
        unsafe.putInt(this.pointer, this.swapped ? Integer.reverseBytes(val) : val);
        this.pointer += 4;
    }

    @Override
    public final void putLong(long val) {
        unsafe.putLong(this.pointer, this.swapped ? Long.reverseBytes(val) : val);
        this.pointer += 8;
    }

    @Override
    public final void putFloat(float val) {
        if (this.swapped) {
            unsafe.putInt(this.pointer, Integer.reverseBytes(Float.floatToIntBits(val)));
        } else {
            unsafe.putFloat(this.pointer, val);
        }
        this.pointer += 4;
    }

    @Override
    public final void putDouble(double val) {
        if (this.swapped) {
            unsafe.putLong(this.pointer, Long.reverseBytes(Double.doubleToLongBits(val)));
        } else {
            unsafe.putDouble(this.pointer, val);
        }
        this.pointer += 8;
    }

    @Override
    public final void putData(byte[] data) {
        unsafe.copyMemory(data, Unsafe.ARRAY_BYTE_BASE_OFFSET, null, this.pointer, data.length);
        this.pointer += data.length;
    }

    @Override
    public final void putBooleanAt(long offset, boolean val) {
        unsafe.putByte(this.pointer + offset, val ? (byte) 1 : (byte) 0);
    }

    @Override
    public final void putByteAt(long offset, byte val) {
        unsafe.putByte(this.pointer + offset, val);
    }

    @Override
    public final void putShortAt(long offset, short val) {
        unsafe.putShort(this.pointer + offset, this.swapped ? Short.reverseBytes(val) : val);
    }

    @Override
    public final void putIntAt(long offset, int val) {
        unsafe.putInt(this.pointer + offset, this.swapped ? Integer.reverseBytes(val) : val);
    }

    @Override
    public final void putLongAt(long offset, long val) {
        unsafe.putLong(this.pointer + offset, this.swapped ? Long.reverseBytes(val) : val);
    }

    @Override
    public final void putFloatAt(long offset, float val) {
        if (this.swapped) {
            unsafe.putInt(this.pointer + offset, Integer.reverseBytes(Float.floatToIntBits(val)));
        } else {
            unsafe.putFloat(this.pointer + offset, val);
        }
    }

    @Override
    public final void putDoubleAt(long offset, double val) {
        if (this.swapped) {
            unsafe.putLong(this.pointer + offset, Long.reverseBytes(Double.doubleToLongBits(val)));
        } else {
            unsafe.putDouble(this.pointer + offset, val);
        }
    }

    @Override
    public final void putDataAt(long offset, byte[] data) {
        unsafe.copyMemory(data, Unsafe.ARRAY_BYTE_BASE_OFFSET, null, this.pointer + offset, data.length);
    }

    @Override
    public final Long find(long limit, byte[] needle) {
        long address = NativeString.memmem(this.pointer, limit, needle);
        if (address == 0L) {
            return null;
        } else {
            return address - this.pointer;
        }
    }

    @Override
    public final Long find(long offset, long limit, byte[] needle) {
        long address = NativeString.memmem(this.pointer + offset, limit, needle);
        if (address == 0L) {
            return null;
        } else {
            return address - this.pointer;
        }
    }

    @Override
    public final void close() {
        if (this.closed) {
            return;
        }
        try {
            if (this.source != null) {
                this.source.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.closed = true;
        }
    }

    @Override
    public final String toString() {
        return "nbuf_" + this.source;
    }

    @Override
    protected final void finalize() throws Throwable {
        this.close();
        super.finalize();
    }
}
