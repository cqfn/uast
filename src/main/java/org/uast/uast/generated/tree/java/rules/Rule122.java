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
public final class Rule122 implements Converter {
    /**
     * The instance.
     */
    public static final Converter INSTANCE = new Rule122();

    /**
     * The number of the first hole.
     */
    private static final int FIRST_HOLE_ID = 1;

    /**
     * The 'Declarator' string.
     */
    private static final String DECLARATOR = "Declarator";

    /**
     * The 'DeclaratorList' string.
     */
    private static final String DECLARATOR_LIST = "DeclaratorList";

    /**
     * The 'FieldDeclaration' string.
     */
    private static final String FIELD_DECLARATIO = "FieldDeclaration";

    /**
     * Constructor.
     */
    private Rule122() {
    }

    @Override
    public Node convert(final Node node, final Factory factory) {
        Node result = EmptyTree.INSTANCE;
        final Map<Integer, List<Node>> children = new TreeMap<>();
        final Map<Integer, String> data = new TreeMap<>();
        final boolean matched = Matcher209.INSTANCE.match(node, children, data);
        if (matched) {
            result = Rule122.firstBuilder(factory, children);
        }
        return result;
    }

    /**
     * Builds a node with 'FieldDeclaration' type.
     * @param factory The node factory
     * @param children The collection of child nodes
     * @return A node
     */
    private static Node firstBuilder(final Factory factory,
        final Map<Integer, List<Node>> children) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule122.FIELD_DECLARATIO);
        final List<Node> list = new LinkedList<>();
        list.addAll(children.get(Rule122.FIRST_HOLE_ID));
        list.add(Rule122.secondBuilder(factory, children));
        final boolean applied = builder.setChildrenList(list);
        if (applied && builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }

    /**
     * Builds a node with 'DeclaratorList' type.
     * @param factory The node factory
     * @param children The collection of child nodes
     * @return A node
     */
    private static Node secondBuilder(final Factory factory,
        final Map<Integer, List<Node>> children) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule122.DECLARATOR_LIST);
        final List<Node> list = new LinkedList<>();
        list.add(Rule122.thirdBuilder(factory, children));
        final boolean applied = builder.setChildrenList(list);
        if (applied && builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }

    /**
     * Builds a node with 'Declarator' type.
     * @param factory The node factory
     * @param children The collection of child nodes
     * @return A node
     */
    private static Node thirdBuilder(final Factory factory,
        final Map<Integer, List<Node>> children) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule122.DECLARATOR);
        final List<Node> list = children.get(2);
        final boolean applied = builder.setChildrenList(list);
        if (applied && builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }
}
