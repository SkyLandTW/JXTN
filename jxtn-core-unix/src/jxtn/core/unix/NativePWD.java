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

public final class NativePWD extends JNIBase {

    private static final int NGROUPS = 16;
    private static final int GROUP_NAME_SZ = 32;

    public static int getgrouplist(String user, Out<String[]> groups_out) {
        byte[][] raw_groups = new byte[NGROUPS][];
        for (int i = 0; i < raw_groups.length; i++) {
            raw_groups[i] = new byte[GROUP_NAME_SZ];
        }
        int ngroups = getgrouplist(tName(user), raw_groups);
        if (ngroups == -1) {
            return -1;
        }
        String[] str_groups = new String[ngroups];
        for (int i = 0; i < ngroups; i++) {
            str_groups[i] = CStrings.toString(raw_groups[i]);
        }
        groups_out.set(str_groups);
        return ngroups;
    }

    private static native int getgrouplist(byte[] user, byte[][] groups);

    private NativePWD() {
    }
}