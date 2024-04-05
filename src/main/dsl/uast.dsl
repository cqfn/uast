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

Program <- {ProgramItem};

ProgramItem <- ClassDeclaration | 0;

ClassDeclaration <- name@Identifier, [body@ClassBody];
ClassBody <- {ClassItem};
ClassItem <- FieldDeclaration | MethodDeclaration;

DataType <- PrimitiveType | Void;
Void <- 0;

FieldDeclaration <- [dataType@DataType], name@Identifier, [initValue@Expression];

MethodDeclaration <- 0;

Statement <- Return | EmptyStatement | StatementBlock;
Return <- [expression@Expression];
EmptyStatement <- 0;
StatementBlock <- {Statement};

Expression <- AssignableExpression | IntegerLiteral | StringLiteral | This | Assignment;
AssignableExpression <- Variable | FieldAccess;
ExpressionList <- {Expression};
This <- 0;
Variable <- name@Identifier;
FieldAccess <- 0;
Assignment <- left@AssignableExpression, right@Expression;

/*
    --- Java -------------------------------------------------------------------------------------
*/

java:

Synchronized <- expression@Expression, body@StatementBlock;
Statement <- & | Synchronized;

IntegerLiteralExpr<#1> -> IntegerLiteral<#1>;

FieldDeclaration(VariableDeclarator(PrimitiveType<#1>, SimpleName<#2>)) -> FieldDeclaration(PrimitiveType<#1>, Identifier<#2>);
FieldDeclaration(VariableDeclarator(PrimitiveType<#1>, SimpleName<#2>, #3)) -> FieldDeclaration(PrimitiveType<#1>, Identifier<#2>, #3);

ClassOrInterfaceDeclaration(SimpleName<#1>) -> ClassDeclaration(Identifier<#1>);
ClassOrInterfaceDeclaration(SimpleName<#1>, #2...) -> ClassDeclaration(Identifier<#1>, ClassBody(#2));

CompilationUnit(#1) -> Program(#1);

/*
    --- Python  ----------------------------------------------------------------------------------
*/

python:

atom(name(literal<"self">)) -> This;
name(literal<#1>) -> Identifier<#1>;

testlist_star_expr(testlist(test(logical_test(comparison(expr(atom(number(integer(literal<#1>))))))))) -> IntegerLiteral<#1>;
testlist_star_expr(testlist(test(logical_test(comparison(expr(atom(Identifier<#1>))))))) -> Variable(Identifier<#1>);
stmt(simple_stmt(small_stmt(#1, assign_part(literal<"=">, #2)))) -> Assignment(#1, #2);

stmt(simple_stmt(small_stmt(literal<"pass">))) -> EmptyStatement;

suite(Assignment(Variable(#1), #2)) -> FieldDeclaration(#1, #2);

stmt(compound_stmt(classdef(Identifier<#1>, suite(EmptyStatement)))) -> ClassDeclaration(Identifier<#1>);
stmt(compound_stmt(classdef(Identifier<#1>, #2...))) -> ClassDeclaration(Identifier<#1>, ClassBody(#2));

file_input(#1...) -> Program(#1);

/*
    --- JavaScript -------------------------------------------------------------------------------
*/

js:

Yield <- Expression;

identifier(literal<#1>) -> Identifier<#1>;
singleExpression(literal(numericLiteral(literal<#1>))) -> IntegerLiteral<#1>;

classElement(propertyName(identifierName(Identifier<#1>)), literal<"=">, #2) -> FieldDeclaration(Identifier<#1>, #2);

sourceElement(statement(classDeclaration(Identifier<#1>, classTail<"{}">))) -> ClassDeclaration(Identifier<#1>);
sourceElement(statement(classDeclaration(Identifier<#1>, classTail(#2...)))) -> ClassDeclaration(Identifier<#1>, ClassBody(#2));

program(sourceElements(#1...)) -> Program(#1);
