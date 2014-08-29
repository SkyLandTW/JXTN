// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.effect;

/**
 * {@link InnerShadow}建構器
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class InnerShadowBuilder<Z extends InnerShadow, B extends InnerShadowBuilder<Z, B>>
        extends javafx.scene.effect.EffectBuilder<Z, B>
{

    protected boolean hasBlurType;
    protected javafx.scene.effect.BlurType valBlurType;

    protected boolean hasChoke;
    protected double valChoke;

    protected boolean hasColor;
    protected javafx.scene.paint.Color valColor;

    protected boolean hasHeight;
    protected double valHeight;

    protected boolean hasInput;
    protected javafx.scene.effect.Effect valInput;

    protected boolean hasOffsetX;
    protected double valOffsetX;

    protected boolean hasOffsetY;
    protected double valOffsetY;

    protected boolean hasRadius;
    protected double valRadius;

    protected boolean hasWidth;
    protected double valWidth;

    protected boolean boundBlurType;
    protected javafx.beans.value.ObservableValue<? extends javafx.scene.effect.BlurType> obsrvBlurType;

    protected boolean boundChoke;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvChoke;

    protected boolean boundColor;
    protected javafx.beans.value.ObservableValue<? extends javafx.scene.paint.Color> obsrvColor;

    protected boolean boundHeight;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvHeight;

    protected boolean boundInput;
    protected javafx.beans.value.ObservableValue<? extends javafx.scene.effect.Effect> obsrvInput;

    protected boolean boundOffsetX;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvOffsetX;

    protected boolean boundOffsetY;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvOffsetY;

    protected boolean boundRadius;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvRadius;

    protected boolean boundWidth;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvWidth;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasBlurType)
            instance.setBlurType(this.valBlurType);
        if (this.hasChoke)
            instance.setChoke(this.valChoke);
        if (this.hasColor)
            instance.setColor(this.valColor);
        if (this.hasHeight)
            instance.setHeight(this.valHeight);
        if (this.hasInput)
            instance.setInput(this.valInput);
        if (this.hasOffsetX)
            instance.setOffsetX(this.valOffsetX);
        if (this.hasOffsetY)
            instance.setOffsetY(this.valOffsetY);
        if (this.hasRadius)
            instance.setRadius(this.valRadius);
        if (this.hasWidth)
            instance.setWidth(this.valWidth);
        if (this.boundBlurType)
            instance.blurTypeProperty().bind(this.obsrvBlurType);
        if (this.boundChoke)
            instance.chokeProperty().bind(this.obsrvChoke);
        if (this.boundColor)
            instance.colorProperty().bind(this.obsrvColor);
        if (this.boundHeight)
            instance.heightProperty().bind(this.obsrvHeight);
        if (this.boundInput)
            instance.inputProperty().bind(this.obsrvInput);
        if (this.boundOffsetX)
            instance.offsetXProperty().bind(this.obsrvOffsetX);
        if (this.boundOffsetY)
            instance.offsetYProperty().bind(this.obsrvOffsetY);
        if (this.boundRadius)
            instance.radiusProperty().bind(this.obsrvRadius);
        if (this.boundWidth)
            instance.widthProperty().bind(this.obsrvWidth);
    }

    /**
     * 設定屬性{@link InnerShadow#setBlurType(javafx.scene.effect.BlurType)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B blurType(javafx.scene.effect.BlurType value)
    {
        this.hasBlurType = true;
        this.valBlurType = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link InnerShadow#setChoke(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B choke(double value)
    {
        this.hasChoke = true;
        this.valChoke = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link InnerShadow#setColor(javafx.scene.paint.Color)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B color(javafx.scene.paint.Color value)
    {
        this.hasColor = true;
        this.valColor = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link InnerShadow#setHeight(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B height(double value)
    {
        this.hasHeight = true;
        this.valHeight = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link InnerShadow#setInput(javafx.scene.effect.Effect)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B input(javafx.scene.effect.Effect value)
    {
        this.hasInput = true;
        this.valInput = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link InnerShadow#setOffsetX(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B offsetX(double value)
    {
        this.hasOffsetX = true;
        this.valOffsetX = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link InnerShadow#setOffsetY(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B offsetY(double value)
    {
        this.hasOffsetY = true;
        this.valOffsetY = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link InnerShadow#setRadius(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B radius(double value)
    {
        this.hasRadius = true;
        this.valRadius = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link InnerShadow#setWidth(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B width(double value)
    {
        this.hasWidth = true;
        this.valWidth = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link InnerShadow#blurTypeProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBlurType(javafx.beans.value.ObservableValue<? extends javafx.scene.effect.BlurType> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundBlurType = true;
        this.obsrvBlurType = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link InnerShadow#chokeProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindChoke(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundChoke = true;
        this.obsrvChoke = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link InnerShadow#colorProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindColor(javafx.beans.value.ObservableValue<? extends javafx.scene.paint.Color> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundColor = true;
        this.obsrvColor = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link InnerShadow#heightProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindHeight(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundHeight = true;
        this.obsrvHeight = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link InnerShadow#inputProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindInput(javafx.beans.value.ObservableValue<? extends javafx.scene.effect.Effect> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundInput = true;
        this.obsrvInput = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link InnerShadow#offsetXProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOffsetX(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundOffsetX = true;
        this.obsrvOffsetX = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link InnerShadow#offsetYProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOffsetY(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundOffsetY = true;
        this.obsrvOffsetY = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link InnerShadow#radiusProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindRadius(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundRadius = true;
        this.obsrvRadius = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link InnerShadow#widthProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindWidth(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundWidth = true;
        this.obsrvWidth = source;
        return (B) this;
    }

    /**
     * 建構{@link InnerShadow}物件
     *
     * @return 新的{@link InnerShadow}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public InnerShadow build()
    {
        InnerShadow instance = new InnerShadow();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
