/*
 * MIT License Copyright (c) 2022 unified-ast
 * https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt
 */

package org.uast.uast.utils.cli;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;
import java.util.Locale;

/**
 * Custom implementation of CLI language parameter converter.
 *
 * @since 1.0
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
        String lowercase = value.toLowerCase(Locale.ENGLISH);
        final String result;
        switch(lowercase) {
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
        }
        return result;
    }
}
