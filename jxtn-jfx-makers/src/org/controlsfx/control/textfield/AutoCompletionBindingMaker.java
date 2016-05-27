// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package org.controlsfx.control.textfield;

/**
 * {@link AutoCompletionBinding}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version controlsfx-8.40.10.jar
 * @param <Z> 要建構的物件型態(需繼承{@link AutoCompletionBinding})
 * @param <B> 建構器本身的型態(需繼承{@link AutoCompletionBindingMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class AutoCompletionBindingMaker<T extends java.lang.Object, Z extends AutoCompletionBinding<T>, B extends AutoCompletionBindingMaker<T, Z, B>>
        extends jxtn.jfx.makers.AbstractMaker<Z, B>
        implements AutoCompletionBindingMakerExt<T, Z, B>
{

    private boolean hasHideOnEscape;
    private boolean valHideOnEscape;

    private boolean hasOnAutoCompleted;
    private javafx.event.EventHandler<org.controlsfx.control.textfield.AutoCompletionBinding.AutoCompletionEvent<T>> valOnAutoCompleted;

    private boolean hasUserInput;
    private java.lang.String valUserInput;

    private boolean hasVisibleRowCount;
    private int valVisibleRowCount;

    private boolean bound1OnAutoCompleted;
    private boolean bound2OnAutoCompleted;
    private javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<org.controlsfx.control.textfield.AutoCompletionBinding.AutoCompletionEvent<T>>> obsrv1OnAutoCompleted;
    private javafx.beans.property.Property<javafx.event.EventHandler<org.controlsfx.control.textfield.AutoCompletionBinding.AutoCompletionEvent<T>>> obsrv2OnAutoCompleted;

    private boolean bound1VisibleRowCount;
    private boolean bound2VisibleRowCount;
    private javafx.beans.value.ObservableValue<? extends Number> obsrv1VisibleRowCount;
    private javafx.beans.property.Property<Number> obsrv2VisibleRowCount;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasHideOnEscape)
            instance.setHideOnEscape(this.valHideOnEscape);
        if (this.hasOnAutoCompleted)
            instance.setOnAutoCompleted(this.valOnAutoCompleted);
        if (this.hasUserInput)
            instance.setUserInput(this.valUserInput);
        if (this.hasVisibleRowCount)
            instance.setVisibleRowCount(this.valVisibleRowCount);
        if (this.bound1OnAutoCompleted)
            instance.onAutoCompletedProperty().bind(this.obsrv1OnAutoCompleted);
        if (this.bound2OnAutoCompleted)
            instance.onAutoCompletedProperty().bindBidirectional(this.obsrv2OnAutoCompleted);
        if (this.bound1VisibleRowCount)
            instance.visibleRowCountProperty().bind(this.obsrv1VisibleRowCount);
        if (this.bound2VisibleRowCount)
            instance.visibleRowCountProperty().bindBidirectional(this.obsrv2VisibleRowCount);
    }

    /**
     * 設定屬性{@link AutoCompletionBinding#setHideOnEscape(boolean)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B hideOnEscape(boolean value)
    {
        this.hasHideOnEscape = true;
        this.valHideOnEscape = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link AutoCompletionBinding#setOnAutoCompleted(javafx.event.EventHandler)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B onAutoCompleted(javafx.event.EventHandler<org.controlsfx.control.textfield.AutoCompletionBinding.AutoCompletionEvent<T>> value)
    {
        this.hasOnAutoCompleted = true;
        this.valOnAutoCompleted = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link AutoCompletionBinding#setUserInput(java.lang.String)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B userInput(java.lang.String value)
    {
        this.hasUserInput = true;
        this.valUserInput = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link AutoCompletionBinding#setVisibleRowCount(int)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B visibleRowCount(int value)
    {
        this.hasVisibleRowCount = true;
        this.valVisibleRowCount = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link AutoCompletionBinding#onAutoCompletedProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOnAutoCompleted(javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<org.controlsfx.control.textfield.AutoCompletionBinding.AutoCompletionEvent<T>>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnAutoCompleted = true;
        this.obsrv1OnAutoCompleted = source;
        this.bound2OnAutoCompleted = false;
        this.obsrv2OnAutoCompleted = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link AutoCompletionBinding#onAutoCompletedProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalOnAutoCompleted(javafx.beans.property.Property<javafx.event.EventHandler<org.controlsfx.control.textfield.AutoCompletionBinding.AutoCompletionEvent<T>>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnAutoCompleted = false;
        this.obsrv1OnAutoCompleted = null;
        this.bound2OnAutoCompleted = true;
        this.obsrv2OnAutoCompleted = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link AutoCompletionBinding#visibleRowCountProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindVisibleRowCount(javafx.beans.value.ObservableValue<? extends Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1VisibleRowCount = true;
        this.obsrv1VisibleRowCount = source;
        this.bound2VisibleRowCount = false;
        this.obsrv2VisibleRowCount = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link AutoCompletionBinding#visibleRowCountProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalVisibleRowCount(javafx.beans.property.Property<Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1VisibleRowCount = false;
        this.obsrv1VisibleRowCount = null;
        this.bound2VisibleRowCount = true;
        this.obsrv2VisibleRowCount = source;
        return (B) this;
    }
}