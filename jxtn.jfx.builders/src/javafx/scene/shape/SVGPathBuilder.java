// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.shape;

/**
 * {@link SVGPath}建構器
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class SVGPathBuilder<Z extends SVGPath, B extends SVGPathBuilder<Z, B>>
        extends javafx.scene.shape.ShapeBuilder<Z, B>
{

    protected boolean hasContent;
    protected java.lang.String valContent;

    protected boolean hasFillRule;
    protected javafx.scene.shape.FillRule valFillRule;

    protected boolean boundContent;
    protected javafx.beans.value.ObservableValue<? extends String> obsrvContent;

    protected boolean boundFillRule;
    protected javafx.beans.value.ObservableValue<? extends javafx.scene.shape.FillRule> obsrvFillRule;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasContent)
            instance.setContent(this.valContent);
        if (this.hasFillRule)
            instance.setFillRule(this.valFillRule);
        if (this.boundContent)
            instance.contentProperty().bind(this.obsrvContent);
        if (this.boundFillRule)
            instance.fillRuleProperty().bind(this.obsrvFillRule);
    }

    /**
     * 設定屬性{@link SVGPath#setContent(java.lang.String)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B content(java.lang.String value)
    {
        this.hasContent = true;
        this.valContent = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link SVGPath#setFillRule(javafx.scene.shape.FillRule)}
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
     * 設定屬性{@link SVGPath#contentProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindContent(javafx.beans.value.ObservableValue<? extends String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundContent = true;
        this.obsrvContent = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link SVGPath#fillRuleProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindFillRule(javafx.beans.value.ObservableValue<? extends javafx.scene.shape.FillRule> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundFillRule = true;
        this.obsrvFillRule = source;
        return (B) this;
    }

    /**
     * 建構{@link SVGPath}物件
     *
     * @return 新的{@link SVGPath}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public SVGPath build()
    {
        SVGPath instance = new SVGPath();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
