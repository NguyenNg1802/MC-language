.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static sum(II)I
.var 0 is a I from Label0 to Label1
.var 1 is b I from Label0 to Label1
Label0:
	iload_0
	iload_1
	iadd
Label1:
	ireturn
.limit stack 2
.limit locals 2
.end method

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
.var 1 is a I from Label0 to Label1
.var 2 is b I from Label0 to Label1
Label0:
	iconst_2
	dup
	istore_1
	pop
	iconst_3
	dup
	istore_2
	pop
	iload_1
	iconst_1
	isub
	iload_2
	if_icmpge Label3
	iconst_1
	goto Label4
Label3:
	iconst_0
Label4:
	dup
	ifgt Label2
	iload_1
	iconst_2
	imul
	iload_2
	iconst_1
	iadd
	if_icmple Label5
	iconst_1
	goto Label6
Label5:
	iconst_0
Label6:
	ior
Label2:
	ifeq Label7
	iload_1
	iload_2
	invokestatic MCClass/sum(II)I
	i2f
	invokestatic io/putFloatLn(F)V
	goto Label8
Label7:
	iload_1
	i2f
	iload_2
	i2f
	invokestatic MCClass/mul(FF)F
	invokestatic io/putFloatLn(F)V
Label8:
Label1:
	return
.limit stack 4
.limit locals 3
.end method

.method public static mul(FF)F
.var 0 is a F from Label0 to Label1
.var 1 is b F from Label0 to Label1
Label0:
	fload_0
	fload_1
	fmul
Label1:
	freturn
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
