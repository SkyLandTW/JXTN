// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package org.controlsfx.control.table;

/**
 * {@link TableRowExpanderColumn.TableRowDataFeatures}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version controlsfx-8.40.14.jar
 * @param <Z> 要建構的物件型態(需繼承{@link TableRowExpanderColumn.TableRowDataFeatures})
 * @param <B> 建構器本身的型態(需繼承{@link TableRowExpanderColumn.TableRowDataFeaturesMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class TableRowExpanderColumn_TableRowDataFeaturesMaker<S extends java.lang.Object, Z extends TableRowExpanderColumn.TableRowDataFeatures<S>, B extends TableRowExpanderColumn_TableRowDataFeaturesMaker<S, Z, B>>
        extends jxtn.jfx.makers.AbstractMaker<Z, B>
        implements TableRowExpanderColumn_TableRowDataFeaturesMakerExt<S, Z, B>
{

    private boolean hasExpanded;
    private java.lang.Boolean valExpanded;

    private boolean bound1Expanded;
    private boolean bound2Expanded;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1Expanded;
    private javafx.beans.property.Property<Boolean> obsrv2Expanded;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasExpanded)
            instance.setExpanded(this.valExpanded);
        if (this.bound1Expanded)
            instance.expandedProperty().bind(this.obsrv1Expanded);
        if (this.bound2Expanded)
            instance.expandedProperty().bindBidirectional(this.obsrv2Expanded);
    }

    /**
     * 設定屬性{@link TableRowExpanderColumn.TableRowDataFeatures#setExpanded(java.lang.Boolean)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B expanded(java.lang.Boolean value)
    {
        this.hasExpanded = true;
        this.valExpanded = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableRowExpanderColumn.TableRowDataFeatures#expandedProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindExpanded(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Expanded = true;
        this.obsrv1Expanded = source;
        this.bound2Expanded = false;
        this.obsrv2Expanded = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableRowExpanderColumn.TableRowDataFeatures#expandedProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalExpanded(javafx.beans.property.Property<Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Expanded = false;
        this.obsrv1Expanded = null;
        this.bound2Expanded = true;
        this.obsrv2Expanded = source;
        return (B) this;
    }

    /**
     * 建構{@link TableRowExpanderColumn.TableRowDataFeatures}物件。
     *
     * @return 新的{@link TableRowExpanderColumn.TableRowDataFeatures}物件實體
     */
    @SuppressWarnings("unchecked")
    public TableRowExpanderColumn.TableRowDataFeatures<S> build(javafx.scene.control.TableRow<S> arg0, org.controlsfx.control.table.TableRowExpanderColumn<S> arg1, S arg2)
    {
        TableRowExpanderColumn.TableRowDataFeatures<S> instance = new TableRowExpanderColumn.TableRowDataFeatures<S>(arg0, arg1, arg2);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}