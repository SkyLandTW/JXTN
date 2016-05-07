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

import java.util.Objects;
import jxtn.core.axi.collections.AbstractIterator;

/**
 * Splitting iterator for {@link ByteArray} or {@link ByteString}
 *
 * @param <T> {@link ByteArray} or {@link ByteString}
 * @author aqd
 */
public final class ByteSequenceSplittingIterator<T extends ByteSequence<T>>
        extends AbstractIterator<T> {

    private final T string;
    private final T separator;

    private int offset = 0;
    private boolean endNext = false;

    public ByteSequenceSplittingIterator(T string, T separator) {
        this.string = Objects.requireNonNull(string);
        this.separator = Objects.requireNonNull(separator);
        if (separator.isEmpty()) {
            throw new IllegalArgumentException("separator");
        }
    }

    @Override
    protected T fetchNext() {
        if (this.endNext) {
            return this.end();
        }
        int sepIndex = this.string.indexOf(this.separator, this.offset);
        if (sepIndex < 0) {
            this.endNext = true;
            return this.string.substring(this.offset);
        } else {
            T part = this.string.substring(this.offset, sepIndex);
            this.offset = sepIndex + this.separator.length();
            return part;
        }
    }

}
