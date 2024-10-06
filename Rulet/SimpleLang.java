import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleLang {

    public static void main(String[] args) {
        // Пример исходного кода
        String sourceCode = """
                import MathLib;

                fun main() {
                    var a = 5;
                    var b = 10;
                    if (a < b) {
                        print("a is less than b");
                    } else {
                        print("a is not less than b");
                    }

                    // Использование функций из MathLib
                    var sum = MathLib.add(a, b);
                    print("Sum: " + sum);
                    
                    var product = MathLib.multiply(a, b);
                    print("Product: " + product);
                    
                    var quotient = MathLib.divide(b, a);
                    print("Quotient: " + quotient);
                }
                """;

        // Инициализация виртуальной машины
        VirtualMachine vm = new VirtualMachine();

        // Загрузка стандартной библиотеки
        vm.loadLibrary(new MathLib());

        // Лексический анализ
        Lexer lexer = new Lexer(sourceCode);
        List<Token> tokens = lexer.tokenize();

        // Парсинг токенов в AST
        ASTBuilder astBuilder = new ASTBuilder(tokens);
        List<ASTNode> ast = astBuilder.buildAST();

        // Выполнение AST
        vm.execute(ast);
    }

    // Класс токена
    static class Token {
        public final TokenType type;
        public final String value;

        public Token(TokenType type, String value) {
            this.type = type;
            this.value = value;
        }
    }

    // Типы токенов
    enum TokenType {
        KEYWORD,
        ID,
        OPERATOR,
        NUMBER,
        STRING,
        EOF,
        IMPORT
    }

    // Лексер
    static class Lexer {
        private final String source;
        private int position = 0;

        public Lexer(String source) {
            this.source = source;
        }

        public List<Token> tokenize() {
            List<Token> tokens = new ArrayList<>();
            while (position < source.length()) {
                char current = source.charAt(position);
                if (Character.isWhitespace(current)) {
                    position++;
                    continue;
                }

                if (Character.isDigit(current)) {
                    tokens.add(new Token(TokenType.NUMBER, consumeNumber()));
                    continue;
                }

                if (Character.isLetter(current)) {
                    String id = consumeIdentifier();
                    if (id.equals("import")) {
                        tokens.add(new Token(TokenType.IMPORT, id));
                    } else if (isKeyword(id)) {
                        tokens.add(new Token(TokenType.KEYWORD, id));
                    } else {
                        tokens.add(new Token(TokenType.ID, id));
                    }
                    continue;
                }

                if (current == '"') {
                    tokens.add(new Token(TokenType.STRING, consumeString()));
                    continue;
                }

                if ("+-*/<>=;(){}".contains(String.valueOf(current))) {
                    tokens.add(new Token(TokenType.OPERATOR, String.valueOf(current)));
                    position++;
                    continue;
                }

                // Обработка запятой
                if (current == ',') {
                    tokens.add(new Token(TokenType.OPERATOR, String.valueOf(current)));
                    position++;
                    continue;
                }

                throw new RuntimeException("Unexpected character: " + current);
            }

            tokens.add(new Token(TokenType.EOF, ""));
            return tokens;
        }

        private String consumeNumber() {
            StringBuilder number = new StringBuilder();
            while (position < source.length() && Character.isDigit(source.charAt(position))) {
                number.append(source.charAt(position++));
            }
            return number.toString();
        }

        private String consumeIdentifier() {
            StringBuilder id = new StringBuilder();
            while (position < source.length() && (Character.isLetterOrDigit(source.charAt(position)) || source.charAt(position) == '_')) {
                id.append(source.charAt(position++));
            }
            return id.toString();
        }

        private String consumeString() {
            position++; // Пропустить открывающую кавычку
            StringBuilder str = new StringBuilder();
            while (position < source.length() && source.charAt(position) != '"') {
                str.append(source.charAt(position++));
            }
            position++; // Пропустить закрывающую кавычку
            return str.toString();
        }

        private boolean isKeyword(String id) {
            return switch (id) {
                case "fun", "if", "else", "var", "for", "while", "do", "return", "break", "continue", "print" -> true;
                default -> false;
            };
        }
    }

    // Узлы абстрактного синтаксического дерева (AST)
    static abstract class ASTNode {
    }

    static class ImportNode extends ASTNode {
        public final String libraryName;

        public ImportNode(String libraryName) {
            this.libraryName = libraryName;
        }
    }

    static class FunctionDeclarationNode extends ASTNode {
        public final String name;
        public final List<VariableDeclarationNode> parameters;
        public final BlockNode body;

        public FunctionDeclarationNode(String name, List<VariableDeclarationNode> parameters, BlockNode body) {
            this.name = name;
            this.parameters = parameters;
            this.body = body;
        }
    }

    static class VariableDeclarationNode extends ASTNode {
        public final String name;
        public final ASTNode initializer;

        public VariableDeclarationNode(String name, ASTNode initializer) {
            this.name = name;
            this.initializer = initializer;
        }
    }

    static class BlockNode extends ASTNode {
        public final List<ASTNode> statements;

        public BlockNode(List<ASTNode> statements) {
            this.statements = statements;
        }
    }

    static class IfStatementNode extends ASTNode {
        public final ASTNode condition;
        public final BlockNode thenBlock;
        public final BlockNode elseBlock;

        public IfStatementNode(ASTNode condition, BlockNode thenBlock, BlockNode elseBlock) {
            this.condition = condition;
            this.thenBlock = thenBlock;
            this.elseBlock = elseBlock;
        }
    }

    static class ForLoopNode extends ASTNode {
        public final VariableDeclarationNode initializer;
        public final ASTNode condition;
        public final ASTNode increment;
        public final BlockNode body;

        public ForLoopNode(VariableDeclarationNode initializer, ASTNode condition, ASTNode increment, BlockNode body) {
            this.initializer = initializer;
            this.condition = condition;
            this.increment = increment;
            this.body = body;
        }
    }

    static class PrintNode extends ASTNode {
        public final ASTNode value;

        public PrintNode(ASTNode value) {
            this.value = value;
        }
    }

    static class ValueNode extends ASTNode {
        public final String value;

        public ValueNode(String value) {
            this.value = value;
        }
    }

    // Класс построения AST
    static class ASTBuilder {
        private final List<Token> tokens;
        private int currentPosition;

        public ASTBuilder(List<Token> tokens) {
            this.tokens = tokens;
            this.currentPosition = 0;
        }

        public List<ASTNode> buildAST() {
            List<ASTNode> ast = new ArrayList<>();

            while (currentPosition < tokens.size()) {
                Token currentToken = tokens.get(currentPosition);
                if (currentToken.type == TokenType.IMPORT) {
                    consume(TokenType.IMPORT);
                    String libraryName = consume(TokenType.ID).value;
                    ast.add(new ImportNode(libraryName)); // Сохраняем импорты библиотек
                } else if (currentToken.type == TokenType.KEYWORD) {
                    switch (currentToken.value) {
                        case "fun" -> ast.add(parseFunction());
                        case "if" -> ast.add(parseIfStatement());
                        case "for" -> ast.add(parseForLoop());
                        case "print" -> ast.add(parsePrintStatement());
                        // Добавьте дополнительные конструкции по мере необходимости
                    }
                }
                currentPosition++;
            }
            return ast;
        }

        private FunctionDeclarationNode parseFunction() {
            consume(TokenType.KEYWORD, "fun");
            String functionName = consume(TokenType.ID).value;

            List<VariableDeclarationNode> parameters = new ArrayList<>();
            consume(TokenType.OPERATOR, "(");
            consume(TokenType.OPERATOR, ")");
            BlockNode body = parseBlock();
            return new FunctionDeclarationNode(functionName, parameters, body);
        }

        private BlockNode parseBlock() {
            consume(TokenType.OPERATOR, "{");
            List<ASTNode> statements = new ArrayList<>();

            while (currentPosition < tokens.size() && !tokens.get(currentPosition).value.equals("}")) {
                statements.add(buildAST().get(0)); // Парсим и добавляем инструкции
            }
            consume(TokenType.OPERATOR, "}");
            return new BlockNode(statements);
        }

        private IfStatementNode parseIfStatement() {
            consume(TokenType.KEYWORD, "if");
            consume(TokenType.OPERATOR, "(");
            ASTNode condition = new ValueNode(consume(TokenType.NUMBER).value); // Упрощенное условие
            consume(TokenType.OPERATOR, ")");
            BlockNode thenBlock = parseBlock();
            BlockNode elseBlock = null;
            if (currentPosition < tokens.size() && tokens.get(currentPosition).value.equals("else")) {
                consume(TokenType.KEYWORD, "else");
                elseBlock = parseBlock();
            }
            return new IfStatementNode(condition, thenBlock, elseBlock);
        }

        private ForLoopNode parseForLoop() {
            consume(TokenType.KEYWORD, "for");
            consume(TokenType.OPERATOR, "(");
            VariableDeclarationNode initializer = parseVariableDeclaration();
            ASTNode condition = new ValueNode(consume(TokenType.NUMBER).value); // Упрощенное условие
            ASTNode increment = new ValueNode(consume(TokenType.NUMBER).value); // Упрощенный инкремент
            consume(TokenType.OPERATOR, ")");
            BlockNode body = parseBlock();
            return new ForLoopNode(initializer, condition, increment, body);
        }

        private VariableDeclarationNode parseVariableDeclaration() {
            consume(TokenType.KEYWORD, "var");
            String varName = consume(TokenType.ID).value;
            consume(TokenType.OPERATOR, "=");
            ASTNode initializer = new ValueNode(consume(TokenType.NUMBER).value); // Упрощенная инициализация
            return new VariableDeclarationNode(varName, initializer);
        }

        private PrintNode parsePrintStatement() {
            consume(TokenType.KEYWORD, "print");
            consume(TokenType.OPERATOR, "(");
            ASTNode value = new ValueNode(consume(TokenType.STRING).value); // Упрощенное значение
            consume(TokenType.OPERATOR, ")");
            return new PrintNode(value);
        }

        private void consume(TokenType type) {
            if (currentPosition < tokens.size() && tokens.get(currentPosition).type == type) {
                currentPosition++;
            } else {
                throw new RuntimeException("Expected token type: " + type);
            }
        }

        private void consume(TokenType type, String value) {
            if (currentPosition < tokens.size() && tokens.get(currentPosition).type == type &&
                    tokens.get(currentPosition).value.equals(value)) {
                currentPosition++;
            } else {
                throw new RuntimeException("Expected token: " + value);
            }
        }
    }

    // Виртуальная машина
    static class VirtualMachine {
        private final Map<String, Object> variables = new HashMap<>();
        private final List<MathLib> libraries = new ArrayList<>();

        public void loadLibrary(MathLib library) {
            libraries.add(library);
        }

        public void execute(List<ASTNode> ast) {
            for (ASTNode node : ast) {
                if (node instanceof PrintNode printNode) {
                    System.out.println(evaluate(printNode.value));
                }
            }
        }

        private Object evaluate(ASTNode node) {
            if (node instanceof ValueNode valueNode) {
                return valueNode.value;
            }
            return null; // Добавьте другие типы узлов по мере необходимости
        }
    }

    // Пример библиотеки
    static class MathLib {
        public int add(int a, int b) {
            return a + b;
        }

        public int multiply(int a, int b) {
            return a * b;
        }

        public double divide(double a, double b) {
            if (b == 0) {
                throw new ArithmeticException("Cannot divide by zero");
            }
            return a / b;
        }
    }
}
