IntegerLiteral <- $int$, $String.valueOf(#)$, $Integer.parseInt(#)$, $NumberFormatException$;
Identifier <- $String$, $#$, $#$;
StringLiteral <- $String$, $#$, $#$;
Modifier <- $String$, $#$, $#$;
PrimitiveType <- $String$, $#$, $#$;

Program <- {ProgramItem};
ProgramItem <- ClassDeclaration | Statement | ClassItem;

Expression <- BinaryExpression | IntegerLiteral | This | StringLiteral | Identifier |
 FunctionCall | UnaryExpression | BitwiseExpression | LogicalExpression | AssignableExpression | Assignment | ParenthesizedExpression;
ArithmeticExpression <- Addition | Subtraction | Multiplication | Division | Modulus;
BinaryExpression <-  ArithmeticExpression | RelationalExpression;
RelationalExpression <- IsEqualTo | NotEqualTo | GreaterThan | LessThan | GreaterThanOrEqualTo | LessThanOrEqualTo;
Statement <- Return | StatementBlock | VariableDeclaration | ExpressionStatement;
TypeName <- ArrayType | PrimitiveType | ClassType | VoidType;
UnaryExpression <- PreIncrement | PreDecrement | PostIncrement | PostDecrement | Positive | Negative;
BitwiseExpression <- BitwiseComplement | LeftShift | RightShift | UnsignedRightShift | BitwiseAnd | BitwiseOr | ExclusiveOr;
LogicalExpression <- LogicalAnd | LogicalOr | LogicalNot;
Assignment <- SimpleAssignment | AdditionAssignment | SubtractionAssignment | MultiplicationAssignment | DivisionAssignment
    | ModulusAssignment | BitwiseAndAssignment | BitwiseOrAssignment | ExclusiveOrAssignment
    | RightShiftAssignment | UnsignedRightShiftAssignment | LeftShiftAssignment;
AssignableExpression <- Variable | PropertyAccess;

ExpressionStatement <- expression@Expression;
ParenthesizedExpression <- expression@Expression;

Addition <- left@Expression, right@Expression;
Subtraction <- left@Expression, right@Expression;
Multiplication <- left@Expression, right@Expression;
Division <- left@Expression, right@Expression;
Modulus <-  left@Expression, right@Expression;

IsEqualTo <- left@Expression, right@Expression;
NotEqualTo <- left@Expression, right@Expression;
GreaterThan <- left@Expression, right@Expression;
LessThan <- left@Expression, right@Expression;
GreaterThanOrEqualTo <- left@Expression, right@Expression;
LessThanOrEqualTo <- left@Expression, right@Expression;

BitwiseComplement <- Expression;
BitwiseAnd <- left@Expression, right@Expression;
BitwiseOr <- left@Expression, right@Expression;
ExclusiveOr <- left@Expression, right@Expression;
LeftShift <- left@Expression, right@Expression;
RightShift <- left@Expression, right@Expression;
UnsignedRightShift <- left@Expression, right@Expression;

LogicalAnd <- left@Expression, right@Expression;
LogicalOr <- left@Expression, right@Expression;
LogicalNot <- Expression;

PreIncrement <- Expression;
PreDecrement <- Expression;
PostIncrement <- Expression;
PostDecrement <- Expression;
Positive <- Expression;
Negative <- Expression;

SimpleAssignment <- left@AssignableExpression, right@Expression;
AdditionAssignment <- left@AssignableExpression, right@Expression;
SubtractionAssignment <- left@AssignableExpression, right@Expression;
MultiplicationAssignment <- left@AssignableExpression, right@Expression;
DivisionAssignment <- left@AssignableExpression, right@Expression;
ModulusAssignment <- left@AssignableExpression, right@Expression;
BitwiseAndAssignment <- left@AssignableExpression, right@Expression;
BitwiseOrAssignment <- left@AssignableExpression, right@Expression;
ExclusiveOrAssignment <- left@AssignableExpression, right@Expression;
UnsignedRightShiftAssignment <- left@AssignableExpression, right@Expression;
RightShiftAssignment <- left@AssignableExpression, right@Expression;
LeftShiftAssignment <- left@AssignableExpression, right@Expression;

ClassType <- name@Name;
ArrayType <- base@TypeName, dimensions@DimensionList;
DimensionList <- {Dimension};
Dimension <- [expression@Expression];
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
Parameter <- [modifiers@ModifierBlock], [datatype@TypeName], name@Identifier;
FunctionDeclaration <- [modifiers@ModifierBlock], [datatype@TypeName], name@Identifier, parameters@ParameterBlock, body@StatementBlock;
ParameterBlock <- {Parameter};
ClassDeclaration <- [modifiers@ModifierBlock], name@Identifier, [extendsbl@ExtendsBlock], [implementsbl@ImplementsBlock], body@ClassBody;
ExtendsBlock <- {ClassType};
ImplementsBlock <- {ClassType};
ClassBody <- {ClassItem};
ClassItem <- FunctionDeclaration | FieldDeclaration;
FieldDeclaration <- [modifiers@ModifierBlock], [datatype@TypeName], declarators@DeclaratorList;
VariableDeclaration <- [modifiers@ModifierBlock], [datatype@TypeName], declarators@DeclaratorList;
DeclaratorList <- {Declarator};
Declarator <- name@Identifier, [value@Expression];

java:

Synchronized <- expression@Expression, body@StatementBlock;
Statement <- & | Synchronized;

IntegerLiteralExpr<#1> -> IntegerLiteral<#1>;


EnclosedExpr(#1) -> ParenthesizedExpression(#1);

BinaryExpr(#1, #2)<"+"> -> Addition(#1, #2);
BinaryExpr(Name(#1), Name(#2))<"+"> -> Addition(Variable(Name(#1)), Variable(Name(#2)));
BinaryExpr(Name(#1), #2)<"+"> -> Addition(Variable(Name(#1)), #2);
BinaryExpr(#1, Name(#2))<"+"> -> Addition(#1, Variable(Name(#2)));

BinaryExpr(#1, #2)<"-"> -> Subtraction(#1, #2);
BinaryExpr(Name(#1), Name(#2))<"-"> -> Subtraction(Variable(Name(#1)), Variable(Name(#2)));
BinaryExpr(Name(#1), #2)<"-"> -> Subtraction(Variable(Name(#1)), #2);
BinaryExpr(#1, Name(#2))<"-"> -> Subtraction(#1, Variable(Name(#2)));

BinaryExpr(#1, #2)<"*"> -> Multiplication(#1, #2);
BinaryExpr(Name(#1), Name(#2))<"*"> -> Multiplication(Variable(Name(#1)), Variable(Name(#2)));
BinaryExpr(Name(#1), #2)<"*"> -> Multiplication(Variable(Name(#1)), #2);
BinaryExpr(#1, Name(#2))<"*"> -> Multiplication(#1, Variable(Name(#2)));

BinaryExpr(#1, #2)<"/"> -> Division(#1, #2);
BinaryExpr(Name(#1), Name(#2))<"/"> -> Division(Variable(Name(#1)), Variable(Name(#2)));
BinaryExpr(Name(#1), #2)<"/"> -> Division(Variable(Name(#1)), #2);
BinaryExpr(#1, Name(#2))<"/"> -> Division(#1, Variable(Name(#2)));

BinaryExpr(#1, #2)<"%"> -> Modulus(#1, #2);
BinaryExpr(Name(#1), Name(#2))<"%"> -> Modulus(Variable(Name(#1)), Variable(Name(#2)));
BinaryExpr(Name(#1), #2)<"%"> -> Modulus(Variable(Name(#1)), #2);
BinaryExpr(#1, Name(#2))<"%"> -> Modulus(#1, Variable(Name(#2)));

BinaryExpr(#1, #2)<"=="> -> IsEqualTo(#1, #2);
BinaryExpr(Name(#1), Name(#2))<"=="> -> IsEqualTo(Variable(Name(#1)), Variable(Name(#2)));
BinaryExpr(Name(#1), #2)<"=="> -> IsEqualTo(Variable(Name(#1)), #2);
BinaryExpr(#1, Name(#2))<"=="> -> IsEqualTo(#1, Variable(Name(#2)));

BinaryExpr(#1, #2)<"!="> -> NotEqualTo(#1, #2);
BinaryExpr(Name(#1), Name(#2))<"!="> -> NotEqualTo(Variable(Name(#1)), Variable(Name(#2)));
BinaryExpr(Name(#1), #2)<"!="> -> NotEqualTo(Variable(Name(#1)), #2);
BinaryExpr(#1, Name(#2))<"!="> -> NotEqualTo(#1, Variable(Name(#2)));

BinaryExpr(#1, #2)<">"> -> GreaterThan(#1, #2);
BinaryExpr(Name(#1), Name(#2))<">"> -> GreaterThan(Variable(Name(#1)), Variable(Name(#2)));
BinaryExpr(Name(#1), #2)<">"> -> GreaterThan(Variable(Name(#1)), #2);
BinaryExpr(#1, Name(#2))<">"> -> GreaterThan(#1, Variable(Name(#2)));

BinaryExpr(#1, #2)<"<"> -> LessThan(#1, #2);
BinaryExpr(Name(#1), Name(#2))<"<"> -> LessThan(Variable(Name(#1)), Variable(Name(#2)));
BinaryExpr(Name(#1), #2)<"<"> -> LessThan(Variable(Name(#1)), #2);
BinaryExpr(#1, Name(#2))<"<"> -> LessThan(#1, Variable(Name(#2)));

BinaryExpr(#1, #2)<">="> -> GreaterThanOrEqualTo(#1, #2);
BinaryExpr(Name(#1), Name(#2))<">="> -> GreaterThanOrEqualTo(Variable(Name(#1)), Variable(Name(#2)));
BinaryExpr(Name(#1), #2)<">="> -> GreaterThanOrEqualTo(Variable(Name(#1)), #2);
BinaryExpr(#1, Name(#2))<">="> -> GreaterThanOrEqualTo(#1, Variable(Name(#2)));

BinaryExpr(#1, #2)<"<="> -> LessThanOrEqualTo(#1, #2);
BinaryExpr(Name(#1), Name(#2))<"<="> -> LessThanOrEqualTo(Variable(Name(#1)), Variable(Name(#2)));
BinaryExpr(Name(#1), #2)<"<="> -> LessThanOrEqualTo(Variable(Name(#1)), #2);
BinaryExpr(#1, Name(#2))<"<="> -> LessThanOrEqualTo(#1, Variable(Name(#2)));

BinaryExpr(#1, #2)<"&"> -> BitwiseAnd(#1, #2);
BinaryExpr(Name(#1), Name(#2))<"&"> -> BitwiseAnd(Variable(Name(#1)), Variable(Name(#2)));
BinaryExpr(Name(#1), #2)<"&"> -> BitwiseAnd(Variable(Name(#1)), #2);
BinaryExpr(#1, Name(#2))<"&"> -> BitwiseAnd(#1, Variable(Name(#2)));

BinaryExpr(#1, #2)<"|"> -> BitwiseOr(#1, #2);
BinaryExpr(Name(#1), Name(#2))<"|"> -> BitwiseOr(Variable(Name(#1)), Variable(Name(#2)));
BinaryExpr(Name(#1), #2)<"|"> -> BitwiseOr(Variable(Name(#1)), #2);
BinaryExpr(#1, Name(#2))<"|"> -> BitwiseOr(#1, Variable(Name(#2)));

BinaryExpr(#1, #2)<"^"> -> ExclusiveOr(#1, #2);
BinaryExpr(Name(#1), Name(#2))<"^"> -> ExclusiveOr(Variable(Name(#1)), Variable(Name(#2)));
BinaryExpr(Name(#1), #2)<"^"> -> ExclusiveOr(Variable(Name(#1)), #2);
BinaryExpr(#1, Name(#2))<"^"> -> ExclusiveOr(#1, Variable(Name(#2)));

BinaryExpr(#1, #2)<"<<"> -> LeftShift(#1, #2);
BinaryExpr(Name(#1), Name(#2))<"<<"> -> LeftShift(Variable(Name(#1)), Variable(Name(#2)));
BinaryExpr(Name(#1), #2)<"<<"> -> LeftShift(Variable(Name(#1)), #2);
BinaryExpr(#1, Name(#2))<"<<"> -> LeftShift(#1, Variable(Name(#2)));

BinaryExpr(#1, #2)<">>"> -> RightShift(#1, #2);
BinaryExpr(Name(#1), Name(#2))<">>"> -> RightShift(Variable(Name(#1)), Variable(Name(#2)));
BinaryExpr(Name(#1), #2)<">>"> -> RightShift(Variable(Name(#1)), #2);
BinaryExpr(#1, Name(#2))<">>"> -> RightShift(#1, Variable(Name(#2)));

BinaryExpr(#1, #2)<">>>"> -> UnsignedRightShift(#1, #2);
BinaryExpr(Name(#1), Name(#2))<">>>"> -> UnsignedRightShift(Variable(Name(#1)), Variable(Name(#2)));
BinaryExpr(Name(#1), #2)<">>>"> -> UnsignedRightShift(Variable(Name(#1)), #2);
BinaryExpr(#1, Name(#2))<">>>"> -> UnsignedRightShift(#1, Variable(Name(#2)));

BitwiseComplement(Name(#1)) -> BitwiseComplement(Variable(Name(#1)));
BitwiseComplement(#1) -> BitwiseComplement(#1);

BinaryExpr(#1, #2)<"&&"> -> LogicalAnd(#1, #2);
BinaryExpr(Name(#1), Name(#2))<"&&"> -> LogicalAnd(Variable(Name(#1)), Variable(Name(#2)));
BinaryExpr(Name(#1), #2)<"&&"> -> LogicalAnd(Variable(Name(#1)), #2);
BinaryExpr(#1, Name(#2))<"&&"> -> LogicalAnd(#1, Variable(Name(#2)));

BinaryExpr(#1, #2)<"||"> -> LogicalOr(#1, #2);
BinaryExpr(Name(#1), Name(#2))<"||"> -> LogicalOr(Variable(Name(#1)), Variable(Name(#2)));
BinaryExpr(Name(#1), #2)<"||"> -> LogicalOr(Variable(Name(#1)), #2);
BinaryExpr(#1, Name(#2))<"||"> -> LogicalOr(#1, Variable(Name(#2)));

AssignExpr(#1, #2)<"="> -> SimpleAssignment(Variable(#1), #2);
AssignExpr(#1, #2)<"="> -> SimpleAssignment(#1, Variable(#2));
AssignExpr(#1, #2)<"+="> -> AdditionAssignment(Variable(#1), #2);
AssignExpr(#1, #2)<"-="> -> SubtractionAssignment(Variable(#1), #2);
AssignExpr(#1, #2)<"*="> -> MultiplicationAssignment(Variable(#1), #2);
AssignExpr(#1, #2)<"/="> -> DivisionAssignment(Variable(#1), #2);
AssignExpr(#1, #2)<"%="> -> ModulusAssignment(Variable(#1), #2);
AssignExpr(#1, #2)<"&="> -> BitwiseAndAssignment(Variable(#1), #2);
AssignExpr(#1, #2)<"|="> -> BitwiseOrAssignment(Variable(#1), #2);
AssignExpr(#1, #2)<"^="> -> ExclusiveOrAssignment(Variable(#1), #2);
AssignExpr(#1, #2)<">>="> -> RightShiftAssignment(Variable(#1), #2);
AssignExpr(#1, #2)<">>>="> -> UnsignedRightShiftAssignment(Variable(#1), #2);
AssignExpr(#1, #2)<"<<="> -> LeftShiftAssignment(Variable(#1), #2);

LogicalComplement(Name(#1)) -> LogicalNot(Variable(Name(#1)));
LogicalComplement(#1) -> LogicalNot(#1);

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

PrimitiveType<#1> -> PrimitiveType<#1>;

VariableDeclarationExpr(VariableDeclarator(#1, #2, #3)) -> VariableDeclaration(#1, DeclaratorList(Declarator(#2, #3)));
VariableDeclarationExpr(Modifier#1, VariableDeclarator(#2, #3, #4))
    -> VariableDeclaration(ModifierBlock(#1), #2, DeclaratorList(Declarator(#3, #4)));

ExpressionStmt(#1) -> ExpressionStatement(#1);
ExpressionStmt(#1) -> #1;

ReturnStmt(#1) -> Return(#1);
BlockStmt(#1...) -> StatementBlock(#1);
SynchronizedStmt(#1, #2) -> Synchronized(#1, #2);
SynchronizedStmt(#1, #2) -> Synchronized(Variable(#1), #2);
SimpleName<#1> -> Identifier<#1>;
NameExpr(#1) -> Name(#1);
FieldAccessExpr(NameExpr(#1), #2) -> Name(Name(#1), #2);
FieldAccessExpr(#1, #2) -> Name(#1, #2);
FieldAccessExpr(#1, #2) -> PropertyAccess(#1, #2);
ThisExpr -> This;
StringLiteralExpr<#1> -> StringLiteral<#1>;
EnclosedExpr(Addition(#1, #2)) -> Addition(#1, #2);
BinaryExpr(#1, EnclosedExpr(#2))<"+"> -> Addition(#1, #2);
MethodCallExpr(#1, #2, Name(#3)) -> FunctionCall(#1, #2, ExpressionList(Variable(Name(#3))));
MethodCallExpr(#1, #2, #3) -> FunctionCall(#1, #2, ExpressionList(#3));
MethodCallExpr(#1, #2...) -> FunctionCall(#1, ExpressionList(#2));
ClassType(#1) -> ClassType(Name(#1));
ClassOrInterfaceType(#1) -> ClassType(Name(#1));
ArrayType(#1) -> ArrayType(#1, DimensionList(Dimension));
Parameter(Modifier#1, #2, #3) -> Parameter(ModifierBlock(#1), #2, #3);
VoidType -> VoidType;
Modifier<#1> -> Modifier<#1>;

CompilationUnit(#1) -> Program(#1);

// Class declaration ClassDeclaration <- [ModifierBlock], Identifier, [ExtendsBlock], [ImplementsBlock], ClassBody;

ClassOrInterfaceDeclaration(Modifier#1, #2, InterfaceType(#3)) ->
  ClassDeclaration(ModifierBlock(#1), #2, ImplementsBlock(ClassType(Name(#3))), ClassBody);

ClassOrInterfaceDeclaration(Modifier#1, #2, ClassType(#3)) ->
  ClassDeclaration(ModifierBlock(#1), #2, ExtendsBlock(ClassType(#3)), ClassBody);

ClassOrInterfaceDeclaration(Modifier#1, #2, #3...) ->
  ClassDeclaration(ModifierBlock(#1), #2, ClassBody(#3));

ClassOrInterfaceDeclaration(#1, #2...) -> ClassDeclaration(#1, ClassBody(#2));

FieldDeclaration(VariableDeclarator(#1, #2)) -> FieldDeclaration(#1, DeclaratorList(Declarator(#2)));
FieldDeclaration(VariableDeclarator(#1, #2, #3)) -> FieldDeclaration(#1, DeclaratorList(Declarator(#2, #3)));

FieldDeclaration(Modifier#3, VariableDeclarator(#1, #2)) -> FieldDeclaration(ModifierBlock(#3), #1, DeclaratorList(Declarator(#2)));
FieldDeclaration(Modifier#4, VariableDeclarator(#1, #2, #3)) -> FieldDeclaration(ModifierBlock(#4), #1, DeclaratorList(Declarator(#2, #3)));

// Function declaration FunctionDeclaration <- [modifiers@ModifierBlock], [typename@TypeName], name@Identifier, parameters@ParameterBlock, body@StatementBlock;

MethodDeclaration(Modifier#1, #2, Parameter#3, #4, #5) ->
  FunctionDeclaration(ModifierBlock(#1), #4, #2, ParameterBlock(#3), #5);

ConstructorDeclaration(Modifier#1, #2, Parameter#3, #4) ->
    FunctionDeclaration(ModifierBlock(#1), #2, ParameterBlock(#3), #4);

js:

ClassItem <- & | Property;
Property <- name@Identifier, value@Expression;
Yield <- Expression;

singleExpression(expressionSequence(#1)) -> #1;
singleExpression(Identifier<#1>) -> Variable(Name(Identifier<#1>));

singleExpression(literal(numericLiteral(literal<#1>))) -> IntegerLiteral<#1>;
singleExpression(literal(literal<#1>)) -> StringLiteral<#1>;

singleExpression(#1, literal<"+">, #2) -> Addition(#1, #2);
singleExpression(singleExpression(#1), literal<"+">, singleExpression(#2)) -> Addition(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(singleExpression(#1), literal<"+">, #2) -> Addition(Variable(Name(#1)), #2);
singleExpression(#1, literal<"+">, singleExpression(#2)) -> Addition(#1, Variable(Name(#2)));

singleExpression(#1, literal<"-">, #2) -> Subtraction(#1, #2);
singleExpression(singleExpression(#1), literal<"-">, singleExpression(#2)) -> Subtraction(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(singleExpression(#1), literal<"-">, #2) -> Subtraction(Variable(Name(#1)), #2);
singleExpression(#1, literal<"-">, singleExpression(#2)) -> Subtraction(#1, Variable(Name(#2)));

singleExpression(#1, literal<"*">, #2) -> Multiplication(#1, #2);
singleExpression(singleExpression(#1), literal<"*">, singleExpression(#2)) -> Multiplication(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(singleExpression(#1), literal<"*">, #2) -> Multiplication(Variable(Name(#1)), #2);
singleExpression(#1, literal<"*">, singleExpression(#2)) -> Multiplication(#1, Variable(Name(#2)));

singleExpression(#1, literal<"/">, #2) -> Division(#1, #2);
singleExpression(singleExpression(#1), literal<"/">, singleExpression(#2)) -> Division(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(singleExpression(#1), literal<"/">, #2) -> Division(Variable(Name(#1)), #2);
singleExpression(#1, literal<"/">, singleExpression(#2)) -> Division(#1, Variable(Name(#2)));

singleExpression(#1, literal<"%">, #2) -> Modulus(#1, #2);
singleExpression(singleExpression(#1), literal<"%">, singleExpression(#2)) -> Modulus(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(singleExpression(#1), literal<"%">, #2) -> Modulus(Variable(Name(#1)), #2);
singleExpression(#1, literal<"%">, singleExpression(#2)) -> Modulus(#1, Variable(Name(#2)));

singleExpression(#1, literal<"==">, #2) -> IsEqualTo(#1, #2);
singleExpression(singleExpression(#1), literal<"==">, singleExpression(#2)) -> IsEqualTo(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(singleExpression(#1), literal<"==">, #2) -> IsEqualTo(Variable(Name(#1)), #2);
singleExpression(#1, literal<"==">, singleExpression(#2)) -> IsEqualTo(#1, Variable(Name(#2)));

singleExpression(#1, literal<"!=">, #2) -> NotEqualTo(#1, #2);
singleExpression(singleExpression(#1), literal<"!=">, singleExpression(#2)) -> NotEqualTo(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(singleExpression(#1), literal<"!=">, #2) -> NotEqualTo(Variable(Name(#1)), #2);
singleExpression(#1, literal<"!=">, singleExpression(#2)) -> NotEqualTo(#1, Variable(Name(#2)));

singleExpression(#1, literal<">">, #2) -> GreaterThan(#1, #2);
singleExpression(singleExpression(#1), literal<">">, singleExpression(#2)) -> GreaterThan(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(singleExpression(#1), literal<">">, #2) -> GreaterThan(Variable(Name(#1)), #2);
singleExpression(#1, literal<">">, singleExpression(#2)) -> GreaterThan(#1, Variable(Name(#2)));

singleExpression(#1, literal<"<">, #2) -> LessThan(#1, #2);
singleExpression(singleExpression(#1), literal<"<">, singleExpression(#2)) -> LessThan(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(singleExpression(#1), literal<"<">, #2) -> LessThan(Variable(Name(#1)), #2);
singleExpression(#1, literal<"<">, singleExpression(#2)) -> LessThan(#1, Variable(Name(#2)));

singleExpression(#1, literal<">=">, #2) -> GreaterThanOrEqualTo(#1, #2);
singleExpression(singleExpression(#1), literal<">=">, singleExpression(#2)) -> GreaterThanOrEqualTo(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(singleExpression(#1), literal<">=">, #2) -> GreaterThanOrEqualTo(Variable(Name(#1)), #2);
singleExpression(#1, literal<">=">, singleExpression(#2)) -> GreaterThanOrEqualTo(#1, Variable(Name(#2)));

singleExpression(#1, literal<"<=">, #2) -> LessThanOrEqualTo(#1, #2);
singleExpression(singleExpression(#1), literal<"<=">, singleExpression(#2)) -> LessThanOrEqualTo(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(singleExpression(#1), literal<"<=">, #2) -> LessThanOrEqualTo(Variable(Name(#1)), #2);
singleExpression(#1, literal<"<=">, singleExpression(#2)) -> LessThanOrEqualTo(#1, Variable(Name(#2)));

singleExpression(#1, literal<"&">, #2) -> BitwiseAnd(#1, #2);
singleExpression(singleExpression(#1), literal<"&">, singleExpression(#2)) -> BitwiseAnd(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(singleExpression(#1), literal<"&">, #2) -> BitwiseAnd(Variable(Name(#1)), #2);
singleExpression(#1, literal<"&">, singleExpression(#2)) -> BitwiseAnd(#1, Variable(Name(#2)));

singleExpression(#1, literal<"|">, #2) -> BitwiseOr(#1, #2);
singleExpression(singleExpression(#1), literal<"|">, singleExpression(#2)) -> BitwiseOr(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(singleExpression(#1), literal<"|">, #2) -> BitwiseOr(Variable(Name(#1)), #2);
singleExpression(#1, literal<"|">, singleExpression(#2)) -> BitwiseOr(#1, Variable(Name(#2)));

singleExpression(#1, literal<"^">, #2) -> ExclusiveOr(#1, #2);
singleExpression(singleExpression(#1), literal<"^">, singleExpression(#2)) -> ExclusiveOr(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(singleExpression(#1), literal<"^">, #2) -> ExclusiveOr(Variable(Name(#1)), #2);
singleExpression(#1, literal<"^">, singleExpression(#2)) -> ExclusiveOr(#1, Variable(Name(#2)));

singleExpression(#1, literal<"<<">, #2) -> LeftShift(#1, #2);
singleExpression(singleExpression(#1), literal<"<<">, singleExpression(#2)) -> LeftShift(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(singleExpression(#1), literal<"<<">, #2) -> LeftShift(Variable(Name(#1)), #2);
singleExpression(#1, literal<"<<">, singleExpression(#2)) -> LeftShift(#1, Variable(Name(#2)));

singleExpression(#1, literal<">>">, #2) -> RightShift(#1, #2);
singleExpression(singleExpression(#1), literal<">>">, singleExpression(#2)) -> RightShift(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(singleExpression(#1), literal<">>">, #2) -> RightShift(Variable(Name(#1)), #2);
singleExpression(#1, literal<">>">, singleExpression(#2)) -> RightShift(#1, Variable(Name(#2)));

singleExpression(#1, literal<">>>">, #2) -> UnsignedRightShift(#1, #2);
singleExpression(singleExpression(#1), literal<">>>">, singleExpression(#2)) -> UnsignedRightShift(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(singleExpression(#1), literal<">>>">, #2) -> UnsignedRightShift(Variable(Name(#1)), #2);
singleExpression(#1, literal<">>>">, singleExpression(#2)) -> UnsignedRightShift(#1, Variable(Name(#2)));

singleExpression(literal<"~">, #1) ->  BitwiseComplement(#1);
singleExpression(literal<"~">, singleExpression(#1)) ->  BitwiseComplement(Variable(Name(#1)));

singleExpression(#1, literal<"&&">, #2) -> LogicalAnd(#1, #2);
singleExpression(singleExpression(#1), literal<"&&">, singleExpression(#2)) -> LogicalAnd(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(singleExpression(#1), literal<"&&">, #2) -> LogicalAnd(Variable(Name(#1)), #2);
singleExpression(#1, literal<"&&">, singleExpression(#2)) -> LogicalAnd(#1, Variable(Name(#2)));

singleExpression(#1, literal<"||">, #2) -> LogicalOr(#1, #2);
singleExpression(singleExpression(#1), literal<"||">, singleExpression(#2)) -> LogicalOr(Variable(Name(#1)), Variable(Name(#2)));
singleExpression(singleExpression(#1), literal<"||">, #2) -> LogicalOr(Variable(Name(#1)), #2);
singleExpression(#1, literal<"||">, singleExpression(#2)) -> LogicalOr(#1, Variable(Name(#2)));

singleExpression(literal<"!">, #1) -> LogicalNot(#1);
singleExpression(literal<"!">, singleExpression(#1)) -> LogicalNot(Variable(Name(#1)));

singleExpression(#1, literal<"+">, singleExpression(expressionSequence(#2))) -> Addition(#1, #2);

singleExpression(singleExpression(singleExpression(#1), identifierName(#2)), arguments(argument(#3))) ->
  FunctionCall(Name(#1), #2, ExpressionList(#3));
singleExpression(singleExpression(Variable(#1), identifierName(#2)), arguments(#3...)) ->
  FunctionCall(#1, #2, ExpressionList(#3));

singleExpression(literal<"++">, singleExpression(#1)) ->  PreIncrement(#1);
singleExpression(literal<"--">, singleExpression(#1)) ->  PreDecrement(#1);
singleExpression(singleExpression(#1), literal<"++">) ->  PostIncrement(#1);
singleExpression(singleExpression(#1), literal<"--">) ->  PostDecrement(#1);
singleExpression(literal<"-">, singleExpression(#1)) ->  Negative(#1);
singleExpression(literal<"+">, singleExpression(#1)) ->  Positive(#1);

singleExpression(literal<"++">, #1) ->  PreIncrement(#1);
singleExpression(literal<"--">, #1) ->  PreDecrement(#1);
singleExpression(#1, literal<"++">) ->  PostIncrement(#1);
singleExpression(#1, literal<"--">) ->  PostDecrement(#1);
singleExpression(literal<"-">, #1) ->  Negative(#1);
singleExpression(literal<"+">, #1) ->  Positive(#1);


identifier(literal<#1>) -> Identifier<#1>;

singleExpression(#1, literal<"=">, #2) -> SimpleAssignment(#1, #2);
singleExpression(#1, literal<"=">, #2) -> SimpleAssignment(Variable(Name(#1)), #2);
singleExpression(#1, assignmentOperator(literal<"+=">), #2) -> AdditionAssignment(#1, #2);
singleExpression(#1, assignmentOperator(literal<"-=">), #2) -> SubtractionAssignment(#1, #2);
singleExpression(#1, assignmentOperator(literal<"*=">), #2) -> MultiplicationAssignment(#1, #2);
singleExpression(#1, assignmentOperator(literal<"/=">), #2) -> DivisionAssignment(#1, #2);
singleExpression(#1, assignmentOperator(literal<"%=">), #2) -> ModulusAssignment(#1, #2);
singleExpression(#1, assignmentOperator(literal<"&=">), #2) -> BitwiseAnd(#1, #2);
singleExpression(#1, assignmentOperator(literal<"|=">), #2) -> BitwiseOr(#1, #2);
singleExpression(#1, assignmentOperator(literal<"^=">), #2) -> ExclusiveOr(#1, #2);
singleExpression(#1, assignmentOperator(literal<">>=">), #2) -> RightShift(#1, #2);
singleExpression(#1, assignmentOperator(literal<">>>=">), #2) -> UnsignedRightShift(#1, #2);
singleExpression(#1, assignmentOperator(literal<"<<=">), #2) -> LeftShift(#1, #2);

singleExpression(literal<"this">) -> This;
singleExpression(This, identifierName(#1)) -> PropertyAccess(This, #1);

variableDeclaration(assignable(#1), literal<"=">, #2) -> Declarator(#1, #2);
variableDeclarationList(varModifier(let_(literal<"let">)), #1...) -> VariableDeclaration(DeclaratorList(#1));
variableDeclarationList(varModifier(literal<"var">), #1...) -> VariableDeclaration(DeclaratorList(#1));

sourceElement(statement(variableStatement(#1))) -> #1;
expressionStatement(expressionSequence(#1)) -> ExpressionStatement(#1);

sourceElement(statement(FunctionCall(#1...))) -> ExpressionStatement(FunctionCall(#1));
sourceElement(statement(#1)) -> #1;

expressionStatement(expressionSequence(singleExpression(singleExpression(#1), arguments))) ->
  ExpressionStatement(FunctionCall(#1, ExpressionList()));

argument(#1) -> #1;

singleExpression(Variable(Name(#1)), arguments(#2...)) ->
  FunctionCall(#1, ExpressionList(#2));

program(sourceElements(sourceElement(statement(#1)), sourceElement(statement(#2)))) -> Program(#1, #2);
program(sourceElements(sourceElement(statement(#1)))) -> Program(#1);

program(sourceElements(#1...)) -> Program(#1);

returnStatement(literal<"return">, expressionSequence(#1)) -> Return(#1);

// Class declaration ClassDeclaration <- [ModifierBlock], Identifier, [ExtendsBlock], [ImplementsBlock], ClassBody;

classDeclaration(literal<"class">, #1, classTail(literal<"extends">, Variable(#2)))
    -> ClassDeclaration(#1, ExtendsBlock(ClassType(#2)), ClassBody);
classDeclaration(literal<"class">, #1, classTail(#2, classElement(emptyStatement_)))
    -> ClassDeclaration(#1, ClassBody(#2));
classDeclaration(literal<"class">, #1, classTail(#2...)) -> ClassDeclaration(#1, ClassBody(#2));

classElement(propertyName(identifierName(#1)), literal<"=">, #2) -> Property(#1, #2);
classElement(#1) -> #1;

// Function declaration FunctionDeclaration <- [modifiers@ModifierBlock], [typename@TypeName], name@Identifier, parameters@ParameterBlock, body@StatementBlock;

functionBody(sourceElements(sourceElement(statement(expressionStatement(expressionSequence(#1)))))) ->
  StatementBlock(#1);
functionBody(sourceElements(sourceElement(statement(#1)))) ->
  StatementBlock(#1);
functionBody(sourceElements(#1...)) -> StatementBlock(#1);

functionDeclaration(literal<"function">, #1, #2) ->
  FunctionDeclaration(#1, ParameterBlock, #2);
functionDeclaration(literal<"function">, #1, #2, #3) ->
  FunctionDeclaration(#1, #2, #3);

methodDefinition(propertyName(identifierName(#1)), functionBody) -> FunctionDeclaration(#1, ParameterBlock, StatementBlock);
methodDefinition(propertyName(identifierName(#1)), #2, #3) ->  FunctionDeclaration(#1, #2, #3);

formalParameterArg(assignable(#1)) -> Parameter(#1);
formalParameterList(#1...) -> ParameterBlock(#1);


python:

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

expr(atom(testlist_comp(#1))) -> #1;

atom(name(literal<"self">)) -> This;
expr(This, trailer(name(literal<#1>))) -> PropertyAccess(This, Identifier<#1>);

comparison(comparison(#1), literal<"==">, comparison(#2)) -> IsEqualTo(#1, #2);
comparison(comparison(#1), literal<"!=">, comparison(#2)) -> NotEqualTo(#1, #2);
comparison(comparison(#1), literal<">">, comparison(#2)) -> GreaterThan(#1, #2);
comparison(comparison(#1), literal<"<">, comparison(#2)) -> LessThan(#1, #2);
comparison(comparison(#1), literal<">=">, comparison(#2)) -> GreaterThanOrEqualTo(#1, #2);
comparison(comparison(#1), literal<"<=">, comparison(#2)) -> LessThanOrEqualTo(#1, #2);

comparison(comparison(#1), literal<"&">, comparison(#2)) -> BitwiseAnd(#1, #2);
comparison(comparison(#1), literal<"|">, comparison(#2)) -> BitwiseOr(#1, #2);
comparison(comparison(#1), literal<"^">, comparison(#2)) -> ExclusiveOr(#1, #2);

expr(#1, literal<">>">, #2) -> RightShift(#1, #2);
expr(#1, literal<"<<">, #2) -> LeftShift(#1, #2);

comparison(comparison(#1), literal<"<<">, comparison(#2)) -> LeftShift(#1, #2);
comparison(comparison(#1), literal<">>">, comparison(#2)) -> RightShift(#1, #2);
expr(literal<"~">, #1) -> BitwiseComplement(#1);

expr(literal<"+">, #1) -> Positive(#1);
expr(literal<"-">, #1) -> Negative(#1);

logical_test(comparison(#1)) -> #1;
logical_test(#1) -> #1;
logical_test(literal<"not">, #1) -> LogicalNot(#1);

logical_test(#1, literal<"and">, #2) -> LogicalAnd(#1, #2);
logical_test(#1, literal<"or">, #2) -> LogicalOr(#1, #2);

argument(#1) -> #1;

expr(atom(name(literal<#1>)), trailer(arguments(arglist(argument(comparison(#2)))))) ->
  FunctionCall(Identifier<#1>, ExpressionList(#2));
expr(atom(name(literal<#1>)), trailer(arguments(arglist(argument(#2))))) ->
  FunctionCall(Identifier<#1>, ExpressionList(#2));
expr(atom(literal<#1>), trailer(arguments(arglist(argument(comparison(#2)))))) ->
  FunctionCall(Identifier<#1>, ExpressionList(#2));
expr(atom(literal<#1>), trailer(arguments(arglist(argument(#2))))) ->
  FunctionCall(Identifier<#1>, ExpressionList(#2));
expr(atom(name(literal<#1>)), trailer(arguments())) ->
  FunctionCall(Identifier<#1>, ExpressionList());
expr(atom(name(literal<#1>)), trailer(arguments(arglist(#2...)))) ->
  FunctionCall(Identifier<#1>, ExpressionList(#2));
expr(atom(literal<#1>), trailer(arguments(arglist(#2...)))) ->
  FunctionCall(Identifier<#1>, ExpressionList(#2));
expr(atom(name(literal<#1>)), trailer(name(literal<#2>), arguments(arglist(#3...)))) ->
  FunctionCall(Name(Identifier<#1>), Identifier<#2>, ExpressionList(#3));

small_stmt(FunctionCall(#1...)) -> ExpressionStatement(FunctionCall(#1));

testlist_star_expr(#1) -> #1;

small_stmt(literal<"return">, comparison(#1)) -> Return(#1);
small_stmt(literal<"return">, #1) -> Return(#1);

small_stmt(#1, assign_part(literal<"=">, #2)) -> SimpleAssignment(#1, #2);
small_stmt(#1, assign_part(literal<"+=">, #2)) -> AdditionAssignment(#1, #2);
small_stmt(#1, assign_part(literal<"-=">, #2)) -> SubtractionAssignment(#1, #2);
small_stmt(#1, assign_part(literal<"*=">, #2)) -> MultiplicationAssignment(#1, #2);
small_stmt(#1, assign_part(literal<"/=">, #2)) -> DivisionAssignment(#1, #2);
small_stmt(#1, assign_part(literal<"%=">, #2)) -> ModulusAssignment(#1, #2);
small_stmt(#1, assign_part(literal<"&=">, #2)) -> BitwiseAnd(#1, #2);
small_stmt(#1, assign_part(literal<"|=">, #2)) -> BitwiseOr(#1, #2);
small_stmt(#1, assign_part(literal<"^=">, #2)) -> ExclusiveOr(#1, #2);
small_stmt(#1, assign_part(literal<">>=">, #2)) -> RightShift(#1, #2);
small_stmt(#1, assign_part(literal<"<<=">, #2)) -> LeftShift(#1, #2);

stmt(#1) -> ExpressionStatement(#1);
stmt(#1) -> #1;

named_parameter(name(literal<#1>)) -> Parameter(Identifier<#1>);
def_parameters(#1...) -> ParameterBlock(#1);

funcdef(literal<"def">, name(literal<#1>), suite(small_stmt(comparison(#2)))) ->
  FunctionDeclaration(Identifier<#1>, ParameterBlock, StatementBlock(#2));
funcdef(literal<"def">, name(literal<#1>), suite(small_stmt(#2))) ->
  FunctionDeclaration(Identifier<#1>, ParameterBlock, StatementBlock(#2));
funcdef(literal<"def">, name(literal<#1>), suite(small_stmt(literal<"pass">))) ->
    FunctionDeclaration(Identifier<#1>, ParameterBlock, StatementBlock);
funcdef(literal<"def">, name(literal<#1>), suite(#2)) ->
  FunctionDeclaration(Identifier<#1>, ParameterBlock, StatementBlock(#2));
funcdef(literal<"def">, name(literal<#1>), suite(#2...)) ->
  FunctionDeclaration(Identifier<#1>, ParameterBlock, StatementBlock(#2));
funcdef(literal<"def">, name(literal<#1>), typedargslist(#2), suite(#3...)) ->
  FunctionDeclaration(Identifier<#1>, #2, StatementBlock(#3));

file_input(#1, small_stmt(comparison(#2))) -> Program(#1, #2);
file_input(#1, small_stmt(#2)) -> Program(#1, #2);

file_input(#1...) -> Program(#1);

// Class declaration ClassDeclaration <- [ModifierBlock], Identifier, [ExtendsBlock], [ImplementsBlock], ClassBody;

classdef(literal<"class">, name(literal<#1>), suite(small_stmt(literal<"pass">)))
    -> ClassDeclaration(Identifier<#1>, ClassBody);

classdef(literal<"class">, name(literal<#1>), arglist(Variable(#2)), suite(small_stmt(literal<"pass">)))
    -> ClassDeclaration(Identifier<#1>, ExtendsBlock(ClassType(#2)), ClassBody);

classdef(literal<"class">, name(literal<#1>), suite(#2...))
    -> ClassDeclaration(Identifier<#1>, ClassBody(#2));
classdef(literal<"class">, name(literal<#1>), #2...)
    -> ClassDeclaration(Identifier<#1>, ClassBody(#2));

small_stmt(Variable(Name(#1))) -> FieldDeclaration(DeclaratorList(Declarator(#1)));

suite(ExpressionStatement(SimpleAssignment(Variable(Name(#1)), #2))) -> FieldDeclaration(DeclaratorList(Declarator(#1, #2)));
