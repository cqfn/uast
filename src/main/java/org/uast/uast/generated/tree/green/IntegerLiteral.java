/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.green;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.uast.uast.base.Builder;
import org.uast.uast.base.ChildDescriptor;
import org.uast.uast.base.EmptyFragment;
import org.uast.uast.base.Fragment;
import org.uast.uast.base.Node;
import org.uast.uast.base.Type;

/**
 * Node that describes the 'IntegerLiteral' type.
 *
 * @since 1.0
 */
public final class IntegerLiteral implements Node {
    /**
     * The type.
     */
    public static final Type TYPE = new TypeImpl();

    /**
     * The fragment associated with the node.
     */
    private Fragment fragment;

    /**
     * The data.
     */
    private int data;

    /**
     * Constructor.
     */
    private IntegerLiteral() {
    }

    @Override
    public Type getType() {
        return IntegerLiteral.TYPE;
    }

    @Override
    public Fragment getFragment() {
        return this.fragment;
    }

    @Override
    public String getData() {
        return String.valueOf(this.data);
    }

    @Override
    public int getChildCount() {
        return 0;
    }

    @Override
    public Node getChild(final int index) {
        throw new IndexOutOfBoundsException();
    }

    /**
     * Type descriptor of the 'IntegerLiteral' node.
     *
     * @since 1.0
     */
    private static class TypeImpl implements Type {
        /**
         * The 'IntegerLiteral' string.
         */
        private static final String INTEGER_LITERAL = "IntegerLiteral";

        /**
         * Hierarchy.
         */
        private static final List<String> HIERARCHY =
            Collections.unmodifiableList(
                Arrays.asList(
                )
            );

        @Override
        public String getName() {
            return TypeImpl.INTEGER_LITERAL;
        }

        @Override
        public List<ChildDescriptor> getChildTypes() {
            return Collections.emptyList();
        }

        @Override
        public List<String> getHierarchy() {
            return TypeImpl.HIERARCHY;
        }

        @Override
        public Builder createBuilder() {
            return new Constructor();
        }
    }

    /**
     * Class for 'IntegerLiteral' node construction.
     *
     * @since 1.0
     */
    public static final class Constructor implements Builder {
        /**
         * The fragment associated with the node.
         */
        private Fragment fragment = EmptyFragment.INSTANCE;

        /**
         * The flag indicating that the builder has been initialized.
         */
        private boolean initialized;

        /**
         * The data.
         */
        private int data;

        @Override
        public void setFragment(final Fragment obj) {
            this.fragment = obj;
        }

        @Override
        public boolean setData(final String value) {
            boolean success = true;
            try {
                this.data = Integer.parseInt(value);
                this.initialized = true;
            } catch (final NumberFormatException ignored) {
                success = false;
            }
            return success;
        }

        @Override
        public boolean setChildrenList(final List<Node> list) {
            return list.isEmpty();
        }

        @Override
        public boolean isValid() {
            return this.initialized;
        }

        @Override
        public IntegerLiteral createNode() {
            final IntegerLiteral node = new IntegerLiteral();
            node.fragment = this.fragment;
            node.data = this.data;
            return node;
        }
    }
}
