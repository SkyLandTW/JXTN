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
 * @param <Head> 路徑開頭的詮釋資料型態(即源頭物件的詮釋資料型態)
 * @param <Next> 路徑後段的詮釋資料型態(即目前屬性的詮釋資料型態)
 */
public class PathStepImpl<Head, Next> implements PathStep<Head, Next>
{
    protected final boolean hasHead;
    protected final Head head;
    protected final PathStep<Head, ?> parent;
    protected final String step;

    public PathStepImpl()
    {
        this.hasHead = false;
        this.head = null;
        this.parent = null;
        this.step = null;
    }

    public PathStepImpl(Head head)
    {
        this.hasHead = true;
        this.head = head;
        this.parent = null;
        this.step = null;
    }

    public PathStepImpl(PathStep<Head, ?> parent, String step)
    {
        Objects.requireNonNull(parent);
        Objects.requireNonNull(step);
        this.hasHead = false;
        this.head = null;
        this.parent = parent;
        this.step = step;
    }

    @Override
    public boolean hasHead()
    {
        if (this.isRoot())
            return this.hasHead;
        else
            return this.getParent().hasHead();
    }

    @Override
    public Head getHead()
    {
        if (this.isRoot())
        {
            if (!this.hasHead)
                throw new IllegalStateException();
            return this.head;
        }
        else
            return this.getParent().getHead();
    }

    @Override
    public PathStep<Head, ?> getParent()
    {
        return this.parent;
    }

    @Override
    public String getStep()
    {
        return this.step;
    }
}
