package br.com.exercises.studentsofaclass;

import java.util.Locale;

public class Main {
    static void main() {
        StudentsClass firstGrade = new StudentsClass();

        Student mary = new Student("Mary", "001");
        Student jonny = new Student("Jonny", "002");
        Student joseph = new Student("Joseph", "003");
        Student stephen = new Student("Stephen", "004");
        Student judit = new Student("Judit", "005");

        for (int i = 0; i < 4; i++) {
            mary.addGrade(Math.random() * 10);
            jonny.addGrade(Math.random() * 10);
            joseph.addGrade(Math.random() * 10);
            stephen.addGrade(Math.random() * 10);
            judit.addGrade(Math.random() * 10);
        }

        firstGrade.addStudent(mary);
        firstGrade.addStudent(jonny);
        firstGrade.addStudent(joseph);
        firstGrade.addStudent(stephen);
        firstGrade.addStudent(judit);

        System.out.println("-------------------------------------");

        System.out.println("1st Grade students:\n");

        System.out.println(firstGrade.getClassAsString());

        System.out.println("-------------------------------------");

        System.out.println("Best student:");

        System.out.println(firstGrade.getBestStudent().getStudentAsString());

        System.out.println("-------------------------------------");

        System.out.printf(Locale.US, "Overall average: %.2f\n", firstGrade.overallAverage());
    }
}
