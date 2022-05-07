/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.python.rules;

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
public final class Rule0 implements Converter {
    /**
     * The instance.
     */
    public static final Converter INSTANCE = new Rule0();

    /**
     * The 'This' string.
     */
    private static final String THIS = "This";

    /**
     * Constructor.
     */
    private Rule0() {
    }

    @Override
    public Node convert(final Node node, final Factory factory) {
        Node result = EmptyTree.INSTANCE;
        final Map<Integer, Node> children = new TreeMap<>();
        final Map<Integer, String> data = new TreeMap<>();
        final boolean matched = Matcher0.INSTANCE.match(node, children, data);
        if (matched) {
            result = Rule0.firstBuilder(factory);
        }
        return result;
    }

    /**
     * Builds a node with 'This' type.
     * @param factory The node factory
     * @return A node
     */
    private static Node firstBuilder(final Factory factory) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule0.THIS);
        if (builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }
}
