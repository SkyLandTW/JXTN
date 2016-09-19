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

public final class Sockaddr_ll extends Sockaddr {

    public final short protocol;
    public final int ifindex;
    public final short hatype;
    public final byte pkttype;
    public final byte halen;
    public final byte[] addr = new byte[8];

    public Sockaddr_ll(ByteBuffer buffer) {
        super(NativeNet.AF_PACKET);
        this.protocol = buffer.order(ByteOrder.BIG_ENDIAN).getShort();
        buffer.order(ByteOrder.nativeOrder());
        this.ifindex = buffer.getInt();
        this.hatype = buffer.getShort();
        this.pkttype = buffer.get();
        this.halen = buffer.get();
        buffer.get(this.addr);
    }

    @Override
    public byte[] toBytes() {
        byte[] array = new byte[2 + 2 + 4 + 2 + 1 + 1 + 8];
        ByteBuffer buffer = ByteBuffer.wrap(array);
        buffer.order(ByteOrder.nativeOrder()).putShort(this.family);
        buffer.order(ByteOrder.BIG_ENDIAN).putShort(this.protocol);
        buffer.order(ByteOrder.nativeOrder());
        buffer.putInt(this.ifindex);
        buffer.putShort(this.hatype);
        buffer.put(this.pkttype);
        buffer.put(this.halen);
        buffer.put(this.addr);
        return array;
    }
}
