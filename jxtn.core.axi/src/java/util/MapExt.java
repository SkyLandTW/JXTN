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

import jxtn.core.axi.collections.MappedMap;

/**
 * {@link Map}的延伸功能
 *
 * @author AqD
 * @param <K> 鍵值型態
 * @param <V> 項目值型態
 */
public interface MapExt<K, V>
{
    //////////////////////////////////////////////////////////////////////////
    // 泛型方法
    //

    /**
     * 泛型版本的{@link Map#containsKey}
     *
     * @param key 要檢查是否包含的索引鍵
     * @return true表示索引鍵{@code key}在目前的集合內
     */
    @SuppressWarnings("deprecation")
    default boolean containsKey2(K key)
    {
        Map<K, V> thiz = (Map<K, V>) this;
        return thiz.containsKey(key);
    }

    /**
     * 泛型版本的{@link Map#containsValue}
     *
     * @param value 要檢查是否包含的項目值
     * @return true表示項目值{@code value}在目前的集合內
     */
    @SuppressWarnings("deprecation")
    default boolean containsValue2(V value)
    {
        Map<K, V> thiz = (Map<K, V>) this;
        return thiz.containsValue(value);
    }

    /**
     * 泛型版本的{@link Map#get}
     *
     * @param key 要取得項目值的索引鍵
     * @return 索引鍵{@code key}對照的項目值
     */
    @SuppressWarnings("deprecation")
    default V get2(K key)
    {
        Map<K, V> thiz = (Map<K, V>) this;
        return thiz.get(key);
    }

    /**
     * 泛型版本的{@link Map#remove(Object)}
     *
     * @param key 要移除的索引鍵
     * @return 索引鍵{@code key}對照的項目值
     */
    @SuppressWarnings({ "deprecation", "javadoc" })
    default V remove2(K key)
    {
        Map<K, V> thiz = (Map<K, V>) this;
        return thiz.remove(key);
    }

    /**
     * 泛型版本的{@link Map#remove(Object,Object)}
     *
     * @param key 要移除的索引鍵
     * @param value 要移除的項目值
     * @return true表示移除成功
     */
    @SuppressWarnings({ "deprecation", "javadoc" })
    default boolean remove2(K key, V value)
    {
        Map<K, V> thiz = (Map<K, V>) this;
        return thiz.remove(key, value);
    }

    /**
     * 泛型版本的{@link Map#getOrDefault}
     *
     * @param key 要取得項目值的索引鍵
     * @param defaultValue 預設項目值
     * @return 索引鍵{@code key}對照的項目值，或{@code defaultValue}
     */
    @SuppressWarnings("deprecation")
    default V getOrDefault2(K key, V defaultValue)
    {
        Map<K, V> thiz = (Map<K, V>) this;
        return thiz.getOrDefault(key, defaultValue);
    }

    //////////////////////////////////////////////////////////////////////////
    // 項目轉換
    //

    /**
     * 依照對照函數建立對照{@link Map}
     *
     * @param <V2> 對照項目值型態
     * @param mapper 對照項目值的函數
     * @return 對照項目值的唯讀{@link Map}，依賴原有的{@link Map}
     */
    default <V2> Map<K, V2> mapValues(Function<? super V, ? extends V2> mapper)
    {
        Map<K, V> thiz = (Map<K, V>) this;
        return new MappedMap<>(thiz, (k, v) -> mapper.apply(v));
    }

    /**
     * 依照對照函數建立對照{@link Map}
     *
     * @param <V2> 對照項目值型態
     * @param mapper 對照項目值的函數
     * @return 對照項目值的唯讀{@link Map}，依賴原有的{@link Map}
     */
    default <V2> Map<K, V2> mapValues(BiFunction<? super K, ? super V, ? extends V2> mapper)
    {
        Map<K, V> thiz = (Map<K, V>) this;
        return new MappedMap<>(thiz, mapper);
    }

    //////////////////////////////////////////////////////////////////////////
    // 項目統整
    //

    /**
     * 對照目前的項目值以產生新的{@link HashMap}
     *
     * @param <V2> 對照項目值型態
     * @param mapper 對照項目值的函數
     * @return 對照後的新{@link HashMap}(不依賴原有的)
     */
    default <V2> HashMap<K, V2> toHashMapMapped(Function<? super V, V2> mapper)
    {
        Map<K, V> thiz = (Map<K, V>) this;
        HashMap<K, V2> result = new HashMap<>(thiz.size());
        thiz.forEach((k, v) -> result.put(k, mapper.apply(v)));
        return result;
    }

    /**
     * 對照目前的項目值以產生新的{@link HashMap}
     *
     * @param <K2> 對照鍵值型態
     * @param <V2> 對照項目值型態
     * @param keyMapper 對照鍵值的函數
     * @param valueMapper 對照項目值的函數
     * @return 對照後的新{@link HashMap}(不依賴原有的)
     */
    default <K2, V2> HashMap<K2, V2> toHashMapMapped(
            Function<? super K, K2> keyMapper,
            Function<? super V, V2> valueMapper)
    {
        Map<K, V> thiz = (Map<K, V>) this;
        HashMap<K2, V2> result = new HashMap<>(thiz.size());
        thiz.forEach((k, v) -> result.put(keyMapper.apply(k), valueMapper.apply(v)));
        return result;
    }

    /**
     * 對照目前的項目值以產生新的{@link HashMap}
     *
     * @param <V2> 對照項目值型態
     * @param mapper 對照項目值的函數
     * @return 對照後的新{@link HashMap}(不依賴原有的)
     */
    default <V2> HashMap<K, V2> toHashMapMapped(BiFunction<? super K, ? super V, V2> mapper)
    {
        Map<K, V> thiz = (Map<K, V>) this;
        HashMap<K, V2> result = new HashMap<>(thiz.size());
        thiz.forEach((k, v) -> result.put(k, mapper.apply(k, v)));
        return result;
    }

    /**
     * 對照目前的項目值以產生新的{@link HashMap}
     *
     * @param <K2> 對照鍵值型態
     * @param <V2> 對照項目值型態
     * @param keyMapper 對照鍵值的函數
     * @param valueMapper 對照項目值的函數
     * @return 對照後的新{@link HashMap}(不依賴原有的)
     */
    default <K2, V2> HashMap<K2, V2> toHashMapMapped(
            BiFunction<? super K, ? super V, K2> keyMapper,
            BiFunction<? super K, ? super V, V2> valueMapper)
    {
        Map<K, V> thiz = (Map<K, V>) this;
        HashMap<K2, V2> result = new HashMap<>(thiz.size());
        thiz.forEach((k, v) -> result.put(keyMapper.apply(k, v), valueMapper.apply(k, v)));
        return result;
    }
}
