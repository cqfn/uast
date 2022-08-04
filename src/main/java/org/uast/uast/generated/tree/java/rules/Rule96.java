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
public final class Rule96 implements Converter {
    /**
     * The instance.
     */
    public static final Converter INSTANCE = new Rule96();

    /**
     * The number of the first hole.
     */
    private static final int FIRST_HOLE_ID = 1;

    /**
     * The number of the second hole.
     */
    private static final int SECOND_HOLE_ID = 2;

    /**
     * The number of the third hole.
     */
    private static final int THIRD_HOLE_ID = 3;

    /**
     * The 'Declarator' string.
     */
    private static final String DECLARATOR = "Declarator";

    /**
     * The 'DeclaratorList' string.
     */
    private static final String DECLARATOR_LIST = "DeclaratorList";

    /**
     * The 'VariableDeclaration' string.
     */
    private static final String VARIABLE_DECLARA = "VariableDeclaration";

    /**
     * Constructor.
     */
    private Rule96() {
    }

    @Override
    public Node convert(final Node node, final Factory factory) {
        Node result = EmptyTree.INSTANCE;
        final Map<Integer, List<Node>> children = new TreeMap<>();
        final Map<Integer, String> data = new TreeMap<>();
        final boolean matched = Matcher170.INSTANCE.match(node, children, data);
        if (matched) {
            result = Rule96.firstBuilder(factory, children);
        }
        return result;
    }

    /**
     * Builds a node with 'VariableDeclaration' type.
     * @param factory The node factory
     * @param children The collection of child nodes
     * @return A node
     */
    private static Node firstBuilder(final Factory factory,
        final Map<Integer, List<Node>> children) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule96.VARIABLE_DECLARA);
        final List<Node> list = new LinkedList<>();
        list.addAll(children.get(Rule96.FIRST_HOLE_ID));
        list.add(Rule96.secondBuilder(factory, children));
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
        final Builder builder = factory.createBuilder(Rule96.DECLARATOR_LIST);
        final List<Node> list = new LinkedList<>();
        list.add(Rule96.thirdBuilder(factory, children));
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
        final Builder builder = factory.createBuilder(Rule96.DECLARATOR);
        final List<Node> list = new LinkedList<>();
        list.addAll(children.get(Rule96.SECOND_HOLE_ID));
        list.addAll(children.get(Rule96.THIRD_HOLE_ID));
        final boolean applied = builder.setChildrenList(list);
        if (applied && builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }
}
