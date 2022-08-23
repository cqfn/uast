/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.algorithms;

import org.uast.uast.base.EmptyTree;
import org.uast.uast.base.Node;

/**
 * The algorithm that conducts carrying.
 *
 * @since 1.0
 */
public final class Carrying implements Algorithm {
    /**
     * The instance.
     */
    public static final Carrying INSTANCE = new Carrying();

    /**
     * Constructor.
     */
    private Carrying() {
    }

    @Override
    public Node process(final Node tree) {
        return EmptyTree.INSTANCE;
    }
}
