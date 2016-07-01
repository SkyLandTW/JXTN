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

import java.io.Closeable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * {@link ByteBuffer}-like wrapper for unsafe/native memory block
 *
 * @author aqd
 */
public interface NativeBuffer extends Closeable {

    ByteOrder order();

    boolean hasRemaining();

    long length();

    void move(long relativePosition);

    long position();

    void position(long absolutePosition);

    byte getByte();

    short getShort();

    int getInt();

    long getLong();

    short getUByte();

    int getUShort();

    long getUInt();

    void getData(byte[] data);

    byte getByteAt(long offset);

    short getShortAt(long offset);

    int getIntAt(long offset);

    long getLongAt(long offset);

    short getUByteAt(long offset);

    int getUShortAt(long offset);

    long getUIntAt(long offset);

    void getDataAt(long offset, byte[] data);

    void putByte(byte val);

    void putShort(short val);

    void putInt(int val);

    void putLong(long val);

    void putData(byte[] data);

    void putByteAt(long offset, byte val);

    void putShortAt(long offset, short val);

    void putIntAt(long offset, int val);

    void putLongAt(long offset, long val);

    void putDataAt(long offset, byte[] data);

    @Override
    void close();
}
