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

package jxtn.core.axi.collections;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;

/**
 * 依照指定函數做對照的{@link Map}。
 * <p>
 * 不支援修改、{@link #containsValue}及{@link #values}。
 * </p>
 *
 * @author AqD
 * @param <K> 來源{@link Map}鍵值型態
 * @param <V> 來源{@link Map}項目型態
 * @param <V2> 來源{@link Map}項目型態
 */
public class MappedMap<K, V, V2> implements Map<K, V2> {
    /**
     * 來源{@link Map}。
     */
    protected final Map<K, V> source;

    /**
     * 對照函數。
     */
    protected final BiFunction<? super K, ? super V, ? extends V2> mapper;

    /**
     * 建立指定函數做對照的{@link Map}。
     * <p>
     * {@link MappedMap}會依照{@code mapper}將每個{@code source}的項目值做轉換。
     * </p>
     *
     * @param source 來源列舉器
     * @param mapper 對照函數
     */
    public MappedMap(Map<K, V> source, BiFunction<? super K, ? super V, ? extends V2> mapper) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(mapper);
        this.source = source;
        this.mapper = mapper;
    }

    @Override
    public int size() {
        return this.source.size();
    }

    @Override
    public boolean isEmpty() {
        return this.source.isEmpty();
    }

    @Deprecated
    @Override
    public boolean containsKey(Object key) {
        return this.source.containsKey(key);
    }

    /**
     * @throws UnsupportedOperationException 不支援操作
     */
    @Deprecated
    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    @SuppressWarnings("unchecked")
    public V2 get(Object key) {
        if (this.source.containsKey(key)) {
            V value = this.source.get(key);
            return this.mapper.apply((K) key, value);
        }
        return null;
    }

    /**
     * @throws UnsupportedOperationException 不支援操作
     */
    @Override
    public V2 put(K key, V2 value) {
        throw new UnsupportedOperationException();
    }

    /**
     * @throws UnsupportedOperationException 不支援操作
     */
    @Deprecated
    @Override
    public V2 remove(Object key) {
        throw new UnsupportedOperationException();
    }

    /**
     * @throws UnsupportedOperationException 不支援操作
     */
    @Override
    public void putAll(Map<? extends K, ? extends V2> m) {
        throw new UnsupportedOperationException();
    }

    /**
     * @throws UnsupportedOperationException 不支援操作
     */
    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        return this.source.keySet();
    }

    /**
     * @throws UnsupportedOperationException 不支援操作
     */
    @Override
    public Collection<V2> values() {
        throw new UnsupportedOperationException();
    }

    /**
     * @throws UnsupportedOperationException 不支援操作
     */
    @Override
    public Set<java.util.Map.Entry<K, V2>> entrySet() {
        throw new UnsupportedOperationException();
    }
}
