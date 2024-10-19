#include "controller.h"
#include "lexer.h"
#include <iostream>
#include <fstream>
#include <sstream>

#ifdef _WIN32
#include <windows.h>
#endif

void UTF() {
#ifdef _WIN32
    SetConsoleOutputCP(CP_UTF8);
#endif
}

void enderr(int errend) {
    UTF();
    std::cout << "\nProcess finished with exit code " << errend << std::endl;
}

void error(int err, const std::string& text, const std::string& texta) {
    UTF();
    const std::string site = "https://github.com/YaroslavlPe1/Rl/blob/main/Error%20code.md";
    std::cout << text << "\n"
              << "Код ошибки: " << err << "\n"
              << "Подробнее на сайте: " << site << "\n\n"
              << texta << "\n"
              << "Error code: " << err << "\n"
              << "Learn more at: " << site << "\n"
              << "\n\nProcess finished with exit code " << err << std::endl;
}

void pcode(const std::string& input) {
    Lexer lexer(input);
    std::vector<Token> tokens = lexer.tokenize();

    // Допустим, здесь идет обработка полученных токенов
    // ...

    enderr(0);
}

void debag(const std::string& input) {
    Lexer lexer(input);
    std::vector<Token> tokens = lexer.tokenize();

    UTF();
    std::cout << "Debug mode: Token list:\n";
    for (const Token& token : tokens) {
        std::cout << "Token(Type: " << static_cast<int>(token.type) << ", Value: \"" << token.value << "\")\n";
    }

    // Обработка после вывода токенов
    // ...

    enderr(0);
}

enum class Mode {
    Default,
    Debug,
    None
};

Mode determineMode(bool isDefault, bool isDebug) {
    if (isDefault && isDebug) {
        return Mode::None;
    } else if (!isDefault && !isDebug) {
        return Mode::None;
    } else if (isDefault) {
        return Mode::Default;
    } else {
        return Mode::Debug;
    }
}

void controller(const std::string& text, bool isDefault, bool isDebug) {
    Mode mode = determineMode(isDefault, isDebug);

    switch (mode) {
        case Mode::None:
            error(2, "Ошибка: не выбран режим работы!", "Error: no mode selected!");
            return;
        case Mode::Default:
            pcode(text);
            break;
        case Mode::Debug:
            debag(text);
            break;
    }
}
