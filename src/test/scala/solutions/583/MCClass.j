.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
.var 1 is a F from Label0 to Label1
.var 2 is i F from Label0 to Label1
.var 3 is j F from Label0 to Label1
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
	iconst_3
	i2f
	dup
	fstore_3
	pop
Label11:
	fload_3
	iconst_1
	i2f
	fcmpl
	ifle Label12
	iconst_1
	goto Label13
Label12:
	iconst_0
Label13:
	ifeq Label10
Label14:
	fload_1
	iconst_4
	iconst_2
	idiv
	i2f
	fadd
	dup
	fstore_1
	pop
	iconst_2
	i2f
	fload_1
	fmul
	dup
	fstore_1
	pop
Label15:
Label9:
	fload_3
	iconst_1
	i2f
	fsub
	dup
	fstore_3
	pop
	goto Label11
Label10:
	fload_1
	iconst_1
	i2f
	fsub
	dup
	fstore_1
	pop
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
