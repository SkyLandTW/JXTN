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
 * {@code wait4} syscall wrappers
 * 
 * @author aqd
 */
public final class NativeWait extends JNIBase {

    public static final int WSTOPPED = 2; /* Report stopped child (same as WUNTRACED). */
    public static final int WEXITED = 4; /* Report dead child.  */
    public static final int WCONTINUED = 8; /* Report continued child.  */
    public static final int WNOWAIT = 0x01000000; /* Don't reap, just poll status.  */
    public static final int __WNOTHREAD = 0x20000000; /* Don't wait on children of other threads in this group */
    public static final int __WALL = 0x40000000; /* Wait for any child.  */
    public static final int __WCLONE = 0x80000000; /* Wait for cloned process.  */

    public static final int WNOHANG = 1; /* Don't block waiting.  */
    public static final int WUNTRACED = 2; /* Report status of stopped children.  */

    public static native int waitpid(int pid, int[] status, int options);

    private NativeWait() {
    }
}
