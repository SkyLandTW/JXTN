// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.chart;

/**
 * {@link AreaChart}建構器
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class AreaChartBuilder<X extends java.lang.Object, Y extends java.lang.Object, Z extends AreaChart<X, Y>, B extends AreaChartBuilder<X, Y, Z, B>>
        extends javafx.scene.chart.XYChartBuilder<X, Y, Z, B>
{

    protected boolean hasCreateSymbols;
    protected boolean valCreateSymbols;

    protected boolean boundCreateSymbols;
    protected javafx.beans.value.ObservableValue<? extends Boolean> obsrvCreateSymbols;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasCreateSymbols)
            instance.setCreateSymbols(this.valCreateSymbols);
        if (this.boundCreateSymbols)
            instance.createSymbolsProperty().bind(this.obsrvCreateSymbols);
    }

    /**
     * 設定屬性{@link AreaChart#setCreateSymbols(boolean)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B createSymbols(boolean value)
    {
        this.hasCreateSymbols = true;
        this.valCreateSymbols = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link AreaChart#createSymbolsProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindCreateSymbols(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundCreateSymbols = true;
        this.obsrvCreateSymbols = source;
        return (B) this;
    }

    /**
     * 建構{@link AreaChart}物件
     *
     * @return 新的{@link AreaChart}物件實體
     */
    @SuppressWarnings("unchecked")
    public AreaChart<X, Y> build(javafx.scene.chart.Axis<X> arg0, javafx.scene.chart.Axis<Y> arg1)
    {
        AreaChart<X, Y> instance = new AreaChart<X, Y>(arg0, arg1);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
