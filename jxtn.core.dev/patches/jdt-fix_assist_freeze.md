Fix code-assist freeze in Eclipse JDT
=====================================

Bug report: https://bugs.eclipse.org/bugs/show_bug.cgi?id=482663

The bug is triggered by the inability of JDT CompletionParser to quit from
parse-error-resume cycle in 4.5 Mars.

------------------------------------------------------------------------------

Modify *org.eclipse.jdt.core*
-----------------------------

##### org.eclipse.jdt.internal.codeassist.complete.CompletionParser

Under method *resumeOnSyntaxError()*, insert in the beginning:

```java
    if (this.monitor == null) {
        if (++this.resumeOnSyntaxError > 100) {
            this.resumeOnSyntaxError = 0;
            return HALT;
        }
    }
```
