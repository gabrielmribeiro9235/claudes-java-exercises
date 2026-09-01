package br.com.practicetests.test3;

public class Vehicle {
    private final String licensePlate;
    private final String model;

    public Vehicle(String licensePlate, String model) {
        if (isLicensePlateValid(licensePlate)) {
            this.licensePlate = licensePlate;
            this.model = model;
        } else {
            throw new IllegalArgumentException("Invalid license plate");
        }
    }

    public boolean isLicensePlateValid(String licensePlate) {
        for (int i = 0; i < licensePlate.length(); i++) {
            if ((i < 3 || i == 4) && !Character.isUpperCase(licensePlate.charAt(i))) {
                return false;
            }
            if (!Character.isDigit(licensePlate.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getVehicleAsString() {
        return "License plate: " + licensePlate + "\nModel: " + model;
    }
}
