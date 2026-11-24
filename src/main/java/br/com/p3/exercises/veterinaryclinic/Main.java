
package br.com.p3.exercises.veterinaryclinic;

import java.time.LocalDate;

public class Main {

    private static final String URL =
            "jdbc:sqlite:src/main/java/br/com/p3/exercises/veterinaryclinic/clinic.db?foreign_keys=on";

    public static void main(String[] args) {
        DatabaseInitializer.init();
        VeterinaryClinic clinic = new VeterinaryClinic();

        Tutor tutor1 = new Tutor("Carlos", "11999990001");
        tutor1.adicionarPet(new Cachorro("Rex", tutor1));
        tutor1.adicionarPet(new Gato("Mimi", tutor1));

        Tutor tutor2 = new Tutor("Ana", "11999990002");
        tutor2.adicionarPet(new Cachorro("Thor", tutor2));

        Tutor tutor3 = new Tutor("Pedro", "11999990003");
        tutor3.adicionarPet(new Gato("Luna", tutor3));
        tutor3.adicionarPet(new Outras("Pipoca", tutor3));

        Tutor tutor4 = new Tutor("Mariana", "11999990004");
        tutor4.adicionarPet(new Cachorro("Bob", tutor4));

        System.out.println("=== CADASTRO DE TUTORES ===");

        clinic.adicionarTutor(tutor1);
        clinic.adicionarTutor(tutor2);
        clinic.adicionarTutor(tutor3);
        clinic.adicionarTutor(tutor4);

        System.out.println("Tutores cadastrados com sucesso.");

        System.out.println("\n=== BUSCA DE TUTOR ===");

        Tutor tutor = clinic.getTutor(1);

        if (tutor != null) {
            System.out.println(tutor);
            tutor.getPets().forEach(System.out::println);
        }

        System.out.println("\n=== CADASTRO DE CONSULTAS ===");

        Pet rex = tutor.getPets().stream()
                .filter(p -> p.getNome().equals("Rex"))
                .findFirst()
                .orElseThrow();

        Pet mimi = tutor.getPets().stream()
                .filter(p -> p.getNome().equals("Mimi"))
                .findFirst()
                .orElseThrow();

        Tutor ana = clinic.getTutor(2);
        Pet thor = ana.getPets().getFirst();

        Tutor pedro = clinic.getTutor(3);
        Pet luna = pedro.getPets().stream()
                .filter(p -> p.getNome().equals("Luna"))
                .findFirst()
                .orElseThrow();

        clinic.adicionarConsulta(new Consulta(rex, LocalDate.of(2026, 1, 10),"Vacinação", 150.00));

        clinic.adicionarConsulta(new Consulta(rex, LocalDate.of(2026, 3, 15),"Exames", 300.00));

        clinic.adicionarConsulta(new Consulta(mimi, LocalDate.of(2026, 2, 20),"Consulta de rotina", 100.00));

        clinic.adicionarConsulta(new Consulta(thor, LocalDate.of(2026, 4, 5),"Cirurgia", 1200.00));

        clinic.adicionarConsulta(new Consulta(luna, LocalDate.of(2026, 5, 12),"Tratamento", 500.00));

        System.out.println("Consultas cadastradas com sucesso.");

        System.out.println("\n=== HISTÓRICO DE CONSULTAS ===");

        clinic.getConsultas(rex).forEach(System.out::println);

        System.out.println("\n=== TOP 3 TUTORES COM MAIS GASTOS ===");

        clinic.getTresTutoresComMaisGastos()
                .forEach(System.out::println);

        System.out.println("\n=== REMOÇÃO DE PET ===");

        clinic.removerPet(rex);

        System.out.println("Pet removido: " + rex.getNome());

        System.out.println("\n=== HISTÓRICO APÓS REMOÇÃO ===");

        System.out.println(clinic.getConsultas(rex));

        System.out.println("\n=== TUTOR APÓS REMOÇÃO ===");

        clinic.getTutor(1).getPets().forEach(System.out::println);
    }
}
