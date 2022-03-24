/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.base;

/**
 * An abstract syntax tree node.
 *
 * @since 1.0
 */
public interface Node {
    /**
     * Returns the type of the node.
     * @return The type
     */
    Type getType();

    /**
     * Returns data associated with the node (in a textual format).
     * @return Node data or empty string
     */
    String getData();

    /**
     * Returns the number of children.
     * @return Child node count
     */
    int getChildCount();

    /**
     * Returns a child by its index.
     * @param index Child index
     * @return A node
     */
    Node getChild(int index);
}
