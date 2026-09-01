package br.com.practicetests.test3;

import java.time.Duration;
import java.time.LocalDateTime;

public class Ticket {
    private final String code;
    private final Vehicle vehicle;
    private final LocalDateTime entryTime;
    private LocalDateTime departureTime;

    public Ticket(String code, Vehicle vehicle) {
        this.code = code;
        this.vehicle = vehicle;
        entryTime = LocalDateTime.now();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void finalizeTicket() {
        departureTime = LocalDateTime.now();
    }

    public boolean isOpened() {
        return departureTime == null;
    }

    public Duration lengthOfStay() {
        return Duration.between(entryTime, departureTime);
    }

    public double amountCharged(double hourlyRate) {
        if (isOpened()) return 0;

        int roundedHours = Math.ceilDiv(Math.toIntExact(lengthOfStay().toMinutes()), 60);

        return roundedHours * hourlyRate;
    }
}
