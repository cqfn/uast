/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.algorithms;

import org.uast.uast.base.Node;

/**
 * An algorithm to be applied to a UAST.
 *
 * @since 1.0
 */
public interface Algorithm {
    /**
     * Processes a UAST.
     * @param tree The tree to be processed
     * @return The result of the processing
     */
    Node process(Node tree);
}
