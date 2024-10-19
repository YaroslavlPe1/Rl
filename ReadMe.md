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



### Это  список имен токенов:
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
### Например:
#### Код :
1 + 2 * 3 - 4
#### Вывод токенов можно при включении Debug режима.
```cpp
Token(Type: 0, Value: "1")
Token(Type: 1, Value: "+")
Token(Type: 0, Value: "2")
Token(Type: 3, Value: "*")
Token(Type: 0, Value: "3")
Token(Type: 2, Value: "-")
Token(Type: 0, Value: "4")
Token(Type: 7, Value: "")
```

