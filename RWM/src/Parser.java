import java.util.List;
import java.util.Scanner;

public class Parser {
    private final List<Token> tokens;
    private int currentTokenIndex = 0;
    private final Scanner scanner = new Scanner(System.in);
    private final Environment environment = new Environment();

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void parse() {
        while (currentTokenIndex < tokens.size()) {
            Token token = getNextToken();
            handleToken(token);
        }
    }

    private Token getNextToken() {
        if (currentTokenIndex < tokens.size()) {
            return tokens.get(currentTokenIndex++);
        }
        return new Token(TokenType.EOF, "");
    }

    private void handleToken(Token token) {
        switch (token.getType()) {
            case KEYWORD:
                handleKeyword(token);
                break;
            case ID:
                // Handle identifiers if needed
                break;
            case NUMBER:
                // Handle numbers if needed
                break;
            case OPERATOR:
                // Handle operators if needed
                break;
            case SYMBOL:
                // Handle symbols if needed
                break;
            default:
                break;
        }
    }

    private void handleKeyword(Token token) {
        switch (token.getValue()) {
            case "cin":
                handleCinFunction();
                break;
            case "cout":
                handleCoutFunction();
                break;
            case "int":
                handleVariableDeclaration();
                break;
            default:
                break;
        }
    }

    private void handleVariableDeclaration() {
        Token variableToken = getNextToken();
        if (variableToken.getType() == TokenType.ID) {
            environment.setVariable(variableToken.getValue(), "0"); // Default initialization
            getNextToken(); // Skip semicolon
        }
    }

    private void handleCinFunction() {
        Token nextToken = getNextToken();
        if (nextToken.getType() == TokenType.ID) {
            System.out.print("Enter value for " + nextToken.getValue() + ": ");
            String value = scanner.nextLine();
            environment.setVariable(nextToken.getValue(), value);
            getNextToken(); // Skip the semicolon
        }
    }

    private void handleCoutFunction() {
        Token nextToken = getNextToken();
        if (nextToken.getType() == TokenType.ID) {
            String value = environment.getVariable(nextToken.getValue());
            System.out.println(value); // Output the value of the variable
        } else if (nextToken.getType() == TokenType.NUMBER) {
            System.out.println(nextToken.getValue()); // Output the number directly
        }
        getNextToken(); // Skip the closing symbol
    }
}
