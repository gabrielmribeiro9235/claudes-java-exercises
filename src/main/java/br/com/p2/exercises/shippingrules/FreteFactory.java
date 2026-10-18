package br.com.p2.exercises.shippingrules;

public class FreteFactory {
    private FreteFactory() {}

    public static RegraFrete para(String tipo) {
        try {
            return switch (tipo.toLowerCase()) {
                case "padrao" -> new FretePadrao();
                case "expresso" -> new FreteExpresso(new FretePadrao());
                case "gratis200" -> new FreteGratisAcimaDe(200, new FretePadrao());
                case "retirada" -> _ -> 0;
                default -> throw new IllegalArgumentException();
            };
        } catch (RuntimeException _) {
            throw new IllegalArgumentException("Tipo de frete desconhecido: " + tipo);
        }
    }
}
