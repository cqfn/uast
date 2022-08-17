/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.js;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.uast.uast.base.Adapter;
import org.uast.uast.base.Converter;

/**
 * Adapter that converts syntax trees, prepared by the parser of the Js language.
 *
 * @since 1.0
 */
public final class JsAdapter extends Adapter {
    /**
     * The instance.
     */
    public static final Adapter INSTANCE = new JsAdapter();

    /**
     * The number of rules.
     */
    private static final int RULES_NUM = 141;

    /**
     * The prefix of a rule class name.
     */
    private static final String PREFIX = "org.uast.uast.generated.tree.js.rules.Rule";

    /**
     * The name of the rule instance field.
     */
    private static final String RULE_INSTANCE = "INSTANCE";

    /**
     * Constructor.
     */
    private JsAdapter() {
        super(Collections.unmodifiableList(JsAdapter.init()), JsFactory.INSTANCE);
    }

    /**
     * Initializes the list of node converters.
     * @return The list of node converters
     */
    private static List<Converter> init() {
        final List<Converter> rules = new ArrayList<>(JsAdapter.RULES_NUM);
        for (int index = 0; index < JsAdapter.RULES_NUM; index = index + 1) {
            final String name = JsAdapter.PREFIX
                .concat(Integer.toString(index));
            try {
                final Class<?> cls = Class.forName(name);
                final Field instance = cls.getField(JsAdapter.RULE_INSTANCE);
                rules.add((Converter) instance.get(null));
            } catch (final ClassNotFoundException | IllegalAccessException
                | NoSuchFieldException ignored) {
                continue;
            }
        }
        return rules;
    }
}
