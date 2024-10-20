#ifndef PARSER_H
#define PARSER_H

#include "token.h"
#include <vector>

enum class Precedence {
    NONE,
    SUM,        // +
    SUBTRACT,   // -
    MULTIPLY,   // *
    DIVIDE,     // /
    GROUPING    // ()
};

class Parser {
public:
    Parser(const std::vector<Token>& tokens) : tokens(tokens), currentTokenIndex(0) {}

    double parse(); // Измените возвращаемый тип на double

private:
    std::vector<Token> tokens;
    size_t currentTokenIndex;

    Token currentToken() {
        return tokens[currentTokenIndex];
    }

    void advance();
    double factor();
    double term();
    double expression();
    Precedence getPrecedence(TokenType type); // Функция для получения приоритета
};

#endif // PARSER_H
