// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package org.controlsfx.control;

/**
 * {@link StatusBar}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version controlsfx-8.20.8.jar
 * @param <Z> 要建構的物件型態(需繼承{@link StatusBar})
 * @param <B> 建構器本身的型態(需繼承{@link StatusBarBuilder})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class StatusBarBuilder<Z extends StatusBar, B extends StatusBarBuilder<Z, B>>
        extends javafx.scene.control.ControlBuilder<Z, B>
        implements StatusBarBuilderExt<Z, B>
{

    private boolean hasGraphic;
    private javafx.scene.Node valGraphic;

    private boolean hasLeftItems;
    private java.util.Collection<javafx.scene.Node> valLeftItems;

    private boolean hasProgress;
    private double valProgress;

    private boolean hasRightItems;
    private java.util.Collection<javafx.scene.Node> valRightItems;

    private boolean hasText;
    private java.lang.String valText;

    private boolean bound1Graphic;
    private boolean bound2Graphic;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.Node> obsrv1Graphic;
    private javafx.beans.property.Property<javafx.scene.Node> obsrv2Graphic;

    private boolean bound1Progress;
    private boolean bound2Progress;
    private javafx.beans.value.ObservableValue<? extends Number> obsrv1Progress;
    private javafx.beans.property.Property<Number> obsrv2Progress;

    private boolean bound1Text;
    private boolean bound2Text;
    private javafx.beans.value.ObservableValue<? extends String> obsrv1Text;
    private javafx.beans.property.Property<String> obsrv2Text;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasGraphic)
            instance.setGraphic(this.valGraphic);
        if (this.hasLeftItems)
            instance.getLeftItems().addAll(this.valLeftItems);
        if (this.hasProgress)
            instance.setProgress(this.valProgress);
        if (this.hasRightItems)
            instance.getRightItems().addAll(this.valRightItems);
        if (this.hasText)
            instance.setText(this.valText);
        if (this.bound1Graphic)
            instance.graphicProperty().bind(this.obsrv1Graphic);
        if (this.bound2Graphic)
            instance.graphicProperty().bindBidirectional(this.obsrv2Graphic);
        if (this.bound1Progress)
            instance.progressProperty().bind(this.obsrv1Progress);
        if (this.bound2Progress)
            instance.progressProperty().bindBidirectional(this.obsrv2Progress);
        if (this.bound1Text)
            instance.textProperty().bind(this.obsrv1Text);
        if (this.bound2Text)
            instance.textProperty().bindBidirectional(this.obsrv2Text);
    }

    /**
     * 設定屬性{@link StatusBar#setGraphic(javafx.scene.Node)}。
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
     * 設定集合屬性{@link StatusBar#getLeftItems}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     * @deprecated 屬性值並非{@link javafx.collections.ObservableList}
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public final B leftItems(java.util.Collection<? extends javafx.scene.Node> value)
    {
        this.hasLeftItems = true;
        this.valLeftItems = (java.util.Collection<javafx.scene.Node>) value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link StatusBar#getLeftItems}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B leftItems(javafx.scene.Node... value)
    {
        this.hasLeftItems = true;
        this.valLeftItems = java.util.Arrays.asList(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link StatusBar#getLeftItems}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B leftItemsAdd(java.util.Collection<? extends javafx.scene.Node> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasLeftItems = true;
        if (this.valLeftItems == null)
            this.valLeftItems = new java.util.ArrayList<>(value.size());
        this.valLeftItems.addAll(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link StatusBar#getLeftItems}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B leftItemsAdd(javafx.scene.Node... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasLeftItems = true;
        if (this.valLeftItems == null)
            this.valLeftItems = new java.util.ArrayList<>(value.length);
        this.valLeftItems.addAll(java.util.Arrays.asList(value));
        return (B) this;
    }

    /**
     * 設定屬性{@link StatusBar#setProgress(double)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B progress(double value)
    {
        this.hasProgress = true;
        this.valProgress = value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link StatusBar#getRightItems}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     * @deprecated 屬性值並非{@link javafx.collections.ObservableList}
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public final B rightItems(java.util.Collection<? extends javafx.scene.Node> value)
    {
        this.hasRightItems = true;
        this.valRightItems = (java.util.Collection<javafx.scene.Node>) value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link StatusBar#getRightItems}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B rightItems(javafx.scene.Node... value)
    {
        this.hasRightItems = true;
        this.valRightItems = java.util.Arrays.asList(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link StatusBar#getRightItems}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B rightItemsAdd(java.util.Collection<? extends javafx.scene.Node> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasRightItems = true;
        if (this.valRightItems == null)
            this.valRightItems = new java.util.ArrayList<>(value.size());
        this.valRightItems.addAll(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link StatusBar#getRightItems}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B rightItemsAdd(javafx.scene.Node... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasRightItems = true;
        if (this.valRightItems == null)
            this.valRightItems = new java.util.ArrayList<>(value.length);
        this.valRightItems.addAll(java.util.Arrays.asList(value));
        return (B) this;
    }

    /**
     * 設定屬性{@link StatusBar#setText(java.lang.String)}。
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
     * 設定屬性{@link StatusBar#graphicProperty}的連結。
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
     * 設定屬性{@link StatusBar#graphicProperty}的雙向連結。
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
     * 設定屬性{@link StatusBar#progressProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindProgress(javafx.beans.value.ObservableValue<? extends Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Progress = true;
        this.obsrv1Progress = source;
        this.bound2Progress = false;
        this.obsrv2Progress = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link StatusBar#progressProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalProgress(javafx.beans.property.Property<Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Progress = false;
        this.obsrv1Progress = null;
        this.bound2Progress = true;
        this.obsrv2Progress = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link StatusBar#textProperty}的連結。
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
     * 設定屬性{@link StatusBar#textProperty}的雙向連結。
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
     * 建構{@link StatusBar}物件。
     *
     * @return 新的{@link StatusBar}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public StatusBar build()
    {
        StatusBar instance = new StatusBar();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
