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
 * Node that describes the 'FunctionDeclaration' type.
 *
 * @since 1.0
 */
@SuppressWarnings("PMD.DataClass")
public final class FunctionDeclaration implements ClassItem {
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
     * Child with the 'modifiers' tag.
     */
    private ModifierBlock modifiers;

    /**
     * Child with the 'typename' tag.
     */
    private TypeName typename;

    /**
     * Child with the 'name' tag.
     */
    private Identifier name;

    /**
     * Child with the 'parameters' tag.
     */
    private ParameterBlock parameters;

    /**
     * Child with the 'body' tag.
     */
    private StatementBlock body;

    /**
     * Constructor.
     */
    private FunctionDeclaration() {
    }

    @Override
    public Type getType() {
        return FunctionDeclaration.TYPE;
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
     * Returns the child with the 'modifiers' tag.
     * @return The node
     */
    public ModifierBlock getModifiers() {
        return this.modifiers;
    }

    /**
     * Returns the child with the 'typename' tag.
     * @return The node
     */
    public TypeName getTypename() {
        return this.typename;
    }

    /**
     * Returns the child with the 'name' tag.
     * @return The node
     */
    public Identifier getName() {
        return this.name;
    }

    /**
     * Returns the child with the 'parameters' tag.
     * @return The node
     */
    public ParameterBlock getParameters() {
        return this.parameters;
    }

    /**
     * Returns the child with the 'body' tag.
     * @return The node
     */
    public StatementBlock getBody() {
        return this.body;
    }

    /**
     * Type descriptor of the 'FunctionDeclaration' node.
     *
     * @since 1.0
     */
    private static class TypeImpl implements Type {
        /**
         * The 'FunctionDeclaration' string.
         */
        private static final String FUNCTION_DECLARA = "FunctionDeclaration";

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
         * The 'ParameterBlock' string.
         */
        private static final String PARAMETER_BLOCK = "ParameterBlock";

        /**
         * The 'StatementBlock' string.
         */
        private static final String STATEMENT_BLOCK = "StatementBlock";

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
                    ),
                    new ChildDescriptor(
                        TypeImpl.PARAMETER_BLOCK,
                        false
                    ),
                    new ChildDescriptor(
                        TypeImpl.STATEMENT_BLOCK,
                        false
                    )
                )
            );

        /**
         * The 'ClassItem' string.
         */
        private static final String CLASS_ITEM = "ClassItem";

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
                    TypeImpl.FUNCTION_DECLARA,
                    TypeImpl.CLASS_ITEM,
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
            return TypeImpl.FUNCTION_DECLARA;
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
     * Class for 'FunctionDeclaration' node construction.
     *
     * @since 1.0
     */
    public static final class Constructor implements Builder {
        /**
         * The maximum number of nodes.
         */
        private static final int MAX_NODE_COUNT = 5;

        /**
         * The position of the 'modifiers' field.
         */
        private static final int MODIFIERS_POS = 0;

        /**
         * The position of the 'typename' field.
         */
        private static final int TYPENAME_POS = 1;

        /**
         * The position of the 'name' field.
         */
        private static final int NAME_POS = 2;

        /**
         * The position of the 'parameters' field.
         */
        private static final int PARAMETERS_POS = 3;

        /**
         * The position of the 'body' field.
         */
        private static final int BODY_POS = 4;

        /**
         * The fragment associated with the node.
         */
        private Fragment fragment = EmptyFragment.INSTANCE;

        /**
         * Node with the 'modifiers' tag.
         */
        private ModifierBlock modifiers;

        /**
         * Node with the 'typename' tag.
         */
        private TypeName typename;

        /**
         * Node with the 'name' tag.
         */
        private Identifier name;

        /**
         * Node with the 'parameters' tag.
         */
        private ParameterBlock parameters;

        /**
         * Node with the 'body' tag.
         */
        private StatementBlock body;

        @Override
        public void setFragment(final Fragment obj) {
            this.fragment = obj;
        }

        @Override
        public boolean setData(final String str) {
            return str.isEmpty();
        }

        /**
         * Sets the node with the 'modifiers' tag.
         * @param node The node
         */
        public void setModifiers(final ModifierBlock node) {
            this.modifiers = node;
        }

        /**
         * Sets the node with the 'typename' tag.
         * @param node The node
         */
        public void setTypename(final TypeName node) {
            this.typename = node;
        }

        /**
         * Sets the node with the 'name' tag.
         * @param node The node
         */
        public void setName(final Identifier node) {
            this.name = node;
        }

        /**
         * Sets the node with the 'parameters' tag.
         * @param node The node
         */
        public void setParameters(final ParameterBlock node) {
            this.parameters = node;
        }

        /**
         * Sets the node with the 'body' tag.
         * @param node The node
         */
        public void setBody(final StatementBlock node) {
            this.body = node;
        }

        @Override
        public boolean setChildrenList(final List<Node> list) {
            final Node[] mapping = new Node[Constructor.MAX_NODE_COUNT];
            final ChildrenMapper mapper =
                new ChildrenMapper(FunctionDeclaration.TYPE.getChildTypes());
            final boolean result = mapper.map(mapping, list);
            if (result) {
                this.modifiers = (ModifierBlock) mapping[Constructor.MODIFIERS_POS];
                this.typename = (TypeName) mapping[Constructor.TYPENAME_POS];
                this.name = (Identifier) mapping[Constructor.NAME_POS];
                this.parameters = (ParameterBlock) mapping[Constructor.PARAMETERS_POS];
                this.body = (StatementBlock) mapping[Constructor.BODY_POS];
            }
            return result;
        }

        @Override
        public boolean isValid() {
            return this.name != null
                && this.parameters != null
                && this.body != null;
        }

        @Override
        public FunctionDeclaration createNode() {
            if (!this.isValid()) {
                throw new IllegalStateException();
            }
            final FunctionDeclaration node = new FunctionDeclaration();
            node.fragment = this.fragment;
            node.children = new ListUtils<Node>()
                .add(
                    this.modifiers,
                    this.typename,
                    this.name,
                    this.parameters,
                    this.body
                )
                .make();
            node.modifiers = this.modifiers;
            node.typename = this.typename;
            node.name = this.name;
            node.parameters = this.parameters;
            node.body = this.body;
            return node;
        }
    }
}
