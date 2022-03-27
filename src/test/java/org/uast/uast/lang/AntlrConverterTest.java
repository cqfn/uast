/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.lang;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.uast.uast.base.Node;
import org.uast.uast.generated.antlr4.JavaScriptLexer;
import org.uast.uast.generated.antlr4.JavaScriptParser;

/**
 * Test for {@link AntlrConverter} class.
 *
 * @since 1.0
 */
public class AntlrConverterTest {
    /**
     * Parsing JavaScript source.
     */
    @Test
    public void jsSource() {
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
            final AntlrConverter converter = new AntlrConverter(parser);
            final Node node = converter.convert(parser.program());
            Assertions.assertEquals(node.getType().getName(), "program");
        } catch (final IOException exception) {
            oops = true;
        }
        Assertions.assertFalse(oops);
    }
}
