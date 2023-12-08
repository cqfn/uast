/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2023 Ivan Kniazkov
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
import org.cqfn.uast.tree.green.ArrayType;
import org.cqfn.uast.tree.green.ClassType;
import org.cqfn.uast.tree.green.Dimension;
import org.cqfn.uast.tree.green.DimensionList;
import org.cqfn.uast.tree.green.Expression;
import org.cqfn.uast.tree.green.TypeName;

/**
 * Code generator for Java type names.
 *
 * @since 0.1.2
 */
public final class TypeNames {
    /**
     * The instance.
     */
    public static final TypeNames INSTANCE = new TypeNames();

    /**
     * Map that contains generators.
     */
    private final Map<String, Generator> map;

    /**
     * Constructor.
     */
    private TypeNames() {
        this.map = Collections.unmodifiableMap(this.init());
    }

    /**
     * Generates source code from expression.
     * @param name The type name
     * @return Java source code
     */
    public String generate(final TypeName name) {
        return this.map.get(name.getTypeName()).generate(name);
    }

    /**
     * Initialises map with generators.
     * @return A map
     */
    private Map<String, Generator> init() {
        final Map<String, Generator> gen = new TreeMap<>();
        gen.put("PrimitiveType", Node::getData);
        gen.put("VoidType", name -> "void");
        gen.put(
            "ArrayType",
            name -> {
                final ArrayType array = (ArrayType) name;
                final StringBuilder code = new StringBuilder();
                code.append(this.generate(array.getBase()));
                final DimensionList dimensions = array.getDimensions();
                for (int index = 0; index < dimensions.getChildCount(); index = index + 1) {
                    final Dimension dimension = dimensions.getDimension(index);
                    code.append('[');
                    final Expression expression = dimension.getExpression();
                    if (expression != null) {
                        code.append(Expressions.INSTANCE.generate(expression));
                    }
                    code.append(']');
                }
                return code.toString();
            }
        );
        gen.put(
            "ClassType",
            name -> {
                final ClassType type = (ClassType) name;
                return Names.INSTANCE.generate(type.getName());
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
         * Generates Java source code for a specific expression.
         * @param name A type name
         * @return Java source code
         */
        String generate(TypeName name);
    }
}
