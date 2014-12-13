// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.layout;

/**
 * {@link Background}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link Background})
 * @param <B> 建構器本身的型態(需繼承{@link BackgroundMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class BackgroundMaker<Z extends Background, B extends BackgroundMaker<Z, B>>
        extends jxtn.jfx.makers.AbstractMaker<Z, B>
        implements BackgroundMakerExt<Z, B>
{

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
    }

    /**
     * 建構{@link Background}物件。
     *
     * @return 新的{@link Background}物件實體
     */
    @SuppressWarnings("unchecked")
    public Background build(javafx.scene.layout.BackgroundFill[] arg0)
    {
        Background instance = new Background(arg0);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link Background}物件。
     *
     * @return 新的{@link Background}物件實體
     */
    @SuppressWarnings("unchecked")
    public Background build(javafx.scene.layout.BackgroundImage[] arg0)
    {
        Background instance = new Background(arg0);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link Background}物件。
     *
     * @return 新的{@link Background}物件實體
     */
    @SuppressWarnings("unchecked")
    public Background build(java.util.List<javafx.scene.layout.BackgroundFill> arg0, java.util.List<javafx.scene.layout.BackgroundImage> arg1)
    {
        Background instance = new Background(arg0, arg1);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link Background}物件。
     *
     * @return 新的{@link Background}物件實體
     */
    @SuppressWarnings("unchecked")
    public Background build(javafx.scene.layout.BackgroundFill[] arg0, javafx.scene.layout.BackgroundImage[] arg1)
    {
        Background instance = new Background(arg0, arg1);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
