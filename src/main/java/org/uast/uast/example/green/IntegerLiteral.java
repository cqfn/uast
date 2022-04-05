/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.example.green;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
public final class IntegerLiteral implements Expression {
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
     * Private constructor.
     */
    private IntegerLiteral() {
    }

    @Override
    public Fragment getFragment() {
        return this.fragment;
    }

    @Override
    public Type getType() {
        return IntegerLiteral.TYPE;
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
     * Type descriptor of the 'Addition' node.
     *
     * @since 1.0
     */
    private static class TypeImpl implements Type {
        /**
         * The name.
         */
        private static final String NAME = "IntegerLiteral";

        /**
         * The 'Expression' string.
         */
        private static final String EXPRESSION = "Expression";

        /**
         * Hierarchy.
         */
        private static final List<String> HIERARCHY =
            Collections.unmodifiableList(
                Arrays.asList(
                    TypeImpl.NAME,
                    TypeImpl.EXPRESSION
                )
            );

        @Override
        public String getName() {
            return TypeImpl.NAME;
        }

        @Override
        public List<ChildDescriptor> getChildTypes() {
            return Collections.emptyList();
        }

        @Override
        public List<String> getHierarchy() {
            return TypeImpl.HIERARCHY;
        }
    }

    /**
     * Class for 'IntegerLiteral' node construction.
     *
     * @since 1.0
     */
    public static class Constructor {
        /**
         * The fragment associated with the node.
         */
        private Fragment fragment = EmptyFragment.INSTANCE;

        /**
         * Node with the 'left' tag.
         */
        private int data;

        /**
         * Associate a new fragment with the node.
         * @param obj A new fragment
         */
        public void setFragment(final Fragment obj) {
            this.fragment = obj;
        }

        /**
         * Sets the data.
         * @param str Data as a string
         */
        public void setData(final String str) {
            this.data = Integer.parseInt(str);
        }

        /**
         * Creates a new node from the collected data.
         * @return A new node
         */
        public IntegerLiteral create() {
            final IntegerLiteral node = new IntegerLiteral();
            node.fragment = this.fragment;
            node.data = this.data;
            return node;
        }
    }
}
