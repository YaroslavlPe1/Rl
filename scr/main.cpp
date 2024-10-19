#include "lexer.h"
#include "controller.h"
#include <iostream>

int main() {
    std::string input = "1 + 2 * 3 - 4";
    controller(input, false, true);
    return 0;
}
