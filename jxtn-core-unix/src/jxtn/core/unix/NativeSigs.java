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

/**
 * Signal-related syscall wrappers
 *
 * @author aqd
 */
public final class NativeSigs extends JNIBase {

    public static final int SIGHUP = 1; /* Hangup (POSIX).  */
    public static final int SIGINT = 2; /* Interrupt (ANSI).  */
    public static final int SIGQUIT = 3; /* Quit (POSIX).  */
    public static final int SIGILL = 4; /* Illegal instruction (ANSI).  */
    public static final int SIGTRAP = 5; /* Trace trap (POSIX).  */
    public static final int SIGABRT = 6; /* Abort (ANSI).  */
    public static final int SIGIOT = 6; /* IOT trap (4.2 BSD).  */
    public static final int SIGBUS = 7; /* BUS error (4.2 BSD).  */
    public static final int SIGFPE = 8; /* Floating-point exception (ANSI).  */
    public static final int SIGKILL = 9; /* Kill, unblockable (POSIX).  */
    public static final int SIGUSR1 = 10; /* User-defined signal 1 (POSIX).  */
    public static final int SIGSEGV = 11; /* Segmentation violation (ANSI).  */
    public static final int SIGUSR2 = 12; /* User-defined signal 2 (POSIX).  */
    public static final int SIGPIPE = 13; /* Broken pipe (POSIX).  */
    public static final int SIGALRM = 14; /* Alarm clock (POSIX).  */
    public static final int SIGTERM = 15; /* Termination (ANSI).  */
    public static final int SIGSTKFLT = 16; /* Stack fault.  */
    public static final int SIGCHLD = 17; /* Child status has changed (POSIX).  */
    public static final int SIGCLD = SIGCHLD; /* Same as SIGCHLD (System V).  */
    public static final int SIGCONT = 18; /* Continue (POSIX).  */
    public static final int SIGSTOP = 19; /* Stop, unblockable (POSIX).  */
    public static final int SIGTSTP = 20; /* Keyboard stop (POSIX).  */
    public static final int SIGTTIN = 21; /* Background read from tty (POSIX).  */
    public static final int SIGTTOU = 22; /* Background write to tty (POSIX).  */
    public static final int SIGURG = 23; /* Urgent condition on socket (4.2 BSD).  */
    public static final int SIGXCPU = 24; /* CPU limit exceeded (4.2 BSD).  */
    public static final int SIGXFSZ = 25; /* File size limit exceeded (4.2 BSD).  */
    public static final int SIGVTALRM = 26; /* Virtual alarm clock (4.2 BSD).  */
    public static final int SIGPROF = 27; /* Profiling alarm clock (4.2 BSD).  */
    public static final int SIGWINCH = 28; /* Window size change (4.3 BSD, Sun).  */
    public static final int SIGIO = 29; /* I/O now possible (4.2 BSD).  */
    public static final int SIGPOLL = SIGIO; /* Pollable event occurred (System V).  */
    public static final int SIGPWR = 30; /* Power failure restart (System V).  */
    public static final int SIGSYS = 31; /* Bad system call.  */

    public static native int kill(int pid, int sig);

    private NativeSigs() {
    }
}
