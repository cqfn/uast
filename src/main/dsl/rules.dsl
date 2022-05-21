IntegerLiteral <- $int$, $String.valueOf(#)$, $Integer.parseInt(#)$, $NumberFormatException$;
Identifier <- $String$, $#$, $#$;
StringLiteral <- $String$, $#$, $#$;
Modifier <- $String$, $#$, $#$;
PrimitiveType <- $String$, $#$, $#$;

Program <- {ProgramItem};
ProgramItem <- ClassDeclaration | Statement | ClassItem;
Addition <- left@Expression, right@Expression;
Subtraction <- left@Expression, right@Expression;
Expression <- BinaryExpression | IntegerLiteral | Variable | This | StringLiteral | Identifier | PropertyAccess | FunctionCallExpression;
FunctionCallExpression <- [owner@Name], name@Identifier, arguments@ExpressionList;
BinaryExpression <- Addition | Subtraction;
Statement <- Return | StatementBlock | FunctionCall;
TypeName <- ArrayType | PrimitiveType | ClassType | VoidType;
ClassType <- Name;
ArrayType <- TypeName, DimensionList;
DimensionList <- {Dimension};
Dimension <- [Expression];
Return <- [expression@Expression];
Name <- [composition@Name], last@Identifier;
Variable <- Name;
StatementBlock <- {Statement};
This <- 0;
VoidType <- 0;
PropertyAccess <- left@Expression, right@Expression;
ModifierBlock <- {Modifier};
ExpressionList <- {Expression};
FunctionCall <- [owner@Name], name@Identifier, arguments@ExpressionList;
Parameter <- TypeName, Identifier;
FunctionDeclaration <- [ModifierBlock], [TypeName], Identifier, ParameterBlock, StatementBlock;
ParameterBlock <- {Parameter};
ClassDeclaration <- [ModifierBlock], Identifier, ClassBody;
ClassBody <- {ClassItem};
ClassItem <- FunctionDeclaration | VariableDeclaration;
VariableDeclaration <- [ModifierBlock], TypeName, DeclaratorList;
DeclaratorList <- {Declarator};
Declarator <- name@Identifier, value@Expression;

java:

Synchronized <- Expression, StatementBlock;
Statement <- & | Synchronized;

IntegerLiteralExpr<#1> -> IntegerLiteral<#1>;
BinaryExpr(#1, #2)<"+"> -> Addition(#1, #2);
BinaryExpr(#1, #2)<"-"> -> Subtraction(#1, #2);
SimpleName<#1> -> Identifier<#1>;
ReturnStmt(#1) -> Return(#1);
BlockStmt(#1...) -> StatementBlock(#1);
SynchronizedStmt(#1, #2) -> Synchronized(#1, #2);
FieldAccessExpr(NameExpr(#1), #2) -> Name(Name(#1), #2);
StringLiteralExpr<#1> -> StringLiteral<#1>;
EnclosedExpr(Addition(#1, #2)) -> Addition(#1, #2);
BinaryExpr(#1, EnclosedExpr(#2))<"+"> -> Addition(#1, #2);
MethodCallExpr(#1, #2, #3) -> FunctionCall(#1, #2, ExpressionList(#3));
BlockStmt(ExpressionStmt(#1)) -> StatementBlock(#1);
ClassOrInterfaceType(#1) -> ClassType(Name(#1));
ArrayType(#1) -> ArrayType(#1, DimensionList(Dimension));
Parameter(#1, #2) -> Parameter(#1, #2);
VoidType -> VoidType;
MethodDeclaration(Modifier<#1>, Modifier<#2>, #3, #4, #5, #6) ->
  FunctionDeclaration(ModifierBlock(Modifier<#1>, Modifier<#2>), #5, #3, ParameterBlock(#4), #6);
ClassOrInterfaceDeclaration(Modifier<#1>, #2, #3) ->
  ClassDeclaration(ModifierBlock(Modifier<#1>), #2, ClassBody(#3));
CompilationUnit(#1) -> Program(#1);

js:

Yield <- Expression;

singleExpression(literal(numericLiteral(literal<#1>))) -> IntegerLiteral<#1>;
singleExpression(literal(literal<#1>)) -> StringLiteral<#1>;
singleExpression(#1, literal<"+">, #2) -> Addition(#1, #2);
singleExpression(#1, literal<"+">, singleExpression(expressionSequence(#2))) -> Addition(#1, #2);
singleExpression(singleExpression(singleExpression(#1), identifierName(#2)), arguments(argument(#3))) ->
  FunctionCall(Name(#1), #2, ExpressionList(#3));

identifier(literal<#1>) -> Identifier<#1>;

functionBody(sourceElements(sourceElement(statement(expressionStatement(expressionSequence(#1)))))) ->
  StatementBlock(#1);
functionDeclaration(literal<"function">, #1, #2) ->
  FunctionDeclaration(#1, ParameterBlock, #2);

expressionStatement(expressionSequence(singleExpression(singleExpression(#1), arguments))) ->
  FunctionCall(#1, ExpressionList());

program(sourceElements(sourceElement(statement(#1)), sourceElement(statement(#2)))) -> Program(#1, #2);

python:

self -> This;

expr(atom(literal<#1>)) -> StringLiteral<#1>;
expr(atom(integer(literal<#1>))) -> IntegerLiteral<#1>;
expr(#1, literal<"+">, #2) -> Addition(#1, #2);
expr(atom(name(literal<#1>)), trailer(arguments(arglist(argument(comparison(#2)))))) ->
  FunctionCallExpression(Identifier<#1>, ExpressionList(#2));
expr(atom(literal<#1>), trailer(arguments(arglist(argument(comparison(#2)))))) ->
  FunctionCall(Identifier<#1>, ExpressionList(#2));
expr(atom(name(literal<#1>)), trailer(arguments())) ->
  FunctionCall(Identifier<#1>, ExpressionList());

funcdef(literal<"def">, name(literal<#1>), suite(small_stmt(comparison(#2)))) ->
  FunctionDeclaration(Identifier<#1>, ParameterBlock, StatementBlock(#2));

file_input(#1, small_stmt(comparison(#2))) -> Program(#1, #2);