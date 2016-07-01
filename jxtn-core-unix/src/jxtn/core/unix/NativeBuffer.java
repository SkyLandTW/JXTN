package jxtn.core.unix;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteOrder;
import sun.misc.Unsafe;

public class NativeBuffer implements Closeable {

    private static final Unsafe unsafe = Memory.unsafe;

    private final Closeable source;
    private final long address;
    private final long length;

    private long pointer;
    private boolean swapped;
    private boolean closed;

    public NativeBuffer(Closeable source, long address, long length) {
        this.source = source;
        this.address = address;
        this.length = length;
        //
        this.pointer = address;
        this.swapped = false;
    }

    public final ByteOrder order() {
        if (this.swapped) {
            return ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN
                    ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
        } else {
            return ByteOrder.nativeOrder();
        }
    }

    public final void order(ByteOrder newOrder) {
        this.swapped = newOrder != ByteOrder.nativeOrder();
    }

    public final boolean hasRemaining() {
        return (this.pointer - this.address) < this.length;
    }

    public final long length() {
        return this.length;
    }

    public final void move(long relativePosition) {
        this.pointer += relativePosition;
    }

    public final long position() {
        return this.pointer - this.address;
    }

    public final void position(long absolutePosition) {
        this.pointer = this.address + absolutePosition;
    }

    public final byte getByte() {
        byte value = unsafe.getByte(this.pointer);
        this.pointer += 1;
        return value;
    }

    public final short getShort() {
        short value = unsafe.getShort(this.pointer);
        this.pointer += 2;
        return this.swapped ? Short.reverseBytes(value) : value;
    }

    public final int getInt() {
        int value = unsafe.getInt(this.pointer);
        this.pointer += 4;
        return this.swapped ? Integer.reverseBytes(value) : value;
    }

    public final long getLong() {
        long value = unsafe.getLong(this.pointer);
        this.pointer += 8;
        return this.swapped ? Long.reverseBytes(value) : value;
    }

    public final short getUByte() {
        byte value = unsafe.getByte(this.pointer);
        this.pointer += 1;
        return (short) (value & 0xFF);
    }

    public final int getUShort() {
        short value = unsafe.getShort(this.pointer);
        this.pointer += 2;
        return (this.swapped ? Short.reverseBytes(value) : value) & 0xFFFF;
    }

    public final long getUInt() {
        int value = unsafe.getInt(this.pointer);
        this.pointer += 4;
        return (this.swapped ? Integer.reverseBytes(value) : value) & 0xFFFFFFFFL;
    }

    public final void getData(byte[] data) {
        unsafe.copyMemory(null, this.pointer, data, Unsafe.ARRAY_BYTE_BASE_OFFSET, data.length);
        this.pointer += data.length;
    }

    public final byte getByteAt(long offset) {
        return unsafe.getByte(this.pointer + offset);
    }

    public final short getShortAt(long offset) {
        short value = unsafe.getShort(this.pointer + offset);
        return this.swapped ? Short.reverseBytes(value) : value;
    }

    public final int getIntAt(long offset) {
        int value = unsafe.getInt(this.pointer + offset);
        return this.swapped ? Integer.reverseBytes(value) : value;
    }

    public final long getLongAt(long offset) {
        long value = unsafe.getLong(this.pointer + offset);
        return this.swapped ? Long.reverseBytes(value) : value;
    }

    public final short getUByteAt(long offset) {
        return (short) (unsafe.getByte(this.pointer + offset) & 0xFF);
    }

    public final int getUShortAt(long offset) {
        short value = unsafe.getShort(this.pointer + offset);
        return (this.swapped ? Short.reverseBytes(value) : value) & 0xFFFF;
    }

    public final long getUIntAt(long offset) {
        int value = unsafe.getInt(this.pointer + offset);
        return (this.swapped ? Integer.reverseBytes(value) : value) & 0xFFFFFFFFL;
    }

    public final void getDataAt(long offset, byte[] data) {
        unsafe.copyMemory(null, this.pointer + offset, data, Unsafe.ARRAY_BYTE_BASE_OFFSET, data.length);
    }

    public final void putByte(byte val) {
        unsafe.putByte(this.pointer, val);
        this.pointer += 1;
    }

    public final void putShort(short val) {
        unsafe.putShort(this.pointer, this.swapped ? Short.reverseBytes(val) : val);
        this.pointer += 2;
    }

    public final void putInt(int val) {
        unsafe.putInt(this.pointer, this.swapped ? Integer.reverseBytes(val) : val);
        this.pointer += 4;
    }

    public final void putLong(long val) {
        unsafe.putLong(this.pointer, this.swapped ? Long.reverseBytes(val) : val);
        this.pointer += 8;
    }

    public final void putData(byte[] data) {
        unsafe.copyMemory(data, Unsafe.ARRAY_BYTE_BASE_OFFSET, null, this.pointer, data.length);
        this.pointer += data.length;
    }

    public final void putByteAt(long offset, byte val) {
        unsafe.putByte(this.pointer + offset, val);
    }

    public final void putShortAt(long offset, short val) {
        unsafe.putShort(this.pointer + offset, this.swapped ? Short.reverseBytes(val) : val);
    }

    public final void putIntAt(long offset, int val) {
        unsafe.putInt(this.pointer + offset, this.swapped ? Integer.reverseBytes(val) : val);
    }

    public final void putLongAt(long offset, long val) {
        unsafe.putLong(this.pointer + offset, this.swapped ? Long.reverseBytes(val) : val);
    }

    public final void putDataAt(long offset, byte[] data) {
        unsafe.copyMemory(data, Unsafe.ARRAY_BYTE_BASE_OFFSET, null, this.pointer + offset, data.length);
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
