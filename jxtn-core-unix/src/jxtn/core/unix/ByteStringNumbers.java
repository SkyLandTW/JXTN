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

/**
 * @author aqd
 */
public final class ByteStringNumbers {

    private static final byte[] digits = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    public static int parseInt(ByteSequence<?> s) throws NumberFormatException {
        return parseInt(s, 10);
    }

    public static int parseInt(ByteSequence<?> s, int radix) throws NumberFormatException {
        if (s == null) {
            throw new NumberFormatException("null");
        }
        if (radix < Character.MIN_RADIX) {
            throw new NumberFormatException("radix " + radix
                    + " less than Character.MIN_RADIX");
        }
        if (radix > Character.MAX_RADIX) {
            throw new NumberFormatException("radix " + radix
                    + " greater than Character.MAX_RADIX");
        }
        int result = 0;
        boolean negative = false;
        int i = 0, len = s.length();
        int limit = -Integer.MAX_VALUE;
        int multmin;
        int digit;

        if (len > 0) {
            byte firstChar = s.byteAt(0);
            if (firstChar < (byte) '0') { // Possible leading "+" or "-"
                if (firstChar == (byte) '-') {
                    negative = true;
                    limit = Integer.MIN_VALUE;
                } else if (firstChar != (byte) '+') {
                    throw new NumberFormatException();
                }
                if (len == 1) { // Cannot have lone "+" or "-"
                    throw new NumberFormatException();
                }
                i++;
            }
            multmin = limit / radix;
            while (i < len) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                digit = Character.digit(Primitives.unsigned(s.byteAt(i++)), radix);
                if (digit < 0) {
                    throw new NumberFormatException();
                }
                if (result < multmin) {
                    throw new NumberFormatException();
                }
                result *= radix;
                if (result < limit + digit) {
                    throw new NumberFormatException();
                }
                result -= digit;
            }
        } else {
            throw new NumberFormatException();
        }
        return negative ? result : -result;
    }

    public static long parseLong(ByteString s) throws NumberFormatException {
        return parseLong(s, 10);
    }

    public static long parseLong(ByteString s, int radix) throws NumberFormatException {
        if (s == null) {
            throw new NumberFormatException("null");
        }

        if (radix < Character.MIN_RADIX) {
            throw new NumberFormatException("radix " + radix
                    + " less than Character.MIN_RADIX");
        }
        if (radix > Character.MAX_RADIX) {
            throw new NumberFormatException("radix " + radix
                    + " greater than Character.MAX_RADIX");
        }

        long result = 0;
        boolean negative = false;
        int i = 0, len = s.length();
        long limit = -Long.MAX_VALUE;
        long multmin;
        int digit;

        if (len > 0) {
            byte firstChar = s.byteAt(0);
            if (firstChar < (byte) '0') { // Possible leading "+" or "-"
                if (firstChar == (byte) '-') {
                    negative = true;
                    limit = Long.MIN_VALUE;
                } else if (firstChar != (byte) '+') {
                    throw new NumberFormatException();
                }
                if (len == 1) { // Cannot have lone "+" or "-"
                    throw new NumberFormatException();
                }
                i++;
            }
            multmin = limit / radix;
            while (i < len) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                digit = Character.digit(Primitives.unsigned(s.byteAt(i++)), radix);
                if (digit < 0) {
                    throw new NumberFormatException();
                }
                if (result < multmin) {
                    throw new NumberFormatException();
                }
                result *= radix;
                if (result < limit + digit) {
                    throw new NumberFormatException();
                }
                result -= digit;
            }
        } else {
            throw new NumberFormatException();
        }
        return negative ? result : -result;
    }

    public static ByteString toString(byte value) {
        return toString(value, 10);
    }

    public static ByteString toString(byte value, int radix) {
        return toString(value & 255, radix);
    }

    public static ByteString toString(int value) {
        return toString(value, 10);
    }

    public static ByteString toString(int value, int radix) {
        int i = value;
        byte[] buf = new byte[33];
        boolean negative = i < 0;
        int charPos = buf.length - 1;
        if (!negative) {
            i = -i;
        }
        while (i <= -radix) {
            buf[charPos--] = digits[-(i % radix)];
            i /= radix;
        }
        buf[charPos] = digits[-i];
        if (negative) {
            buf[--charPos] = '-';
        }
        return new ByteString(buf, charPos, buf.length - charPos);
    }

    public static ByteString toString(long value) {
        return toString(value, 10);
    }

    public static ByteString toString(long value, int radix) {
        long i = value;
        byte[] buf = new byte[65];
        boolean negative = i < 0;
        int charPos = buf.length - 1;
        if (!negative) {
            i = -i;
        }
        while (i <= -radix) {
            buf[charPos--] = digits[(int) (-(i % radix))];
            i /= radix;
        }
        buf[charPos] = digits[(int) (-i)];
        if (negative) {
            buf[--charPos] = '-';
        }
        return new ByteString(buf, charPos, buf.length - charPos);
    }

    private ByteStringNumbers() {
    }

}
