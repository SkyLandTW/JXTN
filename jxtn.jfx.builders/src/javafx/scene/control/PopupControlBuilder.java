// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.control;

/**
 * {@link PopupControl}建構器
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class PopupControlBuilder<Z extends PopupControl, B extends PopupControlBuilder<Z, B>>
        extends javafx.stage.PopupWindowBuilder<Z, B>
{

    protected boolean hasId;
    protected java.lang.String valId;

    protected boolean hasMaxHeight;
    protected double valMaxHeight;

    protected boolean hasMaxWidth;
    protected double valMaxWidth;

    protected boolean hasMinHeight;
    protected double valMinHeight;

    protected boolean hasMinWidth;
    protected double valMinWidth;

    protected boolean hasPrefHeight;
    protected double valPrefHeight;

    protected boolean hasPrefWidth;
    protected double valPrefWidth;

    protected boolean hasSkin;
    protected javafx.scene.control.Skin<?> valSkin;

    protected boolean hasStyle;
    protected java.lang.String valStyle;

    protected boolean hasStyleClass;
    protected java.util.Collection<java.lang.String> valStyleClass;

    protected boolean boundId;
    protected javafx.beans.value.ObservableValue<? extends String> obsrvId;

    protected boolean boundMaxHeight;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvMaxHeight;

    protected boolean boundMaxWidth;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvMaxWidth;

    protected boolean boundMinHeight;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvMinHeight;

    protected boolean boundMinWidth;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvMinWidth;

    protected boolean boundPrefHeight;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvPrefHeight;

    protected boolean boundPrefWidth;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvPrefWidth;

    protected boolean boundSkin;
    protected javafx.beans.value.ObservableValue<? extends javafx.scene.control.Skin<?>> obsrvSkin;

    protected boolean boundStyle;
    protected javafx.beans.value.ObservableValue<? extends String> obsrvStyle;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasId)
            instance.setId(this.valId);
        if (this.hasMaxHeight)
            instance.setMaxHeight(this.valMaxHeight);
        if (this.hasMaxWidth)
            instance.setMaxWidth(this.valMaxWidth);
        if (this.hasMinHeight)
            instance.setMinHeight(this.valMinHeight);
        if (this.hasMinWidth)
            instance.setMinWidth(this.valMinWidth);
        if (this.hasPrefHeight)
            instance.setPrefHeight(this.valPrefHeight);
        if (this.hasPrefWidth)
            instance.setPrefWidth(this.valPrefWidth);
        if (this.hasSkin)
            instance.setSkin(this.valSkin);
        if (this.hasStyle)
            instance.setStyle(this.valStyle);
        if (this.hasStyleClass)
            instance.getStyleClass().setAll(this.valStyleClass);
        if (this.boundId)
            instance.idProperty().bind(this.obsrvId);
        if (this.boundMaxHeight)
            instance.maxHeightProperty().bind(this.obsrvMaxHeight);
        if (this.boundMaxWidth)
            instance.maxWidthProperty().bind(this.obsrvMaxWidth);
        if (this.boundMinHeight)
            instance.minHeightProperty().bind(this.obsrvMinHeight);
        if (this.boundMinWidth)
            instance.minWidthProperty().bind(this.obsrvMinWidth);
        if (this.boundPrefHeight)
            instance.prefHeightProperty().bind(this.obsrvPrefHeight);
        if (this.boundPrefWidth)
            instance.prefWidthProperty().bind(this.obsrvPrefWidth);
        if (this.boundSkin)
            instance.skinProperty().bind(this.obsrvSkin);
        if (this.boundStyle)
            instance.styleProperty().bind(this.obsrvStyle);
    }

    /**
     * 設定屬性{@link PopupControl#setId(java.lang.String)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B id(java.lang.String value)
    {
        this.hasId = true;
        this.valId = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link PopupControl#setMaxHeight(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B maxHeight(double value)
    {
        this.hasMaxHeight = true;
        this.valMaxHeight = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link PopupControl#setMaxWidth(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B maxWidth(double value)
    {
        this.hasMaxWidth = true;
        this.valMaxWidth = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link PopupControl#setMinHeight(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B minHeight(double value)
    {
        this.hasMinHeight = true;
        this.valMinHeight = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link PopupControl#setMinWidth(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B minWidth(double value)
    {
        this.hasMinWidth = true;
        this.valMinWidth = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link PopupControl#setPrefHeight(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B prefHeight(double value)
    {
        this.hasPrefHeight = true;
        this.valPrefHeight = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link PopupControl#setPrefWidth(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B prefWidth(double value)
    {
        this.hasPrefWidth = true;
        this.valPrefWidth = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link PopupControl#setSkin(javafx.scene.control.Skin)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B skin(javafx.scene.control.Skin<?> value)
    {
        this.hasSkin = true;
        this.valSkin = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link PopupControl#setStyle(java.lang.String)}
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
     * 設定集合屬性{@link PopupControl#getStyleClass}的內容
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B styleClass(java.util.Collection<java.lang.String> value)
    {
        this.hasStyleClass = true;
        this.valStyleClass = value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link PopupControl#getStyleClass}的內容
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
     * 設定屬性{@link PopupControl#idProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindId(javafx.beans.value.ObservableValue<? extends String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundId = true;
        this.obsrvId = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link PopupControl#maxHeightProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindMaxHeight(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundMaxHeight = true;
        this.obsrvMaxHeight = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link PopupControl#maxWidthProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindMaxWidth(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundMaxWidth = true;
        this.obsrvMaxWidth = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link PopupControl#minHeightProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindMinHeight(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundMinHeight = true;
        this.obsrvMinHeight = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link PopupControl#minWidthProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindMinWidth(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundMinWidth = true;
        this.obsrvMinWidth = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link PopupControl#prefHeightProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindPrefHeight(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundPrefHeight = true;
        this.obsrvPrefHeight = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link PopupControl#prefWidthProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindPrefWidth(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundPrefWidth = true;
        this.obsrvPrefWidth = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link PopupControl#skinProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindSkin(javafx.beans.value.ObservableValue<? extends javafx.scene.control.Skin<?>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundSkin = true;
        this.obsrvSkin = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link PopupControl#styleProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindStyle(javafx.beans.value.ObservableValue<? extends String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundStyle = true;
        this.obsrvStyle = source;
        return (B) this;
    }

    /**
     * 建構{@link PopupControl}物件
     *
     * @return 新的{@link PopupControl}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public PopupControl build()
    {
        PopupControl instance = new PopupControl();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
