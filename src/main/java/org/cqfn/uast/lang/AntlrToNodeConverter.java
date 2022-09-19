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
package org.cqfn.uast.lang;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
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
     * Names of nodes to be skipped.
     */
    private static final Set<String> SKIPPED_NODES = new HashSet<>(
        Arrays.asList(
            "single_input", "eval_input",
            "stmt", "simple_stmt", "compound_stmt",
            "test", "testlist",
            "def_parameter",
            "number"
        )
    );

    /**
     * Names of nodes to be ignored.
     */
    private static final Set<String> IGNORED_NODES = new HashSet<>(
        Arrays.asList(
            "eos", "emptyStatement_"
        )
    );

    /**
     * Terminals to be skipped.
     */
    private static final Set<String> SKIPPED_TERMINALS = new HashSet<>(
        Arrays.asList(
            ")", "(", "{", "}", ":", ";", ",", ".", "<EOF>"
        )
    );

    /**
     * The 'literal' string.
     */
    private static final String LITERAL = "literal";

    /**
     * Retain the initial syntax ANTLR tree.
     */
    private final boolean initial;

    /**
     * ANTLR language parser.
     */
    private final Parser parser;

    /**
     * Constructor.
     * @param parser ANTLR parser of the processed language
     * @param initial If {@code true}, retain the initial ANTLR syntax tree
     */
    public AntlrToNodeConverter(final Parser parser, final boolean initial) {
        this.parser = parser;
        this.initial = initial;
    }

    /**
     * Converter from ANTLR context to {@link Node}.
     * @param ctx Current context
     * @return A node
     */
    public Node convert(final RuleContext ctx) {
        final Node node;
        if (this.initial) {
            node = this.convertInitial(ctx);
        } else {
            node = this.convertSimplified(ctx);
        }
        return node;
    }

    /**
     * Recursive converter from ANTLR context to {@link Node}.
     * @param ctx Current context
     * @return A node
     */
    private Node convertInitial(final RuleContext ctx) {
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
            final Node child = this.convertInitial((RuleContext) element);
            ctor.addChild(child);
        }
        return ctor.createNode();
    }

    /**
     * Recursive converter from ANTLR context to {@link Node}.
     * @param ctx Current context
     * @return A node
     */
    private Node convertSimplified(final RuleContext ctx) {
        final String name = this.parser.getRuleNames()[ctx.getRuleIndex()];
        final Node result;
        if (AntlrToNodeConverter.SKIPPED_NODES.contains(name)) {
            final ParseTree element = ctx.getChild(0);
            if (element instanceof TerminalNode) {
                result = convertTerminal((TerminalNode) element);
            } else {
                result = this.convertSimplified((RuleContext) ctx.getChild(0));
            }
        } else {
            final DraftNode.Constructor ctor = new DraftNode.Constructor();
            ctor.setName(name);
            for (int idx = 0; idx < ctx.getChildCount(); idx += 1) {
                final ParseTree element = ctx.getChild(idx);
                if (!this.hasEmptyNode(element)) {
                    this.processParseTree(element, ctor);
                }
            }
            result = ctor.createNode();
        }
        return result;
    }

    /**
     * Checks if {@link ParseTree} element contains a node
     * that represents an empty entity.
     * @param element A {@link ParseTree} element
     * @return The result, {@code true} if elements contains an empty node
     */
    private boolean hasEmptyNode(final ParseTree element) {
        boolean result = false;
        if (!(element instanceof TerminalNode) && element.getChild(0) instanceof RuleContext) {
            final String name = this.parser
                .getRuleNames()[((RuleContext) element.getChild(0)).getRuleIndex()];
            result = AntlrToNodeConverter.IGNORED_NODES.contains(name);
        }
        return result;
    }

    /**
     * Processes {@link ParseTree} element.
     * @param element A {@link ParseTree} element
     * @param ctor A constructor class for draft node
     */
    private void processParseTree(final ParseTree element, final DraftNode.Constructor ctor) {
        if (element instanceof RuleContext
            && !AntlrToNodeConverter.IGNORED_NODES.contains(
                this.parser.getRuleNames()[((RuleContext) element).getRuleIndex()]
            )
        ) {
            final Node child = this.convertSimplified((RuleContext) element);
            ctor.addChild(child);
        }
        if (element instanceof TerminalNode) {
            final Node child = convertTerminal((TerminalNode) element);
            final String data = child.getData();
            if (!(data.isEmpty()
                || AntlrToNodeConverter.SKIPPED_TERMINALS.contains(data))) {
                ctor.addChild(child);
            }
        }
    }

    /**
     * Convert a terminal ANTLR node to {@link Node}.
     * @param terminal Terminal node
     * @return A node
     */
    private static Node convertTerminal(final TerminalNode terminal) {
        final DraftNode.Constructor ctor = new DraftNode.Constructor();
        ctor.setName(AntlrToNodeConverter.LITERAL);
        final String data = terminal.getText();
        ctor.setData(data);
        return ctor.createNode();
    }
}
