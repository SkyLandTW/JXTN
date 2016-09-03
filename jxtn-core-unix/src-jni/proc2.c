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

#include <linux/limits.h>
#include <signal.h>
#include <stdlib.h>
#include <string.h>
#include <sys/prctl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>

#include "dirent.h"
#include "internals.h"

extern char **environ;

/**
 * Close all file descriptors, starting from {@code lowfd}
 * @param lowfd the lowest fd to close
 */
static void closefrom(int lowfd);

static pid_t pexec(int fd_stdin, int fd_stdout, int fd_stderr, const char *filename,
        char * const argv[], char * const envp[]);

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeProc2_pexec(JNIEnv *env, jclass thisObj,
        jint fd_stdin, jint fd_stdout, jint fd_stderr, jbyteArray filename, jobjectArray argv, jobjectArray envp) {
    if (filename == NULL) {
        return SETERR(EFAULT);
    }
    // create C argv
    const int argv_len = argv == NULL ? 0 : (*env)->GetArrayLength(env, argv);
    char* argv_b[argv_len + 1];
    if (argv != NULL) {
        for (int i = 0; i < argv_len; i++) {
            jbyteArray arg = (jstring)(*env)->GetObjectArrayElement(env, argv, i);
            argv_b[i] = (char*) resolveCS(arg);
        }
    }
    argv_b[argv_len] = NULL;
    // create C envp
    const int envp_len = envp == NULL ? 0 : (*env)->GetArrayLength(env, envp);
    char * envp_b[envp_len + 1];
    char ** child_environ;
    if (envp != NULL) {
        for (int i = 0; i < envp_len; i++) {
            jbyteArray evi = (jstring)(*env)->GetObjectArrayElement(env, envp, i);
            envp_b[i] = (char*) resolveCS(evi);
        }
        envp_b[envp_len] = NULL;
        child_environ = envp_b;
    } else {
        child_environ = environ;
    }
    return ERR(pexec(fd_stdin, fd_stdout, fd_stderr, resolveCS(filename), argv_b, child_environ));
}

static void closefrom(int lowfd) {
    int dirfd = open("/proc/self/fd", O_RDONLY | O_CLOEXEC | O_DIRECTORY, 0);
    if (dirfd < 0) {
        perror("open /proc/self/fd");
        return;
    }
    void* dirp_buf = malloc(GETDENTS64_BUF_SIZE);
    struct linux_dirent64 * dirp = (struct linux_dirent64 *) dirp_buf;
    int ret;
    while ((ret = getdents64(dirfd, dirp, GETDENTS64_BUF_SIZE)) > 0) {
        void* d_buf = dirp_buf;
        void* d_end = dirp_buf + ret;
        while (d_buf < d_end) {
            struct linux_dirent64 * d = (struct linux_dirent64 *) d_buf;
            if (dname_is_self(d->d_name) || dname_is_parent(d->d_name)) {
                // nothing
            } else if (d->d_type != DT_DIR
                    && d->d_name[0] >= '0'
                    && d->d_name[0] <= '9') {
                int fd = atoi(d->d_name);
                if (fd >= lowfd) {
                    if (close(fd) == -1) {
                        char str[PATH_MAX];
                        sprintf(str, "%d: close /proc/self/fd/%s\n", getpid(), d->d_name);
                        perror(str);
                    }
                }
            }
            d_buf = d_buf + d->d_reclen;
        }
    }
    free(dirp_buf);
    close(dirfd);
}

static pid_t pexec(int fd_stdin, int fd_stdout, int fd_stderr, const char *filename,
        char * const argv[], char * const envp[]) {
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
        if (execve(filename, argv, envp) == -1) {
            perror("execve");
        }
        exit(1);
    }
    return ERR(pid);
}
