/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.green;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.uast.uast.base.Builder;
import org.uast.uast.base.ChildDescriptor;
import org.uast.uast.base.ChildrenMapper;
import org.uast.uast.base.EmptyFragment;
import org.uast.uast.base.Fragment;
import org.uast.uast.base.ListUtils;
import org.uast.uast.base.Node;
import org.uast.uast.base.Type;

/**
 * Node that describes the 'BitwiseAndAssignment' type.
 *
 * @since 1.0
 */
public final class BitwiseAndAssignment implements Assignment {
    /**
     * The type.
     */
    public static final Type TYPE = new TypeImpl();

    /**
     * The number of children.
     */
    private static final int CHILD_COUNT = 2;

    /**
     * The fragment associated with the node.
     */
    private Fragment fragment;

    /**
     * List of child nodes.
     */
    private List<Node> children;

    /**
     * Child with the 'left' tag.
     */
    private AssignableExpression left;

    /**
     * Child with the 'right' tag.
     */
    private Expression right;

    /**
     * Constructor.
     */
    private BitwiseAndAssignment() {
    }

    @Override
    public Type getType() {
        return BitwiseAndAssignment.TYPE;
    }

    @Override
    public Fragment getFragment() {
        return this.fragment;
    }

    @Override
    public String getData() {
        return "";
    }

    @Override
    public int getChildCount() {
        return BitwiseAndAssignment.CHILD_COUNT;
    }

    @Override
    public Node getChild(final int index) {
        return this.children.get(index);
    }

    @Override
    public AssignableExpression getLeft() {
        return this.left;
    }

    @Override
    public Expression getRight() {
        return this.right;
    }

    /**
     * Type descriptor of the 'BitwiseAndAssignment' node.
     *
     * @since 1.0
     */
    private static class TypeImpl implements Type {
        /**
         * The 'BitwiseAndAssignment' string.
         */
        private static final String BITWISE_AND_ASSI = "BitwiseAndAssignment";

        /**
         * The 'AssignableExpression' string.
         */
        private static final String ASSIGNABLE_EXPRE = "AssignableExpression";

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
                    new ChildDescriptor(
                        TypeImpl.ASSIGNABLE_EXPRE,
                        false
                    ),
                    new ChildDescriptor(
                        TypeImpl.EXPRESSION,
                        false
                    )
                )
            );

        /**
         * The 'Assignment' string.
         */
        private static final String ASSIGNMENT = "Assignment";

        /**
         * The 'Statement' string.
         */
        private static final String STATEMENT = "Statement";

        /**
         * The 'ProgramItem' string.
         */
        private static final String PROGRAM_ITEM = "ProgramItem";

        /**
         * Hierarchy.
         */
        private static final List<String> HIERARCHY =
            Collections.unmodifiableList(
                Arrays.asList(
                    TypeImpl.BITWISE_AND_ASSI,
                    TypeImpl.ASSIGNMENT,
                    TypeImpl.STATEMENT,
                    TypeImpl.PROGRAM_ITEM
                )
            );

        /**
         * Properties.
         */
        private static final Map<String, String> PROPERTIES = Stream.of(
            new String[][] {
                {"color", "green"},
                {"language", "common"},
            }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

        @Override
        public String getName() {
            return TypeImpl.BITWISE_AND_ASSI;
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
            return TypeImpl.PROPERTIES.getOrDefault(name, "");
        }

        @Override
        public Builder createBuilder() {
            return new Constructor();
        }
    }

    /**
     * Class for 'BitwiseAndAssignment' node construction.
     *
     * @since 1.0
     */
    public static final class Constructor implements Builder {
        /**
         * The maximum number of nodes.
         */
        private static final int MAX_NODE_COUNT = 2;

        /**
         * The position of the 'left' field.
         */
        private static final int LEFT_POS = 0;

        /**
         * The position of the 'right' field.
         */
        private static final int RIGHT_POS = 1;

        /**
         * The fragment associated with the node.
         */
        private Fragment fragment = EmptyFragment.INSTANCE;

        /**
         * Node with the 'left' tag.
         */
        private AssignableExpression left;

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
         * @param node The node
         */
        public void setLeft(final AssignableExpression node) {
            this.left = node;
        }

        /**
         * Sets the node with the 'right' tag.
         * @param node The node
         */
        public void setRight(final Expression node) {
            this.right = node;
        }

        @Override
        public boolean setChildrenList(final List<Node> list) {
            final Node[] mapping = new Node[Constructor.MAX_NODE_COUNT];
            final ChildrenMapper mapper =
                new ChildrenMapper(BitwiseAndAssignment.TYPE.getChildTypes());
            final boolean result = mapper.map(mapping, list);
            if (result) {
                this.left = (AssignableExpression) mapping[Constructor.LEFT_POS];
                this.right = (Expression) mapping[Constructor.RIGHT_POS];
            }
            return result;
        }

        @Override
        public boolean isValid() {
            return this.left != null
                && this.right != null;
        }

        @Override
        public BitwiseAndAssignment createNode() {
            if (!this.isValid()) {
                throw new IllegalStateException();
            }
            final BitwiseAndAssignment node = new BitwiseAndAssignment();
            node.fragment = this.fragment;
            node.children = new ListUtils<Node>()
                .add(
                    this.left,
                    this.right
                )
                .make();
            node.left = this.left;
            node.right = this.right;
            return node;
        }
    }
}
