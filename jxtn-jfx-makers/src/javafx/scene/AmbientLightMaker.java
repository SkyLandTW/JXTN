// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene;

/**
 * {@link AmbientLight}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link AmbientLight})
 * @param <B> 建構器本身的型態(需繼承{@link AmbientLightMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class AmbientLightMaker<Z extends AmbientLight, B extends AmbientLightMaker<Z, B>>
        extends javafx.scene.LightBaseMaker<Z, B>
        implements AmbientLightMakerExt<Z, B>
{

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
    }

    /**
     * 建構{@link AmbientLight}物件。
     *
     * @return 新的{@link AmbientLight}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public AmbientLight build()
    {
        AmbientLight instance = new AmbientLight();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link AmbientLight}物件。
     *
     * @return 新的{@link AmbientLight}物件實體
     */
    @SuppressWarnings("unchecked")
    public AmbientLight build(javafx.scene.paint.Color arg0)
    {
        AmbientLight instance = new AmbientLight(arg0);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}