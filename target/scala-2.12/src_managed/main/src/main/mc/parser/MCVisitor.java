// Generated from src/main/mc/parser/MC.g4 by ANTLR 4.6

	package mc.parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MCParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MCVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MCParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MCParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#globdecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobdecl(MCParser.GlobdeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#vardecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVardecl(MCParser.VardeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#idlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdlist(MCParser.IdlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(MCParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#arrid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrid(MCParser.ArridContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#funcdecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncdecl(MCParser.FuncdeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#functype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctype(MCParser.FunctypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#parlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParlist(MCParser.ParlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#pardecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPardecl(MCParser.PardeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#arrptr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrptr(MCParser.ArrptrContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#callfunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallfunction(MCParser.CallfunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#nortype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNortype(MCParser.NortypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#prtype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrtype(MCParser.PrtypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#litlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLitlist(MCParser.LitlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#blkstmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlkstmt(MCParser.BlkstmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(MCParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#ifst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfst(MCParser.IfstContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#dowhilest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDowhilest(MCParser.DowhilestContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#forst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForst(MCParser.ForstContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#brkst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBrkst(MCParser.BrkstContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#contst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContst(MCParser.ContstContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#retst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRetst(MCParser.RetstContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(MCParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#exp1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp1(MCParser.Exp1Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#exp2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp2(MCParser.Exp2Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#exp3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp3(MCParser.Exp3Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#exp4}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp4(MCParser.Exp4Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#exp5}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp5(MCParser.Exp5Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#exp6}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp6(MCParser.Exp6Context ctx);
}