/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.base;

/**
 * This class describes child node within type descriptor.
 *
 * @since 1.0
 */
public final class ChildDescriptor {
    /**
     * The name of the child type.
     */
    private final String type;

    /**
     * Flag that states that the child is optional.
     */
    private final boolean optional;

    /**
     * Constructor.
     * @param type The name of the child type
     * @param optional Flag that states that the child is optional
     */
    public ChildDescriptor(final String type, final  boolean optional) {
        this.type = type;
        this.optional = optional;
    }

    /**
     * Additional constructor.
     * @param type The name of the child type
     */
    public ChildDescriptor(final String type) {
        this(type, false);
    }

    /**
     * Returns the name of the child type.
     * @return The name
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns the flag that states that the child is optional.
     * @return The optional flag
     */
    public boolean isOptional() {
        return this.optional;
    }

    @Override
    public String toString() {
        final String result;
        if (this.optional) {
            result = new StringBuilder()
                .append('[')
                .append(this.type)
                .append(']')
                .toString();
        } else {
            result = this.type;
        }
        return result;
    }
}
