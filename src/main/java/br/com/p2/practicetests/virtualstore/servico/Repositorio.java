package br.com.p2.practicetests.virtualstore.servico;

import java.util.List;

public interface Repositorio<K, V> {
    void salvar(K chave, V valor);
    V buscar(K chave);
    List<V> listar();
}
