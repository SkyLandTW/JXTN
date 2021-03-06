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

#### Cautions

- Several I/O functions have names such as "readNB" or "writeNB", the "NB" indicate non-blocking property of the fd
  itself, which could eliminate the need of duplicating buffers (NOT SAFE according to JNI spec).
  Blockable operations such as those on socket should use read() and write() which resort to the standard method of
  copying C buffer to/from Java buffer (GC may rearrange Java buffers during I/O waiting). If the caller is uncertain,
  the blocking version should be used instead for reliability.
- Some operations such as *getdents64* or *readlink* never copy Java buffers. It's uncertain whether there would be
  issues on slow FS like NFS or CIFS.

#### Build & Install

1. Install following headers:

   - JDK headers: openjdk-8-jdk
   - xattr headers: libattr1-dev
   - PAM headers: libpam0g-dev

   ```shell
   apt-get install openjdk-8-jdk libattr1-dev libpam0g-dev
   ```

2. Set *JAVA_HOME* to the JDK directory
3. Execute *make install* in the *jxtn-core-unix* directory
4. Build the JAR file by ant

*libjxtn-core-unix.so* will be installed into */usr/java/packages/lib/amd64/* by make
