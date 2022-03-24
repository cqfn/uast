/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.base;

/**
 * A type of abstract syntax tree node.
 *
 * @since 1.0
 */
public interface Type {
    /**
     * Returns the type name.
     * @return The type name
     */
    String getName();
}
