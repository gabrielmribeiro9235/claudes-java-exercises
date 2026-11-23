package br.com.p3.exercises.veterinaryclinic;

import java.time.LocalDate;
import java.util.Objects;

public class Consulta {
    private static int ID_ATUAL = 1;

    private final int id;
    private final Pet pet;
    private final LocalDate data;
    private final String motivo;
    private final double valor;

    public Consulta(Pet pet, LocalDate data, String motivo, double valor) {
        this(ID_ATUAL++, pet, data, motivo, valor);
    }

    public Consulta(int id, Pet pet, LocalDate data, String motivo, double valor) {
        Objects.requireNonNull(pet, "Pet não pode ser nulo.");
        Objects.requireNonNull(data, "Data não pode ser nula.");
        if (motivo == null || motivo.isBlank()) {
            throw new IllegalArgumentException("Motivo não pode ser nulo ou vazio.");
        }
        this.id = id;
        this.pet = pet;
        this.data = data;
        this.motivo = motivo;
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Consulta consulta = (Consulta) o;
        return id == consulta.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", pet=" + pet +
                ", data=" + data +
                ", motivo='" + motivo + '\'' +
                ", valor=" + valor +
                '}';
    }

    public int getId() {
        return id;
    }

    public Pet getPet() {
        return pet;
    }

    public LocalDate getData() {
        return data;
    }

    public String getMotivo() {
        return motivo;
    }

    public double getValor() {
        return valor;
    }
}
