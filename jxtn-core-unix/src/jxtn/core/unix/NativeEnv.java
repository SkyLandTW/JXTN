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
 * Program stack and environment
 *
 * @author aqd
 */
public final class NativeEnv extends JNIBase {

    /**
     * Get the count of command arguments
     *
     * @return count of command arguments
     */
    public static native int getArgc();

    /**
     * Get the value of specified argument
     *
     * @param index index of the argument to get
     * @return argument value
     */
    public static native String getArgv(int index);

    /**
     * Set the value of specified argument
     * <p>
     * {@code value} is limited by the size of the initial argument value; truncated automatically if too long.
     * </p>
     *
     * @param index index of the argument to set
     * @param value new argument value
     */
    public static native void setArgv(int index, String value);

    private NativeEnv() {
    }
}
