import org.scalatest.FunSuite
import mc.checker._
import mc.utils._


class CheckerSuite extends FunSuite with TestChecker {
  test("Redeclared variable with same type and same name") {
    val input = "int a;string b;int a;"
    val expected = "Redeclared Variable: a"
    assert(checkCkr(input, expected, 401))
  }

  test("Redeclared variable with different type and same name") {
    val input = "int a,b;boolean c;string a;"
    val expected = "Redeclared Variable: a"
    assert(checkCkr(input, expected, 402))
  }

  test("Redeclared variable inside a function") {
    val input = "int b;int function(int a){int b,a;}"
    val expected = "Redeclared Variable: a"
    assert(checkCkr(input, expected, 403))
  }

  test("Redeclared variable inside a for statement") {
    val input = "int main(){int a,b;for(a=1;a<2;a=a+1){string b,c;int c;}}"
    val expected = "Redeclared Variable: c"
    assert(checkCkr(input, expected, 404))
  }

  test("Redeclared variable use same name as a declared function") {
    val input = "int sum(int a,int b){return a+b;}int sum;"
    val expected = "Redeclared Variable: sum"
    assert(checkCkr(input, expected, 405))
  }

  test("Redeclared function with same return type and same name") {
    val input = "int main(){return 1;}int main(){return 1;}"
    val expected = "Redeclared Function: main"
    assert(checkCkr(input, expected, 406))
  }

  test("Redeclared function with different return type and same name") {
    val input = "int func(){return 1;}boolean func(){return true;}"
    val expected = "Redeclared Function: func"
    assert(checkCkr(input, expected, 407))
  }

  test("Reclared function use same name as a declared variable") {
    val input = "int A;float A(){return a*2-1;}"
    val expected = "Redeclared Function: A"
    assert(checkCkr(input, expected, 408))
  }

  test("Redeclared built in function: getInt()") {
    val input = "void main(){}int sub(int a,int b){return a-b;}int getInt(){return 1;}"
    val expected = "Redeclared Function: getInt"
    assert(checkCkr(input, expected, 409))
  }

  test("Redeclared built in function: putInt()") {
    val input = "void main(){}void putInt(int i){return 3+i;}"
    val expected = "Redeclared Function: putInt"
    assert(checkCkr(input, expected, 410))
  }

  test("Redeclared built in function: putStringLn()") {
    val input = "void main(){}void putStringLn(string str){return str;}"
    val expected = "Redeclared Function: putStringLn"
    assert(checkCkr(input, expected, 411))
  }

  test("Redeclared parameter with same type and same name") {
    val input = "int func(int a,int b,int a){return a+b;}"
    val expected = "Redeclared Parameter: a"
    assert(checkCkr(input, expected, 412))
  }

  test("Redeclared parameter with different type and same name") {
    val input = "boolean func(int a,boolean b,string a){return !b;}"
    val expected = "Redeclared Parameter: a"
    assert(checkCkr(input, expected, 413))
  }


  test("Undeclared identifier used as a formal integer variable") {
    val input = "void main(){int b,c;b=c;b=a;}"
    val expected = "Undeclared Identifier: a"
    assert(checkCkr(input, expected, 414))
  }

  test("Undeclared identifier used in if statement") {
    val input = "int main(){int b;if(a>0){b=b+1;}}"
    val expected = "Undeclared Identifier: a"
    assert(checkCkr(input, expected, 415))
  }

  test("Undeclared identifier used in for statement") {
    val input = "int main(){for(a=1;a<1;a=a+1){a=a-1;}}"
    val expected = "Undeclared Identifier: a"
    assert(checkCkr(input, expected, 416))
  }

  test("Undeclared identifier used in do while statement") {
    val input = "int b;int main(){do{b=b-1;a=a-1;}while(a<1);}"
    val expected = "Undeclared Identifier: a"
    assert(checkCkr(input, expected, 417))
  }

  test("Undeclared identifier used outside scope that have same name identifier declaration") {
    val input = "int main(){int b;{int a;}a=1;}"
    val expected = "Undeclared Identifier: a"
    assert(checkCkr(input, expected, 418))
  }

  test("Undeclared identifier used in other function") {
    val input = "int sum1(){int a;a=1;}int sum2(){a=1;}"
    val expected = "Undeclared Identifier: a"
    assert(checkCkr(input, expected, 419))
  }

  test("Undeclared identifier: id as array type used without declaration"){
    val input = "void main(){int a[3];a[0]=a[2]+1;a[1]=b[0]+a[2];}"
    val expected = "Undeclared Identifier: b"
    assert(checkCkr(input, expected, 420))
  }

  test("Undeclared identifier: id of function use as a variable"){
    val input = "int sum(int a,int b){return a+b;}int main(){sum;}"
    val expected = "Undeclared Identifier: sum"
    assert(checkCkr(input, expected, 421))
  }

  test("Undeclared function used as int return type function") {
    val input = "void main(){int a,b,c;c=sum(a,b);}"
    val expected = "Undeclared Function: sum"
    assert(checkCkr(input, expected, 422))
  }

  test("Undeclared function used as bool return type function") {
    val input = "void main(){int a;if(ifBiggerThan1(a)){a=a+1;}}"
    val expected = "Undeclared Function: ifBiggerThan1"
    assert(checkCkr(input, expected, 423))
  }

  test("Undeclared function: variable id used for a function call") {
    val input = "int sum;void main(){int a;int b;sum(a,b);}"
    val expected = "Undeclared Function: sum"
    assert(checkCkr(input, expected, 424))
  }

  test("Type mismatch in if statement: type of conditional expression is int type") {
    val input = "void main(){int x;if(x>1)if(3){x=x+1;}}"
    val expected = "Type Mismatch In Statement: If(IntLiteral(3),Block(List(),List(BinaryOp(=,Id(x),BinaryOp(+,Id(x),IntLiteral(1))))),None)"
    assert(checkCkr(input, expected, 425))
  }

  test("Type mismatch in if statement: type of conditional expression is float type") {
    val input = "void main(){float x;if(x>2)if(x*1.2-2)x=x+1;}"
    val expected = "Type Mismatch In Statement: If(BinaryOp(-,BinaryOp(*,Id(x),FloatLiteral(1.2)),IntLiteral(2)),BinaryOp(=,Id(x),BinaryOp(+,Id(x),IntLiteral(1))),None)"
    assert(checkCkr(input, expected, 426))
  }

  test("Type mismatch in if statement: type of conditional expression is string type") {
    val input = "void main(){string x;if(x=\"hello\")x=\"goodbye\";}"
    val expected = "Type Mismatch In Statement: If(BinaryOp(=,Id(x),StringLiteral(hello)),BinaryOp(=,Id(x),StringLiteral(goodbye)),None)"
    assert(checkCkr(input, expected, 427))
  }

  test("Type mismatch in for statement: type of first expression is bool type") {
    val input = "void main(){int a;for(a=1;a>1;a=a+1){for(a>2;a<3;a=a+1){a=1-3*a;}}}"
    val expected = "Type Mismatch In Statement: For(BinaryOp(>,Id(a),IntLiteral(2)),BinaryOp(<,Id(a),IntLiteral(3)),BinaryOp(=,Id(a),BinaryOp(+,Id(a),IntLiteral(1))),Block(List(),List(BinaryOp(=,Id(a),BinaryOp(-,IntLiteral(1),BinaryOp(*,IntLiteral(3),Id(a)))))))"
    assert(checkCkr(input, expected, 428))
  }

  test("Type mismatch in for statement: type of first expression is float type") {
    val input = "void main(){float a;int b;for(a=2;b<3;b=b+2){a=2*b-3;}}"
    val expected = "Type Mismatch In Statement: For(BinaryOp(=,Id(a),IntLiteral(2)),BinaryOp(<,Id(b),IntLiteral(3)),BinaryOp(=,Id(b),BinaryOp(+,Id(b),IntLiteral(2))),Block(List(),List(BinaryOp(=,Id(a),BinaryOp(-,BinaryOp(*,IntLiteral(2),Id(b)),IntLiteral(3))))))"
    assert(checkCkr(input, expected, 429))
  }

  test("Type mismatch in for statement: type of second expression is int type") {
    val input = "void main(){int a;for(a=1;a+1;a=a+1){a=a*2;}}"
    val expected = "Type Mismatch In Statement: For(BinaryOp(=,Id(a),IntLiteral(1)),BinaryOp(+,Id(a),IntLiteral(1)),BinaryOp(=,Id(a),BinaryOp(+,Id(a),IntLiteral(1))),Block(List(),List(BinaryOp(=,Id(a),BinaryOp(*,Id(a),IntLiteral(2))))))"
    assert(checkCkr(input, expected, 430))
  }

  test("Type mismatch in for statement: type of third expression is bool type") {
    val input = "void main(){int b;for(b=b+1;b<1;b==3){b=b*2;}}"
    val expected = "Type Mismatch In Statement: For(BinaryOp(=,Id(b),BinaryOp(+,Id(b),IntLiteral(1))),BinaryOp(<,Id(b),IntLiteral(1)),BinaryOp(==,Id(b),IntLiteral(3)),Block(List(),List(BinaryOp(=,Id(b),BinaryOp(*,Id(b),IntLiteral(2))))))"
    assert(checkCkr(input, expected, 431))
  }

  test("Type mismatch in do while statement: type of conditional expression is int type") {
    val input = "void main(){int n;do n=n/2+1;while(n+1);}"
    val expected = "Type Mismatch In Statement: Dowhile(List(BinaryOp(=,Id(n),BinaryOp(+,BinaryOp(/,Id(n),IntLiteral(2)),IntLiteral(1)))),BinaryOp(+,Id(n),IntLiteral(1)))"
    assert(checkCkr(input, expected, 432))
  }

  test("Type mismatch in do while statement: type of conditional expression is float type") {
    val input = "void main(){float x;do x=x+1;while (x<3);do x=x/2+1;while(x-5*(x-2));}"
    val expected = "Type Mismatch In Statement: Dowhile(List(BinaryOp(=,Id(x),BinaryOp(+,BinaryOp(/,Id(x),IntLiteral(2)),IntLiteral(1)))),BinaryOp(-,Id(x),BinaryOp(*,IntLiteral(5),BinaryOp(-,Id(x),IntLiteral(2)))))"
    assert(checkCkr(input, expected, 433))
  }

  test("Type mismatch in do while statement: type of conditional expression is string type") {
    val input = "void main(){float x;string y;do x=2+3/x;while(y);}"
    val expected = "Type Mismatch In Statement: Dowhile(List(BinaryOp(=,Id(x),BinaryOp(+,IntLiteral(2),BinaryOp(/,IntLiteral(3),Id(x))))),Id(y))"
    assert(checkCkr(input, expected, 434))
  }

  test("Type mismatch in return statement: return statement type is float while the return type of function is int") {
    val input = "int sum(){int x;return x+1.2;}"
    val expected = "Type Mismatch In Statement: Return(Some(BinaryOp(+,Id(x),FloatLiteral(1.2))))"
    assert(checkCkr(input, expected, 435))
  }

  test("Type mismatch in return statement: return statement type is bool while the return type of function is float") {
    val input = "float returnFloat(){int x;if(x<0)x=x*2+6;return x>3;}"
    val expected = "Type Mismatch In Statement: Return(Some(BinaryOp(>,Id(x),IntLiteral(3))))"
    assert(checkCkr(input, expected, 436))
  }

  test("Type mismatch in return statement: return statement type is string while the return type of function is bool") {
    val input = "boolean ifLongString(){string str;str=\"holy\";return str;}"
    val expected = "Type Mismatch In Statement: Return(Some(Id(str)))"
    assert(checkCkr(input, expected, 437))
  }

  test("Type mismatch in return statement: return statement type is int while the return type of function is array pointer") {
    val input = "int[] getArray(){int x[2];return x[0];}"
    val expected = "Type Mismatch In Statement: Return(Some(ArrayCell(Id(x),IntLiteral(0))))"
    assert(checkCkr(input, expected, 438))
  }

  test("Type mismatch in return statement: return statement type is array type of int while the return type of function is array pointer of bool") {
    val input = "boolean[] getArray(boolean y[]){int x[3];if(y[1]==false)return y;else return x;}"
    val expected = "Type Mismatch In Statement: Return(Some(Id(x)))"
    assert(checkCkr(input, expected, 439))
  }

  test("Type mismatch in return statement: return statement type is array type of float while the return type of function is array pointer of int") {
    val input = "int[] getArray(){float x[3];int y[2];if(x[1]>0)return y; else return x;}"
    val expected = "Type Mismatch In Statement: Return(Some(Id(x)))"
    assert(checkCkr(input, expected, 440))
  }

  test("Type mismatch in statement: return statement type is int while the return type of function is array pointer") {
    val input = "int[] getArray(){int x[2];if(x[0]>1)x[0]=x[0]+1;return x[0];}"
    val expected = "Type Mismatch In Statement: Return(Some(ArrayCell(Id(x),IntLiteral(0))))"
    assert(checkCkr(input, expected, 441))
  }

  test("Type mismatch in statement: return statement return nothing while the return type of function is int") {
    val input = "int callFunc(){int x,y;return sub(x,y);}int sub(int x,int y){return;}"
    val expected = "Type Mismatch In Statement: Return(None)"
    assert(checkCkr(input, expected, 442))
  }

  test("Type mismatch in statement: return statement return boolean while the type of function is void"){
    val input ="boolean ifBigger(int x,int y){if(x>y) return true; else return false;}void main(){return ifBigger(3,2);}"
    val expected = "Type Mismatch In Statement: Return(Some(CallExpr(Id(ifBigger),List(IntLiteral(3),IntLiteral(2)))))"
    assert(checkCkr(input, expected, 443))
  }

  test("Type mismatch in statement: return statement return void while the type of function is void"){
    val input ="void voidFunc(){return;}void main(){return voidFunc();}"
    val expected = "Type Mismatch In Statement: Return(Some(CallExpr(Id(voidFunc),List())))"
    assert(checkCkr(input, expected, 444))
  }

  test("Type mismatch in subcripting expression E1[E2]: E1 is int type") {
    val input = "int main(){int a,b[2];a=b[1]+1;return a[2];}"
    val expected = "Type Mismatch In Expression: ArrayCell(Id(a),IntLiteral(2))"
    assert(checkCkr(input, expected, 445))
  }

  test("Type mismatch in subcripting expression E1[E2]: E1 is bool type") {
    val input = "boolean ifBiggerThan1(int x){if(x>1){return true;}else{false;}}int main(){int x;return ifBiggerThan1(x)[2];}"
    val expected = "Type Mismatch In Expression: ArrayCell(CallExpr(Id(ifBiggerThan1),List(Id(x))),IntLiteral(2))"
    assert(checkCkr(input, expected, 446))
  }

  test("Type mismatch in subcripting expression E1[E2]: E1 is float type") {
    val input = "int main(){int x,y[2];x=y[1]+1;x=3;return 2.1[x];}"
    val expected = "Type Mismatch In Expression: ArrayCell(FloatLiteral(2.1),Id(x))"
    assert(checkCkr(input, expected, 447))
  }

  test("Type mismatch in subcripting expression E1[E2]: E2 is float type") {
    val input = "void main(){int x[3];x[1]=0;x[0.2]=3+1;}"
    val expected = "Type Mismatch In Expression: ArrayCell(Id(x),FloatLiteral(0.2))"
    assert(checkCkr(input, expected, 448))
  }

  test("Type mismatch in subcripting expression E1[E2]: E2 is bool type") {
    val input = "void main(){int x[3],y;x[y<4];}"
    val expected = "Type Mismatch In Expression: ArrayCell(Id(x),BinaryOp(<,Id(y),IntLiteral(4)))"
    assert(checkCkr(input, expected, 449))
  }

  test("Type mismatch in binary expression: Unary operation ! used on int value") {
    val input = "void main(){int x;x=!x;}"
    val expected = "Type Mismatch In Expression: UnaryOp(!,Id(x))"
    assert(checkCkr(input, expected, 450))
  }

  test("Type mismatch in binary expression: Unary operation ! used on float value") {
    val input = "void main(){float x;x=!2.1;}"
    val expected = "Type Mismatch In Expression: UnaryOp(!,FloatLiteral(2.1))"
    assert(checkCkr(input, expected, 451))
  }

  test("Type mismatch in binary expression: Unary operation ! used on string value") {
    val input = "void main(){string x;x=\"my string\";x=!x;}"
    val expected = "Type Mismatch In Expression: UnaryOp(!,Id(x))"
    assert(checkCkr(input, expected, 452))
  }

  test("Type mismatch in binary expression: Unary operation - used on bool value") {
    val input = "void main(){boolean x;x=-true;}"
    val expected = "Type Mismatch In Expression: UnaryOp(-,BooleanLiteral(true))"
    assert(checkCkr(input, expected, 453))
  }

  test("Type mismatch in binary expression: Unary operation - used on string value") {
    val input = "void main(){string str;str=\"Hello\";str=-str;}"
    val expected = "Type Mismatch In Expression: UnaryOp(-,Id(str))"
    assert(checkCkr(input, expected, 454))
  }

  test("Type mismatch in binary expression: Binary operation || used between 2 int value") {
    val input = "void main(){float x,y;if(x||y){x=y-x/2;}}"
    val expected = "Type Mismatch In Expression: BinaryOp(||,Id(x),Id(y))"
    assert(checkCkr(input, expected, 455))
  }

  test("Type mismatch in binary expression: Binary operation || used between 2 string value") {
    val input = "void main(){string x,y;if(x||y){x=y-x/2;}}"
    val expected = "Type Mismatch In Expression: BinaryOp(||,Id(x),Id(y))"
    assert(checkCkr(input, expected, 456))
  }

  test("Type mismatch in binary expression: Binary operation == used between 2 float value") {
    val input = "void main(){float x,y;if(x==y){x=y-x/2;}}"
    val expected = "Type Mismatch In Expression: BinaryOp(==,Id(x),Id(y))"
    assert(checkCkr(input, expected, 457))
  }

  test("Type mismatch in binary expression: Binary operation == used between 2 string value") {
    val input = "void main(){int x;string y,z;if(x==3){if(y==z)x=x+4;}}"
    val expected = "Type Mismatch In Expression: BinaryOp(==,Id(y),Id(z))"
    assert(checkCkr(input, expected, 458))
  }

  test("Type mismatch in binary expression: Binary operation || used between float and bool value") {
    val input = "void main(){float x,y;if(x>y||x){x=2*x+1;}}"
    val expected = "Type Mismatch In Expression: BinaryOp(||,BinaryOp(>,Id(x),Id(y)),Id(x))"
    assert(checkCkr(input, expected, 459))
  }

  test("Type mismatch in binary expression: Binary operation || used between bool and string value") {
    val input = "void main(){int x,y;string str;if(x>y || x==y)x=x+y;else if(x==y+2 || str)y=y-x;}"
    val expected = "Type Mismatch In Expression: BinaryOp(||,BinaryOp(==,Id(x),BinaryOp(+,Id(y),IntLiteral(2))),Id(str))"
    assert(checkCkr(input, expected, 460))
  }

  test("Type mismatch in binary expression: Binary operation && used between int and bool value") {
    val input = "void main(){int x,y;if(x<1||x==2){y=x+1;if(y<2 && x+3){x=y*2;}}} "
    val expected = "Type Mismatch In Expression: BinaryOp(&&,BinaryOp(<,Id(y),IntLiteral(2)),BinaryOp(+,Id(x),IntLiteral(3)))"
    assert(checkCkr(input, expected, 461))
  }

  test("Type mismatch in binary expression: Binary operation % used between 2 float value") {
    val input = "void main(){float x,y;x=y%2;}"
    val expected = "Type Mismatch In Expression: BinaryOp(%,Id(y),IntLiteral(2))"
    assert(checkCkr(input, expected, 462))
  }

  test("Type mismatch in binary expression: Binary operation % used between float and int value"){
    val input = "void main(){float x;int y,z;y=z%2;x=x%y;}"
    val expected = "Type Mismatch In Expression: BinaryOp(%,Id(x),Id(y))"
    assert(checkCkr(input, expected, 463))
  }

  test("Type mismatch in binary expression: Binary operation % used between 2 stromg value"){
    val input = "void main(){string x,y;x=y%x;}"
    val expected = "Type Mismatch In Expression: BinaryOp(%,Id(y),Id(x))"
    assert(checkCkr(input, expected, 464))
  }

  test("Type mismatch in binary expression: Binary operation + used between bool and int value") {
    val input = "void main(){int x;x=x+true;}"
    val expected = "Type Mismatch In Expression: BinaryOp(+,Id(x),BooleanLiteral(true))"
    assert(checkCkr(input, expected, 465))
  }

  test("Type mismatch in binary expression: Binary operation + used between string and int value") {
    val input = "void main(){int x;string str;str=x+1;}"
    val expected = "Type Mismatch In Expression: BinaryOp(=,Id(str),BinaryOp(+,Id(x),IntLiteral(1)))"
    assert(checkCkr(input, expected, 466))
  }

  test("Type mismatch in binary expression: Wrong binary operation + used on 2 string value") {
    val input = "void main(){string x,y;x=\"Hi\";y=\" there\";x=x+y;}"
    val expected = "Type Mismatch In Expression: BinaryOp(+,Id(x),Id(y))"
    assert(checkCkr(input, expected, 467))
  }

  test("Type mismatch in binary expression: Wrong binary operation / used on 2 string value") {
    val input = "void main(){string x;x=\"Hello\";x=x/3;}"
    val expected = "Type Mismatch In Expression: BinaryOp(/,Id(x),IntLiteral(3))"
    assert(checkCkr(input, expected, 468))
  }

  test("Type mismatch in binary expression: Wrong binary operation / used between int and boolean value") {
    val input = "void main(){int x;x=4*2-1;x=3/x>2;}"
    val expected = "Type Mismatch In Expression: BinaryOp(=,Id(x),BinaryOp(>,BinaryOp(/,IntLiteral(3),Id(x)),IntLiteral(2)))"
    assert(checkCkr(input, expected, 469))
  }

  test("Type mismatch in binary expression: Wrong assignment coercion between int and float value") {
    val input = "void main(){int x;float y;y=x/2.1;x=y+1;}"
    val expected = "Type Mismatch In Expression: BinaryOp(=,Id(x),BinaryOp(+,Id(y),IntLiteral(1)))"
    assert(checkCkr(input, expected, 470))
  }

  test("Type mismatch in binary expression: Another wrong assignment coercion between int and float value") {
    val input = "int[] getX(){int x[3];return x;} void main(){int x;float y;y=y/x+3;x=1+x/y;}"
    val expected = "Type Mismatch In Expression: BinaryOp(=,Id(x),BinaryOp(+,IntLiteral(1),BinaryOp(/,Id(x),Id(y))))"
    assert(checkCkr(input, expected, 471))
  }

  test("Type mismatch in binary expression: Wrong assignment coercion between int and float values of 2 array") {
    val input = "void main(){int x[3];float y[2];if(x[0]>y[0]){x[1]=y[1];}}"
    val expected = "Type Mismatch In Expression: BinaryOp(=,ArrayCell(Id(x),IntLiteral(1)),ArrayCell(Id(y),IntLiteral(1)))"
    assert(checkCkr(input, expected, 472))
  }

  test("Type mismatch in binary expression: Wrong assignment coercion in a mix of int and float value"){
    val input = "void main(){int x;float y;y=x+1;if(x>y){x=y/2+1;}}"
    val expected = "Type Mismatch In Expression: BinaryOp(=,Id(x),BinaryOp(+,BinaryOp(/,Id(y),IntLiteral(2)),IntLiteral(1)))"
    assert(checkCkr(input, expected, 473))
  }

  test("Type mismatch in binary expression: Wrong assignment coercion in a mix of boolean and float value"){
    val input = "void main(){int x;x=2*x+1;if(getInt()>3){x=-3*x+x>2;}}"
    val expected = "Type Mismatch In Expression: BinaryOp(=,Id(x),BinaryOp(>,BinaryOp(+,BinaryOp(*,UnaryOp(-,IntLiteral(3)),Id(x)),Id(x)),IntLiteral(2)))"
    assert(checkCkr(input, expected, 474))
  }

  test("Type mismatch in binary expression: Wrong assignment coercion between boolean and int value"){
    val input = "void main(){int x;boolean y;if(y)x=1/2+y;}"
    val expected = "Type Mismatch In Expression: BinaryOp(+,BinaryOp(/,IntLiteral(1),IntLiteral(2)),Id(y))"
    assert(checkCkr(input, expected, 475))
  }

  test("Type mismatch in binary expression: Wrong assignment coercion between int and string value"){
    val input = "void main(){int x;string str;str=\"hello\";if(x>0){x=x+1;str=str+x;}}"
    val expected = "Type Mismatch In Expression: BinaryOp(+,Id(str),Id(x))"
    assert(checkCkr(input, expected, 476))
  }

  test("Type mismatch in binary expression: Wrong assignment coercion between 2 array type"){
    val input = "void main(){int x[2],y[2];if(x[2]==y[1])x=y;}"
    val expected = "Type Mismatch In Expression: BinaryOp(=,Id(x),Id(y))"
    assert(checkCkr(input, expected, 477))
  }

  test("Type mismatch in binary expression: Wrong assignment coercion array pointer and array type"){
    val input = "void main(int x[]){int y[2];if(x[0]>y[0])x=y;}"
    val expected = "Type Mismatch In Expression: BinaryOp(=,Id(x),Id(y))"
    assert(checkCkr(input, expected, 478))
  }

  test("Type mismatch in binary expression: wrong assignment coercion in a mix of float and int value"){
    val input = "void main(){float x;int y;x=y-2/3;y=(y+3/2)-4+x;}"
    val expected = "Type Mismatch In Expression: BinaryOp(=,Id(y),BinaryOp(+,BinaryOp(-,BinaryOp(+,Id(y),BinaryOp(/,IntLiteral(3),IntLiteral(2))),IntLiteral(4)),Id(x)))"
    assert(checkCkr(input, expected, 479))
  }

  test("Type mismatch in binary expression in statement if"){
    val input = "void main(){int x,y;if(x>y && x+2)x=2*y-3;}"
    val expected = "Type Mismatch In Expression: BinaryOp(&&,BinaryOp(>,Id(x),Id(y)),BinaryOp(+,Id(x),IntLiteral(2)))"
    assert(checkCkr(input, expected, 480))
  }

  test("Another type mismatch in binary expression in statement if"){
    val input = "void main(){int x;float y;if(x==2 || x/2==y)x=4*y-3;}"
    val expected = "Type Mismatch In Expression: BinaryOp(==,BinaryOp(/,Id(x),IntLiteral(2)),Id(y))"
    assert(checkCkr(input, expected, 481))
  }

  test("Type mismatch in binary expression in statement for"){
    val input = "void main(){int x,y;for(x=1;x<y||x>3;y=x-3.2){x=2*y;y=y+1;}}"
    val expected = "Type Mismatch In Expression: BinaryOp(=,Id(y),BinaryOp(-,Id(x),FloatLiteral(3.2)))"
    assert(checkCkr(input, expected, 482))
  }

  test("Another type mismatch in binary expression in statement for"){
    val input = "void main(){int x,y;for(x=1;x<y||x-3;y=x-1){x=2*y;y=y+1;}}"
    val expected = "Type Mismatch In Expression: BinaryOp(||,BinaryOp(<,Id(x),Id(y)),BinaryOp(-,Id(x),IntLiteral(3)))"
    assert(checkCkr(input, expected, 483))
  }

  test("Type mismatch in binary expression in statement do while"){
    val input = "void main(){int x,y;do{x=2*y;y=y+1;}while(x>1 || x==\"string\");}"
    val expected = "Type Mismatch In Expression: BinaryOp(==,Id(x),StringLiteral(string))"
    assert(checkCkr(input, expected, 484))
  }

  test("Another type mismatch in binary expression in statement do while"){
    val input = "void main(){int x,y;do{x=2*y;y=y+1;}while(x==x/(y-1.2));}"
    val expected = "Type Mismatch In Expression: BinaryOp(==,Id(x),BinaryOp(/,Id(x),BinaryOp(-,Id(y),FloatLiteral(1.2))))"
    assert(checkCkr(input, expected, 485))
  }

  test("Type mismatch in binary expression in a complex program"){
    val input = "void main(){float x;int y;x=2.1-y/2.2;y=y*2+5;if((x-1+y/2)>3)y=2*3-x;}"
    val expected = "Type Mismatch In Expression: BinaryOp(=,Id(y),BinaryOp(-,BinaryOp(*,IntLiteral(2),IntLiteral(3)),Id(x)))"
    assert(checkCkr(input, expected, 486))
  }

  test("Type mismatch in binary expression caused by overrided variable"){
    val input = "int a,b;void main(){string a;b=b+1;a=a+1;}"
    val expected = "Type Mismatch In Expression: BinaryOp(+,Id(a),IntLiteral(1))"
    assert(checkCkr(input, expected, 487))
  }

  test("Another type mismatch in binary expression caused by overrided variable"){
    val input = "float a,b;void main(){int a;b=a/1.2+1;a=a-1.1;}"
    val expected = "Type Mismatch In Expression: BinaryOp(=,Id(a),BinaryOp(-,Id(a),FloatLiteral(1.1)))"
    assert(checkCkr(input, expected, 488))
  }

  test("Type mismatch in call expression: not enough arguments to call corresponding function"){
    val input="int sum(int a,int b){return a+b;}void main(){int x,y,z;z=sum(x,y);y=sum(x);}"
    val expected="Type Mismatch In Expression: CallExpr(Id(sum),List(Id(x)))"
    assert(checkCkr(input, expected, 489))
  }

  test("Type mismatch in call expression: to much arguments to call corresponding function"){
    val input="int sum(int a,int b){return a+b;}void main(){int x,y,z;z=sum(x,y);y=sum(x,z,y);}"
    val expected="Type Mismatch In Expression: CallExpr(Id(sum),List(Id(x),Id(z),Id(y)))"
    assert(checkCkr(input, expected, 490))
  }

  test("Type mismatch in call expression: argument is int while corresponding parameter is bool"){
    val input="int ifIncrease(int x,boolean y){if(y){return x+1;}else{return x;}}void main(){int x,y;x=ifIncrease(x,y);}"
    val expected="Type Mismatch In Expression: CallExpr(Id(ifIncrease),List(Id(x),Id(y)))"
    assert(checkCkr(input, expected, 491))
  }

  test("Type mismatch in call expression: argument is boolean while corresponding parameter is int"){
    val input="int getBigger(int x,int y){if(x>y)return x;else return y;}void main(){int x,y;x=getBigger(x,y>2);}"
    val expected="Type Mismatch In Expression: CallExpr(Id(getBigger),List(Id(x),BinaryOp(>,Id(y),IntLiteral(2))))"
    assert(checkCkr(input, expected, 492))
  }

  test("Type mismatch in call expression: argument is float while corresponding parameter is int"){
    val input="int getMaxNum(int x,int y,int z){int max;max=0;if(x>max)max=x;if(y>max)max=y;if(z>max)max=z;return max;}void main(){int x,y,z,max;max=getMaxNum(x,y,z+1.2);}"
    val expected="Type Mismatch In Expression: CallExpr(Id(getMaxNum),List(Id(x),Id(y),BinaryOp(+,Id(z),FloatLiteral(1.2))))"
    assert(checkCkr(input, expected, 493))
  }

  test("Type mismatch in call expression: argument is string while corresponding parameter is float"){
    val input="float addNum(float x,float y){return x+y;}void main(){string x;float y,z;y=addNum(x,y);z=addNum(y,z);}"
    val expected="Type Mismatch In Expression: CallExpr(Id(addNum),List(Id(x),Id(y)))"
    assert(checkCkr(input, expected, 494))
  }

  test("Type mismatch in call expression: argument is array type of float while corresponding parameter is array pointer type of int"){
    val input="int getRemainderOf1st(int x[],int y){return x[0]/y;}void main(){int x[3];float y[2];x[1]=getRemainderOf1st(x,x[0]);x[2]=getRemainderOf1st(y,x[1]);}"
    val expected="Type Mismatch In Expression: CallExpr(Id(getRemainderOf1st),List(Id(y),ArrayCell(Id(x),IntLiteral(1))))"
    assert(checkCkr(input, expected, 495))
  }

  test("Type mismatch in call expression: argument is array type of int while corresponding parameter is array pointer type of bool"){
    val input="boolean getFirstElem(boolean x[]){return x[0];}void main(){boolean x[4];int y[2];x[3]=getFirstElem(x);x[2]=x[3]+getFirstElem(y);}"
    val expected="Type Mismatch In Expression: CallExpr(Id(getFirstElem),List(Id(y)))"
    assert(checkCkr(input, expected, 496))
  }

  test("Type mismatch in call expression: argument is array type of bool while corresponding parameter is array pointer type of float"){
    val input="float getFirstElem(float x[]){return x[0]+1.2;}void main(){float x[3];boolean y[2];if(y[1])x[2]=getFirstElem(x)+x[0];else x[1]=getFirstElem(y);}"
    val expected="Type Mismatch In Expression: CallExpr(Id(getFirstElem),List(Id(y)))"
    assert(checkCkr(input, expected, 497))
  }

  test("Type mismatch in call expression: call function getInt with an argument of integer") {
    val input = "int func(int a[],int b[]){return a[1];}void main (int a[]) {int b[3];func(a,b);if(getInt()==2)getInt(3);}"
    val expected = "Type Mismatch In Expression: CallExpr(Id(getInt),List(IntLiteral(3)))"
    assert(checkCkr(input,expected,498))
  }

  test("Type mismatch in call expression: call function putIntLn with no argument") {
    val input = "void main () {putIntLn(3);putIntLn();}"
    val expected = "Type Mismatch In Expression: CallExpr(Id(putIntLn),List())"
    assert(checkCkr(input,expected,499))
  }

  test("Type mismatch in call expression in a complex program"){
    val input = "int getBigger(int a,int b){if(a>b)return a;else return b;}void main(){int a,b;float c;if(a>1)a=a*2-3;b=getBigger(a,5);b=3*4-2*a;return getBigger(a,c);}"
    val expected = "Type Mismatch In Expression: CallExpr(Id(getBigger),List(Id(a),Id(c)))"
    assert(checkCkr(input,expected,500))
  }
}
