/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.exceptions;

/**
 * Exception "This rule cannot contain holes".
 *
 * @since 1.0
 */
public final class WrongFileExtension extends VisualizerException {
    /**
     * The instance.
     */
    public static final VisualizerException INSTANCE = new WrongFileExtension();

    /**
     * Constructor.
     */
    private WrongFileExtension() {
        super();
    }

    @Override
    public String getErrorMessage() {
        return "The file extension must be .png";
    }
}
