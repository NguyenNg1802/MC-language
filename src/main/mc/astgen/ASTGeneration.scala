
package mc.astgen
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.ParserRuleContext
import java.io.{PrintWriter,File}
import org.antlr.v4.runtime.ANTLRFileStream
import mc.utils._
import scala.collection.JavaConverters._
import org.antlr.v4.runtime.tree._
import mc.parser._
import mc.parser.MCParser._

class ASTGeneration extends MCBaseVisitor[Any] {
  /*program  : globdecl EOF;*/
  override def visitProgram(ctx: ProgramContext) ={
    val listDecl=ctx.globdecl().accept(this).asInstanceOf[List[Decl]]
    Program(ctx.globdecl().accept(this).asInstanceOf[List[Decl]])
  }

  /*globdecl: (funcdecl|vardecl)+;*/
  override def visitGlobdecl(ctx: GlobdeclContext)= {
    for(x<-0 to ctx.getChildCount()-1){
      if(ctx.getChild(x)==null){
        null
      }
    }
    val declList:List[List[Any]]=(for (i <- 0 to (ctx.getChildCount() - 1)) yield{
      ctx.getChild(i).accept(this).asInstanceOf[List[Decl]]  //List[Decl],Decl
    }).toList
    declList.flatten
  }

  /*vardecl: prtype idlist SEMI;*/
  override def visitVardecl(ctx: VardeclContext) = {
    val idList:List[List[String]]= ctx.idlist.accept(this).asInstanceOf[List[List[String]]]
    (for(i <- 0 to idList.length-1) yield {
      val idListElement:List[String]=idList(i)
      if(idList(i).length==1){
        VarDecl(Id(idListElement(0)), ctx.prtype.accept(this).asInstanceOf[Type])
      }
      else if(idList(i).length==2){
        VarDecl(Id(idListElement(0)), ArrayType(IntLiteral(idListElement(1).toInt),ctx.prtype.accept(this).asInstanceOf[Type]))
      }
      }).toList
  }

  /*funcdecl: functype ID LB parlist? RB blkstmt;*/
  override def visitFuncdecl(ctx: FuncdeclContext) = {
    if (ctx.getChildCount() == 6) {
      List(FuncDecl(Id(ctx.ID.getText), ctx.parlist.accept(this).asInstanceOf[List[VarDecl]], ctx.functype().accept(this).asInstanceOf[Type], ctx.blkstmt.accept(this).asInstanceOf[Stmt]))
    }
    else if (ctx.getChildCount() == 5) {
      List(FuncDecl(Id(ctx.ID.getText), List(), ctx.functype.accept(this).asInstanceOf[Type], ctx.blkstmt().accept(this).asInstanceOf[Stmt]))
    }
  }

  /*idlist: id (COMMA id)*;*/
  override def visitIdlist(ctx: IdlistContext) = {
    ctx.id().asScala.toList.map(_.accept(this)).asInstanceOf[List[List[String]]]
  }

  /*id: ID | arrid */
  override def visitId(ctx: IdContext) = {
    if (ctx.ID() != null) {
      List(ctx.ID().getText)
    }
    else {
      ctx.arrid().accept(this).asInstanceOf[List[String]]
    }
  }

  /*arrid: ID LSB INTLIT RSB;*/
  override def visitArrid(ctx: ArridContext) = List(ctx.ID.getText,ctx.INTLIT.getText)

  /*parlist: pardecl (COMMA pardecl)*;*/
  override def visitParlist(ctx: ParlistContext) = {
    ctx.pardecl().asScala.toList.map(_.accept(this)).asInstanceOf[List[VarDecl]]
  }

  /*pardecl: prtype (ID|arrptr);*/
  override def visitPardecl(ctx: PardeclContext) = {
    if (ctx.ID != null) {
      VarDecl(Id(ctx.ID.getText), ctx.prtype.accept(this).asInstanceOf[Type])
    }
    else {
      VarDecl(ctx.arrptr.accept(this).asInstanceOf[Id], ArrayPointerType(ctx.prtype.accept(this).asInstanceOf[Type]))
    }
  }

  /*arrptr: ID LSB RSB;*/
  override def visitArrptr(ctx:ArrptrContext)= Id(ctx.ID.getText)

  /*callfunction: ID LB (expr(COMMA expr)*)? RB;*/
  override def visitCallfunction(ctx: CallfunctionContext) = CallExpr(Id(ctx.ID().getText), ctx.expr().asScala.toList.map(_.accept(this)).asInstanceOf[List[Expr]])

  /*functype: nortype (LSB RSB)?*/
  override def visitFunctype(ctx: FunctypeContext) = {
    if(ctx.getChildCount()==1) {
      ctx.nortype.accept(this)
    }
    else{
      ArrayPointerType(ctx.nortype.accept(this).asInstanceOf[Type])
    }
  }

  /*nortype: prtype | VOIDTYPE;*/
  override def visitNortype(ctx: NortypeContext) = {
    if (ctx.prtype != null) {
      ctx.prtype.accept(this)
    }
    else if (ctx.VOIDTYPE !=null){
      VoidType
    }
  }

  /*prtype: INTTYPE | BOOLEANTYPE | STRINGTYPE | FLOATTYPE;*/
  override def visitPrtype(ctx: PrtypeContext) = {
    if (ctx.INTTYPE != null) {
      IntType
    }
    else if (ctx.BOOLEANTYPE != null) {
      BoolType
    }
    else if (ctx.STRINGTYPE != null) {
      StringType
    }
    else if (ctx.FLOATTYPE != null) {
      FloatType
    }
  }

  /*litlist: INTLIT | TRUE | FALSE | FLOATLIT | STRINGLIT;*/
  override def visitLitlist(ctx: LitlistContext) = {
    if (ctx.INTLIT != null) {
      IntLiteral(ctx.INTLIT.getText.toInt)
    }
    else if (ctx.TRUE != null) {
      BooleanLiteral(ctx.TRUE.getText.toBoolean)
    }
    else if (ctx.FALSE != null) {
      BooleanLiteral(ctx.FALSE.getText.toBoolean)
    }
    else if (ctx.FLOATLIT != null) {
      FloatLiteral(ctx.FLOATLIT.getText.toFloat)
    }
    else if (ctx.STRINGLIT != null){
      StringLiteral(ctx.STRINGLIT.getText)
    }
  }

  /*blkstmt: LP vardecl* stmt* RP;*/
  override def visitBlkstmt(ctx: BlkstmtContext)= {
    val dList: List[List[Decl]] = ctx.vardecl().asScala.toList.map(_.accept(this)).asInstanceOf[List[List[Decl]]]
    val sList: List[Stmt] = ctx.stmt().asScala.toList.map(_.accept(this)).asInstanceOf[List[Stmt]]
    Block(dList.flatten, sList)
  }

  /*stmt: ifst | dowhilest | forst | brkst | contst | retst | blkstmt | (expr SEMI);*/
  override def visitStmt(ctx: StmtContext) = {
    if(ctx.getChildCount()==1) {
      ctx.getChild(0).accept(this)
    }
    else if(ctx.getChildCount()==2){
      ctx.expr.accept(this)
    }
  }

  /*ifst: IF expr stmt (ELSE stmt)?;*/
  override def visitIfst(ctx: IfstContext) = {
    if (ctx.getChildCount()==3) {
      If(ctx.expr.accept(this).asInstanceOf[Expr], ctx.stmt(0).accept(this).asInstanceOf[Stmt], None)
    }
    else  if(ctx.getChildCount()==5){
      If(ctx.expr.accept(this).asInstanceOf[Expr], ctx.stmt(0).accept(this).asInstanceOf[Stmt], Some(ctx.stmt(1).accept(this).asInstanceOf[Stmt]))
    }
  }

  /*dowhilest: DO stmt* WHILE expr SEMI ;*/
  override def visitDowhilest(ctx: DowhilestContext) = {
    Dowhile(ctx.stmt().asScala.toList.map(_.accept(this)).asInstanceOf[List[Stmt]], ctx.expr.accept(this).asInstanceOf[Expr])
  }

  /*forst: FOR LB expr SEMI expr SEMI expr RB stmt;*/
  override def visitForst(ctx: ForstContext) = {
    For(ctx.expr(0).accept(this).asInstanceOf[Expr], ctx.expr(1).accept(this).asInstanceOf[Expr], ctx.expr(2).accept(this).asInstanceOf[Expr], ctx.stmt.accept(this).asInstanceOf[Stmt])
  }

  /*brkst: BREAK SEMI;*/
  override def visitBrkst(ctx: BrkstContext) = {
    Break
  }

  /*contst: CONTINUE SEMI;*/
  override def visitContst(ctx: ContstContext) = {
    Continue
  }

  /*retst: RETURN expr? SEMI;*/
  override def visitRetst(ctx: RetstContext) = {
    if(ctx.expr!=null){
      Return(Some(ctx.expr.accept(this).asInstanceOf[Expr]))
    }
    else{
      Return(None)
    }
  }

  /*expr: exp1 | exp2 | exp3 | exp4 | exp5 | exp6;*/
  override def visitExpr(ctx:ExprContext) ={
    ctx.getChild(0).accept(this)
  }

  /*exp1: LB  expr  RB | callfunction | litlist | ID ;*/
  override def visitExp1(ctx:Exp1Context) ={
    if(ctx.getChildCount()==3){
      ctx.expr.accept(this)
    }
    else if(ctx.callfunction!=null){
      ctx.callfunction.accept(this)
    }
    else if(ctx.litlist!=null){
      ctx.litlist.accept(this)
    }
    else if(ctx.ID!=null){
      Id(ctx.ID().getText)
    }
  }

  /*exp2: exp1 | exp1 LSB expr RSB ;*/
  override def visitExp2(ctx:Exp2Context) ={
    if(ctx.getChildCount()==4){
      ArrayCell(ctx.exp1.accept(this).asInstanceOf[Expr],ctx.expr.accept(this).asInstanceOf[Expr])
    }
    else if (ctx.getChildCount()==1){
      ctx.exp1.accept(this)
    }
  }

  /*exp3: exp2 | (SUB | NOT) exp3 | <assoc=left> exp3 (MUL | DIV | MOD) exp3 | <assoc=left> exp3 (ADD | SUB) exp3;*/
  override def visitExp3(ctx:Exp3Context) = {
    if (ctx.getChildCount() == 1) {
      ctx.exp2().accept(this)
    }
    else if (ctx.getChildCount() == 2) {
      if (ctx.SUB != null) {
        UnaryOp(ctx.SUB.getText, ctx.exp3(0).accept(this).asInstanceOf[Expr])
      }
      else {
        UnaryOp(ctx.NOT.getText, ctx.exp3(0).accept(this).asInstanceOf[Expr])
      }
    }
    else {
      if (ctx.MUL != null) {
        BinaryOp(ctx.MUL.getText, ctx.exp3(0).accept(this).asInstanceOf[Expr], ctx.exp3(1).accept(this).asInstanceOf[Expr])
      }
      else if (ctx.DIV != null) {
        BinaryOp(ctx.DIV.getText, ctx.exp3(0).accept(this).asInstanceOf[Expr], ctx.exp3(1).accept(this).asInstanceOf[Expr])
      }
      else if (ctx.MOD != null) {
        BinaryOp(ctx.MOD.getText, ctx.exp3(0).accept(this).asInstanceOf[Expr], ctx.exp3(1).accept(this).asInstanceOf[Expr])
      }
      else if (ctx.ADD != null) {
        BinaryOp(ctx.ADD.getText, ctx.exp3(0).accept(this).asInstanceOf[Expr], ctx.exp3(1).accept(this).asInstanceOf[Expr])
      }
      else if (ctx.SUB != null) {
        BinaryOp(ctx.SUB.getText, ctx.exp3(0).accept(this).asInstanceOf[Expr], ctx.exp3(1).accept(this).asInstanceOf[Expr])
      }
    }
  }

  /*exp4: exp3 | exp3 (LT |LOE | GT | GOE) exp3;*/
  override def visitExp4(ctx:Exp4Context) ={
    if(ctx.getChildCount==1){
      ctx.exp3(0).accept(this)
    }
    else{
      if (ctx.LT != null) {
        BinaryOp(ctx.LT.getText, ctx.exp3(0).accept(this).asInstanceOf[Expr], ctx.exp3(1).accept(this).asInstanceOf[Expr])
      }
      else if (ctx.LOE != null) {
        BinaryOp(ctx.LOE.getText, ctx.exp3(0).accept(this).asInstanceOf[Expr], ctx.exp3(1).accept(this).asInstanceOf[Expr])
      }
      else if (ctx.GT != null) {
        BinaryOp(ctx.GT.getText, ctx.exp3(0).accept(this).asInstanceOf[Expr], ctx.exp3(1).accept(this).asInstanceOf[Expr])
      }
      else{
        BinaryOp(ctx.GOE.getText, ctx.exp3(0).accept(this).asInstanceOf[Expr], ctx.exp3(1).accept(this).asInstanceOf[Expr])
      }
    }
  }

  /*exp5: exp4 | exp4 (EQU |NOTEQU) exp4;*/
  override def visitExp5(ctx:Exp5Context) ={
    if(ctx.getChildCount()==1){
      ctx.exp4(0).accept(this)
    }
    else{
      if (ctx.EQU != null) {
        BinaryOp(ctx.EQU.getText, ctx.exp4(0).accept(this).asInstanceOf[Expr], ctx.exp4(1).accept(this).asInstanceOf[Expr])
      }
      else{
        BinaryOp(ctx.NOTEQU.getText, ctx.exp4(0).accept(this).asInstanceOf[Expr], ctx.exp4(1).accept(this).asInstanceOf[Expr])
      }
    }
  }

  /*exp6: exp5 | <assoc=left> exp6 AND exp6 | <assoc=left> exp6 OR exp6 | <assoc=right> exp6 ASSIGN exp6;*/
  override def visitExp6(ctx:Exp6Context) ={
    if(ctx.getChildCount()==1){
      ctx.exp5.accept(this)
    }
    else{
      if (ctx.AND != null) {
        BinaryOp(ctx.AND.getText, ctx.exp6(0).accept(this).asInstanceOf[Expr], ctx.exp6(1).accept(this).asInstanceOf[Expr])
      }
      else if (ctx.OR != null) {
        BinaryOp(ctx.OR.getText, ctx.exp6(0).accept(this).asInstanceOf[Expr], ctx.exp6(1).accept(this).asInstanceOf[Expr])
      }
      else{
        BinaryOp(ctx.ASSIGN.getText, ctx.exp6(0).accept(this).asInstanceOf[Expr], ctx.exp6(1).accept(this).asInstanceOf[Expr])
      }
    }
  }
}


