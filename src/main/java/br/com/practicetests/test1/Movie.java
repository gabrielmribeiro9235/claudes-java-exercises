package br.com.practicetests.test1;

import java.util.Locale;

public class Movie {
    private final String title;
    private final String genre;
    private final double dailyRate;

    public Movie(String title, String genre, double dailyRate) {
        this.title = title;
        this.genre = genre;
        this.dailyRate = dailyRate;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public String getMovieAsString() {
        return String.format(Locale.US, "Title: %s\nGenre: %s\nDaily rate: US$ %.2f", title, genre, dailyRate);
    }

    public String getTitle() {
        return title;
    }
}
