.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
.var 1 is a F from Label0 to Label1
.var 2 is b [I from Label0 to Label1
Label0:
	iconst_3
	newarray int
	astore_2
	aload_2
	iconst_0
	iconst_3
	dup_x2
	iastore
	pop
	aload_2
	iconst_0
	aload_2
	iconst_0
	iaload
	iconst_2
	imul
	dup_x2
	iastore
	i2f
	dup
	fstore_1
	pop
	aload_2
	iconst_0
	aload_2
	iconst_0
	iaload
	iconst_2
	imul
	iconst_1
	isub
	dup_x2
	iastore
	pop
	aload_2
	iconst_0
	iaload
	iconst_2
	if_icmplt Label4
	iconst_1
	goto Label5
Label4:
	iconst_0
Label5:
	dup
	ifeq Label3
	iconst_1
	i2f
	dup
	fstore_1
	aload_2
	iconst_0
	iaload
	iconst_2
	idiv
	i2f
	fcmpl
	ifle Label6
	iconst_1
	goto Label7
Label6:
	iconst_0
Label7:
	iand
Label3:
	dup
	ifeq Label2
	aload_2
	iconst_0
	iconst_4
	dup_x2
	iastore
	iconst_1
	if_icmple Label8
	iconst_1
	goto Label9
Label8:
	iconst_0
Label9:
	iand
Label2:
	pop
	fload_1
	invokestatic io/putFloatLn(F)V
	aload_2
	iconst_0
	iaload
	invokestatic io/putIntLn(I)V
Label1:
	return
.limit stack 5
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
