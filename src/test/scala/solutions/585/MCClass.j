.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
Label0:
	iconst_3
	iconst_5
	invokestatic MCClass/getPower(II)I
	invokestatic io/putIntLn(I)V
Label1:
	return
.limit stack 2
.limit locals 1
.end method

.method public static getPower(II)I
.var 0 is a I from Label0 to Label1
.var 1 is b I from Label0 to Label1
.var 2 is i I from Label0 to Label1
.var 3 is c I from Label0 to Label1
Label0:
	iconst_1
	dup
	istore_3
	pop
	iconst_0
	dup
	istore_2
	pop
Label4:
	iload_2
	iload_1
	if_icmpge Label5
	iconst_1
	goto Label6
Label5:
	iconst_0
Label6:
	ifeq Label3
Label7:
	iload_3
	iload_0
	imul
	dup
	istore_3
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
	iload_3
Label1:
	ireturn
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
