// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.control;

/**
 * {@link ToolBar}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link ToolBar})
 * @param <B> 建構器本身的型態(需繼承{@link ToolBarMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class ToolBarMaker<Z extends ToolBar, B extends ToolBarMaker<Z, B>>
        extends javafx.scene.control.ControlMaker<Z, B>
        implements ToolBarMakerExt<Z, B>
{

    private boolean hasItems;
    private java.util.Collection<javafx.scene.Node> valItems;

    private boolean hasOrientation;
    private javafx.geometry.Orientation valOrientation;

    private boolean bound1Orientation;
    private boolean bound2Orientation;
    private javafx.beans.value.ObservableValue<? extends javafx.geometry.Orientation> obsrv1Orientation;
    private javafx.beans.property.Property<javafx.geometry.Orientation> obsrv2Orientation;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasItems)
            instance.getItems().addAll(this.valItems);
        if (this.hasOrientation)
            instance.setOrientation(this.valOrientation);
        if (this.bound1Orientation)
            instance.orientationProperty().bind(this.obsrv1Orientation);
        if (this.bound2Orientation)
            instance.orientationProperty().bindBidirectional(this.obsrv2Orientation);
    }

    /**
     * 設定集合屬性{@link ToolBar#getItems}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     * @deprecated 屬性值並非{@link javafx.collections.ObservableList}
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public final B items(java.util.Collection<? extends javafx.scene.Node> value)
    {
        this.hasItems = true;
        this.valItems = (java.util.Collection<javafx.scene.Node>) value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link ToolBar#getItems}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B items(javafx.scene.Node... value)
    {
        this.hasItems = true;
        this.valItems = java.util.Arrays.asList(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link ToolBar#getItems}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B itemsAdd(java.util.Collection<? extends javafx.scene.Node> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasItems = true;
        if (this.valItems == null)
            this.valItems = new java.util.ArrayList<>(value.size());
        this.valItems.addAll(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link ToolBar#getItems}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B itemsAdd(javafx.scene.Node... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasItems = true;
        if (this.valItems == null)
            this.valItems = new java.util.ArrayList<>(value.length);
        this.valItems.addAll(java.util.Arrays.asList(value));
        return (B) this;
    }

    /**
     * 增加集合屬性{@link ToolBar#getItems}的內容，排除null項目。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B itemsAddNonNull(java.util.Collection<? extends javafx.scene.Node> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasItems = true;
        if (this.valItems == null)
            this.valItems = new java.util.ArrayList<>(value.size());
        for (javafx.scene.Node i : value)
            if (i != null)
                this.valItems.add(i);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link ToolBar#getItems}的內容，排除null項目。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B itemsAddNonNull(javafx.scene.Node... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasItems = true;
        if (this.valItems == null)
            this.valItems = new java.util.ArrayList<>(value.length);
        for (javafx.scene.Node i : value)
            if (i != null)
                this.valItems.add(i);
        return (B) this;
    }

    /**
     * 設定屬性{@link ToolBar#setOrientation(javafx.geometry.Orientation)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B orientation(javafx.geometry.Orientation value)
    {
        this.hasOrientation = true;
        this.valOrientation = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ToolBar#orientationProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOrientation(javafx.beans.value.ObservableValue<? extends javafx.geometry.Orientation> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Orientation = true;
        this.obsrv1Orientation = source;
        this.bound2Orientation = false;
        this.obsrv2Orientation = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link ToolBar#orientationProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalOrientation(javafx.beans.property.Property<javafx.geometry.Orientation> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Orientation = false;
        this.obsrv1Orientation = null;
        this.bound2Orientation = true;
        this.obsrv2Orientation = source;
        return (B) this;
    }

    /**
     * 建構{@link ToolBar}物件。
     *
     * @return 新的{@link ToolBar}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public ToolBar build()
    {
        ToolBar instance = new ToolBar();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link ToolBar}物件。
     *
     * @return 新的{@link ToolBar}物件實體
     */
    @SuppressWarnings("unchecked")
    public ToolBar build(javafx.scene.Node[] arg0)
    {
        ToolBar instance = new ToolBar(arg0);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}