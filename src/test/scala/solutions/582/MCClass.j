.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
.var 1 is a F from Label0 to Label1
.var 2 is i F from Label0 to Label1
Label0:
	ldc 2.5
	dup
	fstore_1
	pop
	iconst_1
	i2f
	dup
	fstore_2
	pop
Label4:
	fload_2
	iconst_4
	i2f
	fcmpl
	ifge Label5
	iconst_1
	goto Label6
Label5:
	iconst_0
Label6:
	ifeq Label3
Label7:
	fload_1
	bipush 7
	i2f
	fcmpl
	ifle Label9
	iconst_1
	goto Label10
Label9:
	iconst_0
Label10:
	ifeq Label11
	fload_1
	iconst_2
	i2f
	fmul
	dup
	fstore_1
	pop
	goto Label12
Label11:
	fload_1
	iconst_1
	i2f
	fsub
	dup
	fstore_1
	pop
Label12:
Label8:
Label2:
	fload_2
	iconst_1
	i2f
	fadd
	dup
	fstore_2
	pop
	goto Label4
Label3:
	fload_1
	invokestatic io/putFloatLn(F)V
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
