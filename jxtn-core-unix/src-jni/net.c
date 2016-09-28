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

#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>

#include "internals.h"

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeNet_socket(JNIEnv *env, jclass thisObj,
        jint domain, jint type, jint protocol) {
    return ERR(socket(domain, type, protocol));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeNet_bind(JNIEnv *env, jclass thisObj,
        jint sockfd, jbyteArray addr) {
    socklen_t addrlen = 0;
    if (addr != NULL) {
        addrlen = UI((*env)->GetArrayLength(env, addr));
    }
    return ERR(bind(sockfd, (const struct sockaddr*) resolveBA(addr), addrlen));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeNet_listen(JNIEnv *env, jclass thisObj,
        jint sockfd, jint backlog) {
    return ERR(listen(sockfd, backlog));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeNet_accept(JNIEnv *env, jclass thisObj,
        jint sockfd, jbyteArray addr) {
    socklen_t addrlen = 0;
    char addr_buf[128];
    struct sockaddr* addr_tmp = NULL;
    if (addr != NULL) {
        addrlen = UI((*env)->GetArrayLength(env, addr));
        addr_tmp = (struct sockaddr*) &addr_buf;
    }
    int ret = ERR(accept(sockfd, addr_tmp, &addrlen));
    if (ret >= 0 && addr_tmp != NULL) {
        memcpy(resolveBA(addr), addr_tmp, addrlen);
    }
    return ret;
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeNet_accept4(JNIEnv *env, jclass thisObj,
        jint sockfd, jbyteArray addr, jint flags) {
    socklen_t addrlen = 0;
    char addr_buf[128];
    struct sockaddr* addr_tmp = NULL;
    if (addr != NULL) {
        addrlen = UI((*env)->GetArrayLength(env, addr));
        addr_tmp = (struct sockaddr*) &addr_buf;
    }
    int ret = ERR(accept4(sockfd, addr_tmp, &addrlen, flags));
    if (ret >= 0 && addr_tmp != NULL) {
        memcpy(resolveBA(addr), addr_tmp, addrlen);
    }
    return ret;
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeNet_getsockopt(JNIEnv *env, jclass thisObj,
        jint sockfd, jint level, int optname, jbyteArray optval) {
    socklen_t optlen = 0;
    if (optval != NULL) {
        optlen = UI((*env)->GetArrayLength(env, optval));
    }
    return ERR(getsockopt(sockfd, level, optname, resolveBA(optval), &optlen));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeNet_setsockopt(JNIEnv *env, jclass thisObj,
        jint sockfd, jint level, int optname, jbyteArray optval) {
    socklen_t optlen = 0;
    if (optval != NULL) {
        optlen = UI((*env)->GetArrayLength(env, optval));
    }
    return ERR(setsockopt(sockfd, level, optname, resolveBA(optval), optlen));
}
