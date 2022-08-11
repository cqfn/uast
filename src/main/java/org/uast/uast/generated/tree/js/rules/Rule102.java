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
public final class Rule102 implements Converter {
    /**
     * The instance.
     */
    public static final Converter INSTANCE = new Rule102();

    /**
     * The number of the first hole.
     */
    private static final int FIRST_HOLE_ID = 1;

    /**
     * The number of the second hole.
     */
    private static final int SECOND_HOLE_ID = 2;

    /**
     * The 'SubtractionAssignment' string.
     */
    private static final String SUBTRACTION_ASSI = "SubtractionAssignment";

    /**
     * Constructor.
     */
    private Rule102() {
    }

    @Override
    public Node convert(final Node node, final Factory factory) {
        Node result = EmptyTree.INSTANCE;
        final Map<Integer, List<Node>> children = new TreeMap<>();
        final Map<Integer, String> data = new TreeMap<>();
        final boolean matched = Matcher301.INSTANCE.match(node, children, data);
        if (matched) {
            result = Rule102.firstBuilder(factory, children);
        }
        return result;
    }

    /**
     * Builds a node with 'SubtractionAssignment' type.
     * @param factory The node factory
     * @param children The collection of child nodes
     * @return A node
     */
    private static Node firstBuilder(final Factory factory,
        final Map<Integer, List<Node>> children) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule102.SUBTRACTION_ASSI);
        final List<Node> list = new LinkedList<>();
        list.addAll(children.get(Rule102.FIRST_HOLE_ID));
        list.addAll(children.get(Rule102.SECOND_HOLE_ID));
        final boolean applied = builder.setChildrenList(list);
        if (applied && builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }
}
