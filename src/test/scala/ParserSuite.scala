import org.scalatest.FunSuite

class ParserSuite  extends FunSuite with TestParser {

  test("a simple program") {
    val input = "int main () {}"
    val expect = "sucessful"
    assert(checkRec(input,expect,101))
  }

  test("more complex program") {
    val input ="""int main () {
     putIntLn(4);
    }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,102))
  }

  test("wrong program"){
    val input = "} int main {"
    val expect = "Error on line 1 col 1: }"
    assert(checkRec(input,expect,103))
  }

  test("line comment 1"){
    val input = """//comment
    int main () {
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,104))
  }

  test("line comment 2"){
    val input = """int main () {
        //comment
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,105))
  }

  test("block comment 1"){
    val input = """/* comment */
    int main () {
        //comment
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,106))
  }

  test("block comment 2"){
    val input = """/*com
    ment */
    int main () {
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,107))
  }

  test("block comment 3"){
    val input = """int main () {
        /*comment*/
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,108))
  }

  test("global variable declaration 1"){
    val input ="""int x;
    int main () {
    }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,109))
  }

  test("global variable declaration 2"){
    val input ="""boolean x,y;
    int main () {
    }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,110))
  }

  test("global variable declaration 3"){
    val input ="""int x,y,z[3];
    int main () {
    }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,111))
  }

  test("function declaration 1"){
    val input ="""void func(){ return;}
    int main () {
    }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,112))
  }

  test("function declaration 2"){
    val input ="""int func(){ return 1;}
    int main () {
    }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,113))
  }

  test("function declaration 3"){
    val input ="""int func(int a, int b){ return a+b;}
    int main () {
    }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,114))
  }

  test("function declaration 4"){
    val input ="""float[] func(){ int a;a=1;return a+1;}
    int main () {
    }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,115))
  }

  test("function declaration 5"){
    val input ="""void func(int a[]){return a[1];}
    int main () {
    }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,116))
  }

  test("local variable declaration 1"){
    val input="""int main () {
        int x;
    }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,117))
  }

  test("local declaration 2"){
    val input="""int main () {
        float x,y;
    }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,118))
  }

  test("local declaration 3"){
    val input="""int main () {
        string x,y;
        boolean z[3];
    }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,119))
  }

  test("local declaration 4"){
    val input="""int main () {
        float x;
        x=2;
        if(x>0){
            int y;
            y=x+1;
        }
    }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,120))
  }

  test("variable initialization 1"){
    val input ="""int x;
    int main () {
        x=1;
    }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,121))
  }

  test("variable initialization 2"){
    val input ="""float x[3];
    int main () {
        x[0]=1.1;
        x[1]=1.2e3;
    }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,122))
  }

  test("variable initialization 3"){
    val input ="""string x,y;
    int main () {
        x="abc";
        y=x+"d";
    }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,123))
  }

  test("variable initialization 4"){
    val input ="""boolean x,y;
    int main () {
        x=true;
        if(!x==false){
            y=x;
        }
    }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,124))
  }

  test("call function 1"){
    val input ="""int func(){ return 1;}
    int main () {
        int x;
        x=func();
    }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,125))
  }

  test("call function 2"){
    val input ="""int x;
    int func(int a){ return a+1;}
    int main () {
        x=0;
        x=func(x);
    }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,126))
  }

  test("call function 3"){
    val input ="""int func0(){ return 1;}
    int func(int a){ return a+1;}
    int main () {
        x=0;
        x=func(func0());
    }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,127))
  }

  test("if else statement 1") {
    val input ="""int main () {
        if(x==2) x=1;
    }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,128))
  }

  test("if else statement 2"){
    val input = """int main () {
        if(x==true) x=x+1;y=false;
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,129))
  }

  test("if else statement 3"){
    val input = """int main () {
        if(x>3 && y<2){x=3;y=2;}
        else{x=0;}
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,130))
  }

  test("if else statement 4"){
    val input = """boolean x,y;
    int main () {
        x=true;y=false;
        if(x==true || y==true){x=false;y=false;}
        else x=false;
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,131))
  }

  test("do while statement 1"){
    val input = """int x;
    int main () {
        x=0;
        do{x=x+1;}
        while(x<2);
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,132))
  }

  test("do while statement 2"){
    val input = """int x,y;
    int main () {
        x=0;y=0;
        do{x=x+1;y=y-1;}
        while(x<2 && y>3);
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,133))
  }

  test("do while statement 3"){
    val input = """int x,y;
    int main () {
        x=0;y=3;
        do{
            do{x=x+1;}
            while(x<y);
            y=y+1;
        }
        while(x<10);
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,134))
  }

  test("for statement 1"){
    val input = """int main () {
        int i;
        for(i=0;i<=3;i=i+1){
            //do something
        }
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,135))
  }

  test("for statement 2"){
    val input = """int main () {
        int i,x;
        for(i=0;i<=3;i=i+1){
            x=x+1;
            for(i=0;i<=3;i=i+1){
                x=x*2;
            }
        }
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,136))
  }

  test("break statement"){
    val input = """int x;
    int main () {
        x=0;
        do{x=x+1;break;}
        while(x<2);
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,137))
  }

  test("continue statement"){
    val input = """int x;
    int main () {
        x=-1;
        do{
            x=x+1;
            continue;
            x=5;
        }
        while(x<2);
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,138))
  }

  test("return statement 1"){
    val input = """int main () {
        return 0;
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,139))
  }

  test("return statement 2"){
    val input = """int Sum(int a,int b){return a+b;}
    int main () {
        return 0;
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,140))
  }

  test("return statement 3"){
    val input = """int a;
    void Add(){a=a+1;return;}
    int main () {
        Add();
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,141))
  }

  test("expression statement 1"){
    val input = """int a,b;
    int main () {
        a=0;
        a=a+1;
        b=a*2;
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,142))
  }

  test("expression statement 2"){
    val input = """boolean a,b;
    int main () {
        a=true;
        b=!a;
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,143))
  }

  test("expression statement 3"){
    val input = """int a,b;
    int main () {
        a=4;
        b=(a%3)*2;
        a=-b;
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,144))
  }

  test("expression statement 4"){
    val input = """int a,b;
    int main () {
        a=1;
        if(a>0 || a!=-2){
            b=a*3/2;
            a=b-4;
        }
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,145))
  }

  test("expression statement 5"){
    val input = """int a,b[3];
    int main () {
        a=1;
        do{
            a=a+(4-2)/2;
            b[a-1]=a*3/2;
        }
        while(a<3 && b>0);
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,146))
  }

  test("expression statement 6"){
    val input = """int a[5],b[3];
    int main () {
        b[3]=3;
        a[b[3]-1]=4;
        a[0]=a[b[3]-b[a[1]*2]];
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,147))
  }

  test("expression statement 7"){
    val input = """int func(int x){return x+1;}
    int main () {
        int x;
        x=1;
        x=3*func(x)-4;
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,148))
  }

  test("expression statement 8"){
    val input = """int func(int x){return x+1;}
    int main () {
        int x,y;
        x=y=1;
        x=3-2*5=y;
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,149))
  }

  test("operation precedence 1"){
    val input = """int main () {
        int x;
        if(x-2*3>=2){
            x=x-1;
        }
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,150))
  }

  test("operation precedence 2"){
    val input = """int main () {
        int x;
        if(x>=2*3+5 || x<0){
            x=-x+5%2;
        }
        else{
            x=4-3*x+5/2;
        }
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,151))
  }

  test("operation precedence 3"){
    val input = """int main () {
        float x;
        x=x-4.1*2+1*5;
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,152))
  }

  test("operation association 1"){
    val input = """int main () {
        int x;
        x>3==2;
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,153))
  }

  test("operation association 2"){
    val input = """int main () {
        int x;
        x=4>2+5;
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,154))
  }

  test("several statements 1"){
    val input = """int x;
    int main () {
        x=-1;
        do{
            if(x==1){continue;}
            else{x=x+1;}
            x=x+1;
        }
        while(x<2);
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,155))
  }

  test("several statements 2"){
    val input = """int fibo(int a){
        if(a==0){
            return 0;
        }
        else{
            if(a==1){
                return 1;
            }
            else{
                return fibo(a-2)+fibo(a-1);
            }
        }
    }
    int main () {
        return fibo(5);
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,156))
  }

  test("several statements 3"){
    val input = """int main () {
        int a,b,i;
        a=14;
        b=2;
        if(a>=100){
            return a;
        }
        else{
            for(i=0;i>-1;i=i+1){
                if(a>=100){
                    a=a+b*i;
                }
                else{
                    break;
                }
            }
        }
        return a;
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,157))
  }

  test("block comment error"){
    val input = """/* comment
    int main () {
    }"""
    val expect = "Error on line 1 col 1: /"
    assert(checkRec(input,expect,158))
  }

  test("no semicolon error 1"){
    val input ="""int x
    int main () {
    }"""
    val expect ="Error on line 2 col 5: int"
    assert(checkRec(input,expect,159))
  }

  test("no semicolon error 2"){
    val input ="""int main () {
        boolean x
    }
    }"""
    val expect ="Error on line 3 col 5: }"
    assert(checkRec(input,expect,160))
  }

  test("declaration error 1"){
    val input ="""intt x;
    int main () {
    }"""
    val expect ="Error on line 1 col 1: intt"
    assert(checkRec(input,expect,161))
  }

  test("declaration error 2"){
    val input ="""int 2x;
    int main () {
    }"""
    val expect ="Error on line 1 col 5: 2"
    assert(checkRec(input,expect,162))
  }

  test("declaration error 3"){
    val input ="""int x,int y;
    int main () {
    }"""
    val expect ="Error on line 1 col 7: int"
    assert(checkRec(input,expect,163))
  }

  test("declaration error 4"){
    val input ="""int x[];
    int main () {
    }"""
    val expect ="Error on line 1 col 7: ]"
    assert(checkRec(input,expect,164))
  }

  test("declaration error 5"){
    val input ="""int x=0;
    int main () {
    }"""
    val expect ="Error on line 1 col 6: ="
    assert(checkRec(input,expect,165))
  }

  test("declaration error 6"){
    val input ="""int x;
    int main () {
        x=0;
        int y;
    }"""
    val expect ="Error on line 4 col 9: int"
    assert(checkRec(input,expect,166))
  }

  test("declaration error 7"){
    val input ="""int main () {
        int Add(int x){
            return x+1;
        }
    }"""
    val expect ="Error on line 2 col 16: ("
    assert(checkRec(input,expect,167))
  }

  test("initialization error 1"){
    val input ="""int x;
    int main () {
        x=1a5;
    }"""
    val expect ="Error on line 3 col 12: a5"
    assert(checkRec(input,expect,168))
  }

  test("initialization error 2"){
    val input ="""int x;
    x=1;
    int main () {
       x=x+1;
    }"""
    val expect ="Error on line 2 col 5: x"
    assert(checkRec(input,expect,169))
  }

  test("function parameter error 1"){
    val input ="""void func(int a[2]){ return;}
    int main () {
    }"""
    val expect ="Error on line 1 col 17: 2"
    assert(checkRec(input,expect,170))
  }

  test("function parameter error 2"){
    val input ="""void func(int a,b){ return;}
    int main () {
    }"""
    val expect ="Error on line 1 col 17: b"
    assert(checkRec(input,expect,171))
  }

  test("if else statement error 1"){
    val input ="""int x;
    int main () {
        if(){
            x=1;
        }
    }"""
    val expect ="Error on line 3 col 12: )"
    assert(checkRec(input,expect,172))
  }

  test("if else statement error 3"){
    val input ="""int x;
    int main () {
        else{
            x=1;
        }
    }"""
    val expect ="Error on line 3 col 9: else"
    assert(checkRec(input,expect,173))
  }

  test("do while statement error 1"){
    val input ="""int x;
     int main () {
         x=1;
         do{
             x=x+1;
         }
     }"""
    val expect ="""Error on line 7 col 6: }"""
    assert(checkRec(input,expect,174))
  }

  test("do while statement error 2"){
    val input ="""int x;
    int main () {
        x=1;
        do{
            x=x+1;
        }
        while();
    }"""
    val expect ="Error on line 7 col 15: )"
    assert(checkRec(input,expect,175))
  }

  test("do while statement error 3"){
    val input ="""int x;
    int main () {
        x=1;
        while(x>1);
    }"""
    val expect ="Error on line 4 col 9: while"
    assert(checkRec(input,expect,176))
  }

  test("for statement error"){
    val input ="""int main () {
    for( ; ; ){

    }
    }"""
    val expect ="Error on line 2 col 10: ;"
    assert(checkRec(input,expect,177))
  }

  test("return statement error 1"){
    val input ="""int x;
    int main () {
        return int;
    }"""
    val expect ="Error on line 3 col 16: int"
    assert(checkRec(input,expect,178))
  }

  test("return statement error 2"){
    val input ="""int x;
    int main () {
        return continue;
    }"""
    val expect ="Error on line 3 col 16: continue"
    assert(checkRec(input,expect,179))
  }

  test("expression error 1"){
    val input ="""int main (){
        int x;
        x=1+;
    }"""
    val expect ="Error on line 3 col 13: ;"
    assert(checkRec(input,expect,180))
  }

  test("expression error 2"){
    val input ="""int main (){
        int x;
        x=3-1*;
    }"""
    val expect ="Error on line 3 col 15: ;"
    assert(checkRec(input,expect,181))
  }

  test("expression error 3"){
    val input ="""int main (){
        int x;
        x=5*+4;
    }"""
    val expect ="Error on line 3 col 13: +"
    assert(checkRec(input,expect,182))
  }

  test("expression error 4"){
    val input ="""int main (){
        int x;
        x-1/2=3-;
    }"""
    val expect ="""Error on line 3 col 16: -"""
    assert(checkRec(input,expect,183))
  }

  test("expression error 5"){
    val input ="""int main (){
        int x;
        x!=2a;
    }"""
    val expect ="""Error on line 3 col 13: a"""
    assert(checkRec(input,expect,184))
  }

  test("expression error 6"){
    val input ="""int main (){
        int x;
        x-[3]=2;
    }"""
    val expect ="Error on line 3 col 11: ["
    assert(checkRec(input,expect,185))
  }

  test("expression error 7"){
    val input ="""int main (){
        int x;
        =x+2;
    }"""
    val expect ="Error on line 3 col 9: ="
    assert(checkRec(input,expect,186))
  }

  test("expression error 8"){
    val input ="""int main (){
        int x;
        if(x>2=+4){
            x=x+1;
        }
    }"""
    val expect ="Error on line 3 col 16: +"
    assert(checkRec(input,expect,187))
  }

  test("expression error 9"){
    val input ="""int main (){
        int x;
        x+=3;
    }"""
    val expect ="Error on line 3 col 11: ="
    assert(checkRec(input,expect,188))
  }

  test("expression error 10"){
    val input ="""int main (){
        int x;
        <x=5;
    }"""
    val expect ="Error on line 3 col 9: <"
    assert(checkRec(input,expect,189))
  }

  test("expression error 11"){
    val input ="""int main (){
        a>b>c=1;
    }"""
    val expect ="Error on line 2 col 12: >"
    assert(checkRec(input,expect,190))
  }

  test("expression error 12"){
    val input ="""int main (){
        a>b+c<d;
    }"""
    val expect ="Error on line 2 col 14: <"
    assert(checkRec(input,expect,191))
  }

  test("expression error 13"){
    val input ="""int main (){
        a==b-c==d;
    }"""
    val expect ="Error on line 2 col 15: =="
    assert(checkRec(input,expect,192))
  }

  test("expression error 14"){
    val input ="""int main (){
        a>b*d*e<=c;
    }"""
    val expect ="Error on line 2 col 16: <="
    assert(checkRec(input,expect,193))
  }

  test("expression error 15"){
    val input ="""int main (){
        a[a>=b<c]];
    }"""
    val expect ="Error on line 2 col 15: <"
    assert(checkRec(input,expect,194))
  }

  test("expression error 16"){
    val input ="""int main (){
        int a,b,c,d;
        a[c](*)b[d];
    }"""
    val expect ="Error on line 3 col 13: ("
    assert(checkRec(input,expect,195))
  }

  test("expression error 17"){
    val input ="""int main (){
        int a,b,c;
        a==b+c!=d;
    }"""
    val expect ="Error on line 3 col 15: !="
    assert(checkRec(input,expect,196))
  }

  test("expression error 18"){
    val input ="""int main (){
        1==2>3!=4;
    }"""
    val expect ="Error on line 2 col 15: !="
    assert(checkRec(input,expect,197))
  }

  test("expression error 19"){
    val input ="""int main (){
        int a,b,c;
        if(a>b&&a<c>d){
           a=b;
        }
     }"""
    val expect ="Error on line 3 col 20: >"
    assert(checkRec(input,expect,198))
  }

  test("expression error 20"){
    val input ="""int main (){
        int a,b[c],d;
        if(a==b[c]==d){
            b[c]=1;
        }
     }"""
    val expect ="Error on line 2 col 17: c"
    assert(checkRec(input,expect,199))
  }

  test("expression error 21"){
    val input ="""int main (){
         int a,b,c,d,e;
         if(a<b*c+d>e){
             a=b;
         }
     }"""
    val expect ="Error on line 3 col 20: >"
    assert(checkRec(input,expect,200))
  }
}


