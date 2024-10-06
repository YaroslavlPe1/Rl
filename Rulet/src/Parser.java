import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    private List<Token> tokens;
    private int currentPosition;
    private Map<String, String> variables = new HashMap<>(); // Для хранения переменных

    public void parse(List<Token> tokens) {
        this.tokens = tokens;
        this.currentPosition = 0;

        while (currentPosition < tokens.size()) {
            Token currentToken = tokens.get(currentPosition);

            if (currentToken.type == TokenType.KEYWORD) {
                if (currentToken.value.equals("class")) {
                    parseClass();
                } else if (currentToken.value.equals("function")) {
                    parseFunction();
                } else if (currentToken.value.equals("print")) {
                    parsePrint(); // Обрабатываем команду print
                } else {
                    throw new RuntimeException("Unexpected keyword: " + currentToken.value);
                }
            } else {
                throw new RuntimeException("Unexpected token: " + currentToken.value);
            }
        }
    }

    // Обработка класса
    private void parseClass() {
        consume(TokenType.KEYWORD, "class");
        String className = consume(TokenType.ID).value;
        System.out.println("Class declared: " + className);

        consume(TokenType.OPERATOR, "{");

        while (!check(TokenType.OPERATOR, "}")) {
            parseFunction();
        }

        consume(TokenType.OPERATOR, "}");
    }

    // Обработка функции
    private void parseFunction() {
        consume(TokenType.KEYWORD, "function");
        String functionName = consume(TokenType.ID).value;
        System.out.println("Function declared: " + functionName);

        consume(TokenType.OPERATOR, "(");
        consume(TokenType.OPERATOR, ")");
        consume(TokenType.OPERATOR, "{");

        while (!check(TokenType.OPERATOR, "}")) {
            Token currentToken = tokens.get(currentPosition);
            if (currentToken.type == TokenType.KEYWORD && currentToken.value.equals("print")) {
                parsePrint();
            } else if (currentToken.type == TokenType.KEYWORD && currentToken.value.equals("int")) {
                parseAssignment();
            } else {
                throw new RuntimeException("Unexpected token in function body: " + currentToken.value);
            }
        }

        consume(TokenType.OPERATOR, "}");
    }

    // Обработка команды print
    private void parsePrint() {
        consume(TokenType.KEYWORD, "print");
        Token value = consume(); // Ожидаем либо строку, либо идентификатор переменной

        if (value.type == TokenType.STRING) {
            // Если это строка, выводим её как есть
            System.out.println(value.value);
        } else if (value.type == TokenType.ID) {
            // Если это идентификатор переменной, выводим значение переменной
            if (variables.containsKey(value.value)) {
                System.out.println(variables.get(value.value));
            } else {
                throw new RuntimeException("Undefined variable: " + value.value);
            }
        } else {
            throw new RuntimeException("Expected string or variable after print");
        }

        consume(TokenType.OPERATOR, ";"); // Завершаем команду `print`
    }

    // Обработка присваивания переменной
    private void parseAssignment() {
        consume(TokenType.KEYWORD, "int");
        Token varName = consume(TokenType.ID);
        consume(TokenType.OPERATOR, "=");
        Token value = consume(TokenType.NUMBER);

        // Сохраняем значение переменной
        variables.put(varName.value, value.value);
        System.out.println("Variable declaration: " + varName.value + " = " + value.value);

        consume(TokenType.OPERATOR, ";");
    }

    private Token consume() {
        return tokens.get(currentPosition++);
    }

    private Token consume(TokenType expectedType) {
        Token token = consume();
        if (token.type != expectedType) {
            throw new RuntimeException("Expected token of type " + expectedType + " but got " + token.type);
        }
        return token;
    }

    private Token consume(TokenType expectedType, String expectedValue) {
        Token token = consume();
        if (token.type != expectedType || !token.value.equals(expectedValue)) {
            throw new RuntimeException("Expected token '" + expectedValue + "' but got '" + token.value + "'");
        }
        return token;
    }

    private boolean check(TokenType type, String value) {
        if (currentPosition >= tokens.size()) return false;
        Token token = tokens.get(currentPosition);
        return token.type == type && token.value.equals(value);
    }
}
