// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.control.cell;

/**
 * {@link TextFieldTreeTableCell}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link TextFieldTreeTableCell})
 * @param <B> 建構器本身的型態(需繼承{@link TextFieldTreeTableCellMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class TextFieldTreeTableCellMaker<S extends java.lang.Object, T extends java.lang.Object, Z extends TextFieldTreeTableCell<S, T>, B extends TextFieldTreeTableCellMaker<S, T, Z, B>>
        extends javafx.scene.control.TreeTableCellMaker<S, T, Z, B>
        implements TextFieldTreeTableCellMakerExt<S, T, Z, B>
{

    private boolean hasConverter;
    private javafx.util.StringConverter<T> valConverter;

    private boolean bound1Converter;
    private boolean bound2Converter;
    private javafx.beans.value.ObservableValue<? extends javafx.util.StringConverter<T>> obsrv1Converter;
    private javafx.beans.property.Property<javafx.util.StringConverter<T>> obsrv2Converter;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasConverter)
            instance.setConverter(this.valConverter);
        if (this.bound1Converter)
            instance.converterProperty().bind(this.obsrv1Converter);
        if (this.bound2Converter)
            instance.converterProperty().bindBidirectional(this.obsrv2Converter);
    }

    /**
     * 設定屬性{@link TextFieldTreeTableCell#setConverter(javafx.util.StringConverter)}。
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
     * 設定屬性{@link TextFieldTreeTableCell#converterProperty}的連結。
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
     * 設定屬性{@link TextFieldTreeTableCell#converterProperty}的雙向連結。
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
     * 建構{@link TextFieldTreeTableCell}物件。
     *
     * @return 新的{@link TextFieldTreeTableCell}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public TextFieldTreeTableCell<S, T> build()
    {
        TextFieldTreeTableCell<S, T> instance = new TextFieldTreeTableCell<S, T>();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link TextFieldTreeTableCell}物件。
     *
     * @return 新的{@link TextFieldTreeTableCell}物件實體
     */
    @SuppressWarnings("unchecked")
    public TextFieldTreeTableCell<S, T> build(javafx.util.StringConverter<T> arg0)
    {
        TextFieldTreeTableCell<S, T> instance = new TextFieldTreeTableCell<S, T>(arg0);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
