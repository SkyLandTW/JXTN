// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.chart;

/**
 * {@link StackedBarChart}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link StackedBarChart})
 * @param <B> 建構器本身的型態(需繼承{@link StackedBarChartMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class StackedBarChartMaker<X extends java.lang.Object, Y extends java.lang.Object, Z extends StackedBarChart<X, Y>, B extends StackedBarChartMaker<X, Y, Z, B>>
        extends javafx.scene.chart.XYChartMaker<X, Y, Z, B>
        implements StackedBarChartMakerExt<X, Y, Z, B>
{

    private boolean hasCategoryGap;
    private double valCategoryGap;

    private boolean bound1CategoryGap;
    private boolean bound2CategoryGap;
    private javafx.beans.value.ObservableValue<? extends Number> obsrv1CategoryGap;
    private javafx.beans.property.Property<Number> obsrv2CategoryGap;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasCategoryGap)
            instance.setCategoryGap(this.valCategoryGap);
        if (this.bound1CategoryGap)
            instance.categoryGapProperty().bind(this.obsrv1CategoryGap);
        if (this.bound2CategoryGap)
            instance.categoryGapProperty().bindBidirectional(this.obsrv2CategoryGap);
    }

    /**
     * 設定屬性{@link StackedBarChart#setCategoryGap(double)}。
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
     * 設定屬性{@link StackedBarChart#categoryGapProperty}的連結。
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
     * 設定屬性{@link StackedBarChart#categoryGapProperty}的雙向連結。
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
     * 建構{@link StackedBarChart}物件。
     *
     * @return 新的{@link StackedBarChart}物件實體
     */
    @SuppressWarnings("unchecked")
    public StackedBarChart<X, Y> build(javafx.scene.chart.Axis<X> arg0, javafx.scene.chart.Axis<Y> arg1)
    {
        StackedBarChart<X, Y> instance = new StackedBarChart<X, Y>(arg0, arg1);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link StackedBarChart}物件。
     *
     * @return 新的{@link StackedBarChart}物件實體
     */
    @SuppressWarnings("unchecked")
    public StackedBarChart<X, Y> build(javafx.scene.chart.Axis<X> arg0, javafx.scene.chart.Axis<Y> arg1, javafx.collections.ObservableList<javafx.scene.chart.XYChart.Series<X, Y>> arg2)
    {
        StackedBarChart<X, Y> instance = new StackedBarChart<X, Y>(arg0, arg1, arg2);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link StackedBarChart}物件。
     *
     * @return 新的{@link StackedBarChart}物件實體
     */
    @SuppressWarnings("unchecked")
    public StackedBarChart<X, Y> build(javafx.scene.chart.Axis<X> arg0, javafx.scene.chart.Axis<Y> arg1, javafx.collections.ObservableList<javafx.scene.chart.XYChart.Series<X, Y>> arg2, double arg3)
    {
        StackedBarChart<X, Y> instance = new StackedBarChart<X, Y>(arg0, arg1, arg2, arg3);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
