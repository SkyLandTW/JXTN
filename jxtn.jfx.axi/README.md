jxtn.jfx.axi
------------

Overrides and extends classes/interfaces already existing in JavaFX.

##### Functionality
 - ObservableValue methods: asObject(mapper)/asBoolean(mapper)/...

##### Runtime License
 - Due to the restriction of 'Oracle Binary Code License Agreement' (
   SUPPLEMENTAL LICENSE TERMS, Section F), you may not able to use this library
   with the official JRE/JDK provided by Oracle. Instead you should use binary
   from OpenJDK - such as [Zulu](http://www.azul.com/downloads/zulu/) or the
   OpenJDK packages on most Linux distributions.
 - On Windows, however, [Zulu](http://www.azul.com/downloads/zulu/) does not
   provide JavaFX (OpenJFX) in their binaries as of 2015 Nov, you must either
   build your own or use [mine](https://github.com/AqD/OpenJFX-binary-windows).

##### Dependencies
 - OpenJDK (as source) for Java version updating: To update the project for
   newer Java versions, simply copy the latest source files from OpenJDK,
   override and modify *interface ...* line of each file to extend extension
   interfaces. For instance:

   ```java
   public interface ObservableValue<T> extends Observable {}
   ```

   change to:

   ```java
   public interface ObservableValue<T> extends Observable, ObservableValueExt<T> {}
   ```

##### Installation
 1. Build *jxtn.jfx.axi*
 2. Drop *jxtn.jfx.axi* into your own project(s). The library overrides
    inner interfaces (called by JavaFX itself, as opposed to builder classes),
    so it wouldn't be as simple as modifying classpath, and _endorsed_ cannot
    work because the class loader which loads endorsed classes is the bootstrap
    one, which cannot load anything under _ext_ as needed by this library.
    There are two ways:

    * Put the library into _jre/lib/ext_, rename the file to something like
      _jfxaxi.jar_ to make sure it's before _jfxrt.jar_ (type *dir* on cmd to
      check this). While there is no guarantee on file order from _File.list()_
      calls, it actually works on NTFS and possibly some of Linux filesystems
      due to the internal organization (mostly B-tree) of file entries on
      directories.

    * Modify the classpath list of _ExtClassLoader_ at runtime by
      _java.net.URLClassLoaderExt.insertURL()_ from _jxtn.core.axi_:

      ```java
      URLClassLoader extClassLoader = (URLClassLoader) IterableExt
              .linkLine(AppClass.class.getClassLoader(), cl -> cl.getParent())
              .first(cl -> cl.getClass().getCanonicalName().equals("sun.misc.Launcher.ExtClassLoader"))
      Path classpath = Paths.get("X:/somewhere/jxtn.jfx.axi.jar");
      URLClassLoaderExt.insertURL(extClassLoader, classpath.toUri().toURL());
      ```
      (that also serves as an example of _Iterable_ extensions)

##### Notes
 - The licenses of modified OpenJDK source remain unchanged. They're NOT under
   public domain like the rest of files.
 - Comments are written in Chinese until I find ways to make dual-language
   javadoc.
