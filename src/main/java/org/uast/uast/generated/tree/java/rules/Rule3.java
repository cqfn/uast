/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.java.rules;

import java.util.Arrays;
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
public final class Rule3 implements Converter {
    /**
     * The instance.
     */
    public static final Converter INSTANCE = new Rule3();

    /**
     * The 'Return' string.
     */
    private static final String RETURN = "Return";

    /**
     * Constructor.
     */
    private Rule3() {
    }

    @Override
    public Node convert(final Node node, final Factory factory) {
        Node result = EmptyTree.INSTANCE;
        final Map<Integer, Node> children = new TreeMap<>();
        final Map<Integer, String> data = new TreeMap<>();
        final boolean matched = Matcher3.INSTANCE.match(node, children, data);
        if (matched) {
            result = Rule3.firstBuilder(factory, children);
        }
        return result;
    }

    /**
     * Builds a node with 'Return' type.
     * @param factory The node factory
     * @param children The collection of child nodes
     * @return A node
     */
    private static Node firstBuilder(final Factory factory, final Map<Integer, Node> children) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule3.RETURN);
        final boolean applied = builder.setChildrenList(
            Arrays.asList(
                children.get(1)
            )
        );
        if (applied && builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }
}
