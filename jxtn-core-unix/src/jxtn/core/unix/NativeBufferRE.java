package jxtn.core.unix;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteOrder;
import sun.misc.Unsafe;

public final class NativeBufferRE implements NativeBuffer {

    private static final Unsafe unsafe = Memory.unsafe;
    private static final ByteOrder order = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN
            ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN;

    private final Closeable source;
    private final long address;
    private final long length;

    private long pointer;
    private boolean closed;

    public NativeBufferRE(Closeable source, long address, long length) {
        this.source = source;
        this.address = address;
        this.length = length;
    }

    @Override
    public ByteOrder order() {
        return order;
    }

    @Override
    public boolean hasRemaining() {
        return (this.pointer - this.address) < this.length;
    }

    @Override
    public long length() {
        return this.length;
    }

    @Override
    public void move(long relativePosition) {
        this.pointer += relativePosition;
    }

    @Override
    public long position() {
        return this.pointer - this.address;
    }

    @Override
    public void position(long absolutePosition) {
        this.pointer = this.address + absolutePosition;
    }

    @Override
    public byte getByte() {
        byte value = unsafe.getByte(this.pointer);
        this.pointer += 1;
        return value;
    }

    @Override
    public short getShort() {
        short value = Short.reverseBytes(unsafe.getShort(this.pointer));
        this.pointer += 2;
        return value;
    }

    @Override
    public int getInt() {
        int value = Integer.reverseBytes(unsafe.getInt(this.pointer));
        this.pointer += 4;
        return value;
    }

    @Override
    public long getLong() {
        long value = Long.reverseBytes(unsafe.getLong(this.pointer));
        this.pointer += 8;
        return value;
    }

    @Override
    public short getUByte() {
        byte value = unsafe.getByte(this.pointer);
        this.pointer += 1;
        return (short) (value & 0xFF);
    }

    @Override
    public int getUShort() {
        short value = Short.reverseBytes(unsafe.getShort(this.pointer));
        this.pointer += 2;
        return value & 0xFFFF;
    }

    @Override
    public long getUInt() {
        int value = Integer.reverseBytes(unsafe.getInt(this.pointer));
        this.pointer += 4;
        return value & 0xFFFFFFFFL;
    }

    @Override
    public void getData(byte[] data) {
        unsafe.copyMemory(null, this.pointer, data, Unsafe.ARRAY_BYTE_BASE_OFFSET, data.length);
        this.pointer += data.length;
    }

    @Override
    public byte getByteAt(long offset) {
        return unsafe.getByte(this.pointer + offset);
    }

    @Override
    public short getShortAt(long offset) {
        return Short.reverseBytes(unsafe.getShort(this.pointer + offset));
    }

    @Override
    public int getIntAt(long offset) {
        return Integer.reverseBytes(unsafe.getInt(this.pointer + offset));
    }

    @Override
    public long getLongAt(long offset) {
        return Long.reverseBytes(unsafe.getLong(this.pointer + offset));
    }

    @Override
    public short getUByteAt(long offset) {
        return (short) (unsafe.getByte(this.pointer + offset) & 0xFF);
    }

    @Override
    public int getUShortAt(long offset) {
        return Short.reverseBytes(unsafe.getShort(this.pointer + offset)) & 0xFFFF;
    }

    @Override
    public long getUIntAt(long offset) {
        return Integer.reverseBytes(unsafe.getInt(this.pointer + offset)) & 0xFFFFFFFFL;
    }

    @Override
    public void getDataAt(long offset, byte[] data) {
        unsafe.copyMemory(null, this.pointer + offset, data, Unsafe.ARRAY_BYTE_BASE_OFFSET, data.length);
    }

    @Override
    public void putByte(byte val) {
        unsafe.putByte(this.pointer, val);
        this.pointer += 1;
    }

    @Override
    public void putShort(short val) {
        unsafe.putShort(this.pointer, Short.reverseBytes(val));
        this.pointer += 2;
    }

    @Override
    public void putInt(int val) {
        unsafe.putInt(this.pointer, Integer.reverseBytes(val));
        this.pointer += 4;
    }

    @Override
    public void putLong(long val) {
        unsafe.putLong(this.pointer, Long.reverseBytes(val));
        this.pointer += 8;
    }

    @Override
    public void putData(byte[] data) {
        unsafe.copyMemory(data, Unsafe.ARRAY_BYTE_BASE_OFFSET, null, this.pointer, data.length);
        this.pointer += data.length;
    }

    @Override
    public void putByteAt(long offset, byte val) {
        unsafe.putByte(this.pointer + offset, val);
    }

    @Override
    public void putShortAt(long offset, short val) {
        unsafe.putShort(this.pointer + offset, Short.reverseBytes(val));
    }

    @Override
    public void putIntAt(long offset, int val) {
        unsafe.putInt(this.pointer + offset, Integer.reverseBytes(val));
    }

    @Override
    public void putLongAt(long offset, long val) {
        unsafe.putLong(this.pointer + offset, Long.reverseBytes(val));
    }

    @Override
    public void putDataAt(long offset, byte[] data) {
        unsafe.copyMemory(data, Unsafe.ARRAY_BYTE_BASE_OFFSET, null, this.pointer + offset, data.length);
    }

    @Override
    public void close() {
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
    public String toString() {
        return "nbuf_" + this.source;
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }
}
