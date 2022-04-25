/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.example.green;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.uast.uast.base.Builder;
import org.uast.uast.base.ChildDescriptor;
import org.uast.uast.base.ChildrenMapper;
import org.uast.uast.base.EmptyFragment;
import org.uast.uast.base.Fragment;
import org.uast.uast.base.Node;
import org.uast.uast.base.Type;

/**
 * Node that describes the 'Addition' type.
 *
 * Addition
 * ->
 * left@Expression, right@Expression
 *
 * @since 1.0
 */
public final class Addition implements BinaryExpression {
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
    private List<Node> children;

    /**
     * Node with the 'left' tag.
     */
    private Expression left;

    /**
     * Node with the 'right' tag.
     */
    private Expression right;

    /**
     * Private constructor.
     */
    private Addition() {
    }

    @Override
    public Fragment getFragment() {
        return this.fragment;
    }

    @Override
    public Type getType() {
        return Addition.TYPE;
    }

    @Override
    public String getData() {
        return "";
    }

    @Override
    public int getChildCount() {
        return 2;
    }

    @Override
    public Node getChild(final int index) {
        return this.children.get(index);
    }

    @Override
    public Expression getLeft() {
        return this.left;
    }

    @Override
    public Expression getRight() {
        return this.right;
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
        private static final String NAME = "Addition";

        /**
         * The 'BinaryExpression' string.
         */
        private static final String BINARY_EXPRESSION = "BinaryExpression";

        /**
         * The 'Expression' string.
         */
        private static final String EXPRESSION = "Expression";

        /**
         * The list of child types.
         */
        private static final List<ChildDescriptor> CHILDREN =
            Collections.unmodifiableList(
                Arrays.asList(
                    new ChildDescriptor(TypeImpl.EXPRESSION),
                    new ChildDescriptor(TypeImpl.EXPRESSION)
                )
            );

        /**
         * Hierarchy.
         */
        private static final List<String> HIERARCHY =
            Collections.unmodifiableList(
                Arrays.asList(
                    TypeImpl.NAME,
                    TypeImpl.BINARY_EXPRESSION,
                    TypeImpl.EXPRESSION
                )
            );

        @Override
        public String getName() {
            return TypeImpl.NAME;
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
         * Node with the 'left' tag.
         */
        private Expression left;

        /**
         * Node with the 'right' tag.
         */
        private Expression right;

        @Override
        public void setFragment(final Fragment obj) {
            this.fragment = obj;
        }

        @Override
        public boolean setData(final String str) {
            return str.isEmpty();
        }

        /**
         * Sets the node with the 'left' tag.
         * @param node Node
         */
        public void setLeft(final Expression node) {
            this.left = node;
        }

        /**
         * Sets the node with the 'right' tag.
         * @param node Node
         */
        public void setRight(final Expression node) {
            this.right = node;
        }

        @Override
        public boolean setChildrenList(final List<Node> list) {
            final Node[] mapping = new Node[2];
            final ChildrenMapper mapper = new ChildrenMapper(Addition.TYPE.getChildTypes());
            final boolean result = mapper.map(mapping, list);
            if (result) {
                this.left = (Expression) mapping[0];
                this.right = (Expression) mapping[1];
            }
            return result;
        }

        @Override
        public boolean isValid() {
            return this.left != null && this.right != null;
        }

        @Override
        public Addition createNode() {
            if (!this.isValid()) {
                throw new IllegalStateException();
            }
            final Addition node = new Addition();
            node.fragment = this.fragment;
            node.children = Arrays.asList(this.left, this.right);
            node.left = this.left;
            node.right = this.right;
            return node;
        }
    }
}
