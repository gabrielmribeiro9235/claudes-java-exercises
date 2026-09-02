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
        char[] charArray = licensePlate.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if ((i == 0 || i == 1 || i == 2 || i == 4) && (!Character.isLetter(c) || !Character.isUpperCase(c))) {
                return false;
            } else if ((i == 3 || i == 5 || i == 6) && !Character.isDigit(c)) {
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
