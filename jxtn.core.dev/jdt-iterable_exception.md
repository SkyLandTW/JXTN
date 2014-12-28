Iterable with exception in Eclipse JDT
======================================

Supports Iterable/Iterator with exceptions in Eclipse JDT

*ABANDONED, DO NOT USE*
*REQUIRE code from branch _iteratorEx2_ to work*

##### What changes:
 - Tailing generic parameters might be optional and default to
   *java.lang.RuntimeException* if their variable names contain "Exception"
   (case-sensitive).
 - For-Each loop may propergate exceptions, depending on checked exception list
   from *Iterator.hasNext* and *Iterator.next* (probing method signatures).

##### What works:
 - Eclipse JDT compilation
 - Eclipse JDT code assist

##### What doesn't work:
 - Source compatibility

##### Tested environments:
 - Eclipse v4.4 (luna)
   * org.eclipse.jdt.core v3.10.0
   * org.eclipse.jdt.ui v3.10.0

------------------------------------------------------------------------------

Modify *org.eclipse.jdt.core*
-----------------------------

##### org.eclipse.jdt.internal.compiler.ast.ForeachStatement

Add this field:
```java
    private TypeBinding iteratorReceiverType;
    private TypeBinding collectionElementType;
    private TypeBinding collectionIterableType;     // !INSERT!
```

Inside *resolve*, add this line:

```java
    ReferenceBinding iterableType = ......
    if (iterableType == null && isTargetJsr14) {
        iterableType = ......
    }
    this.collectionIterableType = iterableType;     // !INSERT!
    checkIterable: {
        if (iterableType == null) break checkIterable;
```

Inside *resolve*, remove this line:

```java
    if (arguments.length != 1) break checkIterable; // per construction can only be one
```

Inside *analyseCode*, add these lines:

```java
//end of loop
////////////////////////////////////////////////////////////////////////////////////////////// !INSERT!
if (kind == GENERIC_ITERABLE) {                                                             // !INSERT!
    if (collectionIterableType != null) {                                                   // !INSERT!
        // System.out.println("for-each on " + collectionIterableType);                     // !INSERT!
        if (collectionIterableType.isValidBinding()) {                                      // !INSERT!
            System.out.println(" => valid");                                                // !INSERT!
            org.eclipse.jdt.internal.compiler.lookup.MethodBinding iteratorMethod           // !INSERT!
                    = ((ReferenceBinding) collectionIterableType).getExactMethod(           // !INSERT!
                        "iterator".toCharArray(),                                           // !INSERT!
                        new TypeBinding[0],                                                 // !INSERT!
                        this.scope.compilationUnitScope());                                 // !INSERT!
            if (iteratorMethod != null && iteratorMethod.isValidBinding()) {                // !INSERT!
                TypeBinding iteratorType = iteratorMethod.returnType;                       // !INSERT!
                System.out.println(" => iterator " + iteratorType);                         // !INSERT!
                if (iteratorType != null && iteratorType.isValidBinding()) {                // !INSERT!
                    org.eclipse.jdt.internal.compiler.lookup.MethodBinding hasNextMethod    // !INSERT!
                            = ((ReferenceBinding) iteratorType).getExactMethod(             // !INSERT!
                                "hasNext".toCharArray(),                                    // !INSERT!
                                new TypeBinding[0],                                         // !INSERT!
                                this.scope.compilationUnitScope());                         // !INSERT!
                    org.eclipse.jdt.internal.compiler.lookup.MethodBinding nextMethod       // !INSERT!
                            = ((ReferenceBinding) iteratorType).getExactMethod(             // !INSERT!
                                "next".toCharArray(),                                       // !INSERT!
                                new TypeBinding[0],                                         // !INSERT!
                                this.scope.compilationUnitScope());                         // !INSERT!
                    if (hasNextMethod != null && nextMethod != null) {                      // !INSERT!
                        ReferenceBinding[] thrownExceptions = new ReferenceBinding[         // !INSERT!
                                hasNextMethod.thrownExceptions.length                       // !INSERT!
                                + nextMethod.thrownExceptions.length                        // !INSERT!
                        ];                                                                  // !INSERT!
                        if (thrownExceptions.length > 0) {                                  // !INSERT!
                            System.arraycopy(                                               // !INSERT!
                                    hasNextMethod.thrownExceptions, 0,                      // !INSERT!
                                    thrownExceptions, 0,                                    // !INSERT!
                                    hasNextMethod.thrownExceptions.length);                 // !INSERT!
                            System.arraycopy(                                               // !INSERT!
                                    nextMethod.thrownExceptions, 0,                         // !INSERT!
                                    thrownExceptions, hasNextMethod.thrownExceptions.length,// !INSERT!
                                    nextMethod.thrownExceptions.length);                    // !INSERT!
                            flowContext.checkExceptionHandlers(                             // !INSERT!
                                    thrownExceptions,                                       // !INSERT!
                                    this,                                                   // !INSERT!
                                    flowInfo.unconditionalCopy(),                           // !INSERT!
                                    currentScope);                                          // !INSERT!
                        }                                                                   // !INSERT!
                    }                                                                       // !INSERT!
                }                                                                           // !INSERT!
            }                                                                               // !INSERT!
        }                                                                                   // !INSERT!
    }                                                                                       // !INSERT!
}                                                                                           // !INSERT!
////////////////////////////////////////////////////////////////////////////////////////////// !INSERT!
loopingContext.complainOnDeferredNullChecks(currentScope, actionInfo);
```

##### org.eclipse.jdt.internal.compiler.ast.ParameterizedQualifiedTypeReference

Inside *internalResolveLeafType*, add those lines:

```java
TypeReference keep = null;
if (isClassScope) {
    keep = ((ClassScope) scope).superTypeReference;
    ((ClassScope) scope).superTypeReference = null;
}
////////////////////////////////////////////////////////////////////////////////////////////// !INSERT!
if (args.length > 0 /* TODO: handles empty args */) {                                       // !INSERT!
    TypeVariableBinding[] typeVariables = this.resolvedType.original().typeVariables();     // !INSERT!
    if (typeVariables != Binding.NO_TYPE_VARIABLES && args.length < typeVariables.length) { // !INSERT!
        if (java.util.Arrays.asList(typeVariables)                                          // !INSERT!
                .subList(args.length, typeVariables.length)                                 // !INSERT!
                .stream()                                                                   // !INSERT!
                .allMatch(tv -> new String(tv.sourceName).contains("Exception"))) {         // !INSERT!
            TypeReference[] newArgs = new TypeReference[typeVariables.length];              // !INSERT!
            System.arraycopy(args, 0, newArgs, 0, args.length);                             // !INSERT!
            for (int aidx = args.length; aidx < typeVariables.length; aidx++) {             // !INSERT!
                TypeReference defaultArg = new SingleTypeReference(                         // !INSERT!
                    "RuntimeException".toCharArray(), 0);                                   // !INSERT!
                defaultArg.sourceStart = args[args.length - 1].sourceStart;                 // !INSERT!
                defaultArg.sourceEnd = args[args.length - 1].sourceEnd;                     // !INSERT!
                newArgs[aidx] = defaultArg;                                                 // !INSERT!
            }                                                                               // !INSERT!
            this.typeArguments[i] = args = newArgs;                                         // !INSERT!
        }                                                                                   // !INSERT!
    }                                                                                       // !INSERT!
}                                                                                           // !INSERT!
////////////////////////////////////////////////////////////////////////////////////////////// !INSERT!
int argLength = args.length;
boolean isDiamond = argLength == 0 && (i == (max -1)) && ((this.bits & ASTNode.IsDiamond) != 0);
TypeBinding[] argTypes = new TypeBinding[argLength];
```

##### org.eclipse.jdt.internal.compiler.ast.ParameterizedSingleTypeReference

Inside *internalResolveLeafType*, add those lines:

```java
TypeReference keep = null;
if (isClassScope) {
    keep = ((ClassScope) scope).superTypeReference;
    ((ClassScope) scope).superTypeReference = null;
}
////////////////////////////////////////////////////////////////////////////////////////////// !INSERT!
if (this.typeArguments.length > 0 /* TODO: handles empty args */) {                         // !INSERT!
    TypeReference[] args = this.typeArguments;                                              // !INSERT!
    TypeVariableBinding[] typeVariables = this.resolvedType.original().typeVariables();     // !INSERT!
    if (typeVariables != Binding.NO_TYPE_VARIABLES && args.length < typeVariables.length) { // !INSERT!
        if (java.util.Arrays.asList(typeVariables)                                          // !INSERT!
                .subList(args.length, typeVariables.length)                                 // !INSERT!
                .stream()                                                                   // !INSERT!
                .allMatch(tv -> new String(tv.sourceName).contains("Exception"))) {         // !INSERT!
            TypeReference[] newArgs = new TypeReference[typeVariables.length];              // !INSERT!
            System.arraycopy(args, 0, newArgs, 0, args.length);                             // !INSERT!
            for (int aidx = args.length; aidx < typeVariables.length; aidx++) {             // !INSERT!
                TypeReference defaultArg = new SingleTypeReference(                         // !INSERT!
                    "RuntimeException".toCharArray(), 0);                                   // !INSERT!
                defaultArg.sourceStart = args[args.length - 1].sourceStart;                 // !INSERT!
                defaultArg.sourceEnd = args[args.length - 1].sourceEnd;                     // !INSERT!
                newArgs[aidx] = defaultArg;                                                 // !INSERT!
            }                                                                               // !INSERT!
            this.typeArguments = newArgs;                                                   // !INSERT!
        }                                                                                   // !INSERT!
    }                                                                                       // !INSERT!
}                                                                                           // !INSERT!
////////////////////////////////////////////////////////////////////////////////////////////// !INSERT!
final boolean isDiamond = (this.bits & ASTNode.IsDiamond) != 0;
int argLength = this.typeArguments.length;
TypeBinding[] argTypes = new TypeBinding[argLength];
```

##### org.eclipse.jdt.internal.compiler.lookup.ParameterizedTypeBinding

Inside *substitute*, add those lines:

```java
if (currentType.arguments != null) {
     if (currentType.arguments.length == 0) { // diamond type
            return originalVariable;
     }
     ///////////////////////////////////////////////////////////////////////// !INSERT!
     if (currentType.arguments.length <= originalVariable.rank) {           // !INSERT!
            return originalVariable;                                        // !INSERT!
     }                                                                      // !INSERT!
     ///////////////////////////////////////////////////////////////////////// !INSERT!
     TypeBinding substitute = currentType.arguments[originalVariable.rank];
     return originalVariable.hasTypeAnnotations() ? this.environment.createAnnotatedType(substitute, originalVariable.getTypeAnnotations()) : substitute;
}   
```
