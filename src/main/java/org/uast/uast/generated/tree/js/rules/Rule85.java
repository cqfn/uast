/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.js.rules;

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
public final class Rule85 implements Converter {
    /**
     * The instance.
     */
    public static final Converter INSTANCE = new Rule85();

    /**
     * The 'Name' string.
     */
    private static final String NAME = "Name";

    /**
     * The number of the first hole.
     */
    private static final int FIRST_HOLE_ID = 2;

    /**
     * The 'ExpressionList' string.
     */
    private static final String EXPRESSION_LIST = "ExpressionList";

    /**
     * The 'FunctionCallExpression' string.
     */
    private static final String FUNCTION_CALL_EX = "FunctionCallExpression";

    /**
     * Constructor.
     */
    private Rule85() {
    }

    @Override
    public Node convert(final Node node, final Factory factory) {
        Node result = EmptyTree.INSTANCE;
        final Map<Integer, List<Node>> children = new TreeMap<>();
        final Map<Integer, String> data = new TreeMap<>();
        final boolean matched = Matcher253.INSTANCE.match(node, children, data);
        if (matched) {
            result = Rule85.firstBuilder(factory, children);
        }
        return result;
    }

    /**
     * Builds a node with 'FunctionCallExpression' type.
     * @param factory The node factory
     * @param children The collection of child nodes
     * @return A node
     */
    private static Node firstBuilder(final Factory factory,
        final Map<Integer, List<Node>> children) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule85.FUNCTION_CALL_EX);
        final List<Node> list = new LinkedList<>();
        list.add(Rule85.secondBuilder(factory, children));
        list.addAll(children.get(Rule85.FIRST_HOLE_ID));
        list.add(Rule85.thirdBuilder(factory, children));
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
    private static Node secondBuilder(final Factory factory,
        final Map<Integer, List<Node>> children) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule85.NAME);
        final List<Node> list = children.get(1);
        final boolean applied = builder.setChildrenList(list);
        if (applied && builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }

    /**
     * Builds a node with 'ExpressionList' type.
     * @param factory The node factory
     * @param children The collection of child nodes
     * @return A node
     */
    private static Node thirdBuilder(final Factory factory,
        final Map<Integer, List<Node>> children) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule85.EXPRESSION_LIST);
        final List<Node> list = children.get(3);
        final boolean applied = builder.setChildrenList(list);
        if (applied && builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }
}
