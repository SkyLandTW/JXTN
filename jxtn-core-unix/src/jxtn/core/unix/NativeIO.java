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
import java.util.List;
import sun.misc.Unsafe;

/**
 * IO-related syscall wrappers (anything related to fd)
 *
 * @author aqd
 */
public final class NativeIO extends JNIBase {

    /* close */

    public static native int close(int fd);

    /* pread */

    public static long pread(int fd, ByteBuffer buf, long offset) {
        return rBuffer(buf, pread(fd, buf.array(), buf.arrayOffset() + buf.position(), buf.remaining(), offset));
    }

    public static long pread(int fd, byte[] buf_array, long offset) {
        return pread(fd, (Object) buf_array, Unsafe.ARRAY_BYTE_BASE_OFFSET, buf_array.length, offset);
    }

    static long pread(int fd, byte[] buf_array, int buf_offset, int count, long offset) {
        return pread(fd, (Object) buf_array, Unsafe.ARRAY_BYTE_BASE_OFFSET + buf_offset, count, offset);
    }

    static native long pread(int fd, Object buf_base, long buf_offset, long count, long offset);

    /* preadv */

    public static long preadv(int fd, List<ByteBuffer> iovecs, long offset) {
        byte[][] iov_bases = new byte[iovecs.size()][];
        int[] iov_offs = new int[iovecs.size()];
        long[] iov_lens = new long[iovecs.size()];
        for (int i = 0; i < iovecs.size(); i++) {
            ByteBuffer buf = iovecs.get(i);
            iov_bases[i] = buf.array();
            iov_offs[i] = buf.arrayOffset() + buf.position();
            iov_lens[i] = buf.remaining();
        }
        long ret = preadv(fd, iov_bases, iov_offs, iov_lens, offset);
        if (ret != -1L) {
            long r = ret;
            int i = 0;
            while (r >= 0L) {
                ByteBuffer buf = iovecs.get(i);
                int len = (int) Math.min(r, iov_lens[i]);
                buf.position(buf.position() + len);
                r -= len;
                i += 1;
            }
        }
        return ret;
    }

    static native long preadv(int fd, byte[][] iov_bases, int[] iov_offs, long[] iov_lens, long offset);

    /* pwrite */

    public static long pwrite(int fd, ByteBuffer buf, long offset) {
        return rBuffer(buf, pwrite(fd, buf.array(), buf.arrayOffset() + buf.position(), buf.remaining(), offset));
    }

    public static long pwrite(int fd, byte[] buf_array, long offset) {
        return pwrite(fd, (Object) buf_array, Unsafe.ARRAY_BYTE_BASE_OFFSET, buf_array.length, offset);
    }

    static long pwrite(int fd, byte[] buf_array, int buf_offset, int count, long offset) {
        return pwrite(fd, (Object) buf_array, Unsafe.ARRAY_BYTE_BASE_OFFSET + buf_offset, count, offset);
    }

    static native long pwrite(int fd, Object buf_base, long buf_offset, long count, long offset);

    /* pwritev */

    public static long pwritev(int fd, List<ByteBuffer> iovecs, long offset) {
        byte[][] iov_bases = new byte[iovecs.size()][];
        int[] iov_offs = new int[iovecs.size()];
        long[] iov_lens = new long[iovecs.size()];
        for (int i = 0; i < iovecs.size(); i++) {
            ByteBuffer buf = iovecs.get(i);
            iov_bases[i] = buf.array();
            iov_offs[i] = buf.arrayOffset() + buf.position();
            iov_lens[i] = buf.remaining();
        }
        long ret = pwritev(fd, iov_bases, iov_offs, iov_lens, offset);
        if (ret != -1L) {
            long r = ret;
            int i = 0;
            while (r >= 0L) {
                ByteBuffer buf = iovecs.get(i);
                int len = (int) Math.min(r, iov_lens[i]);
                buf.position(buf.position() + len);
                r -= len;
                i += 1;
            }
        }
        return ret;
    }

    static native long pwritev(int fd, byte[][] iov_bases, int[] iov_offs, long[] iov_lens, long offset);

    /* read */

    public static long read(int fd, ByteBuffer buf) {
        return rBuffer(buf, read(fd, buf.array(), buf.arrayOffset() + buf.position(), buf.remaining()));
    }

    public static long read(int fd, byte[] buf_array) {
        return read(fd, (Object) buf_array, Unsafe.ARRAY_BYTE_BASE_OFFSET, buf_array.length);
    }

    static long read(int fd, byte[] buf_array, int buf_offset, int count) {
        return read(fd, (Object) buf_array, Unsafe.ARRAY_BYTE_BASE_OFFSET + buf_offset, count);
    }

    static native long read(int fd, Object buf_base, long buf_offset, long count);

    /* sendfile */

    public static native long sendfile(int out_fd, int in_fd, long offset, long count);

    /* write */

    public static long write(int fd, ByteBuffer buf) {
        return rBuffer(buf, write(fd, buf.array(), buf.arrayOffset() + buf.position(), buf.remaining()));
    }

    public static long write(int fd, byte[] buf_array) {
        return write(fd, (Object) buf_array, Unsafe.ARRAY_BYTE_BASE_OFFSET, buf_array.length);
    }

    static long write(int fd, byte[] buf_array, int buf_offset, int count) {
        return write(fd, (Object) buf_array, Unsafe.ARRAY_BYTE_BASE_OFFSET + buf_offset, count);
    }

    static native long write(int fd, Object buf_base, long buf_offset, long count);

    private NativeIO() {
    }
}
