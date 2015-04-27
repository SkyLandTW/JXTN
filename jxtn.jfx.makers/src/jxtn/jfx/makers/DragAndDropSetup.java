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

package jxtn.jfx.makers;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

/**
 * 拖放功能設定
 *
 * @author AqD
 * @param <T> 拖放項目型態
 */
public final class DragAndDropSetup<T>
{
    /**
     * 建立新的拖放功能設定
     */
    public DragAndDropSetup()
    {
    }

    /**
     * 指定從項目取得ID的函數
     *
     * @param idFromItem 從項目取得ID的函數
     * @return 目前的建構器(this)
     */
    public DragAndDropSetup<T> idFromItem(Function<T, String> idFromItem)
    {
        this.idFromItem = idFromItem;
        return this;
    }

    /**
     * 指定從ID尋找項目的函數
     *
     * @param itemFromId 從ID尋找項目的函數
     * @return 目前的建構器(this)
     */
    public DragAndDropSetup<T> itemFromId(Function<String, T> itemFromId)
    {
        this.itemFromId = itemFromId;
        return this;
    }

    /**
     * 指定測試項目是否可拖曳的條件
     *
     * @param acceptDrag 測試項目是否可拖曳的條件
     * @return 目前的建構器(this)
     */
    public DragAndDropSetup<T> acceptDrag(Predicate<T> acceptDrag)
    {
        this.acceptDrag = acceptDrag;
        return this;
    }

    /**
     * 指定測試項目是否可接收被拖曳項目的條件
     *
     * @param acceptDrop 測試項目是否可接收被拖曳項目的條件，(項目,新位置項目)
     * @return 目前的建構器(this)
     */
    public DragAndDropSetup<T> acceptDrop(BiPredicate<T, T> acceptDrop)
    {
        this.acceptDrop = acceptDrop;
        return this;
    }

    /**
     * 指定拖放結束的移動項目到新位置的動作
     *
     * @param moveItem 拖放結束的移動項目到新位置的動作，(項目,新位置項目)
     * @return 目前的建構器(this)
     */
    public DragAndDropSetup<T> moveItem(BiConsumer<T, T> moveItem)
    {
        this.moveItem = moveItem;
        return this;
    }

    /**
     * 套用拖放功能到指定的UI節點
     *
     * @param <N> UI節點型態
     * @param node 要套用拖放功能的目的UI節點
     * @param isEmpty 測試節點是否不含資料的條件
     * @param getItem 取得節點代表資料項目的函數
     */
    public <N extends Node> void apply(N node, Predicate<N> isEmpty, Function<N, T> getItem)
    {
        Objects.requireNonNull(node);
        Objects.requireNonNull(isEmpty);
        Objects.requireNonNull(getItem);
        Objects.requireNonNull(this.idFromItem);
        Objects.requireNonNull(this.itemFromId);
        Objects.requireNonNull(this.acceptDrag);
        Objects.requireNonNull(this.acceptDrop);
        Objects.requireNonNull(this.moveItem);
        node.setOnDragDetected(e -> this.nodeOnDragDetected(node, isEmpty, getItem, e));
        node.setOnDragDone(e -> this.nodeOnDragDone(e));
        node.setOnDragOver(e -> this.nodeOnDragOver(node, isEmpty, getItem, e));
        node.setOnDragDropped(e -> this.nodeOnDragDropped(node, isEmpty, getItem, e));
    }

    private <N extends Node> void nodeOnDragDetected(
            N node, Predicate<N> isEmpty, Function<N, T> getItem, MouseEvent e)
    {
        if (isEmpty.test(node))
            return;
        T item = getItem.apply(node);
        if (!this.acceptDrag.test(item))
            return;
        Dragboard dragBoard = node.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        String id = this.idFromItem.apply(item);
        content.put(DataFormat.PLAIN_TEXT, Objects.requireNonNull(id));
        dragBoard.setContent(content);
        e.consume();
    }

    private void nodeOnDragDone(DragEvent e)
    {
        e.setDropCompleted(e.getTransferMode() == TransferMode.MOVE);
        e.consume();
    }

    private <N extends Node> void nodeOnDragOver(
            N node, Predicate<N> isEmpty, Function<N, T> getItem, DragEvent e)
    {
        if (isEmpty.test(node))
            return;
        if (!e.getDragboard().hasString())
            return;
        if (e.getDragboard().getString().equals(this.idFromItem.apply(getItem.apply(node))))
            return;
        T itemToMove = this.itemFromId.apply(e.getDragboard().getString());
        T newParent = getItem.apply(node);
        if (this.acceptDrop.test(itemToMove, newParent))
        {
            e.acceptTransferModes(TransferMode.MOVE);
        }
        e.consume();
    }

    private <N extends Node> void nodeOnDragDropped(
            N node, Predicate<N> isEmpty, Function<N, T> getItem, DragEvent e)
    {
        if (isEmpty.test(node))
            return;
        if (!e.getDragboard().hasString())
            return;
        if (e.getDragboard().getString().equals(this.idFromItem.apply(getItem.apply(node))))
            return;
        T itemToMove = this.itemFromId.apply(e.getDragboard().getString());
        T newParent = getItem.apply(node);
        if (this.acceptDrop.test(itemToMove, newParent))
        {
            this.moveItem.accept(itemToMove, newParent);
        }
        e.consume();
    }

    private Function<T, String> idFromItem;
    private Function<String, T> itemFromId;
    private Predicate<T> acceptDrag;
    private BiPredicate<T, T> acceptDrop;
    private BiConsumer<T, T> moveItem;
}
