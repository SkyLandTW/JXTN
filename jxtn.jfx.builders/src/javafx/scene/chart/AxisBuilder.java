// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.chart;

/**
 * {@link Axis}建構器
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class AxisBuilder<T extends java.lang.Object, Z extends Axis<T>, B extends AxisBuilder<T, Z, B>>
        extends javafx.scene.layout.RegionBuilder<Z, B>
{

    protected boolean hasAnimated;
    protected boolean valAnimated;

    protected boolean hasAutoRanging;
    protected boolean valAutoRanging;

    protected boolean hasLabel;
    protected java.lang.String valLabel;

    protected boolean hasSide;
    protected javafx.geometry.Side valSide;

    protected boolean hasTickLabelFill;
    protected javafx.scene.paint.Paint valTickLabelFill;

    protected boolean hasTickLabelFont;
    protected javafx.scene.text.Font valTickLabelFont;

    protected boolean hasTickLabelGap;
    protected double valTickLabelGap;

    protected boolean hasTickLabelRotation;
    protected double valTickLabelRotation;

    protected boolean hasTickLabelsVisible;
    protected boolean valTickLabelsVisible;

    protected boolean hasTickLength;
    protected double valTickLength;

    protected boolean hasTickMarkVisible;
    protected boolean valTickMarkVisible;

    protected boolean hasTickMarks;
    protected java.util.Collection<javafx.scene.chart.Axis.TickMark<T>> valTickMarks;

    protected boolean boundAnimated;
    protected javafx.beans.value.ObservableValue<? extends Boolean> obsrvAnimated;

    protected boolean boundAutoRanging;
    protected javafx.beans.value.ObservableValue<? extends Boolean> obsrvAutoRanging;

    protected boolean boundLabel;
    protected javafx.beans.value.ObservableValue<? extends java.lang.String> obsrvLabel;

    protected boolean boundSide;
    protected javafx.beans.value.ObservableValue<? extends javafx.geometry.Side> obsrvSide;

    protected boolean boundTickLabelFill;
    protected javafx.beans.value.ObservableValue<? extends javafx.scene.paint.Paint> obsrvTickLabelFill;

    protected boolean boundTickLabelFont;
    protected javafx.beans.value.ObservableValue<? extends javafx.scene.text.Font> obsrvTickLabelFont;

    protected boolean boundTickLabelGap;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvTickLabelGap;

    protected boolean boundTickLabelRotation;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvTickLabelRotation;

    protected boolean boundTickLabelsVisible;
    protected javafx.beans.value.ObservableValue<? extends Boolean> obsrvTickLabelsVisible;

    protected boolean boundTickLength;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvTickLength;

    protected boolean boundTickMarkVisible;
    protected javafx.beans.value.ObservableValue<? extends Boolean> obsrvTickMarkVisible;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasAnimated)
            instance.setAnimated(this.valAnimated);
        if (this.hasAutoRanging)
            instance.setAutoRanging(this.valAutoRanging);
        if (this.hasLabel)
            instance.setLabel(this.valLabel);
        if (this.hasSide)
            instance.setSide(this.valSide);
        if (this.hasTickLabelFill)
            instance.setTickLabelFill(this.valTickLabelFill);
        if (this.hasTickLabelFont)
            instance.setTickLabelFont(this.valTickLabelFont);
        if (this.hasTickLabelGap)
            instance.setTickLabelGap(this.valTickLabelGap);
        if (this.hasTickLabelRotation)
            instance.setTickLabelRotation(this.valTickLabelRotation);
        if (this.hasTickLabelsVisible)
            instance.setTickLabelsVisible(this.valTickLabelsVisible);
        if (this.hasTickLength)
            instance.setTickLength(this.valTickLength);
        if (this.hasTickMarkVisible)
            instance.setTickMarkVisible(this.valTickMarkVisible);
        if (this.hasTickMarks)
            instance.getTickMarks().setAll(this.valTickMarks);
        if (this.boundAnimated)
            instance.animatedProperty().bind(this.obsrvAnimated);
        if (this.boundAutoRanging)
            instance.autoRangingProperty().bind(this.obsrvAutoRanging);
        if (this.boundLabel)
            instance.labelProperty().bind(this.obsrvLabel);
        if (this.boundSide)
            instance.sideProperty().bind(this.obsrvSide);
        if (this.boundTickLabelFill)
            instance.tickLabelFillProperty().bind(this.obsrvTickLabelFill);
        if (this.boundTickLabelFont)
            instance.tickLabelFontProperty().bind(this.obsrvTickLabelFont);
        if (this.boundTickLabelGap)
            instance.tickLabelGapProperty().bind(this.obsrvTickLabelGap);
        if (this.boundTickLabelRotation)
            instance.tickLabelRotationProperty().bind(this.obsrvTickLabelRotation);
        if (this.boundTickLabelsVisible)
            instance.tickLabelsVisibleProperty().bind(this.obsrvTickLabelsVisible);
        if (this.boundTickLength)
            instance.tickLengthProperty().bind(this.obsrvTickLength);
        if (this.boundTickMarkVisible)
            instance.tickMarkVisibleProperty().bind(this.obsrvTickMarkVisible);
    }

    /**
     * 設定屬性{@link Axis#setAnimated(boolean)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B animated(boolean value)
    {
        this.hasAnimated = true;
        this.valAnimated = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Axis#setAutoRanging(boolean)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B autoRanging(boolean value)
    {
        this.hasAutoRanging = true;
        this.valAutoRanging = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Axis#setLabel(java.lang.String)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B label(java.lang.String value)
    {
        this.hasLabel = true;
        this.valLabel = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Axis#setSide(javafx.geometry.Side)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B side(javafx.geometry.Side value)
    {
        this.hasSide = true;
        this.valSide = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Axis#setTickLabelFill(javafx.scene.paint.Paint)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B tickLabelFill(javafx.scene.paint.Paint value)
    {
        this.hasTickLabelFill = true;
        this.valTickLabelFill = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Axis#setTickLabelFont(javafx.scene.text.Font)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B tickLabelFont(javafx.scene.text.Font value)
    {
        this.hasTickLabelFont = true;
        this.valTickLabelFont = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Axis#setTickLabelGap(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B tickLabelGap(double value)
    {
        this.hasTickLabelGap = true;
        this.valTickLabelGap = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Axis#setTickLabelRotation(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B tickLabelRotation(double value)
    {
        this.hasTickLabelRotation = true;
        this.valTickLabelRotation = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Axis#setTickLabelsVisible(boolean)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B tickLabelsVisible(boolean value)
    {
        this.hasTickLabelsVisible = true;
        this.valTickLabelsVisible = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Axis#setTickLength(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B tickLength(double value)
    {
        this.hasTickLength = true;
        this.valTickLength = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Axis#setTickMarkVisible(boolean)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B tickMarkVisible(boolean value)
    {
        this.hasTickMarkVisible = true;
        this.valTickMarkVisible = value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link Axis#getTickMarks}的內容
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B tickMarks(java.util.Collection<javafx.scene.chart.Axis.TickMark<T>> value)
    {
        this.hasTickMarks = true;
        this.valTickMarks = value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link Axis#getTickMarks}的內容
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B tickMarks(javafx.scene.chart.Axis.TickMark<T>... value)
    {
        this.hasTickMarks = true;
        this.valTickMarks = java.util.Arrays.asList(value);
        return (B) this;
    }

    /**
     * 設定屬性{@link Axis#animatedProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindAnimated(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundAnimated = true;
        this.obsrvAnimated = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Axis#autoRangingProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindAutoRanging(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundAutoRanging = true;
        this.obsrvAutoRanging = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Axis#labelProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindLabel(javafx.beans.value.ObservableValue<? extends java.lang.String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundLabel = true;
        this.obsrvLabel = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Axis#sideProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindSide(javafx.beans.value.ObservableValue<? extends javafx.geometry.Side> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundSide = true;
        this.obsrvSide = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Axis#tickLabelFillProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindTickLabelFill(javafx.beans.value.ObservableValue<? extends javafx.scene.paint.Paint> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundTickLabelFill = true;
        this.obsrvTickLabelFill = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Axis#tickLabelFontProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindTickLabelFont(javafx.beans.value.ObservableValue<? extends javafx.scene.text.Font> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundTickLabelFont = true;
        this.obsrvTickLabelFont = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Axis#tickLabelGapProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindTickLabelGap(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundTickLabelGap = true;
        this.obsrvTickLabelGap = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Axis#tickLabelRotationProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindTickLabelRotation(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundTickLabelRotation = true;
        this.obsrvTickLabelRotation = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Axis#tickLabelsVisibleProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindTickLabelsVisible(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundTickLabelsVisible = true;
        this.obsrvTickLabelsVisible = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Axis#tickLengthProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindTickLength(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundTickLength = true;
        this.obsrvTickLength = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Axis#tickMarkVisibleProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindTickMarkVisible(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundTickMarkVisible = true;
        this.obsrvTickMarkVisible = source;
        return (B) this;
    }
}
