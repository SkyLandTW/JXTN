Multi-line strings in Eclipse JDT
======================================

Supports multi-line string literals in Eclipse JDT

##### What works:
 - Eclipse JDT compilation
 - Eclipse JDT code assist

##### What doesn't work:
 - Syntax highlightening (only the first line is colored)

##### Tested environments:
 - Eclipse v4.4 (luna) / org.eclipse.jdt.core v3.10.0

------------------------------------------------------------------------------

**Modify org.eclipse.jdt.core**

org.eclipse.jdt.internal.codeassist.complete.CompletionScanner
--------------------------------------------------------------
Remove the following lines:

    ```java
        /**** \r and \n are not valid in string literals ****/
        if ((this.currentCharacter == '\n') || (this.currentCharacter == '\r')) {
            ....
            throw new InvalidInputException(INVALID_CHAR_IN_STRING);
        }
    ```

org.eclipse.jdt.internal.compiler.parser.Scanner
------------------------------------------------
Remove the following lines:

    ```java
        /**** \r and \n are not valid in string literals ****/
        if ((this.currentCharacter == '\n') || (this.currentCharacter == '\r')) {
            ....
            throw new InvalidInputException(INVALID_CHAR_IN_STRING);
        }
    ```

