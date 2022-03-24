/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.lang;

import java.util.LinkedList;
import java.util.List;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.uast.uast.base.DraftNode;
import org.uast.uast.base.Node;

/**
 * Converter from the format provided by the ANTLR parsers
 * to the {@link Node} interface.
 *
 * @since 1.0
 */
public class AntlrConverter {
    /**
     * ANTLR language parser.
     */
    private final Parser parser;

    /**
     * Constructor.
     * @param parser ANTLR parser of the processed language
     */
    public AntlrConverter(final Parser parser) {
        this.parser = parser;
    }

    /**
     * Recursive converter from ANTLR context to {@link Node}.
     * @param ctx Current context
     * @return A node
     */
    public Node convert(final RuleContext ctx) {
        final String type = this.parser.getRuleNames()[ctx.getRuleIndex()];
        final String data = ctx.getText();
        final List<Node> children = new LinkedList<>();
        for (int idx = 0; idx < ctx.getChildCount(); idx += 1) {
            final ParseTree element = ctx.getChild(idx);
            if (!(element instanceof RuleContext)) {
                continue;
            }
            final Node child = this.convert((RuleContext) element);
            children.add(child);
        }
        return new DraftNode(type, data, children);
    }
}
