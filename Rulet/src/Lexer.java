import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private String code; // Исходный код
    private int position; // Текущая позиция в коде

    // Конструктор, принимающий исходный код
    public Lexer(String code) {
        this.code = code;
        this.position = 0;
    }

    // Метод для токенизации исходного кода
    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        // Главный цикл, который проходит по каждому символу кода
        while (position < code.length()) {
            char currentChar = code.charAt(position);

            // Пропуск пробелов
            if (Character.isWhitespace(currentChar)) {
                position++;
                continue;
            }

            // Обработка цифр (чисел)
            if (Character.isDigit(currentChar)) {
                tokens.add(readNumber());
                continue;
            }

            // Обработка идентификаторов (имена переменных, функций, классов)
            if (Character.isLetter(currentChar)) {
                tokens.add(readIdentifier());
                continue;
            }

            // Обработка операторов (например, =, +, -, *, / и т.д.)
            if (isOperator(currentChar)) {
                tokens.add(readOperator());
                continue;
            }

            // Обработка строк (например, "Hello, World!")
            if (currentChar == '"') {
                tokens.add(readString());
                continue;
            }

            // Обработка специальных символов (например, ;, {, }, (, ))
            switch (currentChar) {
                case '{':
                    tokens.add(new Token(TokenType.OPERATOR, "{"));
                    position++;
                    break;
                case '}':
                    tokens.add(new Token(TokenType.OPERATOR, "}"));
                    position++;
                    break;
                case '(':
                    tokens.add(new Token(TokenType.OPERATOR, "("));
                    position++;
                    break;
                case ')':
                    tokens.add(new Token(TokenType.OPERATOR, ")"));
                    position++;
                    break;
                case ';':
                    tokens.add(new Token(TokenType.OPERATOR, ";"));
                    position++;
                    break;
                default:
                    throw new RuntimeException("Unknown character: " + currentChar);
            }
        }

        return tokens;
    }

    // Метод для чтения чисел
    private Token readNumber() {
        StringBuilder number = new StringBuilder();
        while (position < code.length() && Character.isDigit(code.charAt(position))) {
            number.append(code.charAt(position));
            position++;
        }
        return new Token(TokenType.NUMBER, number.toString());
    }

    // Метод для чтения идентификаторов (ключевых слов, имен переменных, функций и т.д.)
    private Token readIdentifier() {
        StringBuilder identifier = new StringBuilder();
        while (position < code.length() && (Character.isLetterOrDigit(code.charAt(position)) || code.charAt(position) == '_')) {
            identifier.append(code.charAt(position));
            position++;
        }

        // Проверка на ключевые слова
        String id = identifier.toString();
        if (isKeyword(id)) {
            return new Token(TokenType.KEYWORD, id);
        } else {
            return new Token(TokenType.ID, id);
        }
    }

    // Метод для чтения строк
    private Token readString() {
        StringBuilder str = new StringBuilder();
        position++; // Пропускаем начальную кавычку
        while (position < code.length() && code.charAt(position) != '"') {
            str.append(code.charAt(position));
            position++;
        }
        position++; // Пропускаем конечную кавычку
        return new Token(TokenType.STRING, str.toString());
    }

    // Метод для чтения операторов (например, +, -, =)
    private Token readOperator() {
        char currentChar = code.charAt(position);
        position++;
        return new Token(TokenType.OPERATOR, String.valueOf(currentChar));
    }

    // Проверка, является ли символ оператором
    private boolean isOperator(char c) {
        return "+-*/=<>!&|".indexOf(c) != -1;
    }

    // Проверка, является ли строка ключевым словом
    private boolean isKeyword(String word) {
        return word.equals("class") || word.equals("function") || word.equals("int") || word.equals("print");
    }
}
