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

#define _GNU_SOURCE

#include <fcntl.h>
#include <linux/limits.h>
#include <sys/sendfile.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>

#include "internals.h"

static ssize_t copyfile_sparse(int srcfd, int dstfd);
static int copyat(int olddirfd, const char* oldpath, int newdirfd, const char* newpath);

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles2_copy(JNIEnv *env, jclass thisObj,
        jbyteArray oldpath, jbyteArray newpath) {
    char* c_oldpath = ACOPY_CS(oldpath);
    char* c_newpath = ACOPY_CS(newpath);
    return ERR(copyat(AT_FDCWD, c_oldpath, AT_FDCWD, c_newpath));
}

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeFiles2_copyat(JNIEnv *env, jclass thisObj,
        jint olddirfd, jbyteArray oldpath, jint newdirfd, jbyteArray newpath) {
    char* c_oldpath = ACOPY_CS(oldpath);
    char* c_newpath = ACOPY_CS(newpath);
    return ERR(copyat(olddirfd, c_oldpath, newdirfd, c_newpath));
}

static int copyat(int olddirfd, const char* oldpath, int newdirfd, const char* newpath) {
    int err;
    int oldfd = openat(olddirfd, oldpath, O_RDONLY | O_CLOEXEC | O_NOFOLLOW | O_NOATIME);
    if (oldfd == -1 && errno == EPERM) {
        oldfd = openat(olddirfd, oldpath, O_RDONLY | O_CLOEXEC | O_NOFOLLOW);
    }
    if (oldfd == -1 && errno == ELOOP) {
        char oldtarget[PATH_MAX];
        ssize_t oldlen = readlinkat(olddirfd, oldpath, oldtarget, sizeof(oldtarget));
        if (oldlen == -1) {
            return -1;
        }
        oldtarget[oldlen] = '\0';
        return symlinkat(oldtarget, newdirfd, newpath);
    }
    if (oldfd == -1) {
        return -1;
    }
    struct stat oldstat;
    if (fstat(oldfd, &oldstat) == -1) {
        err = errno;
        close(oldfd);
        errno = err;
        return -1;
    }
    int newfd = openat(newdirfd, newpath, O_WRONLY | O_CLOEXEC | O_CREAT | O_TRUNC | O_NOATIME, oldstat.st_mode);
    if (newfd == -1 && errno == EPERM) {
        newfd = openat(newdirfd, newpath, O_WRONLY | O_CLOEXEC | O_CREAT | O_TRUNC, oldstat.st_mode);
    }
    if (newfd == -1) {
        err = errno;
        close(oldfd);
        errno = err;
        return -1;
    }
    if (fchown(newfd, oldstat.st_uid, oldstat.st_gid) == -1) {
        // ignore error
    }
    if (copyfile_sparse(oldfd, newfd) == -1) {
        err = errno;
        close(newfd);
        close(oldfd);
        errno = err;
        return -1;
    }
    close(newfd);
    close(oldfd);
    struct timespec times[2];
    times[0] = oldstat.st_atim;
    times[1] = oldstat.st_mtim;
    utimensat(newdirfd, newpath, times, 0); // ignore error
    return 0;
}

static ssize_t copyfile_sparse(int srcfd, int dstfd) {
    ssize_t srcend = lseek(srcfd, 0L, SEEK_END);
    if (srcend == -1L) {
        return -1L;
    }
    if (ftruncate(dstfd, srcend) == -1) {
        return -1L;
    }
    ssize_t num_total = 0L;
    ssize_t srcpos = 0L;
    while (srcpos < srcend) {
        ssize_t next_beg = srcpos = lseek(srcfd, srcpos, SEEK_DATA);
        if (next_beg == -1L) {
            return -1L;
        }
        ssize_t next_end = srcpos = lseek(srcfd, srcpos, SEEK_HOLE);
        if (next_end == -1L) {
            return -1L;
        }
        ssize_t next_len = next_end - next_beg;
        if (next_len > 0) {
            if (lseek(srcfd, next_beg, SEEK_SET) == -1L) {
                return -1L;
            }
            if (lseek(dstfd, next_beg, SEEK_SET) == -1L) {
                return -1L;
            }
            ssize_t num = sendfile(dstfd, srcfd, NULL, UL(next_len));
            if (num == -1L) {
                return -1L;
            }
            num_total += num;
        }
        srcpos = next_end;
    }
    return num_total;
}

