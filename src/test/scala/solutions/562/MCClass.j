.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static Func([I)I
.var 0 is a [I from Label0 to Label1
Label0:
	aload_0
	iconst_0
	iconst_1
	dup_x2
	iastore
	pop
	aload_0
	iconst_0
	iaload
Label1:
	ireturn
.limit stack 4
.limit locals 1
.end method

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
.var 1 is a [I from Label0 to Label1
Label0:
	iconst_2
	newarray int
	astore_1
	aload_1
	invokestatic MCClass/Func([I)I
	i2f
	invokestatic io/putFloatLn(F)V
Label1:
	return
.limit stack 1
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
