Iterable with exception in Eclipse JDT
======================================

Supports Iterable/Iterator with exceptions in Eclipse JDT

##### What changes:
 - Tailing generic parameters might be optional and default to
   *java.lang.RuntimeException* if their variable names contain "Exception"
   (case-sensitive).
 - TODO: For-Each loop may propergate exceptions, depending on *Iterable* type
   parameters.

##### What works:
 - Eclipse JDT compilation
 - Eclipse JDT code assist

##### Tested environments:
 - Eclipse v4.4 (luna)
   * org.eclipse.jdt.core v3.10.0
   * org.eclipse.jdt.ui v3.10.0

------------------------------------------------------------------------------

Modify *org.eclipse.jdt.core*
-----------------------------

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
