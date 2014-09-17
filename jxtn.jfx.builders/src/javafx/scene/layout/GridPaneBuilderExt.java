/*
 * Unlicensed
 */

package javafx.scene.layout;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import jxtn.jfx.builders.JFX;

/**
 * {@link GridPane}建構器延伸（供客製化）
 *
 * @author AqD
 * @param <Z> 要建構的物件型態(需繼承{@link GridPane})
 * @param <B> 建構器本身的型態(需繼承{@link GridPaneBuilder})
 */
public interface GridPaneBuilderExt<Z extends GridPane, B extends GridPaneBuilder<Z, B>>
        extends javafx.scene.layout.PaneBuilderExt<Z, B>
{
    /**
     * 增加唯讀欄位
     *
     * @param row 列號
     * @param margin 邊界
     * @param label 欄位標題
     * @param source 資料來源
     * @return 自己
     */
    default B addFieldRO(int row, Insets margin, String label, ObservableValue<? extends String> source)
    {
        this.self().childrenAdd(
                JFX.label()
                        .GridPane_margin(margin)
                        .GridPane_rowIndex(row).GridPane_columnIndex(0)
                        .GridPane_fillWidth(true).GridPane_fillHeight(true)
                        .text(label)
                        .build(),
                JFX.textField()
                        .GridPane_margin(margin)
                        .GridPane_rowIndex(row).GridPane_columnIndex(1)
                        .GridPane_fillWidth(true).GridPane_fillHeight(true)
                        .GridPane_hgrow(Priority.ALWAYS)
                        .bindText(source)
                        .editable(false)
                        .build());
        return this.self();
    }
}
