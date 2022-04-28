/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class for writing files.
 *
 * @since 1.0
 */
public class FilesWriter {
    /**
     * The file path.
     */
    private final Path path;

    /**
     * Constructor.
     * @param path Path as a string
     */
    public FilesWriter(final String path) {
        this.path = Paths.get(path);
    }

    /**
     * Writes the string to the file.
     * @param str The string
     * @throws IOException If the file can't be written
     */
    public void writeString(final String str) throws IOException {
        final Path parent = this.path.getParent();
        if (parent != null) {
            Files.createDirectories(this.path.getParent());
        }
        final OutputStream stream = Files.newOutputStream(this.path);
        stream.write(str.getBytes(StandardCharsets.UTF_8));
        stream.close();
    }

    /**
     * Writes the string to the file (without exception).
     * @param str The string
     * @return Result of the operation, {@code true} if successful
     */
    public boolean writeStringNoExcept(final String str) {
        boolean success = true;
        try {
            this.writeString(str);
        } catch (final IOException ignored) {
            success = false;
        }
        return success;
    }
}
