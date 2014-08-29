// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.control;

/**
 * {@link ListView}建構器
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class ListViewBuilder<T extends java.lang.Object, Z extends ListView<T>, B extends ListViewBuilder<T, Z, B>>
        extends javafx.scene.control.ControlBuilder<Z, B>
{

    protected boolean hasCellFactory;
    protected javafx.util.Callback<javafx.scene.control.ListView<T>, javafx.scene.control.ListCell<T>> valCellFactory;

    protected boolean hasEditable;
    protected boolean valEditable;

    protected boolean hasFixedCellSize;
    protected double valFixedCellSize;

    protected boolean hasFocusModel;
    protected javafx.scene.control.FocusModel<T> valFocusModel;

    protected boolean hasItems;
    protected javafx.collections.ObservableList<T> valItems;

    protected boolean hasOnEditCancel;
    protected javafx.event.EventHandler<javafx.scene.control.ListView.EditEvent<T>> valOnEditCancel;

    protected boolean hasOnEditCommit;
    protected javafx.event.EventHandler<javafx.scene.control.ListView.EditEvent<T>> valOnEditCommit;

    protected boolean hasOnEditStart;
    protected javafx.event.EventHandler<javafx.scene.control.ListView.EditEvent<T>> valOnEditStart;

    protected boolean hasOnScrollTo;
    protected javafx.event.EventHandler<javafx.scene.control.ScrollToEvent<java.lang.Integer>> valOnScrollTo;

    protected boolean hasOrientation;
    protected javafx.geometry.Orientation valOrientation;

    protected boolean hasPlaceholder;
    protected javafx.scene.Node valPlaceholder;

    protected boolean hasSelectionModel;
    protected javafx.scene.control.MultipleSelectionModel<T> valSelectionModel;

    protected boolean boundCellFactory;
    protected javafx.beans.value.ObservableValue<? extends javafx.util.Callback<javafx.scene.control.ListView<T>, javafx.scene.control.ListCell<T>>> obsrvCellFactory;

    protected boolean boundEditable;
    protected javafx.beans.value.ObservableValue<? extends Boolean> obsrvEditable;

    protected boolean boundFixedCellSize;
    protected javafx.beans.value.ObservableValue<? extends Double> obsrvFixedCellSize;

    protected boolean boundFocusModel;
    protected javafx.beans.value.ObservableValue<? extends javafx.scene.control.FocusModel<T>> obsrvFocusModel;

    protected boolean boundItems;
    protected javafx.beans.value.ObservableValue<? extends javafx.collections.ObservableList<T>> obsrvItems;

    protected boolean boundOnEditCancel;
    protected javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.scene.control.ListView.EditEvent<T>>> obsrvOnEditCancel;

    protected boolean boundOnEditCommit;
    protected javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.scene.control.ListView.EditEvent<T>>> obsrvOnEditCommit;

    protected boolean boundOnEditStart;
    protected javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.scene.control.ListView.EditEvent<T>>> obsrvOnEditStart;

    protected boolean boundOrientation;
    protected javafx.beans.value.ObservableValue<? extends javafx.geometry.Orientation> obsrvOrientation;

    protected boolean boundPlaceholder;
    protected javafx.beans.value.ObservableValue<? extends javafx.scene.Node> obsrvPlaceholder;

    protected boolean boundSelectionModel;
    protected javafx.beans.value.ObservableValue<? extends javafx.scene.control.MultipleSelectionModel<T>> obsrvSelectionModel;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasCellFactory)
            instance.setCellFactory(this.valCellFactory);
        if (this.hasEditable)
            instance.setEditable(this.valEditable);
        if (this.hasFixedCellSize)
            instance.setFixedCellSize(this.valFixedCellSize);
        if (this.hasFocusModel)
            instance.setFocusModel(this.valFocusModel);
        if (this.hasItems)
            instance.setItems(this.valItems);
        if (this.hasOnEditCancel)
            instance.setOnEditCancel(this.valOnEditCancel);
        if (this.hasOnEditCommit)
            instance.setOnEditCommit(this.valOnEditCommit);
        if (this.hasOnEditStart)
            instance.setOnEditStart(this.valOnEditStart);
        if (this.hasOnScrollTo)
            instance.setOnScrollTo(this.valOnScrollTo);
        if (this.hasOrientation)
            instance.setOrientation(this.valOrientation);
        if (this.hasPlaceholder)
            instance.setPlaceholder(this.valPlaceholder);
        if (this.hasSelectionModel)
            instance.setSelectionModel(this.valSelectionModel);
        if (this.boundCellFactory)
            instance.cellFactoryProperty().bind(this.obsrvCellFactory);
        if (this.boundEditable)
            instance.editableProperty().bind(this.obsrvEditable);
        if (this.boundFixedCellSize)
            instance.fixedCellSizeProperty().bind(this.obsrvFixedCellSize);
        if (this.boundFocusModel)
            instance.focusModelProperty().bind(this.obsrvFocusModel);
        if (this.boundItems)
            instance.itemsProperty().bind(this.obsrvItems);
        if (this.boundOnEditCancel)
            instance.onEditCancelProperty().bind(this.obsrvOnEditCancel);
        if (this.boundOnEditCommit)
            instance.onEditCommitProperty().bind(this.obsrvOnEditCommit);
        if (this.boundOnEditStart)
            instance.onEditStartProperty().bind(this.obsrvOnEditStart);
        if (this.boundOrientation)
            instance.orientationProperty().bind(this.obsrvOrientation);
        if (this.boundPlaceholder)
            instance.placeholderProperty().bind(this.obsrvPlaceholder);
        if (this.boundSelectionModel)
            instance.selectionModelProperty().bind(this.obsrvSelectionModel);
    }

    /**
     * 設定屬性{@link ListView#setCellFactory(javafx.util.Callback)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B cellFactory(javafx.util.Callback<javafx.scene.control.ListView<T>, javafx.scene.control.ListCell<T>> value)
    {
        this.hasCellFactory = true;
        this.valCellFactory = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ListView#setEditable(boolean)}
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
     * 設定屬性{@link ListView#setFixedCellSize(double)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B fixedCellSize(double value)
    {
        this.hasFixedCellSize = true;
        this.valFixedCellSize = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ListView#setFocusModel(javafx.scene.control.FocusModel)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B focusModel(javafx.scene.control.FocusModel<T> value)
    {
        this.hasFocusModel = true;
        this.valFocusModel = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ListView#setItems(javafx.collections.ObservableList)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B items(javafx.collections.ObservableList<T> value)
    {
        this.hasItems = true;
        this.valItems = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ListView#setOnEditCancel(javafx.event.EventHandler)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B onEditCancel(javafx.event.EventHandler<javafx.scene.control.ListView.EditEvent<T>> value)
    {
        this.hasOnEditCancel = true;
        this.valOnEditCancel = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ListView#setOnEditCommit(javafx.event.EventHandler)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B onEditCommit(javafx.event.EventHandler<javafx.scene.control.ListView.EditEvent<T>> value)
    {
        this.hasOnEditCommit = true;
        this.valOnEditCommit = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ListView#setOnEditStart(javafx.event.EventHandler)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B onEditStart(javafx.event.EventHandler<javafx.scene.control.ListView.EditEvent<T>> value)
    {
        this.hasOnEditStart = true;
        this.valOnEditStart = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ListView#setOnScrollTo(javafx.event.EventHandler)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B onScrollTo(javafx.event.EventHandler<javafx.scene.control.ScrollToEvent<java.lang.Integer>> value)
    {
        this.hasOnScrollTo = true;
        this.valOnScrollTo = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ListView#setOrientation(javafx.geometry.Orientation)}
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
     * 設定屬性{@link ListView#setPlaceholder(javafx.scene.Node)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B placeholder(javafx.scene.Node value)
    {
        this.hasPlaceholder = true;
        this.valPlaceholder = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ListView#setSelectionModel(javafx.scene.control.MultipleSelectionModel)}
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B selectionModel(javafx.scene.control.MultipleSelectionModel<T> value)
    {
        this.hasSelectionModel = true;
        this.valSelectionModel = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ListView#cellFactoryProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindCellFactory(javafx.beans.value.ObservableValue<? extends javafx.util.Callback<javafx.scene.control.ListView<T>, javafx.scene.control.ListCell<T>>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundCellFactory = true;
        this.obsrvCellFactory = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ListView#editableProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindEditable(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundEditable = true;
        this.obsrvEditable = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ListView#fixedCellSizeProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindFixedCellSize(javafx.beans.value.ObservableValue<? extends Double> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundFixedCellSize = true;
        this.obsrvFixedCellSize = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ListView#focusModelProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindFocusModel(javafx.beans.value.ObservableValue<? extends javafx.scene.control.FocusModel<T>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundFocusModel = true;
        this.obsrvFocusModel = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ListView#itemsProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindItems(javafx.beans.value.ObservableValue<? extends javafx.collections.ObservableList<T>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundItems = true;
        this.obsrvItems = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ListView#onEditCancelProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOnEditCancel(javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.scene.control.ListView.EditEvent<T>>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundOnEditCancel = true;
        this.obsrvOnEditCancel = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ListView#onEditCommitProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOnEditCommit(javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.scene.control.ListView.EditEvent<T>>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundOnEditCommit = true;
        this.obsrvOnEditCommit = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ListView#onEditStartProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOnEditStart(javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.scene.control.ListView.EditEvent<T>>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundOnEditStart = true;
        this.obsrvOnEditStart = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ListView#orientationProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOrientation(javafx.beans.value.ObservableValue<? extends javafx.geometry.Orientation> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundOrientation = true;
        this.obsrvOrientation = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ListView#placeholderProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindPlaceholder(javafx.beans.value.ObservableValue<? extends javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundPlaceholder = true;
        this.obsrvPlaceholder = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ListView#selectionModelProperty}的連結
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindSelectionModel(javafx.beans.value.ObservableValue<? extends javafx.scene.control.MultipleSelectionModel<T>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.boundSelectionModel = true;
        this.obsrvSelectionModel = source;
        return (B) this;
    }

    /**
     * 建構{@link ListView}物件
     *
     * @return 新的{@link ListView}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public ListView<T> build()
    {
        ListView<T> instance = new ListView<T>();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
