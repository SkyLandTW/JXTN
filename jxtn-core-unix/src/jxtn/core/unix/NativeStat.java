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

/**
 * {@code stat()} syscall wrappers
 * 
 * @author aqd
 */
public final class NativeStat extends JNIBase {

    /* stat */

    public static int stat(Path pathname, Out<Stat64> buf_out) {
        return stat(tPath(pathname), buf_out);
    }

    public static int stat(String pathname, Out<Stat64> buf_out) {
        return stat(tPath(pathname), buf_out);
    }

    private static int stat(byte[] pathname, Out<Stat64> buf_out) {
        byte[] buf = new byte[144];
        int ret = stat(pathname, buf);
        if (ret != -1) {
            buf_out.set(new Stat64(ByteBuffer.wrap(buf)));
        }
        return ret;
    }

    static native int stat(byte[] pathname, byte[] buf);

    /* fstat */

    public static int fstat(int fd, Out<Stat64> buf_out) {
        byte[] buf = new byte[144];
        int ret = fstat(fd, buf);
        if (ret != -1) {
            buf_out.set(new Stat64(ByteBuffer.wrap(buf)));
        }
        return ret;
    }

    static native int fstat(int fd, byte[] buf);

    /* lstat */

    public static int lstat(Path pathname, Out<Stat64> buf_out) {
        return lstat(tPath(pathname), buf_out);
    }

    public static int lstat(String pathname, Out<Stat64> buf_out) {
        return lstat(tPath(pathname), buf_out);
    }

    private static int lstat(byte[] pathname, Out<Stat64> buf_out) {
        byte[] buf = new byte[144];
        int ret = lstat(pathname, buf);
        if (ret != -1) {
            buf_out.set(new Stat64(ByteBuffer.wrap(buf)));
        }
        return ret;
    }

    static native int lstat(byte[] pathname, byte[] buf);

    private NativeStat() {
    }
}
