import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Пример кода с классом и функцией
        String code =
                "class MyClass {" +
                        "   function myFunction() {" +
                        "       print \"Hello, World!\";" +
                        "   }" +
                        "}" +
                        "function main() {" +
                        "   int x = 5;" +
                        "   print x;" +
                        "}";

        // Создаем лексер с кодом
        Lexer lexer = new Lexer(code);
        Parser parser = new Parser();

        List<Token> tokens = lexer.tokenize();
        parser.parse(tokens);
    }
}

