// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.control;

/**
 * {@link Cell}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link Cell})
 * @param <B> 建構器本身的型態(需繼承{@link CellMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class CellMaker<T extends java.lang.Object, Z extends Cell<T>, B extends CellMaker<T, Z, B>>
        extends javafx.scene.control.LabeledMaker<Z, B>
        implements CellMakerExt<T, Z, B>
{

    private boolean hasEditable;
    private boolean valEditable;

    private boolean hasItem;
    private T valItem;

    private boolean bound1Editable;
    private boolean bound2Editable;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1Editable;
    private javafx.beans.property.Property<Boolean> obsrv2Editable;

    private boolean bound1Item;
    private boolean bound2Item;
    private javafx.beans.value.ObservableValue<? extends T> obsrv1Item;
    private javafx.beans.property.Property<T> obsrv2Item;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasEditable)
            instance.setEditable(this.valEditable);
        if (this.hasItem)
            instance.setItem(this.valItem);
        if (this.bound1Editable)
            instance.editableProperty().bind(this.obsrv1Editable);
        if (this.bound2Editable)
            instance.editableProperty().bindBidirectional(this.obsrv2Editable);
        if (this.bound1Item)
            instance.itemProperty().bind(this.obsrv1Item);
        if (this.bound2Item)
            instance.itemProperty().bindBidirectional(this.obsrv2Item);
    }

    /**
     * 設定屬性{@link Cell#setEditable(boolean)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B editable(boolean value)
    {
        this.hasEditable = true;
        this.valEditable = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Cell#setItem(T)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B item(T value)
    {
        this.hasItem = true;
        this.valItem = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Cell#editableProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindEditable(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Editable = true;
        this.obsrv1Editable = source;
        this.bound2Editable = false;
        this.obsrv2Editable = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Cell#editableProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalEditable(javafx.beans.property.Property<Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Editable = false;
        this.obsrv1Editable = null;
        this.bound2Editable = true;
        this.obsrv2Editable = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Cell#itemProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindItem(javafx.beans.value.ObservableValue<? extends T> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Item = true;
        this.obsrv1Item = source;
        this.bound2Item = false;
        this.obsrv2Item = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Cell#itemProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalItem(javafx.beans.property.Property<T> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Item = false;
        this.obsrv1Item = null;
        this.bound2Item = true;
        this.obsrv2Item = source;
        return (B) this;
    }

    /**
     * 建構{@link Cell}物件。
     *
     * @return 新的{@link Cell}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public Cell<T> build()
    {
        Cell<T> instance = new Cell<T>();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
