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

#include <jni.h>

#include <errno.h>
#include <fcntl.h>
#include <signal.h>
#include <string.h>
#include <sys/mman.h>
#include <unistd.h>

/*********************************************************************
 ** Errno
 *********************************************************************/

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscalls_errno(JNIEnv *env, jclass thisObj) {
    return errno;
}

/*********************************************************************
 ** System calls
 *********************************************************************/

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscalls_access(JNIEnv *env, jclass thisObj,
        jlong pathname, jint mode) {
    return access((const char*) pathname, mode);
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscalls_chdir(JNIEnv *env, jclass thisObj,
        jlong path) {
    return chdir((const char*) path);
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscalls_chown(JNIEnv *env, jclass thisObj,
        jlong pathname, jint owner, jint group) {
    return chown((const char*) pathname, owner, group);
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscalls_chroot(JNIEnv *env, jclass thisObj,
        jlong path) {
    return chroot((const char*) path);
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscalls_close(JNIEnv *env, jclass thisObj, jint fd) {
    return close(fd);
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscalls_creat(JNIEnv *env, jclass thisObj,
        jlong pathname, jint mode) {
    return creat((const char*) pathname, mode);
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscalls_fallocate(JNIEnv *env, jclass thisObj,
        jint fd, jint mode, jlong offset, jlong len) {
    return fallocate(fd, mode, offset, len);
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscalls_fork(JNIEnv *env, jclass thisObj) {
    return fork();
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscalls_getpid(JNIEnv *env, jclass thisObj) {
    return getpid();
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscalls_getppid(JNIEnv *env, jclass thisObj) {
    return getppid();
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscalls_kill(JNIEnv *env, jclass thisObj,
        jint pid, jint sig) {
    return kill(pid, sig);
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscalls_madvise(JNIEnv *env, jclass thisObj,
        jlong addr, jlong length, jint advice) {
    return madvise((void*) addr, length, advice);
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_Syscalls_mmap(JNIEnv *env, jclass thisObj,
        jlong addr, jlong length, jint prot, jint flags, jint fd, jlong offset) {
    return (jlong) mmap((void*) addr, length, prot, flags, fd, offset);
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscalls_munmap(JNIEnv *env, jclass thisObj,
        jlong addr, jlong length) {
    return munmap((void*) addr, length);
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscalls_open(JNIEnv *env, jclass thisObj,
        jlong pathname, jint flags, jint mode) {
    return open((const char*) pathname, flags, mode);
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_Syscalls_read(JNIEnv *env, jclass thisObj,
        int fd, jlong buf, jlong count) {
    return read(fd, (void*) buf, count);
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_Syscalls_write(JNIEnv *env, jclass thisObj,
        int fd, jlong buf, jlong count) {
    return write(fd, (void*) buf, count);
}
