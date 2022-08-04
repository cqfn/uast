/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.lang.java;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.LiteralStringValueExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithIdentifier;
import com.github.javaparser.ast.type.PrimitiveType;
import java.util.Locale;
import org.uast.uast.base.DraftNode;
import org.uast.uast.base.Node;

/**
 * Converter from the format provided by the JavaParser parser ("raw")
 * to the {@link Node} interface.
 *
 * @since 1.0
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
        } else {
            ctor.setName(node.getClass().getSimpleName());
        }
        if (node instanceof AssignExpr) {
            final String operator = ((AssignExpr) node).getOperator().asString();
            ctor.setData(operator);
        } else {
            ctor.setData(getData(node));
        }
        for (final com.github.javaparser.ast.Node child : node.getChildNodes()) {
            final Node converted = this.convert(child);
            ctor.addChild(converted);
        }
        return ctor.createNode();
    }

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
