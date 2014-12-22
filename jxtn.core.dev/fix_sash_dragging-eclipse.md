Fix random freeze caused by dragging sash in Eclipse SWT Renderer
=================================================================

Fix random freeze happened during dragging inside *MPartSashContainer*. The
freeze only appears when there are OpenGL canvases in *MPart* and the freeze
point is *SetWindowPos* or *EndDeferWindowPos*, for reasons unknown.

------------------------------------------------------------------------------

Modify *org.eclipse.e4.ui.workbench.renderers.swt*
--------------------------------------------------

##### org.eclipse.e4.ui.workbench.renderers.swt.SashLayout

Under constructor *SashLayout(host, root)*, inside *MouseMoveListener* remove
the **host.layout()** calls:

```java
host.addMouseMoveListener(new MouseMoveListener() {
    @Override
    public void mouseMove(final MouseEvent e) {
        ...
        ...
        adjustWeights(sashesToDrag, e.x, e.y);
        host.layout();                                          // REMOVE
        host.update();                                          // REMOVE
        if (!updateHostInProgress) {                            // INSERT
            updateHostInProgress = true;                        // INSERT
            host.getDisplay().timerExec(50, this::updateHost);  // INSERT
        }                                                       // INSERT
        ...
        ...
    }
    private void updateHost() {                                 // INSERT
        updateHostInProgress = false;                           // INSERT
        host.layout();                                          // INSERT
        host.update();                                          // INSERT
    }                                                           // INSERT
    private boolean updateHostInProgress;                       // INSERT
});
```

Under constructor *SashLayout(host, root)*, inside *addMouseListener* insert
the **host.layout()** calls:

```java
host.addMouseListener(new MouseListener() {
    @Override
    public void mouseUp(MouseEvent e) {
        host.setCapture(false);
        draggingSashes = false;
        host.layout();                                          // INSERT
        host.update();                                          // INSERT
    }
    ...
    ...
```
