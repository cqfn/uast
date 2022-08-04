/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.green;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.uast.uast.base.Factory;
import org.uast.uast.base.Type;

/**
 * Factory that creates 'green' nodes.
 *
 * @since 1.0
 */
public final class GreenFactory extends Factory {
    /**
     * The instance.
     */
    public static final Factory INSTANCE = new GreenFactory();

    /**
     * Constructor.
     */
    private GreenFactory() {
        super(Collections.unmodifiableMap(GreenFactory.init()));
    }

    /**
     * Initialises the set of types arranged by name.
     * @return The map of types by name
     */
    private static Map<String, Type> init() {
        final List<Type> types = Arrays.asList(
            Addition.TYPE,
            AdditionAssignment.TYPE,
            ArrayType.TYPE,
            BitwiseAnd.TYPE,
            BitwiseAndAssignment.TYPE,
            BitwiseComplement.TYPE,
            BitwiseOr.TYPE,
            BitwiseOrAssignment.TYPE,
            ClassBody.TYPE,
            ClassDeclaration.TYPE,
            ClassType.TYPE,
            Declarator.TYPE,
            DeclaratorList.TYPE,
            Dimension.TYPE,
            DimensionList.TYPE,
            Division.TYPE,
            DivisionAssignment.TYPE,
            ExclusiveOr.TYPE,
            ExclusiveOrAssignment.TYPE,
            ExpressionList.TYPE,
            FieldDeclaration.TYPE,
            FunctionCall.TYPE,
            FunctionCallExpression.TYPE,
            FunctionDeclaration.TYPE,
            GreaterThan.TYPE,
            GreaterThanOrEqualTo.TYPE,
            Identifier.TYPE,
            IntegerLiteral.TYPE,
            IsEqualTo.TYPE,
            LeftShift.TYPE,
            LeftShiftAssignment.TYPE,
            LessThan.TYPE,
            LessThanOrEqualTo.TYPE,
            LogicalAnd.TYPE,
            LogicalNot.TYPE,
            LogicalOr.TYPE,
            Modifier.TYPE,
            ModifierBlock.TYPE,
            Modulus.TYPE,
            ModulusAssignment.TYPE,
            Multiplication.TYPE,
            MultiplicationAssignment.TYPE,
            Name.TYPE,
            Negative.TYPE,
            NotEqualTo.TYPE,
            Parameter.TYPE,
            ParameterBlock.TYPE,
            Positive.TYPE,
            PostDecrement.TYPE,
            PostIncrement.TYPE,
            PreDecrement.TYPE,
            PreIncrement.TYPE,
            PrimitiveType.TYPE,
            Program.TYPE,
            PropertyAccess.TYPE,
            Return.TYPE,
            RightShift.TYPE,
            RightShiftAssignment.TYPE,
            SimpleAssignment.TYPE,
            StatementBlock.TYPE,
            StringLiteral.TYPE,
            Subtraction.TYPE,
            SubtractionAssignment.TYPE,
            This.TYPE,
            UnsignedRightShift.TYPE,
            UnsignedRightShiftAssignment.TYPE,
            Variable.TYPE,
            VariableDeclaration.TYPE,
            VoidType.TYPE
        );
        final Map<String, Type> map = new TreeMap<>();
        for (final Type type : types) {
            map.put(type.getName(), type);
        }
        return map;
    }
}
