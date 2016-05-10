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
import java.nio.file.Path;
import sun.misc.Unsafe;
import sun.nio.fs.UnixPath2;

/**
 * Unix system calls
 *
 * @author aqd
 */
public final class Syscall {

    static {
        Runtime.getRuntime().loadLibrary("jxtn-core-unix");
    }

    public static int access(Path pathname, int mode) {
        return access(tPath(pathname), mode);
    }

    public static int access(String pathname, int mode) {
        return access(tPath(pathname), mode);
    }

    public static native int access(byte[] pathname, int mode);

    public static int chdir(Path path) {
        return chdir(tPath(path));
    }

    public static int chdir(String path) {
        return chdir(tPath(path));
    }

    public static native int chdir(byte[] path);

    public static int chown(Path pathname, int owner, int group) {
        return chown(tPath(pathname), owner, group);
    }

    public static int chown(String pathname, int owner, int group) {
        return chown(tPath(pathname), owner, group);
    }

    public static native int chown(byte[] pathname, int owner, int group);

    public static int chroot(Path path) {
        return chroot(tPath(path));
    }

    public static int chroot(String path) {
        return chroot(tPath(path));
    }

    public static native int chroot(byte[] path);

    public static native int close(int fd);

    public static int creat(Path pathname, int mode) {
        return creat(tPath(pathname), mode);
    }

    public static int creat(String pathname, int mode) {
        return creat(tPath(pathname), mode);
    }

    public static native int creat(byte[] pathname, int mode);

    public static native int fallocate(int fd, int mode, long offset, long len);

    public static native int fork();

    public static int fstat(int fd, Out<Stat> outBuf) {
        byte[] buf = new byte[144];
        int ret;
        if ((ret = fstat(fd, buf)) == 0) {
            outBuf.set(new Stat(ByteBuffer.wrap(buf)));
        }
        return ret;
    }

    public static native int fstat(int fd, byte[] buf);

    public static native int geteuid();

    public static native int getpid();

    public static native int getppid();

    public static native int gettid();

    public static native int getuid();

    public static native int kill(int pid, int sig);

    public static int link(Path oldpath, Path newpath) {
        return link(tPath(oldpath), tPath(newpath));
    }

    public static int link(String oldpath, String newpath) {
        return link(tPath(oldpath), tPath(newpath));
    }

    public static native int link(byte[] oldpath, byte[] newpath);

    public static native long lseek(int fd, long offset, int whence);

    public static int lstat(Path pathname, Out<Stat> outBuf) {
        return lstat(tPath(pathname), outBuf);
    }

    public static int lstat(String pathname, Out<Stat> outBuf) {
        return lstat(tPath(pathname), outBuf);
    }

    public static int lstat(byte[] pathname, Out<Stat> outBuf) {
        byte[] buf = new byte[144];
        int ret;
        if ((ret = lstat(pathname, buf)) == 0) {
            outBuf.set(new Stat(ByteBuffer.wrap(buf)));
        }
        return ret;
    }

    public static native int lstat(byte[] pathname, byte[] buf);

    public static native int madvise(long addr, long length, int advice);

    public static int mkdirs(Path pathname, int mode) {
        return mkdirs(tPath(pathname), mode);
    }

    public static int mkdirs(String pathname, int mode) {
        return mkdirs(tPath(pathname), mode);
    }

    public static int mkdirs(byte[] pathname_cstr, int mode) {
        if (mkdir(pathname_cstr, mode) == 0) {
            return 1;
        }
        switch (Errno.errno()) {
        case Errno.EEXIST:
            Errno.setErrno(0);
            return 0;
        case Errno.ENOENT:
            int pathname_len = ByteArrays.indexOf(pathname_cstr, 0, (byte) '\0');
            int pathname_sep = ByteArrays.lastIndexOf(pathname_cstr, 0, pathname_len, (byte) '/');
            if (pathname_sep <= 0) {
                return -1;
            }
            // create parent
            pathname_cstr[pathname_sep] = (byte) '\0';
            int ret_p;
            if ((ret_p = mkdirs(pathname_cstr, mode)) == -1) {
                return -1;
            }
            // create self
            pathname_cstr[pathname_sep] = (byte) '/';
            if (mkdir(pathname_cstr, mode) == 0) {
                return 1 + ret_p;
            } else {
                return Errno.errno() == Errno.EEXIST ? ret_p : -1;
            }
        default:
            return -1;
        }
    }

    public static int mkdir(Path pathname, int mode) {
        return mkdir(tPath(pathname), mode);
    }

    public static int mkdir(String pathname, int mode) {
        return mkdir(tPath(pathname), mode);
    }

    public static native int mkdir(byte[] pathname, int mode);

    public static native long mmap(long addr, long length, int prot, int flags, int fd, long offset);

    public static native int munmap(long addr, long length);

    public static int open(Path pathname, int flags, int mode) {
        return open(tPath(pathname), flags, mode);
    }

    public static int open(String pathname, int flags, int mode) {
        return open(tPath(pathname), flags, mode);
    }

    public static native int open(byte[] pathname, int flags, int mode);

    public static long read(int fd, byte[] buf, int offset, int count) {
        return read(fd, buf, Unsafe.ARRAY_BYTE_BASE_OFFSET + offset, count);
    }

    public static native long read(int fd, Object buf_base, long buf_offset, long count);

    public static int rename(Path oldpath, Path newpath) {
        return rename(tPath(oldpath), tPath(newpath));
    }

    public static int rename(String oldpath, String newpath) {
        return rename(tPath(oldpath), tPath(newpath));
    }

    public static native int rename(byte[] oldpath, byte[] newpath);

    public static int rmdir(Path pathname) {
        return rmdir(tPath(pathname));
    }

    public static int rmdir(String pathname) {
        return rmdir(tPath(pathname));
    }

    public static native int rmdir(byte[] pathname);

    public static native long sendfile(int out_fd, int in_fd, long offset, long count);

    public static int stat(Path pathname, Out<Stat> outBuf) {
        return stat(tPath(pathname), outBuf);
    }

    public static int stat(String pathname, Out<Stat> outBuf) {
        return stat(tPath(pathname), outBuf);
    }

    public static int stat(byte[] pathname, Out<Stat> outBuf) {
        byte[] buf = new byte[144];
        int ret;
        if ((ret = stat(pathname, buf)) == 0) {
            outBuf.set(new Stat(ByteBuffer.wrap(buf)));
        }
        return ret;
    }

    public static native int stat(byte[] pathname, byte[] buf);

    public static int symlink(Path target, Path linkpath) {
        return symlink(tPath(target), tPath(linkpath));
    }

    public static int symlink(String target, String linkpath) {
        return symlink(tPath(target), tPath(linkpath));
    }

    public static native int symlink(byte[] target, byte[] linkpath);

    public static long write(int fd, byte[] buf, int offset, int count) {
        return write(fd, buf, Unsafe.ARRAY_BYTE_BASE_OFFSET + offset, count);
    }

    public static native long write(int fd, Object buf_base, long buf_offset, long count);

    /*
    public static native long getxattr(String path, String name, long value, long size);
    public static native long setxattr(String path, String name, long value, long size, int flags);
    public static native long listxattr(String path, long list, long size);
     */

    private static byte[] tPath(Path path) {
        byte[] path_s = UnixPath2.getBytes(path);
        if (path_s[path_s.length - 1] == (byte) '\0') {
            return path_s;
        }
        byte[] path_b = new byte[path_s.length + 1];
        System.arraycopy(path_s, 0, path_b, 0, path_s.length);
        return path_b;
    }

    private static byte[] tPath(String path) {
        byte[] path_b = new byte[Limits.PATH_MAX];
        FastUTF8.encodeToCString(path, path_b);
        return path_b;
    }

    private static byte[] tName(String name) {
        byte[] name_b = new byte[Limits.PATH_MAX];
        FastUTF8.encodeToCString(name, name_b);
        return name_b;
    }

    private Syscall() {
    }
}
