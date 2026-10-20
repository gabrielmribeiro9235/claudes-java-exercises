package br.com.p2.practicetests.virtualstore.pagamento;

public final class PagamentoFactory {
    private PagamentoFactory() {}

    public static Pagamento criar(String forma) {
        if (forma == null) {
            throw new IllegalArgumentException("Forma de pagamento nula");
        }

        forma = forma.toLowerCase();

        if (!forma.startsWith("pix") &&
                !forma.startsWith("boleto") &&
                !forma.startsWith("cartao")) {
            throw new IllegalArgumentException("Forma de pagamento desconhecida: " + forma);
        }


        return switch (forma) {
            case "pix" -> new Pix();
            case "boleto" -> new Boleto();
            default -> {
                if (forma.split(":").length != 2) {
                    throw new IllegalArgumentException("Informe as parcelas: cartao:N");
                }

                String[] dividida = forma.split(":");

                int parcelas;
                try {
                    parcelas = Integer.parseInt(dividida[1]);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Parcelas invalidas: " + dividida[1]);
                }

                yield new CartaoCredito(parcelas);
            }
        };
    }
}
