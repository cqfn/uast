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

import java.io.File;
import java.io.IOException;
import org.cqfn.astranaut.core.EmptyTree;
import org.cqfn.astranaut.core.Node;
import org.cqfn.astranaut.core.Type;
import org.cqfn.astranaut.core.utils.FilesReader;
import org.cqfn.uast.lang.java.JavaParser;
import org.cqfn.uast.lang.javascript.JavaScriptParser;
import org.cqfn.uast.lang.python.PythonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for unification rules.
 *
 * @since 0.1
 */
class UnificationTest {
    /**
     * The folder with test resources.
     */
    private static final String TESTS_PATH = "src/test/resources/unification/";

    /**
     * Test unification of language constructions.
     */
    @Test
    void testConstructions() {
        final File[] dirs = new File(UnificationTest.TESTS_PATH).listFiles(File::isDirectory);
        Assertions.assertNotNull(dirs);
        for (final File dir : dirs) {
            final File[] files = dir.listFiles();
            Assertions.assertNotNull(files);
            for (final File file : files) {
                final Node tree = this.parseFile(file);
                Assertions.assertTrue(this.hasColor(tree), file.getName());
            }
        }
    }

    /**
     * Checks if a root of the tree has a color - red or green.
     * @param tree The root node of the tree
     * @return The result, @code{true} if the root has a color
     */
    private boolean hasColor(final Node tree) {
        final Type type = tree.getType();
        final String color = type.getProperty("color");
        return !color.isEmpty();
    }

    /**
     * Parses source code.
     * @param file The file with source code on some language
     * @return The created tree
     */
    private Node parseFile(final File file) {
        final String extension = file.getPath().substring(file.getPath().lastIndexOf('.') + 1);
        boolean oops = false;
        String code = "";
        try {
            code = new FilesReader(file.getAbsolutePath()).readAsString();
        } catch (final IOException exception) {
            oops = true;
        }
        Assertions.assertFalse(oops);
        Node node = EmptyTree.INSTANCE;
        switch (extension) {
            case "java":
                final JavaParser jap = new JavaParser(code);
                node = jap.parse(true);
                break;
            case "py":
                final PythonParser pyp = new PythonParser(code, false);
                node = pyp.parse(true);
                break;
            case "js":
                final JavaScriptParser jsp = new JavaScriptParser(code, false);
                node = jsp.parse(true);
                break;
            default:
                break;
        }
        return node;
    }
}
