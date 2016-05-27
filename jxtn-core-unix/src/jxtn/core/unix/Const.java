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
 * Syscall constants
 *
 * @author aqd
 */
public final class Const {

    /* access */

    public static final int F_OK = 0;
    public static final int X_OK = 1;
    public static final int W_OK = 2;
    public static final int R_OK = 4;

    /* open */

    public static final int O_RDONLY /*   */ = 000000000;
    public static final int O_WRONLY /*   */ = 000000001;
    public static final int O_RDWR /*     */ = 000000002;
    public static final int O_ACCMODE /*  */ = 000000003;
    public static final int O_CREAT /*    */ = 000000100;
    public static final int O_EXCL /*     */ = 000000200;
    public static final int O_NOCTTY /*   */ = 000000400;
    public static final int O_TRUNC /*    */ = 000001000;
    public static final int O_APPEND /*   */ = 000002000;
    public static final int O_NONBLOCK /* */ = 000004000;
    public static final int O_NDELAY /*   */ = 000004000;
    public static final int O_DSYNC /*    */ = 000010000;
    public static final int O_ASYNC /*    */ = 000020000;
    public static final int O_DIRECT /*   */ = 000040000;
    public static final int O_LARGEFILE /**/ = 000100000;
    public static final int O_DIRECTORY /**/ = 000200000;
    public static final int O_NOFOLLOW /* */ = 000400000;
    public static final int O_CLOEXEC /*  */ = 002000000;
    public static final int O_SYNC /*     */ = 004010000;
    public static final int O_FSYNC /*    */ = 004010000;
    public static final int O_RSYNC /*    */ = 004010000;
    public static final int O_NOATIME /*  */ = 001000000;
    public static final int O_PATH /*     */ = 010000000;
    public static final int O_TMPFILE /*  */ = 020200000;

    /* fadvise */

    public static final int POSIX_FADV_NORMAL = 0; /* No further special treatment. */
    public static final int POSIX_FADV_RANDOM = 1; /* Expect random page references. */
    public static final int POSIX_FADV_SEQUENTIAL = 2; /* Expect sequential page references. */
    public static final int POSIX_FADV_WILLNEED = 3; /* Will need these pages. */
    public static final int POSIX_FADV_DONTNEED = 4; /* Don't need these pages. */
    public static final int POSIX_FADV_NOREUSE = 5; /* Data will be accessed once. */

    /* lseek */

    public static final int SEEK_SET = 0; /* Seek from beginning of file. */
    public static final int SEEK_CUR = 1; /* Seek from current position. */
    public static final int SEEK_END = 2; /* Seek from end of file. */
    public static final int SEEK_DATA = 3; /* Seek to next data. */
    public static final int SEEK_HOLE = 4; /* Seek to next hole. */

    /* madvise */

    public static final int MADV_NORMAL = 0; /* No further special treatment. */
    public static final int MADV_RANDOM = 1; /* Expect random page references. */
    public static final int MADV_SEQUENTIAL = 2; /* Expect sequential page references. */
    public static final int MADV_WILLNEED = 3; /* Will need these pages. */
    public static final int MADV_DONTNEED = 4; /* Don't need these pages. */
    public static final int MADV_REMOVE = 9; /* Remove these pages and resources. */
    public static final int MADV_DONTFORK = 10; /* Do not inherit across fork. */
    public static final int MADV_DOFORK = 11; /* Do inherit across fork. */
    public static final int MADV_MERGEABLE = 12; /* KSM may merge identical pages. */
    public static final int MADV_UNMERGEABLE = 13; /* KSM may not merge identical pages. */
    public static final int MADV_HUGEPAGE = 14; /* Worth backing with hugepages. */
    public static final int MADV_NOHUGEPAGE = 15; /* Not worth backing with hugepages. */
    public static final int MADV_DONTDUMP = 16; /* Explicity exclude from the core dump,
                                                overrides the coredump filter bits. */
    public static final int MADV_DODUMP = 17; /* Clear the MADV_DONTDUMP flag. */
    public static final int MADV_HWPOISON = 100; /* Poison a page for testing. */

    public static final int POSIX_MADV_NORMAL = 0; /* No further special treatment. */
    public static final int POSIX_MADV_RANDOM = 1; /* Expect random page references. */
    public static final int POSIX_MADV_SEQUENTIAL = 2; /* Expect sequential page references. */
    public static final int POSIX_MADV_WILLNEED = 3; /* Will need these pages. */
    public static final int POSIX_MADV_DONTNEED = 4; /* Don't need these pages. */

    /* mlockall */

    public static final int MCL_CURRENT = 1; /* Lock all currently mapped pages. */
    public static final int MCL_FUTURE = 2; /* Lock all additions to address space. */
    public static final int MCL_ONFAULT = 4; /* Lock all pages that are faulted in. */

    /* mmap */

    public static final int PROT_NONE = 0x0; /* Page can not be accessed. */
    public static final int PROT_READ = 0x1; /* Page can be read. */
    public static final int PROT_WRITE = 0x2; /* Page can be written. */
    public static final int PROT_EXEC = 0x4; /* Page can be executed. */
    public static final int PROT_GROWSDOWN = 0x01000000; /* Extend change to start of growsdown vma (mprotect only). */
    public static final int PROT_GROWSUP = 0x02000000; /* Extend change to start of growsup vma (mprotect only). */

    public static final int MAP_SHARED = 0x01; /* Share changes. */
    public static final int MAP_PRIVATE = 0x02; /* Changes are private. */
    public static final int MAP_FIXED = 0x10; /* Interpret addr exactly. */
    public static final int MAP_ANONYMOUS = 0x20; /* Don't use a file. */
    public static final int MAP_ANON = MAP_ANONYMOUS;
    public static final int MAP_HUGE_SHIFT = 26;
    public static final int MAP_HUGE_MASK = 0x3f;

    public static final long MAP_FAILED = -1L;

    /* mremap */

    public static final int MREMAP_MAYMOVE = 1;
    public static final int MREMAP_FIXED = 2;

    /* msync */

    public static final int MS_ASYNC = 1; /* Sync memory asynchronously. */
    public static final int MS_SYNC = 4; /* Synchronous memory sync. */
    public static final int MS_INVALIDATE = 2; /* Invalidate the caches. */

    /* signal */

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

    /* wait */

    public static final int WNOHANG = 1; /* Don't block waiting.  */
    public static final int WUNTRACED = 2; /* Report status of stopped children.  */

    /* Bits in the fourth argument to `waitid'.  */
    public static final int WSTOPPED = 2; /* Report stopped child (same as WUNTRACED). */
    public static final int WEXITED = 4; /* Report dead child.  */
    public static final int WCONTINUED = 8; /* Report continued child.  */
    public static final int WNOWAIT = 0x01000000; /* Don't reap, just poll status.  */
    public static final int __WNOTHREAD = 0x20000000; /* Don't wait on children of other threads in this group */
    public static final int __WALL = 0x40000000; /* Wait for any child.  */
    public static final int __WCLONE = 0x80000000; /* Wait for cloned process.  */

    /* xattr */

    public static final int XATTR_CREATE = 0x1; /* set value, fail if attr already exists */
    public static final int XATTR_REPLACE = 0x2; /* set value, fail if attr does not exist */

    private Const() {
    }
}
