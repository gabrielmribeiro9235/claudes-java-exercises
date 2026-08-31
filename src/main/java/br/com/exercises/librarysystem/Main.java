package br.com.exercises.librarysystem;

import java.util.Locale;

public class Main {
    static void main() {
        User mary = new User("Mary", "12345");
        User joshua = new User("Joshua", "34557");

        System.out.println("----------------------------------------");
        System.out.println("Users\n");
        System.out.println(mary.getUserAsString());
        System.out.println();
        System.out.println(joshua.getUserAsString());

        Book nineteenEightyFour = new Book("1984", "BOK-001");
        Book animalFarm = new Book("Animal Farm", "BOK-002");
        Book aStudyInScarlet = new Book("A Study in Scarlet", "BOK-003");

        System.out.println("----------------------------------------");
        System.out.println("Books\n");
        System.out.println(nineteenEightyFour.getBookAsString());
        System.out.println();
        System.out.println(animalFarm.getBookAsString());
        System.out.println();
        System.out.println(aStudyInScarlet.getBookAsString());

        Library library = new Library();

        System.out.println("----------------------------------------");
        System.out.println("Loans\n");
        System.out.println(library.getLoansAsString());

        System.out.println("----------------------------------------");
        System.out.println("Mary tries to get 1984");

        Loan loan1984Mary = library.makeLoan(nineteenEightyFour, mary);
        System.out.println(loan1984Mary == null ? "1984 is not available" : "Mary gets 1984");

        System.out.println("----------------------------------------");
        System.out.println("1984 information:\n");
        System.out.println(nineteenEightyFour.getBookAsString());

        System.out.println("----------------------------------------");
        System.out.println("Joshua tries to get 1984 too");

        Loan loan1984Joshua = library.makeLoan(nineteenEightyFour, joshua);
        System.out.println(loan1984Joshua == null ? "1984 is not available" : "Joshua gets 1984");

        System.out.println("----------------------------------------");
        System.out.println("Mary returns 1984");

        int fine = library.returnBook(loan1984Mary);
        System.out.printf(Locale.US, "Fine for late: US$ %.2f\n", (double) fine);
        System.out.printf("Days late: %d\n", fine);

        System.out.println("----------------------------------------");
        System.out.println("1984 information:\n");
        System.out.println(nineteenEightyFour.getBookAsString());

        System.out.println("----------------------------------------");
        System.out.println("Joshua tries to get 1984");

        loan1984Joshua = library.makeLoan(nineteenEightyFour, joshua);
        System.out.println(loan1984Joshua == null ? "1984 is not available" : "Joshua gets 1984");

        System.out.println("----------------------------------------");
        System.out.println("Joshua returns 1984");

        fine = library.returnBook(loan1984Joshua);
        System.out.printf(Locale.US, "Fine for late: US$ %.2f\n", (double) fine);
        System.out.printf("Days late: %d\n", fine);

        System.out.println("----------------------------------------");
        System.out.println("Mary tries to get A Study in Scarlet");
        Loan loanStudyInScarlet = library.makeLoan(aStudyInScarlet, mary);
        System.out.println(loanStudyInScarlet == null ? "A Study in Scarlet is not available" : "Mary gets A Study in Scarlet");

        System.out.println("----------------------------------------");
        System.out.println("Joshua tries to get Animal Farm");
        Loan loanAnimalFarm = library.makeLoan(animalFarm, joshua);
        System.out.println(loanAnimalFarm == null ? "Animal Farm is not available" : "Joshua gets Animal Farm");

        System.out.println("----------------------------------------");
        System.out.println("A Study in Scarlet information:\n");
        System.out.println(aStudyInScarlet.getBookAsString());

        System.out.println("----------------------------------------");
        System.out.println("Animal Farm information:\n");
        System.out.println(animalFarm.getBookAsString());

        System.out.println("----------------------------------------");
        System.out.println("Loans\n");
        System.out.println(library.getLoansAsString());

        System.out.println("----------------------------------------");
        System.out.println("Joshua returns Animal Farm");

        fine = library.returnBook(loanAnimalFarm);
        System.out.printf(Locale.US, "Fine for late: US$ %.2f\n", (double) fine);
        System.out.printf("Days late: %d\n", fine);

        System.out.println("----------------------------------------");
        System.out.println("Mary returns A Study in Scarlet");

        fine = library.returnBook(loanStudyInScarlet);
        System.out.printf(Locale.US, "Fine for late: US$ %.2f\n", (double) fine);
        System.out.printf("Days late: %d\n", fine);

        System.out.println("----------------------------------------");
        System.out.println("A Study in Scarlet information:\n");
        System.out.println(aStudyInScarlet.getBookAsString());

        System.out.println("----------------------------------------");
        System.out.println("Animal Farm information:\n");
        System.out.println(animalFarm.getBookAsString());

        System.out.println("----------------------------------------");
        System.out.println("Loans\n");

        System.out.println(library.getLoansAsString());
        System.out.println("----------------------------------------");
    }
}
