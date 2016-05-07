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
 * Searching iterator for {@link ByteArray} or {@link ByteString}
 *
 * @param <T> {@link ByteArray} or {@link ByteString}
 * @author aqd
 */
public final class ByteSequenceSearchingIterator<T extends ByteSequence<T>>
        extends AbstractIterator<T> {

    private final T string;
    private final T target;

    private int offset = 0;

    public ByteSequenceSearchingIterator(T string, T target) {
        this.string = Objects.requireNonNull(string);
        this.target = Objects.requireNonNull(target);
        if (target.isEmpty()) {
            throw new IllegalArgumentException("target");
        }
    }

    @Override
    protected T fetchNext() {
        int index = this.string.indexOf(this.target, this.offset);
        if (index < 0) {
            this.offset = this.string.length();
            return this.end();
        } else {
            T remaining = this.string.substring(index);
            this.offset = index + this.target.length();
            return remaining;
        }
    }

}
