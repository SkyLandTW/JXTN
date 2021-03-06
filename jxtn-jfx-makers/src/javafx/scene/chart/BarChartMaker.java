// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.chart;

/**
 * {@link BarChart}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link BarChart})
 * @param <B> 建構器本身的型態(需繼承{@link BarChartMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class BarChartMaker<X extends java.lang.Object, Y extends java.lang.Object, Z extends BarChart<X, Y>, B extends BarChartMaker<X, Y, Z, B>>
        extends javafx.scene.chart.XYChartMaker<X, Y, Z, B>
        implements BarChartMakerExt<X, Y, Z, B>
{

    private boolean hasBarGap;
    private double valBarGap;

    private boolean hasCategoryGap;
    private double valCategoryGap;

    private boolean bound1BarGap;
    private boolean bound2BarGap;
    private javafx.beans.value.ObservableValue<? extends Number> obsrv1BarGap;
    private javafx.beans.property.Property<Number> obsrv2BarGap;

    private boolean bound1CategoryGap;
    private boolean bound2CategoryGap;
    private javafx.beans.value.ObservableValue<? extends Number> obsrv1CategoryGap;
    private javafx.beans.property.Property<Number> obsrv2CategoryGap;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasBarGap)
            instance.setBarGap(this.valBarGap);
        if (this.hasCategoryGap)
            instance.setCategoryGap(this.valCategoryGap);
        if (this.bound1BarGap)
            instance.barGapProperty().bind(this.obsrv1BarGap);
        if (this.bound2BarGap)
            instance.barGapProperty().bindBidirectional(this.obsrv2BarGap);
        if (this.bound1CategoryGap)
            instance.categoryGapProperty().bind(this.obsrv1CategoryGap);
        if (this.bound2CategoryGap)
            instance.categoryGapProperty().bindBidirectional(this.obsrv2CategoryGap);
    }

    /**
     * 設定屬性{@link BarChart#setBarGap(double)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B barGap(double value)
    {
        this.hasBarGap = true;
        this.valBarGap = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link BarChart#setCategoryGap(double)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B categoryGap(double value)
    {
        this.hasCategoryGap = true;
        this.valCategoryGap = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link BarChart#barGapProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBarGap(javafx.beans.value.ObservableValue<? extends Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1BarGap = true;
        this.obsrv1BarGap = source;
        this.bound2BarGap = false;
        this.obsrv2BarGap = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link BarChart#barGapProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalBarGap(javafx.beans.property.Property<Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1BarGap = false;
        this.obsrv1BarGap = null;
        this.bound2BarGap = true;
        this.obsrv2BarGap = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link BarChart#categoryGapProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindCategoryGap(javafx.beans.value.ObservableValue<? extends Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1CategoryGap = true;
        this.obsrv1CategoryGap = source;
        this.bound2CategoryGap = false;
        this.obsrv2CategoryGap = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link BarChart#categoryGapProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalCategoryGap(javafx.beans.property.Property<Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1CategoryGap = false;
        this.obsrv1CategoryGap = null;
        this.bound2CategoryGap = true;
        this.obsrv2CategoryGap = source;
        return (B) this;
    }

    /**
     * 建構{@link BarChart}物件。
     *
     * @return 新的{@link BarChart}物件實體
     */
    @SuppressWarnings("unchecked")
    public BarChart<X, Y> build(javafx.scene.chart.Axis<X> arg0, javafx.scene.chart.Axis<Y> arg1)
    {
        BarChart<X, Y> instance = new BarChart<X, Y>(arg0, arg1);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link BarChart}物件。
     *
     * @return 新的{@link BarChart}物件實體
     */
    @SuppressWarnings("unchecked")
    public BarChart<X, Y> build(javafx.scene.chart.Axis<X> arg0, javafx.scene.chart.Axis<Y> arg1, javafx.collections.ObservableList<javafx.scene.chart.XYChart.Series<X, Y>> arg2)
    {
        BarChart<X, Y> instance = new BarChart<X, Y>(arg0, arg1, arg2);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link BarChart}物件。
     *
     * @return 新的{@link BarChart}物件實體
     */
    @SuppressWarnings("unchecked")
    public BarChart<X, Y> build(javafx.scene.chart.Axis<X> arg0, javafx.scene.chart.Axis<Y> arg1, javafx.collections.ObservableList<javafx.scene.chart.XYChart.Series<X, Y>> arg2, double arg3)
    {
        BarChart<X, Y> instance = new BarChart<X, Y>(arg0, arg1, arg2, arg3);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
