jxtn.core.axi
-------------

Extending the Java Collection API by adding non-standard default methods such
as *Iterable.filter* and *Iterable.map*. The library provides the most common
used functions directly on existing Java collections, as a simpler and
easier-to-use alternative to Java 8 Stream API. which should be reserved for
more advanced operations (parallelism etc) only.

##### Examples

   ```java
   String joinNumbersToString(Iterable<Double> list) {
       return String.join(", ", list.filter(d -> d != null).map(d -> String.format("%f", d)));
   }
   ```

   ```java
   Point2D getClosestPoint(Point2D origin, Iterable<Point2D> pointList) {
       return pointList.firstOfMinDouble(p -> p.distance(origin));
   }
   ```


   ```java
   void collapseNestedNodesInTree(javafx.scene.control.TreeItem root) {
       IterableExt.linkTree(root, item -> item.getChildren())
               .skip(1) // skip the root itself
               .forEach(item -> item.setExpanded(false));
   }
   ```

##### Functionality
 - String methods: left/right/padLeft/padRight/...
 - Iterable methods: all/any/concat/filter/map/first/groupBy/avg/max/...
 - Iterator methods: *same as Iterable*
 - dom/NodeList methods: asList
 - generic replacements of non-generic method declarations in the collection
   API, such as:
   * Collection.remove2(E): replaces *Collection.remove(Object)*
   * Map.get2(K): replaces *Map.get(Object)*
   * ......
 - Comparators: generic comparators for comparing arrays and by member.
 - Function interfaces: *FunctionEx*, *SupplierEx*, *ConsumerEx* etc, to allow
   throwing of exceptions while remain compatible with built-in interfaces
   (exceptions are optionally wrapped as _RuntimeException_).
 - Tuples: like javatuples but support null values.

##### Runtime License
 - Due to the restriction of 'Oracle Binary Code License Agreement' (
   SUPPLEMENTAL LICENSE TERMS, Section F), you may not able to use this library
   with the official JRE/JDK provided by Oracle. Instead you should use binary
   from OpenJDK - such as [Zulu](http://www.azul.com/downloads/zulu/) or the
   OpenJDK packages on most Linux distributions.

##### Dependencies
 - OpenJDK (as source) for Java version updating: To update the project for
   newer Java versions, simply copy the latest source files from OpenJDK,
   override and modify *interface ...* line of each file to extend extension
   interfaces. For instance:

   ```java
   public interface List<E> extends Collection<E> {}
   ```

   change to:

   ```java
   public interface List<E> extends Collection<E>, ListExt<E> {}
   ```

##### Installation
 1. Build *jxtn.core.axi.jar*
 2. Create the directory _jre/endorsed_ or _jre8/endorsed_
 3. Put *jxtn.core.axi.jar* into _endorsed_
 4. Classes in *jxtn.core.axi.jar* (and all other jar files in _endorsed_) will
    automatically override anything provided in the standard Java class library
    (rt.jar). Note that you should not use the same way to override classes
    under ext or others, because it'd be the bootstrap classloader which loads
    endorsed jars and it cannot see other jars on application classpath.
 5. You may have to configure IDEs for code inspection to work correctly.
    * Eclipse: add jars under _endorsed_ to your JRE system libraries.
    * Intellij: same as above, and edit _config\options\jdk.table.xml_ to
      change the order of JARs (JARs under _endorsed_ should be at top).

##### Compatibility
 - AXI is compatible with Java SE 8u20 but not older versions due to change in
   the default method *Collection.sort* in 8u20 (but not 8). Compatibility
   shall not be an issue afterwards since the collection interfaces have been
   stable for many years.
 - Custom implementations of collection interfaces should not override the new
   generic version of non-generic methods, for the sake of consistency, ex:
   override *Collection.remove(Object)* instead of *Collection.remove2(E)*,
   because *remove2(E)* should always call *remove(Object)* internally.

##### Notes
 - The licenses of modified OpenJDK source remain unchanged. They're NOT under
   public domain like the rest of files.
 - Comments are written in Chinese until I find a way to make dual-language
   javadoc.
 - Primitive methods are named differently depending on their types instead of
   utilizing overloads (ex: *firstOfMin* and *firstOfMinInt*), because
   overloading with function interfaces plus autoboxing tend to break code
   parsing in Java IDEs.
 - No extension to Array classes for now because I don't know how to do it.
 - No test suite available. Some code may be broken since I never test them.
