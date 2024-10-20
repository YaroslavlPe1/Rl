#ifndef LEXER_H
#define LEXER_H

#include <vector>
#include <string>
#include "token.h"

class Lexer {
public:
    Token getNextToken();
    Lexer(const std::string& text) : text(text), position(0), currentChar(text[position]) {}
    std::vector<Token> tokenize();

private:
    std::string text;
    size_t position;
    char currentChar;
    void advance();
    char peek();
    Token number();
};

#endif // LEXER_H
