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
import java.util.Map;
import java.util.TreeMap;
import org.cqfn.astranaut.core.Factory;
import org.cqfn.uast.tree.green.GreenFactory;
import org.cqfn.uast.tree.java.JavaFactory;
import org.cqfn.uast.tree.js.JsFactory;
import org.cqfn.uast.tree.python.PythonFactory;

/**
 * Selects a suitable factory for the specified programming language.
 *
 * @since 0.1
 */
public final class FactorySelector implements org.cqfn.astranaut.core.FactorySelector {
    /**
     * The instance.
     */
    public static final FactorySelector INSTANCE = new FactorySelector();

    /**
     * The factory collection.
     */
    private final Map<String, Factory> map;

    /**
     * Constructor.
     */
    private FactorySelector() {
        this.map = Collections.unmodifiableMap(FactorySelector.init());
    }

    /**
     * Selects a suitable factory for the specified programming language.
     * @param language The language name
     * @return A suitable factory
     */
    public Factory select(final String language) {
        return this.map.getOrDefault(language, GreenFactory.INSTANCE);
    }

    /**
     * Initialises the map.
     * @return The factory collection.
     */
    private static Map<String, Factory> init() {
        final Map<String, Factory> map = new TreeMap<>();
        map.put("java", JavaFactory.INSTANCE);
        map.put("js", JsFactory.INSTANCE);
        map.put("python", PythonFactory.INSTANCE);
        return map;
    }
}
