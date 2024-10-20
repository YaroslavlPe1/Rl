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

double Parser::factor() {
    Token token = currentToken();

    if (token.type == TokenType::NUMBER) {
        advance();
        return std::stod(token.value); // Используем std::stod для конвертации строки в double
    } else if (token.type == TokenType::LPAREN) {
        advance();
        double result = expression(); // Изменяем int на double
        if (currentToken().type != TokenType::RPAREN) {
            throw std::runtime_error("Expected closing parenthesis");
        }
        advance();
        return result;
    }

    throw std::runtime_error("" + token.value + " (type: " + std::to_string(static_cast<int>(token.type)) + ")");
}

double Parser::term() {
    double result = factor(); // Используем double вместо int
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

double Parser::expression() {
    double result = term(); // Используем double вместо int
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

double Parser::parse() {
    return expression(); // Возвращаем double
}
