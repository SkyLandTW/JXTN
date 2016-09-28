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
import java.nio.file.Path;
import java.util.function.Consumer;

/**
 * inotify support
 *
 * @author aqd
 */
public final class NativeINotify extends JNIBase {

    /* init1 flags */

    public static final int IN_NONBLOCK = 00004000;
    public static final int IN_CLOEXEC = 02000000;

    /* Supported events suitable for mask parameter of inotify_add_watch */

    public static final int IN_ACCESS = 0x00000001; /* File was accessed. */
    public static final int IN_MODIFY = 0x00000002; /* File was modified. */
    public static final int IN_ATTRIB = 0x00000004; /* Metadata changed. */
    public static final int IN_CLOSE_WRITE = 0x00000008; /* Writtable file was closed. */
    public static final int IN_CLOSE_NOWRITE = 0x00000010; /* Unwrittable file closed. */
    public static final int IN_CLOSE = (IN_CLOSE_WRITE | IN_CLOSE_NOWRITE); /* Close. */
    public static final int IN_OPEN = 0x00000020; /* File was opened. */
    public static final int IN_MOVED_FROM = 0x00000040; /* File was moved from X. */
    public static final int IN_MOVED_TO = 0x00000080; /* File was moved to Y. */
    public static final int IN_MOVE = (IN_MOVED_FROM | IN_MOVED_TO); /* Moves. */
    public static final int IN_CREATE = 0x00000100; /* Subfile was created. */
    public static final int IN_DELETE = 0x00000200; /* Subfile was deleted. */
    public static final int IN_DELETE_SELF = 0x00000400; /* Self was deleted. */
    public static final int IN_MOVE_SELF = 0x00000800; /* Self was moved. */

    /* Events sent by the kernel. */

    public static final int IN_UNMOUNT = 0x00002000; /* Backing fs was unmounted. */
    public static final int IN_Q_OVERFLOW = 0x00004000; /* Event queued overflowed. */
    public static final int IN_IGNORED = 0x00008000; /* File was ignored. */

    /* Special flags. */

    public static final int IN_ONLYDIR = 0x01000000; /* Only watch the path if it is a directory. */
    public static final int IN_DONT_FOLLOW = 0x02000000; /* Do not follow a sym link. */
    public static final int IN_EXCL_UNLINK = 0x04000000; /* Exclude events on unlinked objects. */
    public static final int IN_MASK_ADD = 0x20000000; /* Add to the mask of an already existing watch. */
    public static final int IN_ISDIR = 0x40000000; /* Event occurred against dir. */
    public static final int IN_ONESHOT = 0x80000000; /* Only send event once. */

    public static final int IN_ALL_EVENTS = (IN_ACCESS | IN_MODIFY | IN_ATTRIB | IN_CLOSE_WRITE
            | IN_CLOSE_NOWRITE | IN_OPEN | IN_MOVED_FROM
            | IN_MOVED_TO | IN_CREATE | IN_DELETE
            | IN_DELETE_SELF | IN_MOVE_SELF);

    /* */

    private static final int DEFAULT_INOTIFY_BUFFER_SIZE = INotifyEvent.BUFFER_SIZE << 8;

    /* inotify_init */

    public static native int init();

    /* inotify_init1 */

    public static native int init1(int flags);

    /* add_watch */

    public static int add_watch(int fd, Path pathname, int mask) {
        return add_watch(fd, tPath(pathname), mask);
    }

    public static int add_watch(int fd, String pathname, int mask) {
        return add_watch(fd, tPath(pathname), mask);
    }

    private static native int add_watch(int fd, byte[] pathname, int mask);

    /* rm_watch */

    public static native int rm_watch(int fd, int wd);

    /* read */

    public static long read(int fd, Consumer<INotifyEvent> eventConsumer) {
        return read(fd, eventConsumer, DEFAULT_INOTIFY_BUFFER_SIZE);
    }

    public static long read(int fd, Consumer<INotifyEvent> eventConsumer, int bufferLength) {
        byte[] inotify_ary = new byte[Math.max(bufferLength, INotifyEvent.BUFFER_SIZE)];
        long total = 0;
        long ret;
        while ((ret = NativeIO.readb(fd, inotify_ary)) > 0) {
            ByteBuffer inotify_buf = ByteBuffer.wrap(inotify_ary);
            inotify_buf.limit((int) ret);
            total += ret;
            while (inotify_buf.hasRemaining()) {
                INotifyEvent event = new INotifyEvent(inotify_buf);
                eventConsumer.accept(event);
            }
        }
        if (ret != -1) {
            return total;
        } else {
            return ret;
        }
    }

    private NativeINotify() {
    }
}
