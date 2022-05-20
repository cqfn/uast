IntegerLiteral <- $int$, $String.valueOf(#)$, $Integer.parseInt(#)$, $NumberFormatException$;
Identifier <- $String$, $#$, $#$;
StringLiteral <- $String$, $#$, $#$;
Modifier <- $String$, $#$, $#$;

Addition <- left@Expression, right@Expression;
Subtraction <- left@Expression, right@Expression;
Expression <- BinaryExpression | IntegerLiteral | Variable | This | StringLiteral | Identifier | PropertyAccess;
BinaryExpression <- Addition | Subtraction;
Return <- [expression@Expression];
Name <- [composition@Name], last@Identifier;
Variable <- Name;
StatementBlock <- {Statement};
Statement <- Return | StatementBlock;
This <- 0;
PropertyAccess <- left@Expression, right@Expression;
ModifierBlock <- {Modifier};
ExpressionList <- {Expression};
FunctionCall <- [owner@Name], name@Identifier, arguments@ExpressionList;

java:

Synchronized <- Expression, StatementBlock;
Statement <- & | Synchronized;

IntegerLiteralExpr<#1> -> IntegerLiteral<#1>;
BinaryExpr(#1, #2)<"+"> -> Addition(#1, #2);
BinaryExpr(#1, #2)<"-"> -> Subtraction(#1, #2);
ReturnStmt(#1) -> Return(#1);
BlockStmt(#1...) -> StatementBlock(#1);
SynchronizedStmt(#1, #2) -> Synchronized(#1, #2);
FieldAccessExpr(NameExpr(SimpleName<#1>), SimpleName<#2>) -> Name(Name(Identifier<#1>), Identifier<#2>);
StringLiteralExpr<#1> -> StringLiteral<#1>;
EnclosedExpr(Addition(#1, #2)) -> Addition(#1, #2);
BinaryExpr(#1, EnclosedExpr(#2))<"+"> -> Addition(#1, #2);
This <- 0;
FunctionCall <- [owner@Name], name@Identifier, arguments@ExpressionList;

js:

Yield <- Expression;

singleExpression(literal(numericLiteral(literal<#1>))) -> IntegerLiteral<#1>;
singleExpression(literal(literal<#1>)) -> StringLiteral<#1>;
singleExpression(#1, literal<"+">, #2) -> Addition(#1, #2);

python:

self -> This;
