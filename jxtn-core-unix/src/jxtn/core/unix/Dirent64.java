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

/**
 * {@code struct linux_dirent64}
 *
 * @author aqd
 */
public final class Dirent64 {

    public static final byte DT_UNKNOWN = 0; /* The file type is unknown. */
    public static final byte DT_FIFO = 1; /* This is a named pipe (FIFO). */
    public static final byte DT_CHR = 2; /* This is a character device. */
    public static final byte DT_DIR = 3; /* This is a directory. */
    public static final byte DT_BLK = 6; /* This is a block device. */
    public static final byte DT_REG = 8; /* This is a regular file. */
    public static final byte DT_LNK = 10; /* This is a symbolic link. */
    public static final byte DT_SOCK = 12; /* This is a UNIX domain socket. */
    public static final byte DT_WHT = 14; /* */

    /**
     * 64-bit inode number
     */
    public final long d_ino;

    /**
     * 64-bit offset to next structure
     */
    public final long d_off;

    /**
     * Size of this dirent
     */
    public final int d_reclen;

    /**
     * File type
     */
    public final byte d_type;

    /**
     * Filename (null-terminated)
     */
    public final byte[] d_name;

    Dirent64(ByteBuffer buffer) {
        buffer.order(ByteOrder.nativeOrder());
        int start = buffer.position();
        this.d_ino = buffer.getLong();
        this.d_off = buffer.getLong();
        this.d_reclen = buffer.getShort() & 0xFFFF;
        this.d_type = buffer.get();
        this.d_name = CStrings.from(buffer, NativeLimits.NAME_MAX);
        buffer.position(start + this.d_reclen);
    }
}
