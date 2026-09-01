package br.com.practicetests.test3;

import java.time.LocalDateTime;

public class ParkingLot {
    private final int numberOfSpots;
    private final Ticket[] tickets;
    private int numberOfTickets;
    private final double hourlyRate;

    public ParkingLot(int numberOfSpots, double hourlyRate) {
        this.numberOfSpots = numberOfSpots;
        this.hourlyRate = hourlyRate;
        tickets = new Ticket[numberOfSpots];
    }

    public Ticket registerEntry(Vehicle v) {
        if (isVehicleAlreadyParked(v) || availableSpots() == 0) return null;

        String code = v.getLicensePlate() + LocalDateTime.now();

        tickets[numberOfTickets++] = new Ticket(code, v);

        return tickets[numberOfTickets - 1];
    }

    private boolean isVehicleAlreadyParked(Vehicle v) {
        for (int i = 0; i < numberOfTickets; i++) {
            if (tickets[i].getVehicle().getLicensePlate().equals(v.getLicensePlate())) {
                return true;
            }
        }

        return false;
    }

    public double registerDeparture(String licensePlate) {
        int idxToRemove = -1;

        for (int i = 0; i < numberOfTickets; i++) {
            if (tickets[i].getVehicle().getLicensePlate().equals(licensePlate)) {
                idxToRemove = i;
                break;
            }
        }

        if (idxToRemove == -1) return 0;

        tickets[idxToRemove].finalizeTicket();

        double total = tickets[idxToRemove].amountCharged(hourlyRate);

        for (int i = idxToRemove; i < numberOfTickets; i++) {
            tickets[i] = tickets[i+1];
        }

        tickets[numberOfTickets - 1] = null;

        numberOfTickets--;

        return total;
    }

    public int availableSpots() {
        return numberOfSpots - numberOfTickets;
    }

    public Vehicle[] vehiclesInTheParkingLot() {
        Vehicle[] vehicles = new Vehicle[numberOfTickets];

        for (int i = 0; i < numberOfTickets; i++) {
            vehicles[i] = tickets[i].getVehicle();
        }

        return vehicles;
    }
}
