package com.gojek.parkinglot;

public interface IParkingLot {

    public void printStatus();

    public void unpark();

    public void park();

    public void createParkingLot(String size);

    public String getParkingSpotForRegistration(String number);

    public String getParkingSpotForColour(String colour);


}
