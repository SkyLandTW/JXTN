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
 * Directory-related syscall extensions
 *
 * @author aqd
 */
public final class NativeDirs2 extends JNIBase {

    /* mkdirs */

    public static int mkdirs(Path pathname, int mode) {
        return mkdirs(tPath(pathname), mode);
    }

    public static int mkdirs(String pathname, int mode) {
        return mkdirs(tPath(pathname), mode);
    }

    /**
     * Create specified directory and all parent directories if non-existent
     * <p>
     * If {@code pathname} already exists, 0 would be returned.
     * </p>
     *
     * @param pathname Directory to create (may be modified during the process)
     * @param mode Mode of the directory as well as all parent directories
     * @return numbers of directories created, or -1 on error
     */
    static native int mkdirs(byte[] pathname, int mode);

    /* rmdirs */

    public static int rmdirs(Path pathname) {
        return rmdirs(tPath(pathname));
    }

    public static int rmdirs(String pathname) {
        return rmdirs(tPath(pathname));
    }

    /**
     * Create specified directory and all parent directories if non-existent
     * <p>
     * If {@code pathname} already exists, 0 would be returned.
     * </p>
     *
     * @param pathname Directory to create (may be modified during the process)
     * @param mode Mode of the directory as well as all parent directories
     * @return numbers of directories created, or -1 on error
     */
    static native int rmdirs(byte[] pathname);

    private NativeDirs2() {
    }
}
