/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.handlers.visualizer;

import java.io.File;
import java.io.IOException;
import org.uast.uast.base.Node;
import org.uast.uast.exceptions.VisualizerException;

/**
 * Visualizer of ASTs.
 *
 * @since 1.0
 */
public class AstVisualizer {
    /**
     * The tree to be visualized.
     */
    private final Node tree;

    /**
     * Constructor.
     *
     * @param tree The tree to be visualized
     */
    public AstVisualizer(final Node tree) {
        this.tree = tree;
    }

    /**
     * Renders a DOT text of the tree, renders an image from it and
     * saves it to the specified file.
     *
     * @param file A file of the AST visualization
     * @throws IOException If an error during input or output actions occurs
     * @throws VisualizerException If an error during visualization occurs
     */
    public void visualize(final File file) throws IOException, VisualizerException {
        final DotRender render = new DotRender(this.tree);
        final String dot = render.render();
        final ImageRender image = new ImageRender(dot);
        image.render(file);
    }
}
