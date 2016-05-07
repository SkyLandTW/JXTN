Suppress Checked Exceptions in Eclipse JDT
==========================================

Change *Unhandled exception* to a warning instead of an error. No option to
turn off the warning because it's only intended as a temporary workaround
during early stage of development.

------------------------------------------------------------------------------

Modify *org.eclipse.jdt.core*
-----------------------------

##### org.eclipse.jdt.internal.compiler.problem.ProblemReporter

Under method *computeSeverity()*, add the 5 lines:

```java
public int computeSeverity(int problemID){

    switch (problemID) {
        case IProblem.UnhandledException:                           // ADD
        case IProblem.UnhandledExceptionInDefaultConstructor:       // ADD
        case IProblem.UnhandledExceptionInImplicitConstructorCall:  // ADD
        case IProblem.UnhandledExceptionOnAutoClose:                // ADD
            return ProblemSeverities.Warning;                       // ADD
        case IProblem.VarargsConflict :
            return ProblemSeverities.Warning;
        case IProblem.TypeCollidesWithPackage :
            return ProblemSeverities.Warning;
```
