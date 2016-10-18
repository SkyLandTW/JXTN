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
public final class FileMapping {

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

    public static NativeBuffer create(FileChannel channel, long length, boolean canWrite)
            throws IOException {
        int imode = canWrite ? 1 : 0;
        long address;
        try {
            address = (long) channelMap0.invoke(channel, imode, 0L, roundTo4096(length));
        } catch (ReflectiveOperationException e) {
            throw new IOException(e.getCause());
        }
        FileMappingFromChannel source = new FileMappingFromChannel(channel, address, length);
        return wrap(source);
    }

    public static NativeBuffer create(Path path, int prot, int flags)
            throws IOException {
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
            throw new IOException(NativeErrno.errName());
        }
        long address, length;
        try {
            length = NativeFiles.lseek(fd, 0, NativeFiles.SEEK_END);
            if (length == -1L) {
                throw new IOException(NativeErrno.errName());
            }
            address = NativeMMap.mmap(0L, length, prot, flags, fd, 0L);
            if (address == NativeMMap.MAP_FAILED) {
                throw new IOException(NativeErrno.errName());
            }
        } finally {
            NativeIO.close(fd);
        }
        FileMappingFromUnix source = new FileMappingFromUnix(path, address, length);
        return wrap(source);
    }

    public static NativeBuffer create(int fd, int prot, int flags)
            throws IOException {
        long length = NativeFiles.lseek(fd, 0, NativeFiles.SEEK_END);
        if (length == -1L) {
            throw new IOException(NativeErrno.errName());
        }
        return create(fd, 0L, length, prot, flags);
    }

    public static NativeBuffer create(int fd, long offset, long length, int prot, int flags)
            throws IOException {
        String name = "fd=" + fd;
        long address = NativeMMap.mmap(0L, length, prot, flags, fd, offset);
        if (address == NativeMMap.MAP_FAILED) {
            throw new IOException(NativeErrno.errName());
        }
        FileMappingFromUnix source = new FileMappingFromUnix(name, address, length);
        return wrap(source);
    }

    public static NativeBuffer tryCreate(Path path, int prot, int flags) {
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
            return null;
        }
        long address, length;
        try {
            length = NativeFiles.lseek(fd, 0, NativeFiles.SEEK_END);
            if (length == -1L) {
                return null;
            }
            address = NativeMMap.mmap(0L, length, prot, flags, fd, 0L);
            if (address == NativeMMap.MAP_FAILED) {
                return null;
            }
        } finally {
            NativeIO.close(fd);
        }
        FileMappingFromUnix source = new FileMappingFromUnix(path, address, length);
        return wrap(source);
    }

    public static NativeBuffer tryCreate(int fd, long offset, long length, int prot, int flags) {
        String name = "fd=" + fd;
        long address = NativeMMap.mmap(0L, length, prot, flags, fd, offset);
        if (address == NativeMMap.MAP_FAILED) {
            return null;
        }
        FileMappingFromUnix source = new FileMappingFromUnix(name, address, length);
        return wrap(source);
    }

    private static NativeBuffer wrap(FileMappingSource source) {
        return new NativeBuffer(source, source.address, source.length);
    }

    private static long roundTo4096(long i) {
        return (i + 0xfffL) & ~0xfffL;
    }

    private static abstract class FileMappingSource implements Closeable {
        protected final long address;
        protected final long length;

        protected FileMappingSource(long address, long length) {
            this.address = address;
            this.length = length;
        }

        @Override
        public abstract void close();

        @Override
        protected void finalize() throws Throwable {
            this.close();
            super.finalize();
        }
    }

    private static final class FileMappingFromChannel extends FileMappingSource {
        private final String name;
        private boolean closed;

        public FileMappingFromChannel(FileChannel channel, long address, long length) {
            super(address, length);
            this.name = Channels.toString(channel);
        }

        @Override
        public void close() {
            if (this.closed) {
                return;
            }
            try {
                channelUnmap0.invoke(null, this.address, roundTo4096(this.length));
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            } finally {
                this.closed = true;
            }
        }

        @Override
        public String toString() {
            return "mmap_" + this.name;
        }
    }

    private static final class FileMappingFromUnix extends FileMappingSource {
        private final Object source;
        private boolean closed;

        public FileMappingFromUnix(Object source, long address, long length) {
            super(address, length);
            this.source = source;
        }

        @Override
        public void close() {
            if (this.closed) {
                return;
            }
            try {
                if (NativeMMap.munmap(this.address, this.length) == -1) {
                    System.err.println("munmap " + this.source + ": " + NativeErrno.errName());
                }
            } finally {
                this.closed = true;
            }
        }

        @Override
        public String toString() {
            return "mmap_" + this.source;
        }
    }

    private FileMapping() {
    }
}
