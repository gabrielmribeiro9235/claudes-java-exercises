package br.com.exercises.apidatetimetask;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class Task {
    private final String description;
    private final LocalDateTime start;
    private final LocalDateTime end;

    public Task(String description, LocalDateTime start, LocalDateTime end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    private Duration duration() {
        if (end == null) return Duration.between(start, LocalDateTime.now());

        return Duration.between(start, end);
    }

    public String formattedDuration() {
        return duration().toHoursPart() + "h " + duration().toMinutesPart() + "min";
    }

    public boolean isLate(LocalDateTime deadline) {
        if (end == null) return deadline.isBefore(LocalDateTime.now());

        return deadline.isBefore(end);
    }

    public String getStateAsString() {
        StringBuilder builder = new StringBuilder();
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add(builder.append("Description: ").append(description));

        builder.setLength(0);

        joiner.add(builder.append("Start: ").append(start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));

        builder.setLength(0);

        joiner.add(builder.append("End: ").append(end == null ? LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));

        return joiner.toString();
    }
}
