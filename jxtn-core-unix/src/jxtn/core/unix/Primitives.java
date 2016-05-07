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
 * Primitives related utility (byte/int/...)
 *
 * @author aqd
 */
public final class Primitives {

    public static final byte NIBBLE_MASK = 0x0F;
    public static final short BYTE_MASK = 0xFF;
    public static final int SHORT_MASK = 0xFFFF;
    public static final long INT_MASK = 0xFFFFFFFFL;

    public static final int NIBBLE_BITS = 4;
    public static final int BYTE_BITS = 8;
    public static final int SHORT_BITS = 16;
    public static final int INT_BITS = 32;
    public static final int LONG_BITS = 64;

    public static byte lowNibble(byte b) {
        return (byte) (b & NIBBLE_MASK);
    }

    public static byte lowNibble(int b) {
        return (byte) (b & NIBBLE_MASK);
    }

    public static byte highNibble(byte b) {
        return (byte) ((b >>> 4) & NIBBLE_MASK);
    }

    public static byte highNibble(int b) {
        return (byte) ((b >>> 4) & NIBBLE_MASK);
    }

    public static short unsigned(byte v) {
        return (short) (v & BYTE_MASK);
    }

    public static int unsigned(short v) {
        return v & SHORT_MASK;
    }

    public static long unsigned(int v) {
        return v & INT_MASK;
    }

    private Primitives() {
    }
}
