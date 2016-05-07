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
 * ASCII byte utilities
 *
 * @author aqd
 */
public final class Bytes {

    private static final boolean[] tableForDigits = new boolean[128];
    private static final boolean[] tableForLetters = new boolean[128];
    private static final boolean[] tableForWhitespaces = new boolean[128];
    private static final boolean[] tableForUpperCases = new boolean[128];
    private static final boolean[] tableForLowerCases = new boolean[128];

    static {
        for (byte c = (byte) '0'; c <= (byte) '9'; c++) {
            tableForDigits[c] = true;
        }
        for (byte c = (byte) 'A'; c <= (byte) 'Z'; c++) {
            tableForLetters[c] = true;
        }
        for (byte c = (byte) 'a'; c <= (byte) 'z'; c++) {
            tableForLetters[c] = true;
        }
        tableForWhitespaces[' '] = true;
        tableForWhitespaces['\t'] = true; // horizontal tabulation
        tableForWhitespaces['\n'] = true; // line feed
        tableForWhitespaces['\u000B'] = true; // vertical tabulation
        tableForWhitespaces['\f'] = true; // form feed
        tableForWhitespaces['\r'] = true; // carriage return
        tableForWhitespaces['\u001C'] = true; // file separator
        tableForWhitespaces['\u001D'] = true; // group separator
        tableForWhitespaces['\u001E'] = true; // record separator
        tableForWhitespaces['\u001F'] = true; // unit separator
        for (byte c = (byte) 'A'; c <= (byte) 'Z'; c++) {
            tableForUpperCases[c] = true;
        }
        for (byte c = (byte) 'a'; c <= (byte) 'z'; c++) {
            tableForLowerCases[c] = true;
        }
    }

    public static boolean isDigit(byte character) {
        return character >= 0 && tableForDigits[character];
    }

    public static boolean isLetter(byte character) {
        return character >= 0 && tableForLetters[character];
    }

    public static boolean isLetterOrDigit(byte character) {
        return character >= 0 && (tableForLetters[character] || tableForDigits[character]);
    }

    public static boolean isWhitespace(byte character) {
        return character >= 0 && tableForWhitespaces[character];
    }

    public static boolean isUpperCase(byte character) {
        return character >= 0 && tableForUpperCases[character];
    }

    public static boolean isLowerCase(byte character) {
        return character >= 0 && tableForLowerCases[character];
    }

    private Bytes() {
    }

}
