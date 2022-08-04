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
import org.uast.uast.generated.tree.python.rules.Rule10;
import org.uast.uast.generated.tree.python.rules.Rule11;
import org.uast.uast.generated.tree.python.rules.Rule12;
import org.uast.uast.generated.tree.python.rules.Rule13;
import org.uast.uast.generated.tree.python.rules.Rule14;
import org.uast.uast.generated.tree.python.rules.Rule15;
import org.uast.uast.generated.tree.python.rules.Rule16;
import org.uast.uast.generated.tree.python.rules.Rule17;
import org.uast.uast.generated.tree.python.rules.Rule18;
import org.uast.uast.generated.tree.python.rules.Rule19;
import org.uast.uast.generated.tree.python.rules.Rule2;
import org.uast.uast.generated.tree.python.rules.Rule20;
import org.uast.uast.generated.tree.python.rules.Rule21;
import org.uast.uast.generated.tree.python.rules.Rule22;
import org.uast.uast.generated.tree.python.rules.Rule23;
import org.uast.uast.generated.tree.python.rules.Rule24;
import org.uast.uast.generated.tree.python.rules.Rule25;
import org.uast.uast.generated.tree.python.rules.Rule26;
import org.uast.uast.generated.tree.python.rules.Rule27;
import org.uast.uast.generated.tree.python.rules.Rule28;
import org.uast.uast.generated.tree.python.rules.Rule29;
import org.uast.uast.generated.tree.python.rules.Rule3;
import org.uast.uast.generated.tree.python.rules.Rule30;
import org.uast.uast.generated.tree.python.rules.Rule31;
import org.uast.uast.generated.tree.python.rules.Rule32;
import org.uast.uast.generated.tree.python.rules.Rule33;
import org.uast.uast.generated.tree.python.rules.Rule34;
import org.uast.uast.generated.tree.python.rules.Rule35;
import org.uast.uast.generated.tree.python.rules.Rule36;
import org.uast.uast.generated.tree.python.rules.Rule37;
import org.uast.uast.generated.tree.python.rules.Rule38;
import org.uast.uast.generated.tree.python.rules.Rule39;
import org.uast.uast.generated.tree.python.rules.Rule4;
import org.uast.uast.generated.tree.python.rules.Rule40;
import org.uast.uast.generated.tree.python.rules.Rule41;
import org.uast.uast.generated.tree.python.rules.Rule42;
import org.uast.uast.generated.tree.python.rules.Rule43;
import org.uast.uast.generated.tree.python.rules.Rule44;
import org.uast.uast.generated.tree.python.rules.Rule45;
import org.uast.uast.generated.tree.python.rules.Rule46;
import org.uast.uast.generated.tree.python.rules.Rule47;
import org.uast.uast.generated.tree.python.rules.Rule48;
import org.uast.uast.generated.tree.python.rules.Rule49;
import org.uast.uast.generated.tree.python.rules.Rule5;
import org.uast.uast.generated.tree.python.rules.Rule50;
import org.uast.uast.generated.tree.python.rules.Rule51;
import org.uast.uast.generated.tree.python.rules.Rule52;
import org.uast.uast.generated.tree.python.rules.Rule53;
import org.uast.uast.generated.tree.python.rules.Rule54;
import org.uast.uast.generated.tree.python.rules.Rule55;
import org.uast.uast.generated.tree.python.rules.Rule56;
import org.uast.uast.generated.tree.python.rules.Rule6;
import org.uast.uast.generated.tree.python.rules.Rule7;
import org.uast.uast.generated.tree.python.rules.Rule8;
import org.uast.uast.generated.tree.python.rules.Rule9;

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
            Rule29.INSTANCE,
            Rule30.INSTANCE,
            Rule31.INSTANCE,
            Rule32.INSTANCE,
            Rule33.INSTANCE,
            Rule34.INSTANCE,
            Rule35.INSTANCE,
            Rule36.INSTANCE,
            Rule37.INSTANCE,
            Rule38.INSTANCE,
            Rule39.INSTANCE,
            Rule40.INSTANCE,
            Rule41.INSTANCE,
            Rule42.INSTANCE,
            Rule43.INSTANCE,
            Rule44.INSTANCE,
            Rule45.INSTANCE,
            Rule46.INSTANCE,
            Rule47.INSTANCE,
            Rule48.INSTANCE,
            Rule49.INSTANCE,
            Rule50.INSTANCE,
            Rule51.INSTANCE,
            Rule52.INSTANCE,
            Rule53.INSTANCE,
            Rule54.INSTANCE,
            Rule55.INSTANCE,
            Rule56.INSTANCE
        );
    }
}
