IntegerLiteral <- $int$, $String.valueOf(#)$, $Integer.parseInt(#)$, $NumberFormatException$;

Addition <- left@Expression, right@Expression;
Subtraction <- left@Expression, right@Expression;
Expression <- BinaryExpression | IntegerLiteral;
BinaryExpression <- Addition | Subtraction;

java:

IntegerLiteralExpr<#1> -> IntegerLiteral<#1>;
BinaryExpr(#1, #2)<"+"> -> Addition(#1, #2);
