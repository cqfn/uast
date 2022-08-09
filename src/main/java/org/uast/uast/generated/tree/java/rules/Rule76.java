/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.java.rules;

import java.util.LinkedList;
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
public final class Rule76 implements Converter {
    /**
     * The instance.
     */
    public static final Converter INSTANCE = new Rule76();

    /**
     * The 'Variable' string.
     */
    private static final String VARIABLE = "Variable";

    /**
     * The number of the first hole.
     */
    private static final int FIRST_HOLE_ID = 2;

    /**
     * The 'BitwiseOrAssignment' string.
     */
    private static final String BITWISE_OR_ASSIG = "BitwiseOrAssignment";

    /**
     * Constructor.
     */
    private Rule76() {
    }

    @Override
    public Node convert(final Node node, final Factory factory) {
        Node result = EmptyTree.INSTANCE;
        final Map<Integer, List<Node>> children = new TreeMap<>();
        final Map<Integer, String> data = new TreeMap<>();
        final boolean matched = Matcher143.INSTANCE.match(node, children, data);
        if (matched) {
            result = Rule76.firstBuilder(factory, children);
        }
        return result;
    }

    /**
     * Builds a node with 'BitwiseOrAssignment' type.
     * @param factory The node factory
     * @param children The collection of child nodes
     * @return A node
     */
    private static Node firstBuilder(final Factory factory,
        final Map<Integer, List<Node>> children) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule76.BITWISE_OR_ASSIG);
        final List<Node> list = new LinkedList<>();
        list.add(Rule76.secondBuilder(factory, children));
        list.addAll(children.get(Rule76.FIRST_HOLE_ID));
        final boolean applied = builder.setChildrenList(list);
        if (applied && builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }

    /**
     * Builds a node with 'Variable' type.
     * @param factory The node factory
     * @param children The collection of child nodes
     * @return A node
     */
    private static Node secondBuilder(final Factory factory,
        final Map<Integer, List<Node>> children) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule76.VARIABLE);
        final List<Node> list = children.get(1);
        final boolean applied = builder.setChildrenList(list);
        if (applied && builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }
}
