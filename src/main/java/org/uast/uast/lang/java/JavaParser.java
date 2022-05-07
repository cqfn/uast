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
     * Parses Java source code to [unified] syntax tree.
     * @param unified Convert into unified syntax tree after parsing
     * @return Root node
     */
    public Node parse(final boolean unified) {
        final Node result;
        final CompilationUnit raw = StaticJavaParser.parse(this.source);
        final JavaRawToNodeConverter converter = new JavaRawToNodeConverter();
        final Node draft = converter.convert(raw);
        if (unified) {
            result = JavaAdapter.INSTANCE.convert(draft);
        } else {
            result = draft;
        }
        return result;
    }
}
