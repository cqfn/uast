/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.example.javascript.rules;

import java.util.Map;
import org.uast.uast.base.Matcher;
import org.uast.uast.base.Node;

/**
 * Matcher for the subtree returned by the 'js' language parser.
 *
 * @since 1.0
 */
public final class Matcher0 implements Matcher {
    /**
     * The instance.
     */
    public static final Matcher INSTANCE = new Matcher0();

    /**
     * Expected number of child nodes.
     */
    private static final String EXPECTED_TYPE = "literal";

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
    public boolean match(final Node node, final Map<Integer, Node> children,
        final Map<Integer, String> data) {
        final boolean result = node.belongsToGroup(Matcher0.EXPECTED_TYPE)
            && node.getChildCount() == Matcher0.EXPECTED_COUNT;
        if (result) {
            data.put(1, node.getData());
        }
        return result;
    }
}
