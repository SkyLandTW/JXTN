Multi-line Strings in Javac
===========================

Supports multi-line string literals in OpenJDK Javac

##### What works:
 - javac compilation
 - javac generated LineNumberTable (*)

##### Tested environments:
 - OpenJDK 1.8.0_91

------------------------------------------------------------------------------

Modify *langtools*
------------------

##### com.sun.tools.javac.parser.JavaTokenizer

Search for *unclosed.str.lit*, make the following changes:

From:

```java
while (reader.ch != '\"' && reader.ch != CR && reader.ch != LF && reader.bp < reader.buflen)
    scanLitChar(pos);
```

To: (remove CR/LF check)

```java
while (reader.ch != '\"' && reader.bp < reader.buflen)
    scanLitChar(pos);
```

Build and copy the class into _lib/tools.jar_
