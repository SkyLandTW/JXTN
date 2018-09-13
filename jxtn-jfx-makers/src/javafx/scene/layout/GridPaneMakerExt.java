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

import javafx.beans.property.Property;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    //////////////////////////////////////////////////////////////////////////
    // 標籤及唯讀欄位
    //

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
        return this.addFieldROIf(true, row, 0, margin, label, source, styleClasses);
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
     * @param row 列號
     * @param column 行號
     * @param margin 邊界
     * @param label 欄位標題
     * @param source 資料來源
     * @param styleClasses 輸入欄位樣式
     * @return 自己
     */
    default B addFieldRO(int row, int column, Insets margin, String label, ObservableValue<? extends String> source, String... styleClasses)
    {
        return this.addFieldROIf(true, row, column, margin, label, source, styleClasses);
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
        return this.addFieldROIf(condition, row, 0, margin, label, source, styleClasses);
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
     * @param column 行號
     * @param margin 邊界
     * @param label 欄位標題
     * @param source 資料來源
     * @param styleClasses 輸入欄位樣式
     * @return 自己
     */
    default B addFieldROIf(boolean condition, int row, int column, Insets margin, String label, ObservableValue<? extends String> source, String... styleClasses)
    {
        if (condition)
        {
            Insets labelMargin = column == 0 ? margin
                    : new Insets(
                            margin.getTop(), margin.getRight(), margin.getBottom(), margin.getLeft() * 2);
            Node fieldNode = JFX.textField()
                    .GridPane_margin(margin)
                    .GridPane_rowIndex(row).GridPane_columnIndex(column + 1)
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
                            .GridPane_margin(labelMargin)
                            .GridPane_rowIndex(row).GridPane_columnIndex(column + 0)
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

    //////////////////////////////////////////////////////////////////////////
    // 標籤及可輸入欄位
    //

    /**
     * 增加可輸入欄位
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
    default B addFieldRW(int row, Insets margin, String label, Property<String> source, String... styleClasses)
    {
        return this.addFieldRWIf(true, row, 0, margin, label, source, styleClasses);
    }

    /**
     * 增加可輸入欄位
     * <p>
     * 每個欄位佔第一行及第二行：
     * <ol>
     * <li>第一行顯示標籤({@code label})，向右中對齊</li>
     * <li>第二行顯示文字控制{@link javafx.scene.control.TextField}，向左上對齊，橫向填滿，連結資料{@code source}</li>
     * </ol>
     * </p>
     *
     * @param row 列號
     * @param column 行號
     * @param margin 邊界
     * @param label 欄位標題
     * @param source 資料來源
     * @param styleClasses 輸入欄位樣式
     * @return 自己
     */
    default B addFieldRW(int row, int column, Insets margin, String label, Property<String> source, String... styleClasses)
    {
        return this.addFieldRWIf(true, row, column, margin, label, source, styleClasses);
    }

    /**
     * 增加可輸入欄位
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
    default B addFieldRWIf(boolean condition, int row, Insets margin, String label, Property<String> source, String... styleClasses)
    {
        return this.addFieldRWIf(condition, row, 0, margin, label, source, styleClasses);
    }

    /**
     * 增加可輸入欄位
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
     * @param column 行號
     * @param margin 邊界
     * @param label 欄位標題
     * @param source 資料來源
     * @param styleClasses 輸入欄位樣式
     * @return 自己
     */
    default B addFieldRWIf(boolean condition, int row, int column, Insets margin, String label, Property<String> source, String... styleClasses)
    {
        if (condition)
        {
            Insets labelMargin = column == 0 ? margin
                    : new Insets(
                            margin.getTop(), margin.getRight(), margin.getBottom(), margin.getLeft() * 2);
            Node fieldNode = JFX.textField()
                    .GridPane_margin(margin)
                    .GridPane_rowIndex(row).GridPane_columnIndex(column + 1)
                    .GridPane_fillWidth(true).GridPane_fillHeight(true)
                    .GridPane_halignment(HPos.LEFT)
                    .GridPane_valignment(VPos.TOP)
                    .GridPane_hgrow(Priority.ALWAYS)
                    .bindBidirectionalText(source)
                    .editable(true)
                    .styleClass(styleClasses)
                    .build();
            this.self().childrenAdd(
                    JFX.label()
                            .GridPane_margin(labelMargin)
                            .GridPane_rowIndex(row).GridPane_columnIndex(column + 0)
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

    //////////////////////////////////////////////////////////////////////////
    // 標籤及可輸入密碼欄位
    //

    /**
     * 增加可輸入欄位
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
    default B addPasswordRW(int row, Insets margin, String label, Property<String> source, String... styleClasses)
    {
        return this.addPasswordRWIf(true, row, 0, margin, label, source, styleClasses);
    }

    /**
     * 增加可輸入欄位
     * <p>
     * 每個欄位佔第一行及第二行：
     * <ol>
     * <li>第一行顯示標籤({@code label})，向右中對齊</li>
     * <li>第二行顯示文字控制{@link javafx.scene.control.TextField}，向左上對齊，橫向填滿，連結資料{@code source}</li>
     * </ol>
     * </p>
     *
     * @param row 列號
     * @param column 行號
     * @param margin 邊界
     * @param label 欄位標題
     * @param source 資料來源
     * @param styleClasses 輸入欄位樣式
     * @return 自己
     */
    default B addPasswordRW(int row, int column, Insets margin, String label, Property<String> source, String... styleClasses)
    {
        return this.addPasswordRWIf(true, row, column, margin, label, source, styleClasses);
    }

    /**
     * 增加可輸入欄位
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
    default B addPasswordRWIf(boolean condition, int row, Insets margin, String label, Property<String> source, String... styleClasses)
    {
        return this.addPasswordRWIf(condition, row, 0, margin, label, source, styleClasses);
    }

    /**
     * 增加可輸入欄位
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
     * @param column 行號
     * @param margin 邊界
     * @param label 欄位標題
     * @param source 資料來源
     * @param styleClasses 輸入欄位樣式
     * @return 自己
     */
    default B addPasswordRWIf(boolean condition, int row, int column, Insets margin, String label, Property<String> source, String... styleClasses)
    {
        if (condition)
        {
            Insets labelMargin = column == 0 ? margin
                    : new Insets(
                            margin.getTop(), margin.getRight(), margin.getBottom(), margin.getLeft() * 2);
            Node fieldNode = JFX.passwordField()
                    .GridPane_margin(margin)
                    .GridPane_rowIndex(row).GridPane_columnIndex(column + 1)
                    .GridPane_fillWidth(true).GridPane_fillHeight(true)
                    .GridPane_halignment(HPos.LEFT)
                    .GridPane_valignment(VPos.TOP)
                    .GridPane_hgrow(Priority.ALWAYS)
                    .bindBidirectionalText(source)
                    .editable(true)
                    .styleClass(styleClasses)
                    .build();
            this.self().childrenAdd(
                    JFX.label()
                            .GridPane_margin(labelMargin)
                            .GridPane_rowIndex(row).GridPane_columnIndex(column + 0)
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

    //////////////////////////////////////////////////////////////////////////
    // 標籤及自訂輸入欄位
    //

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
        return this.addFieldCustomIf(true, row, 0, margin, label, fieldNode);
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
     * @param column 行號
     * @param margin 邊界
     * @param label 欄位標題
     * @param fieldNode 欄位節點
     * @return 自己
     */
    default B addFieldCustom(int row, int column, Insets margin, String label, Node fieldNode)
    {
        return this.addFieldCustomIf(true, row, column, margin, label, fieldNode);
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
        return this.addFieldCustomIf(condition, row, 0, margin, label, fieldNode);
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
     * @param column 行號
     * @param margin 邊界
     * @param label 欄位標題
     * @param fieldNode 欄位節點
     * @return 自己
     */
    default B addFieldCustomIf(boolean condition, int row, int column, Insets margin, String label, Node fieldNode)
    {
        /*if (condition)
        {
            Insets labelMargin = column == 0 ? margin
                    : new Insets(
                            margin.getTop(), margin.getRight(), margin.getBottom(), margin.getLeft() * 2);*/
        Insets labelMargin = margin;
        GridPane.setMargin(fieldNode, margin);
        GridPane.setRowIndex(fieldNode, row);
        GridPane.setColumnIndex(fieldNode, column + 1);
        GridPane.setFillWidth(fieldNode, true);
        GridPane.setFillHeight(fieldNode, true);
        GridPane.setHgrow(fieldNode, Priority.ALWAYS);
        GridPane.setHalignment(fieldNode, HPos.LEFT);
        GridPane.setValignment(fieldNode, VPos.TOP);
        this.self().childrenAdd(
                JFX.label()
                        .GridPane_margin(labelMargin)
                        .GridPane_rowIndex(row).GridPane_columnIndex(column + 0)
                        .GridPane_fillWidth(true).GridPane_fillHeight(true)
                        .GridPane_halignment(HPos.LEFT)
                        .GridPane_valignment(VPos.CENTER)
                        .text(label)
                        .ellipsisString(label)
                        .labelFor(fieldNode)
                        .afterBuild(lbl -> lbl.minWidthProperty().bind(lbl.prefWidthProperty()))
                        .build(),
                fieldNode);
        //}
        return this.self();
    }

    //////////////////////////////////////////////////////////////////////////
    // 自訂標籤及自訂輸入欄位
    //

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
    default B addFieldCustom(int row, Insets margin, Node labelNode, Node fieldNode)
    {
        return this.addFieldCustomIf(true, row, margin, labelNode, fieldNode);
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
     * @param column 行號
     * @param margin 邊界
     * @param labelNode 欄位標題
     * @param fieldNode 欄位節點
     * @return 自己
     */
    default B addFieldCustom(int row, int column, Insets margin, Node labelNode, Node fieldNode)
    {
        return this.addFieldCustomIf(true, row, column, margin, labelNode, fieldNode);
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
    default B addFieldCustomIf(boolean condition, int row, Insets margin, Node labelNode, Node fieldNode)
    {
        return this.addFieldCustomIf(condition, row, 0, margin, labelNode, fieldNode);
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
     * @param column 行號
     * @param margin 邊界
     * @param labelNode 欄位標題節點
     * @param fieldNode 欄位內容節點
     * @return 自己
     */
    default B addFieldCustomIf(boolean condition, int row, int column, Insets margin, Node labelNode, Node fieldNode)
    {
        if (condition)
        {
            Insets labelMargin = column == 0 ? margin
                    : new Insets(
                            margin.getTop(), margin.getRight(), margin.getBottom(), margin.getLeft() * 2);
            //
            GridPane.setMargin(labelNode, labelMargin);
            GridPane.setRowIndex(labelNode, row);
            GridPane.setColumnIndex(labelNode, column + 0);
            GridPane.setFillWidth(labelNode, true);
            GridPane.setFillHeight(labelNode, true);
            GridPane.setHalignment(labelNode, HPos.LEFT);
            GridPane.setValignment(labelNode, VPos.CENTER);
            GridPane.setMargin(fieldNode, margin);
            GridPane.setRowIndex(fieldNode, row);
            GridPane.setColumnIndex(fieldNode, column + 1);
            GridPane.setFillWidth(fieldNode, true);
            GridPane.setFillHeight(fieldNode, true);
            GridPane.setHgrow(fieldNode, Priority.ALWAYS);
            GridPane.setHalignment(fieldNode, HPos.LEFT);
            GridPane.setValignment(fieldNode, VPos.TOP);
            this.self().childrenAdd(labelNode, fieldNode);
        }
        return this.self();
    }

    default B addSimpleLabel(int row, int column, Insets margin, String label)
    {
        Insets labelMargin = margin;
        this.self().childrenAdd(
                JFX.label()
                        .GridPane_margin(labelMargin)
                        .GridPane_rowIndex(row).GridPane_columnIndex(column)
                        .GridPane_fillWidth(true).GridPane_fillHeight(true)
                        .text(label)
                        .alignment(Pos.CENTER)
                        .ellipsisString(label)
                        .afterBuild(lbl -> lbl.minWidthProperty().bind(lbl.prefWidthProperty()))
                        .build());
        return this.self();
    }

    default B addSimpleButton(int row, int column, Insets margin, String label)
    {
        Insets labelMargin = margin;
        this.self().childrenAdd(
                JFX.button()
                        .GridPane_margin(labelMargin)
                        .GridPane_rowIndex(row).GridPane_columnIndex(column)
                        .GridPane_halignment(HPos.CENTER)
                        .GridPane_valignment(VPos.CENTER)
                        .text(label)
                        .alignment(Pos.CENTER)
                        .ellipsisString(label)
                        .afterBuild(lbl -> lbl.minWidthProperty().bind(lbl.prefWidthProperty()))
                        .build());
        return this.self();
    }
}
