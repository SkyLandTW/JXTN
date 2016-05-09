jxtn-core-unix
--------------

Provide direct access to Unix/Linux system calls to replace the crippled and inefficient system interfacing in Java.

#### Dependencies

- jxtn-core-axi (Collection extensions)
- OpenJDK 8

#### Restrictions

This library is *NOT* meant to be portable, since most other OS and CPU architectures are dead anyway.

It's intended for:
- Architecture: 64-bit
- Endianness: Little Endian
- Default Charset: UTF-8
- Target OS: Linux after v3.0

#### Current Goals

- Maximum performance and minimum memory cost; use of reflection shall be minimal and JNA cannot be used.
- UTF-8 encoded strings (ByteString)
- Very basic calls such as getpid/getuid
- Direct File Operations without exceptions
- Direct File I/O without Java streams
- Direct File Mapping (64-bit) 

(Networking is of lower priority because I don't need it for now)
