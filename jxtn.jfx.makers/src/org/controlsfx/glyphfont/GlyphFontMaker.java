// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package org.controlsfx.glyphfont;

/**
 * {@link GlyphFont}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version controlsfx-8.20.8.jar
 * @param <Z> 要建構的物件型態(需繼承{@link GlyphFont})
 * @param <B> 建構器本身的型態(需繼承{@link GlyphFontMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class GlyphFontMaker<Z extends GlyphFont, B extends GlyphFontMaker<Z, B>>
        extends jxtn.jfx.makers.AbstractMaker<Z, B>
        implements GlyphFontMakerExt<Z, B>
{

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
    }

    /**
     * 建構{@link GlyphFont}物件。
     *
     * @return 新的{@link GlyphFont}物件實體
     */
    @SuppressWarnings("unchecked")
    public GlyphFont build(java.lang.String arg0, int arg1, java.io.InputStream arg2)
    {
        GlyphFont instance = new GlyphFont(arg0, arg1, arg2);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link GlyphFont}物件。
     *
     * @return 新的{@link GlyphFont}物件實體
     */
    @SuppressWarnings("unchecked")
    public GlyphFont build(java.lang.String arg0, int arg1, java.lang.String arg2)
    {
        GlyphFont instance = new GlyphFont(arg0, arg1, arg2);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link GlyphFont}物件。
     *
     * @return 新的{@link GlyphFont}物件實體
     */
    @SuppressWarnings("unchecked")
    public GlyphFont build(java.lang.String arg0, int arg1, java.io.InputStream arg2, boolean arg3)
    {
        GlyphFont instance = new GlyphFont(arg0, arg1, arg2, arg3);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link GlyphFont}物件。
     *
     * @return 新的{@link GlyphFont}物件實體
     */
    @SuppressWarnings("unchecked")
    public GlyphFont build(java.lang.String arg0, int arg1, java.lang.String arg2, boolean arg3)
    {
        GlyphFont instance = new GlyphFont(arg0, arg1, arg2, arg3);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
