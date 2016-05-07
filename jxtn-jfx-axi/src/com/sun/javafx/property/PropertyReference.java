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

package com.sun.javafx.property;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.tuple.TDouble;

import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;

import com.sun.javafx.binding.SelectBinding;

/**
 * 屬性參考，提供{@link SelectBinding}界接物件屬性
 * <ul>
 * <li>改寫原本的{@code PropertyReference}以改善效能及支援非官方定義的屬性</li>
 * <li>所有公開成員皆為原本{@code PropertyReference}所定義，不可更動其介面</li>
 * <li>額外支援以{@code xxxProperty}命名的屬性方法及欄位</li>
 * <li>額外支援常數名稱的方法及欄位，如<i>"nameProperty"</i>取得<i>nameProperty</i>的方法或欄位傳回的屬性本身(而非屬性的值)</li>
 * </ul>
 *
 * @param <T> 屬性型態
 */
public final class PropertyReference<T>
{
    /**
     * 建立新的屬性參考
     *
     * @param clazz 屬性來源物件的類別
     * @param name 屬性名稱
     * @throws NullPointerException {@code clazz}或{@code name}為null
     * @throws IllegalArgumentException {@code name}為空字串
     */
    public PropertyReference(Class<?> clazz, String name)
    {
        if (name == null)
            throw new NullPointerException("Name must be specified");
        if (name.trim().length() == 0)
            throw new IllegalArgumentException("Name must be specified");
        if (clazz == null)
            throw new NullPointerException("Class must be specified");
        this.name = name;
        this.clazz = clazz;
    }

    /**
     * 屬性是否可寫入
     *
     * @return {@code true}表示屬性可寫入
     */
    public boolean isWritable()
    {
        this.reflect();
        return this.reflectedInfo.setValue != null;
    }

    /**
     * 屬性是否可讀取
     *
     * @return {@code true}表示屬性可讀取
     */
    public boolean isReadable()
    {
        this.reflect();
        return this.reflectedInfo.getValue != null;
    }

    /**
     * 是否提供屬性本身（{@link ReadOnlyProperty}）
     *
     * @return {@code true}表示提供屬性本身（{@link ReadOnlyProperty}）
     */
    public boolean hasProperty()
    {
        this.reflect();
        return this.reflectedInfo.getProperty != null;
    }

    /**
     * 取得屬性名稱
     *
     * @return 屬性名稱
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * 取得屬性所在的類別
     *
     * @return 屬性所在的類別
     */
    public Class<?> getContainingClass()
    {
        return this.clazz;
    }

    /**
     * 取得屬性的型態
     *
     * @return 屬性的型態
     */
    public Class<?> getType()
    {
        this.reflect();
        return this.reflectedInfo.type;
    }

    /**
     * 設定屬性值
     *
     * @param bean 要設定屬性值的物件
     * @param value 新的屬性值
     * @throws IllegalStateException 屬性不可寫入（{@link #isWritable}）
     */
    public void set(Object bean, T value)
    {
        if (!this.isWritable())
            throw new IllegalStateException(
                    "Cannot write to readonly property " + this.name);
        this.reflectedInfo.setValue.accept(bean, value);
    }

    /**
     * 取得屬性值
     *
     * @param bean 要取得屬性值的物件
     * @return 目前屬性值
     * @throws IllegalStateException 屬性不可讀取（{@link #isReadable}）
     */
    @SuppressWarnings("unchecked")
    public T get(Object bean)
    {
        if (!this.isReadable())
            throw new IllegalStateException(
                    "Cannot read from unreadable property " + this.name);
        return (T) this.reflectedInfo.getValue.apply(bean);
    }

    /**
     * 取得屬性本身
     *
     * @param bean 要取得屬性本身的物件
     * @return 屬性本身
     * @throws IllegalStateException 屬性不提供{@link ReadOnlyProperty}
     */
    @SuppressWarnings("unchecked")
    public ReadOnlyProperty<T> getProperty(Object bean)
    {
        if (!this.hasProperty())
            throw new IllegalStateException("Cannot get property " + this.name);
        return (ReadOnlyProperty<T>) this.reflectedInfo.getProperty.apply(bean);
    }

    @Override
    public String toString()
    {
        return this.name;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof PropertyReference))
        {
            return false;
        }
        final PropertyReference<?> other = (PropertyReference<?>) obj;
        if (this.name != other.name
                && (this.name == null || !this.name.equals(other.name)))
        {
            return false;
        }
        if (this.clazz != other.clazz
                && (this.clazz == null || !this.clazz.equals(other.clazz)))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 97 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 97 * hash + (this.clazz != null ? this.clazz.hashCode() : 0);
        return hash;
    }

    private void reflect()
    {
        if (this.reflectedInfo != null)
            return;
        this.reflectedInfo = cachedPropertyInfo.computeIfAbsent(
                new TDouble<>(this.clazz, this.name),
                k -> new ReflectedPropertyInfo(this.clazz, this.name));
    }

    private final String name;
    private final Class<?> clazz;
    private ReflectedPropertyInfo reflectedInfo;

    //////////////////////////////////////////////////////////////////////////

    private static class ReflectedPropertyInfo
    {
        public ReflectedPropertyInfo(Class<?> clazz, String name)
        {
            Objects.requireNonNull(clazz);
            Objects.requireNonNull(name);
            this.clazz = clazz;
            this.name = name;
            final String propName = name.length() == 1
                    ? name.substring(0, 1).toUpperCase()
                    : Character.toUpperCase(name.charAt(0)) + name.substring(1);
            Class<?> propType = null;
            // getter
            Method mGetter = tryGetMethod(clazz, "get" + propName);
            if (mGetter == null)
                mGetter = tryGetMethod(clazz, "is" + propName);
            // setter
            Method mSetter = null;
            if (mGetter != null)
            {
                propType = mGetter.getReturnType();
                mSetter = tryGetMethod(clazz, "set" + propName, propType);
            }
            else
            {
                String setterName = "set" + propName;
                for (final Method m : clazz.getMethods())
                {
                    final Class<?>[] parameters = m.getParameterTypes();
                    if (setterName.equals(m.getName())
                            && parameters.length == 1
                            && Modifier.isPublic(m.getModifiers()))
                    {
                        mSetter = m;
                        propType = parameters[0];
                        break;
                    }
                }
            }
            //
            this.valueGetter = mGetter;
            this.valueSetter = mSetter;
            this.propertyGetter = tryGetMethod(clazz, name + "Property");
            this.propertyField = tryGetField(clazz, name + "Property");
            this.constantGetter = tryGetMethod(clazz, name);
            this.constantField = tryGetField(clazz, name);
            this.type = this.resolvePropertyValueType(propType);
            // determine getValue
            if (this.valueGetter != null)
            {
                this.getValue = o -> invokeMethod(this.valueGetter, o);
            }
            else if (this.propertyGetter != null)
            {
                this.getValue = o ->
                    {
                        ReadOnlyProperty<?> p = (ReadOnlyProperty<?>) invokeMethod(this.propertyGetter, o);
                        return p == null ? null : p.getValue();
                    };
            }
            else if (this.propertyField != null)
            {
                this.getValue = o ->
                    {
                        ReadOnlyProperty<?> p = (ReadOnlyProperty<?>) invokeFieldGet(this.propertyField, o);
                        return p == null ? null : p.getValue();
                    };
            }
            else if (this.constantGetter != null)
            {
                this.getValue = o -> invokeMethod(this.constantGetter, o);
            }
            else if (this.constantField != null)
            {
                this.getValue = o -> invokeFieldGet(this.constantField, o);
            }
            else
            {
                this.getValue = null;
            }
            // determine setValue
            if (this.valueSetter != null)
            {
                this.setValue = (o, v) -> invokeMethod(this.valueSetter, o, v);
            }
            else if (this.propertyGetter != null)
            {
                if (Property.class.isAssignableFrom(this.propertyGetter.getReturnType()))
                    this.setValue = (o, v) ->
                        {
                            @SuppressWarnings("unchecked") Property<Object> p = (Property<Object>) invokeMethod(this.propertyGetter, o);
                            if (p != null)
                                p.setValue(v);
                        };
                else
                    this.setValue = null;
            }
            else if (this.propertyField != null)
            {
                if (Property.class.isAssignableFrom(this.propertyField.getType()))
                    this.setValue = (o, v) ->
                        {
                            @SuppressWarnings("unchecked") Property<Object> p = (Property<Object>) invokeFieldGet(this.propertyField, o);
                            if (p != null)
                                p.setValue(v);
                        };
                else
                    this.setValue = null;
            }
            else
            {
                this.setValue = null;
            }
            // determine getProperty
            if (this.propertyGetter != null)
            {
                this.getProperty = o ->
                    {
                        ReadOnlyProperty<?> p = (ReadOnlyProperty<?>) invokeMethod(this.propertyGetter, o);
                        return p;
                    };
            }
            else if (this.propertyField != null)
            {
                this.getProperty = o ->
                    {
                        ReadOnlyProperty<?> p = (ReadOnlyProperty<?>) invokeFieldGet(this.propertyField, o);
                        return p;
                    };
            }
            else if (this.constantGetter != null || this.constantField != null)
            {
                this.getProperty = o -> new ConstantObservable()
                    {
                        @Override
                        public Object getBean()
                        {
                            return o;
                        }

                        @Override
                        public String getName()
                        {
                            return name;
                        }

                        @Override
                        public Object getValue()
                        {
                            return ReflectedPropertyInfo.this.getValue.apply(o);
                        }
                    };
            }
            else
            {
                this.getProperty = null;
            }
        }

        @Override
        public boolean equals(Object obj)
        {
            if (this == obj)
            {
                return true;
            }
            if (!(obj instanceof ReflectedPropertyInfo))
            {
                return false;
            }
            final ReflectedPropertyInfo other = (ReflectedPropertyInfo) obj;
            if (this.name != other.name
                    && (this.name == null || !this.name.equals(other.name)))
            {
                return false;
            }
            if (this.clazz != other.clazz
                    && (this.clazz == null || !this.clazz.equals(other.clazz)))
            {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode()
        {
            int hash = 5;
            hash = 97 * hash + (this.name != null ? this.name.hashCode() : 0);
            hash = 97 * hash + (this.clazz != null ? this.clazz.hashCode() : 0);
            return hash;
        }

        private Class<?> resolvePropertyValueType(Class<?> propertyValueType)
        {
            if (propertyValueType != null)
                return propertyValueType;
            if (this.propertyGetter != null)
            {
                Class<?> propertyType = (Class<?>) this.propertyGetter.getGenericReturnType();
                Method valueGetter = tryGetMethod(propertyType, "getValue");
                if (valueGetter != null)
                    return (Class<?>) valueGetter.getGenericReturnType();
            }
            if (this.propertyField != null)
            {
                Class<?> propertyType = (Class<?>) this.propertyField.getGenericType();
                Method valueGetter = tryGetMethod(propertyType, "getValue");
                if (valueGetter != null)
                    return (Class<?>) valueGetter.getGenericReturnType();
            }
            if (this.constantGetter != null)
                return this.constantGetter.getReturnType();
            if (this.constantField != null)
                return this.constantField.getType();
            return null;
        }

        public final Class<?> clazz;
        public final String name;
        public final Class<?> type;
        public final Function<Object, Object> getValue;
        public final BiConsumer<Object, Object> setValue;
        public final Function<Object, ReadOnlyProperty<?>> getProperty;

        private final Method valueGetter;
        private final Method valueSetter;
        private final Method propertyGetter;
        private final Field propertyField;
        private final Method constantGetter;
        private final Field constantField;

        private static Object invokeFieldGet(Field field, Object instance)
        {
            try
            {
                return field.get(instance);
            }
            catch (IllegalAccessException e)
            {
                throw new RuntimeException(e);
            }
        }

        private static Object invokeMethod(Method method, Object instance, Object... args)
        {
            try
            {
                return method.invoke(instance, args);
            }
            catch (IllegalAccessException | InvocationTargetException e)
            {
                throw new RuntimeException(e);
            }
        }

        private static Field tryGetField(Class<?> clazz, String name)
        {
            try
            {
                return clazz.getField(name);
            }
            catch (NoSuchFieldException e)
            {
                return null;
            }
        }

        private static Method tryGetMethod(Class<?> clazz, String name, Class<?>... parameterTypes)
        {
            try
            {
                return clazz.getMethod(name, parameterTypes);
            }
            catch (NoSuchMethodException e)
            {
                return null;
            }
        }
    }

    private static abstract class ConstantObservable implements ReadOnlyProperty<Object>
    {
        @Override
        public void addListener(ChangeListener<? super Object> listener)
        {
            //
        }

        @Override
        public void removeListener(ChangeListener<? super Object> listener)
        {
            //
        }

        @Override
        public void addListener(InvalidationListener listener)
        {
            //
        }

        @Override
        public void removeListener(InvalidationListener listener)
        {
            //
        }
    }

    private static final ConcurrentHashMap<TDouble<Class<?>, String>, ReflectedPropertyInfo> cachedPropertyInfo =
            new ConcurrentHashMap<>(10000);
}
