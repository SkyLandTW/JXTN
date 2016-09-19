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

    public static long pread(int fd, byte[] buf_array, int buf_offset, int count, long offset) {
        return pread(fd, (Object) buf_array, Unsafe.ARRAY_BYTE_BASE_OFFSET + buf_offset, count, offset);
    }

    private static native long pread(int fd, Object buf_base, long buf_offset, long count, long offset);

    /* preadv */

    public static long preadv_arrays(int fd, List<byte[]> iovecs, long offset) {
        IOVecs iov = IOVecs.fromArrays(iovecs);
        return iov.end(preadv(fd, iov.iov_bases, iov.iov_offs, iov.iov_lens, offset));
    }

    public static long preadv_buffers(int fd, List<ByteBuffer> iovecs, long offset) {
        IOVecs iov = IOVecs.fromBuffers(iovecs);
        return iov.end(preadv(fd, iov.iov_bases, iov.iov_offs, iov.iov_lens, offset));
    }

    private static native long preadv(int fd, byte[][] iov_bases, int[] iov_offs, long[] iov_lens, long offset);

    /* pwrite */

    public static long pwrite(int fd, ByteBuffer buf, long offset) {
        return rBuffer(buf, pwrite(fd, buf.array(), buf.arrayOffset() + buf.position(), buf.remaining(), offset));
    }

    public static long pwrite(int fd, byte[] buf_array, long offset) {
        return pwrite(fd, (Object) buf_array, Unsafe.ARRAY_BYTE_BASE_OFFSET, buf_array.length, offset);
    }

    public static long pwrite(int fd, byte[] buf_array, int buf_offset, int count, long offset) {
        return pwrite(fd, (Object) buf_array, Unsafe.ARRAY_BYTE_BASE_OFFSET + buf_offset, count, offset);
    }

    private static native long pwrite(int fd, Object buf_base, long buf_offset, long count, long offset);

    /* pwritev */

    public static long pwritev_arrays(int fd, List<byte[]> iovecs, long offset) {
        IOVecs iov = IOVecs.fromArrays(iovecs);
        return iov.end(pwritev(fd, iov.iov_bases, iov.iov_offs, iov.iov_lens, offset));
    }

    public static long pwritev_buffers(int fd, List<ByteBuffer> iovecs, long offset) {
        IOVecs iov = IOVecs.fromBuffers(iovecs);
        return iov.end(pwritev(fd, iov.iov_bases, iov.iov_offs, iov.iov_lens, offset));
    }

    private static native long pwritev(int fd, byte[][] iov_bases, int[] iov_offs, long[] iov_lens, long offset);

    /* read */

    public static long read(int fd, ByteBuffer buf) {
        return rBuffer(buf, read(fd, buf.array(), buf.arrayOffset() + buf.position(), buf.remaining()));
    }

    public static long read(int fd, byte[] buf_array) {
        return read(fd, (Object) buf_array, Unsafe.ARRAY_BYTE_BASE_OFFSET, buf_array.length);
    }

    public static long read(int fd, byte[] buf_array, int buf_offset, int count) {
        return read(fd, (Object) buf_array, Unsafe.ARRAY_BYTE_BASE_OFFSET + buf_offset, count);
    }

    private static native long read(int fd, Object buf_base, long buf_offset, long count);

    /* readv */

    public static long readv_arrays(int fd, List<byte[]> iovecs) {
        IOVecs iov = IOVecs.fromArrays(iovecs);
        return iov.end(readv(fd, iov.iov_bases, iov.iov_offs, iov.iov_lens));
    }

    public static long readv_buffers(int fd, List<ByteBuffer> iovecs) {
        IOVecs iov = IOVecs.fromBuffers(iovecs);
        return iov.end(readv(fd, iov.iov_bases, iov.iov_offs, iov.iov_lens));
    }

    private static native long readv(int fd, byte[][] iov_bases, int[] iov_offs, long[] iov_lens);

    /* sendfile */

    public static native long sendfile(int out_fd, int in_fd, long offset, long count);

    /* write */

    public static long write(int fd, ByteBuffer buf) {
        return rBuffer(buf, write(fd, buf.array(), buf.arrayOffset() + buf.position(), buf.remaining()));
    }

    public static long write(int fd, byte[] buf_array) {
        return write(fd, (Object) buf_array, Unsafe.ARRAY_BYTE_BASE_OFFSET, buf_array.length);
    }

    public static long write(int fd, byte[] buf_array, int buf_offset, int count) {
        return write(fd, (Object) buf_array, Unsafe.ARRAY_BYTE_BASE_OFFSET + buf_offset, count);
    }

    private static native long write(int fd, Object buf_base, long buf_offset, long count);

    /* writev */

    public static long writev_arrays(int fd, List<byte[]> iovecs) {
        IOVecs iov = IOVecs.fromArrays(iovecs);
        return iov.end(writev(fd, iov.iov_bases, iov.iov_offs, iov.iov_lens));
    }

    public static long writev_buffers(int fd, List<ByteBuffer> iovecs) {
        IOVecs iov = IOVecs.fromBuffers(iovecs);
        return iov.end(writev(fd, iov.iov_bases, iov.iov_offs, iov.iov_lens));
    }

    private static native long writev(int fd, byte[][] iov_bases, int[] iov_offs, long[] iov_lens);

    private NativeIO() {
    }

    private static final class IOVecs {
        public static IOVecs fromArrays(List<byte[]> arrays) {
            if (arrays.size() > NativeLimits.IOV_MAX) {
                throw new IllegalArgumentException("iovecs too large: " + arrays.size() + ">" + NativeLimits.IOV_MAX);
            }
            byte[][] iov_bases = new byte[arrays.size()][];
            int[] iov_offs = new int[arrays.size()];
            long[] iov_lens = new long[arrays.size()];
            for (int i = 0; i < arrays.size(); i++) {
                byte[] ary = arrays.get(i);
                iov_bases[i] = ary;
                iov_offs[i] = 0;
                iov_lens[i] = ary.length;
            }
            return new IOVecs(iov_bases, iov_offs, iov_lens, null);
        }

        public static IOVecs fromBuffers(List<ByteBuffer> buffers) {
            if (buffers.size() > NativeLimits.IOV_MAX) {
                throw new IllegalArgumentException("iovecs too large: " + buffers.size() + ">" + NativeLimits.IOV_MAX);
            }
            byte[][] iov_bases = new byte[buffers.size()][];
            int[] iov_offs = new int[buffers.size()];
            long[] iov_lens = new long[buffers.size()];
            for (int i = 0; i < buffers.size(); i++) {
                ByteBuffer buf = buffers.get(i);
                iov_bases[i] = buf.array();
                iov_offs[i] = buf.arrayOffset() + buf.position();
                iov_lens[i] = buf.remaining();
            }
            return new IOVecs(iov_bases, iov_offs, iov_lens, buffers);
        }

        public final byte[][] iov_bases;
        public final int[] iov_offs;
        public final long[] iov_lens;

        private final List<ByteBuffer> iovecs_buffer;

        private IOVecs(byte[][] iov_bases, int[] iov_offs, long[] iov_lens, List<ByteBuffer> iovecs_buffer) {
            this.iov_bases = iov_bases;
            this.iov_offs = iov_offs;
            this.iov_lens = iov_lens;
            this.iovecs_buffer = iovecs_buffer;
        }

        public long end(long ret) {
            if (ret == -1L || this.iovecs_buffer == null) {
                return ret;
            }
            long r = ret;
            int i = 0;
            while (r > 0L) {
                ByteBuffer buf = this.iovecs_buffer.get(i);
                int len = (int) Math.min(r, this.iov_lens[i]);
                buf.position(buf.position() + len);
                r -= len;
                i += 1;
            }
            return ret;
        }
    }
}
