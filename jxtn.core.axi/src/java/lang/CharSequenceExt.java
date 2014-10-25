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

package java.lang;

/**
 * {@link CharSequence}的延伸功能。
 *
 * @author AqD
 */
public interface CharSequenceExt
{
    /**
     * 取得最左側指定長度的字串。
     *
     * @param length 要取得的最大長度
     * @return 最左側的字串，長度不一定達{@code length}
     */
    default String left(int length)
    {
        CharSequence thiz = (CharSequence) this;
        int validLength = Math.min(length, thiz.length());
        return thiz.subSequence(0, validLength).toString();
    }

    /**
     * 取得最右側指定長度的字串。
     *
     * @param length 要取得的最大長度
     * @return 最右側的字串，長度不一定達{@code length}
     */
    default String right(int length)
    {
        CharSequence thiz = (CharSequence) this;
        int validLength = Math.min(length, thiz.length());
        return thiz.subSequence(thiz.length() - validLength, thiz.length()).toString();
    }

    /**
     * 向左補滿空白至指定長度。
     *
     * @param totalLength 要補滿的最大長度
     * @return 補滿後的字串
     */
    default String padLeft(int totalLength)
    {
        return this.padLeft(totalLength, ' ');
    }

    /**
     * 向左補滿字元至指定長度。
     *
     * @param totalLength 要補滿的最大長度
     * @param paddingChar 要補滿的字元
     * @return 補滿後的字串
     */
    default String padLeft(int totalLength, char paddingChar)
    {
        CharSequence thiz = (CharSequence) this;
        if (thiz.length() >= totalLength)
        {
            return this.toString();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < totalLength - thiz.length(); i++)
        {
            sb.append(paddingChar);
        }
        return sb.toString() + thiz.toString();
    }

    /**
     * 向右補滿空白至指定長度。
     *
     * @param totalLength 要補滿的最大長度
     * @return 補滿後的字串
     */
    default String padRight(int totalLength)
    {
        return this.padRight(totalLength, ' ');
    }

    /**
     * 向右補滿字元至指定長度。
     *
     * @param totalLength 要補滿的最大長度
     * @param paddingChar 要補滿的字元
     * @return 補滿後的字串
     */
    default String padRight(int totalLength, char paddingChar)
    {
        CharSequence thiz = (CharSequence) this;
        if (thiz.length() >= totalLength)
        {
            return this.toString();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < totalLength - thiz.length(); i++)
        {
            sb.append(paddingChar);
        }
        return thiz.toString() + sb.toString();
    }

    /**
     * 開頭字元改大寫。
     *
     * @return 開頭字元改大寫後的新字串
     */
    default String toCapitalized()
    {
        CharSequence thiz = (CharSequence) this;
        if (thiz.length() == 0)
            return thiz.toString();
        return thiz.subSequence(0, 1).toString().toUpperCase() + thiz.subSequence(1, thiz.length());
    }

    /**
     * 開頭字元改小寫。
     *
     * @return 開頭字元改小寫後的新字串
     */
    default String toUncapitalized()
    {
        CharSequence thiz = (CharSequence) this;
        if (thiz.length() == 0)
            return thiz.toString();
        return thiz.subSequence(0, 1).toString().toLowerCase() + thiz.subSequence(1, thiz.length());
    }
}
