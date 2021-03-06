// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.control;

/**
 * {@link Button}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link Button})
 * @param <B> 建構器本身的型態(需繼承{@link ButtonMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class ButtonMaker<Z extends Button, B extends ButtonMaker<Z, B>>
        extends javafx.scene.control.ButtonBaseMaker<Z, B>
        implements ButtonMakerExt<Z, B>
{

    private boolean hasCancelButton;
    private boolean valCancelButton;

    private boolean hasDefaultButton;
    private boolean valDefaultButton;

    private boolean bound1CancelButton;
    private boolean bound2CancelButton;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1CancelButton;
    private javafx.beans.property.Property<Boolean> obsrv2CancelButton;

    private boolean bound1DefaultButton;
    private boolean bound2DefaultButton;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1DefaultButton;
    private javafx.beans.property.Property<Boolean> obsrv2DefaultButton;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasCancelButton)
            instance.setCancelButton(this.valCancelButton);
        if (this.hasDefaultButton)
            instance.setDefaultButton(this.valDefaultButton);
        if (this.bound1CancelButton)
            instance.cancelButtonProperty().bind(this.obsrv1CancelButton);
        if (this.bound2CancelButton)
            instance.cancelButtonProperty().bindBidirectional(this.obsrv2CancelButton);
        if (this.bound1DefaultButton)
            instance.defaultButtonProperty().bind(this.obsrv1DefaultButton);
        if (this.bound2DefaultButton)
            instance.defaultButtonProperty().bindBidirectional(this.obsrv2DefaultButton);
    }

    /**
     * 設定屬性{@link Button#setCancelButton(boolean)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B cancelButton(boolean value)
    {
        this.hasCancelButton = true;
        this.valCancelButton = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Button#setDefaultButton(boolean)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B defaultButton(boolean value)
    {
        this.hasDefaultButton = true;
        this.valDefaultButton = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Button#cancelButtonProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindCancelButton(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1CancelButton = true;
        this.obsrv1CancelButton = source;
        this.bound2CancelButton = false;
        this.obsrv2CancelButton = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Button#cancelButtonProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalCancelButton(javafx.beans.property.Property<Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1CancelButton = false;
        this.obsrv1CancelButton = null;
        this.bound2CancelButton = true;
        this.obsrv2CancelButton = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Button#defaultButtonProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindDefaultButton(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1DefaultButton = true;
        this.obsrv1DefaultButton = source;
        this.bound2DefaultButton = false;
        this.obsrv2DefaultButton = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Button#defaultButtonProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalDefaultButton(javafx.beans.property.Property<Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1DefaultButton = false;
        this.obsrv1DefaultButton = null;
        this.bound2DefaultButton = true;
        this.obsrv2DefaultButton = source;
        return (B) this;
    }

    /**
     * 建構{@link Button}物件。
     *
     * @return 新的{@link Button}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public Button build()
    {
        Button instance = new Button();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link Button}物件。
     *
     * @return 新的{@link Button}物件實體
     */
    @SuppressWarnings("unchecked")
    public Button build(java.lang.String arg0)
    {
        Button instance = new Button(arg0);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link Button}物件。
     *
     * @return 新的{@link Button}物件實體
     */
    @SuppressWarnings("unchecked")
    public Button build(java.lang.String arg0, javafx.scene.Node arg1)
    {
        Button instance = new Button(arg0, arg1);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
