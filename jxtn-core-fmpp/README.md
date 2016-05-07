jxtn.core.fmpp
--------------

Provides following data loaders:

 - **JarReflectionDataLoader:** translate reflection information from JARs to
   XML, to be used for Java-to-Java code generators (such as builder classes).
 - **SqlSchemaDataLoader:** translate SQL schema to XML, to be used for Java
   code generation from database.

##### Dependencies
 - FMPP at runtime: tested on v0.9.14. The built binary of this project is in
   the form of FMPP plugins and they should be dropped into _fmpp/lib_.

##### Installation
 1. Build *jxtn.core.fmpp.jar*
 2. Drop *jxtn.core.fmpp.jar* into _fmpp\lib_

##### Notes
 - Comments are written in Chinese until I find a way to make dual-language
   javadoc.
 - *JarReflectionDataLoader* is made for JavaFX builders. It skips internal
   members, and is not ready for classes made complex use of generics.
 - *SqlSchemaDataLoader* is made for SQL Server though most of schema is
   obtained from *INFORMATION_SCHEMA* which is SQL standard. It may need minor
   modification for other databases.
