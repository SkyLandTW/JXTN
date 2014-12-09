// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.shape;

/**
 * {@link Path}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link Path})
 * @param <B> 建構器本身的型態(需繼承{@link PathBuilder})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class PathBuilder<Z extends Path, B extends PathBuilder<Z, B>>
        extends javafx.scene.shape.ShapeBuilder<Z, B>
        implements PathBuilderExt<Z, B>
{

    private boolean hasElements;
    private java.util.Collection<javafx.scene.shape.PathElement> valElements;

    private boolean hasFillRule;
    private javafx.scene.shape.FillRule valFillRule;

    private boolean bound1FillRule;
    private boolean bound2FillRule;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.shape.FillRule> obsrv1FillRule;
    private javafx.beans.property.Property<javafx.scene.shape.FillRule> obsrv2FillRule;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasElements)
            instance.getElements().addAll(this.valElements);
        if (this.hasFillRule)
            instance.setFillRule(this.valFillRule);
        if (this.bound1FillRule)
            instance.fillRuleProperty().bind(this.obsrv1FillRule);
        if (this.bound2FillRule)
            instance.fillRuleProperty().bindBidirectional(this.obsrv2FillRule);
    }

    /**
     * 設定集合屬性{@link Path#getElements}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     * @deprecated 屬性值並非{@link javafx.collections.ObservableList}
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public final B elements(java.util.Collection<? extends javafx.scene.shape.PathElement> value)
    {
        this.hasElements = true;
        this.valElements = (java.util.Collection<javafx.scene.shape.PathElement>) value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link Path#getElements}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B elements(javafx.scene.shape.PathElement... value)
    {
        this.hasElements = true;
        this.valElements = java.util.Arrays.asList(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link Path#getElements}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B elementsAdd(java.util.Collection<? extends javafx.scene.shape.PathElement> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasElements = true;
        if (this.valElements == null)
            this.valElements = new java.util.ArrayList<>(value.size());
        this.valElements.addAll(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link Path#getElements}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B elementsAdd(javafx.scene.shape.PathElement... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasElements = true;
        if (this.valElements == null)
            this.valElements = new java.util.ArrayList<>(value.length);
        this.valElements.addAll(java.util.Arrays.asList(value));
        return (B) this;
    }

    /**
     * 設定屬性{@link Path#setFillRule(javafx.scene.shape.FillRule)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B fillRule(javafx.scene.shape.FillRule value)
    {
        this.hasFillRule = true;
        this.valFillRule = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Path#fillRuleProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindFillRule(javafx.beans.value.ObservableValue<? extends javafx.scene.shape.FillRule> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1FillRule = true;
        this.obsrv1FillRule = source;
        this.bound2FillRule = false;
        this.obsrv2FillRule = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Path#fillRuleProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalFillRule(javafx.beans.property.Property<javafx.scene.shape.FillRule> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1FillRule = false;
        this.obsrv1FillRule = null;
        this.bound2FillRule = true;
        this.obsrv2FillRule = source;
        return (B) this;
    }

    /**
     * 建構{@link Path}物件。
     *
     * @return 新的{@link Path}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public Path build()
    {
        Path instance = new Path();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link Path}物件。
     *
     * @return 新的{@link Path}物件實體
     */
    @SuppressWarnings("unchecked")
    public Path build(java.util.Collection<? extends javafx.scene.shape.PathElement> arg0)
    {
        Path instance = new Path(arg0);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link Path}物件。
     *
     * @return 新的{@link Path}物件實體
     */
    @SuppressWarnings("unchecked")
    public Path build(javafx.scene.shape.PathElement[] arg0)
    {
        Path instance = new Path(arg0);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
