import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final String input;
    private int position = 0;

    public Lexer(String input) {
        this.input = input;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (position < input.length()) {
            char current = input.charAt(position);
            if (Character.isWhitespace(current)) {
                position++;
            } else if (Character.isDigit(current)) {
                StringBuilder number = new StringBuilder();
                while (position < input.length() && Character.isDigit(input.charAt(position))) {
                    number.append(input.charAt(position++));
                }
                tokens.add(new Token(TokenType.NUMBER, number.toString()));
            } else if (Character.isLetter(current)) {
                StringBuilder identifier = new StringBuilder();
                while (position < input.length() && Character.isLetterOrDigit(input.charAt(position))) {
                    identifier.append(input.charAt(position++));
                }
                String id = identifier.toString();
                if (id.equals("cin") || id.equals("cout") || id.equals("int")) {
                    tokens.add(new Token(TokenType.KEYWORD, id));
                } else {
                    tokens.add(new Token(TokenType.ID, id));
                }
            } else if (current == ';') {
                tokens.add(new Token(TokenType.SYMBOL, ";"));
                position++;
            } else if (current == '=') {
                tokens.add(new Token(TokenType.OPERATOR, "="));
                position++;
            } else {
                position++; // Skip unrecognized characters
            }
        }
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }
}
