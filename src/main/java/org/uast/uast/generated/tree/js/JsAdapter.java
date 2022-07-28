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
import org.uast.uast.generated.tree.js.rules.Rule10;
import org.uast.uast.generated.tree.js.rules.Rule11;
import org.uast.uast.generated.tree.js.rules.Rule12;
import org.uast.uast.generated.tree.js.rules.Rule13;
import org.uast.uast.generated.tree.js.rules.Rule14;
import org.uast.uast.generated.tree.js.rules.Rule15;
import org.uast.uast.generated.tree.js.rules.Rule16;
import org.uast.uast.generated.tree.js.rules.Rule17;
import org.uast.uast.generated.tree.js.rules.Rule18;
import org.uast.uast.generated.tree.js.rules.Rule19;
import org.uast.uast.generated.tree.js.rules.Rule2;
import org.uast.uast.generated.tree.js.rules.Rule20;
import org.uast.uast.generated.tree.js.rules.Rule21;
import org.uast.uast.generated.tree.js.rules.Rule22;
import org.uast.uast.generated.tree.js.rules.Rule23;
import org.uast.uast.generated.tree.js.rules.Rule24;
import org.uast.uast.generated.tree.js.rules.Rule25;
import org.uast.uast.generated.tree.js.rules.Rule26;
import org.uast.uast.generated.tree.js.rules.Rule27;
import org.uast.uast.generated.tree.js.rules.Rule28;
import org.uast.uast.generated.tree.js.rules.Rule29;
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
            Rule9.INSTANCE,
            Rule10.INSTANCE,
            Rule11.INSTANCE,
            Rule12.INSTANCE,
            Rule13.INSTANCE,
            Rule14.INSTANCE,
            Rule15.INSTANCE,
            Rule16.INSTANCE,
            Rule17.INSTANCE,
            Rule18.INSTANCE,
            Rule19.INSTANCE,
            Rule20.INSTANCE,
            Rule21.INSTANCE,
            Rule22.INSTANCE,
            Rule23.INSTANCE,
            Rule24.INSTANCE,
            Rule25.INSTANCE,
            Rule26.INSTANCE,
            Rule27.INSTANCE,
            Rule28.INSTANCE,
            Rule29.INSTANCE
        );
    }
}
