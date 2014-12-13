// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package org.controlsfx.control;

/**
 * {@link InfoOverlay}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version controlsfx-8.20.8.jar
 * @param <Z> 要建構的物件型態(需繼承{@link InfoOverlay})
 * @param <B> 建構器本身的型態(需繼承{@link InfoOverlayMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class InfoOverlayMaker<Z extends InfoOverlay, B extends InfoOverlayMaker<Z, B>>
        extends javafx.scene.control.ControlMaker<Z, B>
        implements InfoOverlayMakerExt<Z, B>
{

    private boolean hasContent;
    private javafx.scene.Node valContent;

    private boolean hasShowOnHover;
    private boolean valShowOnHover;

    private boolean hasText;
    private java.lang.String valText;

    private boolean bound1Content;
    private boolean bound2Content;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.Node> obsrv1Content;
    private javafx.beans.property.Property<javafx.scene.Node> obsrv2Content;

    private boolean bound1ShowOnHover;
    private boolean bound2ShowOnHover;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1ShowOnHover;
    private javafx.beans.property.Property<Boolean> obsrv2ShowOnHover;

    private boolean bound1Text;
    private boolean bound2Text;
    private javafx.beans.value.ObservableValue<? extends String> obsrv1Text;
    private javafx.beans.property.Property<String> obsrv2Text;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasContent)
            instance.setContent(this.valContent);
        if (this.hasShowOnHover)
            instance.setShowOnHover(this.valShowOnHover);
        if (this.hasText)
            instance.setText(this.valText);
        if (this.bound1Content)
            instance.contentProperty().bind(this.obsrv1Content);
        if (this.bound2Content)
            instance.contentProperty().bindBidirectional(this.obsrv2Content);
        if (this.bound1ShowOnHover)
            instance.showOnHoverProperty().bind(this.obsrv1ShowOnHover);
        if (this.bound2ShowOnHover)
            instance.showOnHoverProperty().bindBidirectional(this.obsrv2ShowOnHover);
        if (this.bound1Text)
            instance.textProperty().bind(this.obsrv1Text);
        if (this.bound2Text)
            instance.textProperty().bindBidirectional(this.obsrv2Text);
    }

    /**
     * 設定屬性{@link InfoOverlay#setContent(javafx.scene.Node)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B content(javafx.scene.Node value)
    {
        this.hasContent = true;
        this.valContent = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link InfoOverlay#setShowOnHover(boolean)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B showOnHover(boolean value)
    {
        this.hasShowOnHover = true;
        this.valShowOnHover = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link InfoOverlay#setText(java.lang.String)}。
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
     * 設定屬性{@link InfoOverlay#contentProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindContent(javafx.beans.value.ObservableValue<? extends javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Content = true;
        this.obsrv1Content = source;
        this.bound2Content = false;
        this.obsrv2Content = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link InfoOverlay#contentProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalContent(javafx.beans.property.Property<javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Content = false;
        this.obsrv1Content = null;
        this.bound2Content = true;
        this.obsrv2Content = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link InfoOverlay#showOnHoverProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindShowOnHover(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1ShowOnHover = true;
        this.obsrv1ShowOnHover = source;
        this.bound2ShowOnHover = false;
        this.obsrv2ShowOnHover = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link InfoOverlay#showOnHoverProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalShowOnHover(javafx.beans.property.Property<Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1ShowOnHover = false;
        this.obsrv1ShowOnHover = null;
        this.bound2ShowOnHover = true;
        this.obsrv2ShowOnHover = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link InfoOverlay#textProperty}的連結。
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
     * 設定屬性{@link InfoOverlay#textProperty}的雙向連結。
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
     * 建構{@link InfoOverlay}物件。
     *
     * @return 新的{@link InfoOverlay}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public InfoOverlay build()
    {
        InfoOverlay instance = new InfoOverlay();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link InfoOverlay}物件。
     *
     * @return 新的{@link InfoOverlay}物件實體
     */
    @SuppressWarnings("unchecked")
    public InfoOverlay build(java.lang.String arg0, java.lang.String arg1)
    {
        InfoOverlay instance = new InfoOverlay(arg0, arg1);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link InfoOverlay}物件。
     *
     * @return 新的{@link InfoOverlay}物件實體
     */
    @SuppressWarnings("unchecked")
    public InfoOverlay build(javafx.scene.Node arg0, java.lang.String arg1)
    {
        InfoOverlay instance = new InfoOverlay(arg0, arg1);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
