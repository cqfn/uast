/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.js;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.uast.uast.base.Adapter;
import org.uast.uast.base.Converter;
import org.uast.uast.generated.tree.js.rules.Rule0;
import org.uast.uast.generated.tree.js.rules.Rule1;
import org.uast.uast.generated.tree.js.rules.Rule2;
import org.uast.uast.generated.tree.js.rules.Rule3;
import org.uast.uast.generated.tree.js.rules.Rule4;
import org.uast.uast.generated.tree.js.rules.Rule5;
import org.uast.uast.generated.tree.js.rules.Rule6;
import org.uast.uast.generated.tree.js.rules.Rule7;
import org.uast.uast.generated.tree.js.rules.Rule8;
import org.uast.uast.generated.tree.js.rules.Rule9;

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
        return Arrays.asList(
            Rule0.INSTANCE,
            Rule1.INSTANCE,
            Rule2.INSTANCE,
            Rule3.INSTANCE,
            Rule4.INSTANCE,
            Rule5.INSTANCE,
            Rule6.INSTANCE,
            Rule7.INSTANCE,
            Rule8.INSTANCE,
            Rule9.INSTANCE
        );
    }
}
