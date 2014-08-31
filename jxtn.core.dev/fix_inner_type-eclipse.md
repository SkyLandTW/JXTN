Fix inner type in Eclipse JDT
==============================

Inner types since Java 8 sometimes cause Eclipse JDT code assist and hyperlink
to stop working.

Bug report: https://bugs.eclipse.org/bugs/show_bug.cgi?id=442956

------------------------------------------------------------------------------

Modify *org.eclipse.jdt.core*
-----------------------------

##### org.eclipse.jdt.internal.compiler.lookup.UnresolvedReferenceBinding

Under method *resolve()*, change:

```java
if (targetType == null || targetType == this) { // could not resolve any better, error was already reported against it //$IDENTITY-COMPARISON$
    // report the missing class file first - only if not resolving a previously missing type
    if ((this.tagBits & TagBits.HasMissingType) == 0 && !environment.mayTolerateMissingType) {
            environment.problemReporter.isClassPathCorrect(
                this.compoundName,
                environment.unitBeingCompleted,
                environment.missingClassFileLocation);
    }
```

into:

```java
if (targetType == null || targetType == this) { // could not resolve any better, error was already reported against it //$IDENTITY-COMPARISON$
    // report the missing class file first - only if not resolving a previously missing type
    if ((this.tagBits & TagBits.HasMissingType) == 0 && !environment.mayTolerateMissingType) {
        String fullName = String.join(".", Arrays.asList(this.compoundName).map(a -> new String(a)));
        System.err.println("\tunresolved type: " + fullName);
        if (!(fullName.startsWith("java.") && fullName.contains("$")))
            environment.problemReporter.isClassPathCorrect(
                this.compoundName,
                environment.unitBeingCompleted,
                environment.missingClassFileLocation);
    }
```

