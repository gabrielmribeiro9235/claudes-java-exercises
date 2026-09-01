package br.com.practicetests.test1;

import java.util.Locale;

public class Principal {
    static void main() {
        Client mary = new Client("Mary", "123456789");
        Client joshua = new Client("Joshua", "674926348");
        Client jonny = new Client("Jonny", "097876656");

        System.out.println("----------------------------------");
        System.out.println("Clients\n");

        System.out.println(mary.getClientAsString());
        System.out.println();
        System.out.println(joshua.getClientAsString());
        System.out.println();
        System.out.println(jonny.getClientAsString());

        Movie titanic = new Movie("Titanic", "disaster", 25.00);
        Movie theHangover1 = new Movie("The Hangover 1", "comedy", 40.39);
        Movie theHangover2 = new Movie("The Hangover 2", "comedy", 40.39);
        Movie theHangover3 = new Movie("The Hangover 3", "comedy", 40.39);

        System.out.println("----------------------------------");
        System.out.println("Movies\n");

        System.out.println(titanic.getMovieAsString());
        System.out.println();
        System.out.println(theHangover1.getMovieAsString());
        System.out.println();
        System.out.println(theHangover2.getMovieAsString());
        System.out.println();
        System.out.println(theHangover3.getMovieAsString());

        MovieRental movieRental = new MovieRental();

        Rental maryTitanic = movieRental.rentMovie(mary, titanic, 10);
        Rental joshuaTheHangover1 = movieRental.rentMovie(joshua, theHangover1, 3);
        Rental joshuaTheHangover2 = movieRental.rentMovie(joshua, theHangover2, 4);
        Rental jonnyTheHangover3 = movieRental.rentMovie(joshua, theHangover3, 5);

        System.out.println("----------------------------------");
        System.out.println("Rentals\n");

        System.out.println(maryTitanic.rentalAsString());
        System.out.println(joshuaTheHangover1.rentalAsString());
        System.out.println(joshuaTheHangover2.rentalAsString());
        System.out.println(jonnyTheHangover3.rentalAsString());

        movieRental.returnMovie(1);
        movieRental.returnMovie(2);
        movieRental.returnMovie(3);
        movieRental.returnMovie(4);

        System.out.println("----------------------------------");
        System.out.println("Rentals\n");

        System.out.println(movieRental.getRentalsAsString());

        System.out.println("----------------------------------");
        System.out.println("Late rentals\n");

        Rental[] lateRentals = movieRental.lateRentals();

        for (Rental lateRental : lateRentals) {
            System.out.println(lateRental.rentalAsString());
        }

        System.out.println("----------------------------------");
        System.out.printf(Locale.US, "Total raised: US$ %.2f\n", movieRental.totalRaised());
    }
}
