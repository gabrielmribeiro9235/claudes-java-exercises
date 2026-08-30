package br.com.exercises.apidatetime;

import java.time.LocalDate;

public class Main {
    static void main() {
        Person joseph = new Person("Joseph Smith", LocalDate.of(2006, 10, 24));

        System.out.println("Joseph's age: " + joseph.age());

        System.out.println("Has Joseph already had birthday this year? " + (joseph.hadBirthdayThisYear() ? "Yes" : "No"));

        System.out.println("\nJoseph's information:");
        System.out.println(joseph.getStateAsString());
    }
}
