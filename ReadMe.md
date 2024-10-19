## Режимы
Default — это обычная обработка кода и его выполнения.
```cpp
controller(myText,true,false);
```
true - значения для работы в режиме Default 
Если писать так:
```cpp
controller(myText);
```
То режим по умолчанию режим Default.

Debug — это обработка с откладкой кода и вывод токенов и процессов.
```cpp
controller(myText,false,true);
```
true - значения для работы в режиме Debug 


``` cpp
    NUMBER    -   0
    PLUS      -   1
    MINUS     -   2
    MULTIPLY  -   3
    DIVIDE    -   4
    LPAREN    -   5
    RPAREN    -   6
    END       -   7
```
