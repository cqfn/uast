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
public final class Rule121 implements Converter {
    /**
     * The instance.
     */
    public static final Converter INSTANCE = new Rule121();

    /**
     * The 'Modifier' string.
     */
    private static final String MODIFIER = "Modifier";

    /**
     * The 'ModifierBlock' string.
     */
    private static final String MODIFIER_BLOCK = "ModifierBlock";

    /**
     * The number of the first hole.
     */
    private static final int FIRST_HOLE_ID = 2;

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
    private Rule121() {
    }

    @Override
    public Node convert(final Node node, final Factory factory) {
        Node result = EmptyTree.INSTANCE;
        final Map<Integer, List<Node>> children = new TreeMap<>();
        final Map<Integer, String> data = new TreeMap<>();
        final boolean matched = Matcher206.INSTANCE.match(node, children, data);
        if (matched) {
            result = Rule121.firstBuilder(factory, children, data);
        }
        return result;
    }

    /**
     * Builds a node with 'ClassDeclaration' type.
     * @param factory The node factory
     * @param children The collection of child nodes
     * @param data The data
     * @return A node
     */
    private static Node firstBuilder(final Factory factory,
        final Map<Integer, List<Node>> children,
        final Map<Integer, String> data) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule121.CLASS_DECLARATIO);
        final List<Node> list = new LinkedList<>();
        list.add(Rule121.secondBuilder(factory, data));
        list.addAll(children.get(Rule121.FIRST_HOLE_ID));
        list.add(Rule121.fourthBuilder(factory, children));
        list.add(Rule121.sixthBuilder(factory));
        final boolean applied = builder.setChildrenList(list);
        if (applied && builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }

    /**
     * Builds a node with 'ModifierBlock' type.
     * @param factory The node factory
     * @param data The data
     * @return A node
     */
    private static Node secondBuilder(final Factory factory, final Map<Integer, String> data) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule121.MODIFIER_BLOCK);
        final List<Node> list = new LinkedList<>();
        list.add(Rule121.thirdBuilder(factory, data));
        final boolean applied = builder.setChildrenList(list);
        if (applied && builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }

    /**
     * Builds a node with 'Modifier' type.
     * @param factory The node factory
     * @param data The data
     * @return A node
     */
    private static Node thirdBuilder(final Factory factory, final Map<Integer, String> data) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule121.MODIFIER);
        final boolean set = builder.setData(data.get(1));
        if (set && builder.isValid()) {
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
    private static Node fourthBuilder(final Factory factory,
        final Map<Integer, List<Node>> children) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule121.EXTENDS_BLOCK);
        final List<Node> list = new LinkedList<>();
        list.add(Rule121.fifthBuilder(factory, children));
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
    private static Node fifthBuilder(final Factory factory,
        final Map<Integer, List<Node>> children) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule121.CLASS_TYPE);
        final List<Node> list = children.get(3);
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
    private static Node sixthBuilder(final Factory factory) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule121.CLASS_BODY);
        if (builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }
}
