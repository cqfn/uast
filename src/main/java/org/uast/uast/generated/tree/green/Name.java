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
 * Node that describes the 'Name' type.
 *
 * @since 1.0
 */
public final class Name implements Node {
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
     * Child with the 'composition' tag.
     */
    private Name composition;

    /**
     * Child with the 'last' tag.
     */
    private Identifier last;

    /**
     * Constructor.
     */
    private Name() {
    }

    @Override
    public Type getType() {
        return Name.TYPE;
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
     * Returns the child with the 'composition' tag.
     * @return The node
     */
    public Name getComposition() {
        return this.composition;
    }

    /**
     * Returns the child with the 'last' tag.
     * @return The node
     */
    public Identifier getLast() {
        return this.last;
    }

    /**
     * Type descriptor of the 'Name' node.
     *
     * @since 1.0
     */
    private static class TypeImpl implements Type {
        /**
         * The 'Name' string.
         */
        private static final String NAME = "Name";

        /**
         * The 'Identifier' string.
         */
        private static final String IDENTIFIER = "Identifier";

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
                    )
                )
            );

        /**
         * Hierarchy.
         */
        private static final List<String> HIERARCHY =
            Collections.unmodifiableList(
                Arrays.asList(
                    TypeImpl.NAME
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
            return TypeImpl.PROPERTIES.getOrDefault(name, "");
        }

        @Override
        public Builder createBuilder() {
            return new Constructor();
        }
    }

    /**
     * Class for 'Name' node construction.
     *
     * @since 1.0
     */
    public static final class Constructor implements Builder {
        /**
         * The maximum number of nodes.
         */
        private static final int MAX_NODE_COUNT = 2;

        /**
         * The position of the 'composition' field.
         */
        private static final int COMPOSITION_POS = 0;

        /**
         * The position of the 'last' field.
         */
        private static final int LAST_POS = 1;

        /**
         * The fragment associated with the node.
         */
        private Fragment fragment = EmptyFragment.INSTANCE;

        /**
         * Node with the 'composition' tag.
         */
        private Name composition;

        /**
         * Node with the 'last' tag.
         */
        private Identifier last;

        @Override
        public void setFragment(final Fragment obj) {
            this.fragment = obj;
        }

        @Override
        public boolean setData(final String str) {
            return str.isEmpty();
        }

        /**
         * Sets the node with the 'composition' tag.
         * @param node The node
         */
        public void setComposition(final Name node) {
            this.composition = node;
        }

        /**
         * Sets the node with the 'last' tag.
         * @param node The node
         */
        public void setLast(final Identifier node) {
            this.last = node;
        }

        @Override
        public boolean setChildrenList(final List<Node> list) {
            final Node[] mapping = new Node[Constructor.MAX_NODE_COUNT];
            final ChildrenMapper mapper =
                new ChildrenMapper(Name.TYPE.getChildTypes());
            final boolean result = mapper.map(mapping, list);
            if (result) {
                this.composition = (Name) mapping[Constructor.COMPOSITION_POS];
                this.last = (Identifier) mapping[Constructor.LAST_POS];
            }
            return result;
        }

        @Override
        public boolean isValid() {
            return this.last != null;
        }

        @Override
        public Name createNode() {
            if (!this.isValid()) {
                throw new IllegalStateException();
            }
            final Name node = new Name();
            node.fragment = this.fragment;
            node.children = new ListUtils<Node>()
                .add(
                    this.composition,
                    this.last
                )
                .make();
            node.composition = this.composition;
            node.last = this.last;
            return node;
        }
    }
}
