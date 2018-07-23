.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
.var 1 is a I from Label0 to Label1
.var 2 is b I from Label0 to Label1
Label0:
	iconst_1
	dup
	istore_1
	pop
	iconst_3
	iload_1
	imul
	iconst_1
	isub
	dup
	istore_2
	pop
	iload_2
	iconst_1
	if_icmpeq Label3
	iconst_1
	goto Label4
Label3:
	iconst_0
Label4:
	dup
	ifeq Label2
	iload_2
	iconst_3
	if_icmpeq Label5
	iconst_1
	goto Label6
Label5:
	iconst_0
Label6:
	iand
Label2:
	invokestatic io/putBoolLn(Z)V
Label1:
	return
.limit stack 3
.limit locals 3
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
