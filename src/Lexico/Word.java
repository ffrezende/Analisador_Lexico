/*
 * @author augusto, fabricio e guilherme
 */
package Lexico;


public class Word extends Token {
    
    private String lexeme = "";
    
    public static final Word and = new Word("&&", Tag.AND);
    public static final Word or  = new Word("||", Tag.OR);
    public static final Word eq  = new Word("==", Tag.EQ);
    public static final Word ne  = new Word("!=", Tag.NE);
    public static final Word le  = new Word("<=", Tag.LE);
    public static final Word ge  = new Word(">=", Tag.GE);
    public static final Word True  = new Word("true", Tag.TRUE);
    public static final Word False  = new Word("false", Tag.FALSE);
    //tentativas
    public static final Word assign  = new Word("=", Tag.ASSIGN);
    public static final Word par_begin  = new Word("(", Tag.PAR_BEGIN);
    public static final Word par_end  = new Word(")", Tag.PAR_END);
    public static final Word comma = new Word(",", Tag.COMMA);
    public static final Word semicolon = new Word(";", Tag.SEMICOLON);
    
    public static final Word times = new Word("*", Tag.TIMES);
    public static final Word plus = new Word("+", Tag.PLUS);
    public static final Word minus = new Word("-", Tag.MINUS);
    public static final Word div = new Word("/", Tag.DIV);
    public static final Word gt = new Word(">", Tag.GT);
    public static final Word lt = new Word("<", Tag.LT);

 
    
    public Word(String s, int tag){
        super(tag);
        lexeme = s;
    }
    
    @Override
    public String toString(){
        return "" + lexeme;
    }

    Object getLexeme() {
        return toString();
    }
}
