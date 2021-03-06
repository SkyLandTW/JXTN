// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.transform;

/**
 * {@link Transform}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link Transform})
 * @param <B> 建構器本身的型態(需繼承{@link TransformMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class TransformMaker<Z extends Transform, B extends TransformMaker<Z, B>>
        extends jxtn.jfx.makers.AbstractMaker<Z, B>
        implements TransformMakerExt<Z, B>
{

    private boolean hasOnTransformChanged;
    private javafx.event.EventHandler<? super javafx.scene.transform.TransformChangedEvent> valOnTransformChanged;

    private boolean bound1OnTransformChanged;
    private boolean bound2OnTransformChanged;
    private javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<? super javafx.scene.transform.TransformChangedEvent>> obsrv1OnTransformChanged;
    private javafx.beans.property.Property<javafx.event.EventHandler<? super javafx.scene.transform.TransformChangedEvent>> obsrv2OnTransformChanged;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasOnTransformChanged)
            instance.setOnTransformChanged(this.valOnTransformChanged);
        if (this.bound1OnTransformChanged)
            instance.onTransformChangedProperty().bind(this.obsrv1OnTransformChanged);
        if (this.bound2OnTransformChanged)
            instance.onTransformChangedProperty().bindBidirectional(this.obsrv2OnTransformChanged);
    }

    /**
     * 設定屬性{@link Transform#setOnTransformChanged(javafx.event.EventHandler)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B onTransformChanged(javafx.event.EventHandler<? super javafx.scene.transform.TransformChangedEvent> value)
    {
        this.hasOnTransformChanged = true;
        this.valOnTransformChanged = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Transform#onTransformChangedProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOnTransformChanged(javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<? super javafx.scene.transform.TransformChangedEvent>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnTransformChanged = true;
        this.obsrv1OnTransformChanged = source;
        this.bound2OnTransformChanged = false;
        this.obsrv2OnTransformChanged = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Transform#onTransformChangedProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalOnTransformChanged(javafx.beans.property.Property<javafx.event.EventHandler<? super javafx.scene.transform.TransformChangedEvent>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnTransformChanged = false;
        this.obsrv1OnTransformChanged = null;
        this.bound2OnTransformChanged = true;
        this.obsrv2OnTransformChanged = source;
        return (B) this;
    }
}
