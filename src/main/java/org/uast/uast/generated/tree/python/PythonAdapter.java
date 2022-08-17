/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.python;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.uast.uast.base.Adapter;
import org.uast.uast.base.Converter;

/**
 * Adapter that converts syntax trees, prepared by the parser of the Python language.
 *
 * @since 1.0
 */
public final class PythonAdapter extends Adapter {
    /**
     * The instance.
     */
    public static final Adapter INSTANCE = new PythonAdapter();

    /**
     * The number of rules.
     */
    private static final int RULES_NUM = 73;

    /**
     * The prefix of a rule class name.
     */
    private static final String PREFIX = "org.uast.uast.generated.tree.python.rules.Rule";

    /**
     * The name of the rule instance field.
     */
    private static final String RULE_INSTANCE = "INSTANCE";

    /**
     * Constructor.
     */
    private PythonAdapter() {
        super(Collections.unmodifiableList(PythonAdapter.init()), PythonFactory.INSTANCE);
    }

    /**
     * Initializes the list of node converters.
     * @return The list of node converters
     */
    private static List<Converter> init() {
        final List<Converter> rules = new ArrayList<>(PythonAdapter.RULES_NUM);
        for (int index = 0; index < PythonAdapter.RULES_NUM; index = index + 1) {
            final String name = PythonAdapter.PREFIX
                .concat(Integer.toString(index));
            try {
                final Class<?> cls = Class.forName(name);
                final Field instance = cls.getField(PythonAdapter.RULE_INSTANCE);
                rules.add((Converter) instance.get(null));
            } catch (final ClassNotFoundException | IllegalAccessException
                | NoSuchFieldException ignored) {
                continue;
            }
        }
        return rules;
    }
}
