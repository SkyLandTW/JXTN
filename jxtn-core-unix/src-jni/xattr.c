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

#include <sys/types.h>
#include <attr/xattr.h>

#include "internals.h"

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_NativeXAttr_getxattr(JNIEnv *env, jclass thisObj,
        jbyteArray path, jbyteArray name, jbyteArray value) {
    char* c_path = ACOPY_CS(path);
    char* c_name = ACOPY_CS(name);
    size_t size = value == NULL ? 0 : UL((*env)->GetArrayLength(env, value));
    return ERRL(getxattr(c_path, c_name, resolveBA(value), size));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeXAttr_setxattr(JNIEnv *env, jclass thisObj,
        jbyteArray path, jbyteArray name, jbyteArray value, jint flags) {
    if (value == NULL) {
        return SETERR(EFAULT);
    }
    char* c_path = ACOPY_CS(path);
    char* c_name = ACOPY_CS(name);
    size_t size = UL((*env)->GetArrayLength(env, value));
    return ERR(setxattr(c_path, c_name, resolveBA(value), size, flags));
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_NativeXAttr_fgetxattr(JNIEnv *env, jclass thisObj,
        jint filedes, jbyteArray name, jbyteArray value) {
    char* c_name = ACOPY_CS(name);
    size_t size = value == NULL ? 0 : UL((*env)->GetArrayLength(env, value));
    return ERRL(fgetxattr(filedes, c_name, resolveBA(value), size));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeXAttr_fsetxattr(JNIEnv *env, jclass thisObj,
        jint filedes, jbyteArray name, jbyteArray value, jint flags) {
    if (value == NULL) {
        return SETERR(EFAULT);
    }
    char* c_name = ACOPY_CS(name);
    size_t size = UL((*env)->GetArrayLength(env, value));
    return ERR(fsetxattr(filedes, c_name, resolveBA(value), size, flags));
}

JNIEXPORT jlong JNICALL Java_jxtn_core_unix_NativeXAttr_lgetxattr(JNIEnv *env, jclass thisObj,
        jbyteArray path, jbyteArray name, jbyteArray value) {
    char* c_path = ACOPY_CS(path);
    char* c_name = ACOPY_CS(name);
    size_t size = value == NULL ? 0 : UL((*env)->GetArrayLength(env, value));
    return ERRL(lgetxattr(c_path, c_name, resolveBA(value), size));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeXAttr_lsetxattr(JNIEnv *env, jclass thisObj,
        jbyteArray path, jbyteArray name, jbyteArray value, jint flags) {
    if (value == NULL) {
        return SETERR(EFAULT);
    }
    char* c_path = ACOPY_CS(path);
    char* c_name = ACOPY_CS(name);
    size_t size = UL((*env)->GetArrayLength(env, value));
    return ERR(lsetxattr(c_path, c_name, resolveBA(value), size, flags));
}
