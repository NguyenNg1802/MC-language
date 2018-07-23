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
	if_icmpge Label4
	iconst_1
	goto Label5
Label4:
	iconst_0
Label5:
	dup
	ifeq Label3
	getstatic MCClass.a [I
	iconst_0
	iconst_1
	dup_x2
	iastore
	iconst_1
	if_icmpne Label6
	iconst_1
	goto Label7
Label6:
	iconst_0
Label7:
	iand
Label3:
	dup
	ifeq Label2
	getstatic MCClass.a [I
	iconst_1
	iaload
	iconst_1
	if_icmple Label8
	iconst_1
	goto Label9
Label8:
	iconst_0
Label9:
	iand
Label2:
	ifeq Label10
	getstatic MCClass.a [I
	iconst_0
	iconst_5
	dup_x2
	iastore
	pop
Label10:
	getstatic MCClass.a [I
	iconst_0
	iaload
	i2f
	invokestatic io/putFloatLn(F)V
Label1:
	return
.limit stack 5
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
