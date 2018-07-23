.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
.var 1 is x I from Label0 to Label1
.var 2 is i I from Label0 to Label1
Label0:
	iconst_0
	dup
	istore_2
	pop
	iconst_1
	dup
	istore_1
	pop
Label4:
	iload_1
	bipush 10
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
	irem
	iconst_0
	if_icmpne Label9
	iconst_1
	goto Label10
Label9:
	iconst_0
Label10:
	ifeq Label11
	goto Label2
Label11:
	iload_2
	iconst_1
	iadd
	dup
	istore_2
	pop
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
	iload_2
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
