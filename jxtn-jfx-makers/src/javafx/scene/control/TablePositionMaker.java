// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.control;

/**
 * {@link TablePosition}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link TablePosition})
 * @param <B> 建構器本身的型態(需繼承{@link TablePositionMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class TablePositionMaker<S extends java.lang.Object, T extends java.lang.Object, Z extends TablePosition<S, T>, B extends TablePositionMaker<S, T, Z, B>>
        extends javafx.scene.control.TablePositionBaseMaker<javafx.scene.control.TableColumn<S, T>, Z, B>
        implements TablePositionMakerExt<S, T, Z, B>
{

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
    }

    /**
     * 建構{@link TablePosition}物件。
     *
     * @return 新的{@link TablePosition}物件實體
     */
    @SuppressWarnings("unchecked")
    public TablePosition<S, T> build(javafx.scene.control.TableView<S> arg0, int arg1, javafx.scene.control.TableColumn<S, T> arg2)
    {
        TablePosition<S, T> instance = new TablePosition<S, T>(arg0, arg1, arg2);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
