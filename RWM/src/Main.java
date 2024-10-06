import java.util.List;

public class Main {
    public static void main(String[] args) {
        String code = "cin a; cout(a); cout(100);";
        Lexer lexer = new Lexer(code);
        List<Token> tokens = lexer.tokenize();

        // Create parser and parse tokens
        Parser parser = new Parser(tokens);
        parser.parse();
    }
}
