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
package org.cqfn.uast.lang.javascript;

import antlr4.JavaScriptLexer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.cqfn.astranaut.core.EmptyTree;
import org.cqfn.astranaut.core.Node;
import org.cqfn.astranaut.core.algorithms.NodeSelector;
import org.cqfn.astranaut.core.algorithms.Subtree;
import org.cqfn.uast.lang.AntlrToNodeConverter;
import org.cqfn.uast.tree.js.JsAdapter;

/**
 * Parses JavaScript source code to unified syntax tree.
 *
 * @since 0.1
 */
public final class JavaScriptParser {
    /**
     * The source code.
     */
    private final String source;

    /**
     * Constructor.
     * @param source Source string
     */
    public JavaScriptParser(final String source) {
        this.source = source;
    }

    /**
     * Parses Java source code to [unified] syntax tree.
     * @param unified Convert into unified syntax tree after parsing
     * @return Root node
     */
    public Node parse(final boolean unified) {
        final InputStream input = new ByteArrayInputStream(
            this.source.getBytes(StandardCharsets.UTF_8)
        );
        CharStream stream = null;
        Node result;
        try {
            stream = CharStreams.fromStream(input, StandardCharsets.UTF_8);
            final JavaScriptLexer lexer = new JavaScriptLexer(stream);
            final CommonTokenStream tokens = new CommonTokenStream(lexer);
            final antlr4.JavaScriptParser parser =
                new antlr4.JavaScriptParser(tokens);
            final AntlrToNodeConverter converter = new AntlrToNodeConverter(parser);
            final Node draft = converter.convert(parser.program());
            final Node preprocessed = JavaScriptParser.preprocessTree(draft);
            if (unified) {
                result = JsAdapter.INSTANCE.convert(preprocessed);
            } else {
                result = preprocessed;
            }
        } catch (final IOException exception) {
            result = EmptyTree.INSTANCE;
        }
        return result;
    }

    /**
     * Performs preprocessing of the syntax tree.
     * Deletes some nodes (and maybe in the future will do something else).
     * @param draft Draft syntax tree
     * @return Preprocessed syntax tree
     */
    private static Node preprocessTree(final Node draft) {
        final NodeSelector selector = new NodeSelector(draft);
        final NodeSelector.Criteria criteria = (node, iterable) -> {
            final String name = node.getTypeName();
            final String data = node.getData();
            final Set<String> literals = new TreeSet<>(Arrays.asList("{", "}", "<EOF>", "class"));
            return name.equals("literal") && literals.contains(data);
        };
        final Set<Node> excluded = selector.select(criteria);
        final Subtree subtree = new Subtree(draft, Subtree.EXCLUDE);
        return subtree.create(excluded);
    }
}
