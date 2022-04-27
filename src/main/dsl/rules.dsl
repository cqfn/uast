IntegerLiteral <- $int$, $String.valueOf(#)$, $Integer.parseInt(#)$, $NumberFormatException$;
Identifier <- $String$, $#$, $#$;

Addition <- left@Expression, right@Expression;
Subtraction <- left@Expression, right@Expression;
Expression <- BinaryExpression | IntegerLiteral | Variable | This;
BinaryExpression <- Addition | Subtraction;
Return <- [expression@Expression];
Variable <- Identifier;
BlockStatement <- {Statement};
Statement <- Return | BlockStatement;
This <- 0;

java:

Synchronized <- Expression, BlockStatement;

IntegerLiteralExpr<#1> -> IntegerLiteral<#1>;
BinaryExpr(#1, #2)<"+"> -> Addition(#1, #2);
BinaryExpr(#1, #2)<"-"> -> Subtraction(#1, #2);
ReturnStmt(#1) -> Return(#1);
NameExpr(SimpleName<#1>) -> Variable(Identifier<#1>);
BlockStmt -> BlockStatement;
SynchronizedStmt(#1, #2) -> Synchronized(#1, #2);
