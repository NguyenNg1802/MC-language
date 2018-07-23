.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
.var 1 is a I from Label0 to Label1
Label0:
.var 2 is b I from Label2 to Label3
.var 3 is a F from Label4 to Label5
Label2:
	bipush 12
	dup
	istore_2
	pop
Label4:
Label6:
	iload_2
	i2f
	dup
	fstore_3
	pop
	fload_3
	invokestatic io/putFloatLn(F)V
Label7:
Label5:
Label3:
Label1:
	return
.limit stack 2
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
