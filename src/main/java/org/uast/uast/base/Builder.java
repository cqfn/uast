/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.base;

import java.util.List;

/**
 * Object that creates nodes.
 *
 * @since 1.0
 */
public interface Builder {
    /**
     * Associate a new fragment with the node.
     * @param fragment A new fragment
     */
    void setFragment(Fragment fragment);

    /**
     * Associate a new data with the node (in a textual format).
     * @param str Data as a string
     * @return Result of operation, {@code true} if the new data is suitable for this type of node
     */
    boolean setData(String str);

    /**
     * Sets a new list of children.
     * @param list A list of children
     * @return Result of operation, {@code true} if the new children list is suitable
     *  for this type of node
     */
    boolean setChildrenList(List<Node> list);

    /**
     * Checks if the Creator is in a valid state,
     * that is, whether it can create a new node based on its internal data.
     * @return Checking result
     */
    boolean isValid();

    /**
     * Creates a specific node.
     * @return A node
     */
    Node createNode();
}
