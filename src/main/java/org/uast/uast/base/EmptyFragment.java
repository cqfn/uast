/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.base;

/**
 * The empty fragment.
 *
 * @since 1.0
 */
public final class EmptyFragment implements Fragment {
    /**
     * The instance.
     */
    public static final Fragment INSTANCE = new EmptyFragment();

    /**
     * The source.
     */
    private static final Source SOURCE = new Source() {
        @Override
        public String getFragmentAsString(final Position start, final Position end) {
            return "";
        }
    };

    /**
     * The position.
     */
    private static final Position POSITION = new Position() {
        @Override
        public int getIndex() {
            return 0;
        }
    };

    /**
     * Constructor.
     */
    private EmptyFragment() {
    }

    @Override
    public Source getSource() {
        return EmptyFragment.SOURCE;
    }

    @Override
    public Position getBegin() {
        return EmptyFragment.POSITION;
    }

    @Override
    public Position getEnd() {
        return EmptyFragment.POSITION;
    }
}
