/*
 * @author augusto, fabricio e guilherme
 */
package Lexico;
import java.io.*;
import java.util.*;


public class Lexer {
    public static int line = 1;

    private char ch = ' ';
    private FileReader file = null;
    
    private Hashtable words = new Hashtable();
    
    private void reserve(Word w){
        words.put(w.getLexeme(), w);
    }
    
    public Lexer(String fileName) throws FileNotFoundException{
        
        try{
            file = new FileReader(fileName);
        }
        catch(FileNotFoundException e){
            System.out.println("Arquivo não encontrado");
            throw e;
        }
        
        // Palavras reservadas
        reserve(new Word("program", Tag.PROG));
        reserve(new Word("end", Tag.END));
        reserve(new Word("int", Tag.INT));
        reserve(new Word("string", Tag.STRING));
        reserve(new Word("if", Tag.IF));
        reserve(new Word("then", Tag.THEN));
        reserve(new Word("else", Tag.ELSE));
        reserve(new Word("do", Tag.DO));
        reserve(new Word("while", Tag.WHILE));
        reserve(new Word("scan", Tag.SCAN));
        reserve(new Word("print", Tag.PRINT));
    }
    
    private void readch() throws IOException{
        ch = (char) file.read();
    }

    private boolean readch(char c) throws IOException{
        readch();
        if(ch != c) return false;
        ch = ' ';
        return true;
    }
    
    public void scanAll() throws IOException{
        Token tok = scan();
        do{
            tok = scan();
            if(tok.tag==Tag.END_OF_FILE) break;
            System.out.println("Tag: " + tok.tag + "\t " + tok.toString());

        }while(true);
    }
    
    private Token erroUnexEOF(String tipo){
        return new Word("Final de arquivo inesperado (" + tipo + ") - Linha: " + line,Tag.UNEXPECTED_EOF);}
    
    private Token erroLexico(){
        return new Word("Lexema inválido - Linha: " + line, Tag.INVALID_LEX);
    }
    
    public Token scan() throws IOException{
        
        
            for( ; ; readch()){
                if(ch == ' ' || ch == '\t' || ch == '\r' || ch == '\b') continue;
                else if(ch == '\n') line++;
                else break;
            }
            switch(ch){
                // Operadores
                case '&':
                    if(readch('&')) return Word.and;
                    else return erroLexico();
                case '|':
                    if(readch('|')) return Word.or;
                    else return erroLexico();
                case '=':
                    if(readch('=')) return Word.eq;
                    else return Word.assign;
                case '<':
                    if(readch('=')) return Word.le;
                    else return Word.lt;
                case '>':
                    if(readch('=')) return Word.ge;
                    else return Word.gt;
                case '(':
                    readch();
                    return Word.par_begin;
                case ')':
                    readch();
                    return Word.par_end;
                case ',':
                    readch();
                    return Word.comma;   
                case ';':
                    readch();
                    return Word.semicolon;
                case '*':
                    readch();
                    return Word.times;
                case '+':
                    readch();
                    return Word.plus;
                case '-':
                    readch();
                    return Word.minus;
                case '"':
                {
                    
                    String str = "";
                    str = str + ch;
                    readch();
                    while(ch != '"'){
                        str = str + ch;
                        readch();
                        if(ch == Tag.END_OF_FILE){
                            return erroUnexEOF("Aspas");
                        } 
                        if(ch == '\n'){
                            return erroLexico();
                        } 
                        
                    }
                    str = str+ ch;
                    readch();
                    return new Word(str, Tag.TEXT); 
                }
                case '/': 
                {   
                    readch();
                    if(ch == '*'){
                        readch();
                        int done = 0;
                        while(done==0){
                            readch();
                            if(ch=='*'){
                                readch();
                                if(ch=='/'){
                                    readch();
                                    done=1;
                                }          
                            }
                            if(ch == Tag.END_OF_FILE){
                                return erroUnexEOF("Comentário");
                            }
                        } 
                        return new Word("Comentário", Tag.COMMENT);
                    }else if(ch == '/'){
                        readch();
                        while(ch!='\n')
                            readch();
                        return new Word("Comentário", Tag.COMMENT);
                    } else return Word.div; 
                }                
            }

            // Números
            if(Character.isDigit(ch)){
                int value = 0;
                do{
                    value = 10*value + Character.digit(ch, 10);
                    readch();
                    }while(Character.isDigit(ch));
                return new Num(value);
            }

            // Identificadores
            if(Character.isLetter(ch)){
                StringBuffer sb = new StringBuffer();
                do{
                    sb.append(ch);
                    readch();
                } while(Character.isLetterOrDigit(ch) );

                String s = sb.toString();
                Word w = (Word)words.get(s);
                if(w != null) return w; // ja existe na hashtable ou é uma palabra reservada
                w = new Word(s, Tag.ID);
                words.put(s, w);
                return w;
            } 
            
        // Caracteres não identificados
        if(ch != Tag.END_OF_FILE){
            readch();
            return erroLexico();
        }  
            
        Token t = new Token(ch);
        ch = ' ';
        return t;  
    }
}
