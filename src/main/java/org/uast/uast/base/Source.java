/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.base;

/**
 * Represents a source code file.
 *
 * @since 1.0
 */
public interface Source {
    /**
     * Return a string for a source code fragment within the specified range.
     *
     * @param start Start position
     * @param end End position
     * @return Text of the fragment
     */
    String getFragmentAsString(Position start, Position end);
}
