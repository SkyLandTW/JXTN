// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package org.controlsfx.control.spreadsheet;

/**
 * {@link SpreadsheetCellEditor.DoubleEditor}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version controlsfx-8.40.14.jar
 * @param <Z> 要建構的物件型態(需繼承{@link SpreadsheetCellEditor.DoubleEditor})
 * @param <B> 建構器本身的型態(需繼承{@link SpreadsheetCellEditor.DoubleEditorMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class SpreadsheetCellEditor_DoubleEditorMaker<Z extends SpreadsheetCellEditor.DoubleEditor, B extends SpreadsheetCellEditor_DoubleEditorMaker<Z, B>>
        extends org.controlsfx.control.spreadsheet.SpreadsheetCellEditorMaker<Z, B>
        implements SpreadsheetCellEditor_DoubleEditorMakerExt<Z, B>
{

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
    }

    /**
     * 建構{@link SpreadsheetCellEditor.DoubleEditor}物件。
     *
     * @return 新的{@link SpreadsheetCellEditor.DoubleEditor}物件實體
     */
    @SuppressWarnings("unchecked")
    public SpreadsheetCellEditor.DoubleEditor build(org.controlsfx.control.spreadsheet.SpreadsheetView arg0)
    {
        SpreadsheetCellEditor.DoubleEditor instance = new SpreadsheetCellEditor.DoubleEditor(arg0);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
