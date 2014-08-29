// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.effect;

/**
 * {@link Light}建構器
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class LightBuilder<Z extends Light, B extends LightBuilder<Z, B>>
        extends jxtn.jfx.builders.AbstractBuilder<Z, B>
{

    protected boolean hasColor;
    protected javafx.scene.paint.Color valColor;

    protected boolean boundColor;
    protected javafx.beans.value.ObservableValue<? extends javafx.scene.paint.Color> obsrvColor;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasColor)
            instance.setColor(this.valColor);
        if (this.boundColor)
            instance.colorProperty().bind(this.obsrvColor);
    }

    /**
     * 設定屬性{@link Light#setColor(javafx.scene.paint.Color)}
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
     * 設定屬性{@link Light#colorProperty}的連結
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
}
