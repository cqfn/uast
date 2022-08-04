/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.js;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.uast.uast.base.Factory;
import org.uast.uast.base.Type;
import org.uast.uast.generated.tree.green.Addition;
import org.uast.uast.generated.tree.green.AdditionAssignment;
import org.uast.uast.generated.tree.green.ArrayType;
import org.uast.uast.generated.tree.green.BitwiseAnd;
import org.uast.uast.generated.tree.green.BitwiseAndAssignment;
import org.uast.uast.generated.tree.green.BitwiseComplement;
import org.uast.uast.generated.tree.green.BitwiseOr;
import org.uast.uast.generated.tree.green.BitwiseOrAssignment;
import org.uast.uast.generated.tree.green.ClassBody;
import org.uast.uast.generated.tree.green.ClassDeclaration;
import org.uast.uast.generated.tree.green.ClassType;
import org.uast.uast.generated.tree.green.Declarator;
import org.uast.uast.generated.tree.green.DeclaratorList;
import org.uast.uast.generated.tree.green.Dimension;
import org.uast.uast.generated.tree.green.DimensionList;
import org.uast.uast.generated.tree.green.Division;
import org.uast.uast.generated.tree.green.DivisionAssignment;
import org.uast.uast.generated.tree.green.ExclusiveOr;
import org.uast.uast.generated.tree.green.ExclusiveOrAssignment;
import org.uast.uast.generated.tree.green.ExpressionList;
import org.uast.uast.generated.tree.green.FieldDeclaration;
import org.uast.uast.generated.tree.green.FunctionCall;
import org.uast.uast.generated.tree.green.FunctionCallExpression;
import org.uast.uast.generated.tree.green.FunctionDeclaration;
import org.uast.uast.generated.tree.green.GreaterThan;
import org.uast.uast.generated.tree.green.GreaterThanOrEqualTo;
import org.uast.uast.generated.tree.green.Identifier;
import org.uast.uast.generated.tree.green.IntegerLiteral;
import org.uast.uast.generated.tree.green.IsEqualTo;
import org.uast.uast.generated.tree.green.LeftShift;
import org.uast.uast.generated.tree.green.LeftShiftAssignment;
import org.uast.uast.generated.tree.green.LessThan;
import org.uast.uast.generated.tree.green.LessThanOrEqualTo;
import org.uast.uast.generated.tree.green.LogicalAnd;
import org.uast.uast.generated.tree.green.LogicalNot;
import org.uast.uast.generated.tree.green.LogicalOr;
import org.uast.uast.generated.tree.green.Modifier;
import org.uast.uast.generated.tree.green.ModifierBlock;
import org.uast.uast.generated.tree.green.Modulus;
import org.uast.uast.generated.tree.green.ModulusAssignment;
import org.uast.uast.generated.tree.green.Multiplication;
import org.uast.uast.generated.tree.green.MultiplicationAssignment;
import org.uast.uast.generated.tree.green.Name;
import org.uast.uast.generated.tree.green.Negative;
import org.uast.uast.generated.tree.green.NotEqualTo;
import org.uast.uast.generated.tree.green.Parameter;
import org.uast.uast.generated.tree.green.ParameterBlock;
import org.uast.uast.generated.tree.green.Positive;
import org.uast.uast.generated.tree.green.PostDecrement;
import org.uast.uast.generated.tree.green.PostIncrement;
import org.uast.uast.generated.tree.green.PreDecrement;
import org.uast.uast.generated.tree.green.PreIncrement;
import org.uast.uast.generated.tree.green.PrimitiveType;
import org.uast.uast.generated.tree.green.Program;
import org.uast.uast.generated.tree.green.PropertyAccess;
import org.uast.uast.generated.tree.green.Return;
import org.uast.uast.generated.tree.green.RightShift;
import org.uast.uast.generated.tree.green.RightShiftAssignment;
import org.uast.uast.generated.tree.green.SimpleAssignment;
import org.uast.uast.generated.tree.green.StatementBlock;
import org.uast.uast.generated.tree.green.StringLiteral;
import org.uast.uast.generated.tree.green.Subtraction;
import org.uast.uast.generated.tree.green.SubtractionAssignment;
import org.uast.uast.generated.tree.green.This;
import org.uast.uast.generated.tree.green.UnsignedRightShift;
import org.uast.uast.generated.tree.green.UnsignedRightShiftAssignment;
import org.uast.uast.generated.tree.green.Variable;
import org.uast.uast.generated.tree.green.VariableDeclaration;
import org.uast.uast.generated.tree.green.VoidType;

/**
 * Factory that creates 'js' nodes.
 *
 * @since 1.0
 */
public final class JsFactory extends Factory {
    /**
     * The instance.
     */
    public static final Factory INSTANCE = new JsFactory();

    /**
     * Constructor.
     */
    private JsFactory() {
        super(Collections.unmodifiableMap(JsFactory.init()));
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
            VoidType.TYPE,
            Yield.TYPE
        );
        final Map<String, Type> map = new TreeMap<>();
        for (final Type type : types) {
            map.put(type.getName(), type);
        }
        return map;
    }
}
