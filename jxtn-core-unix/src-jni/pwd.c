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

#include <errno.h>
#include <grp.h>
#include <pwd.h>
#include <sys/types.h>

#include "internals.h"

JNIEXPORT jintArray JNICALL Java_jxtn_core_unix_NativePWD_getgrouplist(JNIEnv *env, jclass thisObj,
        jbyteArray user) {
    if (user == NULL)
        return NULL;
    char* c_user = ACOPY_CS(user);
    struct passwd pwd;
    struct passwd* pwnam_result;
    char pwnam_buf[512];
    if (getpwnam_r(c_user, &pwd, pwnam_buf, sizeof(pwnam_buf), &pwnam_result) == -1)
        return NULL;
    gid_t groups[16];
    int ngroups = 16;
    if (getgrouplist(c_user, pwd.pw_gid, groups, &ngroups) == -1)
        return NULL;
    jintArray j_groups = (*env)->NewIntArray(env, ngroups);
    jint* j_groups_ptr = (*env)->GetIntArrayElements(env, j_groups, 0);
    for (int i = 0; i < ngroups; i++) {
        j_groups_ptr[i] = SI(groups[i]);
    }
    (*env)->ReleaseIntArrayElements(env, j_groups, j_groups_ptr, 0);
    return j_groups;
}

JNIEXPORT jstring JNICALL Java_jxtn_core_unix_NativePWD_getgrgid(JNIEnv *env, jclass thisObj,
        jint gid) {
    struct group grp;
    struct group* grgid_result;
    char grgid_buf[512];
    if (getgrgid_r(UI(gid), &grp, grgid_buf, sizeof(grgid_buf), &grgid_result) == -1) {
        return NULL;
    }
    return (*env)->NewStringUTF(env, grp.gr_name);
}
