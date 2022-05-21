/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.python;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.uast.uast.base.Adapter;
import org.uast.uast.base.Converter;
import org.uast.uast.generated.tree.python.rules.Rule0;
import org.uast.uast.generated.tree.python.rules.Rule1;
import org.uast.uast.generated.tree.python.rules.Rule2;
import org.uast.uast.generated.tree.python.rules.Rule3;
import org.uast.uast.generated.tree.python.rules.Rule4;
import org.uast.uast.generated.tree.python.rules.Rule5;
import org.uast.uast.generated.tree.python.rules.Rule6;
import org.uast.uast.generated.tree.python.rules.Rule7;
import org.uast.uast.generated.tree.python.rules.Rule8;

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
        return Arrays.asList(
            Rule0.INSTANCE,
            Rule1.INSTANCE,
            Rule2.INSTANCE,
            Rule3.INSTANCE,
            Rule4.INSTANCE,
            Rule5.INSTANCE,
            Rule6.INSTANCE,
            Rule7.INSTANCE,
            Rule8.INSTANCE
        );
    }
}
