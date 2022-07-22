/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.java.rules;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.uast.uast.base.Builder;
import org.uast.uast.base.Converter;
import org.uast.uast.base.EmptyTree;
import org.uast.uast.base.Factory;
import org.uast.uast.base.Node;

/**
 * Converter describing DSL conversion rule.
 *
 * @since 1.0
 */
public final class Rule16 implements Converter {
    /**
     * The instance.
     */
    public static final Converter INSTANCE = new Rule16();

    /**
     * The 'VoidType' string.
     */
    private static final String VOID_TYPE = "VoidType";

    /**
     * Constructor.
     */
    private Rule16() {
    }

    @Override
    public Node convert(final Node node, final Factory factory) {
        Node result = EmptyTree.INSTANCE;
        final Map<Integer, List<Node>> children = new TreeMap<>();
        final Map<Integer, String> data = new TreeMap<>();
        final boolean matched = Matcher20.INSTANCE.match(node, children, data);
        if (matched) {
            result = Rule16.firstBuilder(factory);
        }
        return result;
    }

    /**
     * Builds a node with 'VoidType' type.
     * @param factory The node factory
     * @return A node
     */
    private static Node firstBuilder(final Factory factory) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule16.VOID_TYPE);
        if (builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }
}
