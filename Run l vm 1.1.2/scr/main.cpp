#include "controller.h"
#include <iostream>

int main() {
    std::string input = "3.14 + (1.75 - 0.99) * 2.25";
    controller(input, true, false);
    return 0;
}
