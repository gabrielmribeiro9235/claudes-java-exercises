package br.com.exercises.personandaddress;

import java.util.StringJoiner;

public class Person {
    private final String name;
    private final String ssn;
    private Address address;

    public Person(String name, String ssn, String street, int number, String city, String zipCode) {
        this.name = name;
        this.ssn = ssn;

        address = new Address(street, number, city, zipCode);
    }

    public void setAddress(String street, int number, String city, String zipCode) {
        this.address = new Address(street, number, city, zipCode);
    }

    public String showData() {
        StringJoiner joiner = new StringJoiner("\n");

        StringBuilder builder = new StringBuilder();
        joiner.add(builder.append("Name: ").append(name));

        builder.setLength(0);
        joiner.add(builder.append("SSN: ").append(ssn));

        builder.setLength(0);
        joiner.add(builder.append("Address: ").append(address.getAddressAsString()));

        return joiner.toString();
    }
}
