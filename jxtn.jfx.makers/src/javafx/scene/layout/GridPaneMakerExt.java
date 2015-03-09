/*
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <http://unlicense.org/>
 */

package javafx.scene.layout;

import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import jxtn.jfx.makers.JFX;

/**
 * {@link GridPane}建構器延伸（供客製化）
 *
 * @author AqD
 * @param <Z> 要建構的物件型態(需繼承{@link GridPane})
 * @param <B> 建構器本身的型態(需繼承{@link GridPaneMaker})
 */
public interface GridPaneMakerExt<Z extends GridPane, B extends GridPaneMaker<Z, B>>
        extends javafx.scene.layout.PaneMakerExt<Z, B>
{
    /**
     * 增加唯讀欄位
     * <p>
     * 每個欄位佔第一行及第二行：
     * <ol>
     * <li>第一行顯示標籤({@code label})，向右中對齊</li>
     * <li>第二行顯示文字控制{@link javafx.scene.control.TextField}，向左上對齊，橫向填滿，連結資料{@code source}</li>
     * </ol>
     * </p>
     *
     * @param row 列號
     * @param margin 邊界
     * @param label 欄位標題
     * @param source 資料來源
     * @param styleClasses 輸入欄位樣式
     * @return 自己
     */
    default B addFieldRO(int row, Insets margin, String label, ObservableValue<? extends String> source, String... styleClasses)
    {
        return this.addFieldROIf(true, row, margin, label, source, styleClasses);
    }

    /**
     * 增加唯讀欄位
     * <p>
     * 每個欄位佔第一行及第二行：
     * <ol>
     * <li>第一行顯示標籤({@code label})，向右中對齊</li>
     * <li>第二行顯示文字控制{@link javafx.scene.control.TextField}，向左上對齊，橫向填滿，連結資料{@code source}</li>
     * </ol>
     * </p>
     *
     * @param condition 條件，為true時才加入欄位
     * @param row 列號
     * @param margin 邊界
     * @param label 欄位標題
     * @param source 資料來源
     * @param styleClasses 輸入欄位樣式
     * @return 自己
     */
    default B addFieldROIf(boolean condition, int row, Insets margin, String label, ObservableValue<? extends String> source, String... styleClasses)
    {
        if (condition)
        {
            Node fieldNode = JFX.textField()
                    .GridPane_margin(margin)
                    .GridPane_rowIndex(row).GridPane_columnIndex(1)
                    .GridPane_fillWidth(true).GridPane_fillHeight(true)
                    .GridPane_halignment(HPos.LEFT)
                    .GridPane_valignment(VPos.TOP)
                    .GridPane_hgrow(Priority.ALWAYS)
                    .bindText(source)
                    .editable(false)
                    .styleClass(styleClasses)
                    .build();
            this.self().childrenAdd(
                    JFX.label()
                            .GridPane_margin(margin)
                            .GridPane_rowIndex(row).GridPane_columnIndex(0)
                            .GridPane_fillWidth(true).GridPane_fillHeight(true)
                            .GridPane_halignment(HPos.RIGHT)
                            .GridPane_valignment(VPos.CENTER)
                            .labelFor(fieldNode)
                            .text(label)
                            .ellipsisString(label)
                            .afterBuild(lbl -> lbl.minWidthProperty().bind(lbl.prefWidthProperty()))
                            .build(),
                    fieldNode);
        }
        return this.self();
    }

    /**
     * 增加自訂欄位
     * <p>
     * 每個欄位佔第一行及第二行：
     * <ol>
     * <li>第一行顯示標籤({@code label})，向右中對齊</li>
     * <li>第二行顯示指定控制項({@code fieldNode})，向左上對齊，橫向填滿</li>
     * </ol>
     * </p>
     *
     * @param row 列號
     * @param margin 邊界
     * @param label 欄位標題
     * @param fieldNode 欄位節點
     * @return 自己
     */
    default B addFieldCustom(int row, Insets margin, String label, Node fieldNode)
    {
        return this.addFieldCustomIf(true, row, margin, label, fieldNode);
    }

    /**
     * 增加自訂欄位
     * <p>
     * 每個欄位佔第一行及第二行：
     * <ol>
     * <li>第一行顯示標籤({@code label})，向右中對齊</li>
     * <li>第二行顯示指定控制項({@code fieldNode})，向左上對齊，橫向填滿</li>
     * </ol>
     * </p>
     *
     * @param condition 條件，為true時才加入欄位
     * @param row 列號
     * @param margin 邊界
     * @param label 欄位標題
     * @param fieldNode 欄位節點
     * @return 自己
     */
    default B addFieldCustomIf(boolean condition, int row, Insets margin, String label, Node fieldNode)
    {
        if (condition)
        {
            GridPane.setMargin(fieldNode, margin);
            GridPane.setRowIndex(fieldNode, row);
            GridPane.setColumnIndex(fieldNode, 1);
            GridPane.setFillWidth(fieldNode, true);
            GridPane.setFillHeight(fieldNode, true);
            GridPane.setHgrow(fieldNode, Priority.ALWAYS);
            GridPane.setHalignment(fieldNode, HPos.LEFT);
            GridPane.setValignment(fieldNode, VPos.TOP);
            this.self().childrenAdd(
                    JFX.label()
                            .GridPane_margin(margin)
                            .GridPane_rowIndex(row).GridPane_columnIndex(0)
                            .GridPane_fillWidth(true).GridPane_fillHeight(true)
                            .GridPane_halignment(HPos.RIGHT)
                            .GridPane_valignment(VPos.CENTER)
                            .text(label)
                            .ellipsisString(label)
                            .labelFor(fieldNode)
                            .afterBuild(lbl -> lbl.minWidthProperty().bind(lbl.prefWidthProperty()))
                            .build(),
                    fieldNode);
        }
        return this.self();
    }

    /**
     * 增加自訂欄位
     * <p>
     * 每個欄位佔第一行及第二行：
     * <ol>
     * <li>第一行顯示標籤({@code label})，向右中對齊</li>
     * <li>第二行顯示指定控制項({@code fieldNode})，向左上對齊，橫向填滿</li>
     * </ol>
     * </p>
     *
     * @param row 列號
     * @param margin 邊界
     * @param labelNode 欄位標題
     * @param fieldNode 欄位節點
     * @return 自己
     */
    default B addRowCustom(int row, Insets margin, Node labelNode, Node fieldNode)
    {
        return this.addRowCustomIf(true, row, margin, labelNode, fieldNode);
    }

    /**
     * 增加自訂欄位
     * <p>
     * 每個欄位佔第一行及第二行：
     * <ol>
     * <li>第一行顯示標籤({@code label})，向右中對齊</li>
     * <li>第二行顯示指定控制項({@code fieldNode})，向左上對齊，橫向填滿</li>
     * </ol>
     * </p>
     *
     * @param condition 條件，為true時才加入欄位
     * @param row 列號
     * @param margin 邊界
     * @param labelNode 欄位標題節點
     * @param fieldNode 欄位內容節點
     * @return 自己
     */
    default B addRowCustomIf(boolean condition, int row, Insets margin, Node labelNode, Node fieldNode)
    {
        if (condition)
        {
            GridPane.setMargin(labelNode, margin);
            GridPane.setRowIndex(labelNode, row);
            GridPane.setColumnIndex(labelNode, 0);
            GridPane.setFillWidth(labelNode, true);
            GridPane.setFillHeight(labelNode, true);
            GridPane.setHalignment(labelNode, HPos.RIGHT);
            GridPane.setValignment(labelNode, VPos.CENTER);
            //
            GridPane.setMargin(fieldNode, margin);
            GridPane.setRowIndex(fieldNode, row);
            GridPane.setColumnIndex(fieldNode, 1);
            GridPane.setFillWidth(fieldNode, true);
            GridPane.setFillHeight(fieldNode, true);
            GridPane.setHgrow(fieldNode, Priority.ALWAYS);
            GridPane.setHalignment(fieldNode, HPos.LEFT);
            GridPane.setValignment(fieldNode, VPos.TOP);
            this.self().childrenAdd(labelNode, fieldNode);
        }
        return this.self();
    }
}
