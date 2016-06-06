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
#include <string.h>
#include <sys/prctl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>

#include "internals.h"

extern char **environ;

/**
 * Close all file descriptors, starting from {@code lowfd}
 * @param lowfd the lowest fd to close
 */
static void closefrom(int lowfd);

/**
 * Create specified directory and all parent directories if non-existent
 * <p>
 * If {@code pathname} already exists, 0 would be returned.
 * </p>
 *
 * @param pathname Directory to create (may be modified during the process)
 * @param mode Mode of the directory as well as all parent directories
 * @return numbers of directories created, or -1 on error
 */
static int mkdirs(const char *pathname, mode_t mode);

static pid_t pexec(int fd_stdin, int fd_stdout, int fd_stderr, const char *filename,
        char * const argv[], char * const envp[]);

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall2_mkdirs(JNIEnv *env, jclass thisObj,
        jbyteArray pathname, jint mode) {
    return ERR(mkdirs(resolveCS(pathname), UI(mode)));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_Syscall2_pexec(JNIEnv *env, jclass thisObj,
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

static int mkdirs(const char *pathname, mode_t mode) {
    if (mkdir(pathname, mode) == 0) {
        errno = 0;
        return 1;
    }
    switch (errno) {
    case EEXIST:
        errno = 0;
        return 0;
    case ENOENT: {
        char* pathname_sep = strrchr(pathname, '/');
        if (pathname_sep == NULL) {
            return -1; // errno = ENOENT
        }
        // create parent
        *pathname_sep = '\0';
        int ret_p = mkdirs(pathname, mode);
        *pathname_sep = '/';
        if (ret_p == -1) {
            return -1; // pass errno
        }
        // create self
        if (mkdir(pathname, mode) == 0) {
            errno = 0;
            return 1 + ret_p;
        } else if (errno == EEXIST) {
            errno = 0;
            return ret_p;
        } else {
            return -1; // pass errno
        }
    }
    default:
        return -1; // pass errno
    }
}
