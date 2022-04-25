/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.lang.java;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.uast.uast.base.Node;
import org.uast.uast.generated.tree.java.JavaAdapter;

/**
 * Parses Java source code to unified syntax tree.
 *
 * @since 1.0
 */
public class JavaParser {
    /**
     * The source code.
     */
    private final String source;

    /**
     * Constructor.
     * @param source Source string.
     */
    public JavaParser(final String source) {
        this.source = source;
    }

    /**
     * Parses Java source code to unified syntax tree.
     * @return Root node
     */
    public Node parse() {
        final CompilationUnit raw = StaticJavaParser.parse(this.source);
        final RawToNodeConverter converter = new RawToNodeConverter();
        final Node draft = converter.convert(raw);
        return JavaAdapter.INSTANCE.convert(draft);
    }
}
