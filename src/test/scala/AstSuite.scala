import org.scalatest.FunSuite
import mc.utils._


class AstSuite extends FunSuite with TestAst {
  test("a simple program with void as return type of main") {
    val input = """void main () {}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(), List()))))
    assert(checkAst(input, expected, 201))
  }
  test("another simple program with int as return type of main") {
    val input = """int main () {}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), IntType, Block(List(), List()))))
    assert(checkAst(input, expected, 202))
  }

  test("another simple program with bool as return type of main") {
    val input = """boolean main () {}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), BoolType, Block(List(), List()))))
    assert(checkAst(input, expected, 203))
  }

  test("a simple program has a simple call putIntLn") {
    val input = "void main () {putIntLn(5);}"
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(), List(CallExpr(Id("putIntLn"), List(IntLiteral(5))))))))
    assert(checkAst(input, expected, 204))
  }

  test("a global variable declaration with int type") {
    val input = """int a;"""
    val expected = Program(List(VarDecl(Id("a"), IntType)))
    assert(checkAst(input, expected, 205))
  }

  test("a global variable declaration with boolean type") {
    val input = """boolean a;"""
    val expected = Program(List(VarDecl(Id("a"), BoolType)))
    assert(checkAst(input, expected, 206))
  }

  test("a list of global variable declarations with int type") {
    val input = """int a,b;"""
    val expected = Program(List(VarDecl(Id("a"), IntType), VarDecl(Id("b"), IntType)))
    assert(checkAst(input, expected, 207))
  }

  test("a list of global variable declarations with int and boolean type") {
    val input = """int a,b;boolean c;"""
    val expected = Program(List(VarDecl(Id("a"), IntType), VarDecl(Id("b"), IntType), VarDecl(Id("c"), BoolType)))
    assert(checkAst(input, expected, 208))
  }

  test("a list of global variable declaratiosn with boolean and string type") {
    val input = """boolean a,b;string c;"""
    val expected = Program(List(VarDecl(Id("a"), BoolType), VarDecl(Id("b"), BoolType), VarDecl(Id("c"), StringType)))
    assert(checkAst(input, expected, 209))
  }

  test("a global variable declaration with int array type") {
    val input = """int a[3];"""
    val expected = Program(List(VarDecl(Id("a"), ArrayType(IntLiteral(3), IntType))))
    assert(checkAst(input, expected, 210))
  }

  test("a list of global variable declarations with int array type and int type") {
    val input = """int a[3],b;"""
    val expected = Program(List(VarDecl(Id("a"), ArrayType(IntLiteral(3), IntType)), VarDecl(Id("b"), IntType)))
    assert(checkAst(input, expected, 211))
  }

  test("a no-paralist function declaration with no return type") {
    val input = """void Func(){return;}"""
    val expected = Program(List(FuncDecl(Id("Func"), List(), VoidType, Block(List(), List(Return(None))))))
    assert(checkAst(input, expected, 212))
  }

  test("a no-paralist function declaration with int as return type") {
    val input = """int Func(){return 1;}"""
    val expected = Program(List(FuncDecl(Id("Func"), List(), IntType, Block(List(), List(Return(Some(IntLiteral(1))))))))
    assert(checkAst(input, expected, 213))
  }

  test("a no-paralist function declaration with bool as a return type") {
    val input = """boolean Func(){return true;}"""
    val expected = Program(List(FuncDecl(Id("Func"), List(), BoolType, Block(List(), List(Return(Some(BooleanLiteral(true))))))))
    assert(checkAst(input, expected, 214))
  }

  test("a no-paralist function declaration with int array pointer as a return type") {
    val input = """int[] Func(){return a;}"""
    val expected = Program(List(FuncDecl(Id("Func"), List(), ArrayPointerType(IntType), Block(List(), List(Return(Some(Id("a"))))))))
    assert(checkAst(input, expected, 215))
  }

  test("a function declaration with int type parameter") {
    val input ="""int func(int a){return 1;}"""
    val expected = Program(List(FuncDecl(Id("func"), List(VarDecl(Id("a"), IntType)), IntType, Block(List(), List(Return(Some(IntLiteral(1))))))))
    assert(checkAst(input, expected, 216))
  }

  test("a function declaration with string type parameter") {
    val input ="""int func(string a){return 1;}"""
    val expected = Program(List(FuncDecl(Id("func"), List(VarDecl(Id("a"), StringType)), IntType, Block(List(), List(Return(Some(IntLiteral(1))))))))
    assert(checkAst(input, expected, 217))
  }

  test("a function declaration with int array pointer type parameter") {
    val input ="""int func(int a[]){return 1;}"""
    val expected = Program(List(FuncDecl(Id("func"), List(VarDecl(Id("a"), ArrayPointerType(IntType))), IntType, Block(List(), List(Return(Some(IntLiteral(1))))))))
    assert(checkAst(input, expected, 218))
  }

  test("a function declaration with int and boolean type parameters") {
    val input ="""int func(int a,boolean b){return 1;}"""
    val expected = Program(List(FuncDecl(Id("func"), List(VarDecl(Id("a"), IntType), VarDecl(Id("b"), BoolType)), IntType, Block(List(), List(Return(Some(IntLiteral(1))))))))
    assert(checkAst(input, expected, 219))
  }

  test("a function declaration with int array pointer and boolean type parameters") {
    val input ="""int func(int a[],boolean b){return 1;}"""
    val expected = Program(List(FuncDecl(Id("func"), List(VarDecl(Id("a"), ArrayPointerType(IntType)), VarDecl(Id("b"), BoolType)), IntType, Block(List(), List(Return(Some(IntLiteral(1))))))))
    assert(checkAst(input, expected, 220))
  }

  test("a local variable declaration with int type") {
    val input ="""void main(){int a;}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("a"), IntType)), List()))))
    assert(checkAst(input, expected, 221))
  }

  test("a list of local variable declarations with bool type") {
    val input ="""void main(){boolean a,b;}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("a"), BoolType), VarDecl(Id("b"), BoolType)), List()))))
    assert(checkAst(input, expected, 222))
  }

  test("a list of local variable declarations with int array type and int type") {
    val input ="""void main(){int a[3],b;}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("a"), ArrayType(IntLiteral(3), IntType)), VarDecl(Id("b"), IntType)), List()))))
    assert(checkAst(input, expected, 223))
  }

  test("a local declarations inside a if scoop") {
    val input ="""void main(){if(true){int x;x=1;}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(), List(If(BooleanLiteral(true), Block(List(VarDecl(Id("x"), IntType)), List(BinaryOp("=", Id("x"), IntLiteral(1)))), None))))))
    assert(checkAst(input, expected, 224))
  }

  test("a simple line comment") {
    val input =
      """void main(){
    //say hello
    }"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(), List()))))
    assert(checkAst(input, expected, 225))
  }

  test("a simple block comment") {
    val input ="""void main(){/*say hello*/}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(), List()))))
    assert(checkAst(input, expected, 226))
  }

  test("a variable initialization for int type") {
    val input ="""void main(){int x;x=1;}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType)), List(BinaryOp("=", Id("x"), IntLiteral(1)))))))
    assert(checkAst(input, expected, 227))
  }

  test("a variable initialization for bool type") {
    val input ="""void main(){boolean x;x=true;}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), BoolType)), List(BinaryOp("=", Id("x"), BooleanLiteral(true)))))))
    assert(checkAst(input, expected, 228))
  }

  test("a variable initialization for string type") {
    val input ="""void main(){string x;x="abc";}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), StringType)), List(BinaryOp("=", Id("x"), StringLiteral("abc")))))))
    assert(checkAst(input, expected, 229))
  }

  test("a more complex variable initialization for string type") {
    val input ="""void main(){string x;x="abc"+"d";}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), StringType)), List(BinaryOp("=", Id("x"), BinaryOp("+", StringLiteral("abc"), StringLiteral("d"))))))))
    assert(checkAst(input, expected, 230))
  }

  test("a variable initialization for array type") {
    val input ="""void main(){int x[3];x[0]=1;x[1]=2;}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), ArrayType(IntLiteral(3), IntType))), List(BinaryOp("=", ArrayCell(Id("x"), IntLiteral(0)), IntLiteral(1)), BinaryOp("=", ArrayCell(Id("x"), IntLiteral(1)), IntLiteral(2)))))))
    assert(checkAst(input, expected, 231))
  }

  test("a more complex variable initialization for array type") {
    val input ="""void main(){int x[3];x[0]=1;x[1]=x[0]+1;}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), ArrayType(IntLiteral(3), IntType))), List(BinaryOp("=", ArrayCell(Id("x"), IntLiteral(0)), IntLiteral(1)), BinaryOp("=", ArrayCell(Id("x"), IntLiteral(1)), BinaryOp("+", ArrayCell(Id("x"), IntLiteral(0)), IntLiteral(1))))))))
    assert(checkAst(input, expected, 232))
  }

  test("A function call without arguments") {
    val input ="""int Func(){return 1;}void main(){Func();}"""
    val expected = Program(List(FuncDecl(Id("Func"), List(), IntType, Block(List(), List(Return(Some(IntLiteral(1)))))), FuncDecl(Id("main"), List(), VoidType, Block(List(), List(CallExpr(Id("Func"), List()))))))
    assert(checkAst(input, expected, 233))
  }

  test("A function call with an int type argument") {
    val input ="""int Func(int x){return x+1;}void main(){Func(1);}"""
    val expected = Program(List(FuncDecl(Id("Func"), List(VarDecl(Id("x"), IntType)), IntType, Block(List(), List(Return(Some(BinaryOp("+", Id("x"), IntLiteral(1))))))), FuncDecl(Id("main"), List(), VoidType, Block(List(), List(CallExpr(Id("Func"), List(IntLiteral(1))))))))
    assert(checkAst(input, expected, 234))
  }

  test("A function call with an bool type argument") {
    val input ="""int Func(boolean x){return 1;}void main(){Func(true);}"""
    val expected = Program(List(FuncDecl(Id("Func"), List(VarDecl(Id("x"), BoolType)), IntType, Block(List(), List(Return(Some(IntLiteral(1)))))), FuncDecl(Id("main"), List(), VoidType, Block(List(), List(CallExpr(Id("Func"), List(BooleanLiteral(true))))))))
    assert(checkAst(input, expected, 235))
  }

  test("A function call with an int array pointer type argument") {
    val input ="""int Func(int x[]){return 1;}void main(){int x[3];Func(x);}"""
    val expected = Program(List(FuncDecl(Id("Func"), List(VarDecl(Id("x"), ArrayPointerType(IntType))), IntType, Block(List(), List(Return(Some(IntLiteral(1)))))), FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), ArrayType(IntLiteral(3), IntType))), List(CallExpr(Id("Func"), List(Id("x"))))))))
    assert(checkAst(input, expected, 236))
  }

  test("A function call with a list of int type arguments") {
    val input ="""int Func(int x,int y){return x+y;}void main(){Func(1,2);}"""
    val expected = Program(List(FuncDecl(Id("Func"), List(VarDecl(Id("x"), IntType), VarDecl(Id("y"), IntType)), IntType, Block(List(), List(Return(Some(BinaryOp("+", Id("x"), Id("y"))))))), FuncDecl(Id("main"), List(), VoidType, Block(List(), List(CallExpr(Id("Func"), List(IntLiteral(1), IntLiteral(2))))))))
    assert(checkAst(input, expected, 237))
  }


  test("A function call with a list of int array pointer and bool type arguments") {
    val input ="""int Func(int x[],boolean y){if(y)return x;}void main(){int a[3];Func(a,true);}"""
    val expected = Program(List(FuncDecl(Id("Func"), List(VarDecl(Id("x"), ArrayPointerType(IntType)), VarDecl(Id("y"), BoolType)), IntType, Block(List(), List(If(Id("y"), Return(Some(Id("x"))), None)))), FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("a"), ArrayType(IntLiteral(3), IntType))), List(CallExpr(Id("Func"), List(Id("a"), BooleanLiteral(true))))))))
    assert(checkAst(input, expected, 238))
  }

  test("More complex function calls with int type argument") {
    val input ="""int Add(int x){return x+1;}int Func(int x){return x*Add(x);}void main(){Func(1);}"""
    val expected = Program(List(FuncDecl(Id("Add"), List(VarDecl(Id("x"), IntType)), IntType, Block(List(), List(Return(Some(BinaryOp("+", Id("x"), IntLiteral(1))))))), FuncDecl(Id("Func"), List(VarDecl(Id("x"), IntType)), IntType, Block(List(), List(Return(Some(BinaryOp("*", Id("x"), CallExpr(Id("Add"), List(Id("x"))))))))), FuncDecl(Id("main"), List(), VoidType, Block(List(), List(CallExpr(Id("Func"), List(IntLiteral(1))))))))
    assert(checkAst(input, expected, 239))
  }

  test("Simple if else without else statement") {
    val input = """void main(){int x;if(x==1){x=x+1;}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType)), List(If(BinaryOp("==", Id("x"), IntLiteral(1)), Block(List(), List(BinaryOp("=", Id("x"), BinaryOp("+", Id("x"), IntLiteral(1))))), None))))))
    assert(checkAst(input, expected, 240))
  }

  test("A complex if else without else statement") {
    val input = """void main(){int x;if(x==1||x==2){x=x+1;}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType)), List(If(BinaryOp("||", BinaryOp("==", Id("x"), IntLiteral(1)), BinaryOp("==", Id("x"), IntLiteral(2))), Block(List(), List(BinaryOp("=", Id("x"), BinaryOp("+", Id("x"), IntLiteral(1))))), None))))))
    assert(checkAst(input, expected, 241))
  }

  test("Another complex if else without else statement") {
    val input = """void main(){int x;if(x<1&&x%2==0){x=x+1;}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType)), List(If(BinaryOp("&&", BinaryOp("<", Id("x"), IntLiteral(1)), BinaryOp("==", BinaryOp("%", Id("x"), IntLiteral(2)), IntLiteral(0))), Block(List(), List(BinaryOp("=", Id("x"), BinaryOp("+", Id("x"), IntLiteral(1))))), None))))))
    assert(checkAst(input, expected, 242))
  }

  test("Simple if else statement") {
    val input = """void main(){int x;if(x==1){x=x+1;}else{x=x-1;}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType)), List(If(BinaryOp("==", Id("x"), IntLiteral(1)), Block(List(), List(BinaryOp("=", Id("x"), BinaryOp("+", Id("x"), IntLiteral(1))))), Some(Block(List(), List(BinaryOp("=", Id("x"), BinaryOp("-", Id("x"), IntLiteral(1))))))))))))
    assert(checkAst(input, expected, 243))
  }

  test("A complex if else statement") {
    val input = """void main(){int x;if(x==1||x==2){x=x+1;}else{x=x-1;}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType)), List(If(BinaryOp("||", BinaryOp("==", Id("x"), IntLiteral(1)), BinaryOp("==", Id("x"), IntLiteral(2))), Block(List(), List(BinaryOp("=", Id("x"), BinaryOp("+", Id("x"), IntLiteral(1))))), Some(Block(List(), List(BinaryOp("=", Id("x"), BinaryOp("-", Id("x"), IntLiteral(1))))))))))))
    assert(checkAst(input, expected, 244))
  }

  test("Another complex if else statement") {
    val input = """void main(){int x;if(x<1&&x%2==0){x=x+1;}else{x=x-1;}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType)), List(If(BinaryOp("&&", BinaryOp("<", Id("x"), IntLiteral(1)), BinaryOp("==", BinaryOp("%", Id("x"), IntLiteral(2)), IntLiteral(0))), Block(List(), List(BinaryOp("=", Id("x"), BinaryOp("+", Id("x"), IntLiteral(1))))), Some(Block(List(), List(BinaryOp("=", Id("x"), BinaryOp("-", Id("x"), IntLiteral(1))))))))))))
    assert(checkAst(input, expected, 245))
  }

  test("A simple do while statement") {
    val input ="""void main(){int x;do{x=x+1;}while(x<5);}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType)), List(Dowhile(List(Block(List(), List(BinaryOp("=", Id("x"), BinaryOp("+", Id("x"), IntLiteral(1)))))), BinaryOp("<", Id("x"), IntLiteral(5))))))))
    assert(checkAst(input, expected, 246))
  }

  test("A complex do while statement") {
    val input ="""void main(){int x;do{x=x+3;}while(x<1||x==3);}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType)), List(Dowhile(List(Block(List(), List(BinaryOp("=", Id("x"), BinaryOp("+", Id("x"), IntLiteral(3)))))), BinaryOp("||", BinaryOp("<", Id("x"), IntLiteral(1)), BinaryOp("==", Id("x"), IntLiteral(3)))))))))
    assert(checkAst(input, expected, 247))
  }

  test("Another complex do while statement") {
    val input ="""void main(){int x;do x=x+3;x=x*2;while(x*2<24);}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType)), List(Dowhile(List(BinaryOp("=", Id("x"), BinaryOp("+", Id("x"), IntLiteral(3))), BinaryOp("=", Id("x"), BinaryOp("*", Id("x"), IntLiteral(2)))), BinaryOp("<", BinaryOp("*", Id("x"), IntLiteral(2)), IntLiteral(24))))))))
    assert(checkAst(input, expected, 248))
  }

  test("A complex do while statement with if else statement") {
    val input ="""void main(){int x;do x=x+2;if(x%2==0){x=x-1;}while(x-4<32);}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType)), List(Dowhile(List(BinaryOp("=", Id("x"), BinaryOp("+", Id("x"), IntLiteral(2))), If(BinaryOp("==", BinaryOp("%", Id("x"), IntLiteral(2)), IntLiteral(0)), Block(List(), List(BinaryOp("=", Id("x"), BinaryOp("-", Id("x"), IntLiteral(1))))), None)), BinaryOp("<", BinaryOp("-", Id("x"), IntLiteral(4)), IntLiteral(32))))))))
    assert(checkAst(input, expected, 249))
  }

  test("Another complex do while statement with if else statement") {
    val input ="""void main(){int x;do{x=x-1;if(x%2==0)x=x*2+1;else x=x+1;}while(x-2<16);}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType)), List(Dowhile(List(Block(List(), List(BinaryOp("=", Id("x"), BinaryOp("-", Id("x"), IntLiteral(1))), If(BinaryOp("==", BinaryOp("%", Id("x"), IntLiteral(2)), IntLiteral(0)), BinaryOp("=", Id("x"), BinaryOp("+", BinaryOp("*", Id("x"), IntLiteral(2)), IntLiteral(1))), Some(BinaryOp("=", Id("x"), BinaryOp("+", Id("x"), IntLiteral(1)))))))), BinaryOp("<", BinaryOp("-", Id("x"), IntLiteral(2)), IntLiteral(16))))))))
    assert(checkAst(input, expected, 250))
  }

  test("A simple for statement") {
    val input = """void main(){int y;for(x=1;x<3;x=x+1){y=y+1;}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("y"), IntType)), List(For(BinaryOp("=", Id("x"), IntLiteral(1)), BinaryOp("<", Id("x"), IntLiteral(3)), BinaryOp("=", Id("x"), BinaryOp("+", Id("x"), IntLiteral(1))), Block(List(), List(BinaryOp("=", Id("y"), BinaryOp("+", Id("y"), IntLiteral(1)))))))))))
    assert(checkAst(input, expected, 251))
  }

  test("A complex for statement with if else statement") {
    val input = """void main(){int y;for(x=1;x<3;x=x*2){if(y>0){y=y+1;}}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("y"), IntType)), List(For(BinaryOp("=", Id("x"), IntLiteral(1)), BinaryOp("<", Id("x"), IntLiteral(3)), BinaryOp("=", Id("x"), BinaryOp("*", Id("x"), IntLiteral(2))), Block(List(), List(If(BinaryOp(">", Id("y"), IntLiteral(0)), Block(List(), List(BinaryOp("=", Id("y"), BinaryOp("+", Id("y"), IntLiteral(1))))), None)))))))))
    assert(checkAst(input, expected, 252))
  }

  test("Another complex for statement with if else statement") {
    val input = """void main(){int y;for(x=0;x<2;x=x*2+1){if(y>0 && y!=3){y=y*2-3;}else{y=y+1;}}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("y"), IntType)), List(For(BinaryOp("=", Id("x"), IntLiteral(0)), BinaryOp("<", Id("x"), IntLiteral(2)), BinaryOp("=", Id("x"), BinaryOp("+", BinaryOp("*", Id("x"), IntLiteral(2)), IntLiteral(1))), Block(List(), List(If(BinaryOp("&&", BinaryOp(">", Id("y"), IntLiteral(0)), BinaryOp("!=", Id("y"), IntLiteral(3))), Block(List(), List(BinaryOp("=", Id("y"), BinaryOp("-", BinaryOp("*", Id("y"), IntLiteral(2)), IntLiteral(3))))), Some(Block(List(), List(BinaryOp("=", Id("y"), BinaryOp("+", Id("y"), IntLiteral(1)))))))))))))))
    assert(checkAst(input, expected, 253))
  }

  test("A complex for statement with do while statement") {
    val input = """void main(){int y;for(x=-2;x<=23;x=x*2+4){do{y=y-1;}while(y>2);y=y*2+1;}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("y"), IntType)), List(For(BinaryOp("=", Id("x"), UnaryOp("-", IntLiteral(2))), BinaryOp("<=", Id("x"), IntLiteral(23)), BinaryOp("=", Id("x"), BinaryOp("+", BinaryOp("*", Id("x"), IntLiteral(2)), IntLiteral(4))), Block(List(), List(Dowhile(List(Block(List(), List(BinaryOp("=", Id("y"), BinaryOp("-", Id("y"), IntLiteral(1)))))), BinaryOp(">", Id("y"), IntLiteral(2))), BinaryOp("=", Id("y"), BinaryOp("+", BinaryOp("*", Id("y"), IntLiteral(2)), IntLiteral(1)))))))))))
    assert(checkAst(input, expected, 254))
  }

  test("Another complex for statement with do while statement") {
    val input = """void main(){int y;for(x=-2;x<=23;x=x*2+4){do{y=y-1;}while(y>2 || y<-5);y=y*3-4;}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("y"), IntType)), List(For(BinaryOp("=", Id("x"), UnaryOp("-", IntLiteral(2))), BinaryOp("<=", Id("x"), IntLiteral(23)), BinaryOp("=", Id("x"), BinaryOp("+", BinaryOp("*", Id("x"), IntLiteral(2)), IntLiteral(4))), Block(List(), List(Dowhile(List(Block(List(), List(BinaryOp("=", Id("y"), BinaryOp("-", Id("y"), IntLiteral(1)))))), BinaryOp("||", BinaryOp(">", Id("y"), IntLiteral(2)), BinaryOp("<", Id("y"), UnaryOp("-", IntLiteral(5))))), BinaryOp("=", Id("y"), BinaryOp("-", BinaryOp("*", Id("y"), IntLiteral(3)), IntLiteral(4)))))))))))
    assert(checkAst(input, expected, 255))
  }

  test("Do while statement inside another do while statement") {
    val input = """void main(){int x,y;do{do{x=x+1;}while(y<100);y=0;}while(x<120);}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType), VarDecl(Id("y"), IntType)), List(Dowhile(List(Block(List(), List(Dowhile(List(Block(List(), List(BinaryOp("=", Id("x"), BinaryOp("+", Id("x"), IntLiteral(1)))))), BinaryOp("<", Id("y"), IntLiteral(100))), BinaryOp("=", Id("y"), IntLiteral(0))))), BinaryOp("<", Id("x"), IntLiteral(120))))))))
    assert(checkAst(input, expected, 256))
  }

  test("Break statement inside a for statement") {
    val input = """void main(){int y;for(x=1;x<3;x=x+1){if(y>3)break;}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("y"), IntType)), List(For(BinaryOp("=", Id("x"), IntLiteral(1)), BinaryOp("<", Id("x"), IntLiteral(3)), BinaryOp("=", Id("x"), BinaryOp("+", Id("x"), IntLiteral(1))), Block(List(), List(If(BinaryOp(">", Id("y"), IntLiteral(3)), Break, None)))))))))
    assert(checkAst(input, expected, 257))
  }

  test("Break statement inside a do while statement") {
    val input ="""void main(){int x;do{if(x==0)break;}while(x<5);}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType)), List(Dowhile(List(Block(List(), List(If(BinaryOp("==", Id("x"), IntLiteral(0)), Break, None)))), BinaryOp("<", Id("x"), IntLiteral(5))))))))
    assert(checkAst(input, expected, 258))
  }

  test("Continue statement inside a for statement") {
    val input = """void main(){int y;for(x=1;x<2;x=6*x-1){if(y>1)continue;}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("y"), IntType)), List(For(BinaryOp("=", Id("x"), IntLiteral(1)), BinaryOp("<", Id("x"), IntLiteral(2)), BinaryOp("=", Id("x"), BinaryOp("-", BinaryOp("*", IntLiteral(6), Id("x")), IntLiteral(1))), Block(List(), List(If(BinaryOp(">", Id("y"), IntLiteral(1)), Continue, None)))))))))
    assert(checkAst(input, expected, 259))
  }

  test("Continue statement inside a do while statement") {
    val input = """void main(){int y;do{x=8*x-21;}while(x<20);}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("y"), IntType)), List(Dowhile(List(Block(List(), List(BinaryOp("=", Id("x"), BinaryOp("-", BinaryOp("*", IntLiteral(8), Id("x")), IntLiteral(21)))))), BinaryOp("<", Id("x"), IntLiteral(20))))))))
    assert(checkAst(input, expected, 260))
  }

  test("Return statement for a function caculating sum of 2 integers") {
    val input = """int Sum(int a,int b){ return a+b;}"""
    val expected = Program(List(FuncDecl(Id("Sum"), List(VarDecl(Id("a"), IntType), VarDecl(Id("b"), IntType)), IntType, Block(List(), List(Return(Some(BinaryOp("+", Id("a"), Id("b")))))))))
    assert(checkAst(input, expected, 261))
  }

  test("Return statement for a function having no return value") {
    val input = """void sayHello(int a){ printf("Hello");return;}"""
    val expected = Program(List(FuncDecl(Id("sayHello"), List(VarDecl(Id("a"), IntType)), VoidType, Block(List(), List(CallExpr(Id("printf"), List(StringLiteral("Hello"))), Return(None))))))
    assert(checkAst(input, expected, 262))
  }

  test("Return statement for a function checking if the input value is even or not") {
    val input = """boolean ifEven(int x){if(x%2==0){return true;}else return false;}"""
    val expected = Program(List(FuncDecl(Id("ifEven"), List(VarDecl(Id("x"), IntType)), BoolType, Block(List(), List(If(BinaryOp("==", BinaryOp("%", Id("x"), IntLiteral(2)), IntLiteral(0)), Block(List(), List(Return(Some(BooleanLiteral(true))))), Some(Return(Some(BooleanLiteral(false))))))))))
    assert(checkAst(input, expected, 263))
  }

  test("Return statement with a mix of expression") {
    val input = """int main(){int x,y,z;return (x*3)-y+2*(z-4);}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), IntType, Block(List(VarDecl(Id("x"), IntType), VarDecl(Id("y"), IntType), VarDecl(Id("z"), IntType)), List(Return(Some(BinaryOp("+", BinaryOp("-", BinaryOp("*", Id("x"), IntLiteral(3)), Id("y")), BinaryOp("*", IntLiteral(2), BinaryOp("-", Id("z"), IntLiteral(4)))))))))))
    assert(checkAst(input, expected, 264))
  }

  test("Return statement for a recursive function") {
    val input ="""int Add(int x){if(x<10)return Add(x);else return x;}"""
    val expected = Program(List(FuncDecl(Id("Add"), List(VarDecl(Id("x"), IntType)), IntType, Block(List(), List(If(BinaryOp("<", Id("x"), IntLiteral(10)), Return(Some(CallExpr(Id("Add"), List(Id("x"))))), Some(Return(Some(Id("x"))))))))))
    assert(checkAst(input, expected, 265))
  }

  test("Binary operation add and sub") {
    val input = """void main(){int x;x=x+3;x=5-x;}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType)), List(BinaryOp("=", Id("x"), BinaryOp("+", Id("x"), IntLiteral(3))), BinaryOp("=", Id("x"), BinaryOp("-", IntLiteral(5), Id("x"))))))))
    assert(checkAst(input, expected, 266))
  }

  test("Binary operation mul and div") {
    val input = """void main(){int x;x=x*3;x=2/x;}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType)), List(BinaryOp("=", Id("x"), BinaryOp("*", Id("x"), IntLiteral(3))), BinaryOp("=", Id("x"), BinaryOp("/", IntLiteral(2), Id("x"))))))))
    assert(checkAst(input, expected, 267))
  }

  test("Binary operation larger or smaller than") {
    val input = """void main(){int x;if(x>1){x=x+1;}else if(x<-5){x=0;}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType)), List(If(BinaryOp(">", Id("x"), IntLiteral(1)), Block(List(), List(BinaryOp("=", Id("x"), BinaryOp("+", Id("x"), IntLiteral(1))))), Some(If(BinaryOp("<", Id("x"), UnaryOp("-", IntLiteral(5))), Block(List(), List(BinaryOp("=", Id("x"), IntLiteral(0)))), None))))))))
    assert(checkAst(input, expected, 268))
  }

  test("Binary operation larger and smaller than or equal") {
    val input = """void main(){int x;if(x>=0){x=x*2;}else if(x<=-4){x=3-x;}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType)), List(If(BinaryOp(">=", Id("x"), IntLiteral(0)), Block(List(), List(BinaryOp("=", Id("x"), BinaryOp("*", Id("x"), IntLiteral(2))))), Some(If(BinaryOp("<=", Id("x"), UnaryOp("-", IntLiteral(4))), Block(List(), List(BinaryOp("=", Id("x"), BinaryOp("-", IntLiteral(3), Id("x"))))), None))))))))
    assert(checkAst(input, expected, 269))
  }

  test("Binary operation equal and not equal") {
    val input = """void main(){int x;if(x==0 || x!=1){x=5;}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType)), List(If(BinaryOp("||", BinaryOp("==", Id("x"), IntLiteral(0)), BinaryOp("!=", Id("x"), IntLiteral(1))), Block(List(), List(BinaryOp("=", Id("x"), IntLiteral(5)))), None))))))
    assert(checkAst(input, expected, 270))
  }

  test("Binary operation and and or") {
    val input = """void main(){int x;if((x>0&&x<5)|| x>10){x=3*x+2;}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType)), List(If(BinaryOp("||", BinaryOp("&&", BinaryOp(">", Id("x"), IntLiteral(0)), BinaryOp("<", Id("x"), IntLiteral(5))), BinaryOp(">", Id("x"), IntLiteral(10))), Block(List(), List(BinaryOp("=", Id("x"), BinaryOp("+", BinaryOp("*", IntLiteral(3), Id("x")), IntLiteral(2))))), None))))))
    assert(checkAst(input, expected, 271))
  }

  test("Unary operation sub and not") {
    val input = """void main(){int x;boolean y;x=-x+2;y=!y;}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType), VarDecl(Id("y"), BoolType)), List(BinaryOp("=", Id("x"), BinaryOp("+", UnaryOp("-", Id("x")), IntLiteral(2))), BinaryOp("=", Id("y"), UnaryOp("!", Id("y"))))))))
    assert(checkAst(input, expected, 272))
  }

  test("Operation association between mul and plus") {
    val input = """void main(){int x;x=x+2*3;x=3*(x+1);}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType)), List(BinaryOp("=", Id("x"), BinaryOp("+", Id("x"), BinaryOp("*", IntLiteral(2), IntLiteral(3)))), BinaryOp("=", Id("x"), BinaryOp("*", IntLiteral(3), BinaryOp("+", Id("x"), IntLiteral(1)))))))))
    assert(checkAst(input, expected, 273))
  }

  test("Operation association between div and plus") {
    val input = """void main(){int x;x=x+2/3+1;x=3/(1+4+x);}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType)), List(BinaryOp("=", Id("x"), BinaryOp("+", BinaryOp("+", Id("x"), BinaryOp("/", IntLiteral(2), IntLiteral(3))), IntLiteral(1))), BinaryOp("=", Id("x"), BinaryOp("/", IntLiteral(3), BinaryOp("+", BinaryOp("+", IntLiteral(1), IntLiteral(4)), Id("x")))))))))
    assert(checkAst(input, expected, 274))
  }

  test("Operation association between mul and sub") {
    val input = """void main(){int x;x=x-4*2;x=2*(12-x);}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType)), List(BinaryOp("=", Id("x"), BinaryOp("-", Id("x"), BinaryOp("*", IntLiteral(4), IntLiteral(2)))), BinaryOp("=", Id("x"), BinaryOp("*", IntLiteral(2), BinaryOp("-", IntLiteral(12), Id("x")))))))))
    assert(checkAst(input, expected, 275))
  }

  test("Operation association between or and equal") {
    val input = """void main(){int x;if(x==3 || x==2){x=x+1;}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType)), List(If(BinaryOp("||", BinaryOp("==", Id("x"), IntLiteral(3)), BinaryOp("==", Id("x"), IntLiteral(2))), Block(List(), List(BinaryOp("=", Id("x"), BinaryOp("+", Id("x"), IntLiteral(1))))), None))))))
    assert(checkAst(input, expected, 276))
  }

  test("A simple mixture between operations and statements") {
    val input = """void main(){int x,y;if(x+3*y-5==2*y)y=-y+4;}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType), VarDecl(Id("y"), IntType)), List(If(BinaryOp("==", BinaryOp("-", BinaryOp("+", Id("x"), BinaryOp("*", IntLiteral(3), Id("y"))), IntLiteral(5)), BinaryOp("*", IntLiteral(2), Id("y"))), BinaryOp("=", Id("y"), BinaryOp("+", UnaryOp("-", Id("y")), IntLiteral(4))), None))))))
    assert(checkAst(input, expected, 277))
  }

  test("Another simple mixture between operations and statements") {
    val input = """void man(){int x,y;if(x==2 || y*2+4==5) x=y*x+1;}"""
    val expected = Program(List(FuncDecl(Id("man"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType), VarDecl(Id("y"), IntType)), List(If(BinaryOp("||", BinaryOp("==", Id("x"), IntLiteral(2)), BinaryOp("==", BinaryOp("+", BinaryOp("*", Id("y"), IntLiteral(2)), IntLiteral(4)), IntLiteral(5))), BinaryOp("=", Id("x"), BinaryOp("+", BinaryOp("*", Id("y"), Id("x")), IntLiteral(1))), None))))))
    assert(checkAst(input, expected, 278))
  }

  test("A complex mixture between operations and statements") {
    val input = """void man(){int x,y,z;if(x>2 || x+3!=5)x=y*z+1;else{z=3-x;}do{z=z+x*2;}while(z<5*x+y-3);}"""
    val expected = Program(List(FuncDecl(Id("man"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType), VarDecl(Id("y"), IntType), VarDecl(Id("z"), IntType)), List(If(BinaryOp("||", BinaryOp(">", Id("x"), IntLiteral(2)), BinaryOp("!=", BinaryOp("+", Id("x"), IntLiteral(3)), IntLiteral(5))), BinaryOp("=", Id("x"), BinaryOp("+", BinaryOp("*", Id("y"), Id("z")), IntLiteral(1))), Some(Block(List(), List(BinaryOp("=", Id("z"), BinaryOp("-", IntLiteral(3), Id("x"))))))), Dowhile(List(Block(List(), List(BinaryOp("=", Id("z"), BinaryOp("+", Id("z"), BinaryOp("*", Id("x"), IntLiteral(2))))))), BinaryOp("<", Id("z"), BinaryOp("-", BinaryOp("+", BinaryOp("*", IntLiteral(5), Id("x")), Id("y")), IntLiteral(3)))))))))
    assert(checkAst(input, expected, 279))
  }

  test("Another complex mixture between operations and statements") {
    val input = """void man(){float x,y,z;for(x=0;x<3*z-y;x=x/3+4){y=2*z+3/4;if(z>y+2 || (y>4 && y%2==0)){break;}}}"""
    val expected = Program(List(FuncDecl(Id("man"), List(), VoidType, Block(List(VarDecl(Id("x"), FloatType), VarDecl(Id("y"), FloatType), VarDecl(Id("z"), FloatType)), List(For(BinaryOp("=", Id("x"), IntLiteral(0)), BinaryOp("<", Id("x"), BinaryOp("-", BinaryOp("*", IntLiteral(3), Id("z")), Id("y"))), BinaryOp("=", Id("x"), BinaryOp("+", BinaryOp("/", Id("x"), IntLiteral(3)), IntLiteral(4))), Block(List(), List(BinaryOp("=", Id("y"), BinaryOp("+", BinaryOp("*", IntLiteral(2), Id("z")), BinaryOp("/", IntLiteral(3), IntLiteral(4)))), If(BinaryOp("||", BinaryOp(">", Id("z"), BinaryOp("+", Id("y"), IntLiteral(2))), BinaryOp("&&", BinaryOp(">", Id("y"), IntLiteral(4)), BinaryOp("==", BinaryOp("%", Id("y"), IntLiteral(2)), IntLiteral(0)))), Block(List(), List(Break)), None)))))))))
    assert(checkAst(input, expected, 280))
  }

  test("Get the largest number in an array") {
    val input = """void main(){int a[3],max;max=0;for(i=0;i<=2;i=i+1){if(a[i]>max)max=a[i];}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("a"), ArrayType(IntLiteral(3), IntType)), VarDecl(Id("max"), IntType)), List(BinaryOp("=", Id("max"), IntLiteral(0)), For(BinaryOp("=", Id("i"), IntLiteral(0)), BinaryOp("<=", Id("i"), IntLiteral(2)), BinaryOp("=", Id("i"), BinaryOp("+", Id("i"), IntLiteral(1))), Block(List(), List(If(BinaryOp(">", ArrayCell(Id("a"), Id("i")), Id("max")), BinaryOp("=", Id("max"), ArrayCell(Id("a"), Id("i"))), None)))))))))
    assert(checkAst(input, expected, 281))
  }

  test("Get the second largest number in an array") {
    val input = """void main(){int a[10],highest,sechighest;highest =0;for(i=0;i<=9;i=i+1){if(a[i]>=highest){sechighest=highest;highest=a[i];}}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("a"), ArrayType(IntLiteral(10), IntType)), VarDecl(Id("highest"), IntType), VarDecl(Id("sechighest"), IntType)), List(BinaryOp("=", Id("highest"), IntLiteral(0)), For(BinaryOp("=", Id("i"), IntLiteral(0)), BinaryOp("<=", Id("i"), IntLiteral(9)), BinaryOp("=", Id("i"), BinaryOp("+", Id("i"), IntLiteral(1))), Block(List(), List(If(BinaryOp(">=", ArrayCell(Id("a"), Id("i")), Id("highest")), Block(List(), List(BinaryOp("=", Id("sechighest"), Id("highest")), BinaryOp("=", Id("highest"), ArrayCell(Id("a"), Id("i"))))), None)))))))))
    assert(checkAst(input, expected, 282))
  }

  test("Get the smallest number in an array") {
    val input = """void main(){int a[3],min;min=0;for(i=0;i<=2;i=i+1){if(a[i]<min)max=a[i];}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("a"), ArrayType(IntLiteral(3), IntType)), VarDecl(Id("min"), IntType)), List(BinaryOp("=", Id("min"), IntLiteral(0)), For(BinaryOp("=", Id("i"), IntLiteral(0)), BinaryOp("<=", Id("i"), IntLiteral(2)), BinaryOp("=", Id("i"), BinaryOp("+", Id("i"), IntLiteral(1))), Block(List(), List(If(BinaryOp("<", ArrayCell(Id("a"), Id("i")), Id("min")), BinaryOp("=", Id("max"), ArrayCell(Id("a"), Id("i"))), None)))))))))
    assert(checkAst(input, expected, 283))
  }

  test("Get the first even number in an array") {
    val input = """void main(){int a[3],evenNum;for(i=0;i<=2;i=i+1){if(a[i]%2==0){evenNum=a[i];break;}}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("a"), ArrayType(IntLiteral(3), IntType)), VarDecl(Id("evenNum"), IntType)), List(For(BinaryOp("=", Id("i"), IntLiteral(0)), BinaryOp("<=", Id("i"), IntLiteral(2)), BinaryOp("=", Id("i"), BinaryOp("+", Id("i"), IntLiteral(1))), Block(List(), List(If(BinaryOp("==", BinaryOp("%", ArrayCell(Id("a"), Id("i")), IntLiteral(2)), IntLiteral(0)), Block(List(), List(BinaryOp("=", Id("evenNum"), ArrayCell(Id("a"), Id("i"))), Break)), None)))))))))
    assert(checkAst(input, expected, 284))
  }

  test("Get the solution x for linear function y=ax+b") {
    val input = """float getX(float y, float a,float b){return (y-b)/a;}void main(){float a,b,y;getX(y,a,b);}"""
    val expected = Program(List(FuncDecl(Id("getX"), List(VarDecl(Id("y"), FloatType), VarDecl(Id("a"), FloatType), VarDecl(Id("b"), FloatType)), FloatType, Block(List(), List(Return(Some(BinaryOp("/", BinaryOp("-", Id("y"), Id("b")), Id("a"))))))), FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("a"), FloatType), VarDecl(Id("b"), FloatType), VarDecl(Id("y"), FloatType)), List(CallExpr(Id("getX"), List(Id("y"), Id("a"), Id("b"))))))))
    assert(checkAst(input, expected, 285))
  }

  test("Get the area of a triangle") {
    val input = """void main(){int s, a, b, c, area;s = (a + b + c) / 2; area = sqrt(s * (s - a) * (s - b) * (s - c));}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("s"), IntType), VarDecl(Id("a"), IntType), VarDecl(Id("b"), IntType), VarDecl(Id("c"), IntType), VarDecl(Id("area"), IntType)), List(BinaryOp("=", Id("s"), BinaryOp("/", BinaryOp("+", BinaryOp("+", Id("a"), Id("b")), Id("c")), IntLiteral(2))), BinaryOp("=", Id("area"), CallExpr(Id("sqrt"), List(BinaryOp("*", BinaryOp("*", BinaryOp("*", Id("s"), BinaryOp("-", Id("s"), Id("a"))), BinaryOp("-", Id("s"), Id("b"))), BinaryOp("-", Id("s"), Id("c")))))))))))
    assert(checkAst(input, expected, 286))
  }

  test("Get the area of a circle") {
    val input = """void main(){float radius, area, PI;area = PI * pow(radius, 2);}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("radius"), FloatType), VarDecl(Id("area"), FloatType), VarDecl(Id("PI"), FloatType)), List(BinaryOp("=", Id("area"), BinaryOp("*", Id("PI"), CallExpr(Id("pow"), List(Id("radius"), IntLiteral(2))))))))))
    //val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(), List()))))
    assert(checkAst(input, expected, 287))
  }

  test("Get the area of a trapezium") {
    val input = """void main(){float a, b, h, area;area = 0.5 * (a + b) * h ;}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("a"), FloatType), VarDecl(Id("b"), FloatType), VarDecl(Id("h"), FloatType), VarDecl(Id("area"), FloatType)), List(BinaryOp("=", Id("area"), BinaryOp("*", BinaryOp("*", FloatLiteral(0.5f), BinaryOp("+", Id("a"), Id("b"))), Id("h"))))))))
    assert(checkAst(input, expected, 288))
  }

  test("Get the perimeter of a triangle") {
    val input = """void main(){float a, b,c,perimeter; perimeter= a+b+c;}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("a"), FloatType), VarDecl(Id("b"), FloatType), VarDecl(Id("c"), FloatType), VarDecl(Id("perimeter"), FloatType)), List(BinaryOp("=", Id("perimeter"), BinaryOp("+", BinaryOp("+", Id("a"), Id("b")), Id("c"))))))))
    assert(checkAst(input, expected, 289))
  }

  test("Get the surface area and volume of a cube") {
    val input = """void main(){float side, surfacearea, volume;surfacearea = 6.0 * side * side;volume = pow(side, 3);}""";
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("side"), FloatType), VarDecl(Id("surfacearea"), FloatType), VarDecl(Id("volume"), FloatType)), List(BinaryOp("=", Id("surfacearea"), BinaryOp("*", BinaryOp("*", FloatLiteral(6.0f), Id("side")), Id("side"))), BinaryOp("=", Id("volume"), CallExpr(Id("pow"), List(Id("side"), IntLiteral(3)))))))))
    assert(checkAst(input, expected, 290))
  }

  test("Get the total sum of 1 to n") {
    val input = """void main(){int n,sum;sum=0;for(i=1;i<=n;i=i+1){sum=sum+i;}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("n"), IntType), VarDecl(Id("sum"), IntType)), List(BinaryOp("=", Id("sum"), IntLiteral(0)), For(BinaryOp("=", Id("i"), IntLiteral(1)), BinaryOp("<=", Id("i"), Id("n")), BinaryOp("=", Id("i"), BinaryOp("+", Id("i"), IntLiteral(1))), Block(List(), List(BinaryOp("=", Id("sum"), BinaryOp("+", Id("sum"), Id("i")))))))))))
    assert(checkAst(input, expected, 291))
  }

  test("Get the total sum of 1 to n*n") {
    val input = """void main(){int n,sum;sum=0;for(i=1;i<=n;i=i+1){sum=sum+i*i;}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("n"), IntType), VarDecl(Id("sum"), IntType)), List(BinaryOp("=", Id("sum"), IntLiteral(0)), For(BinaryOp("=", Id("i"), IntLiteral(1)), BinaryOp("<=", Id("i"), Id("n")), BinaryOp("=", Id("i"), BinaryOp("+", Id("i"), IntLiteral(1))), Block(List(), List(BinaryOp("=", Id("sum"), BinaryOp("+", Id("sum"), BinaryOp("*", Id("i"), Id("i"))))))))))))
    assert(checkAst(input, expected, 292))
  }

  test("Get the total sum of 1 to 1/n") {
    val input = """void main(){int n,sum;sum=0;for(i=1;i<=n;i=i+1){sum=sum+1/i;}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("n"), IntType), VarDecl(Id("sum"), IntType)), List(BinaryOp("=", Id("sum"), IntLiteral(0)), For(BinaryOp("=", Id("i"), IntLiteral(1)), BinaryOp("<=", Id("i"), Id("n")), BinaryOp("=", Id("i"), BinaryOp("+", Id("i"), IntLiteral(1))), Block(List(), List(BinaryOp("=", Id("sum"), BinaryOp("+", Id("sum"), BinaryOp("/", IntLiteral(1), Id("i"))))))))))))
    assert(checkAst(input, expected, 293))
  }

  test("Get all the even number in an array") {
    val input = """void main(){int a[4];int b[4];for(i=0;i<3;i=i+1){if(a[i]%2==0)b[i]=a[i];}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("a"), ArrayType(IntLiteral(4), IntType)), VarDecl(Id("b"), ArrayType(IntLiteral(4), IntType))), List(For(BinaryOp("=", Id("i"), IntLiteral(0)), BinaryOp("<", Id("i"), IntLiteral(3)), BinaryOp("=", Id("i"), BinaryOp("+", Id("i"), IntLiteral(1))), Block(List(), List(If(BinaryOp("==", BinaryOp("%", ArrayCell(Id("a"), Id("i")), IntLiteral(2)), IntLiteral(0)), BinaryOp("=", ArrayCell(Id("b"), Id("i")), ArrayCell(Id("a"), Id("i"))), None)))))))))
    assert(checkAst(input, expected, 294))
  }

  test("Get the mean of three integers") {
    val input = """void main(){int a,b,c;float mean;mean=(a+b+c)/3;}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("a"), IntType), VarDecl(Id("b"), IntType), VarDecl(Id("c"), IntType), VarDecl(Id("mean"), FloatType)), List(BinaryOp("=", Id("mean"), BinaryOp("/", BinaryOp("+", BinaryOp("+", Id("a"), Id("b")), Id("c")), IntLiteral(3))))))))
    assert(checkAst(input, expected, 295))
  }

  test("Get the mean of n integers") {
    val input = """void main(){int i,n,sum,mean;for(i=1;i<=n;i=i+1){sum=sum+i;} sum=sum/n;}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("i"), IntType), VarDecl(Id("n"), IntType), VarDecl(Id("sum"), IntType), VarDecl(Id("mean"), IntType)), List(For(BinaryOp("=", Id("i"), IntLiteral(1)), BinaryOp("<=", Id("i"), Id("n")), BinaryOp("=", Id("i"), BinaryOp("+", Id("i"), IntLiteral(1))), Block(List(), List(BinaryOp("=", Id("sum"), BinaryOp("+", Id("sum"), Id("i")))))), BinaryOp("=", Id("sum"), BinaryOp("/", Id("sum"), Id("n"))))))))
    assert(checkAst(input, expected, 296))
  }

  test("Get the solution of fibonacci function") {
    val input ="""void main(){int n0,n1;int n,sol;n0=0;n1=1;for(i=0;i<=n;i=i+1){if(n==0)sol=0;if(n==1)sol=1;sol=n1+n0;n0=n1;n1=sol;}}"""
    val expected = Program(List(FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("n0"), IntType), VarDecl(Id("n1"), IntType), VarDecl(Id("n"), IntType), VarDecl(Id("sol"), IntType)), List(BinaryOp("=", Id("n0"), IntLiteral(0)), BinaryOp("=", Id("n1"), IntLiteral(1)), For(BinaryOp("=", Id("i"), IntLiteral(0)), BinaryOp("<=", Id("i"), Id("n")), BinaryOp("=", Id("i"), BinaryOp("+", Id("i"), IntLiteral(1))), Block(List(), List(If(BinaryOp("==", Id("n"), IntLiteral(0)), BinaryOp("=", Id("sol"), IntLiteral(0)), None), If(BinaryOp("==", Id("n"), IntLiteral(1)), BinaryOp("=", Id("sol"), IntLiteral(1)), None), BinaryOp("=", Id("sol"), BinaryOp("+", Id("n1"), Id("n0"))), BinaryOp("=", Id("n0"), Id("n1")), BinaryOp("=", Id("n1"), Id("sol"))))))))))
    assert(checkAst(input, expected, 297))
  }

  test("Complex mixture of expressions"){
    val input ="""int func(int x){return x*3/4-24;}void main(){int x,y[19];x=func(x)/3+ y[x-3*1/(6-2)]*y+8/2;}"""
    val expected = Program(List(FuncDecl(Id("func"),List(VarDecl(Id("x"),IntType)),IntType,Block(List(),List(Return(Some(BinaryOp("-",BinaryOp("/",BinaryOp("*",Id("x"),IntLiteral(3)),IntLiteral(4)),IntLiteral(24))))))),FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("x"),IntType),VarDecl(Id("y"),ArrayType(IntLiteral(19),IntType))),List(BinaryOp("=",Id("x"),BinaryOp("+",BinaryOp("+",BinaryOp("/",CallExpr(Id("func"),List(Id("x"))),IntLiteral(3)),BinaryOp("*",ArrayCell(Id("y"),BinaryOp("-",Id("x"),BinaryOp("/",BinaryOp("*",IntLiteral(3),IntLiteral(1)),BinaryOp("-",IntLiteral(6),IntLiteral(2))))),Id("y"))),BinaryOp("/",IntLiteral(8),IntLiteral(2)))))))))
    assert(checkAst(input, expected, 298))
  }
  test("Complicated program") {
    val input =
      """int func(int x,int y){
             return x-3*y+1/4-(12+7/x);
         }
         int[] func2(int x[],int y){
             y = x[1]*(3/6+2);
             return 2*x[2]/3+4/3*(x-3);
         }
         void main(){
           int x,y,z[3];
           if(x==y){
              func(x,z[0]);
           }
           else{
             if(x>y){
                 func(y,z[1]);
             }
             else{
                 func2(z,z[2]);
             }
           }
         }"""
    val expected = Program(List(FuncDecl(Id("func"),List(VarDecl(Id("x"),IntType),VarDecl(Id("y"),IntType)),IntType,Block(List(),List(Return(Some(BinaryOp("-",BinaryOp("+",BinaryOp("-",Id("x"),BinaryOp("*",IntLiteral(3),Id("y"))),BinaryOp("/",IntLiteral(1),IntLiteral(4))),BinaryOp("+",IntLiteral(12),BinaryOp("/",IntLiteral(7),Id("x"))))))))),FuncDecl(Id("func2"),List(VarDecl(Id("x"),ArrayPointerType(IntType)),VarDecl(Id("y"),IntType)),ArrayPointerType(IntType),Block(List(),List(BinaryOp("=",Id("y"),BinaryOp("*",ArrayCell(Id("x"),IntLiteral(1)),BinaryOp("+",BinaryOp("/",IntLiteral(3),IntLiteral(6)),IntLiteral(2)))),Return(Some(BinaryOp("+",BinaryOp("/",BinaryOp("*",IntLiteral(2),ArrayCell(Id("x"),IntLiteral(2))),IntLiteral(3)),BinaryOp("*",BinaryOp("/",IntLiteral(4),IntLiteral(3)),BinaryOp("-",Id("x"),IntLiteral(3))))))))),FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("x"),IntType),VarDecl(Id("y"),IntType),VarDecl(Id("z"),ArrayType(IntLiteral(3),IntType))),List(If(BinaryOp("==",Id("x"),Id("y")),Block(List(),List(CallExpr(Id("func"),List(Id("x"),ArrayCell(Id("z"),IntLiteral(0)))))),Some(Block(List(),List(If(BinaryOp(">",Id("x"),Id("y")),Block(List(),List(CallExpr(Id("func"),List(Id("y"),ArrayCell(Id("z"),IntLiteral(1)))))),Some(Block(List(),List(CallExpr(Id("func2"),List(Id("z"),ArrayCell(Id("z"),IntLiteral(2)))))))))))))))))
    assert(checkAst(input, expected, 299))
  }

  test("Another complicated program") {
    val input =
      """int func(int x,int y){
             return x-3*y+1/4-(12+7/x);
         }
         int[] func2(int x[],int y){
             y = x[1]*(3/6+2);
             return 2*x[2]/3+4/3*(x-3);
         }
        void main(){
              int x,y,z[3];
              int a;
              boolean k;
              if(x+y*4==2*5/3 || x+4*2!=5)
                do
                  x=x+3-2/7*4;3*x-2=6*3+5/1;
                while(x!=0);
              else{
                for(i=0;i<4;i=3*2-i/4){
                  a=-2+a/4;
                }
                do{
                x=3+2*x;x=4-2*3+1%y;
                }
                while(3*x-y+4*2!=0);
              }
        }"""
    val expected = Program(List(FuncDecl(Id("func"), List(VarDecl(Id("x"), IntType), VarDecl(Id("y"), IntType)), IntType, Block(List(), List(Return(Some(BinaryOp("-", BinaryOp("+", BinaryOp("-", Id("x"), BinaryOp("*", IntLiteral(3), Id("y"))), BinaryOp("/", IntLiteral(1), IntLiteral(4))), BinaryOp("+", IntLiteral(12), BinaryOp("/", IntLiteral(7), Id("x"))))))))), FuncDecl(Id("func2"), List(VarDecl(Id("x"), ArrayPointerType(IntType)), VarDecl(Id("y"), IntType)), ArrayPointerType(IntType), Block(List(), List(BinaryOp("=", Id("y"), BinaryOp("*", ArrayCell(Id("x"), IntLiteral(1)), BinaryOp("+", BinaryOp("/", IntLiteral(3), IntLiteral(6)), IntLiteral(2)))), Return(Some(BinaryOp("+", BinaryOp("/", BinaryOp("*", IntLiteral(2), ArrayCell(Id("x"), IntLiteral(2))), IntLiteral(3)), BinaryOp("*", BinaryOp("/", IntLiteral(4), IntLiteral(3)), BinaryOp("-", Id("x"), IntLiteral(3))))))))), FuncDecl(Id("main"), List(), VoidType, Block(List(VarDecl(Id("x"), IntType), VarDecl(Id("y"), IntType), VarDecl(Id("z"), ArrayType(IntLiteral(3), IntType)), VarDecl(Id("a"), IntType), VarDecl(Id("k"), BoolType)), List(If(BinaryOp("||", BinaryOp("==", BinaryOp("+", Id("x"), BinaryOp("*", Id("y"), IntLiteral(4))), BinaryOp("/", BinaryOp("*", IntLiteral(2), IntLiteral(5)), IntLiteral(3))), BinaryOp("!=", BinaryOp("+", Id("x"), BinaryOp("*", IntLiteral(4), IntLiteral(2))), IntLiteral(5))), Dowhile(List(BinaryOp("=", Id("x"), BinaryOp("-", BinaryOp("+", Id("x"), IntLiteral(3)), BinaryOp("*", BinaryOp("/", IntLiteral(2), IntLiteral(7)), IntLiteral(4)))), BinaryOp("=", BinaryOp("-", BinaryOp("*", IntLiteral(3), Id("x")), IntLiteral(2)), BinaryOp("+", BinaryOp("*", IntLiteral(6), IntLiteral(3)), BinaryOp("/", IntLiteral(5), IntLiteral(1))))), BinaryOp("!=", Id("x"), IntLiteral(0))), Some(Block(List(), List(For(BinaryOp("=", Id("i"), IntLiteral(0)), BinaryOp("<", Id("i"), IntLiteral(4)), BinaryOp("=", Id("i"), BinaryOp("-", BinaryOp("*", IntLiteral(3), IntLiteral(2)), BinaryOp("/", Id("i"), IntLiteral(4)))), Block(List(), List(BinaryOp("=", Id("a"), BinaryOp("+", UnaryOp("-", IntLiteral(2)), BinaryOp("/", Id("a"), IntLiteral(4))))))), Dowhile(List(Block(List(), List(BinaryOp("=", Id("x"), BinaryOp("+", IntLiteral(3), BinaryOp("*", IntLiteral(2), Id("x")))), BinaryOp("=", Id("x"), BinaryOp("+", BinaryOp("-", IntLiteral(4), BinaryOp("*", IntLiteral(2), IntLiteral(3))), BinaryOp("%", IntLiteral(1), Id("y"))))))), BinaryOp("!=", BinaryOp("+", BinaryOp("-", BinaryOp("*", IntLiteral(3), Id("x")), Id("y")), BinaryOp("*", IntLiteral(4), IntLiteral(2))), IntLiteral(0))))))))))))
    assert(checkAst(input, expected, 300))
  }
}


