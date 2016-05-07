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

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BytePredicateEx;

/**
 * Base of {@link ByteArray} and {@link ByteString}
 * <p>
 * {@link ByteArray} and {@link ByteString} are functionally identical except {@link ByteString} should be used for
 * UTF-8 encoded strings to prevent confusion.
 * </p>
 *
 * @param <T> {@link ByteArray} and {@link ByteString}
 * @author aqd
 */
public abstract class ByteSequence<T extends ByteSequence<T>>
        implements CharSequence, Cloneable, Comparable<T>, Serializable {

    private static final long serialVersionUID = 1L;

    private static final byte[] escapedBackslash = "\\\\".getBytes(StandardCharsets.UTF_8);
    private static final byte[] backslash = "\\".getBytes(StandardCharsets.UTF_8);

    private static final byte[] escapedDoubleQuote = "\\\"".getBytes(StandardCharsets.UTF_8);
    private static final byte[] doubleQuote = "\"".getBytes(StandardCharsets.UTF_8);

    private static final byte[] escapedSingleQuote = "\\'".getBytes(StandardCharsets.UTF_8);
    private static final byte[] singleQuote = "'".getBytes(StandardCharsets.UTF_8);

    private final byte[] source;
    private final int offset;
    private final int length;

    private boolean hasHash;
    private int hash;

    protected ByteSequence(byte[] source, int offset, int length) {
        if (offset < 0) {
            throw new StringIndexOutOfBoundsException(offset);
        }
        if (length < 0) {
            throw new StringIndexOutOfBoundsException(length);
        }
        if (source.length < offset + length) {
            throw new StringIndexOutOfBoundsException(offset + length);
        }
        this.source = Objects.requireNonNull(source);
        this.offset = offset;
        this.length = length;
    }

    public abstract ByteArray asByteArray();

    public final boolean isEmpty() {
        return this.length == 0;
    }

    @Override
    public final int length() {
        return this.length;
    }

    public final int offset() {
        return this.offset;
    }

    public final byte[] source() {
        return this.source;
    }

    public final ByteReader<RuntimeException> createByteReader() {
        return new ByteReader<RuntimeException>() {
            private int pos = ByteSequence.this.offset;
            private int end = ByteSequence.this.offset + ByteSequence.this.length;

            @Override
            public boolean hasNext() {
                return this.pos < this.end;
            }

            @Override
            public byte nextByte() {
                return ByteSequence.this.source[this.pos++];
            }
        };
    }

    public final byte byteAt(int index) {
        if (index < 0 || index >= this.length) {
            throw new StringIndexOutOfBoundsException(index);
        }
        return this.source[this.offset + index];
    }

    @Deprecated
    @Override
    public final char charAt(int index) {
        if (index < 0 || index >= this.length) {
            throw new StringIndexOutOfBoundsException(index);
        }
        return (char) this.source[this.offset + index];
    }

    @Override
    public final int compareTo(T other) {
        return ByteSequences.compare(this, other);
    }

    @SuppressWarnings("unchecked")
    public final T concat(T str) {
        int otherLen = str.length();
        if (otherLen == 0) {
            return (T) this;
        }
        int len = this.length;
        byte[] buf = new byte[len + otherLen];
        ByteSequences.copyTo(this.source, this.offset, buf, 0, this.length);
        ByteSequences.copyTo(str.source(), str.offset(), buf, this.length, str.length());
        return this.newInstance(buf);
    }

    @SuppressWarnings("unchecked")
    public final T deepCopy(boolean alwaysCopy) {
        if (alwaysCopy || this.offset != 0 || this.length != this.source.length) {
            byte[] newArray = ByteSequences.copy(this.source, this.offset, this.length);
            return this.newInstance(newArray);
        }
        return (T) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        return this.equals((T) obj);
    }

    public final boolean equals(T other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        return this.length == other.length()
                && ByteSequences.equals(
                        this.source, this.offset,
                        other.source(), other.offset(),
                        this.length);
    }

    public final boolean equals(String asciiString) {
        return this.length() == asciiString.length() && this.startsWith(asciiString);
    }

    public final boolean equalsIgnoreCase(T other) {
        return this.length() == other.length() && this.startsWithIgnoreCase(other);
    }

    public final boolean equalsIgnoreCase(String asciiString) {
        return this.length() == asciiString.length() && this.startsWithIgnoreCase(asciiString);
    }

    @Override
    public final int hashCode() {
        if (!this.hasHash) {
            this.hash = ByteSequences.hash(this.source, this.offset, this.length);
            this.hasHash = true;
        }
        return this.hash;
    }

    public final <TException extends Exception> boolean all(BytePredicateEx<TException> condition)
            throws TException {
        for (int i = this.offset; i < this.offset + this.length; i++) {
            if (!condition.testEx(this.source[i])) {
                return false;
            }
        }
        return true;
    }

    public final <TException extends Exception> boolean any(BytePredicateEx<TException> condition)
            throws TException {
        for (int i = this.offset; i < this.offset + this.length; i++) {
            if (condition.testEx(this.source[i])) {
                return true;
            }
        }
        return false;
    }

    public final boolean checkDigits(int start, int length) {
        if (start + length > this.length) {
            return false;
        }
        return ByteSequences.checkDigits(this.source, this.offset + start, length);
    }

    public final boolean checkLetters(int start, int length) {
        if (start + length > this.length) {
            return false;
        }
        return ByteSequences.checkLetters(this.source, this.offset + start, length);
    }

    public final boolean checkWhitespaces(int start, int length) {
        if (start + length > this.length) {
            return false;
        }
        return ByteSequences.checkWhitespaces(this.source, this.offset + start, length);
    }

    public final boolean checkUpperCases(int start, int length) {
        if (start + length > this.length) {
            return false;
        }
        return ByteSequences.checkUpperCases(this.source, this.offset + start, length);
    }

    public final boolean checkLowerCases(int start, int length) {
        if (start + length > this.length) {
            return false;
        }
        return ByteSequences.checkLowerCases(this.source, this.offset + start, length);
    }

    public final boolean contains(byte ch) {
        return this.indexOf(ch) >= 0;
    }

    public final boolean contains(T str) {
        return this.indexOf(str) >= 0;
    }

    public final boolean contains(byte[] str) {
        return this.indexOf(str) >= 0;
    }

    public final boolean contains(String str) {
        return this.indexOf(str) >= 0;
    }

    public final boolean containsIgnoreCase(T str) {
        return this.indexOfIgnoreCase(str) >= 0;
    }

    public final boolean containsIgnoreCase(byte[] str) {
        return this.indexOfIgnoreCase(str) >= 0;
    }

    public final boolean containsIgnoreCase(String str) {
        return this.indexOfIgnoreCase(str) >= 0;
    }

    public final int indexOfOrEnd(byte target, int fromIndex) {
        return ByteSequences.indexOfOrEnd(this, fromIndex, target);
    }

    public final int indexOf(byte ch) {
        return this.indexOf(ch, 0);
    }

    public final int indexOf(byte ch, int fromIndex) {
        return this.indexOf(ch, fromIndex, this.length);
    }

    public final int indexOf(byte ch, int fromIndex, int toIndex) {
        int i = ByteSequences.indexOf(
                this.source, this.offset + fromIndex, Math.min(this.length, toIndex) - fromIndex,
                ch);
        return i >= 0 ? i - this.offset : -1;
    }

    public final int indexOf(char ch) {
        return this.indexOf(ch, 0);
    }

    public final int indexOf(char ch, int fromIndex) {
        return this.indexOf(ch, fromIndex, this.length);
    }

    public final int indexOf(char ch, int fromIndex, int toIndex) {
        int i = ByteSequences.indexOf(
                this.source, this.offset + fromIndex, Math.min(this.length, toIndex) - fromIndex,
                (byte) ch);
        return i >= 0 ? i - this.offset : -1;
    }

    public final int indexOf(T str) {
        return this.indexOf(str, 0);
    }

    public final int indexOf(T str, int fromIndex) {
        return this.indexOf(str, fromIndex, this.length);
    }

    public final int indexOf(T str, int fromIndex, int toIndex) {
        int i = ByteSequences.indexOf(
                this.source, this.offset + fromIndex, Math.min(this.length, toIndex) - fromIndex,
                str.source(), str.offset(), str.length());
        return i >= 0 ? i - this.offset : -1;
    }

    public final int indexOf(byte[] str) {
        return this.indexOf(str, 0);
    }

    public final int indexOf(byte[] str, int fromIndex) {
        return this.indexOf(str, fromIndex, this.length);
    }

    public final int indexOf(byte[] str, int fromIndex, int toIndex) {
        int i = ByteSequences.indexOf(
                this.source, this.offset + fromIndex, Math.min(this.length, toIndex) - fromIndex,
                str, 0, str.length);
        return i >= 0 ? i - this.offset : -1;
    }

    public final int indexOf(String str) {
        return this.indexOf(str, 0);
    }

    public final int indexOf(String str, int fromIndex) {
        return this.indexOf(str, fromIndex, this.length);
    }

    public final int indexOf(String str, int fromIndex, int toIndex) {
        int i = ByteSequences.indexOf(
                this.source, this.offset + fromIndex, Math.min(this.length, toIndex) - fromIndex,
                str, 0, str.length());
        return i >= 0 ? i - this.offset : -1;
    }

    public final int indexOfIgnoreCase(T str) {
        return this.indexOfIgnoreCase(str, 0);
    }

    public final int indexOfIgnoreCase(T str, int fromIndex) {
        return this.indexOf(str, fromIndex, this.length);
    }

    public final int indexOfIgnoreCase(T str, int fromIndex, int toIndex) {
        int i = ByteSequences.indexOfIgnoreCase(
                this.source, this.offset + fromIndex, Math.min(this.length, toIndex) - fromIndex,
                str.source(), str.offset(), str.length());
        return i >= 0 ? i - this.offset : -1;
    }

    public final int indexOfIgnoreCase(byte[] str) {
        return this.indexOfIgnoreCase(str, 0);
    }

    public final int indexOfIgnoreCase(byte[] str, int fromIndex) {
        return this.indexOf(str, fromIndex, this.length);
    }

    public final int indexOfIgnoreCase(byte[] str, int fromIndex, int toIndex) {
        int i = ByteSequences.indexOfIgnoreCase(
                this.source, this.offset + fromIndex, Math.min(this.length, toIndex) - fromIndex,
                str, 0, str.length);
        return i >= 0 ? i - this.offset : -1;
    }

    public final int indexOfIgnoreCase(String str) {
        return this.indexOfIgnoreCase(str, 0);
    }

    public final int indexOfIgnoreCase(String str, int fromIndex) {
        return this.indexOf(str, fromIndex, this.length);
    }

    public final int indexOfIgnoreCase(String str, int fromIndex, int toIndex) {
        int i = ByteSequences.indexOfIgnoreCase(
                this.source, this.offset + fromIndex, Math.min(this.length, toIndex) - fromIndex,
                str, 0, str.length());
        return i >= 0 ? i - this.offset : -1;
    }

    public final int indexOfAny(byte... characters) {
        return this.indexOfAny(characters, 0);
    }

    public final int indexOfAny(byte[] characters, int fromIndex) {
        return this.indexOfAny(characters, fromIndex, this.length);
    }

    public final int indexOfAny(byte[] characters, int fromIndex, int toIndex) {
        int i = ByteSequences.indexOfAny(
                this.source, this.offset + fromIndex, Math.min(this.length, toIndex) - fromIndex,
                characters);
        return i >= 0 ? i - this.offset : -1;
    }

    public final int lastIndexOf(byte ch) {
        return this.lastIndexOf(ch, this.length - 1);
    }

    public final int lastIndexOf(byte ch, int fromIndex) {
        final byte[] value = this.source;
        int i = Math.min(fromIndex, this.length - 1) + this.offset;
        for (; i >= this.offset; i--) {
            if (value[i] == ch) {
                return i - this.offset;
            }
        }
        return -1;
    }

    public final int lastIndexOf(char ch) {
        return this.lastIndexOf((byte) ch);
    }

    public final int lastIndexOf(char ch, int fromIndex) {
        return this.lastIndexOf((byte) ch, fromIndex);
    }

    public final int lastIndexOf(T str) {
        int i = ByteSequences.lastIndexOf(this.source, this.offset, this.length,
                str.source(), str.offset(), str.length());
        return i >= 0 ? i - this.offset : -1;
    }

    public final int lastIndexOf(byte[] str) {
        int i = ByteSequences.lastIndexOf(this.source, this.offset, this.length,
                str, 0, str.length);
        return i >= 0 ? i - this.offset : -1;
    }

    public final int lastIndexOf(String str) {
        int i = ByteSequences.lastIndexOf(this.source, this.offset, this.length,
                str, 0, str.length());
        return i >= 0 ? i - this.offset : -1;
    }

    public final int lastIndexOfIgnoreCase(T str) {
        int i = ByteSequences.lastIndexOfIgnoreCase(this.source, this.offset, this.length,
                str.source(), str.offset(), str.length());
        return i >= 0 ? i - this.offset : -1;
    }

    public final int lastIndexOfIgnoreCase(byte[] str) {
        int i = ByteSequences.lastIndexOfIgnoreCase(this.source, this.offset, this.length,
                str, 0, str.length);
        return i >= 0 ? i - this.offset : -1;
    }

    public final int lastIndexOfIgnoreCase(String str) {
        int i = ByteSequences.lastIndexOfIgnoreCase(this.source, this.offset, this.length,
                str, 0, str.length());
        return i >= 0 ? i - this.offset : -1;
    }

    public final boolean startsWith(byte prefix) {
        return this.startsWith(prefix, 0);
    }

    public final boolean startsWith(byte prefix, int toffset) {
        if (toffset + 1 > this.length) {
            return false;
        }
        return this.source[this.offset + toffset] == prefix;
    }

    public final boolean startsWith(char prefix) {
        return this.startsWith(prefix, 0);
    }

    public final boolean startsWith(char prefix, int toffset) {
        if (toffset + 1 > this.length) {
            return false;
        }
        return this.source[this.offset + toffset] == (byte) prefix;
    }

    public final boolean startsWith(T prefix) {
        return this.startsWith(prefix, 0);
    }

    public final boolean startsWith(T prefix, int toffset) {
        if (toffset + prefix.length() > this.length) {
            return false;
        }
        return ByteSequences.equals(this.source, this.offset + toffset,
                prefix.source(), prefix.offset(), prefix.length());
    }

    public final boolean startsWith(String prefix) {
        return this.startsWith(prefix, 0);
    }

    public final boolean startsWith(String prefix, int toffset) {
        if (toffset + prefix.length() > this.length) {
            return false;
        }
        return ByteSequences.equals(this.source, this.offset + toffset,
                prefix, 0, prefix.length());
    }

    public final boolean startsWithIgnoreCase(T prefix) {
        return this.startsWithIgnoreCase(prefix, 0);
    }

    public final boolean startsWithIgnoreCase(T prefix, int toffset) {
        if (toffset + prefix.length() > this.length) {
            return false;
        }
        return ByteSequences.equalsIgnoreCase(this.source, this.offset + toffset,
                prefix.source(), prefix.offset(), prefix.length());
    }

    public final boolean startsWithIgnoreCase(String prefix) {
        return this.startsWithIgnoreCase(prefix, 0);
    }

    public final boolean startsWithIgnoreCase(String prefix, int toffset) {
        if (toffset + prefix.length() > this.length) {
            return false;
        }
        return ByteSequences.equalsIgnoreCase(this.source, this.offset + toffset,
                prefix, 0, prefix.length());
    }

    public final boolean endsWith(byte suffix) {
        if (1 > this.length) {
            return false;
        }
        return this.source[this.offset + this.length - 1] == suffix;
    }

    public final boolean endsWith(char suffix) {
        if (1 > this.length) {
            return false;
        }
        return this.source[this.offset + this.length - 1] == (byte) suffix;
    }

    public final boolean endsWith(T suffix) {
        int toffset = this.length - suffix.length();
        if (toffset < 0) {
            return false;
        }
        return this.startsWith(suffix, toffset);
    }

    public final boolean endsWith(String suffix) {
        int toffset = this.length - suffix.length();
        if (toffset < 0) {
            return false;
        }
        return this.startsWith(suffix, toffset);
    }

    public final boolean endsWithIgnoreCase(T suffix) {
        int toffset = this.length - suffix.length();
        if (toffset < 0) {
            return false;
        }
        return this.startsWithIgnoreCase(suffix, toffset);
    }

    public final boolean endsWithIgnoreCase(String suffix) {
        int toffset = this.length - suffix.length();
        if (toffset < 0) {
            return false;
        }
        return this.startsWithIgnoreCase(suffix, toffset);
    }

    @SuppressWarnings("unchecked")
    public final T replace(byte[] target, byte[] replacement) {
        int targetPos = ByteSequences.indexOf(
                this.source, this.offset, this.length,
                target);
        if (targetPos == -1) {
            return (T) this;
        }
        int partStart = this.offset;
        ByteSequenceBuilder bsb = new ByteSequenceBuilder(this.length + 16);
        do {
            bsb.append(this.source, partStart, targetPos - partStart);
            bsb.append(replacement);
            partStart = targetPos + target.length;
            targetPos = ByteSequences.indexOf(
                    this.source, partStart, this.length,
                    target, 0, target.length);
        } while (targetPos != -1);
        bsb.append(this.source, partStart, this.length - partStart);
        return this.newInstance(bsb.buffer(), 0, bsb.length());
    }

    @SuppressWarnings("unchecked")
    public final T replace(T target, T replacement) {
        byte[] targetSrc = target.source();
        int targetOff = target.offset();
        int targetLen = target.length();
        int targetPos = ByteSequences.indexOf(
                this.source, this.offset, this.length,
                targetSrc, targetOff, targetLen);
        if (targetPos == -1) {
            return (T) this;
        }
        int partStart = this.offset;
        ByteSequenceBuilder bsb = new ByteSequenceBuilder(this.length + 16);
        do {
            bsb.append(this.source, partStart, targetPos - partStart);
            bsb.append(replacement);
            partStart = targetPos + targetLen;
            targetPos = ByteSequences.indexOf(
                    this.source, partStart, this.length,
                    targetSrc, targetOff, targetLen);
        } while (targetPos != -1);
        bsb.append(this.source, partStart, this.length - partStart);
        return this.newInstance(bsb.buffer(), 0, bsb.length());
    }

    @SuppressWarnings("unchecked")
    public final Iterable<T> searchLazily(T target) {
        return new ByteSequenceSearchingIterable<>((T) this, target);
    }

    public final List<T> split(char divider) {
        return this.split((byte) divider);
    }

    @SuppressWarnings("unchecked")
    public final List<T> split(byte divider) {
        int off = 0;
        int next;
        ArrayList<T> list = new ArrayList<>();
        while ((next = this.indexOf(divider, off)) != -1) {
            list.add(this.substring(off, next));
            off = next + 1;
        }
        if (off == 0) {
            list.add((T) this);
            return list;
        }
        list.add(this.substring(off, this.length));
        return list;
    }

    @SuppressWarnings("unchecked")
    public final List<T> split(T divider) {
        int off = 0;
        int next;
        int dividerLength = divider.length();
        ArrayList<T> list = new ArrayList<>();
        while ((next = this.indexOf(divider, off)) != -1) {
            list.add(this.substring(off, next));
            off = next + dividerLength;
        }
        if (off == 0) {
            list.add((T) this);
            return list;
        }
        list.add(this.substring(off, this.length));
        return list;
    }

    @SuppressWarnings("unchecked")
    public final List<T> split(String divider) {
        int off = 0;
        int next;
        int dividerLength = divider.length();
        ArrayList<T> list = new ArrayList<>();
        while ((next = this.indexOf(divider, off)) != -1) {
            list.add(this.substring(off, next));
            off = next + dividerLength;
        }
        if (off == 0) {
            list.add((T) this);
            return list;
        }
        list.add(this.substring(off, this.length));
        return list;
    }

    @SuppressWarnings("unchecked")
    public final Iterable<T> splitLazily(T divider) {
        return new ByteSequenceSplittingIterable<>((T) this, divider);
    }

    @SuppressWarnings("unchecked")
    public final T stripBefore(byte target, boolean keepTarget) {
        int index = this.indexOf(target);
        if (index < 0) {
            return (T) this;
        }
        return keepTarget ? this.substring(index) : this.substring(index + 1);
    }

    @SuppressWarnings("unchecked")
    public final T stripBefore(String target, boolean keepTarget) {
        int index = this.indexOf(target);
        if (index < 0) {
            return (T) this;
        }
        return keepTarget ? this.substring(index) : this.substring(index + target.length());
    }

    @SuppressWarnings("unchecked")
    public final T stripBefore(T target, boolean keepTarget) {
        int index = this.indexOf(target);
        if (index < 0) {
            return (T) this;
        }
        return keepTarget ? this.substring(index) : this.substring(index + target.length());
    }

    @SuppressWarnings("unchecked")
    public final T stripAfter(byte target, boolean keepTarget) {
        int index = this.indexOf(target);
        if (index < 0) {
            return (T) this;
        }
        return keepTarget ? this.substring(0, index + 1) : this.substring(0, index);
    }

    @SuppressWarnings("unchecked")
    public final T stripAfter(String target, boolean keepTarget) {
        int index = this.indexOf(target);
        if (index < 0) {
            return (T) this;
        }
        return keepTarget ? this.substring(0, index + target.length()) : this.substring(0, index);
    }

    @SuppressWarnings("unchecked")
    public final T stripAfter(T target, boolean keepTarget) {
        int index = this.indexOf(target);
        if (index < 0) {
            return (T) this;
        }
        return keepTarget ? this.substring(0, index + target.length()) : this.substring(0, index);
    }

    @SuppressWarnings("unchecked")
    public final T substring(int beginIndex) {
        if (beginIndex < 0) {
            throw new StringIndexOutOfBoundsException(beginIndex);
        }
        int subLen = this.length - beginIndex;
        if (subLen < 0) {
            throw new StringIndexOutOfBoundsException(subLen);
        }
        return (beginIndex == 0)
                ? (T) this
                : this.newInstance(this.source, this.offset + beginIndex, subLen);
    }

    @SuppressWarnings("unchecked")
    public final T substring(int beginIndex, int endIndex) {
        if (beginIndex < 0) {
            throw new StringIndexOutOfBoundsException(beginIndex);
        }
        if (endIndex > this.length) {
            throw new StringIndexOutOfBoundsException(endIndex);
        }
        int subLen = endIndex - beginIndex;
        if (subLen < 0) {
            throw new StringIndexOutOfBoundsException(subLen);
        }
        return ((beginIndex == 0) && (endIndex == this.length))
                ? (T) this
                : this.newInstance(this.source, this.offset + beginIndex, subLen);
    }

    @Deprecated
    @Override
    public final CharSequence subSequence(int beginIndex, int endIndex) {
        return this.substring(beginIndex, endIndex);
    }

    @SuppressWarnings("unchecked")
    public final T trim() {
        int limit = this.offset + this.length;
        int st = this.offset;
        while (st < limit && this.source[st] <= (byte) ' ') {
            st++;
        }
        while (st < limit && this.source[limit - 1] <= (byte) ' ') {
            limit--;
        }
        return ((st > this.offset) || (limit < this.offset + this.length))
                ? this.substring(st - this.offset, limit - this.offset)
                : (T) this;
    }

    @SuppressWarnings("unchecked")
    public final T trim(byte... bytesToTrim) {
        int limit = this.offset + this.length;
        int st = this.offset;
        while (st < limit && ByteSequences.contains(bytesToTrim, this.source[st])) {
            st++;
        }
        while (st < limit && ByteSequences.contains(bytesToTrim, this.source[limit - 1])) {
            limit--;
        }
        return ((st > this.offset) || (limit < this.offset + this.length))
                ? this.substring(st - this.offset, limit - this.offset)
                : (T) this;
    }

    @SuppressWarnings("unchecked")
    public final T trimEnd() {
        int limit = this.offset + this.length;
        int st = this.offset;
        while (st < limit && this.source[limit - 1] <= (byte) ' ') {
            limit--;
        }
        return ((st > this.offset) || (limit < this.offset + this.length))
                ? this.substring(st - this.offset, limit - this.offset)
                : (T) this;
    }

    @SuppressWarnings("unchecked")
    public final T trimEnd(byte... bytesToTrim) {
        int limit = this.offset + this.length;
        int st = this.offset;
        while (st < limit && ByteSequences.contains(bytesToTrim, this.source[limit - 1])) {
            limit--;
        }
        return ((st > this.offset) || (limit < this.offset + this.length))
                ? this.substring(st - this.offset, limit - this.offset)
                : (T) this;
    }

    @SuppressWarnings("unchecked")
    public final T unquote() {
        if (this.length() >= 2 && this.byteAt(0) == (byte) '"' && this.byteAt(this.length() - 1) == (byte) '"') {
            return this.substring(1, this.length() - 1)
                    .replace(escapedBackslash, backslash)
                    .replace(escapedDoubleQuote, doubleQuote);
        }
        if (this.length() >= 2 && this.byteAt(0) == (byte) '\'' && this.byteAt(this.length() - 1) == (byte) '\'') {
            return this.substring(1, this.length() - 1)
                    .replace(escapedBackslash, backslash)
                    .replace(escapedSingleQuote, singleQuote);
        }
        return (T) this;
    }

    public final T toLowerCase() {
        return this.toLowerCase(false);
    }

    @SuppressWarnings("unchecked")
    public final T toLowerCase(boolean alwaysCopy) {
        int index = this.offset;
        int limit = this.offset + this.length;
        while (index < limit && !Bytes.isUpperCase(this.source[index])) {
            index++;
        }
        if (index >= limit) {
            return alwaysCopy ? this.deepCopy(true) : (T) this;
        }
        byte[] newStr = new byte[this.length];
        ByteSequences.copyTo(this.source, this.offset, newStr, 0, index - this.offset);
        int di = index - this.offset;
        while (index < limit) {
            newStr[di++] = ByteSequences.toLower(this.source[index++]);
        }
        return this.newInstance(newStr);
    }

    public final T toUpperCase() {
        return this.toUpperCase(false);
    }

    @SuppressWarnings("unchecked")
    public final T toUpperCase(boolean alwaysCopy) {
        int index = this.offset;
        int limit = this.offset + this.length;
        while (index < limit && !Bytes.isLowerCase(this.source[index])) {
            index++;
        }
        if (index >= limit) {
            return alwaysCopy ? this.deepCopy(true) : (T) this;
        }
        byte[] newStr = new byte[this.length];
        ByteSequences.copyTo(this.source, this.offset, newStr, 0, index - this.offset);
        int di = index - this.offset;
        while (index < limit) {
            newStr[di++] = ByteSequences.toUpper(this.source[index++]);
        }
        return this.newInstance(newStr);
    }

    public final String toASCIIString() {
        byte[] source = this.source();
        char[] destination = new char[this.length()];
        for (int si = this.offset(), di = 0; di < this.length(); si++, di++) {
            destination[di] = (char) source[si];
        }
        return new String(destination);
    }

    public final byte[] toBytes() {
        return this.toBytes(false);
    }

    public final byte[] toBytes(boolean alwaysCopy) {
        if (!alwaysCopy && this.offset == 0 && this.length == this.source.length) {
            return this.source;
        } else {
            return Arrays.copyOfRange(this.source, this.offset, this.offset + this.length);
        }
    }

    protected abstract T newInstance(byte[] source, int offset, int length);

    protected final T newInstance(byte[] source) {
        return this.newInstance(source, 0, source.length);
    }

}
