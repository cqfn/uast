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

/**
 * One line of source code.
 *
 * @since 0.1
 */
final class Line implements Code {
    /**
     * One "unit" of indentation.
     */
    private static final String TABULATION = "    ";

    /**
     * Text (program code itself).
     */
    private final String text;

    /**
     * Constructor.
     * @param text Text
     */
    Line(final String text) {
        this.text = text;
    }

    @Override
    public void print(final StringBuilder builder, final int indent) {
        assert indent >= 0;
        for (int index = 0; index < indent; index = indent + 1) {
            builder.append(Line.TABULATION);
        }
        builder.append(this.text).append('\n');
    }

    @Override
    public String toString() {
        return this.text;
    }
}
