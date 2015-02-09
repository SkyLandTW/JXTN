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

package jxtn.jfx.axi;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import jxtn.core.axi.util.BinarySearchResult;

/**
 * {@link TreeItem}結構建立的輔助類別
 *
 * @author AqD
 */
final class TreeFactory
{
    static <E> void rebuildTree(
            List<E> sourceList,
            TreeItem<E> targetRoot,
            UnaryOperator<E> getParent,
            Function<E, TreeItem<E>> createNode,
            Map<E, TreeItem<E>> sourceToNodeMap)
    {
        targetRoot.getChildren().clear();
        sourceToNodeMap.clear();
        int index = 0;
        for (E srcItem : sourceList)
        {
            addTreeElement(targetRoot, getParent, createNode, sourceToNodeMap, srcItem, sourceList, index);
            index += 1;
        }
    }

    static <E> void rebuildTree(
            Collection<E> sourceList,
            TreeItem<E> targetRoot,
            UnaryOperator<E> getParent,
            Function<E, TreeItem<E>> createNode,
            Map<E, TreeItem<E>> sourceToNodeMap,
            Comparator<TreeItem<E>> comparator)
    {
        targetRoot.getChildren().clear();
        sourceToNodeMap.clear();
        for (E srcItem : sourceList)
        {
            addTreeElement(targetRoot, getParent, createNode, sourceToNodeMap, srcItem, comparator);
        }
    }

    static <E> void moveChildNodesToRoot(
            TreeItem<E> root, ObservableList<TreeItem<E>> childNodes)
    {
        if (childNodes.isEmpty())
            return;
        ObservableList<TreeItem<E>> rootChildren = root.getChildren();
        List<TreeItem<E>> childNodesCopy = childNodes.toArrayList();
        childNodes.clear();
        rootChildren.addAll(childNodesCopy);
    }

    static <E> void moveChildNodesToRoot(
            TreeItem<E> root, ObservableList<TreeItem<E>> childNodes, Comparator<TreeItem<E>> comparator)
    {
        if (childNodes.isEmpty())
            return;
        ObservableList<TreeItem<E>> rootChildren = root.getChildren();
        List<TreeItem<E>> childNodesCopy = childNodes.toArrayList();
        childNodes.clear();
        for (TreeItem<E> child : childNodesCopy)
        {
            BinarySearchResult result = new BinarySearchResult(Collections.binarySearch(rootChildren, child, comparator));
            rootChildren.add(result.getIndex(), child);
        }
    }

    static <E> TreeItem<E> addTreeElement(
            TreeItem<E> rootNode,
            UnaryOperator<E> getParent,
            Function<E, TreeItem<E>> createNode,
            Map<E, TreeItem<E>> sourceToNodeMap,
            E sourceItem,
            List<E> sourceList,
            Integer cachedSourceIndex)
    {
        TreeItem<E> sourceNode = createNode.apply(sourceItem);
        E parentItem = getParent.apply(sourceItem);
        TreeItem<E> parentNode;
        if (parentItem == null)
            parentNode = rootNode;
        else if (sourceToNodeMap.containsKey2(parentItem))
            parentNode = sourceToNodeMap.get2(parentItem);
        else
            parentNode = addTreeElement(rootNode, getParent, createNode, sourceToNodeMap, parentItem, sourceList, null);
        Integer sourceIndex = cachedSourceIndex != null
                ? cachedSourceIndex
                : (Integer) sourceList.indexOf2(sourceItem);
        ObservableList<TreeItem<E>> parentChildren = parentNode.getChildren();
        BinarySearchResult result = parentChildren.binarySearch(n -> sourceList.indexOf2(n.getValue()), sourceIndex);
        parentChildren.add(result.getIndex(), sourceNode);
        sourceToNodeMap.put(sourceItem, sourceNode);
        return sourceNode;
    }

    static <E> TreeItem<E> addTreeElement(
            TreeItem<E> rootNode,
            UnaryOperator<E> getParent,
            Function<E, TreeItem<E>> createNode,
            Map<E, TreeItem<E>> sourceToNodeMap,
            E sourceItem,
            Comparator<TreeItem<E>> comparator)
    {
        TreeItem<E> sourceNode = createNode.apply(sourceItem);
        E parentItem = getParent.apply(sourceItem);
        TreeItem<E> parentNode;
        if (parentItem == null)
            parentNode = rootNode;
        else if (sourceToNodeMap.containsKey2(parentItem))
            parentNode = sourceToNodeMap.get2(parentItem);
        else
            parentNode = addTreeElement(rootNode, getParent, createNode, sourceToNodeMap, parentItem, comparator);
        ObservableList<TreeItem<E>> parentChildren = parentNode.getChildren();
        BinarySearchResult result = new BinarySearchResult(Collections.binarySearch(parentChildren, sourceNode, comparator));
        parentChildren.add(result.getIndex(), sourceNode);
        sourceToNodeMap.put(sourceItem, sourceNode);
        return sourceNode;
    }

}
