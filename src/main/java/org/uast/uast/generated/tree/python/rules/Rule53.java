/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.python.rules;

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
public final class Rule53 implements Converter {
    /**
     * The instance.
     */
    public static final Converter INSTANCE = new Rule53();

    /**
     * The 'Identifier' string.
     */
    private static final String IDENTIFIER = "Identifier";

    /**
     * The 'ParameterBlock' string.
     */
    private static final String PARAMETER_BLOCK = "ParameterBlock";

    /**
     * The 'StatementBlock' string.
     */
    private static final String STATEMENT_BLOCK = "StatementBlock";

    /**
     * The 'FunctionDeclaration' string.
     */
    private static final String FUNCTION_DECLARA = "FunctionDeclaration";

    /**
     * Constructor.
     */
    private Rule53() {
    }

    @Override
    public Node convert(final Node node, final Factory factory) {
        Node result = EmptyTree.INSTANCE;
        final Map<Integer, List<Node>> children = new TreeMap<>();
        final Map<Integer, String> data = new TreeMap<>();
        final boolean matched = Matcher184.INSTANCE.match(node, children, data);
        if (matched) {
            result = Rule53.firstBuilder(factory, data);
        }
        return result;
    }

    /**
     * Builds a node with 'FunctionDeclaration' type.
     * @param factory The node factory
     * @param data The data
     * @return A node
     */
    private static Node firstBuilder(final Factory factory, final Map<Integer, String> data) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule53.FUNCTION_DECLARA);
        final List<Node> list = new LinkedList<>();
        list.add(Rule53.secondBuilder(factory, data));
        list.add(Rule53.thirdBuilder(factory));
        list.add(Rule53.fourthBuilder(factory));
        final boolean applied = builder.setChildrenList(list);
        if (applied && builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }

    /**
     * Builds a node with 'Identifier' type.
     * @param factory The node factory
     * @param data The data
     * @return A node
     */
    private static Node secondBuilder(final Factory factory, final Map<Integer, String> data) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule53.IDENTIFIER);
        final boolean set = builder.setData(data.get(1));
        if (set && builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }

    /**
     * Builds a node with 'ParameterBlock' type.
     * @param factory The node factory
     * @return A node
     */
    private static Node thirdBuilder(final Factory factory) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule53.PARAMETER_BLOCK);
        if (builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }

    /**
     * Builds a node with 'StatementBlock' type.
     * @param factory The node factory
     * @return A node
     */
    private static Node fourthBuilder(final Factory factory) {
        Node result = EmptyTree.INSTANCE;
        final Builder builder = factory.createBuilder(Rule53.STATEMENT_BLOCK);
        if (builder.isValid()) {
            result = builder.createNode();
        }
        return result;
    }
}
