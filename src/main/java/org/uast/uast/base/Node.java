/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.base;

import java.util.Arrays;
import java.util.List;

/**
 * An abstract syntax tree node.
 *
 * @since 1.0
 */
public interface Node {
    /**
     * Returns the fragment associated with the node.
     * @return The fragment
     */
    Fragment getFragment();

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

    /**
     * Returns the name of the type.
     * @return The name
     */
    default String getTypeName() {
        return this.getType().getName();
    }

    /**
     * Checks whether the node type belongs to group.
     * @param type The type name
     * @return Checking result, {@code true} if the type belongs to the group
     */
    default boolean belongsToGroup(final String type) {
        return this.getType().belongsToGroup(type);
    }

    /**
     * Returns the list of child nodes.
     * @return The node list
     */
    default List<Node> getChildrenList() {
        final int count = this.getChildCount();
        final Node[] result = new Node[count];
        for (int index = 0; index < count; index = index + 1) {
            result[index] = this.getChild(index);
        }
        return Arrays.asList(result);
    }
}
