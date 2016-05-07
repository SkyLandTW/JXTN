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

public final class FastUTF8 {

    private static final int UTF8_2B_MIN = 0b11000000;
    private static final int UTF8_3B_MIN = 0b11100000;
    private static final int UTF8_4B_MIN = 0b11110000;
    private static final int UTF8_5B_MIN = 0b11111000;
    private static final int UTF8_6B_MIN = 0b11111100;
    private static final int UTF8_6B_MAX = 0b11111101;

    public static byte[] encode(char c) {
        if (c < 0x80) {
            // Have at most seven bits
            return new byte[] { (byte) c };
        } else if (c < 0x800) {
            // 2 bytes, 11 bits
            return new byte[] {
                    (byte) (0xc0 | (c >> 6)),
                    (byte) (0x80 | (c & 0x3f))
            };
        } else {
            // 3 bytes, 16 bits
            return new byte[] {
                    (byte) (0xe0 | ((c >> 12))),
                    (byte) (0x80 | ((c >> 6) & 0x3f)),
                    (byte) (0x80 | (c & 0x3f))
            };
        }
    }

    public static boolean verify(ByteBuffer buffer) {
        return verify(buffer.array(),
                buffer.arrayOffset() + buffer.position(),
                buffer.remaining());
    }

    public static boolean verify(ByteString string) {
        return verify(string.source(), string.offset(), string.length());
    }

    public static boolean verify(byte[] buffer) {
        return verify(buffer, 0, buffer.length);
    }

    public static boolean verify(byte[] buffer, int start, int length) {
        int index = start;
        int limit = start + length;
        while (index < limit && buffer[index] >= 0) {
            index++;
        }
        return verifyNonASCII(buffer, index, limit);
    }

    private static boolean verifyNonASCII(byte[] buffer, int start, int limit) {
        // simplified from Google Profobuf, see https://en.wikipedia.org/wiki/UTF-8#Description
        int index = start;
        while (true) {
            byte firstByte;
            // skip ASCII
            do {
                if (index >= limit) {
                    return true;
                }
            } while ((firstByte = buffer[index++]) >= 0);
            int firstChar = Primitives.unsigned(firstByte);
            //
            if (firstChar < UTF8_2B_MIN) {
                return false;
            }
            // two-byte form: rest 1
            if (firstChar < UTF8_3B_MIN) {
                if (index >= limit) {
                    return false;
                }
                if (!verifyUTF8Rest(buffer[index++])) {
                    return false;
                }
                continue;
            }
            // three-byte form: rest 2
            if (firstChar < UTF8_4B_MIN) {
                if (index >= limit - 1) {
                    return false;
                }
                if (!verifyUTF8Rest(buffer[index++])
                        || !verifyUTF8Rest(buffer[index++])) {
                    return false;
                }
                continue;
            }
            // four-byte form: rest 3
            if (firstChar < UTF8_5B_MIN) {
                if (index >= limit - 2) {
                    return false;
                }
                if (!verifyUTF8Rest(buffer[index++])
                        || !verifyUTF8Rest(buffer[index++])
                        || !verifyUTF8Rest(buffer[index++])) {
                    return false;
                }
                continue;
            }
            // five-byte form: rest 4
            if (firstChar < UTF8_6B_MIN) {
                if (index >= limit - 3) {
                    return false;
                }
                if (!verifyUTF8Rest(buffer[index++])
                        || !verifyUTF8Rest(buffer[index++])
                        || !verifyUTF8Rest(buffer[index++])
                        || !verifyUTF8Rest(buffer[index++])) {
                    return false;
                }
                continue;
            }
            // six-byte form: rest 5
            if (firstChar <= UTF8_6B_MAX) {
                if (index >= limit - 4) {
                    return false;
                }
                if (!verifyUTF8Rest(buffer[index++])
                        || !verifyUTF8Rest(buffer[index++])
                        || !verifyUTF8Rest(buffer[index++])
                        || !verifyUTF8Rest(buffer[index++])
                        || !verifyUTF8Rest(buffer[index++])) {
                    return false;
                }
                continue;
            }
            return false;
        }
    }

    private static boolean verifyUTF8Rest(byte b) {
        // see https://en.wikipedia.org/wiki/UTF-8#Description
        int hi = (b & 0xff) >>> 6;
        return hi == 0b10;
    }

}
