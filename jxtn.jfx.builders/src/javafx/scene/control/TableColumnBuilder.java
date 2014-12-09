// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.control;

/**
 * {@link TableColumn}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link TableColumn})
 * @param <B> 建構器本身的型態(需繼承{@link TableColumnBuilder})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class TableColumnBuilder<S extends java.lang.Object, T extends java.lang.Object, Z extends TableColumn<S, T>, B extends TableColumnBuilder<S, T, Z, B>>
        extends javafx.scene.control.TableColumnBaseBuilder<S, T, Z, B>
        implements TableColumnBuilderExt<S, T, Z, B>
{

    private boolean hasCellFactory;
    private javafx.util.Callback<javafx.scene.control.TableColumn<S, T>, javafx.scene.control.TableCell<S, T>> valCellFactory;

    private boolean hasCellValueFactory;
    private javafx.util.Callback<javafx.scene.control.TableColumn.CellDataFeatures<S, T>, javafx.beans.value.ObservableValue<T>> valCellValueFactory;

    private boolean hasColumns;
    private java.util.Collection<javafx.scene.control.TableColumn<S, ?>> valColumns;

    private boolean hasOnEditCancel;
    private javafx.event.EventHandler<javafx.scene.control.TableColumn.CellEditEvent<S, T>> valOnEditCancel;

    private boolean hasOnEditCommit;
    private javafx.event.EventHandler<javafx.scene.control.TableColumn.CellEditEvent<S, T>> valOnEditCommit;

    private boolean hasOnEditStart;
    private javafx.event.EventHandler<javafx.scene.control.TableColumn.CellEditEvent<S, T>> valOnEditStart;

    private boolean hasSortType;
    private javafx.scene.control.TableColumn.SortType valSortType;

    private boolean bound1CellFactory;
    private boolean bound2CellFactory;
    private javafx.beans.value.ObservableValue<? extends javafx.util.Callback<javafx.scene.control.TableColumn<S, T>, javafx.scene.control.TableCell<S, T>>> obsrv1CellFactory;
    private javafx.beans.property.Property<javafx.util.Callback<javafx.scene.control.TableColumn<S, T>, javafx.scene.control.TableCell<S, T>>> obsrv2CellFactory;

    private boolean bound1CellValueFactory;
    private boolean bound2CellValueFactory;
    private javafx.beans.value.ObservableValue<? extends javafx.util.Callback<javafx.scene.control.TableColumn.CellDataFeatures<S, T>, javafx.beans.value.ObservableValue<T>>> obsrv1CellValueFactory;
    private javafx.beans.property.Property<javafx.util.Callback<javafx.scene.control.TableColumn.CellDataFeatures<S, T>, javafx.beans.value.ObservableValue<T>>> obsrv2CellValueFactory;

    private boolean bound1OnEditCancel;
    private boolean bound2OnEditCancel;
    private javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.scene.control.TableColumn.CellEditEvent<S, T>>> obsrv1OnEditCancel;
    private javafx.beans.property.Property<javafx.event.EventHandler<javafx.scene.control.TableColumn.CellEditEvent<S, T>>> obsrv2OnEditCancel;

    private boolean bound1OnEditCommit;
    private boolean bound2OnEditCommit;
    private javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.scene.control.TableColumn.CellEditEvent<S, T>>> obsrv1OnEditCommit;
    private javafx.beans.property.Property<javafx.event.EventHandler<javafx.scene.control.TableColumn.CellEditEvent<S, T>>> obsrv2OnEditCommit;

    private boolean bound1OnEditStart;
    private boolean bound2OnEditStart;
    private javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.scene.control.TableColumn.CellEditEvent<S, T>>> obsrv1OnEditStart;
    private javafx.beans.property.Property<javafx.event.EventHandler<javafx.scene.control.TableColumn.CellEditEvent<S, T>>> obsrv2OnEditStart;

    private boolean bound1SortType;
    private boolean bound2SortType;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.control.TableColumn.SortType> obsrv1SortType;
    private javafx.beans.property.Property<javafx.scene.control.TableColumn.SortType> obsrv2SortType;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasCellFactory)
            instance.setCellFactory(this.valCellFactory);
        if (this.hasCellValueFactory)
            instance.setCellValueFactory(this.valCellValueFactory);
        if (this.hasColumns)
            instance.getColumns().addAll(this.valColumns);
        if (this.hasOnEditCancel)
            instance.setOnEditCancel(this.valOnEditCancel);
        if (this.hasOnEditCommit)
            instance.setOnEditCommit(this.valOnEditCommit);
        if (this.hasOnEditStart)
            instance.setOnEditStart(this.valOnEditStart);
        if (this.hasSortType)
            instance.setSortType(this.valSortType);
        if (this.bound1CellFactory)
            instance.cellFactoryProperty().bind(this.obsrv1CellFactory);
        if (this.bound2CellFactory)
            instance.cellFactoryProperty().bindBidirectional(this.obsrv2CellFactory);
        if (this.bound1CellValueFactory)
            instance.cellValueFactoryProperty().bind(this.obsrv1CellValueFactory);
        if (this.bound2CellValueFactory)
            instance.cellValueFactoryProperty().bindBidirectional(this.obsrv2CellValueFactory);
        if (this.bound1OnEditCancel)
            instance.onEditCancelProperty().bind(this.obsrv1OnEditCancel);
        if (this.bound2OnEditCancel)
            instance.onEditCancelProperty().bindBidirectional(this.obsrv2OnEditCancel);
        if (this.bound1OnEditCommit)
            instance.onEditCommitProperty().bind(this.obsrv1OnEditCommit);
        if (this.bound2OnEditCommit)
            instance.onEditCommitProperty().bindBidirectional(this.obsrv2OnEditCommit);
        if (this.bound1OnEditStart)
            instance.onEditStartProperty().bind(this.obsrv1OnEditStart);
        if (this.bound2OnEditStart)
            instance.onEditStartProperty().bindBidirectional(this.obsrv2OnEditStart);
        if (this.bound1SortType)
            instance.sortTypeProperty().bind(this.obsrv1SortType);
        if (this.bound2SortType)
            instance.sortTypeProperty().bindBidirectional(this.obsrv2SortType);
    }

    /**
     * 設定屬性{@link TableColumn#setCellFactory(javafx.util.Callback)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B cellFactory(javafx.util.Callback<javafx.scene.control.TableColumn<S, T>, javafx.scene.control.TableCell<S, T>> value)
    {
        this.hasCellFactory = true;
        this.valCellFactory = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumn#setCellValueFactory(javafx.util.Callback)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B cellValueFactory(javafx.util.Callback<javafx.scene.control.TableColumn.CellDataFeatures<S, T>, javafx.beans.value.ObservableValue<T>> value)
    {
        this.hasCellValueFactory = true;
        this.valCellValueFactory = value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link TableColumn#getColumns}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     * @deprecated 屬性值並非{@link javafx.collections.ObservableList}
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public final B columns(java.util.Collection<? extends javafx.scene.control.TableColumn<S, ?>> value)
    {
        this.hasColumns = true;
        this.valColumns = (java.util.Collection<javafx.scene.control.TableColumn<S, ?>>) value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link TableColumn#getColumns}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B columns(javafx.scene.control.TableColumn<S, ?>... value)
    {
        this.hasColumns = true;
        this.valColumns = java.util.Arrays.asList(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link TableColumn#getColumns}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B columnsAdd(java.util.Collection<? extends javafx.scene.control.TableColumn<S, ?>> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasColumns = true;
        if (this.valColumns == null)
            this.valColumns = new java.util.ArrayList<>(value.size());
        this.valColumns.addAll(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link TableColumn#getColumns}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B columnsAdd(javafx.scene.control.TableColumn<S, ?>... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasColumns = true;
        if (this.valColumns == null)
            this.valColumns = new java.util.ArrayList<>(value.length);
        this.valColumns.addAll(java.util.Arrays.asList(value));
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumn#setOnEditCancel(javafx.event.EventHandler)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B onEditCancel(javafx.event.EventHandler<javafx.scene.control.TableColumn.CellEditEvent<S, T>> value)
    {
        this.hasOnEditCancel = true;
        this.valOnEditCancel = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumn#setOnEditCommit(javafx.event.EventHandler)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B onEditCommit(javafx.event.EventHandler<javafx.scene.control.TableColumn.CellEditEvent<S, T>> value)
    {
        this.hasOnEditCommit = true;
        this.valOnEditCommit = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumn#setOnEditStart(javafx.event.EventHandler)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B onEditStart(javafx.event.EventHandler<javafx.scene.control.TableColumn.CellEditEvent<S, T>> value)
    {
        this.hasOnEditStart = true;
        this.valOnEditStart = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumn#setSortType(javafx.scene.control.TableColumn.SortType)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B sortType(javafx.scene.control.TableColumn.SortType value)
    {
        this.hasSortType = true;
        this.valSortType = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumn#cellFactoryProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindCellFactory(javafx.beans.value.ObservableValue<? extends javafx.util.Callback<javafx.scene.control.TableColumn<S, T>, javafx.scene.control.TableCell<S, T>>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1CellFactory = true;
        this.obsrv1CellFactory = source;
        this.bound2CellFactory = false;
        this.obsrv2CellFactory = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumn#cellFactoryProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalCellFactory(javafx.beans.property.Property<javafx.util.Callback<javafx.scene.control.TableColumn<S, T>, javafx.scene.control.TableCell<S, T>>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1CellFactory = false;
        this.obsrv1CellFactory = null;
        this.bound2CellFactory = true;
        this.obsrv2CellFactory = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumn#cellValueFactoryProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindCellValueFactory(javafx.beans.value.ObservableValue<? extends javafx.util.Callback<javafx.scene.control.TableColumn.CellDataFeatures<S, T>, javafx.beans.value.ObservableValue<T>>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1CellValueFactory = true;
        this.obsrv1CellValueFactory = source;
        this.bound2CellValueFactory = false;
        this.obsrv2CellValueFactory = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumn#cellValueFactoryProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalCellValueFactory(javafx.beans.property.Property<javafx.util.Callback<javafx.scene.control.TableColumn.CellDataFeatures<S, T>, javafx.beans.value.ObservableValue<T>>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1CellValueFactory = false;
        this.obsrv1CellValueFactory = null;
        this.bound2CellValueFactory = true;
        this.obsrv2CellValueFactory = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumn#onEditCancelProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOnEditCancel(javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.scene.control.TableColumn.CellEditEvent<S, T>>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnEditCancel = true;
        this.obsrv1OnEditCancel = source;
        this.bound2OnEditCancel = false;
        this.obsrv2OnEditCancel = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumn#onEditCancelProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalOnEditCancel(javafx.beans.property.Property<javafx.event.EventHandler<javafx.scene.control.TableColumn.CellEditEvent<S, T>>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnEditCancel = false;
        this.obsrv1OnEditCancel = null;
        this.bound2OnEditCancel = true;
        this.obsrv2OnEditCancel = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumn#onEditCommitProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOnEditCommit(javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.scene.control.TableColumn.CellEditEvent<S, T>>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnEditCommit = true;
        this.obsrv1OnEditCommit = source;
        this.bound2OnEditCommit = false;
        this.obsrv2OnEditCommit = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumn#onEditCommitProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalOnEditCommit(javafx.beans.property.Property<javafx.event.EventHandler<javafx.scene.control.TableColumn.CellEditEvent<S, T>>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnEditCommit = false;
        this.obsrv1OnEditCommit = null;
        this.bound2OnEditCommit = true;
        this.obsrv2OnEditCommit = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumn#onEditStartProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOnEditStart(javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.scene.control.TableColumn.CellEditEvent<S, T>>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnEditStart = true;
        this.obsrv1OnEditStart = source;
        this.bound2OnEditStart = false;
        this.obsrv2OnEditStart = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumn#onEditStartProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalOnEditStart(javafx.beans.property.Property<javafx.event.EventHandler<javafx.scene.control.TableColumn.CellEditEvent<S, T>>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnEditStart = false;
        this.obsrv1OnEditStart = null;
        this.bound2OnEditStart = true;
        this.obsrv2OnEditStart = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumn#sortTypeProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindSortType(javafx.beans.value.ObservableValue<? extends javafx.scene.control.TableColumn.SortType> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1SortType = true;
        this.obsrv1SortType = source;
        this.bound2SortType = false;
        this.obsrv2SortType = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link TableColumn#sortTypeProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalSortType(javafx.beans.property.Property<javafx.scene.control.TableColumn.SortType> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1SortType = false;
        this.obsrv1SortType = null;
        this.bound2SortType = true;
        this.obsrv2SortType = source;
        return (B) this;
    }

    /**
     * 建構{@link TableColumn}物件。
     *
     * @return 新的{@link TableColumn}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public TableColumn<S, T> build()
    {
        TableColumn<S, T> instance = new TableColumn<S, T>();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link TableColumn}物件。
     *
     * @return 新的{@link TableColumn}物件實體
     */
    @SuppressWarnings("unchecked")
    public TableColumn<S, T> build(java.lang.String arg0)
    {
        TableColumn<S, T> instance = new TableColumn<S, T>(arg0);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
