package br.com.exercises.stringandbasicregex;

public class Validator {
    private Validator() {}

    public static boolean emailValidator(String email) {
        // validate emails like text@text.domain
        if (email == null) return false;

        email = email.trim();

        int indexOfAt = -1;
        int numberOfAts = 0;

        char[] charArray = email.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '@') {
                indexOfAt = i;
                numberOfAts++;
            }
        }

        if (numberOfAts != 1) return false;

        String beforeAt = email.substring(0, indexOfAt);

        if (beforeAt.isBlank()) return false;

        String afterAt = email.substring(indexOfAt+1);

        if (afterAt.isBlank()) return false;

        String[] secondPart = afterAt.split("\\.");

        if (secondPart.length != 2) return false;

        return secondPart[0].length() > 1 && secondPart[1].length() > 1;
    }

    public static boolean isOnlyLetters(String text) {
        char[] charArray = text.toCharArray();

        for (char c : charArray) {
            if (!Character.isLetter(c)) return false;
        }

        return true;
    }

    public static String[] separateFirstAndLastName(String fullName) {
        if (fullName == null) return null;

        return fullName.split(" ", 2);
    }
}
