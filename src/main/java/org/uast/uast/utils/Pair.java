/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.utils;

/**
 * Simple pair in case someone needs to use (key, val) objects without creating a map.
 *
 * @param <K> The key type
 * @param <V> The value type
 *
 * @since 0.1.5
 */
public final class Pair<K, V> {
    /**
     * The key.
     */
    private final K key;

    /**
     * The value.
     */
    private final V val;

    /**
     * Constructor.
     * @param key The key
     * @param val The value
     */
    public Pair(final K key, final V val) {
        this.key = key;
        this.val = val;
    }

    /**
     * Returns the key.
     * @return The key
     */
    public K getKey() {
        return this.key;
    }

    /**
     * Returns the value.
     * @return The value
     */
    public V getValue() {
        return this.val;
    }
}
