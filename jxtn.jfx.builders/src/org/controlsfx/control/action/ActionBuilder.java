// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package org.controlsfx.control.action;

/**
 * {@link Action}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version controlsfx-8.20.8.jar
 * @param <Z> 要建構的物件型態(需繼承{@link Action})
 * @param <B> 建構器本身的型態(需繼承{@link ActionBuilder})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class ActionBuilder<Z extends Action, B extends ActionBuilder<Z, B>>
        extends jxtn.jfx.builders.AbstractBuilder<Z, B>
        implements ActionBuilderExt<Z, B>
{

    private boolean hasAccelerator;
    private javafx.scene.input.KeyCombination valAccelerator;

    private boolean hasDisabled;
    private boolean valDisabled;

    private boolean hasGraphic;
    private javafx.scene.Node valGraphic;

    private boolean hasLongText;
    private java.lang.String valLongText;

    private boolean hasSelected;
    private boolean valSelected;

    private boolean hasStyle;
    private java.lang.String valStyle;

    private boolean hasStyleClass;
    private java.util.Collection<java.lang.String> valStyleClass;

    private boolean hasText;
    private java.lang.String valText;

    private boolean bound1Accelerator;
    private boolean bound2Accelerator;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.input.KeyCombination> obsrv1Accelerator;
    private javafx.beans.property.Property<javafx.scene.input.KeyCombination> obsrv2Accelerator;

    private boolean bound1Disabled;
    private boolean bound2Disabled;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1Disabled;
    private javafx.beans.property.Property<Boolean> obsrv2Disabled;

    private boolean bound1Graphic;
    private boolean bound2Graphic;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.Node> obsrv1Graphic;
    private javafx.beans.property.Property<javafx.scene.Node> obsrv2Graphic;

    private boolean bound1LongText;
    private boolean bound2LongText;
    private javafx.beans.value.ObservableValue<? extends String> obsrv1LongText;
    private javafx.beans.property.Property<String> obsrv2LongText;

    private boolean bound1Selected;
    private boolean bound2Selected;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1Selected;
    private javafx.beans.property.Property<Boolean> obsrv2Selected;

    private boolean bound1Style;
    private boolean bound2Style;
    private javafx.beans.value.ObservableValue<? extends String> obsrv1Style;
    private javafx.beans.property.Property<String> obsrv2Style;

    private boolean bound1Text;
    private boolean bound2Text;
    private javafx.beans.value.ObservableValue<? extends String> obsrv1Text;
    private javafx.beans.property.Property<String> obsrv2Text;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasAccelerator)
            instance.setAccelerator(this.valAccelerator);
        if (this.hasDisabled)
            instance.setDisabled(this.valDisabled);
        if (this.hasGraphic)
            instance.setGraphic(this.valGraphic);
        if (this.hasLongText)
            instance.setLongText(this.valLongText);
        if (this.hasSelected)
            instance.setSelected(this.valSelected);
        if (this.hasStyle)
            instance.setStyle(this.valStyle);
        if (this.hasStyleClass)
            instance.getStyleClass().addAll(this.valStyleClass);
        if (this.hasText)
            instance.setText(this.valText);
        if (this.bound1Accelerator)
            instance.acceleratorProperty().bind(this.obsrv1Accelerator);
        if (this.bound2Accelerator)
            instance.acceleratorProperty().bindBidirectional(this.obsrv2Accelerator);
        if (this.bound1Disabled)
            instance.disabledProperty().bind(this.obsrv1Disabled);
        if (this.bound2Disabled)
            instance.disabledProperty().bindBidirectional(this.obsrv2Disabled);
        if (this.bound1Graphic)
            instance.graphicProperty().bind(this.obsrv1Graphic);
        if (this.bound2Graphic)
            instance.graphicProperty().bindBidirectional(this.obsrv2Graphic);
        if (this.bound1LongText)
            instance.longTextProperty().bind(this.obsrv1LongText);
        if (this.bound2LongText)
            instance.longTextProperty().bindBidirectional(this.obsrv2LongText);
        if (this.bound1Selected)
            instance.selectedProperty().bind(this.obsrv1Selected);
        if (this.bound2Selected)
            instance.selectedProperty().bindBidirectional(this.obsrv2Selected);
        if (this.bound1Style)
            instance.styleProperty().bind(this.obsrv1Style);
        if (this.bound2Style)
            instance.styleProperty().bindBidirectional(this.obsrv2Style);
        if (this.bound1Text)
            instance.textProperty().bind(this.obsrv1Text);
        if (this.bound2Text)
            instance.textProperty().bindBidirectional(this.obsrv2Text);
    }

    /**
     * 設定屬性{@link Action#setAccelerator(javafx.scene.input.KeyCombination)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B accelerator(javafx.scene.input.KeyCombination value)
    {
        this.hasAccelerator = true;
        this.valAccelerator = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Action#setDisabled(boolean)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B disabled(boolean value)
    {
        this.hasDisabled = true;
        this.valDisabled = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Action#setGraphic(javafx.scene.Node)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B graphic(javafx.scene.Node value)
    {
        this.hasGraphic = true;
        this.valGraphic = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Action#setLongText(java.lang.String)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B longText(java.lang.String value)
    {
        this.hasLongText = true;
        this.valLongText = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Action#setSelected(boolean)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B selected(boolean value)
    {
        this.hasSelected = true;
        this.valSelected = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Action#setStyle(java.lang.String)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B style(java.lang.String value)
    {
        this.hasStyle = true;
        this.valStyle = value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link Action#getStyleClass}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     * @deprecated 屬性值並非{@link javafx.collections.ObservableList}
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public final B styleClass(java.util.Collection<? extends java.lang.String> value)
    {
        this.hasStyleClass = true;
        this.valStyleClass = (java.util.Collection<java.lang.String>) value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link Action#getStyleClass}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B styleClass(java.lang.String... value)
    {
        this.hasStyleClass = true;
        this.valStyleClass = java.util.Arrays.asList(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link Action#getStyleClass}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B styleClassAdd(java.util.Collection<? extends java.lang.String> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasStyleClass = true;
        if (this.valStyleClass == null)
            this.valStyleClass = new java.util.ArrayList<>(value.size());
        this.valStyleClass.addAll(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link Action#getStyleClass}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B styleClassAdd(java.lang.String... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasStyleClass = true;
        if (this.valStyleClass == null)
            this.valStyleClass = new java.util.ArrayList<>(value.length);
        this.valStyleClass.addAll(java.util.Arrays.asList(value));
        return (B) this;
    }

    /**
     * 增加集合屬性{@link Action#getStyleClass}的內容，排除null項目。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B styleClassAddNonNull(java.util.Collection<? extends java.lang.String> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasStyleClass = true;
        if (this.valStyleClass == null)
            this.valStyleClass = new java.util.ArrayList<>(value.size());
        for (java.lang.String i : value)
            if (i != null)
                this.valStyleClass.add(i);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link Action#getStyleClass}的內容，排除null項目。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B styleClassAddNonNull(java.lang.String... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasStyleClass = true;
        if (this.valStyleClass == null)
            this.valStyleClass = new java.util.ArrayList<>(value.length);
        for (java.lang.String i : value)
            if (i != null)
                this.valStyleClass.add(i);
        return (B) this;
    }

    /**
     * 設定屬性{@link Action#setText(java.lang.String)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B text(java.lang.String value)
    {
        this.hasText = true;
        this.valText = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Action#acceleratorProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindAccelerator(javafx.beans.value.ObservableValue<? extends javafx.scene.input.KeyCombination> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Accelerator = true;
        this.obsrv1Accelerator = source;
        this.bound2Accelerator = false;
        this.obsrv2Accelerator = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Action#acceleratorProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalAccelerator(javafx.beans.property.Property<javafx.scene.input.KeyCombination> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Accelerator = false;
        this.obsrv1Accelerator = null;
        this.bound2Accelerator = true;
        this.obsrv2Accelerator = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Action#disabledProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindDisabled(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Disabled = true;
        this.obsrv1Disabled = source;
        this.bound2Disabled = false;
        this.obsrv2Disabled = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Action#disabledProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalDisabled(javafx.beans.property.Property<Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Disabled = false;
        this.obsrv1Disabled = null;
        this.bound2Disabled = true;
        this.obsrv2Disabled = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Action#graphicProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindGraphic(javafx.beans.value.ObservableValue<? extends javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Graphic = true;
        this.obsrv1Graphic = source;
        this.bound2Graphic = false;
        this.obsrv2Graphic = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Action#graphicProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalGraphic(javafx.beans.property.Property<javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Graphic = false;
        this.obsrv1Graphic = null;
        this.bound2Graphic = true;
        this.obsrv2Graphic = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Action#longTextProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindLongText(javafx.beans.value.ObservableValue<? extends String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1LongText = true;
        this.obsrv1LongText = source;
        this.bound2LongText = false;
        this.obsrv2LongText = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Action#longTextProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalLongText(javafx.beans.property.Property<String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1LongText = false;
        this.obsrv1LongText = null;
        this.bound2LongText = true;
        this.obsrv2LongText = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Action#selectedProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindSelected(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Selected = true;
        this.obsrv1Selected = source;
        this.bound2Selected = false;
        this.obsrv2Selected = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Action#selectedProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalSelected(javafx.beans.property.Property<Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Selected = false;
        this.obsrv1Selected = null;
        this.bound2Selected = true;
        this.obsrv2Selected = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Action#styleProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindStyle(javafx.beans.value.ObservableValue<? extends String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Style = true;
        this.obsrv1Style = source;
        this.bound2Style = false;
        this.obsrv2Style = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Action#styleProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalStyle(javafx.beans.property.Property<String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Style = false;
        this.obsrv1Style = null;
        this.bound2Style = true;
        this.obsrv2Style = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Action#textProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindText(javafx.beans.value.ObservableValue<? extends String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Text = true;
        this.obsrv1Text = source;
        this.bound2Text = false;
        this.obsrv2Text = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Action#textProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalText(javafx.beans.property.Property<String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Text = false;
        this.obsrv1Text = null;
        this.bound2Text = true;
        this.obsrv2Text = source;
        return (B) this;
    }

    /**
     * 建構{@link Action}物件。
     *
     * @return 新的{@link Action}物件實體
     */
    @SuppressWarnings("unchecked")
    public Action build(java.util.function.Consumer<javafx.event.ActionEvent> arg0)
    {
        Action instance = new Action(arg0);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link Action}物件。
     *
     * @return 新的{@link Action}物件實體
     */
    @SuppressWarnings("unchecked")
    public Action build(java.lang.String arg0)
    {
        Action instance = new Action(arg0);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link Action}物件。
     *
     * @return 新的{@link Action}物件實體
     */
    @SuppressWarnings("unchecked")
    public Action build(java.lang.String arg0, java.util.function.Consumer<javafx.event.ActionEvent> arg1)
    {
        Action instance = new Action(arg0, arg1);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
