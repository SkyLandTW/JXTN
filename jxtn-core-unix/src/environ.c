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

#include <string.h>

#include "internals.h"

extern char **environ;

static int environ_find_argc(void);
static const char** environ_find_argv(void);
static void* environ_find_stack(void);

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Environ_getArgc(JNIEnv *env, jclass thisObj) {
    return environ_find_argc();
}

JNIEXPORT jstring JNICALL Java_jxtn_core_unix_Environ_getArgv(JNIEnv *env, jclass thisObj,
        jint index) {
    const char** argv = environ_find_argv();
    const char* value = argv[index];
    return value == NULL ? NULL : (*env)->NewStringUTF(env, value);
}

JNIEXPORT void JNICALL Java_jxtn_core_unix_Environ_setArgv(JNIEnv *env, jclass thisObj,
        jint index, jstring value) {
    char** argv = (char**) environ_find_argv();
    const char* value_b = (*env)->GetStringUTFChars(env, value, NULL);
    if (argv[index] != NULL) {
        const size_t maxlen = (size_t) ((argv[index + 1] == NULL ? environ[0] : argv[index + 1]) - 1 - argv[index]);
        const size_t curlen = MIN(strlen(value_b), maxlen);
        strncpy(argv[index], value_b, curlen);
        memset(argv[index] + curlen, 0, maxlen - curlen + 1);
    }
    (*env)->ReleaseStringUTFChars(env, value, value_b);
}

static int environ_find_argc(void) {
    return *(int*) environ_find_stack();
}

static const char** environ_find_argv(void) {
    unsigned long* argc_ptr = (unsigned long*) environ_find_stack();
    unsigned long* argv_ptr = argc_ptr + 1;
    return (const char**) argv_ptr;
}

static void* environ_find_stack(void) {
    unsigned long* argc_ptr = NULL;
    // Find argc by scanning all pointers from (_x_sys_environ - 2), until there is
    // a pointer that contains a very small value (whose address cannot be real)
    for (argc_ptr = (unsigned long*) environ - 2; *argc_ptr > 255; argc_ptr--)
        ;
    return argc_ptr;
}
