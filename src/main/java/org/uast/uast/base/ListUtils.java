/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Helper class for building lists.
 *
 * @param <T> Item type
 * @since 1.0
 */
public class ListUtils<T> {
    /**
     * List being built.
     */
    private final List<T> result;

    /**
     * Constructor.
     */
    public ListUtils() {
        this.result = new LinkedList<>();
    }

    /**
     * Adds non-null items to the list. Null items are skipped.
     * @param items Items
     * @return Itself
     */
    @SafeVarargs
    public final ListUtils<T> add(final T... items) {
        for (final T item : items) {
            if (item != null) {
                this.result.add(item);
            }
        }
        return this;
    }

    /**
     * Merges another list with the result. Null items are skipped.
     * @param list Another list
     */
    public final void merge(final List<T> list) {
        if (list != null) {
            for (final T item : list) {
                if (item != null) {
                    this.result.add(item);
                }
            }
        }
    }

    /**
     * Builds an unmodifiable list.
     * @return A list
     */
    public List<T> make() {
        return Collections.unmodifiableList(new ArrayList<>(this.result));
    }
}
