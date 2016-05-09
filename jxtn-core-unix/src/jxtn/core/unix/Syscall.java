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

import sun.misc.Unsafe;

/**
 * Unix system calls
 *
 * @author aqd
 */
public final class Syscall {

    static {
        Runtime.getRuntime().loadLibrary("jxtn-core-unix");
    }

    public static int access(String pathname, int mode) {
        return access(tPath(pathname), Unsafe.ARRAY_BYTE_BASE_OFFSET, mode);
    }

    public static native int access(Object pathname_base, long pathname_offset, int mode);

    public static int chdir(String path) {
        return chdir(tPath(path), Unsafe.ARRAY_BYTE_BASE_OFFSET);
    }

    public static native int chdir(Object path_base, long path_offset);

    public static int chown(String pathname, int owner, int group) {
        return chown(tPath(pathname), Unsafe.ARRAY_BYTE_BASE_OFFSET, owner, group);
    }

    public static native int chown(Object pathname_base, long pathname_offset, int owner, int group);

    public static int chroot(String path) {
        return chroot(tPath(path), Unsafe.ARRAY_BYTE_BASE_OFFSET);
    }

    public static native int chroot(Object path_base, long path_offset);

    public static native int close(int fd);

    public static int creat(String pathname, int mode) {
        return creat(tPath(pathname), Unsafe.ARRAY_BYTE_BASE_OFFSET, mode);
    }

    public static native int creat(Object pathname_base, long pathname_offset, int mode);

    public static native int fallocate(int fd, int mode, long offset, long len);

    public static native int fork();

    public static native int geteuid();

    public static native int getpid();

    public static native int getppid();

    public static native int gettid();

    public static native int getuid();

    public static native int kill(int pid, int sig);

    public static native int madvise(long addr, long length, int advice);

    public static int mkdirs(String pathname, int mode) {
        byte[] pathname_cstr = tPath(pathname);
        return mkdirs(pathname_cstr, mode);
    }

    public static int mkdirs(byte[] pathname_cstr, int mode) {
        if (mkdir(pathname_cstr, Unsafe.ARRAY_BYTE_BASE_OFFSET, mode) == 0) {
            return 0;
        }
        int err = Errno.errno();
        System.out.println(new ByteArray(pathname_cstr).stripAfter((byte) '\0', false) + ": err " + err);
        switch (err) {
        case Errno.EEXIST:
            return 0;
        case Errno.ENOENT:
            int pathname_len = ByteSequences.indexOf(pathname_cstr, 0, (byte) '\0');
            int pathname_sep = ByteSequences.lastIndexOf(pathname_cstr, 0, pathname_len, (byte) '/');
            if (pathname_sep <= 0) {
                return -1;
            }
            pathname_cstr[pathname_sep] = (byte) '\0';
            if (mkdirs(pathname_cstr, mode) != 0) {
                return -1;
            }
            pathname_cstr[pathname_sep] = (byte) '/';
            return mkdir(pathname_cstr, Unsafe.ARRAY_BYTE_BASE_OFFSET, mode);
        default:
            return -1;
        }
    }

    public static int mkdir(String pathname, int mode) {
        return mkdir(tPath(pathname), Unsafe.ARRAY_BYTE_BASE_OFFSET, mode);
    }

    public static native int mkdir(Object pathname_base, long pathname_offset, int mode);

    public static native long mmap(long addr, long length, int prot, int flags, int fd, long offset);

    public static native int munmap(long addr, long length);

    public static int open(String pathname, int flags, int mode) {
        return open(tPath(pathname), Unsafe.ARRAY_BYTE_BASE_OFFSET, flags, mode);
    }

    public static native int open(Object pathname_base, long pathname_offset, int flags, int mode);

    public static long read(int fd, byte[] buf, int offset, int count) {
        return read(fd, buf, Unsafe.ARRAY_BYTE_BASE_OFFSET + offset, count);
    }

    public static native long read(int fd, Object buf_base, long buf_offset, long count);

    public static int rmdir(String pathname) {
        return rmdir(tPath(pathname), Unsafe.ARRAY_BYTE_BASE_OFFSET);
    }

    public static native int rmdir(Object pathname_base, long pathname_offset);

    public static long write(int fd, byte[] buf, int offset, int count) {
        return write(fd, buf, Unsafe.ARRAY_BYTE_BASE_OFFSET + offset, count);
    }

    public static native long write(int fd, Object buf_base, long buf_offset, long count);

    /*
    public static native long getxattr(String path, String name, long value, long size);
    public static native long setxattr(String path, String name, long value, long size, int flags);
    public static native long listxattr(String path, long list, long size);
     */

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
