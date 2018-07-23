// Generated from src/main/mc/parser/MC.g4 by ANTLR 4.6

	package mc.parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MCLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMENT=1, INTTYPE=2, VOIDTYPE=3, BOOLEANTYPE=4, FLOATTYPE=5, STRINGTYPE=6, 
		BREAK=7, CONTINUE=8, ELSE=9, FOR=10, IF=11, RETURN=12, DO=13, WHILE=14, 
		TRUE=15, FALSE=16, ADD=17, SUB=18, MUL=19, DIV=20, NOT=21, MOD=22, OR=23, 
		AND=24, NOTEQU=25, EQU=26, LT=27, GT=28, LOE=29, GOE=30, ASSIGN=31, LSB=32, 
		RSB=33, LB=34, RB=35, LP=36, RP=37, SEMI=38, COMMA=39, WS=40, INTLIT=41, 
		FLOATLIT=42, BOOLIT=43, STRINGLIT=44, ID=45, ERROR_CHAR=46, UNCLOSE_STRING=47, 
		ILLEGAL_ESCAPE=48;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"COMMENT", "INTTYPE", "VOIDTYPE", "BOOLEANTYPE", "FLOATTYPE", "STRINGTYPE", 
		"BREAK", "CONTINUE", "ELSE", "FOR", "IF", "RETURN", "DO", "WHILE", "TRUE", 
		"FALSE", "ADD", "SUB", "MUL", "DIV", "NOT", "MOD", "OR", "AND", "NOTEQU", 
		"EQU", "LT", "GT", "LOE", "GOE", "ASSIGN", "LSB", "RSB", "LB", "RB", "LP", 
		"RP", "SEMI", "COMMA", "WS", "INTLIT", "EXPO", "DOT", "FLOATLIT", "BOOLIT", 
		"ESCSENT", "STRCHARS", "STRINGLIT", "ID", "ERROR_CHAR", "UNCLOSE_STRING", 
		"ILLEGAL_ESCAPE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'int'", "'void'", "'boolean'", "'float'", "'string'", "'break'", 
		"'continue'", "'else'", "'for'", "'if'", "'return'", "'do'", "'while'", 
		"'true'", "'false'", "'+'", "'-'", "'*'", "'/'", "'!'", "'%'", "'||'", 
		"'&&'", "'!='", "'=='", "'<'", "'>'", "'<='", "'>='", "'='", "'['", "']'", 
		"'('", "')'", "'{'", "'}'", "';'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "COMMENT", "INTTYPE", "VOIDTYPE", "BOOLEANTYPE", "FLOATTYPE", "STRINGTYPE", 
		"BREAK", "CONTINUE", "ELSE", "FOR", "IF", "RETURN", "DO", "WHILE", "TRUE", 
		"FALSE", "ADD", "SUB", "MUL", "DIV", "NOT", "MOD", "OR", "AND", "NOTEQU", 
		"EQU", "LT", "GT", "LOE", "GOE", "ASSIGN", "LSB", "RSB", "LB", "RB", "LP", 
		"RP", "SEMI", "COMMA", "WS", "INTLIT", "FLOATLIT", "BOOLIT", "STRINGLIT", 
		"ID", "ERROR_CHAR", "UNCLOSE_STRING", "ILLEGAL_ESCAPE"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


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


	public MCLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MC.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 47:
			STRINGLIT_action((RuleContext)_localctx, actionIndex);
			break;
		case 50:
			UNCLOSE_STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 51:
			ILLEGAL_ESCAPE_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void STRINGLIT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			setText(getText().substring(1, getText().length()-1));
			break;
		}
	}
	private void UNCLOSE_STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			setText(getText().substring(1, getText().length()));
			break;
		}
	}
	private void ILLEGAL_ESCAPE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			setText(getText().substring(1, getText().length()));
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\62\u015e\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\3\2\3\2\3\2\3\2\7\2p\n\2\f\2\16\2s\13\2\3\2\3\2\3\2\3"+
		"\2\7\2y\n\2\f\2\16\2|\13\2\3\2\3\2\5\2\u0080\n\2\3\2\3\2\3\3\3\3\3\3\3"+
		"\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22"+
		"\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30"+
		"\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\36"+
		"\3\36\3\36\3\37\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&"+
		"\3\'\3\'\3(\3(\3)\6)\u010d\n)\r)\16)\u010e\3)\3)\3*\6*\u0114\n*\r*\16"+
		"*\u0115\3+\3+\5+\u011a\n+\3+\3+\3,\3,\3-\3-\3-\5-\u0123\n-\3-\5-\u0126"+
		"\n-\3-\3-\3-\5-\u012b\n-\3-\3-\3-\5-\u0130\n-\3.\3.\5.\u0134\n.\3/\3/"+
		"\3/\3\60\3\60\7\60\u013b\n\60\f\60\16\60\u013e\13\60\3\61\3\61\5\61\u0142"+
		"\n\61\3\61\3\61\3\61\3\62\3\62\7\62\u0149\n\62\f\62\16\62\u014c\13\62"+
		"\3\63\3\63\3\64\3\64\5\64\u0152\n\64\3\64\3\64\3\65\3\65\5\65\u0158\n"+
		"\65\3\65\3\65\3\65\3\65\3\65\3z\2\66\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U\2W\2Y"+
		",[-]\2_\2a.c/e\60g\61i\62\3\2\13\3\2\f\f\5\2\13\f\17\17\"\"\3\2\62;\4"+
		"\2GGgg\4\2--//\n\2$$))^^ddhhppttvv\7\2\n\f\16\17$$))^^\5\2C\\aac|\6\2"+
		"\62;C\\aac|\u016b\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E"+
		"\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2"+
		"\2\2\2S\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2"+
		"\2g\3\2\2\2\2i\3\2\2\2\3\177\3\2\2\2\5\u0083\3\2\2\2\7\u0087\3\2\2\2\t"+
		"\u008c\3\2\2\2\13\u0094\3\2\2\2\r\u009a\3\2\2\2\17\u00a1\3\2\2\2\21\u00a7"+
		"\3\2\2\2\23\u00b0\3\2\2\2\25\u00b5\3\2\2\2\27\u00b9\3\2\2\2\31\u00bc\3"+
		"\2\2\2\33\u00c3\3\2\2\2\35\u00c6\3\2\2\2\37\u00cc\3\2\2\2!\u00d1\3\2\2"+
		"\2#\u00d7\3\2\2\2%\u00d9\3\2\2\2\'\u00db\3\2\2\2)\u00dd\3\2\2\2+\u00df"+
		"\3\2\2\2-\u00e1\3\2\2\2/\u00e3\3\2\2\2\61\u00e6\3\2\2\2\63\u00e9\3\2\2"+
		"\2\65\u00ec\3\2\2\2\67\u00ef\3\2\2\29\u00f1\3\2\2\2;\u00f3\3\2\2\2=\u00f6"+
		"\3\2\2\2?\u00f9\3\2\2\2A\u00fb\3\2\2\2C\u00fd\3\2\2\2E\u00ff\3\2\2\2G"+
		"\u0101\3\2\2\2I\u0103\3\2\2\2K\u0105\3\2\2\2M\u0107\3\2\2\2O\u0109\3\2"+
		"\2\2Q\u010c\3\2\2\2S\u0113\3\2\2\2U\u0117\3\2\2\2W\u011d\3\2\2\2Y\u012f"+
		"\3\2\2\2[\u0133\3\2\2\2]\u0135\3\2\2\2_\u013c\3\2\2\2a\u013f\3\2\2\2c"+
		"\u0146\3\2\2\2e\u014d\3\2\2\2g\u014f\3\2\2\2i\u0155\3\2\2\2kl\7\61\2\2"+
		"lm\7\61\2\2mq\3\2\2\2np\n\2\2\2on\3\2\2\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2"+
		"\2r\u0080\3\2\2\2sq\3\2\2\2tu\7\61\2\2uv\7,\2\2vz\3\2\2\2wy\13\2\2\2x"+
		"w\3\2\2\2y|\3\2\2\2z{\3\2\2\2zx\3\2\2\2{}\3\2\2\2|z\3\2\2\2}~\7,\2\2~"+
		"\u0080\7\61\2\2\177k\3\2\2\2\177t\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0082"+
		"\b\2\2\2\u0082\4\3\2\2\2\u0083\u0084\7k\2\2\u0084\u0085\7p\2\2\u0085\u0086"+
		"\7v\2\2\u0086\6\3\2\2\2\u0087\u0088\7x\2\2\u0088\u0089\7q\2\2\u0089\u008a"+
		"\7k\2\2\u008a\u008b\7f\2\2\u008b\b\3\2\2\2\u008c\u008d\7d\2\2\u008d\u008e"+
		"\7q\2\2\u008e\u008f\7q\2\2\u008f\u0090\7n\2\2\u0090\u0091\7g\2\2\u0091"+
		"\u0092\7c\2\2\u0092\u0093\7p\2\2\u0093\n\3\2\2\2\u0094\u0095\7h\2\2\u0095"+
		"\u0096\7n\2\2\u0096\u0097\7q\2\2\u0097\u0098\7c\2\2\u0098\u0099\7v\2\2"+
		"\u0099\f\3\2\2\2\u009a\u009b\7u\2\2\u009b\u009c\7v\2\2\u009c\u009d\7t"+
		"\2\2\u009d\u009e\7k\2\2\u009e\u009f\7p\2\2\u009f\u00a0\7i\2\2\u00a0\16"+
		"\3\2\2\2\u00a1\u00a2\7d\2\2\u00a2\u00a3\7t\2\2\u00a3\u00a4\7g\2\2\u00a4"+
		"\u00a5\7c\2\2\u00a5\u00a6\7m\2\2\u00a6\20\3\2\2\2\u00a7\u00a8\7e\2\2\u00a8"+
		"\u00a9\7q\2\2\u00a9\u00aa\7p\2\2\u00aa\u00ab\7v\2\2\u00ab\u00ac\7k\2\2"+
		"\u00ac\u00ad\7p\2\2\u00ad\u00ae\7w\2\2\u00ae\u00af\7g\2\2\u00af\22\3\2"+
		"\2\2\u00b0\u00b1\7g\2\2\u00b1\u00b2\7n\2\2\u00b2\u00b3\7u\2\2\u00b3\u00b4"+
		"\7g\2\2\u00b4\24\3\2\2\2\u00b5\u00b6\7h\2\2\u00b6\u00b7\7q\2\2\u00b7\u00b8"+
		"\7t\2\2\u00b8\26\3\2\2\2\u00b9\u00ba\7k\2\2\u00ba\u00bb\7h\2\2\u00bb\30"+
		"\3\2\2\2\u00bc\u00bd\7t\2\2\u00bd\u00be\7g\2\2\u00be\u00bf\7v\2\2\u00bf"+
		"\u00c0\7w\2\2\u00c0\u00c1\7t\2\2\u00c1\u00c2\7p\2\2\u00c2\32\3\2\2\2\u00c3"+
		"\u00c4\7f\2\2\u00c4\u00c5\7q\2\2\u00c5\34\3\2\2\2\u00c6\u00c7\7y\2\2\u00c7"+
		"\u00c8\7j\2\2\u00c8\u00c9\7k\2\2\u00c9\u00ca\7n\2\2\u00ca\u00cb\7g\2\2"+
		"\u00cb\36\3\2\2\2\u00cc\u00cd\7v\2\2\u00cd\u00ce\7t\2\2\u00ce\u00cf\7"+
		"w\2\2\u00cf\u00d0\7g\2\2\u00d0 \3\2\2\2\u00d1\u00d2\7h\2\2\u00d2\u00d3"+
		"\7c\2\2\u00d3\u00d4\7n\2\2\u00d4\u00d5\7u\2\2\u00d5\u00d6\7g\2\2\u00d6"+
		"\"\3\2\2\2\u00d7\u00d8\7-\2\2\u00d8$\3\2\2\2\u00d9\u00da\7/\2\2\u00da"+
		"&\3\2\2\2\u00db\u00dc\7,\2\2\u00dc(\3\2\2\2\u00dd\u00de\7\61\2\2\u00de"+
		"*\3\2\2\2\u00df\u00e0\7#\2\2\u00e0,\3\2\2\2\u00e1\u00e2\7\'\2\2\u00e2"+
		".\3\2\2\2\u00e3\u00e4\7~\2\2\u00e4\u00e5\7~\2\2\u00e5\60\3\2\2\2\u00e6"+
		"\u00e7\7(\2\2\u00e7\u00e8\7(\2\2\u00e8\62\3\2\2\2\u00e9\u00ea\7#\2\2\u00ea"+
		"\u00eb\7?\2\2\u00eb\64\3\2\2\2\u00ec\u00ed\7?\2\2\u00ed\u00ee\7?\2\2\u00ee"+
		"\66\3\2\2\2\u00ef\u00f0\7>\2\2\u00f08\3\2\2\2\u00f1\u00f2\7@\2\2\u00f2"+
		":\3\2\2\2\u00f3\u00f4\7>\2\2\u00f4\u00f5\7?\2\2\u00f5<\3\2\2\2\u00f6\u00f7"+
		"\7@\2\2\u00f7\u00f8\7?\2\2\u00f8>\3\2\2\2\u00f9\u00fa\7?\2\2\u00fa@\3"+
		"\2\2\2\u00fb\u00fc\7]\2\2\u00fcB\3\2\2\2\u00fd\u00fe\7_\2\2\u00feD\3\2"+
		"\2\2\u00ff\u0100\7*\2\2\u0100F\3\2\2\2\u0101\u0102\7+\2\2\u0102H\3\2\2"+
		"\2\u0103\u0104\7}\2\2\u0104J\3\2\2\2\u0105\u0106\7\177\2\2\u0106L\3\2"+
		"\2\2\u0107\u0108\7=\2\2\u0108N\3\2\2\2\u0109\u010a\7.\2\2\u010aP\3\2\2"+
		"\2\u010b\u010d\t\3\2\2\u010c\u010b\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u010c"+
		"\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0111\b)\2\2\u0111"+
		"R\3\2\2\2\u0112\u0114\t\4\2\2\u0113\u0112\3\2\2\2\u0114\u0115\3\2\2\2"+
		"\u0115\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116T\3\2\2\2\u0117\u0119\t"+
		"\5\2\2\u0118\u011a\t\6\2\2\u0119\u0118\3\2\2\2\u0119\u011a\3\2\2\2\u011a"+
		"\u011b\3\2\2\2\u011b\u011c\5S*\2\u011cV\3\2\2\2\u011d\u011e\7\60\2\2\u011e"+
		"X\3\2\2\2\u011f\u0120\5S*\2\u0120\u0122\5W,\2\u0121\u0123\5S*\2\u0122"+
		"\u0121\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0125\3\2\2\2\u0124\u0126\5U"+
		"+\2\u0125\u0124\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0130\3\2\2\2\u0127"+
		"\u0128\5W,\2\u0128\u012a\5S*\2\u0129\u012b\5U+\2\u012a\u0129\3\2\2\2\u012a"+
		"\u012b\3\2\2\2\u012b\u0130\3\2\2\2\u012c\u012d\5S*\2\u012d\u012e\5U+\2"+
		"\u012e\u0130\3\2\2\2\u012f\u011f\3\2\2\2\u012f\u0127\3\2\2\2\u012f\u012c"+
		"\3\2\2\2\u0130Z\3\2\2\2\u0131\u0134\5\37\20\2\u0132\u0134\5!\21\2\u0133"+
		"\u0131\3\2\2\2\u0133\u0132\3\2\2\2\u0134\\\3\2\2\2\u0135\u0136\7^\2\2"+
		"\u0136\u0137\t\7\2\2\u0137^\3\2\2\2\u0138\u013b\5]/\2\u0139\u013b\n\b"+
		"\2\2\u013a\u0138\3\2\2\2\u013a\u0139\3\2\2\2\u013b\u013e\3\2\2\2\u013c"+
		"\u013a\3\2\2\2\u013c\u013d\3\2\2\2\u013d`\3\2\2\2\u013e\u013c\3\2\2\2"+
		"\u013f\u0141\7$\2\2\u0140\u0142\5_\60\2\u0141\u0140\3\2\2\2\u0141\u0142"+
		"\3\2\2\2\u0142\u0143\3\2\2\2\u0143\u0144\7$\2\2\u0144\u0145\b\61\3\2\u0145"+
		"b\3\2\2\2\u0146\u014a\t\t\2\2\u0147\u0149\t\n\2\2\u0148\u0147\3\2\2\2"+
		"\u0149\u014c\3\2\2\2\u014a\u0148\3\2\2\2\u014a\u014b\3\2\2\2\u014bd\3"+
		"\2\2\2\u014c\u014a\3\2\2\2\u014d\u014e\13\2\2\2\u014ef\3\2\2\2\u014f\u0151"+
		"\7$\2\2\u0150\u0152\5_\60\2\u0151\u0150\3\2\2\2\u0151\u0152\3\2\2\2\u0152"+
		"\u0153\3\2\2\2\u0153\u0154\b\64\4\2\u0154h\3\2\2\2\u0155\u0157\7$\2\2"+
		"\u0156\u0158\5_\60\2\u0157\u0156\3\2\2\2\u0157\u0158\3\2\2\2\u0158\u0159"+
		"\3\2\2\2\u0159\u015a\7^\2\2\u015a\u015b\n\7\2\2\u015b\u015c\3\2\2\2\u015c"+
		"\u015d\b\65\5\2\u015dj\3\2\2\2\24\2qz\177\u010e\u0115\u0119\u0122\u0125"+
		"\u012a\u012f\u0133\u013a\u013c\u0141\u014a\u0151\u0157\6\b\2\2\3\61\2"+
		"\3\64\3\3\65\4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}