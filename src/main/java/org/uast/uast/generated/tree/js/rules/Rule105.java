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
public final class Rule105 implements Converter {
    /**
     * The instance.
     */
    public static final Converter INSTANCE = new Rule105();

    /**
     * The number of the first hole.
     */
    private static final int FIRST_HOLE_ID = 1;

    /**
     * The 'Name' string.
     */
    private static final String NAME = "Name";

    /**
     * The 'ClassType' string.
     */
    private static final String CLASS_TYPE = "ClassType";

    /**
     * The 'ExtendsBlock' string.
     */
    private static final String EXTENDS_BLOCK = "ExtendsBlock";

    /**
     * The 'ClassBody' string.
     */
    private static final String CLASS_BODY = "ClassBody";

    /**
     * The 'ClassDeclaration' string.
     */
    private static final String CLASS_DECLARATIO = "ClassDeclaration";

    /**
     * Constructor.
     */
    private Rule105() {
    }

    @Override
    public Node convert(final Node node, final Factory factory) {
        Node result = EmptyTree.INSTANCE;
        final Map<Integer, List<Node>> children = new TreeMap<>();
        final Map<Integer, String> data = new TreeMap<>();
        final boolean matched = Matcher334.INSTANCE.match(node, children, data);
        if (matched) {
            result = Rule105.firstBuilder(factory, children);
        }
        return result;
    }

    /**
     * Builds a node with 'ClassDeclaration' type.
     * @param factory The node factory
     * @param children The collection of child nodes
     * @return A node
     */
    private static Node firstBuilder(final Factory factory,
        final Map<Integer, List<Node>> children) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule105.CLASS_DECLARATIO);
        final List<Node> list = new LinkedList<>();
        list.addAll(children.get(Rule105.FIRST_HOLE_ID));
        list.add(Rule105.secondBuilder(factory, children));
        list.add(Rule105.fifthBuilder(factory));
        final boolean applied = builder.setChildrenList(list);
        if (applied && builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }

    /**
     * Builds a node with 'ExtendsBlock' type.
     * @param factory The node factory
     * @param children The collection of child nodes
     * @return A node
     */
    private static Node secondBuilder(final Factory factory,
        final Map<Integer, List<Node>> children) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule105.EXTENDS_BLOCK);
        final List<Node> list = new LinkedList<>();
        list.add(Rule105.thirdBuilder(factory, children));
        final boolean applied = builder.setChildrenList(list);
        if (applied && builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }

    /**
     * Builds a node with 'ClassType' type.
     * @param factory The node factory
     * @param children The collection of child nodes
     * @return A node
     */
    private static Node thirdBuilder(final Factory factory,
        final Map<Integer, List<Node>> children) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule105.CLASS_TYPE);
        final List<Node> list = new LinkedList<>();
        list.add(Rule105.fourthBuilder(factory, children));
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
    private static Node fourthBuilder(final Factory factory,
        final Map<Integer, List<Node>> children) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule105.NAME);
        final List<Node> list = children.get(2);
        final boolean applied = builder.setChildrenList(list);
        if (applied && builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }

    /**
     * Builds a node with 'ClassBody' type.
     * @param factory The node factory
     * @return A node
     */
    private static Node fifthBuilder(final Factory factory) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule105.CLASS_BODY);
        if (builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }
}
