/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.base;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.uast.uast.utils.Pair;

/**
 * Test for {@link TreeReplacer} class.
 *
 * @since 1.0
 */
public class TreeReplacerTest {
    /**
     * Testing tree recreation when a root is the node to be replaced.
     */
    @Test
    public void testReplacementOfRoot() {
        final Node root =
            this.createNode(
                "1",
                "",
                this.createNode(
                    "11",
                    "",
                    this.createNode(
                        "21",
                        ""
                    ),
                    this.createNode(
                        "22",
                        ""
                    )
                ),
                this.createNode("12", ""),
                this.createNode(
                    "13",
                    "",
                    this.createNode(
                        "23",
                        ""
                    ),
                    this.createNode(
                        "24",
                        ""
                    )
                )
            );
        final Node target = this.createTargetTree();
        final Pair<Node, Integer> result = new TreeReplacer().replace(root, root, target);
        Assertions.assertEquals(target, result.getKey());
        Assertions.assertEquals(-1, result.getValue());
    }

    /**
     * Testing tree recreation when a root child is the node to be replaced.
     */
    @Test
    public void testReplacementOfRootChild() {
        final Node source =
            this.createNode(
                "130",
                "",
                this.createNode(
                    "230",
                    ""
                ),
                this.createNode(
                    "240",
                    ""
                )
            );
        final Node root =
            this.createNode(
                "10",
                "",
                this.createNode(
                    "110",
                    "",
                    this.createNode(
                        "210",
                        ""
                    ),
                    this.createNode(
                        "220",
                        ""
                    )
                ),
                this.createNode("120", ""),
                source
            );
        final Node target = this.createTargetTree();
        final Pair<Node, Integer> result = new TreeReplacer().replace(root, source, target);
        Assertions.assertEquals(target, result.getKey().getChild(2));
        Assertions.assertEquals(2, result.getValue());
    }

    /**
     * Testing tree recreation when a child of a root child is the node to be replaced.
     */
    @Test
    public void testReplacementOfRootGrandChild() {
        final Node source = this.createNode("2300", "");
        final Node left =
            this.createNode(
            "1100",
            "",
            this.createNode(
                "2100",
                ""
            ),
            this.createNode(
                "2200",
                ""
            )
        );
        final Node mid = this.createNode("1200", "");
        final Node root = this.createNode(
            "100",
            "",
            left,
            mid,
            this.createNode(
                "1300",
                "",
                source,
                this.createNode(
                    "2400",
                    ""
                )
            )
        );
        final Node target = this.createTargetTree();
        final Pair<Node, Integer> result = new TreeReplacer().replace(root, source, target);
        Assertions.assertEquals(target, result.getKey().getChild(2).getChild(0));
        Assertions.assertEquals(left, result.getKey().getChild(0));
        Assertions.assertEquals(mid, result.getKey().getChild(1));
        Assertions.assertEquals(2, result.getValue());
    }

    /**
     * Testing tree recreation when a source subtree to be replaced is not found.
     */
    @Test
    public void testReplacementWithoutMatch() {
        final Node root =
            this.createNode(
                "111",
                "",
                this.createNode("222", ""),
                this.createNode("333", "")
        );
        final Node source = this.createNode("444", "");
        final Node target = this.createTargetTree();
        final Pair<Node, Integer> result = new TreeReplacer().replace(root, source, target);
        Assertions.assertEquals(EmptyTree.INSTANCE, result.getKey());
        Assertions.assertEquals(-1, result.getValue());
    }

    /**
     * Creates a target tree for replacement.
     * @return A new tree
     */
    private Node createTargetTree() {
        return this.createNode(
            "2",
            "",
            this.createNode(
                "14",
                ""
            )
        );
    }

    /**
     * Creates node for test purposes.
     * @param type The type name
     * @param data The data (in a textual format)
     * @param children The list of children
     * @return A new node
     */
    private Node createNode(final String type, final String data, final Node... children) {
        final DraftNode.Constructor ctor = new DraftNode.Constructor();
        ctor.setName(type);
        ctor.setData(data);
        ctor.setChildrenList(Arrays.asList(children));
        return ctor.createNode();
    }
}
