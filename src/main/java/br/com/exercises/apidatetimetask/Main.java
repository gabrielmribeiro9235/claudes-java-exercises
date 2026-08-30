package br.com.exercises.apidatetimetask;

import java.time.LocalDateTime;

public class Main {
    static void main() {
        Task doTheDishes = new Task("Do the dishes", LocalDateTime.now().minusHours(1), LocalDateTime.now().minusMinutes(50));

        System.out.println("\nTask information:");
        System.out.println(doTheDishes.getStateAsString());
        System.out.println("Is task late? " + (doTheDishes.isLate(LocalDateTime.now().minusMinutes(55)) ? "Yes" : "No"));
        System.out.println("Duration of the task: " + doTheDishes.formattedDuration());

    }
}
