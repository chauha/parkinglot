package com.gojek.vehicle;

public class Car extends Vehicle {


    public Car(String colour, String license) {
        this.Colour = colour;
        this.licensePlate = license;

    }

    public String getColour() {
        return this.Colour;
    }

    public String getLicensePlate() {
        return this.licensePlate;
    }
}
