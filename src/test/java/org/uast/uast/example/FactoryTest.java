/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.uast.uast.base.Builder;
import org.uast.uast.base.Factory;
import org.uast.uast.base.Node;
import org.uast.uast.example.green.GreenFactory;

/**
 * Testing {@link Factory} class.
 *
 * @since 1.0
 */
public class FactoryTest {
    /**
     * Creating integer literal instance.
     */
    @Test
    void createIntegerLiteralTest() {
        final String type = "IntegerLiteral";
        final Factory factory = GreenFactory.INSTANCE;
        final Builder builder = factory.createBuilder(type);
        builder.setData("13");
        Assertions.assertTrue(builder.isValid());
        final Node node = builder.createNode();
        Assertions.assertEquals(node.getType().getName(), type);
    }
}
