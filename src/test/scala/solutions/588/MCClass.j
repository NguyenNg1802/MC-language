.source MCClass.java
.class public MCClass
.super java.lang.Object
.field static x I

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
Label0:
	sipush 2314
	dup
	putstatic MCClass.x I
	pop
Label2:
Label6:
	getstatic MCClass.x I
	iconst_2
	idiv
	iconst_1
	isub
	dup
	putstatic MCClass.x I
	pop
	getstatic MCClass.x I
	invokestatic io/putIntLn(I)V
Label4:
	getstatic MCClass.x I
	bipush 100
	if_icmple Label7
	iconst_1
	goto Label8
Label7:
	iconst_0
Label8:
	ifgt Label6
Label5:
Label3:
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
