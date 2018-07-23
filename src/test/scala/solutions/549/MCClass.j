.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
.var 1 is a F from Label0 to Label1
.var 2 is b I from Label0 to Label1
.var 3 is c I from Label0 to Label1
Label0:
	iconst_1
	dup
	istore_3
	dup
	istore_2
	i2f
	dup
	fstore_1
	pop
	iconst_5
	iload_3
	imul
	iconst_2
	isub
	i2f
	dup
	fstore_1
	pop
	iconst_2
	iload_2
	imul
	iconst_1
	iadd
	dup
	istore_3
	pop
	iload_2
	iload_2
	iconst_3
	imul
	iconst_1
	isub
	dup
	istore_2
	if_icmple Label4
	iconst_1
	goto Label5
Label4:
	iconst_0
Label5:
	dup
	ifgt Label3
	fload_1
	iconst_1
	i2f
	fcmpl
	ifle Label6
	iconst_1
	goto Label7
Label6:
	iconst_0
Label7:
	ior
Label3:
	dup
	ifgt Label2
	iconst_1
	dup
	istore_3
	iconst_1
	if_icmpne Label8
	iconst_1
	goto Label9
Label8:
	iconst_0
Label9:
	ior
Label2:
	pop
	fload_1
	invokestatic io/putFloatLn(F)V
	iload_2
	invokestatic io/putIntLn(I)V
	iload_3
	invokestatic io/putIntLn(I)V
Label1:
	return
.limit stack 3
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
