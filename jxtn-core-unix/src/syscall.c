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

#include <fcntl.h>
#include <signal.h>
#include <string.h>
#include <sys/mman.h>
#include <sys/prctl.h>
#include <sys/sendfile.h>
#include <sys/stat.h>
#include <sys/syscall.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

#include <attr/xattr.h>

#include "internals.h"

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_access(JNIEnv *env, jclass thisObj,
        jbyteArray pathname, jint mode) {
    return ERR(access(resolveCS(pathname), mode));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_chdir(JNIEnv *env, jclass thisObj,
        jbyteArray path) {
    return ERR(chdir(resolveCS(path)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_chown(JNIEnv *env, jclass thisObj,
        jbyteArray pathname, jint owner, jint group) {
    return ERR(chown(resolveCS(pathname), UI(owner), UI(group)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_chroot(JNIEnv *env, jclass thisObj,
        jbyteArray path) {
    return ERR(chroot(resolveCS(path)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_close(JNIEnv *env, jclass thisObj,
        jint fd) {
    return ERR(close(fd));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_creat(JNIEnv *env, jclass thisObj,
        jbyteArray pathname, jint mode) {
    return ERR(creat(resolveCS(pathname), UI(mode)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_fadvise(JNIEnv *env, jclass thisObj,
        jint fd, jlong offset, jlong len, jint advice) {
    return ERR(posix_fadvise(fd, offset, len, advice));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_fallocate(JNIEnv *env, jclass thisObj,
        jint fd, jint mode, jlong offset, jlong len) {
    return ERR(fallocate(fd, mode, offset, len));
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_Syscall_fgetxattr(JNIEnv *env, jclass thisObj,
        jint filedes, jbyteArray name, jbyteArray value) {
    size_t size = value == NULL ? 0 : UL((*env)->GetArrayLength(env, value));
    return ERRL(fgetxattr(filedes, resolveCS(name), resolveBA(value), size));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_fork(JNIEnv *env, jclass thisObj
        ) {
    return ERR(fork());
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_fsetxattr(JNIEnv *env, jclass thisObj,
        jint filedes, jbyteArray name, jbyteArray value, jint flags) {
    if (value == NULL) {
        return SETERR(EFAULT);
    }
    size_t size = UL((*env)->GetArrayLength(env, value));
    return ERR(fsetxattr(filedes, resolveCS(name), resolveBA(value), size, flags));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_fstat(JNIEnv *env, jclass thisObj,
        jint fd, jbyteArray buf) {
    return ERR(fstat(fd, (struct stat *) resolveBA(buf)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_ftruncate(JNIEnv *env, jclass thisObj,
        jint fd, jlong length) {
    return ERR(ftruncate(fd, length));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_getegid(JNIEnv *env, jclass thisObj) {
    return SI(getegid());
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_geteuid(JNIEnv *env, jclass thisObj) {
    return SI(geteuid());
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_getgid(JNIEnv *env, jclass thisObj) {
    return SI(getgid());
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_getpid(JNIEnv *env, jclass thisObj) {
    return getpid();
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_getppid(JNIEnv *env, jclass thisObj) {
    return getppid();
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_gettid(JNIEnv *env, jclass thisObj) {
    return (pid_t) syscall(SYS_gettid);
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_getuid(JNIEnv *env, jclass thisObj) {
    return SI(getuid());
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_Syscall_getxattr(JNIEnv *env, jclass thisObj,
        jbyteArray path, jbyteArray name, jbyteArray value) {
    size_t size = value == NULL ? 0 : UL((*env)->GetArrayLength(env, value));
    return ERRL(getxattr(resolveCS(path), resolveCS(name), resolveBA(value), size));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_kill(JNIEnv *env, jclass thisObj,
        jint pid, jint sig) {
    return ERR(kill(pid, sig));
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_Syscall_lgetxattr(JNIEnv *env, jclass thisObj,
        jbyteArray path, jbyteArray name, jbyteArray value) {
    size_t size = value == NULL ? 0 : UL((*env)->GetArrayLength(env, value));
    return ERRL(lgetxattr(resolveCS(path), resolveCS(name), resolveBA(value), size));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_link(JNIEnv *env, jclass thisObj,
        jbyteArray oldpath, jbyteArray newpath) {
    return ERR(link(resolveCS(oldpath), resolveCS(newpath)));
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_Syscall_lseek(JNIEnv *env, jclass thisObj,
        jint fd, jlong offset, jint whence) {
    return ERRL(lseek(fd, offset, whence));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_lsetxattr(JNIEnv *env, jclass thisObj,
        jbyteArray path, jbyteArray name, jbyteArray value, jint flags) {
    if (value == NULL) {
        return SETERR(EFAULT);
    }
    size_t size = UL((*env)->GetArrayLength(env, value));
    return ERR(lsetxattr(resolveCS(path), resolveCS(name), resolveBA(value), size, flags));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_lstat(JNIEnv *env, jclass thisObj,
        jbyteArray pathname, jbyteArray buf) {
    return ERR(lstat(resolveCS(pathname), (struct stat *) resolveBA(buf)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_madvise(JNIEnv *env, jclass thisObj,
        jlong addr, jlong length, jint advice) {
    return ERR(madvise((void*) addr, UL(length), advice));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_mkdir(JNIEnv *env, jclass thisObj,
        jbyteArray pathname, jint mode) {
    return ERR(mkdir(resolveCS(pathname), UI(mode)));
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_Syscall_mmap(JNIEnv *env, jclass thisObj,
        jlong addr, jlong length, jint prot, jint flags, jint fd, jlong offset) {
    return ERRVP(mmap((void*) addr, UL(length), prot, flags, fd, offset));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_munmap(JNIEnv *env, jclass thisObj,
        jlong addr, jlong length) {
    return ERR(munmap((void*) addr, UL(length)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_open(JNIEnv *env, jclass thisObj,
        jbyteArray pathname, jint flags, jint mode) {
    return ERR(open(resolveCS(pathname), flags, mode));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_pipe(JNIEnv *env, jclass thisObj,
        jintArray pipefd) {
    if (pipefd == NULL || (*env)->GetArrayLength(env, pipefd) != 2) {
        return SETERR(EFAULT);
    }
    jint *pipefd_ptr = (*env)->GetIntArrayElements(env, pipefd, 0);
    jint ret = ERR(pipe(pipefd_ptr));
    (*env)->ReleaseIntArrayElements(env, pipefd, pipefd_ptr, 0);
    return ret;
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_pipe2(JNIEnv *env, jclass thisObj,
        jintArray pipefd, jint flags) {
    if (pipefd == NULL || (*env)->GetArrayLength(env, pipefd) != 2) {
        return SETERR(EFAULT);
    }
    jint *pipefd_ptr = (*env)->GetIntArrayElements(env, pipefd, 0);
    jint ret = ERR(pipe2(pipefd_ptr, flags));
    (*env)->ReleaseIntArrayElements(env, pipefd, pipefd_ptr, 0);
    return ret;
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_prctl(JNIEnv *env, jclass thisObj,
        int option, jlong arg2, jlong arg3, jlong arg4, jlong arg5) {
    return ERR(prctl(option, UL(arg2), UL(arg3), UL(arg4), UL(arg5)));
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_Syscall_pread(JNIEnv *env, jclass thisObj,
        int fd, jobject buf_base, jlong buf_offset, jlong count, jlong offset) {
    void* buf = resolve(buf_base, buf_offset);
    return ERRL(pread(fd, buf, UL(count), offset));
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_Syscall_pwrite(JNIEnv *env, jclass thisObj,
        int fd, jobject buf_base, jlong buf_offset, jlong count, jlong offset) {
    void* buf = resolve(buf_base, buf_offset);
    return ERRL(pwrite(fd, buf, UL(count), offset));
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_Syscall_read(JNIEnv *env, jclass thisObj,
        int fd, jobject buf_base, jlong buf_offset, jlong count) {
    void* buf = resolve(buf_base, buf_offset);
    return ERRL(read(fd, buf, UL(count)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_rename(JNIEnv *env, jclass thisObj,
        jbyteArray oldpath, jbyteArray newpath) {
    return ERR(rename(resolveCS(oldpath), resolveCS(newpath)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_rmdir(JNIEnv *env, jclass thisObj,
        jbyteArray pathname) {
    return ERR(rmdir(resolveCS(pathname)));
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_Syscall_sendfile(JNIEnv *env, jclass thisObj,
        jint out_fd, jint in_fd, jlong offset, jlong count) {
    return ERRL(sendfile(out_fd, in_fd, &offset, UL(count)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_setxattr(JNIEnv *env, jclass thisObj,
        jbyteArray path, jbyteArray name, jbyteArray value, jint flags) {
    if (value == NULL) {
        return SETERR(EFAULT);
    }
    size_t size = UL((*env)->GetArrayLength(env, value));
    return ERR(setxattr(resolveCS(path), resolveCS(name), resolveBA(value), size, flags));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_stat(JNIEnv *env, jclass thisObj,
        jbyteArray pathname, jbyteArray buf) {
    return ERR(stat(resolveCS(pathname), (struct stat *) resolveBA(buf)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_symlink(JNIEnv *env, jclass thisObj,
        jbyteArray target, jbyteArray linkpath) {
    return ERR(symlink(resolveCS(target), resolveCS(linkpath)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_truncate(JNIEnv *env, jclass thisObj,
        jbyteArray path, jlong length) {
    return ERR(truncate(resolveCS(path), length));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall_waitpid(JNIEnv *env, jclass thisObj,
        jint pid, jintArray status, jint options) {
    if (status != NULL && (*env)->GetArrayLength(env, status) != 1) {
        return SETERR(EFAULT);
    }
    jint *status_ptr;
    if (status != NULL) {
        status_ptr = (*env)->GetIntArrayElements(env, status, 0);
    } else {
        status_ptr = NULL;
    }
    pid_t ret = ERR(waitpid(pid, status_ptr, options));
    if (status != NULL) {
        (*env)->ReleaseIntArrayElements(env, status, status_ptr, 0);
    }
    return ret;
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_Syscall_write(JNIEnv *env, jclass thisObj,
        int fd, jobject buf_base, jlong buf_offset, jlong count) {
    void* buf = resolve(buf_base, buf_offset);
    return ERRL(write(fd, buf, UL(count)));
}
