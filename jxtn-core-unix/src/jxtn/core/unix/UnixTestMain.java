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

public final class UnixTestMain {

    public static void main(String[] args) {
        if (args.length <= 0) {
            throw new IllegalArgumentException("Must specified a network interface name");
        }
        String ifName = args[0];
        do {
            int sockfd = NativeNetCap.openRaw();
            if (sockfd == -1) {
                System.err.println("openRaw: " + NativeErrno.errName());
                break;
            }
            if (NativeNetCap.setPromiscuous(sockfd, ifName, true) == -1) {
                System.err.println("setPromiscuous: " + NativeErrno.errName());
                break;
            }
            if (NativeNetCap.setReuseAddr(sockfd) == -1) {
                System.err.println("setReuseAddr: " + NativeErrno.errName());
                break;
            }
            if (NativeNetCap.setInterface(sockfd, ifName) == -1) {
                System.err.println("setInterface: " + NativeErrno.errName());
                break;
            }
            if (NativeNetCap.bindInterface(sockfd, ifName) == -1) {
                System.err.println("bindInterface: " + NativeErrno.errName());
                break;
            }
            if (NativeNetCap.setRcvBuf(sockfd, 1048576 * 10) == -1) {
                System.out.println("setRcvBuf: " + NativeErrno.errName());
            }
            if (NativeNetCap.setRcvBufForce(sockfd, 1048576 * 10) == -1) {
                System.out.println("setRcvBufForce: " + NativeErrno.errName());
            }
            if (NativeNetCap.setRcvTimeo(sockfd, 0L, 500 * 1000L) == -1) {
                System.out.println("setRcvTimeo: " + NativeErrno.errName());
            }
            while (true) {
                byte[] packet = NativeNetCap.recvfrom(sockfd);
                if (packet == null) {
                    System.out.println("recvfrom: " + NativeErrno.errName());
                    continue;
                }
                System.out.print(new String(packet));
            }
        } while (false);
    }

}
