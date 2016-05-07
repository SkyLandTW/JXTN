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
import java.nio.charset.StandardCharsets;
import java.util.Comparator;

/**
 * Primitive byte array
 *
 * @author aqd
 */
public final class ByteArray extends ByteSequence<ByteArray> {

    public static final ByteArray EMPTY = new ByteArray(new byte[0]);

    public static final ByteArrayComparator comparator = new ByteArrayComparator();
    public static final ByteArrayIgnoreCaseComparator comparatorIgnoreCase = new ByteArrayIgnoreCaseComparator();

    private static final long serialVersionUID = 1L;

    public static ByteArray copy(byte[] source) {
        return new ByteArray(ByteSequences.copy(source));
    }

    public static ByteArray copy(byte[] source, int offset, int length) {
        return new ByteArray(ByteSequences.copy(source, offset, length));
    }

    public static ByteArray concat(ByteArray... strings) {
        if (strings.length == 0) {
            return EMPTY;
        }
        int totalLen = 0;
        for (ByteArray str : strings) {
            totalLen += str.length();
        }
        for (ByteArray str : strings) {
            if (str.length() == totalLen) {
                return str;
            }
        }
        byte[] newbuf = new byte[totalLen];
        int filledLen = 0;
        for (ByteArray str : strings) {
            ByteSequences.copyTo(str.source(), str.offset(), newbuf, filledLen, str.length());
            filledLen += str.length();
        }
        return new ByteArray(newbuf);
    }

    public ByteArray(String asciiString) {
        this(ByteSequences.fromASCIIString(asciiString));
    }

    public ByteArray(ByteBuffer buffer) {
        this(buffer, buffer.remaining());
    }

    public ByteArray(ByteBuffer buffer, int length) {
        this(buffer.array(), buffer.arrayOffset() + buffer.position(), length);
    }

    public ByteArray(byte[] source) {
        this(source, 0, source.length);
    }

    public ByteArray(byte[] source, int offset, int length) {
        super(source, offset, length);
    }

    @Override
    public ByteArray asByteArray() {
        return this;
    }

    @Override
    public ByteArray clone() {
        try {
            return (ByteArray) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean equals(ByteString other) {
        if (other == null) {
            return false;
        }
        return this.length() == other.length()
                && ByteSequences.equals(
                        this.source(), this.offset(),
                        other.source(), other.offset(),
                        this.length());
    }

    public boolean contains(ByteString str) {
        return this.indexOf(str) >= 0;
    }

    public boolean containsIgnoreCase(ByteString str) {
        return this.indexOfIgnoreCase(str) >= 0;
    }

    public int indexOf(ByteString str) {
        return this.indexOf(str, 0);
    }

    public int indexOf(ByteString str, int fromIndex) {
        return this.indexOf(str, fromIndex, this.length());
    }

    public int indexOf(ByteString str, int fromIndex, int toIndex) {
        int i = ByteSequences.indexOf(
                this.source(), this.offset() + fromIndex, Math.min(this.length(), toIndex) - fromIndex,
                str.source(), str.offset(), str.length());
        return i >= 0 ? i - this.offset() : -1;
    }

    public int indexOfIgnoreCase(ByteString str) {
        return this.indexOfIgnoreCase(str, 0);
    }

    public int indexOfIgnoreCase(ByteString str, int fromIndex) {
        return this.indexOf(str, fromIndex, this.length());
    }

    public int indexOfIgnoreCase(ByteString str, int fromIndex, int toIndex) {
        int i = ByteSequences.indexOfIgnoreCase(
                this.source(), this.offset() + fromIndex, Math.min(this.length(), toIndex) - fromIndex,
                str.source(), str.offset(), str.length());
        return i >= 0 ? i - this.offset() : -1;
    }

    public int lastIndexOf(ByteString str) {
        int i = ByteSequences.lastIndexOf(this.source(), this.offset(), this.length(),
                str.source(), str.offset(), str.length());
        return i >= 0 ? i - this.offset() : -1;
    }

    public int lastIndexOfIgnoreCase(ByteString str) {
        int i = ByteSequences.lastIndexOfIgnoreCase(this.source(), this.offset(), this.length(),
                str.source(), str.offset(), str.length());
        return i >= 0 ? i - this.offset() : -1;
    }

    public boolean startsWith(ByteString prefix) {
        return this.startsWith(prefix, 0);
    }

    public boolean startsWith(ByteString prefix, int toffset) {
        if (toffset + prefix.length() > this.length()) {
            return false;
        }
        return ByteSequences.equals(this.source(), this.offset() + toffset,
                prefix.source(), prefix.offset(), prefix.length());
    }

    public boolean startsWithIgnoreCase(ByteString prefix) {
        return this.startsWithIgnoreCase(prefix, 0);
    }

    public boolean startsWithIgnoreCase(ByteString prefix, int toffset) {
        if (toffset + prefix.length() > this.length()) {
            return false;
        }
        return ByteSequences.equalsIgnoreCase(this.source(), this.offset() + toffset,
                prefix.source(), prefix.offset(), prefix.length());
    }

    public boolean endsWith(ByteString suffix) {
        int toffset = this.length() - suffix.length();
        if (toffset < 0) {
            return false;
        }
        return this.startsWith(suffix, toffset);
    }

    public boolean endsWithIgnoreCase(ByteString suffix) {
        int toffset = this.length() - suffix.length();
        if (toffset < 0) {
            return false;
        }
        return this.startsWithIgnoreCase(suffix, toffset);
    }

    public ByteString toASCIIByteString() {
        return new ByteString(this.source(), this.offset(), this.length());
    }

    @Override
    public String toString() {
        return new String(this.source(), this.offset(), this.length(), StandardCharsets.UTF_8);
    }

    @Override
    protected ByteArray newInstance(byte[] source, int offset, int length) {
        return new ByteArray(source, offset, length);
    }

    public static class ByteArrayComparator implements Comparator<ByteArray> {

        @Override
        public int compare(ByteArray str1, ByteArray str2) {
            return ByteSequences.compare(str1, str2);
        }

    }

    public static class ByteArrayIgnoreCaseComparator implements Comparator<ByteArray> {

        @Override
        public int compare(ByteArray str1, ByteArray str2) {
            return ByteSequences.compareIgnoreCase(str1, str2);
        }

    }

}
