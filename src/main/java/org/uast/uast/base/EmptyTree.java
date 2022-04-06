/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.base;

import java.util.Collections;
import java.util.List;

/**
 * The empty syntax tree.
 *
 * @since 1.0
 */
public final class EmptyTree implements Node {
    /**
     * The type.
     */
    public static final Type TYPE = new TypeImpl();

    /**
     * The instance.
     */
    public static final Node INSTANCE = new EmptyTree();

    /**
     * The builder.
     */
    public static final Builder BUILDER = new BuilderImpl();

    /**
     * Private constructor.
     */
    private EmptyTree() {
    }

    @Override
    public Fragment getFragment() {
        return EmptyFragment.INSTANCE;
    }

    @Override
    public Type getType() {
        return EmptyTree.TYPE;
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
     * The fake builder that only returns static reference.
     *
     * @since 1.0
     */
    private static final class BuilderImpl implements Builder {
        @SuppressWarnings("PMD.UncommentedEmptyMethodBody")
        @Override
        public void setFragment(final Fragment fragment) {
        }

        @Override
        public boolean setData(final String str) {
            return false;
        }

        @Override
        public boolean setChildrenList(final List<Node> list) {
            return false;
        }

        @Override
        public boolean isValid() {
            return false;
        }

        @Override
        public Node create() {
            return EmptyTree.INSTANCE;
        }
    }

    /**
     * The type of empty tree.
     *
     * @since 1.0
     */
    private static class TypeImpl implements Type {
        @Override
        public String getName() {
            return "<null>";
        }

        @Override
        public List<ChildDescriptor> getChildTypes() {
            return Collections.emptyList();
        }

        @Override
        public List<String> getHierarchy() {
            return Collections.emptyList();
        }

        @Override
        public Builder createBuilder() {
            return EmptyTree.BUILDER;
        }
    }
}
