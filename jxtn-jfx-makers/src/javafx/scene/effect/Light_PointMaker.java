// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.effect;

/**
 * {@link Light.Point}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link Light.Point})
 * @param <B> 建構器本身的型態(需繼承{@link Light.PointMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class Light_PointMaker<Z extends Light.Point, B extends Light_PointMaker<Z, B>>
        extends javafx.scene.effect.LightMaker<Z, B>
        implements Light_PointMakerExt<Z, B>
{

    private boolean hasX;
    private double valX;

    private boolean hasY;
    private double valY;

    private boolean hasZ;
    private double valZ;

    private boolean bound1X;
    private boolean bound2X;
    private javafx.beans.value.ObservableValue<? extends Number> obsrv1X;
    private javafx.beans.property.Property<Number> obsrv2X;

    private boolean bound1Y;
    private boolean bound2Y;
    private javafx.beans.value.ObservableValue<? extends Number> obsrv1Y;
    private javafx.beans.property.Property<Number> obsrv2Y;

    private boolean bound1Z;
    private boolean bound2Z;
    private javafx.beans.value.ObservableValue<? extends Number> obsrv1Z;
    private javafx.beans.property.Property<Number> obsrv2Z;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasX)
            instance.setX(this.valX);
        if (this.hasY)
            instance.setY(this.valY);
        if (this.hasZ)
            instance.setZ(this.valZ);
        if (this.bound1X)
            instance.xProperty().bind(this.obsrv1X);
        if (this.bound2X)
            instance.xProperty().bindBidirectional(this.obsrv2X);
        if (this.bound1Y)
            instance.yProperty().bind(this.obsrv1Y);
        if (this.bound2Y)
            instance.yProperty().bindBidirectional(this.obsrv2Y);
        if (this.bound1Z)
            instance.zProperty().bind(this.obsrv1Z);
        if (this.bound2Z)
            instance.zProperty().bindBidirectional(this.obsrv2Z);
    }

    /**
     * 設定屬性{@link Light.Point#setX(double)}。
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
     * 設定屬性{@link Light.Point#setY(double)}。
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
     * 設定屬性{@link Light.Point#setZ(double)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B z(double value)
    {
        this.hasZ = true;
        this.valZ = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Light.Point#xProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindX(javafx.beans.value.ObservableValue<? extends Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1X = true;
        this.obsrv1X = source;
        this.bound2X = false;
        this.obsrv2X = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Light.Point#xProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalX(javafx.beans.property.Property<Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1X = false;
        this.obsrv1X = null;
        this.bound2X = true;
        this.obsrv2X = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Light.Point#yProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindY(javafx.beans.value.ObservableValue<? extends Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Y = true;
        this.obsrv1Y = source;
        this.bound2Y = false;
        this.obsrv2Y = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Light.Point#yProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalY(javafx.beans.property.Property<Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Y = false;
        this.obsrv1Y = null;
        this.bound2Y = true;
        this.obsrv2Y = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Light.Point#zProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindZ(javafx.beans.value.ObservableValue<? extends Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Z = true;
        this.obsrv1Z = source;
        this.bound2Z = false;
        this.obsrv2Z = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Light.Point#zProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalZ(javafx.beans.property.Property<Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Z = false;
        this.obsrv1Z = null;
        this.bound2Z = true;
        this.obsrv2Z = source;
        return (B) this;
    }

    /**
     * 建構{@link Light.Point}物件。
     *
     * @return 新的{@link Light.Point}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public Light.Point build()
    {
        Light.Point instance = new Light.Point();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link Light.Point}物件。
     *
     * @return 新的{@link Light.Point}物件實體
     */
    @SuppressWarnings("unchecked")
    public Light.Point build(double arg0, double arg1, double arg2, javafx.scene.paint.Color arg3)
    {
        Light.Point instance = new Light.Point(arg0, arg1, arg2, arg3);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}