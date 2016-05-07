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
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;

/**
 * Primitive UTF-8 byte string - each char is represented by one byte
 * <ul>
 * <li>No text character set</li>
 * <li>Not NULL terminated</li>
 * <li>Can represent part of byte[], presumably immutable</li>
 * <li>Unlike {@link String}, {@link ByteString} doesn't make copy implicitly and have to rely on original array - which
 * prevents {@link #source()} from being released even if {@link ByteString} only accounts for a tiny part of it. Beware
 * of implicit GC and mutability issues.</li>
 * </ul>
 * <p>
 * This string should be used for all UTF-8 text processing to avoid unnecessary conversion.
 * </p>
 *
 * @author aqd
 */
public final class ByteString extends ByteSequence<ByteString> {

    public static final ByteString EMPTY = new ByteString(new byte[0]);

    public static final ByteStringComparator comparator = new ByteStringComparator();
    public static final ByteStringIgnoreCaseComparator comparatorIgnoreCase = new ByteStringIgnoreCaseComparator();

    private static final long serialVersionUID = 1L;

    public static ByteString copy(byte[] source) {
        return new ByteString(ByteSequences.copy(source));
    }

    public static ByteString copy(byte[] source, int offset, int length) {
        return new ByteString(ByteSequences.copy(source, offset, length));
    }

    public static ByteString concat(ByteString... strings) {
        if (strings.length == 0) {
            return EMPTY;
        }
        int totalLen = 0;
        for (ByteString str : strings) {
            totalLen += str.length();
        }
        for (ByteString str : strings) {
            if (str.length() == totalLen) {
                return str;
            }
        }
        byte[] newbuf = new byte[totalLen];
        int filledLen = 0;
        for (ByteString str : strings) {
            ByteSequences.copyTo(str.source(), str.offset(), newbuf, filledLen, str.length());
            filledLen += str.length();
        }
        return new ByteString(newbuf);
    }

    public static ByteString join(ByteString delimited, Iterable<ByteString> elements) {
        ByteSequenceBuilder builder = new ByteSequenceBuilder();
        boolean first = true;
        for (ByteString sp : elements) {
            if (first) {
                first = false;
            } else {
                builder.append(delimited);
            }
            builder.append(sp);
        }
        return builder.toByteString();
    }

    public ByteString(String source) {
        this(source, StandardCharsets.UTF_8);
    }

    public ByteString(String source, Charset charset) {
        this(source.getBytes(charset));
    }

    public ByteString(ByteBuffer buffer) {
        this(buffer, buffer.remaining());
    }

    public ByteString(ByteBuffer buffer, int length) {
        this(buffer.array(), buffer.arrayOffset() + buffer.position(), length);
    }

    public ByteString(byte[] source) {
        this(source, 0, source.length);
    }

    public ByteString(byte[] source, int offset, int length) {
        super(source, offset, length);
    }

    @Override
    public ByteArray asByteArray() {
        return new ByteArray(this.source(), this.offset(), this.length());
    }

    @Override
    public ByteString clone() {
        try {
            return (ByteString) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String toString() {
        return new String(this.source(), this.offset(), this.length(), StandardCharsets.UTF_8);
    }

    public boolean verifyUTF8() {
        return FastUTF8.verify(this.source(), this.offset(), this.length());
    }

    @Override
    protected ByteString newInstance(byte[] source, int offset, int length) {
        return new ByteString(source, offset, length);
    }

    public static class ByteStringComparator implements Comparator<ByteString> {

        @Override
        public int compare(ByteString str1, ByteString str2) {
            return ByteSequences.compare(str1, str2);
        }

    }

    public static class ByteStringIgnoreCaseComparator implements Comparator<ByteString> {

        @Override
        public int compare(ByteString str1, ByteString str2) {
            return ByteSequences.compareIgnoreCase(str1, str2);
        }

    }

}
