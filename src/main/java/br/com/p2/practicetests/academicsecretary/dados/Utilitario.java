package br.com.p2.practicetests.academicsecretary.dados;

import java.util.List;

public final class Utilitario {
    private Utilitario() {}

    public static List<String> carregarNotas() {
        return List.of(
                "SC3001;Ana Souza;POO;8.5",
                "SC3001;Ana Souza;POO;7.0",
                "SC3001;Ana Souza;BD;5.0",
                "SC3002;Bruno Lima;POO;4.0",
                "SC3002;Bruno Lima;POO;6.5",
                "SC3002;Bruno Lima;BD;9.0",
                "SC3003;Carla Dias;POO;10.0",
                "SC3003;Carla Dias;BD;abc",
                "SC3004;Davi Reis;POO",
                "SC3003;Carla Dias;BD;11.5",
                "SC3001;Ana Souza;BD;6.0",
                "SC3003;Carla Dias;BD;7.5",
                "",
                "SC3005;Eva Melo;POO;3.0",
                "SC3005;Eva Melo;BD;4.5"
        );
    }
}

