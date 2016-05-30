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

#include <dirent.h>
#include <signal.h>
#include <stdlib.h>
#include <sys/prctl.h>
#include <unistd.h>

#include "internals.h"

extern char **environ;

static void closefrom(int lowfd);

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Exec_pexec(JNIEnv *env, jclass thisObj,
        jint fd_stdin, jint fd_stdout, jint fd_stderr, jstring filename, jobjectArray argv, jobjectArray envp) {
    if (filename == NULL) {
        return SETERR(EFAULT);
    }
    // create C filename
    const char* filename_b = (*env)->GetStringUTFChars(env, filename, NULL);
    // create C argv
    const int argv_len = argv == NULL ? 0 : (*env)->GetArrayLength(env, argv);
    char* argv_b[argv_len + 1];
    if (argv != NULL) {
        for (int i = 0; i < argv_len; i++) {
            jstring arg = (jstring)(*env)->GetObjectArrayElement(env, argv, i);
            argv_b[i] = (char*) (*env)->GetStringUTFChars(env, arg, NULL);
        }
    }
    argv_b[argv_len] = NULL;
    // add C envp
    const int envp_len = envp == NULL ? 0 : (*env)->GetArrayLength(env, envp);
    char * envp_b[envp_len + 1];
    char ** child_environ;
    if (envp != NULL) {
        for (int i = 0; i < envp_len; i++) {
            jstring evi = (jstring)(*env)->GetObjectArrayElement(env, envp, i);
            envp_b[i] = (char*) (*env)->GetStringUTFChars(env, evi, NULL);
        }
        envp_b[envp_len] = NULL;
        child_environ = envp_b;
    } else {
        child_environ = environ;
    }
    // fork
    pid_t pid = fork();
    if (pid == 0) {
        if (prctl(PR_SET_PDEATHSIG, SIGTERM, 0L, 0L, 0L) != 0) {
            perror("PR_SET_PDEATHSIG");
        }
        // child
        if (fd_stdin != 0) {
            close(0);
            if (dup2(fd_stdin, 0) == -1) {
                perror("dup stdin");
            }
        }
        if (fd_stdout != 1) {
            close(1);
            if (dup2(fd_stdout, 1) == -1) {
                perror("dup stdout");
            }
        }
        if (fd_stderr != 2) {
            close(2);
            if (dup2(fd_stderr, 2) == -1) {
                perror("dup stderr");
            }
        }
        closefrom(3);
        if (execve(filename_b, argv_b, child_environ) == -1) {
            perror("execve");
        }
        exit(1);
    }
    // delete C filename
    (*env)->ReleaseStringUTFChars(env, filename, filename_b);
    // delete C argv
    if (argv != NULL) {
        for (int i = 0; i < argv_len; i++) {
            jstring arg = (jstring)(*env)->GetObjectArrayElement(env, argv, i);
            (*env)->ReleaseStringUTFChars(env, arg, argv_b[i]);
        }
    }
    // delete C envp
    if (envp != NULL) {
        for (int i = 0; i < envp_len; i++) {
            jstring evi = (jstring)(*env)->GetObjectArrayElement(env, envp, i);
            (*env)->ReleaseStringUTFChars(env, evi, envp_b[i]);
        }
    }
    //
    return ERR(pid);
}

static void closefrom(int lowfd) {
    struct dirent **namelist;
    int n = scandir("/proc/self/fd", &namelist, NULL, alphasort);
    if (n >= 0) {
        while (n--) {
            if (namelist[n]->d_type != DT_DIR
                    && namelist[n]->d_name[0] >= '0'
                    && namelist[n]->d_name[0] <= '9') {
                int fd = atoi(namelist[n]->d_name);
                if (fd >= lowfd) {
                    close(fd);
                }
            }
            free(namelist[n]);
        }
        free(namelist);
    }
}
