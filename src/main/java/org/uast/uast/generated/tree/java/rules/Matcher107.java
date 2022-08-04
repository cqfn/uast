/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.java.rules;

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
public final class Matcher107 implements Matcher {
    /**
     * The instance.
     */
    public static final Matcher INSTANCE = new Matcher107();

    /**
     * Expected node type.
     */
    private static final String EXPECTED_TYPE = "BinaryExpr";

    /**
     * Expected number of child nodes.
     */
    private static final int EXPECTED_COUNT = 2;

    /**
     * Expected data.
     */
    private static final String EXPECTED_DATA = ">>";

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
    private Matcher107() {
    }

    @Override
    public boolean match(final Node node,
        final Map<Integer, List<Node>> children,
        final Map<Integer, String> data) {
        final boolean result = node.belongsToGroup(Matcher107.EXPECTED_TYPE)
            && node.getChildCount() == Matcher107.EXPECTED_COUNT
            && Matcher107.EXPECTED_DATA.equals(node.getData())
            && Matcher108.INSTANCE.match(node.getChild(1), children, data);
        if (result) {
            children.put(
                Matcher107.FIRST_HOLE_ID,
                Collections.singletonList(node.getChild(Matcher107.FIRST_CHILD_ID))
            );
        }
        return result;
    }
}
