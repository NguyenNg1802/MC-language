.source MCClass.java
.class public MCClass
.super java.lang.Object
.field static a [Z

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
.var 1 is b F from Label0 to Label1
Label0:
Label2:
	getstatic MCClass.a [Z
	iconst_0
	baload
	ifgt Label4
	iconst_1
	goto Label5
Label4:
	iconst_0
Label5:
	ifeq Label6
Label8:
	iconst_1
	i2f
	ldc 2.5
	fadd
	dup
	fstore_1
	pop
	fload_1
	invokestatic io/putFloatLn(F)V
Label9:
Label6:
Label3:
Label1:
	return
.limit stack 3
.limit locals 2
.end method

.method public static <clinit>()V
Label0:
	iconst_1
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
