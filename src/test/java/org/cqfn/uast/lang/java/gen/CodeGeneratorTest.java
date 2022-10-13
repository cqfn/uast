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

import java.io.IOException;
import org.cqfn.astranaut.core.Node;
import org.cqfn.astranaut.core.utils.FilesReader;
import org.cqfn.uast.lang.java.JavaParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Java expression generator.
 *
 * @since 0.1.2
 */
@SuppressWarnings("PMD.TooManyMethods")
class CodeGeneratorTest {
    /**
     * The folder with test resources.
     */
    private static final String TESTS_PATH = "src/test/resources/unification/";

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
     * Testing generator for class declaration with extended class.
     */
    @Test
    void classWithExtendsBlockDeclaration() {
        final String code =
            "public class Test extends Other {\n}\n";
        final String result = CodeGeneratorTest.parseAndGenerate(code);
        Assertions.assertEquals(code, result);
    }

    /**
     * Testing generator for class declaration with implemented class.
     */
    @Test
    void classWithImplementsBlockDeclaration() {
        final String code =
            "public class Test implements Base {\n}\n";
        final String result = CodeGeneratorTest.parseAndGenerate(code);
        Assertions.assertEquals(code, result);
    }

    /**
     * Testing generator for function declaration with no arguments inside a class.
     */
    @Test
    void classWithFunctionDeclarationAndZeroArgument() {
        final String code = this.readTest("function_declaration/FunctionZeroArguments.java");
        final String result = CodeGeneratorTest.parseAndGenerate(code);
        Assertions.assertEquals(code, result);
    }

    /**
     * Testing generator for function declaration with one argument inside a class.
     */
    @Test
    void classWithFunctionDeclarationAndOneArgument() {
        final String code = this.readTest("function_declaration/FunctionOneArgument.java");
        final String result = CodeGeneratorTest.parseAndGenerate(code);
        Assertions.assertEquals(code, result);
    }

    /**
     * Testing generator for function declaration with three arguments inside a class.
     */
    @Test
    void classWithFunctionDeclarationAndThreeArguments() {
        final String code = this.readTest("function_declaration/FunctionThreeArguments.java");
        final String result = CodeGeneratorTest.parseAndGenerate(code);
        Assertions.assertEquals(code, result);
    }

    /**
     * Testing generator for variable declaration.
     */
    @Test
    void variableDeclaration() {
        final String code = this.readTest("variable_declaration/VariableDeclaration.java");
        final String result = CodeGeneratorTest.parseAndGenerate(code);
        Assertions.assertEquals(code, result);
    }

    /**
     * Testing generator for synchronized statement.
     */
    @Test
    void synchronizedStatement() {
        final String code = this.readTest("synchronized/Synchronized.java");
        final String result = CodeGeneratorTest.parseAndGenerate(code);
        Assertions.assertEquals(code, result);
    }

    /**
     * Testing generator for complex math library case.
     */
    @Test
    void mathLibCase() {
        final String code = this.readTest("math_library/Abs.java");
        final String result = CodeGeneratorTest.parseAndGenerate(code);
        Assertions.assertEquals(code, result);
    }

    /**
     * Testing generator for complex carrying case.
     */
    @Test
    void carryingCase() {
        final String code = this.readTest("carrying/Carrying.java");
        final String result = CodeGeneratorTest.parseAndGenerate(code);
        Assertions.assertEquals(code, result);
    }

    /**
     * Testing generator for printing to console case.
     */
    @Test
    void printToConsoleCase() {
        final String code = this.readTest("print_to_console/Print.java");
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

    /**
     * Reads test source from the file.
     * @param name The file name
     * @return Test source
     */
    private String readTest(final String name) {
        String result = "";
        boolean oops = false;
        try {
            result = new FilesReader(CodeGeneratorTest.TESTS_PATH.concat(name))
                .readAsString();
        } catch (final IOException ignored) {
            oops = true;
        }
        Assertions.assertFalse(oops);
        return result.concat("\n");
    }
}
