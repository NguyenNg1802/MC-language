.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
.var 1 is a [I from Label0 to Label1
.var 2 is b I from Label0 to Label1
Label0:
	iconst_2
	newarray int
	astore_1
	aload_1
	iconst_0
	iconst_2
	dup
	istore_2
	dup_x2
	iastore
	pop
	iconst_3
	aload_1
	iconst_0
	iaload
	imul
	iconst_3
	isub
	dup
	istore_2
	pop
	aload_1
	iconst_0
	iaload
	iload_2
	if_icmpge Label2
	iconst_1
	goto Label3
Label2:
	iconst_0
Label3:
	invokestatic io/putBoolLn(Z)V
Label1:
	return
.limit stack 4
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
