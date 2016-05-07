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

/**
 * Unix system calls
 *
 * @author aqd
 */
public final class Syscalls {

    static {
        Runtime.getRuntime().loadLibrary("jxtn-core-unix");
    }

    public static native int errno();

    public static native int access(long pathname, int mode);

    public static native int chdir(long path);

    public static native int chown(long pathname, int owner, int group);

    public static native int chroot(long path);

    public static native int close(int fd);

    public static native int creat(long pathname, int mode);

    public static native int fallocate(int fd, int mode, long offset, long len);

    public static native int fork();

    public static native int getpid();

    public static native int getppid();

    public static native int kill(int pid, int sig);

    public static native int madvise(long addr, long length, int advice);

    public static native int mkdir(long pathname, int mode);

    public static native long mmap(long addr, long length, int prot, int flags, int fd, long offset);

    public static native int munmap(long addr, long length);

    public static native int open(long pathname, int flags, int mode);

    /*
    public static native long getxattr(String path, String name, long value, long size);
    public static native long setxattr(String path, String name, long value, long size, int flags);
    public static native long listxattr(String path, long list, long size);
     */

    private Syscalls() {
    }
}
