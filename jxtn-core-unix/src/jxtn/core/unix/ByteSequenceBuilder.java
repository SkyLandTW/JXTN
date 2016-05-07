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

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Builder of {@link ByteSequence}
 *
 * @author aqd
 */
public final class ByteSequenceBuilder implements CharSequence, ByteWriter<RuntimeException> {

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private static final int INITIAL_SIZE = 16;

    private byte[] buf;
    private int count;

    public ByteSequenceBuilder() {
        this(INITIAL_SIZE);
    }

    public ByteSequenceBuilder(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Negative initial size: " + size);
        }
        this.buf = new byte[size];
    }

    public byte[] buffer() {
        return this.buf;
    }

    @Override
    public int length() {
        return this.count;
    }

    public void length(int newCount) {
        if (newCount > this.count) {
            throw new IllegalArgumentException();
        }
        this.count = newCount;
    }

    public byte byteAt(int index) {
        return this.buf[index];
    }

    @Deprecated
    @Override
    public char charAt(int index) {
        return (char) this.buf[index];
    }

    public void append(byte b) {
        this.ensureCapacity(this.count + 1);
        this.buf[this.count] = b;
        this.count += 1;
    }

    public void append(ByteSequence<?> string) {
        this.ensureCapacity(this.count + string.length());
        ByteSequences.copyTo(string.source(), string.offset(), this.buf, this.count, string.length());
        this.count += string.length();
    }

    public void append(byte[] b) {
        this.append(b, 0, b.length);
    }

    public void append(byte b[], int off, int len) {
        if ((off < 0) || (off > b.length) || (len < 0) || ((off + len) - b.length > 0)) {
            throw new IndexOutOfBoundsException();
        }
        this.ensureCapacity(this.count + len);
        ByteSequences.copyTo(b, off, this.buf, this.count, len);
        this.count += len;
    }

    @Override
    public void writeByte(byte b) throws RuntimeException {
        this.append(b);
    }

    @Override
    public void writeBytes(ByteSequence<?> bytes) throws RuntimeException {
        this.append(bytes);
    }

    @Deprecated
    @Override
    public CharSequence subSequence(int start, int end) {
        return new ByteString(this.buf, start, end - start);
    }

    public byte[] toArray() {
        return Arrays.copyOf(this.buf, this.count);
    }

    public ByteArray toByteArray() {
        return new ByteArray(this.buf, 0, this.count);
    }

    public ByteString toByteString() {
        return new ByteString(this.buf, 0, this.count);
    }

    @Override
    public String toString() {
        return new String(this.buf, 0, this.count, StandardCharsets.UTF_8);
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity - this.buf.length > 0) {
            this.grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = this.buf.length;
        int newCapacity = oldCapacity << 1;
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            newCapacity = hugeCapacity(minCapacity);
        }
        this.buf = Arrays.copyOf(this.buf, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE)
                ? Integer.MAX_VALUE
                : MAX_ARRAY_SIZE;
    }

}
