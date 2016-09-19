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

public final class Sockaddr_in extends Sockaddr {

    public final int port;
    public final byte[] addr = new byte[4];

    public Sockaddr_in(ByteBuffer buffer) {
        super(NativeNet.AF_INET);
        this.port = buffer.order(ByteOrder.BIG_ENDIAN).getShort() & 0xFFFF;
        buffer.order(ByteOrder.nativeOrder());
        buffer.get(this.addr);
    }

    public Sockaddr_in(int port, byte[] addr) {
        super(NativeNet.AF_INET);
        this.port = port;
        if (addr != null) {
            System.arraycopy(addr, 0, this.addr, 0, this.addr.length);
        }
    }

    @Override
    public byte[] toBytes() {
        byte[] array = new byte[2 + 2 + 4 + 8];
        ByteBuffer buffer = ByteBuffer.wrap(array);
        buffer.order(ByteOrder.nativeOrder()).putShort(this.family);
        buffer.order(ByteOrder.BIG_ENDIAN).putShort((short) this.port);
        buffer.order(ByteOrder.nativeOrder());
        buffer.put(this.addr);
        return array;
    }
}
