// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.media;

/**
 * {@link Media}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link Media})
 * @param <B> 建構器本身的型態(需繼承{@link MediaMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class MediaMaker<Z extends Media, B extends MediaMaker<Z, B>>
        extends jxtn.jfx.makers.AbstractMaker<Z, B>
        implements MediaMakerExt<Z, B>
{

    private boolean hasOnError;
    private java.lang.Runnable valOnError;

    private boolean hasTracks;
    private java.util.Collection<javafx.scene.media.Track> valTracks;

    private boolean bound1OnError;
    private boolean bound2OnError;
    private javafx.beans.value.ObservableValue<? extends java.lang.Runnable> obsrv1OnError;
    private javafx.beans.property.Property<java.lang.Runnable> obsrv2OnError;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasOnError)
            instance.setOnError(this.valOnError);
        if (this.hasTracks)
            instance.getTracks().addAll(this.valTracks);
        if (this.bound1OnError)
            instance.onErrorProperty().bind(this.obsrv1OnError);
        if (this.bound2OnError)
            instance.onErrorProperty().bindBidirectional(this.obsrv2OnError);
    }

    /**
     * 設定屬性{@link Media#setOnError(java.lang.Runnable)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B onError(java.lang.Runnable value)
    {
        this.hasOnError = true;
        this.valOnError = value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link Media#getTracks}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     * @deprecated 屬性值並非{@link javafx.collections.ObservableList}
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public final B tracks(java.util.Collection<? extends javafx.scene.media.Track> value)
    {
        this.hasTracks = true;
        this.valTracks = (java.util.Collection<javafx.scene.media.Track>) value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link Media#getTracks}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B tracks(javafx.scene.media.Track... value)
    {
        this.hasTracks = true;
        this.valTracks = java.util.Arrays.asList(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link Media#getTracks}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B tracksAdd(java.util.Collection<? extends javafx.scene.media.Track> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasTracks = true;
        if (this.valTracks == null)
            this.valTracks = new java.util.ArrayList<>(value.size());
        this.valTracks.addAll(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link Media#getTracks}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B tracksAdd(javafx.scene.media.Track... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasTracks = true;
        if (this.valTracks == null)
            this.valTracks = new java.util.ArrayList<>(value.length);
        this.valTracks.addAll(java.util.Arrays.asList(value));
        return (B) this;
    }

    /**
     * 增加集合屬性{@link Media#getTracks}的內容，排除null項目。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B tracksAddNonNull(java.util.Collection<? extends javafx.scene.media.Track> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasTracks = true;
        if (this.valTracks == null)
            this.valTracks = new java.util.ArrayList<>(value.size());
        for (javafx.scene.media.Track i : value)
            if (i != null)
                this.valTracks.add(i);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link Media#getTracks}的內容，排除null項目。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B tracksAddNonNull(javafx.scene.media.Track... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasTracks = true;
        if (this.valTracks == null)
            this.valTracks = new java.util.ArrayList<>(value.length);
        for (javafx.scene.media.Track i : value)
            if (i != null)
                this.valTracks.add(i);
        return (B) this;
    }

    /**
     * 設定屬性{@link Media#onErrorProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOnError(javafx.beans.value.ObservableValue<? extends java.lang.Runnable> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnError = true;
        this.obsrv1OnError = source;
        this.bound2OnError = false;
        this.obsrv2OnError = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link Media#onErrorProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalOnError(javafx.beans.property.Property<java.lang.Runnable> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1OnError = false;
        this.obsrv1OnError = null;
        this.bound2OnError = true;
        this.obsrv2OnError = source;
        return (B) this;
    }

    /**
     * 建構{@link Media}物件。
     *
     * @return 新的{@link Media}物件實體
     */
    @SuppressWarnings("unchecked")
    public Media build(java.lang.String arg0)
    {
        Media instance = new Media(arg0);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}