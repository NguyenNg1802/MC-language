.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
.var 1 is a I from Label0 to Label1
.var 2 is b I from Label0 to Label1
Label0:
	iconst_2
	dup
	istore_2
	pop
	iconst_2
	iload_2
	imul
	iconst_1
	isub
	dup
	istore_1
	pop
	iload_1
	iload_2
	if_icmpge Label3
	iconst_1
	goto Label4
Label3:
	iconst_0
Label4:
	dup
	ifeq Label2
	iload_1
	iconst_2
	iload_2
	imul
	iadd
	iconst_2
	if_icmplt Label5
	iconst_1
	goto Label6
Label5:
	iconst_0
Label6:
	iand
Label2:
	ifeq Label7
Label9:
	iload_2
	dup
	istore_1
	pop
	iconst_2
	iload_1
	imul
	iconst_1
	iadd
	dup
	istore_2
	pop
Label10:
	goto Label8
Label7:
Label11:
	iconst_2
	dup
	istore_1
	pop
	iconst_3
	dup
	istore_2
	iconst_1
	iadd
	dup
	istore_2
	pop
Label12:
Label8:
	iload_1
	invokestatic io/putIntLn(I)V
	iload_2
	invokestatic io/putIntLn(I)V
Label1:
	return
.limit stack 4
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
