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

package java.nio.file;

import java.io.IOException;

import jxtn.core.axi.io.FileCopyingVisitor;
import jxtn.core.axi.io.FileDelectionVisitor;

/**
 * {@link Path}的延伸功能
 *
 * @author AqD
 */
public interface PathExt
{
    /**
     * 複製路徑，包含子目錄及檔案
     *
     * @param target 複製目的
     * @throws IOException IO錯誤
     */
    default void copy(Path target) throws IOException
    {
        Path thiz = (Path) this;
        FileVisitor<Path> visitor = new FileCopyingVisitor(thiz, target);
        Files.walkFileTree(thiz, visitor);
    }

    /**
     * 刪除路徑，包含子目錄及檔案
     *
     * @throws IOException IO錯誤
     */
    default void delete() throws IOException
    {
        Path thiz = (Path) this;
        FileVisitor<Path> visitor = new FileDelectionVisitor();
        Files.walkFileTree(thiz, visitor);
    }
}
