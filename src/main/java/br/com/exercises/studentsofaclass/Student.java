package br.com.exercises.studentsofaclass;

import java.util.Locale;
import java.util.StringJoiner;

public class Student {
    private final String name;
    private final String registration;
    private double[] grades;
    private int numberOfGrades;

    public Student(String name, String registration) {
        this.name = name;
        this.registration = registration;
        grades = new double[10];
    }

    public String getName() {
        return name;
    }

    public String getRegistration() {
        return registration;
    }

    private void increaseArraySize() {
        double[] copy = grades.clone();

        grades = new double[numberOfGrades * 2];

        for (int i = 0; i < numberOfGrades; i++) {
            grades[i] = copy[i];
        }
    }

    public void addGrade(double grade) {
        if (numberOfGrades == grades.length) {
            increaseArraySize();
        }

        grades[numberOfGrades++] = grade;
    }

    public double average() {
        double sum = 0.0;

        for (int i = 0; i < numberOfGrades; i++) {
            sum += grades[i];
        }

        return sum / numberOfGrades;
    }

    public String getStudentAsString() {
        StringBuilder builder = new StringBuilder("Name: ");

        builder.append(name).append("\n").append("Registration: ").append(registration).append("\n");

        StringJoiner joiner = new StringJoiner(", ", "[", "]");

        for (int i = 0; i < numberOfGrades; i++) {
            joiner.add(String.format(Locale.US, "%.2f", grades[i]));
        }

        builder.append("Grades: ").append(joiner);

        builder.append("\n").append("Average: ").append(String.format(Locale.US, "%.2f", average()));

        return builder.toString();
    }
}
