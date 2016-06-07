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

import java.nio.file.Path;

/**
 * File-related syscall wrappers
 * 
 * @author aqd
 */
public final class NativeFiles extends JNIBase {

    /* access */

    public static final int F_OK = 0;
    public static final int X_OK = 1;
    public static final int W_OK = 2;
    public static final int R_OK = 4;

    public static int access(Path pathname, int mode) {
        return access(tPath(pathname), mode);
    }

    public static int access(String pathname, int mode) {
        return access(tPath(pathname), mode);
    }

    static native int access(byte[] pathname, int mode);

    /* chown */

    public static int chown(Path pathname, int owner, int group) {
        return chown(tPath(pathname), owner, group);
    }

    public static int chown(String pathname, int owner, int group) {
        return chown(tPath(pathname), owner, group);
    }

    static native int chown(byte[] pathname, int owner, int group);

    /* creat */

    public static int creat(Path pathname, int mode) {
        return creat(tPath(pathname), mode);
    }

    public static int creat(String pathname, int mode) {
        return creat(tPath(pathname), mode);
    }

    static native int creat(byte[] pathname, int mode);

    /* fadvise */

    public static final int POSIX_FADV_NORMAL = 0; /* No further special treatment. */
    public static final int POSIX_FADV_RANDOM = 1; /* Expect random page references. */
    public static final int POSIX_FADV_SEQUENTIAL = 2; /* Expect sequential page references. */
    public static final int POSIX_FADV_WILLNEED = 3; /* Will need these pages. */
    public static final int POSIX_FADV_DONTNEED = 4; /* Don't need these pages. */
    public static final int POSIX_FADV_NOREUSE = 5; /* Data will be accessed once. */

    public static native int fadvise(int fd, long offset, long len, int advice);

    /* fallocate */

    public static native int fallocate(int fd, int mode, long offset, long len);

    /* ftruncate */

    public static native int ftruncate(int fd, long length);

    /* link */

    public static int link(Path oldpath, Path newpath) {
        return link(tPath(oldpath), tPath(newpath));
    }

    public static int link(String oldpath, String newpath) {
        return link(tPath(oldpath), tPath(newpath));
    }

    static native int link(byte[] oldpath, byte[] newpath);

    /* lseek */

    public static final int SEEK_SET = 0; /* Seek from beginning of file. */
    public static final int SEEK_CUR = 1; /* Seek from current position. */
    public static final int SEEK_END = 2; /* Seek from end of file. */
    public static final int SEEK_DATA = 3; /* Seek to next data. */
    public static final int SEEK_HOLE = 4; /* Seek to next hole. */

    public static native long lseek(int fd, long offset, int whence);

    /* open */

    public static final int O_RDONLY /*   */ = 000000000;
    public static final int O_WRONLY /*   */ = 000000001;
    public static final int O_RDWR /*     */ = 000000002;
    public static final int O_ACCMODE /*  */ = 000000003;
    public static final int O_CREAT /*    */ = 000000100;
    public static final int O_EXCL /*     */ = 000000200;
    public static final int O_NOCTTY /*   */ = 000000400;
    public static final int O_TRUNC /*    */ = 000001000;
    public static final int O_APPEND /*   */ = 000002000;
    public static final int O_NONBLOCK /* */ = 000004000;
    public static final int O_NDELAY /*   */ = 000004000;
    public static final int O_DSYNC /*    */ = 000010000;
    public static final int O_ASYNC /*    */ = 000020000;
    public static final int O_DIRECT /*   */ = 000040000;
    public static final int O_LARGEFILE /**/ = 000100000;
    public static final int O_DIRECTORY /**/ = 000200000;
    public static final int O_NOFOLLOW /* */ = 000400000;
    public static final int O_CLOEXEC /*  */ = 002000000;
    public static final int O_SYNC /*     */ = 004010000;
    public static final int O_FSYNC /*    */ = 004010000;
    public static final int O_RSYNC /*    */ = 004010000;
    public static final int O_NOATIME /*  */ = 001000000;
    public static final int O_PATH /*     */ = 010000000;
    public static final int O_TMPFILE /*  */ = 020200000;

    public static int open(Path pathname, int flags, int mode) {
        return open(tPath(pathname), flags, mode);
    }

    public static int open(String pathname, int flags, int mode) {
        return open(tPath(pathname), flags, mode);
    }

    static native int open(byte[] pathname, int flags, int mode);

    /* pipe */

    public static native int pipe(int[] pipefd);

    /* pipe2 */

    public static native int pipe2(int[] pipefd, int flags);

    /* rename */

    public static int rename(Path oldpath, Path newpath) {
        return rename(tPath(oldpath), tPath(newpath));
    }

    public static int rename(String oldpath, String newpath) {
        return rename(tPath(oldpath), tPath(newpath));
    }

    static native int rename(byte[] oldpath, byte[] newpath);

    /* symlink */

    public static int symlink(Path target, Path linkpath) {
        return symlink(tPath(target), tPath(linkpath));
    }

    public static int symlink(String target, String linkpath) {
        return symlink(tPath(target), tPath(linkpath));
    }

    static native int symlink(byte[] target, byte[] linkpath);

    /* truncate */

    public static int truncate(Path pathname, long length) {
        return truncate(tPath(pathname), length);
    }

    public static int truncate(String pathname, long length) {
        return truncate(tPath(pathname), length);
    }

    static native int truncate(byte[] pathname, long length);

    private NativeFiles() {
    }
}
