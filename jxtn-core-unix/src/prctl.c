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
#include <string.h>
#include <sys/prctl.h>
#include <sys/syscall.h>
#include <unistd.h>

#include "internals.h"

static char prctl_process_name[16];

static void prctl_setName_handler(int signum);

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Prctl_setName(JNIEnv *env, jclass thisObj,
        jbyteArray name) {
    const char* nameSource = resolveCS(name);
    strncpy(prctl_process_name, nameSource, 16);
    prctl_process_name[15] = '\0';
    if (signal(SIGUSR1, prctl_setName_handler) == SIG_ERR)
        return -1;
    if (syscall(SYS_tgkill, getpid(), getpid(), SIGUSR1) == -1)
        return -1;
    return 0;
}

static void prctl_setName_handler(int signum) {
    signal(SIGUSR1, SIG_DFL);
    if (prctl(PR_SET_NAME, prctl_process_name, 0L, 0L, 0L) == -1)
        perror("PR_SET_NAME");
}
