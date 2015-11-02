jxtn.jfx.makers
---------------

Re-creates the deprecated JavaFX builders, based on FMPP/FreeMarker template
engine. It also provides bindings and you may easily extend the functionality
or use the tool to generate builders for 3rd-party libraries (controlsfx
builders are already included).

##### Dependencies
 - FMPP for re-generation of builder classes: tested on v0.9.14.

##### Additionals
 - Java/OpenJDK/OpenJFX 8u60 or newer (with built-in dialogs support).
 - ControlsFX v8.20.8: It's optional and you could remove the library and
   builder classes under _org/controlsfx/*_ directory.
 
##### Installation
 1. Build *jxtn.jfx.makers*
 2. Drop *jxtn.jfx.makers* into your own project(s).

##### Notes
 - Comments are written in Chinese until I find ways to make dual-language
   javadoc.
 - Builder classes are now Makers (ex: *LabelMaker* instead of *LabelBuilder*).
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
