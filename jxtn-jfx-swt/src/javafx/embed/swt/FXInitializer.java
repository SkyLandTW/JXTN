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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

import javafx.scene.control.Tooltip;
import javafx.util.Duration;

/**
 * SWT-JavaFX初始化
 *
 * @author AqD
 */
public final class FXInitializer
{
    /**
     * 初始化JavaFX
     */
    public static void initialize()
    {
        try
        {
            if (FXCanvas_initFx == null)
            {
                FXCanvas_initFx = FXCanvas.class.getDeclaredMethod("initFx");
                FXCanvas_initFx.setAccessible(true);
            }
            FXCanvas_initFx.invoke(null);
        }
        catch (ReflectiveOperationException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 設定提示功能的延遲時間
     *
     * @param openDelayInMillis 預設1000
     * @param visibleDurationInMillis 預設5000
     * @param closeDelayInMillis 預設200
     * @author Karthik Shiraly http://www.coderanch.com/t/622070/JavaFX/java/control-Tooltip-visible-time-duration
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void setupCustomTooltipBehavior(
            int openDelayInMillis, int visibleDurationInMillis, int closeDelayInMillis)
    {
        try
        {
            Class TTBehaviourClass = null;
            Class[] declaredClasses = Tooltip.class.getDeclaredClasses();
            for (Class c : declaredClasses)
            {
                if (c.getCanonicalName().equals("javafx.scene.control.Tooltip.TooltipBehavior"))
                {
                    TTBehaviourClass = c;
                    break;
                }
            }
            Objects.requireNonNull(TTBehaviourClass);
            Constructor constructor = TTBehaviourClass.getDeclaredConstructor(
                    Duration.class, Duration.class, Duration.class, boolean.class);
            assert (constructor != null);
            constructor.setAccessible(true);
            Object newTTBehaviour = constructor.newInstance(
                    new Duration(openDelayInMillis), new Duration(visibleDurationInMillis),
                    new Duration(closeDelayInMillis), false);
            assert (newTTBehaviour != null);
            Field ttbehaviourField = Tooltip.class.getDeclaredField("BEHAVIOR");
            assert (ttbehaviourField != null);
            ttbehaviourField.setAccessible(true);
            // Cache the default behavior if needed.
            // Object defaultTTBehavior = ttbehaviourField.get(Tooltip.class);
            ttbehaviourField.set(Tooltip.class, newTTBehaviour);
        }
        catch (ReflectiveOperationException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static Method FXCanvas_initFx;

    private FXInitializer()
    {
    }
}
