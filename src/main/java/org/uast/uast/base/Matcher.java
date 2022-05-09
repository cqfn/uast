/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.base;

import java.util.List;
import java.util.Map;

/**
 * Checks if the node matches some structure, and extracts the data or (and) children if so.
 *
 * @since 1.0
 */
public interface Matcher {
    /**
     * Matches the node.
     * @param node The node
     * @param children Where to save children when matched
     * @param data Where to save data when matched
     * @return The result of matching, {@code true} if node matches and data was extracted
     */
    boolean match(Node node, Map<Integer, List<Node>> children, Map<Integer, String> data);
}
