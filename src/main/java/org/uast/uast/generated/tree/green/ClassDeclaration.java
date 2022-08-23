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
 * Node that describes the 'ClassDeclaration' type.
 *
 * @since 1.0
 */
public final class ClassDeclaration implements ProgramItem {
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
     * Child with the 'body' tag.
     */
    private ClassBody body;

    /**
     * Constructor.
     */
    private ClassDeclaration() {
    }

    @Override
    public Type getType() {
        return ClassDeclaration.TYPE;
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
     * Returns the child with the 'body' tag.
     * @return The node
     */
    public ClassBody getBody() {
        return this.body;
    }

    /**
     * Type descriptor of the 'ClassDeclaration' node.
     *
     * @since 1.0
     */
    private static class TypeImpl implements Type {
        /**
         * The 'ClassDeclaration' string.
         */
        private static final String CLASS_DECLARATIO = "ClassDeclaration";

        /**
         * The 'ModifierBlock' string.
         */
        private static final String MODIFIER_BLOCK = "ModifierBlock";

        /**
         * The 'Identifier' string.
         */
        private static final String IDENTIFIER = "Identifier";

        /**
         * The 'ExtendsBlock' string.
         */
        private static final String EXTENDS_BLOCK = "ExtendsBlock";

        /**
         * The 'ImplementsBlock' string.
         */
        private static final String IMPLEMENTS_BLOCK = "ImplementsBlock";

        /**
         * The 'ClassBody' string.
         */
        private static final String CLASS_BODY = "ClassBody";

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
                        TypeImpl.IDENTIFIER,
                        false
                    ),
                    new ChildDescriptor(
                        TypeImpl.EXTENDS_BLOCK,
                        true
                    ),
                    new ChildDescriptor(
                        TypeImpl.IMPLEMENTS_BLOCK,
                        true
                    ),
                    new ChildDescriptor(
                        TypeImpl.CLASS_BODY,
                        false
                    )
                )
            );

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
                    TypeImpl.CLASS_DECLARATIO,
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
            return TypeImpl.CLASS_DECLARATIO;
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
     * Class for 'ClassDeclaration' node construction.
     *
     * @since 1.0
     */
    public static final class Constructor implements Builder {
        /**
         * The maximum number of nodes.
         */
        private static final int MAX_NODE_COUNT = 5;

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
         * The position of the 'fourth' field.
         */
        private static final int FOURTH_POS = 3;

        /**
         * The position of the 'body' field.
         */
        private static final int BODY_POS = 4;

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
        private Identifier second;

        /**
         * Node 2.
         */
        private ExtendsBlock third;

        /**
         * Node 3.
         */
        private ImplementsBlock fourth;

        /**
         * Node with the 'body' tag.
         */
        private ClassBody body;

        @Override
        public void setFragment(final Fragment obj) {
            this.fragment = obj;
        }

        @Override
        public boolean setData(final String str) {
            return str.isEmpty();
        }

        /**
         * Sets the node with the 'body' tag.
         * @param node The node
         */
        public void setBody(final ClassBody node) {
            this.body = node;
        }

        @Override
        public boolean setChildrenList(final List<Node> list) {
            final Node[] mapping = new Node[Constructor.MAX_NODE_COUNT];
            final ChildrenMapper mapper =
                new ChildrenMapper(ClassDeclaration.TYPE.getChildTypes());
            final boolean result = mapper.map(mapping, list);
            if (result) {
                this.first = (ModifierBlock) mapping[Constructor.FIRST_POS];
                this.second = (Identifier) mapping[Constructor.SECOND_POS];
                this.third = (ExtendsBlock) mapping[Constructor.THIRD_POS];
                this.fourth = (ImplementsBlock) mapping[Constructor.FOURTH_POS];
                this.body = (ClassBody) mapping[Constructor.BODY_POS];
            }
            return result;
        }

        @Override
        public boolean isValid() {
            return this.second != null
                && this.body != null;
        }

        @Override
        public ClassDeclaration createNode() {
            if (!this.isValid()) {
                throw new IllegalStateException();
            }
            final ClassDeclaration node = new ClassDeclaration();
            node.fragment = this.fragment;
            node.children = new ListUtils<Node>()
                .add(
                    this.first,
                    this.second,
                    this.third,
                    this.fourth,
                    this.body
                )
                .make();
            node.body = this.body;
            return node;
        }
    }
}
