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
public final class Rule111 implements Converter {
    /**
     * The instance.
     */
    public static final Converter INSTANCE = new Rule111();

    /**
     * The number of the first hole.
     */
    private static final int FIRST_HOLE_ID = 1;

    /**
     * The 'Dimension' string.
     */
    private static final String DIMENSION = "Dimension";

    /**
     * The 'DimensionList' string.
     */
    private static final String DIMENSION_LIST = "DimensionList";

    /**
     * The 'ArrayType' string.
     */
    private static final String ARRAY_TYPE = "ArrayType";

    /**
     * Constructor.
     */
    private Rule111() {
    }

    @Override
    public Node convert(final Node node, final Factory factory) {
        Node result = EmptyTree.INSTANCE;
        final Map<Integer, List<Node>> children = new TreeMap<>();
        final Map<Integer, String> data = new TreeMap<>();
        final boolean matched = Matcher190.INSTANCE.match(node, children, data);
        if (matched) {
            result = Rule111.firstBuilder(factory, children);
        }
        return result;
    }

    /**
     * Builds a node with 'ArrayType' type.
     * @param factory The node factory
     * @param children The collection of child nodes
     * @return A node
     */
    private static Node firstBuilder(final Factory factory,
        final Map<Integer, List<Node>> children) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule111.ARRAY_TYPE);
        final List<Node> list = new LinkedList<>();
        list.addAll(children.get(Rule111.FIRST_HOLE_ID));
        list.add(Rule111.secondBuilder(factory));
        final boolean applied = builder.setChildrenList(list);
        if (applied && builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }

    /**
     * Builds a node with 'DimensionList' type.
     * @param factory The node factory
     * @return A node
     */
    private static Node secondBuilder(final Factory factory) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule111.DIMENSION_LIST);
        final List<Node> list = new LinkedList<>();
        list.add(Rule111.thirdBuilder(factory));
        final boolean applied = builder.setChildrenList(list);
        if (applied && builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }

    /**
     * Builds a node with 'Dimension' type.
     * @param factory The node factory
     * @return A node
     */
    private static Node thirdBuilder(final Factory factory) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule111.DIMENSION);
        if (builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }
}
