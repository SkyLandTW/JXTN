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

#include <sys/mman.h>

#include "internals.h"

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeMMap_madvise(JNIEnv *env, jclass thisObj,
        jlong addr, jlong length, jint advice) {
    return ERR(madvise((void*) addr, UL(length), advice));
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_NativeMMap_mmap(JNIEnv *env, jclass thisObj,
        jlong addr, jlong length, jint prot, jint flags, jint fd, jlong offset) {
    return ERRVP(mmap((void*) addr, UL(length), prot, flags, fd, offset));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeMMap_msync(JNIEnv *env, jclass thisObj,
        jlong addr, jlong length, jint flags) {
    return ERR(msync((void*) addr, UL(length), flags));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeMMap_munmap(JNIEnv *env, jclass thisObj,
        jlong addr, jlong length) {
    return ERR(munmap((void*) addr, UL(length)));
}
