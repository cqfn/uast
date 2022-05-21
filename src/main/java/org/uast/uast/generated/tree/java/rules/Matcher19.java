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
public final class Matcher19 implements Matcher {
    /**
     * The instance.
     */
    public static final Matcher INSTANCE = new Matcher19();

    /**
     * Expected node type.
     */
    private static final String EXPECTED_TYPE = "Parameter";

    /**
     * Expected number of child nodes.
     */
    private static final int EXPECTED_COUNT = 2;

    /**
     * Constructor.
     */
    private Matcher19() {
    }

    @Override
    public boolean match(final Node node,
        final Map<Integer, List<Node>> children,
        final Map<Integer, String> data) {
        final boolean result = node.belongsToGroup(Matcher19.EXPECTED_TYPE)
            && node.getChildCount() == Matcher19.EXPECTED_COUNT;
        if (result) {
            children.put(1, Collections.singletonList(node.getChild(0)));
            children.put(2, Collections.singletonList(node.getChild(1)));
        }
        return result;
    }
}
