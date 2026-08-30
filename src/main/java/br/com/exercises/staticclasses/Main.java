package br.com.exercises.staticclasses;

public class Main {
    private static final double EXCHANGE_RATE_IN_AUGUST_29TH = 5.19;
    static void main() {
        System.out.println("---------------------------------------------");
        System.out.println("Testing the Product class\n");

        Product[] products = new Product[5];

        products[0] = Product.registerProduct("mouse");
        products[1] = Product.registerProduct("monitor");
        products[2] = Product.registerProduct("computer");
        products[3] = Product.registerProduct("keyboard");
        products[4] = Product.registerProduct("mousepad");

        System.out.println("Products registered:");

        for (Product product : products) {
            System.out.println(product.getName());
        }

        System.out.println("\nTotal of products registered: " + Product.totalOfProducts());
        System.out.println("---------------------------------------------");

        System.out.println("Testing the CurrencyConverter class\n");

        System.out.printf("R$  10.00 = US$ %.2f\n", CurrencyConverter.reaisToDolar(10, EXCHANGE_RATE_IN_AUGUST_29TH));
        System.out.printf("US$ 10.00 = R$  %.2f\n", CurrencyConverter.dolarToReais(10, EXCHANGE_RATE_IN_AUGUST_29TH));

        /*
        A utility class shouldn't be instantiated because it typically contains only static methods. These methods don't
        depend on the state of an object of the class; instead, they work like regular functions and can be called
        directly using the class itself. The constructor is private to prevent instances of the utility class
        from being created, since creating an object would serve no purpose.
        */
    }
}
