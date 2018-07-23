.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
.var 1 is a [I from Label0 to Label1
Label0:
	iconst_2
	newarray int
	astore_1
	aload_1
	iconst_0
	iconst_4
	dup_x2
	iastore
	pop
	aload_1
	iconst_1
	aload_1
	iconst_0
	iaload
	dup_x2
	iastore
	pop
	aload_1
	iconst_0
	iaload
	iconst_1
	ineg
	if_icmple Label2
	iconst_1
	goto Label3
Label2:
	iconst_0
Label3:
	ifeq Label4
Label6:
	aload_1
	iconst_0
	iaload
	iconst_1
	if_icmple Label8
	iconst_1
	goto Label9
Label8:
	iconst_0
Label9:
	ifeq Label10
	aload_1
	iconst_0
	iaload
	iconst_2
	irem
	iconst_0
	if_icmpne Label12
	iconst_1
	goto Label13
Label12:
	iconst_0
Label13:
	ifeq Label14
	aload_1
	iconst_1
	iconst_3
	dup_x2
	iastore
	pop
Label14:
Label10:
Label7:
Label4:
	aload_1
	iconst_1
	iaload
	invokestatic io/putIntLn(I)V
Label1:
	return
.limit stack 4
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
