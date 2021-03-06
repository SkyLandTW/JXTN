// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.control;

/**
 * {@link SplitMenuButton}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link SplitMenuButton})
 * @param <B> 建構器本身的型態(需繼承{@link SplitMenuButtonMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class SplitMenuButtonMaker<Z extends SplitMenuButton, B extends SplitMenuButtonMaker<Z, B>>
        extends javafx.scene.control.MenuButtonMaker<Z, B>
        implements SplitMenuButtonMakerExt<Z, B>
{

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
    }

    /**
     * 建構{@link SplitMenuButton}物件。
     *
     * @return 新的{@link SplitMenuButton}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public SplitMenuButton build()
    {
        SplitMenuButton instance = new SplitMenuButton();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link SplitMenuButton}物件。
     *
     * @return 新的{@link SplitMenuButton}物件實體
     */
    @SuppressWarnings("unchecked")
    public SplitMenuButton build(javafx.scene.control.MenuItem[] arg0)
    {
        SplitMenuButton instance = new SplitMenuButton(arg0);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
