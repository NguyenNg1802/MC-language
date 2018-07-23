.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
.var 1 is a F from Label0 to Label1
.var 2 is b I from Label0 to Label1
Label0:
	iconst_2
	iconst_1
	isub
	dup
	istore_2
	i2f
	dup
	fstore_1
	pop
	fload_1
	iload_2
	i2f
	ldc 1.2
	fadd
	fcmpl
	ifge Label3
	iconst_1
	goto Label4
Label3:
	iconst_0
Label4:
	dup
	ifeq Label2
	fload_1
	iconst_3
	i2f
	fadd
	iload_2
	i2f
	fcmpl
	ifle Label5
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
