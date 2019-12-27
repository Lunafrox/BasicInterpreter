package InterpreterAlpha;

import org.junit.Test;

import static org.junit.Assert.*;

public class LexerTest {

    @Test
    public void get_next_token() throws Exception{
        Lexer x = new Lexer(":=");
        Token y = x.get_next_token();
        assertEquals(TokenType.ASSIGN, y.type);
        assertEquals(":=", y.value);

        x = new Lexer("{this is a comment}\t\n ;");
        y = x.get_next_token();
        assertEquals(";", y.value);
        assertEquals(TokenType.SEMI, y.type);

        x = new Lexer("a12xb");
        y = x.get_next_token();
        assertEquals(TokenType.ID, y.type);
        assertEquals("a12xb", y.value);

        x = new Lexer("1483");
        y = x.get_next_token();
        assertEquals(TokenType.INTEGER_CONST, y.type);
        assertEquals("1483", y.value);

        x = new Lexer("12.5");
        y = x.get_next_token();
        assertEquals(TokenType.REAL_CONST, y.type);
        assertEquals("12.5", y.value);
    }
}