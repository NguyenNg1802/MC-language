package mc.checker


import mc.parser._
import mc.utils._
import java.io.{File, PrintWriter}

import jdk.nashorn.internal.codegen.types.BooleanType

//import mc.codegen.Val
import org.antlr.v4.runtime.ANTLRFileStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree._

import scala.collection.JavaConverters._

case class Symbol(name:String,typ:Type,val valol: Val)
trait Val
case class FunctionType(input:List[Type],output:Type) extends Type

class StaticChecker(ast:AST) extends BaseVisitor with Utils {

  def check() = visit(ast,null)

  def getName(a:Any):String={
    a match{
      case VarDecl(Id(x),_)=> x
      case FuncDecl(Id(x),_,_,_)=> x
      case Symbol(name,_,_)=> name
    }
  }

  def getDeclType(decl:Decl):Type={
    decl match{
      case VarDecl(_,dType)=> dType
      case FuncDecl(_,parDeclList,rType,_)=> FunctionType(parDeclList.map(getDeclType),rType)
    }
  }

  def getVarType(name:String,declList:List[Symbol]): Type ={
    lookup(name,declList,getName) match{
      case Some(Symbol(name,varType,_)) => if(!varType.isInstanceOf[FunctionType]) varType else throw Undeclared(Identifier,name)
      case _ => throw Undeclared(Identifier,name)
    }
  }

  def getFuncType(name:String,declList:List[Symbol]): FunctionType={
    lookup(name,declList,getName) match{
      case Some(Symbol(name,FunctionType(paramType,returnType),null)) => FunctionType(paramType,returnType)
      case _ => throw Undeclared(Function,name)
    }
  }

  def getBinType(Op:String,left:Type,right:Type):Any={
    (left,right) match{
      case (BoolType,BoolType) => if(Op=="==" || Op=="!=" || Op=="!" || Op=="&&" || Op=="||" || Op=="=") BoolType else null
      case (IntType,IntType) =>
        if(Op=="+" || Op=="-" || Op=="*" || Op =="/" || Op=="%" || Op=="=") IntType
        else if(Op=="<" || Op=="<=" || Op==">" || Op==">=" || Op=="==" || Op=="!=") BoolType
        else null
      case (IntType,FloatType)|(FloatType,IntType)|(FloatType,FloatType)=>
        if(Op=="+" || Op=="-" || Op=="*" || Op =="/" || (Op=="=" && left==FloatType)) FloatType
        else if(Op=="<" || Op=="<=" || Op==">" || Op==">=") BoolType
        else null
      case (StringType,StringType)=> if(Op=="=") StringType else null
      case (_,_) => null
    }
  }

  def checkImplicitAssignRule(lhs:Type,rhs:Type):Boolean={
    (lhs,rhs) match {
      case (_,VoidType)=> false
      case (FloatType,IntType)=> true
      case (ArrayPointerType(a),ArrayType(_,b))=> a==b
      case (ArrayPointerType(a),ArrayPointerType(b))=> a==b
      case (a,b)=> a==b
    }
  }

  def addSymbol(declList:List[Decl],lst:List[Symbol]):List[Symbol]={
    declList.foldLeft(lst)((a,b)=>lookup(getName(b),a,getName) match{
      case Some(Symbol(_,_,_))=> if(b.isInstanceOf[VarDecl]) throw Redeclared(Variable,getName(b)) else throw Redeclared(Function,getName(b))
      case _ => Symbol(getName(b),getDeclType(b),null)::a
    })
  }

  override def visitProgram(ast: Program, c: Any): Any = {
    val listGlobDecl:List[Symbol]=addSymbol(ast.decl,List[Symbol](
      Symbol("getInt",FunctionType(List(),IntType),null),
      Symbol("putInt",FunctionType(List(IntType),VoidType),null),
      Symbol("putIntLn",FunctionType(List(IntType),VoidType),null),
      Symbol("getFloat",FunctionType(List(),FloatType),null),
      Symbol("putFloat",FunctionType(List(FloatType),VoidType),null),
      Symbol("putFloatLn",FunctionType(List(FloatType),VoidType),null),
      Symbol("putBool",FunctionType(List(BoolType),VoidType),null),
      Symbol("putBoolLn",FunctionType(List(BoolType),VoidType),null),
      Symbol("putString",FunctionType(List(StringType),VoidType),null),
      Symbol("putStringLn",FunctionType(List(StringType),VoidType),null),
      Symbol("putLn",FunctionType(List(),VoidType),null)
    ))
    val functionCheck=ast.decl.filter(_.isInstanceOf[FuncDecl]).map(_.accept(this,listGlobDecl))
  }

  override def visitFuncDecl(ast: FuncDecl, c: Any): Any = {
    val lstPar:List[Symbol]=ast.param.foldLeft(List[Symbol]())((a,b)=> lookup(getName(b),a,getName) match {
      case Some(Symbol(_,_,_)) => throw Redeclared(Parameter,getName(b))
      case _ => Symbol(getName(b),getDeclType(b),null)::a
    })
    val lstDecl=ast.body.accept(this,List(ast.returnType,lstPar,c.asInstanceOf[List[Symbol]]))
  }

  override def visitBlock(ast: Block, c: Any): Any = {
    val list:List[Any]=c.asInstanceOf[List[Any]]
    if (ast.decl != List()) {
      if(list.size==3) {
        val declList: List[Symbol] = addSymbol(ast.decl, list(1).asInstanceOf[List[Symbol]]) ::: list(2).asInstanceOf[List[Symbol]]
        val stmtList = ast.stmt.map(_.accept(this, List(list(0), declList)))
      }
      else{
        val declList: List[Symbol] = addSymbol(ast.decl, List[Symbol]()) :::  list(1).asInstanceOf[List[Symbol]]
        val stmtList = ast.stmt.map(_.accept(this, List(list(0),declList)))
      }
    }
    else if(list.size==3){
      val stmtList = ast.stmt.map(_.accept(this, List(list(0),list(1).asInstanceOf[List[Symbol]]::: list(2).asInstanceOf[List[Symbol]])))
  }
    else{
      val stmtList = ast.stmt.map(_.accept(this, List(list(0),list(1).asInstanceOf[List[Symbol]])))
    }
  }

  override def visitBinaryOp(ast: BinaryOp, c: Any): Any = {
    val binType =getBinType(ast.op,ast.left.accept(this,c).asInstanceOf[Type],ast.right.accept(this,c).asInstanceOf[Type])
    if(binType==null) throw TypeMismatchInExpression(ast) else binType
  }

  override def visitUnaryOp(ast: UnaryOp, c:Any):Any={
    val exprType:Type=ast.body.accept(this,c).asInstanceOf[Type]
    if(ast.op=="-" && exprType!=IntType && exprType!=FloatType) throw TypeMismatchInExpression(ast)
    else if(ast.op=="!" && exprType!=BoolType) throw TypeMismatchInExpression(ast)
    else exprType
  }

  override def visitCallExpr(ast: CallExpr, c: Any): Any = {
    val lst:List[Any]=c.asInstanceOf[List[Any]]
    val funcType:FunctionType=getFuncType(ast.method.name,lst(1).asInstanceOf[List[Symbol]])
    if(ast.params.size!=funcType.input.size) throw TypeMismatchInExpression(ast)
    else if(!funcType.input.zip(ast.params).forall(x=>checkImplicitAssignRule(x._1,x._2.accept(this,c).asInstanceOf[Type])))throw TypeMismatchInExpression(ast)
    else funcType.output
  }

  override def visitId(ast: Id, c: Any): Any = {
    val lst:List[Any]=c.asInstanceOf[List[Any]]
    getVarType(ast.name,lst(1).asInstanceOf[List[Symbol]])
  }

  override def visitArrayCell(ast:ArrayCell,c:Any): Any={
    val varType:Type=ast.arr.accept(this,c).asInstanceOf[Type]
    if(ast.idx.accept(this,c)==IntType){
      if(varType.isInstanceOf[ArrayType]) varType.asInstanceOf[ArrayType].eleType
      else if(varType.isInstanceOf[ArrayPointerType]) varType.asInstanceOf[ArrayPointerType].eleType
      else throw TypeMismatchInExpression(ast)
    }
    else throw TypeMismatchInExpression(ast)
  }

  override def visitIf(ast:If, c:Any):Any={
    if(ast.expr.accept(this,c).asInstanceOf[Type]!=BoolType) throw TypeMismatchInStatement(ast)
    val checkThenStmt=ast.thenStmt.accept(this,c)
    ast.elseStmt match{
      case Some(stmt) => val checkStmt=stmt.accept(this,c)
      case _ => val checkStmt=null
    }
  }

  override def visitFor(ast: For, c: Any): Any ={
    if(ast.expr1.accept(this,c)!=IntType || ast.expr2.accept(this,c)!=BoolType || ast.expr3.accept(this,c)!=IntType) throw TypeMismatchInStatement(ast)
    val checkBody=ast.loop.accept(this,c)
  }

  override def visitDowhile(ast: Dowhile, c:Any): Any ={
    if(ast.exp.accept(this,c).asInstanceOf[Type]!=BoolType) throw TypeMismatchInStatement(ast)
    val checkBody=ast.sl.map(_.accept(this,c))
  }

  override def visitReturn(ast: Return, c: Any): Any ={
    val lst:List[Any]=c.asInstanceOf[List[Any]]
    ast.expr match{
      case Some(a) => if(!checkImplicitAssignRule(lst(0).asInstanceOf[Type],a.accept(this,c).asInstanceOf[Type])) throw TypeMismatchInStatement(ast)
      case None => if(lst(0)!=VoidType) throw TypeMismatchInStatement(ast)
    }
  }

  override def visitIntLiteral(ast:IntLiteral,c:Any):Type=IntType

  override def visitFloatLiteral(ast:FloatLiteral,c:Any):Type=FloatType

  override def visitStringLiteral(ast:StringLiteral,c:Any):Type=StringType

  override def visitBooleanLiteral(ast:BooleanLiteral,c:Any):Type=BoolType
}

