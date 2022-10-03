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
    /**
     * The "Name" string.
     */
    private static final String NAME = "Name";

    /**
     * The "Variable" string.
     */
    private static final String VARIABLE = "Variable";

    /**
     * The "Identifier" string.
     */
    private static final String IDENTIFIER = "Identifier";

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
        final Builder ident = factory.createBuilder(ExpressionsTest.IDENTIFIER);
        ident.setData("var");
        final Builder name = factory.createBuilder(ExpressionsTest.NAME);
        name.setChildrenList(Collections.singletonList(ident.createNode()));
        final Builder builder = factory.createBuilder(ExpressionsTest.VARIABLE);
        builder.setChildrenList(Collections.singletonList(name.createNode()));
        final Node variable = builder.createNode();
        final Expressions generator = new Expressions();
        final String code = generator.generate((Expression) variable);
        Assertions.assertEquals("var", code);
    }

    @Test
    void testPreDecrement() {
        final Factory factory = JavaFactory.INSTANCE;
        final Builder ident = factory.createBuilder(ExpressionsTest.IDENTIFIER);
        ident.setData("x");
        final Builder name = factory.createBuilder(ExpressionsTest.NAME);
        name.setChildrenList(Collections.singletonList(ident.createNode()));
        final Builder variable = factory.createBuilder(ExpressionsTest.VARIABLE);
        variable.setChildrenList(Collections.singletonList(name.createNode()));
        final Builder builder = factory.createBuilder("PreDecrement");
        builder.setChildrenList(Collections.singletonList(variable.createNode()));
        final Node predecr = builder.createNode();
        final Expressions generator = new Expressions();
        final String code = generator.generate((Expression) predecr);
        Assertions.assertEquals("--x", code);
    }

    @Test
    void testPostIncrement() {
        final Factory factory = JavaFactory.INSTANCE;
        final Builder ident = factory.createBuilder(ExpressionsTest.IDENTIFIER);
        ident.setData("y");
        final Builder name = factory.createBuilder(ExpressionsTest.NAME);
        name.setChildrenList(Collections.singletonList(ident.createNode()));
        final Builder variable = factory.createBuilder(ExpressionsTest.VARIABLE);
        variable.setChildrenList(Collections.singletonList(name.createNode()));
        final Builder builder = factory.createBuilder("PostIncrement");
        builder.setChildrenList(Collections.singletonList(variable.createNode()));
        final Node predecr = builder.createNode();
        final Expressions generator = new Expressions();
        final String code = generator.generate((Expression) predecr);
        Assertions.assertEquals("y++", code);
    }

    @Test
    void testModulusAssignment() {
        final Factory factory = JavaFactory.INSTANCE;
        final Builder ident = factory.createBuilder(ExpressionsTest.IDENTIFIER);
        ident.setData("num");
        final Builder name = factory.createBuilder(ExpressionsTest.NAME);
        name.setChildrenList(Collections.singletonList(ident.createNode()));
        final Builder variable = factory.createBuilder(ExpressionsTest.VARIABLE);
        variable.setChildrenList(Collections.singletonList(name.createNode()));
        final Builder right = factory.createBuilder("IntegerLiteral");
        right.setData("3");
        final Builder builder = factory.createBuilder("ModulusAssignment");
        builder.setChildrenList(Arrays.asList(variable.createNode(), right.createNode()));
        final Node assign = builder.createNode();
        final Expressions generator = new Expressions();
        final String code = generator.generate((Expression) assign);
        Assertions.assertEquals("num %= 3", code);
    }

    @Test
    void testFunctionCall() {
        final Factory factory = JavaFactory.INSTANCE;
        final Builder java = factory.createBuilder(ExpressionsTest.IDENTIFIER);
        java.setData("java");
        final Builder lang = factory.createBuilder(ExpressionsTest.IDENTIFIER);
        lang.setData("lang");
        final Builder math = factory.createBuilder(ExpressionsTest.IDENTIFIER);
        math.setData("Math");
        final Builder ident = factory.createBuilder(ExpressionsTest.IDENTIFIER);
        ident.setData("val");
        final Builder first = factory.createBuilder(ExpressionsTest.NAME);
        first.setChildrenList(Collections.singletonList(java.createNode()));
        final Builder second = factory.createBuilder(ExpressionsTest.NAME);
        second.setChildrenList(Arrays.asList(first.createNode(), lang.createNode()));
        final Builder owner = factory.createBuilder(ExpressionsTest.NAME);
        owner.setChildrenList(Arrays.asList(second.createNode(), math.createNode()));
        final Builder identifier = factory.createBuilder(ExpressionsTest.IDENTIFIER);
        identifier.setData("abs");
        final Builder name = factory.createBuilder(ExpressionsTest.NAME);
        name.setChildrenList(Collections.singletonList(ident.createNode()));
        final Builder variable = factory.createBuilder(ExpressionsTest.VARIABLE);
        variable.setChildrenList(Collections.singletonList(name.createNode()));
        final Builder list = factory.createBuilder("ExpressionList");
        list.setChildrenList(Collections.singletonList(variable.createNode()));
        final Builder builder = factory.createBuilder("FunctionCall");
        builder.setChildrenList(
            Arrays.asList(
                owner.createNode(),
                identifier.createNode(),
                list.createNode()
            )
        );
        final Node call = builder.createNode();
        final Expressions generator = new Expressions();
        final String code = generator.generate((Expression) call);
        Assertions.assertEquals("java.lang.Math.abs(val)", code);
    }
}
