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
 * Block of source code. It consists of source code lines and nested blocks.
 * Nested blocks are printed with an indentation.
 *
 * @since 0.1
 */
public final class CodeBlock implements Code {
    /**
     * Nested items.
     */
    private final List<Code> items;

    /**
     * Constructor.
     */
    CodeBlock() {
        this.items = new ArrayList<>(0);
    }

    /**
     * Adds a single line to the block of code.
     * @param text Text
     */
    public void addLine(final String text) {
        this.items.add(new Line(text));
    }

    /**
     * Creates and adds an empty block of code to be printed indented.
     * @return Created block
     */
    public CodeBlock createIndentedBlock() {
        final CodeBlock block = new CodeBlock();
        this.items.add(block);
        return block;
    }

    @Override
    public void print(final StringBuilder builder, final int indent) {
        for (final Code item : this.items) {
            item.print(builder, indent + 1);
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        this.print(builder, -1);
        return builder.toString();
    }
}
