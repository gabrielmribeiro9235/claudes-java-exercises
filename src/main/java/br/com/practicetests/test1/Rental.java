package br.com.practicetests.test1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Rental {
    private final int id;
    private final Client client;
    private final Movie movie;
    private final LocalDate withdrawalDate;
    private final LocalDate expectedReturnDate;
    private LocalDate actualReturnDate;

    public Rental(int id, Client client, Movie movie, int days) {
        this.id = id;
        this.client = client;
        this.movie = movie;
        withdrawalDate = LocalDate.now();
        expectedReturnDate = withdrawalDate.plusDays(days);
    }

    public int getId() {
        return id;
    }

    public void registerReturn() {
        actualReturnDate = LocalDate.now();
    }

    public boolean isLate() {
        if (actualReturnDate != null) {
            return actualReturnDate.isAfter(expectedReturnDate);
        }
        return LocalDate.now().isAfter(expectedReturnDate);
    }

    public int daysLate() {
        if (!isLate()) return 0;

        return (int) ChronoUnit.DAYS.between(expectedReturnDate, (actualReturnDate != null ? actualReturnDate : LocalDate.now()));
    }

    public double totalValue() {
        int days = (int) ChronoUnit.DAYS.between(withdrawalDate, expectedReturnDate);

        return days * movie.getDailyRate() + (isLate() ? daysLate() * 0.2 * movie.getDailyRate() : 0);
    }

    public String rentalAsString() {
        return "Rental{" +
                "id=" + id +
                ", client=" + client.getName() +
                ", movie=" + movie.getTitle() +
                ", withdrawalDate=" + withdrawalDate +
                ", expectedReturnDate=" + expectedReturnDate +
                ", actualReturnDate=" + actualReturnDate +
                '}';
    }
}
