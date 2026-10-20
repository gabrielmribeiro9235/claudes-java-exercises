package br.com.p2.practicetests.virtualstore.servico;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RepositorioMemoria<K, V> implements Repositorio<K, V> {
    private final Map<K, V> dados;

    public RepositorioMemoria() {
        dados = new LinkedHashMap<>();
    }

    @Override
    public void salvar(K chave, V valor) {
        dados.put(chave, valor);
    }

    @Override
    public V buscar(K chave) {
        return dados.get(chave);
    }

    @Override
    public List<V> listar() {
        return List.copyOf(dados.values());
    }
}
