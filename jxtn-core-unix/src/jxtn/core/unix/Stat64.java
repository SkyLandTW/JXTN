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
 * {@code struct stat64}
 *
 * @author aqd
 */
public final class Stat64 {

    private static long major(long dev) {
        return ((dev >>> 8) & 0xfff) | ((int) (dev >>> 32) & ~0xfff);
    }

    private static long minor(long dev) {
        return (dev & 0xff) | ((int) (dev >>> 12) & ~0xff);
    }

    /**
     * ID of device containing file
     */
    public final long st_dev;

    /**
     * inode number
     */
    public final long st_ino;

    /**
     * number of hard links
     */
    public final long st_nlink;

    /**
     * protection
     */
    public final int st_mode;

    /**
     * user ID of owner
     */
    public final int st_uid;

    /**
     * group ID of owner
     */
    public final int st_gid;

    /**
     * device ID (if special file)
     */
    public final long st_rdev;

    /**
     * total size, in bytes
     */
    public final long st_size;

    /**
     * blocksize for filesystem I/O
     */
    public final long st_blksize;

    /**
     * number of 512B blocks allocated
     */
    public final long st_blocks;

    /**
     * time of last access
     */
    public final Timespec st_atim;

    /**
     * time of last modification
     */
    public final Timespec st_mtim;

    /**
     * time of last status change
     */
    public final Timespec st_ctim;

    Stat64(ByteBuffer buffer) {
        buffer.order(ByteOrder.nativeOrder());
        this.st_dev = buffer.getLong();
        this.st_ino = buffer.getLong();
        this.st_nlink = buffer.getLong();
        this.st_mode = buffer.getInt();
        this.st_uid = buffer.getInt();
        this.st_gid = buffer.getInt();
        buffer.getInt();
        this.st_rdev = buffer.getLong();
        this.st_size = buffer.getLong();
        this.st_blksize = buffer.getLong();
        this.st_blocks = buffer.getLong();
        this.st_atim = new Timespec(buffer);
        this.st_mtim = new Timespec(buffer);
        this.st_ctim = new Timespec(buffer);
        buffer.getLong(); // reserved[0]
        buffer.getLong(); // reserved[1]
        buffer.getLong(); // reserved[2]
    }

    public String getDescription() {
        return String.join(System.lineSeparator(),
                "device: " + major(this.st_dev) + "/" + minor(this.st_dev),
                "inode: " + this.st_ino,
                "mode: " + StringFormat.padLeft(Integer.toOctalString(this.st_mode), 7, '0'),
                "links: " + this.st_nlink,
                "UID: " + this.st_uid,
                "GID: " + this.st_gid,
                "special: " + major(this.st_rdev) + "/" + minor(this.st_rdev),
                "size: " + this.st_size,
                "block size: " + this.st_blksize,
                "blocks: " + this.st_blocks,
                "last access: " + this.st_atim,
                "last modified: " + this.st_mtim,
                "last changed: " + this.st_ctim);
    }

}
