package InterpreterAlpha;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class InterpreterAlphaTest {

    @Test
    public void interpret() throws Exception{
        String str = "PROGRAM thisProgram;\n" +
                "VAR\n" +
                "    var1, a, b, c : INTEGER;\n" +
                "\n" +
                "BEGIN {example}\n" +
                "    a := 2;\n" +
                "    var1 := 1;\n" +
                "    b := 5;\n" +
                "    c := 20;\n" +
                "\n" +
                "    LOOP b DO\n" +
                "        var1 := var1 + a;\n" +
                "        c := c - 1;\n" +
                "    END;\n" +
                "\n" +
                "    LOOP a DO\n" +
                "        b := b + 2\n" +
                "    END\n" +
                "END. {example}";
        Lexer lexer = new Lexer(str);
        Parser parser = new Parser(lexer);
        AST tree = parser.parse();
        SymbolTableBuilder symtab = new SymbolTableBuilder();
        symtab.visit(tree);
        InterpreterAlpha interpreter = new InterpreterAlpha(tree);
        interpreter.interpret();
        Symbol.resultFile.close();
        String result = Files.readString(Paths.get("result.txt"));
        assertEquals("a = 2\n" +
                "b = 9.0\n" +
                "c = 15.0\n" +
                "var1 = 11.0\n", result);
    }
}