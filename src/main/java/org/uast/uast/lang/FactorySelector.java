/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.lang;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import org.uast.uast.base.Factory;
import org.uast.uast.example.green.GreenFactory;
import org.uast.uast.generated.tree.java.JavaFactory;
import org.uast.uast.generated.tree.js.JsFactory;

/**
 * Selects a suitable factory for the specified programming language.
 *
 * @since 1.0
 */
public final class FactorySelector {
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
        return map;
    }
}
