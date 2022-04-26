/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.visualizer;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.GraphvizV8Engine;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.uast.uast.exceptions.VisualizerException;
import org.uast.uast.exceptions.WrongFileExtension;

/**
 * Renders an AST to an image using the external Graphviz tool.
 *
 * @since 1.0
 */
public class ImageRender {
    /**
     * DOT text with AST description.
     */
    private final String dot;

    /**
     * Constructor.
     *
     * @param dot The DOT file text
     */
    public ImageRender(final String dot) {
        this.dot = dot;
    }

    /**
     * Renders data a graphical format.
     *
     * @param file A file of the AST visualization
     * @throws VisualizerException If an error during visualization occurs
     * @throws IOException If an error during input or output actions occurs
     */
    public void render(final File file) throws VisualizerException, IOException {
        final Enum<Format> format = getFileExtension(file.getPath());
        final MutableGraph graph = new Parser().read(this.dot);
        Graphviz.useEngine(new GraphvizV8Engine());
        Graphviz.fromGraph(graph).render((Format) format).toFile(file);
    }

    /**
     * Get supported graphical file extension.
     *
     * @param path A path to the file to be rendered
     * @return A file extension
     * @throws VisualizerException If a file extension is invalid
     */
    private static Enum<Format> getFileExtension(final String path) throws VisualizerException {
        final Optional<String> optional = Optional.ofNullable(path)
            .filter(f -> f.contains("."))
            .map(f -> f.substring(path.lastIndexOf('.') + 1));
        Enum<Format> format = null;
        if (optional.isPresent()) {
            final String ext = optional.get();
            if ("png".equals(ext)) {
                format = Format.PNG;
            }
            if ("svg".equals(ext)) {
                format = Format.SVG;
            }
        }
        if (format == null) {
            throw WrongFileExtension.INSTANCE;
        }
        return format;
    }
}
