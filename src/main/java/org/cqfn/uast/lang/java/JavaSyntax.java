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
package org.cqfn.uast.lang.java;

import java.util.Map;
import java.util.TreeMap;
import org.cqfn.astranaut.core.Node;
import org.cqfn.uast.codegen.BaseNodeGen;
import org.cqfn.uast.codegen.NodeGen;
import org.cqfn.uast.codegen.Syntax;
import org.cqfn.uast.tree.green.ClassDeclaration;
import org.cqfn.uast.tree.green.Program;

/**
 * The syntax of Java programming language.
 *
 * @since 0.1
 */
public final class JavaSyntax extends Syntax {
    /**
     * The instance.
     */
    public static final Syntax INSTANCE = new JavaSyntax();

    /**
     * Private constructor.
     */
    private JavaSyntax() {
    }

    @Override
    public Map<String, BaseNodeGen> initGenerators() {
        final Map<String, BaseNodeGen> gen = new TreeMap<>();
        gen.put(
            "Program",
            (NodeGen<Program>) (node, code, syntax) -> {
                for (final Node child : node.getChildrenList()) {
                    syntax.generate(child, code);
                }
            }
        );
        gen.put(
            "ClassDeclaration",
            (NodeGen<ClassDeclaration>) (node, code, syntax) -> {
                final StringBuilder header = new StringBuilder();
                header.append("class ").append(node.getName().getData()).append(" {");
                code.addLine(header.toString());
                code.addLine("}");
            }
        );
        return gen;
    }
}
