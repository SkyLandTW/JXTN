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

package java.lang.reflect;

/**
 * {@link Member}的延伸功能
 *
 * @author AqD
 */
public interface MemberExt
{
    default boolean isPublic()
    {
        Member thiz = (Member) this;
        return Modifier.isPublic(thiz.getModifiers());
    }

    default boolean isPrivate()
    {
        Member thiz = (Member) this;
        return Modifier.isPrivate(thiz.getModifiers());
    }

    default boolean isProtected()
    {
        Member thiz = (Member) this;
        return Modifier.isProtected(thiz.getModifiers());
    }

    default boolean isStatic()
    {
        Member thiz = (Member) this;
        return Modifier.isStatic(thiz.getModifiers());
    }

    default boolean isFinal()
    {
        Member thiz = (Member) this;
        return Modifier.isFinal(thiz.getModifiers());
    }

    default boolean isSynchronized()
    {
        Member thiz = (Member) this;
        return Modifier.isSynchronized(thiz.getModifiers());
    }

    default boolean isVolatile()
    {
        Member thiz = (Member) this;
        return Modifier.isVolatile(thiz.getModifiers());
    }

    default boolean isTransient()
    {
        Member thiz = (Member) this;
        return Modifier.isTransient(thiz.getModifiers());
    }

    default boolean isNative()
    {
        Member thiz = (Member) this;
        return Modifier.isNative(thiz.getModifiers());
    }

    default boolean isInterface()
    {
        Member thiz = (Member) this;
        return Modifier.isInterface(thiz.getModifiers());
    }

    default boolean isAbstract()
    {
        Member thiz = (Member) this;
        return Modifier.isAbstract(thiz.getModifiers());
    }

    default boolean isStrict()
    {
        Member thiz = (Member) this;
        return Modifier.isStrict(thiz.getModifiers());
    }

    default String getAccessLevel()
    {
        if (this.isPublic())
            return "public";
        if (this.isProtected())
            return "protected";
        if (this.isPrivate())
            return "private";
        return "package-private";
    }
}
