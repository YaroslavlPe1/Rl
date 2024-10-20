cmake_minimum_required(VERSION 3.10)

project(Run_l_vm)

set(CMAKE_CXX_STANDARD 17)

add_executable(Run_l_vm
        scr/main.cpp
        scr/lexer.h
        scr/lexer.cpp
        scr/controller.cpp
        scr/Token.h
        scr/parser.h
        scr/parser.cpp
        # Добавляем файл с реализацией виртуальной машины
)
