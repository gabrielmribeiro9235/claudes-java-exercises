package br.com.exercises.librarysystem;

public class User {
    private final String name;
    private final String registration;

    public User(String name, String registration) {
        this.name = name;
        this.registration = registration;
    }

    public String getUserAsString() {
        return "Name: " + name + "\nRegistration: " + registration;
    }
}
