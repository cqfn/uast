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
public final class Rule40 implements Converter {
    /**
     * The instance.
     */
    public static final Converter INSTANCE = new Rule40();

    /**
     * The number of the first hole.
     */
    private static final int FIRST_HOLE_ID = 1;

    /**
     * The 'Name' string.
     */
    private static final String NAME = "Name";

    /**
     * The 'Variable' string.
     */
    private static final String VARIABLE = "Variable";

    /**
     * The 'GreaterThanOrEqualTo' string.
     */
    private static final String GREATER_THAN_OR = "GreaterThanOrEqualTo";

    /**
     * Constructor.
     */
    private Rule40() {
    }

    @Override
    public Node convert(final Node node, final Factory factory) {
        Node result = EmptyTree.INSTANCE;
        final Map<Integer, List<Node>> children = new TreeMap<>();
        final Map<Integer, String> data = new TreeMap<>();
        final boolean matched = Matcher79.INSTANCE.match(node, children, data);
        if (matched) {
            result = Rule40.firstBuilder(factory, children);
        }
        return result;
    }

    /**
     * Builds a node with 'GreaterThanOrEqualTo' type.
     * @param factory The node factory
     * @param children The collection of child nodes
     * @return A node
     */
    private static Node firstBuilder(final Factory factory,
        final Map<Integer, List<Node>> children) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule40.GREATER_THAN_OR);
        final List<Node> list = new LinkedList<>();
        list.addAll(children.get(Rule40.FIRST_HOLE_ID));
        list.add(Rule40.secondBuilder(factory, children));
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
        final Builder builder = factory.createBuilder(Rule40.VARIABLE);
        final List<Node> list = new LinkedList<>();
        list.add(Rule40.thirdBuilder(factory, children));
        final boolean applied = builder.setChildrenList(list);
        if (applied && builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }

    /**
     * Builds a node with 'Name' type.
     * @param factory The node factory
     * @param children The collection of child nodes
     * @return A node
     */
    private static Node thirdBuilder(final Factory factory,
        final Map<Integer, List<Node>> children) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule40.NAME);
        final List<Node> list = children.get(2);
        final boolean applied = builder.setChildrenList(list);
        if (applied && builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }
}
