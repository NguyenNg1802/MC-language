.source MCClass.java
.class public MCClass
.super java.lang.Object
.field static x I
.field static y I

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
Label0:
	iconst_1
	dup
	putstatic MCClass.x I
	pop
	iconst_2
	dup
	putstatic MCClass.y I
	pop
Label4:
	getstatic MCClass.x I
	iconst_3
	imul
	dup
	putstatic MCClass.x I
	pop
Label7:
	getstatic MCClass.y I
	iconst_3
	imul
	iconst_2
	iadd
	dup
	putstatic MCClass.y I
	pop
	getstatic MCClass.x I
	iconst_1
	isub
	dup
	putstatic MCClass.x I
	pop
Label5:
	getstatic MCClass.y I
	bipush 15
	if_icmpge Label8
	iconst_1
	goto Label9
Label8:
	iconst_0
Label9:
	ifgt Label7
Label6:
Label2:
	getstatic MCClass.x I
	bipush 14
	if_icmpge Label10
	iconst_1
	goto Label11
Label10:
	iconst_0
Label11:
	ifgt Label4
Label3:
	getstatic MCClass.x I
	invokestatic io/putIntLn(I)V
	getstatic MCClass.y I
	invokestatic io/putIntLn(I)V
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
