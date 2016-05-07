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

package jxtn.core.axi.util;

import java.util.tuple.BaseTuple;

/**
 * 二元搜尋結果。
 *
 * @author AqD
 */
@SuppressWarnings("serial")
public class BinarySearchResult extends BaseTuple<BinarySearchResult> {
    private final boolean found;
    private final int insertPoint;

    /**
     * 用{@link java.util.Collections#binarySearch}的傳回值建立二元搜尋結果。
     *
     * @param index {@link java.util.Collections#binarySearch}的傳回值，大於或等於0表示找到目標的位置，否則為-(插入點+1)
     */
    public BinarySearchResult(int index) {
        this(index >= 0, index >= 0 ? index : -index - 1);
    }

    /**
     * 建立二元搜尋結果。
     *
     * @param found true表示找到目標
     * @param insertPoint 目標位置或插入點
     */
    public BinarySearchResult(boolean found, int insertPoint) {
        super(found, insertPoint);
        this.found = found;
        this.insertPoint = insertPoint;
    }

    /**
     * 是否找到目標。
     *
     * @return true表示找到目標
     */
    public boolean isFound() {
        return this.found;
    }

    /**
     * 目標位置或插入點（依照{@link #isFound}決定）。
     * <p>
     * 插入點是指第一個等於或大於目標的項目索引，或是集合的長度（即目前所有項目之後）。
     * </p>
     *
     * @return 目標位置或插入點
     */
    public int getIndex() {
        return this.insertPoint;
    }
}
