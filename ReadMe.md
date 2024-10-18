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
