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
 * Node that describes the 'Parameter' type.
 *
 * @since 1.0
 */
public final class Parameter implements Node {
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
     * Constructor.
     */
    private Parameter() {
    }

    @Override
    public Type getType() {
        return Parameter.TYPE;
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
     * Type descriptor of the 'Parameter' node.
     *
     * @since 1.0
     */
    private static class TypeImpl implements Type {
        /**
         * The 'Parameter' string.
         */
        private static final String PARAMETER = "Parameter";

        /**
         * The 'ModifierBlock' string.
         */
        private static final String MODIFIER_BLOCK = "ModifierBlock";

        /**
         * The 'TypeName' string.
         */
        private static final String TYPE_NAME = "TypeName";

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
                        TypeImpl.MODIFIER_BLOCK,
                        true
                    ),
                    new ChildDescriptor(
                        TypeImpl.TYPE_NAME,
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
                    TypeImpl.PARAMETER
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
            return TypeImpl.PARAMETER;
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
     * Class for 'Parameter' node construction.
     *
     * @since 1.0
     */
    public static final class Constructor implements Builder {
        /**
         * The maximum number of nodes.
         */
        private static final int MAX_NODE_COUNT = 3;

        /**
         * The position of the 'first' field.
         */
        private static final int FIRST_POS = 0;

        /**
         * The position of the 'second' field.
         */
        private static final int SECOND_POS = 1;

        /**
         * The position of the 'third' field.
         */
        private static final int THIRD_POS = 2;

        /**
         * The fragment associated with the node.
         */
        private Fragment fragment = EmptyFragment.INSTANCE;

        /**
         * Node 0.
         */
        private ModifierBlock first;

        /**
         * Node 1.
         */
        private TypeName second;

        /**
         * Node 2.
         */
        private Identifier third;

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
            final Node[] mapping = new Node[Constructor.MAX_NODE_COUNT];
            final ChildrenMapper mapper =
                new ChildrenMapper(Parameter.TYPE.getChildTypes());
            final boolean result = mapper.map(mapping, list);
            if (result) {
                this.first = (ModifierBlock) mapping[Constructor.FIRST_POS];
                this.second = (TypeName) mapping[Constructor.SECOND_POS];
                this.third = (Identifier) mapping[Constructor.THIRD_POS];
            }
            return result;
        }

        @Override
        public boolean isValid() {
            return this.third != null;
        }

        @Override
        public Parameter createNode() {
            if (!this.isValid()) {
                throw new IllegalStateException();
            }
            final Parameter node = new Parameter();
            node.fragment = this.fragment;
            node.children = new ListUtils<Node>()
                .add(
                    this.first,
                    this.second,
                    this.third
                )
                .make();
            return node;
        }
    }
}
