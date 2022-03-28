/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.exceptions;

/**
 * Exception thrown by the visualizer.
 *
 * @since 1.0
 */
public abstract class VisualizerException extends BaseException {
    @Override
    public final String getInitiator() {
        return "Visualizer";
    }
}
