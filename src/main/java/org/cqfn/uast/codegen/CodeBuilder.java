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

import java.util.ArrayList;
import java.util.List;

/**
 * Class that builds source code.
 *
 * @since 0.1
 */
public final class CodeBuilder {
    /**
     * One "unit" of indentation.
     */
    private static final String TABULATION = "    ";

    /**
     * A set of lines forming the source code.
     */
    private final List<Line> lines;

    /**
     * Constructor.
     */
    public CodeBuilder() {
        this.lines = new ArrayList<>(128);
    }

    /**
     * Adds one line of code.
     * @param indent Indent from the beginning of the line
     * @param text The actual program text
     */
    public void add(final int indent, final String text) {
        this.lines.add(new Line(indent, text));
    }

    /**
     * Generates the source code of the program.
     * @return Source code
     */
    public String generate() {
        final StringBuilder code = new StringBuilder();
        for (final Line line : this.lines) {
            line.append(code);
        }
        return code.toString();
    }

    /**
     * One line of source code.
     *
     * @since 0.1
     */
    private static final class Line {
        /**
         * Indent from the beginning of the line.
         */
        private final int indent;

        /**
         * The actual program text.
         */
        private final String text;

        /**
         * Constructor.
         * @param indent Indentation
         * @param text Program text
         */
        private Line(final int indent, final String text) {
            this.indent = indent;
            this.text = text;
        }

        @Override
        public String toString() {
            return this.text;
        }

        /**
         * Appends the line to the source code.
         * @param code Source code builder
         */
        void append(final StringBuilder code) {
            for (int index = 0; index < this.indent; index = index + 1) {
                code.append(CodeBuilder.TABULATION);
            }
            code.append(this.text).append('\n');
        }
    }
}
