package com.gojek.vehicle;

public abstract class Vehicle {
    protected String licensePlate;
    protected String Colour;
    public String getColour() {
        return this.Colour;
    }

    public String getLicensePlate() {
        return this.licensePlate;
    }

}
