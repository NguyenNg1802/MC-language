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
	dup
	istore_2
	pop
	iconst_2
	iconst_3
	imul
	iload_1
	isub
	dup
	istore_2
	dup
	istore_1
	pop
	iload_1
	iconst_1
	iadd
	iconst_2
	if_icmple Label3
	iconst_1
	goto Label4
Label3:
	iconst_0
Label4:
	dup
	ifgt Label2
	iload_2
	iconst_1
	isub
	iconst_3
	if_icmpge Label5
	iconst_1
	goto Label6
Label5:
	iconst_0
Label6:
	ior
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
