/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Mutable node whose children can be replaced during syntactic tree customization.
 *
 * @since 1.0
 */
public final class ConvertibleNode implements Node {
    /**
     * The parent convertible node.
     */
    private final ConvertibleNode parent;

    /**
     * The prototype node.
     */
    private final Node prototype;

    /**
     * The list of children.
      */
    private final List<Node> children;

    /**
     * Constructor.
     * @param parent The parent convertible node.
     * @param prototype The prototype node.
     */
    private ConvertibleNode(final ConvertibleNode parent, final Node prototype) {
        this.parent = parent;
        this.prototype = prototype;
        this.children = this.initChildrenList();
    }

    /**
     * Constructor.
     * @param prototype The prototype node.
     */
    public ConvertibleNode(final Node prototype) {
        this(null, prototype);
    }

    /**
     * Returns the parent node.
     * @return The parent node
     */
    public ConvertibleNode getParent() {
        return this.parent;
    }

    @Override
    public Fragment getFragment() {
        return this.prototype.getFragment();
    }

    @Override
    public Type getType() {
        return this.prototype.getType();
    }

    @Override
    public String getData() {
        return this.prototype.getData();
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
     * Returns a child, transformed to {@link ConvertibleNode}, by its index.
     * @param index Child index
     * @return Convertible node
     */
    public ConvertibleNode getConvertibleChild(final int index) {
        final Node node = this.children.get(index);
        final ConvertibleNode result;
        if (node instanceof ConvertibleNode) {
            result = (ConvertibleNode) node;
        } else {
            result = new ConvertibleNode(this, node);
            this.children.set(index, result);
        }
        return result;
    }

    @Override
    public List<Node> getChildrenList() {
        return this.children;
    }

    /**
     * Replaces a child node with another node.
     * @param child Child node
     * @param substitute Substitute node
     * @return Result of operation, {@code true} if replacement was successful
     */
    public boolean replaceChild(final Node child, final Node substitute) {
        boolean result = false;
        final int index = this.children.indexOf(child);
        if (index >= 0) {
            this.children.set(index, substitute);
            result = true;
        }
        return result;
    }

    /**
     * Transforms children nodes to convertible ones.
     * @return Array of convertible nodes
     */
    private List<Node> initChildrenList() {
        final int count = this.prototype.getChildCount();
        final List<Node> result = new ArrayList<>(count);
        for (int index = 0; index < count; index = index + 1) {
            result.add(new ConvertibleNode(this, this.prototype.getChild(index)));
        }
        return result;
    }
}
