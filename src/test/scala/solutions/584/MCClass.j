.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
.var 1 is b I from Label0 to Label1
.var 2 is i I from Label0 to Label1
Label0:
Label2:
	iconst_0
	dup
	istore_1
	pop
Label6:
	iload_1
	bipush 6
	if_icmpge Label7
	iconst_1
	goto Label8
Label7:
	iconst_0
Label8:
	ifeq Label5
Label9:
	iconst_2
	iload_1
	imul
	iconst_1
	iadd
	dup
	istore_2
	pop
	iload_2
	invokestatic io/putIntLn(I)V
Label10:
Label4:
	iload_1
	iconst_1
	iadd
	dup
	istore_1
	pop
	goto Label6
Label5:
Label3:
Label1:
	return
.limit stack 2
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
