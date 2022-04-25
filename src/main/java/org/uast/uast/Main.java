/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast;

import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uast.uast.exceptions.VisualizerException;
import org.uast.uast.lang.java.JavaParser;
import org.uast.uast.visualizer.AstVisualizer;

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
            final AstVisualizer visualizer = new AstVisualizer(
                new JavaParser("class X{int calc(){return 2 + 3;}}").parse()
            );
            try {
                visualizer.visualize(new File(args[1]));
            } catch (final IOException | VisualizerException exception) {
                LOG.error(exception.getMessage());
            }
        }
        if ("parse-python".equals(args[0])) {
            final CodeHandler handler = new CodeHandler("def sum(a, b):\n\treturn a + b");
            handler.processPythonCode();
        }
        if ("parse-js".equals(args[0])) {
            final CodeHandler handler = new CodeHandler("function sum(a, b){\n\treturn a + b;\n}");
            handler.processJavaScriptCode();
        }
    }
}
