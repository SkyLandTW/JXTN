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

import java.lang.reflect.Field;
import java.nio.channels.FileChannel;
import sun.nio.ch.FileChannelImpl;

final class IOInternals {

    private static final Field FileChannelImpl_fd;
    private static final Field FileChannelImpl_path;

    static {
        try {
            FileChannelImpl_fd = FileChannelImpl.class.getDeclaredField("fd");
            FileChannelImpl_fd.setAccessible(true);
            FileChannelImpl_path = FileChannelImpl.class.getDeclaredField("path");
            FileChannelImpl_path.setAccessible(true);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toString(FileChannel channel) {
        if (channel instanceof FileChannelImpl) {
            FileChannelImpl impl = (FileChannelImpl) channel;
            String path;
            try {
                path = (String) FileChannelImpl_path.get(impl);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e);
            }
            if (path != null) {
                return path;
            }
            int fd;
            try {
                fd = (int) FileChannelImpl_fd.get(impl);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e);
            }
            return "fd=" + fd;
        }
        return channel.toString();
    }

    private IOInternals() {
    }
}
