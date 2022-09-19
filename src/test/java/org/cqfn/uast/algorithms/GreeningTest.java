/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022 Ivan Kniazkov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cqfn.uast.algorithms;

import java.io.IOException;
import org.cqfn.astranaut.core.Node;
import org.cqfn.astranaut.core.utils.FilesReader;
import org.cqfn.astranaut.core.utils.JsonDeserializer;
import org.cqfn.astranaut.core.utils.JsonSerializer;
import org.cqfn.uast.lang.FactorySelector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for {@link Greening} class.
 *
 * @since 0.1
 */
class GreeningTest {
    /**
     * The folder with test resources.
     */
    private static final String TESTS_PATH = "src/test/resources/algorithms/";

    /**
     * Test for conversion of a {@link org.cqfn.uast.tree.js.Property} into
     * a {@link org.cqfn.uast.tree.green.FieldDeclaration} node.
     */
    @Test
    void testAstVisualizer() {
        final String source = this.loadSampleTreeFromJson("ClassWithProperty_js.json");
        final JsonDeserializer deserializer =
            new JsonDeserializer(source, FactorySelector.INSTANCE);
        final Node initial = deserializer.convert();
        final Greening algorithm = Greening.INSTANCE;
        final Node processed = algorithm.process(initial);
        final JsonSerializer serializer = new JsonSerializer(processed);
        final String result = serializer.serialize();
        final String expected = this.loadSampleTreeFromJson("ClassWithProperty_js_processed.json");
        Assertions.assertEquals(expected, result);
    }

    /**
     * Load a sample tree from JSON file for testing.
     * @param filename The source file name
     * @return Serialized tree
     */
    String loadSampleTreeFromJson(final String filename) {
        final String file = GreeningTest.TESTS_PATH.concat(filename);
        boolean oops = false;
        String source = "";
        try {
            source = new FilesReader(file).readAsString();
        } catch (final IOException exception) {
            oops = true;
        }
        Assertions.assertFalse(oops);
        return source;
    }
}
