.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static ifBigger(II)Z
.var 0 is a I from Label0 to Label1
.var 1 is b I from Label0 to Label1
Label0:
	iload_0
	iload_1
	if_icmple Label2
	iconst_1
	goto Label3
Label2:
	iconst_0
Label3:
	ifeq Label4
	iconst_1
	ireturn
Label4:
	iconst_0
Label1:
	ireturn
.limit stack 2
.limit locals 2
.end method

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
.var 1 is i I from Label0 to Label1
.var 2 is a I from Label0 to Label1
.var 3 is b I from Label0 to Label1
Label0:
	iconst_3
	dup
	istore_2
	pop
	iconst_5
	dup
	istore_3
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
	iload_2
	iload_3
	invokestatic MCClass/ifBigger(II)Z
	ifeq Label9
	iconst_2
	iload_3
	imul
	iconst_1
	iadd
	dup
	istore_3
	pop
	goto Label10
Label9:
	iload_2
	iconst_2
	irem
	iconst_0
	if_icmpne Label11
	iconst_1
	goto Label12
Label11:
	iconst_0
Label12:
	ifeq Label13
	goto Label2
	goto Label14
Label13:
	iload_2
	iconst_1
	iadd
	dup
	istore_2
	pop
Label14:
Label10:
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
	iload_3
	invokestatic io/putIntLn(I)V
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
