.source MCClass.java
.class public MCClass
.super java.lang.Object
.field static a [I

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
Label0:
	getstatic MCClass.a [I
	iconst_0
	iaload
	iconst_3
	if_icmpge Label3
	iconst_1
	goto Label4
Label3:
	iconst_0
Label4:
	dup
	ifeq Label2
	getstatic MCClass.a [I
	iconst_1
	iaload
	iconst_0
	if_icmpne Label5
	iconst_1
	goto Label6
Label5:
	iconst_0
Label6:
	iand
Label2:
	ifeq Label7
	getstatic MCClass.a [I
	iconst_1
	iconst_2
	dup_x2
	iastore
	pop
Label7:
	getstatic MCClass.a [I
	iconst_1
	iaload
	invokestatic io/putIntLn(I)V
Label1:
	return
.limit stack 4
.limit locals 1
.end method

.method public static <clinit>()V
Label0:
	iconst_2
	newarray int
	putstatic MCClass.a [I
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
