// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package org.controlsfx.control.action;

/**
 * {@link AnnotatedAction}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version controlsfx-8.40.11-20151113.010656-84.jar
 * @param <Z> 要建構的物件型態(需繼承{@link AnnotatedAction})
 * @param <B> 建構器本身的型態(需繼承{@link AnnotatedActionMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class AnnotatedActionMaker<Z extends AnnotatedAction, B extends AnnotatedActionMaker<Z, B>>
        extends org.controlsfx.control.action.ActionMaker<Z, B>
        implements AnnotatedActionMakerExt<Z, B>
{

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
    }

    /**
     * 建構{@link AnnotatedAction}物件。
     *
     * @return 新的{@link AnnotatedAction}物件實體
     */
    @SuppressWarnings("unchecked")
    public AnnotatedAction build(java.lang.String arg0, java.lang.reflect.Method arg1, java.lang.Object arg2)
    {
        AnnotatedAction instance = new AnnotatedAction(arg0, arg1, arg2);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}