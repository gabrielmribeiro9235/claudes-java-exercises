package br.com.exercises.personandaddress;

import java.util.StringJoiner;

public class Address {
    private final String street;
    private final int number;
    private final String city;
    private final String zipCode;

    public Address(String street, int number, String city, String zipCode) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getAddressAsString() {
        StringJoiner joiner = new StringJoiner(", ");
        StringBuilder builder = new StringBuilder();

        builder.append(number).append(" ").append(street);

        return joiner.add(builder).add(city).add(zipCode).toString();
    }
}
