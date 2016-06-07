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
#include <sys/stat.h>
#include <sys/types.h>

#include "internals.h"

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

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NativeDirs2_mkdirs(JNIEnv *env, jclass thisObj,
        jbyteArray pathname, jint mode) {
    return ERR(mkdirs(resolveCS(pathname), UI(mode)));
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
