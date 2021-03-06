// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package org.controlsfx.control.spreadsheet;

/**
 * {@link SpreadsheetCellType.StringType}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version controlsfx-8.40.14.jar
 * @param <Z> 要建構的物件型態(需繼承{@link SpreadsheetCellType.StringType})
 * @param <B> 建構器本身的型態(需繼承{@link SpreadsheetCellType.StringTypeMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class SpreadsheetCellType_StringTypeMaker<Z extends SpreadsheetCellType.StringType, B extends SpreadsheetCellType_StringTypeMaker<Z, B>>
        extends org.controlsfx.control.spreadsheet.SpreadsheetCellTypeMaker<java.lang.String, Z, B>
        implements SpreadsheetCellType_StringTypeMakerExt<Z, B>
{

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
    }

    /**
     * 建構{@link SpreadsheetCellType.StringType}物件。
     *
     * @return 新的{@link SpreadsheetCellType.StringType}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public SpreadsheetCellType.StringType build()
    {
        SpreadsheetCellType.StringType instance = new SpreadsheetCellType.StringType();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link SpreadsheetCellType.StringType}物件。
     *
     * @return 新的{@link SpreadsheetCellType.StringType}物件實體
     */
    @SuppressWarnings("unchecked")
    public SpreadsheetCellType.StringType build(javafx.util.StringConverter<java.lang.String> arg0)
    {
        SpreadsheetCellType.StringType instance = new SpreadsheetCellType.StringType(arg0);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
