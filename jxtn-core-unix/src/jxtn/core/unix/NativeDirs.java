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
 * Directory-related syscall wrappers
 *
 * @author aqd
 */
public final class NativeDirs extends JNIBase {

    /* chdir */

    public static int chdir(Path path) {
        return chdir(tPath(path));
    }

    public static int chdir(String path) {
        return chdir(tPath(path));
    }

    static native int chdir(byte[] path);

    /* chroot */

    public static int chroot(Path path) {
        return chroot(tPath(path));
    }

    public static int chroot(String path) {
        return chroot(tPath(path));
    }

    static native int chroot(byte[] path);

    /* mkdir */

    public static int mkdir(Path pathname, int mode) {
        return mkdir(tPath(pathname), mode);
    }

    public static int mkdir(String pathname, int mode) {
        return mkdir(tPath(pathname), mode);
    }

    static native int mkdir(byte[] pathname, int mode);

    /* rmdir */

    public static int rmdir(Path pathname) {
        return rmdir(tPath(pathname));
    }

    public static int rmdir(String pathname) {
        return rmdir(tPath(pathname));
    }

    static native int rmdir(byte[] pathname);

    private NativeDirs() {
    }
}