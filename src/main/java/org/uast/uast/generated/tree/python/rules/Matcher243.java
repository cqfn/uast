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
public final class Matcher243 implements Matcher {
    /**
     * The instance.
     */
    public static final Matcher INSTANCE = new Matcher243();

    /**
     * Expected node type.
     */
    private static final String EXPECTED_TYPE = "classdef";

    /**
     * Expected number of child nodes.
     */
    private static final int EXPECTED_COUNT = 4;

    /**
     * The index of the first child.
     */
    private static final int FIRST_CHILD_ID = 0;

    /**
     * The index of the second child.
     */
    private static final int SECOND_CHILD_ID = 1;

    /**
     * The index of the third child.
     */
    private static final int THIRD_CHILD_ID = 2;

    /**
     * The index of the fourth child.
     */
    private static final int FOURTH_CHILD_ID = 3;

    /**
     * Constructor.
     */
    private Matcher243() {
    }

    @Override
    public boolean match(final Node node,
        final Map<Integer, List<Node>> children,
        final Map<Integer, String> data) {
        return node.belongsToGroup(Matcher243.EXPECTED_TYPE)
            && node.getChildCount() == Matcher243.EXPECTED_COUNT
            && Matcher243.matchChildren(node, children, data);
    }

    /**
     * Checks if the children matches some structure, and extracts the data and children if so.
     * @param node The node
     * @param children Where to save children when matched
     * @param data Where to save data when matched
     * @return The result of matching, {@code true} if node matches and data was extracted
     */
    private static boolean matchChildren(final Node node,
        final Map<Integer, List<Node>> children,
        final Map<Integer, String> data) {
        boolean flag = Matcher244.INSTANCE.match(
            node.getChild(Matcher243.FIRST_CHILD_ID), children, data
        );
        flag = flag && Matcher245.INSTANCE.match(
            node.getChild(Matcher243.SECOND_CHILD_ID), children, data
        );
        flag = flag && Matcher247.INSTANCE.match(
            node.getChild(Matcher243.THIRD_CHILD_ID), children, data
        );
        flag = flag && Matcher249.INSTANCE.match(
            node.getChild(Matcher243.FOURTH_CHILD_ID), children, data
        );
        return flag;
    }
}
