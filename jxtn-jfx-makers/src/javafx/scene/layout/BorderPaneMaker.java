// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.layout;

/**
 * {@link BorderPane}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link BorderPane})
 * @param <B> 建構器本身的型態(需繼承{@link BorderPaneMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class BorderPaneMaker<Z extends BorderPane, B extends BorderPaneMaker<Z, B>>
        extends javafx.scene.layout.PaneMaker<Z, B>
        implements BorderPaneMakerExt<Z, B>
{

    private boolean hasBottom;
    private javafx.scene.Node valBottom;

    private boolean hasCenter;
    private javafx.scene.Node valCenter;

    private boolean hasLeft;
    private javafx.scene.Node valLeft;

    private boolean hasRight;
    private javafx.scene.Node valRight;

    private boolean hasTop;
    private javafx.scene.Node valTop;

    private boolean bound1Bottom;
    private boolean bound2Bottom;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.Node> obsrv1Bottom;
    private javafx.beans.property.Property<javafx.scene.Node> obsrv2Bottom;

    private boolean bound1Center;
    private boolean bound2Center;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.Node> obsrv1Center;
    private javafx.beans.property.Property<javafx.scene.Node> obsrv2Center;

    private boolean bound1Left;
    private boolean bound2Left;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.Node> obsrv1Left;
    private javafx.beans.property.Property<javafx.scene.Node> obsrv2Left;

    private boolean bound1Right;
    private boolean bound2Right;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.Node> obsrv1Right;
    private javafx.beans.property.Property<javafx.scene.Node> obsrv2Right;

    private boolean bound1Top;
    private boolean bound2Top;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.Node> obsrv1Top;
    private javafx.beans.property.Property<javafx.scene.Node> obsrv2Top;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasBottom)
            instance.setBottom(this.valBottom);
        if (this.hasCenter)
            instance.setCenter(this.valCenter);
        if (this.hasLeft)
            instance.setLeft(this.valLeft);
        if (this.hasRight)
            instance.setRight(this.valRight);
        if (this.hasTop)
            instance.setTop(this.valTop);
        if (this.bound1Bottom)
            instance.bottomProperty().bind(this.obsrv1Bottom);
        if (this.bound2Bottom)
            instance.bottomProperty().bindBidirectional(this.obsrv2Bottom);
        if (this.bound1Center)
            instance.centerProperty().bind(this.obsrv1Center);
        if (this.bound2Center)
            instance.centerProperty().bindBidirectional(this.obsrv2Center);
        if (this.bound1Left)
            instance.leftProperty().bind(this.obsrv1Left);
        if (this.bound2Left)
            instance.leftProperty().bindBidirectional(this.obsrv2Left);
        if (this.bound1Right)
            instance.rightProperty().bind(this.obsrv1Right);
        if (this.bound2Right)
            instance.rightProperty().bindBidirectional(this.obsrv2Right);
        if (this.bound1Top)
            instance.topProperty().bind(this.obsrv1Top);
        if (this.bound2Top)
            instance.topProperty().bindBidirectional(this.obsrv2Top);
    }

    /**
     * 設定屬性{@link BorderPane#setBottom(javafx.scene.Node)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B bottom(javafx.scene.Node value)
    {
        this.hasBottom = true;
        this.valBottom = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link BorderPane#setCenter(javafx.scene.Node)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B center(javafx.scene.Node value)
    {
        this.hasCenter = true;
        this.valCenter = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link BorderPane#setLeft(javafx.scene.Node)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B left(javafx.scene.Node value)
    {
        this.hasLeft = true;
        this.valLeft = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link BorderPane#setRight(javafx.scene.Node)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B right(javafx.scene.Node value)
    {
        this.hasRight = true;
        this.valRight = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link BorderPane#setTop(javafx.scene.Node)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B top(javafx.scene.Node value)
    {
        this.hasTop = true;
        this.valTop = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link BorderPane#bottomProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBottom(javafx.beans.value.ObservableValue<? extends javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Bottom = true;
        this.obsrv1Bottom = source;
        this.bound2Bottom = false;
        this.obsrv2Bottom = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link BorderPane#bottomProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalBottom(javafx.beans.property.Property<javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Bottom = false;
        this.obsrv1Bottom = null;
        this.bound2Bottom = true;
        this.obsrv2Bottom = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link BorderPane#centerProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindCenter(javafx.beans.value.ObservableValue<? extends javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Center = true;
        this.obsrv1Center = source;
        this.bound2Center = false;
        this.obsrv2Center = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link BorderPane#centerProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalCenter(javafx.beans.property.Property<javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Center = false;
        this.obsrv1Center = null;
        this.bound2Center = true;
        this.obsrv2Center = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link BorderPane#leftProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindLeft(javafx.beans.value.ObservableValue<? extends javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Left = true;
        this.obsrv1Left = source;
        this.bound2Left = false;
        this.obsrv2Left = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link BorderPane#leftProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalLeft(javafx.beans.property.Property<javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Left = false;
        this.obsrv1Left = null;
        this.bound2Left = true;
        this.obsrv2Left = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link BorderPane#rightProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindRight(javafx.beans.value.ObservableValue<? extends javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Right = true;
        this.obsrv1Right = source;
        this.bound2Right = false;
        this.obsrv2Right = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link BorderPane#rightProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalRight(javafx.beans.property.Property<javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Right = false;
        this.obsrv1Right = null;
        this.bound2Right = true;
        this.obsrv2Right = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link BorderPane#topProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindTop(javafx.beans.value.ObservableValue<? extends javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Top = true;
        this.obsrv1Top = source;
        this.bound2Top = false;
        this.obsrv2Top = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link BorderPane#topProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalTop(javafx.beans.property.Property<javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Top = false;
        this.obsrv1Top = null;
        this.bound2Top = true;
        this.obsrv2Top = source;
        return (B) this;
    }

    /**
     * 建構{@link BorderPane}物件。
     *
     * @return 新的{@link BorderPane}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public BorderPane build()
    {
        BorderPane instance = new BorderPane();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link BorderPane}物件。
     *
     * @return 新的{@link BorderPane}物件實體
     */
    @SuppressWarnings("unchecked")
    public BorderPane build(javafx.scene.Node arg0)
    {
        BorderPane instance = new BorderPane(arg0);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link BorderPane}物件。
     *
     * @return 新的{@link BorderPane}物件實體
     */
    @SuppressWarnings("unchecked")
    public BorderPane build(javafx.scene.Node arg0, javafx.scene.Node arg1, javafx.scene.Node arg2, javafx.scene.Node arg3, javafx.scene.Node arg4)
    {
        BorderPane instance = new BorderPane(arg0, arg1, arg2, arg3, arg4);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
