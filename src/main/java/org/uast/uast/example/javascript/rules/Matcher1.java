/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.example.javascript.rules;

import java.util.List;
import java.util.Map;
import org.uast.uast.base.Matcher;
import org.uast.uast.base.Node;

/**
 * Matcher for the subtree returned by the 'js' language parser.
 *
 * @since 1.0
 */
public final class Matcher1 implements Matcher {
    /**
     * The instance.
     */
    public static final Matcher INSTANCE = new Matcher1();

    /**
     * Expected number of child nodes.
     */
    private static final String EXPECTED_TYPE = "identifier";

    /**
     * Expected number of child nodes.
     */
    private static final int EXPECTED_COUNT = 1;

    /**
     * Constructor.
     */
    private Matcher1() {
    }

    @Override
    public boolean match(final Node node, final Map<Integer, List<Node>> children,
        final Map<Integer, String> data) {
        return node.belongsToGroup(Matcher1.EXPECTED_TYPE)
            && node.getChildCount() == Matcher1.EXPECTED_COUNT
            && Matcher0.INSTANCE.match(node.getChild(0), children, data);
    }
}
