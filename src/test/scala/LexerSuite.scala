import org.scalatest.FunSuite


class LexerSuite extends FunSuite with TestLexer {

  //Comments
  test("simple line comment 1"){
    val input="//abc"
    val expect="<EOF>"
    assert(checkLex(input,expect,1))
  }

  test("simple line comment 2"){
    val input="ab//cde"
    val expect="ab,<EOF>"
    assert(checkLex(input,expect,2))
  }

  test("line comment and an identifier in new line"){
    val input="""//abc
    def"""
    val expect="def,<EOF>"
    assert(checkLex(input,expect,3))
  }

  test("traditional block comment 1"){
    val input="/*abc*/"
    val expect="<EOF>"
    assert(checkLex(input,expect,4))
  }

  test("traditional block comment 2"){
    val input="abc/*cde*/"
    val expect="abc,<EOF>"
    assert(checkLex(input,expect,5))
  }

  test("traditional block comment with a new line inside"){
    val input="""/*abc
    def*/"""
    val expect="<EOF>"
    assert(checkLex(input,expect,6))
  }

  //Identifiers
  test("identifier 1") {
    val input = "abc"
    val expect = "abc,<EOF>"
    assert(checkLex(input,expect,7))
  }

  test("identifier 2"){
    val input="Aa"
    val expect="Aa,<EOF>"
    assert(checkLex(input,expect,8))
  }

  test("identifier 3"){
    val input="_a"
    val expect="_a,<EOF>"
    assert(checkLex(input,expect,9))
  }

  test("identifier 4"){
    val input="__"
    val expect="__,<EOF>"
    assert(checkLex(input,expect,10))
  }

  test("identifier 5"){
    val input="a12"
    val expect="a12,<EOF>"
    assert(checkLex(input,expect,11))
  }

  test("identifier 6"){
    val input="_39"
    val expect="_39,<EOF>"
    assert(checkLex(input,expect,12))
  }

  //Keywords
  test("key word void"){
    val input="void f()"
    val expect="void,f,(,),<EOF>"
    assert(checkLex(input,expect,13))
  }

  test("key word boolean"){
    val input="boolean ifTrue"
    val expect="boolean,ifTrue,<EOF>"
    assert(checkLex(input,expect,14))
  }

  test("key word int"){
    val input="int x"
    val expect="int,x,<EOF>"
    assert(checkLex(input,expect,15))
  }

  test("key word float"){
    val input="float x"
    val expect="float,x,<EOF>"
    assert(checkLex(input,expect,16))
  }

  test("key word string"){
    val input="string str"
    val expect="string,str,<EOF>"
    assert(checkLex(input,expect,17))
  }

  test("key word true"){
    val input="ifTrue=true"
    val expect="ifTrue,=,true,<EOF>"
    assert(checkLex(input,expect,18))
  }

  test("key word false"){
    val input="ifTrue=false"
    val expect="ifTrue,=,false,<EOF>"
    assert(checkLex(input,expect,19))
  }

  test("key word break"){
    val input="break"
    val expect="break,<EOF>"
    assert(checkLex(input,expect,20))
  }

  test("key word continue"){
    val input="continue"
    val expect="continue,<EOF>"
    assert(checkLex(input,expect,21))
  }

  test("key word if"){
    val input="if(x<y)"
    val expect="if,(,x,<,y,),<EOF>"
    assert(checkLex(input,expect,22))
  }

  test("key word else"){
    val input="else{ }"
    val expect="else,{,},<EOF>"
    assert(checkLex(input,expect,23))
  }

  test("key word for"){
    val input="for"
    val expect="for,<EOF>"
    assert(checkLex(input,expect,24))
  }

  test("key word while"){
    val input="while(x<y)"
    val expect="while,(,x,<,y,),<EOF>"
    assert(checkLex(input,expect,25))
  }

  test("key word do"){
    val input="do{ }"
    val expect="do,{,},<EOF>"
    assert(checkLex(input,expect,26))
  }

  test("keyword return"){
    val input="return 0;"
    val expect="return,0,;,<EOF>"
    assert(checkLex(input,expect,27))
  }

  test("keywords and other tokens 1"){
    val input="if(x==1){return 0;}"
    val expect="if,(,x,==,1,),{,return,0,;,},<EOF>"
    assert(checkLex(input,expect,28))
  }

  test("keywords and other tokens 2"){
    val input="while(x>1){x=x-1;}"
    val expect="while,(,x,>,1,),{,x,=,x,-,1,;,},<EOF>"
    assert(checkLex(input,expect,29))
  }

  //Operators
  test("add operator"){
    val input="1+1=2"
    val expect="1,+,1,=,2,<EOF>"
    assert(checkLex(input,expect,30))
  }

  test("sub operator"){
    val input="2-1=1"
    val expect="2,-,1,=,1,<EOF>"
    assert(checkLex(input,expect,31))
  }

  test("multiply operator"){
    val input="3*4=12"
    val expect="3,*,4,=,12,<EOF>"
    assert(checkLex(input,expect,32))
  }

  test("divide operator"){
    val input="12/4=3"
    val expect="12,/,4,=,3,<EOF>"
    assert(checkLex(input,expect,33))
  }

  test("modulus operator"){
    val input="4%3=1"
    val expect="4,%,3,=,1,<EOF>"
    assert(checkLex(input,expect,34))
  }

  test("not operator"){
    val input="!false=true"
    val expect="!,false,=,true,<EOF>"
    assert(checkLex(input,expect,35))
  }

  test("or operator"){
    val input="x||y"
    val expect="x,||,y,<EOF>"
    assert(checkLex(input,expect,36))
  }

  test("and operator"){
    val input="x&&y"
    val expect="x,&&,y,<EOF>"
    assert(checkLex(input,expect,37))
  }

  test("equal operator"){
    val input="if(x==y)"
    val expect="if,(,x,==,y,),<EOF>"
    assert(checkLex(input,expect,38))
  }

  test("not equal operator"){
    val input="if(x!=y)"
    val expect="if,(,x,!=,y,),<EOF>"
    assert(checkLex(input,expect,39))
  }

  test("less than operator"){
    val input="3<4"
    val expect="3,<,4,<EOF>"
    assert(checkLex(input,expect,40))
  }

  test("greater than operator"){
    val input="4>3"
    val expect="4,>,3,<EOF>"
    assert(checkLex(input,expect,41))
  }

  test("less than or equal operator"){
    val input="x<=y"
    val expect="x,<=,y,<EOF>"
    assert(checkLex(input,expect,42))
  }

  test("greater than or equal operator"){
    val input="x>=y"
    val expect="x,>=,y,<EOF>"
    assert(checkLex(input,expect,43))
  }

  test("assign operator"){
    val input="x=y"
    val expect="x,=,y,<EOF>"
    assert(checkLex(input,expect,44))
  }

  test("several operators 1"){
    val input="x+y-z*3"
    val expect="x,+,y,-,z,*,3,<EOF>"
    assert(checkLex(input,expect,45))
  }

  test("several operators 2"){
    val input="x/2+1=4"
    val expect="x,/,2,+,1,=,4,<EOF>"
    assert(checkLex(input,expect,46))
  }

  test("several operators 3"){
    val input="x>3&&y<2"
    val expect="x,>,3,&&,y,<,2,<EOF>"
    assert(checkLex(input,expect,47))
  }

  test("operators and other tokens 1"){
    val input="if(x>3)return 0;"
    val expect="if,(,x,>,3,),return,0,;,<EOF>"
    assert(checkLex(input,expect,48))
  }

  test("operators and and other tokens 2"){
    val input="while(ifTrue=true)x=x+1;"
    val expect="while,(,ifTrue,=,true,),x,=,x,+,1,;,<EOF>"
    assert(checkLex(input,expect,49))
  }

  test("operators and other tokens 3"){
    val input="if(ifTrue=true)break;"
    val expect="if,(,ifTrue,=,true,),break,;,<EOF>"
    assert(checkLex(input,expect,50))
  }

  test("operators and and other tokens 4"){
    val input="if(x==true||y==true)x=x+y;"
    val expect="if,(,x,==,true,||,y,==,true,),x,=,x,+,y,;,<EOF>"
    assert(checkLex(input,expect,51))
  }

  //Separators
  test("open and close brackets"){
    val input="(abc)"
    val expect="(,abc,),<EOF>"
    assert(checkLex(input,expect,52))
  }

  test("open and close square brackets"){
    val input="[abc]"
    val expect="[,abc,],<EOF>"
    assert(checkLex(input,expect,53))
  }

  test("open and close parentheses"){
    val input = "} int main {"
    val expect = """},int,main,{,<EOF>"""
    assert(checkLex(input,expect,54))
  }

  test("semicolon"){
    val input="abc;"
    val expect="abc,;,<EOF>"
    assert(checkLex(input,expect,55))
  }

  test("comma"){
    val input="a,b"
    val expect="a,,,b,<EOF>"
    assert(checkLex(input,expect,56))
  }

  test("seperators and other tokens 1"){
    val input="int a=1;int b=a;"
    val expect="int,a,=,1,;,int,b,=,a,;,<EOF>"
    assert(checkLex(input,expect,57))
  }

  test("seperators and other tokens 2"){
    val input="int a=1;int b=a;"
    val expect="int,a,=,1,;,int,b,=,a,;,<EOF>"
    assert(checkLex(input,expect,58))
  }

  //Integer
  test("simple integer"){
    val input="123"
    val expect="123,<EOF>"
    assert(checkLex(input,expect,59))
  }

  //Float
  test("float literal 1"){
    val input="12.3"
    val expect="12.3,<EOF>"
    assert(checkLex(input,expect,60))
  }

  test("float literal 2"){
    val input="4."
    val expect="4.,<EOF>"
    assert(checkLex(input,expect,61))
  }

  test("float literal 3"){
    val input=".8"
    val expect=".8,<EOF>"
    assert(checkLex(input,expect,62))
  }

  test("float literal 4"){
    val input="12e4"
    val expect="12e4,<EOF>"
    assert(checkLex(input,expect,63))
  }

  test("float literal 5"){
    val input="4e-2"
    val expect="4e-2,<EOF>"
    assert(checkLex(input,expect,64))
  }

  test("float literal 6"){
    val input="1.2e3"
    val expect="1.2e3,<EOF>"
    assert(checkLex(input,expect,65))
  }

  test("float literal 7"){
    val input="2.e5"
    val expect="2.e5,<EOF>"
    assert(checkLex(input,expect,66))
  }

  test("float literal 8"){
    val input="4.e-2"
    val expect="4.e-2,<EOF>"
    assert(checkLex(input,expect,67))
  }

  test("float literal 9"){
    val input="1.5e+6"
    val expect="1.5e+6,<EOF>"
    assert(checkLex(input,expect,68))
  }

  test("float literal 10"){
    val input="6E2"
    val expect="6E2,<EOF>"
    assert(checkLex(input,expect,69))
  }

  test("float literal 11"){
    val input="2E-3"
    val expect="2E-3,<EOF>"
    assert(checkLex(input,expect,70))
  }

  test("float literal 12"){
    val input="4.E1"
    val expect="4.E1,<EOF>"
    assert(checkLex(input,expect,71))
  }

  test("float literal 13"){
    val input="1.2E3"
    val expect="1.2E3,<EOF>"
    assert(checkLex(input,expect,72))
  }

  test("float literal 15"){
    val input="3.E-4"
    val expect="3.E-4,<EOF>"
    assert(checkLex(input,expect,73))
  }

  test("float literal 16"){
    val input="9.2E+4"
    val expect="9.2E+4,<EOF>"
    assert(checkLex(input,expect,74))
  }

  test("simple string 1"){
    val input="\"abc\""
    val expect="abc,<EOF>"
    assert(checkLex(input,expect,75))
  }

  test("simple string 2"){
    val input="\"123\""
    val expect="123,<EOF>"
    assert(checkLex(input,expect,76))
  }

  test("several strings"){
    val input="\"abc\"\"123\""
    val expect="abc,123,<EOF>"
    assert(checkLex(input,expect,77))
  }

  test("backspace in string"){
    val input=""""abc\b""""
    val expect="""abc\b,<EOF>"""
    assert(checkLex(input,expect,78))
  }

  test("horizontal tab in string"){
    val input=""""abc\t""""
    val expect="""abc\t,<EOF>"""
    assert(checkLex(input,expect,79))
  }

  test("formfeed in string"){
    val input=""""abc\f""""
    val expect="""abc\f,<EOF>"""
    assert(checkLex(input,expect,80))
  }

  test("backslash in string"){
    val input=""""abc\\""""
    val expect="""abc\\,<EOF>"""
    assert(checkLex(input,expect,81))
  }

  test("newline in string"){
    val input=""""abc\n""""
    val expect="""abc\n,<EOF>"""
    assert(checkLex(input,expect,82))
  }

  test("single quote in string"){
    val input=""""abc\'""""
    val expect="""abc\',<EOF>"""
    assert(checkLex(input,expect,83))
  }

  test("double quote in string"){
    val input=""""abc\"""""
    val expect="""abc\",<EOF>"""
    assert(checkLex(input,expect,84))
  }

  test("error token 1"){
    val input="$"
    val expect="ErrorToken $"
    assert(checkLex(input,expect,85))
  }

  test("error token 2"){
    val input="x^"
    val expect="x,ErrorToken ^"
    assert(checkLex(input,expect,86))
  }

  test("error token 3"){
    val input="x1@"
    val expect="x1,ErrorToken @"
    assert(checkLex(input,expect,87))
  }

  test("error token 4"){
    val input="#"
    val expect="ErrorToken #"
    assert(checkLex(input,expect,88))
  }

  test("error token 5"){
    val input="~"
    val expect="ErrorToken ~"
    assert(checkLex(input,expect,89))
  }

  test("error token 6"){
    val input="|"
    val expect="ErrorToken |"
    assert(checkLex(input,expect,90))
  }

  test("error token 7"){
    val input="&"
    val expect="ErrorToken &"
    assert(checkLex(input,expect,91))
  }

  test("error token 8"){
    val input=":"
    val expect="ErrorToken :"
    assert(checkLex(input,expect,92))
  }

  test("unclosed string 1"){
    val input="\"abc"
    val expect="Unclosed string: abc"
    assert(checkLex(input,expect,93))
  }

  test("unclosed string 2"){
    val input="123\"cde"
    val expect="123,Unclosed string: cde"
    assert(checkLex(input,expect,94))
  }

  test("unclosed string 3"){
    val input="\"abc\"\"cde"
    val expect="abc,Unclosed string: cde"
    assert(checkLex(input,expect,95))
  }

  test("unclosed string 4"){
    val input=""""abc'"""
    val expect="""Unclosed string: abc"""
    assert(checkLex(input,expect,96))
  }

  test("unclosed string 5"){
    val input=""""abc
              """"
    val expect="""Unclosed string: abc"""
    assert(checkLex(input,expect,97))
  }

  test("illegal escape in string 1"){
    val input=""""abc\a""""
    val expect="""Illegal escape in string: abc\a"""
    assert(checkLex(input,expect,98))
  }

  test("illegal escape in string 2"){
    val input=""""abc""def\k""""
    val expect="""abc,Illegal escape in string: def\k"""
    assert(checkLex(input,expect,99))
  }

  test("illegal escape in string 3"){
    val input=""""abc\g""def""""
    val expect="""Illegal escape in string: abc\g"""
    assert(checkLex(input,expect,100))
  }
}

