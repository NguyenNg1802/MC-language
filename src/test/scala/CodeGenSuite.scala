
import org.scalatest.FunSuite
import mc.utils._

class CodeGenSuite extends FunSuite with TestCodeGen {
  test("print an int") {
    val input = """void main () {putIntLn(1);}"""
    val expected = "1"
    assert(checkCode(input, expected, 501))
  }

  test("print a float") {
    val input = """void main () {putFloatLn(1.3);}"""
    val expected = "1.3"
    assert(checkCode(input, expected, 502))
  }

  test("print another float") {
    val input = """void main () {putFloatLn(1);}"""
    val expected = "1.0"
    assert(checkCode(input, expected, 503))
  }

  test("print a string") {
    val input = """void main () {putStringLn("abc");}"""
    val expected = "abc"
    assert(checkCode(input, expected, 504))
  }

  test("print a boolean") {
    val input = """void main () {putBoolLn(true);}"""
    val expected = "true"
    assert(checkCode(input, expected, 505))
  }

  test("print a global int variable without assign") {
    val input = """int a;void main () {putIntLn(a);}"""
    val expected = "0"
    assert(checkCode(input, expected, 506))
  }

  test("print a global float variable without assign") {
    val input = """float a;void main () {putFloatLn(a);}"""
    val expected = "0.0"
    assert(checkCode(input, expected, 507))
  }

  test("print a global string variable without assign") {
    val input = """string a;void main () {putStringLn(a);}"""
    val expected = "null"
    assert(checkCode(input, expected, 508))
  }

  test("print a global boolean variable without assign") {
    val input = """boolean a;void main () {putBoolLn(a);}"""
    val expected = "false"
    assert(checkCode(input, expected, 509))
  }

  test("print a global int array element without assign") {
    val input = """int a[3];void main(){putIntLn(a[0]);}"""
    val expected = "0"
    assert(checkCode(input, expected, 510))
  }

  test("print a global string array element without assign") {
    val input = """string str[3];void main(){putStringLn(str[0]);}"""
    val expected = "null"
    assert(checkCode(input, expected, 511))
  }

  test("unary expr: print a negative integer") {
    val input = """void main () {putIntLn(-1);}"""
    val expected = "-1"
    assert(checkCode(input, expected, 512))
  }

  test("unary expr: print a negative float") {
    val input = """void main () {putFloatLn(-12.1);}"""
    val expected = "-12.1"
    assert(checkCode(input, expected, 513))
  }

  test("unary expr: print a not boolean") {
    val input = """void main () {putBoolLn(!false);}"""
    val expected = "true"
    assert(checkCode(input, expected, 514))
  }

  test("binary expr: print a assigned int varible") {
    val input = """void main () {int a;a=1;putIntLn(a);}"""
    val expected = "1"
    assert(checkCode(input, expected, 515))
  }

  test("binary expr: print a assigned float varible") {
    val input = """void main () {float a;a=1.2;putFloatLn(a);}"""
    val expected = "1.2"
    assert(checkCode(input, expected, 516))
  }

  test("binary expr: print another assigned float varible") {
    val input = """void main () {float a;a=1;putFloatLn(a);}"""
    val expected = "1.0"
    assert(checkCode(input, expected, 517))
  }

  test("binary expr: print a assigned local array element of integer") {
    val input ="""void main(){int a[3];a[0]=3;putIntLn(a[0]);}"""
    val expected = "3"
    assert(checkCode(input, expected, 518))
  }

  test("binary expr: print a assigned local array element of string") {
    val input ="""void main(){string a[3];a[0]="hello";putStringLn(a[0]);}"""
    val expected = "hello"
    assert(checkCode(input, expected, 519))
  }

  test("binary expr: print result of a more complex assignment for int") {
    val input = """void main () {int a,b;a=b=1;putIntLn(b);}"""
    val expected = "1"
    assert(checkCode(input, expected, 520))
  }

  test("binary expr: print result of another complex assignment for float") {
    val input = """void main () {float a,b;b=2;a=b=b+1.0;putFloatLn(a);putFloatLn(b);}"""
    val expected = "3.03.0"
    assert(checkCode(input, expected, 521))
  }

  test("binary expr: print result of another complex assignment") {
    val input = """void main () {int a;float b;b=a=1;putFloatLn(b);}"""
    val expected = "1.0"
    assert(checkCode(input, expected, 522))
  }

  test("binary expr: print a result of simple binary expr of + between int,int") {
    val input = """void main(){putIntLn(3+2);}"""
    val expected = "5"
    assert(checkCode(input, expected, 523))
  }

  test("binary expr: print a result of more complex binary expr of + between int,int") {
    val input = """void main(){putIntLn(3+2+4+1);}"""
    val expected = "10"
    assert(checkCode(input, expected, 524))
  }

  test("binary expr: print a result of simple binary expr of - between int") {
    val input = """void main(){putIntLn(3-1);}"""
    val expected = "2"
    assert(checkCode(input, expected, 525))
  }

  test("binary expr: print a result of more complex binary expr of - between int,int") {
    val input = """void main(){putIntLn(17-2-4);}"""
    val expected = "11"
    assert(checkCode(input, expected, 526))
  }

  test("binary expr: print a result of simple binary expr of + between float,int") {
    val input = """void main(){putFloatLn(3.1+1);}"""
    val expected = "4.1"
    assert(checkCode(input, expected, 527))
  }

  test("binary expr: print a result of more complex binary expr of + between float,int") {
    val input = """void main(){putFloatLn(3.1+1+4.4+6);}"""
    val expected = "14.5"
    assert(checkCode(input, expected, 528))
  }

  test("binary expr: print a result of simple binary expr of - between float,int") {
    val input = """void main(){putFloatLn(3-1.2);}"""
    val expected = "1.8"
    assert(checkCode(input, expected, 529))
  }

  test("binary expr: print a result of more complex binary expr of +,- between int") {
    val input = """void main(){putIntLn(4-2+1);}"""
    val expected = "3"
    assert(checkCode(input, expected, 530))
  }

  test("binary expr: print a result of more complex binary expr of +,- between float,int") {
    val input = """void main(){putFloatLn(4-2+1+2.3+5);}"""
    val expected = "10.3"
    assert(checkCode(input, expected, 531))
  }

  test("binary expr: print a result of binary expr of * between int") {
    val input = """void main(){putIntLn(4*2*3);}"""
    val expected = "24"
    assert(checkCode(input, expected, 532))
  }

  test("binary expr: print a result of binary expr of / between int") {
    val input = """void main(){int a;a=2;putIntLn(42/a/3);}"""
    val expected = "7"
    assert(checkCode(input, expected, 533))
  }

  test("binary expr: print a result of binary expr of / between float") {
    val input = """void main(){float b;b=36.6;putFloatLn(b/3/2);}"""
    val expected = "6.1"
    assert(checkCode(input, expected, 534))
  }

  test("binary expr: print a result of more complex binary expr of *,/ between int") {
    val input = """void main(){putIntLn(36/3/2*4);}"""
    val expected = "24"
    assert(checkCode(input, expected, 535))
  }

  test("binary expr: print a result of more complex binary expr of *,/ between float") {
    val input = """void main(){putFloatLn(2*3.5*2/6*9);}"""
    val expected = "21.0"
    assert(checkCode(input, expected, 536))
  }

  test("binary expr: print a result of more complex binary expr of +,-,*,/ between int,float") {
    val input = """void main(){int a,b;a=b=2;putFloatLn(a*3.5*b/6*9);}"""
    val expected = "21.0"
    assert(checkCode(input, expected, 537))
  }

  test("binary expr: print a result of a simple binary expr of %") {
    val input ="""void main(){int a;a=5;putIntLn(5%2);}"""
    val expected = "1"
    assert(checkCode(input, expected, 538))
  }

  test("binary expr: print a result of binary expr of % and other binary ops") {
    val input ="""void main(){int a,b;float c;a=13;b=3;c=a%b+1.0/2;putFloatLn(c);}"""
    val expected = "1.5"
    assert(checkCode(input, expected, 539))
  }

  test("binary expr: print a result of more complex binary expr of % and other binary ops") {
    val input ="""void main(){int a,b;float c;a=13;b=3;c=(a%b+5)%4;putFloatLn(c);}"""
    val expected = "2.0"
    assert(checkCode(input, expected, 540))
  }

  test("binary expr: print a result of a more complex simple binary expr of % and other binary ops") {
    val input ="""void main(){int a,b;float c;a=13;b=3;c=(a%b+5)%5;putFloatLn(c);}"""
    val expected = "1.0"
    assert(checkCode(input, expected, 541))
  }

  test("binary expr: print a result of simple binary expr of < between int") {
    val input = """void main(){int a,b;a=(b=2)+1;putBoolLn(a<b);}"""
    val expected = "false"
    assert(checkCode(input, expected, 542))
  }

  test("binary expr: print a result of simple binary expr of > between int") {
    val input = """void main(){int a[2],b;a[0]=b=2;b=3*a[0]-3;putBoolLn(a[0]<b);}"""
    val expected = "true"
    assert(checkCode(input, expected, 543))
  }

  test("binary expr: print a result of simple binary expr of && between simple binary expressions") {
    val input = """void main(){float a;int b;a=b=2-1;putBoolLn(a<b+1.2 && a+3>b);}"""
    val expected = "true"
    assert(checkCode(input, expected, 544))
  }

  test("binary expr: print a result of a more complex expr of && between array element of bool type") {
    val input = """boolean a[3];void main(){int b,c;a[1]=true;b=4;c=2*b-3;putBoolLn(b>2 && c<10 && (a[0]=true) && a[1]);}"""
    val expected = "true"
    assert(checkCode(input, expected, 545))
  }

  test("binary expr: print 2 results of simple binary expr of && to check short circuit") {
    val input = """void main(){float a;int b[3];b[0]=3;a=b[0]=(b[0]*2);b[0]=b[0]*2-1;b[0]>=2 && (a=1)>b[0]/2 && (b[0]=4)>1;putFloatLn(a);putIntLn(b[0]);}"""
    val expected = "1.011"
    assert(checkCode(input, expected, 546))
  }

  test("binary expr: print a result of simple binary expr of || between simple binary expressions") {
    val input = """void main(){int a,b;b=a=1;a=b=2*3-a;putBoolLn(a+1>2 || b-1<3);}"""
    val expected = "true"
    assert(checkCode(input, expected, 547))
  }

  test("binary expr: print a result of a more complex expr of || between array element of bool type") {
    val input = """boolean a[3];void main(){int b,c;a[1]=true;b=4;c=2*b-3;putBoolLn(b<2 || c<10 || (a[0]=true) || a[1]);}"""
    val expected = "true"
    assert(checkCode(input, expected, 548))
  }

  test("binary expr: print 3 results of simple binary expr of || to check short circuit") {
    val input = """void main(){float a;int b,c;a=b=c=1;a=5*c-2;c=2*b+1; b>(b=b*3-1) || a>1 || (c=1)==1;putFloatLn(a);putIntLn(b);putIntLn(c);}"""
    val expected = "3.023"
    assert(checkCode(input, expected, 549))
  }

  test("binary expr: print result of a combination of ||,&& to check short circuit") {
    val input = """void main(){float a;int b,c;a=b=c=1;a=5*c-2;c=2*b+1; b>(b=b*3-1) || a>1 || (c=1)==1;putFloatLn(a);putIntLn(b);putIntLn(c);}"""
    val expected = "3.023"
    assert(checkCode(input, expected, 550))
  }

  test("binary expr: print result of simple binary expr of ==") {
    val input = """void main(){int a,b;a=1;b=3*a-1;putBoolLn(b==2);}"""
    val expected = "true"
    assert(checkCode(input, expected, 551))
  }

  test("binary expr: print result of more complex binary expr of == with other binary operations") {
    val input = """void main(){int a,b;a=1;b=3*a-1;putBoolLn(b==2 && b%2==0);}"""
    val expected = "true"
    assert(checkCode(input, expected, 552))
  }

  test("binary expr: print result of simple binary expr of != ") {
    val input = """void main(){int a,b;a=1;b=3*a-1;putBoolLn(b!=2);}"""
    val expected = "false"
    assert(checkCode(input, expected, 553))
  }

  test("binary expr: print result of more complex simple binary expr of != with other binary operations") {
    val input = """void main(){int a,b;a=1;b=3*a-1;putBoolLn(b!=1 && b!=3);}"""
    val expected = "true"
    assert(checkCode(input, expected, 554))
  }

  test("binary expr: print result of a complex binary binary expr of !=,== and other binary operations") {
    val input = """void main(){int a,b;a=1;b=2;putBoolLn((b!=3)==(a!=1));}"""
    val expected = "false"
    assert(checkCode(input, expected, 555))
  }

  test("binary expr: print result of another a complex binary binary expr of !=,== and other binary operations") {
    val input = """void main(){int a,b;a=1;b=2;putBoolLn((b==2)!=(a!=1));}"""
    val expected = "true"
    assert(checkCode(input, expected, 556))
  }

  test("call expr: print 1 using a function call with no input") {
    val input = """void print1(){putIntLn(1);}void main (){print1();}"""
    val expected = "1"
    assert(checkCode(input, expected, 557))
  }

  test("call expr: print sum of 2 number using a function call with no return") {
    val input = """void printSum(int a,int b){putIntLn(a+b);}void main (){printSum(3,4);}"""
    val expected = "7"
    assert(checkCode(input, expected, 558))
  }

  test("call expr: print result of a sum of interger using function return result") {
    val input = """int sum(int a,int b){return a+b;}void main (){int a,b;a=b=1;b=a+1;putIntLn(sum(a,b));}"""
    val expected = "3"
    assert(checkCode(input, expected, 559))
  }

  test("call expr: print result of a multiplication of float using function") {
    val input = """void main (){float a,b;b=3;a=mul(1.5,2.0);putFloatLn(mul(b,a));}float mul(float a,float b){return a*b;}"""
    val expected = "9.0"
    assert(checkCode(input, expected, 560))
  }

  test("call expr: print result of test using integer into float input function") {
    val input = """float x2Float(float a){return a*2;}void main(){int a;a=3;putFloatLn(x2Float(a));}"""
    val expected = "6.0"
    assert(checkCode(input, expected, 561))
  }

  test("call expr: print a first element of array that is assigned through calling function") {
    val input = """int Func(int a[]){a[0]=1;return a[0];}void main(){int a[2];putFloatLn(Func(a));}"""
    val expected = "1.0"
    assert(checkCode(input, expected, 562))
  }

  test("call expr: print a second element of a array pointer return by a function") {
    val input = """string arr[2];string[] setArr(string a[]){a[0]="b";a[1]="c";return a;}void main(){arr[0]="a";putStringLn(setArr(arr)[0]);}"""
    val expected = "b"
    assert(checkCode(input, expected, 563))
  }

  test("call expr: print result of test using a result of a function as input to call other function") {
    val input = """float add(float a){return a+1;}void main(){int a;a=3;putFloatLn(add(add(3)));}"""
    val expected = "5.0"
    assert(checkCode(input, expected, 564))
  }

  test("call expr: print list of smaller numbers than the input value using a simple recursive function") {
    val input ="""void printNum(int a){if(a>0){putIntLn(a);a=a-1;printNum(a);}}void main(){printNum(7);}"""
    val expected = "7654321"
    assert(checkCode(input, expected, 565))
  }

  test("call expr: print result of a recursive function call") {
    val input ="""void printInt(int n){if(n > 0) printInt(n - 1);putIntLn(n);}void main() {printInt(9);}"""
    val expected = "0123456789"
    assert(checkCode(input, expected, 566))
  }

  test("call expr: print result of sum of two return result of call expr") {
    val input ="""int mul(int a,int b){return a*b;}void main(){putIntLn(mul(2,3)+mul(4,5));}"""
    val expected = "26"
    assert(checkCode(input, expected, 567))
  }

  test("call expr: print result of and of two return result of call expr") {
    val input ="""boolean bigger(int a,int b){return a>b;}void main(){putBoolLn(bigger(4,3)&&bigger(2,5));}"""
    val expected = "false"
    assert(checkCode(input, expected, 568))
  }

  test("call expr: print result of or of two return result of call expr") {
    val input ="""boolean divBy2(int a){return a%2==0;}void main(){putBoolLn(divBy2(5)||divBy2(4));}"""
    val expected = "true"
    assert(checkCode(input, expected, 569))
  }

  test("block statement: print value of integer variable inside the block") {
    val input ="""void main(){int a,b;a=3;{int b;b=a;putIntLn(b);}}"""
    val expected = "3"
    assert(checkCode(input, expected, 570))
  }

  test("block statement: print value of float variable inside the block") {
    val input ="""void main(){int a,b;a=4;{float b;b=a;putFloatLn(b);}}"""
    val expected = "4.0"
    assert(checkCode(input, expected, 571))
  }

  test("block statement: print value of variable inside the nested block") {
    val input ="""void main(){int a;{int b;b=12;{float a;{a=b;putFloatLn(a);}}}}"""
    val expected = "12.0"
    assert(checkCode(input, expected, 572))
  }

  test("if statement: print result of assigned varible using a if stmt without else") {
    val input = """int a[2];void main(){if(a[0]<3 && a[1]==0)a[1]=2;putIntLn(a[1]);}"""
    val expected = "2"
    assert(checkCode(input, expected, 573))
  }

  test("if statement: print result of assigned variable using a more complex if stmt without else") {
    val input = """int a[2];void main(){if(a[0]<3 && ((a[0]=1)==1) && a[1]>1 )a[0]=5;putFloatLn(a[0]);}"""
    val expected = "1.0"
    assert(checkCode(input, expected, 574))
  }

  test("if statement: print result of calling function using a if stmt with else") {
    val input = """int sum(int a,int b){return a+b;}void main(){int a,b;a=2;b=3;if(a-1<b || a*2>b+1) putFloatLn(sum(a,b)); else putFloatLn(mul(a,b));}float mul(float a,float b){return a*b;}"""
    val expected = "5.0"
    assert(checkCode(input, expected, 575))
  }

  test("if statement: print 2 result of a more complex if with else") {
    val input = """void main(){int a,b;b=2;a=2*b-1;if(a<b&&a+2*b>=2){a=b;b=2*a+1;}else{a=2;b=(b=3)+1;}putIntLn(a);putIntLn(b);}"""
    val expected = "24"
    assert(checkCode(input, expected, 576))
  }

  test("if statement: print result varible modified with if statement inside another if stmt without else") {
    val input = """void main(){int a[2];a[0]=4;a[1]=a[0];if(a[0]>-1){if(a[0]>1)if(a[0]%2==0)a[1]=3;}putIntLn(a[1]);}"""
    val expected = "3"
    assert(checkCode(input, expected, 577))
  }

  test("if statement: print result of varible modified with if statement inside another if stmt with else") {
    val input = """void main(){int a[2];a[0]=4;if(a[0]>-1){if(a[1]+1<4){a[0]=2*a[1]-2;}a[0]=a[0]+1;}else a[0]=5;putIntLn(a[0]);}"""
    val expected = "-1"
    assert(checkCode(input, expected, 578))
  }

  test("if statement: print result of variable modified with if statement inside a block statment") {
    val input = """boolean a[1];void main(){float b;{if(!a[0]){b=1+2.5;putFloatLn(b);}}}"""
    val expected = "3.5"
    assert(checkCode(input, expected, 579))
  }

  test("call expr: print result of boolean using call expr to check if the input is odd number") {
    val input ="""boolean checkIfOdd(int num){if(num%2==0)return 0; return 1;}void main(){putBoolLn(checkIfOdd(12));}"""
    val expected = "false"
    assert(checkCode(input, expected, 580))
  }

  test("for statement: print result of varible after modified with a simple for stmt") {
    val input = """void main(){int a,i;a=2;for(i=1;i<4;i=i+1){a=a*2;}putIntLn(a);}"""
    val expected = "16"
    assert(checkCode(input, expected, 581))
  }

  test("for statement: print result of variable after modified with for stmt that contains if stmt") {
    val input = """void main(){float a,i;a=2.5;for(i=1;i<4;i=i+1){if(a>7)a=a*2;else a=a-1;}putFloatLn(a);}"""
    val expected = "-0.5"
    assert(checkCode(input, expected, 582))
  }

  test("for statement: print result of variable after modified with for stmt that contains another for stmt") {
    val input = """void main(){float a,i,j;a=2.5;for(i=1;i<4;i=i+1){for(j=3;j>1;j=j-1){a=a+4/2;a=2*a;}a=a-1;}putFloatLn(a);}"""
    val expected = "391.0"
    assert(checkCode(input, expected, 583))
  }

  test("for statement: print result of variable after modified with for stmt inside a block stmt") {
    val input = """void main(){int b,i;{for(b=0;b<6;b=b+1){i=2*b+1;putIntLn(i);}}}"""
    val expected = "1357911"
    assert(checkCode(input, expected, 584))
  }

  test("for statement: print return result of a function that contain for stmt") {
    val input = """void main(){putIntLn(getPower(3,5));}int getPower(int a,int b){int i,c;c=1;for(i=0;i<b;i=i+1){c=c*a;}return c;}"""
    val expected = "243"
    assert(checkCode(input, expected, 585))
  }

  test("do while statement: print result of variable after modified with a simple do while stmt") {
    val input = """int x;void main(){x=2;do x=2*(x-1);x=x+1;while(x<10);putIntLn(x);}"""
    val expected = "17"
    assert(checkCode(input, expected, 586))
  }

  test("do while statement: print result of variable after modified with do while stmt that contain another do while stmt") {
    val input = """int x,y;void main(){x=1;y=2;do x=x*3;do y=y*3+2;x=x-1;while(y<15);while(x<14);putIntLn(x);putIntLn(y);}"""
    val expected = "14728"
    assert(checkCode(input, expected, 587))
  }

  test("do while statement: print result of variable after modified with a simple do while stmt inside a block stmt") {
    val input = """int x;void main(){x=2314;{do x=x/2-1;putIntLn(x);while(x>100);}}"""
    val expected = "115657728714270"
    assert(checkCode(input, expected, 588))
  }

  test("break statement: print result of a variable after modified with a simple for statement that contain break stmt") {
    val input = """void main(){int i;for(i=0;i<5;i=i+1){break;} putIntLn(i);}"""
    val expected = "0"
    assert(checkCode(input, expected, 589))
  }

  test("break statement: print result of a variable after modified with a more complex for statement that contain break stmt") {
    val input = """void main(){int i;for(i=0;i<12;i=i+1){if(i>8)break;else if(i<5) i=2*i+1;} putIntLn(i);}"""
    val expected = "9"
    assert(checkCode(input, expected, 590))
  }

  test("break statement: print result of a variable after modified with a simple do while statement that contain break stmt") {
    val input = """void main(){int x;x=1;do x=x+1;if(x%2==0 && x>4)break;while(x<9);putIntLn(x);}"""
    val expected = "6"
    assert(checkCode(input, expected, 591))
  }

  test("break statement: print result of a variable after modified with a more complex do while statement that contain break stmt") {
    val input = """void main(){int x;x=1;do x=x+1;if(x%2==0 && x>4)break;while(x<9);putIntLn(x);}"""
    val expected = "6"
    assert(checkCode(input, expected, 592))
  }

  test("break statement: print result of a variable after modified with a more complex do while statement that contain break stmt inside a block stmt") {
    val input = """void main(){int x;x=1;do x=x+1;if(x%2==0 && x>4){x=x+1;{break;}}while(x<9);putIntLn(x);}"""
    val expected = "7"
    assert(checkCode(input, expected, 593))
  }

  test("continue statement: print result of a variable after modified with a simple for statement that contain continue stmt") {
    val input = """void main(){int x,i;i=0;for(x=1;x<10;x=x+1){if(x%2==0)continue;i=i+1;}putIntLn(i);}"""
    val expected = "5"
    assert(checkCode(input, expected, 594))
  }

  test("continue statement: print result of variable after modified with a more complex for statement that contain continue stmt") {
    val input = """boolean ifBigger(int a,int b){if(a>b)return true; return false;}void main(){int i,a,b;a=3;b=5;for(i=1;i<10;i=i+1){if(ifBigger(a,b))b=2*b+1;else if(a%2==0) continue; else a=a+1;}putIntLn(a);putIntLn(b);}"""
    val expected = "45"
    assert(checkCode(input, expected, 595))
  }

  test("continue statement: print result of variable after modified with a simple do while statement that contain continue stmt") {
    val input = """void main(){int x;x=0;do x=x+1;if(x%2==0)continue;x=2*x;while(x<12);putIntLn(x);}"""
    val expected = "14"
    assert(checkCode(input, expected, 596))
  }

  test("return statement: print the result of return in function that return the result of another function") {
    val input ="""int getDouble(int a){return a*2;}void main(){putIntLn(getMaxDouble(5,2));}int getMaxDouble(int a,int b){if(a>b)return getDouble(a);return getDouble(b);}"""
    val expected = "10"
    assert(checkCode(input, expected, 597))
  }

  test("return statement: print the result of return in function that using binary operation &&") {
    val input ="""boolean ifBiggerAndDivBy2(int a,int b){return (a>b) && (a%2==0);}void main(){putBoolLn(ifBiggerAndDivBy2(5,2));} """
    val expected = "false"
    assert(checkCode(input, expected, 598))
  }

  test("return statement: print the result of return in function that using binary operation ||") {
    val input ="""boolean ifSmallerOrDivBy2(int a,int b){return (a<b) || (a%2==0);}void main(){putBoolLn(ifSmallerOrDivBy2(4,2));} """
    val expected = "true"
    assert(checkCode(input, expected, 599))
  }

  test("return statement: print the result of return in function that using binary operation && and short circuit") {
    val input ="""boolean raiseTrue(){putStringLn("true");return true;} boolean ifBiggerAndSmallerThen10(int a,int b){return (a>b) && (a<10) && raiseTrue();}void main(){putBoolLn(ifBiggerAndSmallerThen10(11,2));} """
    val expected = "false"
    assert(checkCode(input, expected, 600))
  }
}