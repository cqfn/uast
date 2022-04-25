/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Draft node for wrapping the results of third-party parsers
 * in the {@link Node} interface.
 *
 * @since 1.0
 */
public final class DraftNode implements Node {
    /**
     * The fragment associated with the node.
     */
    private Fragment fragment;

    /**
     * The node type.
     */
    private Type type;

    /**
     * The node data.
     */
    private String data;

    /**
     * The list of children nodes.
     */
    private List<Node> children;

    /**
     * Private constructor.
     */
    private DraftNode() {
    }

    @Override
    public Fragment getFragment() {
        return this.fragment;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public String getData() {
        return this.data;
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
     * Type implementation for the draft node.
     *
     * @since 1.0
     */
    private static class TypeImpl implements Type {
        /**
         * The type name.
         */
        private final String name;

        /**
         * Constructor.
         * @param name The type name
         */
        TypeImpl(final String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public List<ChildDescriptor> getChildTypes() {
            return Collections.emptyList();
        }

        @Override
        public List<String> getHierarchy() {
            return Collections.singletonList(this.name);
        }

        @Override
        public String getProperty(final String property) {
            return "";
        }

        @Override
        public Builder createBuilder() {
            return new Constructor();
        }
    }

    /**
     * The constructor class for draft node.
     * @since 1.0
     */
    public static final class Constructor implements Builder {
        /**
         * The fragment associated with the node.
         */
        private Fragment fragment;

        /**
         * The type name.
         */
        private String name;

        /**
         * The node data.
         */
        private String data;

        /**
         * The list of children nodes.
         */
        private final List<Node> children;

        /**
         * Constructor.
         */
        public Constructor() {
            this.fragment = EmptyFragment.INSTANCE;
            this.name = "";
            this.data = "";
            this.children = new LinkedList<>();
        }

        @Override
        public void setFragment(final Fragment obj) {
            this.fragment = obj;
        }

        /**
         * Sets the type name.
         * @param str Type mane
         */
        public void setName(final String str) {
            this.name = str;
        }

        @Override
        public boolean setData(final String str) {
            this.data = Objects.requireNonNull(str);
            return true;
        }

        /**
         * Adds a child node.
         * @param node Node
         */
        public void addChild(final Node node) {
            this.children.add(Objects.requireNonNull(node));
        }

        @Override
        public boolean setChildrenList(final List<Node> list) {
            this.children.clear();
            this.children.addAll(list);
            return true;
        }

        @Override
        public boolean isValid() {
            return !this.name.isEmpty();
        }

        @Override
        public DraftNode createNode() {
            if (!this.isValid()) {
                throw new IllegalStateException();
            }
            final DraftNode node = new DraftNode();
            node.fragment = this.fragment;
            node.type = new TypeImpl(this.name);
            node.data = this.data;
            node.children = new ArrayList<>(this.children);
            return node;
        }
    }
}
