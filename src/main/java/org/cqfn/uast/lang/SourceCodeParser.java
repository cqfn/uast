/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022 Ivan Kniazkov
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

import java.io.IOException;
import org.cqfn.astranaut.core.EmptyTree;
import org.cqfn.astranaut.core.Node;
import org.cqfn.astranaut.core.utils.FilesReader;
import org.cqfn.astranaut.core.utils.JsonDeserializer;
import org.cqfn.uast.lang.java.JavaParser;
import org.cqfn.uast.lang.javascript.JavaScriptParser;
import org.cqfn.uast.lang.python.PythonParser;

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
     * @param unified Convert to unified syntax tree
     * @param antlr Retain the original ANTLR syntax tree
     * @return Root node of the [unified] syntax tree
     * @throws IOException In case if impossible to read source code
     */
    public Node parse(final String language, final boolean unified, final boolean antlr)
        throws IOException {
        Node node = EmptyTree.INSTANCE;
        final String code = new FilesReader(this.filename).readAsString();
        switch (language) {
            case "java":
                final JavaParser jap = new JavaParser(code);
                node = jap.parse(unified);
                break;
            case "py":
                final PythonParser pyp = new PythonParser(code, antlr);
                node = pyp.parse(unified);
                break;
            case "js":
                final JavaScriptParser jsp = new JavaScriptParser(code, antlr);
                node = jsp.parse(unified);
                break;
            case "json":
                final JsonDeserializer deserializer =
                    new JsonDeserializer(code, FactorySelector.INSTANCE);
                node = deserializer.convert();
                break;
            default:
                break;
        }
        return node;
    }
}
