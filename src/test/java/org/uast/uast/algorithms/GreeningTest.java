/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.algorithms;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.uast.uast.base.Node;
import org.uast.uast.handlers.json.JsonDeserializer;
import org.uast.uast.handlers.json.JsonSerializer;
import org.uast.uast.utils.FilesReader;

/**
 * Test for {@link Greening} class.
 *
 * @since 1.0
 */
public class GreeningTest {
    /**
     * The folder with test resources.
     */
    private static final String TESTS_PATH = "src/test/resources/algorithms/";

    /**
     * Test for conversion of a {@link org.uast.uast.generated.tree.js.Property} into
     * a {@link org.uast.uast.generated.tree.green.FieldDeclaration} node.
     */
    @Test
    public void testAstVisualizer() {
        final String source = this.loadSampleTreeFromJson("ClassWithProperty_js.json");
        final JsonDeserializer deserializer = new JsonDeserializer(source);
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
    public String loadSampleTreeFromJson(final String filename) {
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
