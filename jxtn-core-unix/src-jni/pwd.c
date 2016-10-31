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

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativePWD_getgrouplist(JNIEnv *env, jclass thisObj,
        jbyteArray user, jobjectArray groups) {
    if (user == NULL || groups == NULL)
        return SETERR(EFAULT);
    char* c_user = ACOPY_CS(user);
    struct passwd pwd;
    struct passwd* pwnam_result;
    char pwnam_buf[512];
    if (getpwnam_r(c_user, &pwd, pwnam_buf, sizeof(pwnam_buf), &pwnam_result) == -1)
        return ERR(-1);
    gid_t gids[16];
    int ngids = 16;
    if (getgrouplist(c_user, pwd.pw_gid, gids, &ngids) == -1)
        return ERR(-1);
    const int groups_max = (*env)->GetArrayLength(env, groups);
    for (int i = 0; i < MIN(ngids, groups_max); i++) {
        struct group grp;
        struct group* grgid_result;
        char grgid_buf[512];
        if (getgrgid_r(gids[i], &grp, grgid_buf, sizeof(grgid_buf), &grgid_result) == -1) {
            jbyteArray out_group = (jbyteArray) (*env)->GetObjectArrayElement(env, groups, i);
            int out_group_sz = (*env)->GetArrayLength(env, out_group);
            snprintf((char*) resolveBA(out_group), UL(out_group_sz), "%d", gids[i]);
        } else {
            jbyteArray out_group = (jbyteArray) (*env)->GetObjectArrayElement(env, groups, i);
            int out_group_sz = (*env)->GetArrayLength(env, out_group);
            strncpy((char*) resolveBA(out_group), grp.gr_name, UL(out_group_sz));
        }
    }
    for (int i = ngids; i < groups_max; i++) {
        jbyteArray out_group = (jbyteArray) (*env)->GetObjectArrayElement(env, groups, i);
        int out_group_sz = (*env)->GetArrayLength(env, out_group);
        memset(resolveBA(out_group), 0, UL(out_group_sz));
    }
    return ngids;
}
