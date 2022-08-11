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
public final class Matcher301 implements Matcher {
    /**
     * The instance.
     */
    public static final Matcher INSTANCE = new Matcher301();

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
     * The number of the second hole.
     */
    private static final int SECOND_HOLE_ID = 2;

    /**
     * The index of the second child.
     */
    private static final int SECOND_CHILD_ID = 2;

    /**
     * Constructor.
     */
    private Matcher301() {
    }

    @Override
    public boolean match(final Node node,
        final Map<Integer, List<Node>> children,
        final Map<Integer, String> data) {
        final boolean result = node.belongsToGroup(Matcher301.EXPECTED_TYPE)
            && node.getChildCount() == Matcher301.EXPECTED_COUNT
            && Matcher302.INSTANCE.match(node.getChild(1), children, data);
        if (result) {
            children.put(
                Matcher301.FIRST_HOLE_ID,
                Collections.singletonList(node.getChild(Matcher301.FIRST_CHILD_ID))
            );
            children.put(
                Matcher301.SECOND_HOLE_ID,
                Collections.singletonList(node.getChild(Matcher301.SECOND_CHILD_ID))
            );
        }
        return result;
    }
}
