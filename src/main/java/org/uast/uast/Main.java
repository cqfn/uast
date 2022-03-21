/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import java.util.logging.Logger;

/**
 * Main class.
 *
 * @since 1.0
 */
public final class Main {
    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(Main.class.getName());

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
            final Node.BreadthFirstIterator iterator = new Node.BreadthFirstIterator(root);
            while (iterator.hasNext()) {
                LOG.info(String.format("* %s",  iterator.next().toString()));
            }
            root.stream(Node.TreeTraversal.PREORDER).forEach(
                node ->
                LOG.info(
                    String.format(
                        "* type: %s fragment: %s",
                        node.getClass().getSimpleName(), node
                    )
                )
            );
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
    }
}
