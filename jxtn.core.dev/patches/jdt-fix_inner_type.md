Fix inner type in Eclipse JDT
=============================

Bug report: https://bugs.eclipse.org/bugs/show_bug.cgi?id=442956

The bug is triggered by jxtn.core.axi having duplicated inner type *Map$Entry*,
which causes Eclipse JDT code assist and hyperlink to stop working due to
unidentified resolve error.

------------------------------------------------------------------------------

Modify *org.eclipse.jdt.core*
-----------------------------

##### org.eclipse.jdt.internal.compiler.lookup.UnresolvedReferenceBinding

Under method *resolve()*, change:

```java
if (targetType == null || targetType == this) { // could not resolve any better, error was already reported against it //$IDENTITY-COMPARISON$
    // report the missing class file first - only if not resolving a previously missing type
    if ((this.tagBits & TagBits.HasMissingType) == 0 && !environment.mayTolerateMissingType) {
        // ADD THESE LINES
        String fullName = String.join(".", java.util.Arrays.asList(this.compoundName).map(a -> new String(a)));
        System.err.println("\tunresolved type: " + fullName);
        if (!(fullName.startsWith("java.") && fullName.contains("$")))
        // END
            environment.problemReporter.isClassPathCorrect(
                this.compoundName,
                environment.unitBeingCompleted,
                environment.missingClassFileLocation);
    }
```

Note *Iterable.map()* is a function provided by *jxtn.core.axi* - this fix
cannot be directly used without it unless rewritten.
