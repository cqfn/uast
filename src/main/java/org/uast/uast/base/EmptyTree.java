/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.base;

/**
 * The empty syntax tree.
 *
 * @since 1.0
 */
public final class EmptyTree implements Node {
    /**
     * The type.
     */
    public static final Type TYPE = new Type() {
        @Override
        public String getName() {
            return "<null>";
        }
    };

    /**
     * The instance.
     */
    public static final Node INSTANCE = new EmptyTree();

    /**
     * Private constructor.
     */
    private EmptyTree() {
    }

    @Override
    public Type getType() {
        return EmptyTree.TYPE;
    }

    @Override
    public String getData() {
        return "";
    }

    @Override
    public int getChildCount() {
        return 0;
    }

    @Override
    public Node getChild(final int index) {
        throw new IndexOutOfBoundsException();
    }
}
