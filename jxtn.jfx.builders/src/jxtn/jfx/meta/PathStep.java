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

package jxtn.jfx.meta;

import java.util.Collections;
import java.util.List;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.value.ObservableValue;

/**
 * 代表物件路徑
 *
 * @author AqD
 * @param <TSource> 路徑源頭的資料型態
 * @param <TTarget> 路徑目的的資料型態
 */
public interface PathStep<TSource, TTarget>
{
    /**
     * 是否有資料來源
     * <p>
     * 資料來源可定義為null，這裡依然會傳回true
     * </p>
     *
     * @return true表示目前路徑有定義資料來源
     */
    public boolean hasDataSource();

    /**
     * 取得資料來源
     *
     * @return 資料來源，可能為null
     * @throws IllegalStateException 未指定資料來源
     */
    public TSource getDataSource();

    /**
     * 取得前一段路徑
     *
     * @return 前一段路徑，或null表示目前路徑為根路徑。
     */
    public PathStep<TSource, ?> getParent();

    /**
     * 取得目前路徑片段名稱
     *
     * @return 目前路徑片段，即屬性名稱。根路徑片段為null。
     */
    public String getStepName();

    /**
     * 是否為根路徑
     *
     * @return true表示目前路徑為根路徑
     */
    default boolean isRoot()
    {
        return this.getParent() == null;
    }

    /**
     * 列出所有經過的路徑片段，不含根路徑
     *
     * @return 所有經過的路徑片段，不含根路徑
     */
    default List<String> listPath()
    {
        PathStep<TSource, ?> tail = this;
        List<String> steps = IterableExt.linkLine(tail, p -> p.getParent())
                .filter(p -> !p.isRoot())
                .map(p -> p.getStepName())
                .toArrayList();
        Collections.reverse(steps);
        return Collections.unmodifiableList(steps);
    }

    /**
     * 建立物件繫節，用{@link #getDataSource}
     *
     * @return 物件繫節
     * @throws IllegalStateException 未定義資料來源
     */
    @SuppressWarnings("unchecked")
    default ObjectBinding<TTarget> bind()
    {
        TSource head = this.getDataSource();
        if (this.isRoot())
        {
            return Bindings.createObjectBinding(() -> (TTarget) head, (Observable) head);
        }
        else
        {
            List<String> pathList = this.listPath();
            String[] pathArray = pathList.toArray(new String[pathList.size()]);
            return Bindings.select(head, pathArray);
        }
    }

    /**
     * 建立物件繫節，用指定的資料來源
     *
     * @param head 資料來源
     * @return 物件繫節
     */
    @SuppressWarnings("unchecked")
    default ObjectBinding<TTarget> bind(TSource head)
    {
        if (this.isRoot())
        {
            return Bindings.createObjectBinding(() -> (TTarget) head, (Observable) head);
        }
        else
        {
            List<String> pathList = this.listPath();
            String[] pathArray = pathList.toArray(new String[pathList.size()]);
            return Bindings.select(head, pathArray);
        }
    }

    /**
     * 建立物件繫節，用指定的資料來源
     *
     * @param headObservable 資料來源
     * @return 物件繫節
     */
    @SuppressWarnings("unchecked")
    default ObjectBinding<TTarget> bind(ObservableValue<? extends TSource> headObservable)
    {
        if (this.isRoot())
        {
            return Bindings.createObjectBinding(() -> (TTarget) headObservable.getValue(), headObservable);
        }
        else
        {
            List<String> pathList = this.listPath();
            if (pathList.isEmpty())
                throw new IllegalStateException(this.toString());
            String[] pathArray = pathList.toArray(new String[pathList.size()]);
            return Bindings.select(headObservable, pathArray);
        }
    }
}
