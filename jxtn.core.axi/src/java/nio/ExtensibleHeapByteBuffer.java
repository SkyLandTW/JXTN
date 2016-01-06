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
package java.nio;

/**
 * Extensible heap-based {@link ByteBuffer}
 * <p>
 * This class is to serve as the base class for customization on the top of {@link ByteBuffer} and
 * {@link HeapByteBuffer}.
 * </p>
 *
 * @author aqd
 */
public class ExtensibleHeapByteBuffer extends HeapByteBuffer {

    /**
     * Create a new {@link ByteBuffer} wrapper
     *
     * @param buf {@link Buffer#array}
     * @param mark the value of <i>mark</i>
     * @param pos {@link Buffer#position}
     * @param lim {@link Buffer#limit}
     * @param cap {@link Buffer#capacity}
     * @param off {@link Buffer#arrayOffset}
     */
    public ExtensibleHeapByteBuffer(byte[] buf,
            int mark, int pos, int lim, int cap,
            int off) {
        super(buf, mark, pos, lim, cap, off);
    }

    /**
     * Create a new {@link ByteBuffer} wrapper from parameters of a given underlyingBuffer
     * <p>
     * The newly-created {@link ExtensibleHeapByteBuffer} would be independent from {@code underlyingBuffer}.
     * </p>
     *
     * @param underlyingBuffer the underlying buffer to be wrapped
     */
    public ExtensibleHeapByteBuffer(ByteBuffer underlyingBuffer) {
        this((HeapByteBuffer) underlyingBuffer);
    }

    /**
     * Create a new {@link ByteBuffer} wrapper from parameters of a given underlyingBuffer
     * <p>
     * The newly-created {@link ExtensibleHeapByteBuffer} would be independent from {@code underlyingBuffer}.
     * </p>
     *
     * @param underlyingBuffer the underlying buffer to be wrapped
     */
    ExtensibleHeapByteBuffer(HeapByteBuffer underlyingBuffer) {
        super(underlyingBuffer.hb,
                underlyingBuffer.markValue(),
                underlyingBuffer.position(),
                underlyingBuffer.limit(),
                underlyingBuffer.capacity(),
                underlyingBuffer.offset);
    }

    /**
     * Get the position of the <i>mark</i>
     *
     * @return the <i>mark</i>
     */
    protected final int markValue2() {
        return this.markValue();
    }

    @Override
    public ExtensibleHeapByteBuffer slice() {
        return new ExtensibleHeapByteBuffer(this.hb,
                -1,
                0,
                this.remaining(),
                this.remaining(),
                this.position() + this.offset);
    }

    @Override
    public ExtensibleHeapByteBuffer duplicate() {
        return new ExtensibleHeapByteBuffer(this.hb,
                this.markValue(),
                this.position(),
                this.limit(),
                this.capacity(),
                this.offset);
    }

    @Override
    public ExtensibleHeapByteBufferR asReadOnlyBuffer() {
        return new ExtensibleHeapByteBufferR(this.hb,
                this.markValue(),
                this.position(),
                this.limit(),
                this.capacity(),
                this.offset);

    }
}
