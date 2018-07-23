.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
.var 1 is i I from Label0 to Label1
Label0:
	iconst_0
	dup
	istore_1
	pop
Label4:
	iload_1
	bipush 12
	if_icmpge Label5
	iconst_1
	goto Label6
Label5:
	iconst_0
Label6:
	ifeq Label3
Label7:
	iload_1
	bipush 8
	if_icmple Label9
	iconst_1
	goto Label10
Label9:
	iconst_0
Label10:
	ifeq Label11
	goto Label3
	goto Label12
Label11:
	iload_1
	iconst_5
	if_icmpge Label13
	iconst_1
	goto Label14
Label13:
	iconst_0
Label14:
	ifeq Label15
	iconst_2
	iload_1
	imul
	iconst_1
	iadd
	dup
	istore_1
	pop
Label15:
Label12:
Label8:
Label2:
	iload_1
	iconst_1
	iadd
	dup
	istore_1
	pop
	goto Label4
Label3:
	iload_1
	invokestatic io/putIntLn(I)V
Label1:
	return
.limit stack 2
.limit locals 2
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
