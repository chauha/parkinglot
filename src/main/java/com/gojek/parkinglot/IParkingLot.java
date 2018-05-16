package com.gojek.parkinglot;

import com.gojek.spot.ParkingSpot;
import com.gojek.vehicle.Car;


public interface IParkingLot {

    public void printStatus();

    public void unpark(ParkingSpot spot);

    public void park(Car input);

    public void createParkingLot(String size);

    public String getParkingSpotForRegistration(String number);

    public String getParkingSpotForColour(String colour);


}
