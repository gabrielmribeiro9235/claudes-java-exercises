package br.com.p2.practicetests.arena.jogo;

import br.com.p2.practicetests.arena.modelo.CuraIndisponivelException;
import br.com.p2.practicetests.arena.modelo.Curandeiro;
import br.com.p2.practicetests.arena.modelo.Personagem;

import java.util.*;

public class Arena {
    private final Map<String, List<Personagem>> times;
    private final Map<String, Integer> danoCausado;
    private int rodada;

    public Arena(String timeA, String timeB) {
        times = new LinkedHashMap<>();
        danoCausado = new TreeMap<>();

        times.put(timeA, new ArrayList<>());
        times.put(timeB, new ArrayList<>());
    }

    public int getRodada() {
        return rodada;
    }

    public void adicionar(String time, Personagem p) {
        if (!times.containsKey(time)) {
            throw new IllegalArgumentException("Time inexistente: " + time);
        }

        times.values().forEach(l -> {
            if (l.contains(p)) {
                throw new IllegalArgumentException("Personagem repetido: " + p.getNome());
            }
        });

        List<Personagem> personagensDoTime = times.get(time);
        personagensDoTime.add(p);

        times.put(time, personagensDoTime);
        danoCausado.put(p.getNome(), 0);
    }

    public Iterator<Personagem> iterador(String time) {
        Objects.requireNonNull(time, "time não pode ser nulo.");
        if (!times.containsKey(time)) {
            return null;
        }

        return times.get(time).iterator();
    }

    public boolean timeDerrotado(String time) {
        if (vencedor() == null) return false;

        return !vencedor().equals(time);
    }

    public String vencedor() {
        Set<String> nomeTimes = times.keySet();

        for (String time : nomeTimes) {
            if (times.get(time).stream().filter(Personagem::estaVivo).toList().isEmpty()) {
                return nomeTimes.stream().filter(s -> !s.equals(time)).findFirst().orElse(null);
            }
        }

        return null;
    }

    private Personagem getMenorVida(String time) {
        return times.get(time).stream()
                .filter(p -> p.estaVivo() && p.getVida() < p.getVidaMaxima() / 2)
                .min(Personagem::compareTo)
                .orElse(null);
    }

    private String ataque(String atacante, String defensor) {
        StringBuilder builder = new StringBuilder();

        Iterator<Personagem> iterator = iterador(atacante);
        while (iterator.hasNext()) {
            Personagem personagem = iterator.next();

            if (timeDerrotado(defensor)) return builder.toString();

            if (!personagem.estaVivo()) continue;

            if (personagem instanceof Curandeiro) {
                Personagem menorVida = getMenorVida(atacante);

                if (menorVida != null) {
                    try {
                        int curaEfetiva = ((Curandeiro) personagem).curar(menorVida);
                        builder.append(String.format("%s cura %s (+%d) vida=%d\n",
                                personagem.getNome(),
                                menorVida.getNome(),
                                curaEfetiva,
                                menorVida.getVida()
                        ));

                        continue;
                    } catch (CuraIndisponivelException e) {
                        builder.append(personagem.getNome()).append(" nao pode curar: ").append(e.getMessage()).append("\n");
                    }
                }
            }

            Personagem primeiroVivo = times.get(defensor).stream().filter(Personagem::estaVivo).findFirst().orElse(null);

            if (primeiroVivo == null) return builder.toString();

            int dano = personagem.atacar();
            primeiroVivo.receberDano(dano);

            danoCausado.put(personagem.getNome(), danoCausado.get(personagem.getNome()) + dano);

            builder.append(String.format("%s ataca %s (-%d) vida=%d%s\n",
                    personagem.getNome(),
                    primeiroVivo.getNome(),
                    dano,
                    primeiroVivo.getVida(),
                    primeiroVivo.getVida() == 0 ? " [DERROTADO]" : ""
            ));
        }

        return builder.toString();
    }

    public String executarRodada() {
        String timeA = times.keySet().stream().findFirst().orElse(null);
        String timeB = times.keySet().stream().skip(1).findFirst().orElse(null);

        if (timeA == null || timeB == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder();

        rodada++;
        builder.append("--- Rodada ").append(rodada).append(" ---\n");

        builder.append(ataque(timeA, timeB));
        builder.append(ataque(timeB, timeA));

        return builder.toString();
    }

    public List<Personagem> ranking() {
        return times.values().stream().flatMap(Collection::stream).sorted(Personagem::compareTo).toList();
    }

    public Map<String, Integer> danoCausado() {
        return new TreeMap<>(danoCausado);
    }
}
