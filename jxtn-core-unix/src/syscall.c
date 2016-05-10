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
#include <sys/stat.h>
#include <sys/syscall.h>
#include <unistd.h>

#include "internals.h"

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_access(JNIEnv *env, jclass thisObj,
        jbyteArray pathname, jint mode) {
    int ret = access(resolveBA(pathname), mode);
    jxtn_core_unix_errno = ret == -1 ? errno : 0;
    return ret;
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_chdir(JNIEnv *env, jclass thisObj,
        jbyteArray path) {
    int ret = chdir(resolveBA(path));
    jxtn_core_unix_errno = ret == -1 ? errno : 0;
    return ret;
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_chown(JNIEnv *env, jclass thisObj,
        jbyteArray pathname, jint owner, jint group) {
    int ret = chown(resolveBA(pathname), owner, group);
    jxtn_core_unix_errno = ret == -1 ? errno : 0;
    return ret;
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_chroot(JNIEnv *env, jclass thisObj,
        jbyteArray path) {
    int ret = chroot(resolveBA(path));
    jxtn_core_unix_errno = ret == -1 ? errno : 0;
    return ret;
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_close(JNIEnv *env, jclass thisObj,
        jint fd) {
    int ret = close(fd);
    jxtn_core_unix_errno = ret == -1 ? errno : 0;
    return ret;
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_creat(JNIEnv *env, jclass thisObj,
        jbyteArray pathname, jint mode) {
    int ret = creat(resolveBA(pathname), mode);
    jxtn_core_unix_errno = ret == -1 ? errno : 0;
    return ret;
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_fallocate(JNIEnv *env, jclass thisObj,
        jint fd, jint mode, jlong offset, jlong len) {
    int ret = fallocate(fd, mode, offset, len);
    jxtn_core_unix_errno = ret == -1 ? errno : 0;
    return ret;
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_fork(JNIEnv *env, jclass thisObj
        ) {
    int ret = fork();
    jxtn_core_unix_errno = ret == -1 ? errno : 0;
    return ret;
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_getegid(JNIEnv *env, jclass thisObj) {
    return getegid();
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_geteuid(JNIEnv *env, jclass thisObj) {
    return geteuid();
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_getgid(JNIEnv *env, jclass thisObj) {
    return getgid();
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_getpid(JNIEnv *env, jclass thisObj) {
    return getpid();
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_getppid(JNIEnv *env, jclass thisObj) {
    return getppid();
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_gettid(JNIEnv *env, jclass thisObj) {
    return syscall(SYS_gettid);
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_getuid(JNIEnv *env, jclass thisObj) {
    return getuid();
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_kill(JNIEnv *env, jclass thisObj,
        jint pid, jint sig) {
    int ret = kill(pid, sig);
    jxtn_core_unix_errno = ret == -1 ? errno : 0;
    return ret;
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_link(JNIEnv *env, jclass thisObj,
        jbyteArray oldpath, jbyteArray newpath) {
    int ret = link(resolveBA(oldpath), resolveBA(newpath));
    jxtn_core_unix_errno = ret == -1 ? errno : 0;
    return ret;
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_Syscall_lseek(JNIEnv *env, jclass thisObj,
        jint fd, jlong offset, jint whence) {
    long ret = lseek(fd, offset, whence);
    jxtn_core_unix_errno = ret == -1L ? errno : 0;
    return ret;
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_madvise(JNIEnv *env, jclass thisObj,
        jlong addr, jlong length, jint advice) {
    int ret = madvise((void*) addr, length, advice);
    jxtn_core_unix_errno = ret == -1 ? errno : 0;
    return ret;
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_mkdir(JNIEnv *env, jclass thisObj,
        jbyteArray pathname, jint mode) {
    int ret = mkdir(resolveBA(pathname), mode);
    jxtn_core_unix_errno = ret == -1 ? errno : 0;
    return ret;
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_Syscall_mmap(JNIEnv *env, jclass thisObj,
        jlong addr, jlong length, jint prot, jint flags, jint fd, jlong offset) {
    void* ret = mmap((void*) addr, length, prot, flags, fd, offset);
    jxtn_core_unix_errno = ret == (void*) -1 ? errno : 0;
    return (long) ret;
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_munmap(JNIEnv *env, jclass thisObj,
        jlong addr, jlong length) {
    int ret = munmap((void*) addr, length);
    jxtn_core_unix_errno = ret == -1 ? errno : 0;
    return ret;
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_open(JNIEnv *env, jclass thisObj,
        jbyteArray pathname, jint flags, jint mode) {
    int ret = open(resolveBA(pathname), flags, mode);
    jxtn_core_unix_errno = ret == -1 ? errno : 0;
    return ret;
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_Syscall_read(JNIEnv *env, jclass thisObj,
        int fd, jobject buf_base, jlong buf_offset, jlong count) {
    void* buf = resolve(buf_base, buf_offset);
    long ret = read(fd, buf, count);
    jxtn_core_unix_errno = ret == -1L ? errno : 0;
    return ret;
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_rename(JNIEnv *env, jclass thisObj,
        jbyteArray oldpath, jbyteArray newpath) {
    int ret = rename(resolveBA(oldpath), resolveBA(newpath));
    jxtn_core_unix_errno = ret == -1 ? errno : 0;
    return ret;
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_rmdir(JNIEnv *env, jclass thisObj,
        jbyteArray pathname) {
    int ret = rmdir(resolveBA(pathname));
    jxtn_core_unix_errno = ret == -1 ? errno : 0;
    return ret;
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_symlink(JNIEnv *env, jclass thisObj,
        jbyteArray target, jbyteArray linkpath) {
    int ret = symlink(resolveBA(target), resolveBA(linkpath));
    jxtn_core_unix_errno = ret == -1 ? errno : 0;
    return ret;
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_Syscall_write(JNIEnv *env, jclass thisObj,
        int fd, jobject buf_base, jlong buf_offset, jlong count) {
    void* buf = resolve(buf_base, buf_offset);
    long ret = write(fd, buf, count);
    jxtn_core_unix_errno = ret == -1L ? errno : 0;
    return ret;
}
