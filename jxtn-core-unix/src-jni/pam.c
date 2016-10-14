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

#include <fcntl.h>
#include <signal.h>
#include <sys/prctl.h>
#include <sys/wait.h>
#include <unistd.h>

#include "internals.h"

extern char **environ;

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativePAM_authenticate(JNIEnv *env, jclass thisObj,
        jbyteArray service, jbyteArray username, jbyteArray password) {
    char* c_service = ACOPY_CS(service);
    char* c_username = ACOPY_CS(username);
    char* c_password = ACOPY_CS(password);
    int pipefd[2];
    if (pipe(pipefd) != 0)
        return ERR(-1);
    pid_t pid = fork();
    if (pid == 0) {
        if (prctl(PR_SET_PDEATHSIG, SIGTERM, 0L, 0L, 0L) != 0) {
            perror("PR_SET_PDEATHSIG");
        }
        close(0);
        dup2(pipefd[0], 0);
        close(pipefd[1]);
        char* argv[] = { "libjxtn-core-unix.so", "pam", c_service, c_username, NULL };
        if (execve("/usr/lib/jni/libjxtn-core-unix.so", argv, environ) == -1) {
            perror("execve");
        }
        _exit(-1);
        return -1;
    } else {
        close(pipefd[0]);
        if (write(pipefd[1], c_password, strlen(c_password)) == -1L) {
            goto ABORT;
        }
        char eol = '\n';
        if (write(pipefd[1], &eol, 1) == -1L) {
            goto ABORT;
        }
        int status = 0;
        if (waitpid(pid, &status, 0) == -1) {
            goto ABORT;
        }
        if (WIFEXITED(status)) {
            return WEXITSTATUS(status);
        } else {
            return SETERR(ENOTRECOVERABLE);
        }
        ABORT: {
            int err = errno;
            kill(pid, SIGKILL);
            return SETERR(err);
        }
    }
}
