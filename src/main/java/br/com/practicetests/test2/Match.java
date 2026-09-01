package br.com.practicetests.test2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Match {
    private final String code;
    private final Player player1;
    private final Player player2;
    private final LocalDateTime time;
    private String result;

    public Match(String code, Player player1, Player player2, LocalDateTime time) {
        if (isCodeValid(code)) {
            this.code = code;
            this.player1 = player1;
            this.player2 = player2;
            this.time = time;
        } else {
            throw new IllegalArgumentException("Invalid code format");
        }
    }

    public LocalDateTime getTime() {
        return time;
    }

    private static boolean isCodeValid(String code) {
        return code.length() == 3 && code.charAt(0) == 'P' && Character.isDigit(code.charAt(1)) && Character.isDigit(code.charAt(2));
    }

    public void registerResult(String result) {
        if (result.equals("1-0") || result.equals("0-1") || result.equals("1/2-1/2")) {
            this.result = result;
        }
    }

    public boolean isDone() {
        return LocalDateTime.now().isAfter(time) && result != null;
    }

    public Player getWinner() {
        if (!isDone()) return null;

        return switch (result) {
            case "1-0" -> player1;
            case "0-1" -> player2;
            default -> null;
        };
    }

    public String getMatchAsString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Code: ").append(code);

        builder.append("\n\nPlayer 1:\n");
        builder.append(player1.getPlayerAsString());

        builder.append("\n\nPlayer 2:\n");
        builder.append(player2.getPlayerAsString());

        builder.append("\n\nStart time: ").append(time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));

        builder.append("\nWinner: ").append(isDone() ? getWinner() == null ? "draw" : getWinner().getName() : "match is not over");

        return builder.toString();
    }
}
