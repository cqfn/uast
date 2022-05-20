/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.java;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.uast.uast.base.Factory;
import org.uast.uast.base.Type;
import org.uast.uast.generated.tree.green.Addition;
import org.uast.uast.generated.tree.green.ExpressionList;
import org.uast.uast.generated.tree.green.FunctionCall;
import org.uast.uast.generated.tree.green.Identifier;
import org.uast.uast.generated.tree.green.IntegerLiteral;
import org.uast.uast.generated.tree.green.Modifier;
import org.uast.uast.generated.tree.green.ModifierBlock;
import org.uast.uast.generated.tree.green.Name;
import org.uast.uast.generated.tree.green.PropertyAccess;
import org.uast.uast.generated.tree.green.Return;
import org.uast.uast.generated.tree.green.StatementBlock;
import org.uast.uast.generated.tree.green.StringLiteral;
import org.uast.uast.generated.tree.green.Subtraction;
import org.uast.uast.generated.tree.green.This;
import org.uast.uast.generated.tree.green.Variable;

/**
 * Factory that creates 'java' nodes.
 *
 * @since 1.0
 */
public final class JavaFactory extends Factory {
    /**
     * The instance.
     */
    public static final Factory INSTANCE = new JavaFactory();

    /**
     * Constructor.
     */
    private JavaFactory() {
        super(Collections.unmodifiableMap(JavaFactory.init()));
    }

    /**
     * Initialises the set of types arranged by name.
     * @return The map of types by name
     */
    private static Map<String, Type> init() {
        final List<Type> types = Arrays.asList(
            Addition.TYPE,
            ExpressionList.TYPE,
            FunctionCall.TYPE,
            Identifier.TYPE,
            IntegerLiteral.TYPE,
            Modifier.TYPE,
            ModifierBlock.TYPE,
            Name.TYPE,
            PropertyAccess.TYPE,
            Return.TYPE,
            StatementBlock.TYPE,
            StringLiteral.TYPE,
            Subtraction.TYPE,
            Synchronized.TYPE,
            This.TYPE,
            Variable.TYPE
        );
        final Map<String, Type> map = new TreeMap<>();
        for (final Type type : types) {
            map.put(type.getName(), type);
        }
        return map;
    }
}
