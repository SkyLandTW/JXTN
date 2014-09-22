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

import java.util.List;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ObservableValue;

/**
 * 物件路徑實作
 *
 * @author AqD
 * @param <TSource> 路徑源頭的資料型態
 */
public class PathStepToDouble<TSource> extends PathStepBase<TSource, Number>
{
    /**
     * 建立根路徑
     */
    public PathStepToDouble()
    {
        super();
    }

    /**
     * 建立根路徑
     *
     * @param dataSource 資料來源，可為null
     */
    public PathStepToDouble(TSource dataSource)
    {
        super(dataSource);
    }

    /**
     * 建立路徑
     *
     * @param parent 上層路徑
     * @param stepName 目前片段名稱，即屬性名稱
     */
    public PathStepToDouble(PathStep<TSource, ?> parent, String stepName)
    {
        super(parent, stepName);
    }

    /**
     * 建立物件繫節，用{@link #getDataSource}
     *
     * @return 物件繫節
     * @throws IllegalStateException 未定義資料來源
     */
    @Override
    public DoubleBinding bind()
    {
        TSource head = this.getDataSource();
        if (this.isRoot())
        {
            return Bindings.createDoubleBinding(() -> (Double) head, (Observable) head);
        }
        else
        {
            List<String> pathList = this.listPath();
            String[] pathArray = pathList.toArray(new String[pathList.size()]);
            return Bindings.selectDouble(head, pathArray);
        }
    }

    /**
     * 建立物件繫節，用指定的資料來源
     *
     * @param head 資料來源
     * @return 物件繫節
     */
    @Override
    public DoubleBinding bind(TSource head)
    {
        if (this.isRoot())
        {
            return Bindings.createDoubleBinding(() -> (Double) head, (Observable) head);
        }
        else
        {
            List<String> pathList = this.listPath();
            String[] pathArray = pathList.toArray(new String[pathList.size()]);
            return Bindings.selectDouble(head, pathArray);
        }
    }

    /**
     * 建立物件繫節，用指定的資料來源
     *
     * @param headObservable 資料來源
     * @return 物件繫節
     */
    @Override
    public DoubleBinding bind(ObservableValue<? extends TSource> headObservable)
    {
        if (this.isRoot())
        {
            return Bindings.createDoubleBinding(() -> (Double) headObservable.getValue(), headObservable);
        }
        else
        {
            List<String> pathList = this.listPath();
            if (pathList.isEmpty())
                throw new IllegalStateException(this.toString());
            String[] pathArray = pathList.toArray(new String[pathList.size()]);
            return Bindings.selectDouble(headObservable, pathArray);
        }
    }
}
