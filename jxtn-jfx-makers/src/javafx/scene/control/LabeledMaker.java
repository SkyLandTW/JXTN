// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.control;

/**
 * {@link Labeled}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link Labeled})
 * @param <B> 建構器本身的型態(需繼承{@link LabeledMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class LabeledMaker<Z extends Labeled, B extends LabeledMaker<Z, B>>
        extends javafx.scene.control.ControlMaker<Z, B>
        implements LabeledMakerExt<Z, B>
{

    private boolean hasAlignment;
    private javafx.geometry.Pos valAlignment;

    private boolean hasContentDisplay;
    private javafx.scene.control.ContentDisplay valContentDisplay;

    private boolean hasEllipsisString;
    private java.lang.String valEllipsisString;

    private boolean hasFont;
    private javafx.scene.text.Font valFont;

    private boolean hasGraphic;
    private javafx.scene.Node valGraphic;

    private boolean hasGraphicTextGap;
    private double valGraphicTextGap;

    private boolean hasLineSpacing;
    private double valLineSpacing;

    private boolean hasMnemonicParsing;
    private boolean valMnemonicParsing;

    private boolean hasText;
    private java.lang.String valText;

    private boolean hasTextAlignment;
    private javafx.scene.text.TextAlignment valTextAlignment;

    private boolean hasTextFill;
    private javafx.scene.paint.Paint valTextFill;

    private boolean hasTextOverrun;
    private javafx.scene.control.OverrunStyle valTextOverrun;

    private boolean hasUnderline;
    private boolean valUnderline;

    private boolean hasWrapText;
    private boolean valWrapText;

    private boolean bound1Alignment;
    private boolean bound2Alignment;
    private javafx.beans.value.ObservableValue<? extends javafx.geometry.Pos> obsrv1Alignment;
    private javafx.beans.property.Property<javafx.geometry.Pos> obsrv2Alignment;

    private boolean bound1ContentDisplay;
    private boolean bound2ContentDisplay;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.control.ContentDisplay> obsrv1ContentDisplay;
    private javafx.beans.property.Property<javafx.scene.control.ContentDisplay> obsrv2ContentDisplay;

    private boolean bound1EllipsisString;
    private boolean bound2EllipsisString;
    private javafx.beans.value.ObservableValue<? extends String> obsrv1EllipsisString;
    private javafx.beans.property.Property<String> obsrv2EllipsisString;

    private boolean bound1Font;
    private boolean bound2Font;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.text.Font> obsrv1Font;
    private javafx.beans.property.Property<javafx.scene.text.Font> obsrv2Font;

    private boolean bound1Graphic;
    private boolean bound2Graphic;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.Node> obsrv1Graphic;
    private javafx.beans.property.Property<javafx.scene.Node> obsrv2Graphic;

    private boolean bound1GraphicTextGap;
    private boolean bound2GraphicTextGap;
    private javafx.beans.value.ObservableValue<? extends Number> obsrv1GraphicTextGap;
    private javafx.beans.property.Property<Number> obsrv2GraphicTextGap;

    private boolean bound1LineSpacing;
    private boolean bound2LineSpacing;
    private javafx.beans.value.ObservableValue<? extends Number> obsrv1LineSpacing;
    private javafx.beans.property.Property<Number> obsrv2LineSpacing;

    private boolean bound1MnemonicParsing;
    private boolean bound2MnemonicParsing;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1MnemonicParsing;
    private javafx.beans.property.Property<Boolean> obsrv2MnemonicParsing;

    private boolean bound1TextAlignment;
    private boolean bound2TextAlignment;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.text.TextAlignment> obsrv1TextAlignment;
    private javafx.beans.property.Property<javafx.scene.text.TextAlignment> obsrv2TextAlignment;

    private boolean bound1TextFill;
    private boolean bound2TextFill;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.paint.Paint> obsrv1TextFill;
    private javafx.beans.property.Property<javafx.scene.paint.Paint> obsrv2TextFill;

    private boolean bound1TextOverrun;
    private boolean bound2TextOverrun;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.control.OverrunStyle> obsrv1TextOverrun;
    private javafx.beans.property.Property<javafx.scene.control.OverrunStyle> obsrv2TextOverrun;

    private boolean bound1Text;
    private boolean bound2Text;
    private javafx.beans.value.ObservableValue<? extends String> obsrv1Text;
    private javafx.beans.property.Property<String> obsrv2Text;

    private boolean bound1Underline;
    private boolean bound2Underline;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1Underline;
    private javafx.beans.property.Property<Boolean> obsrv2Underline;

    private boolean bound1WrapText;
    private boolean bound2WrapText;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1WrapText;
    private javafx.beans.property.Property<Boolean> obsrv2WrapText;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasAlignment)
            instance.setAlignment(this.valAlignment);
        if (this.hasContentDisplay)
            instance.setContentDisplay(this.valContentDisplay);
        if (this.hasEllipsisString)
            instance.setEllipsisString(this.valEllipsisString);
        if (this.hasFont)
            instance.setFont(this.valFont);
        if (this.hasGraphic)
            instance.setGraphic(this.valGraphic);
        if (this.hasGraphicTextGap)
            instance.setGraphicTextGap(this.valGraphicTextGap);
        if (this.hasLineSpacing)
            instance.setLineSpacing(this.valLineSpacing);
        if (this.hasMnemonicParsing)
            instance.setMnemonicParsing(this.valMnemonicParsing);
        if (this.hasText)
            instance.setText(this.valText);
        if (this.hasTextAlignment)
            instance.setTextAlignment(this.valTextAlignment);
        if (this.hasTextFill)
            instance.setTextFill(this.valTextFill);
        if (this.hasTextOverrun)
            instance.setTextOverrun(this.valTextOverrun);
        if (this.hasUnderline)
            instance.setUnderline(this.valUnderline);
        if (this.hasWrapText)
            instance.setWrapText(this.valWrapText);
        if (this.bound1Alignment)
            instance.alignmentProperty().bind(this.obsrv1Alignment);
        if (this.bound2Alignment)
            instance.alignmentProperty().bindBidirectional(this.obsrv2Alignment);
        if (this.bound1ContentDisplay)
            instance.contentDisplayProperty().bind(this.obsrv1ContentDisplay);
        if (this.bound2ContentDisplay)
            instance.contentDisplayProperty().bindBidirectional(this.obsrv2ContentDisplay);
        if (this.bound1EllipsisString)
            instance.ellipsisStringProperty().bind(this.obsrv1EllipsisString);
        if (this.bound2EllipsisString)
            instance.ellipsisStringProperty().bindBidirectional(this.obsrv2EllipsisString);
        if (this.bound1Font)
            instance.fontProperty().bind(this.obsrv1Font);
        if (this.bound2Font)
            instance.fontProperty().bindBidirectional(this.obsrv2Font);
        if (this.bound1Graphic)
            instance.graphicProperty().bind(this.obsrv1Graphic);
        if (this.bound2Graphic)
            instance.graphicProperty().bindBidirectional(this.obsrv2Graphic);
        if (this.bound1GraphicTextGap)
            instance.graphicTextGapProperty().bind(this.obsrv1GraphicTextGap);
        if (this.bound2GraphicTextGap)
            instance.graphicTextGapProperty().bindBidirectional(this.obsrv2GraphicTextGap);
        if (this.bound1LineSpacing)
            instance.lineSpacingProperty().bind(this.obsrv1LineSpacing);
        if (this.bound2LineSpacing)
            instance.lineSpacingProperty().bindBidirectional(this.obsrv2LineSpacing);
        if (this.bound1MnemonicParsing)
            instance.mnemonicParsingProperty().bind(this.obsrv1MnemonicParsing);
        if (this.bound2MnemonicParsing)
            instance.mnemonicParsingProperty().bindBidirectional(this.obsrv2MnemonicParsing);
        if (this.bound1TextAlignment)
            instance.textAlignmentProperty().bind(this.obsrv1TextAlignment);
        if (this.bound2TextAlignment)
            instance.textAlignmentProperty().bindBidirectional(this.obsrv2TextAlignment);
        if (this.bound1TextFill)
            instance.textFillProperty().bind(this.obsrv1TextFill);
        if (this.bound2TextFill)
            instance.textFillProperty().bindBidirectional(this.obsrv2TextFill);
        if (this.bound1TextOverrun)
            instance.textOverrunProperty().bind(this.obsrv1TextOverrun);
        if (this.bound2TextOverrun)
            instance.textOverrunProperty().bindBidirectional(this.obsrv2TextOverrun);
        if (this.bound1Text)
            instance.textProperty().bind(this.obsrv1Text);
        if (this.bound2Text)
            instance.textProperty().bindBidirectional(this.obsrv2Text);
        if (this.bound1Underline)
            instance.underlineProperty().bind(this.obsrv1Underline);
        if (this.bound2Underline)
            instance.underlineProperty().bindBidirectional(this.obsrv2Underline);
        if (this.bound1WrapText)
            instance.wrapTextProperty().bind(this.obsrv1WrapText);
        if (this.bound2WrapText)
            instance.wrapTextProperty().bindBidirectional(this.obsrv2WrapText);
    }

    /**
     * 設定屬性{@link Labeled#setAlignment(javafx.geometry.Pos)}。
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
     * 設定屬性{@link Labeled#setContentDisplay(javafx.scene.control.ContentDisplay)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B contentDisplay(javafx.scene.control.ContentDisplay value)
    {
        this.hasContentDisplay = true;
        this.valContentDisplay = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#setEllipsisString(java.lang.String)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B ellipsisString(java.lang.String value)
    {
        this.hasEllipsisString = true;
        this.valEllipsisString = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#setFont(javafx.scene.text.Font)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B font(javafx.scene.text.Font value)
    {
        this.hasFont = true;
        this.valFont = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#setGraphic(javafx.scene.Node)}。
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
     * 設定屬性{@link Labeled#setGraphicTextGap(double)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B graphicTextGap(double value)
    {
        this.hasGraphicTextGap = true;
        this.valGraphicTextGap = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#setLineSpacing(double)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B lineSpacing(double value)
    {
        this.hasLineSpacing = true;
        this.valLineSpacing = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#setMnemonicParsing(boolean)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B mnemonicParsing(boolean value)
    {
        this.hasMnemonicParsing = true;
        this.valMnemonicParsing = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#setText(java.lang.String)}。
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
     * 設定屬性{@link Labeled#setTextAlignment(javafx.scene.text.TextAlignment)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B textAlignment(javafx.scene.text.TextAlignment value)
    {
        this.hasTextAlignment = true;
        this.valTextAlignment = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#setTextFill(javafx.scene.paint.Paint)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B textFill(javafx.scene.paint.Paint value)
    {
        this.hasTextFill = true;
        this.valTextFill = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#setTextOverrun(javafx.scene.control.OverrunStyle)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B textOverrun(javafx.scene.control.OverrunStyle value)
    {
        this.hasTextOverrun = true;
        this.valTextOverrun = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#setUnderline(boolean)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B underline(boolean value)
    {
        this.hasUnderline = true;
        this.valUnderline = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#setWrapText(boolean)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B wrapText(boolean value)
    {
        this.hasWrapText = true;
        this.valWrapText = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#alignmentProperty}的連結。
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
     * 設定屬性{@link Labeled#alignmentProperty}的雙向連結。
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
     * 設定屬性{@link Labeled#contentDisplayProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindContentDisplay(javafx.beans.value.ObservableValue<? extends javafx.scene.control.ContentDisplay> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1ContentDisplay = true;
        this.obsrv1ContentDisplay = source;
        this.bound2ContentDisplay = false;
        this.obsrv2ContentDisplay = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#contentDisplayProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalContentDisplay(javafx.beans.property.Property<javafx.scene.control.ContentDisplay> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1ContentDisplay = false;
        this.obsrv1ContentDisplay = null;
        this.bound2ContentDisplay = true;
        this.obsrv2ContentDisplay = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#ellipsisStringProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindEllipsisString(javafx.beans.value.ObservableValue<? extends String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1EllipsisString = true;
        this.obsrv1EllipsisString = source;
        this.bound2EllipsisString = false;
        this.obsrv2EllipsisString = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#ellipsisStringProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalEllipsisString(javafx.beans.property.Property<String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1EllipsisString = false;
        this.obsrv1EllipsisString = null;
        this.bound2EllipsisString = true;
        this.obsrv2EllipsisString = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#fontProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindFont(javafx.beans.value.ObservableValue<? extends javafx.scene.text.Font> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Font = true;
        this.obsrv1Font = source;
        this.bound2Font = false;
        this.obsrv2Font = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#fontProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalFont(javafx.beans.property.Property<javafx.scene.text.Font> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Font = false;
        this.obsrv1Font = null;
        this.bound2Font = true;
        this.obsrv2Font = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#graphicProperty}的連結。
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
     * 設定屬性{@link Labeled#graphicProperty}的雙向連結。
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
     * 設定屬性{@link Labeled#graphicTextGapProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindGraphicTextGap(javafx.beans.value.ObservableValue<? extends Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1GraphicTextGap = true;
        this.obsrv1GraphicTextGap = source;
        this.bound2GraphicTextGap = false;
        this.obsrv2GraphicTextGap = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#graphicTextGapProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalGraphicTextGap(javafx.beans.property.Property<Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1GraphicTextGap = false;
        this.obsrv1GraphicTextGap = null;
        this.bound2GraphicTextGap = true;
        this.obsrv2GraphicTextGap = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#lineSpacingProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindLineSpacing(javafx.beans.value.ObservableValue<? extends Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1LineSpacing = true;
        this.obsrv1LineSpacing = source;
        this.bound2LineSpacing = false;
        this.obsrv2LineSpacing = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#lineSpacingProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalLineSpacing(javafx.beans.property.Property<Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1LineSpacing = false;
        this.obsrv1LineSpacing = null;
        this.bound2LineSpacing = true;
        this.obsrv2LineSpacing = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#mnemonicParsingProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindMnemonicParsing(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1MnemonicParsing = true;
        this.obsrv1MnemonicParsing = source;
        this.bound2MnemonicParsing = false;
        this.obsrv2MnemonicParsing = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#mnemonicParsingProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalMnemonicParsing(javafx.beans.property.Property<Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1MnemonicParsing = false;
        this.obsrv1MnemonicParsing = null;
        this.bound2MnemonicParsing = true;
        this.obsrv2MnemonicParsing = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#textAlignmentProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindTextAlignment(javafx.beans.value.ObservableValue<? extends javafx.scene.text.TextAlignment> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1TextAlignment = true;
        this.obsrv1TextAlignment = source;
        this.bound2TextAlignment = false;
        this.obsrv2TextAlignment = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#textAlignmentProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalTextAlignment(javafx.beans.property.Property<javafx.scene.text.TextAlignment> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1TextAlignment = false;
        this.obsrv1TextAlignment = null;
        this.bound2TextAlignment = true;
        this.obsrv2TextAlignment = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#textFillProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindTextFill(javafx.beans.value.ObservableValue<? extends javafx.scene.paint.Paint> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1TextFill = true;
        this.obsrv1TextFill = source;
        this.bound2TextFill = false;
        this.obsrv2TextFill = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#textFillProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalTextFill(javafx.beans.property.Property<javafx.scene.paint.Paint> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1TextFill = false;
        this.obsrv1TextFill = null;
        this.bound2TextFill = true;
        this.obsrv2TextFill = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#textOverrunProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindTextOverrun(javafx.beans.value.ObservableValue<? extends javafx.scene.control.OverrunStyle> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1TextOverrun = true;
        this.obsrv1TextOverrun = source;
        this.bound2TextOverrun = false;
        this.obsrv2TextOverrun = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#textOverrunProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalTextOverrun(javafx.beans.property.Property<javafx.scene.control.OverrunStyle> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1TextOverrun = false;
        this.obsrv1TextOverrun = null;
        this.bound2TextOverrun = true;
        this.obsrv2TextOverrun = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#textProperty}的連結。
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
     * 設定屬性{@link Labeled#textProperty}的雙向連結。
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
     * 設定屬性{@link Labeled#underlineProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindUnderline(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Underline = true;
        this.obsrv1Underline = source;
        this.bound2Underline = false;
        this.obsrv2Underline = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#underlineProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalUnderline(javafx.beans.property.Property<Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Underline = false;
        this.obsrv1Underline = null;
        this.bound2Underline = true;
        this.obsrv2Underline = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#wrapTextProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindWrapText(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1WrapText = true;
        this.obsrv1WrapText = source;
        this.bound2WrapText = false;
        this.obsrv2WrapText = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Labeled#wrapTextProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalWrapText(javafx.beans.property.Property<Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1WrapText = false;
        this.obsrv1WrapText = null;
        this.bound2WrapText = true;
        this.obsrv2WrapText = source;
        return (B) this;
    }
}
