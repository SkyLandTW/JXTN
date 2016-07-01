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

public final class INotifyEvent {

    public static final int BUFFER_SIZE = 4 + 4 + 4 + 4 + 256;

    public final int wd;
    public final long mask;
    public final long cookie;
    public final int len;
    public final byte[] name;

    private String nameStr;

    public String name() {
        if (this.nameStr == null) {
            this.nameStr = new String(this.name, 0, this.name.length - 1);
        }
        return this.nameStr;
    }

    INotifyEvent(ByteBuffer buffer) {
        buffer.order(ByteOrder.nativeOrder());
        this.wd = buffer.getInt();                      // 4
        this.mask = buffer.getInt() & 0xFFFFFFFF;       // 4
        this.cookie = buffer.getInt() & 0xFFFFFFFF;     // 4
        this.len = buffer.getInt() & 0xFFFFFFFF;        // 4
        this.name = new byte[this.len];
        buffer.get(this.name);                          // [len]
    }
}
