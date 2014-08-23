JXTN
====

Java 8 API Extensions and Utilities



Author:  AqD
License: Public Domain


------------------------------------------------------------------------------

# Projects:
 - jxtn.core.axi: Extensions to Java 8 API, to be put into *endorsed* dir.
 - jxtn.core.fmpp: FMPP/FreeMarker data loaders for code generation.
 - jxtn.jfx.builders: Replacement of deprecated JavaFX builders API.

------------------------------------------------------------------------------

## jxtn.core.axi

Adds non-standard default methods such as *Iterable.filter* and *map* into
standard Java API, in attempt to make Java programming bearable. While it
doesn't break binary compatibility, it's not compatible with Java standard (
which I must regretfully violate because it sucks), being exclusive to Java 8
and backport won't be possible.

#### Dependencies
 - OpenJDK: the standard classes such as *Iterable*. Note those source files
   have different license(s).

#### Installation
  1.Build *jxtn.core.axi.jar*
  2.Create the directory _jre/endorsed_ or _jre8/endorsed_
  3.Put *jxtn.core.axi.jar* into _endorsed_
  4.Classes in *jxtn.core.axi.jar* (or any other jar files in _endorsed_)
    automatically overrides anything provided in standard Java class library.
  5.You may have to configure IDEs for code inspection to work correctly.
    * Eclipse: add jars under _endorsed_ to your JRE system libraries.
    * Intellij: same as above, and edit config\options\jdk.table.xml to change
      the order of JARs (jars under _endorsed_ should be at top).

#### Notes
 - Comments are written in Chinese until I find ways to make dual-language
   javadoc.
 - Primitive methods are named differently instead of using overloads (
   *firstOfMin* and *firstOfMinInt*), because they tend to break Java IDEs.
 - No test suite available. Some code may be broken since I never test them.

------------------------------------------------------------------------------

## jxtn.core.fmpp

Provides following data loaders:
 - **JarReflectionDataLoader:** translate reflection information from JARs to
   XML, to be used for Java-to-Java code generators (such as builder classes).
 - **SqlSchemaDataLoader:** translate SQL schema to XML, to be used for Java
   code generation from database.

#### Dependencies
 - FMPP: tested on v0.9.14.

#### Installation
  1.Build *jxtn.core.fmpp.jar*
  2.Drop *jxtn.core.fmpp.jar* into _fmpp\lib_

#### Notes
 - Comments are written in Chinese until I find ways to make dual-language
   javadoc.
 - *JarReflectionDataLoader* is made for JavaFX builders. It skips internal
   members, and is not ready for classes made complex use of generics.

------------------------------------------------------------------------------

## jxtn.jfx.builders

Re-creates the deprecated JavaFX builders, based on FMPP/FreeMarker template
engine. It also provides bindings and you may easily extend the functionality
or use the tool to generate builders for 3rd-party libraries (controlsfx
builders are already included but untested).

#### Dependencies
 - FMPP: tested on v0.9.14.
 - ControlsFX: tested on v8.0.6.

#### Notes
 - Comments are written in Chinese until I find ways to make dual-language
   javadoc.
 - The re-created builders are nearly identical to official JavaFX builders,
   except the Builder.create() methods are replaced by a single *JFX* to avoid
   naming conflict (somehow Eclipse JDT compiler hates it). Instead of:
   ```java
   TabPaneBuilder.create().tabs(
           TabBuilder.create().text("First Tab").build(),
           TabBuilder.create().text("Second Tab").build()
       ).build();
   ```
   You have to write it as:
   ```java
   JFX.tabPane().tabs(
           JFX.tab().text("First Tab").build(),
           JFX.tab().text("Second Tab").build()
       ).build();
   ```
   You can also add the static *create()* methods back easily by modifying
   _javafx.ftl_, if you don't have to use Eclipse JDT compiler.
 - You may remove ControlsFX builders (everything under _org/controlsfx/*_).

