// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.control;

/**
 * {@link TabPane}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link TabPane})
 * @param <B> 建構器本身的型態(需繼承{@link TabPaneMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class TabPaneMaker<Z extends TabPane, B extends TabPaneMaker<Z, B>>
        extends javafx.scene.control.ControlMaker<Z, B>
        implements TabPaneMakerExt<Z, B>
{

    private boolean hasRotateGraphic;
    private boolean valRotateGraphic;

    private boolean hasSelectionModel;
    private javafx.scene.control.SingleSelectionModel<javafx.scene.control.Tab> valSelectionModel;

    private boolean hasSide;
    private javafx.geometry.Side valSide;

    private boolean hasTabClosingPolicy;
    private javafx.scene.control.TabPane.TabClosingPolicy valTabClosingPolicy;

    private boolean hasTabMaxHeight;
    private double valTabMaxHeight;

    private boolean hasTabMaxWidth;
    private double valTabMaxWidth;

    private boolean hasTabMinHeight;
    private double valTabMinHeight;

    private boolean hasTabMinWidth;
    private double valTabMinWidth;

    private boolean hasTabs;
    private java.util.Collection<javafx.scene.control.Tab> valTabs;

    private boolean bound1RotateGraphic;
    private boolean bound2RotateGraphic;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1RotateGraphic;
    private javafx.beans.property.Property<Boolean> obsrv2RotateGraphic;

    private boolean bound1SelectionModel;
    private boolean bound2SelectionModel;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.control.SingleSelectionModel<javafx.scene.control.Tab>> obsrv1SelectionModel;
    private javafx.beans.property.Property<javafx.scene.control.SingleSelectionModel<javafx.scene.control.Tab>> obsrv2SelectionModel;

    private boolean bound1Side;
    private boolean bound2Side;
    private javafx.beans.value.ObservableValue<? extends javafx.geometry.Side> obsrv1Side;
    private javafx.beans.property.Property<javafx.geometry.Side> obsrv2Side;

    private boolean bound1TabClosingPolicy;
    private boolean bound2TabClosingPolicy;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.control.TabPane.TabClosingPolicy> obsrv1TabClosingPolicy;
    private javafx.beans.property.Property<javafx.scene.control.TabPane.TabClosingPolicy> obsrv2TabClosingPolicy;

    private boolean bound1TabMaxHeight;
    private boolean bound2TabMaxHeight;
    private javafx.beans.value.ObservableValue<? extends Number> obsrv1TabMaxHeight;
    private javafx.beans.property.Property<Number> obsrv2TabMaxHeight;

    private boolean bound1TabMaxWidth;
    private boolean bound2TabMaxWidth;
    private javafx.beans.value.ObservableValue<? extends Number> obsrv1TabMaxWidth;
    private javafx.beans.property.Property<Number> obsrv2TabMaxWidth;

    private boolean bound1TabMinHeight;
    private boolean bound2TabMinHeight;
    private javafx.beans.value.ObservableValue<? extends Number> obsrv1TabMinHeight;
    private javafx.beans.property.Property<Number> obsrv2TabMinHeight;

    private boolean bound1TabMinWidth;
    private boolean bound2TabMinWidth;
    private javafx.beans.value.ObservableValue<? extends Number> obsrv1TabMinWidth;
    private javafx.beans.property.Property<Number> obsrv2TabMinWidth;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasRotateGraphic)
            instance.setRotateGraphic(this.valRotateGraphic);
        if (this.hasSelectionModel)
            instance.setSelectionModel(this.valSelectionModel);
        if (this.hasSide)
            instance.setSide(this.valSide);
        if (this.hasTabClosingPolicy)
            instance.setTabClosingPolicy(this.valTabClosingPolicy);
        if (this.hasTabMaxHeight)
            instance.setTabMaxHeight(this.valTabMaxHeight);
        if (this.hasTabMaxWidth)
            instance.setTabMaxWidth(this.valTabMaxWidth);
        if (this.hasTabMinHeight)
            instance.setTabMinHeight(this.valTabMinHeight);
        if (this.hasTabMinWidth)
            instance.setTabMinWidth(this.valTabMinWidth);
        if (this.hasTabs)
            instance.getTabs().addAll(this.valTabs);
        if (this.bound1RotateGraphic)
            instance.rotateGraphicProperty().bind(this.obsrv1RotateGraphic);
        if (this.bound2RotateGraphic)
            instance.rotateGraphicProperty().bindBidirectional(this.obsrv2RotateGraphic);
        if (this.bound1SelectionModel)
            instance.selectionModelProperty().bind(this.obsrv1SelectionModel);
        if (this.bound2SelectionModel)
            instance.selectionModelProperty().bindBidirectional(this.obsrv2SelectionModel);
        if (this.bound1Side)
            instance.sideProperty().bind(this.obsrv1Side);
        if (this.bound2Side)
            instance.sideProperty().bindBidirectional(this.obsrv2Side);
        if (this.bound1TabClosingPolicy)
            instance.tabClosingPolicyProperty().bind(this.obsrv1TabClosingPolicy);
        if (this.bound2TabClosingPolicy)
            instance.tabClosingPolicyProperty().bindBidirectional(this.obsrv2TabClosingPolicy);
        if (this.bound1TabMaxHeight)
            instance.tabMaxHeightProperty().bind(this.obsrv1TabMaxHeight);
        if (this.bound2TabMaxHeight)
            instance.tabMaxHeightProperty().bindBidirectional(this.obsrv2TabMaxHeight);
        if (this.bound1TabMaxWidth)
            instance.tabMaxWidthProperty().bind(this.obsrv1TabMaxWidth);
        if (this.bound2TabMaxWidth)
            instance.tabMaxWidthProperty().bindBidirectional(this.obsrv2TabMaxWidth);
        if (this.bound1TabMinHeight)
            instance.tabMinHeightProperty().bind(this.obsrv1TabMinHeight);
        if (this.bound2TabMinHeight)
            instance.tabMinHeightProperty().bindBidirectional(this.obsrv2TabMinHeight);
        if (this.bound1TabMinWidth)
            instance.tabMinWidthProperty().bind(this.obsrv1TabMinWidth);
        if (this.bound2TabMinWidth)
            instance.tabMinWidthProperty().bindBidirectional(this.obsrv2TabMinWidth);
    }

    /**
     * 設定屬性{@link TabPane#setRotateGraphic(boolean)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B rotateGraphic(boolean value)
    {
        this.hasRotateGraphic = true;
        this.valRotateGraphic = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TabPane#setSelectionModel(javafx.scene.control.SingleSelectionModel)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B selectionModel(javafx.scene.control.SingleSelectionModel<javafx.scene.control.Tab> value)
    {
        this.hasSelectionModel = true;
        this.valSelectionModel = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TabPane#setSide(javafx.geometry.Side)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B side(javafx.geometry.Side value)
    {
        this.hasSide = true;
        this.valSide = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TabPane#setTabClosingPolicy(javafx.scene.control.TabPane.TabClosingPolicy)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B tabClosingPolicy(javafx.scene.control.TabPane.TabClosingPolicy value)
    {
        this.hasTabClosingPolicy = true;
        this.valTabClosingPolicy = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TabPane#setTabMaxHeight(double)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B tabMaxHeight(double value)
    {
        this.hasTabMaxHeight = true;
        this.valTabMaxHeight = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TabPane#setTabMaxWidth(double)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B tabMaxWidth(double value)
    {
        this.hasTabMaxWidth = true;
        this.valTabMaxWidth = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TabPane#setTabMinHeight(double)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B tabMinHeight(double value)
    {
        this.hasTabMinHeight = true;
        this.valTabMinHeight = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TabPane#setTabMinWidth(double)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B tabMinWidth(double value)
    {
        this.hasTabMinWidth = true;
        this.valTabMinWidth = value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link TabPane#getTabs}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     * @deprecated 屬性值並非{@link javafx.collections.ObservableList}
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public final B tabs(java.util.Collection<? extends javafx.scene.control.Tab> value)
    {
        this.hasTabs = true;
        this.valTabs = (java.util.Collection<javafx.scene.control.Tab>) value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link TabPane#getTabs}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B tabs(javafx.scene.control.Tab... value)
    {
        this.hasTabs = true;
        this.valTabs = java.util.Arrays.asList(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link TabPane#getTabs}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B tabsAdd(java.util.Collection<? extends javafx.scene.control.Tab> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasTabs = true;
        if (this.valTabs == null)
            this.valTabs = new java.util.ArrayList<>(value.size());
        this.valTabs.addAll(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link TabPane#getTabs}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B tabsAdd(javafx.scene.control.Tab... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasTabs = true;
        if (this.valTabs == null)
            this.valTabs = new java.util.ArrayList<>(value.length);
        this.valTabs.addAll(java.util.Arrays.asList(value));
        return (B) this;
    }

    /**
     * 增加集合屬性{@link TabPane#getTabs}的內容，排除null項目。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B tabsAddNonNull(java.util.Collection<? extends javafx.scene.control.Tab> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasTabs = true;
        if (this.valTabs == null)
            this.valTabs = new java.util.ArrayList<>(value.size());
        for (javafx.scene.control.Tab i : value)
            if (i != null)
                this.valTabs.add(i);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link TabPane#getTabs}的內容，排除null項目。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B tabsAddNonNull(javafx.scene.control.Tab... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasTabs = true;
        if (this.valTabs == null)
            this.valTabs = new java.util.ArrayList<>(value.length);
        for (javafx.scene.control.Tab i : value)
            if (i != null)
                this.valTabs.add(i);
        return (B) this;
    }

    /**
     * 設定屬性{@link TabPane#rotateGraphicProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindRotateGraphic(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1RotateGraphic = true;
        this.obsrv1RotateGraphic = source;
        this.bound2RotateGraphic = false;
        this.obsrv2RotateGraphic = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TabPane#rotateGraphicProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalRotateGraphic(javafx.beans.property.Property<Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1RotateGraphic = false;
        this.obsrv1RotateGraphic = null;
        this.bound2RotateGraphic = true;
        this.obsrv2RotateGraphic = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TabPane#selectionModelProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindSelectionModel(javafx.beans.value.ObservableValue<? extends javafx.scene.control.SingleSelectionModel<javafx.scene.control.Tab>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1SelectionModel = true;
        this.obsrv1SelectionModel = source;
        this.bound2SelectionModel = false;
        this.obsrv2SelectionModel = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TabPane#selectionModelProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalSelectionModel(javafx.beans.property.Property<javafx.scene.control.SingleSelectionModel<javafx.scene.control.Tab>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1SelectionModel = false;
        this.obsrv1SelectionModel = null;
        this.bound2SelectionModel = true;
        this.obsrv2SelectionModel = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TabPane#sideProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindSide(javafx.beans.value.ObservableValue<? extends javafx.geometry.Side> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Side = true;
        this.obsrv1Side = source;
        this.bound2Side = false;
        this.obsrv2Side = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TabPane#sideProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalSide(javafx.beans.property.Property<javafx.geometry.Side> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Side = false;
        this.obsrv1Side = null;
        this.bound2Side = true;
        this.obsrv2Side = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TabPane#tabClosingPolicyProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindTabClosingPolicy(javafx.beans.value.ObservableValue<? extends javafx.scene.control.TabPane.TabClosingPolicy> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1TabClosingPolicy = true;
        this.obsrv1TabClosingPolicy = source;
        this.bound2TabClosingPolicy = false;
        this.obsrv2TabClosingPolicy = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TabPane#tabClosingPolicyProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalTabClosingPolicy(javafx.beans.property.Property<javafx.scene.control.TabPane.TabClosingPolicy> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1TabClosingPolicy = false;
        this.obsrv1TabClosingPolicy = null;
        this.bound2TabClosingPolicy = true;
        this.obsrv2TabClosingPolicy = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TabPane#tabMaxHeightProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindTabMaxHeight(javafx.beans.value.ObservableValue<? extends Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1TabMaxHeight = true;
        this.obsrv1TabMaxHeight = source;
        this.bound2TabMaxHeight = false;
        this.obsrv2TabMaxHeight = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TabPane#tabMaxHeightProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalTabMaxHeight(javafx.beans.property.Property<Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1TabMaxHeight = false;
        this.obsrv1TabMaxHeight = null;
        this.bound2TabMaxHeight = true;
        this.obsrv2TabMaxHeight = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TabPane#tabMaxWidthProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindTabMaxWidth(javafx.beans.value.ObservableValue<? extends Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1TabMaxWidth = true;
        this.obsrv1TabMaxWidth = source;
        this.bound2TabMaxWidth = false;
        this.obsrv2TabMaxWidth = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TabPane#tabMaxWidthProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalTabMaxWidth(javafx.beans.property.Property<Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1TabMaxWidth = false;
        this.obsrv1TabMaxWidth = null;
        this.bound2TabMaxWidth = true;
        this.obsrv2TabMaxWidth = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TabPane#tabMinHeightProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindTabMinHeight(javafx.beans.value.ObservableValue<? extends Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1TabMinHeight = true;
        this.obsrv1TabMinHeight = source;
        this.bound2TabMinHeight = false;
        this.obsrv2TabMinHeight = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TabPane#tabMinHeightProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalTabMinHeight(javafx.beans.property.Property<Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1TabMinHeight = false;
        this.obsrv1TabMinHeight = null;
        this.bound2TabMinHeight = true;
        this.obsrv2TabMinHeight = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TabPane#tabMinWidthProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindTabMinWidth(javafx.beans.value.ObservableValue<? extends Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1TabMinWidth = true;
        this.obsrv1TabMinWidth = source;
        this.bound2TabMinWidth = false;
        this.obsrv2TabMinWidth = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TabPane#tabMinWidthProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalTabMinWidth(javafx.beans.property.Property<Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1TabMinWidth = false;
        this.obsrv1TabMinWidth = null;
        this.bound2TabMinWidth = true;
        this.obsrv2TabMinWidth = source;
        return (B) this;
    }

    /**
     * 建構{@link TabPane}物件。
     *
     * @return 新的{@link TabPane}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public TabPane build()
    {
        TabPane instance = new TabPane();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link TabPane}物件。
     *
     * @return 新的{@link TabPane}物件實體
     */
    @SuppressWarnings("unchecked")
    public TabPane build(javafx.scene.control.Tab[] arg0)
    {
        TabPane instance = new TabPane(arg0);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
