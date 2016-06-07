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

/**
 * Helper functions for C strings
 *
 * @author aqd
 */
public final class CStrings {

    public static String toString(byte[] cstring) {
        return new String(cstring, 0, strlen(cstring));
    }

    public static byte[] from(ByteBuffer buffer, int maxLength) {
        byte[] rawAry = buffer.array();
        int rawBeg = buffer.arrayOffset() + buffer.position();
        int rawEnd = rawBeg + Math.min(buffer.remaining(), maxLength);
        int pos;
        for (pos = rawBeg; pos < rawEnd; pos++) {
            if (rawAry[pos] == (byte) '\0') {
                break;
            }
        }
        byte[] cstring = new byte[pos - rawBeg + 1];
        System.arraycopy(rawAry, rawBeg, cstring, 0, cstring.length - 1);
        cstring[cstring.length - 1] = 0;
        return cstring;
    }

    public static byte[] from(String jstring, int maxLength) {
        byte[] jbytes = jstring.getBytes();
        int clength = Integer.min(jbytes.length, maxLength - 1);
        byte[] cbytes = new byte[clength + 1];
        System.arraycopy(jbytes, 0, cbytes, 0, clength);
        cbytes[clength] = '\0';
        return cbytes;
    }

    public static int strlen(byte[] cstring) {
        for (int i = 0; i < cstring.length; i++) {
            if (cstring[i] == 0) {
                return i;
            }
        }
        return cstring.length;
    }

    private CStrings() {
    }
}
