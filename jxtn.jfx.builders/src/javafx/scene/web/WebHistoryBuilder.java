// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.web;

/**
 * {@link WebHistory}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link WebHistory})
 * @param <B> 建構器本身的型態(需繼承{@link WebHistoryBuilder})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class WebHistoryBuilder<Z extends WebHistory, B extends WebHistoryBuilder<Z, B>>
        extends jxtn.jfx.builders.AbstractBuilder<Z, B>
        implements WebHistoryBuilderExt<Z, B>
{

    private boolean hasEntries;
    private java.util.Collection<javafx.scene.web.WebHistory.Entry> valEntries;

    private boolean hasMaxSize;
    private int valMaxSize;

    private boolean bound1MaxSize;
    private boolean bound2MaxSize;
    private javafx.beans.value.ObservableValue<? extends Number> obsrv1MaxSize;
    private javafx.beans.property.Property<Number> obsrv2MaxSize;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasEntries)
            instance.getEntries().addAll(this.valEntries);
        if (this.hasMaxSize)
            instance.setMaxSize(this.valMaxSize);
        if (this.bound1MaxSize)
            instance.maxSizeProperty().bind(this.obsrv1MaxSize);
        if (this.bound2MaxSize)
            instance.maxSizeProperty().bindBidirectional(this.obsrv2MaxSize);
    }

    /**
     * 設定集合屬性{@link WebHistory#getEntries}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     * @deprecated 屬性值並非{@link javafx.collections.ObservableList}
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public final B entries(java.util.Collection<? extends javafx.scene.web.WebHistory.Entry> value)
    {
        this.hasEntries = true;
        this.valEntries = (java.util.Collection<javafx.scene.web.WebHistory.Entry>) value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link WebHistory#getEntries}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B entries(javafx.scene.web.WebHistory.Entry... value)
    {
        this.hasEntries = true;
        this.valEntries = java.util.Arrays.asList(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link WebHistory#getEntries}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B entriesAdd(java.util.Collection<? extends javafx.scene.web.WebHistory.Entry> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasEntries = true;
        if (this.valEntries == null)
            this.valEntries = new java.util.ArrayList<>(value.size());
        this.valEntries.addAll(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link WebHistory#getEntries}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B entriesAdd(javafx.scene.web.WebHistory.Entry... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasEntries = true;
        if (this.valEntries == null)
            this.valEntries = new java.util.ArrayList<>(value.length);
        this.valEntries.addAll(java.util.Arrays.asList(value));
        return (B) this;
    }

    /**
     * 增加集合屬性{@link WebHistory#getEntries}的內容，排除null項目。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B entriesAddNonNull(java.util.Collection<? extends javafx.scene.web.WebHistory.Entry> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasEntries = true;
        if (this.valEntries == null)
            this.valEntries = new java.util.ArrayList<>(value.size());
        for (javafx.scene.web.WebHistory.Entry i : value)
            if (i != null)
                this.valEntries.add(i);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link WebHistory#getEntries}的內容，排除null項目。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B entriesAddNonNull(javafx.scene.web.WebHistory.Entry... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasEntries = true;
        if (this.valEntries == null)
            this.valEntries = new java.util.ArrayList<>(value.length);
        for (javafx.scene.web.WebHistory.Entry i : value)
            if (i != null)
                this.valEntries.add(i);
        return (B) this;
    }

    /**
     * 設定屬性{@link WebHistory#setMaxSize(int)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B maxSize(int value)
    {
        this.hasMaxSize = true;
        this.valMaxSize = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link WebHistory#maxSizeProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindMaxSize(javafx.beans.value.ObservableValue<? extends Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1MaxSize = true;
        this.obsrv1MaxSize = source;
        this.bound2MaxSize = false;
        this.obsrv2MaxSize = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link WebHistory#maxSizeProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalMaxSize(javafx.beans.property.Property<Number> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1MaxSize = false;
        this.obsrv1MaxSize = null;
        this.bound2MaxSize = true;
        this.obsrv2MaxSize = source;
        return (B) this;
    }
}
