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
package jxtn.core.unix;

import java.nio.file.Path;

/**
 * Extensions to UNIX system calls
 *
 * @author aqd
 */
public final class Syscall2 extends Unix {

    public static int pexec(int fd_stdin, int fd_stdout, int fd_stderr,
            Path filename, String[] argv, String[] envp) {
        return pexec(fd_stdin, fd_stdout, fd_stderr, tPath(filename),
                argv == null ? null : tArgs(argv),
                envp == null ? null : tArgs(envp));
    }

    public static int pexec(int fd_stdin, int fd_stdout, int fd_stderr,
            String filename, String[] argv, String[] envp) {
        return pexec(fd_stdin, fd_stdout, fd_stderr, tPath(filename),
                argv == null ? null : tArgs(argv),
                envp == null ? null : tArgs(envp));
    }

    /**
     * Launch a child program with specified FDs of stdin/stdout/stderr
     * <p>
     * The {@code filename}, {@code argv} and {@code envp} have the same purpose as the parameters to the system call
     * {@code execve()}.
     * </p>
     *
     * @param fd_stdin FD of child's stdin, set to 0 if no redirection needed
     * @param fd_stdout FD of child's stdout, set to 1 if no redirection needed
     * @param fd_stderr FD of child's stderr, set to 2 if no redirection needed
     * @param filename Path to the child program file
     * @param argv Arguments to the child program, should contain the process name at least
     * @param envp Environment variables for the child program, set to null for the parent's environment
     * @return PID of the launched child process, or -1 if error occurred (see {@link Errno#errno})
     */
    public static native int pexec(int fd_stdin, int fd_stdout, int fd_stderr,
            byte[] filename, byte[][] argv, byte[][] envp);

    public static int mkdirs(Path pathname, int mode) {
        return mkdirs(tPath(pathname), mode);
    }

    public static int mkdirs(String pathname, int mode) {
        return mkdirs(tPath(pathname), mode);
    }

    public static native int mkdirs(byte[] pathname, int mode);

}
