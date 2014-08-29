// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.shape;

/**
 * {@link ArcTo}建構器
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class ArcToBuilder<Z extends ArcTo, B extends ArcToBuilder<Z, B>>
        extends javafx.scene.shape.PathElementBuilder<Z, B>
{

    protected boolean hasLargeArcFlag;
    protected boolean valLargeArcFlag;

    protected boolean hasRadiusX;
    protected double valRadiusX;

    protected boolean hasRadiusY;
    protected double valRadiusY;

    protected boolean hasSweepFlag;
    protected boolean valSweepFlag;

    protected boolean hasX;
    protected double valX;

    protected boolean hasXAxisRotation;
    protected double valXAxisRotation;

    protected boolean hasY;
    protected double valY;

    protected boolean boundXAxisRotation;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvXAxisRotation;

    protected boolean boundLargeArcFlag;
    protected javafx.beans.value.ObservableValue<? extends Boolean> obsrvLargeArcFlag;

    protected boolean boundRadiusX;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvRadiusX;

    protected boolean boundRadiusY;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvRadiusY;

    protected boolean boundSweepFlag;
    protected javafx.beans.value.ObservableValue<? extends Boolean> obsrvSweepFlag;

    protected boolean boundX;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvX;

    protected boolean boundY;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvY;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasLargeArcFlag)
            instance.setLargeArcFlag(this.valLargeArcFlag);
        if (this.hasRadiusX)
            instance.setRadiusX(this.valRadiusX);
        if (this.hasRadiusY)
            instance.setRadiusY(this.valRadiusY);
        if (this.hasSweepFlag)
            instance.setSweepFlag(this.valSweepFlag);
        if (this.hasX)
            instance.setX(this.valX);
        if (this.hasXAxisRotation)
            instance.setXAxisRotation(this.valXAxisRotation);
        if (this.hasY)
            instance.setY(this.valY);
        if (this.boundXAxisRotation)
            instance.XAxisRotationProperty().bind(this.obsrvXAxisRotation);
        if (this.boundLargeArcFlag)
            instance.largeArcFlagProperty().bind(this.obsrvLargeArcFlag);
        if (this.boundRadiusX)
            instance.radiusXProperty().bind(this.obsrvRadiusX);
        if (this.boundRadiusY)
            instance.radiusYProperty().bind(this.obsrvRadiusY);
        if (this.boundSweepFlag)
            instance.sweepFlagProperty().bind(this.obsrvSweepFlag);
        if (this.boundX)
            instance.xProperty().bind(this.obsrvX);
        if (this.boundY)
            instance.yProperty().bind(this.obsrvY);
    }

    /**
     * 設定屬性{@link ArcTo#setLargeArcFlag(boolean)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B largeArcFlag(boolean value)
    {
        this.hasLargeArcFlag = true;
        this.valLargeArcFlag = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ArcTo#setRadiusX(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B radiusX(double value)
    {
        this.hasRadiusX = true;
        this.valRadiusX = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ArcTo#setRadiusY(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B radiusY(double value)
    {
        this.hasRadiusY = true;
        this.valRadiusY = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ArcTo#setSweepFlag(boolean)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B sweepFlag(boolean value)
    {
        this.hasSweepFlag = true;
        this.valSweepFlag = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ArcTo#setX(double)}
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
     * 設定屬性{@link ArcTo#setXAxisRotation(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B xAxisRotation(double value)
    {
        this.hasXAxisRotation = true;
        this.valXAxisRotation = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ArcTo#setY(double)}
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
     * 設定屬性{@link ArcTo#XAxisRotationProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindXAxisRotation(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundXAxisRotation = true;
        this.obsrvXAxisRotation = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ArcTo#largeArcFlagProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindLargeArcFlag(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundLargeArcFlag = true;
        this.obsrvLargeArcFlag = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ArcTo#radiusXProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindRadiusX(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundRadiusX = true;
        this.obsrvRadiusX = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ArcTo#radiusYProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindRadiusY(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundRadiusY = true;
        this.obsrvRadiusY = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ArcTo#sweepFlagProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindSweepFlag(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundSweepFlag = true;
        this.obsrvSweepFlag = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ArcTo#xProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindX(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundX = true;
        this.obsrvX = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ArcTo#yProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindY(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundY = true;
        this.obsrvY = source;
        return (B) this;
    }

    /**
     * 建構{@link ArcTo}物件
     *
     * @return 新的{@link ArcTo}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public ArcTo build()
    {
        ArcTo instance = new ArcTo();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
