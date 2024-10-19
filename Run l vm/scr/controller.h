// controller.h
#ifndef CONTROLLER_H
#define CONTROLLER_H

#include <string>

void UTF();
void pcode(const std::string& text);
void debag(const std::string& text);
void error(int err, const std::string& text, const std::string& texta);
void controller(const std::string& text, bool isDefault, bool isDebug);

#endif // CONTROLLER_H
