/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.js.rules;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.uast.uast.base.Matcher;
import org.uast.uast.base.Node;

/**
 * Checks if the node matches some structure, and extracts the data and children.
 *
 * @since 1.0
 */
public final class Matcher56 implements Matcher {
    /**
     * The instance.
     */
    public static final Matcher INSTANCE = new Matcher56();

    /**
     * Expected node type.
     */
    private static final String EXPECTED_TYPE = "singleExpression";

    /**
     * Expected number of child nodes.
     */
    private static final int EXPECTED_COUNT = 3;

    /**
     * The number of the first hole.
     */
    private static final int FIRST_HOLE_ID = 1;

    /**
     * The index of the first child.
     */
    private static final int FIRST_CHILD_ID = 0;

    /**
     * Constructor.
     */
    private Matcher56() {
    }

    @Override
    public boolean match(final Node node,
        final Map<Integer, List<Node>> children,
        final Map<Integer, String> data) {
        final boolean result = node.belongsToGroup(Matcher56.EXPECTED_TYPE)
            && node.getChildCount() == Matcher56.EXPECTED_COUNT
            && Matcher57.INSTANCE.match(node.getChild(1), children, data)
            && Matcher58.INSTANCE.match(node.getChild(2), children, data);
        if (result) {
            children.put(
                Matcher56.FIRST_HOLE_ID,
                Collections.singletonList(node.getChild(Matcher56.FIRST_CHILD_ID))
            );
        }
        return result;
    }
}
