/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.java;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.uast.uast.base.Builder;
import org.uast.uast.base.ChildDescriptor;
import org.uast.uast.base.EmptyFragment;
import org.uast.uast.base.Fragment;
import org.uast.uast.base.Node;
import org.uast.uast.base.Type;

/**
 * Node that describes the 'This' type.
 *
 * @since 1.0
 */
public final class This implements Node {
    /**
     * The type.
     */
    public static final Type TYPE = new TypeImpl();

    /**
     * The fragment associated with the node.
     */
    private Fragment fragment;

    /**
     * Constructor.
     */
    private This() {
    }

    @Override
    public Type getType() {
        return This.TYPE;
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
        return 0;
    }

    @Override
    public Node getChild(final int index) {
        throw new IndexOutOfBoundsException();
    }

    /**
     * Type descriptor of the 'This' node.
     *
     * @since 1.0
     */
    private static class TypeImpl implements Type {
        /**
         * The 'This' string.
         */
        private static final String THIS = "This";

        /**
         * Hierarchy.
         */
        private static final List<String> HIERARCHY =
            Collections.unmodifiableList(
                Arrays.asList(
                    TypeImpl.THIS
                )
            );

        /**
         * Properties.
         */
        private static final Map<String, String> PROPERTIES = Stream.of(
            new String[][] {
                {"color", "red"},
                {"language", "java"},
            }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

        @Override
        public String getName() {
            return TypeImpl.THIS;
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
        public String getProperty(final String name) {
            return TypeImpl.PROPERTIES.getOrDefault(name, "");
        }

        @Override
        public Builder createBuilder() {
            return new Constructor();
        }
    }

    /**
     * Class for 'This' node construction.
     *
     * @since 1.0
     */
    public static final class Constructor implements Builder {
        /**
         * The fragment associated with the node.
         */
        private Fragment fragment = EmptyFragment.INSTANCE;

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
            return list.isEmpty();
        }

        @Override
        public boolean isValid() {
            return true;
        }

        @Override
        public This createNode() {
            final This node = new This();
            node.fragment = this.fragment;
            return node;
        }
    }
}
