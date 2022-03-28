/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.exceptions;

/**
 * Base exception thrown by the project.
 *
 * @since 1.0
 */
public abstract class BaseException extends Exception {
    /**
     * Returns initiator name, i.e. module that thrown the exception.
     * @return A name
     */
    public abstract String getInitiator();

    /**
     * Returns error message.
     * @return A message
     */
    public abstract String getErrorMessage();
}
