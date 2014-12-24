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

package javafx.collections;

import jxtn.jfx.axi.ObservableMapHelper;

/**
 * {@link ObservableMap}的延伸功能
 *
 * @author AqD
 * @param <K> 索引鍵型態
 * @param <V> 項目值型態
 */
public interface ObservableMapExt<K, V>
{
    /**
     * 建立自動對照索引鍵的{@link ObservableList}
     *
     * @return {@link ObservableList}，對應目前字典的索引鍵
     */
    default ObservableList<K> toKeysObservable()
    {
        ObservableList<K> list = FXCollections.observableArrayList();
        ObservableMapHelper.mapKeysTo((ObservableMap<K, V>) this, list);
        return list;
    }

    /**
     * 建立自動對照索引鍵的{@link ObservableList}
     *
     * @return {@link ObservableList}，對應目前字典的項目值
     */
    default ObservableList<V> toValuesObservable()
    {
        ObservableList<V> list = FXCollections.observableArrayList();
        ObservableMapHelper.mapValuesTo((ObservableMap<K, V>) this, list);
        return list;
    }
}
