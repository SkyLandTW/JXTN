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
 * UTF-8 related functions
 * <p>
 * Rules:
 * <ul>
 * <li>Errors are ignored silently unless explicitly stated.</li>
 * </ul>
 * </p>
 *
 * @author aqd
 */
public final class FastUTF8 {

    private static final int UTF8_2B_MIN = 0b11000000;
    private static final int UTF8_3B_MIN = 0b11100000;
    private static final int UTF8_4B_MIN = 0b11110000;
    private static final int UTF8_5B_MIN = 0b11111000;
    private static final int UTF8_6B_MIN = 0b11111100;
    private static final int UTF8_6B_MAX = 0b11111101;

    /**
     * Encode a single character to UTF-8 bytes
     *
     * @param c character to encode
     * @return UTF-8 bytes representing {@code c}
     */
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

    /**
     * Encode a single character to UTF-8 bytes and store in specified destination array
     *
     * @param c character to encode
     * @param dstBuffer destination buffer to store the resulting UTF-8 bytes
     * @param dstOffset offset in destination buffer for storing of the resulting UTF-8 bytes
     * @return number of UTF-8 bytes encoded from {@code c}
     */
    public static int encode(char c, byte[] dstBuffer, int dstOffset) {
        if (c < 0x80) {
            // Have at most seven bits
            dstBuffer[dstOffset] = (byte) c;
            return 1;
        } else if (c < 0x800) {
            // 2 bytes, 11 bits
            dstBuffer[dstOffset + 0] = (byte) (0xc0 | (c >> 6));
            dstBuffer[dstOffset + 1] = (byte) (0x80 | (c & 0x3f));
            return 2;
        } else {
            // 3 bytes, 16 bits
            dstBuffer[dstOffset + 0] = (byte) (0xe0 | ((c >> 12)));
            dstBuffer[dstOffset + 1] = (byte) (0x80 | ((c >> 6) & 0x3f));
            dstBuffer[dstOffset + 2] = (byte) (0x80 | (c & 0x3f));
            return 3;
        }
    }

    /**
     * Encode a {@link String} to UTF-8 C String (NUL-terminated) and store in specified destination array
     * <p>
     * If the length or the capacity of {@code dstBuffer} is insufficient, this method shall encode as much as it can
     * and return the length of encoded UTF-8 bytes. There is no indication about the status of completion.
     * </p>
     *
     * @param s string to encode
     * @param dstBuffer destination buffer to store the resulting UTF-8 bytes
     * @return number of UTF-8 bytes encoded from {@code s}, not including the NUL termination at the end.
     */
    public static int encodeToCString(String s, byte[] dstBuffer) {
        return encodeToCString(s, dstBuffer, 0, dstBuffer.length);
    }

    /**
     * Encode a {@link String}r to UTF-8 C String (NUL-terminated) and store in specified destination array
     * <p>
     * If the length or the capacity of {@code dstBuffer} is insufficient, this method shall encode as much as it can
     * and return the length of encoded UTF-8 bytes. There is no indication about the status of completion.
     * </p>
     *
     * @param s string to encode
     * @param dstBuffer destination buffer to store the resulting UTF-8 bytes
     * @param dstOffset offset in destination buffer for storing of the resulting UTF-8 bytes
     * @param dstLength length in destination buffer which may be used to store the results
     * @return number of UTF-8 bytes encoded from {@code s}, not including the NUL termination at the end.
     */
    public static int encodeToCString(String s, byte[] dstBuffer, int dstOffset, int dstLength) {
        int dPos = dstOffset;
        int dEnd = dstOffset + Math.min(dstBuffer.length - dstOffset, dstLength) - 1 /* NUL */;
        int sLen = s.length();
        for (int i = 0; i < sLen; i++) {
            char c = s.charAt(i);
            if (c < 0x80) {
                if (dPos > dEnd - 1) {
                    break;
                }
                // Have at most seven bits
                dstBuffer[dPos] = (byte) c;
                dPos += 1;
            } else if (c < 0x800) {
                if (dPos > dEnd - 2) {
                    break;
                }
                // 2 bytes, 11 bits
                dstBuffer[dPos + 0] = (byte) (0xc0 | (c >> 6));
                dstBuffer[dPos + 1] = (byte) (0x80 | (c & 0x3f));
                dPos += 2;
            } else {
                if (dPos > dEnd - 3) {
                    break;
                }
                // 3 bytes, 16 bits
                dstBuffer[dPos + 0] = (byte) (0xe0 | ((c >> 12)));
                dstBuffer[dPos + 1] = (byte) (0x80 | ((c >> 6) & 0x3f));
                dstBuffer[dPos + 2] = (byte) (0x80 | (c & 0x3f));
                dPos += 3;
            }
        }
        dstBuffer[dPos] = 0;
        return dPos - dstOffset;
    }

    /**
     * Verify whether characters in the given buffer are correct UTF-8 sequences.
     *
     * @param buffer buffer to check
     * @return true if {@code buffer} is in UTF-8
     */
    public static boolean verify(ByteBuffer buffer) {
        return verify(buffer.array(),
                buffer.arrayOffset() + buffer.position(),
                buffer.remaining());
    }

    /**
     * Verify whether characters in the given buffer are correct UTF-8 sequences.
     *
     * @param buffer buffer to check
     * @return true if {@code buffer} is in UTF-8
     */
    public static boolean verify(byte[] buffer) {
        return verify(buffer, 0, buffer.length);
    }

    /**
     * Verify whether characters in the given buffer are correct UTF-8 sequences.
     *
     * @param buffer buffer to check
     * @param offset offset of contents in {@code buffer} to check
     * @param length length of contents in {@code buffer} to check, from {@code offset}
     * @return true if {@code buffer} is in UTF-8
     */
    public static boolean verify(byte[] buffer, int offset, int length) {
        int index = offset;
        int limit = offset + length;
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
            int firstChar = firstByte & 0xFF;
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
