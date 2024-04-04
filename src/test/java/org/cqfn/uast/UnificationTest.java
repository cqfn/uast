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
import java.util.Set;
import java.util.TreeSet;
import org.cqfn.astranaut.core.Node;
import org.cqfn.astranaut.core.utils.FilesReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test covering parsing of various syntactic constructs into a unified tree
 * and reverse source code generation.
 *
 * @since 0.1
 */
class UnificationTest {
    /**
     * Root folder containing test files.
     */
    private static final String ROOT_FOLDER = "src/test/resources/unification/";

    @Test
    void test() {
        final Set<String> set = new TreeSet<>();
        UnificationTest.collectTests(new File(UnificationTest.ROOT_FOLDER), set);
        for (final String file : set) {
            boolean oops = false;
            try {
                final Parser parser = new Parser(file);
                final String lang = file.substring(file.lastIndexOf('.') + 1);
                final Node root = parser.parse(lang);
                Assertions.assertNotNull(root);
                Assertions.assertEquals("green", root.getType().getProperty("color"));
                final Generator generator = new Generator(root);
                final String generated = generator.generate(lang);
                final String source = new FilesReader(file).readAsString();
                Assertions.assertEquals(source, generated, file);
            } catch (final IOException ignored) {
                oops = true;
            }
            Assertions.assertFalse(oops);
        }
    }

    /**
     * Collects source code files from all folders.
     * @param dir Folder containing test files and sub-folders
     * @param set Resulting set containing file names
     */
    private static void collectTests(final File dir, final Set<String> set) {
        assert dir.isDirectory();
        final File[] list = dir.listFiles();
        if (list == null)  {
            return;
        }
        for (final File file : list) {
            if (file.isDirectory()) {
                UnificationTest.collectTests(file, set);
            } else {
                set.add(file.getAbsolutePath());
            }
        }
    }
}
