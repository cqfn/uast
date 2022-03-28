/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.lang;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.LiteralStringValueExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithIdentifier;
import com.github.javaparser.ast.type.PrimitiveType;
import java.util.LinkedList;
import java.util.List;
import org.uast.uast.base.DraftNode;
import org.uast.uast.base.Node;

/**
 * Converter from the format provided by the JavaParser parser
 * to the {@link Node} interface.
 *
 * @since 1.0
 */
public class JavaParserConverter {
    /**
     * Recursive converter from ANTLR context to {@link Node}.
     *
     * @param node Current JavaParser node
     * @return A node
     */
    public Node convert(final com.github.javaparser.ast.Node node) {
        final String type = node.getClass().getSimpleName();
        final String data = getData(node);
        final List<Node> children = new LinkedList<>();
        for (final com.github.javaparser.ast.Node child : node.getChildNodes()) {
            final Node converted = this.convert(child);
            children.add(converted);
        }
        return new DraftNode(type, data, children);
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
