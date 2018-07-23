.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
.var 1 is x I from Label0 to Label1
Label0:
	iconst_1
	dup
	istore_1
	pop
Label4:
	iload_1
	iconst_1
	iadd
	dup
	istore_1
	pop
	iload_1
	iconst_2
	irem
	iconst_0
	if_icmpne Label6
	iconst_1
	goto Label7
Label6:
	iconst_0
Label7:
	dup
	ifeq Label5
	iload_1
	iconst_4
	if_icmple Label8
	iconst_1
	goto Label9
Label8:
	iconst_0
Label9:
	iand
Label5:
	ifeq Label10
	goto Label3
Label10:
Label2:
	iload_1
	bipush 9
	if_icmpge Label12
	iconst_1
	goto Label13
Label12:
	iconst_0
Label13:
	ifgt Label4
Label3:
	iload_1
	invokestatic io/putIntLn(I)V
Label1:
	return
.limit stack 3
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
