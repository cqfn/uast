/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.lang;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.uast.uast.base.Node;

/**
 * Test for {@link JavaParserConverter} class.
 *
 * @since 1.0
 */
public class JavaParserConverterTest {
    /**
     * Parsing Java source.
     */
    @Test
    public void javaSource() {
        final String code = "class X{void y(){int z;}}";
        final CompilationUnit root = StaticJavaParser.parse(code);
        final JavaParserConverter converter = new JavaParserConverter();
        final Node node = converter.convert(root);
        Assertions.assertEquals(node.getType().getName(), "CompilationUnit");
    }
}
