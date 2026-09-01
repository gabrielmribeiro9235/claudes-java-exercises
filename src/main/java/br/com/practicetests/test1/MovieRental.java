package br.com.practicetests.test1;

import java.util.StringJoiner;

public class MovieRental {
    private Rental[] rentals;
    private int numberOfRentals;
    private int nextId;

    public MovieRental() {
        rentals = new Rental[10];
        nextId = 1;
    }

    private void increaseArraySize() {
        Rental[] copy = rentals.clone();

        rentals = new Rental[numberOfRentals * 2];

        for (int i = 0; i < numberOfRentals; i++) {
            rentals[i] = copy[i];
        }
    }

    public Rental rentMovie(Client client, Movie movie, int days) {
        if (numberOfRentals == rentals.length) increaseArraySize();

        rentals[numberOfRentals++] = new Rental(nextId++, client, movie, days);

        return rentals[numberOfRentals - 1];
    }

    public void returnMovie(int rentalId) {
        if (rentalId < 0) return;

        int movieIdx = -1;

        for (int i = 0; i < numberOfRentals; i++) {
            if (rentalId == rentals[i].getId()) {
                movieIdx = i;
                rentals[i].registerReturn();
                break;
            }
        }
    }

    public Rental[] lateRentals() {
        Rental[] late;
        int numberOfLateRentals = 0;

        for (int i = 0; i < numberOfRentals; i++) {
            if (rentals[i].isLate()) numberOfLateRentals++;
        }

        if (numberOfLateRentals == 0) return new Rental[0];

        late = new Rental[numberOfLateRentals];
        numberOfLateRentals = 0;

        for (int i = 0; i < numberOfRentals; i++) {
            if (rentals[i].isLate()) late[numberOfLateRentals++] = rentals[i];
        }

        return late;
    }

    public double totalRaised() {
        double total = 0.0;

        for (int i = 0; i < numberOfRentals; i++) {
            total += rentals[i].totalValue();
        }

        return total;
    }

    public String getRentalsAsString() {
        StringJoiner joiner = new StringJoiner("\n");

        for (int i = 0; i < numberOfRentals; i++) {
            joiner.add(rentals[i].rentalAsString());
        }

        return joiner.toString();
    }
}
