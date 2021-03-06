// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.chart;

/**
 * {@link XYChart.Data}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link XYChart.Data})
 * @param <B> 建構器本身的型態(需繼承{@link XYChart.DataMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class XYChart_DataMaker<X extends java.lang.Object, Y extends java.lang.Object, Z extends XYChart.Data<X, Y>, B extends XYChart_DataMaker<X, Y, Z, B>>
        extends jxtn.jfx.makers.AbstractMaker<Z, B>
        implements XYChart_DataMakerExt<X, Y, Z, B>
{

    private boolean hasExtraValue;
    private java.lang.Object valExtraValue;

    private boolean hasNode;
    private javafx.scene.Node valNode;

    private boolean hasXValue;
    private X valXValue;

    private boolean hasYValue;
    private Y valYValue;

    private boolean bound1XValue;
    private boolean bound2XValue;
    private javafx.beans.value.ObservableValue<? extends X> obsrv1XValue;
    private javafx.beans.property.Property<X> obsrv2XValue;

    private boolean bound1YValue;
    private boolean bound2YValue;
    private javafx.beans.value.ObservableValue<? extends Y> obsrv1YValue;
    private javafx.beans.property.Property<Y> obsrv2YValue;

    private boolean bound1ExtraValue;
    private boolean bound2ExtraValue;
    private javafx.beans.value.ObservableValue<? extends java.lang.Object> obsrv1ExtraValue;
    private javafx.beans.property.Property<java.lang.Object> obsrv2ExtraValue;

    private boolean bound1Node;
    private boolean bound2Node;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.Node> obsrv1Node;
    private javafx.beans.property.Property<javafx.scene.Node> obsrv2Node;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasExtraValue)
            instance.setExtraValue(this.valExtraValue);
        if (this.hasNode)
            instance.setNode(this.valNode);
        if (this.hasXValue)
            instance.setXValue(this.valXValue);
        if (this.hasYValue)
            instance.setYValue(this.valYValue);
        if (this.bound1XValue)
            instance.XValueProperty().bind(this.obsrv1XValue);
        if (this.bound2XValue)
            instance.XValueProperty().bindBidirectional(this.obsrv2XValue);
        if (this.bound1YValue)
            instance.YValueProperty().bind(this.obsrv1YValue);
        if (this.bound2YValue)
            instance.YValueProperty().bindBidirectional(this.obsrv2YValue);
        if (this.bound1ExtraValue)
            instance.extraValueProperty().bind(this.obsrv1ExtraValue);
        if (this.bound2ExtraValue)
            instance.extraValueProperty().bindBidirectional(this.obsrv2ExtraValue);
        if (this.bound1Node)
            instance.nodeProperty().bind(this.obsrv1Node);
        if (this.bound2Node)
            instance.nodeProperty().bindBidirectional(this.obsrv2Node);
    }

    /**
     * 設定屬性{@link XYChart.Data#setExtraValue(java.lang.Object)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B extraValue(java.lang.Object value)
    {
        this.hasExtraValue = true;
        this.valExtraValue = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link XYChart.Data#setNode(javafx.scene.Node)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B node(javafx.scene.Node value)
    {
        this.hasNode = true;
        this.valNode = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link XYChart.Data#setXValue(X)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B xValue(X value)
    {
        this.hasXValue = true;
        this.valXValue = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link XYChart.Data#setYValue(Y)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B yValue(Y value)
    {
        this.hasYValue = true;
        this.valYValue = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link XYChart.Data#XValueProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindXValue(javafx.beans.value.ObservableValue<? extends X> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1XValue = true;
        this.obsrv1XValue = source;
        this.bound2XValue = false;
        this.obsrv2XValue = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link XYChart.Data#XValueProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalXValue(javafx.beans.property.Property<X> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1XValue = false;
        this.obsrv1XValue = null;
        this.bound2XValue = true;
        this.obsrv2XValue = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link XYChart.Data#YValueProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindYValue(javafx.beans.value.ObservableValue<? extends Y> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1YValue = true;
        this.obsrv1YValue = source;
        this.bound2YValue = false;
        this.obsrv2YValue = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link XYChart.Data#YValueProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalYValue(javafx.beans.property.Property<Y> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1YValue = false;
        this.obsrv1YValue = null;
        this.bound2YValue = true;
        this.obsrv2YValue = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link XYChart.Data#extraValueProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindExtraValue(javafx.beans.value.ObservableValue<? extends java.lang.Object> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1ExtraValue = true;
        this.obsrv1ExtraValue = source;
        this.bound2ExtraValue = false;
        this.obsrv2ExtraValue = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link XYChart.Data#extraValueProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalExtraValue(javafx.beans.property.Property<java.lang.Object> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1ExtraValue = false;
        this.obsrv1ExtraValue = null;
        this.bound2ExtraValue = true;
        this.obsrv2ExtraValue = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link XYChart.Data#nodeProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindNode(javafx.beans.value.ObservableValue<? extends javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Node = true;
        this.obsrv1Node = source;
        this.bound2Node = false;
        this.obsrv2Node = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link XYChart.Data#nodeProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalNode(javafx.beans.property.Property<javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Node = false;
        this.obsrv1Node = null;
        this.bound2Node = true;
        this.obsrv2Node = source;
        return (B) this;
    }

    /**
     * 建構{@link XYChart.Data}物件。
     *
     * @return 新的{@link XYChart.Data}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public XYChart.Data<X, Y> build()
    {
        XYChart.Data<X, Y> instance = new XYChart.Data<X, Y>();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link XYChart.Data}物件。
     *
     * @return 新的{@link XYChart.Data}物件實體
     */
    @SuppressWarnings("unchecked")
    public XYChart.Data<X, Y> build(X arg0, Y arg1)
    {
        XYChart.Data<X, Y> instance = new XYChart.Data<X, Y>(arg0, arg1);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link XYChart.Data}物件。
     *
     * @return 新的{@link XYChart.Data}物件實體
     */
    @SuppressWarnings("unchecked")
    public XYChart.Data<X, Y> build(X arg0, Y arg1, java.lang.Object arg2)
    {
        XYChart.Data<X, Y> instance = new XYChart.Data<X, Y>(arg0, arg1, arg2);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
