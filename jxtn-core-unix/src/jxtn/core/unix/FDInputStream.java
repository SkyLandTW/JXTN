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

import java.io.IOException;
import java.io.InputStream;

public final class FDInputStream extends InputStream {

    private final int fd;
    private final boolean keepOpen;
    private boolean closed;

    public FDInputStream(int fd) {
        this(fd, false);
    }

    public FDInputStream(int fd, boolean keepOpen) {
        assert fd >= 0;
        this.fd = fd;
        this.keepOpen = keepOpen;
    }

    @Override
    public int read() throws IOException {
        byte[] b = new byte[1];
        if (this.read(b, 0, b.length) == 0) {
            return -1;
        }
        return b[0] & 0xFF;
    }

    @Override
    public int read(byte b[], int off, int len) throws IOException {
        if (off < 0 || off + len > b.length) {
            throw new IllegalArgumentException("b: " + (off + len));
        }
        long ret = NativeIO.read(this.fd, b, off, len);
        if (ret == -1L) {
            throw new IOException("read: " + NativeErrno.errName());
        }
        return (int) ret;
    }

    @Override
    public void close() throws IOException {
        if (!this.closed && !this.keepOpen) {
            try {
                if (NativeIO.close(this.fd) == -1) {
                    throw new IOException("close: " + NativeErrno.errName());
                }
            } finally {
                this.closed = true;
            }
        }
    }
}
