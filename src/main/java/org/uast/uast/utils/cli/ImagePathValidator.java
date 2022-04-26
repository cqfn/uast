/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.utils.cli;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Validator of an image path CLI parameter.
 *
 * @since 1.0
 */
public class ImagePathValidator implements IParameterValidator {
    @Override
    /**
     * Validates an input option parameter that should contain
     * a path to the image file with extension.
     * @param name An option name
     * @param value An option value
     * @throws ParameterException
     */
    public void validate(final String name, final String value) throws ParameterException {
        if (value.charAt(0) == '-') {
            final StringBuilder message = new StringBuilder(50);
            message
                .append("Missed parameter for the option [")
                .append(name)
                .append(']');
            throw new ParameterException(message.toString());
        }
        final File file = new File(value);
        boolean created = false;
        try {
            created = file.mkdirs();
            if (!file.exists()) {
                final StringBuilder message = new StringBuilder(70);
                message
                    .append("The parameter for the option [")
                    .append(name)
                    .append("] should be a path to a graphical file, found: ")
                    .append(value);
                throw new ParameterException(message.toString());
            }
        } finally {
            if (created) {
                file.delete();
            }
        }
        if (!isValidFileExtension(value)) {
            final StringBuilder message = new StringBuilder(50);
            message
                .append("The parameter [")
                .append(value)
                .append("] should be a graphical file: png, svg, gif");
            throw new ParameterException(message.toString());
        }
    }

    /**
     * Checks if a file extension is supported.
     * @param value A path to file
     * @return A boolean {@code true} if a file has a valid extension or
     *  {@code false} otherwise
     */
    private static boolean isValidFileExtension(final String value) {
        final List<String> exts = Arrays.asList("png", "svg", "gif");
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
