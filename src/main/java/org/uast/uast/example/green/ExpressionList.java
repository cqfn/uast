/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.example.green;

import java.util.ArrayList;
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
 * Node that describes the 'ExpressionList' type.
 *
 * @since 1.0
 */
public final class ExpressionList implements Node {
    /**
     * The type.
     */
    public static final Type TYPE = new TypeImpl();

    /**
     * The fragment associated with the node.
     */
    private Fragment fragment;

    /**
     * List of child nodes.
     */
    private List<Expression> children;

    /**
     * Private constructor.
     */
    private ExpressionList() {
    }

    @Override
    public Fragment getFragment() {
        return this.fragment;
    }

    @Override
    public Type getType() {
        return ExpressionList.TYPE;
    }

    @Override
    public String getData() {
        return "";
    }

    @Override
    public int getChildCount() {
        return this.children.size();
    }

    @Override
    public Node getChild(final int index) {
        return this.getExpression(index);
    }

    /**
     * Return a child with 'Expression' type by its index.
     * @param index Child index
     * @return A node
     */
    public Expression getExpression(final int index) {
        return this.children.get(index);
    }

    /**
     * Type descriptor of the 'ExpressionList' node.
     *
     * @since 1.0
     */
    private static class TypeImpl implements Type {
        /**
         * The 'ExpressionList' string.
         */
        private static final String EXPRESSION_LIST = "ExpressionList";

        /**
         * The 'Expression' string.
         */
        private static final String EXPRESSION = "Expression";

        /**
         * The list of child types.
         */
        private static final List<ChildDescriptor> CHILDREN =
            Collections.singletonList(
                new ChildDescriptor(TypeImpl.EXPRESSION)
            );

        /**
         * Hierarchy.
         */
        private static final List<String> HIERARCHY =
            Collections.unmodifiableList(
                Arrays.asList(
                    TypeImpl.EXPRESSION_LIST
                )
            );

        @Override
        public String getName() {
            return TypeImpl.EXPRESSION_LIST;
        }

        @Override
        public List<ChildDescriptor> getChildTypes() {
            return TypeImpl.CHILDREN;
        }

        @Override
        public List<String> getHierarchy() {
            return TypeImpl.HIERARCHY;
        }

        @Override
        public String getProperty(final String name) {
            return "";
        }

        @Override
        public Builder createBuilder() {
            return new Constructor();
        }
    }

    /**
     * Class for 'Addition' node construction.
     *
     * @since 1.0
     */
    public static final class Constructor implements Builder {
        /**
         * The fragment associated with the node.
         */
        private Fragment fragment = EmptyFragment.INSTANCE;

        /**
         * List of child nodes.
         */
        private List<Expression> children = Collections.emptyList();

        @Override
        public void setFragment(final Fragment obj) {
            this.fragment = obj;
        }

        @Override
        public boolean setData(final String str) {
            return str.isEmpty();
        }

        @Override
        public boolean setChildrenList(final List<Node> list) {
            boolean result = true;
            final List<Expression> clarified = new ArrayList<>(list.size());
            for (final Node node : list) {
                if (node instanceof Expression) {
                    clarified.add((Expression) node);
                } else {
                    result = false;
                    break;
                }
            }
            if (result) {
                this.children = Collections.unmodifiableList(clarified);
            }
            return result;
        }

        @Override
        public boolean isValid() {
            return true;
        }

        @Override
        public ExpressionList createNode() {
            if (!this.isValid()) {
                throw new IllegalStateException();
            }
            final ExpressionList node = new ExpressionList();
            node.fragment = this.fragment;
            node.children = this.children;
            return node;
        }
    }
}
