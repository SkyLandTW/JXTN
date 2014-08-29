// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene;

/**
 * {@link LightBase}建構器
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class LightBaseBuilder<Z extends LightBase, B extends LightBaseBuilder<Z, B>>
        extends javafx.scene.NodeBuilder<Z, B>
{

    protected boolean hasColor;
    protected javafx.scene.paint.Color valColor;

    protected boolean hasLightOn;
    protected boolean valLightOn;

    protected boolean hasScope;
    protected java.util.Collection<javafx.scene.Node> valScope;

    protected boolean boundColor;
    protected javafx.beans.value.ObservableValue<? extends javafx.scene.paint.Color> obsrvColor;

    protected boolean boundLightOn;
    protected javafx.beans.value.ObservableValue<? extends Boolean> obsrvLightOn;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasColor)
            instance.setColor(this.valColor);
        if (this.hasLightOn)
            instance.setLightOn(this.valLightOn);
        if (this.hasScope)
            instance.getScope().setAll(this.valScope);
        if (this.boundColor)
            instance.colorProperty().bind(this.obsrvColor);
        if (this.boundLightOn)
            instance.lightOnProperty().bind(this.obsrvLightOn);
    }

    /**
     * 設定屬性{@link LightBase#setColor(javafx.scene.paint.Color)}
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
     * 設定屬性{@link LightBase#setLightOn(boolean)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B lightOn(boolean value)
    {
        this.hasLightOn = true;
        this.valLightOn = value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link LightBase#getScope}的內容
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B scope(java.util.Collection<javafx.scene.Node> value)
    {
        this.hasScope = true;
        this.valScope = value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link LightBase#getScope}的內容
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B scope(javafx.scene.Node... value)
    {
        this.hasScope = true;
        this.valScope = java.util.Arrays.asList(value);
        return (B) this;
    }

    /**
     * 設定屬性{@link LightBase#colorProperty}的連結
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
     * 設定屬性{@link LightBase#lightOnProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindLightOn(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundLightOn = true;
        this.obsrvLightOn = source;
        return (B) this;
    }
}
