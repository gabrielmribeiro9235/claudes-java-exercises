package br.com.practicetests.test2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Principal {
    static void main() {
        Player jonny = new Player("Jonny", 2500);
        Player daniel = new Player("Daniel", 2532);
        Player gilian = new Player("Gilian", 2550);
        Player anthony = new Player("Anthony", 2483);

        System.out.println("----------------------------------------");
        System.out.println("Players\n");

        System.out.println(jonny.getPlayerAsString());
        System.out.println();
        System.out.println(daniel.getPlayerAsString());
        System.out.println();
        System.out.println(gilian.getPlayerAsString());
        System.out.println();
        System.out.println(anthony.getPlayerAsString());

        Tournament tournament = new Tournament("Chess tournament", LocalDateTime.of(LocalDate.of(2026, 9, 1), LocalTime.of(6, 0)));

        Match jonnyVsDaniel = tournament.scheduleMatch(jonny, daniel, 0);
        Match gilianVsAnthony = tournament.scheduleMatch(gilian, anthony, 0);

        System.out.println("----------------------------------------");
        System.out.println("Jonny is going to play against Daniel\n");
        System.out.println("Gilian is going to play against Anthony");

        System.out.println("----------------------------------------");
        System.out.println("Pending matches");

        Match[] pendingMatches = tournament.pendingMatches();

        int i = 0;
        while (i < pendingMatches.length && pendingMatches[i] != null){
            Match pendingMatch = pendingMatches[i];
            System.out.println();
            System.out.println(pendingMatch.getMatchAsString());
            System.out.println("----------------------------------------");
            i++;
        }

        jonnyVsDaniel.registerResult("1/2-1/2");
        gilianVsAnthony.registerResult("0-1");

        System.out.println("Results\n");
        System.out.println(tournament.showAllMatches());

        System.out.println("----------------------------------------");
        System.out.println("Pending matches\n");

        pendingMatches = tournament.pendingMatches();

        i = 0;
        while (i < pendingMatches.length && pendingMatches[i] != null){
            Match pendingMatch = pendingMatches[i];
            System.out.println();
            System.out.println(pendingMatch.getMatchAsString());
            System.out.println("----------------------------------------");
            i++;
        }

        Match finalOfTheTournament = tournament.scheduleMatch(jonny, anthony, 180);

        System.out.println("----------------------------------------");
        System.out.println("Final\n");

        System.out.println(finalOfTheTournament.getMatchAsString());

        finalOfTheTournament.registerResult("1-0");

        System.out.println("----------------------------------------");
        System.out.println("Champion\n");

        System.out.println(tournament.champion().getPlayerAsString());

        System.out.println("----------------------------------------");
        System.out.println("Matches of the \"" + tournament.getName() + "\"\n\n");

        System.out.println(tournament.showAllMatches());

        System.out.println("----------------------------------------");
        System.out.println("Champion: " + tournament.champion().getName());
    }
}
