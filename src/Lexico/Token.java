/*
 * @author augusto, fabricio e guilherme
 */
package Lexico;

public class Token {
    public final int tag;
    
    public Token(int t){
        tag = t;
    }
    
    public String toString(){
        return "" + tag;
    }
}
