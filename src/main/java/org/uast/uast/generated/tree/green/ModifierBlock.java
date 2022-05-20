/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.green;

import java.util.ArrayList;
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
 * Node that describes the 'ModifierBlock' type.
 *
 * @since 1.0
 */
public final class ModifierBlock implements Node {
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
    private List<Modifier> children;

    /**
     * Constructor.
     */
    private ModifierBlock() {
    }

    @Override
    public Type getType() {
        return ModifierBlock.TYPE;
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

    /**
     * Return a child node with 'Modifier' type by its index.
     * @param index Child index
     * @return A node
     */
    public Modifier getModifier(final int index) {
        return this.children.get(index);
    }

    @Override
    public Node getChild(final int index) {
        return this.children.get(index);
    }

    /**
     * Type descriptor of the 'ModifierBlock' node.
     *
     * @since 1.0
     */
    private static class TypeImpl implements Type {
        /**
         * The 'ModifierBlock' string.
         */
        private static final String MODIFIER_BLOCK = "ModifierBlock";

        /**
         * The 'Modifier' string.
         */
        private static final String MODIFIER = "Modifier";

        /**
         * The list of child types.
         */
        private static final List<ChildDescriptor> CHILDREN =
            Collections.singletonList(
                new ChildDescriptor(
                    TypeImpl.MODIFIER,
                    false
                )
            );

        /**
         * Hierarchy.
         */
        private static final List<String> HIERARCHY =
            Collections.unmodifiableList(
                Arrays.asList(
                    TypeImpl.MODIFIER_BLOCK
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
            return TypeImpl.MODIFIER_BLOCK;
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
     * Class for 'ModifierBlock' node construction.
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
        private List<Modifier> children = Collections.emptyList();

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
            final List<Modifier> clarified = new ArrayList<>(list.size());
            for (final Node node : list) {
                if (node instanceof Modifier) {
                    clarified.add((Modifier) node);
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
        public ModifierBlock createNode() {
            final ModifierBlock node = new ModifierBlock();
            node.fragment = this.fragment;
            node.children = this.children;
            return node;
        }
    }
}
