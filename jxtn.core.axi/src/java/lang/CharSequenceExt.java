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
package java.lang;

/**
 * Extension to {@link CharSequence}
 *
 * @author AqD
 */
public interface CharSequenceExt {

    /**
     * Extract up to a specified length of substring in the left (beginning)
     *
     * @param length Maximum length of substring
     * @return The substring extracted from the left; the length might be smaller than {@code length}.
     */
    default String left(int length) {
        CharSequence thiz = (CharSequence) this;
        int validLength = Math.min(length, thiz.length());
        return thiz.subSequence(0, validLength).toString();
    }

    /**
     * Extract up to a specified length of substring in the right (end)
     *
     * @param length Maximum length of substring
     * @return The substring extracted from the right; the length might be smaller than {@code length}.
     */
    default String right(int length) {
        CharSequence thiz = (CharSequence) this;
        int validLength = Math.min(length, thiz.length());
        return thiz.subSequence(thiz.length() - validLength, thiz.length()).toString();
    }

    /**
     * Pad whitespaces in the left to a specified total length.
     *
     * @param totalLength Total length of the resulting string
     * @return The resulting string after padding
     */
    default String padLeft(int totalLength) {
        return this.padLeft(totalLength, ' ');
    }

    /**
     * Pad character in the left to a specified total length
     *
     * @param totalLength Total length of the resulting string
     * @param paddingChar The character for padding
     * @return The resulting string after padding
     */
    default String padLeft(int totalLength, char paddingChar) {
        CharSequence thiz = (CharSequence) this;
        if (thiz.length() >= totalLength) {
            return this.toString();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < totalLength - thiz.length(); i++) {
            sb.append(paddingChar);
        }
        return sb.toString() + thiz.toString();
    }

    /**
     * Pad whitespaces in the right to a specified total length
     *
     * @param totalLength Total length of the resulting string
     * @return The resulting string after padding
     */
    default String padRight(int totalLength) {
        return this.padRight(totalLength, ' ');
    }

    /**
     * Pad characters in the right to a specified total length
     *
     * @param totalLength Total length of the resulting string
     * @param paddingChar The character for padding
     * @return The resulting string after padding
     */
    default String padRight(int totalLength, char paddingChar) {
        CharSequence thiz = (CharSequence) this;
        if (thiz.length() >= totalLength) {
            return this.toString();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < totalLength - thiz.length(); i++) {
            sb.append(paddingChar);
        }
        return thiz.toString() + sb.toString();
    }

    /**
     * Capitalize this string
     *
     * @return The capitalized version of this string; The original string is returned if no change is to be made.
     */
    default String toCapitalized() {
        CharSequence thiz = (CharSequence) this;
        if (thiz.length() == 0) {
            return thiz.toString();
        }
        return thiz.subSequence(0, 1).toString().toUpperCase() + thiz.subSequence(1, thiz.length());
    }

    /**
     * Un-capitalize this string
     *
     * @return The un-capitalized version of this string; The original string is returned if no change is to be made.
     */
    default String toUncapitalized() {
        CharSequence thiz = (CharSequence) this;
        if (thiz.length() == 0) {
            return thiz.toString();
        }
        return thiz.subSequence(0, 1).toString().toLowerCase() + thiz.subSequence(1, thiz.length());
    }
}
