package InterpreterAlpha;

import org.junit.Test;

import static org.junit.Assert.*;

public class LexerTest {

    @Test
    public void get_next_token() throws Exception{
        Lexer x = new Lexer(":=");
        assertEquals(TokenType.ASSIGN, x.get_next_token().type);
    }
}