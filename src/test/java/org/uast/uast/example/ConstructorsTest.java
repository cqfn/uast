/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.uast.uast.example.green.Addition;
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
    void testAddition() {
        IntegerLiteral.Constructor ilc = new IntegerLiteral.Constructor();
        ilc.setData("2");
        final IntegerLiteral left = ilc.create();
        ilc = new IntegerLiteral.Constructor();
        ilc.setData("3");
        final IntegerLiteral right = ilc.create();
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
}
