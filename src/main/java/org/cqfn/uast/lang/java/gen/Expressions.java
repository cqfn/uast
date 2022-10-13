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
import org.cqfn.uast.tree.green.Assignment;
import org.cqfn.uast.tree.green.BinaryExpression;
import org.cqfn.uast.tree.green.Expression;
import org.cqfn.uast.tree.green.ExpressionList;
import org.cqfn.uast.tree.green.FunctionCall;
import org.cqfn.uast.tree.green.Name;
import org.cqfn.uast.tree.green.ParenthesizedExpression;
import org.cqfn.uast.tree.green.PostDecrement;
import org.cqfn.uast.tree.green.PostIncrement;
import org.cqfn.uast.tree.green.PropertyAccess;
import org.cqfn.uast.tree.green.StringLiteral;
import org.cqfn.uast.tree.green.UnaryExpression;
import org.cqfn.uast.tree.green.Variable;

/**
 * Code generator for Java expressions.
 *
 * @since 0.1.2
 */
public final class Expressions {
    /**
     * The instance.
     */
    public static final Expressions INSTANCE = new Expressions();

    /**
     * Map that contains generators.
     */
    private final Map<String, Generator> map;

    /**
     * Constructor.
     */
    private Expressions() {
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
        gen.put("This", expr -> "this");
        gen.put("IntegerLiteral", Node::getData);
        gen.put(
            "StringLiteral",
            expr -> {
                final StringLiteral node = (StringLiteral) expr;
                return String.format("\"%s\"", node.getData());
            }
        );
        gen.put("Identifier", Node::getData);
        gen.put(
            "Variable",
            expr -> {
                final Variable node = (Variable) expr;
                return Names.INSTANCE.generate((Name) node.getChild(0));
            }
        );
        gen.put(
            "ParenthesizedExpression",
            expr -> {
                final ParenthesizedExpression node = (ParenthesizedExpression) expr;
                return String.format("(%s)", this.generate(node.getExpression()));
            }
        );
        gen.put(
            "PropertyAccess",
            expr -> {
                final PropertyAccess node = (PropertyAccess) expr;
                final String left = this.generate(node.getLeft());
                final String right = this.generate(node.getRight());
                return String.format("%s.%s", left, right);
            }
        );
        gen.put(
            "FunctionCall",
            expr -> {
                final FunctionCall node = (FunctionCall) expr;
                String owner = "";
                final Name classname = node.getOwner();
                if (classname != null) {
                    owner = Names.INSTANCE.generate(classname).concat(".");
                }
                final String name = this.generate(node.getName());
                final ExpressionList list = node.getArguments();
                String params = "";
                final int size = list.getChildCount();
                if (size > 0) {
                    final StringBuilder builder = new StringBuilder();
                    builder.append(this.generate(list.getExpression(0)));
                    for (int idx = 1; idx < size; idx += 1) {
                        builder.append(", ").append(this.generate(list.getExpression(idx)));
                    }
                    params = builder.toString();
                }
                return String.format("%s%s(%s)", owner, name, params);
            }
        );
        gen.putAll(this.initBinaryExpressions());
        gen.putAll(this.initUnaryExpressions());
        gen.putAll(this.initAssignmentExpressions());
        return gen;
    }

    /**
     * Initialises map with binary expressions.
     * @return A map
     */
    private Map<String, Generator> initBinaryExpressions() {
        final Map<String, Generator> gen = new TreeMap<>();
        gen.put("Addition", new BinaryExpressionGenerator(this, "+"));
        gen.put("Subtraction", new BinaryExpressionGenerator(this, "-"));
        gen.put("Multiplication", new BinaryExpressionGenerator(this, "*"));
        gen.put("Division", new BinaryExpressionGenerator(this, "/"));
        gen.put("Modulus", new BinaryExpressionGenerator(this, "%"));
        gen.put("IsEqualTo", new BinaryExpressionGenerator(this, "=="));
        gen.put("NotEqualTo", new BinaryExpressionGenerator(this, "!="));
        gen.put("GreaterThan", new BinaryExpressionGenerator(this, ">"));
        gen.put("LessThan", new BinaryExpressionGenerator(this, "<"));
        gen.put("GreaterThanOrEqualTo", new BinaryExpressionGenerator(this, ">="));
        gen.put("LessThanOrEqualTo", new BinaryExpressionGenerator(this, "<="));
        gen.put("BitwiseAnd", new BinaryExpressionGenerator(this, "&"));
        gen.put("BitwiseOr", new BinaryExpressionGenerator(this, "|"));
        gen.put("ExclusiveOr", new BinaryExpressionGenerator(this, "^"));
        gen.put("LeftShift", new BinaryExpressionGenerator(this, "<<"));
        gen.put("RightShift", new BinaryExpressionGenerator(this, ">>"));
        gen.put("UnsignedRightShift", new BinaryExpressionGenerator(this, ">>>"));
        gen.put("LogicalOr", new BinaryExpressionGenerator(this, "||"));
        gen.put("LogicalAnd", new BinaryExpressionGenerator(this, "&&"));
        return gen;
    }

    /**
     * Initialises map with unary expressions.
     * @return A map
     */
    private Map<String, Generator> initUnaryExpressions() {
        final Map<String, Generator> gen = new TreeMap<>();
        gen.put(
            "PostIncrement",
            expr -> {
                final PostIncrement node = (PostIncrement) expr;
                final String element = this.generate((Expression) node.getChild(0));
                return String.format("%s++", element);
            }
        );
        gen.put(
            "PostDecrement",
            expr -> {
                final PostDecrement node = (PostDecrement) expr;
                final String element = this.generate((Expression) node.getChild(0));
                return String.format("%s--", element);
            }
        );
        gen.put("BitwiseComplement", new PreUnaryExpressionGenerator(this, "~"));
        gen.put("LogicalNot", new PreUnaryExpressionGenerator(this, "!"));
        gen.put("PreIncrement", new PreUnaryExpressionGenerator(this, "++"));
        gen.put("PreDecrement", new PreUnaryExpressionGenerator(this, "--"));
        gen.put("Positive", new PreUnaryExpressionGenerator(this, "+"));
        gen.put("Negative", new PreUnaryExpressionGenerator(this, "-"));
        return gen;
    }

    /**
     * Initialises map with assignment expressions.
     * @return A map
     */
    private Map<String, Generator> initAssignmentExpressions() {
        final Map<String, Generator> gen = new TreeMap<>();
        gen.put("SimpleAssignment", new AssignmentExpressionGenerator(this, "="));
        gen.put("AdditionAssignment", new AssignmentExpressionGenerator(this, "+="));
        gen.put("SubtractionAssignment", new AssignmentExpressionGenerator(this, "-="));
        gen.put("MultiplicationAssignment", new AssignmentExpressionGenerator(this, "*="));
        gen.put("DivisionAssignment", new AssignmentExpressionGenerator(this, "/="));
        gen.put("ModulusAssignment", new AssignmentExpressionGenerator(this, "%="));
        gen.put("BitwiseAndAssignment", new AssignmentExpressionGenerator(this, "&="));
        gen.put("BitwiseOrAssignment", new AssignmentExpressionGenerator(this, "|="));
        gen.put("ExclusiveOrAssignment", new AssignmentExpressionGenerator(this, "^="));
        gen.put("RightShiftAssignment", new AssignmentExpressionGenerator(this, ">>="));
        gen.put("UnsignedRightShiftAssignment", new AssignmentExpressionGenerator(this, ">>>="));
        gen.put("LeftShiftAssignment", new AssignmentExpressionGenerator(this, "<<="));
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

    /**
     * Code generator for assignment expressions.
     *
     * @since 0.1.2
     */
    private static class AssignmentExpressionGenerator implements Generator {
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
        AssignmentExpressionGenerator(final Expressions base, final String operator) {
            this.base = base;
            this.operator = operator;
        }

        @Override
        public String generate(final Expression expr) {
            final Assignment node = (Assignment) expr;
            final String left = this.base.generate(node.getLeft());
            final String right = this.base.generate(node.getRight());
            return String.format("%s %s %s", left, this.operator, right);
        }
    }

    /**
     * Code generator for prefix unary expressions.
     *
     * @since 0.1.2
     */
    private static class PreUnaryExpressionGenerator implements Generator {
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
        PreUnaryExpressionGenerator(final Expressions base, final String operator) {
            this.base = base;
            this.operator = operator;
        }

        @Override
        public String generate(final Expression expr) {
            final UnaryExpression node = (UnaryExpression) expr;
            final String element = this.base.generate((Expression) node.getChild(0));
            return String.format("%s%s", this.operator, element);
        }
    }
}
