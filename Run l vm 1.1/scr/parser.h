#ifndef PARSER_H
#define PARSER_H

#include "token.h"
#include <vector>

class Parser {
public:
    Parser(const std::vector<Token>& tokens) : tokens(tokens), currentTokenIndex(0) {}

    int parse();

private:
    std::vector<Token> tokens;
    size_t currentTokenIndex;

    Token currentToken() {
        return tokens[currentTokenIndex];
    }

    void advance();
    int factor();
    int term();
    int expression();
};

#endif // PARSER_H
