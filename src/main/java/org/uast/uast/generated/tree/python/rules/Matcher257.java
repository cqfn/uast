/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.python.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.uast.uast.base.Matcher;
import org.uast.uast.base.Node;

/**
 * Checks if the node matches some structure, and extracts the data and children.
 *
 * @since 1.0
 */
public final class Matcher257 implements Matcher {
    /**
     * The instance.
     */
    public static final Matcher INSTANCE = new Matcher257();

    /**
     * Expected node type.
     */
    private static final String EXPECTED_TYPE = "classdef";

    /**
     * The number of the first hole.
     */
    private static final int FIRST_HOLE_ID = 2;

    /**
     * Constructor.
     */
    private Matcher257() {
    }

    @Override
    public boolean match(final Node node,
        final Map<Integer, List<Node>> children,
        final Map<Integer, String> data) {
        final boolean result = node.belongsToGroup(Matcher257.EXPECTED_TYPE)
            && Matcher258.INSTANCE.match(node.getChild(0), children, data)
            && Matcher259.INSTANCE.match(node.getChild(1), children, data);
        if (result) {
            final int count = node.getChildCount();
            final List<Node> list = new ArrayList<>(count - 2);
            for (int index = 2; index < count; index = index + 1) {
                list.add(node.getChild(index));
            }
            children.put(Matcher257.FIRST_HOLE_ID, list);
        }
        return result;
    }
}
