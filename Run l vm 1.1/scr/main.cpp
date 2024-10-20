#include "controller.h"
#include <iostream>

int main() {
    std::string input = "(10 + 4) * 32";
    controller(input, true, false);
    return 0;
}
