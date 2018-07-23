.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
.var 1 is a I from Label0 to Label1
.var 2 is i I from Label0 to Label1
Label0:
	iconst_2
	dup
	istore_1
	pop
	iconst_1
	dup
	istore_2
	pop
Label4:
	iload_2
	iconst_4
	if_icmpge Label5
	iconst_1
	goto Label6
Label5:
	iconst_0
Label6:
	ifeq Label3
Label7:
	iload_1
	iconst_2
	imul
	dup
	istore_1
	pop
Label8:
Label2:
	iload_2
	iconst_1
	iadd
	dup
	istore_2
	pop
	goto Label4
Label3:
	iload_1
	invokestatic io/putIntLn(I)V
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
