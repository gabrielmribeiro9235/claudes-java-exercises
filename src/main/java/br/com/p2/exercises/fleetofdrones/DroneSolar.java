package br.com.p2.exercises.fleetofdrones;

public final class DroneSolar extends DroneLeve {
    public DroneSolar(String id, int bateria) {
        super(id, bateria);
    }

    @Override
    public void entregar(Pedido pedido) {
        super.entregar(pedido);

        bateria = Math.min(100, bateria + 5);
    }

    @Override
    protected double custoPorKm() {
        return 0.3;
    }
}
