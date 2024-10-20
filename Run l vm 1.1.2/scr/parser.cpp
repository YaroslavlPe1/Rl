#include "parser.h"
#include <stdexcept>
#include <string>
#include "token.h"
#include <iostream>

void Parser::advance() {
    if (currentTokenIndex < tokens.size()) {
        currentTokenIndex++;
    }
}

double Parser::factor() {
    Token token = currentToken();

    if (token.type == TokenType::NUMBER) {
        advance();
        return std::stod(token.value);
    } else if (token.type == TokenType::LPAREN) {
        advance();
        double result = expression();
        if (currentToken().type != TokenType::RPAREN) {
            throw std::runtime_error("Expected closing parenthesis");
        }
        advance();
        return result;
    } else if (token.type == TokenType::ID) {
        advance();
        return 0.0;
    }

    throw std::runtime_error("Unexpected token: " + token.value + " (type: " + std::to_string(static_cast<int>(token.type)) + ")");
}

double Parser::term() {
    double result = factor();
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
    double result = term();
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

Precedence Parser::getPrecedence(TokenType type) {
    switch (type) {
        case TokenType::PLUS:
        case TokenType::MINUS:
            return Precedence::SUM;
        case TokenType::MULTIPLY:
        case TokenType::DIVIDE:
            return Precedence::MULTIPLY;
        case TokenType::LPAREN:
        case TokenType::RPAREN:
            return Precedence::GROUPING;
        default:
            return Precedence::NONE;
    }
}

double Parser::parse() {
    return expression();
}
