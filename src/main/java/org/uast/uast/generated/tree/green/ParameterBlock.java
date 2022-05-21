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
 * Node that describes the 'ParameterBlock' type.
 *
 * @since 1.0
 */
public final class ParameterBlock implements Node {
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
    private List<Parameter> children;

    /**
     * Constructor.
     */
    private ParameterBlock() {
    }

    @Override
    public Type getType() {
        return ParameterBlock.TYPE;
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
     * Return a child node with 'Parameter' type by its index.
     * @param index Child index
     * @return A node
     */
    public Parameter getParameter(final int index) {
        return this.children.get(index);
    }

    @Override
    public Node getChild(final int index) {
        return this.children.get(index);
    }

    /**
     * Type descriptor of the 'ParameterBlock' node.
     *
     * @since 1.0
     */
    private static class TypeImpl implements Type {
        /**
         * The 'ParameterBlock' string.
         */
        private static final String PARAMETER_BLOCK = "ParameterBlock";

        /**
         * The 'Parameter' string.
         */
        private static final String PARAMETER = "Parameter";

        /**
         * The list of child types.
         */
        private static final List<ChildDescriptor> CHILDREN =
            Collections.singletonList(
                new ChildDescriptor(
                    TypeImpl.PARAMETER,
                    false
                )
            );

        /**
         * Hierarchy.
         */
        private static final List<String> HIERARCHY =
            Collections.unmodifiableList(
                Arrays.asList(
                    TypeImpl.PARAMETER_BLOCK
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
            return TypeImpl.PARAMETER_BLOCK;
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
     * Class for 'ParameterBlock' node construction.
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
        private List<Parameter> children = Collections.emptyList();

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
            final List<Parameter> clarified = new ArrayList<>(list.size());
            for (final Node node : list) {
                if (node instanceof Parameter) {
                    clarified.add((Parameter) node);
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
        public ParameterBlock createNode() {
            final ParameterBlock node = new ParameterBlock();
            node.fragment = this.fragment;
            node.children = this.children;
            return node;
        }
    }
}
