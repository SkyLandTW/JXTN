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
import java.lang.reflect.Method;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import sun.misc.Unsafe;
import sun.nio.ch.FileChannelImpl;

/**
 * Direct mmap() support for over 4GB mapping
 * <p>
 * source: http://nyeggen.com/post/2014-05-18-memory-mapping-%3E2gb-of-data-in-java/
 * </p>
 * <p>
 * TODO: investigate associated concerns by fixed memory mapping, cleanup code, fixes etc in Java source.
 * </p>
 *
 * @author aqd
 */
public final class FileMapping implements Closeable {

    private static final Unsafe unsafe = Memory.unsafe;
    private static final Method channelMap0;
    private static final Method channelUnmap0;

    static {
        try {
            channelMap0 = FileChannelImpl.class.getDeclaredMethod("map0", int.class, long.class, long.class);
            channelMap0.setAccessible(true);
            channelUnmap0 = FileChannelImpl.class.getDeclaredMethod("unmap0", long.class, long.class);
            channelUnmap0.setAccessible(true);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    private static long roundTo4096(long i) {
        return (i + 0xfffL) & ~0xfffL;
    }

    private final String source;
    private final boolean fromChannel;
    private final long address;
    private final long length;

    private long pointer;
    private boolean closed;

    public FileMapping(FileChannel channel, long length, boolean canWrite) {
        this.source = Channels.toString(channel);
        this.fromChannel = true;
        int imode = canWrite ? 1 : 0;
        try {
            this.address = (long) channelMap0.invoke(channel, imode, 0L, roundTo4096(length));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
        this.pointer = this.address;
        this.length = length;
    }

    public FileMapping(Path path, int prot, int flags) throws IOException {
        this.source = path.toString();
        this.fromChannel = false;
        int rw = 0;
        if ((prot & NativeMMap.PROT_READ) != 0 || (prot & NativeMMap.PROT_EXEC) != 0) {
            if ((prot & NativeMMap.PROT_WRITE) != 0) {
                rw = NativeFiles.O_RDWR;
            } else {
                rw = NativeFiles.O_RDONLY;
            }
        } else if ((prot & NativeMMap.PROT_WRITE) != 0) {
            rw = NativeFiles.O_WRONLY;
        }
        int fd = NativeFiles.open(path, rw, 0);
        if (fd == -1) {
            throw new IOException("open " + path + ": " + NativeErrno.errName());
        }
        try {
            Out<Stat64> ostat = new Out<>();
            if (NativeStat.fstat(fd, ostat) == -1) {
                throw new IOException("stat " + path + ": " + NativeErrno.errName());
            }
            this.length = ostat.get().st_size;
            this.address = NativeMMap.mmap(0L, this.length, prot, flags, fd, 0L);
            if (this.address == NativeMMap.MAP_FAILED) {
                throw new IOException("mmap " + path + ": " + NativeErrno.errName());
            }
        } finally {
            NativeIO.close(fd);
        }
        this.pointer = this.address;
    }

    public FileMapping(int fd, long offset, long length, int prot, int flags) throws IOException {
        this.source = "fd=" + fd;
        this.fromChannel = false;
        this.address = NativeMMap.mmap(0L, length, prot, flags, fd, offset);
        if (this.address == NativeMMap.MAP_FAILED) {
            throw new IOException("mmap " + fd + ": " + NativeErrno.errName());
        }
        this.pointer = this.address;
        this.length = length;
    }

    public FileMapping(long address, long length) {
        this.source = "addr=0x" + StringFormat.padLeft(Long.toUnsignedString(address, 16), 16, '0')
                + "+" + length;
        this.fromChannel = false;
        this.address = address;
        this.pointer = address;
        this.length = length;
    }

    public boolean hasRemaining() {
        return (this.pointer - this.address) < this.length;
    }

    public long length() {
        return this.length;
    }

    public void move(long relativePosition) {
        this.pointer += relativePosition;
    }

    public long position() {
        return this.pointer - this.address;
    }

    public void position(long absolutePosition) {
        this.pointer = this.address + absolutePosition;
    }

    public byte getByte() {
        byte value = unsafe.getByte(this.pointer);
        this.pointer += 1;
        return value;
    }

    public short getShort() {
        short value = unsafe.getShort(this.pointer);
        this.pointer += 2;
        return value;
    }

    public int getInt() {
        int value = unsafe.getInt(this.pointer);
        this.pointer += 4;
        return value;
    }

    public long getLong() {
        long value = unsafe.getLong(this.pointer);
        this.pointer += 8;
        return value;
    }

    public short getUByte() {
        byte value = unsafe.getByte(this.pointer);
        this.pointer += 1;
        return (short) (value & 0xFF);
    }

    public int getUShort() {
        short value = unsafe.getShort(this.pointer);
        this.pointer += 2;
        return value & 0xFFFF;
    }

    public long getUInt() {
        int value = unsafe.getInt(this.pointer);
        this.pointer += 4;
        return value & 0xFFFFFFFFL;
    }

    public void getData(byte[] data) {
        unsafe.copyMemory(null, this.pointer, data, Unsafe.ARRAY_BYTE_BASE_OFFSET, data.length);
        this.pointer += data.length;
    }

    public byte getByteAt(long offset) {
        return unsafe.getByte(this.pointer + offset);
    }

    public short getShortAt(long offset) {
        return unsafe.getShort(this.pointer + offset);
    }

    public int getIntAt(long offset) {
        return unsafe.getInt(this.pointer + offset);
    }

    public long getLongAt(long offset) {
        return unsafe.getLong(this.pointer + offset);
    }

    public short getUByteAt(long offset) {
        return (short) (unsafe.getByte(this.pointer + offset) & 0xFF);
    }

    public int getUShortAt(long offset) {
        return unsafe.getShort(this.pointer + offset) & 0xFFFF;
    }

    public long getUIntAt(long offset) {
        return unsafe.getInt(this.pointer + offset) & 0xFFFFFFFFL;
    }

    public void getDataAt(long offset, byte[] data) {
        unsafe.copyMemory(null, this.pointer + offset, data, Unsafe.ARRAY_BYTE_BASE_OFFSET, data.length);
    }

    public void putByte(byte val) {
        unsafe.putByte(this.pointer, val);
        this.pointer += 1;
    }

    public void putShort(short val) {
        unsafe.putShort(this.pointer, val);
        this.pointer += 2;
    }

    public void putInt(int val) {
        unsafe.putInt(this.pointer, val);
        this.pointer += 4;
    }

    public void putLong(long val) {
        unsafe.putLong(this.pointer, val);
        this.pointer += 8;
    }

    public void putData(byte[] data) {
        unsafe.copyMemory(data, Unsafe.ARRAY_BYTE_BASE_OFFSET, null, this.pointer, data.length);
        this.pointer += data.length;
    }

    public void putByteAt(long offset, byte val) {
        unsafe.putByte(this.pointer + offset, val);
    }

    public void putShortAt(long offset, short val) {
        unsafe.putShort(this.pointer + offset, val);
    }

    public void putIntAt(long offset, int val) {
        unsafe.putInt(this.pointer + offset, val);
    }

    public void putLongAt(long offset, long val) {
        unsafe.putLong(this.pointer + offset, val);
    }

    public void putDataAt(long offset, byte[] data) {
        unsafe.copyMemory(data, Unsafe.ARRAY_BYTE_BASE_OFFSET, null, this.pointer + offset, data.length);
    }

    @Override
    public void close() {
        if (this.closed) {
            return;
        }
        try {
            if (this.fromChannel) {
                try {
                    channelUnmap0.invoke(null, this.address, roundTo4096(this.length));
                } catch (ReflectiveOperationException e) {
                    e.printStackTrace();
                }
            } else {
                if (NativeMMap.munmap(this.address, this.length) == -1) {
                    System.err.println("munmap " + this.source + ": " + NativeErrno.errName());
                }
            }
        } finally {
            this.closed = true;
        }
    }

    @Override
    public String toString() {
        return "mmap_" + this.source;
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }
}
