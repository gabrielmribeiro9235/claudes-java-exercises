package br.com.p2.exercises.library;

import java.util.Locale;

public class Principal {
    static void main() {
        Biblioteca b = new Biblioteca();
        b.cadastrar(new Livro("L1", "Java: Como Programar"));
        b.cadastrar(new Livro("L2", "Clean Code"));
        b.cadastrar(new Revista("R1", "Superinteressante"));
        b.cadastrar(new Dvd("D1", "Matrix"));
        b.cadastrar(new Dvd("D2", "Interestelar"));
        try {
            b.cadastrar(new Livro("L1", "Duplicado"));
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        try {
            b.emprestar("ana", "L1");
            b.emprestar("ana", "R1");
            b.emprestar("ana", "D1");
            b.emprestar("ana", "L2");
        } catch (ItemIndisponivelException e) {
            System.out.println("Indisponivel: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("Regra: " + e.getMessage());
        }
        try {
            b.emprestar("bia", "L1");
        } catch (ItemIndisponivelException e) {
            System.out.println("Indisponivel: " + e.getMessage());
        }
        try {
            b.emprestar("bia", "X9");
        } catch (ItemIndisponivelException e) {
            System.out.println("Indisponivel: " + e.getMessage());
        }
        
        System.out.print(b.relatorio());
        System.out.println("Emprestados de ana: " + b.emprestadosDe("ana"));
        System.out.printf(Locale.US, "Multa L1 (20 dias): %.2f%n", b.devolver("ana", "L1", 20));
        System.out.printf(Locale.US, "Multa R1 (7 dias): %.2f%n", b.devolver("ana", "R1", 7));
        System.out.printf(Locale.US, "Multa D1 (5 dias): %.2f%n", b.devolver("ana", "D1", 5));
        try {
            b.devolver("ana", "D1", 1);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        System.out.print(b.relatorio());
    }
}
