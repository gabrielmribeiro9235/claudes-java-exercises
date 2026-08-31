package br.com.exercises.librarysystem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.StringJoiner;

public class Library {
    private Loan[] loans;
    private int numberOfLoans;

    public Library() {
        loans = new Loan[100];
    }

    private void increaseArraySize() {
        Loan[] copy = loans.clone();

        loans = new Loan[numberOfLoans * 2];

        for (int i = 0; i < numberOfLoans; i++) {
            loans[i] = copy[i];
        }
    }

    public Loan makeLoan(Book book, User user) {
        if (numberOfLoans == loans.length) {
            increaseArraySize();
        }

        if (!book.isAvailable()) return null;

        loans[numberOfLoans++] = new Loan(book, user);

        book.setIsAvailable(false);

        return loans[numberOfLoans - 1];
    }

    public int returnBook(Loan loan) {
        int indexOfLoan = -1;

        for (int i = 0; i < numberOfLoans; i++) {
            if (loans[i].equals(loan)) {
                indexOfLoan = i;
                break;
            }
        }

        if (indexOfLoan == -1) return -1;

        int fine = 0;

        if (LocalDate.now().isAfter(loan.getReturnDate())) {
            fine = (int) ChronoUnit.DAYS.between(loan.getReturnDate(), LocalDate.now());
        }

        loan.getBook().setIsAvailable(true);

        for (int i = indexOfLoan; i < numberOfLoans; i++) {
            loans[i] = loans[i + 1];
        }

        loans[numberOfLoans - 1] = null;
        numberOfLoans--;

        return fine;
    }

    public String getLoansAsString() {
        StringJoiner joiner = new StringJoiner("\n----------------------------------------\n");

        for (int i = 0; i < numberOfLoans; i++) {
            joiner.add(loans[i].getLoanAsString());
        }

        return joiner.toString();
    }
}
