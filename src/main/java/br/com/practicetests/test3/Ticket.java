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

    public String getCode() {
        return code;
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

        long roundedHours = (long) Math.ceil(lengthOfStay().toMillis() / 3600000.0);
        return roundedHours * hourlyRate;
    }
}
