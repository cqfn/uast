/*
    The MIT License (MIT)

    Copyright (c) 2024 Ivan Kniazkov

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included
    in all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
*/

/*
    --- Unified AST description ------------------------------------------------------------------
*/

IntegerLiteral <- $int$, $String.valueOf(#)$, $Integer.parseInt(#)$, $NumberFormatException$;
Identifier <- $String$, $#$, $#$;
StringLiteral <- $String$, $#$, $#$;
PrimitiveType <- $String$, $#$, $#$;

Statement <- Return | StatementBlock;
Return <- [expression@Expression];
StatementBlock <- {Statement};

Expression <- IntegerLiteral | StringLiteral | This;
ExpressionList <- {Expression};
This <- 0;

/*
    --- Java -------------------------------------------------------------------------------------
*/

java:

Synchronized <- expression@Expression, body@StatementBlock;
Statement <- & | Synchronized;

/*
    --- Python  ----------------------------------------------------------------------------------
*/

python:

atom(name(literal<"self">)) -> This;

/*
    --- JavaScript -------------------------------------------------------------------------------
*/

js:

Yield <- Expression;
