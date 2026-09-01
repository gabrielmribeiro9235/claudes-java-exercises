package br.com.practicetests.test2;

import java.time.LocalDateTime;
import java.util.StringJoiner;

public class Tournament {
    private final String name;
    private final Match[] matches;
    private int numberOfMatches;
    private String nextCode;
    private final LocalDateTime startTime;

    public Tournament(String name, LocalDateTime startTime) {
        this.name = name;
        this.startTime = startTime;
        matches = new Match[100];
        nextCode = "P01";
    }

    public String getName() {
        return name;
    }

    public Match scheduleMatch(Player p1, Player p2, int minutesAfterStart) {
        Match match = new Match(nextCode, p1, p2, startTime.plusMinutes(minutesAfterStart));

        matches[numberOfMatches++] = match;

        nextCode = "P" + (numberOfMatches + 1 >= 10 ? (numberOfMatches + 1) : "0" + (numberOfMatches + 1));

        return match;
    }

    public Match[] pendingMatches() {
        Match[] matchesStartedThatAreNotDone = new Match[numberOfMatches];
        int counter = 0;

        for (int i = 0; i < numberOfMatches; i++) {
            if (matches[i].getTime().isBefore(LocalDateTime.now()) && !matches[i].isDone()) {
                matchesStartedThatAreNotDone[counter++] = matches[i];
            }
        }

        return matchesStartedThatAreNotDone;
    }

    public Player champion() {
        return matches[numberOfMatches - 1].getWinner();
    }

    public String showAllMatches() {
        StringJoiner joiner = new StringJoiner("\n----------------------------------------\n");
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < numberOfMatches; i++) {
            joiner.add(builder.append("Match ").append(i + 1).append(":\n\n").append(matches[i].getMatchAsString()));
            builder.setLength(0);
        }

        return joiner.toString();
    }

}
