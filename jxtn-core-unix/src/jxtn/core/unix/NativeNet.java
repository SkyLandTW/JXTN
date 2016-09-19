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

/**
 * Network-related syscall wrappers
 *
 * @author aqd
 */
public final class NativeNet extends JNIBase {

    public static final int SOCK_STREAM = 1; /* Sequenced, reliable, connection-based byte streams. */
    public static final int SOCK_DGRAM = 2; /* Connectionless, unreliable datagrams of fixed maximum length. */
    public static final int SOCK_RAW = 3; /* Raw protocol interface. */
    public static final int SOCK_RDM = 4; /* Reliably-delivered messages. */
    public static final int SOCK_SEQPACKET = 5; /* Sequenced, reliable, connection-based,
                                                   datagrams of fixed maximum length. */
    public static final int SOCK_DCCP = 6; /* Datagram Congestion Control Protocol. */
    public static final int SOCK_PACKET = 10; /* Linux specific way of getting packets at the dev level.
                                                 For writing rarp and other similar things on the user level. */

    /* Flags to be ORed into the type parameter of socket and socketpair and
       used for the flags parameter of paccept. */

    public static final int SOCK_CLOEXEC = 02000000; /* Atomically set close-on-exec flag for the
                                                     new descriptor(s). */
    public static final int SOCK_NONBLOCK = 00004000; /* Atomically mark descriptor(s) as
                                                      non-blocking. */

    public static final short PF_UNSPEC = 0; /* Unspecified. */
    public static final short PF_LOCAL = 1; /* Local to host (pipes and file-domain). */
    public static final short PF_UNIX = PF_LOCAL; /* POSIX name for PF_LOCAL. */
    public static final short PF_FILE = PF_LOCAL; /* Another non-standard name for PF_LOCAL. */
    public static final short PF_INET = 2; /* IP protocol family. */
    public static final short PF_AX25 = 3; /* Amateur Radio AX.25. */
    public static final short PF_IPX = 4; /* Novell Internet Protocol. */
    public static final short PF_APPLETALK = 5; /* Appletalk DDP. */
    public static final short PF_NETROM = 6; /* Amateur radio NetROM. */
    public static final short PF_BRIDGE = 7; /* Multiprotocol bridge. */
    public static final short PF_ATMPVC = 8; /* ATM PVCs. */
    public static final short PF_X25 = 9; /* Reserved for X.25 project. */
    public static final short PF_INET6 = 10; /* IP version 6. */
    public static final short PF_ROSE = 11; /* Amateur Radio X.25 PLP. */
    public static final short PF_DECnet = 12; /* Reserved for DECnet project. */
    public static final short PF_NETBEUI = 13; /* Reserved for 802.2LLC project. */
    public static final short PF_SECURITY = 14; /* Security callback pseudo AF. */
    public static final short PF_KEY = 15; /* PF_KEY key management API. */
    public static final short PF_NETLINK = 16;
    public static final short PF_ROUTE = PF_NETLINK; /* Alias to emulate 4.4BSD. */
    public static final short PF_PACKET = 17; /* Packet family. */
    public static final short PF_ASH = 18; /* Ash. */
    public static final short PF_ECONET = 19; /* Acorn Econet. */
    public static final short PF_ATMSVC = 20; /* ATM SVCs. */
    public static final short PF_RDS = 21; /* RDS sockets. */
    public static final short PF_SNA = 22; /* Linux SNA Project */
    public static final short PF_IRDA = 23; /* IRDA sockets. */
    public static final short PF_PPPOX = 24; /* PPPoX sockets. */
    public static final short PF_WANPIPE = 25; /* Wanpipe API sockets. */
    public static final short PF_LLC = 26; /* Linux LLC. */
    public static final short PF_IB = 27; /* Native InfiniBand address. */
    public static final short PF_MPLS = 28; /* MPLS. */
    public static final short PF_CAN = 29; /* Controller Area Network. */
    public static final short PF_TIPC = 30; /* TIPC sockets. */
    public static final short PF_BLUETOOTH = 31; /* Bluetooth sockets. */
    public static final short PF_IUCV = 32; /* IUCV sockets. */
    public static final short PF_RXRPC = 33; /* RxRPC sockets. */
    public static final short PF_ISDN = 34; /* mISDN sockets. */
    public static final short PF_PHONET = 35; /* Phonet sockets. */
    public static final short PF_IEEE802154 = 36; /* IEEE 802.15.4 sockets. */
    public static final short PF_CAIF = 37; /* CAIF sockets. */
    public static final short PF_ALG = 38; /* Algorithm sockets. */
    public static final short PF_NFC = 39; /* NFC sockets. */
    public static final short PF_VSOCK = 40; /* vSockets. */
    public static final short PF_MAX = 41; /* For now.. */

    public static final short AF_UNSPEC = PF_UNSPEC;
    public static final short AF_LOCAL = PF_LOCAL;
    public static final short AF_UNIX = PF_UNIX;
    public static final short AF_FILE = PF_FILE;
    public static final short AF_INET = PF_INET;
    public static final short AF_AX25 = PF_AX25;
    public static final short AF_IPX = PF_IPX;
    public static final short AF_APPLETALK = PF_APPLETALK;
    public static final short AF_NETROM = PF_NETROM;
    public static final short AF_BRIDGE = PF_BRIDGE;
    public static final short AF_ATMPVC = PF_ATMPVC;
    public static final short AF_X25 = PF_X25;
    public static final short AF_INET6 = PF_INET6;
    public static final short AF_ROSE = PF_ROSE;
    public static final short AF_DECnet = PF_DECnet;
    public static final short AF_NETBEUI = PF_NETBEUI;
    public static final short AF_SECURITY = PF_SECURITY;
    public static final short AF_KEY = PF_KEY;
    public static final short AF_NETLINK = PF_NETLINK;
    public static final short AF_ROUTE = PF_ROUTE;
    public static final short AF_PACKET = PF_PACKET;
    public static final short AF_ASH = PF_ASH;
    public static final short AF_ECONET = PF_ECONET;
    public static final short AF_ATMSVC = PF_ATMSVC;
    public static final short AF_RDS = PF_RDS;
    public static final short AF_SNA = PF_SNA;
    public static final short AF_IRDA = PF_IRDA;
    public static final short AF_PPPOX = PF_PPPOX;
    public static final short AF_WANPIPE = PF_WANPIPE;
    public static final short AF_LLC = PF_LLC;
    public static final short AF_IB = PF_IB;
    public static final short AF_MPLS = PF_MPLS;
    public static final short AF_CAN = PF_CAN;
    public static final short AF_TIPC = PF_TIPC;
    public static final short AF_BLUETOOTH = PF_BLUETOOTH;
    public static final short AF_IUCV = PF_IUCV;
    public static final short AF_RXRPC = PF_RXRPC;
    public static final short AF_ISDN = PF_ISDN;
    public static final short AF_PHONET = PF_PHONET;
    public static final short AF_IEEE802154 = PF_IEEE802154;
    public static final short AF_CAIF = PF_CAIF;
    public static final short AF_ALG = PF_ALG;
    public static final short AF_NFC = PF_NFC;
    public static final short AF_VSOCK = PF_VSOCK;
    public static final short AF_MAX = PF_MAX;

    public static final int SOL_RAW = 255;
    public static final int SOL_DECNET = 261;
    public static final int SOL_X25 = 262;
    public static final int SOL_PACKET = 263;
    public static final int SOL_ATM = 264; /* ATM layer (cell level). */
    public static final int SOL_AAL = 265; /* ATM Adaption Layer (packet level). */
    public static final int SOL_IRDA = 266;

    public static final int _SS_MAXSIZE = 128;

    /* socket */

    public static native int socket(int domain, int type, int protocol);

    /* bind */

    public static int bind(int sockfd, Sockaddr addr) {
        return bind(sockfd, addr == null ? null : addr.toBytes());
    }

    static native int bind(int sockfd, byte[] addr);

    /* listen */

    public static native int listen(int sockfd, int backlog);

    /* accept */

    public static int accept(int sockfd, Out<Sockaddr> addr_out) {
        byte[] addr = addr_out != null ? new byte[_SS_MAXSIZE] : null;
        int ret = accept(sockfd, addr);
        if (ret != -1 && addr_out != null) {
            addr_out.set(Sockaddr.from(ByteBuffer.wrap(addr)));
        }
        return ret;
    }

    static native int accept(int sockfd, byte[] addr);

    /* accept4 */

    public static int accept4(int sockfd, Out<Sockaddr> addr_out, int flags) {
        byte[] addr = addr_out != null ? new byte[_SS_MAXSIZE] : null;
        int ret = accept4(sockfd, addr, flags);
        if (ret != -1 && addr_out != null) {
            addr_out.set(Sockaddr.from(ByteBuffer.wrap(addr)));
        }
        return ret;
    }

    static native int accept4(int sockfd, byte[] addr, int flags);
}
