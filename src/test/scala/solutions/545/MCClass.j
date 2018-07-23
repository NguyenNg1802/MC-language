.source MCClass.java
.class public MCClass
.super java.lang.Object
.field static a [Z

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
.var 1 is b I from Label0 to Label1
.var 2 is c I from Label0 to Label1
Label0:
	getstatic MCClass.a [Z
	iconst_1
	iconst_1
	dup_x2
	bastore
	pop
	iconst_4
	dup
	istore_1
	pop
	iconst_2
	iload_1
	imul
	iconst_3
	isub
	dup
	istore_2
	pop
	iload_1
	iconst_2
	if_icmple Label5
	iconst_1
	goto Label6
Label5:
	iconst_0
Label6:
	dup
	ifeq Label4
	iload_2
	bipush 10
	if_icmpge Label7
	iconst_1
	goto Label8
Label7:
	iconst_0
Label8:
	iand
Label4:
	dup
	ifeq Label3
	getstatic MCClass.a [Z
	iconst_0
	iconst_1
	dup_x2
	bastore
	iand
Label3:
	dup
	ifeq Label2
	getstatic MCClass.a [Z
	iconst_1
	baload
	iand
Label2:
	invokestatic io/putBoolLn(Z)V
Label1:
	return
.limit stack 5
.limit locals 3
.end method

.method public static <clinit>()V
Label0:
	iconst_3
	newarray boolean
	putstatic MCClass.a [Z
Label1:
	return
.limit stack 1
.limit locals 0
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
