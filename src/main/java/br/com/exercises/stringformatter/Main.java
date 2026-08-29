package br.com.exercises.stringformatter;

public class Main {
    static void main() {
        String fullNameLowerCase = "johnny b. goode";
        String fullNameCapitalized = Formatter.capitalize(fullNameLowerCase);

        System.out.println("Full name before capitalization: " + fullNameLowerCase);
        System.out.println("Full name after capitalization: " + fullNameCapitalized);

        System.out.println("----------------------------------------------------------------------");

        String stringWithNumbers = "There are approximately 200000000 people in Brazil.";
        String onlyNumbers = Formatter.onlyNumbers(stringWithNumbers);

        System.out.println("Full string: " + stringWithNumbers);
        System.out.println("String only with numbers: " + onlyNumbers);

        System.out.println("----------------------------------------------------------------------");

        String stringBeforeTruncation = "There are approximately 200,000,000 people in Brazil.";
        String stringAfterTruncation = Formatter.truncate(stringBeforeTruncation, 20);

        System.out.println("String before truncation: " + stringBeforeTruncation);
        System.out.println("String after truncation: " + stringAfterTruncation);

        System.out.println("----------------------------------------------------------------------");

        String shortString = "Hello";
        String shortStringAfterTruncation = Formatter.truncate(shortString, 20);

        System.out.println("Short string before truncation with size as 20: " + shortString);
        System.out.println("Short string after truncation with size as 20: " + shortStringAfterTruncation);

        System.out.println("----------------------------------------------------------------------");
    }
}
