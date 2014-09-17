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

import java.util.Objects;

/**
 * 物件路徑實作
 *
 * @author AqD
 * @param <TSource> 路徑源頭的資料型態
 * @param <TTarget> 路徑目的的資料型態
 */
public class PathStepImpl<TSource, TTarget> implements PathStep<TSource, TTarget>
{
    private final boolean hasDataSource;
    private final TSource dataSource;
    private final PathStep<TSource, ?> parent;
    private final String stepName;

    /**
     * 建立根路徑
     */
    public PathStepImpl()
    {
        this.hasDataSource = false;
        this.dataSource = null;
        this.parent = null;
        this.stepName = null;
    }

    /**
     * 建立根路徑
     *
     * @param dataSource 資料來源，可為null
     */
    public PathStepImpl(TSource dataSource)
    {
        this.hasDataSource = true;
        this.dataSource = dataSource;
        this.parent = null;
        this.stepName = null;
    }

    /**
     * 建立路徑
     *
     * @param parent 上層路徑
     * @param stepName 目前片段名稱，即屬性名稱
     */
    public PathStepImpl(PathStep<TSource, ?> parent, String stepName)
    {
        Objects.requireNonNull(parent);
        Objects.requireNonNull(stepName);
        this.hasDataSource = false;
        this.dataSource = null;
        this.parent = parent;
        this.stepName = stepName;
    }

    @Override
    public boolean hasDataSource()
    {
        if (this.isRoot())
            return this.hasDataSource;
        else
            return this.getParent().hasDataSource();
    }

    @Override
    public TSource getDataSource()
    {
        if (this.isRoot())
        {
            if (!this.hasDataSource)
                throw new IllegalStateException();
            return this.dataSource;
        }
        else
            return this.getParent().getDataSource();
    }

    @Override
    public PathStep<TSource, ?> getParent()
    {
        return this.parent;
    }

    @Override
    public String getStepName()
    {
        return this.stepName;
    }
}
