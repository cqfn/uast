/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.visualizer;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CLI printer of ANTLR ASTs.
 *
 * @since 1.0
 */
public class AstPrinter {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(AstPrinter.class);

    /**
     * ANTLR language parser.
     */
    private final Parser parser;

    /**
     * Requirement to skip wrapper rules.
     */
    private boolean ignoring;

    /**
     * Constructor.
     *
     * @param parser ANTLR Parser of the processed language
     */
    public AstPrinter(final Parser parser) {
        this.parser = parser;
        this.ignoring = true;
    }

    /**
     * Skip rules that are wrappers.
     *
     * @param ignore Contains {@code true} if wrapper classes should be skipped
     *  or {@code false} otherwise.
     */
    public void setIgnoring(final boolean ignore) {
        this.ignoring = ignore;
    }

    /**
     * Print input AST.
     *
     * @param ctx Parsed tree root rule
     */
    public void print(final RuleContext ctx) {
        final StringBuilder stb = new StringBuilder();
        final String result = this.explore(ctx, 0, stb);
        LOG.info(result);
    }

    /**
     * Explore and print tree node with specified indentation.
     * One indentation is 2 tabs.
     *
     * @param ctx Parsed tree rule to be processed
     * @param indentation Number of indentations
     * @param stb Processed AST to be printed
     * @return Result AST as string
     */
    private String explore(
        final RuleContext ctx,
        final int indentation,
        final StringBuilder stb) {
        final boolean ignore = this.ignoring
            && ctx.getChildCount() == 1
            && ctx.getChild(0) instanceof ParserRuleContext;
        if (!ignore) {
            stb.append('\n');
            final String type = this.parser.getRuleNames()[ctx.getRuleIndex()];
            for (int idx = 0; idx < indentation; idx += 1) {
                stb.append("  ");
            }
            stb.append(type);
            stb.append(": ");
            stb.append(ctx.getText());
        }
        for (int idx = 0; idx < ctx.getChildCount(); idx += 1) {
            final ParseTree element = ctx.getChild(idx);
            if (element instanceof RuleContext) {
                final int nextindent;
                if (ignore) {
                    nextindent = 0;
                } else {
                    nextindent = 1;
                }
                this.explore((RuleContext) element, indentation + nextindent, stb);
            }
        }
        return stb.toString();
    }
}
