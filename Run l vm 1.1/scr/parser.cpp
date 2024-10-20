#include "parser.h"
#include <stdexcept>
#include <string>
#include "token.h"
#include <iostream>
#include <stdexcept>

void Parser::advance() {
    if (currentTokenIndex < tokens.size()) {
        currentTokenIndex++;
    }
}

int Parser::factor() {
    Token token = currentToken();

    if (token.type == TokenType::NUMBER) {
        advance();
        return std::stoi(token.value);
    } else if (token.type == TokenType::LPAREN) {
        advance();
        int result = expression();
        if (currentToken().type != TokenType::RPAREN) {
            throw std::runtime_error("Expected closing parenthesis");
        }
        advance();
        return result;
    }

    throw std::runtime_error("" + token.value + " (type: " + std::to_string(static_cast<int>(token.type)) + ")");
}




int Parser::term() {
    int result = factor();
    while (currentToken().type == TokenType::MULTIPLY || currentToken().type == TokenType::DIVIDE) {
        Token token = currentToken();
        advance();
        if (token.type == TokenType::MULTIPLY) {
            result *= factor();
        } else {
            result /= factor();
        }
    }
    return result;
}

int Parser::expression() {
    int result = term();
    while (currentToken().type == TokenType::PLUS || currentToken().type == TokenType::MINUS) {
        Token token = currentToken();
        advance();
        if (token.type == TokenType::PLUS) {
            result += term();
        } else {
            result -= term();
        }
    }
    return result;
}

int Parser::parse() {
    return expression();
}
