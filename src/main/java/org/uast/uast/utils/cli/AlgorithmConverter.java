/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */
package org.uast.uast.utils.cli;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;
import java.util.Locale;
import org.uast.uast.algorithms.Algorithm;
import org.uast.uast.algorithms.Carrying;
import org.uast.uast.algorithms.Greening;

/**
 * Custom implementation of CLI algorithm parameter converter.
 *
 * @since 1.0
 */
public final class AlgorithmConverter implements IStringConverter<Algorithm> {
    /**
     * The option name.
     */
    private final String option;

    /**
     * Constructor.
     * @param option An option name
     */
    public AlgorithmConverter(final String option) {
        this.option = option;
    }

    @Override
    public Algorithm convert(final String value) {
        if (value.charAt(0) == '-') {
            final StringBuilder message = new StringBuilder(50);
            message
                .append("Missed parameter for the option [")
                .append(this.option)
                .append(']');
            throw new ParameterException(message.toString());
        }
        return this.getAlgorithm(value);
    }

    /**
     * Checks if an input algorithm name is from the list
     *  of supported algorithms.
     * @param value A name of the algorithm
     * @return One of the supported algorithms or throws an exception
     */
    private Algorithm getAlgorithm(final String value) {
        final String name = value.toLowerCase(Locale.ENGLISH);
        final Algorithm algorithm;
        switch (name) {
            case "greening":
                algorithm = Greening.INSTANCE;
                break;
            case "carrying":
                algorithm = Carrying.INSTANCE;
                break;
            default:
                final StringBuilder message = new StringBuilder(50);
                message
                    .append("The algorithm \"")
                    .append(value)
                    .append("\" for option [")
                    .append(this.option)
                    .append("] does not exist");
                throw new ParameterException(message.toString());
        }
        return algorithm;
    }
}
