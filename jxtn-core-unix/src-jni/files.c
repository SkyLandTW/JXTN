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
#include <sys/stat.h>
#include <sys/syscall.h>
#include <sys/types.h>
#include <unistd.h>

#include "internals.h"

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_access(JNIEnv *env, jclass thisObj,
        jbyteArray pathname, jint mode) {
    char* c_pathname = ACOPY_CS(pathname);
    return ERR(access(c_pathname, mode));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_chmod(JNIEnv *env, jclass thisObj,
        jbyteArray pathname, jint mode) {
    char* c_pathname = ACOPY_CS(pathname);
    return ERR(chmod(c_pathname, UI(mode)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_chown(JNIEnv *env, jclass thisObj,
        jbyteArray pathname, jint owner, jint group) {
    char* c_pathname = ACOPY_CS(pathname);
    return ERR(chown(c_pathname, UI(owner), UI(group)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_creat(JNIEnv *env, jclass thisObj,
        jbyteArray pathname, jint mode) {
    char* c_pathname = ACOPY_CS(pathname);
    return ERR(creat(c_pathname, UI(mode)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_fadvise(JNIEnv *env, jclass thisObj,
        jint fd, jlong offset, jlong len, jint advice) {
    return ERR(posix_fadvise(fd, offset, len, advice));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_faccessat(JNIEnv *env, jclass thisObj,
        jint dirfd, jbyteArray pathname, jint mode, jint flags) {
    char* c_pathname = ACOPY_CS(pathname);
    return ERR(faccessat(dirfd, c_pathname, mode, flags));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_fallocate(JNIEnv *env, jclass thisObj,
        jint fd, jint mode, jlong offset, jlong len) {
    return ERR(fallocate(fd, mode, offset, len));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_fchmod(JNIEnv *env, jclass thisObj,
        jint fd, jint mode) {
    return ERR(fchmod(fd, UI(mode)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_fchmodat(JNIEnv *env, jclass thisObj,
        jint dirfd, jbyteArray pathname, jint mode, int flags) {
    char* c_pathname = ACOPY_CS(pathname);
    return ERR(fchmodat(dirfd, c_pathname, UI(mode), flags));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_fchown(JNIEnv *env, jclass thisObj,
        jint fd, jint owner, jint group) {
    return ERR(fchown(fd, UI(owner), UI(group)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_fchownat(JNIEnv *env, jclass thisObj,
        jint dirfd, jbyteArray pathname, jint owner, jint group, jint flags) {
    char* c_pathname = ACOPY_CS(pathname);
    return ERR(fchownat(dirfd, c_pathname, UI(owner), UI(group), flags));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_ftruncate(JNIEnv *env, jclass thisObj,
        jint fd, jlong length) {
    return ERR(ftruncate(fd, length));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_lchown(JNIEnv *env, jclass thisObj,
        jbyteArray pathname, jint owner, jint group) {
    char* c_pathname = ACOPY_CS(pathname);
    return ERR(lchown(c_pathname, UI(owner), UI(group)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_link(JNIEnv *env, jclass thisObj,
        jbyteArray oldpath, jbyteArray newpath) {
    char* c_oldpath = ACOPY_CS(oldpath);
    char* c_newpath = ACOPY_CS(newpath);
    return ERR(link(c_oldpath, c_newpath));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_linkat(JNIEnv *env, jclass thisObj,
        jint olddirfd, jbyteArray oldpath, jint newdirfd, jbyteArray newpath, jint flags) {
    char* c_oldpath = ACOPY_CS(oldpath);
    char* c_newpath = ACOPY_CS(newpath);
    return ERR(linkat(olddirfd, c_oldpath, newdirfd, c_newpath, flags));
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_NativeFiles_lseek(JNIEnv *env, jclass thisObj,
        jint fd, jlong offset, jint whence) {
    return ERRL(lseek(fd, offset, whence));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_open(JNIEnv *env, jclass thisObj,
        jbyteArray pathname, jint flags, jint mode) {
    char* c_pathname = ACOPY_CS(pathname);
    return ERR(open(c_pathname, flags, mode));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_openat(JNIEnv *env, jclass thisObj,
        jint dirfd, jbyteArray pathname, jint flags, jint mode) {
    char* c_pathname = ACOPY_CS(pathname);
    return ERR(openat(dirfd, c_pathname, flags, mode));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_pipe(JNIEnv *env, jclass thisObj,
        jintArray pipefd) {
    if (pipefd == NULL || (*env)->GetArrayLength(env, pipefd) != 2) {
        return SETERR(EFAULT);
    }
    jint *pipefd_ptr = (*env)->GetIntArrayElements(env, pipefd, 0);
    jint ret = ERR(pipe(pipefd_ptr));
    (*env)->ReleaseIntArrayElements(env, pipefd, pipefd_ptr, 0);
    return ret;
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_pipe2(JNIEnv *env, jclass thisObj,
        jintArray pipefd, jint flags) {
    if (pipefd == NULL || (*env)->GetArrayLength(env, pipefd) != 2) {
        return SETERR(EFAULT);
    }
    jint *pipefd_ptr = (*env)->GetIntArrayElements(env, pipefd, 0);
    jint ret = ERR(pipe2(pipefd_ptr, flags));
    (*env)->ReleaseIntArrayElements(env, pipefd, pipefd_ptr, 0);
    return ret;
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_NativeFiles_readlink(JNIEnv *env, jclass thisObj,
        jbyteArray pathname, jbyteArray buf) {
    if (buf == NULL) {
        return SETERR(EFAULT);
    }
    char* c_pathname = ACOPY_CS(pathname);
    size_t bufsiz = UL((*env)->GetArrayLength(env, buf));
    return ERRL(readlink(c_pathname, (char*) resolveBA(buf), bufsiz));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_rename(JNIEnv *env, jclass thisObj,
        jbyteArray oldpath, jbyteArray newpath) {
    char* c_oldpath = ACOPY_CS(oldpath);
    char* c_newpath = ACOPY_CS(newpath);
    return ERR(rename(c_oldpath, c_newpath));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_renameat(JNIEnv *env, jclass thisObj,
        jint olddirfd, jbyteArray oldpath, jint newdirfd, jbyteArray newpath) {
    char* c_oldpath = ACOPY_CS(oldpath);
    char* c_newpath = ACOPY_CS(newpath);
    return ERR(renameat(olddirfd, c_oldpath, newdirfd, c_newpath));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_renameat2(JNIEnv *env, jclass thisObj,
        jint olddirfd, jbyteArray oldpath, jint newdirfd, jbyteArray newpath, jint flags) {
    char* c_oldpath = ACOPY_CS(oldpath);
    char* c_newpath = ACOPY_CS(newpath);
    return ERR((int) syscall(SYS_renameat2, olddirfd, c_oldpath, newdirfd, c_newpath, UI(flags)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_symlink(JNIEnv *env, jclass thisObj,
        jbyteArray target, jbyteArray linkpath) {
    char* c_target = ACOPY_CS(target);
    char* c_linkpath = ACOPY_CS(linkpath);
    return ERR(symlink(c_target, c_linkpath));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_symlinkat(JNIEnv *env, jclass thisObj,
        jbyteArray target, int newdirfd, jbyteArray linkpath) {
    char* c_target = ACOPY_CS(target);
    char* c_linkpath = ACOPY_CS(linkpath);
    return ERR(symlinkat(c_target, newdirfd, c_linkpath));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_truncate(JNIEnv *env, jclass thisObj,
        jbyteArray path, jlong length) {
    char* c_path = ACOPY_CS(path);
    return ERR(truncate(c_path, length));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_unlink(JNIEnv *env, jclass thisObj,
        jbyteArray pathname) {
    char* c_pathname = ACOPY_CS(pathname);
    return ERR(unlink(c_pathname));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles_unlinkat(JNIEnv *env, jclass thisObj,
        jint dirfd, jbyteArray pathname, int flags) {
    char* c_pathname = ACOPY_CS(pathname);
    return ERR(unlinkat(dirfd, c_pathname, flags));
}
