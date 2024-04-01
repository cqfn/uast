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
package org.cqfn.uast.lang.python;

import antlr4.PythonLexer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.cqfn.astranaut.core.EmptyTree;
import org.cqfn.astranaut.core.Node;
import org.cqfn.uast.lang.AntlrToNodeConverter;
import org.cqfn.uast.tree.python.PythonAdapter;

/**
 * Parses Python source code to unified syntax tree.
 *
 * @since 0.1
 */
public class PythonParser {
    /**
     * Retain the initial ANTLR tree.
     */
    private final boolean initial;

    /**
     * The source code.
     */
    private final String source;

    /**
     * Constructor.
     * @param source Source string
     * @param initial If {@code true}, retain the initial ANTLR syntax tree
     */
    public PythonParser(final String source, final boolean initial) {
        this.source = source;
        this.initial = initial;
    }

    /**
     * Parses Java source code to unified syntax tree.
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
            final PythonLexer lexer = new PythonLexer(stream);
            final CommonTokenStream tokens = new CommonTokenStream(lexer);
            final antlr4.PythonParser parser =
                new antlr4.PythonParser(tokens);
            final AntlrToNodeConverter converter = new AntlrToNodeConverter(parser, this.initial);
            final Node draft = converter.convert(parser.file_input());
            if (unified) {
                result = PythonAdapter.INSTANCE.convert(draft);
            } else {
                result = draft;
            }
        } catch (final IOException exception) {
            result = EmptyTree.INSTANCE;
        }
        return result;
    }
}
