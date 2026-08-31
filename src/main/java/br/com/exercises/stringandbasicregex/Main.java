package br.com.exercises.stringandbasicregex;

public class Main {
    static void main() {


        String email = "text@text.domain";
        String onlyLetters = "abcde";
        String fullName = "Johnny B. Goode";

        System.out.printf("Is \"%s\" a valid email? %s\n", email, (Validator.emailValidator(email) ? "Yes" : "No"));
        System.out.printf("Is \"%s\" only letters? %s\n", onlyLetters, (Validator.isOnlyLetters(onlyLetters) ? "Yes" : "No"));
        System.out.printf("Is \"%s\" only letters? %s\n", email, (Validator.isOnlyLetters(email) ? "Yes" : "No"));

        String[] names = Validator.separateFirstAndLastName(fullName);

        System.out.println("\nFull name: " + fullName);
        System.out.printf("First name: %s\nOther names: %s\n", names[0], names[1]);
    }
}
