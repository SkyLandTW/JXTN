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

package java.util;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * {@link Map}的延伸功能
 *
 * @author AqD
 * @param <K> 鍵值型態
 * @param <V> 項目值型態
 */
public interface MapExt<K, V>
{
    /**
     * 對照目前的項目值以產生新的{@link HashMap}
     *
     * @param mapper 對照項目值的函數
     * @return 對照後的新{@link HashMap}(不依賴原有的)
     */
    default <V2> HashMap<K, V2> mapValues(Function<? super V, V2> mapper)
    {
        Map<K, V> thiz = (Map<K, V>) this;
        HashMap<K, V2> result = new HashMap<>(thiz.size());
        thiz.forEach((k, v) -> result.put(k, mapper.apply(v)));
        return result;
    }

    /**
     * 對照目前的項目值以產生新的{@link HashMap}
     *
     * @param mapper 對照項目值的函數
     * @return 對照後的新{@link HashMap}(不依賴原有的)
     */
    default <V2> HashMap<K, V2> mapValues(BiFunction<? super K, ? super V, V2> mapper)
    {
        Map<K, V> thiz = (Map<K, V>) this;
        HashMap<K, V2> result = new HashMap<>(thiz.size());
        thiz.forEach((k, v) -> result.put(k, mapper.apply(k, v)));
        return result;
    }
}
