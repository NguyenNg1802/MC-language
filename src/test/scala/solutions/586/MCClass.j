.source MCClass.java
.class public MCClass
.super java.lang.Object
.field static x I

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
Label0:
	iconst_2
	dup
	putstatic MCClass.x I
	pop
Label4:
	iconst_2
	getstatic MCClass.x I
	iconst_1
	isub
	imul
	dup
	putstatic MCClass.x I
	pop
	getstatic MCClass.x I
	iconst_1
	iadd
	dup
	putstatic MCClass.x I
	pop
Label2:
	getstatic MCClass.x I
	bipush 10
	if_icmpge Label5
	iconst_1
	goto Label6
Label5:
	iconst_0
Label6:
	ifgt Label4
Label3:
	getstatic MCClass.x I
	invokestatic io/putIntLn(I)V
Label1:
	return
.limit stack 3
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
