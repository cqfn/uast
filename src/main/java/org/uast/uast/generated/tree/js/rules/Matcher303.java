/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.js.rules;

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
public final class Matcher303 implements Matcher {
    /**
     * The instance.
     */
    public static final Matcher INSTANCE = new Matcher303();

    /**
     * Expected node type.
     */
    private static final String EXPECTED_TYPE = "variableDeclarationList";

    /**
     * The number of the first hole.
     */
    private static final int FIRST_HOLE_ID = 1;

    /**
     * Constructor.
     */
    private Matcher303() {
    }

    @Override
    public boolean match(final Node node,
        final Map<Integer, List<Node>> children,
        final Map<Integer, String> data) {
        final boolean result = node.belongsToGroup(Matcher303.EXPECTED_TYPE)
            && Matcher304.INSTANCE.match(node.getChild(0), children, data);
        if (result) {
            final int count = node.getChildCount();
            final List<Node> list = new ArrayList<>(count - 1);
            for (int index = 1; index < count; index = index + 1) {
                list.add(node.getChild(index));
            }
            children.put(Matcher303.FIRST_HOLE_ID, list);
        }
        return result;
    }
}
