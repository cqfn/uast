/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022 Ivan Kniazkov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cqfn.uast.algorithms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.cqfn.astranaut.core.Builder;
import org.cqfn.astranaut.core.EmptyTree;
import org.cqfn.astranaut.core.Factory;
import org.cqfn.astranaut.core.Node;
import org.cqfn.astranaut.core.NodeReplacer;
import org.cqfn.astranaut.core.utils.Pair;
import org.cqfn.uast.tree.green.GreenFactory;

/**
 * The algorithm that performs replacement of "red" nodes with "green".
 *
 * @since 0.1
 */
public final class Greening implements Algorithm {
    /**
     * The instance.
     */
    public static final Greening INSTANCE = new Greening();

    /**
     * Constructor.
     */
    private Greening() {
    }

    @Override
    public Node process(final Node tree) {
        Node result = tree;
        final Map<Node, Node> trees = Greening.findReplacementPairs(tree);
        for (final Map.Entry<Node, Node> entry : trees.entrySet()) {
            final Pair<Node, Integer> modification =
                new NodeReplacer().replace(result, entry.getKey(), entry.getValue());
            result = modification.getKey();
        }
        return result;
    }

    /**
     * Finds the subtree that should be replaced, creates a new subtree and matches them.
     * @param tree The root of an initial tree
     * @return Mappings of a subtree to be replaced and a newly created subtree
     */
    private static Map<Node, Node> findReplacementPairs(final Node tree) {
        final Map<Node, Node> result = new HashMap<>();
        final List<Node> list = new LinkedList<>();
        list.add(tree);
        while (!list.isEmpty()) {
            final Node node = list.get(0);
            list.remove(0);
            list.addAll(node.getChildrenList());
            final String type = node.getTypeName();
            if ("ClassBody".equals(type)) {
                final List<Node> children = Greening.processClassBody(node);
                if (!children.equals(node.getChildrenList())) {
                    final Node body = Greening.createClassBody(node, children);
                    result.put(node, body);
                }
            }
        }
        return result;
    }

    /**
     * Creates a {@link org.cqfn.uast.tree.green.ClassBody} node.
     * @param tree The root of an initial tree
     * @param children New list of children for the provided root
     * @return New node
     */
    private static Node createClassBody(final Node tree, final List<Node> children) {
        Node result = EmptyTree.INSTANCE;
        final Builder body = tree.getType().createBuilder();
        final boolean applied = body.setChildrenList(children);
        if (applied && body.isValid()) {
            result = body.createNode();
        }
        return result;
    }

    /**
     * Iterates over {@link org.cqfn.uast.tree.green.ClassBody} children
     * and replaces all {@link org.cqfn.uast.tree.js.Property} nodes
     * with their "green" analogues.
     * @param tree The {@link org.cqfn.uast.tree.green.ClassBody} node
     * @return List of child nodes that may contain replaced children
     */
    private static List<Node> processClassBody(final Node tree) {
        final List<Node> result = new LinkedList<>();
        for (int idx = 0; idx < tree.getChildCount(); idx += 1) {
            final Node child = tree.getChild(idx);
            final String type = child.getTypeName();
            if ("Property".equals(type)) {
                result.add(convertPropertyToField(child));
            } else {
                result.add(child);
            }
        }
        return result;
    }

    /**
     * Converts a {@link org.cqfn.uast.tree.js.Property} node to
     * a {@link org.cqfn.uast.tree.green.FieldDeclaration} node.
     * @param tree The {@link org.cqfn.uast.tree.js.Property} node
     * @return New node
     */
    private static Node convertPropertyToField(final Node tree) {
        Node result = EmptyTree.INSTANCE;
        final Factory factory =  GreenFactory.INSTANCE;
        final Builder field = factory.createBuilder("FieldDeclaration");
        final List<Node> list = new LinkedList<>();
        list.add(createModifierBlock());
        list.add(createDeclaratorList(tree));
        final boolean applied = field.setChildrenList(list);
        if (applied && field.isValid()) {
            result = field.createNode();
        }
        return result;
    }

    /**
     * Creates a {@link org.cqfn.uast.tree.green.ModifierBlock} node.
     * @return New node
     */
    private static Node createModifierBlock() {
        Node result = EmptyTree.INSTANCE;
        final Factory factory =  GreenFactory.INSTANCE;
        final Builder block = factory.createBuilder("ModifierBlock");
        final Builder modifier = factory.createBuilder("Modifier");
        modifier.setData("public");
        final List<Node> list = new LinkedList<>();
        if (modifier.isValid()) {
            list.add(modifier.createNode());
        }
        final boolean applied = block.setChildrenList(list);
        if (applied && block.isValid()) {
            result = block.createNode();
        }
        return result;
    }

    /**
     * Creates a {@link org.cqfn.uast.tree.green.DeclaratorList} node from a given
     * {@link org.cqfn.uast.tree.js.Property} node.
     * @param tree The {@link org.cqfn.uast.tree.js.Property} node
     * @return New node
     */
    private static Node createDeclaratorList(final Node tree) {
        Node result = EmptyTree.INSTANCE;
        final Factory factory =  GreenFactory.INSTANCE;
        final Builder block = factory.createBuilder("DeclaratorList");
        final Builder declarator = factory.createBuilder("Declarator");
        List<Node> list = new LinkedList<>();
        list.add(tree.getChild(0));
        list.add(tree.getChild(1));
        boolean applied = declarator.setChildrenList(list);
        list = new LinkedList<>();
        if (applied && declarator.isValid()) {
            list.add(declarator.createNode());
        }
        applied = block.setChildrenList(list);
        if (applied && block.isValid()) {
            result = block.createNode();
        }
        return result;
    }
}
