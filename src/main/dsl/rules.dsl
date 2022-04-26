IntegerLiteral <- $int$, $String.valueOf(#)$, $Integer.parseInt(#)$, $NumberFormatException$;

Addition <- left@Expression, right@Expression;
Subtraction <- left@Expression, right@Expression;
Expression <- BinaryExpression | IntegerLiteral;
BinaryExpression <- Addition | Subtraction;
Return <- [expression@Expression];

java:

IntegerLiteralExpr<#1> -> IntegerLiteral<#1>;
BinaryExpr(#1, #2)<"+"> -> Addition(#1, #2);
BinaryExpr(#1, #2)<"-"> -> Subtraction(#1, #2);
ReturnStmt(#1) -> Return(#1);
