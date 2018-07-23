.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
.var 1 is a I from Label0 to Label1
.var 2 is b I from Label0 to Label1
.var 3 is c F from Label0 to Label1
Label0:
	bipush 13
	dup
	istore_1
	pop
	iconst_3
	dup
	istore_2
	pop
	iload_1
	iload_2
	irem
	iconst_5
	iadd
	iconst_5
	irem
	i2f
	dup
	fstore_3
	pop
	fload_3
	invokestatic io/putFloatLn(F)V
Label1:
	return
.limit stack 2
.limit locals 4
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
