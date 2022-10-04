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
package org.cqfn.uast.lang.java.gen;

import org.cqfn.astranaut.core.Node;
import org.cqfn.uast.lang.java.JavaParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Java expression generator.
 *
 * @since 0.1.2
 */
class CodeGeneratorTest {
    /**
     * Testing generator for class and field declaration.
     */
    @Test
    void classAndFieldDeclaration() {
        final String code =
            "public class Test {\n    public int x;\n}\n";
        final String result = CodeGeneratorTest.parseAndGenerate(code);
        Assertions.assertEquals(code, result);
    }

    /**
     * Test that parses Java source code and then generate it again.
     * @param source Java source code
     * @return Generated source code
     */
    private static String parseAndGenerate(final String source) {
        final JavaParser parser = new JavaParser(source);
        final Node root = parser.parse(true);
        final CodeBuilder builder = new CodeBuilder();
        final CodeGenerator generator = new CodeGenerator(builder);
        generator.generate(root);
        return builder.toString();
    }
}
