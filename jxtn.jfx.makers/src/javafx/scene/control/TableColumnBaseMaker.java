// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.control;

/**
 * {@link TableColumnBase}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link TableColumnBase})
 * @param <B> 建構器本身的型態(需繼承{@link TableColumnBaseMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class TableColumnBaseMaker<S extends java.lang.Object, T extends java.lang.Object, Z extends TableColumnBase<S, T>, B extends TableColumnBaseMaker<S, T, Z, B>>
        extends jxtn.jfx.makers.AbstractMaker<Z, B>
        implements TableColumnBaseMakerExt<S, T, Z, B>
{

    private boolean hasComparator;
    private java.util.Comparator<T> valComparator;

    private boolean hasContextMenu;
    private javafx.scene.control.ContextMenu valContextMenu;

    private boolean hasEditable;
    private boolean valEditable;

    private boolean hasGraphic;
    private javafx.scene.Node valGraphic;

    private boolean hasId;
    private java.lang.String valId;

    private boolean hasMaxWidth;
    private double valMaxWidth;

    private boolean hasMinWidth;
    private double valMinWidth;

    private boolean hasPrefWidth;
    private double valPrefWidth;

    private boolean hasResizable;
    private boolean valResizable;

    private boolean hasSortNode;
    private javafx.scene.Node valSortNode;

    private boolean hasSortable;
    private boolean valSortable;

    private boolean hasStyle;
    private java.lang.String valStyle;

    private boolean hasStyleClass;
    private java.util.Collection<java.lang.String> valStyleClass;

    private boolean hasText;
    private java.lang.String valText;

    private boolean hasUserData;
    private java.lang.Object valUserData;

    private boolean hasVisible;
    private boolean valVisible;

    private boolean bound1Comparator;
    private boolean bound2Comparator;
    private javafx.beans.value.ObservableValue<? extends java.util.Comparator<T>> obsrv1Comparator;
    private javafx.beans.property.Property<java.util.Comparator<T>> obsrv2Comparator;

    private boolean bound1ContextMenu;
    private boolean bound2ContextMenu;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.control.ContextMenu> obsrv1ContextMenu;
    private javafx.beans.property.Property<javafx.scene.control.ContextMenu> obsrv2ContextMenu;

    private boolean bound1Editable;
    private boolean bound2Editable;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1Editable;
    private javafx.beans.property.Property<Boolean> obsrv2Editable;

    private boolean bound1Graphic;
    private boolean bound2Graphic;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.Node> obsrv1Graphic;
    private javafx.beans.property.Property<javafx.scene.Node> obsrv2Graphic;

    private boolean bound1Id;
    private boolean bound2Id;
    private javafx.beans.value.ObservableValue<? extends String> obsrv1Id;
    private javafx.beans.property.Property<String> obsrv2Id;

    private boolean bound1MaxWidth;
    private boolean bound2MaxWidth;
    private javafx.beans.value.ObservableValue<? extends Number> obsrv1MaxWidth;
    private javafx.beans.property.Property<Number> obsrv2MaxWidth;

    private boolean bound1MinWidth;
    private boolean bound2MinWidth;
    private javafx.beans.value.ObservableValue<? extends Number> obsrv1MinWidth;
    private javafx.beans.property.Property<Number> obsrv2MinWidth;

    private boolean bound1PrefWidth;
    private boolean bound2PrefWidth;
    private javafx.beans.value.ObservableValue<? extends Number> obsrv1PrefWidth;
    private javafx.beans.property.Property<Number> obsrv2PrefWidth;

    private boolean bound1Resizable;
    private boolean bound2Resizable;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1Resizable;
    private javafx.beans.property.Property<Boolean> obsrv2Resizable;

    private boolean bound1SortNode;
    private boolean bound2SortNode;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.Node> obsrv1SortNode;
    private javafx.beans.property.Property<javafx.scene.Node> obsrv2SortNode;

    private boolean bound1Sortable;
    private boolean bound2Sortable;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1Sortable;
    private javafx.beans.property.Property<Boolean> obsrv2Sortable;

    private boolean bound1Style;
    private boolean bound2Style;
    private javafx.beans.value.ObservableValue<? extends String> obsrv1Style;
    private javafx.beans.property.Property<String> obsrv2Style;

    private boolean bound1Text;
    private boolean bound2Text;
    private javafx.beans.value.ObservableValue<? extends String> obsrv1Text;
    private javafx.beans.property.Property<String> obsrv2Text;

    private boolean bound1Visible;
    private boolean bound2Visible;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1Visible;
    private javafx.beans.property.Property<Boolean> obsrv2Visible;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasComparator)
            instance.setComparator(this.valComparator);
        if (this.hasContextMenu)
            instance.setContextMenu(this.valContextMenu);
        if (this.hasEditable)
            instance.setEditable(this.valEditable);
        if (this.hasGraphic)
            instance.setGraphic(this.valGraphic);
        if (this.hasId)
            instance.setId(this.valId);
        if (this.hasMaxWidth)
            instance.setMaxWidth(this.valMaxWidth);
        if (this.hasMinWidth)
            instance.setMinWidth(this.valMinWidth);
        if (this.hasPrefWidth)
            instance.setPrefWidth(this.valPrefWidth);
        if (this.hasResizable)
            instance.setResizable(this.valResizable);
        if (this.hasSortNode)
            instance.setSortNode(this.valSortNode);
        if (this.hasSortable)
            instance.setSortable(this.valSortable);
        if (this.hasStyle)
            instance.setStyle(this.valStyle);
        if (this.hasStyleClass)
            instance.getStyleClass().addAll(this.valStyleClass);
        if (this.hasText)
            instance.setText(this.valText);
        if (this.hasUserData)
            instance.setUserData(this.valUserData);
        if (this.hasVisible)
            instance.setVisible(this.valVisible);
        if (this.bound1Comparator)
            instance.comparatorProperty().bind(this.obsrv1Comparator);
        if (this.bound2Comparator)
            instance.comparatorProperty().bindBidirectional(this.obsrv2Comparator);
        if (this.bound1ContextMenu)
            instance.contextMenuProperty().bind(this.obsrv1ContextMenu);
        if (this.bound2ContextMenu)
            instance.contextMenuProperty().bindBidirectional(this.obsrv2ContextMenu);
        if (this.bound1Editable)
            instance.editableProperty().bind(this.obsrv1Editable);
        if (this.bound2Editable)
            instance.editableProperty().bindBidirectional(this.obsrv2Editable);
        if (this.bound1Graphic)
            instance.graphicProperty().bind(this.obsrv1Graphic);
        if (this.bound2Graphic)
            instance.graphicProperty().bindBidirectional(this.obsrv2Graphic);
        if (this.bound1Id)
            instance.idProperty().bind(this.obsrv1Id);
        if (this.bound2Id)
            instance.idProperty().bindBidirectional(this.obsrv2Id);
        if (this.bound1MaxWidth)
            instance.maxWidthProperty().bind(this.obsrv1MaxWidth);
        if (this.bound2MaxWidth)
            instance.maxWidthProperty().bindBidirectional(this.obsrv2MaxWidth);
        if (this.bound1MinWidth)
            instance.minWidthProperty().bind(this.obsrv1MinWidth);
        if (this.bound2MinWidth)
            instance.minWidthProperty().bindBidirectional(this.obsrv2MinWidth);
        if (this.bound1PrefWidth)
            instance.prefWidthProperty().bind(this.obsrv1PrefWidth);
        if (this.bound2PrefWidth)
            instance.prefWidthProperty().bindBidirectional(this.obsrv2PrefWidth);
        if (this.bound1Resizable)
            instance.resizableProperty().bind(this.obsrv1Resizable);
        if (this.bound2Resizable)
            instance.resizableProperty().bindBidirectional(this.obsrv2Resizable);
        if (this.bound1SortNode)
            instance.sortNodeProperty().bind(this.obsrv1SortNode);
        if (this.bound2SortNode)
            instance.sortNodeProperty().bindBidirectional(this.obsrv2SortNode);
        if (this.bound1Sortable)
            instance.sortableProperty().bind(this.obsrv1Sortable);
        if (this.bound2Sortable)
            instance.sortableProperty().bindBidirectional(this.obsrv2Sortable);
        if (this.bound1Style)
            instance.styleProperty().bind(this.obsrv1Style);
        if (this.bound2Style)
            instance.styleProperty().bindBidirectional(this.obsrv2Style);
        if (this.bound1Text)
            instance.textProperty().bind(this.obsrv1Text);
        if (this.bound2Text)
            instance.textProperty().bindBidirectional(this.obsrv2Text);
        if (this.bound1Visible)
            instance.visibleProperty().bind(this.obsrv1Visible);
        if (this.bound2Visible)
            instance.visibleProperty().bindBidirectional(this.obsrv2Visible);
    }

    /**
     * 設定屬性{@link TableColumnBase#setComparator(java.util.Comparator)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B comparator(java.util.Comparator<T> value)
    {
        this.hasComparator = true;
        this.valComparator = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#setContextMenu(javafx.scene.control.ContextMenu)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B contextMenu(javafx.scene.control.ContextMenu value)
    {
        this.hasContextMenu = true;
        this.valContextMenu = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#setEditable(boolean)}。
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
     * 設定屬性{@link TableColumnBase#setGraphic(javafx.scene.Node)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B graphic(javafx.scene.Node value)
    {
        this.hasGraphic = true;
        this.valGraphic = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#setId(java.lang.String)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B id(java.lang.String value)
    {
        this.hasId = true;
        this.valId = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#setMaxWidth(double)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B maxWidth(double value)
    {
        this.hasMaxWidth = true;
        this.valMaxWidth = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#setMinWidth(double)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B minWidth(double value)
    {
        this.hasMinWidth = true;
        this.valMinWidth = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#setPrefWidth(double)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B prefWidth(double value)
    {
        this.hasPrefWidth = true;
        this.valPrefWidth = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#setResizable(boolean)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B resizable(boolean value)
    {
        this.hasResizable = true;
        this.valResizable = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#setSortNode(javafx.scene.Node)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B sortNode(javafx.scene.Node value)
    {
        this.hasSortNode = true;
        this.valSortNode = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#setSortable(boolean)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B sortable(boolean value)
    {
        this.hasSortable = true;
        this.valSortable = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#setStyle(java.lang.String)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B style(java.lang.String value)
    {
        this.hasStyle = true;
        this.valStyle = value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link TableColumnBase#getStyleClass}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     * @deprecated 屬性值並非{@link javafx.collections.ObservableList}
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public final B styleClass(java.util.Collection<? extends java.lang.String> value)
    {
        this.hasStyleClass = true;
        this.valStyleClass = (java.util.Collection<java.lang.String>) value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link TableColumnBase#getStyleClass}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B styleClass(java.lang.String... value)
    {
        this.hasStyleClass = true;
        this.valStyleClass = java.util.Arrays.asList(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link TableColumnBase#getStyleClass}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B styleClassAdd(java.util.Collection<? extends java.lang.String> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasStyleClass = true;
        if (this.valStyleClass == null)
            this.valStyleClass = new java.util.ArrayList<>(value.size());
        this.valStyleClass.addAll(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link TableColumnBase#getStyleClass}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B styleClassAdd(java.lang.String... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasStyleClass = true;
        if (this.valStyleClass == null)
            this.valStyleClass = new java.util.ArrayList<>(value.length);
        this.valStyleClass.addAll(java.util.Arrays.asList(value));
        return (B) this;
    }

    /**
     * 增加集合屬性{@link TableColumnBase#getStyleClass}的內容，排除null項目。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B styleClassAddNonNull(java.util.Collection<? extends java.lang.String> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasStyleClass = true;
        if (this.valStyleClass == null)
            this.valStyleClass = new java.util.ArrayList<>(value.size());
        for (java.lang.String i : value)
            if (i != null)
                this.valStyleClass.add(i);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link TableColumnBase#getStyleClass}的內容，排除null項目。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B styleClassAddNonNull(java.lang.String... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasStyleClass = true;
        if (this.valStyleClass == null)
            this.valStyleClass = new java.util.ArrayList<>(value.length);
        for (java.lang.String i : value)
            if (i != null)
                this.valStyleClass.add(i);
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#setText(java.lang.String)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B text(java.lang.String value)
    {
        this.hasText = true;
        this.valText = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#setUserData(java.lang.Object)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B userData(java.lang.Object value)
    {
        this.hasUserData = true;
        this.valUserData = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#setVisible(boolean)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B visible(boolean value)
    {
        this.hasVisible = true;
        this.valVisible = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#comparatorProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindComparator(javafx.beans.value.ObservableValue<? extends java.util.Comparator<T>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Comparator = true;
        this.obsrv1Comparator = source;
        this.bound2Comparator = false;
        this.obsrv2Comparator = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#comparatorProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalComparator(javafx.beans.property.Property<java.util.Comparator<T>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Comparator = false;
        this.obsrv1Comparator = null;
        this.bound2Comparator = true;
        this.obsrv2Comparator = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#contextMenuProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindContextMenu(javafx.beans.value.ObservableValue<? extends javafx.scene.control.ContextMenu> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1ContextMenu = true;
        this.obsrv1ContextMenu = source;
        this.bound2ContextMenu = false;
        this.obsrv2ContextMenu = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#contextMenuProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalContextMenu(javafx.beans.property.Property<javafx.scene.control.ContextMenu> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1ContextMenu = false;
        this.obsrv1ContextMenu = null;
        this.bound2ContextMenu = true;
        this.obsrv2ContextMenu = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#editableProperty}的連結。
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
     * 設定屬性{@link TableColumnBase#editableProperty}的雙向連結。
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
     * 設定屬性{@link TableColumnBase#graphicProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindGraphic(javafx.beans.value.ObservableValue<? extends javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Graphic = true;
        this.obsrv1Graphic = source;
        this.bound2Graphic = false;
        this.obsrv2Graphic = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#graphicProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalGraphic(javafx.beans.property.Property<javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Graphic = false;
        this.obsrv1Graphic = null;
        this.bound2Graphic = true;
        this.obsrv2Graphic = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#idProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindId(javafx.beans.value.ObservableValue<? extends String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Id = true;
        this.obsrv1Id = source;
        this.bound2Id = false;
        this.obsrv2Id = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#idProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalId(javafx.beans.property.Property<String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Id = false;
        this.obsrv1Id = null;
        this.bound2Id = true;
        this.obsrv2Id = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#maxWidthProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindMaxWidth(javafx.beans.value.ObservableValue<? extends Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1MaxWidth = true;
        this.obsrv1MaxWidth = source;
        this.bound2MaxWidth = false;
        this.obsrv2MaxWidth = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#maxWidthProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalMaxWidth(javafx.beans.property.Property<Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1MaxWidth = false;
        this.obsrv1MaxWidth = null;
        this.bound2MaxWidth = true;
        this.obsrv2MaxWidth = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#minWidthProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindMinWidth(javafx.beans.value.ObservableValue<? extends Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1MinWidth = true;
        this.obsrv1MinWidth = source;
        this.bound2MinWidth = false;
        this.obsrv2MinWidth = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#minWidthProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalMinWidth(javafx.beans.property.Property<Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1MinWidth = false;
        this.obsrv1MinWidth = null;
        this.bound2MinWidth = true;
        this.obsrv2MinWidth = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#prefWidthProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindPrefWidth(javafx.beans.value.ObservableValue<? extends Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1PrefWidth = true;
        this.obsrv1PrefWidth = source;
        this.bound2PrefWidth = false;
        this.obsrv2PrefWidth = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#prefWidthProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalPrefWidth(javafx.beans.property.Property<Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1PrefWidth = false;
        this.obsrv1PrefWidth = null;
        this.bound2PrefWidth = true;
        this.obsrv2PrefWidth = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#resizableProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindResizable(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Resizable = true;
        this.obsrv1Resizable = source;
        this.bound2Resizable = false;
        this.obsrv2Resizable = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#resizableProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalResizable(javafx.beans.property.Property<Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Resizable = false;
        this.obsrv1Resizable = null;
        this.bound2Resizable = true;
        this.obsrv2Resizable = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#sortNodeProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindSortNode(javafx.beans.value.ObservableValue<? extends javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1SortNode = true;
        this.obsrv1SortNode = source;
        this.bound2SortNode = false;
        this.obsrv2SortNode = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#sortNodeProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalSortNode(javafx.beans.property.Property<javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1SortNode = false;
        this.obsrv1SortNode = null;
        this.bound2SortNode = true;
        this.obsrv2SortNode = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#sortableProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindSortable(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Sortable = true;
        this.obsrv1Sortable = source;
        this.bound2Sortable = false;
        this.obsrv2Sortable = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#sortableProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalSortable(javafx.beans.property.Property<Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Sortable = false;
        this.obsrv1Sortable = null;
        this.bound2Sortable = true;
        this.obsrv2Sortable = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#styleProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindStyle(javafx.beans.value.ObservableValue<? extends String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Style = true;
        this.obsrv1Style = source;
        this.bound2Style = false;
        this.obsrv2Style = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#styleProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalStyle(javafx.beans.property.Property<String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Style = false;
        this.obsrv1Style = null;
        this.bound2Style = true;
        this.obsrv2Style = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#textProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindText(javafx.beans.value.ObservableValue<? extends String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Text = true;
        this.obsrv1Text = source;
        this.bound2Text = false;
        this.obsrv2Text = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#textProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalText(javafx.beans.property.Property<String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Text = false;
        this.obsrv1Text = null;
        this.bound2Text = true;
        this.obsrv2Text = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#visibleProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindVisible(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Visible = true;
        this.obsrv1Visible = source;
        this.bound2Visible = false;
        this.obsrv2Visible = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumnBase#visibleProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalVisible(javafx.beans.property.Property<Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Visible = false;
        this.obsrv1Visible = null;
        this.bound2Visible = true;
        this.obsrv2Visible = source;
        return (B) this;
    }
}