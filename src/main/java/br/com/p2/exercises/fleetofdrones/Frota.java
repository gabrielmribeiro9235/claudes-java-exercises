package br.com.p2.exercises.fleetofdrones;

import java.util.*;

public class Frota {
    private final Map<String, Drone> drones;
    private final Map<String, Pedido> pendentes;

    public Frota() {
        drones = new TreeMap<>();
        pendentes = new HashMap<>();
    }

    public void adicionar(Drone drone) {
        Objects.requireNonNull(drone, "Drone não pode ser nulo.");

        if (drones.containsKey(drone.id())) {
            throw new IllegalArgumentException ("Drone repetido: " + drone.id());
        }

        drones.put(drone.id(), drone);
    }

    public void solicitar(Pedido pedido) {
        Objects.requireNonNull(pedido, "Pedido não pode ser nulo.");

        if (pendentes.containsKey(pedido.getId())) {
            throw new IllegalArgumentException("Pedido repetido: " + pedido.getId());
        }

        pendentes.put(pedido.getId(), pedido);
    }

    public void recarregar(String id) {
        if (!drones.containsKey(id)) return;

        drones.get(id).recarregar();
    }

    public static <T extends Entregador> T maisBarato(Collection<T> candidatos, Pedido pedido) {
        Objects.requireNonNull(candidatos);
        Objects.requireNonNull(pedido);

        return candidatos.stream().min((c1, c2) -> {
            if (c1.custo(pedido) == c2.custo(pedido)) {
                return c1.id().compareTo(c2.id());
            }

            return Double.compare(c1.custo(pedido), c2.custo(pedido));
        }).orElse(null);
    }

    public Drone atribuir(Pedido pedido) throws SemDroneDisponivelException {
        Objects.requireNonNull(pedido, "Pedido não pode ser nulo.");

        Drone entregador = maisBarato(drones.values().stream().filter(d -> d.podeEntregar(pedido)).toList(), pedido );

        if (entregador == null) {
            throw new SemDroneDisponivelException("nenhum drone pode atender " + pedido.getId());
        }

        entregador.entregar(pedido);

        return entregador;
    }

    public String processarPendentes() {
        StringBuilder builder = new StringBuilder();

        Iterator<Map.Entry<String, Pedido>> iterator = pendentes.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Pedido> pedido = iterator.next();

            try {
                Drone entregador = atribuir(pedido.getValue());

                builder.append(
                        String.format(Locale.US, "%s -> %s (custo %.2f)\n",
                                pedido.getValue().getId(),
                                entregador.id(),
                                entregador.custo(pedido.getValue())
                        )
                );

                iterator.remove();
            } catch (SemDroneDisponivelException e) {
                builder.append(
                        String.format("%s -> PENDENTE (%s)\n",
                                pedido.getValue().getId(),
                                e.getMessage()
                        )
                );
            }
        }

        return builder.toString();
    }

    public int totalPendentes() {
        return pendentes.size();
    }

    public List<Drone> dronesPorBateria() {
        return drones.values().stream().sorted(Drone::compareTo).toList();
    }
}
