// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.control;

/**
 * {@link ChoiceBox}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link ChoiceBox})
 * @param <B> 建構器本身的型態(需繼承{@link ChoiceBoxMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class ChoiceBoxMaker<T extends java.lang.Object, Z extends ChoiceBox<T>, B extends ChoiceBoxMaker<T, Z, B>>
        extends javafx.scene.control.ControlMaker<Z, B>
        implements ChoiceBoxMakerExt<T, Z, B>
{

    private boolean hasConverter;
    private javafx.util.StringConverter<T> valConverter;

    private boolean hasItems;
    private javafx.collections.ObservableList<T> valItems;

    private boolean hasOnAction;
    private javafx.event.EventHandler<javafx.event.ActionEvent> valOnAction;

    private boolean hasOnHidden;
    private javafx.event.EventHandler<javafx.event.Event> valOnHidden;

    private boolean hasOnHiding;
    private javafx.event.EventHandler<javafx.event.Event> valOnHiding;

    private boolean hasOnShowing;
    private javafx.event.EventHandler<javafx.event.Event> valOnShowing;

    private boolean hasOnShown;
    private javafx.event.EventHandler<javafx.event.Event> valOnShown;

    private boolean hasSelectionModel;
    private javafx.scene.control.SingleSelectionModel<T> valSelectionModel;

    private boolean hasValue;
    private T valValue;

    private boolean bound1Converter;
    private boolean bound2Converter;
    private javafx.beans.value.ObservableValue<? extends javafx.util.StringConverter<T>> obsrv1Converter;
    private javafx.beans.property.Property<javafx.util.StringConverter<T>> obsrv2Converter;

    private boolean bound1Items;
    private boolean bound2Items;
    private javafx.beans.value.ObservableValue<? extends javafx.collections.ObservableList<T>> obsrv1Items;
    private javafx.beans.property.Property<javafx.collections.ObservableList<T>> obsrv2Items;

    private boolean bound1OnAction;
    private boolean bound2OnAction;
    private javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.event.ActionEvent>> obsrv1OnAction;
    private javafx.beans.property.Property<javafx.event.EventHandler<javafx.event.ActionEvent>> obsrv2OnAction;

    private boolean bound1OnHidden;
    private boolean bound2OnHidden;
    private javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.event.Event>> obsrv1OnHidden;
    private javafx.beans.property.Property<javafx.event.EventHandler<javafx.event.Event>> obsrv2OnHidden;

    private boolean bound1OnHiding;
    private boolean bound2OnHiding;
    private javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.event.Event>> obsrv1OnHiding;
    private javafx.beans.property.Property<javafx.event.EventHandler<javafx.event.Event>> obsrv2OnHiding;

    private boolean bound1OnShowing;
    private boolean bound2OnShowing;
    private javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.event.Event>> obsrv1OnShowing;
    private javafx.beans.property.Property<javafx.event.EventHandler<javafx.event.Event>> obsrv2OnShowing;

    private boolean bound1OnShown;
    private boolean bound2OnShown;
    private javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.event.Event>> obsrv1OnShown;
    private javafx.beans.property.Property<javafx.event.EventHandler<javafx.event.Event>> obsrv2OnShown;

    private boolean bound1SelectionModel;
    private boolean bound2SelectionModel;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.control.SingleSelectionModel<T>> obsrv1SelectionModel;
    private javafx.beans.property.Property<javafx.scene.control.SingleSelectionModel<T>> obsrv2SelectionModel;

    private boolean bound1Value;
    private boolean bound2Value;
    private javafx.beans.value.ObservableValue<? extends T> obsrv1Value;
    private javafx.beans.property.Property<T> obsrv2Value;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasConverter)
            instance.setConverter(this.valConverter);
        if (this.hasItems)
            instance.setItems(this.valItems);
        if (this.hasOnAction)
            instance.setOnAction(this.valOnAction);
        if (this.hasOnHidden)
            instance.setOnHidden(this.valOnHidden);
        if (this.hasOnHiding)
            instance.setOnHiding(this.valOnHiding);
        if (this.hasOnShowing)
            instance.setOnShowing(this.valOnShowing);
        if (this.hasOnShown)
            instance.setOnShown(this.valOnShown);
        if (this.hasSelectionModel)
            instance.setSelectionModel(this.valSelectionModel);
        if (this.hasValue)
            instance.setValue(this.valValue);
        if (this.bound1Converter)
            instance.converterProperty().bind(this.obsrv1Converter);
        if (this.bound2Converter)
            instance.converterProperty().bindBidirectional(this.obsrv2Converter);
        if (this.bound1Items)
            instance.itemsProperty().bind(this.obsrv1Items);
        if (this.bound2Items)
            instance.itemsProperty().bindBidirectional(this.obsrv2Items);
        if (this.bound1OnAction)
            instance.onActionProperty().bind(this.obsrv1OnAction);
        if (this.bound2OnAction)
            instance.onActionProperty().bindBidirectional(this.obsrv2OnAction);
        if (this.bound1OnHidden)
            instance.onHiddenProperty().bind(this.obsrv1OnHidden);
        if (this.bound2OnHidden)
            instance.onHiddenProperty().bindBidirectional(this.obsrv2OnHidden);
        if (this.bound1OnHiding)
            instance.onHidingProperty().bind(this.obsrv1OnHiding);
        if (this.bound2OnHiding)
            instance.onHidingProperty().bindBidirectional(this.obsrv2OnHiding);
        if (this.bound1OnShowing)
            instance.onShowingProperty().bind(this.obsrv1OnShowing);
        if (this.bound2OnShowing)
            instance.onShowingProperty().bindBidirectional(this.obsrv2OnShowing);
        if (this.bound1OnShown)
            instance.onShownProperty().bind(this.obsrv1OnShown);
        if (this.bound2OnShown)
            instance.onShownProperty().bindBidirectional(this.obsrv2OnShown);
        if (this.bound1SelectionModel)
            instance.selectionModelProperty().bind(this.obsrv1SelectionModel);
        if (this.bound2SelectionModel)
            instance.selectionModelProperty().bindBidirectional(this.obsrv2SelectionModel);
        if (this.bound1Value)
            instance.valueProperty().bind(this.obsrv1Value);
        if (this.bound2Value)
            instance.valueProperty().bindBidirectional(this.obsrv2Value);
    }

    /**
     * 設定屬性{@link ChoiceBox#setConverter(javafx.util.StringConverter)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B converter(javafx.util.StringConverter<T> value)
    {
        this.hasConverter = true;
        this.valConverter = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#setItems(javafx.collections.ObservableList)}。
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
     * 設定屬性{@link ChoiceBox#setOnAction(javafx.event.EventHandler)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B onAction(javafx.event.EventHandler<javafx.event.ActionEvent> value)
    {
        this.hasOnAction = true;
        this.valOnAction = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#setOnHidden(javafx.event.EventHandler)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B onHidden(javafx.event.EventHandler<javafx.event.Event> value)
    {
        this.hasOnHidden = true;
        this.valOnHidden = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#setOnHiding(javafx.event.EventHandler)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B onHiding(javafx.event.EventHandler<javafx.event.Event> value)
    {
        this.hasOnHiding = true;
        this.valOnHiding = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#setOnShowing(javafx.event.EventHandler)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B onShowing(javafx.event.EventHandler<javafx.event.Event> value)
    {
        this.hasOnShowing = true;
        this.valOnShowing = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#setOnShown(javafx.event.EventHandler)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B onShown(javafx.event.EventHandler<javafx.event.Event> value)
    {
        this.hasOnShown = true;
        this.valOnShown = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#setSelectionModel(javafx.scene.control.SingleSelectionModel)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B selectionModel(javafx.scene.control.SingleSelectionModel<T> value)
    {
        this.hasSelectionModel = true;
        this.valSelectionModel = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#setValue(T)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B value(T value)
    {
        this.hasValue = true;
        this.valValue = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#converterProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindConverter(javafx.beans.value.ObservableValue<? extends javafx.util.StringConverter<T>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Converter = true;
        this.obsrv1Converter = source;
        this.bound2Converter = false;
        this.obsrv2Converter = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#converterProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalConverter(javafx.beans.property.Property<javafx.util.StringConverter<T>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Converter = false;
        this.obsrv1Converter = null;
        this.bound2Converter = true;
        this.obsrv2Converter = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#itemsProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindItems(javafx.beans.value.ObservableValue<? extends javafx.collections.ObservableList<T>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Items = true;
        this.obsrv1Items = source;
        this.bound2Items = false;
        this.obsrv2Items = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#itemsProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalItems(javafx.beans.property.Property<javafx.collections.ObservableList<T>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Items = false;
        this.obsrv1Items = null;
        this.bound2Items = true;
        this.obsrv2Items = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#onActionProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOnAction(javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.event.ActionEvent>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnAction = true;
        this.obsrv1OnAction = source;
        this.bound2OnAction = false;
        this.obsrv2OnAction = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#onActionProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalOnAction(javafx.beans.property.Property<javafx.event.EventHandler<javafx.event.ActionEvent>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnAction = false;
        this.obsrv1OnAction = null;
        this.bound2OnAction = true;
        this.obsrv2OnAction = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#onHiddenProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOnHidden(javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.event.Event>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnHidden = true;
        this.obsrv1OnHidden = source;
        this.bound2OnHidden = false;
        this.obsrv2OnHidden = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#onHiddenProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalOnHidden(javafx.beans.property.Property<javafx.event.EventHandler<javafx.event.Event>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnHidden = false;
        this.obsrv1OnHidden = null;
        this.bound2OnHidden = true;
        this.obsrv2OnHidden = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#onHidingProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOnHiding(javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.event.Event>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnHiding = true;
        this.obsrv1OnHiding = source;
        this.bound2OnHiding = false;
        this.obsrv2OnHiding = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#onHidingProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalOnHiding(javafx.beans.property.Property<javafx.event.EventHandler<javafx.event.Event>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnHiding = false;
        this.obsrv1OnHiding = null;
        this.bound2OnHiding = true;
        this.obsrv2OnHiding = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#onShowingProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOnShowing(javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.event.Event>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnShowing = true;
        this.obsrv1OnShowing = source;
        this.bound2OnShowing = false;
        this.obsrv2OnShowing = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#onShowingProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalOnShowing(javafx.beans.property.Property<javafx.event.EventHandler<javafx.event.Event>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnShowing = false;
        this.obsrv1OnShowing = null;
        this.bound2OnShowing = true;
        this.obsrv2OnShowing = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#onShownProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOnShown(javafx.beans.value.ObservableValue<? extends javafx.event.EventHandler<javafx.event.Event>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnShown = true;
        this.obsrv1OnShown = source;
        this.bound2OnShown = false;
        this.obsrv2OnShown = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#onShownProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalOnShown(javafx.beans.property.Property<javafx.event.EventHandler<javafx.event.Event>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnShown = false;
        this.obsrv1OnShown = null;
        this.bound2OnShown = true;
        this.obsrv2OnShown = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#selectionModelProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindSelectionModel(javafx.beans.value.ObservableValue<? extends javafx.scene.control.SingleSelectionModel<T>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1SelectionModel = true;
        this.obsrv1SelectionModel = source;
        this.bound2SelectionModel = false;
        this.obsrv2SelectionModel = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#selectionModelProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalSelectionModel(javafx.beans.property.Property<javafx.scene.control.SingleSelectionModel<T>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1SelectionModel = false;
        this.obsrv1SelectionModel = null;
        this.bound2SelectionModel = true;
        this.obsrv2SelectionModel = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#valueProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindValue(javafx.beans.value.ObservableValue<? extends T> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Value = true;
        this.obsrv1Value = source;
        this.bound2Value = false;
        this.obsrv2Value = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link ChoiceBox#valueProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalValue(javafx.beans.property.Property<T> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Value = false;
        this.obsrv1Value = null;
        this.bound2Value = true;
        this.obsrv2Value = source;
        return (B) this;
    }

    /**
     * 建構{@link ChoiceBox}物件。
     *
     * @return 新的{@link ChoiceBox}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public ChoiceBox<T> build()
    {
        ChoiceBox<T> instance = new ChoiceBox<T>();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link ChoiceBox}物件。
     *
     * @return 新的{@link ChoiceBox}物件實體
     */
    @SuppressWarnings("unchecked")
    public ChoiceBox<T> build(javafx.collections.ObservableList<T> arg0)
    {
        ChoiceBox<T> instance = new ChoiceBox<T>(arg0);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
