/*
 * Copyright © 2014 Skyland Inc.
 * Copyright © 2014 天基科技有限公司。
 */

package javafx.embed.swt;

import java.lang.reflect.Field;
import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.InputMethodHighlight;
import javafx.scene.input.InputMethodTextRun;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.IME;

import com.sun.javafx.collections.ObservableListWrapper;
import com.sun.javafx.embed.EmbeddedSceneInterface;

/**
 * 修正IME輸入問題的{@link FXCanvas}
 * <ul>
 * <li>修正IME輸入問題</li>
 * </ul>
 *
 * @author AqD
 */
public class FXCanvas2 extends FXCanvas
{
    private static Field scenePeerField;

    static
    {
        try
        {
            scenePeerField = FXCanvas.class.getDeclaredField("scenePeer");
        }
        catch (NoSuchFieldException e)
        {
            throw new RuntimeException(e);
        }
        scenePeerField.setAccessible(true);
    }

    private final IME ime;

    private EmbeddedSceneInterface scenePeer;

    public FXCanvas2(Composite parent, int style)
    {
        super(parent, style);
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
            }
        }
    }

    private String getTextForEvent(String text)
    {
        if (text == null)
            return "";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if (c == CharacterIterator.DONE)
                break;
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
