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

#include <sys/wait.h>

#include "internals.h"

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeWait_waitpid(JNIEnv *env, jclass thisObj,
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
