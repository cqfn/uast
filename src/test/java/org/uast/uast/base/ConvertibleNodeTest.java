/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.base;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.uast.uast.example.green.Addition;
import org.uast.uast.example.green.IntegerLiteral;

/**
 * Testing {@link ConvertibleNode} class.
 *
 * @since 1.0
 */
public class ConvertibleNodeTest {
    /**
     * Testing the transformation from 'typical' node to convertible.
     */
    @Test
    public void transformation() {
        IntegerLiteral.Constructor icr = new IntegerLiteral.Constructor();
        icr.setData("7");
        final Node left = icr.createNode();
        icr = new IntegerLiteral.Constructor();
        icr.setData("11");
        final Node right = icr.createNode();
        final Addition.Constructor acr = new Addition.Constructor();
        acr.setChildrenList(Arrays.asList(left, right));
        final Node addition = acr.createNode();
        final ConvertibleNode convertible = new ConvertibleNode(addition);
        Assertions.assertEquals("Addition", convertible.getType().getName());
        Assertions.assertEquals(2, convertible.getChildCount());
        final ConvertibleNode first = convertible.getConvertibleChild(0);
        Assertions.assertEquals(convertible, first.getParent());
        final Node second = convertible.getChild(1);
        icr = new IntegerLiteral.Constructor();
        icr.setData("13");
        final Node third = icr.createNode();
        final boolean result = convertible.replaceChild(second, third);
        Assertions.assertTrue(result);
    }
}
