// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package org.controlsfx.control;

/**
 * {@link PrefixSelectionComboBox}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version controlsfx-8.40.14.jar
 * @param <Z> 要建構的物件型態(需繼承{@link PrefixSelectionComboBox})
 * @param <B> 建構器本身的型態(需繼承{@link PrefixSelectionComboBoxMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class PrefixSelectionComboBoxMaker<T extends java.lang.Object, Z extends PrefixSelectionComboBox<T>, B extends PrefixSelectionComboBoxMaker<T, Z, B>>
        extends javafx.scene.control.ComboBoxMaker<T, Z, B>
        implements PrefixSelectionComboBoxMakerExt<T, Z, B>
{

    private boolean hasBackSpaceAllowed;
    private boolean valBackSpaceAllowed;

    private boolean hasDisplayOnFocusedEnabled;
    private boolean valDisplayOnFocusedEnabled;

    private boolean hasLookup;
    private java.util.function.BiFunction<javafx.scene.control.ComboBox, java.lang.String, java.util.Optional> valLookup;

    private boolean hasTypingDelay;
    private int valTypingDelay;

    private boolean bound1BackSpaceAllowed;
    private boolean bound2BackSpaceAllowed;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1BackSpaceAllowed;
    private javafx.beans.property.Property<Boolean> obsrv2BackSpaceAllowed;

    private boolean bound1DisplayOnFocusedEnabled;
    private boolean bound2DisplayOnFocusedEnabled;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1DisplayOnFocusedEnabled;
    private javafx.beans.property.Property<Boolean> obsrv2DisplayOnFocusedEnabled;

    private boolean bound1Lookup;
    private boolean bound2Lookup;
    private javafx.beans.value.ObservableValue<? extends java.util.function.BiFunction<javafx.scene.control.ComboBox, java.lang.String, java.util.Optional>> obsrv1Lookup;
    private javafx.beans.property.Property<java.util.function.BiFunction<javafx.scene.control.ComboBox, java.lang.String, java.util.Optional>> obsrv2Lookup;

    private boolean bound1TypingDelay;
    private boolean bound2TypingDelay;
    private javafx.beans.value.ObservableValue<? extends Number> obsrv1TypingDelay;
    private javafx.beans.property.Property<Number> obsrv2TypingDelay;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasBackSpaceAllowed)
            instance.setBackSpaceAllowed(this.valBackSpaceAllowed);
        if (this.hasDisplayOnFocusedEnabled)
            instance.setDisplayOnFocusedEnabled(this.valDisplayOnFocusedEnabled);
        if (this.hasLookup)
            instance.setLookup(this.valLookup);
        if (this.hasTypingDelay)
            instance.setTypingDelay(this.valTypingDelay);
        if (this.bound1BackSpaceAllowed)
            instance.backSpaceAllowedProperty().bind(this.obsrv1BackSpaceAllowed);
        if (this.bound2BackSpaceAllowed)
            instance.backSpaceAllowedProperty().bindBidirectional(this.obsrv2BackSpaceAllowed);
        if (this.bound1DisplayOnFocusedEnabled)
            instance.displayOnFocusedEnabledProperty().bind(this.obsrv1DisplayOnFocusedEnabled);
        if (this.bound2DisplayOnFocusedEnabled)
            instance.displayOnFocusedEnabledProperty().bindBidirectional(this.obsrv2DisplayOnFocusedEnabled);
        if (this.bound1Lookup)
            instance.lookupProperty().bind(this.obsrv1Lookup);
        if (this.bound2Lookup)
            instance.lookupProperty().bindBidirectional(this.obsrv2Lookup);
        if (this.bound1TypingDelay)
            instance.typingDelayProperty().bind(this.obsrv1TypingDelay);
        if (this.bound2TypingDelay)
            instance.typingDelayProperty().bindBidirectional(this.obsrv2TypingDelay);
    }

    /**
     * 設定屬性{@link PrefixSelectionComboBox#setBackSpaceAllowed(boolean)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B backSpaceAllowed(boolean value)
    {
        this.hasBackSpaceAllowed = true;
        this.valBackSpaceAllowed = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link PrefixSelectionComboBox#setDisplayOnFocusedEnabled(boolean)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B displayOnFocusedEnabled(boolean value)
    {
        this.hasDisplayOnFocusedEnabled = true;
        this.valDisplayOnFocusedEnabled = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link PrefixSelectionComboBox#setLookup(java.util.function.BiFunction)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B lookup(java.util.function.BiFunction<javafx.scene.control.ComboBox, java.lang.String, java.util.Optional> value)
    {
        this.hasLookup = true;
        this.valLookup = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link PrefixSelectionComboBox#setTypingDelay(int)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B typingDelay(int value)
    {
        this.hasTypingDelay = true;
        this.valTypingDelay = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link PrefixSelectionComboBox#backSpaceAllowedProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBackSpaceAllowed(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1BackSpaceAllowed = true;
        this.obsrv1BackSpaceAllowed = source;
        this.bound2BackSpaceAllowed = false;
        this.obsrv2BackSpaceAllowed = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link PrefixSelectionComboBox#backSpaceAllowedProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalBackSpaceAllowed(javafx.beans.property.Property<Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1BackSpaceAllowed = false;
        this.obsrv1BackSpaceAllowed = null;
        this.bound2BackSpaceAllowed = true;
        this.obsrv2BackSpaceAllowed = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link PrefixSelectionComboBox#displayOnFocusedEnabledProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindDisplayOnFocusedEnabled(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1DisplayOnFocusedEnabled = true;
        this.obsrv1DisplayOnFocusedEnabled = source;
        this.bound2DisplayOnFocusedEnabled = false;
        this.obsrv2DisplayOnFocusedEnabled = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link PrefixSelectionComboBox#displayOnFocusedEnabledProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalDisplayOnFocusedEnabled(javafx.beans.property.Property<Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1DisplayOnFocusedEnabled = false;
        this.obsrv1DisplayOnFocusedEnabled = null;
        this.bound2DisplayOnFocusedEnabled = true;
        this.obsrv2DisplayOnFocusedEnabled = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link PrefixSelectionComboBox#lookupProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindLookup(javafx.beans.value.ObservableValue<? extends java.util.function.BiFunction<javafx.scene.control.ComboBox, java.lang.String, java.util.Optional>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Lookup = true;
        this.obsrv1Lookup = source;
        this.bound2Lookup = false;
        this.obsrv2Lookup = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link PrefixSelectionComboBox#lookupProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalLookup(javafx.beans.property.Property<java.util.function.BiFunction<javafx.scene.control.ComboBox, java.lang.String, java.util.Optional>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Lookup = false;
        this.obsrv1Lookup = null;
        this.bound2Lookup = true;
        this.obsrv2Lookup = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link PrefixSelectionComboBox#typingDelayProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindTypingDelay(javafx.beans.value.ObservableValue<? extends Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1TypingDelay = true;
        this.obsrv1TypingDelay = source;
        this.bound2TypingDelay = false;
        this.obsrv2TypingDelay = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link PrefixSelectionComboBox#typingDelayProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalTypingDelay(javafx.beans.property.Property<Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1TypingDelay = false;
        this.obsrv1TypingDelay = null;
        this.bound2TypingDelay = true;
        this.obsrv2TypingDelay = source;
        return (B) this;
    }

    /**
     * 建構{@link PrefixSelectionComboBox}物件。
     *
     * @return 新的{@link PrefixSelectionComboBox}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public PrefixSelectionComboBox<T> build()
    {
        PrefixSelectionComboBox<T> instance = new PrefixSelectionComboBox<T>();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}