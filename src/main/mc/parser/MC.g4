grammar MC;


@lexer::header{
	package mc.parser;
}

@lexer::members{
@Override
public Token emit() {
    switch (getType()) {
    case UNCLOSE_STRING:       
        Token result = super.emit();
        // you'll need to define this method
        throw new UncloseString(result.getText());

        
    case ILLEGAL_ESCAPE:
    	result = super.emit();
    	throw new IllegalEscape(result.getText());

    case ERROR_CHAR:
    	result = super.emit();
    	throw new ErrorToken(result.getText());


    default:
        return super.emit();
    }
}
}

@parser::header{
	package mc.parser;
}

options{
	language=Java;
}

program  : globdecl EOF;

//Global decalartion
globdecl: (funcdecl | vardecl)+; //global declaration

//Variable declaration
vardecl: prtype idlist SEMI;
idlist: id (COMMA id)*;
id: ID | arrid;
arrid: ID LSB INTLIT RSB;

//Function declaration
funcdecl: functype ID LB parlist? RB blkstmt;
functype: nortype (LSB RSB)?;
parlist: pardecl (COMMA pardecl)*;
pardecl: prtype (ID|arrptr);
arrptr: ID LSB RSB;

//Function call
callfunction: ID LB (expr(COMMA expr)*)? RB;

//Types
nortype: prtype | VOIDTYPE;
prtype: INTTYPE | BOOLEANTYPE | STRINGTYPE | FLOATTYPE;

//Literals
litlist: INTLIT | TRUE | FALSE | FLOATLIT | STRINGLIT;

//Block statement
blkstmt: LP vardecl* stmt* RP;

//Statements
stmt: ifst | dowhilest | forst | brkst | contst | retst | blkstmt | (expr SEMI);

//If statement
ifst: IF expr stmt (ELSE stmt)?;

//Do while statement
dowhilest: DO stmt* WHILE expr SEMI ;

//For statement
forst: FOR LB expr SEMI expr SEMI expr RB stmt;

//Break statement
brkst: BREAK SEMI;

//Continue statement
contst: CONTINUE SEMI;

//Return statement
retst: RETURN expr? SEMI;

expr: exp1
    | exp2
    | exp3
    | exp4
    | exp5
    | exp6;

exp1: LB  expr  RB
    | callfunction|litlist| ID ;
exp2: exp1
    | exp1 LSB expr RSB ;
exp3: exp2
    | (SUB | NOT) exp3
    | <assoc=left> exp3 (MUL | DIV | MOD) exp3
    | <assoc=left> exp3 (ADD | SUB) exp3;
exp4: exp3
    | exp3 (LT | LOE | GT | GOE) exp3;
exp5: exp4
    | exp4(EQU |NOTEQU) exp4;
exp6: exp5
    | <assoc=left> exp6 AND exp6
    | <assoc=left> exp6 OR exp6
    | <assoc=right> exp6 ASSIGN exp6;

COMMENT: ('//'(~[\n])*|'/*'.*?'*/')->skip ;

//15 keywords
INTTYPE: 'int' ;
VOIDTYPE: 'void' ;
BOOLEANTYPE: 'boolean';
FLOATTYPE: 'float';
STRINGTYPE: 'string';
BREAK: 'break';
CONTINUE: 'continue';
ELSE: 'else';
FOR: 'for';
IF: 'if';
RETURN: 'return';
DO: 'do';
WHILE: 'while';
TRUE: 'true';
FALSE: 'false';

//15 operators:
ADD: '+';
SUB: '-';
MUL: '*';
DIV: '/';
NOT: '!';
MOD: '%';
OR: '||';
AND: '&&';
NOTEQU: '!=';
EQU: '==';
LT: '<';
GT:'>';
LOE:'<=';
GOE:'>=';
ASSIGN:'=';

//5 separators
LSB: '[';

RSB: ']';

LB: '(' ;

RB: ')' ;

LP: '{';

RP: '}';

SEMI: ';' ;

COMMA: ',' ;

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

//4 literals
INTLIT: [0-9]+;
fragment EXPO: [Ee][+-]?INTLIT;
fragment DOT: '.';
FLOATLIT: INTLIT DOT INTLIT? EXPO? | DOT INTLIT EXPO? | INTLIT EXPO;
BOOLIT: TRUE|FALSE;
fragment ESCSENT: '\\'[bfrnt'"\\];
fragment STRCHARS: (ESCSENT|~[\b\f\r\n\t'"\\])*;
STRINGLIT: '"'STRCHARS?'"'{setText(getText().substring(1, getText().length()-1));};

/*EscapeSentences
BS: '\b';
FF: '\f';
CR: '\r';
NL: '\n';
HT: '\t';
SQ: '\'';
DQ: '\"';
BD: '\\';
ESCSenteces: BS| FF | CR | NL | HT | SQ |DQ |BD;
*/

//Identifiers
ID: [a-zA-Z_][a-zA-Z0-9_]* ;

ERROR_CHAR: .;
UNCLOSE_STRING: ('"'STRCHARS?){setText(getText().substring(1, getText().length()));};
ILLEGAL_ESCAPE: ('"'STRCHARS?'\\'~[bfrnt'"\\]){setText(getText().substring(1, getText().length()));};
