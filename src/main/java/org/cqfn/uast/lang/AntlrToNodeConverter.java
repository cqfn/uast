/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2024 Ivan Kniazkov
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
package org.cqfn.uast.lang;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.cqfn.astranaut.core.DraftNode;
import org.cqfn.astranaut.core.Node;

/**
 * Converter from the format provided by the ANTLR parsers
 * to the {@link Node} interface.
 *
 * @since 0.1
 */
public class AntlrToNodeConverter {
    /**
     * The 'literal' string.
     */
    private static final String LITERAL = "literal";

    /**
     * ANTLR language parser.
     */
    private final Parser parser;

    /**
     * Constructor.
     * @param parser ANTLR parser of the processed language
     */
    public AntlrToNodeConverter(final Parser parser) {
        this.parser = parser;
    }

    /**
     * Converter from ANTLR context to {@link Node}.
     * @param ctx Current context
     * @return A node
     */
    public Node convert(final RuleContext ctx) {
        final DraftNode.Constructor ctor = new DraftNode.Constructor();
        final String name = this.parser.getRuleNames()[ctx.getRuleIndex()];
        ctor.setName(name);
        final String data = ctx.getText();
        ctor.setData(data);
        for (int idx = 0; idx < ctx.getChildCount(); idx += 1) {
            final ParseTree element = ctx.getChild(idx);
            if (!(element instanceof RuleContext)) {
                if (((TerminalNodeImpl) element).getSymbol() instanceof CommonToken) {
                    final DraftNode.Constructor literal = new DraftNode.Constructor();
                    literal.setName(AntlrToNodeConverter.LITERAL);
                    literal.setData(element.getText());
                    ctor.addChild(literal.createNode());
                }
                continue;
            }
            final Node child = this.convert((RuleContext) element);
            ctor.addChild(child);
        }
        return ctor.createNode();
    }
}
