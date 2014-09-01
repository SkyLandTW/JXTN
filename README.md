JXTN
====

Java 8 API Extensions and Utilities



###### License: Public Domain
###### Author:  AqD

#### Projects:
 - **jxtn.core.axi:** Extensions to Java 8 API, to be put into *endorsed* dir.
 - **jxtn.core.dev:** Patches to compilers and IDEs
 - **jxtn.core.fmpp:** FMPP/FreeMarker data loaders for code generation.
 - **jxtn.jfx.builders:** Replacement of deprecated JavaFX builders API.

------------------------------------------------------------------------------

jxtn.core.axi
-------------

Fixes generic Collections API and adds non-standard default methods such as
*Iterable.filter* and *map* into Java API, in attempt to make Java programming
bearable.

##### Functions
 - String: left/right/padLeft/padRight/...
 - Iterable: all/any/concat/filter/map/first/groupBy/avg/max/...
 - Iterator: *same as Iterable*
 - dom/NodeList: asList
 - correct generic functions such as Collection.remove(E) and Map.get(K)

##### Dependencies
 - OpenJDK (as source): the standard classes such as *Iterable*. Note those
   source files have different license(s).

##### Installation
 1. Build *jxtn.core.axi.jar*
 2. Create the directory _jre/endorsed_ or _jre8/endorsed_
 3. Put *jxtn.core.axi.jar* into _endorsed_
 4. Classes in *jxtn.core.axi.jar* (or any other jar files in _endorsed_)
    automatically override anything provided in standard Java class library.
 5. You may have to configure IDEs for code inspection to work correctly.
    * Eclipse: add jars under _endorsed_ to your JRE system libraries.
    * Intellij: same as above, and edit _config\options\jdk.table.xml_ to
      change the order of JARs (JARs under _endorsed_ should be at top).

##### Notes
 - Comments are written in Chinese until I find ways to make dual-language
   javadoc.
 - Primitive methods are named differently instead of using overloads (
   *firstOfMin* and *firstOfMinInt*), because they tend to break Java IDEs.
 - No extension to Array classes for now because I don't know how to do it.
 - No test suite available. Some code may be broken since I never test them.
 - AXI should only be used with the latest released version of Oracle JDK/JRE.

------------------------------------------------------------------------------

jxtn.core.dev
-------------
Provides patches to compilers and IDEs for bugfixes and new features.

##### Patches:
 - [Eclipse JDT; Fix resolve error triggered by *jxtn.core.axi*](jxtn.core.dev/fix_inner_type-eclipse.md)
 - [Eclipse JDT; Multi-line Strings in Java](jxtn.core.dev/multiline_string-eclipse.md)

------------------------------------------------------------------------------

jxtn.core.fmpp
-------------

Provides following data loaders:

 - **JarReflectionDataLoader:** translate reflection information from JARs to
   XML, to be used for Java-to-Java code generators (such as builder classes).
 - **SqlSchemaDataLoader:** translate SQL schema to XML, to be used for Java
   code generation from database.

##### Dependencies
 - FMPP: tested on v0.9.14.

##### Installation
 1. Build *jxtn.core.fmpp.jar*
 2. Drop *jxtn.core.fmpp.jar* into _fmpp\lib_

##### Notes
 - Comments are written in Chinese until I find ways to make dual-language
   javadoc.
 - *JarReflectionDataLoader* is made for JavaFX builders. It skips internal
   members, and is not ready for classes made complex use of generics.

------------------------------------------------------------------------------

jxtn.jfx.builders
-------------

Re-creates the deprecated JavaFX builders, based on FMPP/FreeMarker template
engine. It also provides bindings and you may easily extend the functionality
or use the tool to generate builders for 3rd-party libraries (controlsfx
builders are already included but untested).

##### Dependencies
 - FMPP: tested on v0.9.14.
 - ControlsFX: tested on v8.0.6.

##### Notes
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
 - Selection model builders are omitted because Oracle uses weird class
   hierarchies - there are internal classes in-between public classes...
 - You may remove ControlsFX builders (everything under _org/controlsfx/*_).

