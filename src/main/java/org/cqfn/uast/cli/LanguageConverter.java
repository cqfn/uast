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

/**
 * Custom implementation of CLI language parameter converter.
 *
 * @since 0.1
 */
public class LanguageConverter implements IStringConverter<String> {
    /**
     * The option name.
     */
    private final String option;

    /**
     * Constructor.
     * @param option An option name
     */
    public LanguageConverter(final String option) {
        this.option = option;
    }

    /**
     * Converts a command-line parameter to a language name.
     * @param value An option value
     * @return A new file
     */
    public String convert(final String value) {
        if (value.charAt(0) == '-') {
            final StringBuilder message = new StringBuilder(50);
            message
                .append("Missed parameter for the option [")
                .append(this.option)
                .append(']');
            throw new ParameterException(message.toString());
        }
        final String lang = getSourceLanguage(value);
        if (lang.isEmpty()) {
            final StringBuilder message = new StringBuilder(50);
            message
                .append("The language [")
                .append(value)
                .append("] is not supported");
            throw new ParameterException(message.toString());
        }
        return lang;
    }

    /**
     * Checks if an input file extension is from the list
     *  of supported languages.
     * @param value An option parameter value
     * @return One of the supported languages or an empty string if
     *  the input language is not supported
     */
    private static String getSourceLanguage(final String value) {
        final String lowercase = value.toLowerCase(Locale.ENGLISH);
        final String result;
        switch (lowercase) {
            case "java":
            case "js":
            case "python":
            case "json":
                result = lowercase;
                break;
            case "javascript":
                result = "js";
                break;
            default:
                result = "";
                break;
        }
        return result;
    }
}
