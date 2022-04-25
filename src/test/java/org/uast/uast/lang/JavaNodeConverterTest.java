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
import org.uast.uast.lang.java.JavaRawToNodeConverter;

/**
 * Test for {@link JavaRawToNodeConverter} class.
 *
 * @since 1.0
 */
public class JavaNodeConverterTest {
    /**
     * Parsing Java source.
     */
    @Test
    public void javaSource() {
        final String code = "class X{void y(){int z;}}";
        final CompilationUnit root = StaticJavaParser.parse(code);
        final JavaRawToNodeConverter converter = new JavaRawToNodeConverter();
        final Node node = converter.convert(root);
        Assertions.assertEquals(node.getType().getName(), "CompilationUnit");
    }
}
