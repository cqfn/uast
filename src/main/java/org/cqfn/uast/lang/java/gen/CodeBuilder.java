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

/**
 * Source code builder.
 *
 * @since 0.1.2
 */
@SuppressWarnings("PMD.AvoidStringBufferField")
public class CodeBuilder {
    /**
     * Tabulation symbols.
     */
    private static final String TABULATION = "    ";

    /**
     * String builder that contains the source code.
     */
    private final StringBuilder code;

    /**
     * Current code indent from the beginning of the line.
     */
    private int indent;

    /**
     * Constructor.
     */
    public CodeBuilder() {
        this.code = new StringBuilder();
        this.indent = 0;
    }

    /**
     * Increase code indent from the beginning of the line.
     */
    public void increaseIndent() {
        this.indent = this.indent + 1;
    }

    /**
     * Decrease code indent from the beginning of the line.
     */
    public void decreaseIndent() {
        assert this.indent > 0;
        this.indent = this.indent - 1;
    }

    /**
     * Prints a line of code.
     * @param line Line of code
     */
    public void print(final String line) {
        for (int counter = 0; counter < this.indent; counter = counter + 1) {
            this.code.append(CodeBuilder.TABULATION);
        }
        this.code.append(line).append('\n');
    }

    @Override
    public final String toString() {
        return this.code.toString();
    }
}
