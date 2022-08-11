/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.js.rules;

import java.util.List;
import java.util.Map;
import org.uast.uast.base.Matcher;
import org.uast.uast.base.Node;

/**
 * Checks if the node matches some structure, and extracts the data and children.
 *
 * @since 1.0
 */
public final class Matcher419 implements Matcher {
    /**
     * The instance.
     */
    public static final Matcher INSTANCE = new Matcher419();

    /**
     * Expected node type.
     */
    private static final String EXPECTED_TYPE = "formalParameterList";

    /**
     * The number of the first hole.
     */
    private static final int FIRST_HOLE_ID = 1;

    /**
     * Constructor.
     */
    private Matcher419() {
    }

    @Override
    public boolean match(final Node node,
        final Map<Integer, List<Node>> children,
        final Map<Integer, String> data) {
        final boolean result = node.belongsToGroup(Matcher419.EXPECTED_TYPE);
        if (result) {
            children.put(Matcher419.FIRST_HOLE_ID, node.getChildrenList());
        }
        return result;
    }
}
