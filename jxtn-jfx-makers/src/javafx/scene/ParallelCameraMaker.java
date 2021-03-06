// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene;

/**
 * {@link ParallelCamera}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link ParallelCamera})
 * @param <B> 建構器本身的型態(需繼承{@link ParallelCameraMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class ParallelCameraMaker<Z extends ParallelCamera, B extends ParallelCameraMaker<Z, B>>
        extends javafx.scene.CameraMaker<Z, B>
        implements ParallelCameraMakerExt<Z, B>
{

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
    }

    /**
     * 建構{@link ParallelCamera}物件。
     *
     * @return 新的{@link ParallelCamera}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public ParallelCamera build()
    {
        ParallelCamera instance = new ParallelCamera();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
