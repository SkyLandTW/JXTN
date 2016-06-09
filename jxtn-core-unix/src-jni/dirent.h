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
#include <stdlib.h>
#include <sys/syscall.h>
#include <unistd.h>

#define GETDENTS64_BUF_SIZE (256 * 1024)
#ifndef O_NOATIME
#define O_NOATIME __O_NOATIME
#endif

enum
{
    DT_UNKNOWN = 0,
    DT_FIFO = 1,
    DT_CHR = 2,
    DT_DIR = 4,
    DT_BLK = 6,
    DT_REG = 8,
    DT_LNK = 10,
    DT_SOCK = 12,
    DT_WHT = 14
};

struct linux_dirent64 {
    unsigned long d_ino; /* 64-bit inode number */
    unsigned long d_off; /* 64-bit offset to next structure */
    unsigned short d_reclen; /* Size of this dirent */
    unsigned char d_type; /* File type */
    char d_name[]; /* Filename (null-terminated) */
};

typedef int (*dirent_handler_t)(int dirfd, struct linux_dirent64* dirp, void* param);

static inline int dname_is_self(char* name) {
    return name[0] == '.' && name[1] == '\0';
}

static inline int dname_is_parent(char* name) {
    return name[0] == '.' && name[1] == '.' && name[2] == '\0';
}

static inline int getdents64(int fd, struct linux_dirent64 *dirp, int count) {
    return (int) syscall(SYS_getdents64, fd, dirp, count);
}

static inline int walkdirs(int dirfd, void* param,
        dirent_handler_t on_dir_begin, dirent_handler_t on_file, dirent_handler_t on_dir_end) {
    void* dirp_buf = malloc(GETDENTS64_BUF_SIZE);
    struct linux_dirent64 * dirp = (struct linux_dirent64 *) dirp_buf;
    int total = 0;
    int ret;
    int err;
    while ((ret = getdents64(dirfd, dirp, GETDENTS64_BUF_SIZE)) > 0) {
        void* d_buf = dirp_buf;
        void* d_end = dirp_buf + ret;
        while (d_buf < d_end) {
            struct linux_dirent64 * d = (struct linux_dirent64 *) d_buf;
            if (dname_is_self(d->d_name) || dname_is_parent(d->d_name)) {
                // nothing
            } else if (d->d_type == DT_DIR) {
                if (on_dir_begin != NULL) {
                    if ((ret = on_dir_begin(dirfd, d, param)) < 0) {
                        err = errno;
                        goto END;
                    }
                    total += ret;
                }
                int child_dirfd = openat(dirfd, d->d_name, O_RDONLY | O_CLOEXEC | O_DIRECTORY | O_NOATIME);
                if (child_dirfd == -1 && errno == EPERM) {
                    child_dirfd = openat(dirfd, d->d_name, O_RDONLY | O_CLOEXEC | O_DIRECTORY);
                }
                if (child_dirfd == -1) {
                    ret = child_dirfd;
                    err = errno;
                    goto END;
                }
                ret = walkdirs(child_dirfd, param, on_dir_begin, on_file, on_dir_end);
                if (ret < 0) {
                    err = errno;
                }
                close(child_dirfd);
                if (ret < 0) {
                    goto END;
                }
                total += ret;
                if (on_dir_end != NULL) {
                    if ((ret = on_dir_end(dirfd, d, param)) < 0) {
                        err = errno;
                        goto END;
                    }
                    total += ret;
                }
            } else {
                if (on_file != NULL) {
                    if ((ret = on_file(dirfd, d, param)) < 0) {
                        err = errno;
                        goto END;
                    }
                    total += ret;
                }
            }
            d_buf = d_buf + d->d_reclen;
        }
    }
    err = errno;
    END:
    free(dirp_buf);
    if (ret >= 0) {
        errno = 0;
        return total;
    } else {
        errno = err;
        return ret;
    }
}
