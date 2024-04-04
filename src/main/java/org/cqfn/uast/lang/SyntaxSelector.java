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
package org.cqfn.uast.lang;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import org.cqfn.uast.codegen.Syntax;
import org.cqfn.uast.lang.java.JavaSyntax;
import org.cqfn.uast.lang.javascript.JavaScriptSyntax;
import org.cqfn.uast.lang.python.PythonSyntax;

/**
 * Selects a syntax for the specified programming language.
 *
 * @since 0.1
 */
public final class SyntaxSelector {
    /**
     * The instance.
     */
    public static final SyntaxSelector INSTANCE = new SyntaxSelector();

    /**
     * The syntax collection.
     */
    private final Map<String, Syntax> map;

    /**
     * Constructor.
     */
    private SyntaxSelector() {
        this.map = Collections.unmodifiableMap(SyntaxSelector.init());
    }

    /**
     * Returns the syntax for specified programming language.
     * @param language The language name
     * @return Syntax
     */
    public Syntax select(final String language) {
        return this.map.get(language.toLowerCase(Locale.ENGLISH));
    }

    /**
     * Initialises the map.
     * @return The syntax collection.
     */
    private static Map<String, Syntax> init() {
        final Map<String, Syntax> map = new TreeMap<>();
        map.put("java", JavaSyntax.INSTANCE);
        map.put("javascript", JavaScriptSyntax.INSTANCE);
        map.put("js", JavaScriptSyntax.INSTANCE);
        map.put("python", PythonSyntax.INSTANCE);
        map.put("py", PythonSyntax.INSTANCE);
        return map;
    }
}
