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

import java.util.function.BiConsumerEx;
import java.util.function.BiFunction;
import java.util.function.BiFunctionEx;
import java.util.function.Function;
import java.util.function.FunctionEx;
import jxtn.core.axi.collections.MappedMap;

/**
 * {@link Map}的延伸功能。
 *
 * @author AqD
 * @param <K> 鍵值型態
 * @param <V> 項目值型態
 */
public interface MapExt<K, V> {
    //////////////////////////////////////////////////////////////////////////
    // 泛型方法
    //

    /**
     * 針對每個鍵值及項目值執行指定動作
     *
     * @param <TException> 動作可拋出的例外型態
     * @param action 要執行的動作
     * @throws TException 表示{@code action}丟出例外
     */
    default <TException extends Exception> void forEachEx(
            BiConsumerEx<? super K, ? super V, ? extends TException> action)
            throws TException {
        Objects.requireNonNull(action);
        Map<K, V> thiz = (Map<K, V>) this;
        for (Map.Entry<K, V> entry : thiz.entrySet()) {
            K k;
            V v;
            try {
                k = entry.getKey();
                v = entry.getValue();
            } catch (IllegalStateException ise) {
                throw new ConcurrentModificationException(ise);
            }
            action.acceptEx(k, v);
        }
    }

    //////////////////////////////////////////////////////////////////////////
    // 項目轉換
    //

    /**
     * 依照對照函數建立對照{@link Map}。
     *
     * @param <V2> 對照項目值型態
     * @param mapper 對照項目值的函數
     * @return 對照項目值的唯讀{@link Map}，依賴原有的{@link Map}
     */
    default <V2> Map<K, V2> mapValues(Function<? super V, ? extends V2> mapper) {
        Map<K, V> thiz = (Map<K, V>) this;
        return new MappedMap<>(thiz, (k, v) -> mapper.apply(v));
    }

    /**
     * 依照對照函數建立對照{@link Map}。
     *
     * @param <V2> 對照項目值型態
     * @param mapper 對照項目值的函數
     * @return 對照項目值的唯讀{@link Map}，依賴原有的{@link Map}
     */
    default <V2> Map<K, V2> mapValues(BiFunction<? super K, ? super V, ? extends V2> mapper) {
        Map<K, V> thiz = (Map<K, V>) this;
        return new MappedMap<>(thiz, mapper);
    }

    //////////////////////////////////////////////////////////////////////////
    // 項目統整
    //

    /**
     * 對照目前的項目值以產生新的{@link HashMap}。
     *
     * @param <V2> 對照項目值型態
     * @param <VException> 計算項目值函數可拋出的例外型態
     * @param mapper 對照項目值的函數
     * @return 對照後的新{@link HashMap}(不依賴原有的)
     * @throws VException 表示{@code mapper}丟出例外
     */
    default <V2, VException extends Exception> HashMap<K, V2> toHashMapMapped(
            FunctionEx<? super V, ? extends V2, ? extends VException> mapper)
            throws VException {
        Map<K, V> thiz = (Map<K, V>) this;
        HashMap<K, V2> result = new HashMap<>(thiz.size());
        for (Map.Entry<K, V> entry : thiz.entrySet()) {
            V2 newValue = mapper.applyEx(entry.getValue());
            result.put(entry.getKey(), newValue);
        }
        return result;
    }

    /**
     * 對照目前的項目值以產生新的{@link HashMap}。
     *
     * @param <K2> 對照鍵值型態
     * @param <V2> 對照項目值型態
     * @param <KException> 計算鍵值函數可拋出的例外型態
     * @param <VException> 計算項目值函數可拋出的例外型態
     * @param keyMapper 對照鍵值的函數
     * @param valueMapper 對照項目值的函數
     * @return 對照後的新{@link HashMap}(不依賴原有的)
     * @throws KException 表示{@code keyMapper}丟出例外
     * @throws VException 表示{@code valueMapper}丟出例外
     */
    default <K2, V2, KException extends Exception, VException extends Exception> HashMap<K2, V2> toHashMapMapped(
            FunctionEx<? super K, ? extends K2, ? extends KException> keyMapper,
            FunctionEx<? super V, ? extends V2, ? extends VException> valueMapper)
            throws KException, VException {
        Map<K, V> thiz = (Map<K, V>) this;
        HashMap<K2, V2> result = new HashMap<>(thiz.size());
        for (Map.Entry<K, V> entry : thiz.entrySet()) {
            K2 newKey = keyMapper.applyEx(entry.getKey());
            V2 newVal = valueMapper.applyEx(entry.getValue());
            result.put(newKey, newVal);
        }
        return result;
    }

    /**
     * 對照目前的項目值以產生新的{@link HashMap}。
     *
     * @param <V2> 對照項目值型態
     * @param <VException> 計算項目值函數可拋出的例外型態
     * @param mapper 對照項目值的函數
     * @return 對照後的新{@link HashMap}(不依賴原有的)
     * @throws VException 表示{@code mapper}丟出例外
     */
    default <V2, VException extends Exception> HashMap<K, V2> toHashMapMapped(
            BiFunctionEx<? super K, ? super V, V2, ? extends VException> mapper)
            throws VException {
        Map<K, V> thiz = (Map<K, V>) this;
        HashMap<K, V2> result = new HashMap<>(thiz.size());
        for (Map.Entry<K, V> entry : thiz.entrySet()) {
            K oldKey = entry.getKey();
            V2 newValue = mapper.applyEx(oldKey, entry.getValue());
            result.put(oldKey, newValue);
        }
        return result;
    }

    /**
     * 對照目前的項目值以產生新的{@link HashMap}。
     *
     * @param <K2> 對照鍵值型態
     * @param <V2> 對照項目值型態
     * @param <KException> 計算鍵值函數可拋出的例外型態
     * @param <VException> 計算項目值函數可拋出的例外型態
     * @param keyMapper 對照鍵值的函數
     * @param valueMapper 對照項目值的函數
     * @return 對照後的新{@link HashMap}(不依賴原有的)
     * @throws KException 表示{@code keyMapper}丟出例外
     * @throws VException 表示{@code valueMapper}丟出例外
     */
    default <K2, V2, KException extends Exception, VException extends Exception> HashMap<K2, V2> toHashMapMapped(
            BiFunctionEx<? super K, ? super V, K2, ? extends KException> keyMapper,
            BiFunctionEx<? super K, ? super V, V2, ? extends VException> valueMapper)
            throws KException, VException {
        Map<K, V> thiz = (Map<K, V>) this;
        HashMap<K2, V2> result = new HashMap<>(thiz.size());
        for (Map.Entry<K, V> entry : thiz.entrySet()) {
            K oldKey = entry.getKey();
            V oldVal = entry.getValue();
            K2 newKey = keyMapper.applyEx(oldKey, oldVal);
            V2 newVal = valueMapper.applyEx(oldKey, oldVal);
            result.put(newKey, newVal);
        }
        return result;
    }

    /**
     * 對照目前的項目值以產生新的{@link TreeMap}。
     *
     * @param <V2> 對照項目值型態
     * @param <VException> 計算項目值函數可拋出的例外型態
     * @param mapper 對照項目值的函數
     * @return 對照後的新{@link TreeMap}(不依賴原有的)
     * @throws VException 表示{@code mapper}丟出例外
     */
    default <V2, VException extends Exception> TreeMap<K, V2> toTreeMapMapped(
            FunctionEx<? super V, ? extends V2, ? extends VException> mapper)
            throws VException {
        Map<K, V> thiz = (Map<K, V>) this;
        TreeMap<K, V2> result = new TreeMap<>();
        for (Map.Entry<K, V> entry : thiz.entrySet()) {
            V2 newValue = mapper.applyEx(entry.getValue());
            result.put(entry.getKey(), newValue);
        }
        return result;
    }

    /**
     * 對照目前的項目值以產生新的{@link TreeMap}。
     *
     * @param <K2> 對照鍵值型態
     * @param <V2> 對照項目值型態
     * @param <KException> 計算鍵值函數可拋出的例外型態
     * @param <VException> 計算項目值函數可拋出的例外型態
     * @param keyMapper 對照鍵值的函數
     * @param valueMapper 對照項目值的函數
     * @return 對照後的新{@link TreeMap}(不依賴原有的)
     * @throws KException 表示{@code keyMapper}丟出例外
     * @throws VException 表示{@code valueMapper}丟出例外
     */
    default <K2, V2, KException extends Exception, VException extends Exception> TreeMap<K2, V2> toTreeMapMapped(
            FunctionEx<? super K, ? extends K2, ? extends KException> keyMapper,
            FunctionEx<? super V, ? extends V2, ? extends VException> valueMapper)
            throws KException, VException {
        Map<K, V> thiz = (Map<K, V>) this;
        TreeMap<K2, V2> result = new TreeMap<>();
        for (Map.Entry<K, V> entry : thiz.entrySet()) {
            K2 newKey = keyMapper.applyEx(entry.getKey());
            V2 newVal = valueMapper.applyEx(entry.getValue());
            result.put(newKey, newVal);
        }
        return result;
    }

    /**
     * 對照目前的項目值以產生新的{@link TreeMap}。
     *
     * @param <V2> 對照項目值型態
     * @param <VException> 計算項目值函數可拋出的例外型態
     * @param mapper 對照項目值的函數
     * @return 對照後的新{@link TreeMap}(不依賴原有的)
     * @throws VException 表示{@code mapper}丟出例外
     */
    default <V2, VException extends Exception> TreeMap<K, V2> toTreeMapMapped(
            BiFunctionEx<? super K, ? super V, V2, ? extends VException> mapper)
            throws VException {
        Map<K, V> thiz = (Map<K, V>) this;
        TreeMap<K, V2> result = new TreeMap<>();
        for (Map.Entry<K, V> entry : thiz.entrySet()) {
            K oldKey = entry.getKey();
            V2 newValue = mapper.applyEx(oldKey, entry.getValue());
            result.put(oldKey, newValue);
        }
        return result;
    }

    /**
     * 對照目前的項目值以產生新的{@link TreeMap}。
     *
     * @param <K2> 對照鍵值型態
     * @param <V2> 對照項目值型態
     * @param <KException> 計算鍵值函數可拋出的例外型態
     * @param <VException> 計算項目值函數可拋出的例外型態
     * @param keyMapper 對照鍵值的函數
     * @param valueMapper 對照項目值的函數
     * @return 對照後的新{@link TreeMap}(不依賴原有的)
     * @throws KException 表示{@code keyMapper}丟出例外
     * @throws VException 表示{@code valueMapper}丟出例外
     */
    default <K2, V2, KException extends Exception, VException extends Exception> TreeMap<K2, V2> toTreeMapMapped(
            BiFunctionEx<? super K, ? super V, K2, ? extends KException> keyMapper,
            BiFunctionEx<? super K, ? super V, V2, ? extends VException> valueMapper)
            throws KException, VException {
        Map<K, V> thiz = (Map<K, V>) this;
        TreeMap<K2, V2> result = new TreeMap<>();
        for (Map.Entry<K, V> entry : thiz.entrySet()) {
            K oldKey = entry.getKey();
            V oldVal = entry.getValue();
            K2 newKey = keyMapper.applyEx(oldKey, oldVal);
            V2 newVal = valueMapper.applyEx(oldKey, oldVal);
            result.put(newKey, newVal);
        }
        return result;
    }
}
