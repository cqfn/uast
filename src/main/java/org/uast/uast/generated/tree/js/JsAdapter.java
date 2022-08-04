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
import org.uast.uast.generated.tree.js.rules.Rule100;
import org.uast.uast.generated.tree.js.rules.Rule101;
import org.uast.uast.generated.tree.js.rules.Rule102;
import org.uast.uast.generated.tree.js.rules.Rule103;
import org.uast.uast.generated.tree.js.rules.Rule104;
import org.uast.uast.generated.tree.js.rules.Rule105;
import org.uast.uast.generated.tree.js.rules.Rule106;
import org.uast.uast.generated.tree.js.rules.Rule107;
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
import org.uast.uast.generated.tree.js.rules.Rule30;
import org.uast.uast.generated.tree.js.rules.Rule31;
import org.uast.uast.generated.tree.js.rules.Rule32;
import org.uast.uast.generated.tree.js.rules.Rule33;
import org.uast.uast.generated.tree.js.rules.Rule34;
import org.uast.uast.generated.tree.js.rules.Rule35;
import org.uast.uast.generated.tree.js.rules.Rule36;
import org.uast.uast.generated.tree.js.rules.Rule37;
import org.uast.uast.generated.tree.js.rules.Rule38;
import org.uast.uast.generated.tree.js.rules.Rule39;
import org.uast.uast.generated.tree.js.rules.Rule4;
import org.uast.uast.generated.tree.js.rules.Rule40;
import org.uast.uast.generated.tree.js.rules.Rule41;
import org.uast.uast.generated.tree.js.rules.Rule42;
import org.uast.uast.generated.tree.js.rules.Rule43;
import org.uast.uast.generated.tree.js.rules.Rule44;
import org.uast.uast.generated.tree.js.rules.Rule45;
import org.uast.uast.generated.tree.js.rules.Rule46;
import org.uast.uast.generated.tree.js.rules.Rule47;
import org.uast.uast.generated.tree.js.rules.Rule48;
import org.uast.uast.generated.tree.js.rules.Rule49;
import org.uast.uast.generated.tree.js.rules.Rule5;
import org.uast.uast.generated.tree.js.rules.Rule50;
import org.uast.uast.generated.tree.js.rules.Rule51;
import org.uast.uast.generated.tree.js.rules.Rule52;
import org.uast.uast.generated.tree.js.rules.Rule53;
import org.uast.uast.generated.tree.js.rules.Rule54;
import org.uast.uast.generated.tree.js.rules.Rule55;
import org.uast.uast.generated.tree.js.rules.Rule56;
import org.uast.uast.generated.tree.js.rules.Rule57;
import org.uast.uast.generated.tree.js.rules.Rule58;
import org.uast.uast.generated.tree.js.rules.Rule59;
import org.uast.uast.generated.tree.js.rules.Rule6;
import org.uast.uast.generated.tree.js.rules.Rule60;
import org.uast.uast.generated.tree.js.rules.Rule61;
import org.uast.uast.generated.tree.js.rules.Rule62;
import org.uast.uast.generated.tree.js.rules.Rule63;
import org.uast.uast.generated.tree.js.rules.Rule64;
import org.uast.uast.generated.tree.js.rules.Rule65;
import org.uast.uast.generated.tree.js.rules.Rule66;
import org.uast.uast.generated.tree.js.rules.Rule67;
import org.uast.uast.generated.tree.js.rules.Rule68;
import org.uast.uast.generated.tree.js.rules.Rule69;
import org.uast.uast.generated.tree.js.rules.Rule7;
import org.uast.uast.generated.tree.js.rules.Rule70;
import org.uast.uast.generated.tree.js.rules.Rule71;
import org.uast.uast.generated.tree.js.rules.Rule72;
import org.uast.uast.generated.tree.js.rules.Rule73;
import org.uast.uast.generated.tree.js.rules.Rule74;
import org.uast.uast.generated.tree.js.rules.Rule75;
import org.uast.uast.generated.tree.js.rules.Rule76;
import org.uast.uast.generated.tree.js.rules.Rule77;
import org.uast.uast.generated.tree.js.rules.Rule78;
import org.uast.uast.generated.tree.js.rules.Rule79;
import org.uast.uast.generated.tree.js.rules.Rule8;
import org.uast.uast.generated.tree.js.rules.Rule80;
import org.uast.uast.generated.tree.js.rules.Rule81;
import org.uast.uast.generated.tree.js.rules.Rule82;
import org.uast.uast.generated.tree.js.rules.Rule83;
import org.uast.uast.generated.tree.js.rules.Rule84;
import org.uast.uast.generated.tree.js.rules.Rule85;
import org.uast.uast.generated.tree.js.rules.Rule86;
import org.uast.uast.generated.tree.js.rules.Rule87;
import org.uast.uast.generated.tree.js.rules.Rule88;
import org.uast.uast.generated.tree.js.rules.Rule89;
import org.uast.uast.generated.tree.js.rules.Rule9;
import org.uast.uast.generated.tree.js.rules.Rule90;
import org.uast.uast.generated.tree.js.rules.Rule91;
import org.uast.uast.generated.tree.js.rules.Rule92;
import org.uast.uast.generated.tree.js.rules.Rule93;
import org.uast.uast.generated.tree.js.rules.Rule94;
import org.uast.uast.generated.tree.js.rules.Rule95;
import org.uast.uast.generated.tree.js.rules.Rule96;
import org.uast.uast.generated.tree.js.rules.Rule97;
import org.uast.uast.generated.tree.js.rules.Rule98;
import org.uast.uast.generated.tree.js.rules.Rule99;

/**
 * Adapter that converts syntax trees, prepared by the parser of the Js language.
 *
 * @since 1.0
 */
@SuppressWarnings("PMD.ExcessiveMethodLength")
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
            Rule56.INSTANCE,
            Rule57.INSTANCE,
            Rule58.INSTANCE,
            Rule59.INSTANCE,
            Rule60.INSTANCE,
            Rule61.INSTANCE,
            Rule62.INSTANCE,
            Rule63.INSTANCE,
            Rule64.INSTANCE,
            Rule65.INSTANCE,
            Rule66.INSTANCE,
            Rule67.INSTANCE,
            Rule68.INSTANCE,
            Rule69.INSTANCE,
            Rule70.INSTANCE,
            Rule71.INSTANCE,
            Rule72.INSTANCE,
            Rule73.INSTANCE,
            Rule74.INSTANCE,
            Rule75.INSTANCE,
            Rule76.INSTANCE,
            Rule77.INSTANCE,
            Rule78.INSTANCE,
            Rule79.INSTANCE,
            Rule80.INSTANCE,
            Rule81.INSTANCE,
            Rule82.INSTANCE,
            Rule83.INSTANCE,
            Rule84.INSTANCE,
            Rule85.INSTANCE,
            Rule86.INSTANCE,
            Rule87.INSTANCE,
            Rule88.INSTANCE,
            Rule89.INSTANCE,
            Rule90.INSTANCE,
            Rule91.INSTANCE,
            Rule92.INSTANCE,
            Rule93.INSTANCE,
            Rule94.INSTANCE,
            Rule95.INSTANCE,
            Rule96.INSTANCE,
            Rule97.INSTANCE,
            Rule98.INSTANCE,
            Rule99.INSTANCE,
            Rule100.INSTANCE,
            Rule101.INSTANCE,
            Rule102.INSTANCE,
            Rule103.INSTANCE,
            Rule104.INSTANCE,
            Rule105.INSTANCE,
            Rule106.INSTANCE,
            Rule107.INSTANCE
        );
    }
}
