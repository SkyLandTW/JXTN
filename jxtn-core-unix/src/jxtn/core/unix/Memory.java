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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import sun.misc.Unsafe;
import sun.nio.ch.FileChannelImpl;

/**
 * Access to memory-related internal classes
 *
 * @author aqd
 */
public final class Memory {

    public static final Unsafe unsafe;
    public static final Method mmap;
    public static final Method unmap;
    public static final Method reserveMemory;
    public static final Method unreserveMemory;

    static {
        try {
            Field singleoneInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
            singleoneInstanceField.setAccessible(true);
            unsafe = (Unsafe) singleoneInstanceField.get(null);
            mmap = getMethod(FileChannelImpl.class, "map0", int.class, long.class, long.class);
            unmap = getMethod(FileChannelImpl.class, "unmap0", long.class, long.class);
            Class<?> bitsClass = Class.forName("java.nio.Bits");
            reserveMemory = getMethod(bitsClass, "reserveMemory", long.class, int.class);
            unreserveMemory = getMethod(bitsClass, "unreserveMemory", long.class, int.class);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public static void reserveMemory(long size, int cap) {
        try {
            reserveMemory.invoke(null, size, cap);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public static void unreserveMemory(long size, int cap) {
        try {
            unreserveMemory.invoke(null, size, cap);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    private static Method getMethod(Class<?> cls, String name, Class<?>... params)
            throws ReflectiveOperationException {
        Method m = cls.getDeclaredMethod(name, params);
        m.setAccessible(true);
        return m;
    }

    private Memory() {
    }
}
