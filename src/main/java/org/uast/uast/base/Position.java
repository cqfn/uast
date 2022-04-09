/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.base;

/**
 * Represents a position in source code file.
 *
 * @since 1.0
 */
public interface Position {
    /**
     * Returns absolute position (character index).
     * @return The index
     */
    int getIndex();
}
