package br.com.p2.practicetests.arena.modelo;

public interface Curandeiro {
    int curar(Personagem alvo) throws CuraIndisponivelException;
}
