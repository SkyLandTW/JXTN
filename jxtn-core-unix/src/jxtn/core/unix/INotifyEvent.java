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
import java.util.ArrayList;
import java.util.List;

public final class INotifyEvent {

    public static final int BUFFER_SIZE = 4 + 4 + 4 + 4 + NativeLimits.NAME_MAX + 1;

    public static String maskName(long mask) {
        List<String> maskNames = new ArrayList<>();
        if ((mask & NativeINotify.IN_ACCESS) != 0) {
            maskNames.add("IN_ACCESS");
        }
        if ((mask & NativeINotify.IN_MODIFY) != 0) {
            maskNames.add("IN_MODIFY");
        }
        if ((mask & NativeINotify.IN_ATTRIB) != 0) {
            maskNames.add("IN_ATTRIB");
        }
        if ((mask & NativeINotify.IN_CLOSE_WRITE) != 0) {
            maskNames.add("IN_CLOSE_WRITE");
        }
        if ((mask & NativeINotify.IN_CLOSE_NOWRITE) != 0) {
            maskNames.add("IN_CLOSE_NOWRITE");
        }
        if ((mask & NativeINotify.IN_OPEN) != 0) {
            maskNames.add("IN_OPEN");
        }
        if ((mask & NativeINotify.IN_MOVED_FROM) != 0) {
            maskNames.add("IN_MOVED_FROM");
        }
        if ((mask & NativeINotify.IN_MOVED_TO) != 0) {
            maskNames.add("IN_MOVED_TO");
        }
        if ((mask & NativeINotify.IN_CREATE) != 0) {
            maskNames.add("IN_CREATE");
        }
        if ((mask & NativeINotify.IN_DELETE) != 0) {
            maskNames.add("IN_DELETE");
        }
        if ((mask & NativeINotify.IN_DELETE_SELF) != 0) {
            maskNames.add("IN_DELETE_SELF");
        }
        if ((mask & NativeINotify.IN_MOVE_SELF) != 0) {
            maskNames.add("IN_MOVE_SELF");
        }
        if ((mask & NativeINotify.IN_UNMOUNT) != 0) {
            maskNames.add("IN_UNMOUNT");
        }
        if ((mask & NativeINotify.IN_Q_OVERFLOW) != 0) {
            maskNames.add("IN_Q_OVERFLOW");
        }
        if ((mask & NativeINotify.IN_IGNORED) != 0) {
            maskNames.add("IN_IGNORED");
        }
        if ((mask & NativeINotify.IN_ISDIR) != 0) {
            maskNames.add("IN_ISDIR");
        }
        return String.join(",", maskNames);
    }

    public final int wd;
    public final long mask;
    public final long cookie;
    public final int len;
    public final byte[] name;

    private String nameStr;

    public String name() {
        if (this.nameStr == null) {
            this.nameStr = CStrings.toString(this.name);
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

    @Override
    public String toString() {
        return this.wd + "|" + maskName(this.mask) + "|" + this.name();
    }
}
