/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.green;

/**
 * Node that describes the 'Assignment' type.
 *
 * @since 1.0
 */
public interface Assignment extends Statement {
    /**
     * Returns the child with the 'left' tag.
     * @return The node
     */
    AssignableExpression getLeft();

    /**
     * Returns the child with the 'right' tag.
     * @return The node
     */
    Expression getRight();
}
