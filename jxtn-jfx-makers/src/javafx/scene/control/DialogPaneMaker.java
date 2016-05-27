// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.control;

/**
 * {@link DialogPane}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link DialogPane})
 * @param <B> 建構器本身的型態(需繼承{@link DialogPaneMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class DialogPaneMaker<Z extends DialogPane, B extends DialogPaneMaker<Z, B>>
        extends javafx.scene.layout.PaneMaker<Z, B>
        implements DialogPaneMakerExt<Z, B>
{

    private boolean hasButtonTypes;
    private java.util.Collection<javafx.scene.control.ButtonType> valButtonTypes;

    private boolean hasContent;
    private javafx.scene.Node valContent;

    private boolean hasContentText;
    private java.lang.String valContentText;

    private boolean hasExpandableContent;
    private javafx.scene.Node valExpandableContent;

    private boolean hasExpanded;
    private boolean valExpanded;

    private boolean hasGraphic;
    private javafx.scene.Node valGraphic;

    private boolean hasHeader;
    private javafx.scene.Node valHeader;

    private boolean hasHeaderText;
    private java.lang.String valHeaderText;

    private boolean bound1Content;
    private boolean bound2Content;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.Node> obsrv1Content;
    private javafx.beans.property.Property<javafx.scene.Node> obsrv2Content;

    private boolean bound1ContentText;
    private boolean bound2ContentText;
    private javafx.beans.value.ObservableValue<? extends String> obsrv1ContentText;
    private javafx.beans.property.Property<String> obsrv2ContentText;

    private boolean bound1ExpandableContent;
    private boolean bound2ExpandableContent;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.Node> obsrv1ExpandableContent;
    private javafx.beans.property.Property<javafx.scene.Node> obsrv2ExpandableContent;

    private boolean bound1Expanded;
    private boolean bound2Expanded;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1Expanded;
    private javafx.beans.property.Property<Boolean> obsrv2Expanded;

    private boolean bound1Graphic;
    private boolean bound2Graphic;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.Node> obsrv1Graphic;
    private javafx.beans.property.Property<javafx.scene.Node> obsrv2Graphic;

    private boolean bound1Header;
    private boolean bound2Header;
    private javafx.beans.value.ObservableValue<? extends javafx.scene.Node> obsrv1Header;
    private javafx.beans.property.Property<javafx.scene.Node> obsrv2Header;

    private boolean bound1HeaderText;
    private boolean bound2HeaderText;
    private javafx.beans.value.ObservableValue<? extends String> obsrv1HeaderText;
    private javafx.beans.property.Property<String> obsrv2HeaderText;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasButtonTypes)
            instance.getButtonTypes().addAll(this.valButtonTypes);
        if (this.hasContent)
            instance.setContent(this.valContent);
        if (this.hasContentText)
            instance.setContentText(this.valContentText);
        if (this.hasExpandableContent)
            instance.setExpandableContent(this.valExpandableContent);
        if (this.hasExpanded)
            instance.setExpanded(this.valExpanded);
        if (this.hasGraphic)
            instance.setGraphic(this.valGraphic);
        if (this.hasHeader)
            instance.setHeader(this.valHeader);
        if (this.hasHeaderText)
            instance.setHeaderText(this.valHeaderText);
        if (this.bound1Content)
            instance.contentProperty().bind(this.obsrv1Content);
        if (this.bound2Content)
            instance.contentProperty().bindBidirectional(this.obsrv2Content);
        if (this.bound1ContentText)
            instance.contentTextProperty().bind(this.obsrv1ContentText);
        if (this.bound2ContentText)
            instance.contentTextProperty().bindBidirectional(this.obsrv2ContentText);
        if (this.bound1ExpandableContent)
            instance.expandableContentProperty().bind(this.obsrv1ExpandableContent);
        if (this.bound2ExpandableContent)
            instance.expandableContentProperty().bindBidirectional(this.obsrv2ExpandableContent);
        if (this.bound1Expanded)
            instance.expandedProperty().bind(this.obsrv1Expanded);
        if (this.bound2Expanded)
            instance.expandedProperty().bindBidirectional(this.obsrv2Expanded);
        if (this.bound1Graphic)
            instance.graphicProperty().bind(this.obsrv1Graphic);
        if (this.bound2Graphic)
            instance.graphicProperty().bindBidirectional(this.obsrv2Graphic);
        if (this.bound1Header)
            instance.headerProperty().bind(this.obsrv1Header);
        if (this.bound2Header)
            instance.headerProperty().bindBidirectional(this.obsrv2Header);
        if (this.bound1HeaderText)
            instance.headerTextProperty().bind(this.obsrv1HeaderText);
        if (this.bound2HeaderText)
            instance.headerTextProperty().bindBidirectional(this.obsrv2HeaderText);
    }

    /**
     * 設定集合屬性{@link DialogPane#getButtonTypes}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     * @deprecated 屬性值並非{@link javafx.collections.ObservableList}
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public final B buttonTypes(java.util.Collection<? extends javafx.scene.control.ButtonType> value)
    {
        this.hasButtonTypes = true;
        this.valButtonTypes = (java.util.Collection<javafx.scene.control.ButtonType>) value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link DialogPane#getButtonTypes}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B buttonTypes(javafx.scene.control.ButtonType... value)
    {
        this.hasButtonTypes = true;
        this.valButtonTypes = java.util.Arrays.asList(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link DialogPane#getButtonTypes}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B buttonTypesAdd(java.util.Collection<? extends javafx.scene.control.ButtonType> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasButtonTypes = true;
        if (this.valButtonTypes == null)
            this.valButtonTypes = new java.util.ArrayList<>(value.size());
        this.valButtonTypes.addAll(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link DialogPane#getButtonTypes}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B buttonTypesAdd(javafx.scene.control.ButtonType... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasButtonTypes = true;
        if (this.valButtonTypes == null)
            this.valButtonTypes = new java.util.ArrayList<>(value.length);
        this.valButtonTypes.addAll(java.util.Arrays.asList(value));
        return (B) this;
    }

    /**
     * 增加集合屬性{@link DialogPane#getButtonTypes}的內容，排除null項目。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B buttonTypesAddNonNull(java.util.Collection<? extends javafx.scene.control.ButtonType> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasButtonTypes = true;
        if (this.valButtonTypes == null)
            this.valButtonTypes = new java.util.ArrayList<>(value.size());
        for (javafx.scene.control.ButtonType i : value)
            if (i != null)
                this.valButtonTypes.add(i);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link DialogPane#getButtonTypes}的內容，排除null項目。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B buttonTypesAddNonNull(javafx.scene.control.ButtonType... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasButtonTypes = true;
        if (this.valButtonTypes == null)
            this.valButtonTypes = new java.util.ArrayList<>(value.length);
        for (javafx.scene.control.ButtonType i : value)
            if (i != null)
                this.valButtonTypes.add(i);
        return (B) this;
    }

    /**
     * 設定屬性{@link DialogPane#setContent(javafx.scene.Node)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B content(javafx.scene.Node value)
    {
        this.hasContent = true;
        this.valContent = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link DialogPane#setContentText(java.lang.String)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B contentText(java.lang.String value)
    {
        this.hasContentText = true;
        this.valContentText = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link DialogPane#setExpandableContent(javafx.scene.Node)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B expandableContent(javafx.scene.Node value)
    {
        this.hasExpandableContent = true;
        this.valExpandableContent = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link DialogPane#setExpanded(boolean)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B expanded(boolean value)
    {
        this.hasExpanded = true;
        this.valExpanded = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link DialogPane#setGraphic(javafx.scene.Node)}。
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
     * 設定屬性{@link DialogPane#setHeader(javafx.scene.Node)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B header(javafx.scene.Node value)
    {
        this.hasHeader = true;
        this.valHeader = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link DialogPane#setHeaderText(java.lang.String)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B headerText(java.lang.String value)
    {
        this.hasHeaderText = true;
        this.valHeaderText = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link DialogPane#contentProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindContent(javafx.beans.value.ObservableValue<? extends javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Content = true;
        this.obsrv1Content = source;
        this.bound2Content = false;
        this.obsrv2Content = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link DialogPane#contentProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalContent(javafx.beans.property.Property<javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Content = false;
        this.obsrv1Content = null;
        this.bound2Content = true;
        this.obsrv2Content = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link DialogPane#contentTextProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindContentText(javafx.beans.value.ObservableValue<? extends String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1ContentText = true;
        this.obsrv1ContentText = source;
        this.bound2ContentText = false;
        this.obsrv2ContentText = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link DialogPane#contentTextProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalContentText(javafx.beans.property.Property<String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1ContentText = false;
        this.obsrv1ContentText = null;
        this.bound2ContentText = true;
        this.obsrv2ContentText = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link DialogPane#expandableContentProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindExpandableContent(javafx.beans.value.ObservableValue<? extends javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1ExpandableContent = true;
        this.obsrv1ExpandableContent = source;
        this.bound2ExpandableContent = false;
        this.obsrv2ExpandableContent = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link DialogPane#expandableContentProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalExpandableContent(javafx.beans.property.Property<javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1ExpandableContent = false;
        this.obsrv1ExpandableContent = null;
        this.bound2ExpandableContent = true;
        this.obsrv2ExpandableContent = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link DialogPane#expandedProperty}的連結。
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
     * 設定屬性{@link DialogPane#expandedProperty}的雙向連結。
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
     * 設定屬性{@link DialogPane#graphicProperty}的連結。
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
     * 設定屬性{@link DialogPane#graphicProperty}的雙向連結。
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
     * 設定屬性{@link DialogPane#headerProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindHeader(javafx.beans.value.ObservableValue<? extends javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Header = true;
        this.obsrv1Header = source;
        this.bound2Header = false;
        this.obsrv2Header = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link DialogPane#headerProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalHeader(javafx.beans.property.Property<javafx.scene.Node> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Header = false;
        this.obsrv1Header = null;
        this.bound2Header = true;
        this.obsrv2Header = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link DialogPane#headerTextProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindHeaderText(javafx.beans.value.ObservableValue<? extends String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1HeaderText = true;
        this.obsrv1HeaderText = source;
        this.bound2HeaderText = false;
        this.obsrv2HeaderText = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link DialogPane#headerTextProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalHeaderText(javafx.beans.property.Property<String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1HeaderText = false;
        this.obsrv1HeaderText = null;
        this.bound2HeaderText = true;
        this.obsrv2HeaderText = source;
        return (B) this;
    }

    /**
     * 建構{@link DialogPane}物件。
     *
     * @return 新的{@link DialogPane}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public DialogPane build()
    {
        DialogPane instance = new DialogPane();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}