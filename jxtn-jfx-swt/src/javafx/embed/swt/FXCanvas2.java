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

import com.sun.javafx.collections.ObservableListWrapper;
import com.sun.javafx.embed.EmbeddedSceneInterface;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.input.InputMethodHighlight;
import javafx.scene.input.InputMethodRequests;
import javafx.scene.input.InputMethodTextRun;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.COMPOSITIONFORM;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.IME;

/**
 * 修正版的{@link FXCanvas}，包含可實作於子類別的修正
 * <ul>
 * <li>修正IME輸入問題（<a href="https://javafx-jira.kenai.com/browse/RT-39268">JavaFX-JIRA RT-39268</a>）</li>
 * </ul>
 *
 * @author AqD
 */
@SuppressWarnings("restriction")
public class FXCanvas2 extends FXCanvas
{
    private static Field scenePeerField;
    private static Method sendResizeEventToFXMethod;

    static
    {
        try
        {
            scenePeerField = FXCanvas.class.getDeclaredField("scenePeer");
            sendResizeEventToFXMethod = FXCanvas.class.getDeclaredMethod("sendResizeEventToFX");
        }
        catch (ReflectiveOperationException e)
        {
            throw new RuntimeException(e);
        }
        scenePeerField.setAccessible(true);
        sendResizeEventToFXMethod.setAccessible(true);
    }

    private final IME ime;

    private EmbeddedSceneInterface scenePeer;

    @SuppressWarnings("deprecation")
    public FXCanvas2(Composite parent, int style)
    {
        super(parent, style);
        if (System.getProperty("os.name").toLowerCase().contains("win"))
        {
            this.ime = new IME(this, SWT.NONE);
            this.ime.addListener(SWT.ImeComposition, new org.eclipse.swt.widgets.Listener()
                {
                    @Override
                    public void handleEvent(org.eclipse.swt.widgets.Event event)
                    {
                        switch (event.detail)
                        {
                        case SWT.COMPOSITION_CHANGED:
                            FXCanvas2.this.sendInputMethodEventToFX();
                            break;
                        }
                    }

                });
        }
        else
        {
            this.ime = null;
        }
    }

    /**
     * 取得JavaFX的控制介面
     *
     * @return 控制介面
     */
    public EmbeddedSceneInterface getScenePeer()
    {
        if (this.scenePeer == null)
        {
            try
            {
                this.scenePeer = (EmbeddedSceneInterface) scenePeerField.get(this);
            }
            catch (IllegalAccessException e)
            {
                throw new RuntimeException(e);
            }
        }
        return this.scenePeer;
    }

    /**
     * 強迫JavaFX的{@link javafx.stage.Stage}及{@link javafx.scene.Scene}重新依照目前畫布更新尺寸
     */
    public void sendResizeEventToFX()
    {
        try
        {
            FXCanvas2.sendResizeEventToFXMethod.invoke(this);
        }
        catch (ReflectiveOperationException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void sendInputMethodEventToFX()
    {
        String text = this.getTextForEvent(this.ime.getText());
        EmbeddedSceneInterface scenePeer = this.getScenePeer();
        if (scenePeer != null)
        {
            String committed = text.substring(0, this.ime.getCommitCount());
            if (this.ime.getText().length() == this.ime.getCommitCount())
            {
                // Send empty text when committed, because the actual chars will then be immediately sent via keys.
                scenePeer.inputMethodEvent(
                        javafx.scene.input.InputMethodEvent.INPUT_METHOD_TEXT_CHANGED,
                        FXCollections.emptyObservableList(), "", this.ime.getCompositionOffset());
            }
            else
            {
                ObservableList<InputMethodTextRun> composed = this.inputMethodEventComposed(text, this.ime.getCommitCount());
                scenePeer.inputMethodEvent(
                        javafx.scene.input.InputMethodEvent.INPUT_METHOD_TEXT_CHANGED,
                        composed, committed, this.ime.getCaretOffset());
                this.updateImeCandicatePos();
            }
        }
    }

    @SuppressWarnings("deprecation")
    private void updateImeCandicatePos()
    {
        EmbeddedSceneInterface emScene = (EmbeddedSceneInterface) this.getScene().impl_getPeer();
        InputMethodRequests imeRequests = emScene.getInputMethodRequests();
        Point2D absolutePos = imeRequests.getTextLocation(0);
        COMPOSITIONFORM lpCompForm = new COMPOSITIONFORM();
        lpCompForm.dwStyle = OS.CFS_CANDIDATEPOS;
        Point relativePos = this.toControl((int) absolutePos.getX(), (int) absolutePos.getY());
        lpCompForm.x = relativePos.x;
        lpCompForm.y = relativePos.y;
        long hIMC = OS.ImmGetContext(this.handle);
        try
        {
            OS.ImmSetCompositionWindow(hIMC, lpCompForm);
        }
        finally
        {
            OS.ImmReleaseContext(this.handle, hIMC);
        }
    }

    private String getTextForEvent(String text)
    {
        if (text == null)
        {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if (c == CharacterIterator.DONE)
            {
                break;
            }
            builder.append(c);
        }
        return builder.toString();
    }

    private ObservableList<InputMethodTextRun> inputMethodEventComposed(String text, int commitCount)
    {
        List<InputMethodTextRun> composed = new ArrayList<>();
        if (commitCount < text.length())
        {
            composed.add(new InputMethodTextRun(
                    text.substring(commitCount), InputMethodHighlight.UNSELECTED_RAW));
        }
        return new ObservableListWrapper<>(composed);
    }
}
