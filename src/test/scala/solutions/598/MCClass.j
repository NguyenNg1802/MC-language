.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static ifBiggerAndDivBy2(II)Z
.var 0 is a I from Label0 to Label1
.var 1 is b I from Label0 to Label1
Label0:
	iload_0
	iload_1
	if_icmple Label3
	iconst_1
	goto Label4
Label3:
	iconst_0
Label4:
	dup
	ifeq Label2
	iload_0
	iconst_2
	irem
	iconst_0
	if_icmpne Label5
	iconst_1
	goto Label6
Label5:
	iconst_0
Label6:
	iand
Label2:
Label1:
	ireturn
.limit stack 3
.limit locals 2
.end method

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
Label0:
	iconst_5
	iconst_2
	invokestatic MCClass/ifBiggerAndDivBy2(II)Z
	invokestatic io/putBoolLn(Z)V
Label1:
	return
.limit stack 2
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
