/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for {@link Main} class.
 *
 * @since 1.0
 */
public class MainTest {
    /**
     * Argument example.
     */
    private static final String ARG = "test";

    /**
     * Test passing an argument to main().
     */
    @Test
    public void testNoException() {
        final String[] example = {
            MainTest.ARG,
        };
        boolean caught = false;
        try {
            Main.main(example);
        } catch (final IllegalArgumentException exc) {
            caught = true;
        }
        Assertions.assertFalse(caught);
    }

    /**
     * Test passing no argument to main().
     */
    @Test
    public void testWithException() {
        final String[] example = {
        };
        boolean caught = false;
        try {
            Main.main(example);
        } catch (final IllegalArgumentException exc) {
            caught = true;
        }
        Assertions.assertTrue(caught);
    }

    /**
     * Test passing no argument to main().
     */
    @Test
    public void testMain() {
        final String[] example = {
            MainTest.ARG,
        };
        Main.main(example);
        Assertions.assertTrue(example.length > 0);
    }
}
