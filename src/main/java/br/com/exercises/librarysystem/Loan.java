package br.com.exercises.librarysystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class Loan {
    private final Book book;
    private final User user;
    private final LocalDate loanDate;
    private final LocalDate returnDate;

    public Loan(Book book, User user) {
        this.book = book;
        this.user = user;
        loanDate = LocalDate.now();
        returnDate = loanDate.plusDays(7);
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public Book getBook() {
        return book;
    }

    public String getLoanAsString() {
        StringJoiner joiner = new StringJoiner("\n");

        StringBuilder builder = new StringBuilder();

        joiner.add("Book:").add(builder.append("Title: ").append(book.getTitle()));
        builder.setLength(0);
        joiner.add(builder.append("Code: ").append(book.getCode()));
        joiner.add("User:").add(user.getUserAsString());
        builder.setLength(0);
        joiner.add(builder.append("Loan date: ").append(loanDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        builder.setLength(0);
        joiner.add(builder.append("Return date: ").append(returnDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));

        return joiner.toString();
    }
}
