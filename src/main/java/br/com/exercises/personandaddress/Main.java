package br.com.exercises.personandaddress;

public class Main {
    static void main() {
        Person johnny = new Person("Johnny B. Goode", "123-45-6789", "5th Avenue", 40, "New York", "10011");


        System.out.println("Personal data of Johnny:");
        System.out.println(johnny.showData());
        System.out.println("-------------------------------------------");

        johnny.setAddress("Wall Street", 101, "New York", "10005");
        System.out.println("Johnny has changed his address...");
        System.out.println("-------------------------------------------");

        System.out.println("New personal data of Johnny:");
        System.out.println(johnny.showData());
        System.out.println("-------------------------------------------");
    }
}
