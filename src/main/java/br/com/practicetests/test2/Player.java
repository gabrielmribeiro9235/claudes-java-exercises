package br.com.practicetests.test2;

public class Player {
    private final String name;
    private final int rating;

    public Player(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getPlayerAsString() {
        return "Name: " + name + "\n" +
                "Rating: " + rating;
    }
}
