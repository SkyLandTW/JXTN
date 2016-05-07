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

package javafx.beans.property;

import java.util.function.Function;

import jxtn.jfx.axi.BidirectionalBinding2;

/**
 * {@link Property}的延伸功能。
 *
 * @author AqD
 * @param <T> 屬性值型態
 */
public interface PropertyExt<T>
{
    /**
     * 建立自動轉型指定型態的目的屬性。
     *
     * @param <R> 目的型態
     * @param mapTo 轉換目前屬性值到指定型態的函數
     * @param mapFrom 轉換指定型態屬性值回目前型態的函數
     * @return 指定型態的屬性(與目前屬性做雙向連動)
     */
    default <R> ObjectProperty<R> asObjectProperty(
            Function<? super T, ? extends R> mapTo,
            Function<? super R, ? extends T> mapFrom)
    {
        Property<T> thiz = (Property<T>) this;
        ObjectProperty<R> newProperty = new SimpleObjectProperty<R>()
            {
                @SuppressWarnings("unused")
                private final Property<T> source = thiz;
            };
        BidirectionalBinding2.bind(newProperty, thiz, mapFrom, mapTo);
        return newProperty;
    }

    /**
     * 建立自動轉型{@link Boolean}型態的目的屬性。
     *
     * @param mapTo 轉換目前屬性值到布林型態的函數
     * @param mapFrom 轉換布林型態屬性值回目前型態的函數
     * @return {@link Boolean}型態的屬性(與目前屬性做雙向連動)
     */
    default BooleanProperty asBooleanProperty(
            Function<? super T, ? extends Boolean> mapTo,
            Function<? super Boolean, ? extends T> mapFrom)
    {
        Property<T> thiz = (Property<T>) this;
        BooleanProperty newProperty = new SimpleBooleanProperty()
            {
                @SuppressWarnings("unused")
                private final Property<T> source = thiz;
            };
        BidirectionalBinding2.bind(newProperty, thiz, mapFrom, mapTo);
        return newProperty;
    }

    /**
     * 建立自動轉型{@link Float}型態的目的屬性。
     *
     * @param mapTo 轉換目前屬性值到數字型態的函數
     * @param mapFrom 轉換數字型態屬性值回目前型態的函數
     * @return {@link Float}型態的屬性(與目前屬性做雙向連動)
     */
    default FloatProperty asFloatProperty(
            Function<? super T, ? extends Number> mapTo,
            Function<? super Number, ? extends T> mapFrom)
    {
        Property<T> thiz = (Property<T>) this;
        FloatProperty newProperty = new SimpleFloatProperty()
            {
                @SuppressWarnings("unused")
                private final Property<T> source = thiz;
            };
        BidirectionalBinding2.bind(newProperty, thiz, mapFrom, mapTo);
        return newProperty;
    }

    /**
     * 建立自動轉型{@link Double}型態的目的屬性。
     *
     * @param mapTo 轉換目前屬性值到數字型態的函數
     * @param mapFrom 轉換數字型態屬性值回目前型態的函數
     * @return {@link Double}型態的屬性(與目前屬性做雙向連動)
     */
    default DoubleProperty asDoubleProperty(
            Function<? super T, ? extends Number> mapTo,
            Function<? super Number, ? extends T> mapFrom)
    {
        Property<T> thiz = (Property<T>) this;
        DoubleProperty newProperty = new SimpleDoubleProperty()
            {
                @SuppressWarnings("unused")
                private final Property<T> source = thiz;
            };
        BidirectionalBinding2.bind(newProperty, thiz, mapFrom, mapTo);
        return newProperty;
    }

    /**
     * 建立自動轉型{@link Integer}型態的目的屬性。
     *
     * @param mapTo 轉換目前屬性值到數字型態的函數
     * @param mapFrom 轉換數字型態屬性值回目前型態的函數
     * @return {@link Integer}型態的屬性(與目前屬性做雙向連動)
     */
    default IntegerProperty asIntegerProperty(
            Function<? super T, ? extends Number> mapTo,
            Function<? super Number, ? extends T> mapFrom)
    {
        Property<T> thiz = (Property<T>) this;
        IntegerProperty newProperty = new SimpleIntegerProperty()
            {
                @SuppressWarnings("unused")
                private final Property<T> source = thiz;
            };
        BidirectionalBinding2.bind(newProperty, thiz, mapFrom, mapTo);
        return newProperty;
    }

    /**
     * 建立自動轉型{@link Long}型態的目的屬性。
     *
     * @param mapTo 轉換目前屬性值到數字型態的函數
     * @param mapFrom 轉換數字型態屬性值回目前型態的函數
     * @return {@link Long}型態的屬性(與目前屬性做雙向連動)
     */
    default LongProperty asLongProperty(
            Function<? super T, ? extends Number> mapTo,
            Function<? super Number, ? extends T> mapFrom)
    {
        Property<T> thiz = (Property<T>) this;
        LongProperty newProperty = new SimpleLongProperty()
            {
                @SuppressWarnings("unused")
                private final Property<T> source = thiz;
            };
        BidirectionalBinding2.bind(newProperty, thiz, mapFrom, mapTo);
        return newProperty;
    }

    /**
     * 建立自動轉型{@link String}型態的目的屬性。
     *
     * @param mapTo 轉換目前屬性值到數字型態的函數
     * @param mapFrom 轉換數字型態屬性值回目前型態的函數
     * @return {@link String}型態的屬性(與目前屬性做雙向連動)
     */
    default StringProperty asStringProperty(
            Function<? super T, ? extends String> mapTo,
            Function<? super String, ? extends T> mapFrom)
    {
        Property<T> thiz = (Property<T>) this;
        StringProperty newProperty = new SimpleStringProperty()
            {
                @SuppressWarnings("unused")
                private final Property<T> source = thiz;
            };
        BidirectionalBinding2.bind(newProperty, thiz, mapFrom, mapTo);
        return newProperty;
    }
}
