Multi-line strings in Eclipse JDT
======================================

Supports multi-line string literals in Eclipse JDT

##### What works:
 - Eclipse JDT compilation
 - Eclipse JDT code assist
 - Eclipse JDT syntax highlighting

##### Tested environments:
 - Eclipse v4.4 (luna)
   * org.eclipse.jdt.core v3.10.0
   * org.eclipse.jdt.ui v3.10.0

------------------------------------------------------------------------------

Modify *org.eclipse.jdt.core*
-----------------------------

##### org.eclipse.jdt.internal.codeassist.complete.CompletionScanner

Remove the following lines:

```java
/**** \r and \n are not valid in string literals ****/
if ((this.currentCharacter == '\n') || (this.currentCharacter == '\r')) {
    ....
    throw new InvalidInputException(INVALID_CHAR_IN_STRING);
}
```

##### org.eclipse.jdt.internal.compiler.parser.Scanner

Remove the following lines:

```java
/**** \r and \n are not valid in string literals ****/
if ((this.currentCharacter == '\n') || (this.currentCharacter == '\r')) {
    ....
    throw new InvalidInputException(INVALID_CHAR_IN_STRING);
}
```

------------------------------------------------------------------------------

Modify *org.eclipse.jdt.ui*
---------------------------

##### org.eclipse.jdt.internal.ui.text.FastJavaPartitionScanner

Comment out the *case STRING* lines from:

```java
case '\r':
    // emulate JavaPartitionScanner
    if (!fEmulate && fLast != CARRIAGE_RETURN) {
        fLast= CARRIAGE_RETURN;
        fTokenLength++;
        continue;

    } else {

        switch (fState) {
        case SINGLE_LINE_COMMENT:
        case CHARACTER:
        // case STRING:     <<==== !!! COMMENT OUT THIS LINE !!!
            if (fTokenLength > 0) {
```

and:

```java
case '\n':
    switch (fState) {
    case SINGLE_LINE_COMMENT:
    case CHARACTER:
    // case STRING:         <<==== !!! COMMENT OUT THIS LINE !!!
        // assert(fTokenLength > 0);
        return postFix(fState);
```

There are also *JavaPartitionScanner* and *JavaCodeScanner*, both containing
String-parsing rules but seem to have been deprecated.

