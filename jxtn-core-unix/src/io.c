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

#include <sys/sendfile.h>
#include <unistd.h>

#include "internals.h"

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeIO_close(JNIEnv *env, jclass thisObj,
        jint fd) {
    return ERR(close(fd));
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_NativeIO_pread(JNIEnv *env, jclass thisObj,
        int fd, jobject buf_base, jlong buf_offset, jlong count, jlong offset) {
    void* buf = resolve(buf_base, buf_offset);
    return ERRL(pread(fd, buf, UL(count), offset));
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_NativeIO_pwrite(JNIEnv *env, jclass thisObj,
        int fd, jobject buf_base, jlong buf_offset, jlong count, jlong offset) {
    void* buf = resolve(buf_base, buf_offset);
    return ERRL(pwrite(fd, buf, UL(count), offset));
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_NativeIO_read(JNIEnv *env, jclass thisObj,
        int fd, jobject buf_base, jlong buf_offset, jlong count) {
    void* buf = resolve(buf_base, buf_offset);
    return ERRL(read(fd, buf, UL(count)));
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_NativeIO_sendfile(JNIEnv *env, jclass thisObj,
        jint out_fd, jint in_fd, jlong offset, jlong count) {
    return ERRL(sendfile(out_fd, in_fd, &offset, UL(count)));
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_NativeIO_write(JNIEnv *env, jclass thisObj,
        int fd, jobject buf_base, jlong buf_offset, jlong count) {
    void* buf = resolve(buf_base, buf_offset);
    return ERRL(write(fd, buf, UL(count)));
}
