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

#define _GNU_SOURCE

#include <arpa/inet.h>
#include <errno.h>
#include <linux/if_ether.h>
#include <linux/if_packet.h>
#include <net/if.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/ioctl.h>
#include <sys/socket.h>
#include <unistd.h>

#include "internals.h"

/*
 * Initialize network socket on given interface name for raw packet capture
 *
 * source: https://gist.github.com/austinmarton/2862515
 */

#define FRAME_MAXSIZE    (65536)

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeNetCap_openRaw(JNIEnv *env, jclass thisObj) {
    // ETH_P_IP cannot capture outgoing packets from this machine
    // ETH_P_ALL captures all but packets are in wrong order
    return ERR(socket(PF_PACKET, SOCK_RAW, htons(ETH_P_ALL)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeNetCap_setPromiscuous(JNIEnv *env, jclass thisObj,
        jint sockfd, jstring ifName, jboolean value) {
    // query interface $ifName
    struct ifreq ifopts;
    if (ifName) {
        jboolean isCopy = 0;
        const char* ifNameChars = (*env)->GetStringUTFChars(env, ifName, &isCopy);
        strncpy(ifopts.ifr_name, ifNameChars, IFNAMSIZ - 1);
        (*env)->ReleaseStringUTFChars(env, ifName, ifNameChars);
    }
    if (ioctl(sockfd, SIOCGIFFLAGS, &ifopts) == -1) {
        return ERR(-1);
    }
    // setup interface $ifName
    if (value) {
        ifopts.ifr_flags |= IFF_PROMISC;
    } else {
        ifopts.ifr_flags &= ~IFF_PROMISC;
    }
    return ERR(ioctl(sockfd, SIOCSIFFLAGS, &ifopts));
    //
#if 0
    struct packet_mreq mr;
    memset(&mr, 0, sizeof ( mr));
    mr.mr_ifindex = ifopts.ifr_ifindex;
    mr.mr_type = PACKET_MR_PROMISC;
    if (setsockopt(sockfd, SOL_PACKET, PACKET_ADD_MEMBERSHIP, &mr, sizeof (mr)) == -1) {
        return ERR(-1);
    }
#endif
#if 0
    if (setsockopt(sockfd, SOL_PACKET, PACKET_RECV_OUTPUT, NULL, 0) == -1) {
        return ERR(-1);
    }
#endif
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeNetCap_setReuseAddr(JNIEnv *env, jclass thisObj,
        jint sockfd) {
    int sockopt = 0;
    return ERR(setsockopt(sockfd, SOL_SOCKET, SO_REUSEADDR, &sockopt, sizeof sockopt));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeNetCap_setInterface(JNIEnv *env, jclass thisObj,
        jint sockfd, jstring ifName) {
    jboolean isCopy = 0;
    const char* ifNameChars = (*env)->GetStringUTFChars(env, ifName, &isCopy);
    int ret = setsockopt(sockfd, SOL_SOCKET, SO_BINDTODEVICE, ifNameChars, IFNAMSIZ - 1);
    (*env)->ReleaseStringUTFChars(env, ifName, ifNameChars);
    return ERR(ret);
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeNetCap_bindInterface(JNIEnv *env, jclass thisObj,
        jint sockfd, jstring ifName) {
    struct sockaddr_ll sock_address;
    memset(&sock_address, 0, sizeof(sock_address));
    sock_address.sll_family = PF_PACKET;
    sock_address.sll_protocol = htons(ETH_P_ALL);
    jboolean isCopy = 0;
    const char* ifNameChars = (*env)->GetStringUTFChars(env, ifName, &isCopy);
    sock_address.sll_ifindex = SI(if_nametoindex(ifNameChars));
    (*env)->ReleaseStringUTFChars(env, ifName, ifNameChars);
    return ERR(bind(sockfd, (struct sockaddr*) &sock_address, sizeof(sock_address)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeNetCap_setRcvBuf(JNIEnv *env, jclass thisObj,
        jint sockfd, jint size) {
    return ERR(setsockopt(sockfd, SOL_SOCKET, SO_RCVBUF, &size, sizeof(size)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeNetCap_setRcvBufForce(JNIEnv *env, jclass thisObj,
        jint sockfd, jint size) {
    return ERR(setsockopt(sockfd, SOL_SOCKET, SO_RCVBUFFORCE, &size, sizeof(size)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeNetCap_setRcvTimeo(JNIEnv *env, jclass thisObj,
        jint sockfd, jlong tv_sec, jlong tv_usec) {
    struct timeval timeout = { tv_sec, tv_usec };
    return ERR(setsockopt(sockfd, SOL_SOCKET, SO_RCVTIMEO, &timeout, sizeof(struct timeval)));
}

JNIEXPORT jbyteArray JNICALL Java_jxtn_core_unix_NativeNetCap_recvfrom(JNIEnv *env, jclass thisObj,
        jint sockfd) {
    uint8_t buf[FRAME_MAXSIZE];
    jsize len = (jsize) recvfrom(sockfd, buf, sizeof(buf), 0, NULL, NULL);
    if (len >= 0) {
        jbyteArray retBuf = (*env)->NewByteArray(env, len);
        (*env)->SetByteArrayRegion(env, retBuf, 0, len, (jbyte*) buf);
        return retBuf;
    }
    ERR(-1);
    return NULL;
}
