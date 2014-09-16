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

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;

/**
 * 代表物件路徑
 *
 * @author AqD
 * @param <Head> 路徑開頭的詮釋資料型態(即源頭物件的詮釋資料型態)
 * @param <Next> 路徑後段的詮釋資料型態(即目前屬性的詮釋資料型態)
 */
public interface PathStep<Head, Next>
{
    public boolean hasHead();

    public Head getHead();

    public PathStep<Head, ?> getParent();

    public String getStep();

    default boolean isRoot()
    {
        return this.getParent() == null;
    }

    default List<String> listPath()
    {
        PathStep<Head, ?> tail = this;
        List<String> steps = IterableExt.linkLine(tail, p -> p.getParent())
                .filter(p -> !p.isRoot())
                .map(p -> p.getStep())
                .toArrayList();
        Collections.reverse(steps);
        return Collections.unmodifiableList(steps);
    }

    default ObjectBinding<Next> bind()
    {
        Head head = this.getHead();
        List<String> pathList = this.listPath();
        String[] pathArray = pathList.toArray(new String[pathList.size()]);
        return Bindings.select(head, pathArray);
    }

    default ObjectBinding<Next> bind(Head head)
    {
        List<String> pathList = this.listPath();
        String[] pathArray = pathList.toArray(new String[pathList.size()]);
        return Bindings.select(head, pathArray);
    }
}
