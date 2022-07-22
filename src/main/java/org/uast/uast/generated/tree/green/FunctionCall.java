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
 * Node that describes the 'FunctionCall' type.
 *
 * @since 1.0
 */
@SuppressWarnings("PMD.DataClass")
public final class FunctionCall implements Statement {
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
     * Child with the 'owner' tag.
     */
    private Name owner;

    /**
     * Child with the 'name' tag.
     */
    private Identifier name;

    /**
     * Child with the 'arguments' tag.
     */
    private ExpressionList arguments;

    /**
     * Constructor.
     */
    private FunctionCall() {
    }

    @Override
    public Type getType() {
        return FunctionCall.TYPE;
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
        return this.children.size();
    }

    @Override
    public Node getChild(final int index) {
        return this.children.get(index);
    }

    /**
     * Returns the child with the 'owner' tag.
     * @return The node
     */
    public Name getOwner() {
        return this.owner;
    }

    /**
     * Returns the child with the 'name' tag.
     * @return The node
     */
    public Identifier getName() {
        return this.name;
    }

    /**
     * Returns the child with the 'arguments' tag.
     * @return The node
     */
    public ExpressionList getArguments() {
        return this.arguments;
    }

    /**
     * Type descriptor of the 'FunctionCall' node.
     *
     * @since 1.0
     */
    private static class TypeImpl implements Type {
        /**
         * The 'FunctionCall' string.
         */
        private static final String FUNCTION_CALL = "FunctionCall";

        /**
         * The 'Name' string.
         */
        private static final String NAME = "Name";

        /**
         * The 'Identifier' string.
         */
        private static final String IDENTIFIER = "Identifier";

        /**
         * The 'ExpressionList' string.
         */
        private static final String EXPRESSION_LIST = "ExpressionList";

        /**
         * The list of child types.
         */
        private static final List<ChildDescriptor> CHILDREN =
            Collections.unmodifiableList(
                Arrays.asList(
                    new ChildDescriptor(
                        TypeImpl.NAME,
                        true
                    ),
                    new ChildDescriptor(
                        TypeImpl.IDENTIFIER,
                        false
                    ),
                    new ChildDescriptor(
                        TypeImpl.EXPRESSION_LIST,
                        false
                    )
                )
            );

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
                    TypeImpl.FUNCTION_CALL,
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
            return TypeImpl.FUNCTION_CALL;
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
     * Class for 'FunctionCall' node construction.
     *
     * @since 1.0
     */
    public static final class Constructor implements Builder {
        /**
         * The maximum number of nodes.
         */
        private static final int MAX_NODE_COUNT = 3;

        /**
         * The position of the 'owner' field.
         */
        private static final int OWNER_POS = 0;

        /**
         * The position of the 'name' field.
         */
        private static final int NAME_POS = 1;

        /**
         * The position of the 'arguments' field.
         */
        private static final int ARGUMENTS_POS = 2;

        /**
         * The fragment associated with the node.
         */
        private Fragment fragment = EmptyFragment.INSTANCE;

        /**
         * Node with the 'owner' tag.
         */
        private Name owner;

        /**
         * Node with the 'name' tag.
         */
        private Identifier name;

        /**
         * Node with the 'arguments' tag.
         */
        private ExpressionList arguments;

        @Override
        public void setFragment(final Fragment obj) {
            this.fragment = obj;
        }

        @Override
        public boolean setData(final String str) {
            return str.isEmpty();
        }

        /**
         * Sets the node with the 'owner' tag.
         * @param node The node
         */
        public void setOwner(final Name node) {
            this.owner = node;
        }

        /**
         * Sets the node with the 'name' tag.
         * @param node The node
         */
        public void setName(final Identifier node) {
            this.name = node;
        }

        /**
         * Sets the node with the 'arguments' tag.
         * @param node The node
         */
        public void setArguments(final ExpressionList node) {
            this.arguments = node;
        }

        @Override
        public boolean setChildrenList(final List<Node> list) {
            final Node[] mapping = new Node[Constructor.MAX_NODE_COUNT];
            final ChildrenMapper mapper =
                new ChildrenMapper(FunctionCall.TYPE.getChildTypes());
            final boolean result = mapper.map(mapping, list);
            if (result) {
                this.owner = (Name) mapping[Constructor.OWNER_POS];
                this.name = (Identifier) mapping[Constructor.NAME_POS];
                this.arguments = (ExpressionList) mapping[Constructor.ARGUMENTS_POS];
            }
            return result;
        }

        @Override
        public boolean isValid() {
            return this.name != null
                && this.arguments != null;
        }

        @Override
        public FunctionCall createNode() {
            if (!this.isValid()) {
                throw new IllegalStateException();
            }
            final FunctionCall node = new FunctionCall();
            node.fragment = this.fragment;
            node.children = new ListUtils<Node>()
                .add(
                    this.owner,
                    this.name,
                    this.arguments
                )
                .make();
            node.owner = this.owner;
            node.name = this.name;
            node.arguments = this.arguments;
            return node;
        }
    }
}
