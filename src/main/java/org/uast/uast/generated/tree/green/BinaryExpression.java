/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.green;

/**
 * Node that describes the 'BinaryExpression' type.
 *
 * @since 1.0
 */
public interface BinaryExpression extends Expression {
    /**
     * Returns the child with the 'left' tag.
     * @return The node
     */
    Expression getLeft();

    /**
     * Returns the child with the 'right' tag.
     * @return The node
     */
    Expression getRight();
}
