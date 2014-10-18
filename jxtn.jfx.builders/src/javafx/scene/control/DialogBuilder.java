// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.control;

/**
 * {@link Dialog}建構器
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version openjfx-dialogs-1.0.2.jar
 * @param <Z> 要建構的物件型態(需繼承{@link Dialog})
 * @param <B> 建構器本身的型態(需繼承{@link DialogBuilder})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class DialogBuilder<R extends java.lang.Object, Z extends Dialog<R>, B extends DialogBuilder<R, Z, B>>
        extends jxtn.jfx.builders.AbstractBuilder<Z, B>
        implements DialogBuilderExt<R, Z, B>
{

    private boolean hasContentText;
    private java.lang.String valContentText;

    private boolean hasDialogPane;
    private javafx.scene.control.DialogPane valDialogPane;

    private boolean hasGraphic;
    private javafx.scene.Node valGraphic;

    private boolean hasHeaderText;
    private java.lang.String valHeaderText;

    private boolean hasHeight;
    private double valHeight;

    private boolean hasOnCloseRequest;
    private javafx.event.EventHandler<javafx.scene.control.DialogEvent> valOnCloseRequest;

    private boolean hasOnHidden;
    private javafx.event.EventHandler<javafx.scene.control.DialogEvent> valOnHidden;

    private boolean hasOnHiding;
    private javafx.event.EventHandler<javafx.scene.control.DialogEvent> valOnHiding;

    private boolean hasOnShowing;
    private javafx.event.EventHandler<javafx.scene.control.DialogEvent> valOnShowing;

    private boolean hasOnShown;
    private javafx.event.EventHandler<javafx.scene.control.DialogEvent> valOnShown;

    private boolean hasResizable;
    private boolean valResizable;

    private boolean hasResult;
    private R valResult;

    private boolean hasResultConverter;
    private javafx.util.Callback<javafx.scene.control.ButtonType, R> valResultConverter;

    private boolean hasTitle;
    private java.lang.String valTitle;

    private boolean hasWidth;
    private double valWidth;

    private boolean hasX;
    private double valX;

    private boolean hasY;
    private double valY;

    private boolean bound1ContentText;
    private boolean bound2ContentText;
    private javafx.beans.value.ObservableValue<? extends String> obsrv1ContentText;
    private javafx.beans.property.Property<String> obsrv2ContentText;

    private boolean bound1DialogPane;
    private boolean bound2DialogPane;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.control.DialogPane> obsrv1DialogPane;
    private javafx.beans.property.Property<javafx.scene.control.DialogPane> obsrv2DialogPane;

    private boolean bound1Graphic;
    private boolean bound2Graphic;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.Node> obsrv1Graphic;
    private javafx.beans.property.Property<javafx.scene.Node> obsrv2Graphic;

    private boolean bound1HeaderText;
    private boolean bound2HeaderText;
    private javafx.beans.value.ObservableValue<? extends String> obsrv1HeaderText;
    private javafx.beans.property.Property<String> obsrv2HeaderText;

    private boolean bound1OnCloseRequest;
    private boolean bound2OnCloseRequest;
    private javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.scene.control.DialogEvent>> obsrv1OnCloseRequest;
    private javafx.beans.property.Property<javafx.event.EventHandler<javafx.scene.control.DialogEvent>> obsrv2OnCloseRequest;

    private boolean bound1OnHidden;
    private boolean bound2OnHidden;
    private javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.scene.control.DialogEvent>> obsrv1OnHidden;
    private javafx.beans.property.Property<javafx.event.EventHandler<javafx.scene.control.DialogEvent>> obsrv2OnHidden;

    private boolean bound1OnHiding;
    private boolean bound2OnHiding;
    private javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.scene.control.DialogEvent>> obsrv1OnHiding;
    private javafx.beans.property.Property<javafx.event.EventHandler<javafx.scene.control.DialogEvent>> obsrv2OnHiding;

    private boolean bound1OnShowing;
    private boolean bound2OnShowing;
    private javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.scene.control.DialogEvent>> obsrv1OnShowing;
    private javafx.beans.property.Property<javafx.event.EventHandler<javafx.scene.control.DialogEvent>> obsrv2OnShowing;

    private boolean bound1OnShown;
    private boolean bound2OnShown;
    private javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.scene.control.DialogEvent>> obsrv1OnShown;
    private javafx.beans.property.Property<javafx.event.EventHandler<javafx.scene.control.DialogEvent>> obsrv2OnShown;

    private boolean bound1Resizable;
    private boolean bound2Resizable;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1Resizable;
    private javafx.beans.property.Property<Boolean> obsrv2Resizable;

    private boolean bound1ResultConverter;
    private boolean bound2ResultConverter;
    private javafx.beans.value.ObservableValue<? extends javafx.util.Callback<javafx.scene.control.ButtonType, R>> obsrv1ResultConverter;
    private javafx.beans.property.Property<javafx.util.Callback<javafx.scene.control.ButtonType, R>> obsrv2ResultConverter;

    private boolean bound1Result;
    private boolean bound2Result;
    private javafx.beans.value.ObservableValue<? extends R> obsrv1Result;
    private javafx.beans.property.Property<R> obsrv2Result;

    private boolean bound1Title;
    private boolean bound2Title;
    private javafx.beans.value.ObservableValue<? extends String> obsrv1Title;
    private javafx.beans.property.Property<String> obsrv2Title;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasContentText)
            instance.setContentText(this.valContentText);
        if (this.hasDialogPane)
            instance.setDialogPane(this.valDialogPane);
        if (this.hasGraphic)
            instance.setGraphic(this.valGraphic);
        if (this.hasHeaderText)
            instance.setHeaderText(this.valHeaderText);
        if (this.hasHeight)
            instance.setHeight(this.valHeight);
        if (this.hasOnCloseRequest)
            instance.setOnCloseRequest(this.valOnCloseRequest);
        if (this.hasOnHidden)
            instance.setOnHidden(this.valOnHidden);
        if (this.hasOnHiding)
            instance.setOnHiding(this.valOnHiding);
        if (this.hasOnShowing)
            instance.setOnShowing(this.valOnShowing);
        if (this.hasOnShown)
            instance.setOnShown(this.valOnShown);
        if (this.hasResizable)
            instance.setResizable(this.valResizable);
        if (this.hasResult)
            instance.setResult(this.valResult);
        if (this.hasResultConverter)
            instance.setResultConverter(this.valResultConverter);
        if (this.hasTitle)
            instance.setTitle(this.valTitle);
        if (this.hasWidth)
            instance.setWidth(this.valWidth);
        if (this.hasX)
            instance.setX(this.valX);
        if (this.hasY)
            instance.setY(this.valY);
        if (this.bound1ContentText)
            instance.contentTextProperty().bind(this.obsrv1ContentText);
        if (this.bound2ContentText)
            instance.contentTextProperty().bindBidirectional(this.obsrv2ContentText);
        if (this.bound1DialogPane)
            instance.dialogPaneProperty().bind(this.obsrv1DialogPane);
        if (this.bound2DialogPane)
            instance.dialogPaneProperty().bindBidirectional(this.obsrv2DialogPane);
        if (this.bound1Graphic)
            instance.graphicProperty().bind(this.obsrv1Graphic);
        if (this.bound2Graphic)
            instance.graphicProperty().bindBidirectional(this.obsrv2Graphic);
        if (this.bound1HeaderText)
            instance.headerTextProperty().bind(this.obsrv1HeaderText);
        if (this.bound2HeaderText)
            instance.headerTextProperty().bindBidirectional(this.obsrv2HeaderText);
        if (this.bound1OnCloseRequest)
            instance.onCloseRequestProperty().bind(this.obsrv1OnCloseRequest);
        if (this.bound2OnCloseRequest)
            instance.onCloseRequestProperty().bindBidirectional(this.obsrv2OnCloseRequest);
        if (this.bound1OnHidden)
            instance.onHiddenProperty().bind(this.obsrv1OnHidden);
        if (this.bound2OnHidden)
            instance.onHiddenProperty().bindBidirectional(this.obsrv2OnHidden);
        if (this.bound1OnHiding)
            instance.onHidingProperty().bind(this.obsrv1OnHiding);
        if (this.bound2OnHiding)
            instance.onHidingProperty().bindBidirectional(this.obsrv2OnHiding);
        if (this.bound1OnShowing)
            instance.onShowingProperty().bind(this.obsrv1OnShowing);
        if (this.bound2OnShowing)
            instance.onShowingProperty().bindBidirectional(this.obsrv2OnShowing);
        if (this.bound1OnShown)
            instance.onShownProperty().bind(this.obsrv1OnShown);
        if (this.bound2OnShown)
            instance.onShownProperty().bindBidirectional(this.obsrv2OnShown);
        if (this.bound1Resizable)
            instance.resizableProperty().bind(this.obsrv1Resizable);
        if (this.bound2Resizable)
            instance.resizableProperty().bindBidirectional(this.obsrv2Resizable);
        if (this.bound1ResultConverter)
            instance.resultConverterProperty().bind(this.obsrv1ResultConverter);
        if (this.bound2ResultConverter)
            instance.resultConverterProperty().bindBidirectional(this.obsrv2ResultConverter);
        if (this.bound1Result)
            instance.resultProperty().bind(this.obsrv1Result);
        if (this.bound2Result)
            instance.resultProperty().bindBidirectional(this.obsrv2Result);
        if (this.bound1Title)
            instance.titleProperty().bind(this.obsrv1Title);
        if (this.bound2Title)
            instance.titleProperty().bindBidirectional(this.obsrv2Title);
    }

    /**
     * 設定屬性{@link Dialog#setContentText(java.lang.String)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B contentText(java.lang.String value)
    {
        this.hasContentText = true;
        this.valContentText = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#setDialogPane(javafx.scene.control.DialogPane)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B dialogPane(javafx.scene.control.DialogPane value)
    {
        this.hasDialogPane = true;
        this.valDialogPane = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#setGraphic(javafx.scene.Node)}
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
     * 設定屬性{@link Dialog#setHeaderText(java.lang.String)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B headerText(java.lang.String value)
    {
        this.hasHeaderText = true;
        this.valHeaderText = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#setHeight(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B height(double value)
    {
        this.hasHeight = true;
        this.valHeight = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#setOnCloseRequest(javafx.event.EventHandler)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B onCloseRequest(javafx.event.EventHandler<javafx.scene.control.DialogEvent> value)
    {
        this.hasOnCloseRequest = true;
        this.valOnCloseRequest = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#setOnHidden(javafx.event.EventHandler)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B onHidden(javafx.event.EventHandler<javafx.scene.control.DialogEvent> value)
    {
        this.hasOnHidden = true;
        this.valOnHidden = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#setOnHiding(javafx.event.EventHandler)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B onHiding(javafx.event.EventHandler<javafx.scene.control.DialogEvent> value)
    {
        this.hasOnHiding = true;
        this.valOnHiding = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#setOnShowing(javafx.event.EventHandler)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B onShowing(javafx.event.EventHandler<javafx.scene.control.DialogEvent> value)
    {
        this.hasOnShowing = true;
        this.valOnShowing = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#setOnShown(javafx.event.EventHandler)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B onShown(javafx.event.EventHandler<javafx.scene.control.DialogEvent> value)
    {
        this.hasOnShown = true;
        this.valOnShown = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#setResizable(boolean)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B resizable(boolean value)
    {
        this.hasResizable = true;
        this.valResizable = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#setResult(R)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B result(R value)
    {
        this.hasResult = true;
        this.valResult = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#setResultConverter(javafx.util.Callback)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B resultConverter(javafx.util.Callback<javafx.scene.control.ButtonType, R> value)
    {
        this.hasResultConverter = true;
        this.valResultConverter = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#setTitle(java.lang.String)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B title(java.lang.String value)
    {
        this.hasTitle = true;
        this.valTitle = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#setWidth(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B width(double value)
    {
        this.hasWidth = true;
        this.valWidth = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#setX(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B x(double value)
    {
        this.hasX = true;
        this.valX = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#setY(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B y(double value)
    {
        this.hasY = true;
        this.valY = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#contentTextProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindContentText(javafx.beans.value.ObservableValue<? extends String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1ContentText = true;
        this.obsrv1ContentText = source;
        this.bound2ContentText = false;
        this.obsrv2ContentText = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#contentTextProperty}的雙向連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalContentText(javafx.beans.property.Property<String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1ContentText = false;
        this.obsrv1ContentText = null;
        this.bound2ContentText = true;
        this.obsrv2ContentText = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#dialogPaneProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindDialogPane(javafx.beans.value.ObservableValue<? extends javafx.scene.control.DialogPane> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1DialogPane = true;
        this.obsrv1DialogPane = source;
        this.bound2DialogPane = false;
        this.obsrv2DialogPane = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#dialogPaneProperty}的雙向連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalDialogPane(javafx.beans.property.Property<javafx.scene.control.DialogPane> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1DialogPane = false;
        this.obsrv1DialogPane = null;
        this.bound2DialogPane = true;
        this.obsrv2DialogPane = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#graphicProperty}的連結
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
     * 設定屬性{@link Dialog#graphicProperty}的雙向連結
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
     * 設定屬性{@link Dialog#headerTextProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindHeaderText(javafx.beans.value.ObservableValue<? extends String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1HeaderText = true;
        this.obsrv1HeaderText = source;
        this.bound2HeaderText = false;
        this.obsrv2HeaderText = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#headerTextProperty}的雙向連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalHeaderText(javafx.beans.property.Property<String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1HeaderText = false;
        this.obsrv1HeaderText = null;
        this.bound2HeaderText = true;
        this.obsrv2HeaderText = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#onCloseRequestProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOnCloseRequest(javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.scene.control.DialogEvent>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnCloseRequest = true;
        this.obsrv1OnCloseRequest = source;
        this.bound2OnCloseRequest = false;
        this.obsrv2OnCloseRequest = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#onCloseRequestProperty}的雙向連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalOnCloseRequest(javafx.beans.property.Property<javafx.event.EventHandler<javafx.scene.control.DialogEvent>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnCloseRequest = false;
        this.obsrv1OnCloseRequest = null;
        this.bound2OnCloseRequest = true;
        this.obsrv2OnCloseRequest = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#onHiddenProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOnHidden(javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.scene.control.DialogEvent>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnHidden = true;
        this.obsrv1OnHidden = source;
        this.bound2OnHidden = false;
        this.obsrv2OnHidden = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#onHiddenProperty}的雙向連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalOnHidden(javafx.beans.property.Property<javafx.event.EventHandler<javafx.scene.control.DialogEvent>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnHidden = false;
        this.obsrv1OnHidden = null;
        this.bound2OnHidden = true;
        this.obsrv2OnHidden = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#onHidingProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOnHiding(javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.scene.control.DialogEvent>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnHiding = true;
        this.obsrv1OnHiding = source;
        this.bound2OnHiding = false;
        this.obsrv2OnHiding = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#onHidingProperty}的雙向連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalOnHiding(javafx.beans.property.Property<javafx.event.EventHandler<javafx.scene.control.DialogEvent>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnHiding = false;
        this.obsrv1OnHiding = null;
        this.bound2OnHiding = true;
        this.obsrv2OnHiding = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#onShowingProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOnShowing(javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.scene.control.DialogEvent>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnShowing = true;
        this.obsrv1OnShowing = source;
        this.bound2OnShowing = false;
        this.obsrv2OnShowing = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#onShowingProperty}的雙向連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalOnShowing(javafx.beans.property.Property<javafx.event.EventHandler<javafx.scene.control.DialogEvent>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnShowing = false;
        this.obsrv1OnShowing = null;
        this.bound2OnShowing = true;
        this.obsrv2OnShowing = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#onShownProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOnShown(javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.scene.control.DialogEvent>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnShown = true;
        this.obsrv1OnShown = source;
        this.bound2OnShown = false;
        this.obsrv2OnShown = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#onShownProperty}的雙向連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalOnShown(javafx.beans.property.Property<javafx.event.EventHandler<javafx.scene.control.DialogEvent>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnShown = false;
        this.obsrv1OnShown = null;
        this.bound2OnShown = true;
        this.obsrv2OnShown = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#resizableProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindResizable(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Resizable = true;
        this.obsrv1Resizable = source;
        this.bound2Resizable = false;
        this.obsrv2Resizable = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#resizableProperty}的雙向連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalResizable(javafx.beans.property.Property<Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Resizable = false;
        this.obsrv1Resizable = null;
        this.bound2Resizable = true;
        this.obsrv2Resizable = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#resultConverterProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindResultConverter(javafx.beans.value.ObservableValue<? extends javafx.util.Callback<javafx.scene.control.ButtonType, R>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1ResultConverter = true;
        this.obsrv1ResultConverter = source;
        this.bound2ResultConverter = false;
        this.obsrv2ResultConverter = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#resultConverterProperty}的雙向連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalResultConverter(javafx.beans.property.Property<javafx.util.Callback<javafx.scene.control.ButtonType, R>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1ResultConverter = false;
        this.obsrv1ResultConverter = null;
        this.bound2ResultConverter = true;
        this.obsrv2ResultConverter = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#resultProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindResult(javafx.beans.value.ObservableValue<? extends R> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Result = true;
        this.obsrv1Result = source;
        this.bound2Result = false;
        this.obsrv2Result = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#resultProperty}的雙向連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalResult(javafx.beans.property.Property<R> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Result = false;
        this.obsrv1Result = null;
        this.bound2Result = true;
        this.obsrv2Result = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#titleProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindTitle(javafx.beans.value.ObservableValue<? extends String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Title = true;
        this.obsrv1Title = source;
        this.bound2Title = false;
        this.obsrv2Title = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Dialog#titleProperty}的雙向連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalTitle(javafx.beans.property.Property<String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Title = false;
        this.obsrv1Title = null;
        this.bound2Title = true;
        this.obsrv2Title = source;
        return (B) this;
    }

    /**
     * 建構{@link Dialog}物件
     *
     * @return 新的{@link Dialog}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public Dialog<R> build()
    {
        Dialog<R> instance = new Dialog<R>();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}