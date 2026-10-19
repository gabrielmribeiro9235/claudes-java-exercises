package br.com.p2.exercises.library;

import java.util.*;

public class Biblioteca {
    private final Map<String, Item> acervo;
    private final Map<String, List<Item>> emprestimos;

    public Biblioteca() {
        acervo = new LinkedHashMap<>();
        emprestimos = new HashMap<>();
    }

    public void cadastrar(Item item) {
        if (acervo.containsKey(item.getCodigo())) {
            throw new IllegalArgumentException("Codigo ja cadastrado: " + item.getCodigo());
        }

        acervo.put(item.getCodigo(), item);
    }

    public void emprestar(String usuario, String codigo) throws ItemIndisponivelException {
        if (!acervo.containsKey(codigo)) {
            throw new ItemIndisponivelException("Item inexistente: " + codigo);
        }

        if (acervo.get(codigo).isEmprestado()) {
            throw new ItemIndisponivelException("Item ja emprestado: " + codigo);
        }

        if (emprestimos.get(usuario) != null && emprestimos.get(usuario).size() == 3) {
            throw new IllegalStateException("Limite de 3 emprestimos atingido por " + usuario);
        }

        Item item = acervo.get(codigo);
        item.setEmprestado(true);

        if (emprestimos.get(usuario) == null) {
            List<Item> itens = new ArrayList<>();
            itens.add(item);
            emprestimos.put(usuario, itens);
        } else {
            emprestimos.get(usuario).add(item);
        }
    }

    public double devolver(String usuario, String codigo, int dias) {
        Objects.requireNonNull(usuario, "Usuário não pode ser nulo");
        Objects.requireNonNull(codigo, "Código não pode ser nulo");

        List<Item> itensDoUsuario = emprestimos.get(usuario);

        if (itensDoUsuario == null) {
            throw new IllegalArgumentException(usuario + " nao possui o item " + codigo);
        }

        Item itemEmprestado = itensDoUsuario.stream().filter(i -> codigo.equals(i.getCodigo())).findFirst().orElse(null);
        if (itemEmprestado == null) {
            throw new IllegalArgumentException(usuario + " nao possui o item " + codigo);
        }

        itemEmprestado.setEmprestado(false);

        itensDoUsuario.remove(itemEmprestado);

        emprestimos.replace(usuario, itensDoUsuario);

        return itemEmprestado.calcularMulta(dias);
    }

    public List<Item> emprestadosDe(String usuario) {
        Objects.requireNonNull(usuario, "Usuário não pode ser nulo");

        return emprestimos.get(usuario) != null ? List.copyOf(emprestimos.get(usuario)) : Collections.emptyList();
    }

    public String relatorio() {
        StringBuilder builder = new StringBuilder();

        acervo.forEach((key, value) ->
                builder.append(
                        String.format("%-6s | %-8s | %-22s | %s%n",
                                key,
                                value.getClass().getSimpleName(),
                                value.getTitulo(),
                                (value.isEmprestado() ? "EMPRESTADO" : "DISPONIVEL")
                        )
                )
        );

        return builder.toString();
    }
}
