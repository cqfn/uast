/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.base;

/**
 * Describes a fragment of source code.
 *
 * @since 1.0
 */
public interface Fragment {
    /**
     * Returns the source of the fragment.
     * @return Source instance
     */
    Source getSource();

    /**
     * Returns the first position of the fragment.
     * @return The first position.
     */
    Position getBegin();

    /**
     * Returns the last position of the fragment.
     * @return The last position.
     */
    Position getEnd();
}
