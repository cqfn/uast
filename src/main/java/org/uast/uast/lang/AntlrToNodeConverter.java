/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.lang;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.uast.uast.base.DraftNode;
import org.uast.uast.base.Node;

/**
 * Converter from the format provided by the ANTLR parsers
 * to the {@link Node} interface.
 *
 * @since 1.0
 */
public class AntlrToNodeConverter {
    /**
     * Names of nodes to be skipped.
     */
    private static final Set<String> SKIPPED_NODES = new HashSet<>(
        Arrays.asList(
            "single_input", "file_input", "eval_input",
            "stmt", "simple_stmt", "compound_stmt",
            "test", "logical_test", "testlist",
            "def_parameter",
            "number",
            "atom"
        )
    );

    /**
     * Names of nodes to be ignored.
     */
    private static final Set<String> IGNORED_NODES = new HashSet<>(
        Collections.singletonList("eos")
    );

    /**
     * Terminals to be skipped.
     */
    private static final Set<String> SKIPPED_TERMINALS = new HashSet<>(
        Arrays.asList(
            ")", "(", "{", "}", ":", ";", "=", ",", ".", "<EOF>"
        )
    );

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
     * Recursive converter from ANTLR context to {@link Node}.
     * @param ctx Current context
     * @return A node
     */
    public Node convert(final RuleContext ctx) {
        final String name = this.parser.getRuleNames()[ctx.getRuleIndex()];
        final Node result;
        if (AntlrToNodeConverter.SKIPPED_NODES.contains(name)) {
            result = this.convert((RuleContext) ctx.getChild(0));
        } else {
            final DraftNode.Constructor ctor = new DraftNode.Constructor();
            ctor.setName(name);
            for (int idx = 0; idx < ctx.getChildCount(); idx += 1) {
                final ParseTree element = ctx.getChild(idx);
                this.processParseTree(element, ctor);
            }
            result = ctor.createNode();
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
            final Node child = this.convert((RuleContext) element);
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
        ctor.setName("literal");
        final String data = terminal.getText();
        ctor.setData(data);
        return ctor.createNode();
    }
}
