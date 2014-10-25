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

package javafx.beans.value;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.LongBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.StringBinding;

/**
 * {@link ObservableValue}的延伸功能。
 *
 * @author AqD
 * @param <T> 被包裝的值型態（被觀察的項目型態）
 */
public interface ObservableValueExt<T>
{
    /**
     * 建立包裝目前觀察值為指定型態的繫節。
     *
     * @param <R> 目的型態
     * @param mapper 轉換目前觀察值為指定型態的函數
     * @return 指定型態的繫節(依賴目前的觀察物件)
     */
    default <R> ObjectBinding<R> asObject(Function<? super T, ? extends R> mapper)
    {
        ObservableValue<T> thiz = (ObservableValue<T>) this;
        return Bindings.createObjectBinding(() -> mapper.apply(thiz.getValue()), thiz);
    }

    /**
     * 建立包裝目前觀察值為布林型態的繫節。
     *
     * @param mapper 轉換目前觀察值為布林型態的函數
     * @return 布林型態的繫節(依賴目前的觀察物件)
     */
    default BooleanBinding asBoolean(Predicate<? super T> mapper)
    {
        ObservableValue<T> thiz = (ObservableValue<T>) this;
        return Bindings.createBooleanBinding(() -> mapper.test(thiz.getValue()), thiz);
    }

    /**
     * 建立包裝目前觀察值為浮點型態的繫節。
     *
     * @param thiz 來源觀察值
     * @param mapper 轉換目前觀察值為浮點型態的函數
     * @return 浮點型態的繫節(依賴目前的觀察物件)
     */
    default DoubleBinding asDouble(ToDoubleFunction<? super T> mapper)
    {
        ObservableValue<T> thiz = (ObservableValue<T>) this;
        return Bindings.createDoubleBinding(() -> mapper.applyAsDouble(thiz.getValue()), thiz);
    }

    /**
     * 建立包裝目前觀察值為整數型態的繫節。
     *
     * @param thiz 來源觀察值
     * @param mapper 轉換目前觀察值為整數型態的函數
     * @return 整數型態的繫節(依賴目前的觀察物件)
     */
    default IntegerBinding asInteger(ToIntFunction<? super T> mapper)
    {
        ObservableValue<T> thiz = (ObservableValue<T>) this;
        return Bindings.createIntegerBinding(() -> mapper.applyAsInt(thiz.getValue()), thiz);
    }

    /**
     * 建立包裝目前觀察值為長整數型態的繫節。
     *
     * @param thiz 來源觀察值
     * @param mapper 轉換目前觀察值為長整數型態的函數
     * @return 長整數型態的繫節(依賴目前的觀察物件)
     */
    default LongBinding asLong(ToLongFunction<? super T> mapper)
    {
        ObservableValue<T> thiz = (ObservableValue<T>) this;
        return Bindings.createLongBinding(() -> mapper.applyAsLong(thiz.getValue()), thiz);
    }

    /**
     * 建立包裝目前觀察值為字串型態的繫節。
     *
     * @param thiz 來源觀察值
     * @param mapper 轉換目前觀察值為字串型態的函數
     * @return 字串型態的繫節(依賴目前的觀察物件)
     */
    default StringBinding asString(Function<? super T, String> mapper)
    {
        ObservableValue<T> thiz = (ObservableValue<T>) this;
        return Bindings.createStringBinding(() -> mapper.apply(thiz.getValue()), thiz);
    }
}
