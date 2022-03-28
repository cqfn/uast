/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uast.uast.exceptions.VisualizerException;
import org.uast.uast.visualizer.ImageRender;

/**
 * Main class.
 *
 * @since 1.0
 */
public final class Main {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    /**
     * Private constructor.
     */
    private Main() {
    }

    /**
     * The main function. Parses the command line and runs actions.
     *
     * @param args The command-line arguments
     */
    public static void main(final String... args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No action specified.");
        }
        if ("parse-java".equals(args[0])) {
            final CompilationUnit root = StaticJavaParser.parse("class X{void y(){int z;}}");
            root.walk(
                node ->
                LOG.info(
                    String.format(
                        "* node children: %s",
                        node.getChildNodes()
                    )
                )
            );
        }
        if ("parse-python".equals(args[0])) {
            final CodeHandler handler = new CodeHandler("def sum(a, b):\n\treturn a + b");
            handler.processPythonCode();
        }
        if ("parse-js".equals(args[0])) {
            final CodeHandler handler = new CodeHandler("function sum(a, b){\n\treturn a + b;\n}");
            handler.processJavaScriptCode();
        }
        if ("visualize".equals(args[0])) {
            try (InputStream stream = Files.newInputStream(Paths.get("dot/gr.dot"))) {
                final StringBuilder builder = new StringBuilder();
                for (int chr = stream.read(); chr != -1; chr = stream.read()) {
                    builder.append((char) chr);
                }
                final String code = builder.toString();
                final ImageRender visualizer = new ImageRender(code);
                visualizer.render(new File("dot/ex_gr.svg"));
            } catch (final IOException | VisualizerException exception) {
                LOG.error(exception.getMessage());
            }
        }
    }
}
