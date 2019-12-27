package InterpreterAlpha;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class SymbolTableBuilderTest {

    @Test
    public void printTable() throws Exception{
        String str = "PROGRAM thisProgram;\n" +
                "VAR\n" +
                "    var1, a, b, c : INTEGER;\n" +
                "    d : REAL;\n" +
                "BEGIN {example}\n" +
                "END. {example}";
        Lexer lexer = new Lexer(str);
        Parser parser = new Parser(lexer);
        AST tree = parser.parse();
        SymbolTableBuilder sym_table = new SymbolTableBuilder();
        sym_table.visit(tree);
        sym_table.printTable();
        Symbol.resultFile.close();
        String result = Files.readString(Paths.get("result.txt"));
        assertEquals("<a : INTEGER>; <b : INTEGER>; <c : INTEGER>; <d : REAL>; REAL; <var1 : INTEGER>; INTEGER; \n\n", result);
    }
}