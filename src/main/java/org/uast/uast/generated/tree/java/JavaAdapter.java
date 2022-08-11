/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.generated.tree.java;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.uast.uast.base.Adapter;
import org.uast.uast.base.Converter;
import org.uast.uast.generated.tree.java.rules.Rule0;
import org.uast.uast.generated.tree.java.rules.Rule1;
import org.uast.uast.generated.tree.java.rules.Rule10;
import org.uast.uast.generated.tree.java.rules.Rule100;
import org.uast.uast.generated.tree.java.rules.Rule101;
import org.uast.uast.generated.tree.java.rules.Rule102;
import org.uast.uast.generated.tree.java.rules.Rule103;
import org.uast.uast.generated.tree.java.rules.Rule104;
import org.uast.uast.generated.tree.java.rules.Rule105;
import org.uast.uast.generated.tree.java.rules.Rule106;
import org.uast.uast.generated.tree.java.rules.Rule107;
import org.uast.uast.generated.tree.java.rules.Rule108;
import org.uast.uast.generated.tree.java.rules.Rule109;
import org.uast.uast.generated.tree.java.rules.Rule11;
import org.uast.uast.generated.tree.java.rules.Rule110;
import org.uast.uast.generated.tree.java.rules.Rule111;
import org.uast.uast.generated.tree.java.rules.Rule112;
import org.uast.uast.generated.tree.java.rules.Rule113;
import org.uast.uast.generated.tree.java.rules.Rule114;
import org.uast.uast.generated.tree.java.rules.Rule115;
import org.uast.uast.generated.tree.java.rules.Rule116;
import org.uast.uast.generated.tree.java.rules.Rule117;
import org.uast.uast.generated.tree.java.rules.Rule118;
import org.uast.uast.generated.tree.java.rules.Rule119;
import org.uast.uast.generated.tree.java.rules.Rule12;
import org.uast.uast.generated.tree.java.rules.Rule120;
import org.uast.uast.generated.tree.java.rules.Rule121;
import org.uast.uast.generated.tree.java.rules.Rule122;
import org.uast.uast.generated.tree.java.rules.Rule123;
import org.uast.uast.generated.tree.java.rules.Rule124;
import org.uast.uast.generated.tree.java.rules.Rule125;
import org.uast.uast.generated.tree.java.rules.Rule126;
import org.uast.uast.generated.tree.java.rules.Rule127;
import org.uast.uast.generated.tree.java.rules.Rule128;
import org.uast.uast.generated.tree.java.rules.Rule129;
import org.uast.uast.generated.tree.java.rules.Rule13;
import org.uast.uast.generated.tree.java.rules.Rule130;
import org.uast.uast.generated.tree.java.rules.Rule131;
import org.uast.uast.generated.tree.java.rules.Rule132;
import org.uast.uast.generated.tree.java.rules.Rule133;
import org.uast.uast.generated.tree.java.rules.Rule134;
import org.uast.uast.generated.tree.java.rules.Rule135;
import org.uast.uast.generated.tree.java.rules.Rule136;
import org.uast.uast.generated.tree.java.rules.Rule137;
import org.uast.uast.generated.tree.java.rules.Rule138;
import org.uast.uast.generated.tree.java.rules.Rule139;
import org.uast.uast.generated.tree.java.rules.Rule14;
import org.uast.uast.generated.tree.java.rules.Rule140;
import org.uast.uast.generated.tree.java.rules.Rule141;
import org.uast.uast.generated.tree.java.rules.Rule142;
import org.uast.uast.generated.tree.java.rules.Rule143;
import org.uast.uast.generated.tree.java.rules.Rule144;
import org.uast.uast.generated.tree.java.rules.Rule15;
import org.uast.uast.generated.tree.java.rules.Rule16;
import org.uast.uast.generated.tree.java.rules.Rule17;
import org.uast.uast.generated.tree.java.rules.Rule18;
import org.uast.uast.generated.tree.java.rules.Rule19;
import org.uast.uast.generated.tree.java.rules.Rule2;
import org.uast.uast.generated.tree.java.rules.Rule20;
import org.uast.uast.generated.tree.java.rules.Rule21;
import org.uast.uast.generated.tree.java.rules.Rule22;
import org.uast.uast.generated.tree.java.rules.Rule23;
import org.uast.uast.generated.tree.java.rules.Rule24;
import org.uast.uast.generated.tree.java.rules.Rule25;
import org.uast.uast.generated.tree.java.rules.Rule26;
import org.uast.uast.generated.tree.java.rules.Rule27;
import org.uast.uast.generated.tree.java.rules.Rule28;
import org.uast.uast.generated.tree.java.rules.Rule29;
import org.uast.uast.generated.tree.java.rules.Rule3;
import org.uast.uast.generated.tree.java.rules.Rule30;
import org.uast.uast.generated.tree.java.rules.Rule31;
import org.uast.uast.generated.tree.java.rules.Rule32;
import org.uast.uast.generated.tree.java.rules.Rule33;
import org.uast.uast.generated.tree.java.rules.Rule34;
import org.uast.uast.generated.tree.java.rules.Rule35;
import org.uast.uast.generated.tree.java.rules.Rule36;
import org.uast.uast.generated.tree.java.rules.Rule37;
import org.uast.uast.generated.tree.java.rules.Rule38;
import org.uast.uast.generated.tree.java.rules.Rule39;
import org.uast.uast.generated.tree.java.rules.Rule4;
import org.uast.uast.generated.tree.java.rules.Rule40;
import org.uast.uast.generated.tree.java.rules.Rule41;
import org.uast.uast.generated.tree.java.rules.Rule42;
import org.uast.uast.generated.tree.java.rules.Rule43;
import org.uast.uast.generated.tree.java.rules.Rule44;
import org.uast.uast.generated.tree.java.rules.Rule45;
import org.uast.uast.generated.tree.java.rules.Rule46;
import org.uast.uast.generated.tree.java.rules.Rule47;
import org.uast.uast.generated.tree.java.rules.Rule48;
import org.uast.uast.generated.tree.java.rules.Rule49;
import org.uast.uast.generated.tree.java.rules.Rule5;
import org.uast.uast.generated.tree.java.rules.Rule50;
import org.uast.uast.generated.tree.java.rules.Rule51;
import org.uast.uast.generated.tree.java.rules.Rule52;
import org.uast.uast.generated.tree.java.rules.Rule53;
import org.uast.uast.generated.tree.java.rules.Rule54;
import org.uast.uast.generated.tree.java.rules.Rule55;
import org.uast.uast.generated.tree.java.rules.Rule56;
import org.uast.uast.generated.tree.java.rules.Rule57;
import org.uast.uast.generated.tree.java.rules.Rule58;
import org.uast.uast.generated.tree.java.rules.Rule59;
import org.uast.uast.generated.tree.java.rules.Rule6;
import org.uast.uast.generated.tree.java.rules.Rule60;
import org.uast.uast.generated.tree.java.rules.Rule61;
import org.uast.uast.generated.tree.java.rules.Rule62;
import org.uast.uast.generated.tree.java.rules.Rule63;
import org.uast.uast.generated.tree.java.rules.Rule64;
import org.uast.uast.generated.tree.java.rules.Rule65;
import org.uast.uast.generated.tree.java.rules.Rule66;
import org.uast.uast.generated.tree.java.rules.Rule67;
import org.uast.uast.generated.tree.java.rules.Rule68;
import org.uast.uast.generated.tree.java.rules.Rule69;
import org.uast.uast.generated.tree.java.rules.Rule7;
import org.uast.uast.generated.tree.java.rules.Rule70;
import org.uast.uast.generated.tree.java.rules.Rule71;
import org.uast.uast.generated.tree.java.rules.Rule72;
import org.uast.uast.generated.tree.java.rules.Rule73;
import org.uast.uast.generated.tree.java.rules.Rule74;
import org.uast.uast.generated.tree.java.rules.Rule75;
import org.uast.uast.generated.tree.java.rules.Rule76;
import org.uast.uast.generated.tree.java.rules.Rule77;
import org.uast.uast.generated.tree.java.rules.Rule78;
import org.uast.uast.generated.tree.java.rules.Rule79;
import org.uast.uast.generated.tree.java.rules.Rule8;
import org.uast.uast.generated.tree.java.rules.Rule80;
import org.uast.uast.generated.tree.java.rules.Rule81;
import org.uast.uast.generated.tree.java.rules.Rule82;
import org.uast.uast.generated.tree.java.rules.Rule83;
import org.uast.uast.generated.tree.java.rules.Rule84;
import org.uast.uast.generated.tree.java.rules.Rule85;
import org.uast.uast.generated.tree.java.rules.Rule86;
import org.uast.uast.generated.tree.java.rules.Rule87;
import org.uast.uast.generated.tree.java.rules.Rule88;
import org.uast.uast.generated.tree.java.rules.Rule89;
import org.uast.uast.generated.tree.java.rules.Rule9;
import org.uast.uast.generated.tree.java.rules.Rule90;
import org.uast.uast.generated.tree.java.rules.Rule91;
import org.uast.uast.generated.tree.java.rules.Rule92;
import org.uast.uast.generated.tree.java.rules.Rule93;
import org.uast.uast.generated.tree.java.rules.Rule94;
import org.uast.uast.generated.tree.java.rules.Rule95;
import org.uast.uast.generated.tree.java.rules.Rule96;
import org.uast.uast.generated.tree.java.rules.Rule97;
import org.uast.uast.generated.tree.java.rules.Rule98;
import org.uast.uast.generated.tree.java.rules.Rule99;

/**
 * Adapter that converts syntax trees, prepared by the parser of the Java language.
 *
 * @since 1.0
 */
@SuppressWarnings("PMD.ExcessiveMethodLength")
public final class JavaAdapter extends Adapter {
    /**
     * The instance.
     */
    public static final Adapter INSTANCE = new JavaAdapter();

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
            Rule107.INSTANCE,
            Rule108.INSTANCE,
            Rule109.INSTANCE,
            Rule110.INSTANCE,
            Rule111.INSTANCE,
            Rule112.INSTANCE,
            Rule113.INSTANCE,
            Rule114.INSTANCE,
            Rule115.INSTANCE,
            Rule116.INSTANCE,
            Rule117.INSTANCE,
            Rule118.INSTANCE,
            Rule119.INSTANCE,
            Rule120.INSTANCE,
            Rule121.INSTANCE,
            Rule122.INSTANCE,
            Rule123.INSTANCE,
            Rule124.INSTANCE,
            Rule125.INSTANCE,
            Rule126.INSTANCE,
            Rule127.INSTANCE,
            Rule128.INSTANCE,
            Rule129.INSTANCE,
            Rule130.INSTANCE,
            Rule131.INSTANCE,
            Rule132.INSTANCE,
            Rule133.INSTANCE,
            Rule134.INSTANCE,
            Rule135.INSTANCE,
            Rule136.INSTANCE,
            Rule137.INSTANCE,
            Rule138.INSTANCE,
            Rule139.INSTANCE,
            Rule140.INSTANCE,
            Rule141.INSTANCE,
            Rule142.INSTANCE,
            Rule143.INSTANCE,
            Rule144.INSTANCE
        );
    }
}
