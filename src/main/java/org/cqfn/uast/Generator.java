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
package org.cqfn.uast;

import org.cqfn.astranaut.core.Node;
import org.cqfn.uast.codegen.Syntax;
import org.cqfn.uast.lang.SyntaxSelector;

/**
 * Generates source code from a unified tree.
 *
 * @since 0.1
 */
public final class Generator {
    /**
     * Root node of the syntax tree.
     */
    private final Node root;

    /**
     * Constructor.
     * @param root Root node of the syntax tree
     */
    public Generator(final Node root) {
        this.root = root;
    }

    /**
     * Generates source code from a unified tree.
     * @param language The name of the programming language
     * @return Source code
     */
    public String generate(final String language) {
        String code = "";
        final Syntax syntax = SyntaxSelector.INSTANCE.select(language);
        if (syntax != null) {
            code = syntax.getCode(this.root);
        }
        return code;
    }
}
