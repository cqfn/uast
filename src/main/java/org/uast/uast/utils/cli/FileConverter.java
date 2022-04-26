/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.utils.cli;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Custom implementation of CLI file parameter converter.
 *
 * @since 1.0
 */
public class FileConverter implements IStringConverter<File> {
    /**
     * The option name.
     */
    private final String option;

    /**
     * Constructor.
     * @param option An option name
     */
    public FileConverter(final String option) {
        this.option = option;
    }

    /**
     * Converts a command-line parameter to a file.
     * @param value A path to file
     * @return A new file
     */
    public File convert(final String value) {
        if (value.charAt(0) == '-') {
            final StringBuilder message = new StringBuilder(50);
            message
                .append("Missed parameter for the option [")
                .append(this.option)
                .append(']');
            throw new ParameterException(message.toString());
        }
        if (!isValidFileExtension(value)) {
            final StringBuilder message = new StringBuilder(50);
            message
                .append("The parameter [")
                .append(value)
                .append("] should be a source file");
            throw new ParameterException(message.toString());
        }
        final File file = new File(value);
        if (!file.exists()) {
            final StringBuilder message = new StringBuilder(50);
            message
                .append("The parameter for option [")
                .append(this.option)
                .append("] is not a valid file:")
                .append(value);
            throw new ParameterException(message.toString());
        }
        return new File(value);
    }

    /**
     * Checks if an input file extension is from the list
     *  of supported languages.
     * @param value A path to file
     * @return A boolean {@code true} if a file has a valid extension or
     *  {@code false} otherwise
     */
    private static boolean isValidFileExtension(final String value) {
        final List<String> exts = Arrays.asList("java", "py", "js", "txt");
        final Optional<String> ext = Optional.ofNullable(value)
            .filter(f -> f.contains("."))
            .map(f -> f.substring(value.lastIndexOf('.') + 1));
        boolean valid = false;
        if (ext.isPresent()) {
            valid = exts.contains(ext.get());
        }
        return valid;
    }
}
