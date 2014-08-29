// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.transform;

/**
 * {@link Rotate}建構器
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class RotateBuilder<Z extends Rotate, B extends RotateBuilder<Z, B>>
        extends javafx.scene.transform.TransformBuilder<Z, B>
{

    protected boolean hasAngle;
    protected double valAngle;

    protected boolean hasAxis;
    protected javafx.geometry.Point3D valAxis;

    protected boolean hasPivotX;
    protected double valPivotX;

    protected boolean hasPivotY;
    protected double valPivotY;

    protected boolean hasPivotZ;
    protected double valPivotZ;

    protected boolean boundAngle;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvAngle;

    protected boolean boundAxis;
    protected javafx.beans.value.ObservableValue<? extends javafx.geometry.Point3D> obsrvAxis;

    protected boolean boundPivotX;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvPivotX;

    protected boolean boundPivotY;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvPivotY;

    protected boolean boundPivotZ;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvPivotZ;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasAngle)
            instance.setAngle(this.valAngle);
        if (this.hasAxis)
            instance.setAxis(this.valAxis);
        if (this.hasPivotX)
            instance.setPivotX(this.valPivotX);
        if (this.hasPivotY)
            instance.setPivotY(this.valPivotY);
        if (this.hasPivotZ)
            instance.setPivotZ(this.valPivotZ);
        if (this.boundAngle)
            instance.angleProperty().bind(this.obsrvAngle);
        if (this.boundAxis)
            instance.axisProperty().bind(this.obsrvAxis);
        if (this.boundPivotX)
            instance.pivotXProperty().bind(this.obsrvPivotX);
        if (this.boundPivotY)
            instance.pivotYProperty().bind(this.obsrvPivotY);
        if (this.boundPivotZ)
            instance.pivotZProperty().bind(this.obsrvPivotZ);
    }

    /**
     * 設定屬性{@link Rotate#setAngle(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B angle(double value)
    {
        this.hasAngle = true;
        this.valAngle = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Rotate#setAxis(javafx.geometry.Point3D)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B axis(javafx.geometry.Point3D value)
    {
        this.hasAxis = true;
        this.valAxis = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Rotate#setPivotX(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B pivotX(double value)
    {
        this.hasPivotX = true;
        this.valPivotX = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Rotate#setPivotY(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B pivotY(double value)
    {
        this.hasPivotY = true;
        this.valPivotY = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Rotate#setPivotZ(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B pivotZ(double value)
    {
        this.hasPivotZ = true;
        this.valPivotZ = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Rotate#angleProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindAngle(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundAngle = true;
        this.obsrvAngle = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Rotate#axisProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindAxis(javafx.beans.value.ObservableValue<? extends javafx.geometry.Point3D> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundAxis = true;
        this.obsrvAxis = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Rotate#pivotXProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindPivotX(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundPivotX = true;
        this.obsrvPivotX = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Rotate#pivotYProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindPivotY(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundPivotY = true;
        this.obsrvPivotY = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Rotate#pivotZProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindPivotZ(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundPivotZ = true;
        this.obsrvPivotZ = source;
        return (B) this;
    }

    /**
     * 建構{@link Rotate}物件
     *
     * @return 新的{@link Rotate}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public Rotate build()
    {
        Rotate instance = new Rotate();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
