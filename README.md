# MC-language
Note: This is my assignment via course Principle of Programming Language taught by Dr Nguyen Hua Phung. 
The task is that writing a compiler for a programming language named MC.

## Warning
```diff
-This is for reference only. Do not copy it for your any assignment in class. However, feel free to improve it.
```

## Introduction
MC (Mini C) is a language which consists of a subset of C plus some Java language features. 
The C features of this language are (details will be discussed later): a few primitive types, one-dimensional arrays, 
control structures, expressions, compound statements (i.e., blocks) and functions. Any further check out MC.pdf

## How to Test
- Install Java JDK  if it haven't been installed. Make sure that javac can be run on your machine 
(open Command Prompt on Windows or Terminal on Linux or MacOS and type javac - if it does not run, 
you must set the value of the path variable to folder bin)
- Install sbt (http://www.scala-sbt.org),
- If you want to use IDE, please install IntelliJ IDEA (https://www.jetbrains.com/idea/) or Code editor like Visual Studio code or Sublime Text,

- Clone the project move directory to root project , meaning MC-language, and type in Terminal:

```
sbt
```
- It will appear like this:

```
sbt:mc>
```

- Test Lexer:
```
sbt:mc>testOnly LexerSuite
```

- Test Parser:
```
sbt:mc>testOnly ParserSuite
```

- Test Ast:
```
sbt:mc>testOnly AstSuite
```

- Test Checker:
```
sbt:mc>testOnly CheckerSuite
```

- Test Code Generator:
```
sbt:mc>testOnly CodeGenSuite
```
