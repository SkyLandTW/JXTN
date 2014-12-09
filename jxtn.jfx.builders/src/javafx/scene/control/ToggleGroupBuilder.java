// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.control;

/**
 * {@link ToggleGroup}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link ToggleGroup})
 * @param <B> 建構器本身的型態(需繼承{@link ToggleGroupBuilder})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class ToggleGroupBuilder<Z extends ToggleGroup, B extends ToggleGroupBuilder<Z, B>>
        extends jxtn.jfx.builders.AbstractBuilder<Z, B>
        implements ToggleGroupBuilderExt<Z, B>
{

    private boolean hasToggles;
    private java.util.Collection<javafx.scene.control.Toggle> valToggles;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasToggles)
            instance.getToggles().addAll(this.valToggles);
    }

    /**
     * 設定集合屬性{@link ToggleGroup#getToggles}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     * @deprecated 屬性值並非{@link javafx.collections.ObservableList}
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public final B toggles(java.util.Collection<? extends javafx.scene.control.Toggle> value)
    {
        this.hasToggles = true;
        this.valToggles = (java.util.Collection<javafx.scene.control.Toggle>) value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link ToggleGroup#getToggles}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B toggles(javafx.scene.control.Toggle... value)
    {
        this.hasToggles = true;
        this.valToggles = java.util.Arrays.asList(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link ToggleGroup#getToggles}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B togglesAdd(java.util.Collection<? extends javafx.scene.control.Toggle> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasToggles = true;
        if (this.valToggles == null)
            this.valToggles = new java.util.ArrayList<>(value.size());
        this.valToggles.addAll(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link ToggleGroup#getToggles}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B togglesAdd(javafx.scene.control.Toggle... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasToggles = true;
        if (this.valToggles == null)
            this.valToggles = new java.util.ArrayList<>(value.length);
        this.valToggles.addAll(java.util.Arrays.asList(value));
        return (B) this;
    }

    /**
     * 建構{@link ToggleGroup}物件。
     *
     * @return 新的{@link ToggleGroup}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public ToggleGroup build()
    {
        ToggleGroup instance = new ToggleGroup();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
