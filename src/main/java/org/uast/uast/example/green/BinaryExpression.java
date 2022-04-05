/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.example.green;

/**
 * Node that describes the 'BinaryExpression' type.
 *
 * @since 1.0
 */
public interface BinaryExpression extends Expression {
    /**
     * Returns a node with the 'left' tag.
     * @return A node
     */
    Expression getLeft();

    /**
     * Returns a node with the 'right' tag.
     * @return A node
     */
    Expression getRight();
}
