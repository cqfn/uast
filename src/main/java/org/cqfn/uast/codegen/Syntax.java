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
package org.cqfn.uast.codegen;

import java.util.Map;
import org.cqfn.astranaut.core.Node;

/**
 * Programming language syntax.
 * Contains generators for all types of nodes.
 *
 * @since 0.1
 */
public abstract class Syntax {
    /**
     * Code block generators.
     */
    private final Map<String, BaseBlockGenerator> cbgen;

    /**
     * Line generators.
     */
    private final Map<String, BaseLineGenerator> lgen;

    /**
     * Constructor.
     */
    public Syntax() {
        this.cbgen = this.initBlockGenerators();
        this.lgen = this.initLineGenerators();
    }

    /**
     * Generates a block of code for a node.
     * @param node Node
     * @param code Code block in which to add lines of code and nested blocks
     */
    public void generateBlock(final Node node, final CodeBlock code) {
        final BaseBlockGenerator generator = this.cbgen.get(node.getTypeName());
        assert generator != null;
        generator.exec(node, code, this);
    }

    /**
     * Generates some source code for node.
     * @param node Node
     * @return Generated source code.
     */
    public String getCode(final Node node) {
        final String code;
        final BaseLineGenerator generator = this.lgen.get(node.getTypeName());
        if (generator == null) {
            final CodeBlock block = new CodeBlock();
            this.generateBlock(node, block);
            final StringBuilder builder = new StringBuilder();
            block.print(builder, -1);
            code = builder.toString();
        } else {
            code = generator.exec(node, this);
        }
        return code;
    }

    /**
     * Initializes block generators for different types of nodes.
     * @return A collection of block generators for different types of nodes.
     */
    public abstract Map<String, BaseBlockGenerator> initBlockGenerators();

    /**
     * Initializes line generators for different types of nodes.
     * @return A collection of line generators for different types of nodes.
     */
    public abstract Map<String, BaseLineGenerator> initLineGenerators();
}
