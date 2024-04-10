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
package org.cqfn.uast.lang.javascript;

import java.util.Map;
import org.cqfn.uast.codegen.BaseBlockGenerator;
import org.cqfn.uast.codegen.BaseLineGenerator;
import org.cqfn.uast.codegen.BlockGenerator;
import org.cqfn.uast.codegen.CodeBlock;
import org.cqfn.uast.codegen.Syntax;
import org.cqfn.uast.lang.green.CommonSyntax;
import org.cqfn.uast.tree.green.ClassDeclaration;

/**
 * The syntax of JavaScript programming language.
 *
 * @since 0.1
 */
public final class JavaScriptSyntax extends CommonSyntax {
    /**
     * The instance.
     */
    public static final Syntax INSTANCE = new JavaScriptSyntax();

    /**
     * Private constructor.
     */
    private JavaScriptSyntax() {
    }

    @Override
    public Map<String, BaseBlockGenerator> initBlockGenerators() {
        final Map<String, BaseBlockGenerator> gen = this.initCommonBlockGenerators();
        gen.put(
            "ClassDeclaration",
            (BlockGenerator<ClassDeclaration>) (node, code, syntax) -> {
                final StringBuilder header = new StringBuilder();
                header.append("class ").append(node.getName().getData()).append(" {");
                code.addLine(header.toString());
                if (node.getBody() != null) {
                    final CodeBlock body = code.createIndentedBlock();
                    syntax.generateBlock(node.getBody(), body);
                }
                code.addLine("}");
            }
        );
        return gen;
    }

    @Override
    public Map<String, BaseLineGenerator> initLineGenerators() {
        return CommonSyntax.initCommonLineGenerators();
    }

    @Override
    public String getStatementSeparator() {
        return ";";
    }
}