/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.base;

import java.util.List;

/**
 * A type of abstract syntax tree node.
 *
 * @since 1.0
 */
public interface Type {
    /**
     * Returns the type name.
     * @return The type name
     */
    String getName();

    /**
     * Returns the list of child types.
     * @return The list of descriptors
     */
    List<ChildDescriptor> getChildTypes();

    /**
     * The hierarchy of names of groups the node type belongs to.
     * @return The list of type names
     */
    List<String> getHierarchy();

    /**
     * Returns the value of some property (depends on implementation).
     * @param name The name of property
     * @return Property value (if the property is not defined, returns an empty string)
     */
    String getProperty(String name);

    /**
     * Creates a new builder who builds a node of this type.
     * @return A builder.
     */
    Builder createBuilder();

    /**
     * Checks whether the type belongs to group.
     * @param type The type name
     * @return Checking result, {@code true} if the type belongs to the group
     */
    default boolean belongsToGroup(final String type) {
        return this.getHierarchy().contains(type);
    }
}
