// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package org.controlsfx.control;

/**
 * {@link SegmentedBar}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version controlsfx-8.40.14.jar
 * @param <Z> 要建構的物件型態(需繼承{@link SegmentedBar})
 * @param <B> 建構器本身的型態(需繼承{@link SegmentedBarMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class SegmentedBarMaker<T extends org.controlsfx.control.SegmentedBar.Segment, Z extends SegmentedBar<T>, B extends SegmentedBarMaker<T, Z, B>>
        extends javafx.scene.control.ControlMaker<Z, B>
        implements SegmentedBarMakerExt<T, Z, B>
{

    private boolean hasInfoNodeFactory;
    private javafx.util.Callback<T, javafx.scene.Node> valInfoNodeFactory;

    private boolean hasOrientation;
    private javafx.geometry.Orientation valOrientation;

    private boolean hasSegmentViewFactory;
    private javafx.util.Callback<T, javafx.scene.Node> valSegmentViewFactory;

    private boolean hasSegments;
    private javafx.collections.ObservableList<T> valSegments;

    private boolean bound1InfoNodeFactory;
    private boolean bound2InfoNodeFactory;
    private javafx.beans.value.ObservableValue<? extends javafx.util.Callback<T, javafx.scene.Node>> obsrv1InfoNodeFactory;
    private javafx.beans.property.Property<javafx.util.Callback<T, javafx.scene.Node>> obsrv2InfoNodeFactory;

    private boolean bound1Orientation;
    private boolean bound2Orientation;
    private javafx.beans.value.ObservableValue<? extends javafx.geometry.Orientation> obsrv1Orientation;
    private javafx.beans.property.Property<javafx.geometry.Orientation> obsrv2Orientation;

    private boolean bound1SegmentViewFactory;
    private boolean bound2SegmentViewFactory;
    private javafx.beans.value.ObservableValue<? extends javafx.util.Callback<T, javafx.scene.Node>> obsrv1SegmentViewFactory;
    private javafx.beans.property.Property<javafx.util.Callback<T, javafx.scene.Node>> obsrv2SegmentViewFactory;

    private boolean bound1Segments;
    private boolean bound2Segments;
    private javafx.beans.value.ObservableValue<? extends javafx.collections.ObservableList<T>> obsrv1Segments;
    private javafx.beans.property.Property<javafx.collections.ObservableList<T>> obsrv2Segments;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasInfoNodeFactory)
            instance.setInfoNodeFactory(this.valInfoNodeFactory);
        if (this.hasOrientation)
            instance.setOrientation(this.valOrientation);
        if (this.hasSegmentViewFactory)
            instance.setSegmentViewFactory(this.valSegmentViewFactory);
        if (this.hasSegments)
            instance.setSegments(this.valSegments);
        if (this.bound1InfoNodeFactory)
            instance.infoNodeFactoryProperty().bind(this.obsrv1InfoNodeFactory);
        if (this.bound2InfoNodeFactory)
            instance.infoNodeFactoryProperty().bindBidirectional(this.obsrv2InfoNodeFactory);
        if (this.bound1Orientation)
            instance.orientationProperty().bind(this.obsrv1Orientation);
        if (this.bound2Orientation)
            instance.orientationProperty().bindBidirectional(this.obsrv2Orientation);
        if (this.bound1SegmentViewFactory)
            instance.segmentViewFactoryProperty().bind(this.obsrv1SegmentViewFactory);
        if (this.bound2SegmentViewFactory)
            instance.segmentViewFactoryProperty().bindBidirectional(this.obsrv2SegmentViewFactory);
        if (this.bound1Segments)
            instance.segmentsProperty().bind(this.obsrv1Segments);
        if (this.bound2Segments)
            instance.segmentsProperty().bindBidirectional(this.obsrv2Segments);
    }

    /**
     * 設定屬性{@link SegmentedBar#setInfoNodeFactory(javafx.util.Callback)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B infoNodeFactory(javafx.util.Callback<T, javafx.scene.Node> value)
    {
        this.hasInfoNodeFactory = true;
        this.valInfoNodeFactory = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link SegmentedBar#setOrientation(javafx.geometry.Orientation)}。
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
     * 設定屬性{@link SegmentedBar#setSegmentViewFactory(javafx.util.Callback)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B segmentViewFactory(javafx.util.Callback<T, javafx.scene.Node> value)
    {
        this.hasSegmentViewFactory = true;
        this.valSegmentViewFactory = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link SegmentedBar#setSegments(javafx.collections.ObservableList)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B segments(javafx.collections.ObservableList<T> value)
    {
        this.hasSegments = true;
        this.valSegments = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link SegmentedBar#infoNodeFactoryProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindInfoNodeFactory(javafx.beans.value.ObservableValue<? extends javafx.util.Callback<T, javafx.scene.Node>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1InfoNodeFactory = true;
        this.obsrv1InfoNodeFactory = source;
        this.bound2InfoNodeFactory = false;
        this.obsrv2InfoNodeFactory = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link SegmentedBar#infoNodeFactoryProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalInfoNodeFactory(javafx.beans.property.Property<javafx.util.Callback<T, javafx.scene.Node>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1InfoNodeFactory = false;
        this.obsrv1InfoNodeFactory = null;
        this.bound2InfoNodeFactory = true;
        this.obsrv2InfoNodeFactory = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link SegmentedBar#orientationProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindOrientation(javafx.beans.value.ObservableValue<? extends javafx.geometry.Orientation> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Orientation = true;
        this.obsrv1Orientation = source;
        this.bound2Orientation = false;
        this.obsrv2Orientation = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link SegmentedBar#orientationProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalOrientation(javafx.beans.property.Property<javafx.geometry.Orientation> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Orientation = false;
        this.obsrv1Orientation = null;
        this.bound2Orientation = true;
        this.obsrv2Orientation = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link SegmentedBar#segmentViewFactoryProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindSegmentViewFactory(javafx.beans.value.ObservableValue<? extends javafx.util.Callback<T, javafx.scene.Node>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1SegmentViewFactory = true;
        this.obsrv1SegmentViewFactory = source;
        this.bound2SegmentViewFactory = false;
        this.obsrv2SegmentViewFactory = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link SegmentedBar#segmentViewFactoryProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalSegmentViewFactory(javafx.beans.property.Property<javafx.util.Callback<T, javafx.scene.Node>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1SegmentViewFactory = false;
        this.obsrv1SegmentViewFactory = null;
        this.bound2SegmentViewFactory = true;
        this.obsrv2SegmentViewFactory = source;
        return (B) this;
    }

    /**
     * 設定屬性{@link SegmentedBar#segmentsProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindSegments(javafx.beans.value.ObservableValue<? extends javafx.collections.ObservableList<T>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Segments = true;
        this.obsrv1Segments = source;
        this.bound2Segments = false;
        this.obsrv2Segments = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link SegmentedBar#segmentsProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalSegments(javafx.beans.property.Property<javafx.collections.ObservableList<T>> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1Segments = false;
        this.obsrv1Segments = null;
        this.bound2Segments = true;
        this.obsrv2Segments = source;
        return (B) this;
    }

    /**
     * 建構{@link SegmentedBar}物件。
     *
     * @return 新的{@link SegmentedBar}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public SegmentedBar<T> build()
    {
        SegmentedBar<T> instance = new SegmentedBar<T>();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
