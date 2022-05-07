/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.python.rules;

import java.util.Map;
import org.uast.uast.base.Matcher;
import org.uast.uast.base.Node;

/**
 * Checks if the node matches some structure, and extracts the data and children.
 *
 * @since 1.0
 */
public final class Matcher0 implements Matcher {
    /**
     * The instance.
     */
    public static final Matcher INSTANCE = new Matcher0();

    /**
     * Expected node type.
     */
    private static final String EXPECTED_TYPE = "self";

    /**
     * Expected number of child nodes.
     */
    private static final int EXPECTED_COUNT = 0;

    /**
     * Constructor.
     */
    private Matcher0() {
    }

    @Override
    public boolean match(final Node node,
        final Map<Integer, Node> children,
        final Map<Integer, String> data) {
        return node.belongsToGroup(Matcher0.EXPECTED_TYPE)
            && node.getChildCount() == Matcher0.EXPECTED_COUNT;
    }
}
