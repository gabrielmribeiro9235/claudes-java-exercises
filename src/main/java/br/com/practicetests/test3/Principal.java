package br.com.practicetests.test3;

import java.util.Locale;

public class Principal {
    static void main() {
        Vehicle peugeout206 = new Vehicle("KCQ3D54", "Peugeout 206");
        Vehicle nissanVersa = new Vehicle("ABC1D11", "Versa");
        Vehicle fusca = new Vehicle("DDI5K66", "Fusca");

        System.out.println("-----------------------------------------------");
        System.out.println("Vehicles\n");

        System.out.println(peugeout206.getVehicleAsString());
        System.out.println();
        System.out.println(nissanVersa.getVehicleAsString());
        System.out.println();
        System.out.println(fusca.getVehicleAsString());

        ParkingLot parkingLot = new ParkingLot(2, 30);


        Ticket peugeoutTicket = parkingLot.registerEntry(peugeout206);

        System.out.println("-----------------------------------------------");
        System.out.println("Parking the Peugeout\n");

        if (peugeoutTicket == null) {
            System.out.println("Parking Lot is full");
        } else if (peugeoutTicket.getCode() == null) {
            System.out.println("Peugeout is already parked");
        } else {
            System.out.println("Peugeout successfully parked");
        }

        Ticket versaTicket = parkingLot.registerEntry(nissanVersa);

        System.out.println("-----------------------------------------------");
        System.out.println("Parking the Versa\n");

        if (versaTicket == null) {
            System.out.println("Parking Lot is full");
        } else if (versaTicket.getCode() == null) {
            System.out.println("Versa is already parked");
        }else {
            System.out.println("Versa successfully parked");
        }

        Ticket fuscaTicket = parkingLot.registerEntry(fusca);

        System.out.println("-----------------------------------------------");
        System.out.println("Parking the Fusca\n");

        if (fuscaTicket == null) {
            System.out.println("Parking Lot is full");
        } else if (fuscaTicket.getCode() == null) {
            System.out.println("Fusca is already parked");
        } else {
            System.out.println("Fusca successfully parked");
        }

        System.out.println("-----------------------------------------------");
        System.out.println("Vehicles in the Parking Lot\n");

        Vehicle[] vehiclesParked = parkingLot.vehiclesInTheParkingLot();

        for (Vehicle vehicle : vehiclesParked) {
            System.out.println(vehicle.getVehicleAsString());
            System.out.println();
        }

        System.out.println("-----------------------------------------------");
        System.out.println("Versa is leaving the Parking Lot\n");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        double valueToPay = parkingLot.registerDeparture(nissanVersa.getLicensePlate());
        System.out.printf(Locale.US, "Value to pay: US$ %.2f\n", valueToPay);

        System.out.println("-----------------------------------------------");
        System.out.println("Vehicles in the Parking Lot\n");

        vehiclesParked = parkingLot.vehiclesInTheParkingLot();

        for (Vehicle vehicle : vehiclesParked) {
            System.out.println(vehicle.getVehicleAsString());
            System.out.println();
        }

        fuscaTicket = parkingLot.registerEntry(fusca);

        System.out.println("-----------------------------------------------");
        System.out.println("Parking the Fusca\n");

        if (fuscaTicket == null) {
            System.out.println("Parking Lot is full");
        } else if (fuscaTicket.getCode() == null) {
            System.out.println("Fusca is already parked");
        } else {
            System.out.println("Fusca successfully parked");
        }

        Ticket fuscaTicket2 = parkingLot.registerEntry(fusca);

        System.out.println("-----------------------------------------------");
        System.out.println("Trying to park the Fusca again\n");

        if (fuscaTicket2 == null) {
            System.out.println("Parking Lot is full");
        } else if (fuscaTicket2.getCode() == null) {
            System.out.println("Fusca is already parked");
        } else {
            System.out.println("Fusca successfully parked");
        }

        System.out.println("-----------------------------------------------");
        System.out.println("Vehicles in the Parking Lot\n");

        vehiclesParked = parkingLot.vehiclesInTheParkingLot();

        for (Vehicle vehicle : vehiclesParked) {
            System.out.println(vehicle.getVehicleAsString());
            System.out.println();
        }
    }
}
