/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022 Ivan Kniazkov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cqfn.uast.cli;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;
import java.util.Locale;
import org.cqfn.uast.algorithms.Algorithm;
import org.cqfn.uast.algorithms.Carrying;
import org.cqfn.uast.algorithms.Greening;

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
