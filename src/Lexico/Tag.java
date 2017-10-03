/*
 * @author augusto, fabricio e guilherme
 */
package Lexico;

public class Tag {
    public final static int
            INVALID_LEX = -2,
            UNEXPECTED_EOF = -1,
            END_OF_FILE = 65535,
            // Palavras reservadas
            PROG = 1,    // program
            END = 2,    // end
            INT = 3,    // int
            STRING = 4, // string
            IF = 5,     // if
            THEN = 6,   // then
            ELSE = 7,   // else
            DO = 8,     // do
            WHILE = 9, // while
            SCAN = 10,  // scan
            PRINT = 11, // print
            TEXT = 12,  // " " 
            // Pontuação
            COMMA = 13,     // ,
            SEMICOLON = 14, // ;
            PAR_BEGIN = 15, // (
            PAR_END = 16,   // )
            // Operadores
            ASSIGN = 17,    // =
            EQ = 18,    // ==
            GT = 19,    // >
            GE = 20,    // >=
            LT = 21,    // <
            LE = 22,    // <=
            NE = 23,    // !=
            PLUS = 24,  // +
            MINUS = 25, // -
            OR = 26,    // ||
            TIMES = 27, // *
            DIV = 28,   // /
            AND = 29,   // &&
            // Comentario
            COMMENT = 30,   // //
            // Outros tokens
            NUM = 31,   // numeros
            ID = 32,    // identificador
            TRUE = 33,  // true
            FALSE = 34  // false
            ;
}