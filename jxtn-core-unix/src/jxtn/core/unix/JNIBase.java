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
 * Base class for all those requiring <i>jxtn-core-unix</i>
 *
 * @author aqd
 */
abstract class JNIBase {

    static {
        System.out.println(System.getProperty("java.library.path"));
        Runtime.getRuntime().loadLibrary("jxtn-core-unix");
    }

    protected static long rBuffer(ByteBuffer buffer, long length) {
        if (length != -1L) {
            buffer.position(buffer.position() + (int) length);
        }
        return length;
    }

    protected static int rBuffer(ByteBuffer buffer, int status) {
        if (status != -1) {
            buffer.position(buffer.limit());
        }
        return status;
    }

    protected static byte[][] tArgs(String[] args) {
        byte[][] args_b = new byte[args.length][];
        for (int i = 0; i < args.length; i++) {
            args_b[i] = tName(args[i]);
        }
        return args_b;
    }

    protected static byte[] tArray(ByteBuffer buffer) {
        if (buffer.arrayOffset() + buffer.position() == 0) {
            return buffer.array();
        } else {
            return ByteArrays.copy(buffer);
        }
    }

    protected static byte[] tName(String name) {
        byte[] name_b = new byte[NativeLimits.PATH_MAX];
        FastUTF8.encodeToCString(name, name_b);
        return name_b;
    }

    protected static byte[] tPath(Path path) {
        byte[] path_s = CPaths.getBytes(path);
        if (path_s[path_s.length - 1] == (byte) '\0') {
            return path_s;
        }
        byte[] path_b = new byte[path_s.length + 1];
        System.arraycopy(path_s, 0, path_b, 0, path_s.length);
        return path_b;
    }

    protected static byte[] tPath(String path) {
        byte[] path_b = new byte[NativeLimits.PATH_MAX];
        FastUTF8.encodeToCString(path, path_b);
        return path_b;
    }

    JNIBase() {
    }
}
