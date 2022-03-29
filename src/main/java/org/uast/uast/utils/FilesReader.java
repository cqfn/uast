/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class for reading files.
 *
 * @since 1.0
 */
public class FilesReader {
    /**
     * The file path.
     */
    private final Path path;

    /**
     * Constructor.
     * @param path Path as a string
     */
    public FilesReader(final String path) {
        this.path = Paths.get(path);
    }

    /**
     * Reads file content as string.
     * @return File content
     * @throws IOException If the file can't be read
     */
    public String readAsString() throws IOException {
        final InputStream stream = Files.newInputStream(this.path);
        final StringBuilder builder = new StringBuilder();
        for (int chr = stream.read(); chr != -1; chr = stream.read()) {
            if (chr != '\r') {
                builder.append((char) chr);
            }
        }
        stream.close();
        return builder.toString();
    }
}
