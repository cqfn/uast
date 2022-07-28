IntegerLiteral <- $int$, $String.valueOf(#)$, $Integer.parseInt(#)$, $NumberFormatException$;
Identifier <- $String$, $#$, $#$;
StringLiteral <- $String$, $#$, $#$;
Modifier <- $String$, $#$, $#$;
PrimitiveType <- $String$, $#$, $#$;

Program <- {ProgramItem};
ProgramItem <- ClassDeclaration | Statement | ClassItem;

UnaryExpression <- PreIncrement | PreDecrement | PostIncrement | PostDecrement | Positive | Negative;
Addition <- left@Expression, right@Expression;
Subtraction <- left@Expression, right@Expression;
Multiplication <- left@Expression, right@Expression;
Division <- left@Expression, right@Expression;
Modulus <-  left@Expression, right@Expression;
PreIncrement <- Expression;
PreDecrement <- Expression;
PostIncrement <- Expression;
PostDecrement <- Expression;
Positive <- Expression;
Negative <- Expression;

Expression <- BinaryExpression | IntegerLiteral | Variable | This | StringLiteral | Identifier | PropertyAccess | FunctionCallExpression | UnaryExpression;
FunctionCallExpression <- [owner@Name], name@Identifier, arguments@ExpressionList;
BinaryExpression <- Addition | Subtraction | Multiplication | Division | Modulus;
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
FunctionDeclaration <- [modifiers@ModifierBlock], [typename@TypeName], name@Identifier, parameters@ParameterBlock, body@StatementBlock;
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
BinaryExpr(Name(#1), Name(#2))<"+"> -> Addition(Variable(Name(#1)), Variable(Name(#2)));
BinaryExpr(#1, #2)<"-"> -> Subtraction(#1, #2);
BinaryExpr(Name(#1), Name(#2))<"-"> -> Subtraction(Variable(Name(#1)), Variable(Name(#2)));
BinaryExpr(#1, #2)<"*"> -> Multiplication(#1, #2);
BinaryExpr(Name(#1), Name(#2))<"*"> -> Multiplication(Variable(Name(#1)), Variable(Name(#2)));
BinaryExpr(#1, #2)<"/"> -> Division(#1, #2);
BinaryExpr(Name(#1), Name(#2))<"/"> -> Division(Variable(Name(#1)), Variable(Name(#2)));
BinaryExpr(#1, #2)<"%"> -> Modulus(#1, #2);
BinaryExpr(Name(#1), Name(#2))<"%"> -> Modulus(Variable(Name(#1)), Variable(Name(#2)));
PrefixIncrement(Name(#1)) -> PreIncrement(Variable(Name(#1)));
PrefixIncrement(#1) -> PreIncrement(#1);
PrefixDecrement(Name(#1)) -> PreDecrement(Variable(Name(#1)));
PrefixDecrement(#1) -> PreDecrement(#1);
PostfixIncrement(Name(#1)) -> PostIncrement(Variable(Name(#1)));
PostfixIncrement(#1) -> PostIncrement(#1);
PostfixDecrement(Name(#1)) -> PostDecrement(Variable(Name(#1)));
PostfixDecrement(#1) -> PostDecrement(#1);
Minus(Name(#1)) -> Negative(Variable(Name(#1)));
Plus(Name(#1)) -> Positive(Variable(Name(#1)));
Minus(#1) -> Negative(#1);
Plus(#1) -> Positive(#1);
ReturnStmt(#1) -> Return(#1);
BlockStmt(#1...) -> StatementBlock(#1);
SynchronizedStmt(#1, #2) -> Synchronized(#1, #2);
SimpleName<#1> -> Identifier<#1>;
NameExpr(#1) -> Name(#1);
FieldAccessExpr(NameExpr(#1), #2) -> Name(Name(#1), #2);
FieldAccessExpr(#1, #2) -> Name(#1, #2);
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
singleExpression(singleExpression(#1), literal<"+">, singleExpression(#2)) -> Addition(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(#1, literal<"-">, #2) -> Subtraction(#1, #2);
singleExpression(singleExpression(#1), literal<"-">, singleExpression(#2)) -> Subtraction(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(#1, literal<"*">, #2) -> Multiplication(#1, #2);
singleExpression(singleExpression(#1), literal<"*">, singleExpression(#2)) -> Multiplication(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(#1, literal<"/">, #2) -> Division(#1, #2);
singleExpression(singleExpression(#1), literal<"/">, singleExpression(#2)) -> Division(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(#1, literal<"%">, #2) -> Modulus(#1, #2);
singleExpression(singleExpression(#1), literal<"%">, singleExpression(#2)) -> Modulus(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(#1, literal<"+">, singleExpression(expressionSequence(#2))) -> Addition(#1, #2);
singleExpression(singleExpression(singleExpression(#1), identifierName(#2)), arguments(argument(#3))) ->
  FunctionCall(Name(#1), #2, ExpressionList(#3));
singleExpression(literal<"++">, singleExpression(#1)) ->  PreIncrement(#1);
singleExpression(literal<"--">, singleExpression(#1)) ->  PreDecrement(#1);
singleExpression(singleExpression(#1), literal<"++">) ->  PostIncrement(#1);
singleExpression(singleExpression(#1), literal<"--">) ->  PostDecrement(#1);
singleExpression(literal<"-">, singleExpression(#1)) ->  Negative(Variable(Name(#1)));
singleExpression(literal<"+">, singleExpression(#1)) ->  Positive(Variable(Name(#1)));
singleExpression(literal<"-">, #1) ->  Negative(#1);
singleExpression(literal<"+">, #1) ->  Positive(#1);
identifier(literal<#1>) -> Identifier<#1>;

functionBody(sourceElements(sourceElement(statement(expressionStatement(expressionSequence(#1)))))) ->
  StatementBlock(#1);
functionBody(sourceElements(sourceElement(statement(#1)))) ->
  StatementBlock(#1);
functionDeclaration(literal<"function">, #1, #2) ->
  FunctionDeclaration(#1, ParameterBlock, #2);

expressionStatement(expressionSequence(singleExpression(singleExpression(#1), arguments))) ->
  FunctionCall(#1, ExpressionList());

program(sourceElements(sourceElement(statement(#1)), sourceElement(statement(#2)))) -> Program(#1, #2);
program(sourceElements(sourceElement(statement(#1)))) -> Program(#1);

returnStatement(literal<"return">, expressionSequence(#1)) -> Return(#1);

python:

self -> This;

expr(atom(literal<#1>)) -> StringLiteral<#1>;
expr(atom(name(literal<#1>))) -> Variable(Name(Identifier<#1>));
expr(atom(integer(literal<#1>))) -> IntegerLiteral<#1>;
expr(atom(literal<"-">, integer(literal<#1>))) -> Negative(IntegerLiteral<#1>);
expr(#1, literal<"+">, #2) -> Addition(#1, #2);
expr(singleExpression(#1), literal<"+">, singleExpression(#2)) -> Addition(Variable(Name(#1)), Variable(Name(#2)));
expr(#1, literal<"-">, #2) -> Subtraction(#1, #2);
expr(#1, literal<"*">, #2) -> Multiplication(#1, #2);
expr(#1, literal<"/">, #2) -> Division(#1, #2);
expr(#1, literal<"%">, #2) -> Modulus(#1, #2);
expr(literal<"+">, #1) -> Positive(#1);
expr(literal<"-">, #1) -> Negative(#1);
expr(atom(name(literal<#1>)), trailer(arguments(arglist(argument(comparison(#2)))))) ->
  FunctionCallExpression(Identifier<#1>, ExpressionList(#2));
expr(atom(literal<#1>), trailer(arguments(arglist(argument(comparison(#2)))))) ->
  FunctionCall(Identifier<#1>, ExpressionList(#2));
expr(atom(name(literal<#1>)), trailer(arguments())) ->
  FunctionCall(Identifier<#1>, ExpressionList());

small_stmt(literal<"return">, comparison(#1)) -> Return(#1);

funcdef(literal<"def">, name(literal<#1>), suite(small_stmt(comparison(#2)))) ->
  FunctionDeclaration(Identifier<#1>, ParameterBlock, StatementBlock(#2));
funcdef(literal<"def">, name(literal<#1>), suite(#2)) ->
  FunctionDeclaration(Identifier<#1>, ParameterBlock, StatementBlock(#2));

file_input(#1, small_stmt(comparison(#2))) -> Program(#1, #2);

file_input(#1) -> Program(#1);