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

/**
 * UNIX system calls
 *
 * @author aqd
 */
public final class Syscall extends Unix {

    /* access */

    public static int access(Path pathname, int mode) {
        return access(tPath(pathname), mode);
    }

    public static int access(String pathname, int mode) {
        return access(tPath(pathname), mode);
    }

    private static native int access(byte[] pathname, int mode);

    /* chdir */

    public static int chdir(Path path) {
        return chdir(tPath(path));
    }

    public static int chdir(String path) {
        return chdir(tPath(path));
    }

    private static native int chdir(byte[] path);

    /* chown */

    public static int chown(Path pathname, int owner, int group) {
        return chown(tPath(pathname), owner, group);
    }

    public static int chown(String pathname, int owner, int group) {
        return chown(tPath(pathname), owner, group);
    }

    private static native int chown(byte[] pathname, int owner, int group);

    /* chroot */

    public static int chroot(Path path) {
        return chroot(tPath(path));
    }

    public static int chroot(String path) {
        return chroot(tPath(path));
    }

    private static native int chroot(byte[] path);

    /* close */

    public static native int close(int fd);

    /* creat */

    public static int creat(Path pathname, int mode) {
        return creat(tPath(pathname), mode);
    }

    public static int creat(String pathname, int mode) {
        return creat(tPath(pathname), mode);
    }

    private static native int creat(byte[] pathname, int mode);

    /* fadvise */

    public static native int fadvise(int fd, long offset, long len, int advice);

    /* fallocate */

    public static native int fallocate(int fd, int mode, long offset, long len);

    /* fgetxattr */

    public static long fgetxattr(int fd, String name) {
        return fgetxattr(fd, tName(name), null);
    }

    public static long fgetxattr(int fd, String name, byte[] value) {
        return fgetxattr(fd, tName(name), value);
    }

    private static native long fgetxattr(int fd, byte[] name, byte[] value);

    /* fork */

    public static native int fork();

    /* fsetxattr */

    public static int fsetxattr(int fd, String name, byte[] value, int flags) {
        return fsetxattr(fd, tName(name), value, flags);
    }

    private static native int fsetxattr(int fd, byte[] name, byte[] value, int flags);

    /* fstat */

    public static int fstat(int fd, Out<Stat> buf_out) {
        byte[] buf = new byte[144];
        int ret = fstat(fd, buf);
        if (ret != -1) {
            buf_out.set(new Stat(ByteBuffer.wrap(buf)));
        }
        return ret;
    }

    private static native int fstat(int fd, byte[] buf);

    /* ftruncate */

    public static native int ftruncate(int fd, long length);

    /* geteuid */

    public static native int geteuid();

    /* getpid */

    public static native int getpid();

    /* getppid */

    public static native int getppid();

    /* gettid */

    public static native int gettid();

    /* getuid */

    public static native int getuid();

    /* getxattr */

    public static long getxattr(Path path, String name) {
        return getxattr(tPath(path), tName(name), null);
    }

    public static long getxattr(Path path, String name, byte[] value) {
        return getxattr(tPath(path), tName(name), value);
    }

    public static long getxattr(String path, String name) {
        return getxattr(tPath(path), tName(name), null);
    }

    public static long getxattr(String path, String name, byte[] value) {
        return getxattr(tPath(path), tName(name), value);
    }

    private static native long getxattr(byte[] path, byte[] name, byte[] value);

    /* kill */

    public static native int kill(int pid, int sig);

    /* lgetxattr */

    public static long lgetxattr(Path path, String name) {
        return lgetxattr(tPath(path), tName(name), null);
    }

    public static long lgetxattr(Path path, String name, byte[] value) {
        return lgetxattr(tPath(path), tName(name), value);
    }

    public static long lgetxattr(String path, String name) {
        return lgetxattr(tPath(path), tName(name), null);
    }

    public static long lgetxattr(String path, String name, byte[] value) {
        return lgetxattr(tPath(path), tName(name), value);
    }

    private static native long lgetxattr(byte[] path, byte[] name, byte[] value);

    /* link */

    public static int link(Path oldpath, Path newpath) {
        return link(tPath(oldpath), tPath(newpath));
    }

    public static int link(String oldpath, String newpath) {
        return link(tPath(oldpath), tPath(newpath));
    }

    private static native int link(byte[] oldpath, byte[] newpath);

    /* lseek */

    public static native long lseek(int fd, long offset, int whence);

    /* lsetxattr */

    public static int lsetxattr(Path path, String name, byte[] value, int flags) {
        return lsetxattr(tPath(path), tName(name), value, flags);
    }

    public static int lsetxattr(String path, String name, byte[] value, int flags) {
        return lsetxattr(tPath(path), tName(name), value, flags);
    }

    private static native int lsetxattr(byte[] path, byte[] name, byte[] value, int flags);

    /* lstat */

    public static int lstat(Path pathname, Out<Stat> buf_out) {
        return lstat(tPath(pathname), buf_out);
    }

    public static int lstat(String pathname, Out<Stat> buf_out) {
        return lstat(tPath(pathname), buf_out);
    }

    private static int lstat(byte[] pathname, Out<Stat> buf_out) {
        byte[] buf = new byte[144];
        int ret = lstat(pathname, buf);
        if (ret != -1) {
            buf_out.set(new Stat(ByteBuffer.wrap(buf)));
        }
        return ret;
    }

    private static native int lstat(byte[] pathname, byte[] buf);

    /* madvise */

    public static native int madvise(long addr, long length, int advice);

    /* mkdir */

    public static int mkdir(Path pathname, int mode) {
        return mkdir(tPath(pathname), mode);
    }

    public static int mkdir(String pathname, int mode) {
        return mkdir(tPath(pathname), mode);
    }

    public static native int mkdir(byte[] pathname, int mode);

    /* mmap */

    public static native long mmap(long addr, long length, int prot, int flags, int fd, long offset);

    /* munmap */

    public static native int munmap(long addr, long length);

    /* open */

    public static int open(Path pathname, int flags, int mode) {
        return open(tPath(pathname), flags, mode);
    }

    public static int open(String pathname, int flags, int mode) {
        return open(tPath(pathname), flags, mode);
    }

    private static native int open(byte[] pathname, int flags, int mode);

    /* pipe */

    public static native int pipe(int[] pipefd);

    /* pipe2 */

    public static native int pipe2(int[] pipefd, int flags);

    /* prctl */

    public static native int prctl(int option, long arg2, long arg3, long arg4, long arg5);

    /* pread */

    public static long pread(int fd, ByteBuffer buf, long offset) {
        return rBuffer(buf, pread(fd, buf.array(), buf.arrayOffset() + buf.position(), buf.remaining(), offset));
    }

    public static long pread(int fd, byte[] buf_array, int buf_offset, int count, long offset) {
        return pread(fd, (Object) buf_array, Unsafe.ARRAY_BYTE_BASE_OFFSET + buf_offset, count, offset);
    }

    private static native long pread(int fd, Object buf_base, long buf_offset, long count, long offset);

    /* pwrite */

    public static long pwrite(int fd, ByteBuffer buf, long offset) {
        return rBuffer(buf, pwrite(fd, buf.array(), buf.arrayOffset() + buf.position(), buf.remaining(), offset));
    }

    public static long pwrite(int fd, byte[] buf_array, int buf_offset, int count, long offset) {
        return pwrite(fd, (Object) buf_array, Unsafe.ARRAY_BYTE_BASE_OFFSET + buf_offset, count, offset);
    }

    private static native long pwrite(int fd, Object buf_base, long buf_offset, long count, long offset);

    /* read */

    public static long read(int fd, ByteBuffer buf) {
        return rBuffer(buf, read(fd, buf.array(), buf.arrayOffset() + buf.position(), buf.remaining()));
    }

    public static long read(int fd, byte[] buf_array, int buf_offset, int count) {
        return read(fd, (Object) buf_array, Unsafe.ARRAY_BYTE_BASE_OFFSET + buf_offset, count);
    }

    private static native long read(int fd, Object buf_base, long buf_offset, long count);

    /* rename */

    public static int rename(Path oldpath, Path newpath) {
        return rename(tPath(oldpath), tPath(newpath));
    }

    public static int rename(String oldpath, String newpath) {
        return rename(tPath(oldpath), tPath(newpath));
    }

    private static native int rename(byte[] oldpath, byte[] newpath);

    /* rmdir */

    public static int rmdir(Path pathname) {
        return rmdir(tPath(pathname));
    }

    public static int rmdir(String pathname) {
        return rmdir(tPath(pathname));
    }

    private static native int rmdir(byte[] pathname);

    /* sendfile */

    public static native long sendfile(int out_fd, int in_fd, long offset, long count);

    /* setxattr */

    public static int setxattr(Path path, String name, byte[] value, int flags) {
        return setxattr(tPath(path), tName(name), value, flags);
    }

    public static int setxattr(String path, String name, byte[] value, int flags) {
        return setxattr(tPath(path), tName(name), value, flags);
    }

    private static native int setxattr(byte[] path, byte[] name, byte[] value, int flags);

    /* stat */

    public static int stat(Path pathname, Out<Stat> buf_out) {
        return stat(tPath(pathname), buf_out);
    }

    public static int stat(String pathname, Out<Stat> buf_out) {
        return stat(tPath(pathname), buf_out);
    }

    private static int stat(byte[] pathname, Out<Stat> buf_out) {
        byte[] buf = new byte[144];
        int ret = stat(pathname, buf);
        if (ret != -1) {
            buf_out.set(new Stat(ByteBuffer.wrap(buf)));
        }
        return ret;
    }

    private static native int stat(byte[] pathname, byte[] buf);

    /* symlink */

    public static int symlink(Path target, Path linkpath) {
        return symlink(tPath(target), tPath(linkpath));
    }

    public static int symlink(String target, String linkpath) {
        return symlink(tPath(target), tPath(linkpath));
    }

    private static native int symlink(byte[] target, byte[] linkpath);

    /* truncate */

    public static int truncate(Path pathname, long length) {
        return truncate(tPath(pathname), length);
    }

    public static int truncate(String pathname, long length) {
        return truncate(tPath(pathname), length);
    }

    private static native int truncate(byte[] pathname, long length);

    /* waitpid */

    public static native int waitpid(int pid, int[] status, int options);

    /* write */

    public static long write(int fd, ByteBuffer buf) {
        return rBuffer(buf, write(fd, buf.array(), buf.arrayOffset() + buf.position(), buf.remaining()));
    }

    public static long write(int fd, byte[] buf_array, int buf_offset, int count) {
        return write(fd, (Object) buf_array, Unsafe.ARRAY_BYTE_BASE_OFFSET + buf_offset, count);
    }

    private static native long write(int fd, Object buf_base, long buf_offset, long count);

    /*
    public static native long getxattr(String path, String name, long value, long size);
    public static native long setxattr(String path, String name, long value, long size, int flags);
    public static native long listxattr(String path, long list, long size);
     */

    private Syscall() {
    }
}
