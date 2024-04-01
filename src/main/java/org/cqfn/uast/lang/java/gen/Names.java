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
package org.cqfn.uast.lang.java.gen;

import org.cqfn.uast.tree.green.Name;

/**
 * Code generator for Java names.
 *
 * @since 0.1.2
 */
public final class Names {
    /**
     * The instance.
     */
    public static final Names INSTANCE = new Names();

    /**
     * Constructor.
     */
    private Names() {
    }

    /**
     * Generates source code from Java name.
     * @param name A name
     * @return Source code
     */
    public String generate(final Name name) {
        final Name composition = name.getComposition();
        String left = "";
        if (composition != null) {
            left = this.generate(composition);
        }
        final String right = name.getLast().getData();
        final String result;
        if (left.isEmpty()) {
            result = right;
        } else {
            result = String.format("%s.%s", left, right);
        }
        return result;
    }
}
