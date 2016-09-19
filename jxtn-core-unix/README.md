jxtn-core-unix
--------------

Provide direct access to Unix/Linux system calls to replace the crippled and inefficient system interfacing in Java.

#### Dependencies

- OpenJDK 8 or Oracle JDK 8 (Linux version only)
- _jxtn-core-axi_ is **NOT** required.

#### Restrictions

This library is *NOT* meant to be portable, since most other OS and CPU architectures are dead anyway.

It's intended for:
- Architecture: 64-bit
- Endianness: Little Endian
- Default Charset: UTF-8
- Target OS: Linux after v3.0

#### Current Features

- Maximum performance and minimum memory cost; No reflection.
- UTF-8 encoded strings (ByteString)
- Basic calls such as getpid/getuid
- Direct File Operations without exception mechanism
- Direct File I/O without Java streams
- Direct File Mapping (64-bit) 
- Direct Directory Reading through getdents64()
- Process invocation and in/out/err redierction (pexec)
- Process renaming
- BSD Socket related calls

#### Build & Install

1. Install JDK headers ("apt-get install openjdk-8-jdk")
2. Set *JAVA_HOME* to the JDK directory
3. Execute *make install* in the *jxtn-core-unix* directory
4. Build the JAR file by ant

*libjxtn-core-unix.so* will be installed into */usr/lib/jni/* by make
