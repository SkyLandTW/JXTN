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

package javafx.scene.control;

import java.util.Objects;

import jxtn.jfx.makers.DragAndDropSetup;

/**
 * {@link Cell}建構器延伸（供客製化）
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <T> {@link Cell}資料項目型態
 * @param <Z> 要建構的物件型態(需繼承{@link Cell})
 * @param <B> 建構器本身的型態(需繼承{@link CellMaker})
 */
public interface CellMakerExt<T extends java.lang.Object, Z extends Cell<T>, B extends CellMaker<T, Z, B>>
        extends javafx.scene.control.LabeledMakerExt<Z, B>
{
    /**
     * 啟用Drag and Drop拖放功能
     *
     * @param setup 拖放功能設定
     * @return 自己
     */
    default B activateDragAndDrop(DragAndDropSetup<T> setup)
    {
        Objects.requireNonNull(setup);
        this.self().afterBuild(
                DragAndDropSetup.class.getCanonicalName(),
                c -> setup.apply(c, Cell::isEmpty, Cell::getItem));
        return this.self();
    }
}
