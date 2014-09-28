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

/**
 * JavaFX的介面延伸（延伸<i>jfxrt.jar</i>）
 * <ul>
 * <li>JAR檔不可放至<i>jre/lib/endorsed</i>目錄下，會無法參考<i>ext</i>內容</li>
 * <li>JAR檔不可放至<i>jre/lib/ext</i>目錄下，會無法確保優先順序在<i>jfxrt.jar</i>之前</li>
 * </ul>
 * <pre>
 * {@code
 * // 假設JAR檔為jre/lib/jxtn.jfx.axi.jar，載入方式如下，以確保順序在其他ext之前
 * URLClassLoader extClassLoader = (URLClassLoader) IterableExt
 *         .linkLine(AppClass.class.getClassLoader(), cl -> cl.getParent())
 *         .first(cl -> cl.getClass().getCanonicalName()
 *                 .equals("sun.misc.Launcher.ExtClassLoader"))
 * Path classpath = Paths.get(System.getProperty("java.home"), "lib", "jxtn.jfx.axi.jar");
 * URLClassLoaderExt.insertURL(extClassLoader, classpath.toUri().toURL());
 * }
 * </pre>
 * @author AqD
 */

package jxtn.jfx.axi;