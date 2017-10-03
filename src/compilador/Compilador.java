/*
 * Trabalho de Compiladores
 */
package compilador;
import Lexico.*;
import java.io.IOException;
/**
 *
 * @author augusto, fabricio e guilherme
 */
public class Compilador {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Lexer lex = new Lexer("./file.txt");
        lex.scanAll();
     }
    
}