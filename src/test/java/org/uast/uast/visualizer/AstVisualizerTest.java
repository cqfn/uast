/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.visualizer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.uast.uast.base.DraftNode;
import org.uast.uast.base.Node;
import org.uast.uast.exceptions.VisualizerException;

/**
 * Test for {@link AstVisualizer} class.
 *
 * @since 1.0
 */
public class AstVisualizerTest {
    /**
     * Test for a tree visualization.
     */
    @Test
    public void testAstVisualizer() {
        final DraftNode.Constructor ctor = new DraftNode.Constructor();
        ctor.setName("TestNode");
        ctor.setData("value");
        final Node root = ctor.create();
        final AstVisualizer visualizer = new AstVisualizer(root);
        boolean oops = false;
        try {
            visualizer.visualize(new File("src/test/java/org/uast/uast/visualizer/png/test.png"));
        } catch (final VisualizerException | IOException exception) {
            oops = true;
        }
        Assertions.assertFalse(oops);
    }

    @SuppressWarnings("PMD.ProhibitPublicStaticMethods")
    @AfterAll
    /**
     * Deletes the generated files after all tests passing.
     */
    public static void deleteGeneratedFile() throws IOException {
        final Path path = Paths.get("src/test/java/org/uast/uast/visualizer/png");
        Files.walk(path)
            .sorted(Comparator.reverseOrder())
            .map(Path::toFile)
            .forEach(File::delete);
    }
}
