/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022 Ivan Kniazkov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cqfn.uast.lang.java.gen;

import java.util.Arrays;
import java.util.Collections;

import org.cqfn.astranaut.core.Builder;
import org.cqfn.astranaut.core.Factory;
import org.cqfn.astranaut.core.Node;
import org.cqfn.uast.tree.green.Expression;
import org.cqfn.uast.tree.java.JavaFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Java expression generator.
 *
 * @since 0.1.2
 */
class ExpressionsTest {
    @Test
    void testAddition() {
        final Factory factory = JavaFactory.INSTANCE;
        final Builder left = factory.createBuilder("IntegerLiteral");
        left.setData("2");
        final Builder right = factory.createBuilder("IntegerLiteral");
        right.setData("3");
        final Builder builder = factory.createBuilder("Addition");
        builder.setChildrenList(Arrays.asList(left.createNode(), right.createNode()));
        final Node addition = builder.createNode();
        final Expressions generator = new Expressions();
        final String code = generator.generate((Expression) addition);
        Assertions.assertEquals("2 + 3", code);
    }

    @Test
    void testVariable() {
        final Factory factory = JavaFactory.INSTANCE;
        final Builder ident = factory.createBuilder("Identifier");
        ident.setData("var");
        final Builder name = factory.createBuilder("Name");
        name.setChildrenList(Collections.singletonList(ident.createNode()));
        final Builder builder = factory.createBuilder("Variable");
        builder.setChildrenList(Collections.singletonList(name.createNode()));
        final Node variable = builder.createNode();
        final Expressions generator = new Expressions();
        final String code = generator.generate((Expression) variable);
        Assertions.assertEquals("var", code);
    }

    @Test
    void testPreDecrement() {
        final Factory factory = JavaFactory.INSTANCE;
        final Builder ident = factory.createBuilder("Identifier");
        ident.setData("var");
        final Builder name = factory.createBuilder("Name");
        name.setChildrenList(Collections.singletonList(ident.createNode()));
        final Builder variable = factory.createBuilder("Variable");
        variable.setChildrenList(Collections.singletonList(name.createNode()));
        final Builder builder = factory.createBuilder("PreDecrement");
        builder.setChildrenList(Collections.singletonList(variable.createNode()));
        final Node predecr = builder.createNode();
        final Expressions generator = new Expressions();
        final String code = generator.generate((Expression) predecr);
        Assertions.assertEquals("--var", code);
    }
}
