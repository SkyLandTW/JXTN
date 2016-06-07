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

#include <sys/stat.h>
#include <sys/syscall.h>
#include <unistd.h>

#include "internals.h"

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeDirs_chdir(JNIEnv *env, jclass thisObj,
        jbyteArray path) {
    return ERR(chdir(resolveCS(path)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeDirs_chroot(JNIEnv *env, jclass thisObj,
        jbyteArray path) {
    return ERR(chroot(resolveCS(path)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeDirs_fchdir(JNIEnv *env, jclass thisObj,
        jint fd) {
    return ERR(fchdir(fd));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeDirs_getdents64(JNIEnv *env, jclass thisObj,
        jint fd, jbyteArray dirp) {
    if (dirp == NULL) {
        return SETERR(EFAULT);
    }
    size_t count = UL((*env)->GetArrayLength(env, dirp));
    return ERR((int) syscall(SYS_getdents64, fd, resolveBA(dirp), count));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeDirs_mkdir(JNIEnv *env, jclass thisObj,
        jbyteArray pathname, jint mode) {
    return ERR(mkdir(resolveCS(pathname), UI(mode)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeDirs_mkdirat(JNIEnv *env, jclass thisObj,
        jint dirfd, jbyteArray pathname, jint mode) {
    return ERR(mkdirat(dirfd, resolveCS(pathname), UI(mode)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeDirs_rmdir(JNIEnv *env, jclass thisObj,
        jbyteArray pathname) {
    return ERR(rmdir(resolveCS(pathname)));
}

