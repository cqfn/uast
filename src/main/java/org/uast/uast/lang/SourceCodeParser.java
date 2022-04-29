/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.lang;

import java.io.IOException;
import org.uast.uast.CodeHandler;
import org.uast.uast.base.EmptyTree;
import org.uast.uast.base.Node;
import org.uast.uast.handlers.json.JsonDeserializer;
import org.uast.uast.lang.java.JavaParser;
import org.uast.uast.utils.FilesReader;

/**
 * Parses source code into unified syntax tree.
 *
 * @since 1.0
 */
public class SourceCodeParser {
    /**
     * The name of the file that contains source code.
     */
    private final String filename;

    /**
     * Constructor.
     * @param filename The name of the file that contains source code
     */
    public SourceCodeParser(final String filename) {
        this.filename = filename;
    }

    /**
     * Parses source code.
     * @param language The name of the programming language
     * @return Root node of the unified syntax tree
     * @throws IOException In case if impossible to read source code
     */
    public Node parse(final String language) throws IOException {
        Node node = EmptyTree.INSTANCE;
        final String code = new FilesReader(filename).readAsString();
        switch (language) {
            case "java":
                final JavaParser parser = new JavaParser(code);
                node = parser.parse();
                break;
            case "py":
                new CodeHandler(code).processPythonCode();
                break;
            case "js":
                new CodeHandler(code).processJavaScriptCode();
                break;
            case "json":
                final JsonDeserializer deserializer = new JsonDeserializer(code);
                node = deserializer.convert();
                break;
            default:
                break;
        }
        return node;
    }
}
