package br.com.practicetests.test1;

import java.util.Locale;

public class Client {
    private final String name;
    private final String ssn;

    public Client(String name, String ssn) {
        this.name = name;
        this.ssn = ssn;
    }

    public String getClientAsString() {
        return String.format("Name: %s\nSSN: %s", name, ssn);
    }

    public String getName() {
        return name;
    }
}
