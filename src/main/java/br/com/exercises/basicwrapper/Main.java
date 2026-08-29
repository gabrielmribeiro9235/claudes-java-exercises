package br.com.exercises.basicwrapper;

import java.util.StringJoiner;

public class Main {
    static void main() {
        Integer[] values = {10, 20, 30, null, 40, 50};

        double average = Statistic.average(values);

        StringJoiner joiner = new StringJoiner(", ", "[", "]");

        for (Integer value : values) {
            joiner.add(value != null ? value.toString() : null);
        }

        System.out.println("------------------------------------------");

        System.out.println("Values: " + joiner);

        System.out.println("Average of the values: " + average);

        System.out.println("------------------------------------------");
        System.out.println("Comparing two Integer objects using \"==\":");

        Integer value1 = 255;
        Integer value2 = 255;

        System.out.println("Is Integer(255) == Integer(255)?\n" + (value1 == value2));

        System.out.println("------------------------------------------");
        System.out.println("Comparing two Integer objects using \".equals()\":");

        System.out.println("Is Integer(255) equals Integer(255)?\n" + (value1.equals(value2)));

        /*
        Comparing two Integer objects using == may return false (if the value is outside the Integer cache) because
        == compares references to determine whether they point to the same object, while .equals() compares
        the values of the objects.
        So, in the example above, value1 == value2 checks whether value1 and value2 refer to the same object,
        while value1.equals(value2) checks whether the two objects have the same value.
        */
    }
}
