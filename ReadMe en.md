# Hi, you want to use a simple language but you don't know which one 🌅

## try the programming language Rulet🎂

```
🥳🥳🥳
This is the first version that does not know much, but soon there will be a release of a full virtual machine and language.
```

# The syntax is the same as in C++

## Keywords Rulet 🎂
### In the future 
### Data types
- **`bool`**: 🔄 A logical data type that takes the values `true` or `false'.
- **`char`**: 🅰️ Data type for storing characters (1 byte).
- **`char16_t`**, **`char32_t`**: 🔠 Types for storing characters in UTF-16 and UTF-32 encodings.
- **`wchar_t`**: 🔡 Data type for wide characters.
- **`int`**: 🔢 Integer data type.
- **`float`**: 🌊 Single precision floating point number.
- **`double`**: 🌊🌊 Double precision floating point number.
- **`void`**: 🚫 Data type for functions that do not return a value.
- **`long`**, **`short`**: 📏 Length modifiers for integers.
- **`signed`**, **`unsigned`**: ➕➖ Modifiers for marking signed and unsigned integers.

### Flow control
- **`if`**, **`else`**: ❓ Conditional execution operators.
- **`switch`**, **`case`**: 🔀 Multiple selection to switch between values.
- **`while`**, **`do`**: 🔁 Loops with execution condition.
- **`for`**: 🔄 A loop with predefined iterations.
- **`break`**: 🛑 Interrupts the execution of the loop or `switch`.
- **`continue`**: ⏩ Proceeds to the next iteration of the loop.
- **`goto`**: 🚦 Jump to the label in the code.

### Functions and OOP
- **`class`**: 🏛 Class definition.
- **`struct`**: 🏗 Is similar to `class`, but with public members by default.
- **`public`**, **`private`**, **`protected`**: 🔓 Access modifiers for class members.
- **`friend`**: 👫 Provides access to private class members of another function or class.
- **`virtual`**: 🌐 Definition of virtual functions for polymorphism.
- **`inline`**: 🏎 Optimization of functions to embed them in the call location.
- **`operator`**: 🔧 Operator overloading to work with custom data types.
- **`new`**, **`delete`**: 🗄 Dynamic memory allocation and deallocation operators.
- **`typedef`**: 🏷 Creating aliases for data types.
- **`namespace`**: 🌌 Organizing code into namespaces to avoid name conflicts.

### Exceptions and error handling
- **`try`**, **`catch`**, **`throw`**: 🛑 Keywords for exception handling.
- **`false`**, **`true`**: ✅❌ Logical constants.
- **`nullptr`**: 🚫🔗 A constant for pointing to a null pointer.

### Modifiers and specifiers
- **`const`**: 🔒 Indicates that the value of the variable cannot be changed.
- **`mutable`**: 🔄 Allows you to change class members, even if the object is declared `const'.
- **`constexpr`**: 🛠 Operations that can be calculated at the compilation stage.
- **`volatile`**: ⚡ Denotes variables that can be changed externally (for example, by hardware interrupts).
- **`register`**: 🚀 Recommendation to store the variable in the processor register.

### Type Conversions
- **`dynamic_cast`**, **`static_cast`**, **`const_cast`**, **`reinterpret_cast`**: 🔄 Operators for various type conversions.

### Templates and metaprogramming
- **`template`**: 📝 Defining class and function templates for working with different data types.
- **`typename`**: 🔠 Indicates the data type in the templates.
- **`decltype`**: 🔍 Defining the type of expression at the compilation stage.

### Preprocessor and assembler
- **`asm`**: ⚙️ Inserting assembly code.
- **`export`**: 🚢 Exporting templates (rarely used).
- **`extern`**: 🌍 Indicates that a variable or function is defined in another file or module.
- **`inline`**: 🏎 Optimization for embedding functions.
- **`noexcept`**: ❌ Indicates that the function does not generate exceptions.
- **`static_assert`**: 🛠 Validation of statements at the compilation stage.
- **`alignas`**, **`alignof`**: 📐 Control the alignment of data in memory.
- **`sizeof`**: 📏 Operator for determining the size of an object in bytes.

### Miscellaneous
- **`this`**: 👆 Pointer to the current object in the methods of the class.
- **`return`**: 🔙 Returns the value from the function.
- **`auto`**: 🤖 Allows the compiler to automatically output the type of a variable.
- **`thread_local`**: 🧵 Defines variables local to each thread.

### Logical and bitwise operators
- **`and`**, **`or`**, **`not`**: 🔗 Logical operators (alternatives `&&`, `||`, `!`).
- **`bitand`**, **`bitor`**, **`compl`**: 🔧 Bitwise operators.
- **`xor`**, **`xor_eq`**: 🔄 Bitwise exclusive OR and its assignment version.
- **`and_eq`**, **`or_eq`**, **`not_eq`**: 🔄 Alternative entries of assignment operators `&=`, `|=`, `!=`.

# Now 
## Rulet Keywords 🎂

# Syntax of a custom programming language 📜

## Overview 🌐
This document describes the syntax and functions of a custom programming language inspired by C++. It includes input and output processing, variable assignment, and operator structure.

---

## Syntax features ✨

### Keywords 🔑
- **int**: Defines an integer variable.
- **void**: Indicates that the function does not return a value.
- **cin**: For input operations.
- **cout**: For output operations.
- **if**: For conditional statements.
- **else**: For alternative execution.
- **while**: For loops as long as the condition is true.
- **for**: For cycles with a certain number of repetitions.
- **break**: Exit the loop.
- **continue**: Skipping the current iteration of the loop.
- **class**: Defines the class.
- **public**: Access modifier for classes and their members.

### Declaring a variable 🔄
``cpp
int x; // Declaring an integer variable
``
