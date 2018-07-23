.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static raiseTrue()Z
Label0:
	ldc "true"
	invokestatic io/putStringLn(Ljava/lang/String;)V
	iconst_1
Label1:
	ireturn
.limit stack 1
.limit locals 0
.end method

.method public static ifBiggerAndSmallerThen10(II)Z
.var 0 is a I from Label0 to Label1
.var 1 is b I from Label0 to Label1
Label0:
	iload_0
	iload_1
	if_icmple Label4
	iconst_1
	goto Label5
Label4:
	iconst_0
Label5:
	dup
	ifeq Label3
	iload_0
	bipush 10
	if_icmpge Label6
	iconst_1
	goto Label7
Label6:
	iconst_0
Label7:
	iand
Label3:
	dup
	ifeq Label2
	invokestatic MCClass/raiseTrue()Z
	iand
Label2:
Label1:
	ireturn
.limit stack 3
.limit locals 2
.end method

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
Label0:
	bipush 11
	iconst_2
	invokestatic MCClass/ifBiggerAndSmallerThen10(II)Z
	invokestatic io/putBoolLn(Z)V
Label1:
	return
.limit stack 2
.limit locals 1
.end method

.method public <init>()V
.var 0 is this LMCClass; from Label0 to Label1
Label0:
	aload_0
	invokespecial java/lang/Object/<init>()V
Label1:
	return
.limit stack 1
.limit locals 1
.end method
