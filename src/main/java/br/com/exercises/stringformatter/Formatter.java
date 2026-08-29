package br.com.exercises.stringformatter;

import java.util.StringJoiner;

public class Formatter {
    public static String capitalize(String string) {
        if (string == null || string.isBlank()) {
            return "";
        }

        String[] words = string.trim().split(" ");

        StringJoiner joiner = new StringJoiner(" ");

        StringBuilder builder = new StringBuilder();

        for (String word : words) {
            joiner.add(builder.append(word.toUpperCase().charAt(0)).append(word.substring(1)));

            builder.setLength(0);
        }

        return joiner.toString();
    }

    public static String onlyNumbers(String string) {
        if (string == null || string.isBlank()) {
            return "";
        }

        char[] characters = string.toCharArray();

        StringBuilder builder = new StringBuilder();

        for (char character : characters) {
            if (Character.isDigit(character)) {
                builder.append(character);
            }
        }

        return builder.toString();
    }

    public static String truncate(String string, int size) {
        if (string == null || string.isBlank()) {
            return "";
        }

        String trimmedString = string.trim();

        if (trimmedString.length() < size) {
            return trimmedString;
        }
        
        return trimmedString.substring(0, size).concat("...");
    }
}
