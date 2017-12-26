// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package org.controlsfx.validation.decoration;

/**
 * {@link CompoundValidationDecoration}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version controlsfx-8.40.14.jar
 * @param <Z> 要建構的物件型態(需繼承{@link CompoundValidationDecoration})
 * @param <B> 建構器本身的型態(需繼承{@link CompoundValidationDecorationMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class CompoundValidationDecorationMaker<Z extends CompoundValidationDecoration, B extends CompoundValidationDecorationMaker<Z, B>>
        extends org.controlsfx.validation.decoration.AbstractValidationDecorationMaker<Z, B>
        implements CompoundValidationDecorationMakerExt<Z, B>
{

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
    }

    /**
     * 建構{@link CompoundValidationDecoration}物件。
     *
     * @return 新的{@link CompoundValidationDecoration}物件實體
     */
    @SuppressWarnings("unchecked")
    public CompoundValidationDecoration build(java.util.Collection<org.controlsfx.validation.decoration.ValidationDecoration> arg0)
    {
        CompoundValidationDecoration instance = new CompoundValidationDecoration(arg0);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link CompoundValidationDecoration}物件。
     *
     * @return 新的{@link CompoundValidationDecoration}物件實體
     */
    @SuppressWarnings("unchecked")
    public CompoundValidationDecoration build(org.controlsfx.validation.decoration.ValidationDecoration[] arg0)
    {
        CompoundValidationDecoration instance = new CompoundValidationDecoration(arg0);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
