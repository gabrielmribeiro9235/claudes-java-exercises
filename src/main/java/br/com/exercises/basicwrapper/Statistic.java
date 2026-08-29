package br.com.exercises.basicwrapper;

public class Statistic {
    public static double average(Integer[] values) {
        int sum = 0;

        for (Integer value : values) {
            if (value != null) {
                sum += value;
            }
        }

        return (double) sum / values.length;
    }
}
