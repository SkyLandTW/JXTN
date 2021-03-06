// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.control;

/**
 * {@link IndexedCell}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link IndexedCell})
 * @param <B> 建構器本身的型態(需繼承{@link IndexedCellMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class IndexedCellMaker<T extends java.lang.Object, Z extends IndexedCell<T>, B extends IndexedCellMaker<T, Z, B>>
        extends javafx.scene.control.CellMaker<T, Z, B>
        implements IndexedCellMakerExt<T, Z, B>
{

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
    }

    /**
     * 建構{@link IndexedCell}物件。
     *
     * @return 新的{@link IndexedCell}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public IndexedCell<T> build()
    {
        IndexedCell<T> instance = new IndexedCell<T>();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
