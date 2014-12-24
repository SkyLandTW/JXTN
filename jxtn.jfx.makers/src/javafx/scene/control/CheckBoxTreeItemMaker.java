// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.control;

/**
 * {@link CheckBoxTreeItem}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link CheckBoxTreeItem})
 * @param <B> 建構器本身的型態(需繼承{@link CheckBoxTreeItemMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class CheckBoxTreeItemMaker<T extends java.lang.Object, Z extends CheckBoxTreeItem<T>, B extends CheckBoxTreeItemMaker<T, Z, B>>
        extends javafx.scene.control.TreeItemMaker<T, Z, B>
        implements CheckBoxTreeItemMakerExt<T, Z, B>
{

    private boolean hasIndependent;
    private boolean valIndependent;

    private boolean hasIndeterminate;
    private boolean valIndeterminate;

    private boolean hasSelected;
    private boolean valSelected;

    private boolean bound1Independent;
    private boolean bound2Independent;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1Independent;
    private javafx.beans.property.Property<Boolean> obsrv2Independent;

    private boolean bound1Indeterminate;
    private boolean bound2Indeterminate;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1Indeterminate;
    private javafx.beans.property.Property<Boolean> obsrv2Indeterminate;

    private boolean bound1Selected;
    private boolean bound2Selected;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1Selected;
    private javafx.beans.property.Property<Boolean> obsrv2Selected;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasIndependent)
            instance.setIndependent(this.valIndependent);
        if (this.hasIndeterminate)
            instance.setIndeterminate(this.valIndeterminate);
        if (this.hasSelected)
            instance.setSelected(this.valSelected);
        if (this.bound1Independent)
            instance.independentProperty().bind(this.obsrv1Independent);
        if (this.bound2Independent)
            instance.independentProperty().bindBidirectional(this.obsrv2Independent);
        if (this.bound1Indeterminate)
            instance.indeterminateProperty().bind(this.obsrv1Indeterminate);
        if (this.bound2Indeterminate)
            instance.indeterminateProperty().bindBidirectional(this.obsrv2Indeterminate);
        if (this.bound1Selected)
            instance.selectedProperty().bind(this.obsrv1Selected);
        if (this.bound2Selected)
            instance.selectedProperty().bindBidirectional(this.obsrv2Selected);
    }

    /**
     * 設定屬性{@link CheckBoxTreeItem#setIndependent(boolean)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B independent(boolean value)
    {
        this.hasIndependent = true;
        this.valIndependent = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link CheckBoxTreeItem#setIndeterminate(boolean)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B indeterminate(boolean value)
    {
        this.hasIndeterminate = true;
        this.valIndeterminate = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link CheckBoxTreeItem#setSelected(boolean)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B selected(boolean value)
    {
        this.hasSelected = true;
        this.valSelected = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link CheckBoxTreeItem#independentProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindIndependent(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Independent = true;
        this.obsrv1Independent = source;
        this.bound2Independent = false;
        this.obsrv2Independent = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link CheckBoxTreeItem#independentProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalIndependent(javafx.beans.property.Property<Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Independent = false;
        this.obsrv1Independent = null;
        this.bound2Independent = true;
        this.obsrv2Independent = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link CheckBoxTreeItem#indeterminateProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindIndeterminate(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Indeterminate = true;
        this.obsrv1Indeterminate = source;
        this.bound2Indeterminate = false;
        this.obsrv2Indeterminate = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link CheckBoxTreeItem#indeterminateProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalIndeterminate(javafx.beans.property.Property<Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Indeterminate = false;
        this.obsrv1Indeterminate = null;
        this.bound2Indeterminate = true;
        this.obsrv2Indeterminate = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link CheckBoxTreeItem#selectedProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindSelected(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Selected = true;
        this.obsrv1Selected = source;
        this.bound2Selected = false;
        this.obsrv2Selected = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link CheckBoxTreeItem#selectedProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalSelected(javafx.beans.property.Property<Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Selected = false;
        this.obsrv1Selected = null;
        this.bound2Selected = true;
        this.obsrv2Selected = source;
        return (B) this;
    }

    /**
     * 建構{@link CheckBoxTreeItem}物件。
     *
     * @return 新的{@link CheckBoxTreeItem}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public CheckBoxTreeItem<T> build()
    {
        CheckBoxTreeItem<T> instance = new CheckBoxTreeItem<T>();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link CheckBoxTreeItem}物件。
     *
     * @return 新的{@link CheckBoxTreeItem}物件實體
     */
    @SuppressWarnings("unchecked")
    public CheckBoxTreeItem<T> build(T arg0)
    {
        CheckBoxTreeItem<T> instance = new CheckBoxTreeItem<T>(arg0);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link CheckBoxTreeItem}物件。
     *
     * @return 新的{@link CheckBoxTreeItem}物件實體
     */
    @SuppressWarnings("unchecked")
    public CheckBoxTreeItem<T> build(T arg0, javafx.scene.Node arg1)
    {
        CheckBoxTreeItem<T> instance = new CheckBoxTreeItem<T>(arg0, arg1);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link CheckBoxTreeItem}物件。
     *
     * @return 新的{@link CheckBoxTreeItem}物件實體
     */
    @SuppressWarnings("unchecked")
    public CheckBoxTreeItem<T> build(T arg0, javafx.scene.Node arg1, boolean arg2)
    {
        CheckBoxTreeItem<T> instance = new CheckBoxTreeItem<T>(arg0, arg1, arg2);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link CheckBoxTreeItem}物件。
     *
     * @return 新的{@link CheckBoxTreeItem}物件實體
     */
    @SuppressWarnings("unchecked")
    public CheckBoxTreeItem<T> build(T arg0, javafx.scene.Node arg1, boolean arg2, boolean arg3)
    {
        CheckBoxTreeItem<T> instance = new CheckBoxTreeItem<T>(arg0, arg1, arg2, arg3);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}