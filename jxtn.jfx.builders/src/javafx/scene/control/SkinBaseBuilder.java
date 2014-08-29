// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.control;

/**
 * {@link SkinBase}建構器
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class SkinBaseBuilder<C extends javafx.scene.control.Control, Z extends SkinBase<C>, B extends SkinBaseBuilder<C, Z, B>>
        extends jxtn.jfx.builders.AbstractBuilder<Z, B>
{

    protected boolean hasChildren;
    protected java.util.Collection<javafx.scene.Node> valChildren;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasChildren)
            instance.getChildren().setAll(this.valChildren);
    }

    /**
     * 設定集合屬性{@link SkinBase#getChildren}的內容
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B children(java.util.Collection<javafx.scene.Node> value)
    {
        this.hasChildren = true;
        this.valChildren = value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link SkinBase#getChildren}的內容
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B children(javafx.scene.Node... value)
    {
        this.hasChildren = true;
        this.valChildren = java.util.Arrays.asList(value);
        return (B) this;
    }
}
