/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.java;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.uast.uast.base.Adapter;
import org.uast.uast.base.Converter;

/**
 * Adapter that converts syntax trees, prepared by the parser of the Java language.
 *
 * @since 1.0
 */
public final class JavaAdapter extends Adapter {
    /**
     * The instance.
     */
    public static final Adapter INSTANCE = new JavaAdapter();

    /**
     * The number of rules.
     */
    private static final int RULES_NUM = 147;

    /**
     * The prefix of a rule class name.
     */
    private static final String PREFIX = "org.uast.uast.generated.tree.java.rules.Rule";

    /**
     * The name of the rule instance field.
     */
    private static final String RULE_INSTANCE = "INSTANCE";

    /**
     * Constructor.
     */
    private JavaAdapter() {
        super(Collections.unmodifiableList(JavaAdapter.init()), JavaFactory.INSTANCE);
    }

    /**
     * Initializes the list of node converters.
     * @return The list of node converters
     */
    private static List<Converter> init() {
        final List<Converter> rules = new ArrayList<>(JavaAdapter.RULES_NUM);
        for (int index = 0; index < JavaAdapter.RULES_NUM; index = index + 1) {
            final String name = JavaAdapter.PREFIX
                .concat(Integer.toString(index));
            try {
                final Class<?> cls = Class.forName(name);
                final Field instance = cls.getField(JavaAdapter.RULE_INSTANCE);
                rules.add((Converter) instance.get(null));
            } catch (final ClassNotFoundException | IllegalAccessException
                | NoSuchFieldException ignored) {
                continue;
            }
        }
        return rules;
    }
}
