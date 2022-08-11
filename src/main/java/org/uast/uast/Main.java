/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.converters.FileConverter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import org.uast.uast.base.Node;
import org.uast.uast.exceptions.VisualizerException;
import org.uast.uast.handlers.json.JsonSerializer;
import org.uast.uast.handlers.visualizer.AstVisualizer;
import org.uast.uast.lang.SourceCodeParser;
import org.uast.uast.utils.cli.ImagePathValidator;
import org.uast.uast.utils.cli.JarPathValidator;
import org.uast.uast.utils.cli.JsonPathValidator;
import org.uast.uast.utils.cli.LanguageConverter;

/**
 * Main class.
 *
 * @since 1.0
 */
@SuppressWarnings("PMD.ImmutableField")
public final class Main {
    /**
     * The source file.
     */
    @Parameter(
        names = { "--parse", "-p" },
        converter = FileConverter.class,
        required = true,
        arity = 1,
        description = "The file with source code"
    )
    private File source;

    /**
     * The programming language for which the analysis is performed.
     */
    @Parameter(
        names = { "--lang", "-l" },
        converter = LanguageConverter.class,
        arity = 1,
        description = "The programming language of the source file"
    )
    private String language;

    /**
     * The image file.
     */
    @Parameter(
        names = { "--visualize", "-v" },
        validateWith = ImagePathValidator.class,
        arity = 1,
        description = "The name (possibly path) of the image file with extension"
    )
    private String image;

    /**
     * The json file to store ASTs.
     */
    @Parameter(
        names = { "--output", "-o" },
        validateWith = JsonPathValidator.class,
        arity = 1,
        description = "The name (possibly path) of the json file with extension"
    )
    private String json;

    /**
     * The jar file with algorithms to process ASTs.
     */
    @Parameter(
        names = { "--processing", "-pr" },
        validateWith = JarPathValidator.class,
        arity = 1,
        description = "The name (possibly path) of the jar file with extension"
    )
    private String proc;

    /**
     * The raw option.
     */
    @Parameter(
        names = "--raw",
        description = "Do not convert to unified syntax tree"
    )
    private boolean raw;

    /**
     * The ANTLR option.
     */
    @Parameter(
        names = "--antlr",
        description = "Do not simplify ANTLR tree"
    )
    private boolean antlr;

    /**
     * The help option.
     */
    @Parameter(names = "--help", help = true)
    private boolean help;

    /**
     * Private constructor with default values.
     */
    private Main() {
        this.help = false;
        this.language = "";
        this.image = "";
        this.json = "";
        this.proc = "";
    }

    /**
     * The main function. Parses the command line and runs actions.
     * @param args The command-line arguments
     * @throws IOException If fails
     * @throws VisualizerException If AST visualizer fails
     */
    public static void main(final String... args) throws IOException, VisualizerException {
        final Main main = new Main();
        final JCommander jcr = JCommander.newBuilder()
            .addObject(main)
            .build();
        jcr.parse(args);
        if (main.help) {
            jcr.usage();
            return;
        }
        main.run();
    }

    /**
     * Runs actions.
     * @throws IOException If fails
     * @throws VisualizerException If AST visualizer fails
     */
    private void run() throws IOException, VisualizerException {
        final String ext = this.source.getName()
            .substring(this.source.getName().lastIndexOf('.') + 1);
        String lang = ext;
        if ("txt".equals(ext)) {
            if (this.language.isEmpty()) {
                throw new ParameterException(
                    "The option [--lang] should be specified with txt source file"
                );
            } else {
                lang = this.language;
            }
        }
        final Node node = new SourceCodeParser(this.source.getPath())
            .parse(lang, !this.raw, this.antlr);
        if (!this.image.isEmpty()) {
            final AstVisualizer visualizer = new AstVisualizer(node);
            visualizer.visualize(new File(this.image));
        }
        if (!this.json.isEmpty()) {
            final JsonSerializer serializer = new JsonSerializer(node);
            serializer.serializeToFile(this.json);
        }
        if (!this.proc.isEmpty()) {
            final URLClassLoader loader = URLClassLoader.newInstance(
                new URL[] {new File(this.proc).toURI().toURL()},
                this.getClass().getClassLoader()
            );
            Object result = null;
            try {
                final Class clazz = Class.forName("org.uast.processor.Processor", true, loader);
                final Method method = clazz.getDeclaredMethod("process", String.class);
                final Object instance = clazz.newInstance();
                result = method.invoke(instance, "node with data");
            } catch (final ClassNotFoundException
                    | NoSuchMethodException | InstantiationException | IllegalAccessException
                    | InvocationTargetException exception) {
                throw new ParameterException(
                    "Cannot find the required method in a jar file"
                );
            }
        }
    }
}
