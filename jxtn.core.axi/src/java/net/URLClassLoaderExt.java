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

package java.net;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import sun.misc.URLClassPath;

/**
 * {@link URLClassLoader}的延伸功能。
 *
 * @author AqD
 */
public final class URLClassLoaderExt
{
    private static final Method URLClassLoader_addURLMethod;
    private static final Field URLClassLoader_ucpField;
    private static final Field URLClassPath_loadersField;
    private static final Field URLClassPath_pathField;
    private static final Method URLClassPath_getLoaderMethod;
    private static final Class<?> URLClassPath_Loader;
    private static final Method URLClassPath_Loader_getBaseURLMethod;

    static
    {
        try
        {
            URLClassLoader_addURLMethod = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            URLClassLoader_addURLMethod.setAccessible(true);
            URLClassLoader_ucpField = URLClassLoader.class.getDeclaredField("ucp");
            URLClassLoader_ucpField.setAccessible(true);
            URLClassPath_loadersField = URLClassPath.class.getDeclaredField("loaders");
            URLClassPath_loadersField.setAccessible(true);
            URLClassPath_pathField = URLClassPath.class.getDeclaredField("path");
            URLClassPath_pathField.setAccessible(true);
            URLClassPath_getLoaderMethod = URLClassPath.class.getDeclaredMethod("getLoader", int.class);
            URLClassPath_getLoaderMethod.setAccessible(true);
            URLClassPath_Loader = Arrays.asList(URLClassPath.class.getDeclaredClasses())
                    .first(c -> c.getSimpleName().equals("Loader"));
            URLClassPath_Loader_getBaseURLMethod = URLClassPath_Loader.getDeclaredMethod("getBaseURL");
            URLClassPath_Loader_getBaseURLMethod.setAccessible(true);
        }
        catch (ReflectiveOperationException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加入新的來源位址到結尾。
     *
     * @param classLoader 要加入新位址的類別載入器
     * @param newURL 新的類別來源位址
     */
    public static synchronized void addURL(URLClassLoader classLoader, URL newURL)
    {
        try
        {
            URLClassLoader_addURLMethod.invoke(classLoader, new Object[] { newURL });
        }
        catch (ReflectiveOperationException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加入新的來源位址到開頭。
     * <p>
     * 依賴{@link URLClassLoader}及{@link URLClassPath}內部結構，驗證Java版本：
     * <ul>
     * <li>Oracle Java SE 8u20</li>
     * </ul>
     * </p>
     *
     * @param classLoader 要加入新位址的類別載入器
     * @param newPath 新的類別來源位址
     */
    public static synchronized void insertURL(URLClassLoader classLoader, Path newPath)
    {
        URL newURL;
        try
        {
            newURL = newPath.toUri().toURL();
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
        insertURL(classLoader, newURL);
    }

    /**
     * 加入新的來源位址到開頭。
     * <p>
     * 依賴{@link URLClassLoader}及{@link URLClassPath}內部結構，驗證Java版本：
     * <ul>
     * <li>Oracle Java SE 8u20</li>
     * </ul>
     * </p>
     *
     * @param classLoader 要加入新位址的類別載入器
     * @param newURL 新的類別來源位址
     */
    public static synchronized void insertURL(URLClassLoader classLoader, URL newURL)
    {
        try
        {
            // Modified from http://pastebin.com/SNgmGMwq
            URLClassPath ucp = (URLClassPath) URLClassLoader_ucpField.get(classLoader);
            ucp.addURL(newURL);
            @SuppressWarnings("unchecked") List<URL> path = (List<URL>) URLClassPath_pathField.get(ucp);
            @SuppressWarnings("unchecked") List<Object> loaders = (List<Object>) URLClassPath_loadersField.get(ucp);
            // 強制建立loader (要將loader排到首位)
            URLClassPath_getLoaderMethod.invoke(ucp, path.size() - 1);
            if (path.size() != loaders.size())
            {
                throw new RuntimeException("Ehh... they should be same size!!");
            }
            else
            {
                int lastIndex = path.size() - 1;
                path.add(0, path.remove(lastIndex));
                loaders.add(0, loaders.remove(lastIndex));
            }
        }
        catch (ReflectiveOperationException e)
        {
            throw new RuntimeException(e);
        }
        /*
        for (URL p : path)
        {
            System.out.println("ext path: " + p);
        }
        for (Object l : loaders)
        {
            URL url = (URL) URLClassPath_Loader_getBaseURLMethod.invoke(l);
            System.out.println("ext loader: " + url);
        }
         */
    }

    private URLClassLoaderExt()
    {
    }
}
