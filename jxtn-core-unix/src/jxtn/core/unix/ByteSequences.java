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

import java.util.Arrays;
import java.util.Collection;

/**
 * {@link ByteSequence} utility functions
 *
 * @author aqd
 */
public final class ByteSequences {

    private static final int LARGE_ARRAY_SIZE = 10;

    static int compare(ByteSequence<?> str1, ByteSequence<?> str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        byte v1[] = str1.source();
        byte v2[] = str2.source();

        int lim = str1.offset() + Math.min(len1, len2);
        int k1 = str1.offset();
        int k2 = str2.offset();
        while (k1 < lim) {
            byte c1 = v1[k1];
            byte c2 = v2[k2];
            int r = c1 - c2;
            if (r != 0) {
                return r;
            }
            k1++;
            k2++;
        }
        return len1 - len2;
    }

    static int compareIgnoreCase(ByteSequence<?> str1, ByteSequence<?> str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        byte v1[] = str1.source();
        byte v2[] = str2.source();

        int lim = str1.offset() + Math.min(len1, len2);
        int k1 = str1.offset();
        int k2 = str2.offset();
        while (k1 < lim) {
            byte c1 = toUpper(v1[k1]);
            byte c2 = toUpper(v2[k2]);
            int r = c1 - c2;
            if (r != 0) {
                return r;
            }
            k1++;
            k2++;
        }
        return len1 - len2;
    }

    public static byte[] fromASCIIString(String asciiString) {
        int length = asciiString.length();
        byte[] array = new byte[length];
        for (int i = 0; i < length; i++) {
            int c = asciiString.charAt(i) & 0xFF;
            array[i] = (byte) c;
        }
        return array;
    }

    public static byte[] copy(byte[] source) {
        byte[] destination = new byte[source.length];
        if (source.length >= LARGE_ARRAY_SIZE) {
            System.arraycopy(source, 0, destination, 0, source.length);
        } else {
            for (int i = 0; i < source.length; i++) {
                destination[i] = source[i];
            }
        }
        return destination;
    }

    public static byte[] copy(byte[] source, int offset, int length) {
        byte[] destination = new byte[length];
        if (length >= LARGE_ARRAY_SIZE) {
            System.arraycopy(source, offset, destination, 0, length);
        } else {
            for (int si = offset, di = 0; di < length; si++, di++) {
                destination[di] = source[si];
            }
        }
        return destination;
    }

    public static void copyTo(ByteSequence<?> source, byte[] destination) {
        copyTo(source, destination, 0);
    }

    public static void copyTo(ByteSequence<?> source, byte[] destination, int destinationOffset) {
        copyTo(source.source(), source.offset(), destination, destinationOffset, source.length());
    }

    public static void copyTo(byte[] source, byte[] destination, int length) {
        if (length >= LARGE_ARRAY_SIZE) {
            System.arraycopy(source, 0, destination, 0, length);
        } else {
            for (int i = 0; i < length; i++) {
                destination[i] = source[i];
            }
        }
    }

    public static void copyTo(byte[] source, byte[] destination, int destinationOffset, int length) {
        if (length >= LARGE_ARRAY_SIZE) {
            System.arraycopy(source, 0, destination, destinationOffset, length);
        } else {
            for (int si = 0, di = destinationOffset; si < length; si++, di++) {
                destination[di] = source[si];
            }
        }
    }

    public static void copyTo(byte[] source, int sourceOffset, byte[] destination, int destinationOffset, int length) {
        if (length >= LARGE_ARRAY_SIZE) {
            System.arraycopy(source, sourceOffset, destination, destinationOffset, length);
        } else {
            for (int si = sourceOffset, di = destinationOffset; si < sourceOffset + length; si++, di++) {
                destination[di] = source[si];
            }
        }
    }

    public static void copyTo(char[] source, byte[] destination, int length) {
        for (int i = 0; i < length; i++) {
            destination[i] = (byte) source[i];
        }
    }

    public static void copyTo(char[] source, int sourceOffset, byte[] destination, int destinationOffset, int length) {
        for (int si = sourceOffset, di = destinationOffset; si < sourceOffset + length; si++, di++) {
            destination[di] = (byte) source[si];
        }
    }

    public static void copyTo(String source, byte[] destination, int destinationOffset) {
        int length = source.length();
        for (int si = 0, di = destinationOffset; si < length; si++, di++) {
            destination[di] = (byte) source.charAt(si);
        }
    }

    public static int hash(byte[]... source) {
        int h = 0;
        for (byte[] s : source) {
            h = 31 * h + Arrays.hashCode(s);
        }
        return h;
    }

    public static int hash(byte[] source, int offset, int length) {
        int h = 0;
        for (int i = offset; i < offset + length; i++) {
            h = 31 * h + source[i];
        }
        return h;
    }

    public static int hashIgnoreCase(byte[] source, int offset, int length) {
        int h = 0;
        for (int i = offset; i < offset + length; i++) {
            h = 31 * h + toUpper(source[i]);
        }
        return h;
    }

    public static boolean endsWith(byte[] source, byte[] suffix) {
        int offset = source.length - suffix.length;
        if (offset < 0) {
            return false;
        }
        return equals(source, offset, suffix);
    }

    public static boolean endsWith(byte[] source, String suffix) {
        int offset = source.length - suffix.length();
        if (offset < 0) {
            return false;
        }
        return equals(source, offset, suffix);
    }

    public static boolean endsWithIgnoreCase(byte[] source, byte[] suffix) {
        int offset = source.length - suffix.length;
        if (offset < 0) {
            return false;
        }
        return equalsIgnoreCase(source, offset, suffix);
    }

    public static boolean endsWithIgnoreCase(byte[] source, String suffix) {
        int offset = source.length - suffix.length();
        if (offset < 0) {
            return false;
        }
        return equalsIgnoreCase(source, offset, suffix);
    }

    public static boolean equals(
            byte[] source, int sourceOffset,
            byte[] target) {
        return equals(
                source, sourceOffset,
                target, 0,
                target.length);
    }

    public static boolean equals(
            byte[] source, int sourceOffset,
            byte[] target, int targetOffset,
            int length) {
        for (int si = sourceOffset, ti = targetOffset; si < sourceOffset + length; si++, ti++) {
            if (source[si] != target[ti]) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(
            byte[] source, int sourceOffset,
            String target) {
        return equals(
                source, sourceOffset,
                target, 0,
                target.length());
    }

    public static boolean equals(
            byte[] source, int sourceOffset,
            String target, int targetOffset,
            int length) {
        for (int si = sourceOffset, ti = targetOffset; si < sourceOffset + length; si++, ti++) {
            if (source[si] != (byte) target.charAt(ti)) {
                return false;
            }
        }
        return true;
    }

    public static boolean equalsIgnoreCase(
            byte[] source, int sourceOffset,
            byte[] target) {
        return equalsIgnoreCase(
                source, sourceOffset,
                target, 0,
                target.length);
    }

    public static boolean equalsIgnoreCase(
            byte[] source, int sourceOffset,
            byte[] target, int targetOffset,
            int length) {
        for (int si = sourceOffset, ti = targetOffset; si < sourceOffset + length; si++, ti++) {
            if (toLower(source[si]) != toLower(target[ti])) {
                return false;
            }
        }
        return true;
    }

    public static boolean equalsIgnoreCase(
            byte[] source, int sourceOffset,
            String target) {
        return equalsIgnoreCase(
                source, sourceOffset,
                target, 0,
                target.length());
    }

    public static boolean equalsIgnoreCase(
            byte[] source, int sourceOffset,
            String target, int targetOffset,
            int length) {
        for (int si = sourceOffset, ti = targetOffset; si < sourceOffset + length; si++, ti++) {
            if (toLower(source[si]) != toLower(target.charAt(ti))) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDigits(byte[] array, int offset, int length) {
        for (int i = offset; i < offset + length; i++) {
            if (!Bytes.isDigit(array[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkLetters(byte[] array, int offset, int length) {
        for (int i = offset; i < offset + length; i++) {
            if (!Bytes.isLetter(array[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkWhitespaces(byte[] array, int offset, int length) {
        for (int i = offset; i < offset + length; i++) {
            if (!Bytes.isWhitespace(array[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkUpperCases(byte[] array, int offset, int length) {
        for (int i = offset; i < offset + length; i++) {
            if (!Bytes.isUpperCase(array[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkLowerCases(byte[] array, int offset, int length) {
        for (int i = offset; i < offset + length; i++) {
            if (!Bytes.isLowerCase(array[i])) {
                return false;
            }
        }
        return true;
    }

    public static byte[] matchesAny(byte[] array, byte[]... targets) {
        return matchesAny(array, 0, targets);
    }

    public static byte[] matchesAny(byte[] array, int offset, byte[]... targets) {
        for (byte[] t : targets) {
            if (equals(array, offset, t)) {
                return t;
            }
        }
        return null;
    }

    public static byte[] matchesAny(byte[] array, Collection<byte[]> targets) {
        return matchesAny(array, 0, targets);
    }

    public static byte[] matchesAny(byte[] array, int offset, Collection<byte[]> targets) {
        for (byte[] t : targets) {
            if (equals(array, offset, t)) {
                return t;
            }
        }
        return null;
    }

    public static boolean startsWithAny(byte[] array, byte[]... targets) {
        return startsWithAny(array, 0, targets);
    }

    public static boolean startsWithAny(byte[] array, int offset, byte[]... targets) {
        for (byte[] t : targets) {
            if (equals(array, offset, t)) {
                return true;
            }
        }
        return false;
    }

    public static boolean startsWithAny(byte[] array, Collection<byte[]> targets) {
        return startsWithAny(array, 0, targets);
    }

    public static boolean startsWithAny(byte[] array, int offset, Collection<byte[]> targets) {
        for (byte[] t : targets) {
            if (equals(array, offset, t)) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(byte[] array, byte target) {
        for (byte b : array) {
            if (b == target) {
                return true;
            }
        }
        return false;
    }

    public static int indexOfOrEnd(byte[] value, int begin, int end, byte target) {
        for (int i = begin; i < end; i++) {
            byte c = value[i];
            if (c == '\\') {
                i++;
            }
            if (c == target) {
                return i;
            }
        }
        return end;
    }

    public static int indexOfOrEnd(ByteSequence<?> value, int begin, byte target) {
        for (int i = begin; i < value.length(); i++) {
            byte c = value.byteAt(i);
            if (c == '\\') {
                i++;
            }
            if (c == target) {
                return i;
            }
        }
        return value.length();
    }

    public static int indexOfAnyOrEnd(byte[] value, int begin, int end, byte... targets) {
        for (int i = begin; i < end; i++) {
            byte c = value[i];
            if (c == '\\') {
                i++;
            }
            for (byte t : targets) {
                if (c == t) {
                    return i;
                }
            }
        }
        return end;
    }

    public static int indexOfAnyOrEnd(ByteSequence<?> value, int begin, byte... targets) {
        for (int i = begin; i < value.length(); i++) {
            byte c = value.byteAt(i);
            if (c == '\\') {
                i++;
            }
            for (byte t : targets) {
                if (c == t) {
                    return i;
                }
            }
        }
        return value.length();
    }

    public static int indexOf(byte[] source, int sourceOffset,
            byte ch) {
        return indexOf(source, sourceOffset, source.length - sourceOffset, ch);
    }

    public static int indexOf(byte[] source, int sourceOffset, int sourceCount,
            byte ch) {
        for (int i = sourceOffset; i < sourceOffset + sourceCount; i++) {
            if (source[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(
            byte[] source, int sourceOffset,
            ByteSequence<?> target) {
        return indexOf(source, sourceOffset, source.length - sourceOffset,
                target.source(), target.offset(), target.length());
    }

    public static int indexOf(
            byte[] source, int sourceOffset, int sourceCount,
            ByteSequence<?> target) {
        return indexOf(source, sourceOffset, sourceCount,
                target.source(), target.offset(), target.length());
    }

    public static int indexOf(
            byte[] source, int sourceOffset,
            byte[] target) {
        return indexOf(source, sourceOffset, source.length - sourceOffset,
                target, 0, target.length);
    }

    public static int indexOf(
            byte[] source, int sourceOffset, int sourceCount,
            byte[] target) {
        return indexOf(source, sourceOffset, sourceCount,
                target, 0, target.length);
    }

    public static int indexOf(
            byte[] source, int sourceOffset, int sourceCount,
            byte[] target, int targetOffset, int targetCount) {
        if (targetCount == 0) {
            return sourceOffset;
        }
        byte first = target[targetOffset];
        int max = sourceOffset + (sourceCount - targetCount);
        for (int i = sourceOffset; i <= max; i++) {
            if (source[i] != first) {
                while (++i <= max && source[i] != first) {
                }
            }
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && source[j] == target[k]; j++, k++) {
                }
                if (j == end) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int indexOf(
            byte[] source, int sourceOffset,
            String target) {
        return indexOf(source, sourceOffset, source.length - sourceOffset,
                target, 0, target.length());
    }

    public static int indexOf(
            byte[] source, int sourceOffset, int sourceCount,
            String target) {
        return indexOf(source, sourceOffset, sourceCount,
                target, 0, target.length());
    }

    public static int indexOf(
            byte[] source, int sourceOffset, int sourceCount,
            String target, int targetOffset, int targetCount) {
        if (targetCount == 0) {
            return sourceOffset;
        }
        byte first = (byte) target.charAt(targetOffset);
        int max = sourceOffset + (sourceCount - targetCount);
        for (int i = sourceOffset; i <= max; i++) {
            if (source[i] != first) {
                while (++i <= max && source[i] != first) {
                }
            }
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && source[j] == (byte) target.charAt(k); j++, k++) {
                }
                if (j == end) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int indexOfIgnoreCase(
            byte[] source, int sourceOffset, int sourceCount,
            byte[] target) {
        return indexOfIgnoreCase(source, sourceOffset, sourceCount,
                target, 0, target.length);
    }

    public static int indexOfIgnoreCase(
            byte[] source, int sourceOffset, int sourceCount,
            byte[] target, int targetOffset, int targetCount) {
        if (targetCount == 0) {
            return sourceOffset;
        }
        byte firstLower = toLower(target[targetOffset]);
        int max = sourceOffset + (sourceCount - targetCount);
        for (int i = sourceOffset; i <= max; i++) {
            if (toLower(source[i]) != firstLower) {
                while (++i <= max && toLower(source[i]) != firstLower) {
                }
            }
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && toLower(source[j]) == toLower(target[k]); j++, k++) {
                }
                if (j == end) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int indexOfIgnoreCase(
            byte[] source, int sourceOffset, int sourceCount,
            String target) {
        return indexOfIgnoreCase(source, sourceOffset, sourceCount,
                target, 0, target.length());
    }

    public static int indexOfIgnoreCase(
            byte[] source, int sourceOffset, int sourceCount,
            String target, int targetOffset, int targetCount) {
        if (targetCount == 0) {
            return sourceOffset;
        }
        byte firstLower = toLower(target.charAt(targetOffset));
        int max = sourceOffset + (sourceCount - targetCount);
        for (int i = sourceOffset; i <= max; i++) {
            if (toLower(source[i]) != firstLower) {
                while (++i <= max && toLower(source[i]) != firstLower) {
                }
            }
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && toLower(source[j]) == toLower(target.charAt(k)); j++, k++) {
                }
                if (j == end) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int indexOfAny(byte[] source, int sourceOffset,
            byte... characters) {
        return indexOfAny(source, sourceOffset, source.length - sourceOffset, characters);
    }

    public static int indexOfAny(byte[] source, int sourceOffset, int sourceCount,
            byte... characters) {
        for (int i = sourceOffset; i < sourceOffset + sourceCount; i++) {
            byte s = source[i];
            for (byte c : characters) {
                if (s == c) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int lastIndexOf(
            byte[] source, int sourceOffset, int sourceCount,
            byte ch) {
        int i = sourceOffset + sourceCount - 1;
        for (; i >= sourceOffset; i--) {
            if (source[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(
            byte[] source, int sourceOffset, int sourceCount,
            byte[] target) {
        return lastIndexOf(source, sourceOffset, sourceCount,
                target, 0, target.length);
    }

    public static int lastIndexOf(
            byte[] source, int sourceOffset, int sourceCount,
            byte[] target, int targetOffset, int targetCount) {
        int rightIndex = sourceCount - targetCount;
        if (targetCount == 0) {
            return rightIndex;
        }
        int strLastIndex = targetOffset + targetCount - 1;
        byte strLastChar = target[strLastIndex];
        int min = sourceOffset + targetCount - 1;
        int i = min + rightIndex;
        startSearchForLastChar: while (true) {
            while (i >= min && source[i] != strLastChar) {
                i--;
            }
            if (i < min) {
                return -1;
            }
            int j = i - 1;
            int start = j - (targetCount - 1);
            int k = strLastIndex - 1;

            while (j > start) {
                if (source[j--] != target[k--]) {
                    i--;
                    continue startSearchForLastChar;
                }
            }
            return start + 1;
        }
    }

    public static int lastIndexOf(
            byte[] source, int sourceOffset, int sourceCount,
            String target) {
        return lastIndexOf(source, sourceOffset, sourceCount,
                target, 0, target.length());
    }

    public static int lastIndexOf(
            byte[] source, int sourceOffset, int sourceCount,
            String target, int targetOffset, int targetCount) {
        int rightIndex = sourceCount - targetCount;
        if (targetCount == 0) {
            return rightIndex;
        }
        int strLastIndex = targetOffset + targetCount - 1;
        byte strLastChar = (byte) target.charAt(strLastIndex);
        int min = sourceOffset + targetCount - 1;
        int i = min + rightIndex;
        startSearchForLastChar: while (true) {
            while (i >= min && source[i] != strLastChar) {
                i--;
            }
            if (i < min) {
                return -1;
            }
            int j = i - 1;
            int start = j - (targetCount - 1);
            int k = strLastIndex - 1;

            while (j > start) {
                if (source[j--] != (byte) target.charAt(k--)) {
                    i--;
                    continue startSearchForLastChar;
                }
            }
            return start + 1;
        }
    }

    public static int lastIndexOfIgnoreCase(
            byte[] source, int sourceOffset, int sourceCount,
            byte[] target) {
        return lastIndexOfIgnoreCase(source, sourceOffset, sourceCount,
                target, 0, target.length);
    }

    public static int lastIndexOfIgnoreCase(
            byte[] source, int sourceOffset, int sourceCount,
            byte[] target, int targetOffset, int targetCount) {
        int rightIndex = sourceCount - targetCount;
        if (targetCount == 0) {
            return rightIndex;
        }
        int strLastIndex = targetOffset + targetCount - 1;
        byte strLastCharLower = toLower(target[strLastIndex]);
        int min = sourceOffset + targetCount - 1;
        int i = min + rightIndex;
        startSearchForLastChar: while (true) {
            while (i >= min && toLower(source[i]) != strLastCharLower) {
                i--;
            }
            if (i < min) {
                return -1;
            }
            int j = i - 1;
            int start = j - (targetCount - 1);
            int k = strLastIndex - 1;

            while (j > start) {
                if (toLower(source[j--]) != toLower(target[k--])) {
                    i--;
                    continue startSearchForLastChar;
                }
            }
            return start + 1;
        }
    }

    public static int lastIndexOfIgnoreCase(
            byte[] source, int sourceOffset, int sourceCount,
            String target) {
        return lastIndexOfIgnoreCase(source, sourceOffset, sourceCount,
                target, 0, target.length());
    }

    public static int lastIndexOfIgnoreCase(
            byte[] source, int sourceOffset, int sourceCount,
            String target, int targetOffset, int targetCount) {
        int rightIndex = sourceCount - targetCount;
        if (targetCount == 0) {
            return rightIndex;
        }
        int strLastIndex = targetOffset + targetCount - 1;
        byte strLastCharLower = toLower(target.charAt(strLastIndex));
        int min = sourceOffset + targetCount - 1;
        int i = min + rightIndex;
        startSearchForLastChar: while (true) {
            while (i >= min && toLower(source[i]) != strLastCharLower) {
                i--;
            }
            if (i < min) {
                return -1;
            }
            int j = i - 1;
            int start = j - (targetCount - 1);
            int k = strLastIndex - 1;

            while (j > start) {
                if (toLower(source[j--]) != toLower(target.charAt(k--))) {
                    i--;
                    continue startSearchForLastChar;
                }
            }
            return start + 1;
        }
    }

    public static byte toLower(byte character) {
        if (character >= 'A' && character <= 'Z') {
            return (byte) (character + 32);
        } else {
            return character;
        }
    }

    public static byte toLower(char character) {
        if (character >= 'A' && character <= 'Z') {
            return (byte) (character + 32);
        } else {
            return (byte) character;
        }
    }

    public static byte toUpper(byte character) {
        if (character >= 'a' && character <= 'z') {
            return (byte) (character - 32);
        } else {
            return character;
        }
    }

    public static byte toUpper(char character) {
        if (character >= 'a' && character <= 'z') {
            return (byte) (character - 32);
        } else {
            return (byte) character;
        }
    }

    public static ByteString toHexByteString(ByteString separator, byte[] array) {
        ByteSequenceBuilder bsb = new ByteSequenceBuilder();
        for (byte b : array) {
            ByteString bs = ByteStringNumbers.toString(Primitives.unsigned(b), 16);
            if (bs.length() < 2) {
                bsb.append((byte) '0');
            }
            bsb.append(bs);
            bsb.append(separator);
        }
        if (bsb.length() > 0) {
            bsb.length(bsb.length() - separator.length());
        }
        return bsb.toByteString();
    }

    public static String toHexString(String separator, byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            String s = Integer.toHexString(Primitives.unsigned(b));
            if (s.length() < 2) {
                sb.append('0');
            }
            sb.append(s);
            sb.append(separator);
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - separator.length());
        }
        return sb.toString();
    }

    private ByteSequences() {
    }

}
