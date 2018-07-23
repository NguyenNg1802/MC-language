.source MCClass.java
.class public MCClass
.super java.lang.Object
.field static arr [Ljava/lang/String;

.method public static setArr([Ljava/lang/String;)[Ljava/lang/String;
.var 0 is a [Ljava/lang/String; from Label0 to Label1
Label0:
	aload_0
	iconst_0
	ldc "b"
	dup_x2
	aastore
	pop
	aload_0
	iconst_1
	ldc "c"
	dup_x2
	aastore
	pop
	aload_0
Label1:
	areturn
.limit stack 4
.limit locals 1
.end method

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
Label0:
	getstatic MCClass.arr [Ljava/lang/String;
	iconst_0
	ldc "a"
	dup_x2
	aastore
	pop
	getstatic MCClass.arr [Ljava/lang/String;
	invokestatic MCClass/setArr([Ljava/lang/String;)[Ljava/lang/String;
	iconst_0
	aaload
	invokestatic io/putStringLn(Ljava/lang/String;)V
Label1:
	return
.limit stack 4
.limit locals 1
.end method

.method public static <clinit>()V
Label0:
	iconst_2
	anewarray java/lang/String
	putstatic MCClass.arr [Ljava/lang/String;
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
