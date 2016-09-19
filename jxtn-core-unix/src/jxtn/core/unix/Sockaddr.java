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
import java.nio.ByteOrder;

public abstract class Sockaddr {

    public static final Sockaddr from(ByteBuffer buffer) {
        short family = buffer.order(ByteOrder.nativeOrder()).getShort();
        switch (family) {
        case NativeNet.AF_UNIX:
            return new Sockaddr_un(buffer);
        case NativeNet.AF_PACKET:
            return new Sockaddr_ll(buffer);
        case NativeNet.AF_INET:
            return new Sockaddr_in(buffer);
        case NativeNet.AF_INET6:
            return new Sockaddr_in6(buffer);
        default:
            throw new IllegalArgumentException("unsupported family: " + family);
        }
    }

    public final short family;

    protected Sockaddr(short family) {
        this.family = family;
    }

    public abstract byte[] toBytes();
}
