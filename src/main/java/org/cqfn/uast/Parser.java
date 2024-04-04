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
package org.cqfn.uast;

import java.io.IOException;
import org.cqfn.astranaut.core.EmptyTree;
import org.cqfn.astranaut.core.Node;
import org.cqfn.astranaut.core.utils.FilesReader;
import org.cqfn.astranaut.core.utils.JsonDeserializer;
import org.cqfn.uast.lang.FactorySelector;
import org.cqfn.uast.lang.java.JavaParser;
import org.cqfn.uast.lang.javascript.JavaScriptParser;
import org.cqfn.uast.lang.python.PythonParser;

/**
 * Parses source code into unified syntax tree.
 *
 * @since 0.1
 */
public final class Parser {
    /**
     * Flag indicating that the resulting tree should be unified.
     */
    private boolean unified;

    /**
     * Constructor.
     */
    public Parser() {
        this.unified = true;
    }

    /**
     * Do not unify the resulting tree.
     * Result will be a tree from the original parser.
     */
    public void doNotUnifyResultingTree() {
        this.unified = false;
    }

    /**
     * Parses source code from string.
     * @param code Source code
     * @param language The name of the programming language
     * @return Root node of the [unified] syntax tree
     */
    public Node parseString(final String code, final String language) {
        Node node = EmptyTree.INSTANCE;
        switch (language) {
            case "java":
                final JavaParser jap = new JavaParser(code);
                node = jap.parse(this.unified);
                break;
            case "python":
            case "py":
                final PythonParser pyp = new PythonParser(code);
                node = pyp.parse(this.unified);
                break;
            case "javascript":
            case "js":
                final JavaScriptParser jsp = new JavaScriptParser(code);
                node = jsp.parse(this.unified);
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

    /**
     * Parses source code from file.
     * @param path Path to file
     * @return Root node of the [unified] syntax tree
     * @throws IOException In case if impossible to read source code
     */
    public Node parseFile(final String path) throws IOException {
        final String code = new FilesReader(path).readAsString();
        return this.parseString(code, Parser.getFileExtension(path));
    }

    /**
     * Parses source code from file.
     * @param path Path to file
     * @param language The name of the programming language
     * @return Root node of the [unified] syntax tree
     * @throws IOException In case if impossible to read source code
     */
    public Node parseFile(final String path, final String language) throws IOException {
        final String code = new FilesReader(path).readAsString();
        return this.parseString(code, language);
    }

    /**
     * Returns the extension (i.e., type) of a file by its full path.
     * Of course, there is a similar function somewhere, but it is defined here
     * so as not to have unnecessary dependencies.
     * @param path Full file name
     * @return File extension
     */
    @SuppressWarnings("PMD.ProhibitPublicStaticMethods")
    public static String getFileExtension(final String path) {
        final int point = path.lastIndexOf('.');
        final String ext;
        if (point < 0) {
            ext = "";
        } else {
            ext = path.substring(point + 1);
        }
        return ext;
    }
}
