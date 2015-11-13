Multi-line Strings in Eclipse JDT
=================================

Supports multi-line string literals in Eclipse JDT

##### What works:
 - Eclipse JDT compilation
 - Eclipse JDT code assist
 - Eclipse JDT syntax highlighting
 - Eclipse JDT generated LineNumberTable (*)

##### Tested environments:
 - Eclipse v4.4 (Luna)
   * org.eclipse.jdt.core v3.10.0
   * org.eclipse.jdt.ui v3.10.0
 - Eclipse v4.5 (Mars)
   * org.eclipse.jdt.core v3.11.0
   * org.eclipse.jdt.ui v3.11.0
 - Eclipse v4.5.1 (Mars 1)
   * org.eclipse.jdt.core v3.11.1
   * org.eclipse.jdt.ui v3.11.1

------------------------------------------------------------------------------

Modify *org.eclipse.jdt.core*
-----------------------------

##### org.eclipse.jdt.internal.codeassist.complete.CompletionScanner

Search for *INVALID_CHAR_IN_STRING*, make the following changes:

```java
/**** \r and \n are not valid in string literals ****/
if ((this.currentCharacter == '\n') || (this.currentCharacter == '\r')) {
    ....                                                        // !REMOVE!
    throw new InvalidInputException(INVALID_CHAR_IN_STRING);    // !REMOVE!
    if (this.recordLineSeparator) {                             // !INSERT!
        if (isUnicode) {                                        // !INSERT!
            pushUnicodeLineSeparator();                         // !INSERT!
        } else {                                                // !INSERT!
            pushLineSeparator();                                // !INSERT!
        }                                                       // !INSERT!
    }                                                           // !INSERT!
}
```

##### org.eclipse.jdt.internal.compiler.parser.Scanner

Search for *INVALID_CHAR_IN_STRING*, make the following changes:

```java
/**** \r and \n are not valid in string literals ****/
if ((this.currentCharacter == '\n') || (this.currentCharacter == '\r')) {
    ....                                                        // !REMOVE!
    throw new InvalidInputException(INVALID_CHAR_IN_STRING);    // !REMOVE!
    if (this.recordLineSeparator) {                             // !INSERT!
        if (isUnicode) {                                        // !INSERT!
            pushUnicodeLineSeparator();                         // !INSERT!
        } else {                                                // !INSERT!
            pushLineSeparator();                                // !INSERT!
        }                                                       // !INSERT!
    }                                                           // !INSERT!
}
```

Search for *the string cannot go further that the line*, make the following changes:

```java
if (this.currentCharacter == '\r'){                                         // !REMOVE!
    if (this.source[this.currentPosition] == '\n') this.currentPosition++;  // !REMOVE!
    break NextToken; // the string cannot go further that the line          // !REMOVE!
}                                                                           // !REMOVE!
if (this.currentCharacter == '\n'){                                         // !REMOVE!
    break; // the string cannot go further that the line                    // !REMOVE!
}                                                                           // !REMOVE!
if (this.currentCharacter == '\r' || this.currentCharacter == '\n') {       // !INSERT!
    if (this.recordLineSeparator) {                                         // !INSERT!
        pushLineSeparator();                                                // !INSERT!
    }                                                                       // !INSERT!
}                                                                           // !INSERT!
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

