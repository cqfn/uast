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

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import org.cqfn.astranaut.core.Node;
import org.cqfn.uast.tree.green.Return;

/**
 * Code generator for Java constructions (except expressions).
 *
 * @since 0.1.2
 */
public class CodeGenerator {
    /**
     * Map that contains generators.
     */
    private final Map<String, Generator> map;

    /**
     * Source code builder.
     */
    private final CodeBuilder builder;

    /**
     * Constructor.
     * @param builder Source code builder
     */
    public CodeGenerator(final CodeBuilder builder) {
        this.map = Collections.unmodifiableMap(this.init());
        this.builder = builder;
    }

    /**
     * Generates source code from node.
     * @param node A node
     */
    public void generate(final Node node) {
        this.map.get(node.getTypeName()).generate(node);
    }

    /**
     * Initialises map with generators.
     * @return A map
     */
    private Map<String, Generator> init() {
        final Map<String, Generator> gen = new TreeMap<>();
        gen.put(
            "Return",
            node -> {
                final Return stmt = (Return) node;
                if (stmt.getChildCount() == 0) {
                    this.builder.print("return;");
                } else {
                    this.builder.print(
                        String.format(
                            "return %s;",
                            Expressions.INSTANCE.generate(stmt.getExpression())
                        )
                    );
                }
            }
        );
        return gen;
    }

    /**
     * Interface for generating Java source code for a specific expression.
     *
     * @since 0.1.2
     */
    private interface Generator {
        /**
         * Generates Java source code for a specific node.
         * @param node A node
         */
        void generate(Node node);
    }
}
