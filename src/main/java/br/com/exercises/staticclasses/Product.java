package br.com.exercises.staticclasses;

public class Product {
    private final String name;
    private static int counter;

    private Product(String name) {
        this.name = name;
        counter++;
    }

    public static int totalOfProducts() {
        return counter;
    }

    public static Product registerProduct(String name) {
        return new Product(name);
    }

    public String getName() {
        return name;
    }
}
