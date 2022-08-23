/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.base;

import java.util.ArrayList;
import java.util.List;
import org.uast.uast.utils.Pair;

/**
 * Creator of a new tree with a modified subtree.
 *
 * @since 1.0
 */
public class TreeReplacer {
    /**
     * Replaces a subtree of the initial tree with the newly created subtree.
     * @param tree The initial tree
     * @param source The subtree to be replaced
     * @param target The new subtree
     * @return Modified tree or an empty tree if it was not modified,
     *  and the index:
     *  - {@code -1} if the full tree was replaced or was not replaced at all;
     *  - index of the root child which branch was modified if a subtree was replaced
     */
    public Pair<Node, Integer> replace(final Node tree, final Node source, final Node target) {
        Pair<Node, Integer> result = new Pair<>(EmptyTree.INSTANCE, -1);
        if (tree.equals(source)) {
            result = new Pair<>(target, -1);
        } else if (tree.getChildCount() > 0) {
            final List<Node> list = new ArrayList<>(tree.getChildCount());
            int pos = -1;
            for (int idx = 0; idx < tree.getChildCount(); idx += 1) {
                final Node child = tree.getChild(idx);
                final Pair<Node, Integer> replacement = this.replace(child, source, target);
                if (replacement.getKey().equals(EmptyTree.INSTANCE)) {
                    list.add(child);
                } else {
                    list.add(replacement.getKey());
                    pos = idx;
                }
            }
            if (pos != -1) {
                final Builder builder = tree.getType().createBuilder();
                builder.setChildrenList(list);
                result = new Pair<>(builder.createNode(), pos);
            }
        }
        return result;
    }
}
