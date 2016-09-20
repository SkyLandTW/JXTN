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
import java.util.function.Consumer;

/**
 * Directory-related syscall wrappers
 *
 * @author aqd
 */
public final class NativeDirs extends JNIBase {

    static final int DEFAULT_GETDENTS_BUFFER_SIZE = Dirent64.BUFFER_SIZE << 8;

    /* chdir */

    public static int chdir(Path path) {
        return chdir(tPath(path));
    }

    public static int chdir(String path) {
        return chdir(tPath(path));
    }

    private static native int chdir(byte[] path);

    /* chroot */

    public static int chroot(Path path) {
        return chroot(tPath(path));
    }

    public static int chroot(String path) {
        return chroot(tPath(path));
    }

    private static native int chroot(byte[] path);

    /* getdents64 */

    public static int getdents64(int fd, Consumer<Dirent64> direntConsumer) {
        return getdents64(fd, direntConsumer, DEFAULT_GETDENTS_BUFFER_SIZE);
    }

    public static int getdents64(int fd, Consumer<Dirent64> direntConsumer, int bufferLength) {
        byte[] dirp_ary = new byte[Math.max(bufferLength, Dirent64.BUFFER_SIZE)];
        ByteBuffer dirp_buf = ByteBuffer.wrap(dirp_ary);
        int total = 0;
        int ret;
        while ((ret = getdents64(fd, dirp_ary)) > 0) {
            dirp_buf.clear();
            dirp_buf.limit(ret);
            total += ret;
            while (dirp_buf.hasRemaining()) {
                Dirent64 dirent = new Dirent64(dirp_buf);
                direntConsumer.accept(dirent);
            }
        }
        if (ret != -1) {
            return total;
        } else {
            return ret;
        }
    }

    private static native int getdents64(int fd, byte[] dirp);

    /* fchdir */

    public static int fchdir(int fd) {
        return fchdir(fd);
    }

    /* mkdir */

    public static int mkdir(Path pathname, int mode) {
        return mkdir(tPath(pathname), mode);
    }

    public static int mkdir(String pathname, int mode) {
        return mkdir(tPath(pathname), mode);
    }

    private static native int mkdir(byte[] pathname, int mode);

    /* mkdirat */

    public static int mkdirat(int dirfd, Path pathname, int mode) {
        return mkdirat(dirfd, tPath(pathname), mode);
    }

    public static int mkdirat(int dirfd, String pathname, int mode) {
        return mkdirat(dirfd, tPath(pathname), mode);
    }

    public static int mkdirat(int dirfd, Dirent64 pathname, int mode) {
        return mkdirat(dirfd, pathname.d_name, mode);
    }

    private static native int mkdirat(int dirfd, byte[] pathname, int mode);

    /* rmdir */

    public static int rmdir(Path pathname) {
        return rmdir(tPath(pathname));
    }

    public static int rmdir(String pathname) {
        return rmdir(tPath(pathname));
    }

    private static native int rmdir(byte[] pathname);

    private NativeDirs() {
    }
}
