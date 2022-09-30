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

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import org.cqfn.astranaut.core.Node;
import org.cqfn.uast.tree.green.BinaryExpression;
import org.cqfn.uast.tree.green.Expression;

/**
 * Code generator for Java expressions.
 *
 * @since 0.1.2
 */
public class Expressions {
    /**
     * Map that contains generators.
     */
    private final Map<String, Generator> map;

    /**
     * Constructor.
     */
    public Expressions() {
        this.map = Collections.unmodifiableMap(this.init());
    }

    /**
     * Generates source code from expression.
     * @param expr The expression
     * @return Java source code
     */
    public String generate(final Expression expr) {
        return this.map.get(expr.getTypeName()).generate(expr);
    }

    /**
     * Initialises map with generators.
     * @return A map
     */
    private Map<String, Generator> init() {
        final Map<String, Generator> gen = new TreeMap<>();
        gen.put("IntegerLiteral", Node::getData);
        gen.put("Addition", new BinaryExpressionGenerator(this, "+"));
        gen.put("Subtraction", new BinaryExpressionGenerator(this, "-"));
        gen.put("Multiplication", new BinaryExpressionGenerator(this, "*"));
        gen.put("LessThanOrEqualTo", new BinaryExpressionGenerator(this, "<="));
        gen.put("This", expr -> "this");
        return gen;
    }

    /**
     * Interface for generating Java source code for a specific expression.
     *
     * @since 0.1.2
     */
    private interface Generator {
        /**
         * Generates Java source code for a specific expression.
         * @param expr An expression
         * @return Java source code
         */
        String generate(Expression expr);
    }

    /**
     * Code generator for binary expressions.
     *
     * @since 0.1.2
     */
    private static class BinaryExpressionGenerator implements Generator {
        /**
         * Base generator.
         */
        private final Expressions base;

        /**
         * Operator as a string.
         */
        private final String operator;

        /**
         * Constructor.
         * @param base Base generator
         * @param operator Operator as a string
         */
        BinaryExpressionGenerator(final Expressions base, final String operator) {
            this.base = base;
            this.operator = operator;
        }

        @Override
        public String generate(final Expression expr) {
            final BinaryExpression node = (BinaryExpression) expr;
            final String left = this.base.generate(node.getLeft());
            final String right = this.base.generate(node.getRight());
            return String.format("%s %s %s", left, this.operator, right);
        }
    }
}
