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

    public static final int AT_FDCWD = -100; /* Special value used to indicate the *at functions
                                                should use the current working directory. */
    public static final int AT_SYMLINK_NOFOLLOW = 0x100; /* Do not follow symbolic links.  */
    public static final int AT_REMOVEDIR = 0x200; /* Remove directory instead of unlinking file.  */
    public static final int AT_EACCESS = 0x200; /* Test access permitted for effective IDs, not real IDs.  */
    public static final int AT_SYMLINK_FOLLOW = 0x400; /* Follow symbolic links.  */
    public static final int AT_NO_AUTOMOUNT = 0x800; /* Suppress terminal automount traversal.  */
    public static final int AT_EMPTY_PATH = 0x1000; /* Allow empty relative pathname.  */

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

    private static native int access(byte[] pathname, int mode);

    /* chmod */

    public static int chmod(Path pathname, int mode) {
        return chmod(tPath(pathname), mode);
    }

    public static int chmod(String pathname, int mode) {
        return chmod(tPath(pathname), mode);
    }

    private static native int chmod(byte[] pathname, int mode);

    /* chown */

    public static int chown(Path pathname, int owner, int group) {
        return chown(tPath(pathname), owner, group);
    }

    public static int chown(String pathname, int owner, int group) {
        return chown(tPath(pathname), owner, group);
    }

    private static native int chown(byte[] pathname, int owner, int group);

    /* creat */

    public static int creat(Path pathname, int mode) {
        return creat(tPath(pathname), mode);
    }

    public static int creat(String pathname, int mode) {
        return creat(tPath(pathname), mode);
    }

    private static native int creat(byte[] pathname, int mode);

    /* fadvise */

    public static final int POSIX_FADV_NORMAL = 0; /* No further special treatment. */
    public static final int POSIX_FADV_RANDOM = 1; /* Expect random page references. */
    public static final int POSIX_FADV_SEQUENTIAL = 2; /* Expect sequential page references. */
    public static final int POSIX_FADV_WILLNEED = 3; /* Will need these pages. */
    public static final int POSIX_FADV_DONTNEED = 4; /* Don't need these pages. */
    public static final int POSIX_FADV_NOREUSE = 5; /* Data will be accessed once. */

    public static native int fadvise(int fd, long offset, long len, int advice);

    /* faccessat */

    public static int faccessat(int dirfd, Path pathname, int mode, int flags) {
        return faccessat(dirfd, tPath(pathname), mode, flags);
    }

    public static int faccessat(int dirfd, String pathname, int mode, int flags) {
        return faccessat(dirfd, tPath(pathname), mode, flags);
    }

    public static int faccessat(int dirfd, Dirent64 pathname, int mode, int flags) {
        return faccessat(dirfd, pathname.d_name, mode, flags);
    }

    private static native int faccessat(int dirfd, byte[] pathname, int mode, int flags);

    /* fallocate */

    public static native int fallocate(int fd, int mode, long offset, long len);

    /* fchmod */

    public static native int fchmod(int fd, int mode);

    /* fchmodat */

    public static int fchmodat(int dirfd, Path pathname, int mode, int flags) {
        return fchmodat(dirfd, tPath(pathname), mode, flags);
    }

    public static int fchmodat(int dirfd, String pathname, int mode, int flags) {
        return fchmodat(dirfd, tPath(pathname), mode, flags);
    }

    public static int fchmodat(int dirfd, Dirent64 pathname, int mode, int flags) {
        return fchmodat(dirfd, pathname.d_name, mode, flags);
    }

    private static native int fchmodat(int dirfd, byte[] pathname, int mode, int flags);

    /* fchown */

    public static native int fchown(int fd, int owner, int group);

    /* fchownat */

    public static int fchownat(int dirfd, Path pathname, int owner, int group, int flags) {
        return fchownat(dirfd, tPath(pathname), owner, group, flags);
    }

    public static int fchownat(int dirfd, String pathname, int owner, int group, int flags) {
        return fchownat(dirfd, tPath(pathname), owner, group, flags);
    }

    public static int fchownat(int dirfd, Dirent64 pathname, int owner, int group, int flags) {
        return fchownat(dirfd, pathname.d_name, owner, group, flags);
    }

    private static native int fchownat(int dirfd, byte[] pathname, int owner, int group, int flags);

    /* ftruncate */

    public static native int ftruncate(int fd, long length);

    /* lchown */

    public static int lchown(Path pathname, int owner, int group) {
        return lchown(tPath(pathname), owner, group);
    }

    public static int lchown(String pathname, int owner, int group) {
        return lchown(tPath(pathname), owner, group);
    }

    private static native int lchown(byte[] pathname, int owner, int group);

    /* link */

    public static int link(Path oldpath, Path newpath) {
        return link(tPath(oldpath), tPath(newpath));
    }

    public static int link(String oldpath, String newpath) {
        return link(tPath(oldpath), tPath(newpath));
    }

    private static native int link(byte[] oldpath, byte[] newpath);

    /* linkat */

    public static int linkat(int olddirfd, Path oldpath, int newdirfd, Path newpath, int flags) {
        return linkat(olddirfd, tPath(oldpath), newdirfd, tPath(newpath), flags);
    }

    public static int linkat(int olddirfd, String oldpath, int newdirfd, String newpath, int flags) {
        return linkat(olddirfd, tPath(oldpath), newdirfd, tPath(newpath), flags);
    }

    public static int linkat(int olddirfd, Dirent64 oldpath, int newdirfd, Dirent64 newpath, int flags) {
        return linkat(olddirfd, oldpath.d_name, newdirfd, newpath.d_name, flags);
    }

    private static native int linkat(int olddirfd, byte[] oldpath, int newdirfd, byte[] newpath, int flags);

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

    private static native int open(byte[] pathname, int flags, int mode);

    /* openat */

    public static int openat(int dirfd, Path pathname, int flags, int mode) {
        return openat(dirfd, tPath(pathname), flags, mode);
    }

    public static int openat(int dirfd, String pathname, int flags, int mode) {
        return openat(dirfd, tPath(pathname), flags, mode);
    }

    public static int openat(int dirfd, Dirent64 pathname, int flags, int mode) {
        return openat(dirfd, pathname.d_name, flags, mode);
    }

    private static native int openat(int dirfd, byte[] pathname, int flags, int mode);

    /* pipe */

    public static native int pipe(int[] pipefd);

    /* pipe2 */

    public static native int pipe2(int[] pipefd, int flags);

    /* readlink */
    public static long readlink(Path pathname, byte[] buf) {
        return readlink(tPath(pathname), buf);
    }

    public static long readlink(String pathname, byte[] buf) {
        return readlink(tPath(pathname), buf);
    }

    private static native long readlink(byte[] pathname, byte[] buf);

    /* rename */

    public static int rename(Path oldpath, Path newpath) {
        return rename(tPath(oldpath), tPath(newpath));
    }

    public static int rename(String oldpath, String newpath) {
        return rename(tPath(oldpath), tPath(newpath));
    }

    private static native int rename(byte[] oldpath, byte[] newpath);

    /* renameat */

    public static int renameat(int olddirfd, Path oldpath, int newdirfd, Path newpath) {
        return renameat(olddirfd, tPath(oldpath), newdirfd, tPath(newpath));
    }

    public static int renameat(int olddirfd, String oldpath, int newdirfd, String newpath) {
        return renameat(olddirfd, tPath(oldpath), newdirfd, tPath(newpath));
    }

    public static int renameat(int olddirfd, Dirent64 oldpath, int newdirfd, Dirent64 newpath) {
        return renameat(olddirfd, oldpath.d_name, newdirfd, newpath.d_name);
    }

    private static native int renameat(int olddirfd, byte[] oldpath, int newdirfd, byte[] newpath);

    /* renameat2 */

    public static final int RENAME_NOREPLACE = (1 << 0); /* Don't overwrite target */
    public static final int RENAME_EXCHANGE = (1 << 1); /* Exchange source and dest */
    public static final int RENAME_WHITEOUT = (1 << 2); /* Whiteout source */

    public static int renameat2(int olddirfd, Path oldpath, int newdirfd, Path newpath, int flags) {
        return renameat2(olddirfd, tPath(oldpath), newdirfd, tPath(newpath), flags);
    }

    public static int renameat2(int olddirfd, String oldpath, int newdirfd, String newpath, int flags) {
        return renameat2(olddirfd, tPath(oldpath), newdirfd, tPath(newpath), flags);
    }

    public static int renameat2(int olddirfd, Dirent64 oldpath, int newdirfd, Dirent64 newpath, int flags) {
        return renameat2(olddirfd, oldpath.d_name, newdirfd, newpath.d_name, flags);
    }

    private static native int renameat2(int olddirfd, byte[] oldpath, int newdirfd, byte[] newpath, int flags);

    /* symlink */

    public static int symlink(Path target, Path linkpath) {
        return symlink(tPath(target), tPath(linkpath));
    }

    public static int symlink(String target, String linkpath) {
        return symlink(tPath(target), tPath(linkpath));
    }

    private static native int symlink(byte[] target, byte[] linkpath);

    /* symlinkat */

    public static int symlinkat(Path target, int newdirfd, Path linkpath) {
        return symlinkat(tPath(target), newdirfd, tPath(linkpath));
    }

    public static int symlinkat(String target, int newdirfd, String linkpath) {
        return symlinkat(tPath(target), newdirfd, tPath(linkpath));
    }

    public static int symlinkat(Dirent64 target, int newdirfd, Dirent64 linkpath) {
        return symlinkat(target.d_name, newdirfd, linkpath.d_name);
    }

    private static native int symlinkat(byte[] target, int newdirfd, byte[] linkpath);

    /* truncate */

    public static int truncate(Path pathname, long length) {
        return truncate(tPath(pathname), length);
    }

    public static int truncate(String pathname, long length) {
        return truncate(tPath(pathname), length);
    }

    private static native int truncate(byte[] pathname, long length);

    /* unlink */

    public static int unlink(Path pathname) {
        return unlink(tPath(pathname));
    }

    public static int unlink(String pathname) {
        return unlink(tPath(pathname));
    }

    private static native int unlink(byte[] pathname);

    /* unlinkat */

    public static int unlinkat(int dirfd, Path pathname, int flags) {
        return unlinkat(dirfd, tPath(pathname), flags);
    }

    public static int unlinkat(int dirfd, String pathname, int flags) {
        return unlinkat(dirfd, tPath(pathname), flags);
    }

    public static int unlinkat(int dirfd, Dirent64 pathname, int flags) {
        return unlinkat(dirfd, pathname.d_name, flags);
    }

    private static native int unlinkat(int dirfd, byte[] pathname, int flags);

    private NativeFiles() {
    }
}
