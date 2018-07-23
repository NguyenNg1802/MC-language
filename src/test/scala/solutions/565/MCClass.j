.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static printNum(I)V
.var 0 is a I from Label0 to Label1
Label0:
	iload_0
	iconst_0
	if_icmple Label2
	iconst_1
	goto Label3
Label2:
	iconst_0
Label3:
	ifeq Label4
Label6:
	iload_0
	invokestatic io/putIntLn(I)V
	iload_0
	iconst_1
	isub
	dup
	istore_0
	pop
	iload_0
	invokestatic MCClass/printNum(I)V
Label7:
Label4:
Label1:
	return
.limit stack 2
.limit locals 1
.end method

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
Label0:
	bipush 7
	invokestatic MCClass/printNum(I)V
Label1:
	return
.limit stack 1
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
