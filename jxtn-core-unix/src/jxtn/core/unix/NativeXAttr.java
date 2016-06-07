package jxtn.core.unix;

import java.nio.file.Path;

/**
 * {@code xattr}-related syscall wrappers
 * 
 * @author aqd
 */
public final class NativeXAttr extends JNIBase {

    public static final int XATTR_CREATE = 0x1; /* set value, fail if attr already exists */
    public static final int XATTR_REPLACE = 0x2; /* set value, fail if attr does not exist */

    /* getxattr */

    public static long getxattr(Path path, String name) {
        return getxattr(tPath(path), tName(name), null);
    }

    public static long getxattr(Path path, String name, byte[] value) {
        return getxattr(tPath(path), tName(name), value);
    }

    public static long getxattr(String path, String name) {
        return getxattr(tPath(path), tName(name), null);
    }

    public static long getxattr(String path, String name, byte[] value) {
        return getxattr(tPath(path), tName(name), value);
    }

    static native long getxattr(byte[] path, byte[] name, byte[] value);

    /* setxattr */

    public static int setxattr(Path path, String name, byte[] value, int flags) {
        return setxattr(tPath(path), tName(name), value, flags);
    }

    public static int setxattr(String path, String name, byte[] value, int flags) {
        return setxattr(tPath(path), tName(name), value, flags);
    }

    static native int setxattr(byte[] path, byte[] name, byte[] value, int flags);

    /* fgetxattr */

    public static long fgetxattr(int fd, String name) {
        return fgetxattr(fd, tName(name), null);
    }

    public static long fgetxattr(int fd, String name, byte[] value) {
        return fgetxattr(fd, tName(name), value);
    }

    static native long fgetxattr(int fd, byte[] name, byte[] value);

    /* fsetxattr */

    public static int fsetxattr(int fd, String name, byte[] value, int flags) {
        return fsetxattr(fd, tName(name), value, flags);
    }

    static native int fsetxattr(int fd, byte[] name, byte[] value, int flags);

    /* lgetxattr */

    public static long lgetxattr(Path path, String name) {
        return lgetxattr(tPath(path), tName(name), null);
    }

    public static long lgetxattr(Path path, String name, byte[] value) {
        return lgetxattr(tPath(path), tName(name), value);
    }

    public static long lgetxattr(String path, String name) {
        return lgetxattr(tPath(path), tName(name), null);
    }

    public static long lgetxattr(String path, String name, byte[] value) {
        return lgetxattr(tPath(path), tName(name), value);
    }

    static native long lgetxattr(byte[] path, byte[] name, byte[] value);

    /* lsetxattr */

    public static int lsetxattr(Path path, String name, byte[] value, int flags) {
        return lsetxattr(tPath(path), tName(name), value, flags);
    }

    public static int lsetxattr(String path, String name, byte[] value, int flags) {
        return lsetxattr(tPath(path), tName(name), value, flags);
    }

    static native int lsetxattr(byte[] path, byte[] name, byte[] value, int flags);

    private NativeXAttr() {
    }
}
