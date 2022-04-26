/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast;

import com.beust.jcommander.ParameterException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.uast.uast.exceptions.VisualizerException;

/**
 * Test for {@link Main} class.
 *
 * @since 1.0
 */
public class MainTest {
    /**
     * The parse option as an argument example.
     */
    private static final String PARSE = "--parse";

    /**
     * Test passing the {@code --generate} option to main().
     * @param source A temporary directory
     */
    @Test
    public void testNoException(@TempDir final Path source) throws IOException {
        final Path file = this.createTempTxtFile(source);
        final String[] example = {
            MainTest.PARSE,
            file.toString(),
        };
        boolean caught = false;
        try {
            Main.main(example);
        } catch (final ParameterException | IOException | VisualizerException exc) {
            caught = true;
        }
        Assertions.assertFalse(caught);
    }

    /**
     * Test passing no option to main().
     */
    @Test
    public void testWithException() {
        final String[] example = {
        };
        boolean caught = false;
        try {
            Main.main(example);
        } catch (final ParameterException | IOException | VisualizerException exc) {
            caught = true;
        }
        Assertions.assertTrue(caught);
    }

    /**
     * Test passing the {@code --parse} option with no parameters to main().
     */
    @Test
    public void testGenerateWithoutParameters() {
        final String[] example = {
            MainTest.PARSE,
        };
        boolean caught = false;
        String message = "";
        try {
            Main.main(example);
        } catch (final ParameterException | IOException | VisualizerException exc) {
            caught = true;
            message = exc.getMessage();
        }
        Assertions.assertTrue(caught);
        Assertions.assertEquals("Expected a value after parameter --parse", message);
    }

    /**
     * Creates a temporary directory to test passing files in CLI options.
     * @param source A temporary directory
     * @return The path to a temporary file
     * @throws IOException If fails to create a temporary file
     */
    private Path createTempTxtFile(@TempDir final Path source) throws IOException {
        final Path file = source.resolve("example.java");
        final List<String> lines = Collections.singletonList("class X{int calc(){return 2 + 3;}}");
        Files.write(file, lines);
        return file;
    }
}
