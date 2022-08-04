/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.python.rules;

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
public final class Matcher92 implements Matcher {
    /**
     * The instance.
     */
    public static final Matcher INSTANCE = new Matcher92();

    /**
     * Expected node type.
     */
    private static final String EXPECTED_TYPE = "logical_test";

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
    private Matcher92() {
    }

    @Override
    public boolean match(final Node node,
        final Map<Integer, List<Node>> children,
        final Map<Integer, String> data) {
        final boolean result = node.belongsToGroup(Matcher92.EXPECTED_TYPE)
            && node.getChildCount() == Matcher92.EXPECTED_COUNT
            && Matcher93.INSTANCE.match(node.getChild(1), children, data);
        if (result) {
            children.put(
                Matcher92.FIRST_HOLE_ID,
                Collections.singletonList(node.getChild(Matcher92.FIRST_CHILD_ID))
            );
            children.put(
                Matcher92.SECOND_HOLE_ID,
                Collections.singletonList(node.getChild(Matcher92.SECOND_CHILD_ID))
            );
        }
        return result;
    }
}
