/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.example;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.uast.uast.base.Node;
import org.uast.uast.example.green.Addition;
import org.uast.uast.example.green.Expression;
import org.uast.uast.example.green.IntegerLiteral;

/**
 * Testing example node constructors.
 *
 * @since 1.0
 */
public class ConstructorsTest {
    /**
     * The first test.
     */
    @Test
    void testSettingSeparateChild() {
        final Expression left = this.createIntegerNode(2);
        final Expression right = this.createIntegerNode(3);
        final Addition.Constructor ctor = new Addition.Constructor();
        ctor.setLeft(left);
        ctor.setRight(right);
        final Addition node = ctor.create();
        Assertions.assertEquals(node.getChildCount(), 2);
        Assertions.assertEquals(node.getChild(0).getData(), "2");
        Assertions.assertEquals(node.getChild(1).getData(), "3");
        Assertions.assertEquals(node.getType().getName(), "Addition");
        Assertions.assertEquals(node.getType().getChildTypes().size(), 2);
    }

    /**
     * Testing the method 'setChildrenList'.
     */
    @Test
    void testSettingChildrenList() {
        final Node left = this.createIntegerNode(2);
        final Node right = this.createIntegerNode(3);
        final Addition.Constructor ctor = new Addition.Constructor();
        final boolean result = ctor.setChildrenList(Arrays.asList(left, right));
        Assertions.assertTrue(result);
        boolean oops = false;
        try {
            ctor.create();
        } catch (final IllegalStateException ignored) {
            oops = true;
        }
        Assertions.assertFalse(oops);
    }

    /**
     * Creates a new node that represents an integer number.
     * @param value Integer number
     * @return A node
     */
    private Expression createIntegerNode(final int value) {
        final IntegerLiteral.Constructor ilc = new IntegerLiteral.Constructor();
        ilc.setData(String.valueOf(value));
        return ilc.create();
    }
}
