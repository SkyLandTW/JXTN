Fix OleClientSite with Office 2007 in Eclipse SWT
=================================================

Disable special handling of Office 2007 documents in *OleClientSite* in Eclipse
SWT.

------------------------------------------------------------------------------

Modify *org.eclipse.swt.win32*
------------------------------

##### org.eclipse.swt.ole.win32.OleClientSite

Under method *OleCreate()*, replace the code:

from:

```java
if (!isOffice2007 && COM.IsEqualGUID(appClsid, fileClsid)){
```

to:

```java
if (true){
```
