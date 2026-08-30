package br.com.exercises.apidatetime;

import java.time.LocalDate;
import java.time.Period;
import java.util.StringJoiner;

public class Person {
    private final String name;
    private final LocalDate birth;

    public Person(String name, LocalDate birth) {
        this.name = name;
        this.birth = birth;
    }

    public int age() {
        return Period.between(birth, LocalDate.now()).getYears();
    }

    public boolean hadBirthdayThisYear() {
        LocalDate today = LocalDate.now();

        if (today.getMonthValue() > birth.getMonthValue()) {
            return true;
        } else if (today.getMonthValue() < birth.getMonthValue()) {
            return false;
        }

        return today.getDayOfMonth() >= birth.getDayOfMonth();
    }

    public String getStateAsString() {
        StringBuilder builder = new StringBuilder();
        StringJoiner joiner = new StringJoiner("\n");

        builder.append("Name: ").append(name);
        joiner.add(builder);

        builder.setLength(0);

        builder.append("Date of birth: ").append(birth);
        joiner.add(builder);

        return joiner.toString();
    }
}
