// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.layout;

/**
 * {@link GridPane}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link GridPane})
 * @param <B> 建構器本身的型態(需繼承{@link GridPaneMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class GridPaneMaker<Z extends GridPane, B extends GridPaneMaker<Z, B>>
        extends javafx.scene.layout.PaneMaker<Z, B>
        implements GridPaneMakerExt<Z, B>
{

    private boolean hasAlignment;
    private javafx.geometry.Pos valAlignment;

    private boolean hasColumnConstraints;
    private java.util.Collection<javafx.scene.layout.ColumnConstraints> valColumnConstraints;

    private boolean hasGridLinesVisible;
    private boolean valGridLinesVisible;

    private boolean hasHgap;
    private double valHgap;

    private boolean hasRowConstraints;
    private java.util.Collection<javafx.scene.layout.RowConstraints> valRowConstraints;

    private boolean hasVgap;
    private double valVgap;

    private boolean bound1Alignment;
    private boolean bound2Alignment;
    private javafx.beans.value.ObservableValue<? extends javafx.geometry.Pos> obsrv1Alignment;
    private javafx.beans.property.Property<javafx.geometry.Pos> obsrv2Alignment;

    private boolean bound1GridLinesVisible;
    private boolean bound2GridLinesVisible;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1GridLinesVisible;
    private javafx.beans.property.Property<Boolean> obsrv2GridLinesVisible;

    private boolean bound1Hgap;
    private boolean bound2Hgap;
    private javafx.beans.value.ObservableValue<? extends Number> obsrv1Hgap;
    private javafx.beans.property.Property<Number> obsrv2Hgap;

    private boolean bound1Vgap;
    private boolean bound2Vgap;
    private javafx.beans.value.ObservableValue<? extends Number> obsrv1Vgap;
    private javafx.beans.property.Property<Number> obsrv2Vgap;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasAlignment)
            instance.setAlignment(this.valAlignment);
        if (this.hasColumnConstraints)
            instance.getColumnConstraints().addAll(this.valColumnConstraints);
        if (this.hasGridLinesVisible)
            instance.setGridLinesVisible(this.valGridLinesVisible);
        if (this.hasHgap)
            instance.setHgap(this.valHgap);
        if (this.hasRowConstraints)
            instance.getRowConstraints().addAll(this.valRowConstraints);
        if (this.hasVgap)
            instance.setVgap(this.valVgap);
        if (this.bound1Alignment)
            instance.alignmentProperty().bind(this.obsrv1Alignment);
        if (this.bound2Alignment)
            instance.alignmentProperty().bindBidirectional(this.obsrv2Alignment);
        if (this.bound1GridLinesVisible)
            instance.gridLinesVisibleProperty().bind(this.obsrv1GridLinesVisible);
        if (this.bound2GridLinesVisible)
            instance.gridLinesVisibleProperty().bindBidirectional(this.obsrv2GridLinesVisible);
        if (this.bound1Hgap)
            instance.hgapProperty().bind(this.obsrv1Hgap);
        if (this.bound2Hgap)
            instance.hgapProperty().bindBidirectional(this.obsrv2Hgap);
        if (this.bound1Vgap)
            instance.vgapProperty().bind(this.obsrv1Vgap);
        if (this.bound2Vgap)
            instance.vgapProperty().bindBidirectional(this.obsrv2Vgap);
    }

    /**
     * 設定屬性{@link GridPane#setAlignment(javafx.geometry.Pos)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B alignment(javafx.geometry.Pos value)
    {
        this.hasAlignment = true;
        this.valAlignment = value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link GridPane#getColumnConstraints}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     * @deprecated 屬性值並非{@link javafx.collections.ObservableList}
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public final B columnConstraints(java.util.Collection<? extends javafx.scene.layout.ColumnConstraints> value)
    {
        this.hasColumnConstraints = true;
        this.valColumnConstraints = (java.util.Collection<javafx.scene.layout.ColumnConstraints>) value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link GridPane#getColumnConstraints}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B columnConstraints(javafx.scene.layout.ColumnConstraints... value)
    {
        this.hasColumnConstraints = true;
        this.valColumnConstraints = java.util.Arrays.asList(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link GridPane#getColumnConstraints}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B columnConstraintsAdd(java.util.Collection<? extends javafx.scene.layout.ColumnConstraints> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasColumnConstraints = true;
        if (this.valColumnConstraints == null)
            this.valColumnConstraints = new java.util.ArrayList<>(value.size());
        this.valColumnConstraints.addAll(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link GridPane#getColumnConstraints}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B columnConstraintsAdd(javafx.scene.layout.ColumnConstraints... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasColumnConstraints = true;
        if (this.valColumnConstraints == null)
            this.valColumnConstraints = new java.util.ArrayList<>(value.length);
        this.valColumnConstraints.addAll(java.util.Arrays.asList(value));
        return (B) this;
    }

    /**
     * 增加集合屬性{@link GridPane#getColumnConstraints}的內容，排除null項目。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B columnConstraintsAddNonNull(java.util.Collection<? extends javafx.scene.layout.ColumnConstraints> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasColumnConstraints = true;
        if (this.valColumnConstraints == null)
            this.valColumnConstraints = new java.util.ArrayList<>(value.size());
        for (javafx.scene.layout.ColumnConstraints i : value)
            if (i != null)
                this.valColumnConstraints.add(i);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link GridPane#getColumnConstraints}的內容，排除null項目。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B columnConstraintsAddNonNull(javafx.scene.layout.ColumnConstraints... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasColumnConstraints = true;
        if (this.valColumnConstraints == null)
            this.valColumnConstraints = new java.util.ArrayList<>(value.length);
        for (javafx.scene.layout.ColumnConstraints i : value)
            if (i != null)
                this.valColumnConstraints.add(i);
        return (B) this;
    }

    /**
     * 設定屬性{@link GridPane#setGridLinesVisible(boolean)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B gridLinesVisible(boolean value)
    {
        this.hasGridLinesVisible = true;
        this.valGridLinesVisible = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link GridPane#setHgap(double)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B hgap(double value)
    {
        this.hasHgap = true;
        this.valHgap = value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link GridPane#getRowConstraints}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     * @deprecated 屬性值並非{@link javafx.collections.ObservableList}
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public final B rowConstraints(java.util.Collection<? extends javafx.scene.layout.RowConstraints> value)
    {
        this.hasRowConstraints = true;
        this.valRowConstraints = (java.util.Collection<javafx.scene.layout.RowConstraints>) value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link GridPane#getRowConstraints}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B rowConstraints(javafx.scene.layout.RowConstraints... value)
    {
        this.hasRowConstraints = true;
        this.valRowConstraints = java.util.Arrays.asList(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link GridPane#getRowConstraints}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B rowConstraintsAdd(java.util.Collection<? extends javafx.scene.layout.RowConstraints> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasRowConstraints = true;
        if (this.valRowConstraints == null)
            this.valRowConstraints = new java.util.ArrayList<>(value.size());
        this.valRowConstraints.addAll(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link GridPane#getRowConstraints}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B rowConstraintsAdd(javafx.scene.layout.RowConstraints... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasRowConstraints = true;
        if (this.valRowConstraints == null)
            this.valRowConstraints = new java.util.ArrayList<>(value.length);
        this.valRowConstraints.addAll(java.util.Arrays.asList(value));
        return (B) this;
    }

    /**
     * 增加集合屬性{@link GridPane#getRowConstraints}的內容，排除null項目。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B rowConstraintsAddNonNull(java.util.Collection<? extends javafx.scene.layout.RowConstraints> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasRowConstraints = true;
        if (this.valRowConstraints == null)
            this.valRowConstraints = new java.util.ArrayList<>(value.size());
        for (javafx.scene.layout.RowConstraints i : value)
            if (i != null)
                this.valRowConstraints.add(i);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link GridPane#getRowConstraints}的內容，排除null項目。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B rowConstraintsAddNonNull(javafx.scene.layout.RowConstraints... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasRowConstraints = true;
        if (this.valRowConstraints == null)
            this.valRowConstraints = new java.util.ArrayList<>(value.length);
        for (javafx.scene.layout.RowConstraints i : value)
            if (i != null)
                this.valRowConstraints.add(i);
        return (B) this;
    }

    /**
     * 設定屬性{@link GridPane#setVgap(double)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B vgap(double value)
    {
        this.hasVgap = true;
        this.valVgap = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link GridPane#alignmentProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindAlignment(javafx.beans.value.ObservableValue<? extends javafx.geometry.Pos> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Alignment = true;
        this.obsrv1Alignment = source;
        this.bound2Alignment = false;
        this.obsrv2Alignment = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link GridPane#alignmentProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalAlignment(javafx.beans.property.Property<javafx.geometry.Pos> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Alignment = false;
        this.obsrv1Alignment = null;
        this.bound2Alignment = true;
        this.obsrv2Alignment = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link GridPane#gridLinesVisibleProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindGridLinesVisible(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1GridLinesVisible = true;
        this.obsrv1GridLinesVisible = source;
        this.bound2GridLinesVisible = false;
        this.obsrv2GridLinesVisible = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link GridPane#gridLinesVisibleProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalGridLinesVisible(javafx.beans.property.Property<Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1GridLinesVisible = false;
        this.obsrv1GridLinesVisible = null;
        this.bound2GridLinesVisible = true;
        this.obsrv2GridLinesVisible = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link GridPane#hgapProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindHgap(javafx.beans.value.ObservableValue<? extends Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Hgap = true;
        this.obsrv1Hgap = source;
        this.bound2Hgap = false;
        this.obsrv2Hgap = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link GridPane#hgapProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalHgap(javafx.beans.property.Property<Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Hgap = false;
        this.obsrv1Hgap = null;
        this.bound2Hgap = true;
        this.obsrv2Hgap = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link GridPane#vgapProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindVgap(javafx.beans.value.ObservableValue<? extends Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Vgap = true;
        this.obsrv1Vgap = source;
        this.bound2Vgap = false;
        this.obsrv2Vgap = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link GridPane#vgapProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalVgap(javafx.beans.property.Property<Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Vgap = false;
        this.obsrv1Vgap = null;
        this.bound2Vgap = true;
        this.obsrv2Vgap = source;
        return (B) this;
    }

    /**
     * 建構{@link GridPane}物件。
     *
     * @return 新的{@link GridPane}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public GridPane build()
    {
        GridPane instance = new GridPane();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
