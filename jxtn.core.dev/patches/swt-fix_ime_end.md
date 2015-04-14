Fix WM_IME_ENDCOMPOSITION in Eclipse SWT
========================================

Correctly handles *WM_IME_ENDCOMPOSITION* message by firing a
*COMPOSITION_CHANGED* event containing empty composed text and empty committed
text.

------------------------------------------------------------------------------

Modify *org.eclipse.swt.win32*
------------------------------

##### org.eclipse.swt.widgets.IME

Under method *WM_IME_ENDCOMPOSITION()*, change the code:

from:

```java
LRESULT WM_IME_ENDCOMPOSITION (long /*int*/ wParam, long /*int*/ lParam) {
    return isInlineEnabled () ? LRESULT.ONE : null;
}
```

to:

```java
LRESULT WM_IME_ENDCOMPOSITION (long /*int*/ wParam, long /*int*/ lParam) {
    if (isInlineEnabled())
    {
        if (text.length() > 0)
        {
            Event event = new Event();
            event.detail = SWT.COMPOSITION_CHANGED;
            event.start = startOffset;
            event.end = startOffset + text.length();
            event.text = "";
            caretOffset = commitCount = 0;
            text = "";
            ranges = null;
            styles = null;
            sendEvent(SWT.ImeComposition, event);
            startOffset = -1;
        }
        return LRESULT.ONE;
    }
    return null;
}
```
