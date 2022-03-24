/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Draft node for wrapping the results of third-party parsers
 * in the {@link Node} interface.
 *
 * @since 1.0
 */
public class DraftNode implements Node {
    /**
     * The node type.
     */
    final Type type;

    /**
     * The node data.
     */
    final String data;

    /**
     * The list of children nodes.
     */
    final List<Node> children;

    /**
     * Constructor.
     * @param name Type name
     * @param data Data associated with the node (in a textual format)
     * @param children List of children nodes
     */
    public DraftNode(final String name, final String data, final List<Node> children) {
        this.type = new Type() {
            @Override
            public String getName() {
                return name;
            }
        };
        this.data = Objects.requireNonNull(data);
        this.children = new ArrayList<>(children);
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public String getData() {
        return this.data;
    }

    @Override
    public int getChildCount() {
        return this.children.size();
    }

    @Override
    public Node getChild(int index) {
        return this.children.get(index);
    }
}
