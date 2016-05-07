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

package jxtn.jfx.axi;

import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.function.Function;

import javafx.beans.WeakListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import com.sun.javafx.binding.BidirectionalBinding;

/**
 * 支援型態轉換的{@link BidirectionalBinding}
 *
 * @author AqD
 * @param <T1> 屬性1型態
 * @param <T2> 屬性2型態
 */
public abstract class BidirectionalBinding2<T1, T2> implements ChangeListener<Object>, WeakListener
{
    public static <T1, T2> BidirectionalBinding2<T1, T2> bind(
            Property<T1> property1, Property<T2> property2,
            Function<? super T1, ? extends T2> convert1to2,
            Function<? super T2, ? extends T1> convert2to1)
    {
        Objects.requireNonNull(property1);
        Objects.requireNonNull(property2);
        if (property1 == property2)
            throw new IllegalArgumentException();
        Objects.requireNonNull(convert1to2);
        Objects.requireNonNull(convert2to1);
        GenericConversionBidirectionalBinding2<T1, T2> binding = new GenericConversionBidirectionalBinding2<T1, T2>(property1, property2, convert1to2, convert2to1);
        property1.setValue(convert2to1.apply(property2.getValue()));
        property1.addListener(binding);
        property2.addListener(binding);
        return binding;
    }

    public static <T1, T2> void unbind(Property<T1> property1, Property<T2> property2)
    {
        Objects.requireNonNull(property1);
        Objects.requireNonNull(property2);
        if (property1 == property2)
            throw new IllegalArgumentException();
        final BidirectionalBinding2<T1, T2> binding = new GenericStubBidirectionalBinding2<>(property1, property2);
        property1.removeListener(binding);
        property2.removeListener(binding);
    }

    private final int cachedHashCode;

    private BidirectionalBinding2(Property<T1> property1, Property<T2> property2)
    {
        this.cachedHashCode = property1.hashCode() * property2.hashCode();
    }

    protected abstract Object getProperty1();

    protected abstract Object getProperty2();

    @Override
    public int hashCode()
    {
        return this.cachedHashCode;
    }

    @Override
    public boolean wasGarbageCollected()
    {
        return (this.getProperty1() == null) || (this.getProperty2() == null);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        final Object propertyA1 = this.getProperty1();
        final Object propertyA2 = this.getProperty2();
        if ((propertyA1 == null) || (propertyA2 == null))
        {
            return false;
        }

        if (obj instanceof BidirectionalBinding2)
        {
            final BidirectionalBinding2<?, ?> otherBinding = (BidirectionalBinding2<?, ?>) obj;
            final Object propertyB1 = otherBinding.getProperty1();
            final Object propertyB2 = otherBinding.getProperty2();
            if ((propertyB1 == null) || (propertyB2 == null))
            {
                return false;
            }

            if (propertyA1 == propertyB1 && propertyA2 == propertyB2)
            {
                return true;
            }
            if (propertyA1 == propertyB2 && propertyA2 == propertyB1)
            {
                return true;
            }
        }
        return false;
    }

    private static class GenericStubBidirectionalBinding2<T1, T2> extends BidirectionalBinding2<T1, T2>
    {
        private final Object property1;
        private final Object property2;

        public GenericStubBidirectionalBinding2(Property<T1> property1, Property<T2> property2)
        {
            super(property1, property2);
            this.property1 = property1;
            this.property2 = property2;
        }

        @Override
        protected Object getProperty1()
        {
            return this.property1;
        }

        @Override
        protected Object getProperty2()
        {
            return this.property2;
        }

        @Override
        public void changed(ObservableValue<? extends Object> sourceProperty, Object oldValue, Object newValue)
        {
            throw new RuntimeException("Should not reach here");
        }
    }

    private static class GenericConversionBidirectionalBinding2<T1, T2> extends BidirectionalBinding2<T1, T2>
    {
        private final WeakReference<Property<T1>> propertyRef1;
        private final WeakReference<Property<T2>> propertyRef2;
        private boolean updating = false;
        private Function<? super T1, ? extends T2> convert1to2;
        private Function<? super T2, ? extends T1> convert2to1;

        private GenericConversionBidirectionalBinding2(
                Property<T1> property1, Property<T2> property2,
                Function<? super T1, ? extends T2> convert1to2,
                Function<? super T2, ? extends T1> convert2to1)
        {
            super(property1, property2);
            this.propertyRef1 = new WeakReference<Property<T1>>(property1);
            this.propertyRef2 = new WeakReference<Property<T2>>(property2);
            this.convert1to2 = convert1to2;
            this.convert2to1 = convert2to1;
        }

        @Override
        protected Property<T1> getProperty1()
        {
            return this.propertyRef1.get();
        }

        @Override
        protected Property<T2> getProperty2()
        {
            return this.propertyRef2.get();
        }

        @Override
        @SuppressWarnings("unchecked")
        public void changed(ObservableValue<? extends Object> sourceProperty, Object oldValue, Object newValue)
        {
            if (!this.updating)
            {
                final Property<T1> property1 = this.propertyRef1.get();
                final Property<T2> property2 = this.propertyRef2.get();
                if ((property1 == null) || (property2 == null))
                {
                    if (property1 != null)
                    {
                        property1.removeListener(this);
                    }
                    if (property2 != null)
                    {
                        property2.removeListener(this);
                    }
                }
                else
                {
                    try
                    {
                        this.updating = true;
                        if (property1 == sourceProperty)
                        {
                            property2.setValue(this.convert1to2.apply((T1) newValue));
                        }
                        else
                        {
                            property1.setValue(this.convert2to1.apply((T2) newValue));
                        }
                    }
                    catch (RuntimeException e)
                    {
                        try
                        {
                            if (property1 == sourceProperty)
                            {
                                property1.setValue((T1) oldValue);
                            }
                            else
                            {
                                property2.setValue((T2) oldValue);
                            }
                        }
                        catch (Exception e2)
                        {
                            e2.addSuppressed(e);
                            unbind(property1, property2);
                            throw new RuntimeException(
                                    "Bidirectional binding failed together with an attempt"
                                            + " to restore the source property to the previous value."
                                            + " Removing the bidirectional binding from properties " +
                                            property1 + " and " + property2, e2);
                        }
                        throw new RuntimeException(
                                "Bidirectional binding failed, setting to the previous value", e);
                    }
                    finally
                    {
                        this.updating = false;
                    }
                }
            }
        }
    }
}
