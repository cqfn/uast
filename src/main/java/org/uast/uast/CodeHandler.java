/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uast.uast.generated.antlr4.JavaScriptLexer;
import org.uast.uast.generated.antlr4.JavaScriptParser;
import org.uast.uast.generated.antlr4.Python3Lexer;
import org.uast.uast.generated.antlr4.Python3Parser;
import org.uast.uast.visualizer.AstPrinter;

/**
 * Handler of source code.
 *
 * @since 1.0
 */
public class CodeHandler {
    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(CodeHandler.class.getName());

    /**
     * Source code to be processed.
     */
    private final String code;

    /**
     * Constructor.
     *
     * @param code Source code to be processed
     */
    public CodeHandler(final String code) {
        this.code = code;
    }

    /**
     * Processes source code in Python.
     */
    public void processPythonCode() {
        final InputStream input = new ByteArrayInputStream(
            this.code.getBytes(StandardCharsets.UTF_8)
        );
        CharStream stream = null;
        try {
            stream = CharStreams.fromStream(input, StandardCharsets.UTF_8);
        } catch (final IOException exception) {
            LOG.severe("Unable to handle Python input data!");
        }
        final Python3Lexer lexer = new Python3Lexer(stream);
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        final Python3Parser parser = new Python3Parser(tokens);
        final AstPrinter printer = new AstPrinter(parser);
        printer.print(parser.file_input());
    }

    /**
     * Processes source code in JavaScript.
     */
    public void processJavaScriptCode() {
        final InputStream input = new ByteArrayInputStream(
            this.code.getBytes(StandardCharsets.UTF_8)
        );
        CharStream stream = null;
        try {
            stream = CharStreams.fromStream(input, StandardCharsets.UTF_8);
        } catch (final IOException exception) {
            LOG.severe("Unable to handle JavaScript input data!");
        }
        final JavaScriptLexer lexer = new JavaScriptLexer(stream);
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        final JavaScriptParser parser = new JavaScriptParser(tokens);
        final AstPrinter printer = new AstPrinter(parser);
        printer.print(parser.program());
    }
}
