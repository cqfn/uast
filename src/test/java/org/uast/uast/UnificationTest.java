/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.uast.uast.base.EmptyTree;
import org.uast.uast.base.Node;
import org.uast.uast.base.Type;
import org.uast.uast.lang.java.JavaParser;
import org.uast.uast.lang.javascript.JavaScriptParser;
import org.uast.uast.lang.python.PythonParser;
import org.uast.uast.utils.FilesReader;

/**
 * Test for unification rules.
 *
 * @since 1.0
 */
public class UnificationTest {
    /**
     * The folder with test resources.
     */
    private static final String TESTS_PATH = "src/test/resources/unification/";

    /**
     * Test unification of language constructions.
     */
    @Test
    public void testConstructions() {
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
