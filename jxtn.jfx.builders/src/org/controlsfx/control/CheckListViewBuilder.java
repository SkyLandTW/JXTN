// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package org.controlsfx.control;

/**
 * {@link CheckListView}建構器
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version controlsfx-8.0.6_20.jar
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class CheckListViewBuilder<T extends java.lang.Object, Z extends CheckListView<T>, B extends CheckListViewBuilder<T, Z, B>>
        extends javafx.scene.control.ListViewBuilder<T, Z, B>
{

    protected boolean hasCheckModel;
    protected javafx.scene.control.MultipleSelectionModel<T> valCheckModel;

    protected boolean boundCheckModel;
    protected javafx.beans.value.ObservableValue<? extends javafx.scene.control.MultipleSelectionModel<T>> obsrvCheckModel;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasCheckModel)
            instance.setCheckModel(this.valCheckModel);
        if (this.boundCheckModel)
            instance.checkModelProperty().bind(this.obsrvCheckModel);
    }

    /**
     * 設定屬性{@link CheckListView#setCheckModel(javafx.scene.control.MultipleSelectionModel)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B checkModel(javafx.scene.control.MultipleSelectionModel<T> value)
    {
        this.hasCheckModel = true;
        this.valCheckModel = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link CheckListView#checkModelProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindCheckModel(javafx.beans.value.ObservableValue<? extends javafx.scene.control.MultipleSelectionModel<T>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundCheckModel = true;
        this.obsrvCheckModel = source;
        return (B) this;
    }

    /**
     * 建構{@link CheckListView}物件
     *
     * @return 新的{@link CheckListView}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public CheckListView<T> build()
    {
        CheckListView<T> instance = new CheckListView<T>();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
