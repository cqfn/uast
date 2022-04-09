/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.base;

/**
 * Interface for converters that checks one rule described in DSL
 * and convert the specified AST built by a third-party parser to the unified format.
 *
 * @since 1.0
 */
public interface Converter {
    /**
     * Converts an AST built by a third-party parser to the unified format.
     *
     * @param node The root of the AST to be converted
     * @param factory The node factory
     * @return A new [unified] node
     */
    Node convert(Node node, Factory factory);
}
