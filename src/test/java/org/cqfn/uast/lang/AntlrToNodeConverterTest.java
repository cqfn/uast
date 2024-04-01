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

import antlr4.JavaScriptLexer;
import antlr4.JavaScriptParser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.cqfn.astranaut.core.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for {@link AntlrToNodeConverter} class.
 *
 * @since 0.1
 */
class AntlrToNodeConverterTest {
    /**
     * Parsing JavaScript source.
     */
    @Test
    void jsSource() {
        boolean oops = false;
        final String code = "function sum(a, b){\n\treturn a + b;\n}";
        final InputStream input = new ByteArrayInputStream(
            code.getBytes(StandardCharsets.UTF_8)
        );
        try {
            final CharStream stream = CharStreams.fromStream(input, StandardCharsets.UTF_8);
            final JavaScriptLexer lexer = new JavaScriptLexer(stream);
            final CommonTokenStream tokens = new CommonTokenStream(lexer);
            final JavaScriptParser parser = new JavaScriptParser(tokens);
            final AntlrToNodeConverter converter = new AntlrToNodeConverter(parser, false);
            final Node node = converter.convert(parser.program());
            Assertions.assertEquals(node.getType().getName(), "program");
        } catch (final IOException exception) {
            oops = true;
        }
        Assertions.assertFalse(oops);
    }
}
