Fix WM_IME_ENDCOMPOSITION in Eclipse SWT
========================================

Correctly handles *WM_IME_ENDCOMPOSITION* message by firing a
*COMPOSITION_CHANGED* event containing empty composed text and empty committed
text.

------------------------------------------------------------------------------

Modify *org.eclipse.swt.win32*
------------------------------

##### org.eclipse.swt.widgets.IME

Under method *WM_IME_ENDCOMPOSITION()*, replace the code:

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
        Event event = new Event ();
        event.detail = SWT.COMPOSITION_CHANGED;
        event.start = startOffset;
        event.end = startOffset;
        event.text = "";
        startOffset = -1;
        ranges = null;
        styles = null;
        caretOffset = commitCount = 0;
        text = "";
        sendEvent (SWT.ImeComposition, event);
        return LRESULT.ONE;
    }
    return null;
}
```
