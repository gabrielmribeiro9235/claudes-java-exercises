package br.com.exercises.studentsofaclass;

import java.util.StringJoiner;

public class StudentsClass {
    private Student[] students;
    private int numberOfStudents;

    public StudentsClass() {
        students = new Student[10];
    }

    public StudentsClass(Student[] students) {
        this.students = students;
    }

    private void increaseArraySize() {
        Student[] copy = students.clone();

        students = new Student[numberOfStudents * 2];

        for (int i = 0; i < numberOfStudents; i++) {
            students[i] = copy[i];
        }
    }

    private boolean isStudentAlreadyInTheClass(Student student) {
        for (int i = 0; i < numberOfStudents; i++) {
            if (students[i].getRegistration().equals(student.getRegistration())) {
                return true;
            }
        }

        return false;
    }

    public void addStudent(Student student) {
        if (student == null || isStudentAlreadyInTheClass(student)) return;

        if (numberOfStudents == students.length) {
            increaseArraySize();
        }

        students[numberOfStudents++] = student;
    }

    public Student getBestStudent() {
        if (numberOfStudents == 0) return null;

        Student bestStudent = students[0];

        for (int i = 0; i < numberOfStudents; i++) {
            if (students[i].average() > bestStudent.average()) {
                bestStudent = students[i];
            }
        }

        return bestStudent;
    }

    public double overallAverage() {
        double sum = 0.0;

        for (int i = 0; i < numberOfStudents; i++) {
             sum += students[i].average();
        }

        return sum / numberOfStudents;
    }

    public String getClassAsString() {
        StringJoiner joiner = new StringJoiner("\n-------------------------------------\n");

        for (int i = 0; i < numberOfStudents; i++) {
            joiner.add(students[i].getStudentAsString());
        }

        return joiner.toString();
    }
}
