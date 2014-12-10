// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.stage;

/**
 * {@link FileChooser}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link FileChooser})
 * @param <B> 建構器本身的型態(需繼承{@link FileChooserBuilder})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class FileChooserBuilder<Z extends FileChooser, B extends FileChooserBuilder<Z, B>>
        extends jxtn.jfx.builders.AbstractBuilder<Z, B>
        implements FileChooserBuilderExt<Z, B>
{

    private boolean hasExtensionFilters;
    private java.util.Collection<javafx.stage.FileChooser.ExtensionFilter> valExtensionFilters;

    private boolean hasInitialDirectory;
    private java.io.File valInitialDirectory;

    private boolean hasInitialFileName;
    private java.lang.String valInitialFileName;

    private boolean hasSelectedExtensionFilter;
    private javafx.stage.FileChooser.ExtensionFilter valSelectedExtensionFilter;

    private boolean hasTitle;
    private java.lang.String valTitle;

    private boolean bound1InitialDirectory;
    private boolean bound2InitialDirectory;
    private javafx.beans.value.ObservableValue<? extends java.io.File> obsrv1InitialDirectory;
    private javafx.beans.property.Property<java.io.File> obsrv2InitialDirectory;

    private boolean bound1InitialFileName;
    private boolean bound2InitialFileName;
    private javafx.beans.value.ObservableValue<? extends java.lang.String> obsrv1InitialFileName;
    private javafx.beans.property.Property<java.lang.String> obsrv2InitialFileName;

    private boolean bound1SelectedExtensionFilter;
    private boolean bound2SelectedExtensionFilter;
    private javafx.beans.value.ObservableValue<? extends javafx.stage.FileChooser.ExtensionFilter> obsrv1SelectedExtensionFilter;
    private javafx.beans.property.Property<javafx.stage.FileChooser.ExtensionFilter> obsrv2SelectedExtensionFilter;

    private boolean bound1Title;
    private boolean bound2Title;
    private javafx.beans.value.ObservableValue<? extends String> obsrv1Title;
    private javafx.beans.property.Property<String> obsrv2Title;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasExtensionFilters)
            instance.getExtensionFilters().addAll(this.valExtensionFilters);
        if (this.hasInitialDirectory)
            instance.setInitialDirectory(this.valInitialDirectory);
        if (this.hasInitialFileName)
            instance.setInitialFileName(this.valInitialFileName);
        if (this.hasSelectedExtensionFilter)
            instance.setSelectedExtensionFilter(this.valSelectedExtensionFilter);
        if (this.hasTitle)
            instance.setTitle(this.valTitle);
        if (this.bound1InitialDirectory)
            instance.initialDirectoryProperty().bind(this.obsrv1InitialDirectory);
        if (this.bound2InitialDirectory)
            instance.initialDirectoryProperty().bindBidirectional(this.obsrv2InitialDirectory);
        if (this.bound1InitialFileName)
            instance.initialFileNameProperty().bind(this.obsrv1InitialFileName);
        if (this.bound2InitialFileName)
            instance.initialFileNameProperty().bindBidirectional(this.obsrv2InitialFileName);
        if (this.bound1SelectedExtensionFilter)
            instance.selectedExtensionFilterProperty().bind(this.obsrv1SelectedExtensionFilter);
        if (this.bound2SelectedExtensionFilter)
            instance.selectedExtensionFilterProperty().bindBidirectional(this.obsrv2SelectedExtensionFilter);
        if (this.bound1Title)
            instance.titleProperty().bind(this.obsrv1Title);
        if (this.bound2Title)
            instance.titleProperty().bindBidirectional(this.obsrv2Title);
    }

    /**
     * 設定集合屬性{@link FileChooser#getExtensionFilters}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     * @deprecated 屬性值並非{@link javafx.collections.ObservableList}
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public final B extensionFilters(java.util.Collection<? extends javafx.stage.FileChooser.ExtensionFilter> value)
    {
        this.hasExtensionFilters = true;
        this.valExtensionFilters = (java.util.Collection<javafx.stage.FileChooser.ExtensionFilter>) value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link FileChooser#getExtensionFilters}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B extensionFilters(javafx.stage.FileChooser.ExtensionFilter... value)
    {
        this.hasExtensionFilters = true;
        this.valExtensionFilters = java.util.Arrays.asList(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link FileChooser#getExtensionFilters}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B extensionFiltersAdd(java.util.Collection<? extends javafx.stage.FileChooser.ExtensionFilter> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasExtensionFilters = true;
        if (this.valExtensionFilters == null)
            this.valExtensionFilters = new java.util.ArrayList<>(value.size());
        this.valExtensionFilters.addAll(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link FileChooser#getExtensionFilters}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B extensionFiltersAdd(javafx.stage.FileChooser.ExtensionFilter... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasExtensionFilters = true;
        if (this.valExtensionFilters == null)
            this.valExtensionFilters = new java.util.ArrayList<>(value.length);
        this.valExtensionFilters.addAll(java.util.Arrays.asList(value));
        return (B) this;
    }

    /**
     * 增加集合屬性{@link FileChooser#getExtensionFilters}的內容，排除null項目。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B extensionFiltersAddNonNull(java.util.Collection<? extends javafx.stage.FileChooser.ExtensionFilter> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasExtensionFilters = true;
        if (this.valExtensionFilters == null)
            this.valExtensionFilters = new java.util.ArrayList<>(value.size());
        for (javafx.stage.FileChooser.ExtensionFilter i : value)
            if (i != null)
                this.valExtensionFilters.add(i);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link FileChooser#getExtensionFilters}的內容，排除null項目。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B extensionFiltersAddNonNull(javafx.stage.FileChooser.ExtensionFilter... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasExtensionFilters = true;
        if (this.valExtensionFilters == null)
            this.valExtensionFilters = new java.util.ArrayList<>(value.length);
        for (javafx.stage.FileChooser.ExtensionFilter i : value)
            if (i != null)
                this.valExtensionFilters.add(i);
        return (B) this;
    }

    /**
     * 設定屬性{@link FileChooser#setInitialDirectory(java.io.File)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B initialDirectory(java.io.File value)
    {
        this.hasInitialDirectory = true;
        this.valInitialDirectory = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link FileChooser#setInitialFileName(java.lang.String)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B initialFileName(java.lang.String value)
    {
        this.hasInitialFileName = true;
        this.valInitialFileName = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link FileChooser#setSelectedExtensionFilter(javafx.stage.FileChooser.ExtensionFilter)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B selectedExtensionFilter(javafx.stage.FileChooser.ExtensionFilter value)
    {
        this.hasSelectedExtensionFilter = true;
        this.valSelectedExtensionFilter = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link FileChooser#setTitle(java.lang.String)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B title(java.lang.String value)
    {
        this.hasTitle = true;
        this.valTitle = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link FileChooser#initialDirectoryProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindInitialDirectory(javafx.beans.value.ObservableValue<? extends java.io.File> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1InitialDirectory = true;
        this.obsrv1InitialDirectory = source;
        this.bound2InitialDirectory = false;
        this.obsrv2InitialDirectory = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link FileChooser#initialDirectoryProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalInitialDirectory(javafx.beans.property.Property<java.io.File> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1InitialDirectory = false;
        this.obsrv1InitialDirectory = null;
        this.bound2InitialDirectory = true;
        this.obsrv2InitialDirectory = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link FileChooser#initialFileNameProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindInitialFileName(javafx.beans.value.ObservableValue<? extends java.lang.String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1InitialFileName = true;
        this.obsrv1InitialFileName = source;
        this.bound2InitialFileName = false;
        this.obsrv2InitialFileName = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link FileChooser#initialFileNameProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalInitialFileName(javafx.beans.property.Property<java.lang.String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1InitialFileName = false;
        this.obsrv1InitialFileName = null;
        this.bound2InitialFileName = true;
        this.obsrv2InitialFileName = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link FileChooser#selectedExtensionFilterProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindSelectedExtensionFilter(javafx.beans.value.ObservableValue<? extends javafx.stage.FileChooser.ExtensionFilter> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1SelectedExtensionFilter = true;
        this.obsrv1SelectedExtensionFilter = source;
        this.bound2SelectedExtensionFilter = false;
        this.obsrv2SelectedExtensionFilter = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link FileChooser#selectedExtensionFilterProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalSelectedExtensionFilter(javafx.beans.property.Property<javafx.stage.FileChooser.ExtensionFilter> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1SelectedExtensionFilter = false;
        this.obsrv1SelectedExtensionFilter = null;
        this.bound2SelectedExtensionFilter = true;
        this.obsrv2SelectedExtensionFilter = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link FileChooser#titleProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindTitle(javafx.beans.value.ObservableValue<? extends String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Title = true;
        this.obsrv1Title = source;
        this.bound2Title = false;
        this.obsrv2Title = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link FileChooser#titleProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalTitle(javafx.beans.property.Property<String> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Title = false;
        this.obsrv1Title = null;
        this.bound2Title = true;
        this.obsrv2Title = source;
        return (B) this;
    }

    /**
     * 建構{@link FileChooser}物件。
     *
     * @return 新的{@link FileChooser}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public FileChooser build()
    {
        FileChooser instance = new FileChooser();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
