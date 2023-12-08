/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2023 Ivan Kniazkov
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

package org.cqfn.uast.lang.java;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.LiteralStringValueExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithIdentifier;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.PrimitiveType;
import java.util.Locale;
import java.util.Optional;
import org.cqfn.astranaut.core.DraftNode;
import org.cqfn.astranaut.core.Node;

/**
 * Converter from the format provided by the JavaParser parser ("raw")
 * to the {@link Node} interface.
 *
 * @since 0.1
 */
public class JavaRawToNodeConverter {
    /**
     * Recursive converter from JavaParser context to {@link Node}.
     *
     * @param node Current JavaParser node
     * @return A node
     */
    public Node convert(final com.github.javaparser.ast.Node node) {
        final DraftNode.Constructor ctor = new DraftNode.Constructor();
        if (node instanceof UnaryExpr) {
            final String operator = ((UnaryExpr) node).getOperator().name();
            final String[] tokens = operator.split("_");
            final StringBuilder builder = new StringBuilder();
            for (final String token : tokens) {
                builder.append(token.substring(0, 1).toUpperCase(Locale.ROOT))
                    .append(token.substring(1).toLowerCase(Locale.ROOT));
            }
            ctor.setName(builder.toString());
        } else if (node instanceof ClassOrInterfaceType) {
            JavaRawToNodeConverter.processClassOrInterfaceType(node, ctor);
        } else {
            ctor.setName(node.getClass().getSimpleName());
        }
        if (node instanceof AssignExpr) {
            final String operator = ((AssignExpr) node).getOperator().asString();
            ctor.setData(operator);
        } else {
            ctor.setData(JavaParserNodeDataExtractor.getData(node));
        }
        for (final com.github.javaparser.ast.Node child : node.getChildNodes()) {
            final Node converted = this.convert(child);
            ctor.addChild(converted);
        }
        return ctor.createNode();
    }

    /**
     * Distinguishes names for class and interface nodes and sets them.
     *
     * @param node The JavaParser node
     * @param ctor The node constructor
     */
    private static void processClassOrInterfaceType(
        final com.github.javaparser.ast.Node node,
        final DraftNode.Constructor ctor) {
        final Optional<com.github.javaparser.ast.Node> parent = node.getParentNode();
        if (parent.isPresent() && parent.get() instanceof ClassOrInterfaceDeclaration) {
            if (((ClassOrInterfaceDeclaration) parent.get()).getExtendedTypes().contains(node)) {
                ctor.setName("ClassType");
            } else {
                ctor.setName("InterfaceType");
            }
        } else if (parent.isPresent() && parent.get() instanceof MethodDeclaration) {
            if (((MethodDeclaration) parent.get()).getThrownExceptions().contains(node)) {
                ctor.setName("Exception");
            } else {
                ctor.setName("ReturnType");
            }
        } else {
            ctor.setName(node.getClass().getSimpleName());
        }
    }

    /**
     * Extracts data from a node of JavaParser.
     *
     * @since 0.1
     */
    private static class JavaParserNodeDataExtractor {
        /**
         * Gets data from a JavaParser node.
         *
         * @param node The JavaParser node
         * @return A data from the node or an empty string if there is no data to return
         */
        private static String getData(final com.github.javaparser.ast.Node node) {
            String data = "";
            if (node instanceof Modifier) {
                data = ((Modifier) node).getKeyword().asString();
            }
            if (node instanceof NodeWithIdentifier) {
                data = ((NodeWithIdentifier<?>) node).getIdentifier();
            }
            if (node instanceof PrimitiveType) {
                data = ((PrimitiveType) node).getType().asString();
            }
            if (node instanceof LiteralStringValueExpr) {
                data = ((LiteralStringValueExpr) node).getValue();
            }
            if (node instanceof BinaryExpr) {
                data = ((BinaryExpr) node).getOperator().asString();
            }
            return data;
        }
    }
}
