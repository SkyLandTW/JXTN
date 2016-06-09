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

#include <signal.h>
#include <sys/syscall.h>
#include <unistd.h>

#include "internals.h"

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeProc_fork(JNIEnv *env, jclass thisObj
        ) {
    return ERR(fork());
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeProc_getegid(JNIEnv *env, jclass thisObj) {
    return SI(getegid());
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeProc_geteuid(JNIEnv *env, jclass thisObj) {
    return SI(geteuid());
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeProc_getgid(JNIEnv *env, jclass thisObj) {
    return SI(getgid());
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeProc_getpid(JNIEnv *env, jclass thisObj) {
    return getpid();
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeProc_getppid(JNIEnv *env, jclass thisObj) {
    return getppid();
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeProc_gettid(JNIEnv *env, jclass thisObj) {
    return (pid_t) syscall(SYS_gettid);
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeProc_getuid(JNIEnv *env, jclass thisObj) {
    return SI(getuid());
}

