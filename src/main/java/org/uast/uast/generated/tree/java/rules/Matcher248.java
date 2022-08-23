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
public final class Matcher248 implements Matcher {
    /**
     * The instance.
     */
    public static final Matcher INSTANCE = new Matcher248();

    /**
     * Expected node type.
     */
    private static final String EXPECTED_TYPE = "MethodDeclaration";

    /**
     * Expected number of child nodes.
     */
    private static final int EXPECTED_COUNT = 6;

    /**
     * The number of the first hole.
     */
    private static final int FIRST_HOLE_ID = 3;

    /**
     * The index of the first child.
     */
    private static final int FIRST_CHILD_ID = 2;

    /**
     * The number of the second hole.
     */
    private static final int SECOND_HOLE_ID = 4;

    /**
     * The index of the second child.
     */
    private static final int SECOND_CHILD_ID = 3;

    /**
     * The number of the third hole.
     */
    private static final int THIRD_HOLE_ID = 5;

    /**
     * The index of the third child.
     */
    private static final int THIRD_CHILD_ID = 4;

    /**
     * The number of the fourth hole.
     */
    private static final int FOURTH_HOLE_ID = 6;

    /**
     * The index of the fourth child.
     */
    private static final int FOURTH_CHILD_ID = 5;

    /**
     * Constructor.
     */
    private Matcher248() {
    }

    @Override
    public boolean match(final Node node,
        final Map<Integer, List<Node>> children,
        final Map<Integer, String> data) {
        final boolean result = node.belongsToGroup(Matcher248.EXPECTED_TYPE)
            && node.getChildCount() == Matcher248.EXPECTED_COUNT
            && Matcher249.INSTANCE.match(node.getChild(0), children, data)
            && Matcher250.INSTANCE.match(node.getChild(1), children, data);
        if (result) {
            children.put(
                Matcher248.FIRST_HOLE_ID,
                Collections.singletonList(node.getChild(Matcher248.FIRST_CHILD_ID))
            );
            children.put(
                Matcher248.SECOND_HOLE_ID,
                Collections.singletonList(node.getChild(Matcher248.SECOND_CHILD_ID))
            );
            children.put(
                Matcher248.THIRD_HOLE_ID,
                Collections.singletonList(node.getChild(Matcher248.THIRD_CHILD_ID))
            );
            children.put(
                Matcher248.FOURTH_HOLE_ID,
                Collections.singletonList(node.getChild(Matcher248.FOURTH_CHILD_ID))
            );
        }
        return result;
    }
}
