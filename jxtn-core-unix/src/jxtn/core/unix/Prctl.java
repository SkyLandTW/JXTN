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

import sun.misc.Unsafe;

/**
 * Helper class for {@link Syscall#prctl}
 *
 * @author aqd
 */
public final class Prctl {

    public static final int PR_SET_PDEATHSIG = 1;
    public static final int PR_GET_PDEATHSIG = 2;

    /* Get/set current->mm->dumpable */
    public static final int PR_GET_DUMPABLE = 3;
    public static final int PR_SET_DUMPABLE = 4;

    /* Get/set unaligned access control bits (if meaningful) */
    public static final int PR_GET_UNALIGN = 5;
    public static final int PR_SET_UNALIGN = 6;
    public static final int PR_UNALIGN_NOPRINT = 1;
    public static final int PR_UNALIGN_SIGBUS = 2;

    /* Get/set whether or not to drop capabilities on setuid() away from
     * uid 0 (as per security/commoncap.c) */
    public static final int PR_GET_KEEPCAPS = 7;
    public static final int PR_SET_KEEPCAPS = 8;

    /* Get/set floating-point emulation control bits (if meaningful) */
    public static final int PR_GET_FPEMU = 9;
    public static final int PR_SET_FPEMU = 10;
    public static final int PR_FPEMU_NOPRINT = 1;
    public static final int PR_FPEMU_SIGFPE = 2;

    /* Get/set floating-point exception mode (if meaningful) */
    public static final int PR_GET_FPEXC = 11;
    public static final int PR_SET_FPEXC = 12;
    public static final int PR_FP_EXC_SW_ENABLE = 0;
    public static final int PR_FP_EXC_DIV = 0;
    public static final int PR_FP_EXC_OVF = 0;
    public static final int PR_FP_EXC_UND = 0;
    public static final int PR_FP_EXC_RES = 0;
    public static final int PR_FP_EXC_INV = 0;
    public static final int PR_FP_EXC_DISABLED = 0;
    public static final int PR_FP_EXC_NONRECOV = 1;
    public static final int PR_FP_EXC_ASYNC = 2;
    public static final int PR_FP_EXC_PRECISE = 3;

    /* Get/set whether we use statistical process timing or accurate timestamp
     * based process timing */
    public static final int PR_GET_TIMING = 13;
    public static final int PR_SET_TIMING = 14;
    public static final int PR_TIMING_STATISTICAL = 0; /* Normal, traditional, statistical process timing */
    public static final int PR_TIMING_TIMESTAMP = 1; /* Accurate timestamp based process timing */

    public static final int PR_SET_NAME = 15;
    public static final int PR_GET_NAME = 16;

    /* Get/set process endian */
    public static final int PR_GET_ENDIAN = 19;
    public static final int PR_SET_ENDIAN = 20;
    public static final int PR_ENDIAN_BIG = 0;
    public static final int PR_ENDIAN_LITTLE = 1;
    public static final int PR_ENDIAN_PPC_LITTLE = 2;

    /* Get/set process seccomp mode */
    public static final int PR_GET_SECCOMP = 21;
    public static final int PR_SET_SECCOMP = 22;

    /* Get/set the capability bounding set (as per security/commoncap.c) */
    public static final int PR_CAPBSET_READ = 23;
    public static final int PR_CAPBSET_DROP = 24;

    /* Get/set the process' ability to use the timestamp counter instruction */
    public static final int PR_GET_TSC = 25;
    public static final int PR_SET_TSC = 26;
    public static final int PR_TSC_ENABLE = 1;
    public static final int PR_TSC_SIGSEGV = 2;

    /* Get/set securebits (as per security/commoncap.c) */
    public static final int PR_GET_SECUREBITS = 27;
    public static final int PR_SET_SECUREBITS = 28;

    /*
     * Get/set the timerslack as used by poll/select/nanosleep
     * A value of 0 means "use default"
     */
    public static final int PR_SET_TIMERSLACK = 29;
    public static final int PR_GET_TIMERSLACK = 30;

    public static final int PR_TASK_PERF_EVENTS_DISABLE = 31;
    public static final int PR_TASK_PERF_EVENTS_ENABLE = 32;

    /*
     * Set early/late kill mode for hwpoison memory corruption.
     * This influences when the process gets killed on a memory corruption.
     */
    public static final int PR_MCE_KILL = 33;
    public static final int PR_MCE_KILL_CLEAR = 0;
    public static final int PR_MCE_KILL_SET = 1;

    public static final int PR_MCE_KILL_LATE = 0;
    public static final int PR_MCE_KILL_EARLY = 1;
    public static final int PR_MCE_KILL_DEFAULT = 2;

    public static final int PR_MCE_KILL_GET = 34;

    /*
     * Tune up process memory map specifics.
     */
    public static final int PR_SET_MM = 35;
    public static final int PR_SET_MM_START_CODE = 1;
    public static final int PR_SET_MM_END_CODE = 2;
    public static final int PR_SET_MM_START_DATA = 3;
    public static final int PR_SET_MM_END_DATA = 4;
    public static final int PR_SET_MM_START_STACK = 5;
    public static final int PR_SET_MM_START_BRK = 6;
    public static final int PR_SET_MM_BRK = 7;
    public static final int PR_SET_MM_ARG_START = 8;
    public static final int PR_SET_MM_ARG_END = 9;
    public static final int PR_SET_MM_ENV_START = 10;
    public static final int PR_SET_MM_ENV_END = 11;
    public static final int PR_SET_MM_AUXV = 12;
    public static final int PR_SET_MM_EXE_FILE = 13;
    public static final int PR_SET_MM_MAP = 14;
    public static final int PR_SET_MM_MAP_SIZE = 15;

    /*
     * This structure provides new memory descriptor
     * map which mostly modifies /proc/pid/stat[m]
     * output for a task. This mostly done in a
     * sake of checkpoint/restore functionality.
     */
    // struct prctl_mm_map
    // {
    //         __u64   start_code;             /* code section bounds */
    //         __u64   end_code;
    //         __u64   start_data;             /* data section bounds */
    //         __u64   end_data;
    //         __u64   start_brk;              /* heap for brk() syscall */
    //         __u64   brk;
    //         __u64   start_stack;            /* stack starts at */
    //         __u64   arg_start;              /* command line arguments bounds */
    //         __u64   arg_end;
    //         __u64   env_start;              /* environment variables bounds */
    //         __u64   env_end;
    //         __u64   *auxv;                  /* auxiliary vector */
    //         __u32   auxv_size;              /* vector size */
    //         __u32   exe_fd;                 /* /proc/$pid/exe link file */
    // };

    /*
     * Set specific pid that is allowed to ptrace the current task.
     * A value of 0 mean "no process".
     */
    public static final int PR_SET_PTRACER = 0;

    public static final long PR_SET_PTRACER_ANY = -1L;

    public static final int PR_SET_CHILD_SUBREAPER = 36;
    public static final int PR_GET_CHILD_SUBREAPER = 37;

    /*
     * If no_new_privs is set, then operations that grant new privileges (i.e.
     * execve) will either fail or not grant them.  This affects suid/sgid,
     * file capabilities, and LSMs.
     *
     * Operations that merely manipulate or drop existing privileges (setresuid,
     * capset, etc.) will still work.  Drop those privileges if you want them gone.
     *
     * Changing LSM security domain is considered a new privilege.  So, for example,
     * asking selinux for a specific new context (e.g. with runcon) will result
     * in execve returning -EPERM.
     *
     * See Documentation/prctl/no_new_privs.txt for more details.
     */
    public static final int PR_SET_NO_NEW_PRIVS = 38;
    public static final int PR_GET_NO_NEW_PRIVS = 39;

    public static final int PR_GET_TID_ADDRESS = 40;

    public static final int PR_SET_THP_DISABLE = 41;
    public static final int PR_GET_THP_DISABLE = 42;

    /*
     * Tell the kernel to start/stop helping userspace manage bounds tables.
     */
    public static final int PR_MPX_ENABLE_MANAGEMENT = 43;
    public static final int PR_MPX_DISABLE_MANAGEMENT = 44;

    public static final int PR_SET_FP_MODE = 45;
    public static final int PR_GET_FP_MODE = 46;
    public static final int PR_FP_MODE_FR = (1 << 0); /* 64b FP registers */
    public static final int PR_FP_MODE_FRE = (1 << 1); /* 32b compatibility */

    /* Control the ambient capability set */
    public static final int PR_CAP_AMBIENT = 47;
    public static final int PR_CAP_AMBIENT_IS_SET = 1;
    public static final int PR_CAP_AMBIENT_RAISE = 2;
    public static final int PR_CAP_AMBIENT_LOWER = 3;
    public static final int PR_CAP_AMBIENT_CLEAR_ALL = 4;

    public static String prGetName() {
        final int NAME_LEN = 16;
        Unsafe unsafe = Memory.unsafe;
        long cbuffer = unsafe.allocateMemory(NAME_LEN);
        try {
            if (Syscall.prctl(PR_GET_NAME, cbuffer, 0L, 0L, 0L) == -1) {
                System.err.println("PR_GET_NAME: " + Errno.errName());
                return null;
            }
            byte[] jbuffer = new byte[NAME_LEN];
            unsafe.copyMemory(null, cbuffer, jbuffer, Unsafe.ARRAY_BYTE_BASE_OFFSET, jbuffer.length);
            return CStrings.toString(jbuffer);
        } finally {
            unsafe.freeMemory(cbuffer);
        }
    }

    public static boolean prSetName(String name) {
        final int NAME_LEN = 16;
        Unsafe unsafe = Memory.unsafe;
        long cbuffer = unsafe.allocateMemory(NAME_LEN);
        try {
            byte[] jbuffer = CStrings.from(name, NAME_LEN);
            unsafe.copyMemory(jbuffer, Unsafe.ARRAY_BYTE_BASE_OFFSET, null, cbuffer, jbuffer.length);
            if (Syscall.prctl(PR_SET_NAME, cbuffer, 0L, 0L, 0L) == -1) {
                System.err.println("PR_SET_NAME: " + Errno.errName());
                return false;
            }
            return true;
        } finally {
            unsafe.freeMemory(cbuffer);
        }
    }

    private Prctl() {
    }
}
