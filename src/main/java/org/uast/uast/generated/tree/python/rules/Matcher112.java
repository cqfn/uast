/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.python.rules;

import java.util.List;
import java.util.Map;
import org.uast.uast.base.Matcher;
import org.uast.uast.base.Node;

/**
 * Checks if the node matches some structure, and extracts the data and children.
 *
 * @since 1.0
 */
public final class Matcher112 implements Matcher {
    /**
     * The instance.
     */
    public static final Matcher INSTANCE = new Matcher112();

    /**
     * Expected node type.
     */
    private static final String EXPECTED_TYPE = "atom";

    /**
     * Expected number of child nodes.
     */
    private static final int EXPECTED_COUNT = 1;

    /**
     * Constructor.
     */
    private Matcher112() {
    }

    @Override
    public boolean match(final Node node,
        final Map<Integer, List<Node>> children,
        final Map<Integer, String> data) {
        return node.belongsToGroup(Matcher112.EXPECTED_TYPE)
            && node.getChildCount() == Matcher112.EXPECTED_COUNT
            && Matcher113.INSTANCE.match(node.getChild(0), children, data);
    }
}
