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

package javafx.embed.swt;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLClassLoaderExt;
import java.nio.file.Paths;
import java.security.ProtectionDomain;

import org.eclipse.osgi.internal.framework.EquinoxConfiguration;
import org.eclipse.osgi.internal.loader.ModuleClassLoader;
import org.eclipse.osgi.internal.loader.classpath.ClasspathEntry;
import org.eclipse.osgi.internal.loader.classpath.ClasspathManager;
import org.eclipse.osgi.storage.BundleInfo.Generation;
import org.eclipse.osgi.storage.bundlefile.BundleFile;
import org.eclipse.osgi.storage.bundlefile.ZipBundleFile;

/**
 * SWT-JavaFX載入器
 *
 * @author AqD
 */
@SuppressWarnings("restriction")
public final class FXLoader
{
    /**
     * 載入JavaFX整合用的<b>jfxswt.jar</b>
     * <ul>
     * <li>掛載<b>jfxswt.jar</b>到目前的{@link ClassLoader}內</li>
     * </ul>
     */
    public static void load()
    {
        classLoader_addClasspath(
                FXLoader.class.getClassLoader(),
                Paths.get(System.getProperty("java.home"), "lib", "jfxswt.jar").toFile());
    }

    /**
     * 於{@link ClassLoader}加上新的搜尋路徑
     * <p>
     * 支援以下格式的{@link ClassLoader}：
     * <ul>
     * <li>{@link java.net.URLClassLoader}：系統的類別載入器</li>
     * <li>{@link org.eclipse.osgi.internal.loader.ModuleClassLoader}：Eclipse外掛的類別載入器</li>
     * </ul>
     * </p>
     *
     * @param classLoader 類別載入器
     * @param jarFile 要加入類別搜尋路徑的JAR檔位置
     * @throws UnsupportedOperationException 不支援的{@code classLoader}型態
     */
    private static void classLoader_addClasspath(ClassLoader classLoader, File jarFile)
    {
        /*
         * Java預設
         */
        if (classLoader instanceof URLClassLoader)
        {
            URL jarURL;
            try
            {
                jarURL = jarFile.toURI().toURL();
            }
            catch (MalformedURLException e)
            {
                throw new RuntimeException(e);
            }
            URLClassLoaderExt.addURL((URLClassLoader) classLoader, jarURL);
        }
        /*
         * Eclipse 4.3 Kepler
         */
        /*
        else if (classLoader instanceof DefaultClassLoader)
        {
            try
            {
                DefaultClassLoader defaultClassLoader = (DefaultClassLoader) classLoader;
                ClasspathManager classpathManager = defaultClassLoader.getClasspathManager();
                // Get ClasspathManager.entries
                if (ClasspathManager_entriesField == null)
                {
                    ClasspathManager_entriesField = ClasspathManager.class.getDeclaredField("entries");
                    ClasspathManager_entriesField.setAccessible(true);
                    Field_modifiersField.setInt(ClasspathManager_entriesField, ClasspathManager_entriesField.getModifiers() & ~Modifier.FINAL);
                }
                ClasspathEntry[] oldEntries = (ClasspathEntry[]) ClasspathManager_entriesField.get(classpathManager);
                // Create ClasspathEntry for the given jarFile
                BundleFile jarBundleFile = new ZipBundleFile(jarFile, null);
                ProtectionDomain domain = oldEntries[0].getDomain();
                ClasspathEntry jarEntry = new ClasspathEntry(jarBundleFile, domain);
                // Prepare new entries
                ClasspathEntry[] newEntries = new ClasspathEntry[oldEntries.length + 1];
                System.arraycopy(oldEntries, 0, newEntries, 0, oldEntries.length);
                newEntries[oldEntries.length] = jarEntry;
                // Set new entries to replace the old one
                ClasspathManager_entriesField.set(classpathManager, newEntries);
                E3Log.logger().fine("added classpath: " + jarFile);
            }
            catch (IOException | ReflectiveOperationException e)
            {
                throw new RuntimeException(e);
            }
        }
         */
        /*
         * Eclipse 4.4 Luna
         */
        else if (classLoader instanceof ModuleClassLoader)
        {
            try
            {
                ModuleClassLoader moduleClassLoader = (ModuleClassLoader) classLoader;
                ClasspathManager classpathManager = moduleClassLoader.getClasspathManager();
                //
                if (Field_modifiersField == null)
                {
                    Field_modifiersField = Field.class.getDeclaredField("modifiers");
                    Field_modifiersField.setAccessible(true);
                }
                // Get Configuration
                if (ModuleClassLoader_getConfigurationMethod == null)
                {
                    ModuleClassLoader_getConfigurationMethod = ModuleClassLoader.class
                            .getDeclaredMethod("getConfiguration");
                    ModuleClassLoader_getConfigurationMethod.setAccessible(true);
                }
                EquinoxConfiguration config = (EquinoxConfiguration) ModuleClassLoader_getConfigurationMethod
                        .invoke(moduleClassLoader);
                // Get ClasspathManager.entries
                if (ClasspathManager_entriesField == null)
                {
                    ClasspathManager_entriesField = ClasspathManager.class.getDeclaredField("entries");
                    ClasspathManager_entriesField.setAccessible(true);
                    Field_modifiersField.setInt(ClasspathManager_entriesField,
                            ClasspathManager_entriesField.getModifiers() & ~Modifier.FINAL);
                }
                ClasspathEntry[] oldEntries = (ClasspathEntry[]) ClasspathManager_entriesField
                        .get(classpathManager);
                // Create ClasspathEntry for the given jarFile
                BundleFile jarBundleFile = new ZipBundleFile(jarFile, null, null, config.getDebug());
                ProtectionDomain domain = oldEntries[0].getDomain();
                Generation generation = classpathManager.getGeneration();
                ClasspathEntry jarEntry = new ClasspathEntry(jarBundleFile, domain, generation);
                // Prepare new entries
                ClasspathEntry[] newEntries = new ClasspathEntry[oldEntries.length + 1];
                System.arraycopy(oldEntries, 0, newEntries, 0, oldEntries.length);
                newEntries[oldEntries.length] = jarEntry;
                // Set new entries to replace the old one
                ClasspathManager_entriesField.set(classpathManager, newEntries);
            }
            catch (IOException | ReflectiveOperationException e)
            {
                throw new RuntimeException(e);
            }
        }
        else
        {
            throw new UnsupportedOperationException(classLoader.toString());
        }
    }

    private static Field Field_modifiersField;
    private static Method ModuleClassLoader_getConfigurationMethod;
    private static Field ClasspathManager_entriesField;

    private FXLoader()
    {
    }
}
